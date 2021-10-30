package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    @Test
    void day18Test1() {
        Day18 problem = new Day18();
        problem.setInput(Util.readLine(2016, 18, 1));
        assertEquals(1913, problem.solve1().intValue());
        assertEquals(19993564, problem.solve2().intValue());
    }
}
