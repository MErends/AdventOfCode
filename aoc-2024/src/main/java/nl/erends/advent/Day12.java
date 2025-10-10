package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>--- Day 12: Garden Groups ---</h1>
 * <p>You're about to settle near a complex arrangement of garden plots when
 * some Elves ask if you can lend a hand. They'd like to set up fences around
 * each region of garden plots, but they can't figure out how much fence they
 * need to order or how much it will cost. Due to "modern" business practices,
 * the price of fence required for a region is found by multiplying that
 * region's area by its perimeter. What is the total price of fencing all
 * regions on your map?</p>
 * <p><a href="https://adventofcode.com/2024/day/12">2024 Day 12</a></p>
 */
public class Day12 extends AbstractProblem<List<String>, Integer> {

    private final List<Area> areasRemaining = new ArrayList<>();
    private Area[][] areaGrid;
    private final Area outside = new Area(-1, -1, '\0');
    private final List<Region> regions = new ArrayList<>();
    private int belowBorderId = 0;
    private int aboveBorderId = 0;
    private int leftBorderId = Integer.MIN_VALUE;
    private int rightBorderId = Integer.MAX_VALUE;
    private boolean inBorder = false;
    private char previousAbove = '\0';
    private char previousBelow = '\0';
    private char previousRight = '\0';
    private char previousLeft = '\0';

    void main() {
        new Day12().setAndSolve(Util.readInput(2024, 12));
    }

    @Override
    protected Integer solve1() {
        areaGrid = new Area[input.size()][input.getFirst().length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                Area area = new Area(x, y, input.get(y).charAt(x));
                areasRemaining.add(area);
                areaGrid[y][x] = area;
            }
        }
        while (!areasRemaining.isEmpty()) {
            regions.add(new Region(areasRemaining.removeFirst()));

        }
        int price = 0;
        for (Region r : regions) {
            price += r.borders * r.areas.size();
        }
        return price;
    }

    @Override
    public Integer solve2() {
        outside.region = new Region(outside);
        for (int y = 0; y <= areaGrid.length; y++) {
            for (int x = 0; x < areaGrid[0].length; x++) {
                processHorizontalWall(x, y);
            }
        }
        inBorder = false;
        for (int x = 0; x <= areaGrid[0].length; x++) {
            for (int y = 0; y < areaGrid.length; y++) {
                procesVerticalWall(x, y);
            }
        }
        int price = 0;
        for (Region r : regions) {
            price += r.borderIds.size() * r.areas.size();
        }
        return price;
    }

    private void procesVerticalWall(int x, int y) {
        Area areaLeft = getAreaGrid(x - 1, y);
        Area areaRight = getAreaGrid(x, y);
        if (areaLeft.plant == areaRight.plant) {
            inBorder = false;
            return;
        }
        if (!inBorder) {
            inBorder = true;
            leftBorderId--;
            rightBorderId++;
        } else {
            if (previousRight != areaRight.plant) {
                rightBorderId++;
            }
            if (previousLeft != areaLeft.plant) {
                leftBorderId--;
            }
        }
        areaLeft.region.borderIds.add(leftBorderId);
        areaRight.region.borderIds.add(rightBorderId);
        previousLeft = areaLeft.plant;
        previousRight = areaRight.plant;
    }

    private void processHorizontalWall(int x, int y) {
        Area areaAbove = getAreaGrid(x, y - 1);
        Area areaBelow = getAreaGrid(x, y);
        if (areaAbove.plant == areaBelow.plant) {
            inBorder = false;
            return;
        }
        if (!inBorder) {
            inBorder = true;
            belowBorderId--;
            aboveBorderId++;
        } else {
            if (previousAbove != areaAbove.plant) {
                aboveBorderId++;
            }
            if (previousBelow != areaBelow.plant) {
                belowBorderId--;
            }
        }
        areaAbove.region.borderIds.add(aboveBorderId);
        areaBelow.region.borderIds.add(belowBorderId);
        previousAbove = areaAbove.plant;
        previousBelow = areaBelow.plant;
    }

    class Area {

        final int x;
        final int y;
        final char plant;
        Region region;

        public Area(int x, int y, char plant) {
            this.x = x;
            this.y = y;
            this.plant = plant;
        }
    }

    class Region {

        int borders = 0;
        final char plant;
        final List<Area> areas = new ArrayList<>();
        final Set<Integer> borderIds = new HashSet<>();

        Region(Area seed) {
            areas.add(seed);
            plant = seed.plant;
            List<Area> seeds = new ArrayList<>();
            seeds.add(seed);
            while (!seeds.isEmpty()) {
                Area a = seeds.removeFirst();
                for (Direction d : Direction.values()) {
                    Area neighbor = getAreaGrid(a.x + d.dx(), a.y + d.dy());
                    if (neighbor.plant != this.plant) {
                        borders++;
                    }
                    if (neighbor.plant == this.plant && areasRemaining.contains(neighbor)) {
                        seeds.add(neighbor);
                        areas.add(neighbor);
                        areasRemaining.remove(neighbor);
                    }
                }
            }
            areas.forEach(a -> a.region = this);
        }
    }

    private Area getAreaGrid(int x, int y) {
        try {
            return areaGrid[y][x];
        } catch (IndexOutOfBoundsException _) {
            return outside;
        }
    }
}
