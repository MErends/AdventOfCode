package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * --- Day 19: Not Enough Minerals ---
 * <p>You and the elephants exit the cave. As you do, you notice a collection of
 * geodes around the pond. Perhaps you could use the obsidian to create some
 * geode-cracking robots and break them open? You need to figure out which
 * blueprint would maximize the number of opened geodes after 24 minutes by
 * figuring out which robots to build and when to build them.
 * <p><a href="https://adventofcode.com/2022/day/19">2022 Day 19</a>
 */
public class Day19 extends AbstractProblem<List<String>, Number> {

    private static final int ORE = 0;
    private static final int CLAY = 1;
    private static final int OBS = 2;
    private static final int GEODE = 3;
    private int maxGeodes;

    public static void main(String[] args) {
        new Day19().setAndSolve(Util.readInput(2022, 19));
    }

    @Override
    protected Number solve1() {
        List<Blueprint> bps = input.stream().map(Blueprint::new).toList();
        int quality = 0;
        for (Blueprint bp : bps) {
            maxGeodes = Integer.MIN_VALUE;
            new State(bp, 24).buildAndCompute(-1, Collections.emptyList());
            quality += bp.id * maxGeodes;
        }
        return quality;
    }

    @Override
    public Number solve2() {
        List<Blueprint> bps = input.stream().map(Blueprint::new).limit(3).toList();
        int geodes = 1;
        for (Blueprint bp : bps) {
            maxGeodes = Integer.MIN_VALUE;
            new State(bp, 32).buildAndCompute(-1, Collections.emptyList());
            geodes *= maxGeodes;
        }
        return geodes;
    }

    private class State {

        private int timeLeft;
        private final int[] resources;
        private final int[] robots;
        private final Blueprint bp;

        private State(Blueprint bp, int timeLeft) {
            this.timeLeft = timeLeft;
            this.resources = new int[]{0, 0, 0, 0};
            this.robots = new int[]{1, 0, 0, 0};
            this.bp = bp;
        }

        private State(State o) {
            timeLeft = o.timeLeft;
            resources = Arrays.copyOf(o.resources, o.resources.length);
            robots = Arrays.copyOf(o.robots, o.robots.length);
            bp = o.bp;
        }

        void buildAndCompute(int newRobot, List<Integer> dontBuild) {
            for (int resource = ORE; resource <= GEODE; resource++) {
                resources[resource] += robots[resource];
            }
            if (newRobot != -1) {
                for (int resource = ORE; resource <= OBS; resource++) {
                    resources[resource] -= bp.robotCost[newRobot][resource];
                }
                robots[newRobot]++;
            }
            timeLeft--;
            int possibleGeodes = resources[GEODE];
            for (int tick = 0; tick < timeLeft; tick++) {
                possibleGeodes += robots[GEODE] + tick;
            }
            if (possibleGeodes <= maxGeodes) {
                return;
            }
            if (timeLeft == 0) {
                maxGeodes =  resources[GEODE];
                return;
            }
            List<Integer> possibleRobots = new ArrayList<>();
            for (int robot = GEODE; robot >= ORE; robot--) {
                if (robots[robot] < bp.maxRobots[robot]
                        && bp.robotCost[robot][ORE] <= resources[ORE]
                        && bp.robotCost[robot][CLAY] <= resources[CLAY]
                        && bp.robotCost[robot][OBS] <= resources[OBS]) {
                    possibleRobots.add(robot);
                }
            }
            for (int robot : possibleRobots) {
                if (!dontBuild.contains(robot)) {
                    new State(this).buildAndCompute(robot, Collections.emptyList());
                }
            }
            buildAndCompute(-1, possibleRobots);
        }
    }

    private static class Blueprint {

        private final int id;
        private final int[][] robotCost;
        private final int[] maxRobots = new int[]{0, 0, 0, Integer.MAX_VALUE};

        private Blueprint(String line) {
            String[] words = line.split(" ");
            id = Integer.parseInt(words[1].substring(0, words[1].length() - 1));
            robotCost = new int[][]{{Integer.parseInt(words[6]), 0, 0},
                    {Integer.parseInt(words[12]), 0, 0},
                    {Integer.parseInt(words[18]), Integer.parseInt(words[21]), 0},
                    {Integer.parseInt(words[27]), 0, Integer.parseInt(words[30])}};
            for (int resource = ORE; resource <= OBS; resource++) {
                for (int robot = ORE; robot <= GEODE; robot++) {
                    maxRobots[resource] = Math.max(maxRobots[resource], robotCost[robot][resource]);
                }
            }
        }
    }
}
