package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 7: No Space Left On Device ---
 * <p>The device the Elves gave you has problems with more than just its
 * communication system. You try to run a system update but there is no space
 * left on the device. Perhaps you can delete some files to make space for the
 * update? Find all of the directories with a total size of at most 100000. What
 * is the sum of the total sizes of those directories, and what is the smallest
 * directory you can delete?
 * <p><a href="https://adventofcode.com/2022/day/7">2022 Day 7</a>
 */
public class Day07 extends AbstractProblem<List<String>, Integer> {

    private int index;
    private final Directory root = new Directory("", new HashMap<>(), new ArrayList<>(), null);
    private Directory pwd;

    static void main() {
        new Day07().setAndSolve(Util.readInput(2022, 7));
    }

    @Override
    protected Integer solve1() {
        while (index < input.size()) {
            String[] command = input.get(index).substring(2).split(" ");
            if ("cd".equals(command[0])) {
                switch (command[1]) {
                    case "/" -> pwd = root;
                    case ".." -> pwd = pwd.parent();
                    default -> pwd = pwd.directories().get(command[1]);
                }
                index++;
            } else { // must be "ls"
                index++;
                fillDirectory();
            }
        }
        int toDelete = 30000000 - (70000000 - root.totalSize());
        answer2 = root.getAllDirectories().stream()
                .mapToInt(Directory::totalSize)
                .filter(s -> s >= toDelete)
                .reduce(Integer.MAX_VALUE, Math::min);

        return root.getAllDirectories().stream()
                .mapToInt(Directory::totalSize)
                .filter(s -> s <= 100000)
                .sum();
    }

    private void fillDirectory() {
        while (index < input.size() && !input.get(index).startsWith("$")) {
            String[] command = input.get(index).split(" ");
            if ("dir".equals(command[0])) {
                pwd.directories().put(command[1], new Directory(command[1], new HashMap<>(), new ArrayList<>(), pwd));
            } else { // must be file
                pwd.files.add(Integer.parseInt(command[0]));
            }
            index++;
        }
    }

    private record Directory(String name, Map<String, Directory> directories, List<Integer> files, Directory parent) {

        List<Directory> getAllDirectories() {
            List<Directory> allDirectories = new ArrayList<>();
            allDirectories.add(this);
            directories().values().forEach(d -> allDirectories.addAll(d.getAllDirectories()));
            return allDirectories;
        }

        int totalSize() {
            int totalSize = files().stream().mapToInt(i -> i).sum();
            totalSize += directories().values().stream().mapToInt(Directory::totalSize).sum();
            return totalSize;
        }
    }
}
