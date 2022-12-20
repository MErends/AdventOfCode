package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day08Test {
    
    private static final String ANSWER2 = """
            .##..####.#....####.#.....##..#...#####..##...###.
            #..#.#....#....#....#....#..#.#...##....#..#.#....
            #....###..#....###..#....#..#..#.#.###..#....#....
            #....#....#....#....#....#..#...#..#....#.....##..
            #..#.#....#....#....#....#..#...#..#....#..#....#.
            .##..#....####.####.####..##....#..#.....##..###..
            """;

    @Test
    void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2016, 8, 1));
        assertThat(problem.solve1(), is("6"));
    }

    @Test
    void day08Test2() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2016, 8, 2));
        assertThat(problem.solve2(), is(ANSWER2));
    }
}
