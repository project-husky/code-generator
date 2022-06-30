/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.codegenerator.cda;

import com.sun.xml.bind.v2.*;
import java.util.Comparator;
import org.husky.codegenerator.cda.java.JavaCodeGenerator;

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
    public boolean myMethod(String myString, ContentProfileConfig myHuskyType, int myIntParam) {
        return true;
    }
}
