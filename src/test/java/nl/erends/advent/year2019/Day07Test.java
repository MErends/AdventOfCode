package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static nl.erends.advent.util.Assert.assertEquals;

class Day07Test {
    
    @Test
    void day07Test1() {
        Day07 problem = new Day07();
        problem.setInput(Util.readLine(2019, 7, 1));
        assertEquals(65210, problem.solve1());
    }

    @Test
    void day07Test2() {
        Day07 problem = new Day07();
        problem.setInput(Util.readLine(2019, 7, 2));
        assertEquals(139629729, problem.solve2());
    }
}
