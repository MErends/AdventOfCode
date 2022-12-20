package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.*;

public class Day19 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day19().setAndSolve(Util.readInput(2018, 19));
    }
    
    @Override
    public Integer solve1() {
        String pointerString = input.remove(0);
        int pointerReg = Integer.parseInt(pointerString.split(" ")[1]);
        ElfMachine elfMachine = new ElfMachine(input);
        elfMachine.setMemory("[0, 0, 0, 0, 0, 0]");
        elfMachine.setPointerBound(pointerReg);
        elfMachine.execute();
        return elfMachine.getMemory().get(0);
    }
    
    @Override
    public Integer solve2() {
        // Decompiled code, calculates sum of divisors:
        int b = 10551300;
        int sum = 0;
        for (int divisor = 1; divisor * divisor < b; divisor++) {
            if (b % divisor == 0) {
                sum += divisor;
                sum += b / divisor;
            }
        }
        return sum;
    }
}
