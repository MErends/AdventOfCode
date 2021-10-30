package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {
    
    @Test
    void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput("1122");
        assertEquals(3, problem.solve1().intValue());
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput("1111");
        assertEquals(4, problem.solve1().intValue());
    }

    @Test
    void day01Test3() {
        Day01 problem = new Day01();
        problem.setInput("1234");
        assertEquals(0, problem.solve1().intValue());
    }

    @Test
    void day01Test4() {
        Day01 problem = new Day01();
        problem.setInput("91212129");
        assertEquals(9, problem.solve1().intValue());
    }

    @Test
    void day01Test5() {
        Day01 problem = new Day01();
        problem.setInput("1212");
        assertEquals(6, problem.solve2().intValue());
    }

    @Test
    void day01Test6() {
        Day01 problem = new Day01();
        problem.setInput("1221");
        assertEquals(0, problem.solve2().intValue());
    }

    @Test
    void day01Test7() {
        Day01 problem = new Day01();
        problem.setInput("123425");
        assertEquals(4, problem.solve2().intValue());
    }

    @Test
    void day01Test8() {
        Day01 problem = new Day01();
        problem.setInput("123123");
        assertEquals(12, problem.solve2().intValue());
    }

    @Test
    void day01Test9() {
        Day01 problem = new Day01();
        problem.setInput("12131415");
        assertEquals(4, problem.solve2().intValue());
    }
}