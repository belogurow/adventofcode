package ru.belogurow.year2022;

import java.util.ArrayList;
import java.util.List;

import ru.belogurow.Day;

public class Day10 extends Day {

    public Day10() {
        super(10, 2022, "Cathode-Ray Tube");
    }

    @Override
    public Long solvePartOne() {
        List<String> lines = readLinesFromResources();

        long x = 1;
        long sumSignals = 0;

        for (int lineI = 0, cycle = 0, addx = 1; lineI < lines.size(); ) {
            cycle++;

            if ((cycle - 20) % 40 == 0) {
                sumSignals += x * cycle;
            }

            String[] elems = lines.get(lineI).split(" ");
            if (elems.length == 1) { // noop
                lineI++;
            } else if (elems.length == 2) {// addx
                if (addx == 1) { // first addx
                    addx = 2;
                } else { // second addx
                    addx = 1;
                    x += Long.parseLong(elems[1]);
                    lineI++;
                }
            }
        }


        return sumSignals;
    }

    @Override
    public String solvePartTwo() {
        List<String> lines = readLinesFromResources();

        long x = 1;
        List<String> result = new ArrayList<>();
        long spritePos = 0;

        for (int lineI = 0, cycle = 0, addx = 1; lineI < lines.size(); ) {
            cycle++;

            int cyclePos = (cycle - 1) % 40;
            if (cyclePos == 0) {
                result.add("\n");
            }

            if (spritePos <= cyclePos && cyclePos < spritePos + 3) {
                result.add("#");
            } else {
                result.add(".");
            }

            String[] elems = lines.get(lineI).split(" ");
            if (elems.length == 1) { // noop
                lineI++;
            } else if (elems.length == 2) {// addx
                if (addx == 1) { // first addx
                    addx = 2;
                } else { // second addx
                    addx = 1;
                    x += Long.parseLong(elems[1]);
                    spritePos = x - 1;
                    lineI++;
                }
            }
        }

        return String.join("", result);
    }
}
