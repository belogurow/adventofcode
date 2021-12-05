package ru.belogurow.year2021;

import lombok.Data;
import ru.belogurow.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Day04 extends Day {

	public Day04() {
		super(4, 2021, "Giant Squid");
	}

	public int solvePartOne() {
		var lines = readLinesFromResources();

		var inputNumbers = Arrays.stream(lines.get(0).split(","))
				.distinct()
				.toList();
		var bingoGrids = readBingoGrids(lines);

		for (String inputNumber : inputNumbers) {
			for (BingoGrid bingoGrid : bingoGrids) {
				bingoGrid.markNumber(inputNumber);

				if (bingoGrid.checkComplete()) {
					return bingoGrid.calculateScore(inputNumber);
				}
			}
		}

		return 0;
	}

	public int solvePartTwo() {
		var lines = readLinesFromResources();

		var inputNumbers = Arrays.stream(lines.get(0).split(","))
				.distinct()
				.toList();
		var bingoGrids = readBingoGrids(lines);

		for (String inputNumber : inputNumbers) {
			if (bingoGrids.size() == 1) {
				bingoGrids.get(0).markNumber(inputNumber);
				return bingoGrids.get(0).calculateScore(inputNumber);
			}

			for (BingoGrid bingoGrid : bingoGrids) {
				bingoGrid.markNumber(inputNumber);

				if (bingoGrid.checkComplete()) {
					bingoGrid.setCompleted(true);
				}
			}

			bingoGrids.removeIf(BingoGrid::isCompleted);
		}

		return 0;
	}

	private List<BingoGrid> readBingoGrids(List<String> inputLines) {
		var bingoGrids = new ArrayList<BingoGrid>();

		for (int i = 0; i < inputLines.size(); i++) {
			boolean emptyLine = inputLines.get(i).isEmpty();
			if (i == 0 || emptyLine) {
				continue;
			}

			BingoGrid bingoGrid = new BingoGrid(new BingoNumber[5][5]);
			for (int j = 0; j < 5; j++, i++) {
				var bingoNumbers = Arrays.stream(inputLines.get(i).strip().split("\\D+"))
						.map(BingoNumber::new)
						.toArray(BingoNumber[]::new);

				bingoGrid.getNumbers()[j] = bingoNumbers;
			}

			bingoGrids.add(bingoGrid);
		}

		return bingoGrids;
	}

	@Data
	private class BingoNumber {
		private final String number;
		private boolean marked;
	}

	@Data
	private class BingoGrid {

		private final BingoNumber[][] numbers;
		private boolean completed;

		public void markNumber(String inputNumber) {
			for (BingoNumber[] number : numbers) {
				for (BingoNumber bingoNumber : number) {
					if (bingoNumber.getNumber().equals(inputNumber)) {
						bingoNumber.setMarked(true);
					}
				}
			}
		}

		public boolean checkComplete() {
			for (int i = 0; i < 5; i++) {
				boolean isRowComplete = checkRowComplete(i);
				boolean isColumnComplete = checkColumnComplete(i);

				if (isRowComplete || isColumnComplete) {
					return true;
				}
			}

			return false;
		}

		public boolean checkRowComplete(int rowIndex) {
			return Arrays.stream(numbers[rowIndex]).allMatch(BingoNumber::isMarked);
		}

		public boolean checkColumnComplete(int columnIndex) {
			return Arrays.stream(numbers)
					.map(row -> row[columnIndex])
					.allMatch(BingoNumber::isMarked);
		}

		public int calculateScore(String inputNumber) {
			var sumOfUnmarkedNumbers = Arrays.stream(numbers)
					.map(row -> Arrays.stream(row)
							.filter(Predicate.not(BingoNumber::isMarked))
							.map(BingoNumber::getNumber)
							.mapToInt(Integer::parseInt)
							.sum())
					.mapToInt(Integer::intValue)
					.sum();

			return sumOfUnmarkedNumbers * Integer.parseInt(inputNumber);
		}
	}
}
