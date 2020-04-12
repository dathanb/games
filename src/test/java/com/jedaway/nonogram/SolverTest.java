package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    private NonogramGame getFinalPosition(NonogramGame position, NonogramMove[] moves) {
        for (NonogramMove move: moves) {
            position = position.apply(move);
        }
        return position;
    }

    @Test
    public void solve_CanSolveASimplePuzzle() {
        NonogramPuzzle puzzle = TestPuzzles.BUFFALO;
        NonogramGame position = puzzle.getInitialPosition();
        Solver solver = new Solver(puzzle, position);
        NonogramMove[] moves = solver.solve();
        NonogramGame solution = getFinalPosition(position, moves);
        System.out.println(solution.toString());
        assertEquals(TestPuzzles.BUFFALO_SOLUTION, solution);
    }
}
