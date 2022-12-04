package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 4: Camp Cleanup ---
 * <p>Space needs to be cleared before the last supplies can be unloaded from
 * the ships, and so several Elves have been assigned the job of cleaning up
 * sections of the camp. However, as some of the Elves compare their section
 * assignments with each other, they've noticed that many of the assignments
 * overlap. In how many assignment pairs do the ranges overlap?
 * <p><a href="https://adventofcode.com/2022/day/4">2022 Day 4</a>
 */
public class Day04 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2022, 4));
    }

    @Override
    protected Integer solve1() {
        int contains = 0;
        int overlaps = 0;
        for (String line : input) {
            String[] ranges = line.split(",");
            String[] rangeA = ranges[0].split("-");
            String[] rangeB = ranges[1].split("-");
            int aMin = Integer.parseInt(rangeA[0]);
            int aMax = Integer.parseInt(rangeA[1]);
            int bMin = Integer.parseInt(rangeB[0]);
            int bMax = Integer.parseInt(rangeB[1]);
            if (bMin <= aMin && aMax <= bMax || aMin <= bMin && bMax <= aMax) {
                contains++;
            }
            if (bMin <= aMin && aMin <= bMax || aMin <= bMin && bMin <= aMax ||
                    bMin <= aMax && aMax <= bMax || aMin <= bMax && bMax <= aMax) {
                overlaps++;
            }
        }
        answer2 = overlaps;
        return contains;
    }
}
