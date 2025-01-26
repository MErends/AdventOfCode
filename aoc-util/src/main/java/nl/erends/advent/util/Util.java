package nl.erends.advent.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
    
    public static final int ASCII_OFFSET = 48;
    
    private static final Logger LOG = LogManager.getLogger(Util.class);
    
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(1))
            .build();
    
    private Util() {
        throw new IllegalStateException("Don't instantiate");
    }
    
    public static List<String> readInput(int year, int day, int testcase) {
        String location = "src/main/resources";
        String extension = ".txt";
        if (testcase != 0) {
            location = "src/test/resources";
            extension = "_"+ testcase + extension;
        }
        location += "/year" + year + "/day" + day + extension;
        Path path = Paths.get(location);
        if (testcase == 0 && Files.notExists(path)) {
            downloadInput(year, day, path);
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            LOG.error("Could not read from: {}", location, e);
        }
        return new ArrayList<>();
    }
    
    public static List<String> readInput(int year, int day) {
        return readInput(year, day, 0);
    }
    
    public static List<Integer> readIntegers(int year, int day, int testcase) {
        return readInput(year, day, testcase).stream()
                .map(Integer::parseInt)
                .toList();
    }
    
    public static List<Integer> readIntegers(int year, int day) {
        return readIntegers(year, day, 0);
    }
    
    public static String readLine(int year, int day) {
        return readInput(year, day).getFirst();
    }

    public static String readLine(int year, int day, int testcase) {
        return readInput(year, day, testcase).getFirst();
    }
    
    private static void downloadInput(int year, int day, Path path) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("https://adventofcode.com/%d/day/%d/input", year, day)))
                .setHeader("Cookie", "session=" + System.getenv("session"))
                .setHeader("User-Agent", "https://github.com/MErends/AdventOfCode/blob/main/src/main/java/nl/erends/advent/util/Util.java:44")
                .build();
        try {
            LOG.info("Calling adventofcode.com!!");
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Files.createFile(path);
            Files.writeString(path, response.body());
        } catch (IOException e) {
            LOG.error("Could not download input", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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

    public static String leftPadWithZero(String input, int length) {
        String zeroes = IntStream.range(0, length - input.length())
                .mapToObj(i -> "0")
                .collect(Collectors.joining());
        return zeroes + input;
    }
}
