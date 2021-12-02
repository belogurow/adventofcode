package ru.belogurow.year2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

	private final Day02 day02 = new Day02();

	@Test
	void solvePartOne() {
		int result = day02.solvePartOne();
		Assertions.assertEquals(2272262, result);
	}

	@Test
	void solvePartTwo() {
		int result = day02.solvePartTwo();
		Assertions.assertEquals(2134882034, result);
	}
}