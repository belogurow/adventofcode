package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {

    private final Day05 day05 = new Day05();

    @Test
    void solvePartOne() {
        var result = day05.solvePartOne();
        Assertions.assertEquals("VWLCWGSDQ", result);
    }

    @Test
    void solvePartTwo() {
        var result = day05.solvePartTwo();
        Assertions.assertEquals("TCGLQSLPW", result);
    }

}