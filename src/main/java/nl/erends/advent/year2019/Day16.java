package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day16 extends AbstractProblem<String, String> {

    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readLine(2019, 16));
    }

    @Override
    public String solve1() {
        String signal = input;
        for (int i = 0; i < 100; i++) {
            signal = FFT.apply(signal, 1);
        }
        return signal.substring(0, 8);
    }

    @Override
    public String solve2() {
        int offset = Integer.parseInt(input.substring(0, 7));
        StringBuilder signalSB = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            signalSB.append(input);
        }
        String signal = signalSB.toString().substring(offset);
        for (int i = 0; i < 100; i++) {
            System.out.println("applying " + i + "/100");
            signal = FFT.apply(signal, offset);
        }
        return signal.substring(0, 8);
    }
}
