package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.IntStream;

public class Day09 extends AbstractProblem<String, Long> {

    void main() {
        new Day09().setAndSolve("446 players; last marble is worth 71522 points");
    }
    
    @Override
    public Long solve1() {
        String[] words = input.split(" ");
        int playerCount = Integer.parseInt(words[0]);
        int maxMarble = Integer.parseInt(words[6]);
        if (part2) {
            maxMarble *= 100;
        }
        List<Integer> circle = new ArrayList<>();
        circle.add(0);
        int currentMarbleIndex = 0;
        List<Player> playerList = IntStream.rangeClosed(1, playerCount)
                .mapToObj(_ -> new Player()).toList();
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
        return optionalLong.orElseThrow(IllegalStateException::new);
    }
    
    private static class Player {
        private long score;
    }
}
