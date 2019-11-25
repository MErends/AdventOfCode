package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day03 extends AbstractProblem<List<String>, Integer> {

    private String[][] cloth;
    private List<String> ids = new ArrayList<>();

    public static void main(String[] args) {
        new Day03().setAndSolve(Util.readInput(2018, 3));
    }
    
    @Override
    public Integer solve1() {
        int xSize = -1;
        int ySize = -1;
        for (String line : input) {
            int x0 = Integer.parseInt(line.split(" ")[2].split(",")[0]);
            int y0 = Integer.parseInt(line.split(",")[1].split(":")[0]);
            int dx = Integer.parseInt(line.split(" ")[3].split("x")[0]);
            int dy = Integer.parseInt(line.split("x")[1]);
            xSize = Math.max(xSize, x0 + dx);
            ySize = Math.max(ySize, y0 + dy);
        }
        cloth = new String[ySize][xSize];
        for (String line : input) {
            String id = line.split("#")[1].split(" ")[0];
            int x0 = Integer.parseInt(line.split(" ")[2].split(",")[0]);
            int y0 = Integer.parseInt(line.split(",")[1].split(":")[0]);
            int dx = Integer.parseInt(line.split(" ")[3].split("x")[0]);
            int dy = Integer.parseInt(line.split("x")[1]);
            ids.add(id);
            loadToCloth(id, x0, y0, dx, dy);
        }
        int answer1 = countOverlap();
        answer2 = Integer.valueOf(ids.get(0));
        return answer1;
    }
    
    private int countOverlap() {
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
    
    private void loadToCloth(String id, int x0, int y0, int dx, int dy) {
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
