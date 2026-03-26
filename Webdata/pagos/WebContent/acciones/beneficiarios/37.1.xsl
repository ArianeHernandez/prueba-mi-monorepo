<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Respuesta Desactivar Beneficiarios">

			<principal>
				<titulo>Respuesta Desactivar Beneficiarios</titulo>
				<contenido>
					
					<xsl:choose>
						<xsl:when test="//eliminarRegistros/Boolean = 'true'">
							<nota>
								Los registros se eliminarón con éxito.
							</nota>
						</xsl:when>
						<xsl:otherwise>
							<alerta>
								No fue posible eliminar los registros.
							</alerta>
						</xsl:otherwise>
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary volver" destino="beneficiarios/37.do">Volver</boton>
						<boton estilo="danger" destino="inicio/0.do">Cancelar</boton>
					</area_botones>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>
	
</xsl:stylesheet>
