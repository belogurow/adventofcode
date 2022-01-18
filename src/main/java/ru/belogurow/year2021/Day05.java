package ru.belogurow.year2021;

import ru.belogurow.Day;

import java.util.List;

public class Day05 extends Day {

	public Day05() {
		super(5, 2021, "Hydrothermal Venture");
	}

	public int solvePartOne() {
		var parsedLines = parseLines();
		var filteredLines = parsedLines.stream()
				.filter(line -> line.isHorizontal() || line.isVertical())
				.toList();

		var diagram = new int[1000][1000]; // based on test data

		for (Line line : filteredLines) {
			if (line.isVertical()) {
				processVerticalLine(diagram, line);
			} else {
				processHorizontalLine(diagram, line);
			}
		}

		return findOverlapPoints(diagram);
	}

	public int solvePartTwo() {
		var parsedLines = parseLines();
		var diagram = new int[1000][1000]; // based on test data

		for (Line line : parsedLines) {
			if (line.isVertical()) {
				processVerticalLine(diagram, line);
			} else if (line.isHorizontal()) {
				processHorizontalLine(diagram, line);
			} else {
				processDiagonalLine(diagram, line);
			}
		}

		return findOverlapPoints(diagram);
	}

	private void processVerticalLine(int[][] diagram, Line line) {
		boolean isPositive = line.a.y <= line.b.y;
		var currentY = line.a.y;
		var currentX = line.a.x;
		var length = Math.abs(line.a.y - line.b.y);

		for (int i = 0; i <= length; i++) {
			diagram[currentY][currentX] += 1;

			if (isPositive) {
				currentY += 1;
			} else {
				currentY -= 1;
			}
		}
	}

	private void processHorizontalLine(int[][] diagram, Line line) {
		boolean isPositive = line.a.x <= line.b.x;
		var currentY = line.a.y;
		var currentX = line.a.x;
		var length = Math.abs(line.a.x - line.b.x);

		for (int i = 0; i <= length; i++) {
			diagram[currentY][currentX] += 1;

			if (isPositive) {
				currentX += 1;
			} else {
				currentX -= 1;
			}
		}
	}

	private void processDiagonalLine(int[][] diagram, Line line) {
		boolean isPositiveY = line.a.y <= line.b.y;
		boolean isPositiveX = line.a.x <= line.b.x;

		var currentY = line.a.y;
		var currentX = line.a.x;
		var length = Math.abs(line.a.y - line.b.y);

		for (int i = 0; i <= length; i++) {
			diagram[currentY][currentX] += 1;

			if (isPositiveX) {
				currentX += 1;
			} else {
				currentX -= 1;
			}

			if (isPositiveY) {
				currentY += 1;
			} else {
				currentY -= 1;
			}
		}
	}

	private int findOverlapPoints(int[][] diagram) {
		var overlapPoints = 0;
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (diagram[i][j] >= 2) {
					overlapPoints += 1;
				}
			}
		}

		return overlapPoints;
	}

	private List<Day05.Line> parseLines() {
		var lines = readLinesFromResources();
		return lines.stream()
				.map(line -> {
					var points = line.split("(,)|( -> )");

					var firstPoint = new Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
					var secondPoint = new Point(Integer.parseInt(points[2]), Integer.parseInt(points[3]));
					return new Line(firstPoint, secondPoint);
				})
				.toList();
	}

	private record Point(int x, int y) {
	}

	private record Line(Point a, Point b) {
		public boolean isHorizontal() {
			return a.y == b.y;
		}

		public boolean isVertical() {
			return a.x == b.x;
		}

		public boolean isDiagonal() {
			return Math.abs(a.y - b.y) == Math.abs(a.x - b.x);
		}
	}
}
