package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

    private final Day02 day02 = new Day02();

    @Test
    void solvePartOne() {
        var result = day02.solvePartOne();
        Assertions.assertEquals(10994L, result);
    }

    @Test
    void solvePartTwo() {
        var result = day02.solvePartTwo();
        Assertions.assertEquals(12526, result);
    }
}