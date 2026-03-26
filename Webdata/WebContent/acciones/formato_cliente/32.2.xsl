<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Titulo">
			
			<principal>
				<titulo>Resultado de la operacion</titulo>
				<contenido>
				<div class="box-container">
				
					<xsl:choose>
						<xsl:when test="//guardarExcepcionesFormatoPorCliente/exitoso='true'">
							La operacion se ha guardado con exito.
						</xsl:when>
						<xsl:otherwise>
							Lo sentimos, la operacion no se ha podido completar con exito. Por favor intentelo nuevamente.
						</xsl:otherwise>
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary volver" id="boton_volver" destino="formato_cliente/32.1.do">Volver</boton>
					</area_botones>
					
				</div>
				</contenido>
			
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
