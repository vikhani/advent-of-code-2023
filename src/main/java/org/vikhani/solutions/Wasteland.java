package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Wasteland {
    private static final String INPUT_NAME = "8_input.txt";

    private class Node {
        private String left;
        private String right;

        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

        public Node(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lines = Arrays.stream(data.split("\n")).filter(entry -> !entry.isBlank()).toList();
        var path = lines.get(0).split("");
        Map<String, Node> nodes = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).contains("=")) {
                continue;
            }
            var el = Arrays.stream(lines.get(i).split("=")).map(String::strip).toList();
            var left = el.get(1).substring(el.get(1).indexOf("(") + 1, el.get(1).indexOf(","));
            var right = el.get(1).substring(el.get(1).indexOf(",") + 1, el.get(1).indexOf(")"));
            nodes.put(el.get(0).strip(), new Node(left.strip(), right.strip()));

        }

        var curPos = new StringBuilder("AAA");
        AtomicInteger steps = new AtomicInteger(0);
        while (!searchForZZZ(path, nodes, curPos, steps)) {
        }
        return steps.get();
    }

    private boolean searchForZZZ(String[] path, Map<String, Node> nodes, StringBuilder curPos, AtomicInteger steps) {
        boolean found = false;
        for (var step : path) {
            steps.getAndIncrement();
            var n = nodes.get(curPos.toString());
            if (step.equals("R")) {
                curPos.delete(0, curPos.length());
                curPos.append(n.getRight());
            } else {
                curPos.delete(0, curPos.length());
                curPos.append(n.getLeft());
            }
            if (curPos.toString().equals("ZZZ")) {
                found = true;
                break;
            }
        }

        return found;
    }

    private boolean searchForZZZ2(String[] path, Map<String, Node> nodes, List<String> curPos, AtomicInteger steps) {
        int foundCounter = 0;
        for (var step : path) {
            steps.getAndIncrement();
            for (int i = 0; i < curPos.size(); i++) {
                var n = nodes.get(curPos.get(i));
                if (step.equals("R")) {
                    curPos.set(i, n.getRight());
                } else {
                    curPos.set(i, n.getLeft());
                }
                if (curPos.get(i).contains("Z")) {
                    foundCounter += 1;
                    break;
                }
            }
            if (foundCounter == curPos.size()) {
                break;
            }
        }

        return foundCounter == curPos.size();
    }


    public int solveSecondProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lines = Arrays.stream(data.split("\n")).filter(entry -> !entry.isBlank()).toList();
        var path = lines.get(0).split("");
        Map<String, Node> nodes = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).contains("=")) {
                continue;
            }
            var el = Arrays.stream(lines.get(i).split("=")).map(String::strip).toList();
            var left = el.get(1).substring(el.get(1).indexOf("(") + 1, el.get(1).indexOf(","));
            var right = el.get(1).substring(el.get(1).indexOf(",") + 1, el.get(1).indexOf(")"));
            nodes.put(el.get(0).strip(), new Node(left.strip(), right.strip()));

        }

        List<String> positions = nodes.keySet().stream().filter(node -> node.endsWith("A")).collect(Collectors.toList());
        AtomicInteger steps = new AtomicInteger(0);
        while (!searchForZZZ2(path, nodes, positions, steps)) {
        }
        return steps.get();
    }
}
