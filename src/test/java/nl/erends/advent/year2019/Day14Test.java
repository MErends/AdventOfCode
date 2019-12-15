package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day14Test {

    @Test
    public void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput(Util.readInput(2019, 14, 1));
        assertEquals(2210736, problem.solve1());
        assertEquals(460664, problem.solve2());
    }
    
}