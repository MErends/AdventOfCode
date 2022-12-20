package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day12 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day12().setAndSolve(Util.readInput(2016, 12));
    }
    
    @Override
    public Integer solve1() {
        Assembunny assembunny = new Assembunny(input);
        assembunny.execute();
        return assembunny.getMemoryBank().get("a");
    }
    
    @Override
    public Integer solve2() {
        Assembunny assembunny = new Assembunny(input);
        assembunny.getMemoryBank().put("c", 1);
        assembunny.execute();
        return assembunny.getMemoryBank().get("a");
    }
}
