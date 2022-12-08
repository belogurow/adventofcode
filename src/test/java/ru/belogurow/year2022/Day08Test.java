package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day08Test {

    private final Day08 day08 = new Day08();

    @Test
    void solvePartOne() {
        var result = day08.solvePartOne();
        Assertions.assertEquals(1708, result);
    }

    @Test
    void solvePartTwo() {
        var result = day08.solvePartTwo();
        Assertions.assertEquals(504000, result);
    }
}
