package nl.erends.advent.year2017;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Duet {
    
    private List<Map<String, Long>> memoryBank = Arrays.asList(new HashMap<String, Long>(), new HashMap<String, Long>());
    private List<Long> pointer = Arrays.asList(0L, 0L);
    private List<Queue<Long>> queues = Arrays.asList(new LinkedList<Long>(), new LinkedList<Long>());
    private List<Boolean> alive = Arrays.asList(true, true);
    private List<String> instructions;
    private long sound;
    private long recieve;
    private boolean multithread = false;
    private int activeID = 0;
    
    
    Duet(List<String> instructions) {
        this.instructions = instructions;
    }
    
    public void execute() {
        while (alive.get(0) || alive.get(1)) {
            String instruction = instructions.get((int) getPointer());
            String[] words = instruction.split(" ");
            switch (words[0]) {
                case "snd":
                    if (multithread) {
                        doSndMT(words[1]);
                    } else {
                        doSnd(words[1]);
                    }
                    break;
                case "set":
                    doSet(words[1], words[2]);
                    break;
                case "add":
                    doAdd(words[1], words[2]);
                    break;
                case "mul": 
                    doMul(words[1], words[2]);
                    break;
                case "mod":
                    doMod(words[1], words[2]);
                    break;
                case "rcv":
                    if (multithread) {
                        doRcvMT(words[1]);
                    } else {
                        doRcv(words[1]);
                    }
                    break;
                case "jgz":
                    doJgz(words[1], words[2]);
                    break;
                default:
            }
        }
    }
    
    private long getMemory(String key) {
        try {
            return Long.parseLong(key);
        } catch (NumberFormatException e) {
            return memoryBank.get(activeID).computeIfAbsent(key, k -> 0L);
        }
    }
    
    private void doSnd(String memory) {
        sound = getMemory(memory);
        addToPointer(1);
    }

    private void doSndMT(String memory) {
        long value = getMemory(memory);
        int target = activeID == 0 ? 1 : 0;
        queues.get(target).add(value);
        alive.set(target, true);
        addToPointer(1);
        if (activeID == 1) {
            sound++;
        }
    }

    private void doSet(String memory, String value) {
        long setValue = getMemory(value);
        memoryBank.get(activeID).put(memory, setValue);
        addToPointer(1);
    }
    
    private void doAdd(String memory, String value) {
        long addValue = getMemory(value);
        memoryBank.get(activeID).put(memory, getMemory(memory) + addValue);
        addToPointer(1);
    }
    
    private void doMul(String memory, String value) {
        long mulValue = getMemory(value);
        memoryBank.get(activeID).put(memory, getMemory(memory) * mulValue);
        addToPointer(1);
    }
    
    private void doMod(String memory, String value) {
        long modValue = getMemory(value);
        memoryBank.get(activeID).put(memory, getMemory(memory) % modValue);
        addToPointer(1);
    }
    
    private void doRcv(String memory) {
        long value = getMemory(memory);
        if (value != 0) {
            recieve = sound;
            alive.set(0, false);
            alive.set(1, false);
        } else {
            addToPointer(1);
        }
    }

    private void doRcvMT(String memory) {
        if (queues.get(activeID).isEmpty()) {
            alive.set(activeID, false);
            activeID = activeID == 0 ? 1 : 0;
        } else {
            long value = queues.get(activeID).poll();
            memoryBank.get(activeID).put(memory, value);
            addToPointer(1);
        }
    }
    
    private void doJgz(String memory, String value) {
        long longValue = getMemory(memory);
        if (longValue > 0) {
            addToPointer(getMemory(value));
        } else {
            addToPointer(1);
        }
    }
    
    public long getRecieve() {
        return recieve;
    }

    public long getSound() {
        return sound;
    }

    private long getPointer() {
        return pointer.get(activeID);
    }
    
    private void addToPointer(long add) {
        pointer.set(activeID, pointer.get(activeID) + add);
    }

    public void setMultithread() {
        multithread = true;
        memoryBank.get(1).put("p", 1L);
    }
}
