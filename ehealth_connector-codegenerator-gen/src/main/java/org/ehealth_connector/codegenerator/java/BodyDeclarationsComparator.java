/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
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

import com.github.javaparser.ast.body.BodyDeclaration;
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
		if ((a == null) && (b == null))
			return 0;
		else if ((a == null) && (b != null))
			return -1;
		else if ((a != null) && (b == null))
			return 1;
		else {
			if (a instanceof FieldDeclaration && b instanceof FieldDeclaration) {
				FieldDeclaration field_a = (FieldDeclaration) a;
				FieldDeclaration field_b = (FieldDeclaration) b;
				return field_a.getVariable(0).getNameAsString()
						.compareTo(field_b.getVariable(0).getNameAsString());
			} else if (a instanceof MethodDeclaration && b instanceof MethodDeclaration) {
				MethodDeclaration method_a = (MethodDeclaration) a;
				MethodDeclaration method_b = (MethodDeclaration) b;
				return method_a.getNameAsString().compareTo(method_b.getNameAsString());
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