package nl.erends.advent;


//     1 2 3   7 4 1
//     4 5 6   8 5 2
//     7 8 9   9 6 3

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 extends AbstractProblem<List<String>, Integer> {
    
    private final Map<String, String> mapping = new HashMap<>();
    
    static void main() {
        new Day21().setAndSolve(Util.readInput(2017, 21));
    }
    
    @Override
    public Integer solve1() {
        int answer1 = 0;
        for (String line : input) {
            String[] mappingArray = line.split("=>");
            mapping.put(mappingArray[0].trim(), mappingArray[1].trim());
        }
        char[][] grid = {{'.','#','.'},{'.', '.','#'},{'#','#','#'}};
        for (int iterations = 1 ; iterations <= 18; iterations++) {
            grid = enhanceGrid(grid);
            if (iterations == 5) {
                answer1 = pixelsOn(grid);
            }
            
        }
        answer2 = pixelsOn(grid);
        return answer1;
    }
    
    private char[][] enhanceGrid(char[][] grid) {
        int scale = grid.length % 2 == 0 ? 2 : 3;
        int newSize = scale == 2 ? grid.length / 2 * 3 : grid.length / 3 * 4;
        char[][] target = new char[newSize][newSize];
        for (int x = 0; x < grid.length / scale; x++) {
            for (int y = 0; y < grid.length / scale; y++) {
                char[][] subgrid = getSubgrid(grid, x, y);
                subgrid = getEnhancedSubgrid(subgrid);
                setSubgrid(target, subgrid, x, y);
            }
        }
        return target;
    }
    
    
    
    private char[][] getSubgrid(char[][] grid, int x, int y) {
        int scale = grid.length % 2 == 0 ? 2 : 3;
        char[][] subgrid = new char[scale][scale];
        for (int subgridY = 0; subgridY < scale; subgridY++) {
            System.arraycopy(grid[y * scale + subgridY], x * scale, subgrid[subgridY], 0, scale);
        }
        return subgrid;
    }
    
    private void setSubgrid(char[][] grid, char[][] subgrid, int x, int y) {
        int scale = subgrid.length;
        for (int subgridY = 0; subgridY < scale; subgridY++) {
            System.arraycopy(subgrid[subgridY], 0, grid[y * scale + subgridY], x * scale, scale);
        }
    }
    
    private char[][] getEnhancedSubgrid(char[][] input) {
        for (int i = 0; i < 4; i++) {
            if (mapping.containsKey(getStringRep(input))) {
                break;
            }
            rotate(input);
        }
        if (!mapping.containsKey(getStringRep(input))) {
            flip(input);
            for (int i = 0; i < 4; i++) {
                if (mapping.containsKey(getStringRep(input))) {
                    break;
                }
                rotate(input);
            }
        }
        String inputString = getStringRep(input);
        String outputString = mapping.get(inputString);
        String[] outputArray = outputString.split("/");
        char[][] output = new char[outputArray.length][outputArray.length];
        for (int y = 0; y < outputArray.length; y++) {
            output[y] = outputArray[y].toCharArray();
        }
        return output;
    }

    private String getStringRep(char[][] input) {
        StringBuilder inputString = new StringBuilder();
        for (char[] row : input) {
            inputString.append(row).append("/");
        }
        inputString.deleteCharAt(inputString.length() - 1);
        return inputString.toString();
    }

    private void rotate(char[][] input) {
        if (input.length == 2) {
            rotate2x2(input);
        } else {
            rotate3x3(input);
        }
    }
    
    private void rotate3x3(char[][] input) {
        char[][] temp = new char[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                temp[y][x] = input[y][x];
            }
        }
        input[0][0] = temp[2][0];
        input[0][1] = temp[1][0];
        input[0][2] = temp[0][0];
        input[1][0] = temp[2][1];
        input[1][2] = temp[0][1];
        input[2][0] = temp[2][2];
        input[2][1] = temp[1][2];
        input[2][2] = temp[0][2];
    }
    
    private void rotate2x2(char[][] input) {
        char[][] temp = new char[2][2];
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                temp[y][x] = input[y][x];
            }
        }
        input[0][0] = temp[1][0];
        input[0][1] = temp[0][0];
        input[1][0] = temp[1][1];
        input[1][1] = temp[0][1];
    }


    private void flip(char[][] input) {
        if (input.length == 2) {
            flip2x2(input);
        } else {
            flip3x3(input);
        }
    }
    
    private void flip3x3(char[][] input) {
        char[][] temp = new char[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                temp[y][x] = input[y][x];
            }
        }
        input[0][0] = temp[2][0];
        input[0][1] = temp[2][1];
        input[0][2] = temp[2][2];
        input[2][0] = temp[0][0];
        input[2][1] = temp[0][1];
        input[2][2] = temp[0][2];
    }

    private void flip2x2(char[][] input) {
        char[][] temp = new char[2][2];
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                temp[y][x] = input[y][x];
            }
        }
        input[0][0] = temp[1][0];
        input[0][1] = temp[1][1];
        input[1][0] = temp[0][0];
        input[1][1] = temp[0][1];
    }
    
    private int pixelsOn(char[][] grid) {
        int pixelsOn = 0;
        for (char[] row : grid) {
            for (char c : row) {
                if (c == '#') pixelsOn++;
            }
        }
        return pixelsOn;
    }
}
