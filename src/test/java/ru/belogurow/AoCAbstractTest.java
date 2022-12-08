package ru.belogurow;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AoCAbstractTest {

    private static final String PART_1 = "Part 1";
    private static final String PART_2 = "Part 2";

    public abstract Stream<AoCTestResult> getTestResultStream();

    private String getTestName(AoCTestResult testResult) {
        return testResult.day().getClass().getSimpleName() + " \"" + testResult.day().getTitle() + "\"";
    }

    private Stream<? extends DynamicNode> getDynamicTests(AoCTestResult testResult) {
        return Stream.of(
                DynamicTest.dynamicTest(PART_1, () -> assertEquals(testResult.firstPartAnswer(), testResult.solveFirstPart())),
                DynamicTest.dynamicTest(PART_2, () -> assertEquals(testResult.secondPartAnswer(), testResult.solveSecondPart())));
    }

    @TestFactory
    Stream<DynamicNode> dynamicContainer() {
        return getTestResultStream()
                .map(input -> DynamicContainer.dynamicContainer(getTestName(input), getDynamicTests(input)));
    }
}
