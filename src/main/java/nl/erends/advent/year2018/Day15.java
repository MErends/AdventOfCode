package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    private static List<Unit> units = new ArrayList<>();
    private static List<Elf> elfs = new ArrayList<>();
    private static List<Goblin> goblins = new ArrayList<>();
    
    private static Cave cave;

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day15.txt");
        long start = System.currentTimeMillis();
        cave = new Cave(input);
        int rounds = 0;
        loop:
        while (true) {
            units.clear();
            units.addAll(elfs);
            units.addAll(goblins);
            Collections.sort(units);
            for (Unit unit : units) {
                if (elfs.size() == 0 || goblins.size() == 0) {
                    break loop;
                }
                if (unit.HP <= 0) continue;
                if (!unit.nextToEnemy()) {
                    List<Tile> targetsInRange = unit.findTargetsInRange();
                    if (targetsInRange.size() == 0) continue;
                    Map<Tile, Integer> targetsReachable = unit.findTargetsReachable(targetsInRange);
                    if (targetsReachable.size() == 0) continue;
                    List<Tile> targetsNearest = unit.findTargetsNearest(targetsReachable);
                    Collections.sort(targetsNearest);
                    Tile targetChosen = targetsNearest.get(0);
                    Tile step = unit.determineFirstStep(targetChosen);
                    unit.moveTo(step);
                }
                if (unit.nextToEnemy()) {
                    unit.doAttack();
                }
            }
            rounds++;
        }
        units.clear();
        units.addAll(elfs);
        units.addAll(goblins);
        int HPleft = units.stream().mapToInt(unit -> unit.HP).sum();
        System.out.println(rounds * HPleft);
        long mid = System.currentTimeMillis();
        for (int elfAttack = 4; ; elfAttack++) {
            Elf.ATTACK = elfAttack;
            cave = new Cave(input);
            int elfsNeeded = elfs.size();
            rounds = 0;
            loop:
            while (true) {
                units.clear();
                units.addAll(elfs);
                units.addAll(goblins);
                Collections.sort(units);
                for (Unit unit : units) {
                    if (elfs.size() == 0 || goblins.size() == 0) {
                        break loop;
                    }
                    if (unit.HP <= 0) continue;
                    if (!unit.nextToEnemy()) {
                        List<Tile> targetsInRange = unit.findTargetsInRange();
                        if (targetsInRange.size() == 0) continue;
                        Map<Tile, Integer> targetsReachable = unit.findTargetsReachable(targetsInRange);
                        if (targetsReachable.size() == 0) continue;
                        List<Tile> targetsNearest = unit.findTargetsNearest(targetsReachable);
                        Collections.sort(targetsNearest);
                        Tile targetChosen = targetsNearest.get(0);
                        Tile step = unit.determineFirstStep(targetChosen);
                        unit.moveTo(step);
                    }
                    if (unit.nextToEnemy()) {
                        unit.doAttack();
                    }
                }
                rounds++;
            }
            units.clear();
            units.addAll(elfs);
            units.addAll(goblins);
            if (elfs.size() != elfsNeeded) continue;
            HPleft = units.stream().mapToInt(unit -> unit.HP).sum();
            System.out.println(rounds * HPleft);
            break;
        }
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }

    private static abstract class Unit implements Comparable<Unit> {
        int HP = 200;
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
            List<Tile> tileList = currentTile.getNeighbors();
            final int[] distance = {1};
            while (tileList.size() != 0) {
                tileList = tileList.stream()
                        .filter(tile -> !distanceMap.containsKey(tile))
                        .filter(Tile::isEmpty)
                        .peek(tile -> distanceMap.put(tile, distance[0]))
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
        
        void moveTo(Tile target) {
            cave.getTile(x, y).unit = null;
            target.unit = this;
            x = target.x;
            y = target.y;
        }
        
    }
    
    private static class Elf extends Unit {
        
        static int ATTACK = 3;

        Elf(int x, int y) {
            super(x, y);
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
            if (enemies.size() == 0) return;
            int minHP = Integer.MAX_VALUE;
            for (Goblin goblin : enemies) {
                minHP = Math.min(minHP, goblin.HP);
            }
            int finalMinHP = minHP;
            enemies.removeIf(goblin -> goblin.HP != finalMinHP);
            Collections.sort(enemies);
            Goblin target = enemies.get(0);
            target.HP -= ATTACK;
            if (target.HP <= 0) {
                goblins.remove(target);
                cave.getTile(target.x, target.y).unit = null;
            }
        }
        
    }
    
    private static class Goblin extends Unit {
        
        static int ATTACK = 3;

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
            if (enemies.size() == 0) return;
            int minHP = Integer.MAX_VALUE;
            for (Elf elf : enemies) {
                minHP = Math.min(minHP, elf.HP);
            }
            int finalMinHP = minHP;
            enemies.removeIf(goblin -> goblin.HP != finalMinHP);
            Collections.sort(enemies);
            Elf target = enemies.get(0);
            target.HP -= ATTACK;
            if (target.HP <= 0) {
                elfs.remove(target);
                cave.getTile(target.x, target.y).unit = null;
            }
        }
    }
    
    private static class Tile implements Comparable<Tile> {
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
        public String toString() {
            return "Tile{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    
    private static class Cave {
        private Tile[][] grid;
        
        Cave(List<String> input) {
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
                        Elf elf = new Elf(x, y);
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
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Tile[] row : grid) {
                StringBuilder hp = new StringBuilder();
                for (Tile tile : row) {
                    if (tile.unit != null) {
                        sb.append(tile.unit instanceof Elf ? 'E' : 'G');
                        hp.append(tile.unit instanceof Elf ? 'E' : 'G');
                        hp.append("(").append(tile.unit.HP).append("), ");
                    } else {
                        sb.append(tile.type);
                    }
                    sb.append(' ');
                }
                sb.append(hp.toString()).append('\n');
            }
            return sb.toString();
        }
    }
    
    private static class QueueItem {
        Tile tile;
        int steps;

        QueueItem(Tile tile, int steps) {
            this.tile = tile;
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "QueueItem{" +
                    "tile=" + tile +
                    ", steps=" + steps +
                    '}';
        }
    }
}
