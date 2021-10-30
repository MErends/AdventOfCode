package nl.erends.advent.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput(2018);
        assertEquals("5941429882", problem.solve1());
        assertEquals("86764", problem.solve2());
    }
}