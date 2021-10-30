package nl.erends.advent.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    @Test
    void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput("[1,2,3]");
        assertEquals(6, problem.solve1().intValue());
    }

    @Test
    void day12Test2() {
        Day12 problem = new Day12();
        problem.setInput("{\"a\":2,\"b\":4}");
        assertEquals(6, problem.solve1().intValue());
    }

    @Test
    void day12Test3() {
        Day12 problem = new Day12();
        problem.setInput("[[[3]]]");
        assertEquals(3, problem.solve1().intValue());
    }

    @Test
    void day12Test4() {
        Day12 problem = new Day12();
        problem.setInput("{\"a\":{\"b\":4},\"c\":-1}");
        assertEquals(3, problem.solve1().intValue());
    }

    @Test
    void day12Test5() {
        Day12 problem = new Day12();
        problem.setInput("{\"a\":[-1,1]}");
        assertEquals(0, problem.solve1().intValue());
    }

    @Test
    void day12Test6() {
        Day12 problem = new Day12();
        problem.setInput("[-1,{\"a\":1}]");
        assertEquals(0, problem.solve1().intValue());
    }

    @Test
    void day12Test7() {
        Day12 problem = new Day12();
        problem.setInput("[]");
        assertEquals(0, problem.solve1().intValue());
    }
    
    @Test
    void day12Test8() {
        Day12 problem = new Day12();
        problem.setInput("{}");
        assertEquals(0, problem.solve1().intValue());
    }
}
