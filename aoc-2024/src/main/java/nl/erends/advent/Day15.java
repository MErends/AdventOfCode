package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 15: Warehouse Woes ---</h1>
 * <p>You appear back inside your own mini submarine! These lanternfish seem so
 * anxious because they have lost control of the robot that operates one of
 * their most important warehouses! As the robot (@) attempts to move, if there
 * are any boxes (O) in the way, the robot will also attempt to push those
 * boxes. Predict the motion of the robot and boxes in the warehouse. After the
 * robot is finished moving, what is the sum of all boxes' GPS coordinates?</p>
 * <p><a href="https://adventofcode.com/2024/day/15">2024 Day 15</a></p>
 */
public class Day15 extends AbstractProblem<List<String>, Integer> {

    private char[][] grid;
    private Coord robot;
    private List<Box> boxes;

    static void main() {
        new Day15().setAndSolve(Util.readInput(2024, 15));
    }

    @Override
    protected Integer solve1() {
        int width = part2 ? 2 : 1;
        int emptyIndex = input.indexOf("");
        grid = new char[emptyIndex][];
        boxes = new ArrayList<>();
        for (int y = 0; y < emptyIndex; y++) {
            grid[y] = new char[input.get(y).length() * width];
            readLine(y, width);
        }
        String movements = input.stream()
                .skip(emptyIndex + 1L)
                .collect(Collectors.joining());

        for (char move : movements.toCharArray()) {
            Direction d = Direction.getDirection(move);
            Optional<Box> pushbox = boxes.stream().filter(b -> b.hasPosition(robot.addDirection(d))).findAny();
            if (pushbox.isPresent()) {
                Box box = pushbox.get();
                if (box.canMove(d)) {
                    box.doMove(d);
                    robot = robot.addDirection(d);
                }
            } else if (grid[robot.y() + d.dy()][robot.x() + d.dx()] != '#') {
                robot = robot.addDirection(d);
            }
        }
        return boxes.stream()
                .mapToInt(b -> b.position.getFirst().y() * 100 + b.position.getFirst().x()).sum();
    }

    private void readLine(int y, int width) {
        for (int x = 0; x < input.get(y).length(); x++) {
            switch (input.get(y).charAt(x)) {
                case '.' -> {
                    grid[y][width * x] = '.';
                    if (part2) {
                        grid[y][width * x + 1] = '.';
                    }
                }
                case 'O' -> {
                    boxes.add(new Box(width * x, y, part2));
                    grid[y][width * x] = '.';
                    if (part2) {
                        grid[y][width * x + 1] = '.';
                    }
                }
                case '@' -> {
                    grid[y][width * x] = '.';
                    if (part2) {
                        grid[y][width * x + 1] = '.';
                    }
                    robot = Coord.of(width * x, y);
                }
                default -> {
                    grid[y][width * x] = '#';
                    if (part2) {
                        grid[y][width * x + 1] = '#';
                    }
                }
            }
        }
    }

    class Box {

        final List<Coord> position = new ArrayList<>();

        Box(int x, int y, boolean wide) {
            position.add(Coord.of(x, y));
            if (wide) {
                position.add(Coord.of(x + 1, y));
            }
        }

        boolean hasPosition(Coord other) {
            return position.contains(other);
        }

        boolean canMove(Direction d) {
            for (Coord p : position) {
                if (grid[p.y() + d.dy()][p.x() + d.dx()] == '#') {
                    return false;
                }
            }
            Set<Box> neighbors = getNeighbors(d);
            for (Box neighbor : neighbors) {
                if (!neighbor.canMove(d)) {
                    return false;
                }
            }
            return true;
        }

        void doMove(Direction d) {
            Set<Box> neighbors = getNeighbors(d);
            position.replaceAll(coord -> coord.addDirection(d));
            for (Box neighbor : neighbors) {
                neighbor.doMove(d);
            }
        }

        Set<Box> getNeighbors(Direction d) {
            Set<Box> neighbors = new HashSet<>();
            Coord leftTarget = position.getFirst().addDirection(d);
            Coord rightTarget = position.getLast().addDirection(d);
            for (Box box : boxes) {
                if ((box.hasPosition(leftTarget) || box.hasPosition(rightTarget)) && box != this) {
                    neighbors.add(box);
                }
            }
            return neighbors;
        }
    }
}
