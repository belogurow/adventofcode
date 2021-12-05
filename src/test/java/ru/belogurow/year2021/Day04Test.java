package ru.belogurow.year2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04Test {

	private final Day04 day04 = new Day04();

	@Test
	void solvePartOne() {
		var result = day04.solvePartOne();
		Assertions.assertEquals(39984, result);
	}

	@Test
	void solvePartTwo() {
		var result = day04.solvePartTwo();
		Assertions.assertEquals(8468, result);
	}
}