<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Autorización de direcciones IP">
			<javascript>validacion_ip/validacion_ip.js</javascript>
			
			<principal>
				<titulo><texto key="VALIDACION IP" /></titulo>
				<contenido>
					
					<parrafo estilo="resaltado" id="estado_ip">Iniciando <texto key="VALIDACION IP" /></parrafo>
					
					<parrafo>Por favor, verifique que tenga Java instalado y que sea aceptado el certificado.</parrafo>
					
					<escapar>
						
	    				<applet id="appletValidacion" code="com.osmosyscol.datasuite.applet.autips.IPApplet.class"
								archive = "publicfiles/jar/valida_ip.jar"	    					
	    						width  = "1" height = "1"
	    					>
		    				<param name = "CONTEXTPATH" value = "{//CONTEXT_PATH}" />
	    					<param name = "JSESSIONID" value = "{//SESSION_ID}" />
	    					<param name = "HOST_PORT" value = "{//HEADERS/HEADER[name = 'host']/value}"/>
						</applet>
					</escapar>
					
				</contenido>
			
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
