package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Calorie Counting ---
 * <p>Santa's reindeer typically eat regular reindeer food, but they need a lot
 * of magical energy to deliver presents on Christmas. For that, their favorite
 * snack is a special type of star fruit that only grows deep in the jungle. The
 * Elves have brought you on their annual expedition to the grove where the
 * fruit grows. The Elves take turns writing down the number of Calories
 * contained by the various meals, snacks, rations, etc. that they've brought
 * with them. Find the Elf carrying the most Calories. How many total Calories
 * is that Elf carrying?
 * <p><a href="https://adventofcode.com/2022/day/1">2022 Day 1</a>
 */
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
