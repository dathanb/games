package com.jedaway.sorting;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.jedaway.game.Engine;
import com.jedaway.game.MaxStrategy;
import com.jedaway.game.MoveGenerator;
import com.jedaway.game.MoveStrategy;
import com.jedaway.game.PositionEvaluator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SmokeTest {
    @Test
    public void runSortingGame() throws InterruptedException {
        Random random = new Random(1);
        SortingGame sortingGame = SortingGame.randomGame(random);

        PositionEvaluator<SortingGame, SortingGameMove> positionEvaluator = new OrderingPositionEvaluator();
        MoveGenerator<SortingGame, SortingGameMove> moveGenerator = new SortingGameMoveGenerator(new Random(1));
        MetricRegistry metrics = new MetricRegistry();
        MoveStrategy<SortingGame, SortingGameMove> maxStrategy = new MaxStrategy<>(positionEvaluator, moveGenerator, metrics);
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        Engine<SortingGame, SortingGameMove> engine = new Engine<>(sortingGame, maxStrategy, metrics);
        reporter.report();

        System.out.println("Starting position:\n" + sortingGame.toString());
        List<SortingGameMove> run = engine.run();
        System.out.println(String.format("Solved in %d moves: %s", run.size(), run.toString()));
        Thread.sleep(1000);
    }
}
