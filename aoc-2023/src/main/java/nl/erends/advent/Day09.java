package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * <h1>--- Day 9: Mirage Maintenance ---</h1>
 * <p>You ride the camel through the sandstorm and stop where the ghost's maps
 * told you to stop. The sandstorm subsequently subsides, somehow seeing you
 * standing at an oasis! Analyze your OASIS report and extrapolate the next
 * value for each history. What is the sum of these extrapolated values?</p>
 * <p><a href="https://adventofcode.com/2023/day/9">2023 Day 9</a></p>
 */
public class Day09 extends AbstractProblem<List<String>, Number> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readInput(2023, 9));
    }

    private Number solve(ToIntFunction<List<Integer>>mapper) {
        return input.stream()
                .map(line -> line.split(" "))
                .map(sA -> Arrays.stream(sA).map(Integer::parseInt).toList())
                .mapToInt(mapper)
                .sum();
    }

    @Override
    protected Number solve1() {
        return solve(this::getNextNumber);
    }

    @Override
    public Number solve2() {
        return solve(this::getPreviousNumber);
    }

    private int getNextNumber(List<Integer> numberlist) {
        if (numberlist.stream().allMatch(n -> n == 0)) {
            return 0;
        }
        return numberlist.get(numberlist.size() - 1) + getNextNumber(getDerivative(numberlist));
    }

    private int getPreviousNumber(List<Integer> numberlist) {
        if (numberlist.stream().allMatch(n -> n == 0)) {
            return 0;
        }
        return numberlist.get(0) - getPreviousNumber(getDerivative(numberlist));
    }

    private List<Integer> getDerivative(List<Integer> numberlist) {
        List<Integer> deivative = new ArrayList<>();
        for (int index = 1; index < numberlist.size(); index++) {
            deivative.add(numberlist.get(index) - numberlist.get(index - 1));
        }
        return deivative;

    }
}