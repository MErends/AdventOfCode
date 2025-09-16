package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>--- Day 19: Linen Layout ---</h1>
 * <p>Every towel at this onsen is marked with a pattern of colored stripes.
 * There are only a few patterns, but for any particular pattern, the staff can
 * get you as many towels with that pattern as you need. The Official Onsen
 * Branding Expert has produced a list of designs - each a long sequence of
 * stripe colors - that they would like to be able to display. To get into the
 * onsen as soon as possible, consult your list of towel patterns and desired
 * designs carefully. How many designs are possible?</p>
 * <p><a href="https://adventofcode.com/2024/day/19">2024 Day 19</a></p>
 */
public class Day19 extends AbstractProblem<List<String>, Long> {

    List<String> towels;
    final Map<String, Long> towelMemo = new HashMap<>();

    static void main() {
        new Day19().setAndSolve(Util.readInput(2024, 19));
    }

    @Override
    protected Long solve1() {
        towels = List.of(input.getFirst().split(", "));
        long answer1 = 0;
        answer2 = 0L;
        for (int i = 2; i < input.size(); i++) {
            long count = getTowelCount(input.get(i));
            if (count != 0) {
                answer1++;
                answer2 += count;
            }
        }
        return answer1;
    }

    private long getTowelCount(String line) {
        if (towelMemo.containsKey(line)) {
            return towelMemo.get(line);
        }
        if (line.isEmpty()) {
            return 1;
        }
        long count = 0;
        for (String towel : towels) {
            if (line.startsWith(towel)) {
                count += getTowelCount(line.substring(towel.length()));
            }
        }
        towelMemo.put(line, count);
        return count;
    }
}
