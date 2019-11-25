package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day15 extends AbstractProblem<List<String>, Integer> {

    private List<Unit> units = new ArrayList<>();
    private List<Elf> elfs = new ArrayList<>();
    private List<Goblin> goblins = new ArrayList<>();
    private int rounds;
    
    private Cave cave;

    public static void main(String[] args) {
        new Day15().setAndSolve(Util.readInput(2018, 15));
    }
    
    @Override
    public Integer solve1() {
        cave = new Cave(input, 3);
        resolveCave();
        return rounds * goblins.stream().mapToInt(goblin -> goblin.hp).sum();
    }

    @Override
    public Integer solve2() {
        int elfAttack = 4;
        while (true) {
            cave = new Cave(input, elfAttack);
            int elfsBefore = elfs.size();
            resolveCave();
            if (elfs.size() == elfsBefore) {
                break;
            }
            elfAttack++;
        }
        int hpLeft = elfs.stream().mapToInt(elf -> elf.hp).sum();
        return rounds * hpLeft;
    }

    private void resolveCave() {
        rounds = 0;
        while (true) {
            units.clear();
            units.addAll(elfs);
            units.addAll(goblins);
            Collections.sort(units);
            for (Unit unit : units) {
                if (elfs.isEmpty() || goblins.isEmpty()) {
                    return;
                }
                if (unit.hp <= 0) {
                    continue;
                }
                unit.moveAndAttack();
            }
            rounds++;
        }
    }

    private abstract class Unit implements Comparable<Unit> {
        int hp = 200;
        int x;
        int y;

        @Override
        public int compareTo(Unit other) {
            if (y < other.y) return -1;
            else if (y > other.y) return 1;
            else if (x < other.x) return -1;
            else if (x > other.x) return 1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Unit unit = (Unit) o;
            return x == unit.x &&
                    y == unit.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        Unit(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public abstract boolean nextToEnemy();

        public abstract void doAttack();

        public abstract List<Tile> findTargetsInRange();

        Map<Tile, Integer> findTargetsReachable(List<Tile> targetsInRange) {
            Tile currentTile = cave.getTile(x, y);
            Map<Tile, Integer> distanceMap = new HashMap<>();
            List<Tile> neighbors = currentTile.getNeighbors();
            final int[] distance = {1};
            while (!neighbors.isEmpty()) {
                neighbors = neighbors.stream()
                        .filter(tile -> !distanceMap.containsKey(tile))
                        .filter(Tile::isEmpty)
                        .map(tile -> {distanceMap.put(tile, distance[0]); return tile;})
                        .map(Tile::getNeighbors)
                        .flatMap(List::stream)
                        .filter(tile -> !distanceMap.containsKey(tile))
                        .collect(Collectors.toList());
                distance[0]++;
            }
            Map<Tile, Integer> targetsDistanceMap = new HashMap<>();
            for (Tile tile : targetsInRange) {
                if (distanceMap.containsKey(tile)) {
                    targetsDistanceMap.put(tile, distanceMap.get(tile));
                }
            }
            return targetsDistanceMap;
        }

        List<Tile> findTargetsNearest(Map<Tile, Integer> distanceMap) {
            int closest = distanceMap.values().stream().mapToInt(i -> i).min().orElseThrow(IllegalStateException::new);
            List<Tile> closestList = new ArrayList<>();
            for (Map.Entry<Tile, Integer> entry : distanceMap.entrySet()) {
                if (entry.getValue() == closest) {
                    closestList.add(entry.getKey());
                }
            }
            return closestList;
        }

        Tile determineFirstStep(Tile target) {
            Tile currentTile = cave.getTile(x, y);
            List<QueueItem> path = new ArrayList<>();
            int steps = 0;
            path.add(new QueueItem(target, steps));
            for (int index = 0; index < path.size(); index++) {
                QueueItem item = path.get(index);
                steps = item.steps + 1;
                if (item.tile == currentTile) {
                    path.add(new QueueItem(target, steps));
                    break;
                }
                List<Tile> neighbors = item.tile.getNeighbors();
                neighbors.removeIf(neighbor -> path.stream().anyMatch(queueItem -> queueItem.tile == neighbor));
                for (Tile neighbor : neighbors) {
                    if (!neighbor.isEmpty() && neighbor != currentTile) {
                        continue;
                    }
                    path.add(new QueueItem(neighbor, steps));
                }
            }
            int startStep = steps - 1;
            return path.stream()
                    .filter(qi -> qi.steps == (startStep - 1))
                    .map(qi -> qi.tile)
                    .sorted()
                    .filter(tile -> currentTile.getNeighbors().contains(tile))
                    .findFirst().orElse(null);
        }

        void moveAndAttack() {
            if(!nextToEnemy()) {
                moveIfPossible();
            }
            if(nextToEnemy()) {
            doAttack();
            }
        }

        void moveIfPossible() {
            List<Tile> targetsInRange = findTargetsInRange();
            if (!targetsInRange.isEmpty()) {
                Map<Tile, Integer> targetsReachable = findTargetsReachable(targetsInRange);
                if (targetsReachable.size() != 0) {
                    List<Tile> targetsNearest = findTargetsNearest(targetsReachable);
                    Collections.sort(targetsNearest);
                    Tile targetChosen = targetsNearest.get(0);
                    Tile step = determineFirstStep(targetChosen);
                    moveTo(step);
                }
            }
        }
        
        void moveTo(Tile target) {
            cave.getTile(x, y).unit = null;
            target.unit = this;
            x = target.x;
            y = target.y;
        }
        
    }
    
    private class Elf extends Unit {
        
        int attack;

        Elf(int x, int y, int elfAttack) {
            super(x, y);
            attack = elfAttack;
        }
        
        @Override
        public boolean nextToEnemy() {
            Tile currentTile = cave.getTile(x, y);
            List<Tile> neighborTiles = currentTile.getNeighbors();
            return neighborTiles.stream().anyMatch(tile -> tile.unit instanceof Goblin);
        }
        
        @Override
        public List<Tile> findTargetsInRange() {
            Set<Tile> targetsInRange = new HashSet<>();
            for (Goblin goblin : goblins) {
                targetsInRange.addAll(cave.getTile(goblin.x, goblin.y).getNeighbors());
            }
            targetsInRange.removeIf(tile -> !tile.isEmpty());
            return new ArrayList<>(targetsInRange);
        }

        @Override
        public void doAttack() {
            Tile currentTile = cave.getTile(x, y);
            List<Tile> neighborTiles = currentTile.getNeighbors();
            List<Goblin> enemies = new ArrayList<>();
            for (Tile neighborTile : neighborTiles) {
                if (neighborTile.unit instanceof Goblin) {
                    enemies.add((Goblin) neighborTile.unit);
                }
            }
            if (enemies.isEmpty()) return;
            int minHP = Integer.MAX_VALUE;
            for (Goblin goblin : enemies) {
                minHP = Math.min(minHP, goblin.hp);
            }
            int finalMinHP = minHP;
            enemies.removeIf(goblin -> goblin.hp != finalMinHP);
            Collections.sort(enemies);
            Goblin target = enemies.get(0);
            target.hp -= attack;
            if (target.hp <= 0) {
                goblins.remove(target);
                cave.getTile(target.x, target.y).unit = null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Goblin goblin = (Goblin) o;
            return attack == goblin.attack;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), attack);
        }
    }
    
    private class Goblin extends Unit {
        
        int attack = 3;

        Goblin(int x, int y) {
            super(x, y);
        }

        @Override
        public boolean nextToEnemy() {
            Tile currentTile = cave.getTile(x, y);
            List<Tile> neighborTiles = currentTile.getNeighbors();
            return neighborTiles.stream().anyMatch(tile -> tile.unit instanceof Elf);
        }
        
        @Override
        public List<Tile> findTargetsInRange() {
            Set<Tile> targetsInRange = new HashSet<>();
            for (Elf elf : elfs) {
                targetsInRange.addAll(cave.getTile(elf.x, elf.y).getNeighbors());
            }
            targetsInRange.removeIf(tile -> !tile.isEmpty());
            return new ArrayList<>(targetsInRange);
        }

        @Override
        public void doAttack() {
            Tile currentTile = cave.getTile(x, y);
            List<Tile> neighborTiles = currentTile.getNeighbors();
            List<Elf> enemies = new ArrayList<>();
            for (Tile neighborTile : neighborTiles) {
                if (neighborTile.unit instanceof Elf) {
                    enemies.add((Elf) neighborTile.unit);
                }
            }
            if (enemies.isEmpty()) return;
            int minHP = Integer.MAX_VALUE;
            for (Elf elf : enemies) {
                minHP = Math.min(minHP, elf.hp);
            }
            int finalMinHP = minHP;
            enemies.removeIf(goblin -> goblin.hp != finalMinHP);
            Collections.sort(enemies);
            Elf target = enemies.get(0);
            target.hp -= attack;
            if (target.hp <= 0) {
                elfs.remove(target);
                cave.getTile(target.x, target.y).unit = null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Goblin goblin = (Goblin) o;
            return attack == goblin.attack;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), attack);
        }
    }
    
    private class Tile implements Comparable<Tile> {
        private char type;
        private int x;
        private int y;
        private Unit unit;

        Tile(char c, int x, int y) {
            this.type = c;
            this.x = x;
            this.y = y;
        }
        
        boolean isWall() {
            return type == '#';
        }
        
        public boolean isEmpty() {
            return !isWall() && unit == null;
        }
        
        public List<Tile> getNeighbors() {
            List<Tile> neighbors = new ArrayList<>();
            neighbors.add(cave.getTile(x, y + 1));
            neighbors.add(cave.getTile(x, y - 1));
            neighbors.add(cave.getTile(x + 1, y));
            neighbors.add(cave.getTile(x - 1, y));
            neighbors.removeIf(Tile::isWall);
            return neighbors;
        }

        @Override
        public int compareTo(Tile other) {
            if (y < other.y) return -1;
            else if (y > other.y) return 1;
            else if (x < other.x) return -1;
            else if (x > other.x) return 1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tile tile = (Tile) o;
            return x == tile.x &&
                    y == tile.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    private class Cave {
        private Tile[][] grid;
        
        Cave(List<String> input, int elfAttack) {
            elfs = new ArrayList<>();
            goblins = new ArrayList<>();
            units = new ArrayList<>();
            grid = new Tile[input.size()][input.get(0).length()];
            for (int y = 0; y < input.size(); y++) {
                for (int x = 0; x < input.get(y).length(); x++) {
                    char c = input.get(y).charAt(x);
                    grid[y][x] = new Tile(c, x, y);
                    if (c == 'G') {
                        Goblin goblin = new Goblin(x, y);
                        goblins.add(goblin);
                        grid[y][x].unit = goblin;
                        grid[y][x].type = '.';
                    } else if (c == 'E') {
                        Elf elf = new Elf(x, y, elfAttack);
                        elfs.add(elf);
                        grid[y][x].unit = elf;
                        grid[y][x].type = '.';
                    }
                }
            }
        }
        
        public Tile getTile(int x, int y) {
            return grid[y][x];
        }
    }
    
    private static class QueueItem {
        Tile tile;
        int steps;

        QueueItem(Tile tile, int steps) {
            this.tile = tile;
            this.steps = steps;
        }
    }
}
