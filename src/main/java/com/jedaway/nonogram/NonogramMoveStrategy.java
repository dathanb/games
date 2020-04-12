package com.jedaway.nonogram;

import java.util.Optional;

import static com.jedaway.nonogram.MoveEvaluation.WINNING;

public class NonogramMoveStrategy {
    private final NonogramMoveGenerator moveGenerator;

    public NonogramMoveStrategy(NonogramMoveGenerator moveGenerator) {
        this.moveGenerator = moveGenerator;
    }

    public Optional<NonogramMove> chooseMove(NonogramGame game) {
        for (NonogramMove move: moveGenerator.getMoves(game)) {
            if (new MoveEvaluator(move, game, game.getPuzzle()).evaluate() == WINNING) {
                return Optional.of(move);
            }
        }
        return Optional.empty();
    }
}
