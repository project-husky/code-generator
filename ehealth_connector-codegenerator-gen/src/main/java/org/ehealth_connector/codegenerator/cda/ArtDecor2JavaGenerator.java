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
package org.ehealth_connector.codegenerator.cda;

import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlType;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.NotImplementedException;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsLexer;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ContainsAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.DataTypeAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.IdAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MaxOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MinOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.NameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.RefAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.ehealth_connector.codegenerator.cda.xslt.Hl7Its2EhcTransformer;
import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.javadoc.Javadoc;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

	private static final String COMMENT_SETTER_FOR_FIXED_CONTENTS_COMPLETED = "setting the fixed value";

	private static HashMap<String, String> dataTypeIndex = null;

	private static void addBodyStatement(ConstructorDeclaration constructor, String statement) {
		BlockStmt body = constructor.getBody();
		body.addStatement(statement);

	}

	public static void addImport(CompilationUnit compilationUnit, String value) {
		boolean isAlreadyThere = false;
		for (ImportDeclaration item : compilationUnit.getImports()) {
			isAlreadyThere = (item.getNameAsString().equals(value));
			if (isAlreadyThere)
				break;
		}
		if (!isAlreadyThere)
			compilationUnit.addImport(value);
	}

	private static void completeSetterForFixedContentsMethod(
			MethodDeclaration setterForFixedContentsMethod, CdaElement cdaElement) {

		Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
		if (bodyOpt.isPresent()) {
			BlockStmt body = bodyOpt.get();

			String name = cdaElement.getXmlName().replace("hl7:", "");
			@SuppressWarnings("rawtypes")
			Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(),
					name);
			boolean isField = (memberType != null);
			boolean isMethod = false;
			if (!isField) {
				memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(),
						name);
				isMethod = (memberType != null);
			}
			if (!(isField || isMethod)) {
				// TODO for prefix of names (Academic is default for
				// doctors), and so on
				// throw new RuntimeException(
				// name + " is neither an accesible field nor a accessible
				// getter");
			} else {
				String cast = "";
				boolean doCast = false;
				if (doCast)
					cast = "(" + memberType.getName() + ")";
				ExpressionStmt statement = null;
				if (isClassCollection(memberType)) {
					statement = body.addAndGetStatement("super.get"
							+ toUpperFirstChar(cdaElement.getXmlName().replace("hl7:", ""))
							+ "().add(" + cast + "member)");
				} else {
					statement = body.addAndGetStatement("super.set"
							+ toUpperFirstChar(cdaElement.getXmlName().replace("hl7:", "")) + "("
							+ cast + "member)");
				}
				if (statement != null)
					statement.setComment(
							new LineComment(COMMENT_SETTER_FOR_FIXED_CONTENTS_COMPLETED));
			}
		}
	}

	private static void createAdder(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		method = myClass.addMethod("add" + JavaCodeGenerator.toPascalCase(cdaElement.getJavaName()),
				publicModifier().getKeyword());

		String comment = "Adds a " + cdaElement.getJavaName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += Util.getPlatformSpecificLineBreak() + desc;

		method.setJavadocComment(comment);

		method.addAndGetParameter(cdaElement.getDataType(), "value");

		String name = cdaElement.getXmlName().replace("hl7:", "");
		String genericType = getGenericFieldType(cdaElement.getParentCdaElement().getDataType(),
				name);
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		boolean isMethod = false;
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
			isMethod = (memberType != null);
		}
		// if (!(isField || isMethod))
		// throw new RuntimeException(
		// name + " is neither an accesible field nor a accessible getter");

		BlockStmt body = method.createBody();
		if (isClassCollection(memberType)) {
			name = toUpperFirstChar(name);
			body.addStatement(createGetterNamePascalCase(name) + "().add(value);");
		} else {
			if (cdaElement.getDataType().endsWith(".ENXP")) {
				String enPartL = cdaElement.getXmlName().replace("hl7:", "");
				String enPartU = toUpperFirstChar(enPartL);

				addImport(compilationUnit, "javax.xml.namespace.QName");
				addImport(compilationUnit, "org.ehealth_connector.common.hl7cdar2.En" + enPartU);

				body.addStatement("En" + enPartU + " obj = new En" + enPartU + "();");
				body.addStatement("obj.xmlContent = value.xmlContent;");
				body.addStatement("getContent().add(new JAXBElement<En" + enPartU
						+ ">(new QName(\"hl7:" + enPartL + "\"), En" + enPartU + ".class, obj));");
			} else {
				System.out.println("\nWARNING: " + cdaElement.getFullXmlName()
						+ " is declared as list, but parent hosts it as single field. It is strongly recommended to correct the ART-DECOR model!");
				body.addStatement("super." + name + "= value;");
			}
		}
	}

	private static void createClearer(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		String name = cdaElement.getXmlName().replace("hl7:", "");
		String genericType = getGenericFieldType(cdaElement.getParentCdaElement().getDataType(),
				name);
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		boolean isMethod = false;
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
			isMethod = (memberType != null);
		}
		// if (!(isField || isMethod))
		// throw new RuntimeException(
		// name + " is neither an accesible field nor a accessible getter");

		if (isClassCollection(memberType)) {
			MethodDeclaration method;

			method = myClass.addMethod(
					"clear" + JavaCodeGenerator.toPascalCase(cdaElement.getJavaName()),
					publicModifier().getKeyword());

			String comment = "Adds a " + cdaElement.getJavaName();
			String desc = cdaElement.getDescription();
			if (desc != null)
				comment += Util.getPlatformSpecificLineBreak() + desc;

			method.setJavadocComment(comment);

			BlockStmt body = method.createBody();
			name = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
			body.addStatement(createGetterNamePascalCase(name) + "().clear();");
		} else {
			// Do nothing
		}

	}

	private static ConstructorDeclaration createDefaultConstructor(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		return myClass.addConstructor(publicModifier().getKeyword());
	}

	private static void createFixedAttributeValues(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {
		MethodDeclaration setterForFixedContentsMethod = null;
		ConstructorDeclaration constructor = null;
		StringBuilder sb = new StringBuilder();
		for (CdaAttribute cdaAttribute : cdaElement.getCdaAttributeList()) {
			if (cdaAttribute.getValue() != null) {
				Optional<ConstructorDeclaration> constructorOpt = myClass
						.getConstructorByParameterTypes(new String[] {});
				boolean constructorExist = constructorOpt.isPresent();
				if (!constructorExist) {
					constructor = createDefaultConstructor(compilationUnit, myClass);
				} else
					constructor = constructorOpt.get();

				String setterForFixedContentsName = createSetterNamePascalCase(
						cdaElement.getJavaName()) + "FixedValue";
				List<MethodDeclaration> setterForFixedContentsMethodList = myClass
						.getMethodsByName(setterForFixedContentsName);

				boolean setterForFixedContentsExist = (setterForFixedContentsMethodList.size() > 0);
				if (!setterForFixedContentsExist) {
					setterForFixedContentsMethod = createSetterForFixedContents(compilationUnit,
							myClass, cdaElement, setterForFixedContentsName);
				} else
					setterForFixedContentsMethod = setterForFixedContentsMethodList.get(0);

				updateSetterForFixedContentsMethod(compilationUnit, setterForFixedContentsMethod,
						cdaElement, cdaAttribute);

				if (sb.length() != 0)
					sb.append(", ");
				sb.append("\"" + cdaAttribute.getValue() + "\"");

			}
		}
		if (setterForFixedContentsMethod != null) {
			int argCountMethod = setterForFixedContentsMethod.getParameters().size();
			// fix for setHl7EntryRelationshipFixedValue: sometimes in the first
			// occurrence, there is no inversionInd in the ART-DECOR model
			if ("setHl7EntryRelationshipFixedValue"
					.contentEquals(setterForFixedContentsMethod.getNameAsString())) {
				if (argCountMethod == 1) {
					CdaAttribute attr = new CdaAttribute();
					attr.setCdaElement(cdaElement);
					attr.setName("inversionInd");
					updateSetterForFixedContentsMethod(compilationUnit,
							setterForFixedContentsMethod, cdaElement, attr);

				}
				argCountMethod = 2;
			}
			// end of fix

			if (!isCompleteSetterForFixedContentsMethod(setterForFixedContentsMethod))
				completeSetterForFixedContentsMethod(setterForFixedContentsMethod, cdaElement);

			int argCountGiven = org.apache.commons.lang3.StringUtils.countMatches(sb.toString(),
					",") + 1;

			while (argCountGiven < argCountMethod) {
				sb.append(", ");
				sb.append("null");
				argCountGiven = org.apache.commons.lang3.StringUtils.countMatches(sb.toString(),
						",") + 1;
			}

			addBodyStatement(constructor,
					setterForFixedContentsMethod.getNameAsString() + "(" + sb.toString() + ");");
		}
	}

	private static void createGetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		method = myClass.addMethod(createGetterNamePascalCase(cdaElement.getJavaName()),
				publicModifier().getKeyword());
		method.setType(cdaElement.getDataType());

		String comment = "Gets the " + cdaElement.getJavaName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += Util.getPlatformSpecificLineBreak() + desc;

		method.setJavadocComment(comment);

		BlockStmt body = method.createBody();
		String name = cdaElement.getXmlName().replace("hl7:", "");
		String genericType = getGenericFieldType(cdaElement.getParentCdaElement().getDataType(),
				name);
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		boolean isMethod = false;
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
			isMethod = (memberType != null);
		}
		if (!(isField || isMethod))
			throw new RuntimeException(
					name + " is neither an accesible field nor a accessible getter");
		String temp = name;
		String cast = "";
		boolean doCast = false;
		if (isClassCollection(memberType)) {
			if (isMethod)
				temp = createGetterNameUpperFirstChar(name) + "()";
			temp = "super." + temp;
			if (genericType.endsWith(".ANY>"))
				doCast = true;

			if (doCast)
				cast = "(" + cdaElement.getDataType() + ")";

			body.addStatement(cdaElement.getDataType() + " retVal = null;");
			body.addStatement("if (" + temp + " != null) if (" + temp + ".size() > 0)  retVal = "
					+ cast + temp + ".get(0);");
			body.addStatement("return retVal;");
		} else {
			if (genericType.endsWith(".CD") && cdaElement.getDataType().endsWith(".CE"))
				doCast = true;

			if (doCast)
				cast = "(" + cdaElement.getDataType() + ")";

			temp = "super." + temp;
			body.addStatement("return " + cast + temp + ";");
		}

	}

	private static String createGetterNamePascalCase(String name) {
		return "get" + JavaCodeGenerator.toPascalCase(name);
	}

	private static String createGetterNameUpperFirstChar(String name) {
		return "get" + toUpperFirstChar(name);
	}

	private static void createLoaderMethods(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		addImport(compilationUnit, "java.io.File");
		addImport(compilationUnit, "javax.xml.bind.JAXBContext");
		addImport(compilationUnit, "javax.xml.bind.JAXBException");
		addImport(compilationUnit, "org.ehealth_connector.common.CdaNamespacePrefixMapper");

		addImport(compilationUnit, "java.io.IOException");
		addImport(compilationUnit, "javax.xml.bind.Unmarshaller");
		addImport(compilationUnit, "javax.xml.transform.stream.StreamSource");
		addImport(compilationUnit, "javax.xml.bind.JAXBElement");

		MethodDeclaration method;
		String comment = "Loads the CDA document from file.";

		// The one with the file name as parameter
		method = myClass.addMethod("loadFromFile", publicModifier().getKeyword(),
				staticModifier().getKeyword());
		method.setType(myClass.getNameAsString());
		method.setJavadocComment(comment
				+ "\n@param inputFileName the full path and filename of the sourcefile.\n@return the CDA document\\n@throws JAXBException\\n@throws IOException Signals that an I/O exception has occurred.");
		method.addAndGetParameter("String", "inputFileName");
		method.addThrownException(JAXBException.class);
		method.addThrownException(IOException.class);

		BlockStmt body = method.createBody();
		body.addStatement("return loadFromFile(new File(inputFileName));");

		// The one with the file as parameter
		method = myClass.addMethod("loadFromFile", publicModifier().getKeyword(),
				staticModifier().getKeyword());
		method.setType(myClass.getNameAsString());
		method.setJavadocComment(comment
				+ "\n@param inputFile the source file.\nn@return the CDA document\\n@throws JAXBException\\n@throws IOException Signals that an I/O exception has occurred.");
		method.addAndGetParameter("File", "inputFile");
		method.addThrownException(JAXBException.class);
		method.addThrownException(IOException.class);

		body = method.createBody();
		body.addStatement(myClass.getNameAsString() + " retVal;");
		body.addStatement("JAXBContext context = JAXBContext.newInstance("
				+ myClass.getNameAsString() + ".class);");
		body.addStatement("Unmarshaller mar = context.createUnmarshaller();");
		body.addStatement("StreamSource source = new StreamSource(inputFile);");
		body.addStatement("JAXBElement<" + myClass.getNameAsString()
				+ "> root = mar.unmarshal(source, " + myClass.getNameAsString() + ".class);");
		body.addStatement("retVal = root.getValue();");
		body.addStatement("return retVal;");

	}

	private static void createSaverMethods(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		addImport(compilationUnit, "java.io.File");
		addImport(compilationUnit, "javax.xml.bind.JAXBContext");
		addImport(compilationUnit, "javax.xml.bind.JAXBException");
		addImport(compilationUnit, "javax.xml.bind.Marshaller");
		addImport(compilationUnit, "org.ehealth_connector.common.CdaNamespacePrefixMapper");

		MethodDeclaration method;
		String comment = "Saves the current CDA document to file.";

		// The one with the file name as parameter
		method = myClass.addMethod("saveToFile", publicModifier().getKeyword());
		method.setJavadocComment(comment
				+ "\n@param outputFileName the full path and filename of the destination file.\n@throws JAXBException");
		method.addAndGetParameter("String", "outputFileName");
		method.addThrownException(JAXBException.class);

		BlockStmt body = method.createBody();
		body.addStatement("saveToFile(new File(outputFileName));");

		// The one with the file as parameter
		method = myClass.addMethod("saveToFile", publicModifier().getKeyword());
		method.setJavadocComment(
				comment + "\n@param outputFile the destination file.\n@throws JAXBException");
		method.addAndGetParameter("File", "outputFile");
		method.addThrownException(JAXBException.class);

		body = method.createBody();
		body.addStatement("JAXBContext context = JAXBContext.newInstance(this.getClass());");
		body.addStatement("Marshaller mar = context.createMarshaller();");
		body.addStatement(
				"mar.setProperty(\"com.sun.xml.bind.namespacePrefixMapper\", new CdaNamespacePrefixMapper());");
		body.addStatement("mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);");
		body.addStatement("mar.marshal(this, outputFile);");

	}

	private static void createSetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		method = myClass.addMethod(createSetterNamePascalCase(cdaElement.getJavaName()),
				publicModifier().getKeyword());

		String comment = "Sets the " + cdaElement.getJavaName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += Util.getPlatformSpecificLineBreak() + desc;

		method.setJavadocComment(comment);

		String name = cdaElement.getXmlName().replace("hl7:", "");
		String dataType = cdaElement.getDataType();
		if ("typeId".equals(name)) {
			dataType = "org.ehealth_connector.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
		}
		method.addAndGetParameter(dataType, "value");

		BlockStmt body = method.createBody();
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		boolean isMethod = false;
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
			isMethod = (memberType != null);
		}
		if (!(isField || isMethod))
			throw new RuntimeException(
					name + " is neither an accesible field nor a accessible getter");
		String temp = name;
		if (isClassCollection(memberType)) {
			if (isMethod)
				temp = createGetterNameUpperFirstChar(name) + "()";
			temp = "super." + temp;
			body.addStatement(temp + ".clear();");
			body.addStatement(temp + ".add(value);");
		} else {
			String cast = "";
			boolean doCast = false;

			if (doCast)
				cast = "(" + memberType.getName() + ")";
			temp = "super." + temp;

			if (memberType.getName().endsWith(".IVLTS")
					&& (!cdaElement.getDataType().endsWith(".IVLTS"))) {
				if (memberType.getName().endsWith(".IVLTS")
						&& (cdaElement.getDataType().endsWith(".TS"))) {
					// Create Interval from single TimeStamp
					addImport(compilationUnit, "org.ehealth_connector.common.hl7cdar2.IVLTS");
					addImport(compilationUnit,
							"org.ehealth_connector.common.hl7cdar2.ObjectFactory");

					body.addStatement("ObjectFactory factory = new ObjectFactory();");
					body.addStatement("IVLTS ivlts = factory.createIVLTS();");
					body.addStatement("ivlts.setValue(value.getValue());");
					body.addStatement(temp + " = ivlts;");
				} else
					throw new RuntimeException(memberType.getName() + " cannot be cast to "
							+ cdaElement.getDataType());
			} else
				body.addStatement(temp + " = " + cast + "value;");
		}

	}

	private static MethodDeclaration createSetterForFixedContents(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement,
			String setterForFixedContentsName) {
		MethodDeclaration method = myClass.addMethod(setterForFixedContentsName,
				publicModifier().getKeyword());
		method.setJavadocComment("Creates fixed contents for " + cdaElement.getJavaName() + "\n\n");

		compilationUnit.addImport("org.ehealth_connector.common.hl7cdar2.ObjectFactory");

		BlockStmt body = method.createBody();
		body.addStatement("ObjectFactory factory = new ObjectFactory();");

		String dataType = cdaElement.getDataType();

		String name = cdaElement.getXmlName().replace("hl7:", "");
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		boolean isMethod = false;
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
			isMethod = (memberType != null);
		}
		if (!(isField || isMethod)) {
			// TODO for prefix of names (Academic is default for
			// doctors), and so on
			// throw new RuntimeException(
			// name + " is neither an accesible field nor a accessible
			// getter");
		} else {
			if (memberType.getName().endsWith("POCDMT000040InfrastructureRootTypeId")) {
				dataType = memberType.getName();
			}
			body.addStatement(dataType + " member = factory.create"
					+ dataType.replace("org.ehealth_connector.common.hl7cdar2.", "") + "();");
		}
		return method;
	}

	private static String createSetterNamePascalCase(String name) {
		return "set" + JavaCodeGenerator.toPascalCase(name);
	}

	private static String createSetterNameUpperFirstChar(String name) {
		return "set" + toUpperFirstChar(name);
	}

	/**
	 * Creates an XmlElementName annotation. Example:
	 *
	 * (at)XmlElement(name = "hl7:author")
	 *
	 *
	 * @param annotationName
	 *            the annotation name
	 * @param nameValue
	 *            the name value
	 * @return the normal annotation expr
	 */
	public static NormalAnnotationExpr createXmlElementNameAnnotation(String nameValue) {
		NormalAnnotationExpr retVal = new NormalAnnotationExpr();
		retVal.setName(new Name("XmlElement"));
		retVal.addPair("name", "\"" + nameValue + "\"");
		return retVal;
	}

	/**
	 * Creates an XmlRootElement annotation. Examples:
	 *
	 * (at)XmlRootElement(name = "ClinicalDocument", namespace =
	 * "urn:hl7-org:v3")
	 *
	 *
	 * @param annotationName
	 *            the annotation name
	 * @param nameValue
	 *            the name value
	 * @return the normal annotation expr
	 */
	public static NormalAnnotationExpr createXmlRootElementAnnotation(String nameValue) {
		NormalAnnotationExpr retVal = new NormalAnnotationExpr();
		retVal.setName(new Name("XmlRootElement"));
		retVal.addPair("name", "\"" + nameValue + "\"");
		retVal.addPair("namespace", "\"urn:hl7-org:v3\"");
		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static Class getDeclaredFieldDatatype(String className, String memberName) {
		Class retVal = null;

		try {
			Class c = Class.forName(className);
			if (c != null) {
				Field f;
				try {
					f = c.getDeclaredField(memberName);
					retVal = f.getType();
				} catch (NoSuchFieldException | SecurityException e) {
					// Do nothing
				}
			}

		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static Class getFieldDatatype(String className, String memberName) {
		Class retVal = null;

		try {
			Class c = Class.forName(className);
			if (c != null) {
				Field f;
				try {
					f = c.getField(memberName);
					retVal = f.getType();
				} catch (NoSuchFieldException | SecurityException e) {
					// Do nothing
				}
			}

		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static String getGenericFieldType(String className, String memberName) {
		String retVal = "";

		try {
			Class c = Class.forName(className);
			if (c != null) {

				Field f = null;
				try {
					f = c.getDeclaredField(memberName);
				} catch (NoSuchFieldException | SecurityException e) {
					// Do nothing
				}

				Method m = null;
				try {
					m = c.getDeclaredMethod(
							createGetterNamePascalCase(JavaCodeGenerator.toPascalCase(memberName)));
				} catch (NoSuchMethodException | SecurityException e) {
					// Do nothing
				}

				if ((f != null) && (m != null)) {
					f.setAccessible(true);
					try {
						retVal = f.getGenericType().toString();
					} catch (IllegalArgumentException e) {
						// Do nothing
					}

				}
			}

		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static Class getMethodDatatype(String className, String memberName) {
		Class retVal = null;

		try {
			Class c = Class.forName(className);
			if (c != null) {
				Method m;
				try {
					m = c.getMethod(
							createGetterNamePascalCase(JavaCodeGenerator.toPascalCase(memberName)));
					retVal = m.getReturnType();
				} catch (NoSuchMethodException | SecurityException e) {
					// Do nothing
				}
			}

		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	public static boolean isClassCollection(Class c) {
		if (c == null)
			return false;
		else
			return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
	}

	private static boolean isCompleteSetterForFixedContentsMethod(
			MethodDeclaration setterForFixedContentsMethod) {
		Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
		boolean retVal = false;
		if (bodyOpt.isPresent()) {
			BlockStmt body = bodyOpt.get();
			for (Comment comment : body.getAllContainedComments()) {
				retVal = (COMMENT_SETTER_FOR_FIXED_CONTENTS_COMPLETED.equals(comment.getContent()));
			}
		}
		return retVal;
	}

	private static void printCdaAttributes(String intend, ArrayList<CdaAttribute> attrList) {
		for (CdaAttribute attr : attrList) {
			System.out.println(intend + "  " + attr.getName() + " = " + attr.getValue()
					+ " (dataType: " + attr.getDataType() + ")");
		}
	}

	private static void printCdaElementRecursive(String intend, CdaElement cdaElement) {
		System.out.println(intend + "- CdaElement Name = " + cdaElement.getJavaName()
				+ " (dataType: " + cdaElement.getDataType() + ")");
		printCdaAttributes(intend, cdaElement.getCdaAttributeList());
		for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
			printCdaElementRecursive(intend + "  ", item);
		}
	}

	private static String toUpperFirstChar(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1, value.length());
	}

	private static void updateSetterForFixedContentsMethod(CompilationUnit compilationUnit,
			MethodDeclaration setterForFixedContentsMethod, CdaElement cdaElement,
			CdaAttribute cdaAttribute) {

		if ("setHl7TypeIdFixedValue".equals(setterForFixedContentsMethod.getNameAsString()))
			System.out.println("Stop here");

		Optional<Parameter> params = setterForFixedContentsMethod
				.getParameterByName(cdaAttribute.getName());
		if (!params.isPresent()) {
			setterForFixedContentsMethod.addAndGetParameter("String", cdaAttribute.getName());
			Optional<Javadoc> javaDocOpt = setterForFixedContentsMethod.getJavadoc();
			Javadoc javadoc = javaDocOpt.get();
			javadoc.addBlockTag("param",
					cdaAttribute.getName() + " the desired fixed value for this argument.");
			setterForFixedContentsMethod.setJavadocComment(javadoc);

			Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
			if (bodyOpt.isPresent()) {
				BlockStmt body = bodyOpt.get();

				String name = cdaElement.getXmlName().replace("hl7:", "");

				@SuppressWarnings("rawtypes")
				Class memberType = getDeclaredFieldDatatype(
						cdaElement.getParentCdaElement().getDataType(), name);

				if ((memberType == null) && (!"translation".equals(name))) {
					System.out.println("Stop here - " + cdaElement.getFullXmlName()
							+ " cannot set fixed content");
					// TODO for prefix of names (Academic is default for
					// doctors), and so on
					// throw new RuntimeException(
					// name + " is neither an accesible field nor a accessible
					// getter");
				} else {
					if ("nullFlavor".equals(cdaAttribute.getName())) {
						addImport(compilationUnit, "java.util.ArrayList");
						body.addStatement("member.nullFlavor = new ArrayList<String>();");
						body.addStatement("member.nullFlavor.add(nullFlavor);");
					} else if ((isClassCollection(memberType) && (!"translation".equals(name))
							&& (!"templateId".equals(name)) && (!"relatedDocument".equals(name))
							&& (!"targetSiteCode".equals(name))) || ("serviceEvent".equals(name))
							|| ("associatedEntity".equals(name))) {
						if ("setHl7EntryFixedValue"
								.equals(setterForFixedContentsMethod.getNameAsString())) {
							addImport(compilationUnit,
									"org.ehealth_connector.common.hl7cdar2.XActRelationshipEntry");
							body.addStatement(
									"member.setTypeCode(XActRelationshipEntry.valueOf(typeCode));");
						} else if ("setHl7ComponentFixedValue"
								.equals(setterForFixedContentsMethod.getNameAsString())) {
							addImport(compilationUnit,
									"org.ehealth_connector.common.hl7cdar2.ActRelationshipHasComponent");
							body.addStatement(
									"member.setTypeCode(ActRelationshipHasComponent.fromValue(typeCode));");
						} else if ("setHl7ReferenceFixedValue"
								.equals(setterForFixedContentsMethod.getNameAsString())) {
							addImport(compilationUnit,
									"org.ehealth_connector.common.hl7cdar2.XActRelationshipExternalReference");
							body.addStatement(
									"member.setTypeCode(XActRelationshipExternalReference.fromValue(typeCode));");
						} else if ("setHl7EntryRelationshipFixedValue"
								.equals(setterForFixedContentsMethod.getNameAsString())) {
							addImport(compilationUnit,
									"org.ehealth_connector.common.hl7cdar2.XActRelationshipEntryRelationship");
							if ("typeCode".equals(cdaAttribute.getName()))
								body.addStatement(
										"member.setTypeCode(XActRelationshipEntryRelationship.fromValue(typeCode));");
							else
								body.addStatement(
										"member.setInversionInd(Boolean.parseBoolean(inversionInd));");
						} else
							body.addStatement(
									"member.get" + toUpperFirstChar(cdaAttribute.getName())
											+ "().add(" + cdaAttribute.getName() + ");");
					} else {
						if ("relatedDocument".equals(name)) {
							addImport(compilationUnit,
									"org.ehealth_connector.common.hl7cdar2.XActRelationshipDocument");
							body.addStatement(
									"member.setTypeCode(XActRelationshipDocument.fromValue(typeCode));");
						} else if ("representation".contentEquals(cdaAttribute.getName())) {
							addImport(compilationUnit,
									"org.ehealth_connector.common.hl7cdar2.BinaryDataEncoding");
							body.addStatement(
									"member.setRepresentation(BinaryDataEncoding.fromValue(representation));");
						} else
							body.addStatement(
									"member.set" + toUpperFirstChar(cdaAttribute.getName()) + "("
											+ cdaAttribute.getName() + ");");
					}
				}
			}
		}
	}

	private CdaElement callingCdaElement;

	private CdaAttribute currentCdaAttribute = null;

	private CdaElement currentCdaElement = null;

	private CdaTemplate currentCdaTemplate = null;

	private String dstFilePath = null;

	private String fileHeader;

	private boolean isChildElement;

	private boolean isContains;

	private boolean isParentElement;

	private boolean isSiblingElement;

	private CdaTemplate mainCdaTemplate = null;

	private String packageName;

	private CdaElement parentForContains;

	private CdaElement parentForIncludes;

	private boolean printAssembledDebugInformation = true;

	private boolean printDataTypeDebugInformation = false;

	private boolean printParsingDebugInformation = false;

	private int processingAttribute = 0;

	private int processingElement = 0;

	private int processingElementPrev = 0;

	private int processingTemplate = 0;

	private String srcFilePath = null;

	private HashMap<String, String> templateIndex = null;

	private ArrayList<CdaTemplate> templateList = null;

	public ArtDecor2JavaGenerator(CdaElement callingCdaElement,
			HashMap<String, String> templateIndex, ArrayList<CdaTemplate> templateList,
			String srcFilePath, String dstFilePath, String packageName, String fileHeader,
			String containsDataType) {
		this.callingCdaElement = callingCdaElement;

		if (templateIndex == null)
			this.templateIndex = new HashMap<String, String>();
		else
			this.templateIndex = templateIndex;

		if (templateList == null)
			this.templateList = new ArrayList<CdaTemplate>();
		else
			this.templateList = templateList;

		this.srcFilePath = srcFilePath;
		this.dstFilePath = dstFilePath;
		this.packageName = packageName;
		this.fileHeader = fileHeader;
		this.isContains = isContains;

		if (!this.dstFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.dstFilePath += FileUtil.getPlatformSpecificPathSeparator();

		if (!this.srcFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.srcFilePath += FileUtil.getPlatformSpecificPathSeparator();

	}

	private String adjustDataType(String dataType) {
		String retVal = dataType;
		if (retVal != null) {

			if (retVal.endsWith("running"))
				retVal = null;
			else {
				if (retVal.startsWith("IVL_TS."))
					retVal = "IVLTS";
				if (retVal.startsWith("TS."))
					retVal = "TS";
				if (retVal.startsWith("SXPR_TS"))
					retVal = "SXPRTS";
				if (retVal.startsWith("SD.TEXT"))
					retVal = "StrucDocText";
				if (retVal.startsWith("INT.NONNEG"))
					retVal = "INT";

				if (!templateIndex.containsValue(dataType))
					if (!retVal.contains("."))
						retVal = "org.ehealth_connector.common.hl7cdar2." + retVal;
			}
		}
		return retVal;
	}

	private void createField(CompilationUnit compilationUnit, ClassOrInterfaceDeclaration myClass,
			CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getXmlName());

		String dataType = adjustDataType(cdaElement.getDataType());
		cdaElement.setDataType(dataType);

		String xmlName = cdaElement.getXmlName();
		String javaName = JavaCodeGenerator.toCamelCase(xmlName);
		cdaElement.setJavaName(javaName);

		// This is for debugging purposes, only
		// System.out.println("data type: " + dataType + " /
		// name: " + name);

		boolean elementExist = !(myClass
				.getMethodsByName("add" + JavaCodeGenerator.toPascalCase(javaName)).isEmpty()
				&& myClass.getMethodsByName(createGetterNamePascalCase(javaName)).isEmpty());

		if (!elementExist) {
			if (cdaElement.getMaxOccurs() > 1) {
				createAdder(compilationUnit, myClass, cdaElement);
				createClearer(compilationUnit, myClass, cdaElement);
			} else {
				createGetter(compilationUnit, myClass, cdaElement);
				createSetter(compilationUnit, myClass, cdaElement);
			}
		}

		if (cdaElement.getCdaAttributeList().size() > 0) {
			createFixedAttributeValues(compilationUnit, myClass, cdaElement);
		}
	}

	private void createJavaClassFile(CdaTemplate cdaTemplate, String packageName,
			String dstFilePath) throws IOException {

		// Save the current CDA Element as Java Class File
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.setPackageDeclaration(packageName);

		String className = JavaCodeGenerator.toPascalCase(cdaTemplate.getName());
		CdaElement cdaElement = cdaTemplate.getRootCdaElement();

		if (cdaElement == null)
			throw new RuntimeException(
					"No root CDA element found in template " + cdaTemplate.getName());

		String dataType = adjustDataType(cdaElement.getDataType());
		cdaElement.setDataType(dataType);

		ClassOrInterfaceDeclaration myClass = compilationUnit.addClass(className).setPublic(true);

		if ("org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument"
				.equals(cdaElement.getDataType())) {
			addImport(compilationUnit, "javax.xml.bind.annotation.XmlRootElement");
			myClass.addAnnotation(createXmlRootElementAnnotation("ClinicalDocument"));
		}

		String descTemplate = cdaTemplate.getDescription();
		if (descTemplate == null)
			descTemplate = "";
		else
			descTemplate = "Template description: " + descTemplate;

		descTemplate = "Original ART-DECOR template id: " + cdaTemplate.getId()
				+ Util.getPlatformSpecificLineBreak() + descTemplate
				+ Util.getPlatformSpecificLineBreak() + Util.getPlatformSpecificLineBreak();

		String descElement = cdaElement.getDescription();
		if (descElement == null)
			descElement = "";
		else
			descElement = "Element description: " + descElement;

		myClass.setJavadocComment(descTemplate + descElement);
		String inheritenceOf = cdaTemplate.getDataType();
		if (inheritenceOf == null)
			throw new RuntimeException("Inheritance undefined for: " + cdaElement.getJavaName());
		myClass.setExtendedTypes(
				new NodeList<ClassOrInterfaceType>(new ClassOrInterfaceType(null, inheritenceOf)));

		for (CdaElement cdaElement1 : cdaElement.getChildrenCdaElementList()) {
			createField(compilationUnit, myClass, cdaElement1);
		}

		createLoaderMethods(compilationUnit, myClass);
		createSaverMethods(compilationUnit, myClass);

		File outFile = new File(dstFilePath + className + ".java");
		JavaCodeGenerator.completeAndSave(compilationUnit, outFile);
	}

	public String doOneTemplate(String templateId)
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		String retVal = "FAILURE";

		// TODO: This is a quick fix for CDA-CH-EMED
		if (templateId.startsWith("1.3.6.1.4.1.19376."))
			return "NYI_FAILURE";

		if (templateIndex.containsKey(templateId)) {
			if (printParsingDebugInformation)
				System.out.println(
						templateId + " is already known as " + templateIndex.get(templateId));
			retVal = templateIndex.get(templateId);
		}

		boolean initialRun = (callingCdaElement == null);

		if ("FAILURE".equals(retVal)) {
			retVal = "templateDataTypeFAILURE";
			if (initialRun)
				System.out.println("Processing document template: " + templateId);

			templateIndex.put(templateId, "running");

			// TODO naming
			String kitSrcFilePath = srcFilePath;
			if (!kitSrcFilePath.endsWith("kit" + FileUtil.getPlatformSpecificPathSeparator()))
				kitSrcFilePath += "kit" + FileUtil.getPlatformSpecificPathSeparator();

			String orgFn = srcFilePath + templateId + ".xml";
			String trnFn = srcFilePath + templateId + "_transformed.xml";
			File orgFile = new File(orgFn);
			File trnFile = new File(trnFn);

			if (!orgFile.exists()) {
				orgFn = kitSrcFilePath + templateId + ".xml";
				trnFn = kitSrcFilePath + templateId + "_transformed.xml";
				orgFile = new File(orgFn);
				trnFile = new File(trnFn);
			}

			if (!trnFile.exists())
				Hl7Its2EhcTransformer.transform(orgFn, trnFn);

			String content;

			content = new String(Files.readAllBytes(Paths.get(trnFn)));

			// instantiate the lexer, the parser, and the walker
			Hl7ItsLexer lexer = new Hl7ItsLexer(CharStreams.fromString(content));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			Hl7ItsParser parser = new Hl7ItsParser(tokens);
			ParseTreeWalker walker = new ParseTreeWalker();

			// Parse the template
			if (printParsingDebugInformation)
				System.out.println("Parsing started");

			walker.walk(this, parser.template());
			retVal = JavaCodeGenerator.toPascalCase(currentCdaTemplate.getName());
			templateIndex.remove(templateId, "running");
			templateIndex.put(templateId, retVal);

			if (printParsingDebugInformation) {
				System.out.println("Parsing completed");
				System.out.println("-----------------------------------------------------------");
				System.out.println("");
			}

			// filling missing data types
			for (CdaElement item : mainCdaTemplate.getCdaElementList()) {
				fillDatatypesRecursive(item);
			}

			// Show the content (this is for debugging purposes, only)
			if (printAssembledDebugInformation && initialRun) {
				System.out.println("Assembled content for template " + mainCdaTemplate.getName()
						+ " (id: " + mainCdaTemplate.getId() + ")");
				printCdaAttributes(" ", mainCdaTemplate.getCdaAttributeList());

				for (CdaElement item : mainCdaTemplate.getCdaElementList()) {
					printCdaElementRecursive("", item);
				}
			}

			// Create the Java Classes
			if (initialRun) {
				System.out.print("Writing Java Class files");
				for (CdaTemplate cdaTemplate : templateList) {
					System.out.print(".");
					createJavaClassFile(cdaTemplate, packageName, dstFilePath);
				}
				System.out.println(" done.");
			}
		}

		if (initialRun)
			System.out.println("Processing: " + templateId + " done.");

		return retVal;

	}

	@Override
	public void enterAttribute(Hl7ItsParser.AttributeContext ctx) {
		processingAttribute++;
		currentCdaAttribute = new CdaAttribute();
		currentCdaAttribute.setCdaElement(currentCdaElement);

		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			currentCdaAttribute.setName(nameAttrCtx.AttrValue().getText().replace("\"", ""));

		ValueAttrContext valueAttrCtx = ctx.valueAttr();
		if (valueAttrCtx != null)
			currentCdaAttribute.setValue(valueAttrCtx.AttrValue().getText().replace("\"", ""));

		DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
		if (dataTypeAttrCtx != null)
			currentCdaAttribute
					.setDataType(dataTypeAttrCtx.AttrValue().getText().replace("\"", ""));

		if (currentCdaElement == null)
			currentCdaTemplate.addAttribute(currentCdaAttribute);
		else
			currentCdaElement.addAttribute(currentCdaAttribute);

		if (printParsingDebugInformation)
			System.out.println("Attribute: " + currentCdaAttribute.getName() + "="
					+ currentCdaAttribute.getValue() + " (dataType: "
					+ currentCdaAttribute.getDataType() + ")");
	}

	@Override
	public void enterDesc(Hl7ItsParser.DescContext ctx) {
		int a = ctx.start.getStartIndex();
		int b = ctx.stop.getStopIndex();
		Interval interval = new Interval(a, b);
		String desc = ctx.start.getInputStream().getText(interval);
		desc = desc.replace("\r\n", "\r");
		desc = desc.replace("\r", "");
		desc = desc.replace("<br />", "<br/>");
		desc = desc.replace("<br/>", "");
		desc = desc.replace("\t", "");
		desc = desc.replace("<desc>", "");
		desc = desc.replace("</desc>", "");
		desc = desc.replace("<strong>", "");
		desc = desc.replace("</strong>", "");

		if ((processingTemplate > 0) && (processingElement == 0))
			currentCdaTemplate.setDescription(desc);
		if ((processingElement > 0) && (processingAttribute == 0))
			currentCdaElement.setDescription(desc);
		if (processingAttribute > 0)
			currentCdaAttribute.setDescription(desc);
	}

	@Override
	public void enterElement(Hl7ItsParser.ElementContext ctx) {

		String name = null;
		String dataType = null;
		String maxOccursStr = null;
		String minOccursStr = null;

		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			name = nameAttrCtx.AttrValue().getText().replace("\"", "");

		DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
		if (dataTypeAttrCtx != null)
			dataType = dataTypeAttrCtx.AttrValue().getText().replace("\"", "");

		MaxOccursAttrContext maxOccursAttrCtx = ctx.maxOccursAttr();
		if (maxOccursAttrCtx != null)
			maxOccursStr = maxOccursAttrCtx.AttrValue().getText().replace("\"", "");

		MinOccursAttrContext minOccursAttrCtx = ctx.minOccursAttr();
		if (minOccursAttrCtx != null)
			minOccursStr = minOccursAttrCtx.AttrValue().getText().replace("\"", "");

		dataType = adjustDataType(dataType);

		CdaElement cdaElement = new CdaElement(null);
		cdaElement.setXmlName(name);
		cdaElement.setDataType(dataType);
		if (maxOccursStr != null) {
			int temp = 0;
			if ("*".equals(maxOccursStr))
				temp = Integer.MAX_VALUE;
			else
				temp = Integer.parseInt(maxOccursStr);
			cdaElement.setMaxOccurs(temp);
		}
		if (minOccursStr != null) {
			int temp = 0;
			if ("*".equals(minOccursStr))
				temp = Integer.MAX_VALUE;
			else
				temp = Integer.parseInt(minOccursStr);
			cdaElement.setMinOccurs(temp);
		}

		parentForContains = cdaElement;

		isChildElement = (processingElement > processingElementPrev);
		isSiblingElement = (processingElement == processingElementPrev);
		isParentElement = (processingElement < processingElementPrev);
		int levels = 0;
		if (isParentElement)
			levels = processingElementPrev - processingElement;
		processingElementPrev = processingElement;
		processingElement++;

		CdaElement parentCdaElement = null;
		if ((callingCdaElement == null) && (currentCdaElement == null)) {
			// This is the root element (the root element of the
			// calling template)
			parentCdaElement = null;
			currentCdaTemplate.addCdaElement(cdaElement);
			parentForIncludes = cdaElement;
			try {
				if (dataType == null)
					dataType = getDataType(cdaElement, templateIndex);
				currentCdaTemplate.setDataType(dataType);
				cdaElement.setDataType(dataType);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| IllegalArgumentException | InvocationTargetException | NoSuchFieldException
					| SecurityException | IOException e) {
				throw new RuntimeException("Template data type cannot be set for "
						+ currentCdaTemplate.getName() + ": " + e.getMessage());
			}
		} else if ((callingCdaElement != null) && (currentCdaElement == null)) {
			// This is the first level below a referenced template
			currentCdaTemplate.addCdaElement(cdaElement);
			parentCdaElement = callingCdaElement;
			parentForIncludes = parentCdaElement;
			if (dataType == null) {
				String parentDataType = null;
				if (parentCdaElement != null) {
					parentDataType = parentCdaElement.getDataType();
					if (parentDataType != null) {
						try {
							dataType = getDataType(parentDataType, name);
							cdaElement.setDataType(dataType);
						} catch (InstantiationException | IllegalAccessException
								| ClassNotFoundException | NoSuchFieldException
								| SecurityException e) {
							throw new RuntimeException("Template data type cannot be set for "
									+ currentCdaTemplate.getName() + ": " + e.getMessage());
						}
					}
				}
			}
			currentCdaTemplate.setDataType(dataType);
		} else {
			if (isChildElement) {
				parentCdaElement = currentCdaElement;
			} else if (isSiblingElement) {
				parentCdaElement = currentCdaElement.getParentCdaElement();
			} else if (isParentElement) {
				parentCdaElement = currentCdaElement.getParentCdaElement();
				for (int i = 0; i < levels; i++) {
					parentCdaElement = parentCdaElement.getParentCdaElement();
				}
			} else
				throw new RuntimeException(
						"element '" + name + "' is neither child, sibling, parent");

			if (parentCdaElement == null)
				throw new RuntimeException("Parent CDA element is null for " + name);

			if (dataType == null) {
				String parentDataType = null;
				if (parentCdaElement != null) {
					parentDataType = parentCdaElement.getDataType();
					if (parentDataType != null) {
						try {
							dataType = getDataType(parentDataType, name);
							cdaElement.setDataType(dataType);
						} catch (InstantiationException | IllegalAccessException
								| ClassNotFoundException | NoSuchFieldException
								| SecurityException e) {
							throw new RuntimeException("Template data type cannot be set for "
									+ currentCdaTemplate.getName() + ": " + e.getMessage());
						}
					}
				}
			}
			parentForIncludes = parentCdaElement;
		}

		if (parentCdaElement != null) {
			parentCdaElement.addChild(cdaElement);
			cdaElement.setParentCdaElement(parentCdaElement);

		}
		currentCdaElement = cdaElement;

		ContainsAttrContext containsAttrCtx = ctx.containsAttr();
		String contains = "";
		if (containsAttrCtx != null) {
			contains = containsAttrCtx.AttrValue().getText().replace("\"", "");
			if (printParsingDebugInformation)
				System.out.println(cdaElement.getJavaName() + " contains " + contains);
			try {
				if (parentForContains == null)
					throw new RuntimeException("parent is null for contains " + contains);
				ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
						parentForContains, templateIndex, templateList, srcFilePath, dstFilePath,
						packageName, fileHeader, dataType);
				artDecor2JavaGenerator.doOneTemplate(contains);
			} catch (SaxonApiException | IOException | JAXBException | ClassNotFoundException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchFieldException | SecurityException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		if (printParsingDebugInformation)
			System.out.println("Element: " + cdaElement.getJavaName() + " (dataType: "
					+ cdaElement.getDataType() + ")");

	}

	@Override
	public void enterInclude(Hl7ItsParser.IncludeContext ctx) {

		isParentElement = (processingElement < processingElementPrev);
		int levels = 0;
		if (isParentElement)
			levels = processingElementPrev - processingElement;

		if (isParentElement) {
			parentForIncludes = currentCdaElement.getParentCdaElement();
			for (int i = 0; i < levels; i++) {
				parentForIncludes = parentForIncludes.getParentCdaElement();
			}
		}

		RefAttrContext refAttrCtx = ctx.refAttr();
		String ref = "";
		String dataType = null;
		if (refAttrCtx != null) {
			ref = refAttrCtx.AttrValue().getText().replace("\"", "");
			if (printParsingDebugInformation)
				if (printParsingDebugInformation)
					System.out.println("Include: " + ref);
			try {
				ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
						parentForIncludes, templateIndex, templateList, srcFilePath, dstFilePath,
						packageName, fileHeader, null);
				dataType = artDecor2JavaGenerator.doOneTemplate(ref);
			} catch (SaxonApiException | IOException | JAXBException | ClassNotFoundException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchFieldException | SecurityException e) {
				dataType = "includeDataTypeFAILURE";
				e.printStackTrace();
			}
		}

	}

	@Override
	public void enterTemplate(Hl7ItsParser.TemplateContext ctx) {
		String name = null;
		String id = null;

		IdAttrContext idAttrCtx = ctx.idAttr();
		if (idAttrCtx != null)
			id = idAttrCtx.AttrValue().getText().replace("\"", "");

		if ("2.16.756.5.30.1.1.10.9.16".equals(id))
			System.out.println("Stop here");

		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			name = nameAttrCtx.AttrValue().getText().replace("\"", "");

		if ((id == null) && (name == null))
			throw new RuntimeException(
					"id and name are null for template " + ctx.getText().substring(0, 500) + "...");
		if (id == null)
			throw new RuntimeException("id is null for template " + name);
		if (name == null)
			throw new RuntimeException("name is null for template " + id);

		processingTemplate++;
		currentCdaTemplate = new CdaTemplate();
		currentCdaTemplate.setId(id);
		currentCdaTemplate.setName(name);

		if (mainCdaTemplate == null)
			mainCdaTemplate = currentCdaTemplate;

		templateList.add(currentCdaTemplate);

		if (printParsingDebugInformation)
			System.out.println("Template: " + currentCdaTemplate.getName() + " (id: "
					+ currentCdaTemplate.getId() + ")");
	}

	@Override
	public void exitAttribute(Hl7ItsParser.AttributeContext ctx) {
		processingAttribute--;
	}

	@Override
	public void exitElement(Hl7ItsParser.ElementContext ctx) {
		processingElement--;
	}

	@Override
	public void exitTemplate(Hl7ItsParser.TemplateContext ctx) {
		processingTemplate--;
	}

	private void fillDatatypesRecursive(CdaElement cdaElement)
			throws JAXBException, ClassNotFoundException, IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException,
			NoSuchFieldException, SecurityException {
		if (cdaElement != null) {
			if (dataTypeIndex == null)
				dataTypeIndex = loadDataTypeIndex();
			String dataType = cdaElement.getDataType();
			if (dataType != null)
				if ("running".equals(dataType))
					dataType = null;
			if (dataType == null) {
				dataType = getDataType(cdaElement, templateIndex);
				cdaElement.setDataType(dataType);
			}
			if (printDataTypeDebugInformation)
				System.out.println("Datatype for "
						+ cdaElement.getFullXmlName().replace("hl7:", "").replace("pharm:", "")
						+ " --> " + dataType);
			for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
				fillDatatypesRecursive(item);
			}
		}
	}

	private String getDataType(CdaElement cdaElement, HashMap<String, String> templateIndex)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException,
			SecurityException, IOException {
		String retVal = null;
		String cdaElementName = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "");

		if (retVal == null) {
			ArrayList<String> candidates = new ArrayList<String>();
			if (dataTypeIndex == null)
				dataTypeIndex = loadDataTypeIndex();
			for (String key : dataTypeIndex.keySet()) {
				String value = dataTypeIndex.get(key).toString();
				if (key.startsWith(cdaElementName)
						|| key.startsWith("all.InfrastructureRoot." + cdaElementName)) {
					candidates.add(value);
				}
			}
			if (candidates.size() == 1)
				retVal = candidates.get(0);
			else if (candidates.size() > 1) {
				throw new RuntimeException("There are multiple data type candidates for "
						+ cdaElement.getFullXmlName());
			}
		}

		String parentDataType = null;
		if (cdaElement.getParentCdaElement() != null) {
			parentDataType = cdaElement.getParentCdaElement().getDataType();
			if (parentDataType == null) {
				parentDataType = getDataType(cdaElement.getParentCdaElement(), templateIndex);
				if (parentDataType != null) {
					parentDataType = adjustDataType(parentDataType);
				}
			}
		}

		if ((retVal == null) && (parentDataType != null)) {
			if (templateIndex.containsValue(parentDataType)) {
				retVal = parentDataType;
			}
		}

		if ((retVal == null) && (parentDataType != null)) {
			retVal = getDataType(parentDataType, cdaElementName);
			if (retVal == null) {
				String parentType = getParentDataType(parentDataType);
				while ((retVal == null) && (parentType != null)) {
					retVal = getDataType(parentType, cdaElementName);
					parentType = getParentDataType(parentType);
				}
			}
		}

		if (retVal == null)
			throw new RuntimeException("There is no data type candidate for "
					+ cdaElement.getFullXmlName()
					+ ". Make sure, the ART-DECOR model conforms to the XML schema (CDA_extPHARM.xsd).");
		return retVal;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getDataType(String parentClassName, String cdaElementName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		String retVal = null;

		Class cl = Class.forName(adjustDataType(parentClassName));
		XmlType xmlType = (XmlType) cl.getAnnotation(XmlType.class);
		if (xmlType != null) {
			for (String prop : xmlType.propOrder()) {
				if (prop.equals(cdaElementName.replace("hl7:", "").replace("pharm:", ""))) {
					String expectedMethodName = createGetterNamePascalCase(prop);
					for (Method method : cl.getMethods()) {
						if (method.getName().equals(expectedMethodName)) {
							if ("java.util.List".equals(method.getReturnType().getName())) {
								Field field = cl.getDeclaredField(prop);
								Type type = field.getGenericType();
								if (type instanceof ParameterizedType) {
									ParameterizedType pt = (ParameterizedType) type;
									if (pt.getActualTypeArguments().length > 1)
										throw new RuntimeException("Unhandled data type:"
												+ type.toString() + " (multiple types in list)");
									if (pt.getActualTypeArguments().length == 0)
										throw new RuntimeException("Unhandled data type:"
												+ type.toString() + " (zero types in List)");
									retVal = pt.getActualTypeArguments()[0].getTypeName();
								}
							} else
								retVal = method.getReturnType().getName();
						}
					}
				}
			}
		}
		return retVal;
	}

	@SuppressWarnings("rawtypes")
	private String getParentDataType(String parentClassName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String retVal = null;
		Class cl = Class.forName(adjustDataType(parentClassName));
		if (cl != null)
			if (cl.getSuperclass() != null)
				retVal = cl.getSuperclass().getName();
		return retVal;
	}

	@SuppressWarnings("rawtypes")
	private HashMap<String, String> loadDataTypeIndex() throws ClassNotFoundException, IOException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		HashMap<String, String> retVal = new HashMap<String, String>();

		ObjectFactory factory = new ObjectFactory();
		Method[] methods = factory.getClass().getMethods();
		for (int j = 0; j < methods.length; j++) {
			String key = null;
			String value = null;
			if ("javax.xml.bind.JAXBElement".equals(methods[j].getReturnType().getName())) {
				JAXBElement jaxbElement = (JAXBElement) methods[j].invoke(factory,
						new Object[] { null });
				key = jaxbElement.getName().getLocalPart();
				value = jaxbElement.getDeclaredType().getName();
			} else if (methods[j].getName().startsWith("create")) {
				Object obj = methods[j].invoke(factory);
				value = obj.getClass().getName();
				XmlType xmlType = (XmlType) obj.getClass().getAnnotation(XmlType.class);
				key = xmlType.name();
			}
			if (key != null) {
				int i = 0;
				while (retVal.containsKey(key + i)) {
					if (retVal.get(key + i).equals(value)) {
						i = -1;
						break;
					}
					i++;
				}
				if (i > -1)
					retVal.put(key + i, value);
			}
		}

		// This is for debugging purposes, only
		// retVal.entrySet().forEach(entry -> {
		// System.out.println(entry.getKey() + " " + entry.getValue());
		// });

		return retVal;
	}
}
