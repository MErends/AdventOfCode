package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput("flqrgnkx");
        assertEquals(8108, problem.solve1().intValue());
        assertEquals(1242, problem.solve2().intValue());
    }
}