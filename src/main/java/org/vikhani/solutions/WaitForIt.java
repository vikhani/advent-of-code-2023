package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WaitForIt {
    private static final String INPUT_NAME = "6_input.txt";

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lines = Arrays.stream(data.split("\n")).toList();
        var timeToDistance = getTimeToDistance1(lines);
        int mult = 1;
        for (var race : timeToDistance.entrySet()) {
            int count = 0;
            for (int i = 0; i < race.getKey(); i++) {
                if (race.getValue() < i * (race.getKey() - i)) {
                    count++;
                }

            }
            mult *= count;
        }
        return mult;
    }
    public int solveSecondProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lines = Arrays.stream(data.split("\n")).toList();
        var timeToDistance = getTimeToDistance2(lines);
        int count = 0;
        for (var race : timeToDistance.entrySet()) {
            for (int i = 0; i < race.getKey(); i++) {
                if (race.getValue() < i * (race.getKey() - i)) {
                    count++;
                }

            }
        }
        return count;
    }

    private static Map<Integer, Integer> getTimeToDistance1(List<String> lines) {
        Map<Integer, Integer> timeToDistance = new HashMap<>();
        var times = Arrays.stream(
                lines.get(0).substring(lines.get(0).indexOf(":") + 1).split(" ")
        ).filter(el -> !el.isBlank()).map(num -> Integer.parseInt(num.strip())).toList();
        var dists = Arrays.stream(
                lines.get(1).substring(lines.get(1).indexOf(":") + 1).split(" ")
        ).filter(el -> !el.isBlank()).map(num -> Integer.parseInt(num.strip())).toList();
        for (int i = 0; i < times.size(); i++) {
            timeToDistance.put(times.get(i), dists.get(i));
        }
        return timeToDistance;
    }

    private static Map<Long, Long> getTimeToDistance2(List<String> lines) {
        Map<Long, Long> timeToDistance = new HashMap<>();
        var times = Long.parseLong(Arrays.stream(
                lines.get(0).substring(lines.get(0).indexOf(":") + 1).split(" ")
        ).filter(el -> !el.isBlank()).collect(Collectors.joining()));
        var dists = Long.parseLong(Arrays.stream(
                lines.get(1).substring(lines.get(1).indexOf(":") + 1).split(" ")
        ).filter(el -> !el.isBlank()).collect(Collectors.joining()));
        timeToDistance.put(times, dists);
        return timeToDistance;
    }
}
