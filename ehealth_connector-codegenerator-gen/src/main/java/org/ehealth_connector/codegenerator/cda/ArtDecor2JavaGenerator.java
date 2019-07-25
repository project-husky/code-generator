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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.NotImplementedException;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsLexer;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.DataTypeAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.IdAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.NameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.RefAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.ehealth_connector.codegenerator.cda.xslt.Hl7Its2EhcTransformer;
import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.common.utils.FileUtil;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

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

	private static void printElement(String intend, CdaElement cdaElement) {
		System.out.println(intend + "- CdaElement Name = " + cdaElement.getName() + " (dataType: "
				+ cdaElement.getDataType() + ")");
		for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
			printElement(intend + "  ", item);
		}
	}

	private int processingTemplate = 0;

	private int processingElement = 0;

	private int processingElementPrev = 0;

	private int processingAttribute = 0;

	private CdaElement currentCdaElement = null;

	private CdaTemplate currentCdaTemplate = null;

	private String srcFilePath = null;

	private String dstFilePath = null;

	private String fileHeader;

	private String packageName;

	private HashMap<String, String> templateIndex = null;

	private CdaElement parentCdaElement;

	public ArtDecor2JavaGenerator(CdaElement parentCdaElement,
			HashMap<String, String> templateIndex, String srcFilePath, String dstFilePath,
			String packageName, String fileHeader) {
		this.parentCdaElement = parentCdaElement;

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

	public String doOneTemplate(String templateId) throws SaxonApiException, IOException {

		boolean initialRun = (parentCdaElement == null);
		String retVal = "FAILURE";

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
		System.out.println("Parsing started");
		walker.walk(this, parser.template());
		System.out.println("Parsing completed");
		System.out.println("-----------------------------------------------------------");
		System.out.println("");

		// Show the content (this is for debugging purposes, only)
		if (initialRun) {
			System.out.println("Assembled contents:");
			printElement("", currentCdaTemplate.getCdaElement());
			// System.out.println("CdaTemplate Name = " +
			// currentCdaTemplate.getName() + " (id: "
			// + currentCdaTemplate.getId() + ")");
			// System.out.println("CdaElement Name = " +
			// currentCdaTemplate.getCdaElement().getName()
			// + " (dataType: " +
			// currentCdaTemplate.getCdaElement().getDataType() + ")");
			// for (CdaElement cdaElement : currentCdaTemplate.getCdaElement()
			// .getChildrenCdaElementList()) {
			// System.out.println(" - CdaElement Name = " + cdaElement.getName()
			// + " (dataType: "
			// + cdaElement.getDataType() + ")");
			// for (CdaElement cdaElement1 :
			// cdaElement.getChildrenCdaElementList()) {
			// System.out.println(" - CdaElement Name = " +
			// cdaElement1.getName()
			// + " (dataType: " + cdaElement1.getDataType() + ")");
			// for (CdaElement cdaElement2 :
			// cdaElement1.getChildrenCdaElementList()) {
			// System.out.println(" - CdaElement Name = " +
			// cdaElement2.getName()
			// + " (dataType: " + cdaElement2.getDataType() + ")");
			// if (cdaElement2.getChildrenCdaElementList().size() > 0)
			// System.out.println("THERE ARE MORE CHILDREN");
			// for (CdaElement cdaElement3 :
			// cdaElement2.getChildrenCdaElementList()) {
			// System.out.println(" - CdaElement Name = " +
			// cdaElement3.getName()
			// + " (dataType: " + cdaElement3.getDataType() + ")");
			// if (cdaElement3.getChildrenCdaElementList().size() > 0)
			// System.out.println("THERE ARE MORE CHILDREN");
			// }
			// }
			// }
			// }
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

		return retVal;
	}

	@Override
	public void enterAttribute(Hl7ItsParser.AttributeContext ctx) {
		processingAttribute++;
		NameAttrContext nameAttrCtx = ctx.nameAttr();
		String attrName = "";
		if (nameAttrCtx != null)
			attrName = nameAttrCtx.AttrValue().getText().replace("\"", "");

		String attrValue = "";
		ValueAttrContext valueAttrCtx = ctx.valueAttr();
		if (valueAttrCtx != null)
			attrValue = valueAttrCtx.AttrValue().getText().replace("\"", "");

		System.out.println("Attribute: " + attrName + "=" + attrValue);
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
			System.out.println("*** STOP ***: Description for an Attribute");
	}

	@Override
	public void enterElement(Hl7ItsParser.ElementContext ctx) {

		boolean elementInElement = (processingElement > processingElementPrev);
		processingElementPrev = processingElement;
		processingElement++;
		CdaElement cdaElement;
		if (elementInElement) {
			cdaElement = new CdaElement(currentCdaElement);
			parentCdaElement = currentCdaElement;
		} else
			cdaElement = new CdaElement(parentCdaElement);

		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			cdaElement.setName(nameAttrCtx.AttrValue().getText().replace("\"", ""));

		DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
		if (dataTypeAttrCtx != null)
			cdaElement.setDataType(dataTypeAttrCtx.AttrValue().getText().replace("\"", ""));

		System.out.println("Element: " + cdaElement.getName() + " (dataType: "
				+ cdaElement.getDataType() + ")");

		if (parentCdaElement != null)
			if (elementInElement)
				currentCdaElement.addChild(cdaElement);
			else
				parentCdaElement.addChild(cdaElement);
		else {
			parentCdaElement = cdaElement;
			currentCdaTemplate.setCdaElement(cdaElement);
		}

		currentCdaElement = cdaElement;

		// if (createChildElement) {
		// currentCdaElement.addChild(cdaElement);
		// currentCdaElement = cdaElement;
		// } else {
		// currentCdaElement = new CdaElement(parentCdaElement);
		// if (parentCdaElement != null)
		// parentCdaElement.addChild(currentCdaElement);
		// else {
		// parentCdaElement = currentCdaElement;
		// currentCdaTemplate.setCdaElement(currentCdaElement);
		// }
		// }

	}

	@Override
	public void enterInclude(Hl7ItsParser.IncludeContext ctx) {
		RefAttrContext refAttrCtx = ctx.refAttr();
		String ref = "";
		String dataType = null;
		if (refAttrCtx != null) {
			ref = refAttrCtx.AttrValue().getText().replace("\"", "");
			System.out.println("Include: " + ref);
			if (templateIndex.containsKey(ref)) {
				System.out.println(ref + " is already known as " + templateIndex.get(ref));
				dataType = templateIndex.get(ref);
			} else {
				try {
					ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
							parentCdaElement, templateIndex, srcFilePath, dstFilePath, packageName,
							fileHeader);
					dataType = artDecor2JavaGenerator.doOneTemplate(ref);
				} catch (SaxonApiException | IOException e) {
					dataType = "FAILURE";
					e.printStackTrace();
				}
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
}
