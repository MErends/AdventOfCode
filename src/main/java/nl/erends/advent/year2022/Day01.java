package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new  Day01().setAndSolve(Util.readInput(2022, 1));
    }

    @Override
    protected Integer solve1() {
        int mostCalories = Integer.MIN_VALUE;
        int currentElf = 0;
        for (String line : input) {
            if (line.equals("")) {
                mostCalories = Math.max(mostCalories, currentElf);
                currentElf = 0;
            } else {
                currentElf += Integer.parseInt(line);
            }
        }
        return Math.max(mostCalories, currentElf);
    }

    @Override
    public Integer solve2() {
        List<Integer> elves = new ArrayList<>();
        int currentElf = 0;
        for (String line : input) {
            if (line.equals("")) {
                elves.add(currentElf);
                currentElf = 0;
            } else {
                currentElf += Integer.parseInt(line);
            }
        }
        elves.add(currentElf);
        return elves.stream()
                .mapToInt(i -> i)
                .sorted()
                .skip(elves.size() - 3L)
                .sum();
    }
}
