package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day01().setAndSolve(Util.readInput(2020, 1));
    }
    
    @Override
    public Integer solve1() {
        int answer1 = 0;
        List<Integer> listOfNumbers = input.stream().map(Integer::parseInt).collect(Collectors.toList());
        for (int a = 0; a < listOfNumbers.size(); a++) {
            for (int b = a + 1; b < listOfNumbers.size(); b++) {
                int numberA = listOfNumbers.get(a);
                int numberB = listOfNumbers.get(b);
                if (numberA + numberB == 2020) {
                    answer1 = numberA * numberB;
                }
                for (int c = b + 1; c < listOfNumbers.size(); c++) {
                    int numberC = listOfNumbers.get(c);
                    if (numberA + numberB + numberC == 2020) {
                        answer2 = numberA * numberB * numberC;
                    }
                }
            }
        }
        return answer1;
    }
}
