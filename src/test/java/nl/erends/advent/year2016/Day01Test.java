package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day01Test {

    @Test
    void day01Test1() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 1));
        assertThat(problem.solve1(), is(5));
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 2));
        assertThat(problem.solve1(), is(2));
    }

    @Test
    void day01Test3() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 3));
        assertThat(problem.solve1(), is(12));
    }

    @Test
    void day01Test4() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 4));
        assertThat(problem.solve2(), is(4));
    }

}
