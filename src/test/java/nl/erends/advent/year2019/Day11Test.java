package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {

    @Test
    public void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(Util.readLine(2019, 11, 1));
        assertEquals("2238", problem.solve1());
        String expected = " ###  #  # #### ###   ##  #### ###  ###    \n" +
                          " #  # # #  #    #  # #  #    # #  # #  #   \n" +
                          " #  # ##   ###  #  # #  #   #  #  # #  #   \n" +
                          " ###  # #  #    ###  ####  #   ###  ###    \n" +
                          " #    # #  #    #    #  # #    # #  #      \n" +
                          " #    #  # #    #    #  # #### #  # #      \n";
        assertEquals(expected, problem.solve2());
    }
}