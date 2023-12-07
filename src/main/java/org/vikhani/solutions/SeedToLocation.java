package org.vikhani.solutions;

import org.vikhani.utils.SolutionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SeedToLocation {
    private static final String INPUT_NAME = "5_input.txt";

    private class Mapper {
        private Long sourceStart;
        private Long targetStart;
        private Long range;

        public Mapper(Long sourceStart, Long targetStart, Long range) {
            this.sourceStart = sourceStart;
            this.targetStart = targetStart;
            this.range = range;
        }

        public Long getSourceStart() {
            return sourceStart;
        }

        public Long getTargetStart() {
            return targetStart;
        }

        public Long getRange() {
            return range;
        }
    }

    public Long solveFirstProblem() throws IOException {
        String data = SolutionUtils.getProblemData(INPUT_NAME);
        var lines = Arrays.stream(data.split("\n")).toList();
        if (lines.isEmpty()) {
            return 0L;
        }

        var seeds = Arrays.stream(
                lines.get(0).substring(lines.get(0).lastIndexOf(":") + 1, lines.get(0).length()).split(" ")
        ).filter(seed -> !seed.isBlank()).map(seed -> Long.parseLong(seed.strip())).toList();

        var mappersLines = lines.stream().skip(1).toList();
        var mappers = getMappersMap(mappersLines);
        for (var maps : mappers.entrySet()) {
            Map<Long, Long> seedToPossibleLoc = new HashMap<>();
            for (var map : maps.getValue()) {
                var startOriginPos = map.getSourceStart();
                var startTargetPos = map.getTargetStart();
                var endOriginPos = startOriginPos + map.getRange();
                var endTargetPos = startTargetPos + map.getRange();
                for (var seed : seeds) {
                    Long destPos = null;
                    if (startOriginPos <= seed && seed <= endOriginPos) {
                        destPos = startTargetPos + seed - startOriginPos;
                    }
                    if (destPos != null
                            && destPos < endTargetPos)
                        seedToPossibleLoc.put(seed, destPos);
                }
            }

            for (var seed : seeds) {
                if (!seedToPossibleLoc.containsKey(seed)) {
                    seedToPossibleLoc.put(seed, seed);
                }
            }

            seeds = seedToPossibleLoc.values().stream().toList();
        }

        return Collections.min(seeds);
    }

    private Map<String, List<Mapper>> getMappersMap(List<String> mappersLines) {
        Map<String, List<Mapper>> mappers = new LinkedHashMap<>();

        String mapName = null;
        Mapper mapper = null;
        for (var line : mappersLines) {
            if (line.contains("map")) {
                mapName = line.substring(0, line.indexOf(":"));
            } else {
                var nums = Arrays.stream(line.split(" ")
                ).filter(val -> !val.isBlank()).map(val -> Long.parseLong(val.strip())).toList();
                if (nums.size() < 3) {
                    continue;
                }
                mapper = new Mapper(nums.get(1), nums.get(0), nums.get(2));
            }

            if (!mappers.containsKey(mapName)) {
                mappers.put(mapName, new ArrayList<>());
            } else {
                 mappers.get(mapName).add(mapper);
            }
        }
        return mappers;
    }
}
