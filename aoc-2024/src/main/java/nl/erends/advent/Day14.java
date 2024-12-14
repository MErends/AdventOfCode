package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>--- Day 14: Restroom Redoubt ---</h1>
 * <p>To get The Historian safely to the bathroom, you'll need a way to predict
 * where the robots will be in the future. Fortunately, they all seem to be
 * moving on the tile floor in predictable straight lines. To determine the
 * safest area, count the number of robots in each quadrant after 100 seconds.
 * What will the safety factor be after exactly 100 seconds have elapsed?</p>
 * <p><a href="https://adventofcode.com/2024/day/14">2024 Day 14</a></p>
 */
public class Day14 extends AbstractProblem<List<String>, Integer> {

    int roomWidth = 101;
    int roomHeight = 103;

    public static void main(String[] args) {
        new Day14().setAndSolve(Util.readInput(2024, 14));
    }

    @Override
    protected Integer solve1() {
        List<Robot> robots = input.stream().map(Robot::new).toList();
        robots.forEach(robot1 -> robot1.move(100));
        return getSafetyFactor(robots);
    }

    @Override
    public Integer solve2() {
        List<Robot> robots = input.stream().map(Robot::new).toList();
        int steps = 0;
        double totalDeviation = 0;
        while (true) {
            robots.forEach(r -> r.move(1));
            steps++;
            double currentDeviation = getDeviation(robots);
            totalDeviation += currentDeviation;
            double rollingDeviation = totalDeviation / steps;
            if (currentDeviation * 1.5 < rollingDeviation) {
                break;
            }
        }
        return steps;
    }

    private double getDeviation(List<Robot> robots) {
        int robotCount = robots.size();
        double averageX = (double) robots.stream().mapToInt(r -> r.x).sum() / robotCount;
        double averageY = (double) robots.stream().mapToInt(r -> r.y).sum() / robotCount;
        return robots.stream()
                .mapToDouble(r -> Math.sqrt(Math.pow(averageX - r.x, 2) + Math.pow(averageY - r.y, 2)))
                .sum() / robotCount;
    }

    private int getSafetyFactor(List<Robot> robots) {
        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;
        for (Robot robot : robots) {
            if (robot.x < roomWidth / 2) {
                if (robot.y < roomHeight / 2) {
                    quad1++;
                } else if (robot.y > roomHeight / 2) {
                    quad2++;
                }
            } else if (robot.x > roomWidth / 2) {
                if (robot.y < roomHeight / 2) {
                    quad3++;
                } else if (robot.y > roomHeight / 2) {
                    quad4++;
                }
            }
        }
        return quad1 * quad2 * quad3 * quad4;
    }

    protected void setTestRoom() {
        roomWidth = 11;
        roomHeight = 7;
    }

    class Robot {

        static Pattern p = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");

        int x;
        int y;
        int vX;
        int vY;

        Robot(String line) {
            Matcher m = p.matcher(line);
            if (!m.find()) throw new IllegalArgumentException();
            x = Integer.parseInt(m.group(1));
            y = Integer.parseInt(m.group(2));
            vX = Integer.parseInt(m.group(3));
            vY = Integer.parseInt(m.group(4));
        }

        void move(int steps) {
            x += (vX * steps);
            x %= roomWidth;
            if (x < 0) {
                x += roomWidth;
            }
            y += (vY * steps);
            y %= roomHeight;
            if (y < 0) {
                y += roomHeight;
            }
        }
    }
}
