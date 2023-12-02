package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.Map;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * <h1>--- Day 1: Trebuchet?! ---</h1>
 * <p>The Elves need to send you to the clouds to fix the weather machine. For
 * this they use a trebuchet. However, it still needs to be calibrated. In order
 * to do this, find the first and last occurance of digit in a string. It can be
 * numeral or written out.</p>
 * <p><a href="https://adventofcode.com/2022/day/1">2023 Day 1</a></p>
 */
public class Day01 extends AbstractProblem<List<String>,  Number> {

    public static final Map<String, Integer> NUMBER_MAP = Map.of("one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    public static void main(String[] args) {
        new Day01().setAndSolve(Util.readInput(2023, 1));
    }

    @Override
    protected Number solve1() {
        int sum = 0;
        for (String line : input) {
            List<Integer> numbers = line.chars()
                    .filter(Character::isDigit)
                    .map(c -> c - ASCII_OFFSET)
                    .boxed().toList();
            sum += numbers.get(0) * 10 + numbers.get(numbers.size() - 1);
        }
        return sum;
    }

    @Override
    public Number solve2() {
        int sum = 0;
        for (String line : input) {
            int startnumber = 0;
            for (int index = 0; index < line.length(); index++) {
                startnumber = getNumberAtIndex(line, index);
                if (startnumber != 0) {
                    break;
                }
            }
            int endnumber = 0;
            for (int index = line.length() - 1; index >= 0; index--) {
                endnumber = getNumberAtIndex(line, index);
                if (endnumber != 0) {
                    break;
                }
            }
            sum += startnumber * 10 + endnumber;
        }
        return sum;
    }

    private int getNumberAtIndex(String line, int index) {
        int number = 0;
        if (Character.isDigit(line.charAt(index))) {
            number = Integer.parseInt(line.substring(index, index + 1));
        } else {
            String subString = line.substring(index);
            for (Map.Entry<String, Integer> e : NUMBER_MAP.entrySet()) {
                if (subString.startsWith(e.getKey())) {
                    number = e.getValue();
                }
            }
        }
        return number;
    }
}
