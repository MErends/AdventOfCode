package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11 extends AbstractProblem<List<String>, Integer> {

    private char[][] area;
    private static final char SEAT = 'L';
    private static final char OCCUPIED = '#';
    private static final char FLOOR = '.';


    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readInput(2020, 11));
    }

    @Override
    public Integer solve1() {
        area = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            area[i] = input.get(i).toCharArray();
        }
        Set<Long> seenStates = new HashSet<>();
        long hash = getHash();
        while (!seenStates.contains(hash)) {
            seenStates.add(hash);
            if (part2) {
                iterate2();
            } else {
                iterate();
            }
            hash = getHash();
        }
        int occupiedSeats = 0;
        for (char[] row : area) {
            for (char seat : row) {
                if (seat == OCCUPIED) {
                    occupiedSeats++;
                }
            }
        }
        return occupiedSeats;
    }

    private long getHash() {
        long hash = 17L;
        for (int y = 0; y < area.length; y++) {
            for (int x = 0; x < area[y].length; x++) {
                hash += (long) (x + 19) * (y + 23) * area[y][x];
            }
        }
        return hash;
    }

    private void iterate() {
        char[][] newArea = new char[area.length][area[0].length];
        for (int y = 0; y < area.length; y++) {
            for (int x = 0; x < area[y].length; x++) {
                if (area[y][x] == FLOOR) {
                    newArea[y][x] = FLOOR;
                } else {
                    int neighbors = countNeighbors(x, y);
                    if (area[y][x] == SEAT && neighbors == 0) {
                        newArea[y][x] = OCCUPIED;
                    } else if (area[y][x] == OCCUPIED && neighbors >= 4) {
                        newArea[y][x] = SEAT;
                    } else {
                        newArea[y][x] = area[y][x];
                    }
                }
            }
        }
        area = newArea;
    }

    private int countNeighbors(int x, int y) {
        int neighbors = 0;
        for (int dy = Integer.max(0, y - 1); dy < Integer.min(y + 2, area.length); dy++) {
            for (int dx = Integer.max(0, x - 1); dx < Integer.min(x + 2, area[dy].length); dx++) {
                if (area[dy][dx] == OCCUPIED) {
                    neighbors++;
                }
            }
        }
        if (area[y][x] == OCCUPIED) {
            neighbors--;
        }
        return neighbors;
    }
    private void iterate2() {
        char[][] newArea = new char[area.length][area[0].length];
        for (int y = 0; y < area.length; y++) {
            for (int x = 0; x < area[y].length; x++) {
                if (area[y][x] == FLOOR) {
                    newArea[y][x] = FLOOR;
                } else {
                    int neighbors = countNeighbors2(x, y);
                    if (area[y][x] == SEAT && neighbors == 0) {
                        newArea[y][x] = OCCUPIED;
                    } else if (area[y][x] == OCCUPIED && neighbors >= 5) {
                        newArea[y][x] = SEAT;
                    } else {
                        newArea[y][x] = area[y][x];
                    }
                }
            }
        }
        area = newArea;
    }

    private int countNeighbors2(int x, int y) {
        int[] neighbors = new int[]{0};
        lookN(x, y, neighbors);
        lookNE(x, y, neighbors);
        lookE(x, y, neighbors);
        lookSE(x, y, neighbors);
        lookS(x, y, neighbors);
        lookSW(x, y, neighbors);
        lookW(x, y, neighbors);
        lookNW(x, y, neighbors);
        return neighbors[0];
    }

    private void lookN(int x, int y, int[] n) {
        for (int dy = y - 1; dy >= 0; dy--) {
            if (area[dy][x] != FLOOR) {
                n[0] += isOccupied(x, dy);
                return;
            }
        }
    }

    private void lookNE(int x, int y, int[] n) {
        for (int dy = y - 1, dx = x + 1; dy >= 0 && dx < area[dy].length; dy--, dx++) {
            if (area[dy][dx] != FLOOR) {
                n[0] += isOccupied(dx, dy);
                return;
            }
        }
    }

    private void lookE(int x, int y, int[] n) {
        for (int dx = x + 1; dx < area[y].length; dx++) {
            if (area[y][dx] != FLOOR) {
                n[0] += isOccupied(dx, y);
                return;
            }
        }
    }

    private void lookSE(int x, int y, int[] n) {
        for (int dy = y + 1, dx = x + 1; dy < area.length && dx < area[dy].length; dy++, dx++) {
            if (area[dy][dx] != FLOOR) {
                n[0] += isOccupied(dx, dy);
                return;
            }
        }
    }

    private void lookS(int x, int y, int[] n) {
        for (int dy = y + 1; dy < area.length; dy++) {
            if (area[dy][x] != FLOOR) {
                n[0] += isOccupied(x, dy);
                return;
            }
        }
    }

    private void lookSW(int x, int y, int[] n) {
        for (int dy = y + 1, dx = x - 1; dy < area.length && dx >= 0; dy++, dx--) {
            if (area[dy][dx] != FLOOR) {
                n[0] += isOccupied(dx, dy);
                return;
            }
        }
    }

    private void lookW(int x, int y, int[] n) {
        for (int dx = x - 1; dx >= 0; dx--) {
            if (area[y][dx] != FLOOR) {
                n[0] += isOccupied(dx, y);
                return;
            }
        }
    }

    private void lookNW(int x, int y, int[] n) {
        for (int dy = y - 1, dx = x - 1; dy >= 0 && dx >= 0; dy--, dx--) {
            if (area[dy][dx] != FLOOR) {
                n[0] += isOccupied(dx, dy);
                return;
            }
        }
    }
    
    private int isOccupied(int x, int y) {
        return area[y][x] == OCCUPIED ? 1 : 0;
    }
}
