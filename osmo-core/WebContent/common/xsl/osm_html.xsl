<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="contextPath" />
	<xsl:param name="page" />
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="form">
		<form action="{@action}" method="post" enctype="{@enctype}" onsubmit="return false;" novalidate="novalidate">
			<xsl:if test="count(@method)>0">
				<xsl:attribute name="method"><xsl:value-of select="@method"/></xsl:attribute>
			</xsl:if>
			<xsl:call-template name="attributes"/>
			<xsl:apply-templates/>
			<input type="hidden" name="osm_lastpage" value="{$page}"/>
			<input type="hidden" name="osm_ticket" value="{//osm_informacion-pagina/osm_tiquete}"/>
		</form>
	</xsl:template>


	
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="option">
		<option>
			<xsl:if test="string-length(@value)>0 and (@value = ../@value or (count(../@label)>0 and @value = ../../@value))">
				<xsl:attribute name="selected">true</xsl:attribute>
			</xsl:if>
			<xsl:call-template name="attributes"/>
			<xsl:apply-templates/>
			<xsl:call-template name="osm_vacio"/>
		</option>
	</xsl:template>
	
	
	

	
	
	<xsl:template name="attributes">
		<xsl:if test="count(@name)>0"><xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@id)>0"><xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@class)>0"><xsl:attribute name="class"><xsl:value-of select="@class"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@style)>0"><xsl:attribute name="style"><xsl:value-of select="@style"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@label)>0"><xsl:attribute name="label"><xsl:value-of select="@label"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@href)>0"><xsl:attribute name="href"><xsl:value-of select="@href"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onclick)>0"><xsl:attribute name="onclick"><xsl:value-of select="@onclick"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onsubmit)>0"><xsl:attribute name="onsubmit"><xsl:value-of select="@onsubmit"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onmousedown)>0"><xsl:attribute name="onmousedown"><xsl:value-of select="@onmousedown"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onfocus)>0"><xsl:attribute name="onfocus"><xsl:value-of select="@onfocus"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@maxlength)>0"><xsl:attribute name="maxlength"><xsl:value-of select="@maxlength"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onload)>0"><xsl:attribute name="onload"><xsl:value-of select="@onload"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onkeypress)>0"><xsl:attribute name="onkeypress"><xsl:value-of select="@onkeypress"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onkeyup)>0"><xsl:attribute name="onkeyup"><xsl:value-of select="@onkeyup"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onmouseover)>0"><xsl:attribute name="onmouseover"><xsl:value-of select="@onmouseover"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onmouseout)>0"><xsl:attribute name="onmouseout"><xsl:value-of select="@onmouseout"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onblur)>0"><xsl:attribute name="onblur"><xsl:value-of select="@onblur"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@onchange)>0"><xsl:attribute name="onchange"><xsl:value-of select="@onchange"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@rowspan)>0"><xsl:attribute name="rowspan"><xsl:value-of select="@rowspan"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@colspan)>0"><xsl:attribute name="colspan"><xsl:value-of select="@colspan"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@value)>0"><xsl:attribute name="value"><xsl:value-of select="@value"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@type)>0"><xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@selected)>0"><xsl:attribute name="selected"><xsl:value-of select="@selected"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@disabled)>0"><xsl:attribute name="disabled"><xsl:value-of select="@disabled"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@checked)>0"><xsl:attribute name="checked"><xsl:value-of select="@checked"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@target)>0"><xsl:attribute name="target"><xsl:value-of select="@target"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@width)>0"><xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@height)>0"><xsl:attribute name="height"><xsl:value-of select="@height"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@src)>0"><xsl:attribute name="src"><xsl:value-of select="@src"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@title)>0"><xsl:attribute name="title"><xsl:value-of select="@title"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@archive)>0"><xsl:attribute name="archive"><xsl:value-of select="@archive"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@code)>0"><xsl:attribute name="code"><xsl:value-of select="@code"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@readonly)>0"><xsl:attribute name="readonly"><xsl:value-of select="@readonly"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@for)>0"><xsl:attribute name="for"><xsl:value-of select="@for"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@frameborder)>0"><xsl:attribute name="frameborder"><xsl:value-of select="@frameborder"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@scrolling)>0"><xsl:attribute name="scrolling"><xsl:value-of select="@scrolling"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@lov)>0"><xsl:attribute name="lov"><xsl:value-of select="@lov"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@ondblclick)>0"><xsl:attribute name="ondblclick"><xsl:value-of select="@ondblclick"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@tabindex)>0"><xsl:attribute name="tabindex"><xsl:value-of select="@tabindex"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@role)>0"><xsl:attribute name="role"><xsl:value-of select="@role"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@data-toggle)>0"><xsl:attribute name="data-toggle"><xsl:value-of select="@data-toggle"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@data-target)>0"><xsl:attribute name="data-target"><xsl:value-of select="@data-target"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@placeholder)>0"><xsl:attribute name="placeholder"><xsl:value-of select="@placeholder"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@min)>0"><xsl:attribute name="min"><xsl:value-of select="@min"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@max)>0"><xsl:attribute name="max"><xsl:value-of select="@max"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@accept)>0"><xsl:attribute name="accept"><xsl:value-of select="@accept"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@multiple)>0"><xsl:attribute name="multiple"><xsl:value-of select="@multiple"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@rows)>0"><xsl:attribute name="rows"><xsl:value-of select="@rows"/></xsl:attribute></xsl:if>
		
		<xsl:if test="count(@aria-labelledby)>0"><xsl:attribute name="aria-labelledby"><xsl:value-of select="@aria-labelledby"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@aria-hidden)>0"><xsl:attribute name="aria-hidden"><xsl:value-of select="@aria-hidden"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@data-dismiss)>0"><xsl:attribute name="data-dismiss"><xsl:value-of select="@data-dismiss"/></xsl:attribute></xsl:if>
		<xsl:if test="count(@data-sitekey)>0"><xsl:attribute name="data-sitekey"><xsl:value-of select="@data-sitekey"/></xsl:attribute></xsl:if>
		
		<!-- Copy AngularJS attributes -->
		<xsl:copy-of select="@*[starts-with(name(),'data-')]"/>
		<xsl:copy-of select="@*[starts-with(name(),'ng-')]"/>
		
		
		<xsl:if test="count(@idname)>0">
			<xsl:attribute name="name"><xsl:value-of select="@idname"/></xsl:attribute>
			<xsl:attribute name="id"><xsl:value-of select="@idname"/></xsl:attribute>
		</xsl:if>
		
	</xsl:template>
	
	<xsl:template name="osm_vacio" xml:space="preserve"><xsl:if test="count(*)=0 and string-length()=0"> </xsl:if></xsl:template>
	
	<xsl:template match="add">
	</xsl:template>
	
	<xsl:template name="add">
	</xsl:template>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
</xsl:stylesheet>
