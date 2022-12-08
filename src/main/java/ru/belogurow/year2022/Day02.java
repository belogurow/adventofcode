package ru.belogurow.year2022;

import java.util.NoSuchElementException;

import ru.belogurow.Day;

public class Day02 extends Day {

    private static final char ROCK = 'A';
    private static final char PAPER = 'B';
    private static final char SCISSORS = 'C';

    private static final char NEED_LOSE = 'X';
    private static final char NEED_DRAW = 'Y';
    private static final char NEED_WIN = 'Z';

    private static final long DRAW_SCORE = 3;
    private static final long WON_SCORE = 6;

    public Day02() {
        super(2, 2022, "Rock Paper Scissors");
    }

    public Long solvePartOne() {
        long result = 0;

        for (String line : readLinesFromResources()) {
            result += getScorePartOne(line);
        }

        return result;
    }

    public Long solvePartTwo() {
        long result = 0L;

        for (String line : readLinesFromResources()) {
            result += getScorePartTwo(line);
        }

        return result;
    }

    private long getScorePartOne(String line) {
        char p1 = line.charAt(0);
        char p2 = (char) (line.charAt(2) - 23);

        if (p1 == p2) {
            return DRAW_SCORE + getScoreOfSign(p2);
        }

        if (p1 == ROCK && p2 == PAPER) {
            return WON_SCORE + getScoreOfSign(p2);
        }

        if (p1 == PAPER && p2 == SCISSORS) {
            return WON_SCORE + getScoreOfSign(p2);
        }

        if (p1 == SCISSORS && p2 == ROCK) {
            return WON_SCORE + getScoreOfSign(p2);
        }

        // with lost score = 0 + getScoreOfSign(p2)
        return getScoreOfSign(p2);
    }

    private long getScorePartTwo(String line) {
        char p1 = line.charAt(0);
        char p2 = line.charAt(2);

        return switch (p2) {
            case NEED_LOSE -> switch (p1) {
                case ROCK -> getScoreOfSign(SCISSORS);
                case PAPER -> getScoreOfSign(ROCK);
                case SCISSORS -> getScoreOfSign(PAPER);
                default -> throw new NoSuchElementException();
            };
            case NEED_DRAW -> DRAW_SCORE + getScoreOfSign(p1);
            case NEED_WIN -> WON_SCORE + switch (p1) {
                case ROCK -> getScoreOfSign(PAPER);
                case PAPER -> getScoreOfSign(SCISSORS);
                case SCISSORS -> getScoreOfSign(ROCK);
                default -> throw new NoSuchElementException();
            };
            default -> throw new NoSuchElementException();
        };
    }

    private long getScoreOfSign(char sign) {
        return switch (sign) {
            case ROCK -> 1L;
            case PAPER -> 2L;
            case SCISSORS -> 3L;
            default -> throw new NoSuchElementException();
        };
    }
}
