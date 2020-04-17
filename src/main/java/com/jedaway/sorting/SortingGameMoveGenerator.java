package com.jedaway.sorting;

import com.google.common.collect.Sets;
import com.jedaway.game.MoveGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate moves for the SortingGame
 */
public class SortingGameMoveGenerator implements MoveGenerator<SortingGame, SortingGameMove> {

    @Override
    public List<SortingGameMove> getMoves(SortingGame game) {
        if (game.isTerminal()) {
            return Collections.emptyList();
        }

        List<SortingGameMove> moves = new ArrayList<>();
        List<Bucket> buckets = game.getBuckets();
        Set<Integer> sourceBuckets = new HashSet<>();
        for (int i = 0; i < game.getBuckets().size(); i++) {
            if (game.getBuckets().get(i).size() > 0) {
                sourceBuckets.add(i);
            }
        }

        Set<Integer> destinationBuckets = new HashSet<>();
        for (int i = 0; i < game.getBuckets().size(); i++) {
            if (game.getBuckets().get(i).size() < game.getBuckets().get(i).getCapacity()) {
                destinationBuckets.add(i);
            }
        }

        for (int src : sourceBuckets) {
            for (int dest : Sets.difference(destinationBuckets, Sets.newHashSet(src))) {
                if (buckets.get(src).size() > 0 && buckets.get(dest).size() < buckets.get(dest).getCapacity()) {
                    moves.add(new SortingGameMove(src, dest));
                }
            }
        }

        Collections.shuffle(moves); // eliminate bias in move ordering to minimize likelihood of the engine entering a loop
        return moves;
    }
}
