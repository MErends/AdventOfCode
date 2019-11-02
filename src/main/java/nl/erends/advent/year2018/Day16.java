package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {


    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day16.txt");
        long start = System.currentTimeMillis();
        int ops3OrMore = 0;
        Map<String, Set<Integer>> possibleOpMap = new HashMap<>();
        possibleOpMap.put("addr", new HashSet<>());
        possibleOpMap.put("addi", new HashSet<>());
        possibleOpMap.put("mulr", new HashSet<>());
        possibleOpMap.put("muli", new HashSet<>());
        possibleOpMap.put("banr", new HashSet<>());
        possibleOpMap.put("bani", new HashSet<>());
        possibleOpMap.put("borr", new HashSet<>());
        possibleOpMap.put("bori", new HashSet<>());
        possibleOpMap.put("setr", new HashSet<>());
        possibleOpMap.put("seti", new HashSet<>());
        possibleOpMap.put("gtir", new HashSet<>());
        possibleOpMap.put("gtri", new HashSet<>());
        possibleOpMap.put("gtrr", new HashSet<>());
        possibleOpMap.put("eqir", new HashSet<>());
        possibleOpMap.put("eqri", new HashSet<>());
        possibleOpMap.put("eqrr", new HashSet<>());
        int beforeLineIndex = 0;
        for (; ; beforeLineIndex += 4) {
            if (!input.get(beforeLineIndex).contains("Before")) break;
            int matches = 0;
            Memorybank before = new Memorybank(input.get(beforeLineIndex).split(":")[1].trim());
            List<Integer> operand = Arrays.stream(input.get(beforeLineIndex + 1).split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int opcode = operand.get(0);
            int a = operand.get(1);
            int b = operand.get(2);
            int c = operand.get(3);
            Memorybank after = new Memorybank(input.get(beforeLineIndex + 2).split(":")[1].trim());
            Memorybank calculated = addr(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("addr").add(opcode);
            }
            calculated = addi(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("addi").add(opcode);
            }
            calculated = mulr(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("mulr").add(opcode);
            }
            calculated = muli(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("muli").add(opcode);
            }
            calculated = banr(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("banr").add(opcode);
            }
            calculated = bani(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("bani").add(opcode);
            }
            calculated = borr(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("borr").add(opcode);
            }
            calculated = bori(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("bori").add(opcode);
            }
            calculated = setr(before, a, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("setr").add(opcode);
            }
            calculated = seti(before, a, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("seti").add(opcode);
            }
            calculated = gtir(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("gtir").add(opcode);
            }
            calculated = gtri(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("gtri").add(opcode);
            }
            calculated = gtrr(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("gtrr").add(opcode);
            }
            calculated = eqir(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("eqir").add(opcode);
            }
            calculated = eqri(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("eqri").add(opcode);
            }
            calculated = eqrr(before, a, b, c);
            if (after.equals(calculated)) {
                matches++;
                possibleOpMap.get("eqrr").add(opcode);
            }
            if (matches >= 3) ops3OrMore++;
        }
        System.out.println(ops3OrMore);
        long mid = System.currentTimeMillis();
        Map<Integer, String> opMap = new HashMap<>(); 
        while (!possibleOpMap.isEmpty()) {
            for (Map.Entry<String, Set<Integer>> entry : possibleOpMap.entrySet()) {
                if (entry.getValue().size() == 1) {
                    int op = new ArrayList<>(entry.getValue()).get(0);
                    opMap.put(op, entry.getKey());
                    possibleOpMap.remove(entry.getKey());
                    for (Set<Integer> values : possibleOpMap.values()) {
                        values.remove(op);
                    }
                    break; 
                }
            }
        }
//        System.out.println(opMap);
        Memorybank memorybank = new Memorybank("[0, 0, 0, 0]");
        for ( ; beforeLineIndex < input.size(); beforeLineIndex++) {
            String opLine = input.get(beforeLineIndex);
            if (opLine.isEmpty()) continue;
            List<Integer> operand = Arrays.stream(opLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int opcode = operand.get(0);
            int a = operand.get(1);
            int b = operand.get(2);
            int c = operand.get(3);
            String opString = opMap.get(opcode);
            switch (opString) {
                case "addr":
                    memorybank = addr(memorybank, a, b, c);
                    break;
                case "addi":
                    memorybank = addi(memorybank, a, b, c);
                    break;
                case "mulr":
                    memorybank = mulr(memorybank, a, b, c);
                    break;
                case "muli":
                    memorybank = muli(memorybank, a, b, c);
                    break;
                case "banr":
                    memorybank = banr(memorybank, a, b, c);
                    break;
                case "bani":
                    memorybank = bani(memorybank, a, b, c);
                    break;
                case "borr":
                    memorybank = borr(memorybank, a, b, c);
                    break;
                case "bori":
                    memorybank = bori(memorybank, a, b, c);
                    break;
                case "setr":
                    memorybank = setr(memorybank, a, c);
                    break;
                case "seti":
                    memorybank = seti(memorybank, a, c);
                    break;
                case "gtir":
                    memorybank = gtir(memorybank, a, b, c);
                    break;
                case "gtri":
                    memorybank = gtri(memorybank, a, b, c);
                    break;
                case "gtrr":
                    memorybank = gtrr(memorybank, a, b, c);
                    break;
                case "eqir":
                    memorybank = eqir(memorybank, a, b, c);
                    break;
                case "eqri":
                    memorybank = eqri(memorybank, a, b, c);
                    break;
                case "eqrr":
                    memorybank = eqrr(memorybank, a, b, c);
                    break;
            }
        }
        System.out.println(memorybank.get(0));
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }

    private static Memorybank addr(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) + input.get(b));
        return output;
    }
    
    private static Memorybank addi(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) + b);
        return output;
    }

    private static Memorybank mulr(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) * input.get(b));
        return output;        
    }
    
    private static Memorybank muli(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) * b);
        return output;
    }
    
    private static Memorybank banr(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) & input.get(b));
        return output;
    }

    private static Memorybank bani(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) & b);
        return output;
    }

    private static Memorybank borr(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) | input.get(b));
        return output;
    }

    private static Memorybank bori(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) | b);
        return output;
    }

    private static Memorybank setr(Memorybank input, int a, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a));
        return output;
    }

    private static Memorybank seti(Memorybank input, int a, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, a);
        return output;
    }

    private static Memorybank gtir(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, a > input.get(b) ? 1 : 0);
        return output;
    }

    private static Memorybank gtri(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) > b ? 1 : 0);
        return output;
    }

    private static Memorybank gtrr(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) > input.get(b) ? 1 : 0);
        return output;
    }

    private static Memorybank eqir(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, a == input.get(b) ? 1 : 0);
        return output;
    }

    private static Memorybank eqri(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) == b ? 1 : 0);
        return output;
    }

    private static Memorybank eqrr(Memorybank input, int a, int b, int c) {
        Memorybank output = new Memorybank(input);
        output.set(c, input.get(a) == input.get(b) ? 1 : 0);
        return output;
    }
    
    private static class Memorybank {
        private List<Integer> memory;
        
        private Memorybank(String input) {
            memory = new ArrayList<>();
            String[] numbers = input.split(",");
            memory.add(Integer.parseInt("" + numbers[0].charAt(1)));
            memory.add(Integer.parseInt("" + numbers[1].charAt(1)));
            memory.add(Integer.parseInt("" + numbers[2].charAt(1)));
            memory.add(Integer.parseInt("" + numbers[3].charAt(1)));
        }
        
        private Memorybank(Memorybank old) {
            memory = new ArrayList<>(old.memory);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Memorybank that = (Memorybank) o;

            return memory.equals(that.memory);
        }

        @Override
        public int hashCode() {
            return memory.hashCode();
        }
        
        private int get(int index) {
            return memory.get(index);
        }
        
        private void set(int index, Integer element) {
            memory.set(index, element);
        }
        
        @Override
        public String toString() {
            return memory.toString();
        }
    }
}
