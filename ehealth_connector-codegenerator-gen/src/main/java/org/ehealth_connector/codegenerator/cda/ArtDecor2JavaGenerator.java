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

import static com.github.javaparser.ast.Modifier.privateModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;
import static com.github.javaparser.ast.Modifier.staticModifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsLexer;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.CodeAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.CodeSystemAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ConformanceAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ContainsAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.DataTypeAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.FlexibilityAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.IdAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MaxOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MinOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.NameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.RefAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueSetAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.ehealth_connector.codegenerator.cda.enums.ProcessModes;
import org.ehealth_connector.codegenerator.cda.xslt.Hl7Its2EhcTransformer;
import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.codegenerator.valuesets.UpdateValueSets;
import org.ehealth_connector.codegenerator.valuesets.ValueSetUtil;
import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.basetypes.CodeBaseType;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;
import org.ehealth_connector.common.utils.FileUtil;
import org.ehealth_connector.common.utils.Util;
import org.ehealth_connector.valueset.api.ValueSetManager;
import org.ehealth_connector.valueset.config.ValueSetConfig;
import org.ehealth_connector.valueset.enums.SourceFormatType;
import org.ehealth_connector.valueset.enums.SourceSystemType;
import org.ehealth_connector.valueset.exceptions.InitializationException;
import org.ehealth_connector.valueset.model.ValueSet;
import org.xml.sax.SAXException;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.javadoc.Javadoc;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

	private static HashMap<String, String> dataTypeIndex = null;

	private static void addBodyComment(ConstructorDeclaration constructor, String comment) {
		BlockStmt body = constructor.getBody();
		LineComment c = new LineComment(comment);
		body.addOrphanComment(c);
	}

	private static void addBodyComment(MethodDeclaration method, String comment) {
		Optional<BlockStmt> bodyOpt = method.getBody();
		if (bodyOpt.isPresent()) {
			LineComment c = new LineComment(comment);
			bodyOpt.get().addOrphanComment(c);
		}
	}

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

	private static void completeCreatorForFixedContentsMethod(
			MethodDeclaration creatorForFixedContentsMethod, CdaElement cdaElement) {

		Optional<BlockStmt> bodyOpt = creatorForFixedContentsMethod.getBody();
		if (bodyOpt.isPresent()) {
			BlockStmt body = bodyOpt.get();

			// remove probably earlier added statements
			ArrayList<Statement> statements = new ArrayList<Statement>();
			for (Statement stmt : body.getStatements()) {
				if ("return retVal;".equals(stmt.toString()))
					statements.add(stmt);
			}
			for (Statement statement2 : statements) {
				body.getStatements().remove(statement2);
			}

			body.addStatement("return retVal;");
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

		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		}

		boolean isLocalField = (myClass.getExtendedTypes().size() == 0);

		BlockStmt body = method.createBody();
		if (isClassCollection(memberType)) {
			if (isLocalField) {
				body.addStatement(name + ".add(value);");
			} else {
				name = toUpperFirstChar(name);
				body.addStatement(createGetterNamePascalCase(name) + "().add(value);");
			}
		} else {
			if (cdaElement.getDataType().endsWith(".ENXP")) {
				String enPartL = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
						.replace("xsi:", "");
				String enPartU = toUpperFirstChar(enPartL);

				addImport(compilationUnit, "javax.xml.namespace.QName");
				addImport(compilationUnit, "org.ehealth_connector.common.hl7cdar2.En" + enPartU);
				addImport(compilationUnit, "javax.xml.bind.JAXBElement");

				body.addStatement("En" + enPartU + " obj = new En" + enPartU + "();");
				body.addStatement("obj.xmlContent = value.xmlContent;");
				body.addStatement("getContent().add(new JAXBElement<En" + enPartU
						+ ">(new QName(\"hl7:" + enPartL + "\"), En" + enPartU + ".class, obj));");
			} else {
				System.out.println("\nWARNING: " + cdaElement.getFullXmlName()
						+ " is declared as list, but the XML Schema hosts it as single field. It is strongly recommended to correct the ART-DECOR model!");
				body.addStatement(name + "= value;");
			}
		}
	}

	private static String createAdderNamePascalCase(String name) {
		return "add" + JavaCodeGenerator.toPascalCase(name);
	}

	private static void createClearer(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		}

		boolean isLocalField = (myClass.getExtendedTypes().size() == 0);

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
			if (isLocalField) {
				body.addStatement(name + ".clear();");
			} else {
				name = toUpperFirstChar(name);
				body.addStatement(createGetterNamePascalCase(name) + "().clear();");
			}
		} else {
			// Do nothing
		}

	}

	private static ConstructorDeclaration createDefaultConstructor(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		return myClass.addConstructor(publicModifier().getKeyword());
	}

	private static void createField(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");
		String dataType = cdaElement.getDataType();
		if ("hl7:typeId".contentEquals(cdaElement.getXmlName())) {
			dataType = "org.ehealth_connector.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
		}
		@SuppressWarnings("rawtypes")
		Class memberType = getDeclaredFieldDatatype(cdaElement.getParentCdaElement().getDataType(),
				name);

		Optional<FieldDeclaration> fieldOpt = myClass.getFieldByName(name);
		FieldDeclaration field = null;
		if (fieldOpt.isPresent())
			field = fieldOpt.get();
		else {
			if (isClassCollection(memberType)) {
				addImport(compilationUnit, "java.util.ArrayList");
				field = myClass.addPrivateField("ArrayList<" + dataType + ">", name);
				field.getVariable(0).setInitializer("new ArrayList<" + dataType + ">()");
			} else
				field = myClass.addPrivateField(dataType, name);

			// We do not want to have this member serialized!
			addImport(compilationUnit, "javax.xml.bind.annotation.XmlTransient");
			field.addAnnotation(createXmlTransientAnnotation());
		}
		String comment = null;
		Optional<JavadocComment> jdocOpt = field.getJavadocComment();
		if (jdocOpt.isPresent())
			comment = jdocOpt.get().getContent();

		String desc = cdaElement.getDescription();
		if (desc != null)
			if (comment == null)
				comment = desc;
			else
				comment = desc + Util.getPlatformSpecificLineBreak() + comment;
		if (comment == null)
			comment = "No description available in the ART-DECOR model for this field.";
		field.setJavadocComment(comment);
	}

	private static void createGetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		method = myClass.addMethod(createGetterNamePascalCase(cdaElement.getJavaName()),
				publicModifier().getKeyword());

		String dataType = cdaElement.getDataType();
		if ("hl7:typeId".contentEquals(cdaElement.getXmlName())) {
			dataType = "org.ehealth_connector.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
		}
		method.setType(dataType);

		String comment = "Gets the " + cdaElement.getJavaName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += Util.getPlatformSpecificLineBreak() + desc;

		method.setJavadocComment(comment);

		BlockStmt body = method.createBody();
		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");

		CdaElement elForType = cdaElement.getParentCdaElement();
		if (elForType == null)
			elForType = cdaElement;
		String genericType = getGenericFieldType(elForType.getDataType(), name);
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(elForType.getDataType(), name);
		boolean isMethod = false;
		if (memberType == null) {
			memberType = getMethodDatatype(elForType.getDataType(), name);
			isMethod = (memberType != null);
		}
		if (memberType == null && cdaElement.getDataType().endsWith(".ADXP")) {
			memberType = getClass(cdaElement.getDataType());
		}
		if (myClass.getExtendedTypes().size() == 0)
			isMethod = false;
		if (memberType != null) {
			String temp = name;
			String cast = "";
			boolean doCast = false;
			if (isClassCollection(memberType)) {
				if (isMethod)
					temp = createGetterNameUpperFirstChar(name) + "()";
				if (genericType.endsWith(".ANY>"))
					doCast = true;
				if (cdaElement.getDataType().endsWith(".IVLTS"))
					doCast = true;

				if (doCast)
					cast = "(" + cdaElement.getDataType() + ")";

				body.addStatement(cdaElement.getDataType() + " retVal = null;");
				body.addStatement("if (" + temp + " != null) if (" + temp
						+ ".size() > 0)  retVal = " + cast + temp + ".get(0);");
				body.addStatement("return retVal;");
			} else {
				if (genericType.endsWith(".CD") && cdaElement.getDataType().endsWith(".CE"))
					doCast = true;

				if (doCast)
					cast = "(" + cdaElement.getDataType() + ")";

				body.addStatement("return " + cast + temp + ";");
			}
		} else
			body.addStatement("return this;");
	}

	private static String createGetterNamePascalCase(String name) {
		return "get" + JavaCodeGenerator.toPascalCase(name);
	}

	private static String createGetterNameUpperFirstChar(String name) {
		return "get" + toUpperFirstChar(name);
	}

	private static void createInitVersionMethods(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {

		addImport(compilationUnit, "org.ehealth_connector.common.Identificator");
		addImport(compilationUnit, "org.ehealth_connector.common.utils.Hl7CdaR2Util");

		MethodDeclaration method;
		BlockStmt body;

		// initFirstVersion
		method = myClass.addMethod("initFirstVersion", publicModifier().getKeyword());
		method.setJavadocComment(
				"Sets the version number to 1 and makes sure the setId is the same as the document id.\n@param newDocId the new doc id");
		method.addAndGetParameter("Identificator", "newDocId");

		body = method.createBody();
		body.addStatement("Identificator docId = newDocId;");
		body.addStatement(
				"if (docId == null) docId = new Identificator(Identificator.builder().withRoot(org.openhealthtools.ihe.utils.UUID.generate()).build());");
		body.addStatement("super.setId(docId.getHl7CdaR2Ii());");
		body.addStatement("setVersion(docId, 1);");

		// initNextVersion
		method = myClass.addMethod("initNextVersion", publicModifier().getKeyword());
		method.setJavadocComment(
				"Increases the version number by one and makes sure the setId remains the same as previously.\n@param newDocId the new doc id");
		method.addAndGetParameter("Identificator", "newDocId");

		body = method.createBody();
		body.addStatement("org.ehealth_connector.common.hl7cdar2.II setId = getSetId();");
		body.addStatement("if (setId == null) setId = getId();");
		body.addStatement("if (setId == null) setId = newDocId.getHl7CdaR2Ii();");
		body.addStatement("Integer version = CdaUtil.getInt(getVersionNumber());");
		body.addStatement("setId(newDocId.getHl7CdaR2Ii());");
		body.addStatement("setVersion(new Identificator(setId), version + 1);");

		// setVersion
		method = myClass.addMethod("setVersion", publicModifier().getKeyword());
		method.setJavadocComment(
				"<div class=\"en\">Sets the document set Id and version number.</div>\n\n<div class=\"de\">Weist dem Dokument eine Set Id und eine Versionsnummer zu.</div>\n@param idVersion1 the set Id (if null, the document ID will be used)\n@param version the version of the document");
		method.addAndGetParameter("Identificator", "idVersion1");
		method.addAndGetParameter("int", "version");

		body = method.createBody();
		body.addStatement("super.setSetId(idVersion1.getHl7CdaR2Ii());");
		body.addStatement("super.setVersionNumber(Hl7CdaR2Util.createHl7CdaR2Int(version));");

	}

	private static void createLoaderMethods(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		addImport(compilationUnit, "java.io.File");
		addImport(compilationUnit, "javax.xml.bind.JAXBContext");
		addImport(compilationUnit, "javax.xml.bind.JAXBElement");
		addImport(compilationUnit, "javax.xml.bind.JAXBException");
		addImport(compilationUnit, "java.io.IOException");
		addImport(compilationUnit, "javax.xml.bind.Unmarshaller");
		addImport(compilationUnit, "javax.xml.transform.stream.StreamSource");

		MethodDeclaration method;
		String comment = "Loads the CDA document from file.";

		// The one with the file name as parameter
		method = myClass.addMethod("loadFromFile", publicModifier().getKeyword(),
				staticModifier().getKeyword());
		method.setType(myClass.getNameAsString());
		method.setJavadocComment(comment
				+ "\n@param inputFileName the full path and filename of the sourcefile.\n@return the CDA document\\n@throws JAXBException the JAXB exception\\n@throws IOException Signals that an I/O exception has occurred.");
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
				+ "\n@param inputFile the source file.\nn@return the CDA document\\n@throws JAXBException the JAXB exception\\n@throws IOException Signals that an I/O exception has occurred.");
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

	private static void createMemberAndGetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, String dataType, String memberName,
			String methodName) {

		Optional<FieldDeclaration> memberOpt = myClass.getFieldByName(memberName);
		if (!memberOpt.isPresent()) {

			FieldDeclaration field = myClass.addField(dataType, memberName,
					privateModifier().getKeyword());

			// We do not want to have this member serialized!
			addImport(compilationUnit, "javax.xml.bind.annotation.XmlTransient");
			field.addAnnotation(createXmlTransientAnnotation());

			MethodDeclaration method;
			method = myClass.addMethod(methodName, publicModifier().getKeyword());
			method.setType(dataType);

			String comment = "Gets the member " + memberName;
			method.setJavadocComment(comment);

			BlockStmt body = method.createBody();
			body.addStatement("return " + memberName + ";");
		}
	}

	private static void createSaverMethods(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		// addImport(compilationUnit, "java.io.File");
		// addImport(compilationUnit, "javax.xml.bind.JAXBContext");
		// addImport(compilationUnit, "javax.xml.bind.JAXBException");
		// addImport(compilationUnit, "javax.xml.bind.Marshaller");
		// addImport(compilationUnit,
		// "org.ehealth_connector.common.CdaNamespacePrefixMapper");

		addImport(compilationUnit, "org.ehealth_connector.cda.utils.CdaUtil");

		MethodDeclaration method;
		BlockStmt body;
		String comment = "Saves the current CDA document to file.";

		// The one with the file name as parameter
		method = myClass.addMethod("saveToFile", publicModifier().getKeyword());
		method.setJavadocComment(comment
				+ "\n@param outputFileName the full path and filename of the destination file.\n@throws JAXBException the JAXB exception\n@throws ParserConfigurationException the parser configuration exception\n@throws TransformerException the transformer exception\n@throws FileNotFoundException the file not found exception");
		method.addAndGetParameter("String", "outputFileName");
		method.addThrownException(JAXBException.class);
		method.addThrownException(ParserConfigurationException.class);
		method.addThrownException(TransformerException.class);
		method.addThrownException(FileNotFoundException.class);

		body = method.createBody();
		body.addStatement("saveToFile(new File(outputFileName), null, null);");

		// The one with the file name as parameter
		method = myClass.addMethod("saveToFile", publicModifier().getKeyword());
		method.setJavadocComment(comment
				+ "\n@param outputFile the destination file.\n@throws JAXBException the JAXB exception\n@throws ParserConfigurationException the parser configuration exception\n@throws TransformerException the transformer exception\n@throws FileNotFoundException the file not found exception");
		method.addAndGetParameter("File", "outputFile");
		method.addThrownException(JAXBException.class);
		method.addThrownException(ParserConfigurationException.class);
		method.addThrownException(TransformerException.class);
		method.addThrownException(FileNotFoundException.class);

		body = method.createBody();
		body.addStatement("saveToFile(outputFile, null, null);");

		// The one with the file name and xsl, css as parameter
		method = myClass.addMethod("saveToFile", publicModifier().getKeyword());
		method.setJavadocComment(comment
				+ "\n@param outputFileName the full path and filename of the destination file.\n@param xsl the path and filename or url to the rendering stylesheet\n@param css the path and filename or url to the rendering css\n@throws JAXBException the JAXB exception\n@throws ParserConfigurationException the parser configuration exception\n@throws TransformerException the transformer exception\\n@throws FileNotFoundException the file not found exception");
		method.addAndGetParameter("String", "outputFileName");
		method.addAndGetParameter("String", "xsl");
		method.addAndGetParameter("String", "css");
		method.addThrownException(JAXBException.class);
		method.addThrownException(ParserConfigurationException.class);
		method.addThrownException(TransformerException.class);
		method.addThrownException(FileNotFoundException.class);

		body = method.createBody();
		body.addStatement("saveToFile(new File(outputFileName), xsl, css);");

		// The one with the file as parameter
		method = myClass.addMethod("saveToFile", publicModifier().getKeyword());
		method.setJavadocComment(comment
				+ "\n@param outputFile the destination file.\n@param xsl the path and filename or url to the rendering stylesheet\n@param css the path and filename or url to the rendering css\n@throws JAXBException the JAXB exception\n@throws ParserConfigurationException the parser configuration exception\n@throws TransformerException the transformer exception\\n@throws FileNotFoundException the file not found exception");
		method.addAndGetParameter("File", "outputFile");
		method.addAndGetParameter("String", "xsl");
		method.addAndGetParameter("String", "css");
		method.addThrownException(JAXBException.class);
		method.addThrownException(ParserConfigurationException.class);
		method.addThrownException(TransformerException.class);
		method.addThrownException(FileNotFoundException.class);

		body = method.createBody();
		body.addStatement("CdaUtil.saveJaxbObjectToFile(this, outputFile, xsl, css);");
	}

	private static void createSetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		method = myClass.addMethod(createSetterNamePascalCase(cdaElement.getJavaName()),
				publicModifier().getKeyword());

		String dataType = cdaElement.getDataType();
		// TODO: This is a quick fix for CDA-CH-EMED
		if ("hl7:routeCode".equals(cdaElement.getXmlName()))
			dataType = dataType.replace(".CD", ".CE");
		// End of fix
		if ("hl7:typeId".contentEquals(cdaElement.getXmlName())) {
			dataType = "org.ehealth_connector.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
		}

		String comment = "Sets the " + cdaElement.getJavaName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += Util.getPlatformSpecificLineBreak() + desc;

		method.setJavadocComment(comment);

		method.addAndGetParameter(dataType, "value");

		BlockStmt body = method.createBody();
		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");

		CdaElement elForType = cdaElement.getParentCdaElement();
		if (elForType == null)
			elForType = cdaElement;
		// String genericType = getGenericFieldType(elForType.getDataType(),
		// name);
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(elForType.getDataType(), name);
		boolean isMethod = false;
		if (memberType == null) {
			memberType = getMethodDatatype(elForType.getDataType(), name);
			isMethod = (memberType != null);
		}
		if (memberType == null && dataType.endsWith(".ADXP")) {
			memberType = getClass(dataType);
		}
		if (myClass.getExtendedTypes().size() == 0)
			isMethod = false;
		if (memberType != null) {
			String temp = name;
			if (isClassCollection(memberType)) {
				if (isMethod)
					temp = createGetterNameUpperFirstChar(name) + "()";
				body.addStatement(temp + ".clear();");
				body.addStatement(temp + ".add(value);");
			} else {
				String cast = "";
				boolean doCast = false;

				if (doCast)
					cast = "(" + memberType.getName() + ")";

				if (memberType.getName().endsWith(".IVLTS") && !(dataType.endsWith(".IVLTS"))) {
					if (memberType.getName().endsWith(".IVLTS") && (dataType.endsWith(".TS"))) {
						// Create Interval from single TimeStamp
						addImport(compilationUnit, "org.ehealth_connector.common.hl7cdar2.IVLTS");
						addImport(compilationUnit,
								"org.ehealth_connector.common.hl7cdar2.ObjectFactory");

						body.addStatement("ObjectFactory factory = new ObjectFactory();");
						body.addStatement("IVLTS ivlts = factory.createIVLTS();");
						body.addStatement("ivlts.setValue(value.getValue());");
						body.addStatement("this." + temp + " = ivlts;");
					} else
						throw new RuntimeException(
								memberType.getName() + " cannot be cast to " + dataType);
				} else
					body.addStatement("this." + temp + " = " + cast + "value;");
			}
		} else
			addBodyComment(method, "TODO: NYI");
	}

	private static String createSetterNamePascalCase(String name) {
		return "set" + JavaCodeGenerator.toPascalCase(name);
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

	public static NormalAnnotationExpr createXmlTransientAnnotation() {
		NormalAnnotationExpr retVal = new NormalAnnotationExpr();
		retVal.setName(new Name("XmlTransient"));
		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static Class getClass(String className) {
		Class retVal = null;

		try {
			retVal = Class.forName(className);

		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static Class getDeclaredFieldDatatype(Class myClass, String memberName) {
		Class retVal = null;

		if (myClass != null) {
			Field f;
			try {
				f = myClass.getDeclaredField(memberName);
				retVal = f.getType();
			} catch (NoSuchFieldException | SecurityException e) {
				if (myClass.getSuperclass() != null)
					retVal = getDeclaredFieldDatatype(myClass.getSuperclass(), memberName);
			}
		}

		return retVal;
	}

	@SuppressWarnings("rawtypes")
	public static Class getDeclaredFieldDatatype(String className, String memberName) {
		Class retVal = null;

		try {
			Class c = Class.forName(className);
			if (c != null) {
				retVal = getDeclaredFieldDatatype(c, memberName);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	@SuppressWarnings("rawtypes")
	public static boolean isClassCollection(Class c) {
		if (c == null)
			return false;
		else
			return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
	}

	private static boolean isCompleteCreatorForFixedContentsMethod(
			MethodDeclaration setterForFixedContentsMethod) {
		Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
		boolean retVal = false;
		if (bodyOpt.isPresent()) {
			BlockStmt body = bodyOpt.get();
			for (Statement stmt : body.getStatements()) {
				retVal = ("return retVal;".equals(stmt.toString()));
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

	private CdaElement callingCdaElement;

	private CdaAttribute currentCdaAttribute = null;

	private CdaElement currentCdaElement = null;

	private CdaTemplate currentCdaTemplate = null;

	private String dstFilePath = null;

	private String fullDstFilePath = null;

	private String fileHeader;

	private CdaTemplate mainCdaTemplate = null;

	private String packageName;

	private CdaElement parentForContains;

	private CdaElement parentForIncludes;

	private boolean printAssembledDebugInformation = false;

	private boolean printDataTypeDebugInformation = false;

	private boolean printParsingDebugInformation = false;

	private int processingAttribute = 0;

	private int processingElement = 0;

	private int processingElementPrev = 0;

	private int processingTemplate = 0;

	boolean initialRun;

	private String srcFilePath = null;

	private String artDecorPrefix;

	private HashMap<String, CdaTemplate> templateIndex = null;

	private HashMap<String, String> valueSetIndex = null;

	private ArrayList<CdaTemplate> templateList = null;

	private boolean skipValueSetGeneration = false;

	private CdaTemplate runningTemplate;

	private boolean processingRootInclude = false;

	public ArtDecor2JavaGenerator(CdaElement callingCdaElement,
			HashMap<String, CdaTemplate> templateIndex, HashMap<String, String> valueSetIndex,
			ArrayList<CdaTemplate> templateList, String srcFilePath, String dstFilePath,
			String packageName, String fileHeader, String artDecorPrefix) throws IOException {
		this.callingCdaElement = callingCdaElement;
		this.artDecorPrefix = artDecorPrefix;

		if (templateIndex == null)
			this.templateIndex = new HashMap<String, CdaTemplate>();
		else
			this.templateIndex = templateIndex;

		if (valueSetIndex == null)
			this.valueSetIndex = new HashMap<String, String>();
		else
			this.valueSetIndex = valueSetIndex;

		if (templateList == null)
			this.templateList = new ArrayList<CdaTemplate>();
		else
			this.templateList = templateList;

		this.srcFilePath = srcFilePath;
		this.dstFilePath = dstFilePath;
		this.packageName = packageName;
		this.fileHeader = fileHeader;

		if (!this.dstFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.dstFilePath += FileUtil.getPlatformSpecificPathSeparator();
		fullDstFilePath = new File(new File(dstFilePath, "src/main/java"),
				packageName.replaceAll("\\.", "/")).getAbsolutePath();

		if (!this.fullDstFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.fullDstFilePath += FileUtil.getPlatformSpecificPathSeparator();

		initialRun = (callingCdaElement == null);
		if (initialRun)
			FileUtils.deleteDirectory(new File(fullDstFilePath));

		if (!this.srcFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.srcFilePath += FileUtil.getPlatformSpecificPathSeparator();

		runningTemplate = new CdaTemplate();
		runningTemplate.setName("***Running***");

	}

	@SuppressWarnings("unlikely-arg-type")
	private String adjustDataType(String dataType) {
		String retVal = dataType;
		if (retVal != null) {

			if (retVal.endsWith("running"))
				retVal = null;
			else {
				if (retVal.startsWith("IVL_PQ"))
					retVal = "IVLPQ";
				if (retVal.startsWith("EIVL_TS"))
					retVal = "IVLTS";
				if (retVal.startsWith("IVL_TS"))
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

	private String adjustValueSet(String value) {
		String retVal = value;
		String testEnum = value.replace(packageName + ".enums",
				"org.ehealth_connector.common.hl7cdar2");
		try {
			Class cl = Class.forName(adjustDataType(testEnum));
			if (cl != null)
				retVal = cl.getName();
		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	private MethodDeclaration createCreatorForFixedContentsAttribute(
			CompilationUnit compilationUnit, ClassOrInterfaceDeclaration myClass,
			CdaAttribute cdaAttribute, String setterForFixedContentsName) {
		MethodDeclaration method = myClass.addMethod(setterForFixedContentsName,
				privateModifier().getKeyword());
		BlockStmt body = method.createBody();

		String dataType = null;
		if (cdaAttribute.getValueSetId() != null) {
			dataType = adjustValueSet(valueSetIndex.get(cdaAttribute.getValueSetId()));
		} else {
			dataType = "String";
		}

		String attrName = cdaAttribute.getName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");
		String memberName = "my" + JavaCodeGenerator.toPascalCase(attrName);

		String methodName = "getPredefined" + JavaCodeGenerator.toPascalCase(attrName);
		createMemberAndGetter(compilationUnit, myClass, dataType, memberName, methodName);

		method.addAndGetParameter(dataType, "value");
		method.setJavadocComment(
				"Creates fixed contents for CDA Attribute " + cdaAttribute.getName() + "\n\n");

		body.addStatement("this." + memberName + " = value;");

		return method;
	}

	private MethodDeclaration createCreatorForFixedContentsElement(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement,
			String setterForFixedContentsName) {

		MethodDeclaration method = myClass.addMethod(setterForFixedContentsName,
				publicModifier().getKeyword());
		method.setJavadocComment(
				"Creates fixed contents for CDA Element " + cdaElement.getJavaName() + "\n\n");

		compilationUnit.addImport("org.ehealth_connector.common.hl7cdar2.ObjectFactory");

		BlockStmt body = method.createBody();

		String dataType = adjustDataType(cdaElement.getDataType());
		// TODO: This is a quick fix for CDA-CH-EMED
		if ("hl7:routeCode".equals(cdaElement.getXmlName()))
			dataType = dataType.replace(".CD", ".CE");
		// End of fix

		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");
		@SuppressWarnings("rawtypes")
		Class memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
		boolean isField = (memberType != null);
		boolean isMethod = false;
		if (!isField) {
			memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
			isMethod = (memberType != null);
		}
		if (!(isField || isMethod)) {
			if (cdaElement.getDataType().endsWith(".ENXP")) {
				method.setType(dataType);

				String enPartL = name;
				String enPartU = toUpperFirstChar(enPartL);

				addImport(compilationUnit, "org.ehealth_connector.common.hl7cdar2.En" + enPartU);

				body.addStatement("En" + enPartU + " retVal = new En" + enPartU + "();");
			} else
				throw new RuntimeException(
						name + " is neither an accesible field nor an accessible getter");
		} else {
			if (memberType.getName().endsWith("POCDMT000040InfrastructureRootTypeId")) {
				dataType = memberType.getName();
			}
			method.setType(dataType);
			body.addStatement("ObjectFactory factory = new ObjectFactory();");
			body.addStatement(dataType + " retVal = factory.create"
					+ dataType.replace("org.ehealth_connector.common.hl7cdar2.", "") + "();");
		}
		return method;
	}

	private void createFixedAttributeValues(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {
		if (cdaElement.getCdaAttributeList() != null) {

			ConstructorDeclaration constructor = null;
			MethodDeclaration creatorForFixedContentsMethod = null;
			StringBuilder sb = new StringBuilder();

			boolean isAttributeOfTemplateRootElement = false;
			if (cdaElement.getTemplate().getCdaElementList().size() == 1)
				isAttributeOfTemplateRootElement = (cdaElement
						.equals(cdaElement.getTemplate().getCdaElementList().get(0)));

			int i = 0;
			for (CdaAttribute cdaAttribute : cdaElement.getCdaAttributeList()) {
				String attrName = cdaAttribute.getName().replace("hl7:", "").replace("pharm:", "")
						.replace("xsi:", "");
				Optional<ConstructorDeclaration> constructorOpt = myClass
						.getConstructorByParameterTypes(new String[] {});
				boolean constructorExist = constructorOpt.isPresent();
				if (!constructorExist) {
					constructor = createDefaultConstructor(compilationUnit, myClass);
				} else
					constructor = constructorOpt.get();

				if ((cdaAttribute.getValue() != null) || (cdaAttribute.getValueSetId() != null)) {
					if (cdaElement.getJavaName() == null)
						cdaElement.setJavaName(
								JavaCodeGenerator.toCamelCase(cdaElement.getXmlName()));

					String creatorForFixedContentsName;
					if (isAttributeOfTemplateRootElement) {
						creatorForFixedContentsName = "create"
								+ JavaCodeGenerator.toPascalCase(attrName) + "FixedValue";

					} else {
						creatorForFixedContentsName = "create"
								+ JavaCodeGenerator.toPascalCase(cdaElement.getJavaName())
								+ "FixedValue";
					}
					List<MethodDeclaration> creatorForFixedContentsMethodList = myClass
							.getMethodsByName(creatorForFixedContentsName);

					boolean creatorForFixedContentsExist = (creatorForFixedContentsMethodList
							.size() > 0);
					if (!creatorForFixedContentsExist) {
						if (isAttributeOfTemplateRootElement)
							creatorForFixedContentsMethod = createCreatorForFixedContentsAttribute(
									compilationUnit, myClass, cdaAttribute,
									creatorForFixedContentsName);
						else
							creatorForFixedContentsMethod = createCreatorForFixedContentsElement(
									compilationUnit, myClass, cdaElement,
									creatorForFixedContentsName);
					} else
						creatorForFixedContentsMethod = creatorForFixedContentsMethodList.get(0);

					if (cdaAttribute.getValue() != null) {
						boolean ignoreAttr = false;
						// fix for createHl7EffectiveTimeFixedValue: there is no
						// type in the ART-DECOR model
						if ("createHl7EffectiveTimeFixedValue"
								.equals(creatorForFixedContentsMethod.getNameAsString())) {
							ignoreAttr = ("xsi:type".contentEquals(cdaAttribute.getName()));
						}
						// end of fix

						if (!ignoreAttr) {
							if (!isAttributeOfTemplateRootElement)
								updateCreatorForFixedContentsMethod(compilationUnit,
										creatorForFixedContentsMethod, cdaElement, cdaAttribute);

							// if (cdaAttribute.isVocab()) {
							// if (cdaAttribute.getCode() != null)
							// addBodyComment(constructor,
							// "Vocab not supported, yet. Should add a code:"
							// + cdaAttribute.getCode());
							// else if (cdaAttribute.getValue() != null)
							// addBodyComment(constructor,
							// "Vocab not supported, yet. Should add a value:"
							// + cdaAttribute.getValue());
							// else if (cdaAttribute.getValueSetId() != null)
							// addBodyComment(constructor,
							// "Vocab not supported, yet. Should add a value
							// set:"
							// + cdaAttribute.getValueSetId());
							// }
							// Condition is not final, yet.
							if (i < creatorForFixedContentsMethod.getParameters().size()) {
								i++;
								if (sb.length() != 0)
									sb.append(", ");
								sb.append("\"" + cdaAttribute.getValue() + "\"");
							}
						}
					}
				}

				String templateClassName = JavaCodeGenerator
						.toPascalCase(cdaElement.getTemplate().getName());
				if (templateClassName.contentEquals(myClass.getNameAsString())) {
					if (cdaAttribute.getValue() != null) {
						String vocabString = "";
						if (cdaAttribute.isVocab())
							vocabString = " (isVocab)";
						addBodyComment(constructor,
								cdaElement.getTemplate().getName() + "/" + cdaElement.getXmlName()
										+ ":" + cdaAttribute.getDataType() + " " + attrName
										+ " = \"" + cdaAttribute.getValue() + "\";" + vocabString);
						boolean isTemp = false;
						if (cdaElement.getTemplate().getCdaElementList().size() == 1)
							isTemp = (cdaElement
									.equals(cdaElement.getTemplate().getCdaElementList().get(0)));
						if (isTemp) {
							@SuppressWarnings("rawtypes")
							Class memberType = getDeclaredFieldDatatype(cdaElement.getDataType(),
									attrName);
							String statement = "";

							boolean isLocalField = (myClass.getExtendedTypes().size() == 0);
							if (isClassCollection(memberType)) {
								if (isLocalField) {
									statement = attrName + ".add(" + "\"" + cdaAttribute.getValue()
											+ "\"" + ");";
								} else {
									statement = "super.get" + toUpperFirstChar(attrName) + "().add("
											+ "\"" + cdaAttribute.getValue() + "\"" + ");";
								}
							} else {
								if (isLocalField) {
									statement = "this." + attrName + " = " + "\""
											+ cdaAttribute.getValue() + "\"" + ";";
								} else {
									String dataType = null;
									try {
										dataType = getDataType(cdaElement.getDataType(), attrName);
									} catch (InstantiationException | IllegalAccessException
											| ClassNotFoundException | NoSuchFieldException
											| SecurityException e) {
										throw new RuntimeException(
												"Unhandled exception while getting Datatype for "
														+ attrName + "("
														+ cdaElement.getFullXmlName() + ")");
									}

									boolean isEnum = false;
									if (dataType != null)
										isEnum = (!"java.lang.String".contentEquals(dataType));
									if (isEnum) {
										String enumName = memberType.getName();
										statement = "super.set" + toUpperFirstChar(attrName) + "("
												+ enumName + ".fromValue(" + "\""
												+ cdaAttribute.getValue() + "\"" + "));";
									} else {
										statement = "super.set" + toUpperFirstChar(attrName) + "("
												+ "\"" + cdaAttribute.getValue() + "\"" + ");";
									}
								}
							}
							addBodyStatement(constructor, statement);
						}
					}
					if (cdaAttribute.getValueSetId() != null) {
						String enumName = adjustValueSet(
								valueSetIndex.get(cdaAttribute.getValueSetId()));
						addBodyComment(constructor,
								cdaElement.getTemplate().getName() + "/" + cdaElement.getXmlName()
										+ ":" + cdaAttribute.getDataType() + " " + attrName
										+ " = valueSet(\"" + cdaAttribute.getValueSetId()
										+ "\"); --> " + enumName);
					}
				}
			}
			if ((creatorForFixedContentsMethod != null)) {
				if (!isAttributeOfTemplateRootElement) {
					if (!isCompleteCreatorForFixedContentsMethod(creatorForFixedContentsMethod))
						completeCreatorForFixedContentsMethod(creatorForFixedContentsMethod,
								cdaElement);

					int argCountMethod = creatorForFixedContentsMethod.getParameters().size();
					// fix for setHl7EntryRelationshipFixedValue: sometimes in
					// the first occurrence, there is no inversionInd in the
					// ART-DECOR model
					if ("createHl7EntryRelationshipFixedValue"
							.contentEquals(creatorForFixedContentsMethod.getNameAsString())) {
						if (argCountMethod == 1) {
							CdaAttribute attr = new CdaAttribute();
							attr.setCdaElement(cdaElement);
							attr.setName("inversionInd");
							updateCreatorForFixedContentsMethod(compilationUnit,
									creatorForFixedContentsMethod, cdaElement, attr);

						}
						argCountMethod = 2;
					}
					int argCountGiven = org.apache.commons.lang3.StringUtils
							.countMatches(sb.toString(), ",") + 1;

					while (argCountGiven < argCountMethod) {
						sb.append(", ");
						sb.append("null");
						argCountGiven = org.apache.commons.lang3.StringUtils
								.countMatches(sb.toString(), ",") + 1;
					}
					// end of fixes

					String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
							.replace("xsi:", "");

					@SuppressWarnings("rawtypes")
					Class memberType = getDeclaredFieldDatatype(
							cdaElement.getParentCdaElement().getDataType(), name);
					String statement = "";

					String creator = creatorForFixedContentsMethod.getNameAsString() + "("
							+ sb.toString() + ")";

					boolean isLocalField = (myClass.getExtendedTypes().size() == 0);
					if (isClassCollection(memberType)) {
						if (isLocalField) {
							statement = cdaElement.getXmlName().replace("hl7:", "")
									.replace("pharm:", "") + ".add(" + creator + ");";
						} else {
							statement = "super.get"
									+ toUpperFirstChar(cdaElement.getXmlName().replace("hl7:", "")
											.replace("pharm:", "").replace("xsi:", ""))
									+ "().add(" + creator + ");";
						}
					} else {
						if (isLocalField) {
							statement = "this." + cdaElement.getXmlName().replace("hl7:", "")
									.replace("pharm:", "") + " = " + creator + ";";
						} else {
							statement = "super.set"
									+ toUpperFirstChar(cdaElement.getXmlName().replace("hl7:", "")
											.replace("pharm:", "").replace("xsi:", ""))
									+ "(" + creator + ");";
						}
					}

					addBodyStatement(constructor, statement);
				}
			}
		}
	}

	public void createJavaClasses() throws IOException {
		regroupTemplateElements(templateList);
		System.out.println("Writing Java Class files:");
		for (CdaTemplate cdaTemplate : templateList) {
			System.out.println("- " + cdaTemplate.getName());
			createJavaClassFile(cdaTemplate, packageName, fullDstFilePath);
		}
		System.out.println(" done.");
	}

	private void createJavaClassFile(CdaTemplate cdaTemplate, String packageName,
			String dstFilePath) throws IOException {

		// Save the current CDA Element as Java Class File
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.setPackageDeclaration(packageName);

		String className = JavaCodeGenerator.toPascalCase(cdaTemplate.getName());

		boolean isSingleElementTemplate = (cdaTemplate.getCdaElementList().size() == 1);

		for (CdaElement cdaElement : cdaTemplate.getCdaElementList()) {

			String dataType = adjustDataType(cdaElement.getDataType());
			// TODO: This is a quick fix for CDA-CH-EMED
			if ("hl7:routeCode".equals(cdaElement.getXmlName()))
				dataType = dataType.replace(".CD", ".CE");
			// End of fix

			cdaElement.setDataType(dataType);

			ClassOrInterfaceDeclaration myClass = null;
			Optional<ClassOrInterfaceDeclaration> classOpt = compilationUnit
					.getClassByName(className);
			if (classOpt.isPresent())
				myClass = classOpt.get();
			if (myClass == null)
				myClass = compilationUnit.addClass(className).setPublic(true);
			boolean isCdaRootElement = "org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument"
					.equals(cdaElement.getDataType());

			if (isCdaRootElement) {
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
			if (inheritenceOf != null)
				myClass.setExtendedTypes(new NodeList<ClassOrInterfaceType>(
						new ClassOrInterfaceType(null, inheritenceOf)));

			if (isSingleElementTemplate) {
				createFixedAttributeValues(compilationUnit, myClass, cdaElement);
				for (CdaElement cdaElement1 : cdaElement.getChildrenCdaElementList()) {
					createMembers(compilationUnit, myClass, cdaElement1, (inheritenceOf == null));
					createFixedAttributeValues(compilationUnit, myClass, cdaElement1);
				}
			} else {
				createMembers(compilationUnit, myClass, cdaElement, (inheritenceOf == null));
				createFixedAttributeValues(compilationUnit, myClass, cdaElement);
			}

			if ("org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument"
					.equals(cdaElement.getDataType())) {
				createLoaderMethods(compilationUnit, myClass);
				createSaverMethods(compilationUnit, myClass);
				createInitVersionMethods(compilationUnit, myClass);
			}

			File outFile = new File(fullDstFilePath + className + ".java");
			JavaCodeGenerator.completeAndSave(compilationUnit, outFile);
		}

	}

	private void createMembers(CompilationUnit compilationUnit, ClassOrInterfaceDeclaration myClass,
			CdaElement cdaElement, boolean createOwnProperty) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getXmlName());

		String dataType = adjustDataType(cdaElement.getDataType());
		cdaElement.setDataType(dataType);

		String xmlName = cdaElement.getXmlName();
		String javaName = JavaCodeGenerator.toCamelCase(xmlName);
		cdaElement.setJavaName(javaName);

		boolean elementExist = !(myClass.getMethodsByName(createAdderNamePascalCase(javaName))
				.isEmpty()
				&& myClass.getMethodsByName(createGetterNamePascalCase(javaName)).isEmpty());

		if (!elementExist) {
			if (createOwnProperty)
				createField(compilationUnit, myClass, cdaElement);
			if (cdaElement.getMaxOccurs() > 1) {
				createAdder(compilationUnit, myClass, cdaElement);
				createClearer(compilationUnit, myClass, cdaElement);
			} else {
				createGetter(compilationUnit, myClass, cdaElement);
				createSetter(compilationUnit, myClass, cdaElement);
			}
		}
	}

	public CdaTemplate doOneTemplate(String templateId)
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		CdaTemplate retVal = null;

		// TODO: This is a quick fix for CDA-CH-EMED
		if (templateId.startsWith("1.3.6.1.4.1.19376."))
			return null;

		if (templateIndex.containsKey(templateId)) {
			if (printParsingDebugInformation)
				System.out.println(
						templateId + " is already known as " + templateIndex.get(templateId));
			retVal = templateIndex.get(templateId);
		}

		if (retVal == null) {
			if (initialRun)
				System.out.println("Processing document template: " + templateId);

			templateIndex.put(templateId, runningTemplate);

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
			templateIndex.remove(templateId, runningTemplate);
			templateIndex.put(templateId, currentCdaTemplate);
			retVal = currentCdaTemplate;

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

			// // Create the Java Classes
			// if (initialRun) {
			// regroupTemplateElements(templateList);
			// System.out.println("Writing Java Class files:");
			// for (CdaTemplate cdaTemplate : templateList) {
			// System.out.println("- " + cdaTemplate.getName());
			// createJavaClassFile(cdaTemplate, packageName, fullDstFilePath);
			// }
			// System.out.println(" done.");
			// }
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
		currentCdaAttribute.setCdaTemplate(currentCdaTemplate);

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
			currentCdaTemplate.addCdaAttribute(currentCdaAttribute);
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
		String conformance = null;

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

		ConformanceAttrContext conformanceAttrCtx = ctx.conformanceAttr();
		if (conformanceAttrCtx != null)
			conformance = conformanceAttrCtx.AttrValue().getText().replace("\"", "");

		dataType = adjustDataType(dataType);

		// XPath contitions in the name is not useful for our purposes. Thus we
		// cut it,
		// here...
		if (name.contains("["))
			name = name.substring(0, name.indexOf("["));

		CdaElement cdaElement = new CdaElement(null);
		cdaElement.setTemplate(currentCdaTemplate);

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

		boolean isTemplateElement = (processingElement == 0);
		boolean isChildElement = (processingElement > processingElementPrev);
		boolean isSiblingElement = (processingElement == processingElementPrev);
		boolean isParentElement = (processingElement < processingElementPrev);
		int levels = 0;
		if (isParentElement)
			levels = processingElementPrev - processingElement;
		processingElementPrev = processingElement;
		processingElement++;

		// Do not generate elements that are not permitted
		if ("NP".equals(conformance))
			return;

		parentForContains = cdaElement;
		CdaElement parentCdaElement = null;
		if ((callingCdaElement == null) && (currentCdaElement == null)) {
			// This is the root element (the root element of the
			// calling template)
			parentCdaElement = null;
			// currentCdaTemplate.addCdaElement(cdaElement);
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
			// currentCdaTemplate.addCdaElement(cdaElement);
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
				parentForIncludes = parentCdaElement;
			} else if (isSiblingElement) {
				parentCdaElement = currentCdaElement.getParentCdaElement();
				if (isTemplateElement)
					parentForIncludes = cdaElement;
				else
					parentForIncludes = parentCdaElement;
			} else if (isParentElement) {
				parentCdaElement = currentCdaElement.getParentCdaElement();
				for (int i = 0; i < levels; i++) {
					parentCdaElement = parentCdaElement.getParentCdaElement();
				}
				parentForIncludes = parentCdaElement;
			} else
				throw new RuntimeException(
						"element '" + name + "' is neither child, sibling, parent");

			if (parentCdaElement == null) {
				parentCdaElement = callingCdaElement;
				if (parentCdaElement != currentCdaElement)
					currentCdaElement.setParentCdaElement(parentCdaElement);
			}
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
		}

		if (parentCdaElement != null) {
			cdaElement.setParentCdaElement(parentCdaElement);
		}

		if (isTemplateElement)
			currentCdaTemplate.addCdaElement(cdaElement);
		else
			parentCdaElement.addChild(cdaElement);

		currentCdaElement = cdaElement;

		boolean isCdaRootElement = "org.ehealth_connector.common.hl7cdar2.POCDMT000040ClinicalDocument"
				.equals(cdaElement.getDataType());
		if (!(isCdaRootElement) && currentCdaTemplate.getCdaElementList().size() > 1)
			currentCdaTemplate.setDataType(null);

		ContainsAttrContext containsAttrCtx = ctx.containsAttr();
		String ref = "";
		if (containsAttrCtx != null) {
			ref = containsAttrCtx.AttrValue().getText().replace("\"", "");
			if (printParsingDebugInformation)
				System.out.println(cdaElement.getJavaName() + " contains " + ref);
			try {
				if (parentForContains == null)
					throw new RuntimeException("parent is null for contains " + ref);
				// Process contains
				ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
						parentForContains, templateIndex, valueSetIndex, templateList, srcFilePath,
						dstFilePath, packageName, fileHeader, artDecorPrefix);
				CdaTemplate template = artDecor2JavaGenerator.doOneTemplate(ref);
				currentCdaElement.addCdaTemplate(template, ProcessModes.CONTAINS);

				// not sure, if this is really needed. if not, remove also the
				// template hash map property in CDATemplate
				currentCdaTemplate.addCdaTemplate(template, ProcessModes.CONTAINS);

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

		RefAttrContext refAttrCtx = ctx.refAttr();
		String ref = "";
		if (refAttrCtx != null) {
			ref = refAttrCtx.AttrValue().getText().replace("\"", "");
			boolean isParentElement = (processingElement < processingElementPrev);
			boolean isChildElement = (processingElement > processingElementPrev);
			boolean isSiblingElement = (processingElement == processingElementPrev);
			int levels = 0;
			if (isParentElement) {
				levels = processingElementPrev - processingElement;
				parentForIncludes = currentCdaElement.getParentCdaElement();
				for (int i = 0; i < levels; i++) {
					parentForIncludes = parentForIncludes.getParentCdaElement();
				}
			} else if (isSiblingElement && (callingCdaElement != null)) {
				if (processingElement == 0) {
					processingRootInclude = true;
					parentForIncludes = callingCdaElement;
					currentCdaElement = new CdaElement(callingCdaElement);
				}
			}

			if (printParsingDebugInformation)
				if (printParsingDebugInformation)
					System.out.println("Include: " + ref);
			try {
				// Process includes
				ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
						parentForIncludes, templateIndex, valueSetIndex, templateList, srcFilePath,
						dstFilePath, packageName, fileHeader, artDecorPrefix);
				CdaTemplate template = artDecor2JavaGenerator.doOneTemplate(ref);

				if (isChildElement)
					currentCdaElement.addCdaTemplate(template, ProcessModes.INCLUDE);
				else if (isSiblingElement)
					currentCdaElement.getParentCdaElement().addCdaTemplate(template,
							ProcessModes.INCLUDE);
				else if (isParentElement)
					currentCdaElement.getParentCdaElement().getParentCdaElement()
							.addCdaTemplate(template, ProcessModes.INCLUDE);
				else
					System.out.println("Stop here - no child, noc sibling, no parent - what else?");

				// not sure, if this is really needed. if not, remove also the
				// template hash map property in CDATemplate
				currentCdaTemplate.addCdaTemplate(template, ProcessModes.INCLUDE);

			} catch (SaxonApiException | IOException | JAXBException | ClassNotFoundException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchFieldException | SecurityException e) {
				throw new RuntimeException(
						"Code generation of include (" + ref + " failed:" + e.getMessage());
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
	public void enterVocab(Hl7ItsParser.VocabContext ctx) {

		String code = null;
		CodeAttrContext codeAttrCtx = ctx.codeAttr();
		if (codeAttrCtx != null)
			code = codeAttrCtx.AttrValue().getText().replace("\"", "");

		String codeSystem = null;
		CodeSystemAttrContext codeSystemAttrCtx = ctx.codeSystemAttr();
		if (codeSystemAttrCtx != null)
			codeSystem = codeSystemAttrCtx.AttrValue().getText().replace("\"", "");

		String valueSetId = null;
		ValueSetAttrContext valueSetAttrCtx = ctx.valueSetAttr();
		if (valueSetAttrCtx != null)
			valueSetId = valueSetAttrCtx.AttrValue().getText().replace("\"", "");

		String flexibility = null;
		FlexibilityAttrContext flexibilityAttrCtx = ctx.flexibilityAttr();
		if (flexibilityAttrCtx != null)
			flexibility = flexibilityAttrCtx.AttrValue().getText().replace("\"", "");
		if ((code != null) || (codeSystem != null) || (valueSetId != null)) {
			if (currentCdaAttribute == null) {
				currentCdaAttribute = new CdaAttribute();
				currentCdaAttribute.setCdaElement(currentCdaElement);
				currentCdaAttribute.setDataType("cs");
			}
			currentCdaAttribute.setVocab(true);
			if (code != null || codeSystem != null) {
				CodeBaseType codeBt = new CodeBaseType();
				codeBt.setCode(code);
				codeBt.setCodeSystem(codeSystem);
				currentCdaAttribute.setCode(new Code(codeBt));
			}

			if (code != null) {
				if (currentCdaAttribute.getName() == null)
					currentCdaAttribute.setName("code");
				currentCdaAttribute.setValue(code);

			} else if (valueSetId != null) {
				if (currentCdaAttribute.getName() == null)
					currentCdaAttribute.setName("valueSet");
				currentCdaAttribute.setValueSetId(valueSetId);

				if (!skipValueSetGeneration && !valueSetIndex.containsKey(valueSetId)) {
					System.out.print("- downloading ValueSet " + valueSetId + " ...");

					String sourceUrl = "http://art-decor.org/decor/services/RetrieveValueSet?prefix="
							+ artDecorPrefix + "&id=" + valueSetId + "&format=json";
					if (!"dynamic".equals(flexibility) && flexibility != null)
						try {
							System.out.println("Stop here - static flexibility");
							sourceUrl += "&effectiveDate=" + java.net.URLEncoder.encode(flexibility,
									Charsets.UTF_8.toString());
						} catch (UnsupportedEncodingException e) {
							throw new RuntimeException("flexibility (" + flexibility
									+ ") cannot be URL encoded: " + e.getMessage());
						}
					ValueSetManager valueSetManager = new ValueSetManager();
					ValueSetConfig valueSetConfig = ValueSetConfig.builder()
							.withProjectFolder(this.dstFilePath)
							.withSourceFormatType(SourceFormatType.JSON)
							.withSourceSystemType(SourceSystemType.ARTDECOR_FHIR)
							.withSourceUrl(sourceUrl).build();
					ValueSet valueSet = null;
					try {
						valueSet = valueSetManager.downloadValueSet(valueSetConfig);
					} catch (IOException | ParserConfigurationException | SAXException
							| InitializationException e) {
						throw new RuntimeException("valueSet (" + valueSetId
								+ ") cannot be downloaded: " + e.getMessage());
					}
					if (valueSet != null) {
						System.out.print(" creating class file ...");
						String fullValueSetClassName = packageName + ".enums."
								+ JavaCodeGenerator.toPascalCase(valueSet.getName());
						valueSetIndex.put(valueSetId, fullValueSetClassName);
						valueSetConfig.setClassName(fullValueSetClassName);
						String baseJavaFolder = valueSetConfig.getProjectFolder();
						String fullyQualifiedclassName = valueSetConfig.getClassName();
						// delete existing class file
						ValueSetUtil.getSourceFileName(baseJavaFolder, fullyQualifiedclassName)
								.delete();

						// create the class file
						try {
							UpdateValueSets.createEnumClassFromTemplate(baseJavaFolder,
									fullyQualifiedclassName);
							UpdateValueSets.updateEnumClass(valueSet.getIdentificator().getRoot(),
									valueSet.getName(), baseJavaFolder,
									valueSetConfig.getClassName(), valueSet);
						} catch (IOException e) {
							throw new RuntimeException("valueSet (" + valueSetId
									+ ") cannot be created as Java enum file: " + e.getMessage());
						}

					}
					System.out.print(" done.\n");
				}
			}
			if (currentCdaElement == null)
				// an attribute on top level of a template without root element
				// belongs to the calling element
				callingCdaElement.addAttribute(currentCdaAttribute);
			else
				currentCdaElement.addAttribute(currentCdaAttribute);

			if (printParsingDebugInformation)
				System.out.println("Attribute from vocab Element: " + currentCdaAttribute.getName()
						+ "=" + currentCdaAttribute.getValue() + " (dataType: "
						+ currentCdaAttribute.getDataType() + ")");
		}
	}

	@Override
	public void exitAttribute(Hl7ItsParser.AttributeContext ctx) {
		processingAttribute--;
	}

	@Override
	public void exitElement(Hl7ItsParser.ElementContext ctx) {
		currentCdaAttribute = null;
		processingElement--;
	}

	@Override
	public void exitInclude(Hl7ItsParser.IncludeContext ctx) {
		if (processingRootInclude) {
			parentForIncludes = null;
			currentCdaElement = currentCdaElement.getParentCdaElement();
		}
		processingRootInclude = false;
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
				System.out
						.println("Datatype for "
								+ cdaElement.getFullXmlName().replace("hl7:", "")
										.replace("pharm:", "").replace("xsi:", "")
								+ " --> " + dataType);
			for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
				fillDatatypesRecursive(item);
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	private String getDataType(CdaElement cdaElement, HashMap<String, CdaTemplate> templateIndex)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException,
			SecurityException, IOException {
		String retVal = null;
		String cdaElementName = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");

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
				while ((retVal == null) && (parentType != null)
						&& (!"java.lang.Object".equals(parentType))) {
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
				if (prop.equals(cdaElementName.replace("hl7:", "").replace("pharm:", "")
						.replace("xsi:", ""))) {
					String expectedMethodName = createGetterNamePascalCase(prop);
					for (Method method : cl.getMethods()) {
						if (method.getName().equals(expectedMethodName)) {
							String returnType = method.getReturnType().getName();
							if ("java.util.List".equals(returnType)
									|| "javax.xml.bind.JAXBElement".equals(returnType)) {
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
		if (retVal == null) {
			// Check attributes
			for (Field f : cl.getDeclaredFields()) {
				XmlAttribute xmlAttribute = (XmlAttribute) f.getAnnotation(XmlAttribute.class);
				if (xmlAttribute != null) {
					if (cdaElementName.contentEquals(xmlAttribute.name()))
						retVal = f.getType().getName();
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

	public void prepareForAnotherTemplate(String srcFilePath) {
		this.callingCdaElement = null;
		this.currentCdaAttribute = null;
		this.currentCdaElement = null;
		this.currentCdaTemplate = null;
		this.mainCdaTemplate = null;
		this.parentForContains = null;
		this.parentForIncludes = null;

		this.srcFilePath = srcFilePath;

	}

	private void regroupTemplateElements(ArrayList<CdaTemplate> myTemplateList) {
		for (CdaTemplate cdaTemplate : myTemplateList) {
			// System.out.println("Processing " + cdaTemplate.getName());
			for (CdaElement cdaElement : cdaTemplate.getCdaElementList()) {
				// System.out.println(" " + cdaElement.getXmlName());
				for (CdaTemplate cdaTemplate2 : cdaElement.getCdaTemplateList().keySet()) {
					ProcessModes mode = cdaElement.getCdaTemplateList().get(cdaTemplate2);
					// System.out.println(" " + mode + " " +
					// cdaTemplate2.getName());
					if (mode != ProcessModes.CONTAINS) {
						for (CdaElement cdaElement2 : cdaTemplate2.getCdaElementList()) {
							// System.out.println("Adding " +
							// cdaElement2.getXmlName());
							cdaElement.addChild(cdaElement2);
						}
					}
				}
			}
		}

	}

	private void updateCreatorForFixedContentsMethod(CompilationUnit compilationUnit,
			MethodDeclaration setterForFixedContentsMethod, CdaElement cdaElement,
			CdaAttribute cdaAttribute) {

		String attrName = cdaAttribute.getName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");

		Optional<Parameter> params = setterForFixedContentsMethod.getParameterByName(attrName);

		if (!params.isPresent()) {
			setterForFixedContentsMethod.addAndGetParameter("String", attrName);
			Optional<Javadoc> javaDocOpt = setterForFixedContentsMethod.getJavadoc();
			Javadoc javadoc = javaDocOpt.get();
			javadoc.addBlockTag("param",
					cdaAttribute.getName() + " the desired fixed value for this argument.");
			setterForFixedContentsMethod.setJavadocComment(javadoc);

			Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
			if (bodyOpt.isPresent()) {
				BlockStmt body = bodyOpt.get();

				String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
						.replace("xsi:", "");

				@SuppressWarnings("rawtypes")
				Class memberType = getDeclaredFieldDatatype(cdaElement.getDataType(), attrName);
				if (memberType == null)
					memberType = getDeclaredFieldDatatype(
							cdaElement.getParentCdaElement().getDataType(), name);

				String valueSetId = null;
				for (CdaAttribute attr : cdaElement.getCdaAttributeList()) {
					valueSetId = attr.getValueSetId();
					if (valueSetId != null)
						break;
				}

				if ((memberType == null) && (!"translation".equals(name))) {
					if (cdaElement.getDataType().endsWith(".ENXP")) {
						String enPartL = attrName;
						String enPartU = toUpperFirstChar(enPartL);
						body.addStatement("retVal.get" + enPartU + "().add(" + enPartL + ");");
					} else
						throw new RuntimeException(
								name + " is neither an accesible field nor an accessible getter");
				} else {
					if ("nullFlavor".equals(attrName)) {
						addImport(compilationUnit, "java.util.ArrayList");
						body.addStatement("retVal.nullFlavor = new ArrayList<String>();");
						body.addStatement("retVal.nullFlavor.add(nullFlavor);");
					} else if (isClassCollection(memberType)) {
						String temp = cdaAttribute.getName().replace("hl7:", "")
								.replace("pharm:", "").replace("xsi:", "");
						body.addStatement(
								"retVal.get" + toUpperFirstChar(temp) + "().add(" + temp + ");");
					} else {

						if ("java.lang.String".contentEquals(memberType.getName()))
							body.addStatement("retVal.set" + toUpperFirstChar(attrName) + "("
									+ attrName + ");");
						else if ("java.lang.Boolean".contentEquals(memberType.getName()))
							body.addStatement("retVal.set" + toUpperFirstChar(attrName)
									+ "(Boolean.parseBoolean(" + attrName + "));");
						else {
							String enumName = memberType.getName();
							if (valueSetId != null) {
								enumName = adjustValueSet(valueSetIndex.get(valueSetId));
								addBodyComment(setterForFixedContentsMethod,
										"TODO: Contents shall be taken from enum: " + enumName);
							} else {
								body.addStatement("retVal.set" + toUpperFirstChar(attrName) + "("
										+ enumName + ".fromValue(" + attrName + "));");

							}
						}
					}
				}
			}
		}
	}

}
