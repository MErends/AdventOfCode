package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day23 extends AbstractProblem<List<String>, Integer> {

    static void main() {
        new Day23().setAndSolve(Util.readInput(2016, 23));
    }

    @Override
    public Integer solve1() {
        Assembunny assembunny = new Assembunny(input);
        assembunny.getMemoryBank().put("a", 7);
        assembunny.execute();
        return assembunny.getMemoryBank().get("a");
    }

    @Override
    public Integer solve2() {
        Assembunny assembunny = new Assembunny(input);
        assembunny.getMemoryBank().put("a", 12);
        assembunny.execute();
        return assembunny.getMemoryBank().get("a");
    }
}
