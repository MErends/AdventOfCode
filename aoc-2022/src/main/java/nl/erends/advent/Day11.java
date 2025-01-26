package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongUnaryOperator;

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
    private long supermodulo = Integer.MAX_VALUE;
    private boolean worried;


    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readInput(2022, 11));
    }

    @Override
    protected Long solve1() {
        loadMonkeys();
        return throwAndResult(20);
    }

    @Override
    public Long solve2() {
        worried = true;
        loadMonkeys();
        supermodulo = 1;
        for (Monkey monkey : monkeys) {
            supermodulo = Util.lcm(supermodulo, monkey.modulo);
        }
        return throwAndResult(10_000);
    }

    private void loadMonkeys() {
        monkeys.clear();
        for (int i = 0; i < input.size(); i += 7) {
            monkeys.add(new Monkey(input.subList(i, i + 6)));
        }
    }

    private Long throwAndResult(int throwing) {
        for (int i = 0; i < throwing; i++) {
            monkeys.forEach(Monkey::throwItems);
        }
        return monkeys.stream()
                .mapToLong(m -> m.inspects)
                .sorted()
                .skip(monkeys.size() - 2L)
                .reduce(1, (i1, i2) -> i1 * i2);
    }

    private class Monkey {
        int inspects = 0;
        final List<Long> items = new ArrayList<>();
        final LongUnaryOperator operation;
        final int modulo;
        final int trueMonkey;
        final int falseMonkey;

        Monkey(List<String> list) {
            Arrays.stream(list.get(1).substring(18).split(", "))
                    .forEach(s -> items.add(Long.parseLong(s)));
            String operand = list.get(2).substring(25);
            if (list.get(2).charAt(23) == '*') {
                if ("old".equals(operand)) {
                    operation = o -> o * o;
                } else {
                    operation = o -> o * Integer.parseInt(operand);
                }
            } else {
                operation = o -> o + Integer.parseInt(operand);
            }
            modulo = Integer.parseInt(list.get(3).substring(21));
            trueMonkey = Integer.parseInt(list.get(4).substring(29));
            falseMonkey = Integer.parseInt(list.get(5).substring(30));
        }

        private void throwItems() {
            inspects += items.size();
            for (long item : items) {
                item = operation.applyAsLong(item);
                if (!worried) {
                    item /= 3;
                }
                int targetMonkey = item % modulo == 0 ? trueMonkey : falseMonkey;
                monkeys.get(targetMonkey).items.add(item % supermodulo);
            }
            items.clear();
        }
    }
}
