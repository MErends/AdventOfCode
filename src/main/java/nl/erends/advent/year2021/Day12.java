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
 * The only way you're getting out of this cave anytime soon is byfinding a path
 * yourself. How many paths through this cave system are there that visit small
 * caves at most once? How many paths are there when you can visit a single
 * small cave twice?
 * <p><a href="https://adventofcode.com/2021/day/12">2021 Day 12</a>
 */
public class Day12 extends AbstractProblem<List<String>, Integer> {

    private final Map<String, Set<String>> segments = new HashMap<>();
    private static final String start = "start";
    private int numPaths = 0;
    private int numPaths2 = 0;

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
        traverse(new ArrayList<>(), start);
        traverse2(new ArrayList<>(), start, false);
        answer2 = numPaths2;
        return numPaths;
    }
    
    private void traverse(List<String> currentPath, String destination) {
        if (destination.toLowerCase().equals(destination) && currentPath.contains(destination)) {
            return;
        }
        if (destination.equals("end")) {
            numPaths++;
            return;
        }
        for (String newDestination : segments.get(destination)) {
            List<String> newCurrentPath = new ArrayList<>(currentPath);
            newCurrentPath.add(destination);
            traverse(newCurrentPath, newDestination);
        }
    }
    
    private void traverse2(List<String> currentPath, String destination, boolean hadDuplicate) {
        if (destination.equals(start) && currentPath.contains(start)) {
            return;
        }
        if (destination.equals("end")) {
            numPaths2++;
            return;
        }
        if (destination.toLowerCase().equals(destination) && currentPath.contains(destination)) {
            if (hadDuplicate) {
                return;
            } else {
                hadDuplicate = true;
            }
        }
        for (String newDestination : segments.get(destination)) {
            List<String> newCurrentPath = new ArrayList<>(currentPath);
            newCurrentPath.add(destination);
            traverse2(newCurrentPath, newDestination, hadDuplicate);
        }
    }
}
