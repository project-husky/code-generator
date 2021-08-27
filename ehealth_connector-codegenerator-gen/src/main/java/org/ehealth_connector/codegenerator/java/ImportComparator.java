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
package org.ehealth_connector.codegenerator.java;

import java.util.Comparator;

import com.github.javaparser.ast.ImportDeclaration;

/**
 * <div class="en">This class is used as comparator to sort items in an
 * ArrayList. It is used to sort all import declarations of a Java class in
 * alphabetical order. The result shall be equal to the "Sort members..." and
 * "Format" menu calls of Eclipse IDE.</div>
 *
 * <div class="de">Diese Klasse wird als Komparator zum Sortieren von Elementen
 * in einer ArrayList verwendet. Es wird verwendet, um alle import Deklarationen
 * einer Java-Klasse in alphabetischer Reihenfolge zu sortieren. Das Ergebnis
 * soll dem Menüaufruf "Sort members..." und "Format" der Eclipse IDE
 * entsprechen.</div>
 */
public class ImportComparator implements Comparator<ImportDeclaration> {

    /**
     *
     * <div class="en">Compares two members on their name.</div>
     *
     * <div class="de">Vergleicht zwei Member nach Name.</div>
     *
     * {@inheritDoc}
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final ImportDeclaration a, final ImportDeclaration b) {
        if (a == null && b == null)
            return 0;
        else if (a == null)
            return -1;
        else if (b == null)
            return 1;
        else {
            return a.getNameAsString().compareTo(b.getNameAsString());
        }
    }
}
