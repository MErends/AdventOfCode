package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * <h1>--- Day 1: Secret Entrance ---</h1>
 * <p>You arrive at the secret entrance to the North Pole base ready to start
 * decorating. Unfortunately, the password seems to have been changed, so you
 * can't get in. Due to new security protocols, the password is locked in the
 * safe. The actual password is the number of times the dial is left pointing at
 * 0 after any rotation in the sequence.</p>
 * <p><a href="https://adventofcode.com/2025/day/1">2025 Day 1</a></p>
 */
public class Day01 extends AbstractProblem<List<String>, Integer> {

    void main() {
        new Day01().setAndSolve(Util.readInput(2025, 1));
    }

    @Override
    protected Integer solve1() {
        int dialPosition = 50;
        int timesAtZero = 0;
        int timesThroughZero = 0;
        for (String line : input) {
            int direction = 1;
            if (line.charAt(0) == 'L') {
                direction *= -1;
            }
            int movement = Integer.parseInt(line.substring(1));
            timesThroughZero += movement / 100;
            movement %= 100;
            int oldPosition = dialPosition;
            dialPosition += movement * direction;
            if ((dialPosition < 0 || dialPosition > 100) && oldPosition != 0) {
                timesThroughZero++;
            }
            dialPosition = (dialPosition + 100) % 100;
            if (dialPosition == 0) {
                timesAtZero++;
            }
        }
        answer2 = timesThroughZero + timesAtZero;
        return timesAtZero;
    }
}
