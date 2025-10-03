package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day12 extends AbstractProblem<String, Integer> {
    void main() {
        new Day12().setAndSolve(Util.readLine(2015, 12));
    }

    @Override
    public Integer solve1() {
        int sum = 0;
        char[] line = input.toCharArray();
        Set<Character> allowed = new HashSet<>();
        allowed.add('0');
        allowed.add('1');
        allowed.add('2');
        allowed.add('3');
        allowed.add('4');
        allowed.add('5');
        allowed.add('6');
        allowed.add('7');
        allowed.add('8');
        allowed.add('9');
        allowed.add('-');
        for (int i = 0; i < line.length; i++) {
            if (!allowed.contains(line[i])) {
                line[i] = ' ';
            }
        }
        String lineString = new String(line);
        String[] words = lineString.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                sum += Integer.parseInt(word);
            }
        }
        return sum;
    }
}
