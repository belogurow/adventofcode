package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

    private final Day06 day06 = new Day06();

    @Test
    void solvePartOne() {
        var result = day06.solvePartOne();
        Assertions.assertEquals(1920, result);
    }

    @Test
    void solvePartTwo() {
        var result = day06.solvePartTwo();
        Assertions.assertEquals(2334, result);
    }

}