package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>--- Day 22: Sand Slabs ---</h1>
 * <p>Enough sand has fallen; it can finally filter water for Snow Island.The
 * sand has been falling as large compacted bricks of sand, piling up to form an
 * impressive stack here near the edge of Island Island. In order to make use of
 * the sand to filter water, some of the bricks will need to be disintegrated
 * back into freely flowing sand. Figure how the blocks will settle based on the
 * snapshot. Once they've settled, consider disintegrating a single brick; how
 * many bricks could be safely chosen as the one to get disintegrated?</p>
 * <p><a href="https://adventofcode.com/2023/day/22">2023 Day 22</a></p>
 */
public class Day22 extends AbstractProblem<List<String>, Number> {

    private static final Pattern LINE_PAT = Pattern.compile("(\\d+),(\\d+),(\\d+)~(\\d+),(\\d+),(\\d+)");
    private List<Brick> bricks;


    static void main() {
        new Day22().setAndSolve(Util.readInput(2023, 22));
    }

    @Override
    protected Integer solve1() {
        bricks = input.stream().map(Brick::new).toList();
        List<Brick> yetToDrop = new ArrayList<>(bricks);
        int z = 0;
        while (!yetToDrop.isEmpty()) {
            List<Brick> dropNow = new ArrayList<>();
            for (Brick dropBrick : yetToDrop) {
                if (dropBrick.z1 <= z && z <= dropBrick.z2) {
                    dropNow.add(dropBrick);
                }
            }
            for (Brick dropBrick : dropNow) {
                while (dropBrick.canDrop()) {
                    dropBrick.z1--;
                    dropBrick.z2--;
                }
                yetToDrop.remove(dropBrick);
            }


            z++;
        }
        return (int) bricks.stream()
                .filter(Brick::canZap).count();
    }

    @Override
    public Number solve2() {
        return bricks.stream()
                .mapToInt(Brick::getChainImpact).sum();
    }

    private class Brick {

        int x1;
        int x2;
        int y1;
        int y2;
        int z1;
        int z2;

        final Set<Brick> bricksBelow = new HashSet<>();
        final Set<Brick> bricksAbove = new HashSet<>();

        private Brick(String line) {
            Matcher m = LINE_PAT.matcher(line);
            if (!m.find()) throw new IllegalArgumentException();
            x1 = Integer.parseInt(m.group(1));
            x2 = Integer.parseInt(m.group(4));
            y1 = Integer.parseInt(m.group(2));
            y2 = Integer.parseInt(m.group(5));
            z1 = Integer.parseInt(m.group(3));
            z2 = Integer.parseInt(m.group(6));
        }

        boolean contains(int x, int y, int z) {
            return x1 <= x && x <= x2
                    && y1 <= y && y <= y2
                    && z1 <= z && z <= z2;
        }

        boolean canDrop() {
            boolean canDrop = true;
            if (z1 == 1) {
                return false;
            }
            int z = z1 - 1;
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    for (Brick brick : bricks) {
                        if (brick.contains(x, y, z)) {
                            bricksBelow.add(brick);
                            brick.bricksAbove.add(this);
                            canDrop = false;
                        }
                    }
                }
            }
            return canDrop;
        }

        boolean canZap() {
            return bricksAbove.stream()
                    .allMatch(above -> above.bricksBelow.size() > 1);
        }

        int getChainImpact() {
            Set<Brick> chainImpact = new HashSet<>();
            chainImpact.add(this);
            Set<Brick> checkBricks = new HashSet<>(bricksAbove);
            while (!checkBricks.isEmpty()) {
                Brick checkBrick = checkBricks.stream()
                        .min(Comparator.comparingInt(b -> b.z1)).orElseThrow();
                checkBricks.remove(checkBrick);
                if (chainImpact.containsAll(checkBrick.bricksBelow)) {
                    chainImpact.add(checkBrick);
                    checkBricks.addAll(checkBrick.bricksAbove);
                }
            }
            return chainImpact.size() - 1;
        }
    }
}
