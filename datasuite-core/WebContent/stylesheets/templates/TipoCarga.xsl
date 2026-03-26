<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<!-- Plantilla para estados de la carga -->
	<xsl:template match="tipo_carga">
		<xsl:variable name="id" select="@id"/>
		<xsl:variable name="dto" select="//obtenerTiposCarga/Listado/TipoCarga[id_tipocarga=$id]"/>
		
		<xsl:value-of select="$dto/nombre"/>
	</xsl:template>
	
</xsl:stylesheet>