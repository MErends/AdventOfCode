package nl.erends.advent.util;

public class Timer {

    private static long start1;
    private static long end1;
    private static long start2;
    private static long end2;

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
        System.out.printf("Part 1:\t%d millis.\nPart 2:\t%d millis.\nTotal:\t%d millis", end1 - start1, end2 - start2, end2 - start1);
    }
}
