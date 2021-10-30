package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {
    
    private static final String ANSWER2 = 
            ".##..####.#....####.#.....##..#...#####..##...###.\n" +
            "#..#.#....#....#....#....#..#.#...##....#..#.#....\n" +
            "#....###..#....###..#....#..#..#.#.###..#....#....\n" +
            "#....#....#....#....#....#..#...#..#....#.....##..\n" +
            "#..#.#....#....#....#....#..#...#..#....#..#....#.\n" +
            ".##..#....####.####.####..##....#..#.....##..###..\n";

    @Test
    void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2016, 8, 1));
        assertEquals("6", problem.solve1());
    }

    @Test
    void day08Test2() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2016, 8, 2));
        assertEquals(ANSWER2, problem.solve2());
    }
}
