package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 extends AbstractProblem<List<String>, Integer> {

    private static List<String> buttonPresses = new ArrayList<>();

    void main() {
        new Day10().setAndSolve(Util.readInput(2025, 10));
    }

    @Override
    protected Integer solve1() {
        List<Blinken> blinkens = new ArrayList<>();
        input.forEach(l -> blinkens.add(new Blinken(l)));
        int mostButtons = blinkens.stream().mapToInt(b -> b.buttons.size()).max().orElseThrow();
        int options = 1;
        for (int button = 0; button < mostButtons; button++) {
            options *=2;
        }
        for (int i = 0; i < options; i++) {
            buttonPresses.add(Integer.toBinaryString(i));
        }
        buttonPresses.sort((s1, s2) -> countOnes(s1) - countOnes(s2));
        int totalPresses = 0;
        for (Blinken machine : blinkens) {
            for (String press : buttonPresses) {
                if (press.length() > machine.buttons.size()) continue;
                machine.reset();
                for (int buttonIndex = 0; buttonIndex < machine.buttons.size(); buttonIndex++) {
                    int pressIndex = press.length() - machine.buttons.size() + buttonIndex;
                    boolean pressButton = pressIndex >= 0 && press.charAt(pressIndex) == '1';
                    if (pressButton) {
                        machine.pressButton(machine.buttons.get(buttonIndex));
                    }
                }
                if (Arrays.equals(machine.lights, machine.target)) {
                    totalPresses += countOnes(press);
                    break;
                }
            }
        }

        return totalPresses;
    }

    @Override
    public Integer solve2() {
        List<Blinken> blinkens = new ArrayList<>();
        input.forEach(l -> blinkens.add(new Blinken(l)));
        int sumPresses = 0;
        int blinkenNr = 0;
        for(Blinken b : blinkens) {
            LOG.info("Blinken {} from {}", ++blinkenNr, blinkens.size());
            b.calculateJoltPresses(0, new int[b.buttons.size()]);
            sumPresses += b.minJoltPresses;
        }
        return sumPresses;
    }

    private int countOnes(String binaryNumber) {
        int ones = 0;
        for (char c : binaryNumber.toCharArray()) {
            if (c == '1') {
                ones++;
            }
        }
        return ones;
    }

    private static class Blinken {

        char[] target;
        List<Button> buttons;
        int[] targetJoltage;

        char[] lights;
        int minJoltPresses = Integer.MAX_VALUE;

        Blinken(String line) {
            List<String> config = new ArrayList<>(Arrays.asList(line.split(" ")));
            String targetString = config.removeFirst();
            target = targetString.substring(1, targetString.length() - 1).toCharArray();
            String joltageString = config.removeLast();
            String[] joltageSplit = joltageString.substring(1, joltageString.length() - 1).split(",");
            targetJoltage = new int[joltageSplit.length];
            for (int i = 0; i < joltageSplit.length; i++) {
                targetJoltage[i] = Integer.parseInt(joltageSplit[i]);
            }
            buttons = new ArrayList<>();
            config.forEach(c -> buttons.add(new Button(c)));
        }

        private void reset() {
            lights = new char[target.length];
            Arrays.fill(lights, '.');
        }

        private void pressButton(Button button) {
            for (int index : button.indeces) {
                lights[index] = lights[index] == '.' ? '#' : '.';
            }
        }
        
        private void calculateJoltPresses(int currentButton, int[] presses) {
            int[] joltage =  calculateJoltage(presses);
            int currentPresses = Arrays.stream(presses).sum();
            if (Arrays.equals(joltage, targetJoltage) || currentPresses > minJoltPresses) {
                if (currentPresses < minJoltPresses) {
                    LOG.info("Found a min at {} presses", currentPresses);
                }
                minJoltPresses = Math.min(minJoltPresses, currentPresses);
                return;
            }
            if (currentButton == buttons.size()) return;
            int[] nextPresses = Arrays.copyOf(presses, presses.length);
            LOG.info(Arrays.toString(nextPresses));
            while (!isOverJoltage(calculateJoltage(nextPresses)) && Arrays.stream(nextPresses).sum() < minJoltPresses) {
                calculateJoltPresses(currentButton + 1, nextPresses);
                nextPresses[currentButton] += 1;
            }
        }
        
        private int[] calculateJoltage(int[] presses) {
            int[] joltage = new int[targetJoltage.length];
            for (int buttonIndex = 0; buttonIndex < presses.length; buttonIndex++) {
                Button button = buttons.get(buttonIndex);
                for (int press = 0; press < presses[buttonIndex]; press++) {
                    for (int index : button.indeces) {
                        joltage[index]++;
                    }
                }
            }
            return joltage;
        }
        private boolean isOverJoltage(int[] joltage) {
            for (int index = 0; index < targetJoltage.length; index++) {
                if (joltage[index] > targetJoltage[index]) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class Button {

        List<Integer> indeces;

        Button(String line) {
            indeces = Arrays.stream(line.substring(1, line.length() - 1).split(","))
                    .map(Integer::parseInt).toList();
        }
    }
}
