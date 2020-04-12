package com.jedaway.game;

/**
 * Engine is the class responsible for running the game.
 */
public class Engine<GameType extends Game<MoveType>, MoveType extends Move> {
    private final GameType game;
    private final MoveStrategy<GameType, MoveType> strategy;

    public Engine(GameType game, MoveStrategy<GameType, MoveType> strategy) {
        this.game = game;
        this.strategy = strategy;
    }

    public MoveType[] run() {
        return null;
    }
}
