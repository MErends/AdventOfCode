package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>--- Day 25: Code Chronicle ---</h1>
 * <p>When you get to the Chief Historian's office, you are surprised to
 * discover that the door to his office is locked! Unfortunately, they've lost
 * track of which locks are installed and which keys go with them, so the best
 * they can do is send over schematics of every lock and every key for the floor
 * you're on. Analyze your lock and key schematics. How many unique lock/key
 * pairs fit together without overlapping in any column?</p>
 * <p><a href="https://adventofcode.com/2024/day/25">2024 Day 25</a></p>
 */
public class Day25 extends AbstractProblem<List<String>, Integer> {

    final List<Lock> locks = new ArrayList<>();
    final List<Key> keys = new ArrayList<>();

    public static void main(String[] args) {
        new Day25().setAndSolve(Util.readInput(2024, 25));
    }

    @Override
    protected Integer solve1() {
        int dim = input.indexOf("");
        int index = 0;
        while (index < input.size()) {
            char[][] item = new char[dim][];
            for (int lineIndex = index; lineIndex < index + dim; lineIndex++) {
                item[lineIndex - index] = input.get(lineIndex).toCharArray();
            }
            if (item[0][0] == '#') {
                locks.add(new Lock(item));
            } else {
                keys.add(new Key(item));
            }
            index += dim + 1;
        }
        int combos = 0;
        for (Lock lock : locks) {
            for (Key key : keys) {
                List<Integer> combo = new ArrayList<>();
                for (int i = 0; i < locks.getFirst().signature.size(); i++) {
                    combo.add(lock.signature.get(i) + key.signature.get(i));
                }
                if (combo.stream().allMatch(c -> c <= dim)) {
                    combos++;
                }
            }
        }
        return combos;
    }

    @Override
    public Integer solve2() {
        return null;
    }

    static class Lock {

        final List<Integer> signature = new ArrayList<>();

        Lock(char[][] grid) {
            for (int x = 0; x < grid[0].length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    if (grid[y][x] == '.') {
                        signature.add(y);
                        break;
                    }
                }
            }
        }
    }

    static class Key {

        final List<Integer> signature = new ArrayList<>();

        Key(char[][] grid) {
            for (int x = 0; x < grid[0].length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    if (grid[grid.length - y - 1][x] == '.') {
                        signature.add(y);
                        break;
                    }
                }
            }
        }
    }
}
