package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 extends AbstractProblem<List<Integer>, Long> {

    private final Map<Integer, Integer> streakPermutationMap = new HashMap<>();

    public static void main(String[] args) {
        new Day10().setAndSolve(Util.readIntegers(2020, 10));
    }

    @Override
    public Long solve1() {
        List<Integer> adapters = input.stream().sorted().collect(Collectors.toList());
        adapters.add(0, 0);
        int diffOf1 = 0;
        int diffOf3 = 0;
        for (int index = 0; index < adapters.size() - 1; index++) {
            int diff = adapters.get(index + 1) - adapters.get(index);
            if (diff == 1) {
                diffOf1++;
            } else if (diff == 3) {
                diffOf3++;
            }
        }
        diffOf3++;
        return (long) diffOf1 * diffOf3;
    }

    @Override
    public Long solve2() {
        List<Integer> adapters = input.stream().sorted().collect(Collectors.toList());
        streakPermutationMap.put(1, 1);
        adapters.add(0, 0);
        List<Long> permutations = new ArrayList<>();
        int streak = 1;
        for (int index = 0; index < adapters.size() - 1; index++) {
            int diff = adapters.get(index + 1) - adapters.get(index);
            if (diff == 1) {
                streak++;
            } else {
                permutations.add((long) getPermutations(streak));
                streak = 1;
            }
        }
        permutations.add((long) getPermutations(streak));
        return permutations.stream().reduce(1L, (i1, i2) -> i1 * i2);
    }

    private int getPermutations(int streak) {
        if (!streakPermutationMap.containsKey(streak)) {
            List<Integer> streakList = IntStream.rangeClosed(1, streak).boxed().collect(Collectors.toList());
            streakPermutationMap.put(streak, countPermutations(streakList, 1));
        }
        return streakPermutationMap.get(streak);
    }


    private int countPermutations(List<Integer> list, int verwijderIndex) {
        if (!isValidList(list)){
            return 0;
        }
        int count = 1;
        for (int index = verwijderIndex; index < list.size() - 1; index++) {
            List<Integer> newList = new ArrayList<>(list);
            newList.remove(index);
            count += countPermutations(newList, index);
        }
        return count;
    }

    private boolean isValidList(List<Integer> list) {
        for (int index = 0; index < list.size() - 1; index++) {
            int diff = list.get(index + 1) - list.get(index);
            if (diff >3) {
                return false;
            }
        }
        return true;
    }
}
