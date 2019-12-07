package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 extends AbstractProblem<String, Integer> {
    
    private int signal = Integer.MIN_VALUE;
    private int feedback = Integer.MIN_VALUE;

    public static void main(String[] args) {
        new Day07().setAndSolve(Util.readLine(2019, 7));
    }

    @Override
    public Integer solve1() {
        determineSignal(Arrays.asList(0, 1, 2, 3, 4), 0);
        return signal;
    }

    @Override
    public Integer solve2() {
        List<Character> phaseSettings = Arrays.asList('5', '6', '7', '8', '9');
        determineFeedback(phaseSettings, "");
        return feedback;
    }

    private void determineSignal(List<Integer> phaseSettings, int inputSignal) {
        if (phaseSettings.isEmpty()) {
            signal = Math.max(inputSignal, signal);
            return;
        }
        for (Integer phaseSetting : phaseSettings) {
            Intcode intcode = new Intcode(input);
            intcode.getInput().add(phaseSetting);
            intcode.getInput().add(inputSignal);
            intcode.execute();
            List<Integer> newPhaseSettings = new ArrayList<>(phaseSettings);
            newPhaseSettings.remove(phaseSetting);
            determineSignal(newPhaseSettings, intcode.getOutput());
        }
    }
    
    private void determineFeedback(List<Character> phaseSettings, String sequence) {
        if (phaseSettings.isEmpty()) {
            feedback = Math.max(feedback, feedbackSignal(sequence));
            return;
        }
        for (Character phaseSetting : phaseSettings) {
            List<Character> newPhaseSettings = new ArrayList<>(phaseSettings);
            newPhaseSettings.remove(phaseSetting);
            determineFeedback(newPhaseSettings, sequence + phaseSetting);
        }
    }
    
    private int feedbackSignal(String sequence) {
        Intcode amplifierA = new Intcode(input);
        amplifierA.getInput().add(Integer.valueOf("" + sequence.charAt(0)));
        Intcode amplifierB = new Intcode(input);
        amplifierB.getInput().add(Integer.valueOf("" + sequence.charAt(1)));
        Intcode amplifierC = new Intcode(input);
        amplifierC.getInput().add(Integer.valueOf("" + sequence.charAt(2)));
        Intcode amplifierD = new Intcode(input);
        amplifierD.getInput().add(Integer.valueOf("" + sequence.charAt(3)));
        Intcode amplifierE = new Intcode(input);
        amplifierE.getInput().add(Integer.valueOf("" + sequence.charAt(4)));
        int finalSignal = 0;
        amplifierA.getInput().add(finalSignal);
        while (true) {
            amplifierA.execute();
            if (amplifierA.isHalted()) {
                return finalSignal;
            }
            amplifierB.getInput().add(amplifierA.getOutput());
            amplifierB.execute();
            
            amplifierC.getInput().add(amplifierB.getOutput());
            amplifierC.execute();

            amplifierD.getInput().add(amplifierC.getOutput());
            amplifierD.execute();

            amplifierE.getInput().add(amplifierD.getOutput());
            amplifierE.execute();
            
            finalSignal = amplifierE.getOutput();
            amplifierA.getInput().add(finalSignal);
        }
    }
}
