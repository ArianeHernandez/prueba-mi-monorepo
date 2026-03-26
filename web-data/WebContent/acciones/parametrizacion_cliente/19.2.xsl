<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Guardado">
			
			<principal>
				<titulo icono="formatos">Confirmación Guardado</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="//guardarHorarioLiberacion/guardado ='true'">
							<bloque-contenido>
								<titulo icono="confirmacion">Confirmación Guardado</titulo>
								<contenido>
								<xsl:choose>
									<xsl:when test="count(//obtenerHorarioLiberacion/Horario/id_horario)>0">
										<parrafo>El horario de liberacion se ha guardado Exitosamente.</parrafo>
									</xsl:when>
									
									<xsl:otherwise>
										<parrafo>El horario NO puedo guardarse correctamente</parrafo>
									</xsl:otherwise>
									
								</xsl:choose>	
								</contenido>
							</bloque-contenido>
						</xsl:when>
						
						<xsl:otherwise>
							<bloque-contenido>
								<titulo icono="error">Confirmación Guardado</titulo>
								<contenido>
									<parrafo>No se ha podido realizar la operacion.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Volver al inicio</boton>
						<boton estilo="primary editar" destino="parametrizacion_cliente/19.1.do">Editar parametrizacion</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
				
	</xsl:template>

</xsl:stylesheet>
