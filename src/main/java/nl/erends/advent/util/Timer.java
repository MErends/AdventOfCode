package nl.erends.advent.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Timer {
    
    private static final Logger LOG = LogManager.getLogger(Timer.class);

    private static long start1;
    private static long end1;
    private static long start2;
    private static long end2;

    private static long currentSecond = System.currentTimeMillis() / 1000;
    private static int count = 0;
    
    private Timer() {
        throw new IllegalStateException("Don't instantiate");
    }

    static void start1() {
        start1 = System.currentTimeMillis();
    }

    static void end1() {
        end1 = System.currentTimeMillis();
    }

    static void start2() {
        start2 = System.currentTimeMillis();
    }

    static void end2() {
        end2 = System.currentTimeMillis();
    }

    static void printStats() {
        if (start2 == 0) {
            LOG.info("Part 1 & 2:\t{} millis.", end1 - start1);
        } else {
            LOG.info("Part 1:\t{} millis.\nPart 2:\t{} millis.\nTotal:\t{} millis.",
                    end1 - start1, end2 - start2, end2 - start1);
        }
    }

    public static void tick() {
        count++;
        long now = System.currentTimeMillis() / 1000;
        if (currentSecond != now) {
            LOG.info("{} tps", count);
            currentSecond = now;
            count = 0;
        }
    }
}
