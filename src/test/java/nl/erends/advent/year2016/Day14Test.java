package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput("abc");
        assertEquals(22728, problem.solve1().intValue());
    }
}
