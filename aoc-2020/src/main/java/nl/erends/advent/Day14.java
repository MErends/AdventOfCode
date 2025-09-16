package nl.erends.advent;

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
    private boolean part1 = true;

    static void main() {
        new Day14().setAndSolve(Util.readInput(2020, 14));
    }

    @Override
    public Long solve1() {
        Map<Long, Long> memory = new HashMap<>();
        for (String line : input) {
            Matcher m = MEM_PAT.matcher(line);
            if (m.find()) {
                long address = Long.parseLong(m.group(1));
                long value = Long.parseLong(m.group(2));
                if (part1) {
                    memory.put(address, maskValue(value));
                } else {
                    List<Long> addressList = maskAddress(address);
                    addressList.forEach(l -> memory.put(l, value));
                }
            } else {
                mask = line.substring(7);
            }
        }
        return memory.values().stream().mapToLong(v -> v).sum();
    }

    @Override
    public Long solve2() {
        part1 = false;
        return solve1();
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

    private List<Long> maskAddress(long addressLong) {
        StringBuilder address = new StringBuilder(Long.toBinaryString(addressLong));
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
        List<Long> resolved = new ArrayList<>();
        List<String> unresolved = new ArrayList<>();
        unresolved.add(result.toString());
        while (!unresolved.isEmpty()) {
            String addressX = unresolved.getFirst();
            int xLocation = addressX.indexOf('X');
            if (xLocation == -1) {
                resolved.add(Long.parseLong(addressX, 2));
            } else {
                unresolved.add(addressX.replaceFirst("X", "0"));
                unresolved.add(addressX.replaceFirst("X", "1"));
            }
            unresolved.removeFirst();
        }
        return resolved;
    }
}
