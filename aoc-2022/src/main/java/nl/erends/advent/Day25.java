package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 25: Full of Hot Air ---
 * <p>To heat the fuel, Bob needs to know the total amount of fuel that will be
 * processed ahead of time so it can correctly calibrate heat output and flow
 * rate. You just need to add together the Special Numeral-Analogue Fuel Units.
 * What SNAFU number do you supply to Bob's console?
 * <p><a href="https://adventofcode.com/2022/day/25">2022 Day 25</a>
 */
public class Day25 extends AbstractProblem<List<String>, String> {

    private final Map<Character, Integer> snafuMap = Map.of('2', 2, '1', 1, '0', 0, '-', -1, '=', -2);
    private final Map<Integer, Character> decMap = Map.of(2, '2', 1, '1', 0, '0', -1, '-', -2, '=');

    static void main() {
        new Day25().setAndSolve(Util.readInput(2022, 25));
    }

    @Override
    public String solve1() {
        return dec2snafu(input.stream().mapToLong(this::snafu2dec).sum());
    }

    private long snafu2dec(String snafu) {
        long dec = 0;
        long factor = 1;
        for (int index = snafu.length() - 1; index >= 0; index--) {
            char snafuDigit = snafu.charAt(index);
            int decDigit = snafuMap.get(snafuDigit);
            dec += decDigit * factor;
            factor *= 5;
        }
        return dec;
    }

    private String dec2snafu(long dec) {
        Map<Integer, Integer> digitMap = new HashMap<>();
        long factor = 1;
        int position = 1;
        while (factor < dec) {
            factor *= 5;
            position++;
        }
        while (position != 0) {
            int count = (int) (dec / factor);
            dec -= factor * count;
            digitMap.put(position, count);
            factor /= 5;
            position--;
        }
        StringBuilder snafuBuilder = new StringBuilder();
        for (position = 1; position <= digitMap.size(); position++) {
            if (digitMap.get(position) > 2) {
                digitMap.computeIfPresent(position, (_, v) -> v - 5);
                digitMap.computeIfPresent(position + 1, (_, v) -> v + 1);
            }
        }
        for (position = digitMap.size(); position > 0; position--) {
            snafuBuilder.append(decMap.get(digitMap.get(position)));
        }
        String snafu = snafuBuilder.toString();
        if (snafu.startsWith("0")) {
            snafu = snafu.substring(1);
        }
        return snafu;
    }
}
