package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day09Test {
    
    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("{<a>,<a>,<a>,<a>}");
        assertThat(problem.solve1(), is(1));
    }

    @Test
    void day09Test2() {
        Day09 problem = new Day09();
        problem.setInput("<{o\"i!a,<{i<a>");
        assertThat(problem.solve2(), is(10));
    }
}
