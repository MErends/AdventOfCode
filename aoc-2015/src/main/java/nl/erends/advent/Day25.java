package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

/**
 * --- Day 25: Let It Snow ---
 * <p>The weather machine beeps! On the console of the machine is a copy
 * protection message asking you to enter a code from the instruction manual.
 * <p><a href="https://adventofcode.com/2021/day/25">2015 Day 25</a>
 */
public class Day25 extends AbstractProblem<String, Number> {

    void main() {
        new Day25().setAndSolve(Util.readLine(2015, 25));
    }

    @Override
    protected Number solve1() {
        String[] words = input.split(" ");
        int row = Integer.parseInt(words[16].substring(0, words[16].length() - 1));
        int column = Integer.parseInt(words[18].substring(0, words[18].length() - 1));
        int position = 0;
        while (column != 1) {
            column--;
            row++;
            position++;
        }
        for (int i = 1; i < row; i++) {
            position += i;
        }
        long code = 20151125;
        for (int i = 0; i < position; i++) {
            code *= 252533;
            code %= 33554393;
        }
        return code;
    }
}
