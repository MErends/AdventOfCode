package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>--- Day 12: Hot Springs ---</h1>
 * <p>In the giant field just outside, the springs are arranged into rows. For
 * each row, the condition records show every spring and whether it is
 * operational (.) or damaged (#). This is the part of the condition records
 * that is itself damaged; for some springs, it is simply unknown (?) whether
 * the spring is operational or damaged. For each row, count all of the
 * different arrangements of operational and broken springs that meet the given
 * criteria. What is the sum of those counts?</p>
 * <p><a href="https://adventofcode.com/2023/day/12">2023 Day 12</a></p>
 */
public class Day12 extends AbstractProblem<List<String>, Number> {

    StringBuilder target;
    List<Integer> formula;
    Map<Integer, Map<Integer, Long>> cache;

    static void main() {
        new Day12().setAndSolve(Util.readInput(2023, 12));
    }

    @Override
    protected Number solve1() {
        return solve(1);
    }

    @Override
    public Number solve2() {
        return solve(5);
    }

    private Number solve(int multiplier) {
        long sum = 0;
        for (String line : input) {
            String[] sA = line.split(" ");
            cache = new HashMap<>();
            target = new StringBuilder(sA[0]);
            formula = Arrays.stream(sA[1].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
            List<Integer> formulaClone = new ArrayList<>(formula);
            IntStream.range(1, multiplier).forEach(_ -> {
                target.append(',').append(sA[0]);
                formula.addAll(formulaClone);});
            long options = getOptions(0, 0);
            sum += options;
        }
        return sum;
    }

    private long getOptions(int groupNumber, int offset) {
        if (cache.containsKey(groupNumber) && cache.get(groupNumber).containsKey(offset)) {
            return cache.get(groupNumber).get(offset);
        }
        if (groupNumber == formula.size() - 1) {
            long options = 0;
            int groupSize = formula.get(groupNumber);
            for (int padding = 0; padding + groupSize <= target.length() - offset; padding++) {
                boolean isOption = true;
                for (int i = 0; i < padding; i++) {
                    if (target.charAt(offset + i) == '#') {
                        isOption = false;
                        break;
                    }
                }
                if (!isOption) {
                    continue;
                }
                for (int i = padding; i < padding + groupSize; i++) {
                    if (target.charAt(offset + i) == '.') {
                        isOption = false;
                        break;
                    }
                }
                if (!isOption) {
                    continue;
                }
                for (int i = padding + groupSize; i < target.length() - offset; i++) {
                    if (target.charAt(offset + i) == '#') {
                        isOption = false;
                        break;
                    }
                }
                options += isOption ? 1 : 0;
            }
            if (!cache.containsKey(groupNumber)) {
                cache.put(groupNumber, new HashMap<>());
            }
            cache.get(groupNumber).put(offset, options);
            return options;
        }
        long options = 0;
        int groupSize = formula.get(groupNumber);

        int restLength = -1;
        for (int i = groupNumber + 1; i < formula.size(); i++) {
            restLength += formula.get(i) + 1;
        }
        int maxPadding = target.length() - offset - restLength - groupSize - 1;
        for (int padding = 0; padding <= maxPadding; padding++) {
            boolean isOption = true;
            for (int i = 0; i < padding; i++) {
                if (target.charAt(offset + i) == '#') {
                    isOption = false;
                    break;
                }
            }
            if (!isOption) {
                continue;
            }
            for (int i = padding; i < padding + groupSize; i++) {
                if (target.charAt(offset + i) == '.') {
                    isOption = false;
                    break;
                }
            }
            if (!isOption) {
                continue;
            }
            if (target.charAt(offset + padding + groupSize) == '#') {
                continue;
            }
            options += getOptions(groupNumber + 1, offset + padding + groupSize + 1);
        }

        if (!cache.containsKey(groupNumber)) {
            cache.put(groupNumber, new HashMap<>());
        }
        cache.get(groupNumber).put(offset, options);
        return options;
    }
}
