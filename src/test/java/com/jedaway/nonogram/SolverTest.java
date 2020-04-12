package com.jedaway.nonogram;

import com.google.common.collect.Lists;
import com.jedaway.game.Engine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    private NonogramGame getFinalPosition(NonogramGame position, List<NonogramMove> moves) {
        for (NonogramMove move: moves) {
            position = position.apply(move);
        }
        return position;
    }

    @Test
    public void solve_CanSolveASimplePuzzle() {
        NonogramPuzzle puzzle = TestPuzzles.BUFFALO;
        NonogramGame position = puzzle.getInitialPosition();
        Solver solver = new Solver(position);
        List<NonogramMove> moves = solver.solve();
        System.out.println("Winning moves: " + Lists.newArrayList(moves).toString());
        NonogramGame solution = getFinalPosition(position, moves);
        System.out.println(solution.toString());
        assertEquals(TestPuzzles.BUFFALO_SOLUTION, solution);
    }

    @Test
    public void engine_solve_CanSolveASimplePuzzle() {
        NonogramPuzzle puzzle = TestPuzzles.BUFFALO;
        NonogramGame position = puzzle.getInitialPosition();

        NonogramMoveGenerator moveGenerator = new NonogramMoveGenerator();
        NonogramMoveStrategy strategy = new NonogramMoveStrategy(moveGenerator);
        Engine<NonogramGame, NonogramMove> engine = new Engine<>(position, strategy);
        List<NonogramMove> moves = engine.run();
        NonogramGame solution = getFinalPosition(position, moves);
        System.out.println(solution.toString());
        assertEquals(TestPuzzles.BUFFALO_SOLUTION, solution);
    }
}
