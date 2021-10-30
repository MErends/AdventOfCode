package nl.erends.advent.year2018;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day09Test {

    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("10 players; last marble is worth 1618 points");
        assertThat(problem.solve1(), is(8317));
        assertThat(problem.solve2(), is(74765078));
    }
}