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
package org.ehealth_connector.codegenerator.cda;

import com.sun.xml.bind.v2.*;
import java.util.Comparator;
import org.ehealth_connector.codegenerator.cda.java.JavaCodeGenerator;

/**
 * This is a sample Class
 */
public class MyCamelCaser extends Blah {

    /**
     * This is a sample constant.
     */
    public static int A_CONSTANT;

    /**
     * This is a sample constant.
     */
    public static int B_TEST_CONSTANT;

    /**
     * This is a sample field member.
     */
    public String abc;

    private String anotherMember;

    /**
     * This is a sample field member
     */
    public int myInt;

    private String name;

    /**
     * This is a sample void method having a long Javadoc description string that will be splitted into multiple lines by the Java code formatter at the very end of the Java class file creation by the JavaGenerator. Formatting is not tested, here. Therefore this comment is intended to appear on one single line!.
     */
    public void anotherGreatMethod() {
    }

    /**
     * This is a sample method (function returning a value).
     */
    public boolean myMethod(String myString, ContentProfileConfig myEhcType, int myIntParam) {
        return true;
    }
}
