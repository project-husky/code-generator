/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.valuesets;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import org.husky.valueset.model.ValueSetEntry;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

/**
 * <div class="en">Contains helper methods and constants that are used to
 * dynamically generate the value sets.</div>
 */
public final class ValueSetUtil {

    /**
     * The class is not instantiable.
     */
    private ValueSetUtil() {
    }

    /**
     * Returns all duplicates in the list.
     *
     * @param list
     *            the list
     * @return the duplicates
     */
    public static List<ValueSetEntry> getDuplicates(final List<ValueSetEntry> list) {
        final List<ValueSetEntry> newList = new ArrayList<>();
        final List<ValueSetEntry> duplicatesList = new ArrayList<>();

        // Traverse through the first list
        for (final ValueSetEntry element : list) {

            final int level = element.getLevel();
            final String code = element.getCodeBaseType().getCode();
            final String displayName = element.getCodeBaseType().getDisplayName();

            // Traverse through the second list
            for (final ValueSetEntry e : newList) {
                if ((level == e.getLevel()) && (code.equals(e.getCodeBaseType().getCode())
                        || (displayName.equalsIgnoreCase(e.getCodeBaseType().getDisplayName())))) {
                    duplicatesList.add(element);
                    break;
                }
            }

            newList.add(element);
        }

        return duplicatesList;
    }

    /**
     * <div class="en">Create a file instance to a Java source file by its fully
     * qualified class name and a base folder.</div>
     *
     * @param baseJavaFolder
     *            The base source folder the Java code resides in.
     * @param fullyQualifiedClassName
     *            The class name including the package name.
     * @return A file instance of the Java file.
     */
    public static File getSourceFileName(final String baseJavaFolder, final String fullyQualifiedClassName) {
        return new File(new File(baseJavaFolder, "src/main/java"),
                fullyQualifiedClassName.replaceAll("\\.", "/") + ".java").getAbsoluteFile();
    }

    /**
     * <div class="en">Retrieves the primary type from a compilation unit.</div>
     *
     * @param javaSource
     *            Parsed AST representation of a Java file.
     * @return Parsed AST representation of the primary type found in the source
     *         file.
     * @throws IOException
     *             If there is no primary type found or the Compilation unit was
     *             not loaded from a file and the primary type could therefore
     *             not be established.
     * @throws InvalidClassException
     *             When failed to load primary type from compilation unit.
     */
    public static TypeDeclaration<?> loadPrimaryType(CompilationUnit javaSource)
            throws IOException {
        return javaSource.getPrimaryType().orElseThrow(() -> new InvalidClassException(
                "Failed to load primary type from compilation unit"));
    }

    /**
     * Removes all duplicates from the list.
     *
     * @param list
     *            the list
     * @return the duplicates
     */
    public static List<ValueSetEntry> removeDuplicates(final List<ValueSetEntry> list) {
        final List<ValueSetEntry> newList = new ArrayList<>();

        // Traverse through the first list
        for (final ValueSetEntry element : list) {

            final int level = element.getLevel();
            final String code = element.getCodeBaseType().getCode();
            final String displayName = element.getCodeBaseType().getDisplayName();
            boolean isDuplicate = false;

            // Traverse through the second list
            for (final ValueSetEntry e : newList) {
                if ((level == e.getLevel()) && (code.equals(e.getCodeBaseType().getCode())
                        || (displayName.equalsIgnoreCase(e.getCodeBaseType().getDisplayName())))) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate)
                newList.add(element);
        }

        return newList;
    }
}
