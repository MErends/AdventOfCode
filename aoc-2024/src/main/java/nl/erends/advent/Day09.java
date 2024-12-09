package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * <h1>--- Day 8: Resonant Collinearity ---</h1>
 * <p>You find yourselves on the roof of a top-secret Easter Bunny installation.
 * While The Historians do their thing, you take a look at the familiar huge
 * antenna. Each antenna is tuned to a specific frequency indicated by a single
 * lowercase letter, uppercase letter, or digit. Calculate the impact of the
 * signal. How many unique locations within the bounds of the map contain an
 * antinode?</p>
 * <p><a href="https://adventofcode.com/2024/day/8">2024 Day 8</a></p>
 */
public class Day09 extends AbstractProblem<String, Long> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readLine(2024, 9));
    }

    @Override
    protected Long solve1() {
        List<Integer> disk = readInputAsInteger();
        int indexA = 0;
        int indexB = disk.size() - 1;
        while (indexA < indexB) {
            if (disk.get(indexA) != -1) {
                indexA++;
            } else if (disk.get(indexB) == -1) {
                indexB--;
            } else {
                disk.set(indexA, disk.get(indexB));
                disk.set(indexB, -1);
                indexA++;
                indexB--;
            }
        }
        disk = disk.subList(0, indexA + 1);
        long result = 0;
        for (int position = 0; position < disk.size(); position++) {
            result += (long) position * disk.get(position);
        }
        return result;
    }

    private List<Integer> readInputAsInteger() {
        List<Integer> disk = new ArrayList<>();
        int id = 0;
        boolean emptyBlock = false;
        for (int i = 0; i < input.length(); i++) {
            int length = input.charAt(i) - ASCII_OFFSET;
            for (int j = 0; j < length; j++) {
                if (emptyBlock) {
                    disk.add(-1);
                } else {
                    disk.add(id);
                }
            }
            if (!emptyBlock) {
                id++;
            }
            emptyBlock = !emptyBlock;
        }
        return disk;
    }

    @Override
    public Long solve2() {
        List<Block> disk = new ArrayList<>();
        int id = 0;
        boolean emptyBlock = false;
        for (int i = 0; i < input.length(); i++) {
            int length = input.charAt(i) - ASCII_OFFSET;
            if (emptyBlock) {
                disk.add(new Block(-1, length));
            } else {
                disk.add(new Block(id++, length));
            }
            emptyBlock = !emptyBlock;
        }
        List<Block> newDisk = defrag(disk);
        return checksum(newDisk);
    }

    private List<Block> defrag(List<Block> disk) {
        List<Block> newDisk = new ArrayList<>();
        for (int i = disk.size() - 1; i >= 0; i--) {
            newDisk.add(disk.get(i));
        }
        for (int blockId = 0; blockId < newDisk.size(); blockId++) {
            Block currentBlock = newDisk.get(blockId);
            if (currentBlock.isEmpty()) {
                continue;
            }
            for (int targetId = newDisk.size() - 1; targetId > blockId; targetId--) {
                Block targetBlock = newDisk.get(targetId);
                if (!targetBlock.isEmpty() || targetBlock.length < currentBlock.length) {
                    continue;
                }
                int emptyLeft = targetBlock.length - currentBlock.length;
                targetBlock.id = currentBlock.id;
                targetBlock.length = currentBlock.length;
                currentBlock.id = -1;
                if (emptyLeft > 0) {
                    newDisk.add(targetId, new Block(-1, emptyLeft));
                }
                break;
            }
        }
        List<Block> okDisk = new ArrayList<>();
        for (int i = newDisk.size() - 1; i >= 0; i--) {
            okDisk.add(newDisk.get(i));
        }
        return okDisk;
    }

    private long checksum(List<Block> disk) {
        long checksum = 0L;
        long position = 0L;
        for (Block block : disk) {
            for (int i = 0; i < block.length; i++) {
                if (!block.isEmpty()) {
                    checksum += (i + position) * block.id;
                }
            }
            position += block.length;
        }
        return checksum;
    }

    private static class Block {
        int id;
        int length;

        public Block(int id, int length) {
            this.id = id;
            this.length = length;
        }

        boolean isEmpty() {
            return id == -1;
        }
    }
}
