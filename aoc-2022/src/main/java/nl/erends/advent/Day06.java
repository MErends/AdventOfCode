package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.Set;

/**
 * --- Day 6: Tuning Trouble ---
 * <p>As you move through the dense undergrowth, one of the Elves gives you a
 * handheld device. He says that it has many fancy features, but the most
 * important one to set up right now is the communication system. To fix the
 * communication system, you need to add a subroutine to the device that detects
 * a start-of-packet marker in the datastream. In the protocol being used by the
 * Elves, the start of a packet is indicated by a sequence of four characters
 * that are all different. How many characters need to be processed before the
 * first start-of-packet marker is detected?
 * <p><a href="https://adventofcode.com/2022/day/6">2022 Day 6</a>
 */
public class Day06 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readLine(2022, 6));
    }

    @Override
    protected Integer solve1() {
        return startPosition(4);
    }

    @Override
    public Integer solve2() {
        return startPosition(14);
    }

    private Integer startPosition(int length) {
        for (int i = 0; i < input.length() - length; i++) {
            Set<Character> letters = new HashSet<>();
            for (char c : input.substring(i, i + length).toCharArray()) {
                letters.add(c);
            }
            if (letters.size() == length) {
                return i + length;
            }
        }
        return null;
    }
}
