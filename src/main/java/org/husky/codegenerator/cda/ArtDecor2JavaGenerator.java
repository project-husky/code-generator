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

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.javadoc.Javadoc;
import net.sf.saxon.s9api.SaxonApiException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.husky.codegenerator.cda.antlr.Hl7ItsLexer;
import org.husky.codegenerator.cda.antlr.Hl7ItsParser;
import org.husky.codegenerator.cda.antlr.Hl7ItsParser.*;
import org.husky.codegenerator.cda.antlr.Hl7ItsParserBaseListener;
import org.husky.codegenerator.cda.config.ContentProfileConfig;
import org.husky.codegenerator.cda.config.ContentProfilePackageConfig;
import org.husky.codegenerator.cda.enums.ProcessModes;
import org.husky.codegenerator.cda.model.CdaAttribute;
import org.husky.codegenerator.cda.model.CdaElement;
import org.husky.codegenerator.cda.model.CdaTemplate;
import org.husky.codegenerator.cda.rest.ArtDecorRestClient;
import org.husky.codegenerator.cda.xslt.Hl7Its2EhcTransformer;
import org.husky.codegenerator.java.JavaCodeGenerator;
import org.husky.codegenerator.valuesets.UpdateValueSets;
import org.husky.codegenerator.valuesets.ValueSetUtil;
import org.husky.common.model.Code;
import org.husky.common.basetypes.CodeBaseType;
import org.husky.common.hl7cdar2.ObjectFactory;
import org.husky.common.utils.Util;
import org.husky.valueset.api.ValueSetManager;
import org.husky.valueset.config.ValueSetConfig;
import org.husky.valueset.enums.SourceFormatType;
import org.husky.valueset.enums.SourceSystemType;
import org.husky.valueset.exceptions.InitializationException;
import org.husky.valueset.model.ValueSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.annotation.processing.Generated;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static com.github.javaparser.ast.Modifier.*;

/**
 * <div class="en">This is the main class of the ART-DECOR to Java Code Generator. It orchestrates
 * the individual modules (such as REST Client to ART-DECOR, XSLT, ANTLR and the final Java Class
 * file creation). This class generates Java Classes for all templates used for a HL7 CDA Document
 * Template modeled in ART-DECOR. <a
 * href="https://gitlab.com/ehealth-connector/api/-/wikis/ART-DECOR-to-Java-Code-Generator">See the
 * wiki</a> for additional information on how to use this class.</div>
 *
 * <p><div class="de">Dies ist die Hauptklasse des ART-DECOR to Java Code Generators. Sie
 * orchestriert die einzelnen Module (z. B. REST-Client to ART-DECOR, XSLT, ANTLR und die endgültige
 * Erstellung der Java-Klassendateien). Diese Klasse generiert Java-Klassen für alle HL7 CDA
 * Document Templates, die in ART-DECOR modelliert sind.</div>
 */
public class ArtDecor2JavaGenerator extends Hl7ItsParserBaseListener {

    /**
     * The log.
     */
    protected static final Logger LOG = LoggerFactory.getLogger(ArtDecor2JavaGenerator.class);
    /**
     * Placeholder for pending actions of the Code Generator.
     */
    private static final String PENDING_ACTIONS = "Pending actions: ";
    /**
     * Placeholder for pending actions of the Code Generator.
     */
    private static final String PENDING_ACTIONS_ADJUST_NAME = "Adjust name: ";
    /**
     * The data type index.
     */
    private static Map<String, String> dataTypeIndex = null;
    /**
     * The base url to ART-DECOR services.
     */
    private final URL artDecorBaseUrl;
    /**
     * The ART-DECOR prefix for the current ART-DECOR project.
     */
    private final String artDecorPrefix;
    /**
     * The file header to be placed in the generated classes.
     */
    private final String fileHeader;
    /**
     * The package name to be used in the generated classes.
     */
    private final String packageName;
    /**
     * The running template (used in the ANTLR4 parser).
     */
    private final CdaTemplate runningTemplate;
    /**
     * Flag for the initial run.
     */
    boolean initialRun;
    /**
     * The calling CDA element (used in the ANTLR4 parser).
     */
    private CdaElement callingCdaElement;
    /**
     * The current CDA attribute (used in the ANTLR4 parser).
     */
    private CdaAttribute currentCdaAttribute = null;
    /**
     * The current CDA element (used in the ANTLR4 parser).
     */
    private CdaElement currentCdaElement = null;
    /**
     * The current CDA template (used in the ANTLR4 parser).
     */
    private CdaTemplate currentCdaTemplate = null;
    /**
     * The destination path where to put the generated classes.
     */
    private String dstFilePath;
    /**
     * The full destination path where to put the generated classes.
     */
    private String fullDstFilePath;
    /**
     * The main CDA template (used in the ANTLR4 parser).
     */
    private CdaTemplate mainCdaTemplate = null;
    /**
     * The parent for contains (used in the ANTLR4 parser).
     */
    private CdaElement parentForContains;
    /**
     * The parent for includes (used in the ANTLR4 parser).
     */
    private CdaElement parentForIncludes;
    /**
     * The processing attribute (used in the ANTLR4 parser).
     */
    private int processingAttribute = 0;
    /**
     * The processing element (used in the ANTLR4 parser).
     */
    private int processingElement = 0;
    /**
     * The number of previously processing element (used in the ANTLR4 parser).
     */
    private int processingElementPrev = 0;
    /**
     * The flag whether processing a root include (used in the ANTLR4 parser).
     */
    private boolean processingRootInclude = false;
    /**
     * The number of processing templates (used in the ANTLR4 parser).
     */
    private int processingTemplate = 0;
    /**
     * The processing vocab (used in the ANTLR4 parser).
     */
    private int processingVocab = 0;
    /**
     * The source file path is where to look for template XMLs.
     */
    private String srcFilePath;
    /**
     * The template index (used in the ANTLR4 parser).
     */
    private final Map<String, CdaTemplate> templateIndex;
    /**
     * The template list (used in the ANTLR4 parser).
     */
    private final List<CdaTemplate> templateList;
    /**
     * The value set index (used in the ANTLR4 parser).
     */
    private final Map<String, String> valueSetIndex;

    /**
     * <div class="en">Constructor for the ART-DECOR to Java Generator.</div>
     *
     * <p><div class="de">Constructor des ART-DECOR to Java Generators.</div>
     *
     * @param callingCdaElement the calling cda element
     * @param templateIndex     the template index
     * @param valueSetIndex     the value set index
     * @param templateList      the template list
     * @param srcFilePath       the src file path
     * @param dstFilePath       the dst file path
     * @param packageName       the package name
     * @param fileHeader        the file header
     * @param artDecorPrefix    the art decor prefix
     * @param artDecorBaseUrl   the art decor base url
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public ArtDecor2JavaGenerator(
            final CdaElement callingCdaElement,
            final Map<String, CdaTemplate> templateIndex,
            final Map<String, String> valueSetIndex,
            final List<CdaTemplate> templateList,
            final String srcFilePath,
            final String dstFilePath,
            final String packageName,
            final String fileHeader,
            final String artDecorPrefix,
            final URL artDecorBaseUrl)
            throws IOException {
        this.callingCdaElement = callingCdaElement;
        this.artDecorPrefix = artDecorPrefix;
        this.artDecorBaseUrl = artDecorBaseUrl;
        this.templateIndex = Objects.requireNonNullElseGet(templateIndex, HashMap::new);
        this.valueSetIndex = Objects.requireNonNullElseGet(valueSetIndex, HashMap::new);
        this.templateList = Objects.requireNonNullElseGet(templateList, ArrayList::new);
        this.srcFilePath = srcFilePath;
        this.dstFilePath = dstFilePath;
        this.packageName = packageName;
        this.fileHeader = fileHeader;

        if (!this.dstFilePath.endsWith("/"))
            this.dstFilePath += "/";
        fullDstFilePath =
                new File(new File(dstFilePath, "src/main/java"), packageName.replaceAll("\\.", "/"))
                        .getAbsolutePath();

        if (!this.fullDstFilePath.endsWith("/"))
            this.fullDstFilePath += "/";

        initialRun = (callingCdaElement == null);
        if (initialRun) {
            FileUtils.deleteDirectory(new File(fullDstFilePath));
        }

        if (!this.srcFilePath.endsWith("/"))
            this.srcFilePath += "/";

        runningTemplate = new CdaTemplate();
        runningTemplate.setName("***Running***");
    }

    /**
     * Adds a comment to the constructor body.
     *
     * @param constructor The constructor
     * @param comment     The comment
     */
    private static void addBodyComment(final ConstructorDeclaration constructor, final String comment) {
        final BlockStmt body = constructor.getBody();
        body.addOrphanComment(new LineComment(comment));
    }

    /**
     * Adds a comment to the method body.
     *
     * @param method  The method
     * @param comment The comment
     */
    private static void addBodyComment(final MethodDeclaration method, final String comment) {
        final Optional<BlockStmt> bodyOpt = method.getBody();
        bodyOpt.ifPresent(blockStmt -> blockStmt.addOrphanComment(new LineComment(comment)));
    }

    /**
     * Adds a statement to the constructor body.
     *
     * @param constructor the constructor
     * @param statement   the statement
     */
    private static void addBodyStatement(final ConstructorDeclaration constructor, final String statement) {
        final BlockStmt body = constructor.getBody();
        body.addStatement(statement);
    }

    /**
     * Completes the creator method for fixed contents.
     *
     * @param creatorForFixedContentsMethod the creator for fixed contents method
     */
    private static void completeCreatorForFixedContentsMethod(final MethodDeclaration creatorForFixedContentsMethod) {
        Objects.requireNonNull(creatorForFixedContentsMethod);
        final Optional<BlockStmt> bodyOpt = creatorForFixedContentsMethod.getBody();
        if (bodyOpt.isPresent()) {
            final BlockStmt body = bodyOpt.get();
            // remove probably earlier added statements
            final ArrayList<Statement> statements = new ArrayList<>();
            for (final Statement stmt : body.getStatements()) {
                if ("return retVal;".equals(stmt.toString())) {
                    statements.add(stmt);
                }
            }
            for (final Statement statement2 : statements) {
                body.getStatements().remove(statement2);
            }
            body.addStatement("return retVal;");
        }
    }

    /**
     * Creates the method for adding the given element to the resulting class.
     *
     * @param compilationUnit the compilation unit
     * @param myClass         the my class
     * @param cdaElement      the cda element
     */
    private static void createAdder(final CompilationUnit compilationUnit,
                                    final ClassOrInterfaceDeclaration myClass,
                                    final CdaElement cdaElement) {
        if (cdaElement.getDataType() == null) {
            throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());
        }

        final MethodDeclaration method = myClass.addMethod(
                        "add" + JavaCodeGenerator.toPascalCase(cdaElement.getJavaName()),
                        publicModifier().getKeyword());

        String comment = "Adds a " + cdaElement.getJavaName();
        final String desc = cdaElement.getDescription();
        if (desc != null) {
            comment += "/" + desc;
        }

        method.setJavadocComment(comment);
        method.addAndGetParameter(cdaElement.getDataType(), "value");

        String name = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");
        Class<?> memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
        boolean isField = (memberType != null);
        if (!isField) {
            memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
        }

        boolean isLocalField = myClass.getExtendedTypes().isEmpty();

        final BlockStmt body = method.createBody();
        if (isClassCollection(memberType)) {
            if (isLocalField) {
                body.addStatement(name + ".add(value);");
            } else {
                name = toUpperFirstChar(name);
                body.addStatement(createGetterNamePascalCase(name) + "().add(value);");
            }
        } else {
            if (cdaElement.getDataType().endsWith(".ENXP")) {
                String enPartL =
                        cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");
                String enPartU = toUpperFirstChar(enPartL);

                compilationUnit.addImport("javax.xml.namespace.QName");
                compilationUnit.addImport("org.husky.common.hl7cdar2.En" + enPartU);
                compilationUnit.addImport("javax.xml.bind.JAXBElement");

                body.addStatement("En" + enPartU + " obj = new En" + enPartU + "();");
                body.addStatement("obj.xmlContent = value.xmlContent;");
                body.addStatement(
                        "getContent().add(new JAXBElement<En"
                                + enPartU
                                + ">(new QName(\"hl7:"
                                + enPartL
                                + "\"), En"
                                + enPartU
                                + ".class, obj));");
            } else {
                LOG.warn("{} is declared as list, but the XML Schema hosts it as single field. It is strongly " +
                        "recommended to correct the ART-DECOR model!", cdaElement.getFullXmlName());
                body.addStatement(name + "= value;");
            }
        }
    }

    /**
     * Creates the name for the method for adding the given element to the resulting class.
     *
     * @param name the name
     * @return the string
     */
    private static String createAdderNamePascalCase(final String name) {
        return "add" + JavaCodeGenerator.toPascalCase(name);
    }

    /**
     * Creates the method for clearing the list of the given element to the resulting class.
     *
     * @param myClass    the my class
     * @param cdaElement the cda element
     */
    private static void createClearer(final ClassOrInterfaceDeclaration myClass, final CdaElement cdaElement) {
        if (cdaElement.getDataType() == null) {
            throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());
        }

        String name =
                cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");
        Class<?> memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
        boolean isField = (memberType != null);
        if (!isField) {
            memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
        }

        boolean isLocalField = myClass.getExtendedTypes().isEmpty();

        if (isClassCollection(memberType)) {
            MethodDeclaration method;

            method =
                    myClass.addMethod(
                            "clear" + JavaCodeGenerator.toPascalCase(cdaElement.getJavaName()),
                            publicModifier().getKeyword());

            String comment = "Adds a " + cdaElement.getJavaName();
            String desc = cdaElement.getDescription();
            if (desc != null) {
                comment += "/" + desc;
            }

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
     * @param myClass the class
     * @return the constructor declaration
     */
    private static ConstructorDeclaration createDefaultConstructor(final ClassOrInterfaceDeclaration myClass) {
        return myClass.addConstructor(publicModifier().getKeyword());
    }

    /**
     * Creates the field for the given element to the resulting class.
     *
     * @param compilationUnit the compilation unit
     * @param myClass         the my class
     * @param cdaElement      the cda element
     */
    private static void createField(final CompilationUnit compilationUnit,
                                    final ClassOrInterfaceDeclaration myClass,
                                    final CdaElement cdaElement) {

        if (cdaElement.getDataType() == null)
            throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

        String name =
                cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");
        String dataType = cdaElement.getDataType();
        if ("hl7:typeId".contentEquals(cdaElement.getXmlName())) {
            dataType = "org.husky.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
        }
        Class<?> memberType =
                getDeclaredFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);

        Optional<FieldDeclaration> fieldOpt = myClass.getFieldByName(name);
        final FieldDeclaration field;
        if (fieldOpt.isPresent()) {
            field = fieldOpt.get();
        }
        else {
            if (isClassCollection(memberType)) {
                compilationUnit.addImport("java.util.ArrayList");
                compilationUnit.addImport("java.util.List");
                field = myClass.addPrivateField("List<" + dataType + ">", name);
                field.getVariable(0).setInitializer("new ArrayList<" + dataType + ">()");
            } else {
                field = myClass.addPrivateField(dataType, name);
            }

            // We do not want to have this member serialized!
            compilationUnit.addImport("javax.xml.bind.annotation.XmlTransient");
            field.addAnnotation(createXmlTransientAnnotation());
        }
        String comment = null;
        Optional<JavadocComment> jdocOpt = field.getJavadocComment();
        if (jdocOpt.isPresent()) {
            comment = jdocOpt.get().getContent();
        }

        String desc = cdaElement.getDescription();
        if (desc != null)
            if (comment == null) {
                comment = desc;
            } else {
                comment = desc + "/" + comment;
            }
        if (comment == null) {
            comment = "No description available in the ART-DECOR model for this field.";
        }
        field.setJavadocComment(comment);
    }

    /**
     * Creates the method for getting the given element to the resulting class.
     *
     * @param compilationUnit the compilation unit
     * @param myClass    the my class
     * @param cdaElement the cda element
     */
    private static void createGetter(final CompilationUnit compilationUnit,
                                     final ClassOrInterfaceDeclaration myClass,
                                     final CdaElement cdaElement) {

        if (cdaElement.getDataType() == null)
            throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());

        MethodDeclaration method;

        String dataType = cdaElement.getDataType();
        if ("hl7:typeId".equals(cdaElement.getXmlName())) {
            dataType = "org.husky.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
        }

        String name =
                cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

        CdaElement elForType = cdaElement.getParentCdaElement();
        if (elForType == null) {
            elForType = cdaElement;
        }
        Class<?> memberType = getFieldDatatype(elForType.getDataType(), name);
        if (memberType == null) {
            memberType = getMethodDatatype(elForType.getDataType(), name);
        }
        if (memberType == null && cdaElement.getDataType().endsWith(".ADXP")) {
            memberType = getClass(cdaElement.getDataType());
        }
        if (isClassCollection(memberType)) {

            boolean isLocalField = myClass.getExtendedTypes().isEmpty();

            if ("effectiveTime".equals(name) && !isLocalField) {
                compilationUnit.addImport("org.husky.common.hl7cdar2.SXCMTS");
                dataType = "SXCMTS";
            }
            if ("value".equals(name)) {
                compilationUnit.addImport("org.husky.common.hl7cdar2.ANY");
                dataType = "ANY";
            }
            compilationUnit.addImport("java.util.List");
            dataType = "List<" + dataType + ">";
        }

        String baseName = cdaElement.getJavaName();
        List<MethodDeclaration> existingMethods =
                myClass.getMethodsByName(createGetterNamePascalCase(baseName));
        if (!existingMethods.isEmpty()) {
            String methodName = existingMethods.get(0).getNameAsString();
            String methodDataType = existingMethods.get(0).getType().asString();
            methodName =
                    methodName + JavaCodeGenerator.toPascalCase(getClassWithoutPackage(methodDataType));
            // prepare adjust name of first method
            if (!existBodyComment(existingMethods.get(0), PENDING_ACTIONS)) {
                addBodyComment(existingMethods.get(0), PENDING_ACTIONS);
                addBodyComment(existingMethods.get(0), PENDING_ACTIONS_ADJUST_NAME + methodName);
            }

            // adjust name of new method
            baseName = baseName + JavaCodeGenerator.toPascalCase(getClassWithoutPackage(dataType));
        }

        if (existingMethods.isEmpty()) {
            method = myClass.addMethod(createGetterNamePascalCase(baseName), publicModifier().getKeyword());
            method.setType(dataType);

            String comment = "Gets the " + cdaElement.getJavaName();
            String desc = cdaElement.getDescription();
            if (desc != null) {
                comment += "/" + desc;
            }

            method.setJavadocComment(comment);

            BlockStmt body = method.createBody();
            String genericType = getGenericFieldType(elForType.getDataType(), name);

            if (memberType != null) {
                String temp = name;
                String cast = "";
                if (isClassCollection(memberType)) {
                    body.addStatement("return " + name + ";");
                } else {
                    if (genericType.endsWith(".CD") && cdaElement.getDataType().endsWith(".CE")) {
                        cast = "(" + cdaElement.getDataType() + ")";
                    }
                    body.addStatement("return " + cast + temp + ";");
                }
            } else body.addStatement("return this;");
        }
    }

    /**
     * Creates the name for the method for getting the given element to the resulting class.
     *
     * @param name the name
     * @return the string
     */
    private static String createGetterNamePascalCase(final String name) {
        return "get" + JavaCodeGenerator.toPascalCase(name);
    }

    /**
     * Creates the name for the method for getting the given element to the resulting class.
     *
     * @param name the name
     * @return the string
     */
    private static String createGetterNameUpperFirstChar(final String name) {
        return "get" + toUpperFirstChar(name);
    }

    /**
     * Creates the methods initFirstVersion, initNextVersion and setVersion and to the resulting class.
     *
     * @param compilationUnit the compilation unit
     * @param myClass         the my class
     */
    private static void createInitVersionMethods(final CompilationUnit compilationUnit,
                                                 final ClassOrInterfaceDeclaration myClass) {
        compilationUnit.addImport("org.husky.emed.cda.utils.CdaR2Utils");
        compilationUnit.addImport("org.husky.common.hl7cdar2.II");
        compilationUnit.addImport("java.util.UUID");

        MethodDeclaration method;
        BlockStmt body;

        // initFirstVersion
        method = myClass.addMethod("initFirstVersion", publicModifier().getKeyword());
        method.setJavadocComment(
                "Sets the version number to 1 and makes sure the setId is the same as the document id.\n@param newDocId the new doc id");
        method.addAndGetParameter("String", "newDocId");

        body = method.createBody();
        body.addStatement("II docId = new II();");
        body.addStatement("docId.setRoot(newDocId);");
        body.addStatement("""
                if (newDocId == null) {
                    docId.setRoot(UUID.randomUUID().toString());
                }
                """);
        body.addStatement("this.setId(docId);");
        body.addStatement("this.setVersion(docId.getRoot(), 1);");


        // initNextVersion
        method = myClass.addMethod("initNextVersion", publicModifier().getKeyword());
        method.setJavadocComment(
                "Increases the version number by one and makes sure the setId remains the same as previously.\n@param newDocId the new doc id");
        method.addAndGetParameter("String", "newDocId");

        body = method.createBody();
        body.addStatement("final var id = new II();");
        body.addStatement("id.setRoot(newDocId);");
        body.addStatement("II setId = this.getSetId();");
        body.addStatement("""
                if (setId == null) {
                    setId = this.getId();
                }
                """);
        body.addStatement("""
                if (setId == null) {
                    setId = id;
                }
                """);
        body.addStatement("Integer version = this.getVersionNumber().getValue().intValue();");
        body.addStatement("this.setId(id);");
        body.addStatement("this.setVersion(setId.getRoot(), version + 1);");

        // setVersion
        method = myClass.addMethod("setVersion", publicModifier().getKeyword());
        method.setJavadocComment(
                "<div class=\"en\">Sets the document set Id and version number.</div>\n\n<div class=\"de\">Weist dem Dokument eine Set Id und eine Versionsnummer zu.</div>\n@param idVersion1 the set Id (if null, the document ID will be used)\n@param version the version of the document");
        method.addAndGetParameter("String", "idVersion1");
        method.addAndGetParameter("int", "version");

        body = method.createBody();
        body.addStatement("final var id = new II();");
        body.addStatement("id.setRoot(idVersion1);");
        body.addStatement("super.setSetId(id);");
        body.addStatement("super.setVersionNumber(CdaR2Utils.createInt(version));");
    }

    /**
     * Creates the setter for the given element to the resulting class.
     *
     * @param compilationUnit the compilation unit
     * @param myClass         the my class
     * @param cdaElement      the cda element
     */
    private static void createSetter(final CompilationUnit compilationUnit,
                                     final ClassOrInterfaceDeclaration myClass,
                                     final CdaElement cdaElement) {
        if (cdaElement.getDataType() == null) {
            throw new NotImplementedException("Type undefined for " + cdaElement.getJavaName());
        }

        final var methodName = createSetterNamePascalCase(cdaElement.getJavaName());
        final var fieldName = cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

        String dataType = cdaElement.getDataType();
        if ("hl7:typeId".equals(cdaElement.getXmlName())) {
            dataType = "org.husky.common.hl7cdar2.POCDMT000040InfrastructureRootTypeId";
        }

        // Check if the setter with this name and data type already exists
        if (!myClass.getMethodsBySignature(methodName, dataType).isEmpty()) {
            return;
        }

        CdaElement elForType = cdaElement.getParentCdaElement();
        if (elForType == null) {
            elForType = cdaElement;
        }

        Class<?> memberType = getFieldDatatype(elForType.getDataType(), fieldName);
        boolean isMethod = false;
        if (memberType == null) {
            memberType = getMethodDatatype(elForType.getDataType(), fieldName);
            isMethod = (memberType != null);
        }
        if (memberType == null && dataType.endsWith(".ADXP")) {
            memberType = getClass(dataType);
        }
        if (myClass.getExtendedTypes().isEmpty()) {
            isMethod = false;
        }

        final MethodDeclaration method = myClass.addMethod(methodName, publicModifier().getKeyword());

        if (isClassCollection(memberType)) {
            boolean isLocalField = (myClass.getExtendedTypes().size() == 0);
            /*if ("effectiveTime".equals(name) && !isLocalField) {
                compilationUnit.addImport("org.husky.common.hl7cdar2.SXCMTS");
                dataType = "SXCMTS";
            }*/
        }

        String comment = "Sets the " + cdaElement.getJavaName();
        String desc = cdaElement.getDescription();
        if (desc != null) {
            comment += "<br/>\n" + desc;
        }

        method.setJavadocComment(comment);
        method.addAndGetParameter(dataType, "value");
        BlockStmt body = method.createBody();

        if (memberType != null) {
            String temp = fieldName;
            if (isClassCollection(memberType)) {
                if (isMethod) {
                    temp = createGetterNameUpperFirstChar(fieldName) + "()";
                }
                body.addStatement(temp + ".clear();");
                body.addStatement(temp + ".add(value);");
            } else {
                String cast = "";
                boolean doCast = false;

                if (doCast) {
                    cast = "(" + memberType.getName() + ")";
                }

                if (memberType.getName().endsWith(".IVLTS") && !(dataType.endsWith(".IVLTS"))) {
                    if (memberType.getName().endsWith(".IVLTS") && (dataType.endsWith(".TS"))) {
                        // Create Interval from single TimeStamp
                        compilationUnit.addImport("org.husky.common.hl7cdar2.IVLTS");
                        compilationUnit.addImport("org.husky.common.hl7cdar2.ObjectFactory");

                        body.addStatement("ObjectFactory factory = new ObjectFactory();");
                        body.addStatement("IVLTS ivlts = factory.createIVLTS();");
                        body.addStatement("ivlts.setValue(value.getValue());");
                        body.addStatement("this." + temp + " = ivlts;");
                    } else {
                        addBodyComment(method, memberType.getName() + " cannot be cast to " + dataType);
                        LOG.error("{}: {} cannot be cast to {}", cdaElement.getFullXmlName(), memberType.getName(),
                                dataType);
                    }
                } else {
                    body.addStatement("this." + temp + " = " + cast + "value;");
                }
            }
        } else {
            addBodyComment(method, "TODO: NYI");
        }
    }

    /**
     * Creates the name for the setter of the given element.
     *
     * @param name the name
     * @return the string
     */
    private static String createSetterNamePascalCase(final String name) {
        return "set" + JavaCodeGenerator.toPascalCase(name);
    }

    /**
     * Creates an XmlRootElement annotation.
     *
     * <p>Examples:
     *
     * <p>(at)XmlRootElement(name = "ClinicalDocument", namespace = "urn:hl7-org:v3")
     *
     * @param nameValue the name value
     * @return the normal annotation expr
     */
    private static NormalAnnotationExpr createXmlRootElementAnnotation(final String nameValue) {
        final NormalAnnotationExpr retVal = new NormalAnnotationExpr();
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
        final NormalAnnotationExpr retVal = new NormalAnnotationExpr();
        retVal.setName(new Name("XmlTransient"));
        return retVal;
    }

    /**
     * Checks whether the given body comment already exists in the resulting class.
     *
     * @param method  the method
     * @param comment the comment
     * @return true, if successful
     */
    private static boolean existBodyComment(final MethodDeclaration method, final String comment) {
        final Optional<BlockStmt> bodyOpt = method.getBody();
        if (bodyOpt.isPresent()) {
            for (Comment c : bodyOpt.get().getOrphanComments()) {
                if (c.asLineComment().getContent().equals(comment)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the class by the given name.
     *
     * @param className The class name
     * @return the class.
     */
    private static Class<?> getClass(final String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Gets the pure class name (without package name) of the class identified by the given name.
     *
     * @param className the class name
     * @return the class without package
     */
    private static String getClassWithoutPackage(final String className) {
        String retVal = className;
        while (retVal.contains(".")) {
            retVal = retVal.substring(retVal.indexOf(".") + 1);
        }
        return retVal;
    }

    /**
     * Gets the declared field data type of the given member in the given class.
     *
     * @param myClass    the my class
     * @param memberName the member name
     * @return the declared field datatype
     */
    private static Class<?> getDeclaredFieldDatatype(final Class<?> myClass, final String memberName) {
        Class<?> retVal = null;

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
     * Gets the declared field data type of the given member in the class identified by the given class name.
     *
     * @param className  the class name
     * @param memberName the member name
     * @return the declared field datatype
     */
    private static Class<?> getDeclaredFieldDatatype(final String className, final String memberName) {
        try {
           return getDeclaredFieldDatatype(Class.forName(className), memberName);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Gets the field data type of the given member in the class identified by the given class name.
     *
     * @param className  the class name
     * @param memberName the member name
     * @return the field datatype
     */
    private static Class<?> getFieldDatatype(final String className, final String memberName) {
        try {
            return Class.forName(className).getField(memberName).getType();
        } catch (NoSuchFieldException | SecurityException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Gets the field data type of the member identified by the given member name in the class identified by the given
     * class name.
     *
     * @param className  the class name
     * @param memberName the member name
     * @return the generic field type
     */
    private static String getGenericFieldType(final String className, final String memberName) {
        String retVal = "";

        try {
            Class<?> c = Class.forName(className);
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

            if (f != null && m != null) {
                f.setAccessible(true);
                try {
                    retVal = f.getGenericType().toString();
                } catch (IllegalArgumentException e) {
                    // Do nothing
                }
            }
        } catch (ClassNotFoundException e) {
            // Do nothing
        }

        return retVal;
    }

    /**
     * Gets the method data type of the method identified by the given method name in the class identified by the given
     * class name.
     *
     * @param className  the class name
     * @param methodName the method name
     * @return the method datatype
     */
    private static Class<?> getMethodDatatype(String className, String methodName) {
        try {
            return Class.forName(className)
                    .getMethod(createGetterNamePascalCase(JavaCodeGenerator.toPascalCase(methodName)))
                    .getReturnType();
        } catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Checks if the given class is a collection.
     *
     * @param c the c
     * @return true, if is class collection
     */
    private static boolean isClassCollection(final Class<?> c) {
        if (c == null) {
            return false;
        } else {
            return Collection.class.isAssignableFrom(c) || Map.class.isAssignableFrom(c);
        }
    }

    /**
     * Checks if the creator method for fixed contents is already complete in the resulting class.
     *
     * @param setterForFixedContentsMethod the setter for fixed contents method
     * @return true, if is complete creator for fixed contents method
     */
    private static boolean isCompleteCreatorForFixedContentsMethod(final MethodDeclaration setterForFixedContentsMethod) {
        final Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
        boolean retVal = false;
        if (bodyOpt.isPresent()) {
            final BlockStmt body = bodyOpt.get();
            for (final Statement stmt : body.getStatements()) {
                retVal = ("return retVal;".equals(stmt.toString()));
            }
        }
        return retVal;
    }

    /**
     * <div class="en">The main entry for the ART-DECOR to Java Code Generator.</div>
     *
     * <p><div class="de">Hauptzugang zum ART-DECOR to Java Code Generator.</div>
     *
     * @param javaSourceDir The Java source directory.
     * @param packageConfig The package config file.
     */
    public static void generate(final File javaSourceDir,
                                final File packageConfig) {
        LOG.info("ArtDecor2JavaGenerator started");

        final File tempDownloadPath = new File(Util.getTempDirectory() + "/eHC_Arde_Download/");

        LOG.info("Settings:");
        LOG.info("Java source dir: {}", javaSourceDir.getAbsolutePath());
        LOG.info("Package config : {}", packageConfig.getAbsolutePath());
        LOG.info("Temp dir       : {}", tempDownloadPath.getAbsolutePath());

        try {
            FileUtils.deleteDirectory(tempDownloadPath);

            // Load Config
            LOG.info("Configuration:");
            LOG.info("Loading configuration...");
            final ArtDecor2JavaManager artDecor2JavaManager2 = new ArtDecor2JavaManager();
            final ContentProfilePackageConfig contentProfilePackageConfig =
                    artDecor2JavaManager2.loadContentProfilePackageConfig(packageConfig.getAbsolutePath());
            LOG.info("Loading configuration done.");

            LOG.debug("Loaded configuration:");
            for (final ContentProfileConfig contentProfile : contentProfilePackageConfig.getContentProfileConfigList()) {
                LOG.debug("- Target namespace: {}", contentProfile.getTargetNamespace());
                for (final String templateId : contentProfile.getArtDecorDocTemplateMap().keySet()) {
                    LOG.debug("  - template id: {}", templateId);
                }
            }
            LOG.info("Configuration done.");

            // Perform REST calls
            LOG.info("Download from ART-DECOR");
            for (final ContentProfileConfig contentProfile : contentProfilePackageConfig.getContentProfileConfigList()) {
                String dir = tempDownloadPath.getAbsolutePath() + "/" + contentProfile.getTargetNamespace() + "/";
                ArtDecorRestClient artDecorRestClient = new ArtDecorRestClient(contentProfile.getArtDecorProjectMap(), dir);

                for (final String templateId : contentProfile.getArtDecorDocTemplateMap().keySet()) {
                    final String effectiveTime = contentProfile.getArtDecorDocTemplateMap().get(templateId);
                    artDecorRestClient.downloadTemplateRecursive(templateId, effectiveTime);
                }
            }
            LOG.info("Download from ART-DECOR done.");

            // Perform Java code generation
            LOG.info("Perform Java code generation");
            final List<String> dstPathList = new ArrayList<>();
            for (final ContentProfileConfig contentProfile : contentProfilePackageConfig.getContentProfileConfigList()) {
                String srcFilePath = tempDownloadPath.getAbsolutePath() + "/" + contentProfile.getTargetNamespace() + "/";
                final Map<String, CdaTemplate> templateIndex = new HashMap<>();
                final Map<String, String> valueSetIndex = new HashMap<>();
                final List<CdaTemplate> templateList = new ArrayList<>();

                final ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
                        null,
                        templateIndex,
                        valueSetIndex,
                        templateList,
                        srcFilePath,
                        javaSourceDir.getAbsolutePath(),
                        contentProfile.getTargetNamespace(),
                        JavaCodeGenerator.getFileHeader(),
                        contentProfile.getArtDecorMainPrefix(),
                        new URL(contentProfile.getArtDecorMainBaseUrl())
                );
                for (final String templateId : contentProfile.getArtDecorDocTemplateMap().keySet()) {
                    artDecor2JavaGenerator.prepareForAnotherTemplate();
                    artDecor2JavaGenerator.doOneTemplate(templateId);
                }
                artDecor2JavaGenerator.createJavaClasses();
                if (!dstPathList.contains(artDecor2JavaGenerator.getFullDstFilePath())) {
                    dstPathList.add(artDecor2JavaGenerator.getFullDstFilePath());
                }
                if (!dstPathList.contains(artDecor2JavaGenerator.getFullDstFilePath() + "/enums")) {
                    dstPathList.add(artDecor2JavaGenerator.getFullDstFilePath() + "enums/");
                }
            }
            LOG.info("Java code generation done.");
            FileUtils.deleteDirectory(tempDownloadPath);
            LOG.info("Generated Java classes and enums in the following folders:");
            for (final String path : dstPathList) {
                LOG.info("- {}", path);
            }
            LOG.info("ArtDecor2JavaGenerator finished.");
        } catch (final Exception e) {
            LOG.error("Uncaught exception: ", e);
        }

        // Clean behind the script
        try {
            FileUtils.deleteDirectory(tempDownloadPath);
        } catch (final Exception ignored) {
        }
    }

    /**
     * Prints the given CDA attribute list to the console.
     *
     * @param intend   the intend
     * @param attrList the attr list
     */
    private static void printCdaAttributes(final String intend, final List<CdaAttribute> attrList) {
        for (final CdaAttribute attr : attrList) {
            LOG.info(
                    "{} {} = {} (dataType: {})", intend, attr.getName(), attr.getValue(), attr.getDataType());
        }
    }

    /**
     * Prints the given CDA element list recursively to the console.
     *
     * @param intend     the intend
     * @param cdaElement the cda element
     */
    private static void printCdaElementRecursive(final String intend, final CdaElement cdaElement) {
        LOG.info("{} - CdaElement Name = {} (dataType: {})", intend, cdaElement.getJavaName(),
                cdaElement.getDataType());
        printCdaAttributes(intend, cdaElement.getCdaAttributeList());
        for (final CdaElement item : cdaElement.getChildrenCdaElementList()) {
            printCdaElementRecursive(intend + "  ", item);
        }
    }

    /**
     * Uppercase the first character of the given string.
     *
     * @param value the value
     * @return the string
     */
    private static String toUpperFirstChar(final String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    /**
     * Performs some data type adjustments.
     *
     * @param dataType            the data type
     * @param containingClassName the name of the containing class
     * @return the string
     */
    private String adjustDataType(final String dataType, final String containingClassName) {
        String retVal = dataType;
        if (retVal != null) {

            if (retVal.endsWith("running")) {
                retVal = null;
            }
            else {
                if (retVal.startsWith("IVL_PQ")) {
                    retVal = "IVLPQ";
                }
                if (retVal.startsWith("EIVL_TS")) {
                    retVal = "EIVLTS";
                }
                if (retVal.startsWith("IVL_TS")) {
                    retVal = "IVLTS";
                }
                if (retVal.startsWith("TS.")) {
                    retVal = "TS";
                }
                if (retVal.startsWith("SXPR_TS")) {
                    retVal = "SXPRTS";
                }
                if (retVal.startsWith("SD.TEXT")) {
                    retVal = "ED";
                    if (containingClassName != null)
                        if (containingClassName.endsWith("Section")) {
                            retVal = "StrucDocText";
                        }
                }
                if (retVal.startsWith("INT.NONNEG")) {
                    retVal = "INT";
                }

                if (!templateIndex.containsKey(dataType) && !retVal.contains(".")) {
                    retVal = "org.husky.common.hl7cdar2." + retVal;
                }
            }
        }
        return retVal;
    }

    /**
     * Performs some value set adjustments.
     *
     * @param value   the value
     * @param myClass the my class
     * @return the string
     */
    private String adjustValueSet(final String value, final ClassOrInterfaceDeclaration myClass) {
        String retVal = value;
        String testEnum =
                value.replace(packageName + ".enums", "org.husky.common.hl7cdar2");
        try {
            String className = null;
            if (myClass != null) {
                className = myClass.getNameAsString();
            }
            Class<?> cl = Class.forName(adjustDataType(testEnum, className));
            if (cl != null) {
                retVal = cl.getName();
            }
        } catch (ClassNotFoundException e) {
            // Do nothing
        }

        return retVal;
    }

    /**
     * Creates the creator method for fixed element contents.
     *
     * @param compilationUnit            the compilation unit
     * @param myClass                    the my class
     * @param cdaElement                 the cda element
     * @param setterForFixedContentsName the setter for fixed contents name
     * @return the method declaration
     */
    private MethodDeclaration createCreatorForFixedContentsElement(
            CompilationUnit compilationUnit,
            ClassOrInterfaceDeclaration myClass,
            CdaElement cdaElement,
            String setterForFixedContentsName) {
        MethodDeclaration method =
                myClass.addMethod(
                        setterForFixedContentsName,
                        privateModifier().getKeyword(),
                        staticModifier().getKeyword());
        method.setJavadocComment(
                "Creates fixed contents for CDA Element " + cdaElement.getJavaName() + "\n\n");

        compilationUnit.addImport("org.husky.common.hl7cdar2.ObjectFactory");

        BlockStmt body = method.createBody();

        String dataType = adjustDataType(cdaElement.getDataType(), myClass.getNameAsString());

        String name =
                cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

        Class<?> memberType = getFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
        boolean isField = (memberType != null);
        boolean isMethod = false;
        if (!isField) {
            memberType = getMethodDatatype(cdaElement.getParentCdaElement().getDataType(), name);
            isMethod = (memberType != null);
        }
        if (!(isField || isMethod)) {
            if (cdaElement.getDataType().endsWith(".ENXP")) {
                method.setType(dataType);
                String enPartU = toUpperFirstChar(name);
                compilationUnit.addImport("org.husky.common.hl7cdar2.En" + enPartU);
                body.addStatement("En" + enPartU + " retVal = new En" + enPartU + "();");
            } else
                throw new RuntimeException(
                        name + " is neither an accessible field nor an accessible getter");
        } else {
            if (memberType.getName().endsWith("POCDMT000040InfrastructureRootTypeId")) {
                dataType = memberType.getName();
            }
            method.setType(dataType);
            body.addStatement("ObjectFactory factory = new ObjectFactory();");
            body.addStatement(
                    dataType
                            + " retVal = factory.create"
                            + dataType.replace("org.husky.common.hl7cdar2.", "")
                            + "();");
        }
        return method;
    }

    /**
     * Creates the assignment if fixed attribute value.
     *
     * @param compilationUnit the compilation unit
     * @param myClass         the my class
     * @param cdaElement      the cda element
     */
    private void createFixedAttributeValues(
            CompilationUnit compilationUnit, ClassOrInterfaceDeclaration myClass, CdaElement cdaElement) {

        if (cdaElement.getCdaAttributeList() != null) {
            final ConstructorDeclaration constructor =
                    myClass.getDefaultConstructor().orElseGet(() -> createDefaultConstructor(myClass));

            MethodDeclaration creatorForFixedContentsMethod = null;
            final List<String> arguments = new ArrayList<>();
            StringBuilder sb = new StringBuilder();

            boolean isAttributeOfTemplateRootElement = false;
            if (cdaElement.getTemplate().getCdaElementList().size() == 1)
                isAttributeOfTemplateRootElement =
                        (cdaElement.equals(cdaElement.getTemplate().getCdaElementList().get(0)));

            if (this.isElementAConsumableWithNaMaterial(cdaElement)) {
                addBodyStatement(constructor, "super.setConsumable(createHl7ConsumableNa());");
            }

            int i = 0;
            for (CdaAttribute cdaAttribute : cdaElement.getCdaAttributeList()) {
                String attrName = "notNamedAttribute";
                if (cdaAttribute.getName() == null) {
                    if ("cs".equals(cdaAttribute.getDataType())) cdaAttribute.setName("code");
                }
                if (cdaAttribute.getName() != null)
                    attrName =
                            cdaAttribute.getName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

                if ((cdaAttribute.getCode() != null) && (cdaAttribute.getValue() == null)) {
                    cdaAttribute.setValue(cdaAttribute.getCode().getCode());
                }

                if ((cdaAttribute.getValue() != null) || (cdaAttribute.getValueSetId() != null)) {
                    if (cdaElement.getJavaName() == null)
                        cdaElement.setJavaName(JavaCodeGenerator.toCamelCase(cdaElement.getXmlName()));

                    String creatorForFixedContentsName;
                    if (isAttributeOfTemplateRootElement) {
                        creatorForFixedContentsName =
                                "create" + JavaCodeGenerator.toPascalCase(attrName) + "FixedValue";

                    } else {
                        creatorForFixedContentsName =
                                "create" + JavaCodeGenerator.toPascalCase(cdaElement.getJavaName()) + "FixedValue";
                    }
                    List<MethodDeclaration> creatorForFixedContentsMethodList =
                            myClass.getMethodsByName(creatorForFixedContentsName);

                    if (!isAttributeOfTemplateRootElement) {
                        boolean creatorForFixedContentsExist = !creatorForFixedContentsMethodList.isEmpty();
                        if (!creatorForFixedContentsExist) {
                            creatorForFixedContentsMethod =
                                    createCreatorForFixedContentsElement(
                                            compilationUnit, myClass, cdaElement, creatorForFixedContentsName);
                        } else creatorForFixedContentsMethod = creatorForFixedContentsMethodList.get(0);

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
                                updateCreatorForFixedContentsMethod(
                                        compilationUnit, creatorForFixedContentsMethod, cdaElement, cdaAttribute);

                                if (i >= creatorForFixedContentsMethod.getParameters().size()) {
                                    arguments.add(sb.toString());
                                    sb = new StringBuilder();
                                    i = 0;
                                }
                                if (i < creatorForFixedContentsMethod.getParameters().size()) {
                                    i++;
                                    if (sb.length() != 0) sb.append(", ");
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
                                            temp = "\"" + cdaAttribute.getCode().getCodeSystem() + "\"";
                                        sb.append(temp);

                                        i++;
                                        sb.append(", ");
                                        temp = "null";
                                        if (cdaAttribute.getCode().getCodeSystemName() != null)
                                            temp = "\"" + cdaAttribute.getCode().getCodeSystemName() + "\"";
                                        sb.append(temp);

                                        i++;
                                        sb.append(", ");
                                        temp = "null";
                                        if (cdaAttribute.getCode().getDisplayName() != null)
                                            temp = "\"" + cdaAttribute.getCode().getDisplayName() + "\"";
                                        sb.append(temp);

                                    } else sb.append("\"" + cdaAttribute.getValue() + "\"");
                                }
                            }
                        }
                    }
                }

                String templateClassName =
                        JavaCodeGenerator.toPascalCase(cdaElement.getTemplate().getName());
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
                            isTemp = (cdaElement.equals(cdaElement.getTemplate().getCdaElementList().get(0)));
                        if (isTemp) {
                            Class<?> memberType = getDeclaredFieldDatatype(cdaElement.getDataType(), attrName);
                            String statement = "";

                            boolean isLocalField = myClass.getExtendedTypes().isEmpty();
                            if (isClassCollection(memberType)) {
                                if (isLocalField) {
                                    statement = attrName + ".add(" + "\"" + cdaAttribute.getValue() + "\"" + ");";
                                } else {
                                    statement =
                                            "super.get"
                                                    + toUpperFirstChar(attrName)
                                                    + "().add("
                                                    + "\""
                                                    + cdaAttribute.getValue()
                                                    + "\""
                                                    + ");";
                                }
                            } else {
                                if (isLocalField) {
                                    statement =
                                            "this." + attrName + " = " + "\"" + cdaAttribute.getValue() + "\"" + ";";
                                } else {
                                    String dataType = null;
                                    try {
                                        dataType = getDataType(cdaElement.getDataType(), attrName);
                                    } catch (ClassNotFoundException
                                            | NoSuchFieldException
                                            | SecurityException e) {
                                        throw new RuntimeException(
                                                "Unhandled exception while getting Datatype for "
                                                        + attrName
                                                        + "("
                                                        + cdaElement.getFullXmlName()
                                                        + ")");
                                    }

                                    boolean isEnum = false;
                                    if (dataType != null) isEnum = (!"java.lang.String".contentEquals(dataType));
                                    if (isEnum) {
                                        String enumName = memberType.getName();
                                        statement =
                                                "super.set"
                                                        + toUpperFirstChar(attrName)
                                                        + "("
                                                        + enumName
                                                        + "."
                                                        + cdaAttribute.getValue()
                                                        + ");";
                                    } else {
                                        if (cdaAttribute.getCode() != null) {
                                            if (cdaAttribute.getCode().getCode() != null) {
                                                statement =
                                                        "super.setCode"
                                                                + "("
                                                                + "\""
                                                                + cdaAttribute.getCode().getCode()
                                                                + "\""
                                                                + ");";
                                                addBodyStatement(constructor, statement);
                                            }
                                            if (cdaAttribute.getCode().getCodeSystem() != null) {
                                                statement =
                                                        "super.setCodeSystem"
                                                                + "("
                                                                + "\""
                                                                + cdaAttribute.getCode().getCodeSystem()
                                                                + "\""
                                                                + ");";
                                                addBodyStatement(constructor, statement);
                                            }
                                            if (cdaAttribute.getCode().getCodeSystemName() != null) {
                                                statement =
                                                        "super.setCodeSystemName"
                                                                + "("
                                                                + "\""
                                                                + cdaAttribute.getCode().getCodeSystemName()
                                                                + "\""
                                                                + ");";
                                                addBodyStatement(constructor, statement);
                                            }
                                            if (cdaAttribute.getCode().getDisplayName() != null) {
                                                statement =
                                                        "super.setDisplayName"
                                                                + "("
                                                                + "\""
                                                                + cdaAttribute.getCode().getDisplayName()
                                                                + "\""
                                                                + ");";
                                                addBodyStatement(constructor, statement);
                                            }
                                            statement = null;
                                        } else
                                            statement =
                                                    "super.set"
                                                            + toUpperFirstChar(attrName)
                                                            + "("
                                                            + "\""
                                                            + cdaAttribute.getValue()
                                                            + "\""
                                                            + ");";
                                    }
                                }
                            }
                            if (statement != null) addBodyStatement(constructor, statement);
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
                if (cdaAttribute.getCodeList() != null) {
                    if (!cdaAttribute.getCodeList().isEmpty()) {
                        String statement;
                        String elementName =
                                cdaElement
                                        .getXmlName()
                                        .replace("hl7:", "")
                                        .replace("pharm:", "")
                                        .replace("xsi:", "");
                        String fieldName =
                                "vocab" + toUpperFirstChar(elementName) + toUpperFirstChar(cdaAttribute.getName());
                        compilationUnit.addImport("org.husky.emed.cda.models.common.Code");
                        compilationUnit.addImport("org.husky.emed.cda.models.common.basetypes.CodeBaseType");
                        compilationUnit.addImport("java.util.ArrayList");
                        compilationUnit.addImport("java.util.List");
                        FieldDeclaration field = myClass.addPrivateField("List<Code>", fieldName);
                        field.getVariable(0).setInitializer("new ArrayList<>()");
                        field.setFinal(true);

                        String dataType = null;
                        try {
                            dataType = getDataType(cdaElement.getParentCdaElement(), templateIndex);
                        } catch (InstantiationException
                                | IllegalAccessException
                                | ClassNotFoundException
                                | NoSuchFieldException
                                | SecurityException
                                | IllegalArgumentException
                                | InvocationTargetException
                                | IOException e) {
                            throw new RuntimeException(
                                    "Unhandled exception while getting Datatype for "
                                            + attrName
                                            + "("
                                            + cdaElement.getFullXmlName()
                                            + ")");
                        }

                        Class<?> fieldDataClass = getDeclaredFieldDatatype(dataType, elementName);

                        boolean isList = isClassCollection(fieldDataClass);
                        boolean codeComplete = false;
                        String codeStatement = "";

                        for (Code code : cdaAttribute.getCodeList()) {
                            String myCode = code.getCode();
                            String myCodeSystem = code.getCodeSystem();
                            String myCodeSystemName = code.getCodeSystemName();
                            String myDisplayName = code.getDisplayName();

                            if ("CS".equals(fieldDataClass.getName()))
                                codeComplete = (myCode != null);
                            else codeComplete = ((myCode != null) && (myCodeSystem != null));

                            statement = fieldName + ".add(";

                            codeStatement = "new Code(CodeBaseType.builder()";

                            if (myCode != null) codeStatement += ".withCode(\"" + myCode + "\")";
                            if (myCodeSystem != null)
                                codeStatement += ".withCodeSystem(\"" + myCodeSystem + "\")";
                            if (myCodeSystemName != null)
                                codeStatement += ".withCodeSystemName(\"" + myCodeSystemName + "\")";
                            if (myDisplayName != null)
                                codeStatement += ".withDisplayName(\"" + myDisplayName + "\")";

                            codeStatement += ".build())";

                            statement += codeStatement + ");";

                            addBodyStatement(constructor, statement);
                        }

                        // This an optimization only. Remember that vocab
                        // elements are to define a vocabulary and not a fixed
                        // code.
                        // Though there are ART-DECOR models that define one
                        // only vocab element in order to suggest a fixed code.
                        // Therefore we optimize, here with the following rules:
                        // 1. If the vocabulary contains only one entry, we can
                        // imagine that this serves as a fixed value.
                        // 2. In any case, this entry is only used as a fixed
                        // value if the code is complete (one can for example
                        // just declare the code systems id without given codes
                        // and in this case, the code is not treated as a fixed
                        // value).
                        //
                        // Note: If you change anything here, make sure all
                        // previous CDA documents still validate (e.g.
                        // CDA-CH-EMED Prescription and MedicationList).
                        if ((cdaAttribute.getCodeList().size() == 1) && codeComplete) {
                            String temp;
                            String shortDatatype;
                            if (isList) {
                                shortDatatype =
                                        JavaCodeGenerator.toPascalCase(
                                                dataType.substring(dataType.length() - 2));
                                if ("translation".equals(elementName)) shortDatatype = "Cd";
                                temp = "(" + codeStatement + ").getHl7CdaR2" + shortDatatype + "()";
                                statement = "super.get" + toUpperFirstChar(elementName) + "().add(" + temp + ");";
                            } else {
                                shortDatatype =
                                        JavaCodeGenerator.toPascalCase(
                                                fieldDataClass
                                                        .getName()
                                                        .substring(
                                                                fieldDataClass.getName().length() - 2
                                                        ));
                                temp = "(" + codeStatement + ").getHl7CdaR2" + shortDatatype + "()";
                                statement = elementName + " = " + temp + ";";
                            }
                            addBodyStatement(constructor, statement);
                        }

                        MethodDeclaration method =
                                myClass.addMethod(
                                        "get" + JavaCodeGenerator.toPascalCase(fieldName),
                                        publicModifier().getKeyword());

                        String comment = "Returns a list of vocab codes as defined in the ART-DECOR model";
                        method.setJavadocComment(comment);
                        method.setType("List<Code>");
                        BlockStmt body = method.createBody();
                        body.addStatement("return " + fieldName + ";");
                    }
                }
            }

            arguments.add(sb.toString());
            sb = null;

            if (creatorForFixedContentsMethod != null) {

                for (String argsForThisCall : arguments) {

                    // fix for setHl7EntryRelationshipFixedValue:
                    // sometimes in the first occurrence, there is no
                    // inversionInd in the ART-DECOR model
                    int argCountMethod = creatorForFixedContentsMethod.getParameters().size();
                    if ("createHl7EntryRelationshipFixedValue"
                            .contentEquals(creatorForFixedContentsMethod.getNameAsString())) {
                        if (argCountMethod == 1) {
                            CdaAttribute attr = new CdaAttribute();
                            attr.setName("inversionInd");
                            updateCreatorForFixedContentsMethod(
                                    compilationUnit, creatorForFixedContentsMethod, cdaElement, attr);
                        }
                        argCountMethod = 2;
                    }
                    int argCountGiven =
                            org.apache.commons.lang3.StringUtils.countMatches(argsForThisCall, ",") + 1;

                    while (argCountGiven < argCountMethod) {
                        argsForThisCall += ", ";
                        argsForThisCall += "null";
                        argCountGiven =
                                org.apache.commons.lang3.StringUtils.countMatches(argsForThisCall, ",") + 1;
                    }
                    // end of fixes

                    if (!isAttributeOfTemplateRootElement) {
                        if (!isCompleteCreatorForFixedContentsMethod(creatorForFixedContentsMethod))
                            completeCreatorForFixedContentsMethod(creatorForFixedContentsMethod);

                        String creator =
                                creatorForFixedContentsMethod.getNameAsString() + "(" + argsForThisCall + ")";

                        if (cdaElement.getMinOccurs() == 1) {
                            // This is fixed content for a required element

                            String name =
                                    cdaElement
                                            .getXmlName()
                                            .replace("hl7:", "")
                                            .replace("pharm:", "")
                                            .replace("xsi:", "");

                            Class<?> memberType =
                                    getDeclaredFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);
                            String statement = "";

                            boolean isLocalField = myClass.getExtendedTypes().isEmpty();
                            if (isClassCollection(memberType)) {
                                if (isLocalField) {
                                    statement =
                                            cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
                                                    + ".add("
                                                    + creator
                                                    + ");";
                                } else {
                                    statement =
                                            "super.get"
                                                    + toUpperFirstChar(
                                                    cdaElement
                                                            .getXmlName()
                                                            .replace("hl7:", "")
                                                            .replace("pharm:", "")
                                                            .replace("xsi:", ""))
                                                    + "().add("
                                                    + creator
                                                    + ");";
                                }
                            } else {
                                if (isLocalField) {
                                    statement =
                                            "this."
                                                    + cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "")
                                                    + " = "
                                                    + creator
                                                    + ";";
                                } else {
                                    statement =
                                            "super.set"
                                                    + toUpperFirstChar(
                                                    cdaElement
                                                            .getXmlName()
                                                            .replace("hl7:", "")
                                                            .replace("pharm:", "")
                                                            .replace("xsi:", ""))
                                                    + "("
                                                    + creator
                                                    + ");";
                                }
                            }

                            addBodyStatement(constructor, statement);
                        }

                        if ((cdaElement.getMinOccurs() == 0) || (arguments.size() > 1)) {
                            // This is fixed content for an optional element
                            String methodName =
                                    "getPredefined"
                                            + JavaCodeGenerator.toPascalCase(
                                            cdaElement
                                                    .getXmlName()
                                                    .replace("hl7:", "")
                                                    .replace("pharm:", "")
                                                    .replace("xsi:", ""))
                                            + JavaCodeGenerator.toPascalCase(argsForThisCall);
                            List<MethodDeclaration> existingMethods = myClass.getMethodsByName(methodName);
                            if (existingMethods.isEmpty()) {
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

                                MethodDeclaration method =
                                        myClass.addMethod(
                                                methodName, publicModifier().getKeyword(), staticModifier().getKeyword());
                                method.setType(creatorForFixedContentsMethod.getType());

                                String comment =
                                        "Adds a predefined "
                                                + creatorForFixedContentsMethod.getTypeAsString()
                                                + ", filled by: "
                                                + argsForThisCall
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
                            addBodyComment(
                                    constructor,
                                    "TODO: fixed content for an element with multiple required elements not handled, yet: "
                                            + creatorForFixedContentsMethod.getNameAsString()
                                            + "("
                                            + argsForThisCall
                                            + ")");
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates the assignment for fixed content value.
     *
     * @param compilationUnit the compilation unit
     * @param myClass         the my class
     * @param cdaElement      the cda element
     */
    private void createFixedContentValues(
            final CompilationUnit compilationUnit,
            final ClassOrInterfaceDeclaration myClass,
            final CdaElement cdaElement) {
        // Initializes the content for an null-flavored consumable:
        // hl7:consumable/hl7:manufacturedProduct/hl7:manufacturedMaterial[nullFlavor="NA"]
        if (this.isElementAConsumableWithNaMaterial(cdaElement)) {
            // It's the right structure, add the creator to the generated class
            compilationUnit.addImport("org.husky.common.hl7cdar2.POCDMT000040Consumable");
            compilationUnit.addImport(
                    "org.husky.common.hl7cdar2.POCDMT000040ManufacturedProduct");
            compilationUnit.addImport("org.husky.common.hl7cdar2.POCDMT000040Material");

            MethodDeclaration method =
                    myClass.addMethod(
                            "createHl7ConsumableNa",
                            privateModifier().getKeyword(),
                            staticModifier().getKeyword());
            method.setJavadocComment(
                    "Creates fixed contents for CDA Element hl7Consumable, "
                            + "containing an hl7ManufacturedMaterial with a null flavor NA.\n");
            method.setType("POCDMT000040Consumable");

            BlockStmt body = method.createBody();
            body.addStatement("final POCDMT000040Material material = new POCDMT000040Material();");
            body.addStatement("material.getNullFlavor().add(\"NA\");");
            body.addStatement(
                    "final POCDMT000040ManufacturedProduct product = new POCDMT000040ManufacturedProduct();");
            body.addStatement("product.setManufacturedMaterial(material);");
            body.addStatement("final POCDMT000040Consumable consumable = new POCDMT000040Consumable();");
            body.addStatement("consumable.setManufacturedProduct(product);");
            body.addStatement("return consumable;");
        }
    }

    /**
     * <div class="en">Checks whether a CDA element is a consumable containing a NA material.</div>
     *
     * @param cdaElement The CDA element to check.
     * @return whether the CDA element is a consumable containing a NA material or not.
     */
    private boolean isElementAConsumableWithNaMaterial(final CdaElement cdaElement) {
        if ("hl7:consumable".equals(cdaElement.getXmlName())) {
            CdaElement manuProduct = null;
            CdaElement manuMaterial = null;
            String nullFlavor = null;
            if (!cdaElement.getChildrenCdaElementList().isEmpty()) {
                manuProduct =
                        cdaElement.getChildrenCdaElementList().stream()
                                .filter(child -> "hl7:manufacturedProduct".equals(child.getXmlName()))
                                .findAny()
                                .orElse(null);
            }
            if (manuProduct != null && !manuProduct.getChildrenCdaElementList().isEmpty()) {
                manuMaterial =
                        manuProduct.getChildrenCdaElementList().stream()
                                .filter(child -> "hl7:manufacturedMaterial".equals(child.getXmlName()))
                                .findAny()
                                .orElse(null);
            }
            if (manuMaterial != null && !manuMaterial.getCdaAttributeList().isEmpty()) {
                nullFlavor =
                        manuMaterial.getCdaAttributeList().stream()
                                .filter(attr -> "nullFlavor".equals(attr.getName()))
                                .findAny()
                                .map(CdaAttribute::getValue)
                                .orElse(null);
            }
            return "NA".equals(nullFlavor);
        }
        return false;
    }

    /**
     * <div class="en">Creates all Java Classes as files.</div>
     *
     * <p><div class="de">Erstellt alle Java-Klassen in Dateien.</div>
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void createJavaClasses() throws IOException {
        regroupTemplateElements(templateList);
        LOG.debug("Writing Java Class files:");
        for (CdaTemplate cdaTemplate : templateList) {
            LOG.debug("- {}", cdaTemplate.getName());
            createJavaClassFile(cdaTemplate, packageName, fullDstFilePath);
        }
        LOG.debug("Writing Java Class files done.");
    }

    /**
     * Creates the Java class for the given CDA Template and writes it to file.
     *
     * @param cdaTemplate the cda template
     * @param packageName the package name
     * @param dstFilePath the dst file path
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void createJavaClassFile(CdaTemplate cdaTemplate, String packageName, String dstFilePath)
            throws IOException {

        // Save the current CDA Element as Java Class File
        CompilationUnit compilationUnit = new CompilationUnit();
        compilationUnit.setPackageDeclaration(packageName);
        compilationUnit.addImport(Generated.class);

        String className = JavaCodeGenerator.toPascalCase(cdaTemplate.getName());

        boolean isSingleElementTemplate = (cdaTemplate.getCdaElementList().size() == 1);

        for (CdaElement cdaElement : cdaTemplate.getCdaElementList()) {

            ClassOrInterfaceDeclaration myClass = null;
            Optional<ClassOrInterfaceDeclaration> classOpt = compilationUnit.getClassByName(className);
            if (classOpt.isPresent()) {
                myClass = classOpt.get();
            }
            if (myClass == null) {
                myClass = compilationUnit.addClass(className).setPublic(true);
            }

            String dataType = adjustDataType(cdaElement.getDataType(), myClass.getNameAsString());

            cdaElement.setDataType(dataType);

            boolean isCdaRootElement =
                    "org.husky.common.hl7cdar2.POCDMT000040ClinicalDocument"
                            .equals(cdaElement.getDataType());

            if (isCdaRootElement) {
                compilationUnit.addImport("javax.xml.bind.annotation.XmlRootElement");
                myClass.addAnnotation(createXmlRootElementAnnotation("ClinicalDocument"));
            }

            // Add @Generated annotation
            if (myClass.getAnnotationByClass(Generated.class).isEmpty()) {
                NormalAnnotationExpr generated =
                        new NormalAnnotationExpr(new Name("Generated"), new NodeList<>());
                generated.addPair(
                        "value",
                        new StringLiteralExpr(
                                "org.husky.codegenerator.cda.ArtDecor2JavaGenerator"));
                generated.addPair("date", new StringLiteralExpr(LocalDate.now().toString()));
                myClass.addAnnotation(generated);
            }

            StringBuilder javadoc = new StringBuilder();
            javadoc.append(String.format("%s\n", cdaTemplate.getName()));
            javadoc.append("<p>\n");
            if (cdaTemplate.getDescription() != null) {
                javadoc.append(String.format("Template description: %s<br>\n", cdaTemplate.getDescription()));
            }
            if (cdaElement.getDescription() != null) {
                javadoc.append(String.format("Element description: %s<br>\n", cdaElement.getDescription()));
            }
            javadoc.append("<p>\n");
            javadoc.append(String.format("Identifier: %s<br>\n", cdaTemplate.getId()));
            javadoc.append(String.format("Effective date: %s<br>\n", cdaTemplate.getEffectiveDate()));
            if (cdaTemplate.getVersionLabel() != null) {
                javadoc.append(String.format("Version: %s<br>\n", cdaTemplate.getVersionLabel()));
            }
            javadoc.append(String.format("Status: %s\n", cdaTemplate.getStatus()));

            myClass.setJavadocComment(javadoc.toString());
            String inheritanceOf = cdaTemplate.getDataType();

            // This is for Templates used as contains:
            if (inheritanceOf == null) {
                inheritanceOf = cdaElement.getParentCdaElement().getDataType();
            }

            // Inheritance does not work for AD elements.
            // This is by HL7 CDA Schema design ...
            if ("org.husky.common.hl7cdar2.AD".equals(inheritanceOf)) {
                inheritanceOf = null;
            }

            if (inheritanceOf != null) {
                compilationUnit.addImport(inheritanceOf);
                myClass.setExtendedTypes(new NodeList<>(new ClassOrInterfaceType(null,
                        getClassWithoutPackage(inheritanceOf))));
            }


            if (isSingleElementTemplate) {
                createFixedAttributeValues(compilationUnit, myClass, cdaElement);
                for (CdaElement cdaElement1 : cdaElement.getChildrenCdaElementList()) {
                    createMembers(compilationUnit, myClass, cdaElement1, (inheritanceOf == null));
                    createFixedAttributeValues(compilationUnit, myClass, cdaElement1);
                    createFixedContentValues(compilationUnit, myClass, cdaElement1);
                }
            } else {
                createMembers(compilationUnit, myClass, cdaElement, (inheritanceOf == null));
                createFixedAttributeValues(compilationUnit, myClass, cdaElement);
                createFixedContentValues(compilationUnit, myClass, cdaElement);
            }

            if ("org.husky.common.hl7cdar2.POCDMT000040ClinicalDocument"
                    .equals(cdaElement.getDataType())) {
                createInitVersionMethods(compilationUnit, myClass);
            }

            // complete pending actions
            for (MethodDeclaration method : myClass.getMethods()) {
                if (existBodyComment(method, PENDING_ACTIONS)) {
                    method.getBody().ifPresent(bodyOpt -> {
                        // Copy the list because calls to bodyOpt.removeOrphanComment(c) will modify it
                        final List<Comment> comments = new ArrayList<>(bodyOpt.getOrphanComments());
                        for (final Comment c : comments) {
                            if (c.asLineComment().getContent().equals(PENDING_ACTIONS))
                                bodyOpt.removeOrphanComment(c);
                            if (c.asLineComment().getContent().startsWith(PENDING_ACTIONS_ADJUST_NAME)) {
                                bodyOpt.removeOrphanComment(c);
                                String methodName = c.getContent().substring(PENDING_ACTIONS_ADJUST_NAME.length());
                                method.setName(methodName);
                            }
                        }
                    });
                }
            }

            File outFile = new File(fullDstFilePath + className + ".java");
            JavaCodeGenerator.completeAndSave(compilationUnit, outFile);
        }
    }

    /**
     * Creates the members of the given CDA element to the resulting class.
     *
     * @param compilationUnit   the compilation unit
     * @param myClass           the my class
     * @param cdaElement        the cda element
     * @param createOwnProperty the create own property
     */
    private void createMembers(final CompilationUnit compilationUnit,
                               final ClassOrInterfaceDeclaration myClass,
                               final CdaElement cdaElement,
                               final boolean createOwnProperty) {

        if (cdaElement.getDataType() == null)
            throw new NotImplementedException("Type undefined for " + cdaElement.getXmlName());

        String dataType = adjustDataType(cdaElement.getDataType(), myClass.getNameAsString());
        cdaElement.setDataType(dataType);

        String xmlName = cdaElement.getXmlName();
        String javaName = JavaCodeGenerator.toCamelCase(xmlName);
        cdaElement.setJavaName(javaName);

        boolean elementExist = existAdderOrSetter(myClass, cdaElement);

        if (!elementExist) {
            if (createOwnProperty) {
                createField(compilationUnit, myClass, cdaElement);
            }
            if (cdaElement.getMaxOccurs() > 1) {
                createAdder(compilationUnit, myClass, cdaElement);
                createClearer(myClass, cdaElement);
            } else {
                createGetter(compilationUnit, myClass, cdaElement);
                createSetter(compilationUnit, myClass, cdaElement);
            }
        }
    }

    /**
     * <div class="en">Performs Java Class generation for one ART-DECOR Document Template.</div>
     *
     * <p><div class="de">Führt die Java Class Generierung für ein ART-DECOR Document Template
     * durch.</div>
     *
     * @param templateId the template id
     * @return the cda template
     * @throws SaxonApiException         the saxon api exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws JAXBException             the JAXB exception
     * @throws ClassNotFoundException    the class not found exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InstantiationException    the instantiation exception
     * @throws NoSuchFieldException      the no such field exception
     * @throws SecurityException         the security exception
     */
    public CdaTemplate doOneTemplate(final String templateId)
            throws SaxonApiException, IOException, JAXBException, ClassNotFoundException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            InstantiationException, NoSuchFieldException, SecurityException {
        CdaTemplate retVal = null;

        if (templateIndex.containsKey(templateId)) {
            LOG.debug("{} is already known as {}", templateId, templateIndex.get(templateId));
            retVal = templateIndex.get(templateId);
        }

        if (retVal == null) {
            if (initialRun) {
                LOG.debug("Processing document template: {}", templateId);
            }

            templateIndex.put(templateId, runningTemplate);

            String kitSrcFilePath = srcFilePath;
            if (!kitSrcFilePath.endsWith("kit/"))
                kitSrcFilePath += "kit/";

            String orgFn = srcFilePath + templateId + ".xml";
            String trnFn = srcFilePath + templateId + "_transformed.xml";
            File orgFile = new File(orgFn);
            File trnFile = new File(trnFn);

            if (!orgFile.exists()) {
                orgFn = kitSrcFilePath + templateId + ".xml";
                trnFn = kitSrcFilePath + templateId + "_transformed.xml";
                trnFile = new File(trnFn);
            }

            if (!trnFile.exists()) {
                Hl7Its2EhcTransformer.transform(orgFn, trnFn);
            }

            String content;

            content = new String(Files.readAllBytes(Paths.get(trnFn)));

            // instantiate the lexer, the parser, and the walker
            Hl7ItsLexer lexer = new Hl7ItsLexer(CharStreams.fromString(content));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Hl7ItsParser parser = new Hl7ItsParser(tokens);
            ParseTreeWalker walker = new ParseTreeWalker();

            // Parse the template
            LOG.debug("Parsing started");

            walker.walk(this, parser.template());
            templateIndex.remove(templateId, runningTemplate);
            templateIndex.put(templateId, currentCdaTemplate);
            retVal = currentCdaTemplate;

            LOG.debug("Parsing completed");

            // filling missing data types
            for (CdaElement item : mainCdaTemplate.getCdaElementList()) {
                fillDatatypesRecursive(item);
            }

            // Show the content (this is for debugging purposes, only)
            if (initialRun) {
                LOG.debug("Assembled content for template {} (id: {})", mainCdaTemplate.getName(), mainCdaTemplate.getId());
                printCdaAttributes(" ", mainCdaTemplate.getCdaAttributeList());

                for (CdaElement item : mainCdaTemplate.getCdaElementList()) {
                    printCdaElementRecursive("", item);
                }
            }
        }

        if (initialRun) {
            LOG.info("Processing: {} done.", templateId);
        }

        return retVal;
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void enterAttribute(final Hl7ItsParser.AttributeContext ctx) {
        processingAttribute++;
        processingVocab = 0;

        currentCdaAttribute = new CdaAttribute();
        currentCdaAttribute.setCdaTemplate(currentCdaTemplate);

        NameAttrContext nameAttrCtx = ctx.nameAttr();
        if (nameAttrCtx != null)
            currentCdaAttribute.setName(nameAttrCtx.AttrValue().getText().replace("\"", ""));

        ValueAttrContext valueAttrCtx = ctx.valueAttr();
        if (valueAttrCtx != null)
            currentCdaAttribute.setValue(valueAttrCtx.AttrValue().getText().replace("\"", ""));

        DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
        if (dataTypeAttrCtx != null)
            currentCdaAttribute.setDataType(dataTypeAttrCtx.AttrValue().getText().replace("\"", ""));

        if (currentCdaElement == null) {
            currentCdaTemplate.addCdaAttribute(currentCdaAttribute);
        } else {
            currentCdaElement.addAttribute(currentCdaAttribute);
        }

        LOG.debug("Attribute: {}={} (dataType: {})", currentCdaAttribute.getName(), currentCdaAttribute.getValue(),
                currentCdaAttribute.getDataType());
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void enterDesc(final Hl7ItsParser.DescContext ctx) {
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
        if (processingAttribute > 0) {
            currentCdaAttribute.setDescription(desc);
        }
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void enterElement(final Hl7ItsParser.ElementContext ctx) {
        currentCdaAttribute = null;

        String name = null;
        String dataType = null;
        String maxOccursStr = null;
        String minOccursStr = null;
        String conformance = null;

        NameAttrContext nameAttrCtx = ctx.nameAttr();
        if (nameAttrCtx != null) {
            name = nameAttrCtx.AttrValue().getText().replace("\"", "");
        }

        DataTypeAttrContext dataTypeAttrCtx = ctx.dataTypeAttr();
        if (dataTypeAttrCtx != null) {
            dataType = dataTypeAttrCtx.AttrValue().getText().replace("\"", "");
        }

        MaxOccursAttrContext maxOccursAttrCtx = ctx.maxOccursAttr();
        if (maxOccursAttrCtx != null)
            maxOccursStr = maxOccursAttrCtx.AttrValue().getText().replace("\"", "");

        MinOccursAttrContext minOccursAttrCtx = ctx.minOccursAttr();
        if (minOccursAttrCtx != null)
            minOccursStr = minOccursAttrCtx.AttrValue().getText().replace("\"", "");

        ConformanceAttrContext conformanceAttrCtx = ctx.conformanceAttr();
        if (conformanceAttrCtx != null)
            conformance = conformanceAttrCtx.AttrValue().getText().replace("\"", "");

        dataType = adjustDataType(dataType, currentCdaTemplate.getDataType());

        // XPath conditions in the name is not useful for our purposes. Thus we cut it here...
        if (name.contains("[")) {
            name = name.substring(0, name.indexOf("["));
        }

        CdaElement cdaElement = new CdaElement(null);
        cdaElement.setTemplate(currentCdaTemplate);

        cdaElement.setXmlName(name);
        cdaElement.setDataType(dataType);
        if (maxOccursStr != null) {
            int temp = 0;
            if ("*".equals(maxOccursStr)) {
                temp = Integer.MAX_VALUE;
            }
            else temp = Integer.parseInt(maxOccursStr);
            cdaElement.setMaxOccurs(temp);
        }
        if (minOccursStr != null) {
            int temp = 0;
            if ("*".equals(minOccursStr)) {
                temp = Integer.MAX_VALUE;
            }
            else temp = Integer.parseInt(minOccursStr);
            cdaElement.setMinOccurs(temp);
        }

        boolean isTemplateElement = (processingElement == 0);
        boolean isChildElement = (processingElement > processingElementPrev);
        boolean isSiblingElement = (processingElement == processingElementPrev);
        boolean isParentElement = (processingElement < processingElementPrev);
        int levels = 0;
        if (isParentElement) {
            levels = processingElementPrev - processingElement;
        }
        processingElementPrev = processingElement;
        processingElement++;

        // Do not generate elements that are not permitted
        if ("NP".equals(conformance)) {
            return;
        }

        parentForContains = cdaElement;
        CdaElement parentCdaElement = null;
        if ((callingCdaElement == null) && (currentCdaElement == null)) {
            // This is the root element (the root element of the
            // calling template)
            parentCdaElement = null;
            // currentCdaTemplate.addCdaElement(cdaElement);
            parentForIncludes = cdaElement;
            try {
                if (dataType == null) {
                    dataType = getDataType(cdaElement, templateIndex);
                }
                currentCdaTemplate.setDataType(dataType);
                cdaElement.setDataType(dataType);
            } catch (InstantiationException
                    | IllegalAccessException
                    | ClassNotFoundException
                    | IllegalArgumentException
                    | InvocationTargetException
                    | NoSuchFieldException
                    | SecurityException
                    | IOException e) {
                throw new RuntimeException(
                        "Template data type cannot be set for "
                                + currentCdaTemplate.getName()
                                + ": "
                                + e.getMessage());
            }
        } else if ((callingCdaElement != null) && (currentCdaElement == null)) {
            // This is the first level below a referenced template
            // currentCdaTemplate.addCdaElement(cdaElement);
            parentCdaElement = callingCdaElement;
            parentForIncludes = parentCdaElement;
            if (dataType == null) {
                String parentDataType = null;
                parentDataType = parentCdaElement.getDataType();
                if (parentDataType != null) {
                    try {
                        dataType = getDataType(parentDataType, name);
                        cdaElement.setDataType(dataType);
                    } catch (ClassNotFoundException
                            | NoSuchFieldException
                            | SecurityException e) {
                        throw new RuntimeException(
                                "Template data type cannot be set for "
                                        + currentCdaTemplate.getName()
                                        + ": "
                                        + e.getMessage());
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
                if (isTemplateElement) {
                    parentForIncludes = cdaElement;
                } else {
                    parentForIncludes = parentCdaElement;
                }
            } else if (isParentElement) {
                parentCdaElement = currentCdaElement.getParentCdaElement();
                for (int i = 0; i < levels; i++) {
                    parentCdaElement = parentCdaElement.getParentCdaElement();
                }
                parentForIncludes = parentCdaElement;
            } else {
                throw new RuntimeException("element '" + name + "' is neither child, sibling, parent");
            }

            if (parentCdaElement == null) {
                parentCdaElement = callingCdaElement;
                if (parentCdaElement != currentCdaElement)
                    currentCdaElement.setParentCdaElement(parentCdaElement);
            }
            if (parentCdaElement == null)
                throw new RuntimeException("Parent CDA element is null for " + name);

            if (dataType == null) {
                String parentDataType = null;
                parentDataType = parentCdaElement.getDataType();
                if (parentDataType != null) {
                    try {
                        dataType = getDataType(parentDataType, name);
                        cdaElement.setDataType(dataType);
                    } catch (ClassNotFoundException
                            | NoSuchFieldException
                            | SecurityException e) {
                        throw new RuntimeException(
                                "Template data type cannot be set for " + currentCdaTemplate.getName(),
                                e
                        );
                    }
                }
            }
        }

        if (parentCdaElement != null) {
            cdaElement.setParentCdaElement(parentCdaElement);
        }

        if (isTemplateElement) {
            currentCdaTemplate.addCdaElement(cdaElement);
        }
        else parentCdaElement.addChild(cdaElement);

        currentCdaElement = cdaElement;

        boolean isCdaRootElement =
                "org.husky.common.hl7cdar2.POCDMT000040ClinicalDocument"
                        .equals(cdaElement.getDataType());
        if (!(isCdaRootElement) && currentCdaTemplate.getCdaElementList().size() > 1)
            currentCdaTemplate.setDataType(null);

        ContainsAttrContext containsAttrCtx = ctx.containsAttr();
        String ref = "";
        if (containsAttrCtx != null) {
            ref = containsAttrCtx.AttrValue().getText().replace("\"", "");
            LOG.debug("{} contains {}", cdaElement.getJavaName(), ref);
            try {
                if (parentForContains == null)
                    throw new RuntimeException("parent is null for contains " + ref);
                // Process contains
                final ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
                                parentForContains,
                                templateIndex,
                                valueSetIndex,
                                templateList,
                                srcFilePath,
                                dstFilePath,
                                packageName,
                                fileHeader,
                                artDecorPrefix,
                                artDecorBaseUrl);
                final CdaTemplate template = artDecor2JavaGenerator.doOneTemplate(ref);
                currentCdaElement.addCdaTemplate(template, ProcessModes.CONTAINS);
            } catch (SaxonApiException
                    | IOException
                    | JAXBException
                    | ClassNotFoundException
                    | IllegalAccessException
                    | IllegalArgumentException
                    | InvocationTargetException
                    | InstantiationException
                    | NoSuchFieldException
                    | SecurityException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        LOG.debug("Element: {} (dataType: {})", cdaElement.getJavaName(), cdaElement.getDataType());
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void enterInclude(final Hl7ItsParser.IncludeContext ctx) {
        final RefAttrContext refAttrCtx = ctx.refAttr();
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

            LOG.debug("Include: {}", ref);
            try {
                // Process includes
                final ArtDecor2JavaGenerator artDecor2JavaGenerator = new ArtDecor2JavaGenerator(
                                parentForIncludes,
                                templateIndex,
                                valueSetIndex,
                                templateList,
                                srcFilePath,
                                dstFilePath,
                                packageName,
                                fileHeader,
                                artDecorPrefix,
                                artDecorBaseUrl);
                final CdaTemplate template = artDecor2JavaGenerator.doOneTemplate(ref);

                if (isChildElement) {
                    currentCdaElement.addCdaTemplate(template, ProcessModes.INCLUDE);
                } else if (isSiblingElement) {
                    currentCdaElement.getParentCdaElement().addCdaTemplate(template, ProcessModes.INCLUDE);
                } else {
                    currentCdaElement.getParentCdaElement().getParentCdaElement()
                            .addCdaTemplate(template, ProcessModes.INCLUDE);
                }
            } catch (SaxonApiException
                    | IOException
                    | JAXBException
                    | ClassNotFoundException
                    | IllegalAccessException
                    | IllegalArgumentException
                    | InvocationTargetException
                    | InstantiationException
                    | NoSuchFieldException
                    | SecurityException e) {
                throw new RuntimeException("Code generation of include (" + ref + " failed:" + e.getMessage());
            }
        }
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void enterTemplate(final Hl7ItsParser.TemplateContext ctx) {
        String name = null;
        String id = null;

        IdAttrContext idAttrCtx = ctx.idAttr();
        if (idAttrCtx != null) {
            id = idAttrCtx.AttrValue().getText().replace("\"", "");
        }

        NameAttrContext nameAttrCtx = ctx.nameAttr();
        if (nameAttrCtx != null) {
            name = nameAttrCtx.AttrValue().getText().replace("\"", "");
        }

        if (id == null && name == null) {
            throw new RuntimeException("id and name are null for template " + ctx.getText().substring(0, 500) + "...");
        }
        if (id == null) {
            throw new RuntimeException("id is null for template " + name);
        }
        if (name == null) {
            throw new RuntimeException("name is null for template " + id);
        }

        processingTemplate++;
        currentCdaTemplate = new CdaTemplate();
        currentCdaTemplate.setId(id);
        currentCdaTemplate.setName(name);

        // Iterate on attributes to find the status, effective date and version label.
        for (int i = 0; i < ctx.getChildCount(); ++i) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof final Hl7ItsParser.AttrContext attrContext) {
                String value = attrContext.AttrValue().getText();
                value = value.substring(1, value.length() - 1);
                if ("statusCode".equals(attrContext.Name().getText())) {
                    currentCdaTemplate.setStatus(value);
                } else if ("effectiveDate".equals(attrContext.Name().getText())) {
                    currentCdaTemplate.setEffectiveDate(value.replace("T", " "));
                } else if ("versionLabel".equals(attrContext.Name().getText())) {
                    currentCdaTemplate.setVersionLabel(value);
                }
            }
        }

        if (mainCdaTemplate == null) {
            mainCdaTemplate = currentCdaTemplate;
        }
        templateList.add(currentCdaTemplate);
        LOG.debug("Template: {} (id: {})", currentCdaTemplate.getName(), currentCdaTemplate.getId());
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void enterVocab(final Hl7ItsParser.VocabContext ctx) {
        processingVocab++;

        String code = null;
        CodeAttrContext codeAttrCtx = ctx.codeAttr();
        if (codeAttrCtx != null) {
            code = codeAttrCtx.AttrValue().getText().replace("\"", "");
        }

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
            if (currentCdaAttribute == null) {
                currentCdaAttribute = new CdaAttribute();
                currentCdaAttribute.setDataType("cs");
                if (currentCdaElement == null)
                    // an attribute on top level of a template without root
                    // element
                    // belongs to the calling element
                    callingCdaElement.addAttribute(currentCdaAttribute);
                else currentCdaElement.addAttribute(currentCdaAttribute);
            }
            currentCdaAttribute.setVocab(true);
            if (code != null || codeSystem != null) {
                CodeBaseType codeBt = new CodeBaseType();
                codeBt.setCode(code);
                codeBt.setCodeSystem(codeSystem);
                if (codeSystemName != null) {
                    codeBt.setCodeSystemName(codeSystemName);
                }
                if (displayName != null) {
                    codeBt.setDisplayName(displayName);
                }
                if (processingVocab == 1) {
                    currentCdaAttribute.setCode(new Code(codeBt));
                } else if (processingVocab == 2) {
                    if (currentCdaAttribute.getCode() != null)
                        currentCdaAttribute.addCode(currentCdaAttribute.getCode());
                    currentCdaAttribute.setCode(null);
                    currentCdaAttribute.addCode(new Code(codeBt));
                } else if (processingVocab > 2) {
                    currentCdaAttribute.addCode(new Code(codeBt));
                }
            }
            if (valueSetId != null) {
                if (currentCdaAttribute.getName() == null) {
                    currentCdaAttribute.setName("valueSet");
                }
                currentCdaAttribute.setValueSetId(valueSetId);

                /*
                 * This is for debugging purposes only and allows to skip the value set generation.
                 */
                boolean skipValueSetGeneration = false;
                if (!skipValueSetGeneration && !valueSetIndex.containsKey(valueSetId)) {
                    LOG.debug("- downloading ValueSet {}...", valueSetId);

                    String sourceUrl =
                            artDecorBaseUrl.toString()
                                    + "RetrieveValueSet?prefix="
                                    + artDecorPrefix
                                    + "&id="
                                    + valueSetId
                                    + "&format=json";
                    if (!"dynamic".equals(flexibility) && flexibility != null)
                        try {
                            sourceUrl +=
                                    "&effectiveDate="
                                            + java.net.URLEncoder.encode(flexibility, StandardCharsets.UTF_8.toString());
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(
                                    "flexibility (" + flexibility + ") cannot be URL encoded: " + e.getMessage());
                        }
                    ValueSetManager valueSetManager = new ValueSetManager();
                    ValueSetConfig valueSetConfig =
                            ValueSetConfig.builder()
                                    .withProjectFolder(this.dstFilePath)
                                    .withSourceFormatType(SourceFormatType.JSON)
                                    .withSourceSystemType(SourceSystemType.ARTDECOR_FHIR)
                                    .withSourceUrl(sourceUrl)
                                    .build();
                    ValueSet valueSet = null;
                    try {
                        valueSet = valueSetManager.downloadValueSet(valueSetConfig);
                    } catch (IOException
                            | ParserConfigurationException
                            | SAXException
                            | InitializationException e) {
                        LOG.error("valueSet ({}) cannot be downloaded: {}", valueSetId, e.getMessage());
                        throw new RuntimeException("valueSet (" + valueSetId + ") cannot be downloaded: " + e.getMessage());
                    }
                    if (valueSet != null) {
                        LOG.debug("  creating enum class file ...");
                        String fullValueSetClassName =
                                packageName + ".enums." + JavaCodeGenerator.toPascalCase(valueSet.getName());
                        valueSetIndex.put(valueSetId, fullValueSetClassName);
                        valueSetConfig.setClassName(fullValueSetClassName);
                        String baseJavaFolder = valueSetConfig.getProjectFolder();
                        String fullyQualifiedclassName = valueSetConfig.getClassName();
                        // delete existing class file
                        ValueSetUtil.getSourceFileName(baseJavaFolder, fullyQualifiedclassName).delete();

                        // create the class file
                        try {
                            UpdateValueSets.createEnumClassFromTemplate(baseJavaFolder, fullyQualifiedclassName);
                            UpdateValueSets.updateEnumClass(
                                    valueSet.getIdentificator().getRoot(),
                                    valueSet.getName(),
                                    baseJavaFolder,
                                    valueSetConfig.getClassName(),
                                    valueSet);
                        } catch (IOException e) {
                            throw new RuntimeException(
                                    "valueSet ("
                                            + valueSetId
                                            + ") cannot be created as Java enum file: "
                                            + e.getMessage());
                        }
                        LOG.debug("  creating enum class file done");
                    }
                    LOG.debug("- downloading ValueSet {} done.", valueSetId);
                }
            }

            String tempName = "unnamedAttribute";
            if (currentCdaAttribute.getName() != null) {
                tempName = currentCdaAttribute.getName();
            }
            if (currentCdaAttribute.getValue() != null) {
                LOG.error("Attribute from vocab Element: {}={} (dataType: {})", tempName,
                        currentCdaAttribute.getValue(), currentCdaAttribute.getDataType());
            } else if (currentCdaAttribute.getCode() != null) {
                LOG.error("Attribute from vocab Element: {}={} (dataType: {})", tempName,
                        currentCdaAttribute.getCode(), currentCdaAttribute.getDataType());
            } else if (currentCdaAttribute.getCodeList() != null) {
                LOG.error("Attribute from vocab Element: {}={} (dataType: {})", tempName,
                        currentCdaAttribute.getCodeList(), currentCdaAttribute.getDataType());
            } else if (currentCdaAttribute.getValueSetId() != null) {
                LOG.error("Attribute from vocab Element: {}={} (dataType: {})", tempName,
                        currentCdaAttribute.getValueSetId(), currentCdaAttribute.getDataType());
            } else {
                LOG.error("Uups some wrong with logMsg in enterVocab()...");
            }
        }
    }

    /**
     * Checks whether the adder or setter already exists in the resulting class.
     *
     * @param myClass    the my class
     * @param cdaElement the cda element
     * @return true, if successful
     */
    private boolean existAdderOrSetter(final ClassOrInterfaceDeclaration myClass, final CdaElement cdaElement) {
        boolean retVal = false;
        String xmlName = cdaElement.getXmlName();
        String javaName = JavaCodeGenerator.toCamelCase(xmlName);
        cdaElement.setJavaName(javaName);

        List<MethodDeclaration> setterList =
                myClass.getMethodsByName(createSetterNamePascalCase(javaName));
        List<MethodDeclaration> adderList =
                myClass.getMethodsByName(createAdderNamePascalCase(javaName));

        if (!setterList.isEmpty() || !adderList.isEmpty()) {
            String name =
                    cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

            String dataType = null;
            CdaElement elForType = cdaElement.getParentCdaElement();
            if (elForType == null) {
                elForType = cdaElement;
            }
            try {
                dataType = getDataType(elForType.getDataType(), name);
            } catch (ClassNotFoundException
                    | NoSuchFieldException
                    | SecurityException e) {
                // Do nothing
            }
            if ("org.husky.common.hl7cdar2.EIVLTS".equals(dataType)) {
                dataType = "org.husky.common.hl7cdar2.IVLTS";
            }
            for (MethodDeclaration methodDeclaration : setterList) {
                if (methodDeclaration.getParameterByType(dataType).isPresent()) {
                    retVal = true;
                    break;
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
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void exitAttribute(final Hl7ItsParser.AttributeContext ctx) {
        processingAttribute--;
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void exitElement(final Hl7ItsParser.ElementContext ctx) {
        processingElement--;
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void exitInclude(final Hl7ItsParser.IncludeContext ctx) {
        if (processingRootInclude) {
            parentForIncludes = null;
            currentCdaElement = currentCdaElement.getParentCdaElement();
        }
        processingRootInclude = false;
    }

    /**
     * <div class="en">Overrides the given Method of the ANTL4 parser for the ART-DECOR to Java
     * Generator.</div>
     *
     * <p><div class="de">Überschreibt die angegebene Methode des ANTL4-Parsers für den ART-DECOR to
     * Java Generator.</div>
     *
     * @param ctx the ctx
     */
    @Override
    public void exitTemplate(final Hl7ItsParser.TemplateContext ctx) {
        processingTemplate--;
    }

    /**
     * Fill all data types recursively for all CDA elements.
     *
     * @param cdaElement the cda element
     * @throws ClassNotFoundException    the class not found exception
     * @throws IOException               Signals that an I/O exception has occurred.
     * @throws IllegalAccessException    the illegal access exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InstantiationException    the instantiation exception
     * @throws NoSuchFieldException      the no such field exception
     * @throws SecurityException         the security exception
     */
    private void fillDatatypesRecursive(final CdaElement cdaElement)
            throws ClassNotFoundException, IOException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException,
            NoSuchFieldException, SecurityException {

        String logMsg;

        if (cdaElement != null) {
            if (dataTypeIndex == null) {
                dataTypeIndex = loadHl7CdaR2DataTypeIndex();
            }

            String dataType = cdaElement.getDataType();
            if (dataType != null) {
                if ("running".equals(dataType)) dataType = null;
            }
            if (dataType == null) {
                dataType = getDataType(cdaElement, templateIndex);
                cdaElement.setDataType(dataType);
            }
            logMsg =
                    "Datatype for "
                            + cdaElement
                            .getFullXmlName()
                            .replace("hl7:", "")
                            .replace("pharm:", "")
                            .replace("xsi:", "")
                            + " --> "
                            + dataType;
            LOG.debug(logMsg);
            for (CdaElement item : cdaElement.getChildrenCdaElementList()) {
                fillDatatypesRecursive(item);
            }
        }
    }

    /**
     * Gets the data type of the given CDA Template.
     *
     * @param cdaElement    the cda element
     * @param templateIndex the template index
     * @return the data type
     * @throws InstantiationException    the instantiation exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws ClassNotFoundException    the class not found exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     * @throws NoSuchFieldException      the no such field exception
     * @throws SecurityException         the security exception
     * @throws IOException               Signals that an I/O exception has occurred.
     */
    private String getDataType(final CdaElement cdaElement, final Map<String, CdaTemplate> templateIndex)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException,
            IllegalArgumentException, InvocationTargetException, NoSuchFieldException,
            SecurityException, IOException {
        String retVal = null;
        String cdaElementName =
                cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

        String parentDataType = "";
        if (cdaElement.getParentCdaElement() != null) {
            parentDataType = cdaElement.getParentCdaElement().getDataType();
            if (parentDataType == null) {
                parentDataType = getDataType(cdaElement.getParentCdaElement(), templateIndex);
                parentDataType = adjustDataType(parentDataType, null);
            }
        }

        ArrayList<String> candidates = new ArrayList<>();
        if (dataTypeIndex == null) {
            dataTypeIndex = loadHl7CdaR2DataTypeIndex();
        }
        for (String key : dataTypeIndex.keySet()) {
            String value = dataTypeIndex.get(key);
            if (key.startsWith(cdaElementName)
                    || key.startsWith("all.InfrastructureRoot." + cdaElementName)) {
                if (parentDataType.startsWith("org.husky.common.hl7cdar2")) {
                    if (parentDataType.equals(value)) {
                        candidates.add(value);
                    }
                } else candidates.add(value);
            }
        }
        if (candidates.size() == 1) {
            retVal = candidates.get(0);
        } else if (candidates.size() > 1) {
            throw new RuntimeException(
                    "There are multiple data type candidates for " + cdaElement.getFullXmlName());
        }

        if ((retVal == null) && (parentDataType != null)) {
            if (templateIndex.containsKey(parentDataType)) {
                retVal = parentDataType;
            }
        }

        if ((retVal == null) && (parentDataType != null)) {
            retVal = getDataType(parentDataType, cdaElementName);
            if (retVal == null) {
                String parentType = getSuperClassDataType(parentDataType);
                while ((retVal == null)
                        && (parentType != null)
                        && (!"java.lang.Object".equals(parentType))) {
                    retVal = getDataType(parentType, cdaElementName);
                    parentType = getSuperClassDataType(parentType);
                }
            }
        }

        if (retVal == null && parentDataType.startsWith("org.husky.common.hl7cdar2")) {
            retVal = parentDataType;
        }
        if (retVal == null) {
            throw new RuntimeException(
                    "There is no data type candidate for "
                            + cdaElement.getFullXmlName()
                            + ". Make sure, the ART-DECOR model conforms to the XML schema (CDA_extPHARM.xsd).");
        }
        return retVal;
    }

    /**
     * Gets the data type of the given CDA element identified by name.
     *
     * @param parentClassName the parent class name
     * @param cdaElementName  the cda element name
     * @return the data type
     * @throws ClassNotFoundException the class not found exception
     * @throws NoSuchFieldException   the no such field exception
     * @throws SecurityException      the security exception
     */
    private String getDataType(String parentClassName, String cdaElementName)
            throws ClassNotFoundException, NoSuchFieldException, SecurityException {
        String retVal = null;

        Class<?> cl = Class.forName(adjustDataType(parentClassName, null));
        XmlType xmlType = cl.getAnnotation(XmlType.class);
        if (xmlType != null) {
            for (String prop : xmlType.propOrder()) {
                if (prop.equals(
                        cdaElementName.replace("hl7:", "").replace("pharm:", "").replace("xsi:", ""))) {
                    String expectedMethodName = createGetterNamePascalCase(prop);
                    for (Method method : cl.getMethods()) {
                        if (method.getName().equals(expectedMethodName)) {
                            String returnType = method.getReturnType().getName();
                            if ("java.util.List".equals(returnType)
                                    || "javax.xml.bind.JAXBElement".equals(returnType)) {
                                Field field = cl.getDeclaredField(prop);
                                Type type = field.getGenericType();
                                if (type instanceof final ParameterizedType pt) {
                                    if (pt.getActualTypeArguments().length > 1)
                                        throw new RuntimeException(
                                                "Unhandled data type:" + type + " (multiple types in list)");
                                    if (pt.getActualTypeArguments().length == 0)
                                        throw new RuntimeException(
                                                "Unhandled data type:" + type + " (zero types in List)");
                                    retVal = pt.getActualTypeArguments()[0].getTypeName();
                                }
                            } else retVal = method.getReturnType().getName();
                        }
                    }
                }
            }
        }
        if (retVal == null) {
            // Check attributes
            for (Field f : cl.getDeclaredFields()) {
                XmlAttribute xmlAttribute = f.getAnnotation(XmlAttribute.class);
                if (xmlAttribute != null) {
                    if (cdaElementName.contentEquals(xmlAttribute.name())) {
                        retVal = f.getType().getName();
                    }
                }
            }
        }

        if (retVal == null && parentClassName.endsWith(".AD")) {
            retVal = "org.husky.common.hl7cdar2.ADXP";
        }

        return retVal;
    }

    /**
     * Gets the full destination file path for the given class identified by name.
     *
     * @return the full dst file path
     */
    private String getFullDstFilePath() {
        return fullDstFilePath;
    }

    /**
     * Gets the data type of the super class of the given class.
     *
     * @param className the class name
     * @return the parent data type
     * @throws ClassNotFoundException the class not found exception
     */
    private String getSuperClassDataType(final String className) throws ClassNotFoundException {
        String retVal = null;
        Class<?> cl = Class.forName(adjustDataType(className, null));
        if (cl.getSuperclass() != null) {
            retVal = cl.getSuperclass().getName();
        }
        return retVal;
    }

    /**
     * Loads the data type index of all classes in the HL7 CDA R2 Object Factory.
     *
     * @return the hash map
     * @throws IllegalAccessException    the illegal access exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     */
    private HashMap<String, String> loadHl7CdaR2DataTypeIndex()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HashMap<String, String> retVal = new HashMap<>();

        ObjectFactory factory = new ObjectFactory();
        Method[] methods = factory.getClass().getMethods();
        for (final Method method : methods) {
            String key = null;
            String value = null;
            if ("javax.xml.bind.JAXBElement".equals(method.getReturnType().getName())) {
                JAXBElement<?> jaxbElement = (JAXBElement<?>) method.invoke(factory, new Object[]{null});
                key = jaxbElement.getName().getLocalPart();
                value = jaxbElement.getDeclaredType().getName();
            } else if (method.getName().startsWith("create")) {
                Object obj = method.invoke(factory);
                value = obj.getClass().getName();
                XmlType xmlType = obj.getClass().getAnnotation(XmlType.class);
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
                if (i > -1) {
                    retVal.put(key + i, value);
                }
            }
        }
        return retVal;
    }

    /**
     * <div class="en">Prepares the current ART-DECOR to Java Generator instance for another Document
     * Template (reset of module variables for a next iteration).</div>
     *
     * <p><div class="de">Bereitet die aktuelle Instanz des ART-DECOR to Java Generators vor für ein
     * nächstes Document Template (reset von Modulvariablen für eine nächste Iteration).</div>
     *
     * <p>Prepare for another template.
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
     * Performs some template adjustments (regrouping contained elements to the hosting template).
     *
     * @param myTemplateList the my template list
     */
    private void regroupTemplateElements(final List<CdaTemplate> myTemplateList) {
        for (final CdaTemplate cdaTemplate : myTemplateList) {
            LOG.debug("Processing {}", cdaTemplate.getName());
            for (final CdaElement cdaElement : cdaTemplate.getCdaElementList()) {
                LOG.debug(" {}", cdaElement.getXmlName());
                for (final CdaTemplate cdaTemplate2 : cdaElement.getCdaTemplateList().keySet()) {
                    final ProcessModes mode = cdaElement.getCdaTemplateList().get(cdaTemplate2);
                    LOG.debug(" {} {}", mode, cdaTemplate2.getName());
                    if (mode != ProcessModes.CONTAINS) {
                        for (final CdaElement cdaElement2 : cdaTemplate2.getCdaElementList()) {
                            LOG.debug("Adding {}", cdaElement2.getXmlName());
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
     * @param compilationUnit              the compilation unit
     * @param setterForFixedContentsMethod the setter for fixed contents method
     * @param cdaElement                   the cda element
     * @param cdaAttribute                 the cda attribute
     */
    private void updateCreatorForFixedContentsMethod(final CompilationUnit compilationUnit,
                                                     final MethodDeclaration setterForFixedContentsMethod,
                                                     final CdaElement cdaElement,
                                                     final CdaAttribute cdaAttribute) {

        String attrName =
                cdaAttribute.getName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

        Optional<Parameter> params = setterForFixedContentsMethod.getParameterByName(attrName);

        if (params.isEmpty()) {
            setterForFixedContentsMethod.addAndGetParameter("String", attrName);
            Optional<Javadoc> javaDocOpt = setterForFixedContentsMethod.getJavadoc();
            Javadoc javadoc = javaDocOpt.get();
            javadoc.addBlockTag(
                    "param", cdaAttribute.getName() + " the desired fixed value for this argument.");
            setterForFixedContentsMethod.setJavadocComment(javadoc);

            Optional<BlockStmt> bodyOpt = setterForFixedContentsMethod.getBody();
            if (bodyOpt.isPresent()) {
                BlockStmt body = bodyOpt.get();

                String name =
                        cdaElement.getXmlName().replace("hl7:", "").replace("pharm:", "").replace("xsi:", "");

                Class<?> memberType = getDeclaredFieldDatatype(cdaElement.getDataType(), attrName);
                if (memberType == null)
                    memberType =
                            getDeclaredFieldDatatype(cdaElement.getParentCdaElement().getDataType(), name);

                String valueSetId = null;
                for (CdaAttribute attr : cdaElement.getCdaAttributeList()) {
                    valueSetId = attr.getValueSetId();
                    if (valueSetId != null) break;
                }

                if ((memberType == null) && (!"translation".equals(name))) {
                    if (cdaElement.getDataType().endsWith(".ENXP")) {
                        String enPartU = toUpperFirstChar(attrName);
                        body.addStatement("retVal.get" + enPartU + "().add(" + attrName + ");");
                    } else
                        throw new RuntimeException(
                                name + " is neither an accessible field nor an accessible getter");
                } else {
                    if ("nullFlavor".equals(attrName)) {
                        compilationUnit.addImport("java.util.ArrayList");
                        body.addStatement("retVal.nullFlavor = new ArrayList<String>();");
                        body.addStatement("retVal.nullFlavor.add(nullFlavor);");
                    } else if (isClassCollection(memberType)) {
                        String temp =
                                cdaAttribute
                                        .getName()
                                        .replace("hl7:", "")
                                        .replace("pharm:", "")
                                        .replace("xsi:", "");
                        body.addStatement("retVal.get" + toUpperFirstChar(temp) + "().add(" + temp + ");");
                    } else {

                        if ("java.lang.String".contentEquals(memberType.getName())) {
                            body.addStatement("retVal.set" + toUpperFirstChar(attrName) + "(" + attrName + ");");
                            if (cdaAttribute.getCode() != null) {
                                setterForFixedContentsMethod.addAndGetParameter("String", "codeSystem");
                                setterForFixedContentsMethod.addAndGetParameter("String", "codeSystemName");
                                setterForFixedContentsMethod.addAndGetParameter("String", "displayName");

                                javadoc.addBlockTag(
                                        "param", "codeSystem the desired fixed value for this argument.");
                                javadoc.addBlockTag(
                                        "param", "codeSystemName the desired fixed value for this argument.");
                                javadoc.addBlockTag(
                                        "param", "codeDisplayName the desired fixed value for this argument.");

                                body.addStatement("retVal.setCodeSystem(codeSystem);");
                                body.addStatement("retVal.setCodeSystemName(codeSystemName);");
                                body.addStatement("retVal.setDisplayName(displayName);");
                            }
                        } else if ("java.lang.Boolean".contentEquals(memberType.getName())) {
                            body.addStatement(
                                    String.format(
                                            "if (%s != null) {" + "    retVal.set%s(Boolean.parseBoolean(%s));" + "}",
                                            attrName, toUpperFirstChar(attrName), attrName));
                        } else {
                            String enumName = memberType.getName();
                            if (valueSetId != null) {
                                enumName = adjustValueSet(valueSetIndex.get(valueSetId), null);
                                addBodyComment(
                                        setterForFixedContentsMethod,
                                        "TODO: Contents shall be taken from enum: " + enumName);
                            } else {
                                body.addStatement(String.format("retVal.set%s(%s.fromValue(%s));",
                                        toUpperFirstChar(attrName), enumName, attrName));
                            }
                        }
                    }
                }
            }
        }
    }
}
