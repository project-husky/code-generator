package org.ehealth_connector.codegenerator.java;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * A wrapper
 *
 * @author Quentin Ligier
 */
public class JavaCodeFormatter {

    /**
     * The Java code formatter.
     */
    private static final Formatter FORMATTER = new Formatter();

    /**
     *
     *
     * @param javaFile
     * @throws IOException
     * @throws FormatterException
     */
    public static void formatFile(final File javaFile) throws IOException, FormatterException {
        final String source = Files.readString(javaFile.toPath(), StandardCharsets.UTF_8);
        final String formattedSource = FORMATTER.formatSourceAndFixImports(source);
        Files.writeString(javaFile.toPath(), formattedSource, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
