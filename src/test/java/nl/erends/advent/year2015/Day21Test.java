package nl.erends.advent.year2015;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day21Test {

    @Test
    public void day21Test1() {
        Day21 problem = new Day21();
        assertEquals(78, problem.solve1(null).intValue());
        problem = new Day21();
        assertEquals(148, problem.solve2(null).intValue());
    }
}
