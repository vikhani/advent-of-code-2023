package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//--- Day 4: Scratchcards ---
public class Scratchcards {
    private static final String INPUT_NAME = "4_input.txt";

    public int solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);

        var allCards = Stream.of(data.split("\n"))
                .map(line -> line.substring(line.indexOf(": ") + 2))
                .toList();
        int sum = 0;

        for (var card : allCards) {
            var pair = List.of(card.split("\\|"));
            if (pair.size() != 2) {
                continue;
            }

            var winning = Arrays.stream(pair.get(0).split(" "))
                    .map(String::strip)
                    .collect(Collectors.toList());
            var having = Arrays.stream(pair.get(1).split(" "))
                    .map(String::strip)
                    .collect(Collectors.toList());

            having.retainAll(winning);

            var intersec = having.stream().filter(val -> !val.isBlank()).toList();
            if (intersec.isEmpty()) {
                continue;
            }

            sum += Math.pow(2, intersec.size() - 1);
        }
        return sum;
    }

}
