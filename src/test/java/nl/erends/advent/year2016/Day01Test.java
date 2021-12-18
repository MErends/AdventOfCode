package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day01Test {

    @ParameterizedTest
    @CsvSource({
            "1, 5",
            "2, 2",
            "3, 12"
    })
    void day01Test1(int testcase, int result) {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, testcase));
        assertThat(problem.solve1(), is(result));
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2016, 1, 4));
        assertThat(problem.solve2(), is(4));
    }


}
