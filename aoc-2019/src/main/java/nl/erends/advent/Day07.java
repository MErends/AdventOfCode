package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 extends AbstractProblem<String, Long> {
    
    private long signal = Long.MIN_VALUE;
    private long feedback = Long.MIN_VALUE;

    public static void main(String[] args) {
        new Day07().setAndSolve(Util.readLine(2019, 7));
    }

    @Override
    public Long solve1() {
        determineSignal(Arrays.asList(0, 1, 2, 3, 4), 0);
        return signal;
    }

    @Override
    public Long solve2() {
        List<Character> phaseSettings = Arrays.asList('5', '6', '7', '8', '9');
        determineFeedback(phaseSettings, "");
        return feedback;
    }

    private void determineSignal(List<Integer> phaseSettings, long inputSignal) {
        if (phaseSettings.isEmpty()) {
            signal = Math.max(inputSignal, signal);
            return;
        }
        for (Integer phaseSetting : phaseSettings) {
            Intcode intcode = new Intcode(input);
            intcode.addInput(phaseSetting);
            intcode.addInput(inputSignal);
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
    
    private long feedbackSignal(String sequence) {
        Intcode amplifierA = new Intcode(input);
        amplifierA.addInput(Integer.parseInt("" + sequence.charAt(0)));
        Intcode amplifierB = new Intcode(input);
        amplifierB.addInput(Integer.parseInt("" + sequence.charAt(1)));
        Intcode amplifierC = new Intcode(input);
        amplifierC.addInput(Integer.parseInt("" + sequence.charAt(2)));
        Intcode amplifierD = new Intcode(input);
        amplifierD.addInput(Integer.parseInt("" + sequence.charAt(3)));
        Intcode amplifierE = new Intcode(input);
        amplifierE.addInput(Integer.parseInt("" + sequence.charAt(4)));
        long finalSignal = 0;
        amplifierA.addInput(finalSignal);
        while (true) {
            amplifierA.execute();
            if (amplifierA.isHalted()) {
                return finalSignal;
            }
            amplifierB.addInput(amplifierA.getOutput());
            amplifierB.execute();
            
            amplifierC.addInput(amplifierB.getOutput());
            amplifierC.execute();

            amplifierD.addInput(amplifierC.getOutput());
            amplifierD.execute();

            amplifierE.addInput(amplifierD.getOutput());
            amplifierE.execute();
            
            finalSignal = amplifierE.getOutput();
            amplifierA.addInput(finalSignal);
        }
    }
}
