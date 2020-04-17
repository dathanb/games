package com.jedaway.sorting;

import com.jedaway.game.Engine;
import com.jedaway.game.MaxStrategy;
import com.jedaway.game.MoveGenerator;
import com.jedaway.game.MoveStrategy;
import com.jedaway.game.PositionEvaluator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

public class SmokeTest {
    @Test
    public void runSortingGame() {
        Random random = new Random(1);
        SortingGame sortingGame = SortingGame.randomGame(random, 10, 4);

        PositionEvaluator<SortingGame, SortingGameMove> positionEvaluator = new OrderingPositionEvaluator();
        MoveGenerator<SortingGame, SortingGameMove> moveGenerator = new SortingGameMoveGenerator(new Random(1));
        MoveStrategy<SortingGame, SortingGameMove> maxStrategy = new MaxStrategy<>(positionEvaluator, moveGenerator, 4);
        Engine<SortingGame, SortingGameMove> engine = new Engine<>(sortingGame, maxStrategy);

        System.out.println("Starting position:\n" + sortingGame.toString());
        List<SortingGameMove> run = engine.run();
        System.out.println(String.format("Solved in %d moves: %s", run.size(), run.toString()));
    }
}
