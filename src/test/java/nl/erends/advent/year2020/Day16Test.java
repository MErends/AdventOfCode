package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day16Test {
    
    @Test
    void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2020, 16, 1));
        assertEquals(71, problem.solve1());
    }
    
    @Test
    void day16Test2() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2020, 16, 2));
        assertEquals(12 * 13, problem.solve2());
        
    }
}
