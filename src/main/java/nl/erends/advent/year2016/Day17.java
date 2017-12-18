package nl.erends.advent.year2016;

import nl.erends.advent.util.MD5;

public class Day17 {

    private static int shortestLength = Integer.MAX_VALUE;
    private static String shortestPath = "";
    private static int longestLength = Integer.MIN_VALUE;


    public static void main(String[] args) {
        solveMaze("", 1, 1);
        System.out.println(shortestPath);
        System.out.println(longestLength);
    }


    private static void solveMaze(String path, int x, int y) {
        if (x == 4 && y == 4) {
            if (path.length() < shortestLength) {
                shortestLength = path.length();
                shortestPath = path;
            } else if (path.length() > longestLength){
                longestLength = path.length();
            }
            return;
        }
        String pass = "rrrbmfta";
        String hash = MD5.getHash(pass + path);
        if (y != 4 && hash.charAt(1) > 'a') {
            solveMaze(path + 'D', x, y + 1);
        }
        if (x != 4 && hash.charAt(3) > 'a') {
            solveMaze(path + 'R', x + 1, y);
        }
        if (x != 1 && hash.charAt(2) > 'a') {
            solveMaze(path + 'L', x - 1, y);
        }
        if (y != 1 && hash.charAt(0) > 'a') {
            solveMaze(path + 'U', x, y - 1);
        }
    }
}
