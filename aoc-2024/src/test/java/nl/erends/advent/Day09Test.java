package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day09Test {

    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput("2333133121414131402");
        assertThat(problem.solve1(), is (1928L));
        assertThat(problem.solve2(), is (2858L));
    }
}
