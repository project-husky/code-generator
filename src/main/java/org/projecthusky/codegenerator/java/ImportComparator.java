/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.codegenerator.java;

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
