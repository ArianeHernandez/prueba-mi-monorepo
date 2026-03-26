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
			<javascript>tareas/6.1.js</javascript>
			
			<principal>
				<titulo icono="variable">Tareas Programadas</titulo>
				<contenido>
				
					<tabla>
						<encabezado>
							<titulo>Nombre</titulo>
							<titulo>Intervalo</titulo>
							<titulo>Fecha de Inicio</titulo>
							<titulo>Activo</titulo>
							<titulo>Eliminar</titulo>
						</encabezado>
						<xsl:for-each select="obtenerTareas/Listado/Tarea">
							<xsl:variable name="tipo_intervalo" select="tipo_intervalo"></xsl:variable>
							<fila  id="tarea_{id_tarea}">
								<valor accion="ver_tarea('{id_tarea}')"><b><xsl:value-of select="nombre"/></b></valor>
								<valor accion="ver_tarea('{id_tarea}')">
									<xsl:value-of select="intervalo_ejecucion"/> - <xsl:value-of select="//obtenerTiposIntervalos/Listado/*[@name=$tipo_intervalo]"/>
								</valor>
								<valor accion="ver_tarea('{id_tarea}')"><xsl:value-of select="fecha_inicio"/></valor>
								<valor accion="ver_tarea('{id_tarea}')">
									<xsl:choose>
										<xsl:when test="activar='S'">Si</xsl:when>
										<xsl:otherwise>No</xsl:otherwise>
									</xsl:choose>
								</valor>
								<valor><boton accion="eliminar('{id_tarea}')">Eliminar</boton></valor>
								
							</fila>
						</xsl:for-each>
					</tabla>
					
					<formulario id="formVerTarea" destino="tareas/6.2.do">
						<variable id="id_tarea"/>
					</formulario>
							
					<formulario id="form_pag" destino="tareas/6.2.do">
						<paginacion id="_numeropagina_tareas" formulario="form_pag" numero="{//numeroPagina}" 
							paginacion="{//TAMANO_PAGINA}" total="{contarTareas/Total}"/>
					</formulario>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="tareas/6.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
