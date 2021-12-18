package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {
    
    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "2, 0",
            "3, 3",
            "4, 3",
            "5, 3",
            "6, -1",
            "7, -1",
            "8, -3",
            "9, -3"
    })
    void day01Test1(int testcase, int result) {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, testcase));
        assertThat(problem.solve1(), is(result));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 1",
            "11, 5"
    })
    void day01Test2(int testcase, int result) {
        Day01 problem = new Day01();
        problem.setInput(Util.readLine(2015, 1, testcase));
        assertThat(problem.solve2(), is(result));
    }
}