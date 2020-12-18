package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day18 extends AbstractProblem<List<String>, Long> {

    private boolean part2 = false;

    public static void main(String[] args) {
        new Day18().setAndSolve(Util.readInput(2020, 18));
    }

    @Override
    public Long solve1() {
        return input.stream().map(this::solveExpr).mapToLong(Long::parseLong).sum();
    }

    private String solveExpr(String line) {
        if (line.indexOf('*') == -1 && line.indexOf('+') == -1) {
            return line;
        }
        int closing = line.indexOf(')');
        if (closing != -1) {
            int opening = closing - 1;
            while (line.charAt(opening) != '(') {
                opening--;
            }
            String subExpr = line.substring(opening, closing + 1);
            line = line.replace(subExpr, solveExpr(subExpr.substring(1, subExpr.length() - 1)));
            return solveExpr(line);
        }
        char plusIndex = (char) line.indexOf('+');
        char timesIndex = (char) line.indexOf('*');
        int operandIndex = Math.min(plusIndex, timesIndex);

        if (part2) {
            operandIndex = plusIndex > line.length() ? timesIndex : plusIndex;
        }
        int beginExp = operandIndex - 2;
        while (beginExp >= 0 && line.charAt(beginExp) != ' ') {
            beginExp--;
        }
        beginExp = Integer.max(0, beginExp + 1);
        int endExp = operandIndex + 2;
        while (endExp < line.length() && line.charAt(endExp) != ' ') {
            endExp++;
        }
        endExp = Integer.min(endExp, line.length());
        String subExpr = line.substring(beginExp, endExp);
        String[] split = subExpr.split(" ");
        long a = Long.parseLong(split[0]);
        char operand = split[1].charAt(0);
        long b = Long.parseLong(split[2]);
        String result;
        if (operand == '+') {
            result = Long.toString(a + b);
        } else {
            result = Long.toString(a * b);
        }
        return solveExpr(line.substring(0, beginExp) + result + line.substring(endExp));
    }

    @Override
    public Long solve2() {
        part2 = true;
        return solve1();
    }
}
