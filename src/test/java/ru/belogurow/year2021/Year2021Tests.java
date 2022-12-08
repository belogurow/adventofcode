package ru.belogurow.year2021;

import java.util.stream.Stream;

import ru.belogurow.AoCAbstractTest;
import ru.belogurow.AoCTestResult;

public class Year2021Tests extends AoCAbstractTest {

    @Override
    public Stream<AoCTestResult> getTestResultStream() {
        return Stream.of(
                AoCTestResult.of(new Day01(), "1316", "1344"),
                AoCTestResult.of(new Day02(), "2272262", "2134882034"),
                AoCTestResult.of(new Day03(), "3847100", "4105235"),
                AoCTestResult.of(new Day04(), "39984", "8468"),
                AoCTestResult.of(new Day05(), "5608", "20299"));
    }
}
