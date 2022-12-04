package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04Test {

    private final Day04 day04 = new Day04();

    @Test
    void solvePartOne() {
        var result = day04.solvePartOne();
        Assertions.assertEquals(494L, result);
    }

    @Test
    void solvePartTwo() {
        var result = day04.solvePartTwo();
        Assertions.assertEquals(833L, result);
    }

}