package ru.belogurow.year2021;

import ru.belogurow.Day;

public class Day01 extends Day {

	public Day01() {
		super(1, 2021, "Sonar Sweep");
	}

	public int solvePartOne() {
		var inputList = readIntegersFromResources();
		var count = 0;

		for (int i = 1; i < inputList.size(); i++) {
			if (inputList.get(i) > inputList.get(i - 1)) {
				count++;
			}
		}

		return count;
	}

	public int solvePartTwo() {
		var inputList = readLinesFromResources().stream()
				.map(line -> line.split(" ")[0])
				.map(Integer::parseInt)
				.toList();
		var count = 0;

		for (int i = 2; i < inputList.size() - 1; i++) {
			var firstMeasurement = inputList.get(i - 2) + inputList.get(i - 1) + inputList.get(i);
			var secondMeasurement = inputList.get(i - 1) + inputList.get(i) + inputList.get(i + 1);

			if (secondMeasurement > firstMeasurement) {
				count++;
			}
		}

		return count;
	}
}