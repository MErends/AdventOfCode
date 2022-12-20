package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * --- Day 24: It Hangs in the Balance ---
 * <p>It's Christmas Eve, and Santa is loading up the sleigh for this year's
 * deliveries. However, there's one small problem: he can't get the sleigh to
 * balance. If it isn't balanced, he can't defy physics, and nobody gets
 * presents this year. The first group needs as few packages as possible so that
 * Santa has some legroom left over. Furthermore, Santa tells you, if there are
 * multiple ways to arrange the packages such that the fewest possible are in
 * the first group, you need to choose the way where the first group has the
 * smallest quantum entanglement. What is the quantum entanglement of the first
 * group of packages in the ideal configuration?
 * <p><a href="https://adventofcode.com/2021/day/24">2015 Day 24</a>
 */
public class Day24 extends AbstractProblem<List<Integer>, Number> {

    private int target;
    private int minPacks = Integer.MAX_VALUE;
    private long minTangle = Long.MAX_VALUE;

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readIntegers(2015, 24));
    }

    @Override
    protected Number solve1() {
        target = input.stream().mapToInt(i -> i).sum() / 3;
        Collections.reverse(input);
        calculate(new ArrayList<>(), input);
        return minTangle;
    }

    @Override
    public Number solve2() {
        target = input.stream().mapToInt(i -> i).sum() / 4;
        calculate(new ArrayList<>(), input);
        return minTangle;
    }

    private void calculate(List<Integer> usedPacks, List<Integer> packs) {
        int currentWeight = usedPacks.stream().mapToInt(i -> i).sum();
        if (currentWeight > target || usedPacks.size() > minPacks) {
            return;
        }
        if (currentWeight == target) {
            int packCount = usedPacks.size();
            long tangle = usedPacks.stream().mapToLong(i -> i).reduce(1L, (i1, i2) -> i1 * i2);
            if (packCount < minPacks || tangle < minTangle) {
                minPacks = packCount;
                minTangle = tangle;
            }
            return;
        }
        for (int index = 0; index < packs.size(); index++) {
            List<Integer> newUsedPacks = new ArrayList<>(usedPacks);
            List<Integer> newPacks = new ArrayList<>(packs);
            newPacks.subList(0, index + 1).clear();
            newUsedPacks.add(packs.get(index));
            calculate(newUsedPacks, newPacks);
        }
    }
}
