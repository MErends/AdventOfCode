package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day02 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readInput(2017, 2));
    }

    @Override
    public Integer solve1() {
        int checksum = 0;
        for (String line : input) {
            int highest = Integer.MIN_VALUE;
            int lowest = Integer.MAX_VALUE;
            String[] numbers = line.split("\t");
            for (String number : numbers) {
                int value = Integer.parseInt(number);
                highest = Math.max(highest, value);
                lowest = Math.min(lowest, value);
            }
            checksum += (highest - lowest);
        }
        return checksum;
    }

    @Override
    public Integer solve2() {
        int checksum = 0;
        for (String line : input) {
            checksum += getChecksum(line);
        }
        return checksum;
    }

    private int getChecksum(String line) {
        int checksum = 0;
        String[] numbers = line.split("\t");
        for (String numberA : numbers) {
            int valueA = Integer.parseInt(numberA);
            for (String numberB : numbers) {
                int valueB = Integer.parseInt(numberB);
                if (valueA > valueB && valueA % valueB == 0) {
                    checksum += valueA / valueB;
                } else if (valueB > valueA && valueB / valueA == 0) {
                    checksum += valueB / valueA;
                }
                if (checksum != 0) {
                    return checksum;
                }
            }
        }
        return checksum;
    }
}
