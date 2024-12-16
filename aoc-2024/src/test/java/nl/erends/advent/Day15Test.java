package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day15Test {

    @Test
    void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2024, 15, 1));
        assertThat(problem.solve1(), is (2028));
    }

    @Test
    void day15Test2() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2024, 15, 2));
        assertThat(problem.solve1(), is (10092));
        assertThat(problem.solve2(), is (9021));
    }
}
