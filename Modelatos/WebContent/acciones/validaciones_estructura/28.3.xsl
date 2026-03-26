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
				
					<xsl:if test="count(//guardarValidacionEstructuraVariosCampos/exitosa)>0">				
						<xsl:choose>
							<xsl:when test="//guardarValidacionEstructuraVariosCampos/exitosa='true'">
								<nota>Creacion exitosa</nota>
							</xsl:when>
							<xsl:otherwise>
								<alerta>Creacion fallida</alerta>
							</xsl:otherwise>
							
						</xsl:choose>
					</xsl:if>
					
					<xsl:if test="count(//actualizarValidacionEstructuraVariosCampos/exitosa)>0">				
						<xsl:choose>
							<xsl:when test="//actualizarValidacionEstructuraVariosCampos/exitosa='true'">
								<nota>Actualizacion exitosa</nota>
							</xsl:when>
							<xsl:otherwise>
								<alerta>Actualizacion fallida</alerta>
							</xsl:otherwise>
							
						</xsl:choose>
					</xsl:if>
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
