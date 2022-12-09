package ru.belogurow.year2022;

import java.util.stream.Stream;

import ru.belogurow.AoCAbstractTest;
import ru.belogurow.AoCTestResult;

public class Year2022Tests extends AoCAbstractTest {

    @Override
    public Stream<AoCTestResult> getTestResultStream() {
        return Stream.of(
                AoCTestResult.of(new Day01(), "69310", "206104"),
                AoCTestResult.of(new Day02(), "10994", "12526"),
                AoCTestResult.of(new Day03(), "8349", "2681"),
                AoCTestResult.of(new Day04(), "494", "833"),
                AoCTestResult.of(new Day05(), "VWLCWGSDQ", "TCGLQSLPW"),
                AoCTestResult.of(new Day06(), "1920", "2334"),
                AoCTestResult.of(new Day07(), "1581595", "1544176"),
                AoCTestResult.of(new Day08(), "1708", "504000"),
                AoCTestResult.of(new Day09(), "6311", "2482"));
    }
}
