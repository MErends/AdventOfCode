package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day07Test {
    
    @Test
    void day07Test1() {
        Day07 problem = new Day07();
        problem.setInput(Util.readInput(2020, 7, 1));
        assertEquals(4, problem.solve1());
        assertEquals(32, problem.solve2());
    }
    
    @Test
    void day07Test2() {
        Day07 problem = new Day07();
        problem.setInput(Util.readInput(2020, 7, 2));
        assertEquals(126, problem.solve2());
    }
}
