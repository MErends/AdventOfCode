package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day05Test {

    @Test
    public void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2015, 5, 1));
        assertEquals(2, problem.solve1().intValue());
    }
    
    @Test
    public void day05Test2() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2015, 5, 2));
        assertEquals(2, problem.solve2().intValue());
    }
}
