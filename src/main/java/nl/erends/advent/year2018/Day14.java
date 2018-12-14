package nl.erends.advent.year2018;

import java.util.ArrayList;
import java.util.List;

public class Day14 {


    public static void main(String[] args) {
        int skipRecepies = 633601;
        long start = System.currentTimeMillis();
        List<Integer> scoreboard = new ArrayList<>();
        scoreboard.add(3);
        scoreboard.add(7);
        int firstElfIndex = 0;
        int secondElfIndex = 1;
        while (scoreboard.size() < skipRecepies + 10) {
            int firstElfRecipe = scoreboard.get(firstElfIndex);
            int secondElfRecipe = scoreboard.get(secondElfIndex);
            int sum =  firstElfRecipe + secondElfRecipe;
            if (sum >= 10) {
                scoreboard.add(sum / 10);
            }
            scoreboard.add(sum % 10);
            firstElfIndex = (firstElfIndex + 1 + firstElfRecipe) % scoreboard.size();
            secondElfIndex = (secondElfIndex + 1 + secondElfRecipe) % scoreboard.size();
        }
        System.out.println(scoreboard.subList(skipRecepies, skipRecepies + 10).stream().reduce("", (s, i) -> s + i, String::concat));
        long mid = System.currentTimeMillis();
        String targetRecipe = Integer.toString(skipRecepies);
        int targetSize = targetRecipe.length();
        StringBuilder lastSix = new StringBuilder("37");
        scoreboard = new ArrayList<>();
        scoreboard.add(3);
        scoreboard.add(7);
        firstElfIndex = 0;
        secondElfIndex = 1;
        while (true) {
            int firstElfRecipe = scoreboard.get(firstElfIndex);
            int secondElfRecipe = scoreboard.get(secondElfIndex);
            int sum =  firstElfRecipe + secondElfRecipe;
            if (sum >= 10) {
                scoreboard.add(sum / 10);
                lastSix.append(sum / 10);
                if (lastSix.length() > targetSize) lastSix.deleteCharAt(0);
                if (targetRecipe.equals(lastSix.toString())) {
                    System.out.println(scoreboard.size() - targetSize);
                    break;
                }
            }
            scoreboard.add(sum % 10);
            lastSix.append(sum % 10);
            if (lastSix.length() > targetSize) lastSix.deleteCharAt(0);
            if (targetRecipe.equals(lastSix.toString())) {
                System.out.println(scoreboard.size() - targetSize);
                break;
            }
            firstElfIndex = (firstElfIndex + 1 + firstElfRecipe) % scoreboard.size();
            secondElfIndex = (secondElfIndex + 1 + secondElfRecipe) % scoreboard.size();
        }
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
}
