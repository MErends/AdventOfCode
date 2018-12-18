package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 {


    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day18.txt");
        long end;
        long mid = System.currentTimeMillis();
        long start = System.currentTimeMillis();
        char[][] field = new char[input.size()][];
        for (int index = 0; index < input.size(); index++) {
            field[index] = input.get(index).toCharArray();
        }
        int turn = 0;
        Map<char[][], Integer> fieldTurnMap = new HashMap<>();
        outer:
        while (true) {
            turn++;
            field = iterate(field);
            int woodCount = countChar(field, '|');
            int lumberCount = countChar(field, '#');
            int score = woodCount * lumberCount;
            if (turn == 10) {
                mid = System.currentTimeMillis();
                System.out.println(score);
            }
            for (char[][] oldField : fieldTurnMap.keySet()) {
                if (Arrays.deepEquals(oldField, field)) {
                    int firstOccurance = fieldTurnMap.get(oldField);
                    int period = turn - firstOccurance;
                    int targetValue = 1000000000;
                    int findValue = ((targetValue - firstOccurance) % period) + firstOccurance;
                    for (Map.Entry<char[][], Integer> entry : fieldTurnMap.entrySet()) {
                        if (entry.getValue() == findValue) {
                            char[][] findField = entry.getKey();
                            woodCount = countChar(findField, '|');
                            lumberCount = countChar(findField, '#');
                            score = woodCount * lumberCount;
                            end = System.currentTimeMillis();
                            System.out.println(score);
                            break outer;
                        }
                    }
                }
            }
            fieldTurnMap.put(field, turn);
        }
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    
    private static int countChar(char[][] field, char c) {
        int count = 0;
        for (char[] aField : field) {
            for (char anAField : aField) {
                count += anAField == c ? 1 : 0;
            }
        }
        return count;
    }
    
    private static char[][] iterate(char[][] field) {
        char[][] newField = new char[field.length][field[0].length];
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                newField[y][x] = getNextState(field, x, y);
            }
        }
        return newField;
    }
    
    private static char getNextState(char[][] field, int x, int y) {
        int xMin = Math.max(x - 1, 0);
        int xMax = Math.min(x + 1, field[0].length - 1);
        int yMin = Math.max(y - 1, 0);
        int yMax = Math.min(y + 1, field.length - 1);
        int treeCount = 0;
        int lumberCount = 0;
        for (int yIndex = yMin; yIndex <= yMax; yIndex++) {
            for (int xIndex = xMin; xIndex <= xMax; xIndex++) {
                char c = field[yIndex][xIndex];
                if (c == '|') treeCount++;
                if (c == '#') lumberCount++;
            }
        }
        char c = field[y][x];
        if (c == '.') return treeCount >= 3 ? '|' : c;
        if (c == '|') return lumberCount >= 3 ?  '#' : c;
        if (c == '#') {
            lumberCount--;
            if (lumberCount == 0 || treeCount == 0) 
                return '.';
            else
                return '#';
        }
        throw new IllegalStateException();
        
    } 
}
