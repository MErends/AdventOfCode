package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
            R2, L3 | 5
            R2, R2, R2 | 2
            R5, L5, R5, R3 | 12
        """)
    void day01Test1(String input, int result) {
        Day01 problem = new Day01();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result));
    }

    @Test
    void day01Test2() {
        Day01 problem = new Day01();
        problem.setInput("R8, R4, R4, R8");
        assertThat(problem.solve2(), is(4));
    }


}
