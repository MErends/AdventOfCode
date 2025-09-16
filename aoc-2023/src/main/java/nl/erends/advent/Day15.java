package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * <h1>--- Day 15: Lens Library ---</h1>
 * <p>The newly-focused parabolic reflector dish is sending all of the collected
 * light to a point on the side of yet another mountain. The book goes on to
 * describe a series of 256 boxes numbered 0 through 255. The boxes are arranged
 * in a line starting from the point where light enters the facility. The boxes
 * have holes that allow light to pass from one box to the next all the way down
 * the line. Inside each box, there are several lens slots that will keep a lens
 * correctly positioned to focus light passing through the box. What is the
 * focusing power of the resulting lens configuration?</p>
 * <p><a href="https://adventofcode.com/2023/day/15">2023 Day 15</a></p>
 */
public class Day15 extends AbstractProblem<List<String>, Number> {


    static void main() {
        new Day15().setAndSolve(Util.readInput(2023, 15));
    }

    @Override
    protected Number solve1() {
        return Arrays.stream(input.getFirst().split(",")).mapToInt(this::hash).sum();
    }

    private int hash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            hash += input.charAt(i);
            hash *= 17;
            hash %= 256;
        }
        return hash;
    }

    @Override
    public Number solve2() {
        List<Box> boxes = IntStream.range(0, 256)
                .mapToObj(_ -> new Box()).toList();
        String[] sequence = input.getFirst().split(",");
        for (String instruction : sequence) {
            int equals = instruction.indexOf('=');
            int minus = instruction.indexOf('-');
            if (equals != -1) {
                int focal = Integer.parseInt(instruction.substring(equals + 1));
                Lens lens = new Lens(instruction.substring(0, equals), focal);
                int box = hash(lens.name);
                boxes.get(box).placeLens(lens);
            } else {
                String name = instruction.substring(0, minus);
                int box = hash(name);
                boxes.get(box).remove(name);
            }
        }
        int totalPower = 0;
        for (int boxID = 0; boxID < boxes.size(); boxID++) {
            Box box = boxes.get(boxID);
            for (int lensID = 0; lensID < box.lenses.size(); lensID++) {
                Lens lens = box.lenses.get(lensID);
                totalPower += (boxID + 1) * (lensID + 1) * lens.focal;
            }
        }
        return totalPower;
    }

    private record Lens(String name, int focal) {
    }

    private static class Box {
        final List<Lens> lenses = new ArrayList<>();

        private void remove(String name) {
            lenses.removeIf(lens -> lens.name.equals(name));
        }

        private void placeLens(Lens lens) {
            Optional<Lens> optionalLens = lenses.stream()
                    .filter(oldLens -> oldLens.name.equals(lens.name))
                    .findFirst();
            if (optionalLens.isPresent()) {
                int index = lenses.indexOf(optionalLens.get());
                lenses.set(index, lens);
            } else {
                lenses.add(lens);
            }
        }
    }
}
