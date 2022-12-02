package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day08Test {

    @Test
    void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput(Util.readLine(2019, 8, 1));
        assertThat(problem.solve1(), is("2975"));
        
        problem = new Day08();
        problem.setInput(Util.readLine(2019, 8, 1));
        String expected = """
                #### #  # ###  #  # ####\s
                #    #  # #  # #  # #   \s
                ###  #### #  # #  # ### \s
                #    #  # ###  #  # #   \s
                #    #  # # #  #  # #   \s
                #### #  # #  #  ##  ####\s
                """;
        assertThat(problem.solve2(), is(expected));
    }
}
