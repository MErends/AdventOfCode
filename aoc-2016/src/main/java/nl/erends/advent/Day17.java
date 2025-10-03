package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.MD5;

public class Day17 extends AbstractProblem<String, String> {

    private int shortestLength = Integer.MAX_VALUE;
    private String shortestPath = "";
    private int longestLength = Integer.MIN_VALUE;


    void main() {
        new Day17().setAndSolve("rrrbmfta");
    }
    @Override
    public String solve1() {
        solveMaze("", 1, 1);
        answer2 = Integer.toString(longestLength);
        return shortestPath;
    }


    private void solveMaze(String path, int x, int y) {
        if (x == 4 && y == 4) {
            if (path.length() < shortestLength) {
                shortestLength = path.length();
                shortestPath = path;
            } else if (path.length() > longestLength){
                longestLength = path.length();
            }
            return;
        }
        String hash = MD5.getHash(input + path);
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
