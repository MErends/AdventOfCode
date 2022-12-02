package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 extends AbstractProblem<List<String>, Integer> {
    
    private Set<String> blackTiles = new HashSet<>();

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2020, 24));
    }

    @Override
    public Integer solve1() {
        for (String line : input) {
            int x = 0;
            int y = 0;
            while (!line.isEmpty()) {
                String dir = line.substring(0, 1);
                line = line.substring(1);
                if ("s".equals(dir) || "n".equals(dir)) {
                    dir += line.substring(0, 1);
                    line = line.substring(1);
                }
                switch (dir) {
                    case "nw" -> {
                        y++;
                        x--;
                    }
                    case "se" -> {
                        x++;
                        y--;
                    }
                    case "ne" -> y++;
                    case "sw" -> y--;
                    case "w" -> x--;
                    default -> x++;
                }
            }
            String tile = tile(x, y);
            if (!blackTiles.remove(tile)) {
                blackTiles.add(tile);
            }
        }
        return blackTiles.size();
    }

    @Override
    public Integer solve2() {
        if (blackTiles.isEmpty()) {
                solve1();
        }
        for (int cycle = 1; cycle <= 100; cycle++) {
            Set<String> newList = new HashSet<>();
            for (int x = -100; x <=  100; x++) {
                for (int y = -100; y <= 100; y++) {
                    String tile = tile(x, y);
                    boolean isBlack = blackTiles.contains(tile);
                    int blackNeighbors = countNeighbors(x, y);
                    if ((isBlack && (blackNeighbors == 1 || blackNeighbors == 2)) ||
                            (!isBlack && blackNeighbors == 2)) {
                        newList.add(tile);
                    }
                }
            }
            blackTiles = newList;
        }
        return blackTiles.size();
    }
    
    private int countNeighbors(int x, int y) {
        int neighbors = 0;
        neighbors += blackTiles.contains(tile(x + 1, y)) ? 1 : 0;     
        neighbors += blackTiles.contains(tile(x + 1, y - 1)) ? 1 : 0; 
        neighbors += blackTiles.contains(tile(x - 1, y)) ? 1 : 0;     
        neighbors += blackTiles.contains(tile(x - 1, y + 1)) ? 1 : 0; 
        neighbors += blackTiles.contains(tile(x, y + 1)) ? 1 : 0;     
        neighbors += blackTiles.contains(tile(x, y - 1)) ? 1 : 0;     
        return neighbors;
    }
    
    private String tile(int x, int y) {
        return "" + x + ',' + y;
        
    }
}
