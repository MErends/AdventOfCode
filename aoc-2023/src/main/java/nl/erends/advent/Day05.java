package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 5: If You Give A Seed A Fertilizer ---</h1>
 * <p>You take the boat and find the gardener right where you were told he would
 * be: managing a giant "garden" that looks more to you like a farm. The almanac
 * (your puzzle input) lists all of the seeds that need to be planted. It also
 * lists what type of soil to use with each kind of seed, what type of
 * fertilizer to use with each kind of soil, what type of water to use with each
 * kind of fertilizer, and so on. Every type of seed, soil, fertilizer and so on
 * is identified with a number. What is the lowest location number that
 * corresponds to any of the initial seed numbers?</p>
 * <p><a href="https://adventofcode.com/2023/day/5">2023 Day 5</a></p>
 */
public class Day05 extends AbstractProblem<List<String>, Number> {

    private List<Range> seedRanges;
    private List<Range> soilRanges;
    private List<Range> fertRanges;
    private List<Range> waterRanges;
    private List<Range> lightRanges;
    private List<Range> tempRanges;
    private List<Range> humiRanges;

    static void main() {
        new Day05().setAndSolve(Util.readInput(2023, 5));
    }

    @Override
    protected Long solve1() {
        seedRanges = getRanges("seed");
        soilRanges = getRanges("soil");
        fertRanges = getRanges("fertilizer");
        waterRanges = getRanges("water");
        lightRanges = getRanges("light");
        tempRanges = getRanges("temperature");
        humiRanges = getRanges("humidity");
        long bestLocation = Long.MAX_VALUE;
        List<Long> seeds = Arrays.stream(input.getFirst().substring(7).split(" "))
                .map(Long::parseLong).toList();
        for (long seed : seeds) {
            long value = seed;
            value = convertValue(value, seedRanges);
            value = convertValue(value, soilRanges);
            value = convertValue(value, fertRanges);
            value = convertValue(value, waterRanges);
            value = convertValue(value, lightRanges);
            value = convertValue(value, tempRanges);
            value = convertValue(value, humiRanges);
            if (value < bestLocation) {
                bestLocation = value;
            }
        }
        return bestLocation;
    }

    @Override
    public Number solve2() {
        long bestLocation = Long.MAX_VALUE;
        List<Long> seeds = Arrays.stream(input.getFirst().substring(7).split(" "))
                .map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));
        while (!seeds.isEmpty()) {
            long seedStart = seeds.removeFirst();
            long seedRange = seeds.removeFirst();
            long seed = seedStart;
            while(seed < seedStart + seedRange) {
                long value = seed;
                long rangeLeft = Long.MAX_VALUE;
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, seedRanges));
                value = convertValue(value, seedRanges);
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, soilRanges));
                value = convertValue(value, soilRanges);
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, fertRanges));
                value = convertValue(value, fertRanges);
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, waterRanges));
                value = convertValue(value, waterRanges);
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, lightRanges));
                value = convertValue(value, lightRanges);
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, tempRanges));
                value = convertValue(value, tempRanges);
                rangeLeft = Math.min(rangeLeft, rangeLeft(value, humiRanges));
                value = convertValue(value, humiRanges);
                bestLocation = Math.min(value, bestLocation);
                seed += rangeLeft;
            }
        }
        return bestLocation;
    }

    private List<Range> getRanges(String type) {
        String startLine = input.stream()
                .filter(s -> s.startsWith(type + '-'))
                .findFirst().orElseThrow();
        int index = input.indexOf(startLine) + 1;
        List<Range> ranges = new ArrayList<>();
        while (index < input.size() && !input.get(index).isEmpty()) {
            ranges.add(new Range(input.get(index)));
            index++;
        }
        return ranges;
    }

    private long convertValue(long source, List<Range> ranges) {
        return ranges.stream()
                .filter(r -> r.inRange(source))
                .findFirst()
                .map(value -> value.convert(source))
                .orElse(source);
    }

    private long rangeLeft(long source, List<Range> ranges) {
        Optional<Range> range = ranges.stream()
                .filter(r -> r.inRange(source))
                .findFirst();
        return range.map(value -> value.rangeLeft(source)).orElseGet(() -> ranges.stream()
                .filter(r -> r.startSource > source)
                .mapToLong(r -> r.startSource - source)
                .min().orElse(Long.MAX_VALUE));
    }

    private static class Range {

        final long startSource;
        final long startDestination;
        final long length;

        public Range(String line) {
            String[] lineSplit = line.split(" ");
            this.startSource = Long.parseLong(lineSplit[1]);
            this.startDestination = Long.parseLong(lineSplit[0]);
            this.length = Long.parseLong(lineSplit[2]);
        }

        boolean inRange(long source) {
            return source >= startSource && source < startSource + length;
        }

        long convert(long source) {
            return (source - startSource) + startDestination;
        }

        long rangeLeft(long source) {
            return length - (source - startSource);
        }
    }
}
