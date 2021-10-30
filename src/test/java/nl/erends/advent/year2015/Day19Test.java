package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {
    
    @Test
    void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(Util.readInput(2015, 19, 1));
        assertEquals(212, problem.solve1().intValue());
    }

}