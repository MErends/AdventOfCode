package nl.erends.advent.year2015;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    public static void main(String[] args) {

        Reindeer comet = new Reindeer(13, 7, 82);
        Reindeer dancer = new Reindeer(3, 16, 37);
        Reindeer vixen = new Reindeer(19, 7, 124);
        Reindeer rudolph = new Reindeer(3, 15, 28);
        Reindeer donner = new Reindeer(19, 9, 164);
        Reindeer blitzen = new Reindeer(19, 9, 158);
        Reindeer cupid = new Reindeer(25, 6, 145);
        Reindeer dasher = new Reindeer(14, 3, 38);
        Reindeer prancer = new Reindeer(25, 6, 143);

        List<Reindeer> reindeers = new ArrayList<>();
        reindeers.add(comet);
        reindeers.add(dancer);
        reindeers.add(vixen);
        reindeers.add(rudolph);
        reindeers.add(donner);
        reindeers.add(blitzen);
        reindeers.add(cupid);
        reindeers.add(dasher);
        reindeers.add(prancer);
        for (int second = 0; second < 2503; second++) {
            reindeers.forEach(Reindeer::update);
            final int[] maxDistance = {0};
            reindeers.forEach(reindeer -> maxDistance[0] = Math.max(maxDistance[0], reindeer.getDistance()));
            reindeers.forEach(reindeer -> {if (reindeer.getDistance() == maxDistance[0]) reindeer.givePoint();});
        }

        reindeers.forEach(r -> System.out.println(r.getPoints()));
//        System.out.println(comet.getDistance());
//        System.out.println(dancer.getDistance());
    }

}

class Reindeer {

    private final int speed;
    private final int duration;
    private final int downtime;

    private int downtimeRemaining;
    private int durationRemaining;
    private int distance;
    private int points;


    public Reindeer(int speed, int duration, int downtime) {
        this.speed = speed;
        this.duration = duration;
        this.downtime = downtime;
        this.durationRemaining = duration;
        this.downtimeRemaining = 0;
        this.distance = 0;
        this.points = 0;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDuration() {
        return duration;
    }

    public int getDowntime() {
        return downtime;
    }

    public int getDowntimeRemaining() {
        return downtimeRemaining;
    }

    public int getDurationRemaining() {
        return durationRemaining;
    }

    public int getDistance() {
        return distance;
    }

    public int getPoints() {
        return points;
    }

    public void update() {
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
    public void givePoint() {
        points++;
    }

}