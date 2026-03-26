<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo><xsl:value-of select="//notificacion/titulo"/></titulo>
			<contenido>
				<parrafo>Se le informa que <xsl:value-of select="//notificacion/nombre"/> ha realizado una solicitud para iniciar un proceso de <xsl:value-of select="//notificacion/proceso"/>.
				Para continuar el proceso de solicitud es necesario certificar la información por lo cual se le solicita seguir los siguientes pasos: 
				</parrafo>
			 	<ol>
				  <li><a href="https://mi.ia.supersociedades.gov.co/WebData/">Iniciar sesión en MI.</a></li>
				  <li>Ir al menú de Servicios y dar clic en Revisiones.</li>
				  <li>Seleccionar la solicitud que desea revisar.</li>
				  <li>El sistema mostrará la información general de la solicitud.</li>
				  <li>Al dar clic en la solicitud el usuario podrá ver el detalle de la solicitud de la misma.</li>
				  <li>En la opción Archivos Adjuntos pondrá los documentos cargados en la solicitud.</li>
				  <li>El usuario dará clic en aprobar la solicitud, si la misma corresponde a la realidad.</li>
				  <li>El sistema generará una ventana emergente para confirmal la aprobación de la solicitud e Ingerar el token de seguridad generado en SignApp.</li>
				  <li>Finalmente, podrá dar clic en aceptar</li>
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
