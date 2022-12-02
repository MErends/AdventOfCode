package nl.erends.advent.year2018;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ElfMachine {
    
    private Memorybank memory;
    private final List<String> instructions;
    private int pointerBound = -1;
    
    ElfMachine(List<String> instructions) {
        this.instructions = instructions;
    }

    public void execute() {
        int pointer = 0;
        while (pointer >= 0 && pointer < instructions.size()) {
            String[] splitInstruction = instructions.get(pointer).split(" ");
            String operation = splitInstruction[0];
            int a = Integer.parseInt(splitInstruction[1]);
            int b = Integer.parseInt(splitInstruction[2]);
            int c = Integer.parseInt(splitInstruction[3]);
            switch (operation) {
                case "addr" -> addr(a, b, c);
                case "addi" -> addi(a, b, c);
                case "mulr" -> mulr(a, b, c);
                case "muli" -> muli(a, b, c);
                case "banr" -> banr(a, b, c);
                case "bani" -> bani(a, b, c);
                case "borr" -> borr(a, b, c);
                case "bori" -> bori(a, b, c);
                case "setr" -> setr(a, c);
                case "seti" -> seti(a, c);
                case "gtir" -> gtir(a, b, c);
                case "gtri" -> gtri(a, b, c);
                case "gtrr" -> gtrr(a, b, c);
                case "eqir" -> eqir(a, b, c);
                case "eqri" -> eqri(a, b, c);
                case "eqrr" -> eqrr(a, b, c);
                default -> throw new IllegalArgumentException(operation);
            }
            if (pointerBound >= 0) {
                pointer = memory.memory.get(pointerBound);
            }
            pointer++;
            if (pointerBound > 0) {
                memory.set(pointerBound, pointer);
            }
        }
    }

    private void addr(int a, int b, int c) {
        memory.set(c, memory.get(a) + memory.get(b));
    }

    private void addi(int a, int b, int c) {
        memory.set(c, memory.get(a) + b);
    }

    private void mulr(int a, int b, int c) {
        memory.set(c, memory.get(a) * memory.get(b));
    }

    private void muli(int a, int b, int c) {
        memory.set(c, memory.get(a) * b);
    }

    private void banr(int a, int b, int c) {
        memory.set(c, memory.get(a) & memory.get(b));
    }

    private void bani(int a, int b, int c) {
        memory.set(c, memory.get(a) & b);
    }

    private void borr(int a, int b, int c) {
        memory.set(c, memory.get(a) | memory.get(b));
    }

    private void bori(int a, int b, int c) {
        memory.set(c, memory.get(a) | b);
    }

    private void setr(int a, int c) {
        memory.set(c, memory.get(a));
    }

    private void seti(int a, int c) {
        memory.set(c, a);
    }

    private void gtir(int a, int b, int c) {
        memory.set(c, a > memory.get(b) ? 1 : 0);
    }

    private void gtri(int a, int b, int c) {
        memory.set(c, memory.get(a) > b ? 1 : 0);
    }

    private void gtrr(int a, int b, int c) {
        memory.set(c, memory.get(a) > memory.get(b) ? 1 : 0);
    }

    private void eqir(int a, int b, int c) {
        memory.set(c, a == memory.get(b) ? 1 : 0);
    }

    private void eqri(int a, int b, int c) {
        memory.set(c, memory.get(a) == b ? 1 : 0);
    }

    private void eqrr(int a, int b, int c) {
        memory.set(c, memory.get(a) == memory.get(b) ? 1 : 0);
    }

    void setMemory(String memoryString) {
        memory = new Memorybank(memoryString);
    }
    
    String getMemoryString() {
        return memory.toString();
    }
    
    List<Integer> getMemory() {
        return memory.memory;
    }

    void setPointerBound(int pointerBound) {
        this.pointerBound = pointerBound;
    }

    private static class Memorybank {
        
        private final List<Integer> memory;

        private Memorybank(String input) {
            memory = new ArrayList<>();
            String[] numbers = input.split(",");
            for (String number : numbers) {
                memory.add(Integer.parseInt("" + number.charAt(1)));
            }
        }

        private int get(int index) {
            return memory.get(index);
        }

        private void set(int index, Integer element) {
            memory.set(index, element);
        }

        @Override
        public String toString() {
            return memory.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"));
        }

    }
}
