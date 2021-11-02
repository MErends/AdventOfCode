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
                case "addr":
                    addr(a, b, c);
                    break;
                case "addi":
                    addi(a, b, c);
                    break;
                case "mulr":
                    mulr(a, b, c);
                    break;
                case "muli":
                    muli(a, b, c);
                    break;
                case "banr":
                    banr(a, b, c);
                    break;
                case "bani":
                    bani(a, b, c);
                    break;
                case "borr":
                    borr(a, b, c);
                    break;
                case "bori":
                    bori(a, b, c);
                    break;
                case "setr":
                    setr(a, c);
                    break;
                case "seti":
                    seti(a, c);
                    break;
                case "gtir":
                    gtir(a, b, c);
                    break;
                case "gtri":
                    gtri(a, b, c);
                    break;
                case "gtrr":
                    gtrr(a, b, c);
                    break;
                case "eqir":
                    eqir(a, b, c);
                    break;
                case "eqri":
                    eqri(a, b, c);
                    break;
                case "eqrr":
                    eqrr(a, b, c);
                    break;
                default:
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

    public void setMemory(String memoryString) {
        memory = new Memorybank(memoryString);
    }
    
    public String getMemoryString() {
        return memory.toString();
    }
    
    public List<Integer> getMemory() {
        return memory.memory;
    }

    public void setPointerBound(int pointerBound) {
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
