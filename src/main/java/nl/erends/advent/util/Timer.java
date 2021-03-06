package nl.erends.advent.util;

import org.apache.log4j.Logger;

public class Timer {
    
    private static final Logger LOG = Logger.getLogger(Timer.class);

    private static long start1;
    private static long end1;
    private static long start2;
    private static long end2;
    
    private Timer() {
        throw new IllegalStateException("Don't instantiate");
    }
    
    public static void start() {
        start1();
        start2();
    }
    
    public static void end() {
        end1();
        end2();
    }

    public static void start1() {
        start1 = System.currentTimeMillis();
    }

    public static void end1() {
        end1 = System.currentTimeMillis();
    }

    public static void start2() {
        start2 = System.currentTimeMillis();
    }

    public static void end2() {
        end2 = System.currentTimeMillis();
    }

    public static void printStats() {
        long end = Math.max(end1, end2);
        LOG.info("Part 1:\t" + (end1 - start1) + " millis.\n" +
                "Part 2:\t" + (end2 - start2) + " millis.\n" +
                "Total:\t" + (end - start1) + " millis");
    }
}
