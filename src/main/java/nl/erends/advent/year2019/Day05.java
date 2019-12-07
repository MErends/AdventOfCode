package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day05 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day05().setAndSolve(Util.readLine(2019, 5));
    }

    @Override
    public Integer solve1() {
        Intcode intcode = new Intcode(input);
        intcode.getInput().add(1);
        List<Integer> output = new ArrayList<>();
        while (true) {
            intcode.execute();
            if (!intcode.isHalted()) {
                output.add(intcode.getOutput());
            } else {
                break;
            }
        }
        return output.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public Integer solve2() {
        Intcode intcode = new Intcode(input);
        intcode.getInput().add(5);
        while (!intcode.isHalted()) {
            intcode.execute();
        }
        return intcode.getOutput();
    }

}
