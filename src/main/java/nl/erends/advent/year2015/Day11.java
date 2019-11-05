package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day11 extends AbstractProblem<String, String> {

    public static void main(String[] args) {
        new Day11().setAndSolve(Util.readLine(2015, 11));
    }

    @Override
    public String solve1() {
        while (isInvalidPassword(input)) {
            input = nextPassword(input);
        }
        return input;
    }

    @Override
    public String solve2() {
        input = solve1();
        input = nextPassword(input);
        while (isInvalidPassword(input)) {
            input = nextPassword(input);
        }
        return input;
    }

    private String nextPassword(String inputString) {
        StringBuilder password = new StringBuilder(inputString);
        boolean overhouden = true;
        int ophogen = password.length() - 1;
        while (overhouden) {
            char letter = password.charAt(ophogen);
            if (letter == 'z') {
                password.replace(ophogen, ophogen + 1, "a");
                ophogen--;
            } else {
                letter++;
                password.replace(ophogen, ophogen + 1, Character.toString(letter));
                overhouden = false;
            }
        }
        return password.toString();
    }

    private boolean isInvalidPassword(String password) {
        if (password.contains("i") || password.contains("o") || password.contains("l")) {
            return true;
        }

        boolean increment = false;
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) - 1 && password.charAt(i) == password.charAt(i + 2) - 2) {
                increment = true;
                break;
            }
        }
        if (!increment) {
            return true;
        }
        int pairs = 0;
        int pointer = 0;
        while (pointer < password.length() - 1) {
            if (password.charAt(pointer) == password.charAt(pointer + 1)) {
                pairs++;
                if (pointer + 2 < password.length() && password.charAt(pointer) == password.charAt(pointer + 2)) {
                    pointer++;
                }
            }
            pointer++;
        }
        return pairs < 2;
    }
}

