package nl.erends.advent.year2019;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Intcode {
    
    private List<Integer> code;
    
    Intcode(String input) {
        code = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
    
    public void execute() {
        int pointer = 0;
        while (true) {
            int opcode = code.get(pointer);
            if (opcode == 1 || opcode == 2) {
                int input1 = code.get(pointer + 1);
                int input2 = code.get(pointer + 2);
                int output = code.get(pointer + 3);
                if (opcode == 1) {
                    setCode(output, getCode(input1) + getCode(input2));
                } else {
                    setCode(output, getCode(input1) * getCode(input2));
                }
            } else if (opcode == 99) {
                return;
            } else {
                throw new IllegalArgumentException();
            }
            pointer += 4;
        }
    }
    
    
    public void setCode(int index, int value) {
        code.set(index, value);
    }
    
    public int getCode(int index) {
        return code.get(index);
    }
}
