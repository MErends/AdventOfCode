package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {
    
    @Test
    void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 1));
        assertEquals(0, problem.solve1().intValue());
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 2));
        assertEquals(0, problem.solve1().intValue());
    }

    @Test
    void day01Test3() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 3));
        assertEquals(3, problem.solve1().intValue());
    }

    @Test
    void day01Test4() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 4));
        assertEquals(3, problem.solve1().intValue());
    }
    
    @Test
    void day01Test5() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 5));
        assertEquals(3, problem.solve1().intValue());
    }

    @Test
    void day01Test6() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 6));
        assertEquals(-1, problem.solve1().intValue());
    }

    @Test
    void day01Test7() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 7));
        assertEquals(-1, problem.solve1().intValue());
    }

    @Test
    void day01Test8() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 8));
        assertEquals(-3, problem.solve1().intValue());
    }

    @Test
    void day01Test9() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 9));
        assertEquals(-3, problem.solve1().intValue());
    }

    @Test
    void day01Test10() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 10));
        assertEquals(1, problem.solve2().intValue());
    }

    @Test
    void day01Test11() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, 11));
        assertEquals(5, problem.solve2().intValue());
    }
}