package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

// --- Day 2: Cube Conundrum ---
public class Conundrum {
    private static final String INPUT_NAME = "2_input.txt";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";
    private static final String RED = "red";
    private static final Map<String, Integer> targetNums = Map.ofEntries(
            Map.entry(RED, 12),
            Map.entry(GREEN, 13),
            Map.entry(BLUE, 14)
    );

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);

        var allGames = Stream.of(data.split("\n")).map(line -> line.substring(line.indexOf(": ") + 2)).toList();
        int gameNum = 1;
        int resSum = 0;

        for (var game : allGames) {
            var sets = game.split(";");
            Integer redNum = null;
            Integer greenNum = null;
            Integer blueNum = null;
            for (var set : sets) {
                var cubes = set.split(", ");
                for (var cube : cubes) {
                    if (cube.contains(RED)) {
                        redNum = getCubeNum(redNum, cube);
                    }
                    if (cube.contains(GREEN)) {
                        greenNum = getCubeNum(greenNum, cube);
                    }
                    if (cube.contains(BLUE)) {
                        blueNum = getCubeNum(blueNum, cube);
                    }
                }
            }

            if (redNum != null && redNum <= targetNums.get(RED)
                    && blueNum != null && blueNum <= targetNums.get(BLUE)
                    && greenNum != null && greenNum <= targetNums.get(GREEN)) {
                resSum += gameNum;
            }
            gameNum += 1;
        }

        return resSum;
    }

    public int solveSecondProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);

        var allGames = Stream.of(data.split("\n")).map(line -> line.substring(line.indexOf(": ") + 2)).toList();
        int resSum = 0;

        for (var game : allGames) {
            var sets = game.split(";");
            Integer redNum = null;
            Integer greenNum = null;
            Integer blueNum = null;
            for (var set : sets) {
                var cubes = set.split(", ");
                for (var cube : cubes) {
                    if (cube.contains(RED)) {
                        redNum = getCubeNum(redNum, cube);
                    }
                    if (cube.contains(GREEN)) {
                        greenNum = getCubeNum(greenNum, cube);
                    }
                    if (cube.contains(BLUE)) {
                        blueNum = getCubeNum(blueNum, cube);
                    }
                }
            }

            resSum += (redNum != null ? redNum : 1)
                    * (greenNum != null ? greenNum : 1)
                    * (blueNum != null ? blueNum : 1);
        }

        return resSum;
    }

    private static Integer getCubeNum(Integer current, String cube) {
        int num = Integer.parseInt(cube.strip().substring(0, cube.strip().indexOf(" ")));
        if (current == null || current < num) {
            current = num;
        }

        return current;
    }
}
