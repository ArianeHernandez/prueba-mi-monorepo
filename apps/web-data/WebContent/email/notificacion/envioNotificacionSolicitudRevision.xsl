<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo><xsl:value-of select="//notificacion/titulo"/></titulo>
			<contenido>
				<parrafo>Se le informa que <xsl:value-of select="//notificacion/nombre"/> ha certificado la información cargada en el sistema.
				Para continuar el proceso de radicación, es necesario aprobar la solicitud por lo cual se le  solicita seguir los siguientes pasos: 
				</parrafo>
			 	<ol>
				  <li><a href="https://mi.ia.supersociedades.gov.co/WebData/">Iniciar sesión en MI.</a></li>
				  <li>Ir al menú Enviar y seleccionar Env Solicitud NEAR.</li>
				  <li>El usuario dará clic en aprobar la solicitud, si la misma corresponde a la realidad.</li>
				  <li>El sistema generará una ventana emergente para confirmar la aprobación de la solicitud e Ingrear el token de seguridad generado en SignApp.</li>
				  <li>Finalmente, podrá dar clic en Aceptar</li>
				</ol>  			
			<parrafo>
				Cordialmente,<br />
			</parrafo>
			<img src="#logo#"/>
			<parrafo>
				Superintendencia de Sociedades
			</parrafo>
		</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
