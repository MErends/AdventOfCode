package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {
    
    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput("192,69,168,160,78,1,166,28,0,83,198,2,254,255,41,12");
        assertEquals("48705", problem.solve1());
    }

    @Test
    void day10Test2() {
        Day10 problem = new Day10();
        problem.setInput("AoC 2017");
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", problem.solve2());
    }
}