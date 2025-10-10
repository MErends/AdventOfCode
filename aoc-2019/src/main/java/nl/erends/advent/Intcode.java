package nl.erends.advent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Intcode {

    private boolean halted = false;
    private long pointer = 0;
    private Map<Long, Long> code = new HashMap<>();
    private Queue<Long> input = new LinkedList<>();
    private Long output;
    private long relativeBase = 0;
    
    Intcode(String input) {
        List<Long> codeList = Arrays.stream(input.split(",")).map(Long::parseLong).toList();
        for (int i = 0; i < codeList.size(); i++) {
            code.put((long) i, codeList.get(i));
        }
    }

    Intcode(Intcode other) {
        this.pointer = other.pointer;
        this.relativeBase = other.relativeBase;
        this.code = new HashMap<>(other.code);
        this.output = other.output;
        this.input = new LinkedList<>(other.input);
        this.halted = other.halted;
    }

    /**
     * @return Whether the computer is still running. If true, either output was produced or input is required
     */
    public boolean execute() {
        while (true) {
            int opcode = (int) (getCode(pointer) % 100);
            switch (opcode) {
                case 1, 2, 7, 8 -> doThreeParameters(opcode); // add, multiply, less than, equals
                case 3 -> { // read
                    boolean success = doReadInput();
                    if (!success) {
                        return true;
                    }
                }
                case 4 -> { // output
                    doOutput();
                    return true;
                }
                case 5, 6 -> doJumpIf(opcode); // jump-if-true, jump-if-false
                case 9 -> doAlterBase();// alter relative base+
                case 99 -> { // halt
                    halted = true;
                    return false;
                }
                default -> throw new IllegalArgumentException("Unkown opcode: " + opcode);
            }
        }
    }

    private void doThreeParameters(int opcodeInt) {
        String opcode = "%05d".formatted(getCode(pointer));
        long input1 = getParameter(1, opcode);
        long input2 = getParameter(2, opcode);
        long target = getCode(pointer + 3);
        if (opcode.charAt(0) == '2') {
            target = relativeBase + target;
        }
        switch (opcodeInt) {
            case 1:
                setCode(target, input1 + input2);
                break;
            case 2:
                setCode(target, input1 * input2);
                break;
            case 7:
                if (input1 < input2) {
                    setCode(target, 1);
                } else {
                    setCode(target, 0);
                }
                break;
            case 8:
                if (input1 == input2) {
                    setCode(target, 1);
                } else {
                    setCode(target, 0);
                }
                break;
            default:
        }
        pointer += 4;
    }

    private boolean doReadInput() {
        String opcode = "%05d".formatted(getCode(pointer));
        long target = getCode(pointer + 1);
        if (opcode.charAt(2) == '2') {
            target = relativeBase + target;
        }
        if (input.isEmpty()) {
            return false;
        }
        setCode(target, input.remove());
        pointer += 2;
        return true;
    }

    private void doOutput() {
        String opcode = "%05d".formatted(getCode(pointer));
        output = getParameter(1, opcode);
        pointer += 2;
    }

    private void doJumpIf(long opcodeInt) {
        String opcode = "%05d".formatted(getCode(pointer));
        long check = getParameter(1, opcode);
        long newPointer = getParameter(2, opcode);
        if ((opcodeInt == 5 && check != 0)
                || (opcodeInt == 6 && check == 0)) {
            pointer = newPointer;
        } else {
            pointer += 3;
        }
    }

    private void doAlterBase() {
        String opcode = "%05d".formatted(getCode(pointer));
        long adjustment = getParameter(1, opcode);
        relativeBase += adjustment;
        pointer += 2;
    }

    private long getParameter(int parameter, String opcode) {
        char mode = opcode.charAt(3 - parameter);
        if (mode == '0') { // position
            int position = (int) getCode(pointer + parameter);
            return getCode(position);
        } else if (mode == '1') {
            return getCode(pointer + parameter);
        } else if (mode == '2') {
            long offset = getCode(pointer + parameter);
            return getCode(relativeBase + offset);
        }
        throw new IllegalArgumentException("Invalid parameter mode: " + mode);
    }
    
    void setCode(long index, long value) {
        code.put(index, value);
    }
    
    long getCode(long index) {
        return code.computeIfAbsent(index, _ -> 0L);
    }

    void addInput(long value) {
        input.add(value);
    }

    public Long getOutput() {
        Long temp = output;
        output = null;
        return temp;
    }

    boolean hasOutput() {
        return output != null;
    }

    boolean isHalted() {
        return halted;
    }
}
