package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GearRatios {
    private static final String INPUT_NAME = "3_input.txt";

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);

        var lines = List.of(data.split("\n"));
        char[][] scheme = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            scheme[i] = lines.get(i).toCharArray();
        }

        int sum = 0;
        for (int i = 0; i < scheme.length; i++) {
            for (int j = 0; j < scheme[i].length; j++) {
                if (Character.isDigit(scheme[i][j])) {
                    int numberPos = j;
                    while (numberPos < scheme[i].length
                            && Character.isDigit(scheme[i][numberPos])) {
                        numberPos += 1;
                    }

                    boolean isPart = false;
                    for (int t = i - 1; t <= i + 1 && t < scheme.length; t++) {
                        if (t < 0) {
                            continue;
                        }
                        for (int k = j - 1; k <= numberPos && k < scheme[t].length; k++) {
                            if (k < 0) {
                                continue;
                            }
                            if (scheme[t][k] != '.'
                                    && !Character.isDigit(scheme[t][k])) {
                                isPart = true;
                                break;
                            }
                        }
                        if (isPart) {
                            break;
                        }
                    }

                    if (isPart) {
                        var num = Integer.parseInt(String.copyValueOf(Arrays.copyOfRange(scheme[i], j, numberPos)));
                        sum += num;
                    }

                    j = numberPos - 1;
                }
            }
        }
        return sum;
    }
}
