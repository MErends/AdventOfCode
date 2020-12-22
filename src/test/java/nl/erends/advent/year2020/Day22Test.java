package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day22Test {

    @Test
    public void day22Test1() {
        Day22 problem = new Day22();
        problem.setInput(Util.readInput(2020, 22, 1));
        assertEquals(306, problem.solve1());
        assertEquals(291, problem.solve2());
    }


    @Test
    public void day22Test2() {
        Day22 problem = new Day22();
        problem.setInput(Util.readInput(2020, 22, 2));
        assertEquals(106, problem.solve2());
    }
}
