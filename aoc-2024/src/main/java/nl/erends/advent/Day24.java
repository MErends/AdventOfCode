package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 24: Crossed Wires ---</h1>
 * <p>While The Historians search the grove, one of them asks if you can take a
 * look at the monitoring device; apparently, it's been malfunctioning recently.
 * The device seems to be trying to produce a number through some boolean logic
 * gates. Each gate has two inputs and one output. The gates all operate on
 * values that are either true (1) or false (0). Ultimately, the system is
 * trying to produce a number by combining the bits on all wires starting with
 * z. Simulate the system of gates and wires. What decimal number does it
 * output?</p>
 * <p><a href="https://adventofcode.com/2024/day/24">2024 Day 24</a></p>
 */
public class Day24 extends AbstractProblem<List<String>, Long> {

    final Map<String, Integer> wiremap = new HashMap<>();

    void main() {
        new Day24().setAndSolve(Util.readInput(2024, 24));
    }

    @Override
    protected Long solve1() {
        List<Gate> gates = new ArrayList<>();
        List<String> zWires = new ArrayList<>();
        int emptyIndex = input.indexOf("");
        for (int i = 0; i < emptyIndex; i++) {
            String[] line = input.get(i).split(": ");
            wiremap.put(line[0], Integer.parseInt(line[1]));
        }
        for (int i = emptyIndex + 1; i < input.size(); i++) {
            String[] line = input.get(i).split(" ");
            if (line[4].startsWith("z")) {
                zWires.add(line[4]);
            }
            switch (line[1]) {
                case "AND" -> gates.add(new AndGate(line[0], line[2], line[4]));
                case "XOR" -> gates.add(new XorGate(line[0], line[2], line[4]));
                default -> gates.add(new OrGate(line[0], line[2], line[4]));
            }
        }
        while (!wiremap.keySet().containsAll(zWires)) {
            gates.forEach(Gate::fire);
            gates.removeIf(g -> g.fired);
        }
        zWires.sort(Collections.reverseOrder());
        String binaryString = zWires.stream().map(z -> Integer.toString(wiremap.get(z)))
                .collect(Collectors.joining());
        return Long.parseLong(binaryString, 2);
    }

    abstract static class Gate {

        boolean fired = false;
        final String in1;
        final String in2;
        final String out;

        Gate(String in1, String in2, String out) {
            this.in1 = in1;
            this.in2 = in2;
            this.out = out;
        }

        abstract void fire();
    }

    class AndGate extends Gate {

        AndGate(String in1, String in2, String out) {
            super(in1, in2, out);
        }

        @Override
        void fire() {
            int value1 = wiremap.getOrDefault(in1, -1);
            int value2 = wiremap.getOrDefault(in2, -1);
            if (value1 != -1 && value2 != -1) {
                if (value1 == 1 && value2 == 1) {
                    wiremap.put(out, 1);
                } else {
                    wiremap.put(out, 0);
                }
                fired = true;
            }
        }
    }

    class OrGate extends Gate {

        OrGate(String in1, String in2, String out) {
            super(in1, in2, out);
        }

        @Override
        void fire() {
            int value1 = wiremap.getOrDefault(in1, -1);
            int value2 = wiremap.getOrDefault(in2, -1);
            if (value1 != -1 && value2 != -1) {
                if (value1 == 1 || value2 == 1) {
                    wiremap.put(out, 1);
                } else {
                    wiremap.put(out, 0);
                }
                fired = true;
            }
        }
    }

    class XorGate extends Gate {

        XorGate(String in1, String in2, String out) {
            super(in1, in2, out);
        }

        @Override
        void fire() {
            int value1 = wiremap.getOrDefault(in1, -1);
            int value2 = wiremap.getOrDefault(in2, -1);
            if (value1 != -1 && value2 != -1) {
                if (value1 != value2) {
                    wiremap.put(out, 1);
                } else {
                    wiremap.put(out, 0);
                }
                fired = true;
            }
        }
    }
}
