package nl.erends.advent.year2018;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day05Test {

    @Test
    void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput("dabAcCaCBAcCcaDA");
        assertThat(problem.solve1(), is(10));
        assertThat(problem.solve2(), is(4));
    }    
}