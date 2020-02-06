package br.com.projeto.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author jefferson
 */
public final class FileUtil {

    public static String toString(final String pathFile) throws IOException {
        return Files.readString(Paths.get(Objects.requireNonNull(FileUtil.class.getClassLoader().getResource(pathFile)).getPath()));
    }

    private FileUtil() {
    }

}
