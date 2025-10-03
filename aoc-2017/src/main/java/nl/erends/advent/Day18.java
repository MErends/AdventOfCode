package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day18 extends AbstractProblem<List<String>, Long> {

    void main() {
        new Day18().setAndSolve(Util.readInput(2017, 18));
    }

    @Override
    public Long solve1() {
        Duet duet = new Duet(input);
        duet.execute();
        return duet.getRecieve();
    }
    
    @Override
    public Long solve2() {
        Duet duet = new Duet(input);
        duet.setMultithread();
        duet.execute();
        return duet.getSound();
    }
}
