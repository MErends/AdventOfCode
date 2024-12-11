package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>--- Day 11: Plutonian Pebbles ---</h1>
 * <p>The ancient civilization on Pluto was known for its ability to manipulate
 * spacetime, and while The Historians explore their infinite corridors, you've
 * noticed a strange set of physics-defying stones. At first glance, they seem
 * like normal stones: they're arranged in a perfectly straight line, and each
 * stone has a number engraved on it. The strange part is that every time you
 * blink, the stones change. How many stones will you have after blinking 25
 * times?</p>
 * <p><a href="https://adventofcode.com/2024/day/11">2024 Day 11</a></p>
 */
public class Day11 extends AbstractProblem<String, Long> {

    Map<Integer, Map<Long, Long>> blinksLeftStone = new HashMap<>();

    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readLine(2024, 11));
    }

    @Override
    protected Long solve1() {
        List<Integer> stones = Arrays.stream(input.split(" "))
                .map(Integer::parseInt).toList();
        long count = 0;
        for (int stone : stones) {
            count += getLengthAfterBlinks(stone, part2 ? 75 : 25);
        }
        return count;
    }

    long getLengthAfterBlinks(long stone, int blinksLeft) {
        if (blinksLeftStone.containsKey(blinksLeft) && blinksLeftStone.get(blinksLeft).containsKey(stone)) {
            return blinksLeftStone.get(blinksLeft).get(stone);
        }
        if (blinksLeft == 0) {
            return 1;
        }
        long length;
        if (stone == 0) {
            length = getLengthAfterBlinks(1, blinksLeft - 1);
        } else {
            String stoneString = Long.toString(stone);
            if (stoneString.length() % 2 == 0) {
                String stoneOne = stoneString.substring(0, stoneString.length() / 2);
                String stoneTwo = stoneString.substring(stoneString.length() / 2);
                length = getLengthAfterBlinks(Long.parseLong(stoneOne), blinksLeft - 1)
                        + getLengthAfterBlinks(Long.parseLong(stoneTwo), blinksLeft - 1);
            } else {
                length = getLengthAfterBlinks(stone * 2024, blinksLeft - 1);
            }
        }
        blinksLeftStone.computeIfAbsent(blinksLeft, i -> new HashMap<>()).put(stone, length);
        return length;
    }
}
