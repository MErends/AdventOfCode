package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day17 extends AbstractProblem<List<String>, Integer> {

    private static final int CYCLES = 6;
    private int[][][][] grid;
    private static final int[] DELTA = new int[]{-1, 0, 1};

    void main() {
        new Day17().setAndSolve(Util.readInput(2020, 17));
    }

    @Override
    public Integer solve1() {
        int dimension = input.size() + CYCLES + CYCLES;
        grid = new int[part2 ? dimension : 1][dimension][dimension][dimension];
        int offsetY = (dimension - input.size()) / 2;
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            int offsetX = (dimension - input.get(y).length()) / 2;
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    grid[part2 ? dimension / 2 : 0][dimension / 2][offsetY + y][offsetX + x] = 1;
                }
            }
        }
        for (int cycle = 0; cycle < CYCLES; cycle++) {
            iterate();
        }
        return getCount();
    }

    private int getCount() {
        int count = 0;
        for (int[][][] cube : grid) {
            for (int[][] vlak : cube) {
                for (int[] regel : vlak) {
                    for (int lamp : regel) {
                        count += lamp;
                    }
                }
            }
        }
        return count;
    }

    private void iterate() {
        int[][][][] newGrid = new int[part2 ? grid.length : 1][grid[0].length][grid[0].length][grid[0].length];
        for (int w = 0; w < grid.length; w++) {
            for (int z = 0; z < grid[w].length; z++) {
                for (int y = 0; y < grid[w][z].length; y++) {
                    for (int x = 0; x < grid[w][z][y].length; x++) {
                        newGrid[w][z][y][x] = newState(x, y, z, w);
                    }
                }
            }
        }
        grid = newGrid;
    }

    private int newState(int x, int y, int z, int w) {
        int neighbors = neighborCount(x, y, z, w);
        if (grid[w][z][y][x] == 1 && (neighbors < 2 || neighbors > 3)) {
            return  0;
        } else if (grid[w][z][y][x] == 0 && neighbors == 3) {
            return  1;
        } else {
            return grid[w][z][y][x];
        }
    }

    private int neighborCount(int x, int y, int z, int w) {
        int count = -grid[w][z][y][x];
        for (int dw : DELTA) {
            for (int dz : DELTA) {
                for (int dy : DELTA) {
                    for (int dx : DELTA) {
                        int nx = x + dx;
                        int ny = y + dy;
                        int nz = z + dz;
                        int nw = w + dw;
                        count += getSafeFromGrid(nx, ny, nz, nw);
                    }
                }
            }
        }
        return count;
    }

    private int getSafeFromGrid(int nx, int ny, int nz, int nw) {
        if (nw >= 0 && nw < grid.length
                && nz >= 0 && nz < grid[nw].length
                && ny > 0 && ny < grid[nw][nz].length
                && nx > 0 && nx < grid[nw][nz][ny].length) {
            return grid[nw][nz][ny][nx];
        }
        return 0;
    }
}
