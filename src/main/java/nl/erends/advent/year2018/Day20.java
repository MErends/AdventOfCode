package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day20 {


    public static void main(String... args) {
        String input = Util.getFileAsList("2018day20.txt").get(0);
        long start = System.currentTimeMillis();
        input = input.substring(0, input.length() - 1).substring(1);
        Maze maze = new Maze();
        maze.drawFromLocation(500, 500, input);
        maze.trimAndFill();
        maze.clearDoors();
        maze.printMaze();
        solvePuzzle(maze);
        long end = System.currentTimeMillis();
        System.out.println("Part 1 & 2: " + (end - start) + " millis.");
    }
    
    private static class Maze {
        char[][] grid;
        
        Maze() {
            grid = new char[1000][1000];
            for (char[] row :  grid) {
                Arrays.fill(row, ' ');
            }
            grid[500][500] = 'X';
        }
        
        private void drawFromLocation(int x, int y, String path) {
            while (path.length() != 0) {
                char c = path.charAt(0);
                switch (c) {
                    case 'N':
                        y--;
                        grid[y][x] = '-';
                        y--;
                        break;
                    case 'E':
                        x++;
                        grid[y][x] = '|';
                        x++;
                        break;
                    case 'S':
                        y++;
                        grid[y][x] = '-';
                        y++;
                        break;
                    case 'W':
                        x--;
                        grid[y][x] = '|';
                        x--;
                        break;
                    case '(':
                        List<String> subPaths = splitOnPipe(path);
                        for (String subPath : subPaths) {
                            drawFromLocation(x, y, subPath);
                        }
                        return;
                    default:
                        throw new IllegalStateException("" + c);
                }
                if (grid[y][x] != 'X') grid[y][x] = '.';
                // printMaze();
                path = path.substring(1);
            }
        }
        
        private void trimAndFill() {
            int xMax = Integer.MIN_VALUE;
            int xMin = Integer.MAX_VALUE;
            int yMax = Integer.MIN_VALUE;
            int yMin = Integer.MAX_VALUE;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] != ' ') {
                        xMax = Math.max(x, xMax);
                        xMin = Math.min(x, xMin);
                        yMax = Math.max(y, yMax);
                        yMin = Math.min(y, yMin);
                    }
                }
            }
            xMax += 2;
            xMin--;
            yMax += 2;
            yMin--;
            char[][] newGrid = new char[yMax - yMin][xMax - xMin];
            for (int y = 0; y < newGrid.length; y++) {
                newGrid[y] = Arrays.copyOfRange(grid[yMin + y], xMin, xMax);
            }
            grid = newGrid;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] == ' ') {
                        grid[y][x] = '#';
                    }
                }
            }
        }
        
        private void clearDoors() {
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (grid[y][x] != '#' && grid[y][x] != 'X') {
                        grid[y][x] = ' ';
                    }
                }
            }
        }
        
        private void printMaze() {
            for (char[] row : grid) {
                boolean hasX = false;
                for (char c : row) {
                    System.out.print(c == '#' || c == 'X' ? c : ' ');
                    if (c == 'X') hasX = true;
                }
                System.out.println(hasX ? "<" : "");
            }
        }
    }
    
    private static List<String> splitOnPipe(String input) {
        boolean hasLoop = false;
        List<String> output = new ArrayList<>();
        List<Integer> splitOnIndex = new ArrayList<>();
        int previousSplit = 1;
        int endsplit = -1;
        int depth = 0;
        for (int index = 0; index < input.length(); index++) {
            // System.out.print(index + ": ");
            char c = input.charAt(index);
            if (c == '(') {
                depth++;
                // System.out.println("Found " + c + ". Going to depth " + depth);
            } else if (c == '|') {
                // System.out.print("Found " + c + "at depth " + depth);
                if (depth == 1) {
                    // System.out.print(". Adding index " + index + " to list");
                    splitOnIndex.add(index);
                }
                // System.out.println();
            } else if (c == ')') {
                depth--;
                // System.out.println("Found " + c + ". Going to depth " + depth);
                if (depth == 0) {
                    endsplit = index;
                    break;
                }
            } 
            // else {
            //     System.out.println("Found " + c);
            // }
        }
        List<String> subPaths = new ArrayList<>();
        String endPath = input.substring(endsplit + 1);
        for (Integer aSplitOnIndex : splitOnIndex) {
            subPaths.add(input.substring(previousSplit, aSplitOnIndex));
            previousSplit = aSplitOnIndex + 1;
        }
        subPaths.add(input.substring(previousSplit, endsplit));
        for (String subPath : subPaths) {
            if (isLoop(subPath)) {
                hasLoop = true;
            }
        }
        for (String subPath : subPaths) {
            if (isLoop(subPath)) {
                output.add(subPath);
            } else {
                output.add(subPath + endPath);
            }
        }
        if (hasLoop) {
            output.add(endPath);
        }
        return output;
    }
    
    private static boolean isLoop(String subPath) {
        int east = 0;
        int west = 0;
        int north = 0;
        int south = 0;
        for (char c : subPath.toCharArray()) {
            switch (c) {
                case 'E': 
                    east++;
                    break;
                case 'W':
                    west++;
                    break;
                case 'N':
                    north++;
                    break;
                case 'S':
                    south++;
                    break;
                case '(':
                    return false;
                default:
                    throw new IllegalStateException();
            }
        }
        return east == west && north == south;
    }
    
    private static void solvePuzzle(Maze maze) {
        char[][] charGrid = maze.grid;
        int[][] intGrid = new int[charGrid.length][charGrid[0].length];
        for (int[] row : intGrid) {
            Arrays.fill(row, -1);
        }
        findX:
        for (int y = 0; y < charGrid.length; y++) {
            for (int x = 0; x < charGrid[y].length; x++) {
                if (charGrid[y][x] == 'X') {
                    intGrid[y][x] = 0;
                    break findX;
                }
            }
        }
        for (int lookFor = 0; ; lookFor++) {
            boolean updated = false;
            for (int y = 0; y < intGrid.length; y++) {
                for (int x = 0; x < intGrid[y].length; x++) {
                    if (intGrid[y][x] == lookFor) {
                        if (charGrid[y + 1][x] != '#' && intGrid[y + 2][x] == -1) {
                            
                            intGrid[y + 2][x] = lookFor + 1;
                            updated = true;
                        }
                        if (charGrid[y - 1][x] != '#' && intGrid[y - 2][x] == -1) {
                            
                            intGrid[y - 2][x] = lookFor + 1;
                            updated = true;
                        }
                        if (charGrid[y][x + 1] != '#' && intGrid[y][x + 2] == -1) {
                            
                            intGrid[y][x + 2] = lookFor + 1;
                            updated = true;
                        }
                        if (charGrid[y][x - 1] != '#' && intGrid[y][x - 2] == -1) {
                            intGrid[y][x - 2] = lookFor + 1;
                            updated = true;
                        }
                    }
                }
            }
            // maze.printMaze();
            // System.out.println();
            // for (int[] row : intGrid) {
            //     for (int i : row) {
            //         if (i == -1) {
            //             System.out.printf("%3s", "#");
            //         } else {
            //             System.out.printf("%3d", i);
            //         }
            //     }
            //     System.out.println("");
            // }
            // System.out.println();
            
            if (!updated) {
                System.out.println(lookFor);
                break;
            }
        }
        int over1K = 0;
        for (int y = 0; y < charGrid.length; y++) {
            for (int x = 0; x < charGrid[y].length; x++) {
                if (intGrid[y][x] >= 1000) over1K++;
            }
        }
        System.out.println(over1K);
        // 8363 too low
    }
}
