package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day01 extends AbstractProblem<String, Integer> {

    static void main() {
        new Day01().setAndSolve(Util.readLine(2015, 1));
    }
    
    @Override
    public Integer solve1() {
        int floor = 0;
        int position = 0;
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            position++;
            if (c == '(') {
                floor++;
            } else {
                floor--;
            }
            if (floor == -1 && answer2 == null) {
                answer2 = position;
            }
        }
        return floor;
    }
}
