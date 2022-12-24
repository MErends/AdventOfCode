package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Point;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * --- Day 24: Blizzard Basin ---
 * <p>With everything replanted for next year, you and the Elves leave for the
 * extraction point. As the expedition reaches a valley that must be traversed
 * to reach the extraction site, you find that strong, turbulent winds are
 * pushing small blizzards of snow and sharp ice around the valley. To make it
 * across safely, you'll need to find a way to avoid them. What is the fewest
 * number of minutes required to avoid the blizzards and reach the goal?
 *<p><a href="https://adventofcode.com/2022/day/24">2022 Day 24</a>
 */
public class Day24 extends AbstractProblem<List<String>, Number> {

    private final List<Blizzard> blizzards = new ArrayList<>();

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2022, 24));
    }

    @Override
    public Number solve1() {
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char c = input.get(y).charAt(x);
                switch (c) {
                    case '<' -> blizzards.add(new Blizzard(x, y, Direction.LEFT));
                    case '>' -> blizzards.add(new Blizzard(x, y, Direction.RIGHT));
                    case '^' -> blizzards.add(new Blizzard(x, y, Direction.UP));
                    case 'v' -> blizzards.add(new Blizzard(x, y, Direction.DOWN));
                    default -> {
                        // no blizzard
                    }
                }
            }
        }
        int startY = 0;
        int startX = IntStream.range(0, input.get(startY).length()).filter(x -> input.get(startY).charAt(x) == '.').findFirst().orElseThrow();
        Point start = new Point(startX, 0);
        int endY = input.size() - 1;
        int endX = IntStream.range(0, input.get(endY).length()).filter(x -> input.get(endY).charAt(x) == '.').findFirst().orElseThrow();
        Point end = new Point(endX, endY);
        int steps = getSteps(start, end);
        int answer1 = steps;
        steps += getSteps(end, start);
        steps += getSteps(start, end);
        answer2 = steps;
        return answer1;
    }

    private int getSteps(Point a, Point b) {
        Set<Point> elfs = new HashSet<>();
        elfs.add(a);
        int steps = 0;
        while (!elfs.contains(b)) {
            blizzards.forEach(Blizzard::update);
            Set<Point> newElfs = new HashSet<>();
            elfs.forEach(e -> newElfs.addAll(nextPoints(e)));
            blizzards.forEach(bl -> newElfs.remove(new Point(bl.x, bl.y)));
            elfs = newElfs;
            steps++;
        }
        return steps;
    }

    private List<Point> nextPoints(Point c) {
        List<Point> newPoints = new ArrayList<>();
        newPoints.add(c);
        for (Direction d : Direction.values()) {
            newPoints.add(new Point(c.x() + d.dx(), c.y() + d.dy()));
        }
        newPoints.removeIf(p -> p.y() < 0 || p.y() == input.size() || input.get(p.y()).charAt(p.x()) == '#');
        return newPoints;
    }

    private class Blizzard {

        int x;
        int y;
        Direction d;

        Blizzard(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        private void update() {
            x += d.dx();
            y += d.dy();
            if (d == Direction.UP && y == 0) {
                y = input.size() - 2;
            } else if (d == Direction.DOWN && y == input.size() - 1) {
                y = 1;
            } else if (d == Direction.LEFT && x == 0) {
                x = input.get(y).length() - 2;
            } else if (d == Direction.RIGHT && x == input.get(y).length() - 1) {
                x = 1;
            }
        }
    }
}
