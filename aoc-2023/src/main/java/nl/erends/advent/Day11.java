package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>--- Day 11: Cosmic Expansion ---</h1>
 * <p>You continue following signs for "Hot Springs" and eventually come across
 * an observatory. The Elf within turns out to be a researcher studying cosmic
 * expansion using the giant telescope here. Due to something involving
 * gravitational effects, only some space expands. In fact, the result is that
 * any rows or columns that contain no galaxies should all actually be twice as
 * big. Find the length of the shortest path between every pair of galaxies.
 * What is the sum of these lengths?</p>
 * <p><a href="https://adventofcode.com/2023/day/11">2023 Day 11</a></p>
 */
public class Day11 extends AbstractProblem<List<String>, Number> {


    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readInput(2023, 11));
    }

    @Override
    protected Number solve1() {
        return getDistanceAfterExpansionFactor(2);
    }

    @Override
    public Number solve2() {
        return getDistanceAfterExpansionFactor(1_000_000);
    }

    public long getDistanceAfterExpansionFactor(int factor) {
        List<Galaxy> galaxies = new ArrayList<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    galaxies.add(new Galaxy(x, y));
                }
            }
        }
        for (int y = input.size() - 1; y >= 0; y--) {
            int yF = y;
            if (galaxies.stream().noneMatch(g -> g.y == yF)) {
                galaxies.stream().filter(g -> g.y > yF).forEach(g -> g.y += factor - 1);
            }
        }
        for (int x = input.get(0).length() - 1; x >= 0; x--) {
            int xF = x;
            if (galaxies.stream().noneMatch(g -> g.x == xF)) {
                galaxies.stream().filter(g -> g.x > xF).forEach(g -> g.x += factor - 1);
            }
        }
        long distanceTotal = 0;
        while (!galaxies.isEmpty()) {
            Galaxy one = galaxies.remove(0);
            for (Galaxy other : galaxies) {
                long distance = Math.abs(one.x - other.x) + Math.abs(one.y - other.y);
                distanceTotal += distance;
            }
        }
        return distanceTotal;

    }

    private static class Galaxy {

        long x;
        long y;

        public Galaxy(final long x, final long y) {
            this.x = x;
            this.y = y;
        }
    }
}
