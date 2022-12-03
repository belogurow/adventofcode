package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

    private final Day03 day03 = new Day03();

    @Test
    void solvePartOne() {
        var result = day03.solvePartOne();
        Assertions.assertEquals(8349L, result);
    }

    @Test
    void solvePartTwo() {
        var result = day03.solvePartTwo();
        Assertions.assertEquals(2681, result);
    }
}