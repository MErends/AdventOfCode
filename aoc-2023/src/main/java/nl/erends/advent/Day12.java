package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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


    public static void main(String[] args) {
        new Day12().setAndSolve(Util.readInput(2023, 12));
    }

    @Override
    protected Number solve1() {
        int sum = 0;
        for (String line : input) {
            String[] sA = line.split(" ");
            String target = sA[0];
            List<Integer> formula = Arrays.stream(sA[1].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
            List<String> options = getOptions(formula, target.length(), target);
            options.removeIf(o -> !goodMatch(target, o));
            sum += options.size();
        }
        return sum;
    }

    @Override
    public Number solve2() {
        int sum = 0;
        int counter = 0;
        for (String line : input) {
            String[] sA = line.split(" ");
            String target = sA[0];
            List<Integer> formula = Arrays.stream(sA[1].split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
            StringBuilder bigTargetSB = new StringBuilder(target);
            List<Integer> bigFormula = new ArrayList<>(formula);
            IntStream.rangeClosed(1, 4).forEach(i -> {
                bigTargetSB.append('?').append(target);
                bigFormula.addAll(formula);
            });
            String bigTarget = bigTargetSB.toString();
            List<String> options = getOptions(bigFormula, bigTarget.length(), bigTarget);
            for (String option : options) {
                if (!goodMatch(bigTarget, option)) {
                    LOG.info(option);
                    LOG.info(bigTarget);
                }
            }
            options.removeIf(o -> !goodMatch(bigTarget, o));
            sum += options.size();
            LOG.info("{}/{}. Sum={}", ++counter, input.size(), options.size());
        }
        return sum;
    }

    private boolean goodMatch(String target, String option) {
        for (int i = 0; i < option.length(); i++) {
            char tC = target.charAt(i);
            char oC = option.charAt(i);
            if (tC != '?' && tC != oC) {
                return false;
            }
        }
        return true;
    }

    private List<String> getOptions(List<Integer> formula, int targetLength, String stringBegin) {
        List<String> options = new ArrayList<>();
        List<Integer> newFormula = new ArrayList<>(formula);
        int groupSize = newFormula.remove(0);
        if (newFormula.isEmpty()) {
            final StringBuilder group = new StringBuilder();
            IntStream.range(0, groupSize).forEach(i -> group.append('#'));
            while (group.length() != targetLength) {
                group.insert(0, '.');
            }
            while (group.charAt(0) != '#') {
                String groupString = group.toString();
                if (goodMatch(stringBegin, groupString)) {
                    options.add(groupString);
                }
                group.deleteCharAt(0);
                group.append('.');
            }
            options.add(group.toString());
            Collections.reverse(options);
            return options;
        }

        final StringBuilder group = new StringBuilder();
        IntStream.range(0, groupSize).forEach(i -> group.append('#'));
        group.append('.');
        int formulaLength = newFormula.stream().mapToInt(i -> i).sum() + newFormula.size() - 1;
        int paddingRoom = targetLength - formulaLength - group.length();
        for (int padding = 0; padding <= paddingRoom; padding++) {
            if (goodMatch(stringBegin, group.toString())) {
                List<String> newOptions = getOptions(newFormula, targetLength - group.length(), stringBegin.substring(group.length()));
                newOptions.forEach(nO -> options.add(group + nO));
            }
            group.insert(0, '.');
        }
        return options;
    }
}
