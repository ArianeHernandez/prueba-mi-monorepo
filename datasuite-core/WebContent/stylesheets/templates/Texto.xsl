<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	
	<xsl:template match="texto">
	
		
		<xsl:variable name="key" select="@key"/>
		<xsl:variable name="texto" select="//obtenerContenidosByURL/listado/Contenido[etiqueta=$key]/texto"/>
		<xsl:variable name="valor" select="@valor"/>
		<xsl:variable name="ocultar_vacio" select="@ocultar_vacio"/>
		
		<xsl:variable name="idkey" select="generate-id()" ></xsl:variable>
		<xsl:if test="string-length($valor) > 0">
			<input type="hidden" id="texto_parametrizable_{$idkey}_valor" value="{$valor}"/>
		</xsl:if>
		<input type="hidden" id="texto_parametrizable_{$idkey}" >
			<xsl:attribute name="value">
				<xsl:choose>
					<xsl:when test="string-length($texto) = 0 and (string-length($ocultar_vacio) = 0 or $ocultar_vacio != 'true')">
						<xsl:value-of select="$key"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$texto"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
		</input>
		
		<script>mostrarContenido( "<xsl:value-of select="$idkey"/>");</script>
		
	</xsl:template>


</xsl:stylesheet>