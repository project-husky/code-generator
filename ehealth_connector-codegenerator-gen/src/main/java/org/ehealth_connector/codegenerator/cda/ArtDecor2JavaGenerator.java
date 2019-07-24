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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsLexer;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ContainsAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.DataTypeAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MaxOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.MinOccursAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.NameAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.RefAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParser.ValueAttrContext;
import org.ehealth_connector.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.ehealth_connector.codegenerator.cda.xslt.Hl7Its2EhcTransformer;
import org.ehealth_connector.codegenerator.java.JavaCodeGenerator;
import org.ehealth_connector.common.utils.FileUtil;

import net.sf.saxon.s9api.SaxonApiException;

public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

	public static String doOneTemplate(List<CdaMember> baseCda, String path, String templateId,
			HashMap<String, String> templateIndex, String hl7ElementName, String fileHeader)
			throws SaxonApiException {

		if (!path.endsWith(FileUtil.getPlatformSpecificPathSeparator()))
			path += FileUtil.getPlatformSpecificPathSeparator();

		String retVal = "FAILURE";
		if (templateIndex == null)
			templateIndex = new HashMap<String, String>();

		String fn = path + templateId + "_transformed.xml";
		File transformed = new File(fn);

		if (!transformed.exists())
			Hl7Its2EhcTransformer.transform(path + templateId + ".xml", fn);

		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get(fn)));
			// instantiate the lexer, the parser, and the walker
			Hl7ItsLexer lexer = new Hl7ItsLexer(CharStreams.fromString(content));

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			Hl7ItsParser parser = new Hl7ItsParser(tokens);
			ParseTreeWalker walker = new ParseTreeWalker();
			ArtDecor2JavaGenerator listener = new ArtDecor2JavaGenerator(baseCda, templateIndex,
					fileHeader);

			walker.walk(listener, parser.template());

			System.out.println("Template Name = " + listener.getTemplateName() + " --> "
					+ listener.getTemplateNameCamelCase());

			listener.hl7ElementName = hl7ElementName;

			String baseClassName = "";
			if (!"".equals(hl7ElementName))
				baseClassName = getBaseClassName(hl7ElementName);

			// JavaClassGenerator jcg = new JavaClassGenerator(baseClassName,
			// listener.getTemplateNameCamelCase(),
			// "org.ehealth_connector.cda.ch.poc",
			// "C:\\src\\ehcincubator\\art-decor-cda-2-java\\ehealthconnector\\ehealth_connector-cda\\ehealth_connector-cda-ch\\src\\main\\java\\org\\ehealth_connector\\cda\\ch\\poc",
			// listener.getTemplateDesc(), codeFormatter, fileHeader);
			// for (CdaMember cdaMember : listener.getCda()) {
			// System.out.println(cdaMember.getName() + " (" +
			// cdaMember.getHl7XmlType() + ")");
			// jcg.addMember(cdaMember);
			// List<CdaMember> components = cdaMember.listComponents();
			// if (components != null) {
			// System.out.println("components:");
			// for (CdaMember component : components) {
			// System.out.println(" - " + component.getName() + " ("
			// + component.getHl7XmlType() + ")");
			// jcg.addComponent(component);
			// }
			// }
			// }
			// if (jcg != null)
			// try {
			// jcg.saveToFile();
			// retVal = jcg.getGeneratedClassName();
			// templateIndex.put(templateId, retVal);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

	private static String getBaseClassName(String elementName) {
		String retVal = "String";

		// TODO this needs to get dynamic
		if (elementName == null)
			elementName = "hl7:author";

		switch (elementName) {
		case "hl7:authenticator":
			retVal = "POCDMT000040Authenticator";
			break;
		case "hl7:author":
			retVal = "POCDMT000040Author";
			break;
		case "ClinicalDocument":
			retVal = "POCDMT000040ClinicalDocument";
			break;
		default:
			retVal = "BaseClassNameUndefined";
			break;
		}
		return retVal;
	}

	public final static void main(String[] args) throws SaxonApiException {

		doOneTemplate(new ArrayList<CdaMember>(), "C:\\temp\\test\\2.16.756.5.30.1.1.10.1.10\\",
				"2.16.756.5.30.1.1.10.1.10", null, "ClinicalDocument",
				JavaCodeGenerator.getFileHeader());
	}

	public static String toCamelCase(String s) {
		String temp = s.replace("-", " ");
		String[] parts = temp.split(" ");
		String camelCaseString = "";
		for (String part : parts) {
			if (part != null && part.trim().length() > 0)
				camelCaseString = camelCaseString + toProperCase(part);
			else
				camelCaseString = camelCaseString + part + " ";
		}
		return camelCaseString.replace(" ", "");
	}

	public static String toProperCase(String s) {
		String temp = s.trim();
		String spaces = "";
		if (temp.length() != s.length()) {
			int startCharIndex = s.charAt(temp.indexOf(0));
			spaces = s.substring(0, startCharIndex);
		}
		temp = temp.substring(0, 1).toUpperCase() + spaces + temp.substring(1).toLowerCase() + " ";
		return temp;

	}

	private String hl7ElementName = "notset";

	private HashMap<String, String> templateIndex = null;

	private List<CdaMember> cda = new ArrayList<CdaMember>();
	private List<CdaMember> baseCda = null;

	private String templateName = null;
	private String templateDesc = null;
	private String templateNameCamelCase = null;
	private CdaMember currentCdaMember = null;
	private boolean isAddingComponent = false;
	private String fileHeader;

	public ArtDecor2JavaGenerator(List<CdaMember> baseCda, HashMap<String, String> templateIndex,
			String fileHeader) {
		if (templateIndex == null)
			this.templateIndex = new HashMap<String, String>();
		else
			this.templateIndex = templateIndex;
		this.fileHeader = fileHeader;
		this.baseCda = baseCda;
	}

	@Override
	public void enterAttribute(Hl7ItsParser.AttributeContext ctx) {
		NameAttrContext nameAttrCtx = ctx.nameAttr();
		String attrName = null;

		if (nameAttrCtx != null)
			attrName = nameAttrCtx.AttrValue().getText().replace("\"", "");

		String attrValue = null;
		ValueAttrContext valueAttrCtx = ctx.valueAttr();

		if (valueAttrCtx != null)
			attrValue = valueAttrCtx.AttrValue().getText().replace("\"", "");

		if (attrValue != null) {
			if (!"".equals(attrValue)) {
				// System.out.println(attrName + "=" + attrValue);
				if (currentCdaMember != null)
					currentCdaMember.addFixedContent(attrName, attrValue);
			}
		}
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
		if (currentCdaMember == null)
			setTemplateDesc(desc);
		else
			currentCdaMember.setDesc(desc);
	}

	@Override
	public void enterElement(Hl7ItsParser.ElementContext ctx) {
		String elementName = "UNKNOWN";

		NameAttrContext nameAttrCtx = ctx.nameAttr();

		if (nameAttrCtx != null)
			elementName = nameAttrCtx.AttrValue().getText().replace("\"", "");

		DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
		String dataType = "";
		if (dataTypeAttrCtx != null)
			dataType = dataTypeAttrCtx.AttrValue().getText().replace("\"", "");

		MinOccursAttrContext minOccursAttrCtx = ctx.minOccursAttr();
		int minOccurs = 1;
		if (minOccursAttrCtx != null)
			minOccurs = Integer.parseInt(minOccursAttrCtx.AttrValue().getText().replace("\"", ""));

		MaxOccursAttrContext maxOccursAttrCtx = ctx.maxOccursAttr();
		int maxOccurs = 1;
		if (maxOccursAttrCtx != null) {
			String temp = maxOccursAttrCtx.AttrValue().getText().replace("\"", "");
			if ("*".equals(temp))
				temp = "-1";
			maxOccurs = Integer.parseInt(temp);
		}

		ContainsAttrContext containsAttrCtx = ctx.containsAttr();
		String contains = "";
		if (containsAttrCtx != null) {
			contains = containsAttrCtx.AttrValue().getText().replace("\"", "");
			System.out.println(elementName + " contains " + contains);
			if (templateIndex.containsKey(contains)) {
				System.out
						.println(contains + " is already known as " + templateIndex.get(contains));
				dataType = templateIndex.get(contains);
			} else {
				if ("hl7:authenticator".equals(elementName))
					try {
						dataType = doOneTemplate(new ArrayList<CdaMember>(),
								"C:\\temp\\test\\2.16.756.5.30.1.1.10.1.10\\kit\\", contains,
								templateIndex, elementName, fileHeader);
					} catch (SaxonApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}

		if ("hl7:ClinicalDocument".equals(elementName))
			elementName = "";

		if ("hl7:component".equals(elementName)) {
			elementName = "";
			isAddingComponent = true;
		}

		if (!"".equals(elementName)) {
			CdaMember cdaMember = new CdaMember(elementName);
			cdaMember.setHl7ElementName(hl7ElementName);
			cdaMember.setHl7XmlType(dataType);
			cdaMember.setMinOccurs(minOccurs);
			cdaMember.setMaxOccurs(maxOccurs);
			if (isAddingComponent)
				currentCdaMember.addComponent(cdaMember);
			else {
				cda.add(cdaMember);
				currentCdaMember = cdaMember;
			}
		}
	}

	@Override
	public void enterInclude(Hl7ItsParser.IncludeContext ctx) {
		MinOccursAttrContext minOccursAttrCtx = ctx.minOccursAttr();
		int minOccurs = 1;
		if (minOccursAttrCtx != null)
			minOccurs = Integer.parseInt(minOccursAttrCtx.AttrValue().getText().replace("\"", ""));

		MaxOccursAttrContext maxOccursAttrCtx = ctx.maxOccursAttr();
		int maxOccurs = 1;
		if (maxOccursAttrCtx != null) {
			String temp = maxOccursAttrCtx.AttrValue().getText().replace("\"", "");
			if ("*".equals(temp))
				temp = "-1";
			maxOccurs = Integer.parseInt(temp);
		}

		RefAttrContext refAttrCtx = ctx.refAttr();
		String ref = "";
		String dataType = "";
		if (refAttrCtx != null) {
			ref = refAttrCtx.AttrValue().getText().replace("\"", "");
			System.out.println("include " + ref);
			if (templateIndex.containsKey(ref)) {
				System.out.println(ref + " is already known as " + templateIndex.get(ref));
				dataType = templateIndex.get(ref);
			} else {
				if ("2.16.756.5.30.1.1.10.2.59".equals(ref))
					try {
						dataType = doOneTemplate(baseCda,
								"C:\\temp\\test\\2.16.756.5.30.1.1.10.1.10\\kit\\", ref,
								templateIndex, null, fileHeader);
					} catch (SaxonApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}

		if (!"".equals(dataType)) {
			// TODO this needs to become dynamic
			String elementName = "author";
			CdaMember cdaMember = new CdaMember(elementName);
			cdaMember.setHl7ElementName(hl7ElementName);
			cdaMember.setHl7XmlType(dataType);
			cdaMember.setMinOccurs(minOccurs);
			cdaMember.setMaxOccurs(maxOccurs);
			baseCda.add(cdaMember);
		}
	}

	@Override
	public void enterTemplate(Hl7ItsParser.TemplateContext ctx) {
		NameAttrContext tnctx = ctx.nameAttr();
		if (tnctx != null) {
			if (tnctx.AttrValue() != null) {
				templateName = tnctx.AttrValue().getText().replace("\"", "");
				templateNameCamelCase = toCamelCase(templateName);
			}
		}
	}

	@Override
	public void exitElement(Hl7ItsParser.ElementContext ctx) {
		String elementName = "UNKNOWN";
		NameAttrContext nameAttrCtx = ctx.nameAttr();
		if (nameAttrCtx != null)
			elementName = nameAttrCtx.AttrValue().getText().replace("\"", "");

		if ("hl7:component".equals(elementName)) {
			isAddingComponent = false;
		}
	}

	public List<CdaMember> getCda() {
		return cda;
	}

	public String getHl7ElementName() {
		return hl7ElementName;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getTemplateNameCamelCase() {
		return templateNameCamelCase;
	}

	public void setCda(List<CdaMember> cda) {
		this.cda = cda;
	}

	public void setHl7ElementName(String hl7ElementName) {
		this.hl7ElementName = hl7ElementName;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setTemplateNameCamelCase(String templateNameCamelCase) {
		this.templateNameCamelCase = templateNameCamelCase;
	}

}
