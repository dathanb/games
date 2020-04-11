# TL;DR

Contains implementations of engines and "AI"s for a few different games:
- Nonograms
- Sorting game

# Games

My goal in working on this project has been to iteratively build a generic engine that plays games that conform to
a shared model of a what a game looks like, and implementations of various games.

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

