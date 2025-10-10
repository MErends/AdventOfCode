package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static nl.erends.advent.Day20.PulseType.HIGH;
import static nl.erends.advent.Day20.PulseType.LOW;
/**
 * <h1>--- Day 20: Pulse Propagation ---</h1>
 * <p>With your help, the Elves manage to find the right parts and fix all of
 * the machines. Now, they just need to send the command to boot up the machines
 * and get the sand flowing again. Modules communicate using pulses. Each pulse
 * is either a high pulse or a low pulse. When a module sends a pulse, it sends
 * that type of pulse to each module in its list of destination modules. What do
 * you get if you multiply the total number of low pulses sent by the total
 * number of high pulses sent?</p>
 * <p><a href="https://adventofcode.com/2023/day/20">2023 Day 20</a></p>
 */
public class Day20 extends AbstractProblem<List<String>, Long> {

    final Map<String, Module> modules = new HashMap<>();
    final List<Pulse> pulses = new ArrayList<>();
    private int push;
    private long answer1;
    private int highPulseCount;
    private int lowPulseCount;
    private final List<String> lowPulsesNeeded = new ArrayList<>();

    void main() {
        new Day20().setAndSolve(Util.readInput(2023, 20));
    }

    @Override
    protected Long solve1() {
        for (String line : input) {
            if (line.isBlank()) continue;
            String[] lineSplit = line.split(" -> ");
            String name = lineSplit[0];
            String outputs = lineSplit[1];
            Module module = switch (name.charAt(0)) {
                case '%' -> new FlipFlop(name.substring(1), outputs);
                case '&' -> new Conjuction(name.substring(1), outputs);
                default ->  new Broadcaster(name, outputs);
            };
            modules.put(module.name, module);
        }
        modules.values().stream()
                .filter(Conjuction.class::isInstance)
                .map(m -> (Conjuction) m)
                .forEach(Conjuction::compileInputs);
        answer2 = 1L;
        modules.values().stream()
                .filter(m -> m.outputs.contains("rx"))
                .map(m -> (Conjuction) m)
                .findFirst()
                .ifPresent(conjuction -> lowPulsesNeeded.addAll(conjuction.inputs));
        while (!lowPulsesNeeded.isEmpty() || push < 1000) {
            doPush();
        }
        return answer1;
    }

    private void doPush() {
        push++;
        pulses.add(new Pulse("button", LOW, "broadcaster"));
        while (!pulses.isEmpty()) {
            Pulse pulse = pulses.removeFirst();
            if (pulse.type == LOW) {
                if (lowPulsesNeeded.contains(pulse.toModule)) {
                    answer2 = Util.lcm(answer2, push);
                    lowPulsesNeeded.remove(pulse.toModule);
                }
                lowPulseCount++;
            } else {
                highPulseCount++;
            }
            try {
                modules.get(pulse.toModule).processPulse(pulse);
            } catch (NullPointerException _) {
                // Must be rx or debug
            }
        }
        if (push == 1000) {
            answer1 = (long) lowPulseCount * highPulseCount;
        }
    }

    private abstract static class Module {
        final String name;
        final List<String> outputs;
        abstract void processPulse(Pulse pulse);

        public Module(String name, String outputs) {
            this. name = name;
            this.outputs = Arrays.stream(outputs.split(", ")).toList();
        }
    }

    private class FlipFlop extends Module {
        boolean isOn = false;

        public FlipFlop(String name, String outputs) {
            super(name, outputs);
        }

        @Override
        void processPulse(Pulse pulse) {
            if (pulse.type == LOW) {
                isOn = !isOn;
                PulseType type = isOn ? HIGH : LOW;
                for (String output : outputs) {
                    pulses.add(new Pulse(name, type, output));
                }
            }
        }
    }

    private class Conjuction extends Module {

        final List<String> inputs = new ArrayList<>();
        final Set<String> highPulses = new HashSet<>();

        public Conjuction(String name, String outputs) {
            super(name, outputs);
        }

        @Override
        void processPulse(Pulse pulse) {
            if (pulse.type == HIGH) {
                highPulses.add(pulse.fromModule);
            } else {
                highPulses.remove(pulse.fromModule);
            }
            PulseType type = inputs.size() == highPulses.size() ? LOW : HIGH;
            for (String output : outputs) {
                pulses.add(new Pulse(name, type, output));
            }
        }

        void compileInputs() {
            for (Module module : modules.values()) {
                if (module.outputs.contains(name)) {
                    inputs.add(module.name);
                }
            }
        }
    }

    private class Broadcaster extends Module {

        public Broadcaster(String name, String outputs) {
            super(name, outputs);
        }

        @Override
        void processPulse(Pulse pulse) {
            for (String output : outputs) {
                pulses.add(new Pulse(name, LOW, output));
            }
        }
    }


    private record Pulse(String fromModule, PulseType type, String toModule) {
    }

    enum PulseType {
        HIGH,
        LOW
    }
}


