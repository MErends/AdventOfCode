package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends AbstractProblem<String, Integer> {

    static void main() {
        new Day01().setAndSolve(Util.readLine(2016, 1));
    }

    @Override
    public Integer solve1() {
        List<String> visited = new ArrayList<>();
        visited.add("0_0");
        int x = 0;
        int y = 0;
        Direction orientation = Direction.DOWN;
        String[] directions = input.split(", ");
        for (String direction : directions) {
            if (direction.charAt(0) == 'L') {
                orientation = orientation.turnLeft();
            } else {
                orientation = orientation.turnRight();
            }
            int steps = Integer.parseInt(direction.substring(1));
            while (steps != 0) {
                switch (orientation) {
                    case RIGHT:
                        x++;
                        steps--;
                        checkVisited(visited, x, y);
                        break;
                    case DOWN:
                        y++;
                        steps--;
                        checkVisited(visited, x, y);
                        break;
                    case LEFT:
                        x--;
                        steps--;
                        checkVisited(visited, x, y);
                        break;
                    case UP:
                        y--;
                        steps--;
                        checkVisited(visited, x, y);
                        break;
                }
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    private void checkVisited(List<String> visited, int x, int y) {
        String position = x + "_" + y;
        if (visited.contains(position) && answer2 == null) {
            answer2 = Math.abs(x) + Math.abs(y);
        } else {
            visited.add(position);
        }
    }
}
