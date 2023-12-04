package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GearRatios {
    private static final String INPUT_NAME = "3_input.txt";

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);

        char[][] scheme = getCharsArrayFromData(data);

        int sum = 0;
        for (int i = 0; i < scheme.length; i++) {
            for (int j = 0; j < scheme[i].length; j++) {
                if (Character.isDigit(scheme[i][j])) {
                    int numberPos = j;
                    numberPos = getEndNumberPos(numberPos, scheme[i]);

                    boolean isPart = checkIfNumberIsPart(scheme, i, j, numberPos);

                    if (isPart) {
                        var num = getNum(j, numberPos, scheme[i]);
                        sum += num;
                    }

                    j = numberPos - 1;
                }
            }
        }
        return sum;
    }

    private static boolean checkIfNumberIsPart(char[][] scheme, int i, int j, int numberPos) {
        boolean isPart = false;

        for (int t = i - 1; t <= i + 1 && t < scheme.length; t++) {
            if (t < 0) {
                continue;
            }
            for (int k = j - 1; k <= numberPos && k < scheme[t].length; k++) {
                if (k < 0) {
                    continue;
                }
                if (isMarkerChar(k, scheme[t])) {
                    isPart = true;
                    break;
                }
            }
            if (isPart) {
                break;
            }
        }
        return isPart;
    }

    private static boolean isMarkerChar(int k, char[] scheme) {
        return notADot(k, scheme)
                && !Character.isDigit(scheme[k]);
    }

    private static char[][] getCharsArrayFromData(String data) {
        List<String> lines = getLines(data);

        char[][] scheme = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            scheme[i] = lines.get(i).toCharArray();
        }
        return scheme;
    }

    private static List<String> getLines(String data) {
        return List.of(data.split("\n"));
    }

    private static int getEndNumberPos(int numberPos, char[] scheme) {
        while (numberPos < scheme.length
                && Character.isDigit(scheme[numberPos])) {
            numberPos += 1;
        }
        return numberPos;
    }

    private static boolean notADot(int k, char[] scheme) {
        return scheme[k] != '.';
    }

    private static int getNum(int j, int numberPos, char[] scheme) {
        return Integer.parseInt(String.copyValueOf(Arrays.copyOfRange(scheme, j, numberPos)));
    }
}
