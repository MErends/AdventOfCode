package nl.erends.advent.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
    
    private static final Logger LOG = Logger.getLogger(Util.class);
    
    private Util() {
        throw new IllegalStateException("Don't instantiate");
    }
    
    public static List<String> readInput(int year, int day, int testcase) {
        String extension = ".txt";
        if (testcase != 0) {
            extension = "_"+ testcase + extension;
        }
        String filename = "/year" + year + "/day" + day + extension;
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream(filename)));
            String input;
            while ((input = br.readLine()) != null) {
                list.add(input);
            }
        } catch (IOException | NullPointerException e) {
            LOG.error("Could not read from: " + filename, e);
        }
        return list;
    }
    
    public static List<String> readInput(int year, int day) {
        return readInput(year, day, 0);
    }
    
    public static String readLine(int year, int day) {
        return readInput(year, day).get(0);
    }

    public static String readLine(int year, int day, int testcase) {
        return readInput(year, day, testcase).get(0);
    }

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        for (long d = Math.min(a, b); d >= 1; d--) {
            if (a % d == 0 && b % d == 0) {
                return d;
            }
        }
        return 1;
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}