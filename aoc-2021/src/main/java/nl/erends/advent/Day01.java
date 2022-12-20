package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.stream.IntStream;

/**
 * --- Day 1: Sonar Sweep ---
 * <p>As the submarine drops below the surface of the ocean, it automatically
 * performs a sonar sweep of the nearby sea floor. Count the number of times a
 * depth measurement increases from the previous measurement.
 * <p><a href="https://adventofcode.com/2021/day/1">2021 Day 1</a>
 */
public class Day01 extends AbstractProblem<List<Integer>, Integer> {

    public static void main(String[] args) {
        new Day01().setAndSolve(Util.readIntegers(2021, 1));
    }

    @Override
    protected Integer solve1() {
        return countDepthIncreases(input);
    }

    @Override
    public Integer solve2() {
        List<Integer> slidingDepthList = IntStream.range(0, input.size() - 2)
                .map(index -> input.get(index) + input.get(index + 1) + input.get(index + 2))
                .boxed().toList();
        return countDepthIncreases(slidingDepthList);
    }

    private int countDepthIncreases(List<Integer> depthList) {
        return (int) IntStream.range(0, depthList.size() - 1)
                .filter(index -> depthList.get(index + 1) > depthList.get(index))
                .count();
    }
}
