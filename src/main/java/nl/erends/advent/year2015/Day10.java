package nl.erends.advent.year2015;

public class Day10 {

    public static void main(String[] args) {
        String input = "1113122113";
        for (int i = 0; i < 50; i++) {
            System.out.println(i + "/50");
            input = nextSequence(input);
        }
        System.out.println(input.length());
    }

    private static String nextSequence(String inputString) {
        StringBuilder input = new StringBuilder(inputString);
        StringBuilder output = new StringBuilder();
        while (input.length() != 0) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(0);
                if (input.charAt(i) != c) {
                    output.append(i).append(c);
                    input.delete(0, i);
                    break;
                } else if (i == input.length() - 1) {
                    output.append(i + 1).append(c);
                    input.delete(0, input.length());
                    break;
                }
            }
        }
        return output.toString();
    }
}

