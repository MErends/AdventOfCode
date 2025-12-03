package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * <h1>--- Day 3: Lobby ---</h1>
 * <p>You descend a short staircase, enter the surprisingly vast lobby, and are
 * quickly cleared by the security checkpoint. When you get to the main
 * elevators, however, you discover that each one has a red light above it:
 * they're all offline. You could at least take the escalator down to the
 * printing department. That is, you could if the escalator weren't also
 * offline. There are batteries nearby that can supply emergency power to the
 * escalator for just such an occasion. here are many batteries in front of you.
 * Find the maximum joltage possible from each bank.</p>
 * <p><a href="https://adventofcode.com/2025/day/3">2025 Day 3</a></p>
 */
public class Day03 extends AbstractProblem<List<String>, Long> {

    void main() {
        new Day03().setAndSolve(Util.readInput(2025, 3));
    }

    @Override
    protected Long solve1() {
        long sum = 0L;
        int desiredLength = part2 ? 12 : 2;
        for (String line : input) {
            StringBuilder batteries = new StringBuilder(line);
            StringBuilder joltage = new StringBuilder();
            while (joltage.length() != desiredLength) {
                int endIndex = batteries.length() - desiredLength + joltage.length() + 1;
                int indexOfHighChar = getIndexOfHighestChar(batteries.substring(0, endIndex));
                joltage.append(batteries.charAt(indexOfHighChar));
                batteries.delete(0, indexOfHighChar + 1);
            }
            sum += Long.parseLong(joltage.toString());
        }
        return sum;
    }

    private int getIndexOfHighestChar(String line) {
        char highestChar = Character.MIN_VALUE;
        int charIndex = 0;
        for (int index = 0; index < line.length(); index++) {
            char c = line.charAt(index);
            if (c > highestChar) {
                highestChar = c;
                charIndex = index;
            }
        }
        return charIndex;
    }
}
