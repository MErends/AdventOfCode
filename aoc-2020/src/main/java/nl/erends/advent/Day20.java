package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day20 extends AbstractProblem<List<String>, Number> {

    private final List<Tile> tiles = new ArrayList<>();
    private Tile cornerTile;
    private Tile[][] tileGrid;
    private static final List<String> SEAMONSTER = Arrays.asList("                  # ",
                                                                 "#    ##    ##    ###",
                                                                 " #  #  #  #  #  #   ");
    private char[][] picture;

    static void main() {
        new Day20().setAndSolve(Util.readInput(2020, 20));
    }

    @Override
    public Long solve1() {
        int tileLength = input.indexOf("");
        for (int index = 0; index < input.size(); index += tileLength + 1) {
            tiles.add(new Tile(input.subList(index, index + tileLength)));
        }
        Map<String, Long> egdeMap = tiles.stream()
                .map(t -> t.allEdges).flatMap(Collection::stream).collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        List<Tile> corners = tiles.stream().filter(t -> t.edgeSum(egdeMap) == 12).sorted(Comparator.comparingInt(t -> t.id)).toList();
        cornerTile = corners.get(3);
        while (egdeMap.get(cornerTile.leftEdge) != 1 || egdeMap.get(cornerTile.topEdge) != 1) {
            cornerTile.rotate();
        }
        return corners.stream().mapToLong(t -> t.id).reduce(1L, (t1, t2) -> t1 * t2);
    }

    @Override
    public Integer solve2() {
        if (tiles.isEmpty()) {
            solve1();
        }
        int gridSize = (int) Math.sqrt(tiles.size());
        int pictureSize = gridSize * (cornerTile.tileGrid.length - 2);
        int subPictureSize = pictureSize / gridSize;
        tileGrid = new Tile[gridSize][gridSize];
        tileGrid[0][0] = cornerTile;
        tiles.remove(cornerTile);
        for (int y = 1; y < gridSize; y++) {
            tileGrid[y][0] = determineTile(y);
        }
        for (int y = 0; y < gridSize; y++) {
            for (int x = 1; x < gridSize; x++) {
                tileGrid[y][x] = determineTile(x, y);
            }
        }
        picture = createPicture(pictureSize, subPictureSize);
        int monstersFound = getMonstersFound();
        int roughWater = 0;
        for (char[] line : picture) {
            for (char c : line) {
                roughWater += c == '#' ? 1 : 0;
            }
        }
        int dragonsWorth = getDragonsWorth();
        return roughWater - dragonsWorth * monstersFound;
    }

    private int getDragonsWorth() {
        int dragonsWorth = 0;
        for (String line : SEAMONSTER) {
            for (char c : line.toCharArray()) {
                dragonsWorth += c == '#' ? 1 : 0;
            }
        }
        return dragonsWorth;
    }

    private int getMonstersFound() {
        int monstersFound = 0;
        for (int i = 0; i < 4 && monstersFound == 0; i++) {
            monstersFound = findMonsters(picture);
            picture = rotate(picture);
        }
        if (monstersFound == 0) {
            picture = mirror(picture);
            for (int i = 0; i < 4 && monstersFound == 0; i++) {
                monstersFound = findMonsters(picture);
                picture = rotate(picture);
            }
        }
        return monstersFound;
    }

    private char[][] createPicture(int pictureSize, int subPictureSize) {
        picture = new char[pictureSize][pictureSize];
        for (int y = 0; y < pictureSize; y++) {
            int tileY = y / subPictureSize;
            int subY = y % subPictureSize;
            for (int x = 0; x < pictureSize; x++) {
                int tileX = x / subPictureSize;
                int subX = x % subPictureSize;
                picture[y][x] = tileGrid[tileY][tileX].tileGrid[subY + 1][subX + 1];
            }
        }
        return picture;
    }

    private Tile determineTile(int y) {
        String targetEdge = tileGrid[y - 1][0].bottomEdge;
        Tile targetTile = tiles.stream().filter(t -> t.allEdges.contains(targetEdge)).findFirst().orElseThrow(IllegalStateException::new);
        for (int i = 0; i < 4; i++) {
            if (targetEdge.equals(targetTile.topEdge)) {
                break;
            }
            targetTile.rotate();
        }
        if (!targetEdge.equals(targetTile.topEdge)) {
            targetTile.mirror();
            for (int i = 0; i < 4; i++) {
                if (targetEdge.equals(targetTile.topEdge)) {
                    break;
                }
                targetTile.rotate();
            }
        }
        if (!targetEdge.equals(targetTile.topEdge)) {
            throw new IllegalStateException("Geen match gevonden!");
        }
        tiles.remove(targetTile);
        return targetTile;
    }

    private Tile determineTile(int x, int y) {
        String targetEdge = tileGrid[y][x - 1].rightEdge;
        Tile targetTile = tiles.stream().filter(t -> t.allEdges.contains(targetEdge)).findFirst().orElseThrow(IllegalStateException::new);
        for (int i = 0; i < 4; i++) {
            if (targetEdge.equals(targetTile.leftEdge)) {
                break;
            }
            targetTile.rotate();
        }
        if (!targetEdge.equals(targetTile.leftEdge)) {
            targetTile.mirror();
            for (int i = 0; i < 4; i++) {
                if (targetEdge.equals(targetTile.leftEdge)) {
                    break;
                }
                targetTile.rotate();
            }
        }
        tiles.remove(targetTile);
        return targetTile;
    }

    private int findMonsters(char[][] picture) {
        int monsterCount = 0;
        for (int y = 0; y < picture.length - SEAMONSTER.size(); y++) {
            for (int x = 0; x < picture[y].length - SEAMONSTER.getFirst().length(); x++) {
                if (monsterAt(x, y, picture)) {
                    monsterCount++;
                }
            }
        }
        return monsterCount;
    }

    private boolean monsterAt(int x, int y, char[][] picture) {
        for (int dy = 0; dy < SEAMONSTER.size(); dy++) {
            for (int dx = 0; dx < SEAMONSTER.get(dy).length(); dx++) {
                if (SEAMONSTER.get(dy).charAt(dx) == '#' && picture[y + dy][x + dx] != '#') {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Tile {
        final int id;
        char[][] tileGrid;
        String topEdge;
        String leftEdge;
        String rightEdge;
        String bottomEdge;
        final List<String> allEdges = new ArrayList<>();

        private Tile(List<String> lines) {
            id = Integer.parseInt(lines.getFirst().split(" ")[1].split(":")[0]);
            tileGrid = new char[lines.size() - 1][];
            for (int index = 1; index < lines.size(); index++) {
                tileGrid[index - 1] = lines.get(index).toCharArray();
            }
            topEdge = new String(tileGrid[0]);
            bottomEdge = new String(tileGrid[tileGrid.length - 1]);
            StringBuilder leftEdgeSB = new StringBuilder();
            StringBuilder rightEdgeSB = new StringBuilder();
            for (char[] line : tileGrid) {
                leftEdgeSB.append(line[0]);
                rightEdgeSB.append(line[line.length - 1]);
            }
            leftEdge = leftEdgeSB.toString();
            rightEdge = rightEdgeSB.toString();
            allEdges.add(topEdge);
            allEdges.add(leftEdge);
            allEdges.add(rightEdge);
            allEdges.add(bottomEdge);
            allEdges.add(new StringBuilder(topEdge).reverse().toString());
            allEdges.add(leftEdgeSB.reverse().toString());
            allEdges.add(rightEdgeSB.reverse().toString());
            allEdges.add(new StringBuilder(bottomEdge).reverse().toString());
        }

        private void rotate() {
            String tempEdge = topEdge;
            topEdge = new StringBuilder(leftEdge).reverse().toString();
            leftEdge = bottomEdge;
            bottomEdge = new StringBuilder(rightEdge).reverse().toString();
            rightEdge = tempEdge;
            tileGrid = Day20.rotate(tileGrid);
        }

        private void mirror() { // horizontal for ease
            String tempEdge = topEdge;
            topEdge = bottomEdge;
            bottomEdge = tempEdge;
            leftEdge = new StringBuilder(leftEdge).reverse().toString();
            rightEdge = new StringBuilder(rightEdge).reverse().toString();
            tileGrid = Day20.mirror(tileGrid);
        }

        private long edgeSum(Map<String, Long> egdeMap) {
            return allEdges.stream().map(egdeMap::get).mapToLong(l -> l).sum();
        }
    }

    private static char[][] rotate(char[][] orig) {
        int size = orig.length;
        char[][] temp = new char[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                temp[i][j] = orig[size - j - 1][i];
            }
        }
        return temp;
    }

    private static char[][] mirror(char[][] orig) {
        int size = orig.length;
        char[][] temp = new char[size][size];
        for (int i = 0; i < size; ++i) {
            temp[i] = orig[size - 1 - i];
        }
        return temp;
    }
}
