<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:param name="contextPath"/>
    <xsl:param name="page"/>
    
    <xsl:template match="/">
    
    	<xsl:variable name="rnd_version" select="concat('HTS', translate(//version_aplicacion, ' $[]:/.','_______') )"/>
    	
        <html>
            <head>
            	<script> var CONTEXTPATH = '<xsl:value-of select="$contextPath"/>';</script>
            	<script src="{$contextPath}/{$rnd_version}/scripts/jsonrpc.js"> </script>
            	<xsl:for-each select="//stylesheet[string-length(.)>0]">
					<link rel="stylesheet" type="text/css" href="{$contextPath}/{$rnd_version}/styles/{.}" />
				</xsl:for-each>
                <title>Osmoautenticador</title>
                <xsl:for-each select="//javascript[string-length(.)>0]">
					<script src="{$contextPath}/{$rnd_version}/scripts/{.}"> </script>
				</xsl:for-each>
            </head>
            <body>
                 <xsl:copy-of select="//CONTENIDO"/> 
            </body>
        </html>
    </xsl:template>
    
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - -->
		
</xsl:stylesheet>
