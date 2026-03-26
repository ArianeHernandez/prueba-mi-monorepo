<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración de Procesos">
			
			<javascript>crear_proceso/10.1.js</javascript>
			
			<principal>
				<titulo>Administración de Procesos</titulo>
				<contenido>
					
					<subtitulo>Cliente: Todos</subtitulo>
					
					<formulario destino="procesos_cliente/39.2.do" id="form_siguiente">
						<variable id="id_proceso" valor=""/>
					</formulario>
					
					<formulario destino="procesos_cliente/39.1.do" id="form_eliminar_proceso">
						<variable id="id_proceso_eliminar" valor=""/>
					</formulario>
					
					<xsl:choose>
						<xsl:when test="count(listarProcesos/Listado/Proceso)>0">
						 	<tabla icono="service">
								<encabezado>
									<titulo>ID</titulo>
									<titulo>Nombre del Proceso</titulo>
									<titulo>Estado</titulo>
									<titulo>Eliminar</titulo>
								</encabezado>
								<xsl:for-each select="listarProcesos/Listado/Proceso">
									<xsl:sort data-type="number" select="id_proceso"/>
									<fila>
										<valor accion="edicionProceso({id_proceso})"><xsl:value-of select="id_proceso"/></valor>
										<valor accion="edicionProceso({id_proceso})"><b><xsl:value-of select="nombre"/></b></valor>
										<valor>
											<cajachequeo accion="cambiarEstado(this.checked, {id_proceso})">
												<xsl:if test="estado='A'">
													<xsl:attribute name="seleccionado">true</xsl:attribute>
												</xsl:if>
											</cajachequeo>
										</valor>
										<valor>
											<boton estilo="primary aceptar" accion="eliminarProceso({id_proceso})">Eliminar</boton>
										</valor>
									</fila>
								</xsl:for-each>
							</tabla>
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No tiene procesos asociados.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary aceptar" accion="verVentana()">Crear Proceso</boton>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
			<!-- - - - - - - - - - - - - - - - - - - - - - - - -->
			
			<ventana id="ventanaProceso" icono="confirmacion">
				<titulo>Creación de Proceso</titulo>
				<contenido>
					
					<bloque estilo="grupo">
						<subtitulo>Ingrese la Información basica para la creacion del proceso.</subtitulo>
						
						<registro>
							<item>Nombre del Proceso:</item>
							<valor>
								<cajatexto id="nombreProceso" valor=""/>
							</valor>
						</registro>
						<variable id="idTipoProceso" valor="1"/>
						
					</bloque>
		
					<area_botones>
						<boton estilo="primary aceptar" id="btn_crearProceso" accion="crearProceso()">Crear Proceso</boton>
						<boton estilo="danger" id="btn_cerrar" accion="cerrarVentana()">Cerrar</boton>
					</area_botones>
				</contenido>
			</ventana>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
