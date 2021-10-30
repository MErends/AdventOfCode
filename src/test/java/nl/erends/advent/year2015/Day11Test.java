package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day11Test {

    @Test
    void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2015, 11, 1));
        assertThat(problem.solve1(), is("abcdffaa"));
    }

    @Test
    void day11Test2() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2015, 11, 2));
        assertThat(problem.solve2(), is("ghjbbcdd"));
    }
}
