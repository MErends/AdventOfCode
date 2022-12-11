package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 11: Monkey in the Middle ---
 * <p>Monkeys are playing Keep Away with your missing things! To get your stuff
 * back, you need to be able to predict where the monkeys will throw your items.
 * After some careful observation, you realize the monkeys operate based on how
 * worried you are about each item. Figure out which monkeys to chase by
 * counting how many items they inspect. What is the level of monkey business
 * after many rounds of stuff-slinging simian shenanigans?
 * <p><a href="https://adventofcode.com/2022/day/11">2022 Day 11</a>
 */
public class Day11 extends AbstractProblem<List<String>, Long> {

    private final List<Monkey> monkeys = new ArrayList<>();
    private boolean part2 = false;
    private final List<Integer> divisors = new ArrayList<>();

    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readInput(2022, 11, 1));
    }

    @Override
    protected Long solve1() {
        for (int i = 0; i < input.size(); i += 7) {
            monkeys.add(new Monkey(input.subList(i, i + 6)));
            divisors.add(Integer.parseInt(input.get(i + 3).substring(21)));
        }
        for (int i = 0; i < 20; i++) {
            monkeys.forEach(Monkey::throwItems);
        }
        return monkeys.stream()
                .mapToLong(m -> m.inspects)
                .sorted()
                .skip(monkeys.size() - 2L)
                .reduce(1, (i1, i2) -> i1*i2);
    }

    @Override
    public Long solve2() {
        part2 = true;
        monkeys.clear();
        for (int i = 0; i < input.size(); i += 7) {
            monkeys.add(new Monkey(input.subList(i, i + 6)));
        }
        for (int i = 0; i < 10_000; i++) {
            Timer.tick(i);
            monkeys.forEach(Monkey::throwItems);
        }
        return monkeys.stream()
                .mapToLong(m -> m.inspects)
                .sorted()
                .skip(monkeys.size() - 2L)
                .reduce(1, (i1, i2) -> i1*i2);
    }

    private class Monkey {
        long inspects = 0;
        int modulo;
        List<Item> items = new ArrayList<>();
        boolean multiply;
        int factor;
        boolean square;
        int monkeyTrue;
        int monkeyFalse;

        Monkey(List<String> list) {
            Arrays.stream(list.get(1).substring(18).split(", "))
                    .forEach(s -> items.add(new Item(Integer.parseInt(s))));
            multiply = list.get(2).charAt(23) == '*';
            if ("old".equals(list.get(2).substring(25))) {
                square = true;
            } else {
                factor = Integer.parseInt(list.get(2).substring(25));
            }
            modulo = Integer.parseInt(list.get(3).substring(21));
            monkeyTrue = Integer.parseInt(list.get(4).substring(29));
            monkeyFalse = Integer.parseInt(list.get(5).substring(30));
        }

        private void throwItems() {
            inspects += items.size();
            for (Item item : items) {
                if (square) {
                    item.square();
                } else if (multiply) {
                    item.multiply(factor);
                } else {
                    item.add(factor);
                }
                if (!part2) {
                    item.value = item.value / 3;
                }
                if (item.modulo(modulo) == 0) {
                    monkeys.get(monkeyTrue).items.add(item);
                } else {
                    monkeys.get(monkeyFalse).items.add(item);
                }
            }
            items.clear();
        }
    }

    private class Item {
        int value;
        Map<Integer, Integer> moduloMap = new HashMap<>();

        Item(int value) {
            this.value = value;
            for (int divisor : divisors) {
                moduloMap.put(divisor, value % divisor);
            }
        }

        void square() {
            value *= value;
            for (Map.Entry<Integer, Integer> entry : moduloMap.entrySet()) {
                entry.setValue((entry.getValue() * entry.getValue()) % entry.getKey());
            }
        }

        void multiply(int factor) {
            this.value *= factor;
            for (Map.Entry<Integer, Integer> entry : moduloMap.entrySet()) {
                entry.setValue((entry.getValue() * factor) % entry.getKey());
            }
        }

        void add(int adder) {
            this.value += adder;
            for (Map.Entry<Integer, Integer> entry : moduloMap.entrySet()) {
                entry.setValue((entry.getValue() + adder) % entry.getKey());
            }
        }

        int modulo(int divisor) {
            if (part2) {
                return moduloMap.get(divisor);
            } else {
                return this.value % divisor;
            }
        }
    }
}
