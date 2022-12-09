package ru.belogurow.year2022;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.belogurow.Day;

public class Day09 extends Day {

    public Day09() {
        super(9, 2022, "Rope Bridge");
    }

    @Override
    public Long solvePartOne() {
        return solve(2);
    }

    @Override
    public Long solvePartTwo() {
        return solve(10);
    }

    private Long solve(int ropeLength) {
        List<String> lines = readLinesFromResources();

        int maxHeight = 600, maxLength = 600; // random
        int[][] field = new int[maxHeight][maxLength];

        Rope rope = new Rope(ropeLength, maxHeight / 2);
        updateTailFieldMotion(field, rope.getTail());

        for (String line : lines) {
            String[] elem = line.split(" ");
            int steps = Integer.parseInt(elem[1]);
            char direction = elem[0].charAt(0);

            for (int i = 0; i < steps; i++) {
                Position newPosHead = switch (direction) {
                    case 'U' -> rope.getHead().up();
                    case 'R' -> rope.getHead().right();
                    case 'L' -> rope.getHead().left();
                    case 'D' -> rope.getHead().down();
                    default -> throw new IllegalStateException("Unexpected value: " + direction);
                };
                rope.moveHead(newPosHead);

                for (int j = 1; j < rope.positions().size(); j++) { // except head
                    Position newPos = moveTail(rope.positions().get(j - 1), rope.positions().get(j));
                    rope.movePos(j, newPos);

                    if (j == rope.positions().size() - 1) {
                        updateTailFieldMotion(field, rope.getTail());
                    }
                }
            }
        }

        return Arrays.stream(field)
                .flatMapToInt(Arrays::stream)
                .filter(a -> a > 0)
                .count();
    }

    private Position moveTail(Position posHead, Position posTail) {
        if (getDistance(posHead, posTail) < 2) {
            return posTail;
        }

        if (posHead.row > posTail.row) {
            posTail = posTail.down();
        }

        if (posHead.row < posTail.row) {
            posTail = posTail.up();
        }

        if (posHead.column > posTail.column) {
            posTail = posTail.right();
        }

        if (posHead.column < posTail.column) {
            posTail = posTail.left();
        }

        return posTail;
    }

    private void updateTailFieldMotion(int[][] field, Position posTail) {
        field[posTail.row][posTail.column]++;
    }

    private double getDistance(Position posHead, Position posTail) {
        return Math.sqrt(
                Math.pow(posHead.row() - posTail.row(), 2) + Math.pow(posHead.column() - posTail.column(), 2));
    }

    private record Rope(int length,
                        List<Position> positions) {
        public Rope(int length, int startPos) {
            this(length, IntStream.range(0, length)
                    .mapToObj(i -> Position.start(startPos))
                    .collect(Collectors.toList()));
        }

        public Position getHead() {
            return positions.get(0);
        }

        public Position getTail() {
            return positions.get(positions.size() - 1);
        }

        public void moveHead(Position newHead) {
            positions.set(0, newHead);
        }

        public void movePos(int index, Position newPos) {
            positions.set(index, newPos);
        }
    }

    private record Position(int row,
                            int column) {

        public static Position start(int startPos) {
            return new Position(startPos, startPos);
        }

        public Position up() {
            return new Position(row - 1, column);
        }

        public Position right() {
            return new Position(row, column + 1);
        }

        public Position left() {
            return new Position(row, column - 1);
        }

        public Position down() {
            return new Position(row + 1, column);
        }
    }
}
