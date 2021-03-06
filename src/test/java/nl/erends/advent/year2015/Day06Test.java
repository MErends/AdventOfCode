package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06Test {

    @Test
    public void day06Test1() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2015, 6, 1));
        assertEquals(998996, problem.solve1().intValue());
        assertEquals(1001996, problem.solve2().intValue());
    }
    
    @Test
    public void day06Test2() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2015, 6, 2));
        assertEquals(999999, problem.solve1().intValue());
        assertEquals(2000001, problem.solve2().intValue());
    }
}
