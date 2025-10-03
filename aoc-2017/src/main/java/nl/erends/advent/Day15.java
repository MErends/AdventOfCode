package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

public class Day15 extends AbstractProblem<String, Integer> {

    void main() {
        new Day15().setAndSolve("699,124");
    }

    @Override
    public Integer solve1() {
        String[] values = input.split(",");
        Generator genA = new Generator(Integer.parseInt(values[0]), 16807);
        Generator genB = new Generator(Integer.parseInt(values[1]), 48271);
        int equals = 0;
        for (int i = 0; i < 40_000_000; i++) {
            long a = genA.getNextNumber();
            long b = genB.getNextNumber();
            if ((a & 0xffff) == (b & 0xffff)) {
                equals++;
            }

        }
        return equals;
    }

    @Override
    public Integer solve2() {
        String[] values = input.split(",");
        Generator genA = new Generator(Integer.parseInt(values[0]), 16807);
        Generator genB = new Generator(Integer.parseInt(values[1]), 48271);
        int equals = 0;
        for (int i = 0; i < 5_000_000; i++) {
            long a = genA.getNextMultiple(4);
            long b = genB.getNextMultiple(8);
            if ((a & 0xffff) == (b & 0xffff)) {
                equals++;
            }

        }
        return equals;
    }

    private static class Generator {

        private long number;
        private final int multiplier;


        Generator(int seed, int multiplier) {
            number = seed;
            this.multiplier = multiplier;
        }

        long getNextNumber() {
            number = (number * multiplier) % 2147483647;
            return number;
        }

        long getNextMultiple(long factor) {
            do {
                getNextNumber();
            } while (number % factor != 0);
            return number;
        }
    }
}
