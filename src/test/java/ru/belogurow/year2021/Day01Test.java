package ru.belogurow.year2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {

	private final Day01 day01 = new Day01();

	@Test
	void solvePartOne() {
		int result = day01.solvePartOne();
		Assertions.assertEquals(1316, result);
	}

	@Test
	void solvePartTwo() {
		int result = day01.solvePartTwo();
		Assertions.assertEquals(1344, result);
	}
}