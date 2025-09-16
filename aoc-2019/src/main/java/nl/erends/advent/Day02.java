package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day02 extends AbstractProblem<String, Long> {

    static void main() {
        new Day02().setAndSolve(Util.readLine(2019, 2));
    }
    
    @Override
    public Long solve1() {
        Intcode intcode = new Intcode(input);
        intcode.setCode(1, 12);
        intcode.setCode(2, 2);
        intcode.execute();
        return intcode.getCode(0); //149 too low
    }
    
    @Override
    public Long solve2() {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {   
                Intcode intcode = new Intcode(input);
                intcode.setCode(1, noun);
                intcode.setCode(2, verb);
                intcode.execute();
                if (intcode.getCode(0) == 19690720) {
                    return 100L * noun + verb;
                }
            }
        }
        throw new IllegalStateException();
    }
}
