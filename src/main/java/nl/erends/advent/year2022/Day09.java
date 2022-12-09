package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 9
 * <p>Consider a rope with a knot at each end; these knots mark the head and the
 * tail of the rope. If the head moves far enough away from the tail, the tail
 * is pulled toward the head. Simulate to complete hypothetical series of
 * motions of the head. How many positions does the tail of the rope visit at
 * least once?
 * <p><a href="https://adventofcode.com/2022/day/9">2022 Day 9</a>
 */
public class Day09 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readInput(2022, 9));
    }

    @Override
    protected Integer solve1() {
        return solveNodeLength(2);
    }

    @Override
    public Integer solve2() {
        return solveNodeLength(10);
    }

    private int solveNodeLength(int length) {
        Set<String> seenStates = new HashSet<>();
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            nodes.add(new Node());
        }
        Node head = nodes.get(0);
        Node tail = nodes.get(length - 1);
        for (String line : input) {
            Direction d = getDirection(line.charAt(0));
            int steps = Integer.parseInt(line.substring(2));
            for (int step = 0; step < steps; step++) {
                head.x += d.dx();
                head.y += d.dy();
                for (int i = 0; i < length - 1; i++) {
                    updateNode(nodes.get(i), nodes.get(i + 1));
                }
                seenStates.add(tail.x + "," + tail.y);
            }
        }
        return seenStates.size();
    }

    private void updateNode(Node head, Node tail) {
        int xSep = head.x - tail.x;
        int ySep = head.y - tail.y;
        if (Math.abs(xSep) == 2) {
            tail.x += xSep / Math.abs(xSep);
            if (ySep != 0) {
                tail.y += ySep / Math.abs(ySep);
            }
        } else if (Math.abs(ySep) == 2) {
            tail.y += ySep / Math.abs(ySep);
            if (xSep != 0) {
                tail.x += xSep / Math.abs(xSep);
            }
        }
    }

    private Direction getDirection(char c) {
        return switch (c) {
            case 'U' -> Direction.UP;
            case 'D' -> Direction.DOWN;
            case 'R' -> Direction.RIGHT;
            default -> Direction.LEFT;
        };
    }

    private static class Node {
        private int x;
        private int y;
    }
}
