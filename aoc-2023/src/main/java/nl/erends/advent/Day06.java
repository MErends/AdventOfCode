package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>--- Day 6: Wait For It ---</h1>
 * <p>As you try to figure out what to do next, you notice a poster on a wall
 * near the ferry dock. "Boat races!" Holding down the button charges the boat,
 * and releasing the button allows the boat to move. Boats move faster if their
 * button was held longer, but time spent holding the button counts against the
 * total race time. To see how much margin of error you have, determine the
 * number of ways you can beat the record in each race;</p>
 * <p><a href="https://adventofcode.com/2023/day/6">2023 Day 6</a></p>
 */
public class Day06 extends AbstractProblem<List<String>, Number> {

    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readInput(2023, 6));
    }

    @Override
    protected Number solve1() {
        List<Integer> times = Arrays.stream(input.get(0).substring(5).split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
        List<Integer> distances = Arrays.stream(input.get(1).substring(9).split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .toList();
        int records = 1;
        for (int race = 0; race < times.size(); race++) {
            int time = times.get(race);
            int distance = distances.get(race);
            int winCount = getWinCount(time, distance);
            records *= winCount;
        }
        return records;
    }

    private static int getWinCount(long time, long distance) {
        double disc = Math.sqrt(time * time - 4.0 * distance) / 2.0;
        int lowBound = (int) Math.ceil(time / 2.0 - disc);
        int upBound = (int) Math.floor(time / 2.0 + disc);
        int winCount = upBound - lowBound + 1;
        if ((time - upBound) * upBound == distance) {
            winCount--;
        }
        if ((time - lowBound) * lowBound == distance) {
            winCount--;
        }
        return winCount;
    }

    @Override
    public Number solve2() {
        long time = Long.parseLong(input.get(0).substring(5).replace(" ", ""));
        long distance = Long.parseLong(input.get(1).substring(9).replace(" ", ""));
        return getWinCount(time, distance);
    }
}
