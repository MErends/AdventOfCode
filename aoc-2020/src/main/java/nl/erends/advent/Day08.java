package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day08 extends AbstractProblem<List<String>, Integer> {

    static void main() {
        new Day08().setAndSolve(Util.readInput(2020, 8));
    }

    @Override
    public Integer solve1() {
        Console console = new Console(input);
        console.isInfiniteLoop();
        return console.getAccumulator();
    }

    @Override
    public Integer solve2() {
        for (int index = 0; index < input.size(); index++) {
            List<String> newInput = new ArrayList<>(input);
            String instruction = newInput.get(index);
            if (instruction.startsWith("nop")) {
                newInput.set(index, instruction.replace("nop", "jmp"));
            } else if (instruction.startsWith("jmp")) {
                newInput.set(index, instruction.replace("jmp", "nop"));
            } else {
                continue;
            }
            Console console = new Console(newInput);
            if (!console.isInfiniteLoop()) {
                return console.getAccumulator();
            }
        }
        throw new IllegalArgumentException("Could not find infinite loop");
    }
}
