package nl.erends.advent.year2015;

public class Day11 {

    public static void main(String[] args) {
        String password = "hxbxxzaa";
        while (!isValidPassword(password)) {
            password = nextPassword(password);
        }
        System.out.println(password);
    }

    private static String nextPassword(String inputString) {
        StringBuilder password = new StringBuilder(inputString);
        boolean overhouden = true;
        int ophogen = password.length() - 1;
        while (overhouden) {
            char letter = password.charAt(ophogen);
            if (letter == 'z') {
                password.replace(ophogen, ophogen + 1, "a");
                ophogen--;
            } else {
                letter++;
                password.replace(ophogen, ophogen + 1, Character.toString(letter));
                overhouden = false;
            }
        }
        return password.toString();
    }

    private static boolean isValidPassword(String password) {
        if (password.contains("i") || password.contains("o") || password.contains("l")) {
            return false;
        }

        boolean increment = false;
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) - 1 && password.charAt(i) == password.charAt(i + 2) - 2) {
                increment = true;
                break;
            }
        }
        if (!increment) {
            return false;
        }
        int pairs = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                pairs++;
                if (i + 2 < password.length() && password.charAt(i) == password.charAt(i + 2)) {
                    i++;
                }
            }
        }
        return pairs >= 2;
    }
}

