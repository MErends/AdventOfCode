package nl.erends.advent.year2022;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day09Test {

    @Test
    void day09Test1() {
        Day09 problem = new Day09();
        problem.setInput(Util.readInput(2022, 9, 1));
        assertThat(problem.solve1(), is (13));
    }

    @Test
    void day09Test2() {
        Day09 problem = new Day09();
        problem.setInput(Util.readInput(2022, 9, 2));
        assertThat(problem.solve2(), is (36));
    }
}
