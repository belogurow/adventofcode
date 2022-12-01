package ru.belogurow.year2022;

import java.util.AbstractList;
import java.util.Arrays;

import ru.belogurow.Day;

public class Day01 extends Day {

    public Day01() {
        super(1, 2022, "Calorie Counting");
    }

    public long solvePartOne() {
        var lines = readLinesFromResources();
        var maxCalories = 0L;

        var currentCalories = 0L;
        for (String line : lines) {

            if (line.isEmpty()) {
                if (currentCalories > maxCalories) {
                    maxCalories = currentCalories;
                }

                currentCalories = 0L;
            } else {
                currentCalories += Long.parseLong(line);
            }
        }

        return maxCalories;
    }

    public long solvePartTwo() {
        var lines = readLinesFromResources();
        var caloriesList = new CaloriesList(3);

        var currentCalories = 0L;
        for (String line : lines) {

            if (line.isEmpty()) {
                caloriesList.add(currentCalories);
                currentCalories = 0L;
            } else {
                currentCalories += Long.parseLong(line);
            }
        }

        return caloriesList.sum();
    }

    private static class CaloriesList extends AbstractList<Long> {

        private final long[] elements;

        public CaloriesList(int size) {
            this.elements = new long[size];
        }

        public void add(long calories) {
            for (int i = 0; i < elements.length; i++) {
                if (calories > elements[i]) {
                    shiftElements(i);
                    elements[i] = calories;
                    return;
                }
            }
        }

        private void shiftElements(int startIndex) {
            for (int i = elements.length - 1; i > startIndex; i--) {
                elements[i] = elements[i - 1];
            }
        }

        @Override
        public Long get(int index) {
            return elements[index];
        }

        @Override
        public int size() {
            return elements.length;
        }

        public long sum() {
            return Arrays.stream(elements)
                    .sum();
        }
    }
}
