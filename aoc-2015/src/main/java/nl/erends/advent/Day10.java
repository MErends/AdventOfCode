package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day10 extends AbstractProblem<String, Integer> {
    
    private String sequence;
    private int timesIterated;
    private int target1 = 40;
    private int target2 = 50;

    static void main() {
        new Day10().setAndSolve(Util.readLine(2015, 10));
    }

    @Override
    public Integer solve1() {
        sequence = input;
        for (timesIterated = 0; timesIterated < target1; timesIterated++) {
            sequence = nextSequence(sequence);
        }
        return sequence.length();
    }

    @Override
    public Integer solve2() {
        if (sequence == null) {
            solve1();
        }
        for ( ; timesIterated < target2; timesIterated++) {
            sequence = nextSequence(sequence);
        }
        return sequence.length();
    }

    private String nextSequence(String inputString) {
        StringBuilder inputSb = new StringBuilder(inputString);
        StringBuilder output = new StringBuilder();
        while (!inputSb.isEmpty()) {
            for (int i = 0; i < inputSb.length(); i++) {
                char c = inputSb.charAt(0);
                if (inputSb.charAt(i) != c || i == inputSb.length() - 1) {
                    if (inputSb.charAt(i) != c) {
                        output.append(i).append(c);
                        inputSb.delete(0, i);
                    } else {
                        output.append(i + 1).append(c);
                        inputSb.delete(0, inputSb.length());
                    }
                    break;
                }
            }
        }
        return output.toString();
    }

    public void setTarget1(int target1) {
        this.target1 = target1;
    }

    public void setTarget2(int target2) {
        this.target2 = target2;
    }
}

