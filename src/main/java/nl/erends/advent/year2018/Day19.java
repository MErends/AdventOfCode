package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;

public class Day19 {


    public static void main(String... args) {
        List<String> input = Util.getFileAsList("2018day19.txt");
        long start = System.currentTimeMillis();

        String pointerString = input.remove(0);
        int pointerReg = Integer.parseInt(pointerString.split(" ")[1]);
        Memorybank memorybank = new Memorybank("[0, 0, 0, 0, 0, 0]");
        for (int pointer = 0 ; pointer >= 0 && pointer < input.size(); pointer++) {
            memorybank = seti(memorybank, pointer, pointerReg);
            String opLine = input.get(pointer);
            if (opLine.isEmpty()) continue;
            String[] operand = opLine.split(" ");
            String opString = operand[0];
            int a = Integer.parseInt(operand[1]);
            int b = Integer.parseInt(operand[2]);
            int c = Integer.parseInt(operand[3]);
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
            pointer = memorybank.get(pointerReg);
        }

        long mid = System.currentTimeMillis();
        System.out.println(memorybank.get(0));
        // Decompiled code, calculates sum of divisors:
        int b = 10551300;
        int sum = 0;
        for (int divisor = 1; divisor * divisor < b; divisor++) {
            if (b % divisor == 0) {
                sum += divisor;
                sum += b / divisor;
            }
        }
        System.out.println(sum);
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
            memory.add(Integer.parseInt("" + numbers[4].charAt(1)));
            memory.add(Integer.parseInt("" + numbers[5].charAt(1)));
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
