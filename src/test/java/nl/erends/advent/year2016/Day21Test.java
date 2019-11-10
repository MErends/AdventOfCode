package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day21Test {
    
    @Test
    public void day21Test1() {
        Day21 problem = new Day21();
        problem.setInput(Util.readInput(2016, 21, 1));
        problem.setInput1("abcde");
        problem.setInput2("decab");
        assertEquals("decab", problem.solve1());
        assertEquals("abcde", problem.solve2());
    }
}