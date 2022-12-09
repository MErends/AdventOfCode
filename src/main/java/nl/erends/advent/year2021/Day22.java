package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 22: Reactor Reboot ---
 * <p>Operating at these extreme ocean depths has overloaded the submarine's
 * reactor. It needs to be rebooted. The reactor core is made up of a large
 * 3-dimensional grid made up entirely of cubes. Starting with all cubes off,
 * execute all reboot steps. Afterward, considering all cubes, how many cubes
 * are on?
 * <p><a href="https://adventofcode.com/2021/day/22">2021 Day 22</a>
 */
public class Day22 extends AbstractProblem<List<String>, Number> {
    
    private final Pattern p = Pattern.compile("(\\w+) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");
    private List<Cube> cubes;
    private boolean part2 = false;
    
    public static void main(String[] args) {
        new Day22().setAndSolve(Util.readInput(2021, 22));
    }
    
    @Override
    protected Long solve1() {
         cubes = new ArrayList<>();
        for (String line : input) {
            Matcher m = p.matcher(line);
            m.find();
            boolean state = m.group(1).equals("on");
            int x0 = Integer.parseInt(m.group(2));
            int x1 = Integer.parseInt(m.group(3)) + 1;
            int y0 = Integer.parseInt(m.group(4));
            int y1 = Integer.parseInt(m.group(5)) + 1;
            int z0 = Integer.parseInt(m.group(6));
            int z1 = Integer.parseInt(m.group(7)) + 1;
            if (!part2 && Math.abs(x0) > 50) {
                break;
            }
            Cube cube = new Cube(x0, x1, y0, y1, z0, z1);
            List<Cube> intersectingCubes = getIntersectingCubes(cube);
            intersectingCubes.forEach(c -> splitCubeX(c, x0));
            intersectingCubes = getIntersectingCubes(cube);
            intersectingCubes.forEach(c -> splitCubeX(c, x1));
            intersectingCubes = getIntersectingCubes(cube);
            intersectingCubes.forEach(c -> splitCubeY(c, y0));
            intersectingCubes = getIntersectingCubes(cube);
            intersectingCubes.forEach(c -> splitCubeY(c, y1));
            intersectingCubes = getIntersectingCubes(cube);
            intersectingCubes.forEach(c -> splitCubeZ(c, z0));
            intersectingCubes = getIntersectingCubes(cube);
            intersectingCubes.forEach(c -> splitCubeZ(c, z1));
            intersectingCubes = getIntersectingCubes(cube);
            cubes.removeAll(intersectingCubes);
            if (state) {
                cubes.add(cube);
            }
        }
        return cubes.stream().mapToLong(this::getVolume).sum();
    }

    @Override
    public Number solve2() {
        part2 = true;
        return solve1();
    }

    private List<Cube> getIntersectingCubes(Cube cubeA) {
        List<Cube> intersectingCubes = new ArrayList<>();
        for (Cube cubeB : cubes) {
            boolean intersectX = cubeA.x0 <= cubeB.x0 && cubeB.x0 < cubeA.x1
                    || cubeB.x0 <= cubeA.x0 && cubeA.x0 < cubeB.x1;
            boolean intersectY = cubeA.y0 <= cubeB.y0 && cubeB.y0 < cubeA.y1
                    || cubeB.y0 <= cubeA.y0 && cubeA.y0 < cubeB.y1;
            boolean intersectZ = cubeA.z0 <= cubeB.z0 && cubeB.z0 < cubeA.z1
                    || cubeB.z0 <= cubeA.z0 && cubeA.z0 < cubeB.z1;
            if (intersectX && intersectY && intersectZ) {
                intersectingCubes.add(cubeB);
            }
        }
        return intersectingCubes;
    }
    
    private void splitCubeX(Cube cube, int x) {
        if (x < cube.x0 || x >= cube.x1) {
            return;
        }
        Cube cube1 = new Cube(cube.x0, x, cube.y0, cube.y1, cube.z0, cube.z1);
        Cube cube2 = new Cube(x, cube.x1, cube.y0, cube.y1, cube.z0, cube.z1);
        cubes.add(cube1);
        cubes.add(cube2);
        cubes.remove(cube);
    }

    private void splitCubeY(Cube cube, int y) {
        if (y < cube.y0 || y >= cube.y1) {
            return;
        }
        Cube cube1 = new Cube(cube.x0, cube.x1, cube.y0, y, cube.z0, cube.z1);
        Cube cube2 = new Cube(cube.x0, cube.x1, y, cube.y1, cube.z0, cube.z1);
        cubes.add(cube1);
        cubes.add(cube2);
        cubes.remove(cube);
    }

    private void splitCubeZ(Cube cube, int z) {
        if (z < cube.z0 || z >= cube.z1) {
            return;
        }
        Cube cube1 = new Cube(cube.x0, cube.x1, cube.y0, cube.y1, cube.z0, z);
        Cube cube2 = new Cube(cube.x0, cube.x1, cube.y0, cube.y1, z, cube.z1);
        cubes.add(cube1);
        cubes.add(cube2);
        cubes.remove(cube);
    }

    private long getVolume(Cube cube) {
        return ((long) cube.x1 - cube.x0) * (cube.y1 - cube.y0) * (cube.z1 - cube.z0);
    }
    
    private record Cube(int x0,int x1, int y0, int y1, int z0, int z1) {
    }
}
