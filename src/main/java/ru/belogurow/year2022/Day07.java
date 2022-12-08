package ru.belogurow.year2022;

import java.util.ArrayList;
import java.util.List;

import ru.belogurow.Day;

public class Day07 extends Day {

    public Day07() {
        super(7, 2022, "No Space Left On Device");
    }

    public Object solvePartOne() {
        Directory rootDir = parseFileSystem(readLinesFromResources());

        long maxSize = 100_000;
        return rootDir.getSizesForAllDirectories().stream()
                .filter(dirSize -> dirSize < maxSize)
                .reduce(0L, Long::sum);
    }

    public Object solvePartTwo() {
        Directory rootDir = parseFileSystem(readLinesFromResources());

        long totalSpace = 70_000_000;
        long maxSize = 30_000_000;
        long needSizeToDelete = maxSize - (totalSpace - rootDir.getSize());

        return rootDir.getSizesForAllDirectories().stream()
                .filter(dirSize -> dirSize >= needSizeToDelete)
                .min(Long::compare)
                .orElse(0L);
    }

    private Directory parseFileSystem(List<String> lines) {
        Directory rootDir = new Directory("/", null);

        Directory currentDirectory = rootDir;

        for (int i = 1; i < lines.size(); i++) {
            String[] lineElements = lines.get(i).split(" ");

            if (isCommand(lineElements[0])) {
                if (isChangeDirectory(lineElements[1])) {
                    if ("..".equals(lineElements[2])) {
                        currentDirectory = currentDirectory.getParentDirectory();
                    } else {
                        currentDirectory = currentDirectory.getChildDirectory(lineElements[2]);
                    }
                    continue;
                }

                if (isListFiles(lineElements[1])) {
                    // nothing
                }
            } else {
                // create dirs and files in current directory
                if (isDir(lineElements[0])) {
                    currentDirectory.createDirectory(lineElements[1]);
                } else {
                    currentDirectory.createFile(lineElements[1], Long.parseLong(lineElements[0]));
                }
            }
        }

        return rootDir;
    }

    private boolean isCommand(String elem) {
        return "$".equals(elem);
    }

    private boolean isListFiles(String elem) {
        return "ls".equals(elem);
    }

    private boolean isChangeDirectory(String elem) {
        return "cd".equals(elem);
    }

    private boolean isDir(String elem) {
        return "dir".equals(elem);
    }

    // with record Directory I got StackOverflowError in link to parentDirectory (???)
    private static class Directory {
        private final String name;
        private final Directory parentDirectory;
        private final List<Directory> directories;
        private final List<File> files;
        private Long size;

        public Directory(String name, Directory parentDirectory) {
            this.name = name;
            this.parentDirectory = parentDirectory;
            this.directories = new ArrayList<>();
            this.files = new ArrayList<>();
        }

        public Directory getChildDirectory(String name) {
            return directories.stream()
                    .filter(dir -> name.equals(dir.getName()))
                    .findFirst()
                    .orElse(null);
        }

        public void createDirectory(String name) {
            directories.add(new Directory(name, this));
        }

        public void createFile(String name, long size) {
            files.add(new File(name, size));
        }

        public long getSize() {
            if (this.size == null) {
                long directoriesSize = directories.stream()
                        .mapToLong(Directory::getSize)
                        .sum();
                long filesSize = files.stream()
                        .mapToLong(File::size)
                        .sum();
                this.size = directoriesSize + filesSize;
            }

            return this.size;
        }

        public List<Long> getSizesForAllDirectories() {
            List<Long> sizes = new ArrayList<>();
            long size = getSize();
            sizes.add(size);
//            System.out.println(getName() + " " + size);

            for (Directory directory : directories) {
                sizes.addAll(directory.getSizesForAllDirectories());
            }

            return sizes;
        }

        public String getName() {
            return name;
        }

        public Directory getParentDirectory() {
            return parentDirectory;
        }

        public List<Directory> getDirectories() {
            return directories;
        }

        public List<File> getFiles() {
            return files;
        }
    }

    private record File(String name,
                        long size) {

    }
}
