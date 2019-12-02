package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day02 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readLine(2019, 2));
    }
    
    @Override
    public Integer solve1() {
        Intcode intcode = new Intcode(input);
        intcode.setCode(1, 12);
        intcode.setCode(2, 2);
        intcode.execute();
        return intcode.getCode(0); //149 too low
    }
    
    @Override
    public Integer solve2() {
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {   
                Intcode intcode = new Intcode(input);
                intcode.setCode(1, noun);
                intcode.setCode(2, verb);
                intcode.execute();
                if (intcode.getCode(0) == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }
        throw new IllegalStateException();
    }
}
