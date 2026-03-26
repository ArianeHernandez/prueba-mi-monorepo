<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Tareas Programadas">
			<javascript>tareas/6.3.js</javascript>
			
			<principal>
				<titulo icono="variable">Tareas Programada</titulo>
				<contenido>
				
					<parrafo>
						<xsl:choose>
							<xsl:when  test="guardarTarea/Respuesta='true'">
								La tarea se almacenó con éxito.
							</xsl:when>
							<xsl:otherwise>
								Ocurrio un problema al guardar la tarea
							</xsl:otherwise>
						</xsl:choose>
						
					</parrafo>					
							
					<area_botones>
						<boton estilo="volver" destino="tareas/6.1.do">Volver</boton>
						<boton estilo="crear" destino="tareas/6.2.do">Guardar</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
