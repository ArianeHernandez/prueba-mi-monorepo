<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<pagina>
			<principal>
			
				<titulo>Resultado de la operacion</titulo>
				<contenido>
					<xsl:choose>
						<xsl:when test="//eliminarValidacionEstructuraVariosCampos/exitosa='true'">
							<nota>Eliminacion exitosa.</nota>
						</xsl:when>
						<xsl:otherwise>
							<alerta>Eliminacion fallida, por favor intentelo nuevamente.</alerta>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
					<br/>
					<br/>
						<boton estilo="volver" id="btn_volver_list_proc_admin" 
								accion="osm_go('validaciones_estructura/28.1.do');return false;">
						Volver al Listado</boton>
					</area_botones>
					
				</contenido>
			</principal>		
		</pagina>
		
	</xsl:template>
	
	

</xsl:stylesheet>
