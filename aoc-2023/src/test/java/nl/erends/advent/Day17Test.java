package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day17Test {

    @Test
    void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput(Util.readInput(2023, 17, 1));
        assertThat(problem.solve1(), is(102));
        assertThat(problem.solve2(), is(94));
    }

    @Test
    void day17Test2() {
        Day17 problem = new Day17();
        problem.setInput(Util.readInput(2023, 17, 2));
        assertThat(problem.solve2(), is(71));
    }
}
