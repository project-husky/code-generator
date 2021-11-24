/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.java;

import java.util.Comparator;

import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * <div class="en">This class is used as comparator to sort items in an
 * ArrayList. It is used to sort all fields and methods of a Java class in
 * alphabetical order. The result shall be equal to the "Sort members..." menu
 * call of Eclipse IDE.</div>
 *
 * <div class="de">Diese Klasse wird als Komparator zum Sortieren von Elementen
 * in einer ArrayList verwendet. Es wird verwendet, um alle Felder und Methoden
 * einer Java-Klasse in alphabetischer Reihenfolge zu sortieren. Das Ergebnis
 * soll dem Menüaufruf "Sort members..." der Eclipse IDE entsprechen.</div>
 *
 */
public class BodyDeclarationsComparator implements Comparator<BodyDeclaration<?>> {

    /**
     *
     * <div class="en">Compares two members on their type and name.</div>
     *
     * <div class="de">Vergleicht zwei Member nach Typ und Name.</div>
     *
     * {@inheritDoc}
     *
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(BodyDeclaration<?> a, BodyDeclaration<?> b) {
        if (a == null && b == null)
            return 0;
        else if (a == null)
            return -1;
        else if (b == null)
            return 1;
        else {

            // TODO: static declarations need to come first!

            if (a instanceof ConstructorDeclaration && b instanceof ConstructorDeclaration) {
                // TODO when there are multiple constructors, add a comparision
                // on their arg list, here
                return 0;
            } else if (a instanceof final FieldDeclaration fieldA && b instanceof final FieldDeclaration fieldB) {
                return fieldA.getVariable(0).getNameAsString() .compareTo(fieldB.getVariable(0).getNameAsString());
            } else if (a instanceof final MethodDeclaration methodA && b instanceof final MethodDeclaration methodB) {
                return methodA.getNameAsString().compareTo(methodB.getNameAsString());
            } else if (a instanceof ConstructorDeclaration && b instanceof FieldDeclaration) {
                return -1;
            } else if (a instanceof FieldDeclaration && b instanceof ConstructorDeclaration) {
                return 1;
            } else if (a instanceof ConstructorDeclaration && b instanceof MethodDeclaration) {
                return -1;
            } else if (a instanceof MethodDeclaration && b instanceof ConstructorDeclaration) {
                return 1;
            } else if (a instanceof FieldDeclaration && b instanceof MethodDeclaration) {
                return -1;
            } else if (a instanceof MethodDeclaration && b instanceof FieldDeclaration) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}