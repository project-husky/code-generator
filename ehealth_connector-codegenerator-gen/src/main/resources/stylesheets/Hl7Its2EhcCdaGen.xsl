<?xml version="1.0" encoding="UTF-8"?>
<!--
******************************************************************************
Stylesheet to simplify ART-DECOR templates for follow-up parsing and Java code generation.

Parameters:
none


History:
2019.04.16: Tony Schaller, medshare GmbH: First draft for PoC
2019.07.23: Tony Schaller, medshare GmbH: Implementation of the XSLT module for ART-DECOR to Java code generator.

******************************************************************************
-->
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ms="http://medshare.net/XSLBase"
	exclude-result-prefixes="xs ms">

	<!--
	****************************************************************************
	The following templates perform a deep copy of the entire XML tree.
	XSL templates matching specific element nodes or having a higher priority
	may overwrite this default behavior.
	****************************************************************************
	-->
	<xsl:template match="@*|node()" priority="-2">
		<xsl:copy-of select="." />
	</xsl:template>

	<xsl:template match="element()" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="@*">
				<xsl:sort select="name()"/>
			</xsl:apply-templates>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="@*|comment()|processing-instruction()">
		<xsl:copy />
	</xsl:template>

	<!--
	****************************************************************************
	Omit the given elements in the target. We only want elements that influence
	the resulting Java code. All others will be skipped, here.
	****************************************************************************
	-->
	<xsl:template match="classification|relationship|context|example"/>
	<xsl:template match="@language" />

	<!--
	****************************************************************************
	Reformat description elements and omit all descriptions that are not English.
	This is preparation for Javadoc, only.
	****************************************************************************
	-->
	<xsl:template match="desc">
		<xsl:if test="@language='en-US'">
			<desc>
				<xsl:apply-templates select="@*|node()" />
			</desc>
		</xsl:if>
	</xsl:template>

	<xsl:template match="b">
		<strong>
			<xsl:value-of select="text()"/>
		</strong>
	</xsl:template>

	<xsl:template match="p">
		<xsl:apply-templates select="@*|node()" />
		<br />
	</xsl:template>

	<xsl:template match="li">
		- <xsl:apply-templates select="@*|node()" />
		<br />
	</xsl:template>
	<xsl:template match="ul">
		<xsl:apply-templates select="@*|node()" />
	</xsl:template>


	<xsl:template match="*/text()[normalize-space()]">
		<xsl:value-of select="normalize-space()"/>
	</xsl:template>

	<xsl:template match="*/text()[not(normalize-space())]" />

</xsl:stylesheet>
