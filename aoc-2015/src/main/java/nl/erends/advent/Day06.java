package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

public class Day06 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readInput(2015, 6));
    }

    @Override
    public Integer solve1() {
        Lamp[][] lampGrid = new Lamp[1000][1000];
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                lampGrid[x][y] = new Lamp();
            }
        }

        for (String line : input) {
            processLine(line, lampGrid);
        }

        int numLampsOn = 0;
        int totalBrightness = 0;
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                if (lampGrid[x][y].isOn()) {
                    numLampsOn++;
                }
                totalBrightness += lampGrid[x][y].getBrightness();
            }
        }
        answer2 = totalBrightness;
        return numLampsOn;
    }

    private void processLine(String line, Lamp[][] lampGrid) {
        String operation;
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        boolean minFound = false;
        List<String> words = Arrays.asList(line.split(" "));
        if (words.contains("on")) {
            operation = "on";
        } else if (words.contains("off")) {
            operation = "off";
        } else {
            operation = "toggle";
        }
        for (String word : words) {
            if (word.contains(",")) {
                String[] coords = word.split(",");
                if (!minFound) {
                    minX = Integer.parseInt(coords[0]);
                    minY = Integer.parseInt(coords[1]);
                    minFound = true;
                } else {
                    maxX = Integer.parseInt(coords[0]);
                    maxY = Integer.parseInt(coords[1]);
                }
            }
        }
        executeLine(minX, minY, maxX, maxY, operation, lampGrid);
    }

    private void executeLine(int minX, int minY, int maxX, int maxY, String operation, Lamp[][] lampGrid) {
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                switch (operation) {
                    case "on" -> {
                        lampGrid[x][y].turnOn();
                        lampGrid[x][y].upBrightness();
                    }
                    case "off" -> {
                        lampGrid[x][y].turnOff();
                        lampGrid[x][y].lowerBrightness();
                    }
                    default -> {
                        lampGrid[x][y].toggle();
                        lampGrid[x][y].upBrightness();
                        lampGrid[x][y].upBrightness();
                    }
                }
            }
        }
    }
    
    private static class Lamp {
        private boolean on = false;
        private int brightness = 0;

        void toggle() {
            on = !on;
        }

        void turnOff() {
            on = false;
        }

        void turnOn() {
            on = true;
        }

        void upBrightness() {
            brightness++;
        }

        void lowerBrightness() {
            brightness--;
        }

        boolean isOn() {
            return on;
        }

        int getBrightness() {
            return brightness;
        }
    }
}
