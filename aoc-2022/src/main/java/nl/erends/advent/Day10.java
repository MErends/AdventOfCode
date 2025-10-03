package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 10: Cathode-Ray Tube ---
 * <p>You can design a replacement for the device's video system! It seems to be
 * some kind of cathode-ray tube screen and simple CPU that are both driven by a
 * precise clock circuit. The clock circuit ticks at a constant rate; each tick
 * is called a cycle. The CRT draws a single pixel during each cycle. What eight
 * capital letters appear on your CRT?
 * <p><a href="https://adventofcode.com/2022/day/10">2022 Day 10</a>
 */
public class Day10 extends AbstractProblem<List<String>, String> {


    void main() {
        new Day10().setAndSolve(Util.readInput(2022, 10));
    }

    @Override
    protected String solve1() {
        StringBuilder screen = new StringBuilder();
        int x = 1;
        int cycle = 0;
        int pointer = 0;
        boolean addBusy = false;
        int signalStrength = 0;
        while (pointer < input.size()) {
            String command = input.get(pointer);
            if (command.equals("noop")) {
                pointer++;
            }
            screen.append(Math.abs(cycle % 40 - x) <= 1 ?  '#' : '.');
            cycle++;
            if (cycle % 40 == 0) {
                screen.append('\n');
            }
            if (cycle % 40 == 20) {
                signalStrength += cycle * x;
            }
            if (command.startsWith("addx")) {
                if (addBusy) {
                    x += Integer.parseInt(command.split(" ")[1]);
                    addBusy = false;
                    pointer++;
                } else {
                    addBusy = true;
                }
            }
        }
        answer2 = screen.toString();
        return Integer.toString(signalStrength);
    }
}
