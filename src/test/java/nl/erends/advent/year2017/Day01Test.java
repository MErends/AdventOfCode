package nl.erends.advent.year2017;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day01Test {
    
    @ParameterizedTest
    @CsvSource({
            "1122, 3",
            "1111, 4",
            "1234, 0",
            "91212129, 9"
    })
    void day01Test1(String input, int result) {
        Day01 problem = new Day01();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result));
    }

    @ParameterizedTest
    @CsvSource({
            "1212, 6",
            "1221, 0",
            "123425, 4",
            "123123, 12",
            "12131415, 4"
    })
    void day01Test2(String input, int result) {
        Day01 problem = new Day01();
        problem.setInput(input);
        assertThat(problem.solve2(), is(result));
    }
}