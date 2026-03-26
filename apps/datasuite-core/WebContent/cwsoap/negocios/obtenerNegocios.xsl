<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dpi="http://www.itosmosys.com/datapi/servicios/ListaNegocios">
	
    <xsl:template match="obtenerNegocios">
        
    	<obtenerNegocios>
    		
	    	<Modelo id_modelo="1">
	    		
	    		<xsl:for-each select="//TipoElementoSalidalista_negocios/negocios/NegociosArrayElement[estado='Activa']">
	    			<Negocio>
		    			<codigo><xsl:value-of select="codigo"/></codigo>
						<nombre><xsl:choose>
							<xsl:when test="string-length(descripcion) = 0">
								<xsl:value-of select="codigo"/>
							</xsl:when>		
							<xsl:otherwise>
								<xsl:value-of select="descripcion"/>
							</xsl:otherwise>		
						</xsl:choose></nombre>
		    		</Negocio>
    			</xsl:for-each>	
	    	</Modelo>
    		
	    </obtenerNegocios>
    	
    </xsl:template>
	
</xsl:stylesheet>