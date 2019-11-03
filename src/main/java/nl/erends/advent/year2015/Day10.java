package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

public class Day10 implements Problem<String, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day10.class);
    
    private String input = "";
    private String sequence;
    private int timesIterated;
    private int target1 = 40;
    private int target2 = 50;

    public static void main(String[] args) {
        String input = Util.readLine(2015, 10);
        Day10 problem = new Day10();
        LOG.info(problem.solve1(input));
        LOG.info(problem.solve2(input));
        Timer.printStats();
    }

    public Integer solve1(String input) {
        Timer.start1();
        this.input = input;
        sequence = input;
        for (timesIterated = 0; timesIterated < target1; timesIterated++) {
            sequence = nextSequence(sequence);
        }
        Timer.end1();
        return sequence.length();
    }
    
    public Integer solve2(String input) {
        Timer.start();
        if (!this.input.equals(input)) {
            solve1(input);
        }
        for ( ; timesIterated < target2; timesIterated++) {
            sequence = nextSequence(sequence);
        }
        Timer.end2();
        return sequence.length();
    }

    private String nextSequence(String inputString) {
        StringBuilder inputSb = new StringBuilder(inputString);
        StringBuilder output = new StringBuilder();
        while (inputSb.length() != 0) {
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

