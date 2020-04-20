# TL;DR

Contains implementations of engines and "AI"s for a few different games:
- Nonograms
- Sorting game

# Games

My goal in working on this project has been to iteratively build a generic engine that plays games that conform to
a shared model of a what a game looks like, and implementations of various games.

# General

TODO:
- [X] Instead of using logging for perf analysis, start recording metrics explicitly

# Setup

In order to get log output while running tests, you'll want to specify a system property: 
`-Dlog4j.configuration=file://path/to/src/main/resources/jog4j.properties`

## SortingGame

TODO:
- [X] Use shallow copy for calculating new positions
- [X] Cache bucket instances
    - Won't do, because buckets are now mostly just `long`s
- [ ] Cache bucket scores
- [-] Use arrays for bucket contents
- [X] Use integers for bucket contents
- [X] Detect terminal states operationally during move evaluation
- [ ] Use integer array for SortingGame state
- [ ] Make MaxStrategy stateful to reduce redundant move evaluations
- [ ] Multithreaded MaxStrategy
- [ ] Alpha pruning
- [ ] Prioritize good positions
- [ ] Deprioritize bad positions
- [ ] Early-return based on relative threshold instead of absolute

#Journal

## 2020-04-11

When implementing the first game, Nonograms, I made some assumptions that the game would have a `Position` that's usable
distinct from the `Game` state, and that there would be a `Puzzle` associated with the game somehow. I think it's clear
that that's not a valid assumption.

Well, maybe the `Position` is, if for two-player games we encode whose turn it is into the `Position`. For, e.g., chess,
we'd also need to encode things like whether each player has castled, what the previous move was (for en passent moves),
and possibly some component of move history (e.g., hash codes for all previous positions) for determining draw by
threefold repetition.

What's the purpose of a `Position` that's distinct from the `Game` state, anyway? I got the idea of creating that model
from the practice of hashing Chess positions to reducing time wasted searching duplicate positions. In *most* cases
a hash of just the board position without regard to game history works fine. But then you have to take into account
the game history to find out whether two positions that are otherwise identical are *really* identical. e.g., the first
time you visit a position is different from the third time you visit the position, since the first time can be evaluated
on its own merits, whereas the third time it results in a draw.

Let's not worry about that until we start working on chess -- it's a more complicated game than the ones we're dealing
with right now.

So for starters, let's do away with the `Puzzle` abstraction. We'll move it into the `nongram` package, since it's not
part of the generic game engine. We might at some point specialize the engine for puzzle games, in which case th engine
might want to be able to check whether a position is valid according to the puzzle, but for a while we can just count
on the move evaluator doing that for us.

And then let's do away with the `Position` abstraction in favor of just the `Game` abstraction. Since some games need
to take history into account to hash effectively but some don't, we'll just leave it up to the individual move evaluators
to do the right thing. They're black boxes anyway right now, so we don't care how they work internally or whether they
make a distinction between the two.

OK, so those two are done. Now let's move the "apply move" function into the Game.

Let's take a step back.

What does the game loop look like?

The engine takes the base game state -- that's the authoritative state of the game -- and wants to pick a new move.
It does that by taking the configured `MoveGenerator` (we could put the logic for generating moves from a game state
into the `Game` itself, but there may be games for which we can intelligently generate a subset of moves to examine; in
that case, since there's a naive, brute-force version and a smart version, it might make sense to tease that
functionality out into a `MoveGenerator` that can be swapped out for a different strategy) to enumerate moves from the
current state. For each move, it passes the game state and the Move to a `MoveEvaluator`, which applies the move to the
current Game state and then evaluates the quality of the resulting game state. Then the `MoveEvaluator` asks the
`MoveGenerator` to generate all moves from the new position, and evaluates them recursively. It terminates according
to some termination function (target position value achieved, or time elapsed, or target depth reached), returning the
move with the highest expected value.

I wonder if we should pull apart the `MoveEvaluator` into a `MoveDecider` that has the optimization logic and a
`PositionEvaluator` that just has the logic for evaluating a single position? That makes sense to me.

Cool. So before making progress on the sorting game, let's rewrite the Nonogram game according to those rules.

I think that's enough for today -- I've pulled out the `NonogramMoveGenerator` and the `NonogramMoveStrategy`,
and the `com.jedaway.games.nonogram.Solver` is getting less nonogram-specific moment by moment. I hope to be able to
incrementally generify it so that I can just pull it out as the `GameEngine`, and then write a `SortingGame` implementation
of all the relevant components. This is so much fun! But it's almost 11:00pm, so time for bed -- I might be able to
work on it again tomorrow.

## 2020-04-12

Got the Engine fully generified. I'm not crazy about `Game<GameType extends Game<GameType, MoveType>, MoveType extends Move<GameType>>`
as a type signature, but it doesn't seem like it's going to get a lot worse, so it's ok for now.

Once I get to a game that's a little fuzzier than Nonograms (e.g., the SortingGame), I'm thinking it'd be cool to
extract the `MoveStrategy` and `MoveEvaluator` into stateful services. Then they could accept callbacks from the
`Engine` to prune any state they're keeping around once it's no longer relevant.

Also I'd like to play around with targeting Akka for all the components. I don't think it'd be that different, and it
would actually offer a little leverage on parallelizing things like the `MoveEvaluator`. Plus it'll make adding things
like time constraints to the `MoveStrategy` more natural. e.g., have the strategy report the best move it's found so
far at regular intervals. Then when the `Engine` decides that it's waited long enough, it can just pick the best move
reported so far and run with it. Or, the `MoveStrategy` can spawn multiple sub-strategies and do that sort of timeout
logic on its own. And if we avoid trying to pass around lambdas, Akka will give us leverage on parallelizing across
multiple computers.

## 2020-04-14

I've implemented a depth-1 max search algorithm. Now I need to make it recursive. I'm not sure the right way to do it.
We want to pick the single move that ends in the maximum value at our max search depth.

So from a given game state, we have a list of possible moves. Each move M results in a new game state Gm that has a
score Sm. That's easy enough -- we put those three into a single tuple and order them by score.

Once we've finished that level of depth, we store it and we move on to the next level.

How do we represent that? I can think of two approaches offhand;
1. We store each distinct path (where a path that terminates at M1 is different from a path that terminates at M1.1)
   as a separate entry.
2. We store move paths as a tree, and as we encounter a new max route, we update the tree.

The tree seems like the more natural representation -- let's pursue that route.

We'll have to get a little creative to support hashing irrespective of move history, but that can wait until later, I
think.

## 2020-04-15

I keep hitting edge cases in my `MaxStrategy`. Now what I've observed is that if at our configured depth we see two paths
that have the same expected value, we'll choose the first one that was encountered. Then on the next round, we're likely
to just move the piece right back where it came from, because the two paths were equally valuable (so going back to
where we started is as valuable as the path we took, because we can always take the same or a different path again, and
the current path doesn't resolve to something significantly better in time to show up in the path analysis). I think we
either want to find a way to penalize moving "backward", or to impose some ordering on the moves instead of doing a straight
breadth-first traversal. By imposing some bias on the movements (or since they're already biased, by randomizing and
removing that bias) we can probably avoid this. The real problem is that the move generation for the SortingGame is
deterministic and creates the likelihood of loops when there isn't a clear reason to choose one move over another.

So if we just shuffle our moves before returning them from the MoveGenerator, that may fix this!

It seemed to help a little bit -- but it's still possible for it to get into a loop.

I guess we could just bias the thing to pick the first move that's *better* than the current state? But it already does
that, so nevermind.

We could try to implement a penalty system where each additional move is a -1 penalty. But right now the MaxStrategy
only updates the max score when it's greater than the current score.

## 2020-04-16

I can solve SortingGame consistently using MaxStrategy, OrderingPositionEvaluator, and maxDepth=3. Single-threaded, 
though, we can't really do maxDepth=4 on 13 colors. Firstly, I'd like to make the MaxStrategy stateful instead of 
stateless. We can save a LOT of processing if we don't redundantly evaluate all the intermediate positions. That could
be enough to get us an additional level of depth.

I don't think it necessarily makes sense to have a `StatelessMaxStrategy` and a `StatefulMaxStrategy` as separate 
implementations. Instead, I think maybe we could pull out some sort of state provider that the `MaxStrategy` interacts
with, and the stateless strategy would just reset the state after each call.

Or, heck, let's just get rid of the stateless version altogether. That makes more sense to me -- let's make it only 
stateful.

But we'll need to share the stateful behavior between the MaxStrategy and the MinimaxStrategy, right? Unless MaxStrategy
turns out to be a subset of Minimax. That might work, but I'd have to look into it more. Rather than work on something
speculative like that -- Minimax doesn't really make sense for a purely self-cooperative game like this -- I want to
make incremental progress on the games I can.

That said, it might be that the biggest speed wins would come from making shallow copies of the `SortingGame` instead
of deep copies. That'd save us a LOT of data and GC time. And it's probably easier, so let's do that first.

Implementation plan / TODO:
- [X] make `SortingGame#apply` use shallow copies to make moves faster
- [ ] make MaxStrategy stateful
- [ ] 

## 2020-04-17

I implemented shallow copy for `SortingGame#apply`, and while I haven't tried to do any robust profiling, one example
ran in 1m46s using deep copy and 1m06s using shallow copy, which is really nice. So I think that's a winner.

## 2020-04-18

I added a TODO to cache bucket instances so we have a single copy of each bucket that we've calculated. In theory it
makes sense, but I'm not sure it really does, because the premise was to reduce allocations and array copies to speed
up execution and reduce memory pressure. However, in the current form we'd have to construct the Bucket first in order
to find it in the cache. That doesn't really reduce allocations much.

So instead of doing that, I'm going to make the change where we switch to an integer representation of Bucket first.
In order to make it easier, I'm going to settle on the maximum game size -- 16 colors (can be represented in 4 bits)
and buckets with capacity=16 (16 spaces * 4 bits = 64 bits, which will fill a java long).

Well, after rewriting to get rid of the List from the Bucket class, our performance is unchanged, or maybe a little
slower. I'm not sure why that would be, but it is. So, since the code is less clear and less flexible in this new
version, I'm not sure whether I want to keep it or not.

Let's evaluate more. I ran 15 colors, bucket size 4, search depth 3, and got similar performance in both the List
version and the integral version. So let's see if the integral version gives us any leverage in higher search depths
where we're more likely to see memory pressure.

It doesn't. But there's a good chance it's because we're still doing move calculation in a really naive way that's
actually shifting the execution elsewhere. I think if we give the `OrderingPositionEvaluator` access to the `Bucket`
internals, there's a good chance we can get it a lot faster.

Well, it's still slow. I bet a big part of it is that we're checking `SortingGame#isTerminal` a bunch of times.
Instead of that, I think we can special-case it: we know how many buckets there are and how deep they are, so we can
easily calculate the score of a terminal position. So let's skip the `isTerminal` check in favor of detecting terminal
positions after evaluating them. That should save us at least half our effort.

It didn't even make a dent in the calculation time for some of the depth-4 positions, as far as I can tell, but between
this and the previous optimization, we cut the time for the depth-3 calculation in half. That suggests that there's
some other limiting factor for speed in the depth-4 version?

## 2020-04-19

After noodling on it for a while, I'm not sure there's a ton of value in sticking with breadth-first search for
SortingGame. Since this type of puzzle isn't competitive, we don't really need to worry about exhaustively exploring
moves in case there's one that hoses us. And since we're just interesting in being able to solve large puzzles, not
in finding the most efficient solution, once we've found a "good" move we don't need to spend a lot of time evaluating
other moves -- let's make a "good" move and then see what the game looks like from there.

So I have three ideas:
1. Make our early-return threshold relative to the root score, and decrease it significantly (e.g., to 8).
2. Aggressively prune negative positions after a couple of levels
3. Prioritize our search based on position quality.

If we prioritize our search based on position quality, we can do away with the breadth-first search entirely, rigtht?
We just need to prune our backlog to keep from OOM'ing in the meantime.

But maybe we don't even need that. Let's start with just prioritizing good moves.

OK, so I've basically implemented depth-first search, prioritizing search of the best moves found so far. And an
interesting thing happened -- it makes an order of magnitude more moves, but solves the puzzle in the blink of an eye.

Turns out I was using a min-heap as a max-heap, to I'm a little surprised that it solved the puzzle at all, much less
in only an order of magnitude more moves. Fixed that, and tuned the parameters a little bit, and we have a blazing-
fast solution to the 4-capacity buckets puzzle.

Huh, doesn't work with deeper buckets. I'm guessing that we might be creating loops again: since all positions with a
given score will also score the same at a given depth, if we can't find a "good" position, we'll re-try positions we've
already evaluated (since they're lal essentially equally-valued).

I don't think we want to keep a cache of all those positions quite yet. Maybe a Bloom filter to just prevent re-visiting
any we've visited before?

So now where are we?

We could save a lot of time and computing effort by making the MaxMoveStrategy stateful, right? Keep the position tree
between calls. And we could either just trim it when asked to choose a move (we'd assume that we were asked to choose
a move and then our move was respected and no moves were made without consulting us; or we could throw away the tree
if that assumption was violated); or we could register the MaxMoveStrategy as a listener on the Engine, and when the
Engine made a move we'd get informed about it and update internal state to avoid keeping track of any moves that were
no longer relevant.

Let's do the former, just because it doesn't require introducing new APIs.
