package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    
    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("ADVENT");
        assertEquals(6, problem.solve1().longValue());
    }

    @Test
    void day09Test2() {
        Day09 problem = new Day09();
        problem.setInput("A(1x5)BC");
        assertEquals(7, problem.solve1().longValue());
    }

    @Test
    void day09Test3() {
        Day09 problem = new Day09();
        problem.setInput("(3x3)XYZ");
        assertEquals(9, problem.solve1().longValue());
        assertEquals(9, problem.solve2().longValue());
    }

    @Test
    void day09Test4() {
        Day09 problem = new Day09();
        problem.setInput("A(2x2)BCD(2x2)EFG");
        assertEquals(11, problem.solve1().longValue());
    }

    @Test
    void day09Test5() {
        Day09 problem = new Day09();
        problem.setInput("(6x1)(1x3)A");
        assertEquals(6, problem.solve1().longValue());
    }

    @Test
    void day09Test6() {
        Day09 problem = new Day09();
        problem.setInput("X(8x2)(3x3)ABCY");
        assertEquals(18, problem.solve1().longValue());
        assertEquals(20, problem.solve2().longValue());
    }

    @Test
    void day09Test7() {
        Day09 problem = new Day09();
        problem.setInput("(27x12)(20x12)(13x14)(7x10)(1x12)A");
        assertEquals(241920, problem.solve2().longValue());
    }

    @Test
    void day09Test8() {
        Day09 problem = new Day09();
        problem.setInput("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN");
        assertEquals(445, problem.solve2().longValue());
    }
}
