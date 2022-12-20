package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day17Test {

    @Test
    void day17Test() {
        Day17 problem = new Day17();
        problem.setInput("target area: x=20..30, y=-10..-5");
        assertThat(problem.solve1(), is(45));
        assertThat(problem.solve2(), is(112));
    }
}
