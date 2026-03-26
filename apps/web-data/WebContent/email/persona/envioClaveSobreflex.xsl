<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Asignación de Clave</titulo>
			<contenido>
				<parrafo><b><xsl:value-of select="nombre"/></b>, se ha realizado la asignación de clave por sobreflex para el acceso en el sistema.</parrafo>
				
				<br/>
				
				<parrafo>
					En el siguiente enlace usted puede acceder para visualizar el nombre de usuario de acceso al sistema.
				</parrafo>
				
				<a href="{//obtenerHost/value}/persona/usuario_sobreflex.pub?hs={hash_acceso}">Ver Nombre de Usuario</a>
				
			</contenido>
	
		</email>
		
	</xsl:template>

</xsl:stylesheet>
