package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 extends AbstractProblem<List<String>, Integer> {

    private int x = 0;
    private int y = 0;

    public static void main(String[] args) {
        new Day03().setAndSolve(Util.readInput(2019, 3));
    }

    @Override
    public Integer solve1() {
        Map<String, Integer> firstWire = new HashMap<>();
        int totalSteps = 0;
        for (String pathElement : input.get(0).split(",")) {
            char direction = pathElement.charAt(0);
            int numSteps = Integer.parseInt(pathElement.substring(1));
            while (numSteps != 0) {
                updateXY(direction);
                totalSteps++;
                firstWire.putIfAbsent(x + "," + y, totalSteps);
                numSteps--;
            }
        }
        x = 0;
        y = 0;
        totalSteps = 0;
        int answer1 = Integer.MAX_VALUE;
        answer2 = Integer.MAX_VALUE;
        for (String pathElement : input.get(1).split(",")) {
            char direction = pathElement.charAt(0);
            int numSteps = Integer.parseInt(pathElement.substring(1));
            while (numSteps != 0) {
                updateXY(direction);
                totalSteps++;
                String coords = x + "," + y;
                if (firstWire.containsKey(coords)) {
                    answer1 = Math.min(answer1, Math.abs(x) + Math.abs(y));
                    answer2 = Math.min(answer2, totalSteps + firstWire.get(coords));
                }
                numSteps--;
            }
        }
        return answer1;
    }

    private void updateXY(char direction) {
        switch (direction) {
            case 'U' -> y++;
            case 'D' -> y--;
            case 'L' -> x--;
            case 'R' -> x++;
            default -> throw new IllegalArgumentException("" + direction);
        }
    }
}
