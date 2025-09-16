package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static nl.erends.advent.util.Util.lcm;

public class Day12 extends AbstractProblem<List<String>, Number> {

    private int maxSteps = 1000;

    static void main() {
        new Day12().setAndSolve(Util.readInput(2019, 12));
    }

    @Override
    public Integer solve1() {
        List<Moon> moons = readInput();
        int steps = 0;
        while (steps != maxSteps) {
            doStep(moons);
            steps++;
        }
        return moons.stream().mapToInt(Moon::getEnergy).sum();
    }

    @Override
    public Long solve2() {
        List<Moon> moons = readInput();
        int xSteps = 0;
        int ySteps = 0;
        int zSteps = 0;
        String xStart = getState(moons, 'x');
        String yStart = getState(moons, 'y');
        String zStart = getState(moons, 'z');
        int steps = 0;
        while (xSteps == 0 || ySteps == 0 || zSteps == 0) {
            doStep(moons);
            steps++;
            String xState = getState(moons, 'x');
            String yState = getState(moons, 'y');
            String zState = getState(moons, 'z');
            if (xSteps == 0 && xState.equals(xStart)) {
                xSteps = steps;
            }
            if (ySteps == 0 && yState.equals(yStart)) {
                ySteps = steps;
            }
            if (zSteps == 0 && zState.equals(zStart)) {
                zSteps = steps;
            }
        }

        return lcm(lcm(xSteps, ySteps), zSteps);
    }

    private List<Moon> readInput() {
        List<Moon> moons = new ArrayList<>();
        for (String line : input) {
            String[] positions = line.substring(1, line.length() - 1).split(",");
            int x = Integer.parseInt(positions[0].split("=")[1].trim());
            int y = Integer.parseInt(positions[1].split("=")[1].trim());
            int z = Integer.parseInt(positions[2].split("=")[1].trim());
            moons.add(new Moon(x, y, z));
        }
        return moons;
    }

    private void doStep(List<Moon> moons) {
        for (Moon moon : moons) {
            for (Moon otherMoon : moons) {
                if (moon != otherMoon) {
                    moon.applyGravity(otherMoon);
                }
            }
        }
        moons.forEach(Moon::applyVelocity);
    }

    private String getState(List<Moon> moons, char axis) {
        Function<Moon, String> function =  m -> m.x + "," + m.vX;
        if (axis == 'y') {
            function =  m -> m.y + "," + m.vY;
        } else if (axis == 'z') {
            function =  m -> m.z + "," + m.vZ;
        }
        return moons.stream().map(function).collect(Collectors.joining(","));
    }

    void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    private static class Moon {

        private int x;
        private int y;
        private int z;
        private int vX = 0;
        private int vY = 0;
        private int vZ = 0;

        Moon(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private void applyGravity(Moon other) {
            if (x < other.x) {
                vX++;
            } else if (x > other.x) {
                vX--;
            }
            if (y < other.y) {
                vY++;
            } else if (y > other.y) {
                vY--;
            }
            if (z < other.z) {
                vZ++;
            } else if (z > other.z) {
                vZ--;
            }
        }

        private void applyVelocity() {
            x += vX;
            y += vY;
            z += vZ;
        }

        private int getEnergy() {
            return (Math.abs(x) + Math.abs(y) + Math.abs(z)) * (Math.abs(vX) + Math.abs(vY) + Math.abs(vZ));
        }
    }
}
