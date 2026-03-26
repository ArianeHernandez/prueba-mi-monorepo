<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dpi="http://www.itosmosys.com/datapi/servicios/NegociosPorAdministrador">
	
    <xsl:template match="obtenerNegociosPorAdministrador">
        
    	<xsl:choose>
    		
    		<xsl:when test="count(//listado)=0">
				<OSM_ERROR/>
			</xsl:when>
    		
    		<xsl:otherwise>
    			
    			<obtenerNegociosPorAdministrador>
		    		<xsl:for-each select="//listado/TipoElementoSalidalista_usua_vs_fides/negocios/NegociosArrayElement[estado='Activa']">
		    			<codigo_negocio><xsl:value-of select="codigo"/></codigo_negocio>
		    		</xsl:for-each>
			    </obtenerNegociosPorAdministrador>
    			
    		</xsl:otherwise>
    		
    	</xsl:choose>
    	
    	
    	
    </xsl:template>
	
</xsl:stylesheet>