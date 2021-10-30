package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readLine(2019, 2, 1));
        assertEquals(6730673, problem.solve1().intValue());
        assertEquals(3749, problem.solve2().intValue());
    }
}