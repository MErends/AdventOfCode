package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 18: Boiling Boulders ---
 * <p>You've emerged near the base of a large volcano that seems to be actively
 * erupting! Outside the cave, you can see the lava landing in a pond and hear
 * it loudly hissing as it solidifies. What is the surface area of your scanned
 * lava droplet?
 * <p><a href="https://adventofcode.com/2022/day/18">2022 Day 18</a>
 */
public class Day18 extends AbstractProblem<List<String>, Number> {

    void main() {
        new Day18().setAndSolve(Util.readInput(2022, 18));
    }

    @Override
    protected Number solve1() {
        List<Cube> lava = getLava();

        return lava.stream()
                .map(Cube::getNeighbors)
                .flatMap(List::stream)
                .filter(neighbors -> !lava.contains(neighbors))
                .count();
    }

    @Override
    public Number solve2() {
        List<Cube> lava = getLava();
        int xMin = lava.stream().mapToInt(c -> c.x).min().orElseThrow();
        int xMax = lava.stream().mapToInt(c -> c.x).max().orElseThrow();
        int yMin = lava.stream().mapToInt(c -> c.y).min().orElseThrow();
        int yMax = lava.stream().mapToInt(c -> c.y).max().orElseThrow();
        int zMin = lava.stream().mapToInt(c -> c.z).min().orElseThrow();
        int zMax = lava.stream().mapToInt(c -> c.z).max().orElseThrow();
        Cube root = new Cube(xMin - 1, yMin -1, zMin -1);
        Set<Cube> water = new HashSet<>();
        List<Cube> spreadFrom = new ArrayList<>();
        water.add(root);
        spreadFrom.add(root);
        while (!spreadFrom.isEmpty()) {
            Cube spread = spreadFrom.removeFirst();
            List<Cube> neighbors = spread.getNeighbors();
            neighbors.removeIf(neighbor -> neighbor.x < xMin - 1 || neighbor.x > xMax + 1 ||
                    neighbor.y < yMin - 1 || neighbor.y > yMax + 1 ||
                    neighbor.z < zMin - 1 || neighbor.z > zMax + 1 ||
                    water.contains(neighbor) ||
                    lava.contains(neighbor));
            spreadFrom.addAll(neighbors);
            water.addAll(neighbors);
        }
        return lava.stream()
                .map(Cube::getNeighbors)
                .flatMap(List::stream)
                .filter(water::contains)
                .count();
    }

    private List<Cube> getLava() {
        return input.stream()
                .map(s -> s.split(","))
                .map(w -> new Cube(Integer.parseInt(w[0]),
                        Integer.parseInt(w[1]),
                        Integer.parseInt(w[2])))
                .toList();
    }

    private record Cube(int x, int y, int z) {

        private List<Cube> getNeighbors() {
            List<Cube> neighbors = new ArrayList<>();
            neighbors.add(new Cube(x - 1, y, z));
            neighbors.add(new Cube(x + 1, y, z));
            neighbors.add(new Cube(x, y - 1, z));
            neighbors.add(new Cube(x, y + 1, z));
            neighbors.add(new Cube(x, y, z - 1));
            neighbors.add(new Cube(x, y, z + 1));
            return neighbors;
        }
    }
}
