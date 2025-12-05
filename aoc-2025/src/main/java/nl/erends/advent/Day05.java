package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>--- Day 5: Cafeteria ---</h1>
 * <p>As the forklifts break through the wall, the Elves are delighted to
 * discover that there was a cafeteria on the other side after all. The Elves in
 * the kitchen explain the situation: they can't figure out which of their
 * ingredients are fresh and which are spoiled. Process the database file from
 * the new inventory management system. How many of the available ingredient IDs
 * are fresh?</p>
 * <p><a href="https://adventofcode.com/2025/day/5">2025 Day 5</a></p>
 */
public class Day05 extends AbstractProblem<List<String>, Long> {

    void main() {
        new Day05().setAndSolve(Util.readInput(2025, 5));
    }

    @Override
    protected Long solve1() {
        int split = input.indexOf("");
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < split; i++) {
            ranges.add(new Range(input.get(i)));
        }
        long sum = 0;
        for (int i = split + 1; i < input.size(); i++) {
            long veg = Long.parseLong(input.get(i));
            if (ranges.stream().anyMatch(r -> r.isInRange(veg))) {
                sum++;
            }
        }
        return sum;
    }

    @Override
    public Long solve2() {
        int blank = input.indexOf("");
        List<Range> ranges = new ArrayList<>();
        for (int index = 0; index < blank; index++) {
            ranges.add(new Range(input.get(index)));
        }
        for (int indexMain = 0; indexMain < ranges.size(); indexMain++) {
            for (int indexSub = indexMain + 1; indexSub < ranges.size(); indexSub++) {
                ranges.get(indexMain).updateBounds(ranges.get(indexSub));
            }
        }
        long sum = 0L;
        for (Range range : ranges) {
            if (range.upper > range.lower) {
                sum += range.upper - range.lower;
            }
        }
        return sum;
    }

    private static class Range {

        long lower;
        long upper;

        Range(String line) {
            String[] ranges = line.split("-");
            lower = Long.parseLong(ranges[0]);
            upper = Long.parseLong(ranges[1]) + 1;
        }

        boolean isInRange(long value) {
            return value >= lower && value < upper;
        }

        void updateBounds(Range sub) {
            if (isInRange(sub.lower)) {
                sub.lower = upper;
            }
            if (isInRange(sub.upper)) {
                sub.upper = lower;
            }
            if (sub.isInRange(lower)) {
                lower = sub.upper;
            }
            if (sub.isInRange(upper)) {
                upper = sub.lower;
            }
        }
    }
}

