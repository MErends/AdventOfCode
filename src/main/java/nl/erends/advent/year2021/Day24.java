package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * --- Day 24: Arithmetic Logic Unit ---
 * <p>To enable as many submarine features as possible, find the largest and
 * smallest valid fourteen-digit model number that contains no 0 digits. What is
 * the largest model number accepted by MONAD?
 * <p><a href="https://adventofcode.com/2021/day/24">2021 Day 24</a>
 */
public class Day24 extends AbstractProblem<List<String>, String> {
    
    private final List<Boolean> shouldDecreaseList = new ArrayList<>();
    private final List<Integer> var1List = new ArrayList<>();
    private final List<Integer> var2List = new ArrayList<>();
    private List<Integer> digitList = new ArrayList<>();
       
    
    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2021, 24));
    }

    @Override
    protected String solve1() {
        for (int index = 0; index < input.size(); index += 18) {
            shouldDecreaseList.add(input.get(index + 4).endsWith("26"));
            var1List.add(Integer.parseInt(input.get(index + 5).split(" ")[2]));
            var2List.add(Integer.parseInt(input.get(index + 15).split(" ")[2]));
        }
        for (int i = 9; i > 0; i--) {
            digitList.add(i);
        }
        return findSerial(0, 0, "");
    }

    @Override
    public String solve2() {
        digitList = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());
        return findSerial(0, 0, "");
    }
    
    private String findSerial(int digitIndex, int currentChecksum, String currentSerial) {
        if (digitIndex == var1List.size()) {
            if (currentChecksum == 0) {
                return currentSerial;
            } else {
                return null;
            }
        }
        boolean shouldDecrease = shouldDecreaseList.get(digitIndex);
        int var1 = var1List.get(digitIndex);
        int var2 = var2List.get(digitIndex);
        for (int digit : digitList) {
            int newChecksum = calcuateChecksum(digit, currentChecksum, shouldDecrease, var1, var2);
            if (shouldDecrease && newChecksum * 2 > currentChecksum) {
                continue;
            }
            String nextMatch =  findSerial(digitIndex + 1, newChecksum, currentSerial + digit);
            if (nextMatch == null) {
                continue;
            }
            return nextMatch;
        }
        return null;
    }
    
    private int calcuateChecksum(int digit, int checksum, boolean shouldDecrease, int var1, int var2) {
        int x = checksum % 26 + var1;
        if (shouldDecrease) {
            checksum /= 26;
        }
        if (x != digit) {
            checksum = checksum * 26 + (digit + var2);
        }
        return checksum;
    }
}
