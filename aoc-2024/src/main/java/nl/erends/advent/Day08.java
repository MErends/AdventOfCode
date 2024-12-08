package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h1>--- Day 8: Resonant Collinearity ---</h1>
 * <p>You find yourselves on the roof of a top-secret Easter Bunny installation.
 * While The Historians do their thing, you take a look at the familiar huge
 * antenna. Each antenna is tuned to a specific frequency indicated by a single
 * lowercase letter, uppercase letter, or digit. Calculate the impact of the
 * signal. How many unique locations within the bounds of the map contain an
 * antinode?</p>
 * <p><a href="https://adventofcode.com/2024/day/8">2024 Day 8</a></p>
 */
public class Day08 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day08().setAndSolve(Util.readInput(2024, 8));
    }

    @Override
    protected Integer solve1() {
        Map<Character, List<Coord>> antennaMap = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char antenna = input.get(y).charAt(x);
                if (antenna != '.') {
                    antennaMap.computeIfAbsent(antenna, a -> new ArrayList<>()).add(new Coord(x, y));
                }
            }
        }
        if (!part2) {
            Set<Coord> antiNodes = getAntiNodes(antennaMap);
            antiNodes.removeIf(node -> !isNodeInRange(node));
            return antiNodes.size();
        }
        return getAntiNodes2(antennaMap).size();
    }

    private static Set<Coord> getAntiNodes(Map<Character, List<Coord>> antennaMap) {
        Set<Coord> antiNodes = new HashSet<>();
        for (List<Coord> frequency : antennaMap.values()) {
            for (Coord antennaA : frequency) {
                for (Coord antennaB : frequency) {
                    if (antennaA == antennaB) {
                        continue;
                    }
                    int dx = antennaB.x - antennaA.x;
                    int dy = antennaB.y - antennaA.y;
                    Coord antinode = new Coord(antennaB.x + dx, antennaB.y + dy);
                    antiNodes.add(antinode);
                }
            }
        }
        return antiNodes;
    }

    private Set<Coord> getAntiNodes2(Map<Character, List<Coord>> antennaMap) {
        Set<Coord> antiNodes = new HashSet<>();
        for (List<Coord> frequency : antennaMap.values()) {
            for (Coord antennaA : frequency) {
                for (Coord antennaB : frequency) {
                    if (antennaA == antennaB) {
                        continue;
                    }
                    int dx = antennaB.x - antennaA.x;
                    int dy = antennaB.y - antennaA.y;
                    int nodeX = antennaB.x;
                    int nodeY = antennaB.y;
                    Coord antinode = new Coord(nodeX, nodeY);
                    while (isNodeInRange(antinode)) {
                        antiNodes.add(antinode);
                        antinode = new Coord(antinode);
                        antinode.x += dx;
                        antinode.y += dy;
                    }
                }
            }
        }
        return antiNodes;
    }

    private boolean isNodeInRange(Coord node) {
        return node.y >= 0 && node.x >= 0
                && node.y < input.size()
                && node.x < input.get(node.y).length();
    }
}
