package nl.erends.advent.year2016;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day09Test {
    
    @ParameterizedTest
    @CsvSource({
            "ADVENT, 6",
            "A(1x5)BC, 7",
            "A(2x2)BCD(2x2)EFG, 11",
            "(6x1)(1x3)A, 6"
    })
    void day09Test1(String input, int result) {
        Day09 problem = new Day09();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result));
    }

    @ParameterizedTest
    @CsvSource({
            "(3x3)XYZ, 9, 9",
            "X(8x2)(3x3)ABCY, 18, 20",
    })
    void day09Test2(String input, int result1, long result2) {
        Day09 problem = new Day09();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result1));
        assertThat(problem.solve2(), is(result2));
    }

    @ParameterizedTest
    @CsvSource({
            "(27x12)(20x12)(13x14)(7x10)(1x12)A, 241920",
            "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN, 445"
    })
    void day09Test3(String input, long result) {
        Day09 problem = new Day09();
        problem.setInput(input);
        assertThat(problem.solve2(), is(result));
    }
}
