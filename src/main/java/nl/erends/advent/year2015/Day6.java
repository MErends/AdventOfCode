package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {
    public static void main(String[] args) throws Exception {
        int niceLine = 0;
        List<String> lines = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2015day6.txt");
        Lamp[][] lampGrid = new Lamp[1000][1000];
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                lampGrid[x][y] = new Lamp();
            }
        }

        for (String line : lines) {
            processLine(line, lampGrid);
        }

        int numLampsOn = 0;
        int totalBrightness = 0;
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
//                if (lampGrid[x][y].isOn()) {
//                    numLampsOn++;
//                }
                totalBrightness += lampGrid[x][y].getBrightness();
            }
        }

        System.out.print(totalBrightness);
    }



    static void processLine(String line, Lamp[][] lampGrid) {
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

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (operation.equals("on")) {
                    lampGrid[x][y].turnOn();
                } else if (operation.equals("off")) {
                    lampGrid[x][y].turnOff();
                } else {
                    lampGrid[x][y].toggle();
                }
            }
        }
    }
}







class Lamp {
    private boolean on = false;
    private int brightness = 0;

    public void toggle() {
//        on = !on;
        brightness += 2;
    }

    public void turnOff() {
//        on = false;
        if (brightness != 0) {
            brightness--;
        }
    }

    public void turnOn() {
//        on = true;
        brightness++;
    }

    public boolean isOn() {
        return on;
    }

    public int getBrightness() {
        return brightness;
    }
}