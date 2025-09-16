package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.ArrayList;
import java.util.List;

public class Day14 extends AbstractProblem<Integer, String> {
    
    static void main() {
        new Day14().setAndSolve(633601);
    }
    
    @Override
    public String solve1() {
        List<Integer> scoreboard = new ArrayList<>();
        scoreboard.add(3);
        scoreboard.add(7);
        int firstElfIndex = 0;
        int secondElfIndex = 1;
        while (scoreboard.size() < input + 10) {
            int firstElfRecipe = scoreboard.get(firstElfIndex);
            int secondElfRecipe = scoreboard.get(secondElfIndex);
            int sum = firstElfRecipe + secondElfRecipe;
            if (sum >= 10) {
                scoreboard.add(sum / 10);
            }
            scoreboard.add(sum % 10);
            firstElfIndex = (firstElfIndex + 1 + firstElfRecipe) % scoreboard.size();
            secondElfIndex = (secondElfIndex + 1 + secondElfRecipe) % scoreboard.size();
        }
        return scoreboard.subList(input, input + 10).stream().reduce("", (s, i) -> s + i, String::concat);
    }
    
    @Override
    public String solve2() {
        String targetRecipe = Integer.toString(input);
        int targetSize = targetRecipe.length();
        StringBuilder lastSix = new StringBuilder("37");
        List<Integer>scoreboard = new ArrayList<>();
        scoreboard.add(3);
        scoreboard.add(7);
        int firstElfIndex = 0;
        int secondElfIndex = 1;
        while (true) {
            int firstElfRecipe = scoreboard.get(firstElfIndex);
            int secondElfRecipe = scoreboard.get(secondElfIndex);
            int sum =  firstElfRecipe + secondElfRecipe;
            if (sum >= 10) {
                scoreboard.add(sum / 10);
                lastSix.append(sum / 10);
                if (lastSix.length() > targetSize) lastSix.deleteCharAt(0);
                if (targetRecipe.contentEquals(lastSix)) {
                    return Integer.toString(scoreboard.size() - targetSize);
                }
            }
            scoreboard.add(sum % 10);
            lastSix.append(sum % 10);
            if (lastSix.length() > targetSize) lastSix.deleteCharAt(0);
            if (targetRecipe.contentEquals(lastSix)) {
                return Integer.toString(scoreboard.size() - targetSize);
            }
            firstElfIndex = (firstElfIndex + 1 + firstElfRecipe) % scoreboard.size();
            secondElfIndex = (secondElfIndex + 1 + secondElfRecipe) % scoreboard.size();
        }
    }
}
