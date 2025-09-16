package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * <h1>--- Day 10: Hoof It ---</h1>
 * <p>You all arrive at a Lava Production Facility on a floating island in the
 * sky. As the others begin to search the massive industrial complex, you feel a
 * small nose boop your leg and look down to discover a reindeer wearing a hard
 * hat. As you're about to ask how you can help, the reindeer brings you a blank
 * topographic map of the surrounding area. The reindeer gleefully carries over
 * a protractor and adds it to the pile. What is the sum of the scores of all
 * trailheads on your topographic map?</p>
 * <p><a href="https://adventofcode.com/2024/day/10">2024 Day 10</a></p>
 */
public class Day10 extends AbstractProblem<List<String>, Integer> {

    int[][] grid;

    static void main() {
        new Day10().setAndSolve(Util.readInput(2024, 10));
    }

    @Override
    protected Integer solve1() {
        grid = new int[input.size()][input.size()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = input.get(y).charAt(x) - ASCII_OFFSET;
            }
        }
        int trailScore = 0;
        List<Coord> trails = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 0) {
                    List<Coord> trailEnds = getTrailEnds(Coord.of(x, y), 0);
                    trails.addAll(trailEnds);
                    trailScore += new HashSet<>(trailEnds).size();
                }
            }
        }
        answer2 = trails.size();
        return trailScore;
    }

    private List<Coord> getTrailEnds(Coord currentCoord, int stepsTaken) {
        if (stepsTaken == 9) {
            return List.of(currentCoord);
        }
        List<Coord> trailsEnds = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (getGrid(currentCoord.x() + d.dx(), currentCoord.y() + d.dy()) == stepsTaken + 1) {
                trailsEnds.addAll(getTrailEnds(currentCoord.addDirection(d), stepsTaken + 1));
            }
        }
        return trailsEnds;
    }

    private int getGrid(int x, int y) {
        try {
            return grid[y][x];
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }
}
