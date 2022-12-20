package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day09 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readLine(2017, 9));
    }
    
    @Override
    public Integer solve1() {
        char[] tokens = input.toCharArray();
        int depth = 0;
        int score = 0;
        int cancelled = 0;
        boolean inGarbage = false;
        int pointer = 0;
        while (pointer < tokens.length) {
            char token = tokens[pointer];
            if (inGarbage && token != '>' && token != '!') {
                cancelled++;
                pointer++;
                continue;
            }
            switch (token) {
                case '{' -> depth++;
                case '}' -> {
                    score += depth;
                    depth--;
                }
                case '!' -> pointer++;
                case '<' -> inGarbage = true;
                case '>' -> inGarbage = false;
                default -> { // Other char
                }
            }
            pointer++;
        }
        answer2 = cancelled;
        return score;
    }
}
