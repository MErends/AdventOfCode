package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day02 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readInput(2020, 2));
    }

    public Integer solve1() {
        int validPasswordCount = 0;
        answer2 = 0;
        for (String line : input) {
            String[] words = line.split(":");
            String policy = words[0];
            String password = words[1].trim();
            char wantedChar = policy.charAt(policy.length() - 1);
            policy = policy.substring(0, policy.length() - 2);
            String[] policySplit = policy.split("-");
            int lowerBound = Integer.parseInt(policySplit[0]);
            int upperBound = Integer.parseInt(policySplit[1]);
            int charCount = 0;
            for (char c : password.toCharArray()) {
                if (c == wantedChar) {
                    charCount++;
                }
            }
            if ((charCount >= lowerBound && charCount <= upperBound)) {
                validPasswordCount++;
            }
            if (password.charAt(--lowerBound) == wantedChar ^ password.charAt(--upperBound) == wantedChar) {
                answer2++;
            }
        }
        return validPasswordCount;
    }
}
