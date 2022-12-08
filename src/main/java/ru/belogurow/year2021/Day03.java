package ru.belogurow.year2021;

import ru.belogurow.Day;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day03 extends Day {

	public Day03() {
		super(3, 2021, "Binary Diagnostic");
	}

	public Integer solvePartOne() {
		var lines = readLinesFromResources();
		var bitLength = lines.get(0).length();

		var rates = new int[bitLength][2];

		for (String line : lines) {
			var elements = line.split("");

			for (int i = 0; i < elements.length; i++) {
				if (elements[i].equals("0")) {
					rates[i][0]++;
				} else {
					rates[i][1]++;
				}
			}
		}

		var gammaRateBinary = new StringBuilder();
		var epsilonRateBinary = new StringBuilder();
		for (int[] rate : rates) {
			if (rate[0] > rate[1]) {
				gammaRateBinary.append("0");
				epsilonRateBinary.append("1");
			} else {
				gammaRateBinary.append("1");
				epsilonRateBinary.append("0");
			}
		}

		var gammaRate = Integer.parseInt(gammaRateBinary.toString(), 2);
		var epsilonRate = Integer.parseInt(epsilonRateBinary.toString(), 2);

		return gammaRate * epsilonRate;
	}

	public Integer solvePartTwo() {
		var lines = readLinesFromResources();

		var oxygenGeneratorRatingBinary = findBitRatingFor(lines, true);
		var c02ScrubberRatingBinary = findBitRatingFor(lines, false);

		var oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingBinary, 2);
		var c02ScrubberRating = Integer.parseInt(c02ScrubberRatingBinary, 2);

		return oxygenGeneratorRating * c02ScrubberRating;
	}

	private String findBitRatingFor(List<String> sourceBits,
	                                boolean isMostFrequentBit) {
		var bitsCount = sourceBits.get(0).length();
		var filteredBits = sourceBits;

		for (int i = 0; i < bitsCount; i++) {
			if (filteredBits.size() == 1) {
				break;
			}

			var foundBit = findBit(filteredBits, i, isMostFrequentBit);

			int finalI = i;
			filteredBits = filteredBits.stream()
					.filter(line -> line.charAt(finalI) == foundBit)
					.toList();
		}

		return filteredBits.get(0);
	}

	private char findBit(List<String> lines, int position, boolean isMostFrequentBit) {
		var occurrencesMap = lines.stream()
				.map(line -> line.split("")[position])
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		var mostFrequentBit = occurrencesMap.get("0") > occurrencesMap.get("1")
				? '0'
				: '1';

		return isMostFrequentBit
				? mostFrequentBit
				: negate(mostFrequentBit);
	}

	private char negate(char c) {
		return c == '0'
				? '1'
				: '0';
	}
}
