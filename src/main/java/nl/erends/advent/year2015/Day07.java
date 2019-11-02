package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day07 implements Problem<List<String>, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day07.class);
    private Map<String, Integer> wires = new HashMap<>();
    private int valueA;
    
    public static void main(String[] args) {
        List<String> input = Util.readInput(2015, 7);
        Day07 problem = new Day07();
        Timer.start1();
        LOG.info(problem.solve1(input));
        Timer.end1();
        LOG.info(problem.solve2(input));
        Timer.printStats();
    }
    
    public Integer solve1(List<String> input) {
        while (getWire("a") == -1) {
            for (String line : input) {
                String[] words = line.split(" ");
                if (line.contains("AND")) {
                    processAnd(words[0], words[2], words[4]);
                } else if (line.contains("OR")) {
                    processOr(words[0], words[2], words[4]);
                } else if (line.contains("LSHIFT")) {
                    processLshift(words[0], words[2], words[4]);
                } else if (line.contains("RSHIFT")) {
                    processRshift(words[0], words[2], words[4]);
                } else if (line.contains("NOT")) {
                    processNot(words[1], words[3]);
                } else {
                    processAssign(words[0], words[2]);
                }
            }
        }
        valueA = getWire("a");
        return valueA;
    }
    
    public Integer solve2(List<String> input) {
        Timer.start2();
        if (valueA == 0) {
            solve1(input);
        }
        wires = new HashMap<>();
        wires.put("b", valueA);
        valueA = solve1(input);
        Timer.end2();
        return valueA;
    }
    
    private void processAnd(String input1, String input2, String output) {
        int signal1 = getWire(input1);
        int signal2 = getWire(input2);
        if (signal1 != -1 && signal2 != -1) {
            setWire(output, getWire(input1) & getWire(input2));
        }
    }

    private void processOr(String input1, String input2, String output) {
        int signal1 = getWire(input1);
        int signal2 = getWire(input2);
        if (signal1 != -1 || signal2 != -1) {
            setWire(output, getWire(input1) | getWire(input2));
        }
    }

    private void processLshift(String input1, String input2, String output) {
        int signal1 = getWire(input1);
        int signal2 = getWire(input2);
        if (signal1 != -1 && signal2 != -1) {
            setWire(output, getWire(input1) << getWire(input2));
        }
    }

    private void processRshift(String input1, String input2, String output) {
        int signal1 = getWire(input1);
        int signal2 = getWire(input2);
        if (signal1 != -1 && signal2 != -1) {
            setWire(output, getWire(input1) >> getWire(input2));
        }
    }

    private void processNot(String input, String output) {
        int signal = getWire(input);
        if (signal != -1) {
            setWire(output, ~getWire(input) & 0xffff);
        }
    }
    
    private void processAssign(String input, String output) {
        setWire(output, getWire(input));
    }
    
    private int getWire(String input) {
        if (wires.containsKey(input)) {
            return wires.get(input);
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void setWire(String key, int value) {
        if (!"b".equals(key) || valueA == 0) {
            wires.put(key, value);
        }
    }
}
