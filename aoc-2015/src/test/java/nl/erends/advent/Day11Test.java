package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day11Test {

    @Test
    void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput("abcdefgh");
        assertThat(problem.solve1(), is("abcdffaa"));
    }

    @Test
    void day11Test2() {
        Day11 problem = new Day11();
        problem.setInput("ghijklmn");
        assertThat(problem.solve2(), is("ghjbbcdd"));
    }
}
