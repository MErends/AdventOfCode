package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 20: Grove Positioning System ---
 * <p>Your handheld device has a file (your puzzle input) that contains the
 * grove's coordinates! Unfortunately, the file is encrypted. The numbers should
 * be moved in the order they originally appear in the encrypted file. Numbers
 * moving around during the mixing process do not change the order in which the
 * numbers are moved. What is the sum of the three numbers that form the grove
 * coordinates?
 * <p><a href="https://adventofcode.com/2022/day/20">2022 Day 20</a>
 */
public class Day20 extends AbstractProblem<List<String>, Long> {

    public static void main(String[] args) {
        new Day20().setAndSolve(Util.readInput(2022, 20));
    }

    @Override
    protected Long solve1() {
        return decrypt(1, 1);
    }

    @Override
    public Long solve2() {
        return decrypt(811589153L, 10);
    }

    private long decrypt(long factor, int loops) {
        List<Number> order = input.stream()
                .mapToLong(Long::parseLong)
                .mapToObj(Number::new).toList();
        order.forEach(n -> n.value *= factor);
        List<Number> numbers = new ArrayList<>(order);
        for (int loop = 0; loop < loops; loop++) {
            for (Number number : order) {
                int oldIndex = numbers.indexOf(number);
                numbers.remove(number);
                int steps = (int) (number.value % numbers.size());
                int newIndex = (oldIndex + steps + numbers.size()) % numbers.size();
                numbers.add(newIndex, number);
            }
        }
        Number zero = numbers.stream()
                .filter(n -> n.value == 0)
                .findFirst().orElseThrow();
        int startIndex = numbers.indexOf(zero);
        long oneK = numbers.get((startIndex + 1000) % numbers.size()).value;
        long twoK = numbers.get((startIndex + 2000) % numbers.size()).value;
        long threeK = numbers.get((startIndex + 3000) % numbers.size()).value;
        return oneK + twoK + threeK;
    }

    private static class Number {

        long value;

        Number(long value) {
            this.value = value;
        }
    }
}
