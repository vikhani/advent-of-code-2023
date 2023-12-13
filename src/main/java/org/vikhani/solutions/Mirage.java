package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mirage {
    private static final String INPUT_NAME = "9_input.txt";

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lists = Arrays.stream(data.split("\n"))
                .filter(entry -> !entry.isBlank())
                .map(line ->
                        Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()
                ).toList();
        int sum = 0;
        for (var list : lists) {
            List<List<Integer>> differencesLayers = new ArrayList<>();
            differencesLayers.add(list);
            var prev = list;
            List<Integer> nextLayer;
            do {
                nextLayer = new ArrayList<>();
                for (int i = 0; i < prev.size() - 1; i++) {
                    nextLayer.add(prev.get(i + 1) - prev.get(i));
                }
                differencesLayers.add(nextLayer);
                prev = nextLayer;
            } while (!nextLayer.stream().allMatch(num -> num == 0));
            int nextNum = 0;
            for (int i = differencesLayers.size() - 1; i >= 0; i--) {
                nextNum = differencesLayers.get(i).get(differencesLayers.get(i).size() - 1) + nextNum;
            }
            sum += nextNum;
        }
        return sum;
    }

    public int solveSecondProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lists = Arrays.stream(data.split("\n"))
                .filter(entry -> !entry.isBlank())
                .map(line ->
                        Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()
                ).toList();
        int sum = 0;
        for (var list : lists) {
            List<List<Integer>> differencesLayers = new ArrayList<>();
            differencesLayers.add(list);
            var prev = list;
            List<Integer> nextLayer;
            do {
                nextLayer = new ArrayList<>();
                for (int i = 0; i < prev.size() - 1; i++) {
                    nextLayer.add(prev.get(i + 1) - prev.get(i));
                }
                differencesLayers.add(nextLayer);
                prev = nextLayer;
            } while (!nextLayer.stream().allMatch(num -> num == 0));
            int nextNum = 0;
            for (int i = differencesLayers.size() - 1; i >= 0; i--) {
                nextNum = differencesLayers.get(i).get(0) - nextNum;
            }
            sum += nextNum;
        }
        return sum;
    }

}
