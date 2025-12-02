package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;
/**
 * <h1>--- Day 2: Gift Shop ---</h1>
 * <p>You get inside and take the elevator to its only other stop: the gift
 * shop. As it turns out, one of the younger Elves was playing on a gift shop
 * computer and managed to add a whole bunch of invalid product IDs to their
 * gift shop database! What do you get if you add up all of the invalid IDs?</p>
 * <p><a href="https://adventofcode.com/2025/day/2">2025 Day 2</a></p>
 */
public class Day02 extends AbstractProblem<String, Long> {

    void main() {
        new Day02().setAndSolve(Util.readLine(2025, 2));
    }

    @Override
    protected Long solve1() {
        String[] ranges = input.split(",");
        long sum = 0;
        answer2 = 0L;
        for (String range : ranges) {
            String[] bounds = range.split("-");
            long lowerBound = Long.parseLong(bounds[0]);
            long upperBound = Long.parseLong(bounds[1]);
            for (long number = lowerBound; number <= upperBound; number++) {
                if (repeats(number, 2)) {
                    sum += number;
                    answer2 += number;
                    continue;
                }
                String numberString = Long.toString(number);
                for (int patternCount = 3; patternCount <= numberString.length(); patternCount++) {
                    if (repeats(number, patternCount)) {
                        answer2 += number;
                        break;
                    }
                }
            }
        }
        return sum;
    }

    boolean repeats(long number, int patternCount) {
        String numberString = Long.toString(number);
        if (numberString.length() % patternCount != 0) {
            return false;
        }
        int patternSize = numberString.length() / patternCount;
        String pattern = numberString.substring(0, patternSize);
        String target = pattern.repeat(patternCount);
        return numberString.equals(target);
    }
}
