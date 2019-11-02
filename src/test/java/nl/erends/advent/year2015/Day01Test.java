package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {
    
    @Test
    public void day01Test1() {
        String input = Util.readLine(2015, 1, 1);
        Day01 problem = new Day01();
        assertEquals(0, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test2() {
        String input = Util.readLine(2015, 1, 2);
        Day01 problem = new Day01();
        assertEquals(0, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test3() {
        String input = Util.readLine(2015, 1, 3);
        Day01 problem = new Day01();
        assertEquals(3, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test4() {
        String input = Util.readLine(2015, 1, 4);
        Day01 problem = new Day01();
        assertEquals(3, problem.solve1(input).intValue());
    }
    
    @Test
    public void day01Test5() {
        String input = Util.readLine(2015, 1, 5);
        Day01 problem = new Day01();
        assertEquals(3, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test6() {
        String input = Util.readLine(2015, 1, 6);
        Day01 problem = new Day01();
        assertEquals(-1, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test7() {
        String input = Util.readLine(2015, 1, 7);
        Day01 problem = new Day01();
        assertEquals(-1, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test8() {
        String input = Util.readLine(2015, 1, 8);
        Day01 problem = new Day01();
        assertEquals(-3, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test9() {
        String input = Util.readLine(2015, 1, 9);
        Day01 problem = new Day01();
        assertEquals(-3, problem.solve1(input).intValue());
    }

    @Test
    public void day01Test10() {
        String input = Util.readLine(2015, 1, 10);
        Day01 problem = new Day01();
        assertEquals(1, problem.solve2(input).intValue());
    }

    @Test
    public void day01Test11() {
        String input = Util.readLine(2015, 1, 11);
        Day01 problem = new Day01();
        assertEquals(5, problem.solve2(input).intValue());
    }
}