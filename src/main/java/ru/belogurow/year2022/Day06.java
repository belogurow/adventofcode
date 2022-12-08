package ru.belogurow.year2022;

import ru.belogurow.Day;

public class Day06 extends Day {

    public Day06() {
        super(6, 2022, "Tuning Trouble");
    }

    public Integer solvePartOne() {
        return solve(4);
    }

    public Integer solvePartTwo() {
        return solve(14);
    }

    private int solve(int distinctChars) {
        char[] allChars = readLinesFromResources().get(0).toCharArray();

        char[] chars;
        int i = distinctChars - 2;
        do {
            i++;
            chars = new char[distinctChars];
            for (int j = distinctChars, k = 0; j > 0; j--, k++) {
                chars[k] = allChars[i - j + 1];
            }
        } while (!allDifferent(chars));

        return i + 1;
    }

    private boolean allDifferent(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
