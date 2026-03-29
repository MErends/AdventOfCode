package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>--- Day 8: Playground ---</h1>
 * <p>Across the playground, a group of Elves are working on setting up an
 * ambitious Christmas decoration project. Through careful rigging, they have
 * suspended a large number of small electrical junction boxes. The Elves are
 * trying to figure out which junction boxes to connect so that electricity can
 * reach every junction box. By connecting these two junction boxes together,
 * because electricity can flow between them, they become part of the same
 * circuit. What do you get if you multiply together the sizes of the three
 * largest circuits?</p>
 * <p><a href="https://adventofcode.com/2025/day/8">2025 Day 8</a></p>
 */
public class Day09 extends AbstractProblem<List<String>, Long> {
    
    static int minStep = Integer.MAX_VALUE;

    void main() {
        new Day09().setAndSolve(Util.readInput(2025, 9, 1));
    }

    @Override
    protected Long solve1() {
        List<Coord> tiles = new ArrayList<>();
        for (String line : input) {
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            tiles.add(Coord.of(x, y));
        }
        long maxSurface = Long.MIN_VALUE;
        for (Coord a : tiles) {
            for (Coord b : tiles) {
                int dx = Math.abs(a.x() - b.x()) + 1;
                int dy = Math.abs(a.y() - b.y()) + 1;
                long surface = (long) dx * dy;
                maxSurface = Math.max(maxSurface, surface);
            }
        }
        return maxSurface;
    }

    @Override
    public Long solve2() {
        List<Coord> tiles = new ArrayList<>();
        for (String line : input) {
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            tiles.add(Coord.of(x, y));
        }
        long maxSurface = Long.MIN_VALUE;
        for (Coord a : tiles) {
            for (Coord b : tiles) {
                int xLow = Math.min(a.x(), b.x());
                int xHigh = Math.max(a.x(), b.x());
                int yLow = Math.min(a.y(), b.y());
                int yHigh = Math.max(a.y(), b.y());
                boolean goodSquare = true;
                for (Coord c : tiles) {
                    if (xLow < c.x() && c.x() < xHigh 
                            && yLow < c.y() && c.y() < yHigh) {
                        goodSquare = false;
                        break;
                    }
                }
                if (goodSquare) {
                    int dx = Math.abs(a.x() - b.x()) + 1;
                    int dy = Math.abs(a.y() - b.y()) + 1;
                    long surface = (long) dx * dy;
                    maxSurface = Math.max(maxSurface, surface);
                }
            }
        }
        return maxSurface;
    }

    //    @Override
//    public Long solve2() {
//        List<Coord> corners = new ArrayList<>();
//        Map<Coord, Character>
//        for (String line : input) {
//            String[] coords = line.split(",");
//            int x = Integer.parseInt(coords[0]);
//            int y = Integer.parseInt(coords[1]);
//            corners.add(Coord.of(x, y));
//        }
//        Direction d = getDirection(corners.getLast(), corners.getFirst());
//        Direction dOrg = d;
//        int rightTurns = 0;
//        int leftTurns = 0;
//        for (int cornerId = 0; cornerId < corners.size(); cornerId++) {
//            Direction d2 = getDirection(corners.get(cornerId), corners.get((cornerId + 1) % corners.size()));
//            if (d.turnLeft() == d2) {
//                leftTurns++;
//            } else {
//                rightTurns++;
//            }
//            d = d2;
//        }
//        return super.solve2();
//    }
//
//    private Direction getDirection(Coord a, Coord b) {
//        if (a.x() < b.x()) {
//            return Direction.RIGHT;
//        }
//        if (a.x() > b.x()) {
//            return Direction.LEFT;
//        }
//        if (a.y() < b.y()) {
//            return Direction.DOWN;
//        }
//        return Direction.UP;
//    }
//    
//    private char getTurn(Direction d, boolean turnLeft) {
//        switch (d) {
//            case UP -> {
//                if (turnLeft) {
//                    return '7';
//                } else {
//                    return 'F';
//                }
//            }
//            case DOWN -> {
//                if (turnLeft) {
//                    return 'L';
//                } else {
//                    return 'J';
//                }
//            }
//            case LEFT -> {
//                if (turnLeft) {
//                    return 'F';
//                } else {
//                    return 'L';
//                }
//            }
//            case RIGHT -> {
//                if (turnLeft) {
//                    return 'J';
//                } else {
//                    return '7';
//                }
//            }
//        }
//    }
//    
//    public record Line(int x1, int y1, int x2, int y2){}
}
