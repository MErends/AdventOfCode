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

    /**
     * Load file into one String
     * Assuming file has no line separators!
     * i.e. 2015 Day 1, 2015 Day 3
     * @param filename file in current working directory or full pathname
     * @return String
     */
    public static String getFileAsString(String filename) {
        return Util.getFileAsList(filename).get(0);
    }


    /**
     * Loads entire file, one line at a time, into List
     * @param filename file in current working directory or full pathname
     * @return ArrayList of strings
     */
    public static List<String> getFileAsList(String filename) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream("/"+ filename)));
                String input;
            while ((input = br.readLine()) != null) {
                    list.add(input);
                }
        } catch (IOException ioe) {
            LOG.error("Could not read from file", ioe);
        }
        return list;
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
        } catch (IOException ioe) {
            LOG.error("Could not read from file", ioe);
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
}