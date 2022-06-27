package lab11.ex05;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicLong;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class Visitor {
    public Visitor(String pathString, Boolean isVisiting) {
        final AtomicLong size = new AtomicLong(0);
        try {
            Path path = Paths.get(pathString);
            String[] pathData = (path + "").split("/");
            String pathName = String.join("/", pathData);
            Files.walkFileTree(path, new FileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (!isVisiting && path != dir) {
                        return FileVisitResult.SKIP_SUBTREE;
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    String[] fp1 = (file + "").split("/");
                    String[] fp2 = Arrays.copyOf(fp1, fp1.length - 1);
                    String dirname = String.join("/", fp2);
                    long fileSize = attrs.size();

                    if (dirname.equals(pathName)) {
                        System.out.println(fp1[fp1.length - 1] + ": " + fileSize + " kB");
                    } else if (isVisiting) {
                        System.out.println(diff(dirname, pathName) + "->" + fp1[fp1.length - 1] + ":"+ fileSize + " kB");
                    }
                    size.addAndGet(fileSize);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    System.err.println("FAILED visiting: " + file);
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e) {
                    if (path == dir) System.out.println("Total: " + size.get() + " kB");
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
    private String diff(String s1, String s2) {
        int index = s1.lastIndexOf(s2);
        if (index > -1) {
            return s1.substring(s2.length());
        }
        return s1;
    }
}