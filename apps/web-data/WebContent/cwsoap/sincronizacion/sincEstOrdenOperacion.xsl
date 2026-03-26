<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:service="http://autenticar.servicioweb.osmoautenticador.osmosyscol.com">
	
    <xsl:template match="enviarCarga">
        <sincEstOrdenOperacion>
        	<endpoint><xsl:value-of select="endpoint"/></endpoint>
        	<id_carga><xsl:value-of select="id_carga"/></id_carga>
        	<id_ejecucion><xsl:value-of select="id_ejecucion"/></id_ejecucion>
    		
    		<xsl:choose>
    			<xsl:when test="normalize-space(soapcall) ='EN PROCESO'">
    				<exito>true</exito>
    			</xsl:when>
    			<xsl:otherwise>
    				<exito>false</exito>		
    			</xsl:otherwise>
    		</xsl:choose>
        	
        	<!--exito>
        		<xsl:value-of select="soapcall"/>
        	</exito-->
        	
        	
        	
        </sincEstOrdenOperacion>
    </xsl:template>
	
</xsl:stylesheet>