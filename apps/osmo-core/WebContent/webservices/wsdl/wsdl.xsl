<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:output indent="yes" method="xml"/>
	
    <xsl:template match="OSM-ACCION">
        <xsl:copy-of select="*"/>
    </xsl:template>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
</xsl:stylesheet>
