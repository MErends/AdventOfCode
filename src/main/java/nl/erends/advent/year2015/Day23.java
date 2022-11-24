package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 23: Opening the Turing Lock ---
 * <p>Little Jane Marie just got her very first computer for Christmas. The
 * manual explains that the computer supports two registers and six
 * instructions. The program exits when it tries to run an instruction beyond
 * the ones defined. What is the value in register b when the program in your
 * puzzle input is finished executing?
 * <p><a href="https://adventofcode.com/2021/day/23">2015 Day 23</a>
 */
public class Day23 extends AbstractProblem<List<String>, Integer> {

    private int pointer;
    private int a = 0;
    private int b = 0;

    public static void main(String[] args) {
        new Day23().setAndSolve(Util.readInput(2015, 23));
    }

    @Override
    protected Integer solve1() {
        boolean active = true;
        while (active) {
            active = execute();
        }
        return b;
    }

    @Override
    public Integer solve2() {
        a = 1;
        b = 0;
        pointer = 0;
        boolean active = true;
        while (active) {
            active = execute();
        }
        return b;
    }

    private boolean execute() {
        if (pointer < 0 || pointer >= input.size()) {
            return false;
        }
        String instruction = input.get(pointer);
        String[] words = instruction.split(" ");
        switch (words[0]) {
            default:
            case "hlf":
                if (instruction.charAt(4) == 'a') {
                    a /= 2;
                } else {
                    b /= 2;
                }
                pointer++;
                break;
            case "tpl":
                if (instruction.charAt(4) == 'a') {
                    a *= 3;
                } else {
                    b *= 3;
                }
                pointer++;
                break;
            case "inc":
                if (instruction.charAt(4) == 'a') {
                    a++;
                } else {
                    b++;
                }
                pointer++;
                break;
            case "jmp":
                pointer += Integer.parseInt(words[1]);
                break;
            case "jie":
                jumpIfEven(instruction.charAt(4), Integer.parseInt(words[2]));
                break;
            case "jio":
                jumpIfOne(instruction.charAt(4), Integer.parseInt(words[2]));
                break;
        }
        return true;
    }

    private void jumpIfEven(char register, int amount) {
        int jie = 1;
        if (register == 'a' && a % 2 == 0
                || register == 'b' && b % 2 == 0) {
            jie = amount;
        }
        pointer += jie;
    }

    private void jumpIfOne(char register, int amount) {
        int jio = 1;
        if (register == 'a' && a == 1
                || register == 'b' && b == 1) {
            jio = amount;
        }
        pointer += jio;
    }
}
