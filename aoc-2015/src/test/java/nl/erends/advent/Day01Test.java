package nl.erends.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {
    
    @ParameterizedTest
    @CsvSource({
            "(()), 0",
            "()(), 0",
            "(((, 3",
            "(()(()(, 3",
            "))(((((, 3",
            "()), -1",
            "))(, -1",
            "))), -3",
            ")())()), -3"
    })
    void day01Test1(String input, int result) {
        Day01 problem = new Day01();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result));
    }

    @ParameterizedTest
    @CsvSource({
            "), 1",
            "()()), 5"
    })
    void day01Test2(String input, int result) {
        Day01 problem = new Day01();
        problem.setInput(input);
        assertThat(problem.solve2(), is(result));
    }
}
