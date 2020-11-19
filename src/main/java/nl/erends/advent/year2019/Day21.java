package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day21 extends AbstractProblem<String, Long> {

    private static final List<String> INSTRUCTIONS = Arrays.asList(
            "NOT B J",  // If B is a hole, then jump
            "NOT C T",  // Is C a hole?
            "OR T J",   // If B or C is a hole, then jump
            "AND D J",  // If we jump, D has to be ground
            "NOT A T",  // Is A a hole?
            "OR T J");  // If A is a hole, then jump

    public static void main(String[] args) {
        new Day21().setAndSolve(Util.readLine(2019, 21));
    }

    @Override
    public Long solve1() {
        List<String> instructions = new ArrayList<>(INSTRUCTIONS);
        instructions.add("WALK");
        return calculateDamage(instructions);
    }

    @Override
    public Long solve2() {
        List<String> instructions = new ArrayList<>(INSTRUCTIONS);
        instructions.add(4, "AND H J"); // If we jump, H has to be ground
        instructions.add("RUN");
        return calculateDamage(instructions);
    }
    
    private long calculateDamage(List<String> instructions) {
        int i = 0;
        Intcode computer = new Intcode(input);
        while (true) {
            computer.execute();
            if (computer.hasOutput()) {
                long value = computer.getOutput();
                if (value > 128) {
                    return value;
                }
            } else if (i < instructions.size()) {
                for (char c : instructions.get(i).toCharArray()) {
                    computer.addInput(c);
                }
                computer.addInput('\n');
                i++;
            }
        }
    }
}
