package ru.belogurow.year2022;

import java.util.List;

import ru.belogurow.Day;

public class Day04 extends Day {

    public Day04() {
        super(4, 2022, "Camp Cleanup");
    }

    public Long solvePartOne() {
        List<String> lines = readLinesFromResources();
        long result = 0L;

        for (String line : lines) {
            if (contains(line)) {
                result += 1;
            }
        }

        return result;
    }

    public Long solvePartTwo() {
        List<String> lines = readLinesFromResources();
        long result = 0L;

        for (String line : lines) {
            if (overlap(line)) {
                result += 1;
            }
        }

        return result;
    }

    private boolean contains(String line) {
        String[] ranges = line.split(",");

        Range firstRange = Range.fromString(ranges[0]);
        Range secondRange = Range.fromString(ranges[1]);

        if (firstRange.left == secondRange.left || firstRange.right == secondRange.right) {
            return true;
        }

        if (firstRange.left < secondRange.left) {
            return firstRange.right > secondRange.right;
        } else {
            return secondRange.right > firstRange.right;
        }
    }

    private boolean overlap(String line) {
        String[] ranges = line.split(",");

        Range firstRange = Range.fromString(ranges[0]);
        Range secondRange = Range.fromString(ranges[1]);

        return firstRange.left <= secondRange.right && firstRange.right >= secondRange.left;
    }

    private record Range(int left, int right) {
        public static Range fromString(String str) {
            String[] elements = str.split("-");
            return new Range(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
        }
    }
}
