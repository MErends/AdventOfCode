package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 7: Bridge Repair ---</h1>
 * <p>Each line represents a single equation. The test value appears before the
 * colon on each line; it is your job to determine whether the remaining numbers
 * can be combined with operators to produce the test value. Glancing into the
 * jungle, you can see elephants holding two different types of operators:
 * add (+) and multiply (*). Determine which equations could possibly be true.
 * What is their total calibration result?</p>
 * <p><a href="https://adventofcode.com/2024/day/7">2024 Day 7</a></p>
 */
public class Day07 extends AbstractProblem<List<String>, Long> {

    private boolean part2 = false;

    public static void main(String[] args) {
        new Day07().setAndSolve(Util.readInput(2024, 7));
    }

    @Override
    protected Long solve1() {
        long result = 0;
        for (String line : input) {
            String[] split = line.split(": ");
            long target = Long.parseLong(split[0]);
            List<Long> operands = Arrays.stream(split[1].split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(ArrayList::new));
            long startValue = operands.remove(0);
            if (isEquationpossible(operands, startValue, target)) {
                result += target;
            }
        }
        return result;
    }

    @Override
    public Long solve2() {
        part2 = true;
        return solve1();
    }

    boolean isEquationpossible(List<Long> operands, long value, long target) {
        if (operands.isEmpty() && value == target) {
            return true;
        }
        if (operands.isEmpty() || value > target) {
            return false;
        }
        List<Long> newOperands = new ArrayList<>(operands);
        long operand = newOperands.remove(0);
        long plusValue = value + operand;
        long timesValue = value * operand;
        boolean isConcatPossile = false;
        if (part2) {
            long concatValue = Long.parseLong("" + value + operand);
            isConcatPossile = isEquationpossible(newOperands, concatValue, target);
        }

        return isConcatPossile
                || isEquationpossible(newOperands, plusValue, target)
                || isEquationpossible(newOperands, timesValue, target);
    }
}
