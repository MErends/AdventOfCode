package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {

    @Test
    public void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput(Util.readLine(2019, 5, 2));
        assertEquals(5044655, problem.solve1().intValue());
        assertEquals(7408802, problem.solve2().intValue());
    }
}