package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day03 extends AbstractProblem<List<String>, Long> {

    void main() {
        new Day03().setAndSolve(Util.readInput(2020, 3));
    }

    public Long solve1() {
        char[][] field = readField();
        return treeCount(3, 1, field);
    }

    @Override
    public Long solve2() {
        char[][] field = readField();
        long treeResult = 1;
        int[] rightArr = {1,3,5,7};
        for (int right : rightArr) {
            treeResult *= treeCount(right, 1, field);
        }
        return treeResult * treeCount(1, 2, field);
    }

    private long treeCount(int right, int down, char[][] field) {
        long treeCount = 0;
        int x = 0;
        int y = 0;
        while (y < field.length) {
            if (field[y][x] == '#') {
                treeCount++;
            }
            x = (x + right) % field[y].length;
            y += down;
        }
        return treeCount;
    }

    private char[][] readField() {
        char[][] field = new char[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            field[y] = input.get(y).toCharArray();
        }
        return field;
    }
}
