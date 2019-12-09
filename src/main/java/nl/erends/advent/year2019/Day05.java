package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day05 extends AbstractProblem<String, Long> {

    public static void main(String[] args) {
        new Day05().setAndSolve(Util.readLine(2019, 5));
    }

    @Override
    public Long solve1() {
        Intcode intcode = new Intcode(input);
        intcode.addInput(1);
        List<Long> output = new ArrayList<>();
        while (true) {
            intcode.execute();
            if (!intcode.isHalted()) {
                output.add(intcode.getOutput());
            } else {
                break;
            }
        }
        return output.stream().mapToLong(Long::longValue).sum();
    }

    @Override
    public Long solve2() {
        Intcode intcode = new Intcode(input);
        intcode.addInput(5);
        while (!intcode.isHalted()) {
            intcode.execute();
        }
        return intcode.getOutput();
    }

}
