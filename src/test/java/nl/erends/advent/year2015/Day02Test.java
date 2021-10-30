package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {
    
    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2015, 2, 1));
        assertEquals(101, problem.solve1().intValue());
        assertEquals(48, problem.solve2().intValue());
    }

}