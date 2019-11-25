package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {

    @Test
    public void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2018, 2, 1));
        assertEquals("12", problem.solve1());
    }

    @Test
    public void day02Test2() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2018, 2, 2));
        assertEquals("fgij", problem.solve2());
    }    
}