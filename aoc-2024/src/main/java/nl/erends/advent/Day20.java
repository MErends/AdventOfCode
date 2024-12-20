package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>--- Day 20: Race Condition ---</h1>
 * <p>While The Historians get to work, a nearby program sees that you're idle
 * and challenges you to a race. Because there is only a single path from the
 * start to the end and the programs all go the same speed, the races used to be
 * pretty boring. To make things more interesting, they introduced a new rule to
 * the races: programs are allowed to cheat. How many cheats would save you at
 * least 100 picoseconds?</p>
 * <p><a href="https://adventofcode.com/2024/day/20">2024 Day 20</a></p>
 */
public class Day20 extends AbstractProblem<List<String>, Integer> {

    private int minimalCheat = 100;
    private List<Coord> trail;

    public static void main(String[] args) {
        new Day20().setAndSolve(Util.readInput(2024, 20));
    }

    @Override
    protected Integer solve1() {
        if (trail == null) {
            readTrail();
        }
        int cheatCount = 0;
        int cheatRange = part2 ? 20 : 2;
        for (int startCheat = 0; startCheat < trail.size(); startCheat++) {
            for (int endCheat = startCheat + 4; endCheat < trail.size(); endCheat++) {
                Coord start = trail.get(startCheat);
                Coord end = trail.get(endCheat);
                int stepsSaved = endCheat - startCheat;
                int cheatLength = start.distanceTo(end);
                int cheatSaves = stepsSaved - cheatLength;
                if (cheatLength <= cheatRange && cheatSaves >= minimalCheat) {
                    cheatCount++;
                }
            }
        }
        return cheatCount;
    }

    private void readTrail() {
        char[][] grid = new char[input.size()][];
        Coord head = Coord.ZERO;
        for (int y = 0; y < input.size(); y++) {
            grid[y] = input.get(y).toCharArray();
            if (head == Coord.ZERO && input.get(y).indexOf('S') != -1) {
                head = Coord.of(input.get(y).indexOf('S'), y);
            }
        }
        Direction d = Direction.UP;
        for (Direction dd : Direction.values()) {
            if (grid[head.y + dd.dy()][head.x + dd.dx()] != '#') {
                d = dd;
                break;
            }
        }
        trail = new ArrayList<>();
        boolean busy = true;
        while (busy) {
            trail.add(head);
            busy = false;
            List<Direction> newDs = List.of(d.turnLeft(), d, d.turnRight());
            for (Direction newD : newDs) {
                Coord newHead = head.addDirection(newD);
                if (grid[newHead.y][newHead.x] != '#') {
                    head = newHead;
                    d = newD;
                    busy = true;
                    break;
                }
            }
        }
    }

    public void setTestValue(int minimalCheat) {
        this.minimalCheat = minimalCheat;
    }
}
