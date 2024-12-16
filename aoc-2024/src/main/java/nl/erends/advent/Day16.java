package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 16: Reindeer Maze ---</h1>
 * <p>It's time again for the Reindeer Olympics! This year, the big event is the
 * Reindeer Maze, where the Reindeer compete for the lowest score. The Reindeer
 * start on the Start Tile (marked S) facing East and need to reach the End Tile
 * (marked E). They can move forward one tile at a time (increasing their score
 * by 1 point), but never into a wall (#). They can also rotate clockwise or
 * counterclockwise 90 degrees at a time (increasing their score by 1000
 * points). What is the lowest score a Reindeer could possibly get?</p>
 * <p><a href="https://adventofcode.com/2024/day/16">2024 Day 16</a></p>
 */
public class Day16 extends AbstractProblem<List<String>, Integer> {

    int[][] bestLeft;
    int[][] bestRight;
    int[][] bestUp;
    int[][] bestDown;
    char[][] grid;
    private Coord end = new Coord(0, 0);

    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readInput(2024, 16));
    }

    @Override
    protected Integer solve1() {
        Coord start = new Coord(0, 0);
        grid = new char[input.size()][input.get(0).length()];
        bestLeft = new int[input.size()][input.get(0).length()];
        bestRight = new int[input.size()][input.get(0).length()];
        bestUp = new int[input.size()][input.get(0).length()];
        bestDown = new int[input.size()][input.get(0).length()];
        for (int y = 0; y< bestLeft.length; y++) {
            Arrays.fill(bestLeft[y], Integer.MAX_VALUE);
            Arrays.fill(bestRight[y], Integer.MAX_VALUE);
            Arrays.fill(bestUp[y], Integer.MAX_VALUE);
            Arrays.fill(bestDown[y], Integer.MAX_VALUE);
        }
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = input.get(y).charAt(x);
                if (grid[y][x] == 'S') {
                    start = new Coord(x, y);
                } else if (grid[y][x] == 'E') {
                    end = new Coord(x, y);
                }
            }
        }

        State beginState = new State(start, Direction.RIGHT, 0, new ArrayList<>());
        List<State> states = new ArrayList<>();
        states.add(beginState);
        List<State> goodPaths = new ArrayList<>();
        int[] targetPoints = new int[]{Integer.MAX_VALUE};
        while (!states.isEmpty()) {
            states.sort(Comparator.comparingInt(s -> s.points));
            State state = states.removeFirst();
            states.removeIf(s -> !isBest(s) || s.points > targetPoints[0]);
            if (state.position.equals(end)) {
                targetPoints[0] = state.points;
                goodPaths.add(state);
            } else {
                states.addAll(state.getNeigbors());
            }
        }
        answer2 = goodPaths.stream()
                .flatMap(s -> s.path.stream())
                .collect(Collectors.toSet()).size();
        return goodPaths.get(0).points;
    }

    private boolean isBest(State s) {
        int[][] best = switch (s.d) {
            case UP -> bestUp;
            case DOWN -> bestDown;
            case LEFT -> bestLeft;
            case RIGHT -> bestRight;
        };
        if (best[s.position.y][s.position.x] < s.points) {
            return false;
        }
        best[s.position.y][s.position.x] = s.points;
        return true;
    }

    class State {

        Coord position;
        Direction d;
        int points;
        List<Coord> path = new ArrayList<>();

        public State(Coord position, Direction d, int points, List<Coord> pastPath) {
            this.position = position;
            this.d = d;
            this.points = points;
            path.addAll(pastPath);
            path.add(position);
        }

        private List<State> getNeigbors() {
            List<State> neighbors = new ArrayList<>();
            // straight
            if (grid[position.y + d.dy()][position.x + d.dx()] != '#') {
                neighbors.add(new State(new Coord(position).addDirection(d), d, points + 1, this.path));
            }
            Direction left = d.turnLeft();
            if (grid[position.y + left.dy()][position.x + left.dx()] != '#') {
                neighbors.add(new State(new Coord(position).addDirection(left), left, points + 1001, this.path));
            }
            Direction right = d.turnRight();
            if (grid[position.y + right.dy()][position.x + right.dx()] != '#') {
                neighbors.add(new State(new Coord(position).addDirection(right), right, points + 1001, this.path));
            }
            if (neighbors.size() == 1 && !neighbors.getFirst().position.equals(end)) {
                return neighbors.getFirst().getNeigbors();
            }
            return neighbors;
        }
    }
}
