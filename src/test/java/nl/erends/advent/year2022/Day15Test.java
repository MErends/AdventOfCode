package nl.erends.advent.year2022;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day15Test {

    @Test
    void day15Test() {
        Day15 problem = new Day15();
        problem.setTest();
        problem.setInput(Util.readInput(2022, 15, 1));
        assertThat(problem.solve1(), is (26));
        assertThat(problem.solve2(), is (56000011L));
    }
}
