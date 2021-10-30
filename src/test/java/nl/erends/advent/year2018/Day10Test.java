package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day10Test {

    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2018, 10, 1));
        problem.setFontSize(8);
        String answer1 = "#...#..###\n" +
                         "#...#...#.\n" +
                         "#...#...#.\n" +
                         "#####...#.\n" +
                         "#...#...#.\n" +
                         "#...#...#.\n" +
                         "#...#...#.\n" +
                         "#...#..###\n";

        assertThat(problem.solve1(), is(answer1));
        assertThat(problem.solve2(), is("3"));
    }
}