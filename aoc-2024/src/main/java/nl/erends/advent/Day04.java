package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>--- Day 4: Ceres Search ---</h1>
 * <p>As the search for the Chief continues, a small Elf who lives on the
 * station tugs on your shirt; she'd like to know if you could help her with her
 * word search. She only has to find one word: XMAS. Take a look at the little
 * Elf's word search. How many times does XMAS appear?</p>
 * <p><a href="https://adventofcode.com/2024/day/4">2024 Day 4</a></p>
 */
public class Day04 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2024, 4));
    }

    @Override
    protected Integer solve1() {
        List<String> lines = new ArrayList<>(input);
        List<String> verticals = new ArrayList<>();
        for (int x = 0; x < input.size(); x++) {
            StringBuilder line = new StringBuilder();
            for (int y = 0; y < input.get(x).length(); y++) {
                line.append(input.get(y).charAt(x));
            }
            verticals.add(line.toString());
        }
        lines.addAll(verticals);
        lines.addAll(getDiagonals());
        Pattern p1 = Pattern.compile("XMAS");
        Pattern p2 = Pattern.compile("SAMX");
        int count = 0;
        for (String line : lines) {
            Matcher m1 = p1.matcher(line);
            Matcher m2 = p2.matcher(line);
            count += (int) m1.results().count();
            count += (int) m2.results().count();
        }
        return count;
    }

    private List<String> getDiagonals() {
        List<String> diagonals = new ArrayList<>();
        for (int x0 = 0; x0 < input.size() - 3; x0++) {
            StringBuilder negLine = new StringBuilder();
            StringBuilder posLine = new StringBuilder();
            int x = x0;
            for (int y = 0; x < input.size() && y < input.size(); x++, y++) {
                negLine.append(input.get(y).charAt(x));
                posLine.append(input.get(input.size() - 1 - y).charAt(x));
            }
            diagonals.add(negLine.toString());
            diagonals.add(posLine.toString());
        }
        for (int y0 = 1; y0 < input.size() - 3; y0++) {
            StringBuilder negLine = new StringBuilder();
            StringBuilder posLine = new StringBuilder();
            int y = y0;
            for (int x = 0; x < input.size() && y < input.size(); x++, y++) {
                negLine.append(input.get(y).charAt(x));
                posLine.append(input.get(input.size() - 1 - y).charAt(x));
            }
            diagonals.add(negLine.toString());
            diagonals.add(posLine.toString());
        }
        return diagonals;
    }

    @Override
    public Integer solve2() {
        char mAndS = 'M' + 'S';
        int count = 0;
        for (int x = 1; x < input.size() - 1; x++) {
            for (int y = 1; y < input.size() - 1; y++) {
                if (input.get(y).charAt(x) != 'A') {
                    continue;
                }
                // Wait, that's illegal!
                if (input.get(y - 1).charAt(x - 1) + input.get(y + 1).charAt(x + 1) == mAndS
                && input.get(y - 1).charAt(x + 1) + input.get(y + 1).charAt(x - 1) == mAndS) {
                    count++;
                }
            }
        }
        return count;
    }
}
