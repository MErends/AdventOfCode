package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>--- Day 1: Historian Hysteria ---</h1>
 * <p>The Chief Historian is always present for the big Christmas sleigh launch,
 * but nobody has seen him in months! Last anyone heard, he was visiting
 * locations that are historically significant to the North Pole. The Historians
 * split into two groups, each searching the office and trying to create their
 * own complete list of location IDs. What is the total distance between your
 * lists?</p>
 * <p><a href="https://adventofcode.com/2024/day/1">2024 Day 1</a></p>
 */
public class Day01 extends AbstractProblem<List<String>, Integer> {

    void main() {
        new Day01().setAndSolve(Util.readInput(2024, 1));
    }

    @Override
    protected Integer solve1() {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        readInputToLists(listA, listB);
        Collections.sort(listA);
        Collections.sort(listB);
        int answer = 0;
        for (int index = 0; index < listA.size(); index++) {
            answer += Math.abs(listA.get(index) - listB.get(index));
        }
        return answer;
    }

    @Override
    public Integer solve2() {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        readInputToLists(listA, listB);
        int answer = 0;
        Map<Integer, Integer> occurences = new HashMap<>();
        for (int numberA : listA) {
            answer += numberA * occurences.computeIfAbsent(numberA, n -> count(n, listB));
        }
        return answer;
    }

    private int count(int number, List<Integer> listB) {
        return (int) listB.stream().filter(n -> n == number).count();
    }

    private void readInputToLists(List<Integer> listA, List<Integer> listB) {
        for (String line : input) {
            String[] split = line.split("\\s+");
            listA.add(Integer.parseInt(split[0]));
            listB.add(Integer.parseInt(split[1]));
        }
    }
}
