package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;

/**
 * --- Day 6: Lanternfish ---
 * <p>A massive school of glowing lanternfish swims past. They must spawn
 * quickly to reach such large numbers. Maybe exponentially quickly? You should
 * model their growth rate to be sure. How many lanternfish would there be after
 * 80 days and after 256 days?
 * <p><a href="https://adventofcode.com/2021/day/6">2021 Day 6</a>
 */
public class Day06 extends AbstractProblem<String, Long> {
    
    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readLine(2021, 6));
    }

    @Override
    protected Long solve1() {
        long[] lanterns = new long[9];
        long answer1 = 0;
        Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .forEach(i -> ++lanterns[i]);
        
        for (int days = 0; days < 256; days++) {
            if (days == 80) {
                answer1 = Arrays.stream(lanterns).sum();
            }
            long ripeLanterns = lanterns[0];
            System.arraycopy(lanterns, 1, lanterns, 0, 8);
            lanterns[6] += ripeLanterns;
            lanterns[8] = ripeLanterns;
        }
        answer2 = Arrays.stream(lanterns).sum();
        return answer1;
    }
}
