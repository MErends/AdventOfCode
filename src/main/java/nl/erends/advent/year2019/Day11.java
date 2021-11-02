package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.Map;

public class Day11 extends AbstractProblem<String, String> {
    
    private Map<String, Integer> tiles = new HashMap<>();
    private int x = 0;
    private int y = 0;
    private Direction direction = Direction.UP;
    private int xMin = Integer.MAX_VALUE;
    private int yMin = Integer.MAX_VALUE;
    private int xMax = Integer.MIN_VALUE;
    private int yMax = Integer.MIN_VALUE;

    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readLine(2019, 11));
    }

    @Override
    public String solve1() {
        Intcode computer = new Intcode(input);
        while (true) {
            int currentPaint = tiles.getOrDefault(x + "," + y, 0);
            computer.addInput(currentPaint);
            computer.execute();
            if (computer.isHalted()) {
                break;
            }
            int paint = (int) computer.getOutput().longValue();
            tiles.put(x + "," + y, paint);
            computer.execute();
            boolean rotateRight = computer.getOutput() == 1;
            updateDirection(rotateRight);
            xMin = Math.min(x, xMin);
            xMax = Math.max(x, xMax);
            yMin = Math.min(y, yMin);
            yMax = Math.max(y, yMax);
        }
        return Integer.toString(tiles.size());
    }

    private void updateDirection(boolean rotateRight) {
        switch (direction) {
            case LEFT:
                direction = rotateRight ? Direction.UP : Direction.DOWN;
                break;
            case UP:
                direction = rotateRight ? Direction.RIGHT : Direction.LEFT;
                break;
            case RIGHT:
                direction = rotateRight ? Direction.DOWN : Direction.UP;
                break;
            case DOWN:
                direction = rotateRight ? Direction.LEFT : Direction.RIGHT;
                break;
        }
        switch (direction) {
            case LEFT:
                x--;
                break;
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
        }
    }

    @Override
    public String solve2() {
        xMin = Integer.MAX_VALUE;
        yMin = Integer.MAX_VALUE;
        xMax = Integer.MIN_VALUE;
        yMax = Integer.MIN_VALUE;
        x = 0;
        y = 0;
        tiles = new HashMap<>();
        tiles.put("0,0", 1);
        solve1();
        StringBuilder output = new StringBuilder();
        for (x = xMax; x >= xMin; x--) {
            for (y = yMin; y <= yMax; y++) {
                int paint = tiles.getOrDefault(x + "," + y, 0);
                output.append(paint == 1 ? '#' : ' ');
            }
            output.append('\n');
        }
        return output.toString();
    }
}
