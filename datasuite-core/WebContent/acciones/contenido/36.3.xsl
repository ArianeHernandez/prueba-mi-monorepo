<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Resultado de la operación">
				
			<principal>
			<titulo>Resultado de la operación</titulo>
				<contenido>
				
				<xsl:choose>
					<xsl:when test="//actualizarTextoContenido/exitoso = 'true'">
						La actualizacion se ha realizado con exito.
					</xsl:when>
					
					<xsl:otherwise>
						Lo sentimos, la operacion no se ha podido llevar a cabo. Por favor intentelo nuevamente.
					</xsl:otherwise>
				</xsl:choose>	
				
				<div id="area_botones" >
					<area_botones>
						<boton estilo="primary" destino="contenido/36.1.do">Volver al listado</boton>
					</area_botones>
				</div>
					
					
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>
	


</xsl:stylesheet>
