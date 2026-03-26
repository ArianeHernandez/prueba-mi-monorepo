<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:service="http://autenticar.servicioweb.osmoautenticador.osmosyscol.com">
	
    <xsl:template match="service">
        <autenticado>
        	<xsl:value-of select="//service:autenticado"/>
        </autenticado>
    </xsl:template>
	
</xsl:stylesheet>