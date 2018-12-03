package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Day03 {
    
    private static final int SIZE = 1000;
    private static String[][] cloth;
    private static List<String> ids = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day03.txt");
        long start = System.currentTimeMillis();
        
        cloth = new String[SIZE][SIZE];
        for (String line : input) {
            String id = line.split("#")[1].split(" ")[0];
            String x0 = line.split(" ")[2].split(",")[0];
            String y0 = line.split(",")[1].split(":")[0];
            String dx = line.split(" ")[3].split("x")[0];
            String dy = line.split("x")[1];
            ids.add(id);
            loadToCloth(id, Integer.parseInt(x0), Integer.parseInt(y0), Integer.parseInt(dx), Integer.parseInt(dy));
        }
        System.out.println(countOverlap());
        System.out.println(ids);
        long end = System.currentTimeMillis();
        System.out.println("Part 1 & 2: " + (end - start) + " millis.");
    }
    
    private static int countOverlap() {
        int overlap = 0;
        for (String[] line : cloth) {
            for (String squareInch : line) {
                if (squareInch != null && squareInch.contains(",")) {
                    overlap++;
                    String[] overlappers = squareInch.split(",");
                    for (String id : overlappers) {
                        ids.remove(id);
                    }
                }
            }
        }
        return overlap;
    }
    
    private static void loadToCloth(String id, int x0, int y0, int dx, int dy) {
        for (int x = x0; x < x0 + dx; x++) {
            for (int y = y0; y < y0 + dy; y++) {
                String squareInch = cloth[x][y];
                if (squareInch != null) {
                    cloth[x][y] = squareInch + "," + id;
                } else {
                    cloth[x][y] = id;
                }
            }
        }
    }
}
