package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * <h1>--- Day 2: Cube Conundrum ---</h1>
 * <p>On your way to the weather machine, you play some sort of cube game with
 * the elves. They draw a random selection of colored cubes and it is your job
 * to do some calculations on the allowed number of cubes, and minimal number of
 * cubes needed.</p>
 * <p><a href="https://adventofcode.com/2023/day/2">2023 Day 2</a></p>
 */
public class Day02 extends AbstractProblem<List<String>,  Number> {

    static void main() {
        new Day02().setAndSolve(Util.readInput(2023, 2));
    }

    @Override
    protected Number solve1() {
        int sumPossibleGames = 0;
        int totalPower = 0;
        final int RED_LIMIT = 12;
        final int GREEN_LIMIT = 13;
        final int BLUE_LIMIT = 14;

        for (String line : input) {
            boolean gamePossible = true;
            int redPower = 0;
            int greenPower = 0;
            int bluePower = 0;
            String[] gameSplit = line.split(":");
            int gameId = Integer.parseInt(gameSplit[0].substring(5));
            String[] grabSplit = gameSplit[1].split(";");
            for (String grab : grabSplit) {
                int redCount = 0;
                int greenCount = 0;
                int blueCount = 0;
                String[] colorSplit = grab.split(",");
                for (String color : colorSplit) {
                    color = color.trim();
                    String[] countSplit = color.split(" ");
                    if (countSplit[1].startsWith("red")) {
                        redCount += Integer.parseInt(countSplit[0]);
                    } else if (countSplit[1].startsWith("green")) {
                        greenCount += Integer.parseInt(countSplit[0]);
                    } else {
                        blueCount += Integer.parseInt(countSplit[0]);
                    }
                }
                if (redCount > RED_LIMIT || greenCount > GREEN_LIMIT || blueCount > BLUE_LIMIT) {
                    gamePossible = false;
                }
                redPower = Math.max(redPower, redCount);
                greenPower = Math.max(greenPower, greenCount);
                bluePower = Math.max(bluePower, blueCount);
            }
            if (gamePossible) {
                sumPossibleGames += gameId;
            }
            totalPower += (redPower * greenPower * bluePower);
        }
        answer2 = totalPower;
        return sumPossibleGames;
    }
}
