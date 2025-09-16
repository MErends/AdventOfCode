package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 5: Print Queue ---</h1>
 * <p>The Elf must recognize you, because they waste no time explaining that the
 * new sleigh launch safety manual updates won't print correctly. The Elf has
 * for you both the page ordering rules and the pages to produce in each update,
 * but can't figure out whether each update has the pages in the right order.
 * Determine which updates are already in the correct order. What do you get if
 * you add up the middle page number from those correctly-ordered updates?</p>
 * <p><a href="https://adventofcode.com/2024/day/5">2024 Day 5</a></p>
 */
public class Day05 extends AbstractProblem<List<String>, Integer> {

    static void main() {
        new Day05().setAndSolve(Util.readInput(2024, 5));
    }

    @Override
    protected Integer solve1() {
        int split = input.indexOf("");
        for (int index = 0; index < split; index++) {
            int before = Integer.parseInt(input.get(index).substring(0, 2));
            int after = Integer.parseInt(input.get(index).substring(3, 5));
            Update.order.computeIfAbsent(before, _ -> new ArrayList<>()).add(after);
        }
        int answer1 = 0;
        answer2 = 0;
        for (int index = split + 1; index < input.size(); index++) {
            Update update = new Update(input.get(index));
            if (update.isValid()) {
                answer1 += update.pages.get(update.pages.size() / 2);
            } else {
                update.pages.sort(Update.comparator);
                answer2 += update.pages.get(update.pages.size() / 2);
            }
        }
        return answer1;
    }

    private static class Update {

        private static final Map<Integer, List<Integer>> order = new HashMap<>();
        private static final Comparator<Integer> comparator = (p1, p2) -> {
            if (order.containsKey(p1) && order.get(p1).contains(p2)) {
                return -1;
            } else if (order.containsKey(p2) && order.get(p2).contains(p1)) {
                return 1;
            }
            return 0;
        };

        private final List<Integer> pages;

        Update(String line) {
            pages = Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        boolean isValid() {
            for (int indexA = 0; indexA < pages.size(); indexA++) {
                int before = pages.get(indexA);
                for (int indexB = indexA + 1; indexB < pages.size(); indexB++) {
                    int after = pages.get(indexB);
                    if (order.containsKey(after) && order.get(after).contains(before)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
