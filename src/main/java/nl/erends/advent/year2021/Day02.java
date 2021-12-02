package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 2: Dive! ---
 * <p>It seems like the submarine can take a series of commands. Calculate the
 * horizontal position and depth you would have after following the planned
 * course. What do you get if you multiply your final horizontal position by
 * your final depth?
 * <p><a href="https://adventofcode.com/2021/day/2">2021 Day 2</a>
 */
public class Day02 extends AbstractProblem<List<String>, Integer> {

    private int depth;
    private int depth2;
    private int distance;
    private int aim;
    
    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readInput(2021, 2));
    }

    @Override
    protected Integer solve1() {
        input.forEach(this::updatePosition);
        return depth * distance;
    }

    private void updatePosition(String line) {
        String[] movement = line.split(" ");
        int value = Integer.parseInt(movement[1]);
        switch (movement[0]) {
            case "forward":
                distance += value;
                depth2 += value * aim;
                answer2 = depth2 * distance;
                break;
            case "down":
                aim += value;
                depth += value;
                break;
            default: // up
                aim -= value;
                depth -= value;
                break;
        }
    }
}
