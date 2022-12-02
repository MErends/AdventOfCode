package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day02 extends AbstractProblem<List<String>, String> {

    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readInput(2016, 2));

    }

    @Override
    public String solve1() {
        StringBuilder output = new StringBuilder();
        int location = 5;
        for (String line : input) {
            char[] directions = line.toCharArray();
            for (char direction : directions) {
                location = newLocation(location, direction);
            }
            output.append(location);
        }
        return output.toString();
    }

    private int newLocation(int oldLocation, char direction) {
        switch (direction) {
            default:
            case 'U':
                if (oldLocation <= 3) {
                    return oldLocation;
                } else {
                    return oldLocation - 3;
                }
            case 'D':
                if (oldLocation >= 7) {
                    return oldLocation;
                } else {
                    return oldLocation + 3;
                }
            case 'L':
                if ((oldLocation - 1) % 3 == 0) {
                    return oldLocation;
                } else {
                    return oldLocation - 1;
                }
            case 'R':
                if (oldLocation % 3 == 0) {
                    return oldLocation;
                } else {
                    return oldLocation + 1;
                }
        }
    }

    @Override
    public String solve2() {
        StringBuilder output = new StringBuilder();
        char location = '5';
        for (String line : input) {
            char[] directions = line.toCharArray();
            for (char direction : directions) {
                location = newLocation2(location, direction);
            }
            output.append(location);
        }
        return output.toString();
    }


    private char newLocation2(char oldLocation, char direction) {
        return switch (direction) {
            case 'U' -> switch (oldLocation) {
                case 'D' -> 'B';
                case 'C' -> '8';
                case 'B' -> '7';
                case 'A' -> '6';
                case '8' -> '4';
                case '7' -> '3';
                case '6' -> '2';
                case '3' -> '1';
                default -> oldLocation;
            };
            case 'D' -> switch (oldLocation) {
                case 'B' -> 'D';
                case '8' -> 'C';
                case '7' -> 'B';
                case '6' -> 'A';
                case '4' -> '8';
                case '3' -> '7';
                case '2' -> '6';
                case '1' -> '3';
                default -> oldLocation;
            };
            case 'L' -> switch (oldLocation) {
                case '9' -> '8';
                case '4' -> '3';
                case '8' -> '7';
                case 'C' -> 'B';
                case '3' -> '2';
                case '7' -> '6';
                case 'B' -> 'A';
                case '6' -> '5';
                default -> oldLocation;
            };
            case 'R' -> switch (oldLocation) {
                case '8' -> '9';
                case '3' -> '4';
                case '7' -> '8';
                case 'B' -> 'C';
                case '2' -> '3';
                case '6' -> '7';
                case 'A' -> 'B';
                case '5' -> '6';
                default -> oldLocation;
            };
            default -> throw new IllegalArgumentException("" + direction);
        };
    }
}
