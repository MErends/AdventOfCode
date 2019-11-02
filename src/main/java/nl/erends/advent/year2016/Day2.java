package nl.erends.advent.year2016;


import nl.erends.advent.util.Util;

import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2016day2.txt");
        System.out.println(solve1(input));
        System.out.println(solve2(input));
    }

    private static String solve1(List<String> input) {
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

    private static int newLocation(int oldLocation, char direction) {
        switch (direction) {
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
        return -1;
    }


    private static String solve2(List<String> input) {
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


    private static char newLocation2(char oldLocation, char direction) {
        switch (direction) {
            case 'U':
                switch (oldLocation) {
                    case 'D': return 'B';
                    case 'C': return '8';
                    case 'B': return '7';
                    case 'A': return '6';
                    case '8': return '4';
                    case '7': return '3';
                    case '6': return '2';
                    case '3': return '1';
                    default: return oldLocation;
                }
            case 'D':
                switch (oldLocation) {
                    case 'B': return 'D';
                    case '8': return 'C';
                    case '7': return 'B';
                    case '6': return 'A';
                    case '4': return '8';
                    case '3': return '7';
                    case '2': return '6';
                    case '1': return '3';
                    default: return oldLocation;
                }
            case 'L':
                switch (oldLocation) {
                    case '9': return '8';
                    case '4': return '3';
                    case '8': return '7';
                    case 'C': return 'B';
                    case '3': return '2';
                    case '7': return '6';
                    case 'B': return 'A';
                    case '6': return '5';
                    default: return oldLocation;
                }
            case 'R':
                switch (oldLocation) {
                    case '8': return '9';
                    case '3': return '4';
                    case '7': return '8';
                    case 'B': return 'C';
                    case '2': return '3';
                    case '6': return '7';
                    case 'A': return 'B';
                    case '5': return '6';
                    default: return oldLocation;
                }
        }
        return '\0';
    }
}
