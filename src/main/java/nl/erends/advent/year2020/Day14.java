package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 extends AbstractProblem<List<String>, Long> {

    private static final Pattern MEM_PAT = Pattern.compile("mem\\[(\\d+)] = (\\d+)");
    private String mask;

    public static void main(String[] args) {
        new Day14().setAndSolve(Util.readInput(2020, 14));
    }

    @Override
    public Long solve1() {
        Map<Integer, Long> memory = new HashMap<>();
        for (String line : input) {
            Matcher m = MEM_PAT.matcher(line);
            if (m.find()) {
                int address = Integer.parseInt(m.group(1));
                long value = Long.parseLong(m.group(2));
                memory.put(address, maskValue(value));
            } else {
                mask = line.substring(7);
            }
        }
        return memory.values().stream().mapToLong(v -> v).sum();
    }

    @Override
    public Long solve2() {
        Map<Long, Long> memory = new HashMap<>();
        for (String line : input) {
            Matcher m = MEM_PAT.matcher(line);
            if (m.find()) {
                int address = Integer.parseInt(m.group(1));
                long value = Long.parseLong(m.group(2));
                List<Long> addressList = maskAddress(address);
                addressList.forEach(l -> memory.put(l, value));
            } else {
                mask = line.substring(7);
            }
        }
        return memory.values().stream().mapToLong(l -> l).sum();
    }

    private long maskValue(long valueLong) {
        StringBuilder value = new StringBuilder(Long.toBinaryString(valueLong));
        while (value.length() != mask.length()) {
            value.insert(0, '0');
        }
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < mask.length(); index++) {
            if (mask.charAt(index) == 'X') {
                result.append(value.charAt(index));
            } else {
                result.append(mask.charAt(index));
            }
        }
        return Long.parseLong(result.toString(), 2);
    }

    private List<Long> maskAddress(int addressInt) {
        StringBuilder address = new StringBuilder(Integer.toBinaryString(addressInt));
        while (address.length() != mask.length()) {
            address.insert(0, '0');
        }
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < mask.length(); index++) {
            if (mask.charAt(index) == '0') {
                result.append(address.charAt(index));
            } else {
                result.append(mask.charAt(index));
            }
        }
        List<String> stringValues = new ArrayList<>();
        stringValues.add(result.toString());
        List<Long> allValues = new ArrayList<>();
        while (!stringValues.isEmpty()) {
            String stringValue = stringValues.get(0);
            int xLocation = stringValue.indexOf('X');
            if (xLocation == -1) {
                allValues.add(Long.parseLong(stringValue, 2));
            } else {
                stringValues.add(stringValue.replaceFirst("X", "0"));
                stringValues.add(stringValue.replaceFirst("X", "1"));
            }
            stringValues.remove(0);
        }
        return allValues;
    }
}
