package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

public class Day21 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day21().setAndSolve(Util.readLine(2019, 21));
    }

    @Override
    public Integer solve1() {
        List<String> instructions = Arrays.asList(
                "NOT A J",
                "NOT B T",
                "OR T J ",
                "NOT C T",
                "OR T J ",
                "NOT D T",
                "NOT T T",
                "AND T J",
                "WALK");
        return calculateDamage(instructions);
    }

    @Override
    public Integer solve2() {        
        List<String> instructions = Arrays.asList(
                
                "RUN");
        return calculateDamage(instructions);
    }
    
    private int calculateDamage(List<String> instructions) {
        int i = 0;
        Intcode computer = new Intcode(input);
        while (!computer.isHalted()) {
            computer.execute();
            if (computer.hasOutput()) {
                long value = computer.getOutput();
                if (value > 128) {
                    return (int) value;
                } else {
                    System.out.print((char) value);
                }
            } else if (i < instructions.size()) {
                for (char c : instructions.get(i).toCharArray()) {
                    computer.addInput(c);
                }
                computer.addInput('\n');
                i++;
            }
        }
        return 0;
    }
}
