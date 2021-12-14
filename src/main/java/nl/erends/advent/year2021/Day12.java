package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Day 12: Passage Pathing ---
 * <p>The only way you're getting out of this cave anytime soon is by finding a
 * path yourself. How many paths through this cave system are there that visit
 * small caves at most once? How many paths are there when you can visit a
 * single small cave twice?
 * <p><a href="https://adventofcode.com/2021/day/12">2021 Day 12</a>
 */
public class Day12 extends AbstractProblem<List<String>, Integer> {

    private final Map<String, Set<String>> segments = new HashMap<>();
    private static final String START = "start";

    public static void main(String[] args) {
        new Day12().setAndSolve(Util.readInput(2021, 12));
    }
    
    @Override
    protected Integer solve1() {
        for (String path : input) {
            String[] pathSplit = path.split("-");
            segments.computeIfAbsent(pathSplit[0], k -> new HashSet<>()).add(pathSplit[1]);
            segments.computeIfAbsent(pathSplit[1], k -> new HashSet<>()).add(pathSplit[0]);
        }
        int numPaths = countPaths(new ArrayList<>(), START, true);
        answer2 = countPaths(new ArrayList<>(), START, false);
        return numPaths;
    }
    
    private int countPaths(List<String> currentPath, String destination, boolean hadDuplicate) {
        if (destination.equals(START) && currentPath.contains(START)) {
            return 0;
        }
        if (destination.equals("end")) {
            return 1;
        }
        if (destination.toLowerCase().equals(destination) && currentPath.contains(destination)) {
            if (hadDuplicate) {
                return 0;
            } else {
                hadDuplicate = true;
            }
        }
        int paths = 0;
        for (String newDestination : segments.get(destination)) {
            List<String> newCurrentPath = new ArrayList<>(currentPath);
            newCurrentPath.add(destination);
            paths += countPaths(newCurrentPath, newDestination, hadDuplicate);
        }
        return paths;
    }
}
