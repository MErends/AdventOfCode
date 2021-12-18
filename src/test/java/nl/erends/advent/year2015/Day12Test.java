package nl.erends.advent.year2015;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day12Test {
    
    @ParameterizedTest
    @CsvSource({
            "'[1,2,3]', 6",
            "'{\"a\":2,\"b\":4}', 6",
            "[[[3]]], 3",
            "'{\"a\":{\"b\":4},\"c\":-1}', 3",
            "'{\"a\":[-1,1]}', 0",
            "'[-1,{\"a\":1}]', 0",
            "[], 0",
            "{}, 0"
    })
    void day12Test(String input, int result) {
        Day12 problem = new Day12();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result));
    }
}
