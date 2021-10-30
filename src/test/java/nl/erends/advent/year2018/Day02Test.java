package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day02Test {

    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2018, 2, 1));
        assertThat(problem.solve1(), is("12"));
    }

    @Test
    void day02Test2() {
        Day02 problem = new Day02();
        problem.setInput(Util.readInput(2018, 2, 2));
        assertThat(problem.solve2(), is("fgij"));
    }    
}