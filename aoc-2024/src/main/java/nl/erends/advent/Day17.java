package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 17: Chronospatial Computer ---</h1>
 * <p>The small handheld device suddenly unfolds into an entire computer! The
 * Historians look around nervously before one of them tosses it to you.The
 * computer knows eight instructions, each identified by a 3-bit number (called
 * the instruction's opcode). Each instruction also reads the 3-bit number after
 * it as an input; this is called its operand. Using the information provided by
 * the debugger, initialize the registers to the given values, then run the
 * program. Once it halts, what do you get if you use commas to join the values
 * it output into a single string?</p>
 * <p><a href="https://adventofcode.com/2024/day/17">2024 Day 17</a></p>
 */
public class Day17 extends AbstractProblem<List<String>, String> {

    Computer computer;

    void main() {
        new Day17().setAndSolve(Util.readInput(2024, 17));
    }

    @Override
    protected String solve1() {
        return new Computer(input).run().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public String solve2() {
        computer = new Computer(input);
        List<Integer> powers = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return Long.toString(findNumber(powers, 15));
    }

    long findNumber(List<Integer> powers, int currentPower) {
        if (currentPower == -1) {
            return 0;
        }
        for (int power = 0; power <= 7; power++) {
            if (currentPower == 15 && power == 0) {
                continue;
            }
            List<Integer> newPowers = new ArrayList<>(powers);
            newPowers.set(currentPower, power);
            long regA = getRegA(newPowers);
            computer.regA = regA;
            List<Integer> result = computer.run();
            if (result.equals(computer.instructions)) {
                return regA;
            }
            if (result.get(currentPower).equals(computer.instructions.get(currentPower))) {
                long candidate = findNumber(newPowers, currentPower - 1);
                if (candidate != 0) {
                    return candidate;
                }
            }
        }
        return 0;
    }

    long getRegA(List<Integer> powers) {
        long regA = 0;
        for (int power = 15; power >= 0; power--) {
            regA += (long) (powers.get(power) * Math.pow(8, power));
        }
        return regA;
    }

    static class Computer {

        long regA;
        long regB;
        long regC;
        final List<Integer> instructions;
        int pointer;
        List<Integer> output;

        Computer(List<String> input) {
            regA = Integer.parseInt(input.get(0).split(": ")[1]);
            regB = Integer.parseInt(input.get(1).split(": ")[1]);
            regC = Integer.parseInt(input.get(2).split(": ")[1]);
            instructions = Arrays.stream(input.get(4).split(": ")[1].split(","))
                    .map(Integer::parseInt).toList();
        }

        List<Integer> run() {
            pointer = 0;
            output = new ArrayList<>();
            while (pointer < instructions.size()) {
                int opcode = instructions.get(pointer++);
                long operand = getValue(instructions.get(pointer++));
                switch (opcode) {
                    case 0 -> regA = (long) (regA / Math.pow(2.0, operand));
                    case 1 -> regB = regB ^ instructions.get(pointer - 1);
                    case 2 -> regB = operand % 8;
                    case 3 -> pointer = regA != 0 ? instructions.get(pointer - 1) : pointer;
                    case 4 -> regB = regB ^ regC;
                    case 5 -> output.add((int) (operand % 8));
                    case 6 -> regB = (long) (regA / Math.pow(2.0, operand));
                    case 7 -> regC = (long) (regA / Math.pow(2.0, operand));
                    default -> throw new IllegalArgumentException("Unknown opcode=" + opcode);
                }
            }
            return output;
        }

        long getValue(int operand) {
            return switch (operand) {
                case 4 -> regA;
                case 5 -> regB;
                case 6 -> regC;
                default -> operand;
            };
        }
    }
}
