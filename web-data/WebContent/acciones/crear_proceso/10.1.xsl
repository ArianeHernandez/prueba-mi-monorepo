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
			<stylesheet>10.css</stylesheet>
			
			<principal>
				<titulo><texto key="CREAR PROCESO" /></titulo>
				<contenido>
				<div class="box-container">
					
					<subtitulo>Cliente: <xsl:value-of select="nombre_usuario_seleccionado"/></subtitulo>
					
					<formulario destino="crear_proceso/10.2.do" id="form_siguiente">
						<variable id="id_proceso" valor=""/>
					</formulario>
					
					<formulario destino="crear_proceso/10.3.do" id="form_eliminar_proceso">
						<variable id="id_proceso_eliminar" valor=""/>
					</formulario>
					
					
					
					<xsl:choose>
						<xsl:when test="count(lista_procesos/Proceso)>0">
						 	<tabla icono="service">
								<encabezado>
									<titulo class="w030">ID</titulo>
									<titulo>Nombre del Proceso</titulo>
									<titulo class="w030">Activo</titulo>
									<titulo class="w100">Tipo de Cargue</titulo>
<!-- 									<titulo class="w100"></titulo> -->
								</encabezado>
								<xsl:for-each select="lista_procesos/Proceso">
									<xsl:sort data-type="number" select="id_proceso"/>
									<xsl:if test="id_usuario!=''">
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
												<xsl:choose>
													<xsl:when test="id_proceso = //obtenerFtpUsuarioPorUsuario/FtpUsuario/id_proceso">
														<div class="led activo">
															<p>Automático</p>
														</div>
													</xsl:when>
													<xsl:otherwise>
														<div class="led inactivo">
															<p>Inactivo</p>
														</div>
													</xsl:otherwise>
												</xsl:choose>
											</valor>
<!-- 											<valor> -->
<!-- 												<boton estilo="primary aceptar" accion="eliminarProceso({id_proceso})">Eliminar</boton> -->
<!-- 											</valor> -->
										</fila>
									</xsl:if>
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
				
				</div>	
				</contenido>
			</principal>
			
			<!-- - - - - - - - - - - - - - - - - - - - - - - - -->
			
			<ventana id="ventanaProceso" icono="confirmacion">
				<titulo>Creación de Proceso</titulo>
				<contenido>
					
					<bloque estilo="grupo">
					<div class="alert alert-info">
						<p>Ingrese la Información basica para la creacion del proceso.</p>
					</div>
					
						
						<div class="form-horizontal">
							
						<xsl:if test="count(//OSM-INIT-SESSION/Info/Negocio[id_negocio=0])=0">
							<registro>
								<item>Producto:</item>
								<valor>
									<constante id="idNegocio_new" valor="{//CONFIGURACION/NOMBRE_NEGOCIO}"/>
								</valor>
							</registro>
						</xsl:if>
						
							<registro>
								<item>Cliente:</item>
								<valor>
									<constante id="idUsuario_new" valor="{nombre_usuario_seleccionado}" />
								</valor>
							</registro>
							
							<registro>
								<item>Nombre del Proceso:</item>
								<valor>
									<cajatexto id="nombreProceso" valor=""/>
								</valor>
							</registro>
							</div>
						
						<variable id="idTipoProceso" valor=""/>
						<div id="area_inputs_proceso">
							<xsl:for-each select="tipoProceso/TipoProceso">
								<variable id="nombreTipoProceso_{id_tipo_proceso}" valor="{nombre}" />
								<variable id="numeroProceso_{id_tipo_proceso}" valor="{numero_procesos_por_cliente}" />
								<registro>
										<xsl:if test="proceso_por_defecto != 'S'">
											<item>
												<xsl:value-of select="nombre"/>
											</item>
											<valor>
												<input type="checkbox" name="id_tipo_proceso" id="id_tipo_proceso_{id_tipo_proceso}" value="{id_tipo_proceso}" onclick="chequeo('id_tipo_proceso_{id_tipo_proceso}')"/>
											</valor>
										</xsl:if>
								</registro>
							</xsl:for-each>
						</div>
						
					</bloque>
		
					<area_botones>
						<boton estilo="primary aceptar" id="btn_crearProceso" accion="crearProceso({id_usuario_seleccionado},{//CONFIGURACION/ID_NEGOCIO})">Crear Proceso</boton>
						<boton estilo="danger" id="btn_cerrar" accion="cerrarVentana()">Cerrar</boton>
					</area_botones>
				</contenido>
			</ventana>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
