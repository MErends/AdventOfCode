package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 extends AbstractProblem<List<String>, Integer> {

    private char[][] field;

    static void main() {
        new Day18().setAndSolve(Util.readInput(2018, 18));
    }
    
    @Override
    public Integer solve1() {
        readInput();
        for (int i = 0; i < 10; i++) {
            iterate();
        }
        int woodCount = countChar(field, '|');
        int lumberCount = countChar(field, '#');
        return woodCount * lumberCount;
    }
    
    @Override
    public Integer solve2() {
        readInput();
        int turn = 0;
        Map<Integer, char[][]> turnFieldMap = new HashMap<>();
        turnFieldMap.put(0, field);
        while (true) {
            turn++;
            iterate();
            for (Map.Entry<Integer, char[][]> entry : turnFieldMap.entrySet()) {
                char[][] oldField = entry.getValue();
                if (Arrays.deepEquals(oldField, field)) {
                    int firstOccurance = entry.getKey();
                    int period = turn - firstOccurance;
                    int targetValue = 1000000000;
                    int findValue = ((targetValue - firstOccurance) % period) + firstOccurance;
                    char[][] findField = turnFieldMap.get(findValue);
                    int woodCount = countChar(findField, '|');
                    int lumberCount = countChar(findField, '#');
                    return  woodCount * lumberCount;
                }
            }
            turnFieldMap.put(turn, field);
        }
    }

    private void readInput() {
        field = new char[input.size()][];
        for (int index = 0; index < input.size(); index++) {
            field[index] = input.get(index).toCharArray();
        }
    }
    
    private int countChar(char[][] field, char c) {
        int count = 0;
        for (char[] row : field) {
            for (char acre : row) {
                count += acre == c ? 1 : 0;
            }
        }
        return count;
    }
    
    private int countChar(int xMin, int xMax, int yMin, int yMax, char c) {
        int count = 0;
        for (int yIndex = yMin; yIndex <= yMax; yIndex++) {
            for (int xIndex = xMin; xIndex <= xMax; xIndex++) {
                if (c == field[yIndex][xIndex]) count++;
            }
        }
        return count;
    }
    
    private void iterate() {
        char[][] newField = new char[field.length][field[0].length];
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                newField[y][x] = getNextState(field, x, y);
            }
        }
        field = newField;
    }
    
    private char getNextState(char[][] field, int x, int y) {
        int xMin = Math.max(x - 1, 0);
        int xMax = Math.min(x + 1, field[0].length - 1);
        int yMin = Math.max(y - 1, 0);
        int yMax = Math.min(y + 1, field.length - 1);
        int treeCount = countChar(xMin, xMax, yMin, yMax, '|');
        int lumberCount = countChar(xMin, xMax, yMin, yMax, '#');

        char c = field[y][x];
        if (c == '.') return treeCount >= 3 ? '|' : c;
        if (c == '|') return lumberCount >= 3 ?  '#' : c;
        lumberCount--;
        if (lumberCount == 0 || treeCount == 0) 
            return '.';
        else
            return '#';
    } 
}
