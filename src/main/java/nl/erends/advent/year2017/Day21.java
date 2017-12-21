package nl.erends.advent.year2017;


//     1 2 3   7 4 1
//     4 5 6   8 5 2
//     7 8 9   9 6 3

import nl.erends.advent.util.FileIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {
    
    private static Map<String, String> mapping = new HashMap<>();
    
    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2017day21.txt");
        for (String line : input) {
            String[] mappingArray = line.split("=>");
            mapping.put(mappingArray[0].trim(), mappingArray[1].trim());
        }
        char[][] grid = {{'.','#','.'},{'.', '.','#'},{'#','#','#'}};
        for (int iterations = 1 ; iterations <= 18; iterations++) {
            grid = enhanceGrid(grid);
            if (iterations == 5) {
                System.out.println(pixelsOn(grid));
            }
            
        }
        System.out.println(pixelsOn(grid));
    }
    
    private static char[][] enhanceGrid(char[][] grid) {
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
    
    
    
    private static char[][] getSubgrid(char[][] grid, int x, int y) {
        int scale = grid.length % 2 == 0 ? 2 : 3;
        char[][] subgrid = new char[scale][scale];
        for (int subgridY = 0; subgridY < scale; subgridY++) {
            for (int subgridX = 0; subgridX < scale; subgridX++) {
                subgrid[subgridY][subgridX] = grid[y * scale + subgridY][x * scale + subgridX];
            }
        }
        return subgrid;
    }
    
    private static void setSubgrid(char[][] grid, char[][] subgrid, int x, int y) {
        int scale = subgrid.length;
        for (int subgridY = 0; subgridY < scale; subgridY++) {
            for (int subgridX = 0; subgridX < scale; subgridX++) {
                grid[y * scale + subgridY][x * scale + subgridX] = subgrid[subgridY][subgridX];
            }
        }
    }
    
    private static char[][] getEnhancedSubgrid(char[][] input) {
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

    private static String getStringRep(char[][] input) {
        StringBuilder inputString = new StringBuilder();
        for (char[] row : input) {
            inputString.append(row).append("/");
        }
        inputString.deleteCharAt(inputString.length() - 1);
        return inputString.toString();
    }

    private static void rotate(char[][] input) {
        if (input.length == 2) {
            rotate2x2(input);
        } else {
            rotate3x3(input);
        }
    }
    
    private static void rotate3x3(char[][] input) {
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
    
    private static void rotate2x2(char[][] input) {
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


    private static void flip(char[][] input) {
        if (input.length == 2) {
            flip2x2(input);
        } else {
            flip3x3(input);
        }
    }
    
    private static void flip3x3(char[][] input) {
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

    private static void flip2x2(char[][] input) {
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
    
    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static int pixelsOn(char[][] grid) {
        int pixelsOn = 0;
        for (char[] row : grid) {
            for (char c : row) {
                if (c == '#') pixelsOn++;
            }
        }
        return pixelsOn;
    }
}