<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Activación Exitosa</titulo>
			<contenido>
				<parrafo>
					El registro en el Módulo de Insolvencia ha finalizado con la activación de su usuario de forma exitosa.
				</parrafo>
				<br></br>
				<parrafo>
					Para generar una solicitud a un proceso Insolvencia ingrese al siguiente link <xsl:value-of select="//link_markup"></xsl:value-of>
				</parrafo>
				<br></br>
				<parrafo>
					En el siguiente link puede ver el tutorial con el paso a paso de realización de una solicitud <xsl:value-of select="//link_video"></xsl:value-of>
				</parrafo>
				<texto key="NO SE HA APROBADO LA CARGA" />
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
