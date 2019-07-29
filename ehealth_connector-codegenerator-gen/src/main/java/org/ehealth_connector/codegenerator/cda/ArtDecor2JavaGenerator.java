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

import static com.github.javaparser.ast.Modifier.privateModifier;
import static com.github.javaparser.ast.Modifier.publicModifier;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.NameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.RefAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.ehealth_connector.codegenerator.cda.xslt.Hl7Its2EhcTransformer;
import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.common.hl7cdar2.ObjectFactory;
import org.ehealth_connector.common.utils.FileUtil;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

	private static HashMap<String, String> dataTypeIndex = null;

	private static void createField(ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getName());

		FieldDeclaration field = myClass.addField(cdaElement.getDataType(),
				JavaCodeGenerator.toCamelCase(cdaElement.getName()),
				privateModifier().getKeyword());

		String desc = cdaElement.getDescription();
		if (desc != null)
			field.setJavadocComment(desc);

		createGetter(myClass, cdaElement);
		createSetter(myClass, cdaElement);

	}

	private static void createGetter(ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getName());

		MethodDeclaration method;

		method = myClass.addMethod("get" + JavaCodeGenerator.toPascalCase(cdaElement.getName()),
				publicModifier().getKeyword());
		method.setType(cdaElement.getDataType());

		String comment = "Gets the " + cdaElement.getName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += "\r\n" + desc;

		method.setJavadocComment(comment);

		method.createBody().addStatement(
				"return " + JavaCodeGenerator.toCamelCase(cdaElement.getName()) + ";");

	}

	private static void createSetter(ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

		if (cdaElement.getDataType() == null)
			throw new NotImplementedException("Type undefined for " + cdaElement.getName());

		MethodDeclaration method;

		method = myClass.addMethod("set" + JavaCodeGenerator.toPascalCase(cdaElement.getName()),
				publicModifier().getKeyword());

		String comment = "Sets the " + cdaElement.getName();
		String desc = cdaElement.getDescription();
		if (desc != null)
			comment += "\r\n" + desc;

		method.setJavadocComment(comment);

		method.addAndGetParameter(cdaElement.getDataType(), "value");

		method.createBody()
				.addStatement(JavaCodeGenerator.toCamelCase(cdaElement.getName()) + " = value;");

	}

	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.'
						+ file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	@SuppressWarnings("rawtypes")
	private static Class[] getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	private static String getDataType(CdaElement cdaElement, HashMap<String, String> templateIndex)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException,
			SecurityException {
		String retVal = null;
		String cdaElementName = cdaElement.getName().replace("hl7:", "").replace("pharm:", "");

		if ((retVal == null) && (cdaElement.getParentCdaElement() != null)) {
			String parentClassName = cdaElement.getParentCdaElement().getDataType();
			if (templateIndex.containsValue(parentClassName)) {
				retVal = parentClassName;
			}
		}

		if ((retVal == null) && (cdaElement.getParentCdaElement() != null)) {
			String parentClassName = cdaElement.getParentCdaElement().getDataType();
			if (parentClassName != null) {
				if (parentClassName.startsWith("IVL_TS."))
					retVal = "org.ehealth_connector.common.hl7cdar2.IVLTS";
				if (parentClassName.startsWith("TS."))
					retVal = "org.ehealth_connector.common.hl7cdar2.TS";
				if (parentClassName.startsWith("SXPR_TS"))
					retVal = "org.ehealth_connector.common.hl7cdar2.SXPRTS";
				if (parentClassName.startsWith("SD.TEXT"))
					retVal = "org.ehealth_connector.common.hl7cdar2.StrucDoc.Text";
			}
		}

		if ((retVal == null) && (cdaElement.getParentCdaElement() != null)) {
			String parentClassName = cdaElement.getParentCdaElement().getDataType();
			if (parentClassName != null) {
				if (!parentClassName.startsWith("org.ehealth_connector.common.hl7cdar2."))
					parentClassName = "org.ehealth_connector.common.hl7cdar2." + parentClassName;
				retVal = getDataType(parentClassName, cdaElementName);
				if (retVal == null) {
					String parentType = getParentDataType(parentClassName);
					while ((retVal == null) && (parentType != null)) {
						retVal = getDataType(parentType, cdaElementName);
						parentType = getParentDataType(parentType);
					}
				}
			}
		}

		if (retVal == null) {
			ArrayList<String> candidates = new ArrayList<String>();
			for (String key : dataTypeIndex.keySet()) {
				String value = dataTypeIndex.get(key).toString();
				if (key.startsWith(cdaElementName)
						|| key.startsWith("all.InfrastructureRoot." + cdaElementName)) {
					candidates.add(value);
				}
			}
			if (candidates.size() == 1)
				retVal = candidates.get(0);
			else {
				throw new RuntimeException(
						"There are multiple data type candidates for " + cdaElement.getFullName());
			}
		}

		if (retVal == null)
			throw new RuntimeException(
					"There is no data type candidate for " + cdaElement.getFullName());
		return retVal;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static String getDataType(String parentClassName, String cdaElementName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			NoSuchFieldException, SecurityException {
		String retVal = null;
		Class cl = Class.forName(parentClassName);
		XmlType xmlType = (XmlType) cl.getAnnotation(XmlType.class);
		if (xmlType != null) {
			for (String prop : xmlType.propOrder()) {
				if (prop.equals(cdaElementName)) {
					String expectedMethodName = "get" + JavaCodeGenerator.toPascalCase(prop);
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
	private static String getParentDataType(String parentClassName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String retVal = null;
		Class cl = Class.forName(parentClassName);
		if (cl != null)
			if (cl.getSuperclass() != null)
				retVal = cl.getSuperclass().getName();
		return retVal;
	}

	private static void printCdaAttributes(String intend, ArrayList<CdaAttribute> attrList) {
		for (CdaAttribute attr : attrList) {
			System.out.println(intend + "  " + attr.getName() + " = " + attr.getValue()
					+ " (dataType: " + attr.getDataType() + ")");
		}
	}

	private static void printCdaElementRecursive(String intend, CdaElement cdaElement) {
		System.out.println(intend + "- CdaElement Name = " + cdaElement.getName() + " (dataType: "
				+ cdaElement.getDataType() + ")");
		printCdaAttributes(intend, cdaElement.getCdaAttributeList());
		for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
			printCdaElementRecursive(intend + "  ", item);
		}
	}

	private boolean printParsingDebugInformation = false;

	private boolean printAssembledDebugInformation = false;

	private boolean printDataTypeDebugInformation = true;

	private int processingTemplate = 0;

	private int processingElement = 0;

	private int processingElementPrev = 0;

	private int processingAttribute = 0;

	private CdaElement currentCdaElement = null;

	private CdaTemplate currentCdaTemplate = null;

	private CdaAttribute currentCdaAttribute = null;

	private String srcFilePath = null;

	private String dstFilePath = null;

	private String fileHeader;

	private String packageName;

	private HashMap<String, String> templateIndex = null;

	private CdaElement callingCdaElement;

	private CdaElement parentForIncludes;

	private CdaElement parentForContains;

	private boolean isChildElement;

	private boolean isSiblingElement;

	private boolean isParentElement;

	//
	// private CdaElement lastParent;
	//
	public ArtDecor2JavaGenerator(CdaElement callingCdaElement,
			HashMap<String, String> templateIndex, String srcFilePath, String dstFilePath,
			String packageName, String fileHeader) {
		this.callingCdaElement = callingCdaElement;

		if (templateIndex == null)
			this.templateIndex = new HashMap<String, String>();
		else
			this.templateIndex = templateIndex;
		this.srcFilePath = srcFilePath;
		this.dstFilePath = dstFilePath;
		this.packageName = packageName;
		this.fileHeader = fileHeader;

		if (!this.dstFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.dstFilePath += FileUtil.getPlatformSpecificPathSeparator();

		if (!this.srcFilePath.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			this.srcFilePath += FileUtil.getPlatformSpecificPathSeparator();

	}

	public String doOneTemplate(String templateId)
			throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException, NoSuchFieldException, SecurityException {

		String retVal = "FAILURE";

		// TODO: This is a quick fix for CDA-CH-EMED
		if (templateId.startsWith("1.3.6.1.4.1.19376.")
				|| "2.16.756.5.30.1.1.10.4.2".equals(templateId))
			return retVal;

		if (templateIndex.containsKey(templateId)) {
			if (printParsingDebugInformation)
				System.out.println(
						templateId + " is already known as " + templateIndex.get(templateId));
			retVal = templateIndex.get(templateId);
		}

		boolean initialRun = (callingCdaElement == null);

		if ("FAILURE".equals(retVal)) {
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
			retVal = currentCdaTemplate.getName();
			templateIndex.remove(templateId, "running");
			templateIndex.put(templateId, retVal);

			if (printParsingDebugInformation) {
				System.out.println("Parsing completed");
				System.out.println("-----------------------------------------------------------");
				System.out.println("");
			}

			// filling missing data types
			if (initialRun)
				fillDatatypesRecursive(currentCdaTemplate.getCdaElement());

			// Show the content (this is for debugging purposes, only)
			if (printAssembledDebugInformation && initialRun) {
				System.out.println("Assembled content for template " + currentCdaTemplate.getName()
						+ " (id: " + currentCdaTemplate.getId() + ")");
				printCdaAttributes(" ", currentCdaTemplate.getCdaAttributeList());

				printCdaElementRecursive("", currentCdaTemplate.getCdaElement());
			}

			// Create the Java Class
			// CompilationUnit compilationUnit = new CompilationUnit();
			// compilationUnit.setPackageDeclaration(packageName);
			//
			// String className =
			// JavaCodeGenerator.toPascalCase(currentCdaTemplate.getName());
			// ClassOrInterfaceDeclaration myClass =
			// compilationUnit.addClass(className).setPublic(true);
			// myClass.setJavadocComment(currentCdaElement.getDescription());
			// String inheritenceOf = currentCdaElement.getDataType();
			// if (inheritenceOf == null)
			// inheritenceOf = "TODO";
			// myClass.setExtendedTypes(
			// new NodeList<ClassOrInterfaceType>(new ClassOrInterfaceType(null,
			// inheritenceOf)));
			//
			// for (CdaElement cdaElement :
			// currentCdaElement.getChildrenCdaElementList()) {
			// createField(myClass, cdaElement);
			// }
			//
			// File outFile = new File(dstFilePath + className + ".java");
			// JavaCodeGenerator.completeAndSave(compilationUnit, outFile);
		}

		if (initialRun)
			System.out.println("Processing: " + templateId + " done.");

		return retVal;

	}

	@Override
	public void enterAttribute(Hl7ItsParser.AttributeContext ctx) {
		processingAttribute++;
		currentCdaAttribute = new CdaAttribute();

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

		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			name = nameAttrCtx.AttrValue().getText().replace("\"", "");
		DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
		if (dataTypeAttrCtx != null)
			dataType = dataTypeAttrCtx.AttrValue().getText().replace("\"", "");

		CdaElement cdaElement = new CdaElement(null);
		cdaElement.setName(name);
		cdaElement.setDataType(dataType);

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
			currentCdaTemplate.setCdaElement(cdaElement);
			parentForIncludes = cdaElement;
		} else if ((callingCdaElement != null) && (currentCdaElement == null)) {
			// This is the first level below a referenced template
			parentCdaElement = callingCdaElement;
			parentForIncludes = parentCdaElement;
		} else {
			if (isChildElement)
				parentCdaElement = currentCdaElement;
			else if (isSiblingElement)
				parentCdaElement = currentCdaElement.getParentCdaElement();
			else if (isParentElement) {
				parentCdaElement = currentCdaElement.getParentCdaElement();
				for (int i = 0; i < levels; i++) {
					parentCdaElement = parentCdaElement.getParentCdaElement();
				}
			} else
				throw new RuntimeException(
						"element '" + name + "' is neither child, sibling, parent");

			if (parentCdaElement == null)
				throw new RuntimeException("Parent CDA element is null for " + name);

			parentForIncludes = parentCdaElement;
		}

		parentForContains = cdaElement;

		if (parentCdaElement != null) {
			parentCdaElement.addChild(cdaElement);
			cdaElement.setParentCdaElement(parentCdaElement);

		}
		currentCdaElement = cdaElement;

		ContainsAttrContext containsAttrCtx = ctx.containsAttr();
		String contains = "";
		String containsDataType = null;
		if (containsAttrCtx != null) {
			contains = containsAttrCtx.AttrValue().getText().replace("\"", "");
			if (printParsingDebugInformation)
				System.out.println(cdaElement.getName() + " contains " + contains);
			try {
				ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
						parentForContains, templateIndex, srcFilePath, dstFilePath, packageName,
						fileHeader);
				containsDataType = artDecor2JavaGenerator.doOneTemplate(contains);
			} catch (SaxonApiException | IOException | JAXBException | ClassNotFoundException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchFieldException | SecurityException e) {
				containsDataType = "FAILURE";
				e.printStackTrace();
			}
			if (containsDataType != null)
				cdaElement.setDataType(containsDataType);
		}

		if (printParsingDebugInformation)
			System.out.println("Element: " + cdaElement.getName() + " (dataType: "
					+ cdaElement.getDataType() + ")");

	}

	@Override
	public void enterInclude(Hl7ItsParser.IncludeContext ctx) {
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
						parentForIncludes, templateIndex, srcFilePath, dstFilePath, packageName,
						fileHeader);
				dataType = artDecor2JavaGenerator.doOneTemplate(ref);
			} catch (SaxonApiException | IOException | JAXBException | ClassNotFoundException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException | NoSuchFieldException | SecurityException e) {
				dataType = "FAILURE";
				e.printStackTrace();
			}
		}

	}

	@Override
	public void enterTemplate(Hl7ItsParser.TemplateContext ctx) {
		processingTemplate++;
		currentCdaTemplate = new CdaTemplate();
		IdAttrContext idAttrCtx = ctx.idAttr();
		if (idAttrCtx != null)
			currentCdaTemplate.setId(idAttrCtx.AttrValue().getText().replace("\"", ""));

		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			currentCdaTemplate.setName(nameAttrCtx.AttrValue().getText().replace("\"", ""));

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
		if (dataTypeIndex == null)
			dataTypeIndex = loadDataTypeIndex();
		String dataType = cdaElement.getDataType();
		if (dataType == null) {
			dataType = getDataType(cdaElement, templateIndex);
			cdaElement.setDataType(dataType);
			if (printDataTypeDebugInformation)
				System.out.println("Datatype for "
						+ cdaElement.getFullName().replace("hl7:", "").replace("pharm:", "")
						+ " --> " + dataType);
		}
		for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
			fillDatatypesRecursive(item);
		}
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
