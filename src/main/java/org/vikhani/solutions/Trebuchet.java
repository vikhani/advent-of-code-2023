package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// --- Day 1: Trebuchet?! ---
public class Trebuchet {

    private static final String INPUT_NAME = "1_input.txt";

    private static final Map<Integer, List<String>> numNames = Map.ofEntries(
            Map.entry(3, List.of("one", "two", "six")),
            Map.entry(4, List.of("zero", "four", "nine", "five")),
            Map.entry(5, List.of("three", "seven", "eight"))
    );

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);

        var lines = List.of(data.split("\n"));
        int sum = 0;
        for (String x : lines) {
            var chars = x.toCharArray();
            Integer left = null;
            Integer right = null;
            for (char ch : chars) {
                if (Character.isDigit(ch)) {
                    left = Character.getNumericValue(ch);
                    break;
                }
            }
            for (int i = chars.length - 1; i >= 0; i--) {
                if (Character.isDigit(chars[i])) {
                    right = Character.getNumericValue(chars[i]);
                    break;
                }
            }
            if (left != null && right != null) {
                sum = sum + left * 10 + right;
            }
        }
        return sum;
    }

    public int solveSecondProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lines = List.of(data.split("\n"));
        int sum = 0;
        for (String x : lines) {
            var chars = x.toCharArray();
            Integer left = null;
            Integer right = null;
            Integer leftNumPos = null;
            Integer rightNumPos = null;
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) {
                    left = Character.getNumericValue(chars[i]);
                    leftNumPos = i;
                    break;
                }
            }
            boolean foundSpelled = false;
            for (int i = 0; i < chars.length; i++) {
                for (int j = 3; j < 6 && j < chars.length; j++) {
                    var subar = Arrays.copyOfRange(chars, i, i + j);
                    if (numNames.get(j).stream().anyMatch(name -> name.equals(String.copyValueOf(subar)))) {
                        if (leftNumPos == null || i < leftNumPos) {
                            left = spelledNumToInt(j, String.copyValueOf(subar));
                        }
                        foundSpelled = true;
                        break;
                    }
                }
                if (foundSpelled) {
                    break;
                }
            }

            for (int i = chars.length - 1; i >= 0; i--) {
                if (Character.isDigit(chars[i])) {
                    right = Character.getNumericValue(chars[i]);
                    rightNumPos = i;
                    break;
                }
            }

            foundSpelled = false;
            for (int i = chars.length - 1; i >= 0; i--) {
                for (int j = 3; j < 6 && j < chars.length && i + 1 >= j; j++) {
                    var subar = Arrays.copyOfRange(chars, i - j + 1, i + 1);
                    if (numNames.get(j).stream().anyMatch(name -> name.equals(String.copyValueOf(subar)))) {
                        if (rightNumPos == null || i > rightNumPos) {
                            right = spelledNumToInt(j, String.copyValueOf(subar));
                        }
                        foundSpelled = true;
                        break;
                    }
                }

                if (foundSpelled) {
                    break;
                }
            }

            if (left != null && right != null) {
                sum = sum + left * 10 + right;
            }
        }

        return sum;
    }

    private static Integer spelledNumToInt(int windowSize, String spelledNumber) {
        Integer res = null;
        if (windowSize == 3) {
            switch (spelledNumber) {
                case "one" -> res = 1;
                case "two" -> res = 2;
                case "six" -> res = 6;
            }
        } else if (windowSize == 4) {
            switch (spelledNumber) {
                case "zero" -> res = 0;
                case "four" -> res = 4;
                case "five" -> res = 5;
                case "nine" -> res = 9;
            }
        } else if (windowSize == 5) {
            switch (spelledNumber) {
                case "three" -> res = 3;
                case "seven" -> res = 7;
                case "eight" -> res = 8;
            }
        }
        return res;
    }
}
