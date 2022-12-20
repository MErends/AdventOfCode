package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day12Test {

    @Test
    void day12Test1() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2019, 12, 1));
        problem.setMaxSteps(10);
        assertThat(problem.solve1(), is(179));
        assertThat(problem.solve2(), is(2772L));
    }

    @Test
    void day12Test2() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2019, 12, 2));
        problem.setMaxSteps(100);
        assertThat(problem.solve1(), is(1940));
        assertThat(problem.solve2(), is(4686774924L));
    }
}
