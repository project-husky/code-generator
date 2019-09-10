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
import java.net.URL;
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
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.CodeSystemNameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ConformanceAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ContainsAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.DataTypeAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.DisplayNameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.FlexibilityAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.IdAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MaxOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MinOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.NameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.RefAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueSetAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.ehealth_connector.codegenerator.cda.config.ContentProfileConfig;
import org.ehealth_connector.codegenerator.cda.config.ContentProfilePackageConfig;
import org.ehealth_connector.codegenerator.cda.enums.ProcessModes;
import org.ehealth_connector.codegenerator.cda.model.CdaAttribute;
import org.ehealth_connector.codegenerator.cda.model.CdaElement;
import org.ehealth_connector.codegenerator.cda.model.CdaTemplate;
import org.ehealth_connector.codegenerator.cda.rest.ArtDecorRestClient;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.javadoc.Javadoc;

import net.sf.saxon.s9api.SaxonApiException;

/**
 * <div class="en">This is the main class of the ART-DECOR to Java Code
 * Generator. It orchestrates the individual modules (such as REST Client to
 * ART-DECOR, XSLT, ANTLR and the final Java Class file creation). This class
 * generates Java Classes for all templates used for a HL7 CDA Document Template
 * modeled in ART-DECOR.</div>
 *
 * <div class="de">Dies ist die Hauptklasse des ART-DECOR to Java Code
 * Generators. Sie orchestriert die einzelnen Module (z. B. REST-Client to
 * ART-DECOR, XSLT, ANTLR und die endgültige Erstellung der
 * Java-Klassendateien). Diese Klasse generiert Java-Klassen für alle HL7 CDA
 * Document Templates, die in ART-DECOR modelliert sind.</div>
 *
 */
public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

	/** The data type index. */
	private static HashMap<String, String> dataTypeIndex = null;

	/**
	 * Settings for the Java Code Formatter application. It contains the same
	 * settings as the formatter XML, just in another file format. Best way is
	 * to export the formatter from eclipse preferences, remove the XML stuff
	 * and put it in the prefs file.
	 */
	private static final String FORMATTER_PREFS = System.getProperty("user.dir")
			+ "/src/main/resources/format/org.eclipse.jdt.core.prefs";

	/** Placeholder for pending actions of the Code Generator. */
	private static String PENDING_ACTIONS = "Pending actions:";

	/** Placeholder for pending actions of the Code Generator. */
	private static String PENDING_ACTIONS_ADJUST_NAME = "Adjust name: ";

	/** The log. */
	protected final static Logger log = LoggerFactory.getLogger(ArtDecor2JavaGenerator.class);

	/**
	 * <div class="en">The main entry for the ART-DECOR to Java Code
	 * Generator.</div>
	 *
	 * <div class="de">Hauptzugang zum ART-DECOR to Java Code Generator.</div>
	 *
	 * @param args
	 *            <pre>
	 *            command line arguments:
	 *            1. Full path and filename to eclipse
	 *            2. Full path to the workspace application
	 *            </pre>
	 *
	 * @throws Exception
	 *             When any operation fails.
	 */
	public static void main(String[] args) throws Exception {

		log.debug("ArtDecor2JavaGenerator started");
		System.out.print("===== ART-DECOR to Java Generator started =====\n");

		File eclipseApp = null;
		File orgWorkspacePath = null;
		File configFile = null;

		boolean argsOk = false;

		if (args.clone().length >= 3) {
			argsOk = true;
			final String eclipseApplicationPath = args[0].toString();
			if (eclipseApplicationPath != null) {
				eclipseApp = new File(eclipseApplicationPath);
				if (!eclipseApp.exists()) {
					System.out.println("ERROR: Eclipse application does not exist ("
							+ eclipseApplicationPath + ")");
					argsOk = false;
				} else {
					if (!eclipseApp.isFile()) {
						System.out.println("ERROR: Eclipse application is not a file ("
								+ eclipseApplicationPath + ")");
						argsOk = false;
					}
				}
			}

			final String orgWorkspacePathString = args[1].toString();
			if (orgWorkspacePathString != null) {
				orgWorkspacePath = new File(orgWorkspacePathString);
				if (!orgWorkspacePath.exists()) {
					System.out.println(
							"ERROR: Workspace does not exist (" + orgWorkspacePathString + ")");
					argsOk = false;
				} else {
					if (!orgWorkspacePath.isDirectory()) {
						System.out.println("ERROR: Workspace is not a directory ("
								+ orgWorkspacePathString + ")");
						argsOk = false;
					}
				}
			}

			final String orgConfigPathString = args[2].toString();
			if (orgConfigPathString != null) {
				configFile = new File(orgConfigPathString);
				if (!configFile.exists()) {
					System.out.println(
							"ERROR: Config File does not exist (" + orgConfigPathString + ")");
					argsOk = false;
				} else {
					if (!configFile.isFile()) {
						System.out.println(
								"ERROR: Config is not a file (" + orgConfigPathString + ")");
						argsOk = false;
					}
				}
			}

		} else {
			System.out.println("Usage:");
			System.out.println("");
			System.out.println("ArtDecor2JavaGenerator <eclipse> <workspace> <config>");
			System.out.println("");
			System.out.println(
					"  eclipse:   First parameter must be the full path and filename of your eclipse application");
			System.out.println(
					"             (e.g. C:\\JavaProgramme\\eclipse\\rcp-2019-06\\eclipse\\eclipse.exe");
			System.out.println("");
			System.out.println(
					"  workspace: Second parameter must be the full path to your current workspace directory");
			System.out.println(
					"             Note: It will be copied into a temp folder, as the current one is in use by Eclipse IDE");
			System.out.println("");
			System.out.println(
					"  config: Third parameter must be the full path to your configuration file");

			argsOk = false;
		}

		if (!argsOk) {
			System.out.println("");
			System.out.println("***");
			System.out.println("Try again :-)");
			return;
		}

		final String tempWorkspacePathString = Util.getTempDirectory()
				+ FileUtil.getPlatformSpecificPathSeparator() + "tmpWS_"
				+ orgWorkspacePath.getName();
		final File tempWorkspacePath = new File(tempWorkspacePathString);

		final String tempDownloadPathString = Util.getTempDirectory()
				+ FileUtil.getPlatformSpecificPathSeparator() + "eHC_Arde_Download"
				+ FileUtil.getPlatformSpecificPathSeparator();
		final File tempDownloadPath = new File(tempDownloadPathString);

		FileUtils.deleteDirectory(tempWorkspacePath);
		FileUtils.deleteDirectory(tempDownloadPath);
		FileUtils.copyDirectoryToDirectory(orgWorkspacePath, tempWorkspacePath);

		System.out.println("----------------------------------------");
		System.out.println("Settings");
		System.out.println("----------------------------------------");
		System.out.println("Eclipse runtime: " + eclipseApp.getAbsolutePath());
		System.out.println("Temp. workspace: " + tempWorkspacePath.getAbsolutePath());
		System.out.println("Config file: " + configFile.getAbsolutePath());
		System.out.println();

		// Load Config
		System.out.println("----------------------------------------");
		System.out.println("Configuration");
		System.out.println("----------------------------------------");
		System.out.print("Loading configuration...");
		ArtDecor2JavaManager artDecor2JavaManager2 = new ArtDecor2JavaManager();
		ContentProfilePackageConfig contentProfilePackageConfig = artDecor2JavaManager2
				.loadContentProfilePackageConfig(configFile.getAbsolutePath());
		System.out.println("done");

		System.out.println("Loaded configuration:");
		for (ContentProfileConfig contentProfile : contentProfilePackageConfig
				.getContentProfileConfigList()) {
			System.out.println("- Target namespace: " + contentProfile.getTargetNamespace());
			for (String templateId : contentProfile.getArtDecorDocTemplateMap().keySet()) {
				System.out.println("  - template id: " + templateId);
			}
		}
		System.out.println("Configuration done");
		System.out.println();

		// Perform REST calls
		System.out.println("----------------------------------------");
		System.out.println("Download from ART-DECOR");
		System.out.println("----------------------------------------");
		for (ContentProfileConfig contentProfile : contentProfilePackageConfig
				.getContentProfileConfigList()) {
			String dir = tempDownloadPath.getAbsolutePath()
					+ FileUtil.getPlatformSpecificPathSeparator()
					+ contentProfile.getTargetNamespace()
					+ FileUtil.getPlatformSpecificPathSeparator();
			ArtDecorRestClient artDecorRestClient = new ArtDecorRestClient(
					contentProfile.getArtDecorProjectMap(), dir);

			for (String templateId : contentProfile.getArtDecorDocTemplateMap().keySet()) {
				String effectiveTime = contentProfile.getArtDecorDocTemplateMap().get(templateId);
				artDecorRestClient.downloadTemplateRecursive(templateId, effectiveTime);
			}
		}
		System.out.println("Download from ART-DECOR done");
		System.out.println();

		// Perform Java code generation
		System.out.println("----------------------------------------");
		System.out.println("Perform Java code generation");
		System.out.println("----------------------------------------");
		System.out.println("Java code generation done");
		System.out.println();
		ArrayList<String> dstPathList = new ArrayList<String>();
		for (ContentProfileConfig contentProfile : contentProfilePackageConfig
				.getContentProfileConfigList()) {
			String srcFilePath = tempDownloadPath.getAbsolutePath()
					+ FileUtil.getPlatformSpecificPathSeparator()
					+ contentProfile.getTargetNamespace()
					+ FileUtil.getPlatformSpecificPathSeparator();
			HashMap<String, CdaTemplate> templateIndex = new HashMap<String, CdaTemplate>();
			HashMap<String, String> valueSetIndex = new HashMap<String, String>();
			ArrayList<CdaTemplate> templateList = new ArrayList<CdaTemplate>();
			String dstFilePath = contentProfile.getTargetDir();
			if (!(dstFilePath.startsWith("/") || dstFilePath.startsWith("\\")
					|| ":".equals(dstFilePath.subSequence(2, 3)))) {
				// is not an absolute path, so we adjust the relative path
				dstFilePath = ".." + FileUtil.getPlatformSpecificPathSeparator() + ".."
						+ FileUtil.getPlatformSpecificPathSeparator() + dstFilePath;
			}
			if (!dstFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
				dstFilePath += FileUtil.getPlatformSpecificPathSeparator();

			String prefix = contentProfile.getArtDecorMainPrefix();
			URL url = new URL(contentProfile.getArtDecorMainBaseUrl());
			ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(null,
					templateIndex, valueSetIndex, templateList, srcFilePath, dstFilePath,
					contentProfile.getTargetNamespace(), JavaCodeGenerator.getFileHeader(), prefix,
					url);
			for (String templateId : contentProfile.getArtDecorDocTemplateMap().keySet()) {
				artDecor2JavaGenerator.prepareForAnotherTemplate();
				artDecor2JavaGenerator.doOneTemplate(templateId);
			}
			artDecor2JavaGenerator.createJavaClasses();
			if (!dstPathList.contains(artDecor2JavaGenerator.getFullDstFilePath()))
				dstPathList.add(artDecor2JavaGenerator.getFullDstFilePath());
			if (!dstPathList.contains(artDecor2JavaGenerator.getFullDstFilePath()
					+ FileUtil.getPlatformSpecificPathSeparator() + "enums"))
				dstPathList.add(artDecor2JavaGenerator.getFullDstFilePath() + "enums"
						+ FileUtil.getPlatformSpecificPathSeparator());
		}

		// Apply formatter
		System.out.println("----------------------------------------");
		System.out.println("Applying formatter");
		System.out.println("----------------------------------------");
		for (String path : dstPathList) {
			try {
				Util.runExternalCommand(
						eclipseApp + " -application org.eclipse.jdt.core.JavaCodeFormatter -data "
								+ tempWorkspacePath + " -config " + FORMATTER_PREFS + " " + path);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Formatter applied");
		System.out.println();

		FileUtils.deleteDirectory(tempWorkspacePath);
		FileUtils.deleteDirectory(tempDownloadPath);

		System.out.println("Generated Java classes and enums in the following folders:");
		for (String path : dstPathList) {
			System.out.println("- " + path);
		}
		System.out.println();

		log.debug("ArtDecor2JavaGenerator finished");
		System.out.print("===== ART-DECOR to Java Generator finished =====\n");

	}

	/**
	 * Adds a comment to the constructor body.
	 *
	 * @param constructor
	 *            the constructor
	 * @param comment
	 *            the comment
	 */
	private static void addBodyComment(ConstructorDeclaration constructor, String comment) {
		BlockStmt body = constructor.getBody();
		LineComment c = new LineComment(comment);
		body.addOrphanComment(c);
	}

	/**
	 * Adds a comment to the method body.
	 *
	 * @param method
	 *            the method
	 * @param comment
	 *            the comment
	 */
	private static void addBodyComment(MethodDeclaration method, String comment) {
		Optional<BlockStmt> bodyOpt = method.getBody();
		if (bodyOpt.isPresent()) {
			LineComment c = new LineComment(comment);
			bodyOpt.get().addOrphanComment(c);
		}
	}

	/**
	 * Adds a statement to the constructor body.
	 *
	 * @param constructor
	 *            the constructor
	 * @param statement
	 *            the statement
	 */
	private static void addBodyStatement(ConstructorDeclaration constructor, String statement) {
		BlockStmt body = constructor.getBody();
		body.addStatement(statement);

	}

	/**
	 * Adds an import statement to the compilation unit.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param value
	 *            the value
	 */
	private static void addImport(CompilationUnit compilationUnit, String value) {
		boolean isAlreadyThere = false;
		for (ImportDeclaration item : compilationUnit.getImports()) {
			isAlreadyThere = (item.getNameAsString().equals(value));
			if (isAlreadyThere)
				break;
		}
		if (!isAlreadyThere)
			compilationUnit.addImport(value);
	}

	/**
	 * Completes the creator method for fixed contents.
	 *
	 * @param creatorForFixedContentsMethod
	 *            the creator for fixed contents method
	 * @param cdaElement
	 *            the cda element
	 */
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

	/**
	 * Creates the method for adding the given element to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 */
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

	/**
	 * Creates the name for the method for adding the given element to the
	 * resulting class.
	 *
	 * @param name
	 *            the name
	 * @return the string
	 */
	private static String createAdderNamePascalCase(String name) {
		return "add" + JavaCodeGenerator.toPascalCase(name);
	}

	/**
	 * Creates the method for clearing the list of the given element to the
	 * resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 */
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

	/**
	 * Creates the default constructor to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @return the constructor declaration
	 */
	private static ConstructorDeclaration createDefaultConstructor(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {
		return myClass.addConstructor(publicModifier().getKeyword());
	}

	/**
	 * Creates the field for the given element to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 */
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

	/**
	 * Creates the method for getting the given element to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 */
	private static void createGetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		String dataType = cdaElement.getDataType();
		if ("hl7:typeId".equals(cdaElement.getXmlName())) {
			dataType = "org.ehealth_connector.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
		}

		String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");

		CdaElement elForType = cdaElement.getParentCdaElement();
		if (elForType == null)
			elForType = cdaElement;
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
		if (isClassCollection(memberType)) {

			boolean isLocalField = (myClass.getExtendedTypes().size() == 0);

			if ("effectiveTime".equals(name) && !isLocalField) {
				dataType = "org.ehealth_connector.common.hl7cdar2.SXCMTS";
			}
			if ("value".equals(name)) {
				dataType = "org.ehealth_connector.common.hl7cdar2.ANY";
			}
			addImport(compilationUnit, "java.util.List");
			dataType = "java.util.List<" + dataType + ">";
		}

		String baseName = cdaElement.getJavaName();
		List<MethodDeclaration> existingMethods = myClass
				.getMethodsByName(createGetterNamePascalCase(baseName));
		if (existingMethods.size() > 0) {
			String methodName = existingMethods.get(0).getNameAsString();
			String methodDataType = existingMethods.get(0).getType().asString();
			methodName = methodName
					+ JavaCodeGenerator.toPascalCase(getClassWithoutPackage(methodDataType));
			// prepare adjust name of first method
			if (!existBodyComment(existingMethods.get(0), PENDING_ACTIONS)) {
				addBodyComment(existingMethods.get(0), PENDING_ACTIONS);
				addBodyComment(existingMethods.get(0), PENDING_ACTIONS_ADJUST_NAME + methodName);
			}

			// adjust name of new method
			baseName = baseName + JavaCodeGenerator.toPascalCase(getClassWithoutPackage(dataType));
		}

		if (existingMethods.size() == 0) {
			method = myClass.addMethod(createGetterNamePascalCase(baseName),
					publicModifier().getKeyword());
			method.setType(dataType);

			String comment = "Gets the " + cdaElement.getJavaName();
			String desc = cdaElement.getDescription();
			if (desc != null)
				comment += Util.getPlatformSpecificLineBreak() + desc;

			method.setJavadocComment(comment);

			BlockStmt body = method.createBody();
			String genericType = getGenericFieldType(elForType.getDataType(), name);
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

					// body.addStatement(cdaElement.getDataType() + " retVal =
					// null;");
					// body.addStatement("if (" + temp + " != null) if (" + temp
					// + ".size() > 0) retVal = " + cast + temp + ".get(0);");
					// body.addStatement("return retVal;");
					body.addStatement("return " + name + ";");
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
	}

	/**
	 * Creates the name for the method for getting the given element to the
	 * resulting class.
	 *
	 * @param name
	 *            the name
	 * @return the string
	 */
	private static String createGetterNamePascalCase(String name) {
		return "get" + JavaCodeGenerator.toPascalCase(name);
	}

	/**
	 * Creates the name for the method for getting the given element to the
	 * resulting class.
	 *
	 * @param name
	 *            the name
	 * @return the string
	 */
	private static String createGetterNameUpperFirstChar(String name) {
		return "get" + toUpperFirstChar(name);
	}

	/**
	 * Creates the methods initFirstVersion, initNextVersion and setVersion and
	 * to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 */
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

	/**
	 * Creates the loadFromFile methods to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 */
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

	/**
	 * Creates the member and getter for the given element to the resulting
	 * class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param dataType
	 *            the data type
	 * @param memberName
	 *            the member name
	 * @param methodName
	 *            the method name
	 */
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

	/**
	 * Creates the saveToFile methods to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 */
	private static void createSaverMethods(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass) {

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

	/**
	 * Creates the setter for the given element to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 */
	private static void createSetter(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

		MethodDeclaration method;

		method = myClass.addMethod(createSetterNamePascalCase(cdaElement.getJavaName()),
				publicModifier().getKeyword());

		String dataType = cdaElement.getDataType();
		if ("hl7:typeId".equals(cdaElement.getXmlName())) {
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
					} else {
						addBodyComment(method,
								memberType.getName() + " cannot be cast to " + dataType);
						System.out.println("\nERROR: " + cdaElement.getFullXmlName() + " :"
								+ memberType.getName() + " cannot be cast to " + dataType);
					}
				} else
					body.addStatement("this." + temp + " = " + cast + "value;");
			}
		} else
			addBodyComment(method, "TODO: NYI");
	}

	/**
	 * Creates the name for the setter of the given element.
	 *
	 * @param name
	 *            the name
	 * @return the string
	 */
	private static String createSetterNamePascalCase(String name) {
		return "set" + JavaCodeGenerator.toPascalCase(name);
	}

	/**
	 * Creates an XmlElementName annotation.
	 *
	 * Example:
	 *
	 * (at)XmlElement(name = "hl7:author")
	 *
	 * @param nameValue
	 *            the name value
	 * @return the normal annotation expr
	 */
	@SuppressWarnings("unused")
	private static NormalAnnotationExpr createXmlElementNameAnnotation(String nameValue) {
		NormalAnnotationExpr retVal = new NormalAnnotationExpr();
		retVal.setName(new Name("XmlElement"));
		retVal.addPair("name", "\"" + nameValue + "\"");
		return retVal;
	}

	/**
	 * Creates an XmlRootElement annotation.
	 *
	 * Examples:
	 *
	 * (at)XmlRootElement(name = "ClinicalDocument", namespace =
	 * "urn:hl7-org:v3")
	 *
	 * @param nameValue
	 *            the name value
	 * @return the normal annotation expr
	 */
	private static NormalAnnotationExpr createXmlRootElementAnnotation(String nameValue) {
		NormalAnnotationExpr retVal = new NormalAnnotationExpr();
		retVal.setName(new Name("XmlRootElement"));
		retVal.addPair("name", "\"" + nameValue + "\"");
		retVal.addPair("namespace", "\"urn:hl7-org:v3\"");
		return retVal;
	}

	/**
	 * Creates an XmlTransient annotation.
	 *
	 * @return the normal annotation expr
	 */
	private static NormalAnnotationExpr createXmlTransientAnnotation() {
		NormalAnnotationExpr retVal = new NormalAnnotationExpr();
		retVal.setName(new Name("XmlTransient"));
		return retVal;
	}

	/**
	 * Checks whether the given body comment already exists in the resulting
	 * class.
	 *
	 * @param method
	 *            the method
	 * @param comment
	 *            the comment
	 * @return true, if successful
	 */
	private static boolean existBodyComment(MethodDeclaration method, String comment) {
		boolean retVal = false;
		Optional<BlockStmt> bodyOpt = method.getBody();
		if (bodyOpt.isPresent()) {
			for (Comment c : bodyOpt.get().getOrphanComments()) {
				retVal = (c.asLineComment().getContent().equals(comment));
				if (retVal)
					break;
			}
		}
		return retVal;
	}

	/**
	 * Gets the class by the given name.
	 *
	 * @param className
	 *            the class name
	 * @return the class
	 */
	@SuppressWarnings("rawtypes")
	private static Class getClass(String className) {
		Class retVal = null;

		try {
			retVal = Class.forName(className);

		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	/**
	 * Gets the pure class name (without package name) of the class identified
	 * by the given name.
	 *
	 * @param className
	 *            the class name
	 * @return the class without package
	 */
	private static String getClassWithoutPackage(String className) {
		String retVal = className;
		while (retVal.contains(".")) {
			retVal = retVal.substring(retVal.indexOf(".") + 1, retVal.length());
		}
		return retVal;
	}

	/**
	 * Gets the declared field data type of the given member in the given class.
	 *
	 * @param myClass
	 *            the my class
	 * @param memberName
	 *            the member name
	 * @return the declared field datatype
	 */
	@SuppressWarnings("rawtypes")
	private static Class getDeclaredFieldDatatype(Class myClass, String memberName) {
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

	/**
	 * Gets the declared field data type of the given member in the class
	 * identified by the given class name.
	 *
	 * @param className
	 *            the class name
	 * @param memberName
	 *            the member name
	 * @return the declared field datatype
	 */
	@SuppressWarnings("rawtypes")
	private static Class getDeclaredFieldDatatype(String className, String memberName) {
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

	/**
	 * Gets the field data type of the given member in the class identified by
	 * the given class name.
	 *
	 * @param className
	 *            the class name
	 * @param memberName
	 *            the member name
	 * @return the field datatype
	 */
	@SuppressWarnings("rawtypes")
	private static Class getFieldDatatype(String className, String memberName) {
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

	/**
	 * Gets the field data type of the member identified by the given member
	 * name in the class identified by the given class name.
	 *
	 * @param className
	 *            the class name
	 * @param memberName
	 *            the member name
	 * @return the generic field type
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static String getGenericFieldType(String className, String memberName) {
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

	/**
	 * Gets the method data type of the method identified by the given method
	 * name in the class identified by the given class name.
	 *
	 * @param className
	 *            the class name
	 * @param methodName
	 *            the method name
	 * @return the method datatype
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Class getMethodDatatype(String className, String methodName) {
		Class retVal = null;

		try {
			Class c = Class.forName(className);
			if (c != null) {
				Method m;
				try {
					m = c.getMethod(
							createGetterNamePascalCase(JavaCodeGenerator.toPascalCase(methodName)));
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

	/**
	 * Checks if the given class is a collection.
	 *
	 * @param c
	 *            the c
	 * @return true, if is class collection
	 */
	@SuppressWarnings("rawtypes")
	private static boolean isClassCollection(Class c) {
		if (c == null)
			return false;
		else
			return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
	}

	/**
	 * Checks if the creator method for fixed contents is already complete in
	 * the resulting class.
	 *
	 * @param setterForFixedContentsMethod
	 *            the setter for fixed contents method
	 * @return true, if is complete creator for fixed contents method
	 */
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

	/**
	 * Prints the given CDA attribute list to the console.
	 *
	 * @param intend
	 *            the intend
	 * @param attrList
	 *            the attr list
	 */
	private static void printCdaAttributes(String intend, ArrayList<CdaAttribute> attrList) {
		for (CdaAttribute attr : attrList) {
			System.out.println(intend + "  " + attr.getName() + " = " + attr.getValue()
					+ " (dataType: " + attr.getDataType() + ")");
		}
	}

	/**
	 * Prints the given CDA element list recursively to the console.
	 *
	 * @param intend
	 *            the intend
	 * @param cdaElement
	 *            the cda element
	 */
	private static void printCdaElementRecursive(String intend, CdaElement cdaElement) {
		System.out.println(intend + "- CdaElement Name = " + cdaElement.getJavaName()
				+ " (dataType: " + cdaElement.getDataType() + ")");
		printCdaAttributes(intend, cdaElement.getCdaAttributeList());
		for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
			printCdaElementRecursive(intend + "  ", item);
		}
	}

	/**
	 * Uppercase the first character of the given string.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	private static String toUpperFirstChar(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1, value.length());
	}

	/** The base url to ART-DECOR services. */
	private URL artDecorBaseUrl;

	/** The ART-DECOR prefix for the current ART-DECOR project. */
	private String artDecorPrefix;

	/** The calling CDA element (used in the ANTLR4 parser). */
	private CdaElement callingCdaElement;

	/** The current CDA attribute (used in the ANTLR4 parser). */
	private CdaAttribute currentCdaAttribute = null;

	/** The current CDA element (used in the ANTLR4 parser). */
	private CdaElement currentCdaElement = null;

	/** The current CDA template (used in the ANTLR4 parser). */
	private CdaTemplate currentCdaTemplate = null;

	/** The destination path where to put the generated classes. */
	private String dstFilePath = null;

	/** The file header to be placed in the generated classes. */
	private String fileHeader;

	/** The full destination path where to put the generated classes. */
	private String fullDstFilePath = null;

	/** The main CDA template (used in the ANTLR4 parser). */
	private CdaTemplate mainCdaTemplate = null;

	/** The package name to be used in the generated classes. */
	private String packageName;

	/** The parent for contains (used in the ANTLR4 parser). */
	private CdaElement parentForContains;

	/** The parent for includes (used in the ANTLR4 parser). */
	private CdaElement parentForIncludes;

	/** Enable this for debug print of assembled debug information. */
	private boolean printAssembledDebugInformation = false;

	/** Enable this for debug print of data type debug information. */
	private boolean printDataTypeDebugInformation = false;

	/** Enable this for debug print of parsing information. */
	private boolean printParsingDebugInformation = false;

	/** The processing attribute (used in the ANTLR4 parser). */
	private int processingAttribute = 0;

	/** The processing element (used in the ANTLR4 parser). */
	private int processingElement = 0;

	/**
	 * The number of previously processing element (used in the ANTLR4 parser).
	 */
	private int processingElementPrev = 0;

	/**
	 * The flag whether processing a root include (used in the ANTLR4 parser).
	 */
	private boolean processingRootInclude = false;

	/** The number of processing templates (used in the ANTLR4 parser). */
	private int processingTemplate = 0;

	/** The running template (used in the ANTLR4 parser). */
	private CdaTemplate runningTemplate;

	/**
	 * This is for debugging purposes, only and allows to skip the value set
	 * generation.
	 */
	private boolean skipValueSetGeneration = false;

	/** The source file path is where to look for template XMLs. */
	private String srcFilePath = null;

	/** The template index (used in the ANTLR4 parser). */
	private HashMap<String, CdaTemplate> templateIndex = null;

	/** The template list (used in the ANTLR4 parser). */
	private ArrayList<CdaTemplate> templateList = null;

	/** The value set index (used in the ANTLR4 parser). */
	private HashMap<String, String> valueSetIndex = null;

	/** Flag for the initial run. */
	boolean initialRun;

	/**
	 *
	 * <div class="en">Constructor for the ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Constructor des ART-DECOR to Java Generators.</div>
	 *
	 * @param callingCdaElement
	 *            the calling cda element
	 * @param templateIndex
	 *            the template index
	 * @param valueSetIndex
	 *            the value set index
	 * @param templateList
	 *            the template list
	 * @param srcFilePath
	 *            the src file path
	 * @param dstFilePath
	 *            the dst file path
	 * @param packageName
	 *            the package name
	 * @param fileHeader
	 *            the file header
	 * @param artDecorPrefix
	 *            the art decor prefix
	 * @param artDecorBaseUrl
	 *            the art decor base url
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ArtDecor2JavaGenerator(CdaElement callingCdaElement,
			HashMap<String, CdaTemplate> templateIndex, HashMap<String, String> valueSetIndex,
			ArrayList<CdaTemplate> templateList, String srcFilePath, String dstFilePath,
			String packageName, String fileHeader, String artDecorPrefix, URL artDecorBaseUrl)
			throws IOException {
		this.callingCdaElement = callingCdaElement;
		this.artDecorPrefix = artDecorPrefix;
		this.artDecorBaseUrl = artDecorBaseUrl;

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

	/**
	 *
	 * <div class="en">Creates all Java Classes as files.</div>
	 *
	 * <div class="de">Erstellt alle Java-Klassen in Dateien.</div>
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void createJavaClasses() throws IOException {
		regroupTemplateElements(templateList);
		System.out.println("Writing Java Class files:");
		for (CdaTemplate cdaTemplate : templateList) {
			System.out.println("- " + cdaTemplate.getName());
			createJavaClassFile(cdaTemplate, packageName, fullDstFilePath);
		}
		System.out.println(" done.");
	}

	/**
	 *
	 * <div class="en">Performs Java Class generation for one ART-DECOR Document
	 * Template.</div>
	 *
	 * <div class="de">Führt die Java Class Generierung für ein ART-DECOR
	 * Document Template durch.</div>
	 *
	 * @param templateId
	 *            the template id
	 * @return the cda template
	 * @throws SaxonApiException
	 *             the saxon api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 */
	public CdaTemplate doOneTemplate(String templateId)
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		CdaTemplate retVal = null;

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
		}

		if (initialRun)
			System.out.println("Processing: " + templateId + " done.");

		return retVal;

	}

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
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

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
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

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
	@Override
	public void enterElement(Hl7ItsParser.ElementContext ctx) {

		currentCdaAttribute = null;

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
						dstFilePath, packageName, fileHeader, artDecorPrefix, artDecorBaseUrl);
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

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
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
						dstFilePath, packageName, fileHeader, artDecorPrefix, artDecorBaseUrl);
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

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
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

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
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

		String codeSystemName = null;
		CodeSystemNameAttrContext codeSystemNameAttrCtx = ctx.codeSystemNameAttr();
		if (codeSystemNameAttrCtx != null)
			codeSystemName = codeSystemNameAttrCtx.AttrValue().getText().replace("\"", "");

		String displayName = null;
		DisplayNameAttrContext displayNameAttrCtx = ctx.displayNameAttr();
		if (displayNameAttrCtx != null)
			displayName = displayNameAttrCtx.AttrValue().getText().replace("\"", "");

		String valueSetId = null;
		ValueSetAttrContext valueSetAttrCtx = ctx.valueSetAttr();
		if (valueSetAttrCtx != null)
			valueSetId = valueSetAttrCtx.AttrValue().getText().replace("\"", "");

		String flexibility = null;
		FlexibilityAttrContext flexibilityAttrCtx = ctx.flexibilityAttr();
		if (flexibilityAttrCtx != null)
			flexibility = flexibilityAttrCtx.AttrValue().getText().replace("\"", "");

		if ((code != null) || (codeSystem != null) || (valueSetId != null)) {
			if (currentCdaAttribute == null || currentCdaAttribute.isVocab()) {
				currentCdaAttribute = new CdaAttribute();
				currentCdaAttribute.setCdaElement(currentCdaElement);
				currentCdaAttribute.setDataType("cs");
			}
			currentCdaAttribute.setVocab(true);
			if (code != null || codeSystem != null) {
				CodeBaseType codeBt = new CodeBaseType();
				codeBt.setCode(code);
				codeBt.setCodeSystem(codeSystem);
				if (codeSystemName != null)
					codeBt.setCodeSystemName(codeSystemName);
				if (displayName != null)
					codeBt.setDisplayName(displayName);
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

					String sourceUrl = artDecorBaseUrl.toString() + "RetrieveValueSet?prefix="
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

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
	@Override
	public void exitAttribute(Hl7ItsParser.AttributeContext ctx) {
		processingAttribute--;
	}

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
	@Override
	public void exitElement(Hl7ItsParser.ElementContext ctx) {
		processingElement--;
	}

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
	@Override
	public void exitInclude(Hl7ItsParser.IncludeContext ctx) {
		if (processingRootInclude) {
			parentForIncludes = null;
			currentCdaElement = currentCdaElement.getParentCdaElement();
		}
		processingRootInclude = false;
	}

	/**
	 * <div class="en">Overrides the given Method of the ANTL4 parser for the
	 * ART-DECOR to Java Generator.</div>
	 *
	 * <div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für
	 * den ART-DECOR to Java Generator.</div>
	 *
	 * @param ctx
	 *            the ctx
	 */
	@Override
	public void exitTemplate(Hl7ItsParser.TemplateContext ctx) {
		processingTemplate--;
	}

	/**
	 *
	 * <div class="en">Prepares the current ART-DECOR to Java Generator instance
	 * for another Document Template (reset of module variables for a next
	 * iteration).</div>
	 *
	 * <div class="de">Bereitet die aktuelle Instanz des ART-DECOR to Java
	 * Generators vor für ein nächstes Document Template (reset von
	 * Modulvariablen für eine nächste Iteration).</div>
	 *
	 * Prepare for another template.
	 */
	public void prepareForAnotherTemplate() {
		this.callingCdaElement = null;
		this.currentCdaAttribute = null;
		this.currentCdaElement = null;
		this.currentCdaTemplate = null;
		this.mainCdaTemplate = null;
		this.parentForContains = null;
		this.parentForIncludes = null;
	}

	/**
	 * Performs some data type adjustments.
	 *
	 * @param dataType
	 *            the data type
	 * @return the string
	 */
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
					retVal = "EIVLTS";
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

	/**
	 * Performs some value set adjustments.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	private String adjustValueSet(String value) {
		String retVal = value;
		String testEnum = value.replace(packageName + ".enums",
				"org.ehealth_connector.common.hl7cdar2");
		try {
			@SuppressWarnings("rawtypes")
			Class cl = Class.forName(adjustDataType(testEnum));
			if (cl != null)
				retVal = cl.getName();
		} catch (ClassNotFoundException e) {
			// Do nothing
		}

		return retVal;
	}

	/**
	 * Creates the creator method for fixed attribute contents.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaAttribute
	 *            the cda attribute
	 * @param setterForFixedContentsName
	 *            the setter for fixed contents name
	 * @return the method declaration
	 */
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

	/**
	 * Creates the creator method for fixed element contents.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 * @param setterForFixedContentsName
	 *            the setter for fixed contents name
	 * @return the method declaration
	 */
	private MethodDeclaration createCreatorForFixedContentsElement(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement,
			String setterForFixedContentsName) {

		MethodDeclaration method = myClass.addMethod(setterForFixedContentsName,
				privateModifier().getKeyword(), staticModifier().getKeyword());
		method.setJavadocComment(
				"Creates fixed contents for CDA Element " + cdaElement.getJavaName() + "\n\n");

		compilationUnit.addImport("org.ehealth_connector.common.hl7cdar2.ObjectFactory");

		BlockStmt body = method.createBody();

		String dataType = adjustDataType(cdaElement.getDataType());

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

	/**
	 * Creates the assignment if fixed attribute value.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 */
	private void createFixedAttributeValues(CompilationUnit compilationUnit,
			ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getCdaAttributeList() != null) {

			ConstructorDeclaration constructor = null;
			MethodDeclaration creatorForFixedContentsMethod = null;
			ArrayList<String> arguments = new ArrayList<String>();
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

					if (!isAttributeOfTemplateRootElement) {
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
							creatorForFixedContentsMethod = creatorForFixedContentsMethodList
									.get(0);

						if (cdaAttribute.getValue() != null) {
							boolean ignoreAttr = false;
							// fix for createHl7EffectiveTimeFixedValue: there
							// is no type in the ART-DECOR model
							if ("createHl7EffectiveTimeFixedValue"
									.equals(creatorForFixedContentsMethod.getNameAsString())) {
								ignoreAttr = ("xsi:type".contentEquals(cdaAttribute.getName()));
							}
							// end of fix

							if (!ignoreAttr) {
								if (!isAttributeOfTemplateRootElement)
									updateCreatorForFixedContentsMethod(compilationUnit,
											creatorForFixedContentsMethod, cdaElement,
											cdaAttribute);

								if (i >= creatorForFixedContentsMethod.getParameters().size()) {
									arguments.add(sb.toString());
									sb = new StringBuilder();
									i = 0;
								}
								if (i < creatorForFixedContentsMethod.getParameters().size()) {
									i++;
									if (sb.length() != 0)
										sb.append(", ");
									if (cdaAttribute.getCode() != null) {
										String temp;
										temp = "null";
										if (cdaAttribute.getCode().getCode() != null)
											temp = "\"" + cdaAttribute.getCode().getCode() + "\"";
										sb.append(temp);

										i++;
										sb.append(", ");
										temp = "null";
										if (cdaAttribute.getCode().getCodeSystem() != null)
											temp = "\"" + cdaAttribute.getCode().getCodeSystem()
													+ "\"";
										sb.append(temp);

										i++;
										sb.append(", ");
										temp = "null";
										if (cdaAttribute.getCode().getCodeSystemName() != null)
											temp = "\"" + cdaAttribute.getCode().getCodeSystemName()
													+ "\"";
										sb.append(temp);

										i++;
										sb.append(", ");
										temp = "null";
										if (cdaAttribute.getCode().getDisplayName() != null)
											temp = "\"" + cdaAttribute.getCode().getDisplayName()
													+ "\"";
										sb.append(temp);

									} else
										sb.append("\"" + cdaAttribute.getValue() + "\"");
								}
							}
						}
					}
				}

				String templateClassName = JavaCodeGenerator
						.toPascalCase(cdaElement.getTemplate().getName());
				if (templateClassName.contentEquals(myClass.getNameAsString())) {
					if (cdaAttribute.getValue() != null) {
						// This is for debugging purposes, only:
						// String vocabString = "";
						// if (cdaAttribute.isVocab())
						// vocabString = " (isVocab)";
						// if (cdaAttribute.getCode() != null)
						// addBodyComment(constructor,
						// cdaElement.getTemplate().getName() + "/"
						// + cdaElement.getXmlName() + ":" +
						// cdaAttribute.getDataType()
						// + " " + attrName + " = \"" +
						// cdaAttribute.getCode().toString()
						// + "\";" + vocabString);
						// else
						// addBodyComment(constructor,
						// cdaElement.getTemplate().getName() + "/"
						// + cdaElement.getXmlName() + ":"
						// + cdaAttribute.getDataType() + " " + attrName + " =
						// \""
						// + cdaAttribute.getValue() + "\";" + vocabString);

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
										if (cdaAttribute.getCode() != null) {
											if (cdaAttribute.getCode().getCode() != null) {
												statement = "super.setCode" + "(" + "\""
														+ cdaAttribute.getCode().getCode() + "\""
														+ ");";
												addBodyStatement(constructor, statement);
											}
											if (cdaAttribute.getCode().getCodeSystem() != null) {
												statement = "super.setCodeSystem" + "(" + "\""
														+ cdaAttribute.getCode().getCodeSystem()
														+ "\"" + ");";
												addBodyStatement(constructor, statement);
											}
											if (cdaAttribute.getCode()
													.getCodeSystemName() != null) {
												statement = "super.setCodeSystemName" + "(" + "\""
														+ cdaAttribute.getCode().getCodeSystemName()
														+ "\"" + ");";
												addBodyStatement(constructor, statement);
											}
											if (cdaAttribute.getCode().getDisplayName() != null) {
												statement = "super.setDisplayName" + "(" + "\""
														+ cdaAttribute.getCode().getDisplayName()
														+ "\"" + ");";
												addBodyStatement(constructor, statement);
											}
											statement = null;
										} else
											statement = "super.set" + toUpperFirstChar(attrName)
													+ "(" + "\"" + cdaAttribute.getValue() + "\""
													+ ");";
									}
								}
							}
							if (statement != null)
								addBodyStatement(constructor, statement);
						}
					}
					if (cdaAttribute.getValueSetId() != null) {
						// this is for debugging purposes, only
						// String enumName = adjustValueSet(
						// valueSetIndex.get(cdaAttribute.getValueSetId()));
						// addBodyComment(constructor,
						// cdaElement.getTemplate().getName() + "/" +
						// cdaElement.getXmlName()
						// + ":" + cdaAttribute.getDataType() + " " + attrName
						// + " = valueSet(\"" + cdaAttribute.getValueSetId()
						// + "\"); --> " + enumName);
					}
				}
			}

			arguments.add(sb.toString());
			sb = null;

			if ((creatorForFixedContentsMethod != null)) {

				for (String argsForThisCall : arguments) {

					// fix for setHl7EntryRelationshipFixedValue:
					// sometimes in the first occurrence, there is no
					// inversionInd in the ART-DECOR model
					int argCountMethod = creatorForFixedContentsMethod.getParameters().size();
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
							.countMatches(argsForThisCall, ",") + 1;

					while (argCountGiven < argCountMethod) {
						argsForThisCall += ", ";
						argsForThisCall += "null";
						argCountGiven = org.apache.commons.lang3.StringUtils
								.countMatches(argsForThisCall, ",") + 1;
					}
					// end of fixes

					if (!isAttributeOfTemplateRootElement) {
						if (!isCompleteCreatorForFixedContentsMethod(creatorForFixedContentsMethod))
							completeCreatorForFixedContentsMethod(creatorForFixedContentsMethod,
									cdaElement);

						String creator = creatorForFixedContentsMethod.getNameAsString() + "("
								+ argsForThisCall + ")";

						if (cdaElement.getMinOccurs() == 1) {
							// This is fixed content for a required element

							String name = cdaElement.getXmlName().replace("hl7:", "")
									.replace("pharm:", "").replace("xsi:", "");

							@SuppressWarnings("rawtypes")
							Class memberType = getDeclaredFieldDatatype(
									cdaElement.getParentCdaElement().getDataType(), name);
							String statement = "";

							boolean isLocalField = (myClass.getExtendedTypes().size() == 0);
							if (isClassCollection(memberType)) {
								if (isLocalField) {
									statement = cdaElement.getXmlName().replace("hl7:", "")
											.replace("pharm:", "") + ".add(" + creator + ");";
								} else {
									statement = "super.get"
											+ toUpperFirstChar(cdaElement.getXmlName()
													.replace("hl7:", "").replace("pharm:", "")
													.replace("xsi:", ""))
											+ "().add(" + creator + ");";
								}
							} else {
								if (isLocalField) {
									statement = "this." + cdaElement.getXmlName()
											.replace("hl7:", "").replace("pharm:", "") + " = "
											+ creator + ";";
								} else {
									statement = "super.set" + toUpperFirstChar(
											cdaElement.getXmlName().replace("hl7:", "")
													.replace("pharm:", "").replace("xsi:", ""))
											+ "(" + creator + ");";
								}
							}

							addBodyStatement(constructor, statement);
						}

						if ((cdaElement.getMinOccurs() == 0) || (arguments.size() > 1)) {
							// This is fixed content for an optional element
							String methodName = "getPredefined"
									+ JavaCodeGenerator.toPascalCase(
											cdaElement.getXmlName().replace("hl7:", "")
													.replace("pharm:", "").replace("xsi:", ""))
									+ JavaCodeGenerator.toPascalCase(argsForThisCall);
							List<MethodDeclaration> existingMethods = myClass
									.getMethodsByName(methodName);
							if (existingMethods.size() == 0) {
								// This is for debugging purposes, only:
								// addBodyComment(constructor,
								// "This is fixed content for an optional
								// element: "
								// +
								// creatorForFixedContentsMethod.getNameAsString()
								// + "("
								// + argsForThisCall + ") --> Creating " +
								// methodName
								// + "();");

								MethodDeclaration method = myClass.addMethod(methodName,
										publicModifier().getKeyword(),
										staticModifier().getKeyword());
								method.setType(creatorForFixedContentsMethod.getType());

								String comment = "Adds a predefined "
										+ creatorForFixedContentsMethod.getTypeAsString()
										+ ", filled by: " + argsForThisCall
										+ "\n@return the predefined element.";
								method.setJavadocComment(comment);

								BlockStmt body = method.createBody();
								body.addStatement("return " + creator + ";");
							}

						}

						if (cdaElement.getMinOccurs() > 1) {
							// This is fixed content for an element with
							// multiple
							// required elements
							addBodyComment(constructor,
									"TODO: fixed content for an element with multiple required elements not handled, yet: "
											+ creatorForFixedContentsMethod.getNameAsString() + "("
											+ argsForThisCall + ")");
						}
					}
				}
			}
		}
	}

	/**
	 * Creates the Java class for the given CDA Template and writes it to file.
	 *
	 * @param cdaTemplate
	 *            the cda template
	 * @param packageName
	 *            the package name
	 * @param dstFilePath
	 *            the dst file path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void createJavaClassFile(CdaTemplate cdaTemplate, String packageName,
			String dstFilePath) throws IOException {

		// Save the current CDA Element as Java Class File
		CompilationUnit compilationUnit = new CompilationUnit();
		compilationUnit.setPackageDeclaration(packageName);

		String className = JavaCodeGenerator.toPascalCase(cdaTemplate.getName());

		boolean isSingleElementTemplate = (cdaTemplate.getCdaElementList().size() == 1);

		for (CdaElement cdaElement : cdaTemplate.getCdaElementList()) {

			String dataType = adjustDataType(cdaElement.getDataType());

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

			// This is for Templates used as contains:
			if (inheritenceOf == null)
				inheritenceOf = cdaElement.getParentCdaElement().getDataType();

			// Inheritence does not work for AD elements.
			// This is by HL7 CDA Schema design ...
			if ("org.ehealth_connector.common.hl7cdar2.AD".equals(inheritenceOf))
				inheritenceOf = null;

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

			// complete pending actions
			for (MethodDeclaration method : myClass.getMethods()) {
				if (existBodyComment(method, PENDING_ACTIONS)) {
					Optional<BlockStmt> bodyOpt = method.getBody();
					if (bodyOpt.isPresent()) {
						for (Comment c : bodyOpt.get().getOrphanComments()) {
							if (c.asLineComment().getContent().equals(PENDING_ACTIONS))
								bodyOpt.get().removeOrphanComment(c);
							if (c.asLineComment().getContent()
									.startsWith(PENDING_ACTIONS_ADJUST_NAME)) {
								bodyOpt.get().removeOrphanComment(c);
								String methodName = c.getContent().substring(
										PENDING_ACTIONS_ADJUST_NAME.length(),
										c.getContent().length());
								method.setName(methodName);
							}
						}
					}
				}
			}

			File outFile = new File(fullDstFilePath + className + ".java");
			JavaCodeGenerator.completeAndSave(compilationUnit, outFile);
		}

	}

	/**
	 * Creates the members of the given CDA element to the resulting class.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 * @param createOwnProperty
	 *            the create own property
	 */
	private void createMembers(CompilationUnit compilationUnit, ClassOrInterfaceDeclaration myClass,
			CdaElement cdaElement, boolean createOwnProperty) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getXmlName());

		String dataType = adjustDataType(cdaElement.getDataType());
		cdaElement.setDataType(dataType);

		String xmlName = cdaElement.getXmlName();
		String javaName = JavaCodeGenerator.toCamelCase(xmlName);
		cdaElement.setJavaName(javaName);

		boolean elementExist = existAdderOrSetter(myClass, cdaElement);

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

	/**
	 * Checks whether the adder or setter already exists in the resulting class.
	 *
	 * @param myClass
	 *            the my class
	 * @param cdaElement
	 *            the cda element
	 * @return true, if successful
	 */
	private boolean existAdderOrSetter(ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {
		boolean retVal = false;
		String xmlName = cdaElement.getXmlName();
		String javaName = JavaCodeGenerator.toCamelCase(xmlName);
		cdaElement.setJavaName(javaName);

		List<MethodDeclaration> setterList = myClass
				.getMethodsByName(createSetterNamePascalCase(javaName));
		List<MethodDeclaration> adderList = myClass
				.getMethodsByName(createAdderNamePascalCase(javaName));

		if (!setterList.isEmpty() || !adderList.isEmpty()) {
			String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
					.replace("xsi:", "");

			String dataType = null;
			CdaElement elForType = cdaElement.getParentCdaElement();
			if (elForType == null)
				elForType = cdaElement;
			try {
				dataType = getDataType(elForType.getDataType(), name);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| NoSuchFieldException | SecurityException e) {
				// Do nothing
			}
			if ("effectiveTime".equals(name)) {
				dataType = "org.ehealth_connector.common.hl7cdar2.IVLTS";
			}
			if (!retVal) {
				for (MethodDeclaration methodDeclaration : setterList) {
					if (methodDeclaration.getParameterByType(dataType).isPresent()) {
						retVal = true;
						break;
					}
				}
			}
			if (!retVal) {
				for (MethodDeclaration methodDeclaration : adderList) {
					if (methodDeclaration.getParameterByType(dataType).isPresent()) {
						retVal = true;
						break;
					}
				}
			}
		}

		return retVal;
	}

	/**
	 * Fill all data types recursively for all CDA elements.
	 *
	 * @param cdaElement
	 *            the cda element
	 * @throws JAXBException
	 *             the JAXB exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 */
	private void fillDatatypesRecursive(CdaElement cdaElement)
			throws JAXBException, ClassNotFoundException, IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException,
			NoSuchFieldException, SecurityException {
		if (cdaElement != null) {
			if (dataTypeIndex == null)
				dataTypeIndex = loadHl7CdaR2DataTypeIndex();

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

	/**
	 * Gets the data type of the given CDA Template.
	 *
	 * @param cdaElement
	 *            the cda element
	 * @param templateIndex
	 *            the template index
	 * @return the data type
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unlikely-arg-type")
	private String getDataType(CdaElement cdaElement, HashMap<String, CdaTemplate> templateIndex)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException,
			SecurityException, IOException {
		String retVal = null;
		String cdaElementName = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
				.replace("xsi:", "");

		String parentDataType = "";
		if (cdaElement.getParentCdaElement() != null) {
			parentDataType = cdaElement.getParentCdaElement().getDataType();
			if (parentDataType == null) {
				parentDataType = getDataType(cdaElement.getParentCdaElement(), templateIndex);
				if (parentDataType != null) {
					parentDataType = adjustDataType(parentDataType);
				}
			}
		}

		if (retVal == null) {
			ArrayList<String> candidates = new ArrayList<String>();
			if (dataTypeIndex == null)
				dataTypeIndex = loadHl7CdaR2DataTypeIndex();
			for (String key : dataTypeIndex.keySet()) {
				String value = dataTypeIndex.get(key).toString();
				if (key.startsWith(cdaElementName)
						|| key.startsWith("all.InfrastructureRoot." + cdaElementName)) {
					if (parentDataType.startsWith("org.ehealth_connector.common.hl7cdar2")) {
						if (parentDataType.equals(value))
							candidates.add(value);
					} else
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

		if ((retVal == null) && (parentDataType != null)) {
			if (templateIndex.containsValue(parentDataType)) {
				retVal = parentDataType;
			}
		}

		if ((retVal == null) && (parentDataType != null)) {
			retVal = getDataType(parentDataType, cdaElementName);
			if (retVal == null) {
				String parentType = getSuperClassDataType(parentDataType);
				while ((retVal == null) && (parentType != null)
						&& (!"java.lang.Object".equals(parentType))) {
					retVal = getDataType(parentType, cdaElementName);
					parentType = getSuperClassDataType(parentType);
				}
			}
		}

		if (retVal == null)
			if (parentDataType.startsWith("org.ehealth_connector.common.hl7cdar2"))
				retVal = parentDataType;

		if (retVal == null)
			throw new RuntimeException("There is no data type candidate for "
					+ cdaElement.getFullXmlName()
					+ ". Make sure, the ART-DECOR model conforms to the XML schema (CDA_extPHARM.xsd).");
		return retVal;

	}

	/**
	 * Gets the data type of the given CDA element identified by name.
	 *
	 * @param parentClassName
	 *            the parent class name
	 * @param cdaElementName
	 *            the cda element name
	 * @return the data type
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 */
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

		if (retVal == null) {
			if (parentClassName.endsWith(".AD"))
				retVal = "org.ehealth_connector.common.hl7cdar2.ADXP";
		}

		return retVal;
	}

	/**
	 * Gets the full destination file path for the given class identified by
	 * name.
	 *
	 * @return the full dst file path
	 */
	private String getFullDstFilePath() {
		return fullDstFilePath;
	}

	/**
	 * Gets the data type of the super class of the given class.
	 *
	 * @param className
	 *            the class name
	 * @return the parent data type
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	@SuppressWarnings("rawtypes")
	private String getSuperClassDataType(String className)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String retVal = null;
		Class cl = Class.forName(adjustDataType(className));
		if (cl != null)
			if (cl.getSuperclass() != null)
				retVal = cl.getSuperclass().getName();
		return retVal;
	}

	/**
	 * Loads the data type index of all classes in the HL7 CDA R2 Object
	 * Factory.
	 *
	 * @return the hash map
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	@SuppressWarnings("rawtypes")
	private HashMap<String, String> loadHl7CdaR2DataTypeIndex()
			throws ClassNotFoundException, IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
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

	/**
	 * Performs some template adjustments (regrouping contained elements to the
	 * hosting template).
	 *
	 * @param myTemplateList
	 *            the my template list
	 */
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

	/**
	 * Updates the creator method for fixed contents by the given Element.
	 *
	 * @param compilationUnit
	 *            the compilation unit
	 * @param setterForFixedContentsMethod
	 *            the setter for fixed contents method
	 * @param cdaElement
	 *            the cda element
	 * @param cdaAttribute
	 *            the cda attribute
	 */
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

						if ("java.lang.String".contentEquals(memberType.getName())) {
							body.addStatement("retVal.set" + toUpperFirstChar(attrName) + "("
									+ attrName + ");");
							if (cdaAttribute.getCode() != null) {
								setterForFixedContentsMethod.addAndGetParameter("String",
										"codeSystem");
								setterForFixedContentsMethod.addAndGetParameter("String",
										"codeSystemName");
								setterForFixedContentsMethod.addAndGetParameter("String",
										"displayName");

								javadoc.addBlockTag("param",
										"codeSystem the desired fixed value for this argument.");
								javadoc.addBlockTag("param",
										"codeSystemName the desired fixed value for this argument.");
								javadoc.addBlockTag("param",
										"codeDisplayName the desired fixed value for this argument.");

								body.addStatement("retVal.setCodeSystem(codeSystem);");
								body.addStatement("retVal.setCodeSystemName(codeSystemName);");
								body.addStatement("retVal.setDisplayName(displayName);");

							}
						} else if ("java.lang.Boolean".contentEquals(memberType.getName()))
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
