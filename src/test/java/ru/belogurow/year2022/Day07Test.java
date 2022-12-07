package ru.belogurow.year2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day07Test {

    private final Day07 day07 = new Day07();

    @Test
    void solvePartOne() {
        var result = day07.solvePartOne();
        Assertions.assertEquals(1581595L, result);
    }

    @Test
    void solvePartTwo() {
        var result = day07.solvePartTwo();
        Assertions.assertEquals(1544176L, result);
    }

}