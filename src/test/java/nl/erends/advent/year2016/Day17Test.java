package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    @Test
    void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput("ihgpwlah");
        assertEquals("DDRRRD", problem.solve1());
        assertEquals("370", problem.solve2());
    }

    @Test
    void day17Test2() {
        Day17 problem = new Day17();
        problem.setInput("kglvqrro");
        assertEquals("DDUDRLRRUDRD", problem.solve1());
        assertEquals("492", problem.solve2());
    }

    @Test
    void day17Test3() {
        Day17 problem = new Day17();
        problem.setInput("ulqzkmiv");
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", problem.solve1());
        assertEquals("830", problem.solve2());
    }
}
