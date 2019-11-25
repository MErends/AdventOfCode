package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Test {

    @Test
    public void day07Test1() {
        Day07 problem = new Day07();
        problem.setInput(Util.readInput(2018, 7, 1));
        problem.setWorkerCount(2);
        problem.setJobTime(0);
        assertEquals("CABDFE", problem.solve1());
        assertEquals("15", problem.solve2());
    }
}