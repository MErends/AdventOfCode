package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day14 extends AbstractProblem<List<String>, Integer> {
    
    private int maxSeconds = 2503;

    public static void main(String[] args) {
        new Day14().setAndSolve(Util.readInput(2015, 14));

    }

    @Override
    public Integer solve1() {
        List<Reindeer> reindeers = new ArrayList<>();
        for (String line : input) {
            reindeers.add(new Reindeer(line));
        }
        for (int second = 0; second < maxSeconds; second++) {
            reindeers.forEach(Reindeer::update);
            final int[] maxDistance = {0};
            reindeers.forEach(reindeer -> maxDistance[0] = Math.max(maxDistance[0], reindeer.distance));
            reindeers.forEach(reindeer -> {
                if (reindeer.distance == maxDistance[0]) {
                    reindeer.points++;
                }
            });
        }
        int distance = reindeers.stream().mapToInt(r -> r.distance).max().orElseThrow(IllegalStateException::new);
        answer2 = reindeers.stream().mapToInt(r -> r.points).max().orElseThrow(IllegalStateException::new);
        return distance;
    }

    public void setMaxSeconds(int maxSeconds) {
        this.maxSeconds = maxSeconds;
    }

    private static class Reindeer {
        private final int speed;
        private final int duration;
        private final int downtime;

        private int downtimeRemaining;
        private int durationRemaining;
        private int distance;
        private int points;
        
        Reindeer(String line) {
            String[] words = line.split(" ");
            this.speed = Integer.parseInt(words[3]);
            this.duration = Integer.parseInt(words[6]);
            this.downtime = Integer.parseInt(words[13]);
            this.durationRemaining = duration;
            this.downtimeRemaining = 0;
            this.distance = 0;
            this.points = 0;
        }

        void update() {
            if (durationRemaining != 0) {
                durationRemaining--;
                distance += speed;
                if (durationRemaining == 0) {
                    downtimeRemaining = downtime;
                }
            } else {
                downtimeRemaining--;
                if (downtimeRemaining == 0) {
                    durationRemaining = duration;
                }
            }
        }
    }
}
