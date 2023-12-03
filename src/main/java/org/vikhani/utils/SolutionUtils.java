package org.vikhani.utils;

import org.vikhani.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SolutionUtils {
    private SolutionUtils() {
    }

    public static String getProblemData(String sourceName) throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/" + sourceName);

        StringBuilder resultStringBuilder = new StringBuilder();
        assert inputStream != null;
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
