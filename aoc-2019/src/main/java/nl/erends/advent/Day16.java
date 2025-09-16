package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day16 extends AbstractProblem<String, String> {

    static void main() {
        new Day16().setAndSolve(Util.readLine(2019, 16));
    }

    @Override
    public String solve1() {
        String signal = input;
        for (int i = 0; i < 100; i++) {
            signal = applyFFT(signal);
        }
        return signal.substring(0, 8);
    }

    @Override
    public String solve2() {
        int offset = Integer.parseInt(input.substring(0, 7));
        StringBuilder signal = new StringBuilder();
        signal.append(input.repeat(10_000));
        if (offset < signal.length() / 2) {
            throw new IllegalArgumentException("Offset too low!");
        }
        signal = new StringBuilder(signal.substring(offset));
        for (int i = 0; i < 100; i++) {
            StringBuilder newSignal = new StringBuilder();
            int digit = 0;
            for (int index = signal.length() - 1; index >= 0; index--) {
                digit = (digit + Integer.parseInt("" + signal.charAt(index))) % 10;
                newSignal.insert(0, digit);
            }
            signal = newSignal;
        }
        return signal.substring(0, 8);
    }

    private String applyFFT(String inputString) {
        List<Integer> input = new ArrayList<>();
        for (char c : inputString.toCharArray()) {
            input.add(Integer.parseInt("" + c));
        }
        StringBuilder output = new StringBuilder();
        int repeating = 1;
        while (!input.isEmpty()) {
            int result = 0;
            for (int pointer = 0; pointer < input.size(); pointer += 4 * repeating) {
                for (int step = 0; step < repeating && pointer + step < input.size(); step++) {
                    result += input.get(pointer + step);
                }
            }
            for (int pointer = 2 * repeating; pointer < input.size(); pointer += 4 * repeating) {
                for (int step = 0; step < repeating && pointer + step < input.size(); step++) {
                    result -= input.get(pointer + step);
                }
            }
            input.removeFirst();
            output.append(Math.abs(result % 10));
            repeating++;
        }
        return output.toString();
    }
}
