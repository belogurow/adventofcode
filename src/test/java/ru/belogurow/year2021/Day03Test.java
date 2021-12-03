package ru.belogurow.year2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

	private final Day03 day03 = new Day03();

	@Test
	void solvePartOne() {
		int result = day03.solvePartOne();
		Assertions.assertEquals(3847100, result);
	}

	@Test
	void solvePartTwo() {
		int result = day03.solvePartTwo();
		Assertions.assertEquals(4105235, result);
	}
}