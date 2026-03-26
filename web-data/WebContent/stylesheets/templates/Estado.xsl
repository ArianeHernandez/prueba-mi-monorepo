<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<!-- Plantilla para estados de la carga -->
	<xsl:template match="estado">
		<xsl:variable name="key" select="@key"/>
		<xsl:variable name="cliente" select="@cliente"/>
		<xsl:variable name="dto" select="//obtenerEstados/Listado/Estado[estado=$key]"/>
		
		<xsl:choose>
			<xsl:when test="$cliente='S'">
				<xsl:value-of select="$dto/nombre_cliente"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$dto/nombre_interno"/>
			</xsl:otherwise>
		</xsl:choose>		
	
	</xsl:template>
	
	<xsl:template match="estados_javascript">
		<xsl:variable name="cliente" select="@cliente"/>
		<script type="text/javascript" >
			
			ESTADOS_CARGA = new Array();
			
			<xsl:for-each select="//obtenerEstados/Listado/Estado">
				<xsl:choose>
					<xsl:when test="$cliente='S'">
						ESTADOS_CARGA["<xsl:value-of select="estado"/>"] = "<xsl:value-of select="nombre_cliente"/>";
					</xsl:when>
					<xsl:otherwise>
						ESTADOS_CARGA["<xsl:value-of select="estado"/>"] = "<xsl:value-of select="nombre_interno"/>";
					</xsl:otherwise>
				</xsl:choose>
			
			</xsl:for-each>
					
		</script>
		
	</xsl:template>


</xsl:stylesheet>