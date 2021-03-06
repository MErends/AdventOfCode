package nl.erends.advent.year2019;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Test {

    @Test
    public void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput("80871224585914546619083218645595");
        assertEquals("24176176", problem.solve1());
    }

    @Test
    public void day16Test2() {
        Day16 problem = new Day16();
        problem.setInput("03036732577212944063491565474664");
        assertEquals("84462026", problem.solve2());
    }
}