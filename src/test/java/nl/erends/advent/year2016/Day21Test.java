package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day21Test {
    
    @Test
    void day21Test1() {
        Day21 problem = new Day21();
        problem.setInput(Util.readInput(2016, 21, 1));
        problem.setInput1("abcde");
        problem.setInput2("decab");
        assertThat(problem.solve1(), is("decab"));
        assertThat(problem.solve2(), is("abcde"));
    }
}