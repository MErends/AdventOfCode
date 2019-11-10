package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day25 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day25().setAndSolve(Util.readInput(2016, 25));
    }

    @Override
    public Integer solve1() {
        answer2 = 0;
        int a = 1;
        while (true) {
            Assembunny assembunny = new Assembunny(input, 12);
            assembunny.getMemoryBank().put("a", a);
            assembunny.execute();
            if ("010101010101".equals(assembunny.getOutput())) {
                break;
            }
            a++;
        }
        return a;
    }
}
