package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {

    @Test
    public void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2018, 4, 1));
        assertEquals(240, problem.solve1().intValue());
        assertEquals(4455, problem.solve2().intValue());
    }    
}