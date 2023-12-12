package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day10Test {

    @ParameterizedTest
    @CsvSource({
            "1, 4",
            "2, 8",
    })
    void day10Test1(int testcase, int result) {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, testcase));
        assertThat(problem.solve1(), is(result));
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4",
            "4, 8",
            "5, 10"
    })
    void day10Test3(int testcase, int result) {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2023, 10, testcase));
        assertThat(problem.solve2(), is(result));
    }
}
