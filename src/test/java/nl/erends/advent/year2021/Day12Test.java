package nl.erends.advent.year2021;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day12Test {

    @Test
    void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2021, 12, 1));
        assertThat(problem.solve1(), is(10));
        assertThat(problem.solve2(), is(36));
    }

    @Test
    void day12Test2() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2021, 12, 2));
        assertThat(problem.solve1(), is(19));
        assertThat(problem.solve2(), is(103));
    }

    @Test
    void day12Test3() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2021, 12, 3));
        assertThat(problem.solve1(), is(226));
        assertThat(problem.solve2(), is(3509));
    }
}
