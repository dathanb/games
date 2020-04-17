package com.jedaway.sorting;

import com.jedaway.game.Engine;
import com.jedaway.game.MaxStrategy;
import com.jedaway.game.MoveGenerator;
import com.jedaway.game.MoveStrategy;
import com.jedaway.game.PositionEvaluator;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SmokeTest {
    @Test
    public void runSortingGame() {
        SortingGame sortingGame = SortingGame.randomGame(3, 3);

        PositionEvaluator<SortingGame, SortingGameMove> positionEvaluator = new OrderingPositionEvaluator();
        MoveGenerator<SortingGame, SortingGameMove> moveGenerator = new SortingGameMoveGenerator();
        MoveStrategy<SortingGame, SortingGameMove> maxStrategy = new MaxStrategy<>(positionEvaluator, moveGenerator, 3);
        Engine<SortingGame, SortingGameMove> engine = new Engine<>(sortingGame, maxStrategy);

        System.out.println("Starting position:\n" + sortingGame.toString());
        List<SortingGameMove> run = engine.run();
        System.out.println("Moves: " + run.toString());
    }
}
