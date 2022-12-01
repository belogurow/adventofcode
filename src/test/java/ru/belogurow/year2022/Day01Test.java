package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {

    private final Day01 day01 = new Day01();

    @Test
    void solvePartOne() {
        var result = day01.solvePartOne();
        Assertions.assertEquals(69310, result);
    }

    @Test
    void solvePartTwo() {
        var result = day01.solvePartTwo();
        Assertions.assertEquals(206104, result);
    }
}