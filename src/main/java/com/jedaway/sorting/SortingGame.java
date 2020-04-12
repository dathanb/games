package com.jedaway.sorting;

import com.jedaway.game.Game;
import com.jedaway.game.Move;

/**
 * An instance of the SortingGame up to a particular point in time, including move history.
 *
 * Its primary roles are (1) to support driving the game by transitioning from one game state to another via {@link Move}s, and (2) to support deduping
 * game states for the purpose of pruning search trees.
 */
public class SortingGame implements Game<SortingGameMove> {
}
