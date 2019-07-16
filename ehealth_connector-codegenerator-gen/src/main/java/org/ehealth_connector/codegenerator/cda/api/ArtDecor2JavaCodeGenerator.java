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
package org.ehealth_connector.codegenerator.cda.api;

/**
 *
 * This class acts as the main engine for the Java code generation. It extends
 * the Hl7ItsParserListener. Basically, one could implement the functionality
 * directly in Hl7ItsParserListener, but we create a new class, as we do not
 * want to edit generated code. A detailed implementation specification has to
 * be elaborated before starting the final implemen- tation.
 *
 */
public class ArtDecor2JavaCodeGenerator {

	// Note:
	// ‘…’ is a placeholder for elements that first need to be specified
	// (metaobjects and required method parameters).
	// See section “9 Open issues” on 42.
	//
	// public List<…> doOneTemplate(…);
	// Creates metaobjects for all Java Classes for the given template and
	// returns them in a list that can be hando-
	// ver to other methods.
	//
	// public boolean generateJava(List<…> metaobjects, String path);
	// Creates the Java class files for all given metaobjects in the given path.
	// The path must fulfil the definitions if
	// the project structure defined in the following section.

}
