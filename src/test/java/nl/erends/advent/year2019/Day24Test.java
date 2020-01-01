package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day24Test {

    @Test
    public void day24Test1() {
        Day24 problem = new Day24();
        problem.setInput(Util.readInput(2019, 24, 1));
        assertEquals(2129920, problem.solve1());
    }
}