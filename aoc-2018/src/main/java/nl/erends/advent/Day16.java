package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 extends AbstractProblem<List<String>, Integer> {

    private int programStartsAt = 0;
    private final Map<Integer, List<String>> opcodeMap = new HashMap<>();
    private final List<String> operations = Arrays.asList("addr", "addi", "mulr", "muli", "banr", "bani", "borr", "bori", "setr", "seti", "gtir", "gtri", "gtrr", "eqir", "eqri", "eqrr");

    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readInput(2018, 16));
    }

    @Override
    public Integer solve1() {
        for (int opcode = 0; opcode < 16;  opcode++) {
            opcodeMap.put(opcode, new ArrayList<>(operations));
        }
        int ambigousInstructions = 0;
        for (int pointer = 0; pointer < input.size(); pointer += 4) {
            if (!input.get(pointer).contains(":")) {
                programStartsAt = pointer + 2;
                break;
            }
            String memoryBefore = input.get(pointer).split(": ")[1];
            String instruction = input.get(pointer + 1);
            String memoryAfter = input.get(pointer + 2).split(": {2}")[1];
            int options = countOptionsAndClear(memoryBefore, instruction, memoryAfter);
            if (options >= 3) {
                ambigousInstructions++;
            }
        }
        return ambigousInstructions;
    }
    
    private int countOptionsAndClear(String memoryBefore, String instruction, String memoryAfter) {
        int options = 0;
        String opCode = instruction.split(" ")[0];
        for (String operation : operations) {
            String filledInstruction = instruction.replaceFirst(opCode, operation);
            ElfMachine elfMachine = new ElfMachine(Collections.singletonList(filledInstruction));
            elfMachine.setMemory(memoryBefore);
            elfMachine.execute();
            if (memoryAfter.equals(elfMachine.getMemoryString())) {
                options++;
            } else {
                opcodeMap.get(Integer.valueOf(opCode)).remove(operation);
            }
        }
        return options;
    }

    @Override
    public Integer solve2() {
        if (opcodeMap.isEmpty()) {
            solve1();
        }
        Map<Integer, String> mapping = new HashMap<>();
        while (!opcodeMap.isEmpty()) {
            Integer opCode = opcodeMap.entrySet().stream()
                    .filter(e -> e.getValue().size() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst().orElseThrow(IllegalStateException::new);
            String operator = opcodeMap.remove(opCode).get(0);
            mapping.put(opCode, operator);
            opcodeMap.remove(opCode);
            opcodeMap.values().forEach(l -> l.remove(operator));
        }
        List<String> instructions = new ArrayList<>();
        for (int pointer = programStartsAt; pointer < input.size(); pointer++) {
            String instruction = input.get(pointer);
            String opCode = instruction.split(" ")[0];
            instructions.add(instruction.replaceFirst(opCode, mapping.get(Integer.parseInt(opCode))));
        }
        ElfMachine elfMachine = new ElfMachine(instructions);
        elfMachine.setMemory("[0, 0, 0, 0]");
        elfMachine.execute();
        return elfMachine.getMemory().get(0);
    }
}
