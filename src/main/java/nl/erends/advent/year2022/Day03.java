package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 3: Rucksack Reorganization ---
 * <p>One Elf has the important job of loading all of the rucksacks with
 * supplies for the jungle journey. The list of items for each rucksack is given
 * as characters all on a single line. Find the item type that appears in both
 * compartments of each rucksack. What is the sum of the priorities of thoseitem types?
 * <p><a href="https://adventofcode.com/2022/day/3">2022 Day 3</a>
 */
public class Day03 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day03().setAndSolve(Util.readInput(2022, 3));
    }

    @Override
    protected Integer solve1() {
        int priority = 0;
        List<Character> mixedItems = input.stream().map(this::getMixedItem).toList();
        for (char c : mixedItems) {
            priority += c - (Character.isLowerCase(c) ? 96 : 38);
        }
        return priority;
    }

    @Override
    public Integer solve2() {
        int priority = 0;
        for (int i = 0; i < input.size(); i += 3) {
            char c = getCommonChar(input.subList(i, i + 3));
            priority += c - (Character.isLowerCase(c) ? 96 : 38);
        }
        return priority;
    }

    private char getMixedItem(String line) {
        for (int indexA = 0; indexA < line.length() / 2; indexA++) {
            for (int indexB = line.length() / 2; indexB < line.length(); indexB++) {
                char a = line.charAt(indexA);
                char b = line.charAt(indexB);
                if (a == b) {
                    return a;
                }
            }
        }
        throw new IllegalArgumentException("No mixed item found");
    }

    private char getCommonChar(List<String> elfs) {
        for (char c : elfs.get(0).toCharArray()) {
            if (elfs.get(1).indexOf(c) != -1 && elfs.get(2).indexOf(c) != -1) {
                return c;
            }
        }
        throw new IllegalArgumentException("No common character");
    }
}
