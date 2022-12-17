package nl.erends.advent.year2022;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day17Test {

    @Test
    void day17Test() {
        Day17 problem = new Day17();
        problem.setInput(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>");
        assertThat(problem.solve1(), is (3068L));
        assertThat(problem.solve2(), is (1514285714288L));
    }
}
