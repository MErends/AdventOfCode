package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day09 extends AbstractProblem<String, Long> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readLine(2019, 9));
    }

    @Override
    public Long solve1() {
        return getResult(1);

    }

    @Override
    public Long solve2() {
        return getResult(2);
    }

    private Long getResult(int parameter) {
        Intcode intcode = new Intcode(input);
        intcode.addInput(parameter);
        intcode.execute();
        return intcode.getOutput();
    }
}
