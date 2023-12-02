package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {

    @Test
    void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readInput(2023, 1, 1));
        assertThat(problem.solve1(), is (142));
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readInput(2023, 1, 2));
        assertThat(problem.solve2(), is (281));
    }
}
