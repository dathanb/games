package com.jedaway.nonogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.jedaway.nonogram.MoveEvaluation.WINNING;

public class Solver {
    private final NonogramPuzzle puzzle;
    private final NonogramGame initialPosition;

    private final List<NonogramGame> positions;
    private final List<NonogramMove> moves;
    private final MoveGenerator moveGenerator;

    public Solver(NonogramPuzzle puzzle, NonogramGame initialPosition) {
        this.puzzle = puzzle;
        this.initialPosition = initialPosition;
        this.moves = new ArrayList<>();
        this.positions = new ArrayList<>();
        this.positions.add(initialPosition);
        this.moveGenerator = new MoveGenerator();
    }

    public NonogramMove[] solve() {
        while (isIncomplete()) {
            boolean madeMove = false;
            for (NonogramMove move: moveGenerator.getMoves(getCurrentPosition())) {
                if (new MoveEvaluator(move, getCurrentPosition(), puzzle).evaluate() == WINNING) {
                    makeMove(move);
                    madeMove = true;
                    break;
                }
            }
            if (!madeMove) {
                System.out.println("Failed to find a move!");
                System.exit(1);
            }
        }
        return moves.toArray(new NonogramMove[]{});
    }

    private boolean isIncomplete() {
        return Arrays.stream(getCurrentPosition().getCells())
                .flatMap(Arrays::stream)
                .anyMatch(CellState::isEmpty);
    }

    private NonogramGame getCurrentPosition() {
        return positions.get(positions.size() - 1);
    }

    private void makeMove(NonogramMove move) {
        moves.add(move);
        positions.add(getCurrentPosition().apply(move));
    }
}
