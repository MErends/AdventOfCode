package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 17: No Such Thing as Too Much ---
 * <p>The elves bought too much eggnog again. To fit it all into your
 * refrigerator, you'll need to move it into smaller containers. Filling all
 * containers entirely, how many different combinations of containers can
 * exactly fit all eggnog? And how many ways are there to use the minimal amount
 * of containers?
 * <p><a href="https://adventofcode.com/2015/day/17">2015 Day 17</a>
 */
public class Day17 extends AbstractProblem<List<Integer>, Integer> {

    private int target = 150;
    private int fillCount;
    private int minCups = Integer.MAX_VALUE;

    void main() {
        new Day17().setAndSolve(Util.readIntegers(2015, 17));
    }

    @Override
    protected Integer solve1() {
        fillCups(new ArrayList<>(), input);
        return fillCount;
    }

    private void fillCups(List<Integer> fullCups, List<Integer> emptyCups) {
        int currentCups = fullCups.stream().mapToInt(i -> i).sum();
        if (currentCups > target) {
            return;
        }
        if (currentCups == target) {
            fillCount++;
            int cupCount = fullCups.size();
            if (cupCount == minCups) {
                answer2++;
            }
            if (cupCount < minCups) {
                minCups = cupCount;
                answer2 = 1;
            }
            return;
        }
        for (int index = 0; index < emptyCups.size(); index++) {
            List<Integer> newFullCups = new ArrayList<>(fullCups);
            List<Integer> newEmptyCups = new ArrayList<>(emptyCups);
            newEmptyCups.subList(0, index + 1).clear();
            newFullCups.add(emptyCups.get(index));
            fillCups(newFullCups, newEmptyCups);
        }
    }

    void setTestTarget() {
        target = 25;
    }
}
