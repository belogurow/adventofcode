package ru.belogurow;

import java.util.List;

public abstract class Day {

	private final int dayNumber;
	private final int year;
	private final String title;

	public Day(int dayNumber, int year, String title) {
		this.dayNumber = dayNumber;
		this.year = year;
		this.title = title;
	}

	public List<String> readLinesFromResources() {
		return ResourcesReaderUtils.readFileFromResources(year, dayNumber);
	}

	public List<Integer> readIntegersFromResources() {
		return ResourcesReaderUtils.readFileFromResources(year, dayNumber).stream()
				.map(Integer::parseInt)
				.toList();
	}
}
