package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day08 extends AbstractProblem<String, String> {
    
    private char[][][] data;
    private static final int COLUMNS = 25;
    private static final int ROWS = 6;

    void main() {
        new Day08().setAndSolve(Util.readLine(2019, 8));
    }

    @Override
    public String solve1() {
        loadData();
        int targetLayer = getTargetLayer();
        int countOnes = 0;
        int countTwos = 0;
        for (char[] row : data[targetLayer]) {
            for (char c : row) {
                if (c == '1') {
                    countOnes++;
                } else if (c == '2') {
                    countTwos++;
                }
            }
        }
        return Integer.toString(countOnes * countTwos);
    }

    private int getTargetLayer() {
        int targetLayer = -1;
        int fewestZeros = Integer.MAX_VALUE;
        for (int layer = 0; layer < data.length; layer++) {
            int currentZeros = 0;
            for (char[] row : data[layer]) {
                for (char c : row) {
                    if (c == '0') {
                        currentZeros++;
                    }
                }
            }
            if (currentZeros < fewestZeros) {
                fewestZeros = currentZeros;
                targetLayer = layer;
            }
        }
        return targetLayer;
    }

    private void loadData() {
        final int LAYERS = input.length() / COLUMNS / ROWS;
        data = new char[LAYERS][ROWS][COLUMNS];
        for (int layer = 0; layer < LAYERS; layer++) {
            for (int row = 0; row < ROWS; row++) {
                int startIndex = layer * ROWS * COLUMNS + row * COLUMNS;
                int endIndex = startIndex + COLUMNS;
                data[layer][row] = input.substring(startIndex, endIndex).toCharArray();
            }
        }
    }

    @Override
    public String solve2() {
        if (data == null) {
            loadData();
        }
        char[][] image = new char[ROWS][COLUMNS];
        for (int layer = data.length - 1; layer >= 0; layer--) {
            for (int row = 0; row < data[layer].length; row++) {
                for (int column = 0; column < data[layer][row].length; column++) {
                    char c = data[layer][row][column];
                    if (c == '0') {
                        image[row][column] = ' ';
                    } else if (c == '1') {
                        image[row][column] = '#';
                    }
                }
            }
        }
        StringBuilder output = new StringBuilder();
        for (char[] row : image) {
            output.append(row).append('\n');
        }
        return output.toString();
    }
}
