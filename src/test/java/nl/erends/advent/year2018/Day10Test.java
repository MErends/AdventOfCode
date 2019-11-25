package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Test {

    @Test
    public void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2018, 10, 1));
        problem.setFontSize(8);
        String answer1 = "#...#..###\n" +
                         "#...#...#.\n" +
                         "#...#...#.\n" +
                         "#####...#.\n" +
                         "#...#...#.\n" +
                         "#...#...#.\n" +
                         "#...#...#.\n" +
                         "#...#..###\n";

        assertEquals(answer1, problem.solve1());
        assertEquals("3", problem.solve2());
    }
}