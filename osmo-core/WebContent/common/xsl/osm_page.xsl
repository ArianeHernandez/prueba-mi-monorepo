<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


	<xsl:template match="OSM_PAGE">
		<OSM_PAGE>
			<version_aplicacion><xsl:value-of select="//VERSION_APLICACION"/></version_aplicacion>
			
			<context-path><xsl:value-of select="//CONTEXT_PATH"/></context-path>
			<system-time><xsl:value-of select="//SYSTEM_TIME"/></system-time>
			<string-time><xsl:value-of select="//STRING_TIME"/></string-time>
			<string-time-formatted><xsl:value-of select="//STRING_TIME_FORMATTED"/></string-time-formatted>
			<base-path><xsl:value-of select="//BASEPATH"/></base-path>
			<client-ip><xsl:value-of select="//CLIENT_IP"/></client-ip>
			<request-uri><xsl:value-of select="//REQUESTURI"/></request-uri>
			
			<request-headers>
				<xsl:for-each select="//HEADERS/HEADER">
					<header name="{name}" value="{value}"/>
				</xsl:for-each>	
			</request-headers>
			
			<osm_informacion-pagina>
				<osm_tiquete><xsl:value-of select="OSM_TICKET/NEW-TICKET"/></osm_tiquete>
				<error-page><xsl:value-of select="OSM_TICKET/TYPE/ERROR"/></error-page>
				
				<validacion>
					<xsl:copy-of select="OSM-VALIDACION/*"/>
				</validacion>
				<init-session>
					<xsl:copy-of select="OSM-INIT-SESSION/*"/>
				</init-session>
				<session>
					<xsl:copy-of select="OSM-SESSION/*"/>
				</session>
			</osm_informacion-pagina>
			
			<osm_general>
				<xsl:copy-of select="OSM-GENERAL/*"/>
			</osm_general>
			
			<xsl:for-each select="//OSM-VALIDACIONMENSAJE/MENSAJE_BASE">
				<osm_mensaje><xsl:value-of select="."/></osm_mensaje>
			</xsl:for-each>
			
			<xsl:apply-templates select="OSM-VALIDACION/*"/>
			<xsl:apply-templates select="OSM-INIT-SESSION/*"/>
			<xsl:apply-templates select="OSM-SESSION/*"/>
			<xsl:apply-templates select="OSM-ACCION"/>
			
		</OSM_PAGE>
		
	</xsl:template>
	
	<xsl:template match="*">
	</xsl:template>
	
</xsl:stylesheet>
