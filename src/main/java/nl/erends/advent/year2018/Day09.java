package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day09 {

    public static void main(String... args) {
        String part = args.length == 0 ? "1" : args[0]; 
        List<String> input = Arrays.asList(Util.getFileAsList("2018day09.txt").get(0).split(" "));
        long start = System.currentTimeMillis();
        int playerCount = Integer.parseInt(input.get(0));
        int maxMarble = Integer.parseInt(input.get(6));
        if (part.equals("2")) maxMarble *= 100;
        List<Integer> circle = new ArrayList<>();
        circle.add(0);
        int currentMarbleIndex = 0;
        List<Player> playerList = IntStream.rangeClosed(1, playerCount)
                .mapToObj(i -> new Player()).collect(Collectors.toList());
        int currentPlayerIndex = 0;
        for (int marble = 1; marble <= maxMarble; marble++) {
            if (marble % 23 == 0) {
                playerList.get(currentPlayerIndex).score += marble;
                int targetIndex = currentMarbleIndex - 7;
                if (targetIndex < 0) targetIndex += circle.size();
                playerList.get(currentPlayerIndex).score += circle.remove(targetIndex);
                currentMarbleIndex = targetIndex;
            } else {
                int targetIndex = (currentMarbleIndex + 2) % circle.size();
                if (targetIndex == 0) targetIndex = circle.size();
                circle.add(targetIndex, marble);
                currentMarbleIndex = targetIndex;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % playerList.size();
        }
        OptionalLong optionalLong = playerList.stream().mapToLong(p -> p.score).max();
        System.out.println(optionalLong.orElse(-1));
        long end = System.currentTimeMillis();
        System.out.println("Part "+ part + ": " + (end - start) + " millis.");
        if (part.equals("1")) {
            main("2");
        }
    }
    
    private static class Player {
        private long score;
    }
}
