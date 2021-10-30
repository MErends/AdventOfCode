package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(1362);
        assertEquals(82, problem.solve1().intValue());
        problem = new Day13();
        problem.setInput(1362);
        assertEquals(138, problem.solve2().intValue());
    }
}
