package com.jedaway.nonogram;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solver {
    private final NonogramGame initialPosition;

    private final List<NonogramGame> positions;
    private final List<NonogramMove> moves;
    private final NonogramMoveGenerator moveGenerator;
    private final NonogramMoveStrategy moveStrategy;

    public Solver(NonogramGame initialPosition) {
        this.initialPosition = initialPosition;
        this.moves = new ArrayList<>();
        this.positions = new ArrayList<>();
        this.positions.add(initialPosition);
        this.moveGenerator = new NonogramMoveGenerator();
        moveStrategy = new NonogramMoveStrategy(moveGenerator);
    }

    public List<NonogramMove> solve() {
        while (!getCurrentPosition().isTerminal()) {
            pickAndMakeMove();
        }
        return ImmutableList.copyOf(moves);
    }

    private void pickAndMakeMove() {
        Optional<NonogramMove> nextMove = moveStrategy.chooseMove(getCurrentPosition());
        nextMove.ifPresent(this::makeMove);
        if (!nextMove.isPresent()) {
            System.out.println("Failed to find a move!");
            System.exit(1);
        }
    }


    private NonogramGame getCurrentPosition() {
        return positions.get(positions.size() - 1);
    }

    private void makeMove(NonogramMove move) {
        moves.add(move);
        positions.add(getCurrentPosition().apply(move));
    }
}
