package ru.belogurow;

public record AoCTestResult(Day day,
                            String firstPartAnswer,
                            String secondPartAnswer) {

    public static AoCTestResult of(Day day,
                                   String firstPartAnswer,
                                   String secondPartAnswer) {
        return new AoCTestResult(day, firstPartAnswer, secondPartAnswer);
    }

    public String solveFirstPart() {
        return String.valueOf(day.solvePartOne());
    }

    public String solveSecondPart() {
        return String.valueOf(day.solvePartTwo());
    }
}