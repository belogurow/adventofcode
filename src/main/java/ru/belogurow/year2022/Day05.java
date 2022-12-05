package ru.belogurow.year2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import ru.belogurow.Day;

public class Day05 extends Day {

    public Day05() {
        super(5, 2022, "Supply Stacks");
    }

    public String solvePartOne() {
        return solve(false);
    }

    public String solvePartTwo() {
        return solve(true);
    }

    private String solve(boolean needOrder) {
        List<String> lines = readLinesFromResources();

        List<Stack<Character>> stacks = new ArrayList<>();
        boolean readStacks = true;

        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);

            if (readStacks) {
                if (currentLine.charAt(1) != '1') {
                    populateStackWithNewValues(stacks, currentLine);
                } else {
                    readStacks = false;
                    i++; // skip empty line
                }
            } else {
                rearrangeElements(stacks, currentLine, needOrder);
            }
        }

        return stacks.stream()
                .map(Stack::peek)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private void populateStackWithNewValues(List<Stack<Character>> stacks, String currentLine) {
        Character[] characters = readLine(currentLine);
        for (int j = 0; j < characters.length; j++) {
            if (j >= stacks.size()) {
                stacks.add(new Stack<>());
            }

            if (characters[j] != 32) { // if not empty
                stacks.get(j).add(0, characters[j]);
            }
        }
    }

    private Character[] readLine(String line) {
        int stackCount = (line.length() + 1) / 4;

        Character[] elems = new Character[stackCount];
        for (int i = 0; i < stackCount; i++) {
            elems[i] = line.charAt(1 + 4 * i);
        }

        return elems;
    }

    private void rearrangeElements(List<Stack<Character>> stacks,
                                   String currentLine,
                                   boolean needOrder) {
        String[] moveElements = currentLine.split(" ");
        int countElementsForMove = Integer.parseInt(moveElements[1]);
        int sourceStackNum = Integer.parseInt(moveElements[3]) - 1;
        int targetStackNum = Integer.parseInt(moveElements[5]) - 1;

        if (needOrder) {
            for (int j = 0; j < countElementsForMove; j++) {
                Character elementToPush = stacks.get(sourceStackNum).pop();
                Stack<Character> targetStack = stacks.get(targetStackNum);
                if (j == 0) {
                    targetStack.add(elementToPush);
                } else {
                    targetStack.add(targetStack.toArray().length - j, elementToPush);
                }
            }
        } else {
            for (int j = 0; j < countElementsForMove; j++) {
                stacks.get(targetStackNum)
                        .push(stacks.get(sourceStackNum).pop());
            }
        }
    }
}
