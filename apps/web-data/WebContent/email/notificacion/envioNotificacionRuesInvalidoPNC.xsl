<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo><xsl:value-of select="//notificacion/titulo"/></titulo>
			<contenido>
				<parrafo>El registro realizado en el Módulo de Insolvencia MI ante la Superintendencia de Sociedades presentó alguna de las siguientes inconsistencias:</parrafo> 
				<ul>
					<li>El deudor quien realizó la solicitud no se encuentra inscrito en el Certificado de Cámara y Comercio.</li> 
					<li>Quien realizó la solicitud correspondió a una persona diferente al deudor inscrito en el Certificado de Cámara y Comercio.</li>
				</ul> 
				<parrafo>
					<strong>Recuerde:</strong> Quien realiza el trámite de inscripción en el módulo de Insolvencia MI ante la Superintendencia de Sociedades debe corresponder <i>únicamente</i> al deudor inscrito en el Certificado de Existencia y Representación Legal.<br/>
				</parrafo><br />
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
