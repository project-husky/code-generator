/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */

package org.ehealth_connector.codegenerator.valuesets;

import static org.ehealth_connector.common.enums.LanguageCode.ENGLISH;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.valueset.model.ValueSetEntry;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

/**
 * <div class="en">Contains helper methods and constants that are used to
 * dynamically generate the value sets.</div>
 */
public final class ValueSetUtil {

	/**
	 * <div class="en">The JSONPath path to extract a value set from the JSON
	 * definition file</div>
	 */
	public static final String VALUE_SET_BASE_PATH = "$.valueSets[0].valueSet[0]";

	/**
	 * <div class="en">Reads the display name from a concept object parsed from
	 * JSON.</div>
	 *
	 * @param language
	 *            The language to find the display name in.
	 * @param concept
	 *            The concept object parsed from JSON.
	 * @return The display name or null if not found.
	 * @throws IllegalStateException
	 *             When no designation found for the provided language
	 */
	@SuppressWarnings("unchecked")
	public static String getDisplayName(LanguageCode language, Map<String, Object> concept)
			throws IllegalStateException {
		if (language == null) {
			return concept.get("displayName").toString();
		}

		List<Map<String, String>> designations = (List<Map<String, String>>) concept
				.get("designation");

		if (designations != null) {
			for (Map<String, String> designation : designations) {
				String designationLanguage = designation.get("language");
				if (designationLanguage != null
						&& designationLanguage.startsWith(language.getCodeValue())) {
					return designation.get("displayName");
				}
			}

			// nothing found for desired language, return the default for
			// english
			if (language == ENGLISH) {
				return getDisplayName(null, concept);
			}
			throw new IllegalStateException("no designation found for language " + language);
		} else
			return concept.get("displayName").toString();
	}

	/**
	 * Returns all duplicates in the list.
	 *
	 * @param list
	 *            the list
	 * @return the duplicates
	 */
	public static List<ValueSetEntry> getDuplicates(List<ValueSetEntry> list) {

		List<ValueSetEntry> newList = new ArrayList<>();
		List<ValueSetEntry> duplicatesList = new ArrayList<>();

		// Traverse through the first list
		for (ValueSetEntry element : list) {

			int level = element.getLevel();
			String code = element.getCodeBaseType().getCode();
			String displayName = element.getCodeBaseType().getDisplayName();

			// Traverse through the second list
			for (ValueSetEntry e : newList) {
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
	public static File getSourceFileName(String baseJavaFolder, String fullyQualifiedClassName) {
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
			throws IOException, InvalidClassException {
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
	public static List<ValueSetEntry> removeDuplicates(List<ValueSetEntry> list) {

		List<ValueSetEntry> newList = new ArrayList<>();

		// Traverse through the first list
		for (ValueSetEntry element : list) {

			int level = element.getLevel();
			String code = element.getCodeBaseType().getCode();
			String displayName = element.getCodeBaseType().getDisplayName();
			boolean isDuplicate = false;

			// Traverse through the second list
			for (ValueSetEntry e : newList) {
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
