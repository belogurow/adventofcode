package ru.belogurow.year2022;

import java.util.Arrays;

import ru.belogurow.Day;

public class Day08 extends Day {

    public Day08() {
        super(8,  2022, "Treetop Tree House");
    }

    public Integer solvePartOne() {
        int[][] trees = readLinesFromResources().stream()
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        int visibleTrees = (trees.length - 1) * 2 + (trees[0].length - 1) * 2; // edge trees

        for (int i = 1; i < trees.length - 1; i++) {
            int[] treeRow = trees[i];
            for (int j = 1; j < treeRow.length - 1; j++) {
                if (isVisible(i, j, trees)) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees;
    }

    private boolean isVisible(int indexRow, int indexCol, int[][] trees) {
        int treeHeight = trees[indexRow][indexCol];

        // from top
        for (int i = 0; i < indexRow; i++) {
            if (trees[i][indexCol] >= treeHeight) {
                break;
            }

            if (i == indexRow - 1) {
                return true;
            }
        }

        // from left
        for (int i = 0; i < indexCol; i++) {
            if (trees[indexRow][i] >= treeHeight) {
                break;
            }

            if (i == indexCol - 1) {
                return true;
            }
        }

        // from right
        for (int i = trees[0].length - 1; i > indexCol; i--) {
            if (trees[indexRow][i] >= treeHeight) {
                break;
            }

            if (i == indexCol + 1) {
                return true;
            }
        }

        // from bottom
        for (int i = trees.length - 1; i > indexRow; i--) {
            if (trees[i][indexCol] >= treeHeight) {
                break;
            }

            if (i == indexRow + 1) {
                return true;
            }
        }

        return false;
    }

    public Integer solvePartTwo() {
        int[][] trees = readLinesFromResources().stream()
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        int maxScenicScore = 0;

        for (int i = 1; i < trees.length - 1; i++) {
            int[] treeRow = trees[i];
            for (int j = 1; j < treeRow.length - 1; j++) {
                int scenicScore = getScenicScore(i, j, trees);
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }

        return maxScenicScore;
    }

    private int getScenicScore(int indexRow, int indexCol, int[][] trees) {
        int treeHeight = trees[indexRow][indexCol];

        int topScenicScore = 0;
        for (int i = indexRow - 1; i >= 0; i--) {
            topScenicScore++;

            if (trees[i][indexCol] >= treeHeight) {
                break;
            }
        }

        int leftScenicScore = 0;
        for (int i = indexCol - 1; i >= 0; i--) {
            leftScenicScore++;

            if (trees[indexRow][i] >= treeHeight) {
                break;
            }
        }

        int rightScenicScore = 0;
        for (int i = indexCol + 1; i < trees[0].length; i++) {
            rightScenicScore++;

            if (trees[indexRow][i] >= treeHeight) {
                break;
            }
        }

        int bottomScenicScore = 0;
        for (int i = indexRow + 1; i < trees.length; i++) {
            bottomScenicScore++;

            if (trees[i][indexCol] >= treeHeight) {
                break;
            }
        }

        return topScenicScore * leftScenicScore * rightScenicScore * bottomScenicScore;
    }
}
