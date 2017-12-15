package nl.erends.advent.year2017;

public class Day15 {

    public static void main(String[] args) {
        Generator genA = new Generator(699, 16807);
        Generator genB = new Generator(124, 48271);
        int equals = 0;
        for (int i = 0; i< 40_000_000; i++) {
            long a = genA.getNextNumber();
            long b = genB.getNextNumber();
            if (judgeEquals(a, b)) {
                equals++;
            }

        }
        System.out.println(equals);

        genA = new Generator(699, 16807);
        genB = new Generator(124, 48271);
        equals = 0;
        for (int i = 0; i< 5_000_000; i++) {
            long a = genA.getNextMultiple(4);
            long b = genB.getNextMultiple(8);
            if (judgeEquals(a, b)) {
                equals++;
            }

        }
        System.out.println(equals);
	}

	private static boolean judgeEquals(long a, long b) {
        StringBuilder sbA = new StringBuilder(Long.toBinaryString(a));
        while (sbA.length() < 32) sbA.insert(0, '0');
        String aString = sbA.substring(16);
        StringBuilder sbB = new StringBuilder(Long.toBinaryString(b));
        while (sbB.length() < 32) sbB.insert(0, '0');
        String bString = sbB.substring(16);
        return (aString.equals(bString));
    }
}

class Generator {

    private long number;
    private int multiplier;


    Generator(int seed, int multiplier) {
        number = seed;
        this.multiplier = multiplier;
    }

    long getNextNumber() {
        number = (number * multiplier) % 2147483647;
        return number;
    }

    long getNextMultiple(long factor) {
        do {
            getNextNumber();
        } while (number % factor != 0);
        return number;
    }
}