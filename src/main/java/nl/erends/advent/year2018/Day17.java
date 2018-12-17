package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day17 {

    private static char[][] grid;
    private static int xMin = Integer.MAX_VALUE;
    private static int xMax = Integer.MIN_VALUE;
    private static int yMin = Integer.MAX_VALUE;
    private static int yMax = Integer.MIN_VALUE;

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day17.txt");
        long start = System.currentTimeMillis();
        for (String line : input) {
            if (line.startsWith("x")) {
                int x = Integer.parseInt(line.split("=")[1].split(",")[0]);
                int y1 = Integer.parseInt(line.split("=")[2].split("\\.")[0]);
                int y2 = Integer.parseInt(line.split("=")[2].split("\\.")[2]);
                xMin = Math.min(xMin, x);
                xMax = Math.max(xMax, x);
                yMin = Math.min(yMin, y1);
                yMax = Math.max(yMax, y2);
            } else {
                int x1 = Integer.parseInt(line.split("=")[2].split("\\.")[0]);
                int x2 = Integer.parseInt(line.split("=")[2].split("\\.")[2]);
                int y = Integer.parseInt(line.split("=")[1].split(",")[0]);
                xMin = Math.min(xMin, x1);
                xMax = Math.max(xMax, x2);
                yMax = Math.max(yMax, y);
            }
        }
        xMin--;
        xMax++;
        grid = new char[yMax + 1][xMax + 1];
        for (String line : input) {
            if (line.startsWith("x")) {
                int x = Integer.parseInt(line.split("=")[1].split(",")[0]);
                int y1 = Integer.parseInt(line.split("=")[2].split("\\.")[0]);
                int y2 = Integer.parseInt(line.split("=")[2].split("\\.")[2]);
                for (int y = y1; y <= y2; y++) grid[y][x] = '#';
            } else {
                int x1 = Integer.parseInt(line.split("=")[2].split("\\.")[0]);
                int x2 = Integer.parseInt(line.split("=")[2].split("\\.")[2]);
                int y = Integer.parseInt(line.split("=")[1].split(",")[0]);
                for (int x = x1; x <= x2; x++) grid[y][x] = '#';
            }
        }
        grid[0][500] = '+';
        for (int y = 0; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                if (grid[y][x] == '\0') grid[y][x] = '.';
            }
        }
        spreadFrom(500, 1);
        int water=0;
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                char c = grid[y][x];
                if(c=='~' || c=='|')water++;
            }
        }
//        printGrid();
        System.out.println(water);
        //33008 too high
        long mid = System.currentTimeMillis();
        water=0;
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                char c = grid[y][x];
                if(c=='~')water++;
            }
        }
        System.out.println(water);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    private static void spreadFrom(int x, int y) {
        if (y > yMax) return;
        if (grid[y][x] != '.') return;
        spreadFrom(x, y + 1);
        grid[y][x] = shouldBeSolid(x, y) ? '~' : '|';
        if (y >= yMax) return;
        if (grid[y + 1][x] == '|') return;
        spreadFrom(x - 1, y);
        spreadFrom(x + 1, y);
    }
    
    private static boolean shouldBeSolid(int x, int y) {
        if (y >= yMax) return false;
        char floor = grid[y + 1][x];
        if (floor != '#' && floor != '~') return false;
        for (int xLeft = x - 1; ; xLeft--) {
            char left = grid[y][xLeft];
            if (left == '#') break;
            floor = grid[y + 1][xLeft];
            if (floor != '~' && floor != '#') return false;
        }
        for (int xRight = x + 1; ; xRight++) {
            char right = grid[y][xRight];
            if (right == '#') break;
            floor = grid[y + 1][xRight];
            if (floor != '~' && floor != '#') return false;
        }
        for (int xLeft = x - 1; ; xLeft--) {
            char left = grid[y][xLeft];
            if (left == '#') break;
            if (left == '|') grid[y][xLeft] = '~';
        }
        for (int xRight = x + 1; ; xRight++) {
            char right = grid[y][xRight];
            if (right == '#') break;
            if (right == '|') grid[y][xRight] = '~';
        }
        return true;
    }
    
    
    
}
