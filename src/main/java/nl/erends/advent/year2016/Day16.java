package nl.erends.advent.year2016;

public class Day16 {


    public static void main(String[] args) {
        String discData = "11100010111110100";
        int targetLength = 272;
        while (discData.length() < targetLength) {
            discData = dragonCurve(discData);
        }
        discData = discData.substring(0, targetLength);
        System.out.println(checksum(discData));
        discData = "11100010111110100";
        targetLength = 35651584;
        while (discData.length() < targetLength) {
            discData = dragonCurve(discData);
        }
        discData = discData.substring(0, targetLength);
        System.out.println(checksum(discData));
    }

    private static String dragonCurve(String input) {
        StringBuilder output = new StringBuilder(input);
        output.append('0');
        char[] inputchars = input.toCharArray();
        for (int index = inputchars.length - 1; index >= 0; index--) {
            output.append(inputchars[index] == '1' ? '0' : '1');
        }
        return output.toString();
    }

    private static String checksum(String input) {
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < input.length(); index += 2) {
            String subString = input.substring(index, index + 2);
            output.append(subString.charAt(0) == subString.charAt(1) ? '1' : '0');
        }
        if (output.length() % 2 == 0) {
            return checksum(output.toString());
        }
        return output.toString();
    }
}
