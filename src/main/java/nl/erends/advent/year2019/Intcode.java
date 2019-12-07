package nl.erends.advent.year2019;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Intcode {

    private boolean halted = false;
    private int pointer = 0;
    private List<Integer> code;
    private Queue<Integer> input = new LinkedList<>();
    private Integer output;
    
    Intcode(String input) {
        code = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
    
    public void execute() {
        while (true) {
            int opcode = getCode(pointer) % 100;
            switch (opcode) {
                case 1:
                case 2:
                case 7:
                case 8:
                    doThreeParameters(opcode);
                    break;
                case 3:
                    doReadInput();
                    break;
                case 4:
                    doOutput();
                    return;
                case 5:
                case 6:
                    doJumpIf(opcode);
                    break;
                case 99:
                    halted = true;
                    return;
                default:
            }
        }
    }

    private void doThreeParameters(int opcodeInt) {
        String opcode = String.format("%05d", getCode(pointer));
        int input1 = getParameter(1, opcode);
        int input2 = getParameter(2, opcode);
        int target = getCode(pointer + 3);
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

    private void doReadInput() {
        int target = getCode(pointer + 1);
        setCode(target, input.remove());
        pointer += 2;
    }

    private void doOutput() {
        String opcode = String.format("%05d", getCode(pointer));
        output = getParameter(1, opcode);
        pointer += 2;
    }

    private void doJumpIf(int opcodeInt) {
        String opcode = String.format("%05d", getCode(pointer));
        int check = getParameter(1, opcode);
        int newPointer = getParameter(2, opcode);
        if ((opcodeInt == 5 && check != 0)
                || (opcodeInt == 6 && check == 0)) {
            pointer = newPointer;
        } else {
            pointer += 3;
        }
    }

    private int getParameter(int parameter, String opcode) {
        int value = getCode(pointer + parameter);
        if (opcode.charAt(3 - parameter) == '0') {
            value = getCode(value);
        }
        return value;
    }
    
    void setCode(int index, int value) {
        code.set(index, value);
    }
    
    int getCode(int index) {
        return code.get(index);
    }

    public Queue<Integer> getInput() {
        return input;
    }

    public Integer getOutput() {
        Integer temp = output;
        output = null;
        return temp;
    }

    public boolean isHalted() {
        return halted;
    }
}
