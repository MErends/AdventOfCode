package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day01 extends AbstractProblem<List<Integer>, Integer> {

    static void main() {
        new Day01().setAndSolve(Util.readIntegers(2020, 1));
    }
    
    @Override
    public Integer solve1() {
        int answer1 = 0;
        for (int a = 0; a < input.size(); a++) {
            for (int b = a + 1; b < input.size(); b++) {
                int numberA = input.get(a);
                int numberB = input.get(b);
                if (numberA + numberB == 2020) {
                    answer1 = numberA * numberB;
                }
                for (int c = b + 1; c < input.size(); c++) {
                    int numberC = input.get(c);
                    if (numberA + numberB + numberC == 2020) {
                        answer2 = numberA * numberB * numberC;
                    }
                }
            }
        }
        return answer1;
    }
}
