<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Usuario Creado</titulo>
			<contenido>
				<parrafo>El usuario <xsl:value-of select="//persona/nombreCompleto"/> ha sido creado con los siguientes datos de Ingreso</parrafo>
				<registro>
					<item>Login:</item>
					<valor>
						<constante><xsl:value-of select="//persona/login"/></constante>
					</valor>
				</registro>
				<registro>
					<item>Clave:</item>
					<valor>
						<constante><xsl:value-of select="//clave"/></constante>
					</valor>
				</registro>
				<parrafo>Por razones de seguridad es conveniente que modifique su contraseña,</parrafo> 
				<parrafo>esto lo puede hacer en el servicio "Configuración de Usuario"</parrafo>
			</contenido>
	
		</email>
		
	</xsl:template>

</xsl:stylesheet>
