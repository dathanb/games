package com.jedaway.game;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Engine is the class responsible for running the game.
 */
public class Engine<GameType extends Game<GameType, MoveType>, MoveType extends Move> {
    private final GameType initialGameState;
    private final MoveStrategy<GameType, MoveType> strategy;
    private final ArrayList<MoveType> moves;
    private GameType currentGameState;

    public Engine(GameType initialGameState, MoveStrategy<GameType, MoveType> strategy) {
        this.initialGameState = initialGameState;
        this.currentGameState = initialGameState;
        this.strategy = strategy;
        this.moves = new ArrayList<>();
    }

    public List<MoveType> run() {
        while (!getCurrentGameState().isTerminal()) {
            pickAndMakeMove();
        }
        return ImmutableList.copyOf(moves);
    }

    private void pickAndMakeMove() {
        Optional<MoveType> nextMove = strategy.chooseMove(currentGameState);
        nextMove.ifPresent(this::makeMove);
        if (!nextMove.isPresent()) {
            System.out.println("Failed to find a move!");
            System.out.println("Recorded moves: " + moves.toString());
            System.exit(1);
        }
    }

    private GameType getCurrentGameState() {
        return currentGameState;
    }

    private void makeMove(MoveType move) {
        moves.add(move);
        currentGameState = currentGameState.apply(move);
    }
}
