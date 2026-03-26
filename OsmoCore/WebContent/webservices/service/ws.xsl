<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:soapenv="generated"
>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<soapenv:Body>
	  			<xsl:copy-of select="ejecutarServicio/*"/>
	  		</soapenv:Body>
	  	</soapenv:Envelope>
	</xsl:template>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
</xsl:stylesheet>
