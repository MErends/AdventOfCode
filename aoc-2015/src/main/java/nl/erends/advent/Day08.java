package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day08 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day08().setAndSolve(Util.readInput(2015, 8));
    }

    @Override
    public Integer solve1() {
        int totalDiff = 0;
        for (String line : input) {
            int difference = 2;
            line = line.substring(1, line.length() - 1);
            int pointer = 0;
            while (pointer < line.length()) {
                char c = line.charAt(pointer);
                if (c == '\\') {
                    char c1 = line.charAt(pointer + 1);
                    if (c1 == 'x') {
                        difference += 3;
                        pointer += 3;
                    } else {
                        difference += 1;
                        pointer++;
                    }
                }
                pointer++;
            }
            totalDiff += difference;
        }
        return totalDiff;
    }

    @Override
    public Integer solve2() {
        int totalDiff = 0;
        for (String line : input) {
            totalDiff += 2;
            for (char c : line.toCharArray()) {
                if (c == '\\' || c == '\"') {
                    totalDiff++;
                }
            }
        }
        return totalDiff;
    }
}
