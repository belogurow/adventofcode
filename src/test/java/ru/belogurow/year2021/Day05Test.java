package ru.belogurow.year2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day05Test {

	private final Day05 day05 = new Day05();

	@Test
	void solvePartOne() {
		var result = day05.solvePartOne();
		Assertions.assertEquals(5608, result);
	}

	@Test
	void solvePartTwo() {
		var result = day05.solvePartTwo();
		Assertions.assertEquals(20299, result);
	}
}
