<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Soporte Pago</titulo>
			<contenido>
				<parrafo><b><xsl:value-of select="//persona/nombreCompleto"/></b>, se ha realizado la activación de su usuario en el sistema.</parrafo>
				<br/>
				<parrafo>El nombre de usuario es: <b><xsl:value-of select="//login"></xsl:value-of></b></parrafo>
				<xsl:if test="count(//usuario/*) > 0">
					<parrafo>La identificación del Cliente es: <xsl:value-of select="//usuario/identificacion"></xsl:value-of></parrafo>
				</xsl:if>
				<parrafo>
					En el siguiente enlace usted puede acceder para definir su contraseña de acceso al sistema.
				</parrafo>
				
				<a href="{//obtenerHost/value}/persona/activar_cuenta.pub?login={//login}&amp;id_cliente={//usuario/identificacion}&amp;id={//clave}">Activar Cuenta</a>
				
				
				
			</contenido>
	
		</email>
		
	</xsl:template>

</xsl:stylesheet>
