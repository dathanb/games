package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {

    private NonogramPosition getFinalPosition(NonogramPosition position, NonogramMove[] moves) {
        for (NonogramMove move: moves) {
            position = position.apply(move);
        }
        return position;
    }

    @Test
    public void foo() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<65536; i++) {
            sb.append(i).append(": ").append((char)i).append('\n');
        }
        System.out.println(sb.toString());
    }

    @Test
    public void solve_CanSolveASimplePuzzle() {
        NonogramPuzzle puzzle = TestPuzzles.BUFFALO;
        NonogramPosition position = puzzle.getInitialPosition();
        Solver solver = new Solver(puzzle, position);
        NonogramMove[] moves = solver.solve();
        NonogramPosition solution = getFinalPosition(position, moves);
        System.out.println(solution.toString());
        assertEquals(TestPuzzles.BUFFALO_SOLUTION, solution);
    }
}
