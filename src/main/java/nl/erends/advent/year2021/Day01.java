package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 1: Sonar Sweep ---
 * <p>As the submarine drops below the surface of the ocean, it automatically
 * performs a sonar sweep of the nearby sea floor. Count the number of times a
 * depth measurement increases from the previous measurement.
 * <p><a href="https://adventofcode.com/2021/day/1">2021 Day 1</a>
 */
public class Day01 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day01().setAndSolve(Util.readInput(2021, 1));
    }

    @Override
    protected Integer solve1() {
        return countDepthIncreases(input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    @Override
    public Integer solve2() {
        List<Integer> depthList = input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> slidingDepthList = new ArrayList<>();
        for (int index = 0; index < depthList.size() - 2; index++) {
            slidingDepthList.add(depthList.get(index) + depthList.get(index + 1) + depthList.get(index + 2));
        }
        return countDepthIncreases(slidingDepthList);
    }

    private int countDepthIncreases(List<Integer> depthList) {
        int depthIncreaseCount = 0;
        for (int index = 1; index < depthList.size(); index++) {
            if (depthList.get(index) > depthList.get(index - 1)) {
                depthIncreaseCount++;
            }
        }
        return depthIncreaseCount;
    }
}
