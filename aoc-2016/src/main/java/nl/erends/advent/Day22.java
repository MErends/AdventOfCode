package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day22 extends AbstractProblem<List<String>, Integer> {
    
    private Gridnode[][] grid;

    void main() {
        new Day22().setAndSolve(Util.readInput(2016, 22));
    }
    
    @Override
    public Integer solve1() {
        List<String> gridnodes = new ArrayList<>(input);
        gridnodes.removeFirst();
        gridnodes.removeFirst();
        createGrid(gridnodes);
        int validPairs = 0;
        for (Gridnode[] rowA : grid) {
            for (Gridnode nodaA : rowA) {
                validPairs += findValidPairs(nodaA, grid);
            }
        }
        return validPairs;
    }
    
    @Override
    public Integer solve2() {
        if (grid == null) {
            List<String> gridnodes = new ArrayList<>(input);
            gridnodes.removeFirst();
            gridnodes.removeFirst();
            createGrid(gridnodes);
        }
        int holeX = 0;
        int holeY = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x].used == 0) {
                    holeX = x;
                    holeY = y;
                    break;
                }
            }
        }
        int steps = 0;
        while (holeX != 0) {
            holeX--;
            steps++;
        }
        while (holeY != 0) {
            holeY--;
            steps++;
        }
        while (holeX != grid[0].length - 1) {
            holeX++;
            steps++;
        }
        steps += 5 * (grid[0].length - 2);
        return steps;
    }
    
    private int findValidPairs(Gridnode nodeA, Gridnode[][] grid) {
        int validPairs = 0;
        for (Gridnode[] rowB : grid) {
            for (Gridnode nodeB : rowB) {
                if (nodeA != nodeB && nodeA.used != 0 && nodeA.used <= nodeB.available) {
                    validPairs++;
                }
            }
        }
        return validPairs;
    }
    
    private void createGrid(List<String> gridnodes) {
        Gridnode maxGridnode = new Gridnode(gridnodes.getLast());
        grid = new Gridnode[maxGridnode.y + 1][maxGridnode.x + 1];
        for (String input : gridnodes) {
            Gridnode gridnode = new Gridnode(input);
            grid[gridnode.y][gridnode.x] = gridnode;
        }
    }


    private static class Gridnode {

        private final int x;
        private final int y;
        private final int used;
        private final int available;

        Gridnode(String input) {
            String id = input.substring(0, 22).trim();
            x = Integer.parseInt(id.split("-")[1].substring(1));
            y = Integer.parseInt(id.split("-")[2].substring(1));
            used = Integer.parseInt(input.substring(28, 33).trim());
            available = Integer.parseInt(input.substring(34, 40).trim());
        }
    }
}
