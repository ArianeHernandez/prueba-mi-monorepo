<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
    <pagina titulo="Sobreflex">
    
    	<javascript>sobreflex/sobreflex_1.js</javascript>
			
		<principal>
			<titulo>Sobreflex para el Cliente <xsl:value-of select="//obtenerUsuario/Usuario/nombre"></xsl:value-of></titulo>
			
			<contenido>
		        <div>
		        
		        <xsl:choose>
		        	<xsl:when test="count(//generarCryptoSobreflex/CryptoSobreflex)>0">
		        		<p>Sobreflex enviado para impresión.</p>
		           
				           <parrafo estilo="resaltado" id="estado_applet"></parrafo>
				           
			               <escapar>
				               <applet id="appletSobreflex" archive="v2/publicfiles/sobreflex/sobre.jar" 
				               code="com.osmosyscol.datasuite.correval.sobreflex.AppletSobreflex.class" height="1" width="1">
				                   <param name="Nombre" value="{//obtenerPersona/Persona/nombre}"/>
				                   <param name="Apellido" value="{//obtenerPersona/Persona/apellido}"/>
				                   <param name="ID" value="{//obtenerUsuario/Usuario/identificacion}"/>
				                   <param name="Texto" value="{//generarCryptoSobreflex/CryptoSobreflex}"/>
				                   <param name="Empresa" value="{//obtenerUsuario/Usuario/nombre}"/>
				                   <param name="CoordenadasX" value="{//getParametrosAppletSobreflex/listado/coordenadasX}"/>
				                   <param name="CoordenadasY" value="{//getParametrosAppletSobreflex/listado/coordenadasY}"/>
				                   <param name="TamLetras" value="{//getParametrosAppletSobreflex/listado/tamLetras}"/>
				                   <param name="Margenes" value="{//getParametrosAppletSobreflex/listado/margenes}"/>
				               </applet>
				             </escapar> 
		        		
		        		<input type="hidden" id="login_usuario" value="{//obtenerCredencialPersonaUsuario/Credencial/login}"/>
		        	
		        	</xsl:when>
		        	
		        	<xsl:otherwise>
		        		<parrafo estilo="resaltado">
		        		
		        			Lo sentimos. No es posible generar sobreflex. Por favor intentelo nuevamente.
		        		</parrafo>
		        	</xsl:otherwise>
		        	
		        
		        </xsl:choose>
		       
		            
		        </div>
	        </contenido>
	    </principal>
			    
	</pagina>     
    </xsl:template>
</xsl:stylesheet>