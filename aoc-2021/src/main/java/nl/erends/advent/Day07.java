package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;

/**
 * --- Day 7: The Treachery of Whales ---
 * <p>A giant whale has decided your submarine is its next meal. Suddenly, a
 * swarm of crabs zooms in to rescue you! Determine the horizontal position that
 * the crabs can align to using the least fuel possible. How much fuel must they
 * spend to align to that position?
 * <p><a href="https://adventofcode.com/2021/day/7">2021 Day 7</a>
 */
public class Day07 extends AbstractProblem<String, Integer> {
    
    static void main() {
        new Day07().setAndSolve(Util.readLine(2021, 7));
    }

    @Override
    protected Integer solve1() {
        int[] crabs = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int leastFuel = Integer.MAX_VALUE;
        int leastFuel2 = Integer.MAX_VALUE;
        for (int pos = 0; ; pos++) {
            int finalPos = pos;
            int fuel = Arrays.stream(crabs)
                    .map(c -> Math.abs(c - finalPos))
                    .sum();
            int fuel2 = Arrays.stream(crabs)
                    .map(c -> Math.abs(c - finalPos) * (Math.abs(c - finalPos) + 1) / 2)
                    .sum();
            if (fuel < leastFuel) {
                leastFuel = fuel;
            } else if (fuel2 < leastFuel2) {
                leastFuel2 = fuel2;
            } else {
                break;
            }
        }
        answer2 = leastFuel2;
        return leastFuel;
    }
}
