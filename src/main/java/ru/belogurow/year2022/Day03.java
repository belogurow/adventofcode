package ru.belogurow.year2022;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import ru.belogurow.Day;

public class Day03 extends Day {

    public Day03() {
        super(3, 2022, "Rucksack Reorganization");
    }

    public long solvePartOne() {
        var lines = readLinesFromResources();
        var count = 0;

        for (String line : lines) {
            int c = getRepeatableChar(Arrays.asList(
                    line.substring(0, line.length() / 2),
                    line.substring(line.length() / 2)));
            count += getCharValue(c);
        }

        return count;
    }

    public long solvePartTwo() {
        var lines = readLinesFromResources();
        var count = 0;

        for (int i = 0; i < lines.size(); i = i + 3) {
            int c = getRepeatableChar(Arrays.asList(lines.get(i), lines.get(i + 1), lines.get(i + 2)));
            count += getCharValue(c);
        }

        return count;
    }

    private int getRepeatableChar(List<String> lines) {
        return lines.stream()
                .map(line -> line.chars()
                        .boxed()
                        .collect(Collectors.toSet()))
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(NoSuchElementException::new);
    }

    private long getCharValue(int c) {
        // 'a' <= c <= 'z'
        if (97 <= c && c <= 122) {
            return c - 96;
        } else {
            return c - 38;
        }
    }
}
