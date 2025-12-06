package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongBinaryOperator;

/**
 * <h1>--- Day 6: Trash Compactor ---</h1>
 * <p>A brief fall later, you find yourself in a garbage smasher. As you try to
 * find a way out, you are approached by a family of cephalopods! While you
 * wait, they're curious if you can help the youngest cephalopod with her math
 * homework. Solve the problems on the math worksheet. What is the grand total
 * found by adding together all of the answers to the individual problems?</p>
 * <p><a href="https://adventofcode.com/2025/day/6">2025 Day 6</a></p>
 */
public class Day06 extends AbstractProblem<List<String>, Long> {

    LongBinaryOperator sum = Long::sum;
    LongBinaryOperator multiply = (l1, l2) -> l1 * l2;

    void main() {
        new Day06().setAndSolve(Util.readInput(2025, 6));
    }

    @Override
    protected Long solve1() {
        List<List<String>> lines = new ArrayList<>();

        input.forEach(line -> lines.add(List.of(line.trim().split("\\s+"))));

        List<String> operands = lines.removeLast();
        long result = 0;
        for (int column = 0; column < operands.size(); column++) {
            long identity = 0;
            LongBinaryOperator operator = sum;
            if (operands.get(column).equals("*")) {
                identity = 1;
                operator = multiply;
            }
            List<Long> numbers = new ArrayList<>();
            for (List<String> line : lines) {
                numbers.add(Long.parseLong(line.get(column)));
            }
            result += numbers.stream()
                    .mapToLong(l -> l)
                    .reduce(identity, operator);
        }
        return result;
    }

    @Override
    public Long solve2() {
        long result = 0L;
        input.replaceAll(l -> l + " ");
        String operators = input.removeLast();
        while (!operators.isEmpty()) {
            int highIndex = 1;
            while (highIndex < operators.length() && operators.charAt(highIndex) == ' ') {
                highIndex++;
            }
            List<Long> numbers = new ArrayList<>();
            for (int index = 0; index < highIndex - 1; index++) {
                StringBuilder number = new StringBuilder();
                for (String line : input) {
                    if (line.charAt(index) != ' ') {
                        number.append(line.charAt(index));
                    }
                }
                numbers.add(Long.parseLong(number.toString()));
            }
            long identity = 0;
            LongBinaryOperator operator = sum;
            if (operators.charAt(0) == '*') {
                identity = 1;
                operator = multiply;
            }
            result += numbers.stream()
                    .mapToLong(l -> l)
                    .reduce(identity, operator);
            
            int splitIndex = highIndex;
            operators = operators.substring(splitIndex);
            input.replaceAll(l -> l.substring(splitIndex));
        }
        return result;
    }
}
