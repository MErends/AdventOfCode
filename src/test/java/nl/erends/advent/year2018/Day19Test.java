package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day19Test {

    @Test
    public void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2018, 19, 1));
        assertEquals(2821, problem.solve1().intValue());
        assertEquals(30529296, problem.solve2().intValue());
    }
    
}