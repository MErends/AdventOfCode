package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Point;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 23: Unstable Diffusion ---
 * <p>You enter a large crater of gray dirt where the grove is supposed to be.
 * The Elves each reach into their pack and pull out a tiny plant. The plants
 * rely on important nutrients from the ash, so they can't be planted too close
 * together. Simulate the Elves' process and find the smallest rectangle that
 * contains the Elves after 10 rounds. What is the number of the first round
 * where no Elf moves?
 * <p><a href="https://adventofcode.com/2022/day/23">2022 Day 23</a>
 */
public class Day23 extends AbstractProblem<List<String>, Number> {

    private final List<Elf> elfs = new ArrayList<>();
    private final List<Direction> directions = new ArrayList<>();

    static void main() {
        new Day23().setAndSolve(Util.readInput(2022, 23));
    }

    @Override
    public Number solve1() {
        int round10 = 0;
        directions.addAll(Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        readElfs();
        int round = 1;
        while (true) {
            List<Elf> moveElfs = elfs.stream().filter(Elf::shouldMove).toList();
            if (moveElfs.isEmpty()) {
                answer2 = round;
                return round10;
            }
            List<Point> proposals = moveElfs.stream().map(Elf::setProposal).toList();
            for (Elf mover : moveElfs) {
                if (mover.proposal != null && proposals.stream().filter(p -> mover.proposal.equals(p)).count() == 1) {
                    mover.position = mover.proposal;
                }
            }
            if (round == 10) {
                int maxX = elfs.stream().mapToInt(e -> e.position.x()).max().orElseThrow();
                int maxY = elfs.stream().mapToInt(e -> e.position.y()).max().orElseThrow();
                int minX = elfs.stream().mapToInt(e -> e.position.x()).min().orElseThrow();
                int minY = elfs.stream().mapToInt(e -> e.position.y()).min().orElseThrow();
                round10 = (maxX - minX + 1) * (maxY - minY + 1) - elfs.size();
            }
            directions.add(directions.removeFirst());
            round++;
        }
    }

    private void readElfs() {
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    elfs.add(new Elf(x, y));
                }
            }
        }
    }

    private class Elf {

        Point position;
        Point proposal;
        boolean[][] occupied;

        Elf(int x, int y) {
            position = new Point(x, y);
        }

        boolean shouldMove() {
            boolean shouldMove = false;
            occupied = new boolean[3][3];
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0) continue;
                    Point neighbor = new Point(position.x() + dx, position.y() + dy);
                    if (elfs.stream().anyMatch(e -> neighbor.equals(e.position))) {
                        shouldMove = true;
                        occupied[dy + 1][dx + 1] = true;
                    }
                }
            }
            return shouldMove;
        }

        Point setProposal() {
            proposal = null;
            for (Direction d : directions) {
                switch (d) {
                    case UP -> {
                        if (!occupied[0][0] && !occupied[0][1] && !occupied[0][2]) {
                            proposal = new Point(position.x() + d.dx(), position.y() + d.dy());
                        }
                    }
                    case DOWN -> {
                        if (!occupied[2][0] && !occupied[2][1] && !occupied[2][2]) {
                            proposal = new Point(position.x() + d.dx(), position.y() + d.dy());
                        }
                    }
                    case LEFT -> {
                        if (!occupied[0][0] && !occupied[1][0] && !occupied[2][0]) {
                            proposal = new Point(position.x() + d.dx(), position.y() + d.dy());
                        }
                    }
                    case RIGHT -> {
                        if (!occupied[0][2] && !occupied[1][2] && !occupied[2][2]) {
                            proposal = new Point(position.x() + d.dx(), position.y() + d.dy());
                        }
                    }
                }
                if (proposal != null) {
                    break;
                }
            }
            return proposal;
        }
    }
}
