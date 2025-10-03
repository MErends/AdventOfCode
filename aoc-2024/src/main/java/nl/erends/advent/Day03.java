package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 3: Mull It Over ---</h1>
 * <p>The computer appears to be trying to run a program, but its memory is
 * corrupted. All of the instructions have been jumbled up! It seems like the
 * goal of the program is just to multiply some numbers. It does that with
 * instructions like mul(X,Y). Scan the corrupted memory for uncorrupted mul
 * instructions. What do you get if you add up all of the results of the
 * multiplications?</p>
 * <p><a href="https://adventofcode.com/2024/day/3">2024 Day 3</a></p>
 */
public class Day03 extends AbstractProblem<List<String>, Integer> {

    public static final Pattern p = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    void main() {
        new Day03().setAndSolve(Util.readInput(2024, 3));
    }

    @Override
    protected Integer solve1() {
        String line = String.join("", input);
        return calculateValue(line);
    }

    @Override
    public Integer solve2() {
        String line = String.join("", input);
        String[] dos = line.split("do\\(\\)");
        line = Arrays.stream(dos)
                .map(segment -> segment.split("don't\\(\\)")[0])
                .collect(Collectors.joining());
        return calculateValue(line);
    }

    private int calculateValue(String line) {
        Matcher m = p.matcher(line);
        int result = 0;
        while (m.find()) {
            result += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
        }
        return result;
    }
}
