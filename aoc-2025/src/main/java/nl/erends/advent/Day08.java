package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * <h1>--- Day 8: Playground ---</h1>
 * <p>Across the playground, a group of Elves are working on setting up an
 * ambitious Christmas decoration project. Through careful rigging, they have
 * suspended a large number of small electrical junction boxes. The Elves are
 * trying to figure out which junction boxes to connect so that electricity can
 * reach every junction box. By connecting these two junction boxes together,
 * because electricity can flow between them, they become part of the same
 * circuit. What do you get if you multiply together the sizes of the three
 * largest circuits?</p>
 * <p><a href="https://adventofcode.com/2025/day/8">2025 Day 8</a></p>
 */
public class Day08 extends AbstractProblem<List<String>, Long> {
    
    List<Set<Coord>> circuits = new ArrayList<>();
    private int targetLinks = 1000;

    void main() {
        new Day08().setAndSolve(Util.readInput(2025, 8));
    }

    @Override
    protected Long solve1() {
        List<Coord> boxes = new ArrayList<>();
        input.forEach(l -> boxes.add(Coord.of(l)));
        Map<Long, Link> distances = new HashMap<>();
        for (int a = 0; a < boxes.size(); a++) {
            Coord boxA = boxes.get(a);
            for (int b = a + 1; b < boxes.size(); b++) {
                Coord boxB = boxes.get(b);
                long distance = Math.powExact((long) boxA.x() - boxB.x(), 2)
                        + Math.powExact((long) boxA.y() - boxB.y(), 2)
                        + Math.powExact((long) boxA.z() - boxB.z(), 2);
                distances.put(distance, new Link(boxA, boxB));
            }
        }
        int allLinks = input.size();
        List<Long> distanceList = new ArrayList<>(distances.keySet().stream().sorted().toList());
        Link lastLink = new Link(Coord.ZERO, Coord.ZERO);
        int linkCount = 0;
        long answer1 = 0;
        while (circuits.size() != 1 || circuits.getFirst().size() != allLinks) {
            lastLink = distances.remove(distanceList.removeFirst());
            addLinkToCircuit(lastLink);
            linkCount++;
            if (linkCount == targetLinks) {
                answer1 = circuits.stream()
                        .map(Set::size)
                        .sorted((i1, i2) -> i2 - i1)
                        .limit(3)
                        .reduce(1, (i1, i2) -> i1 * i2);
            }
        }
        answer2 = (long) lastLink.a.x() * lastLink.b.x();
        return answer1;
    }
    
    private void addLinkToCircuit(Link link) {
        Optional<Set<Coord>> circuitA = circuits.stream().filter(c -> c.contains(link.a)).findFirst();
        Optional<Set<Coord>> circuitB = circuits.stream().filter(c -> c.contains(link.b)).findFirst();
        if (circuitA.isPresent() && circuitB.isEmpty()) {
            circuitA.get().add(link.b);
        } else if (circuitA.isEmpty() && circuitB.isPresent()) {
            circuitB.get().add(link.a);
        } else if (circuitA.isEmpty()) {
            Set<Coord> circuit = new HashSet<>();
            circuit.add(link.a);
            circuit.add(link.b);
            circuits.add(circuit);
        } else if (circuitA.get() != circuitB.get()){
            circuits.remove(circuitB.get());
            circuitA.get().addAll(circuitB.get());
        }
    }
    
    public void setTestTarget() {
        targetLinks = 10;
    }
    
    private record Link(Coord a, Coord b){}
}
