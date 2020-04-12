package com.jedaway.nonogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Solver {
    private final NonogramPuzzle puzzle;
    private final NonogramGame initialPosition;

    private final List<NonogramGame> positions;
    private final List<NonogramMove> moves;
    private final NonogramMoveGenerator moveGenerator;
    private final NonogramMoveStrategy moveStrategy;

    public Solver(NonogramPuzzle puzzle, NonogramGame initialPosition) {
        this.puzzle = puzzle;
        this.initialPosition = initialPosition;
        this.moves = new ArrayList<>();
        this.positions = new ArrayList<>();
        this.positions.add(initialPosition);
        this.moveGenerator = new NonogramMoveGenerator();
        moveStrategy = new NonogramMoveStrategy(moveGenerator);
    }

    public NonogramMove[] solve() {
        while (!getCurrentPosition().isTerminal()) {
            Optional<NonogramMove> nextMove = moveStrategy.chooseMove(getCurrentPosition());
            nextMove.ifPresent(this::makeMove);
            if (!nextMove.isPresent()) {
                System.out.println("Failed to find a move!");
                System.exit(1);
            }
        }
        return moves.toArray(new NonogramMove[]{});
    }


    private NonogramGame getCurrentPosition() {
        return positions.get(positions.size() - 1);
    }

    private void makeMove(NonogramMove move) {
        moves.add(move);
        positions.add(getCurrentPosition().apply(move));
    }
}
