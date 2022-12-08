package ru.belogurow.year2021;

import ru.belogurow.Day;

import java.util.List;

public class Day02 extends Day {

	private final String FORWARD_STEP = "forward";
	private final String DOWN_STEP = "down";
	private final String UP_STEP = "up";

	public Day02() {
		super(2, 2021, "Dive!");
	}

	public Integer solvePartOne() {
		List<Step> steps = readSteps();

		var horizontalPosition = 0;
		var depth = 0;

		for (Step step : steps) {
			switch (step.action()) {
				case FORWARD_STEP -> horizontalPosition += step.units();
				case DOWN_STEP -> depth += step.units();
				case UP_STEP -> depth -= step.units();
			}
		}

		return horizontalPosition * depth;
	}

	public Integer solvePartTwo() {
		List<Step> steps = readSteps();

		var horizontalPosition = 0;
		var depth = 0;
		var aim = 0;

		for (Step step : steps) {
			switch (step.action()) {
				case FORWARD_STEP -> {
					horizontalPosition += step.units();
					depth += aim * step.units();
				}
				case DOWN_STEP -> aim += step.units();
				case UP_STEP -> aim -= step.units();
			}
		}

		return horizontalPosition * depth;
	}

	private List<Step> readSteps() {
		return readLinesFromResources().stream()
				.map(line -> {
					var elements = line.split(" ");
					return new Step(elements[0], Integer.parseInt(elements[1]));
				})
				.toList();
	}

	record Step(String action, int units) {
	}
}



