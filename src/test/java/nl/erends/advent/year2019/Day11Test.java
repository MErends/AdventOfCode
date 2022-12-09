package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day11Test {

    @Test
    void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2019, 11, 1));
        assertThat(problem.solve1(), is("2238"));
        String expected = """
                 ###  #  # #### ###   ##  #### ###  ###   \s
                 #  # # #  #    #  # #  #    # #  # #  #  \s
                 #  # ##   ###  #  # #  #   #  #  # #  #  \s
                 ###  # #  #    ###  ####  #   ###  ###   \s
                 #    # #  #    #    #  # #    # #  #     \s
                 #    #  # #    #    #  # #### #  # #     \s
                """;
        assertThat(problem.solve2(), is(expected));
    }
}
