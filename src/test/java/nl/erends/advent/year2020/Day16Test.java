package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static nl.erends.advent.util.Assert.assertEquals;

public class Day16Test {
    
    @Test
    public void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2020, 16, 1));
        assertEquals(71, problem.solve1());
    }
    
    @Test
    public void day16Test2() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2020, 16, 2));
        assertEquals(12 * 13, problem.solve2());
        
    }
}
