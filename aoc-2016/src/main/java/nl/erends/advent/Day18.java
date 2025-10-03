package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day18 extends AbstractProblem<String, Integer> {

    void main() {
        new Day18().setAndSolve(Util.readLine(2016, 18));
    }
    
    @Override
    public Integer solve1() {
        int answer1 = 0;
        char[][] floor = new char[400000][input.length()];
        for (int x = 0; x < input.length(); x++) {
            floor[0][x] = input.charAt(x);
        }
        for (int y = 1; y < floor.length; y++) {
            for (int x = 0; x < floor[y].length; x++) {
                floor[y][x] = determineChar(x, y, floor);
            }
        }
        int numSaveTiles = 0;
        for (int y = 0; y < floor.length; y++) {
            if (y == 40) {
                answer1 = numSaveTiles;
            }
            for (int x = 0; x < floor[y].length; x++) {
                if (floor[y][x] == '.') numSaveTiles++;
            }
        }
        answer2 = numSaveTiles;
        return answer1;
    }


    private char determineChar(int x, int y, char[][] floor) {
        char left = x == 0 ? '.' : floor[y - 1][x - 1];
        char center = floor[y - 1][x];
        char right = x == floor[0].length - 1 ? '.' : floor[y - 1][x + 1];
        String row = "" + left + center + right;
        return row.equals("^^.") || row.equals(".^^") || row.equals("^..") || row.equals("..^") ? '^' : '.';
    }
}
