package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day12 extends AbstractProblem<List<String>, Integer> {
    
    static void main() {
        new Day12().setAndSolve(Util.readInput(2020, 12));
    }
    
    @Override
    public Integer solve1() {
        int x = 0;
        int y = 0;
        Direction facing = Direction.RIGHT;
        for (String instruction : input) {
            char move = instruction.charAt(0);
            int amount = Integer.parseInt(instruction.substring(1));
            switch (move) {
                case 'N':
                    y -= amount;
                    break;
                case 'E':
                    x += amount;
                    break;
                case 'S':
                    y += amount;
                    break;
                case 'W':
                    x -= amount;
                    break;
                case 'R':
                    while (amount != 0) {
                        facing = facing.turnRight();
                        amount -= 90;
                    }
                    break;
                case 'L':
                    while (amount != 0) {
                        facing = facing.turnLeft();
                        amount -= 90;
                    }
                    break;
                default: // F
                    switch (facing) {
                        case UP -> y -= amount;
                        case DOWN -> y += amount;
                        case LEFT -> x -= amount;
                        case RIGHT -> x += amount;
                    }
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public Integer solve2() {
        int x = 0;
        int y = 0;
        int goEast = 10;
        int goNorth = 1;
        int goWest = 0; // PET SHOP BOYS
        int goSouth = 0;
        int temp;
        for (String instruction : input) {
            char move = instruction.charAt(0);
            int amount = Integer.parseInt(instruction.substring(1));
            switch (move) {
                case 'N':
                    goNorth += amount;
                    break;
                case 'E':
                    goEast += amount;
                    break;
                case 'S':
                    goSouth += amount;
                    break;
                case 'W':
                    goWest += amount;
                    break;
                case 'L':
                    while (amount != 0) {
                        temp = goNorth;
                        goNorth = goEast;
                        goEast = goSouth;
                        goSouth = goWest;
                        goWest = temp;
                        amount -= 90;
                    }
                    break;
                case 'R':
                    while (amount != 0) {
                        temp = goNorth;
                        goNorth = goWest;
                        goWest = goSouth;
                        goSouth = goEast;
                        goEast = temp;
                        amount -= 90;
                    }
                    break;
                default: // F
                    x += goEast * amount;
                    x -= goWest * amount;
                    y += goSouth * amount;
                    y -= goNorth * amount;
                    break;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }
}
