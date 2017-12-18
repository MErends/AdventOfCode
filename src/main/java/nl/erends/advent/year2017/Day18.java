package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Day18 {

    public static final ConcurrentLinkedQueue<Long> queueFor0 = new ConcurrentLinkedQueue<>();
    public static final ConcurrentLinkedQueue<Long> queueFor1 = new ConcurrentLinkedQueue<>();

    private static final Object part2Lock = new Object();

    private static Map<String, Long> memoryMap = new HashMap<>();

    public static void main(String[] args) {
        List<String> commands = FileIO.getFileAsList("2017day18.txt");
        int pointer = 0;
        long lastPlayed = 0;
        while (pointer >=0 && pointer < commands.size()) {
            String[] words = commands.get(pointer).split(" ");
            switch (words[0]) {
                case "snd": {
                    lastPlayed = getMemory(words[1]);
                    break;
                }
                case "set": {
                    setMemory(words[1], getMemory(words[2]));
                    break;
                }
                case "add": {
                    long memory = getMemory(words[1]);
                    long add = getMemory(words[2]);
                    setMemory(words[1], memory + add);
                    break;
                }
                case "mul": {
                    long memory = getMemory(words[1]);
                    long multiply = getMemory(words[2]);
                    setMemory(words[1], memory  * multiply);
                    break;
                }
                case "mod": {
                    long memory = getMemory(words[1]);
                    long modulo = getMemory(words[2]);
                    setMemory(words[1], memory % modulo);
                    break;
                }
                case "rcv": {
                    if (getMemory(words[1]) > 0) {
                        System.out.println(lastPlayed);
                        pointer = -2;
                    }
                    break;
                }
                case "jgz": {
                    long memory = getMemory(words[1]);
                    long offset = getMemory(words[2]);
                    if (memory > 0) {
                        pointer += offset;
                        continue;
                    }
                    break;
                }
            }
            pointer++;
        }


        Machine machine0 = new Machine(0);
        Machine machine1 = new Machine(1);
        Thread machine0Thread = new Thread(machine0);
        Thread machine1Thread = new Thread(machine1);
        machine0Thread.start();
        machine1Thread.start();

        synchronized (Day18.part2Lock) {
            try {
                Day18.part2Lock.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(machine1.sendCommands);
        System.exit(0);
    }



    private static void setMemory(String memory, long value) {
        memoryMap.put(memory, value);
    }

    private static Long getMemory(String memory) {
        try {
            return Long.parseLong(memory);
        } catch (Exception e){
            if (memoryMap.containsKey(memory)) {
                return memoryMap.get(memory);
            } else {
                memoryMap.put(memory, 0L);
                return 0L;
            }
        }
    }
}

class Machine implements Runnable {

    private Map<String, Long> memoryMap = new HashMap<>();
    private int id;
    public int sendCommands = 0;

    Machine(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        List<String> commands = FileIO.getFileAsList("2017day18.txt");
        memoryMap.put("p", (long) id);
        int pointer = 0;
        while (pointer >=0 && pointer < commands.size()) {
            String[] words = commands.get(pointer).split(" ");
            switch (words[0]) {
                case "snd": {
                    long toSend = getMemory(words[1]);
                    if (id == 0) {
                        Day18.queueFor1.add(toSend);
                    } else {
//                            System.out.println("Sending " + toSend + " on queue to 0");
                        ++sendCommands;
                        Day18.queueFor0.add(toSend);
                    }
                    break;
                }
                case "set": {
                    setMemory(words[1], getMemory(words[2]));
                    break;
                }
                case "add": {
                    long memory = getMemory(words[1]);
                    long add = getMemory(words[2]);
                    setMemory(words[1], memory + add);
                    break;
                }
                case "mul": {
                    long memory = getMemory(words[1]);
                    long multiply = getMemory(words[2]);
                    setMemory(words[1], memory  * multiply);
                    break;
                }
                case "mod": {
                    long memory = getMemory(words[1]);
                    long modulo = getMemory(words[2]);
                    setMemory(words[1], memory % modulo);
                    break;
                }
                case "rcv": {
                    try {
                        if (id == 0) {
                            while (Day18.queueFor0.size() == 0) {
//                                    System.out.println("Reading queue 0, no messages. Will wait");
                            }
                            long fromQueue = Day18.queueFor0.poll();
//                                System.out.println("Read " + fromQueue + " from queue 0");
                            setMemory(words[1], fromQueue);
                        } else {
                            while (Day18.queueFor1.size() == 0) {
//                                System.out.println("Reading queue 1, no messages. Will wait");
                            }
                            long fromQueue = Day18.queueFor1.poll();
//                                System.out.println("Read " + fromQueue + " from queue 1");
                            setMemory(words[1], fromQueue);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;
                }
                case "jgz": {
                    long memory = getMemory(words[1]);
                    long offset = getMemory(words[2]);
                    if (memory > 0) {
                        pointer += offset;
                        continue;
                    }
                    break;
                }
            }
            pointer++;
        }
    }

    private void setMemory(String memory, long value) {
        memoryMap.put(memory, value);
    }

    private Long getMemory(String memory) {
        try {
            return Long.parseLong(memory);
        } catch (Exception e){
            if (memoryMap.containsKey(memory)) {
                return memoryMap.get(memory);
            } else {
                memoryMap.put(memory, 0L);
                return 0L;
            }
        }
    }
}