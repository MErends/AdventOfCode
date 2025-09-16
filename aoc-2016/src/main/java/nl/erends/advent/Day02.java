package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day02 extends AbstractProblem<List<String>, String> {

    private char[][] activePad;
    private int x;
    private int y;

    static void main() {
        new Day02().setAndSolve(Util.readInput(2016, 2));
    }

    @Override
    protected String solve1() {
        activePad = new char[][]{
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}};
        x = 1;
        y = 1;
        return getCode();
    }

    @Override
    public String solve2() {
        activePad = new char[][]{
                {'\0', '\0', '1', '\0', '\0'},
                {'\0', '2', '3', '4', '\0'},
                {'5', '6', '7', '8', '9'},
                {'\0', 'A', 'B', 'C', '\0'},
                {'\0', '\0', 'D', '\0', '\0'}};
        x = 0;
        y = 2;
        return getCode();
    }

    private String getCode() {
        StringBuilder code = new StringBuilder();
        for (String line : input) {
            for (char direction : line.toCharArray()) {
                updatePosition(direction);
            }
            code.append(activePad[y][x]);
        }
        return code.toString();
    }

    private void updatePosition(char direction) {
        switch (direction) {
            case 'L':
                if (x > 0 && activePad[y][x - 1] != '\0') {
                    x--;
                }
                break;
            case 'R':
                if (x < activePad[y].length - 1 && activePad[y][x + 1] != '\0') {
                    x++;
                }
                break;
            case 'U':
                if (y > 0 && activePad[y - 1][x] != '\0') {
                    y--;
                }
                break;
            case 'D':
                if (y < activePad.length - 1 && activePad[y + 1][x] != '\0') {
                    y++;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }
}
