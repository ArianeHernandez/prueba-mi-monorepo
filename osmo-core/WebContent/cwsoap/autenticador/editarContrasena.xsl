<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
    <xsl:template match="service">
    	<editarContrasena>
        	<mensaje><xsl:value-of select="//mensaje"/></mensaje>
    		<exito><xsl:value-of select="//exitoso"/></exito>
    	</editarContrasena>
    </xsl:template>
	
</xsl:stylesheet>