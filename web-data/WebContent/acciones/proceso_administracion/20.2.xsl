<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaProcesoAdmin.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<no-plugins/>

		<pagina titulo="Procesos">

			<javascript>proceso_administracion/20.2.js</javascript>
			<javascript>proceso_administracion/informacion_proceso.js</javascript>
			<javascript>proceso_administracion/modelo_proceso.js</javascript>
			<javascript>proceso_administracion/detalle_instancia.js</javascript>
			<javascript>proceso_administracion/detalle_accion.js</javascript>
			<javascript>proceso_administracion/jsDraw2D.js</javascript>
			
			<stylesheet>20.2.css</stylesheet>
			<stylesheet>proceso_admin/proceso_admin.css</stylesheet>
				
			<principal>

				<contenido>
				
						<formulario id="form_recargar" destino="proceso_administracion/20.2.do"/>
						
						<variable id="id_proceso_admin" valor="{id_proceso_admin}" />
						<variable id="id_negocio_proceso_admin" valor="{id_negocio_proceadmin}" />
						
						<!-- CUADRO DE PROCESO-->
						<div id="contenedor_general" class="contenedor_general">
						
							<div id="informacion_proceso" class="informacion_proceso">
								<bloque-contenido>
									<titulo>Proceso</titulo>
									<contenido>
							
										<div id="divcaja_nombre_proceso_admin" class="col-sm-3 div_proceso_nombre">
											Nombre: <input id="cajatex_nombre_proceso_admin" class="input_proceso form-control" type="text" autocomplete="off" onchange="actualizarProceso();"/>
										</div>
										
										<div class="col-sm-3 div_proceso_negocio">	
											<div class="div_nombre_sel">Negocio:</div> <div id="divsel_negocio_proceso_admin" class="div_select"></div>
										</div>	
										
										<div class="col-sm-3 div_proceso_formato">
											<div class="div_nombre_sel"><texto key="FORMATO DE ENTRADA" />:</div> <div id="divsel_formato_proceso_admin" class="div_select" ></div>
										</div>
										
										<div class="col-sm-3 div_proceso_botones">
											<boton estilo="volver" id="btn_volver_list_proc_admin" accion="osm_go('proceso_administracion/20.1.do');return false;">Volver al Listado</boton>
										</div>
										
									</contenido>
								</bloque-contenido>
								
							</div>
							
							<div id="alerta_inicio" class="alert alert-info"><p>Generando proceso...</p></div>
							
							<div id="lista_inst_proceadmin" class="informacion_instancia" style="display:none">
								
							</div>
							
							<div id="area_dibujo" style="display:none">
								
								<div id="div_btn_crear_inst" class="div_btn" onclick="crearInstancia({id_proceso_admin});">
												+
								</div>
								
								<div id="div_btn_eliminar_inst" class="div_btn" onclick="eliminarInstanciaSeleccionada();">
												-
								</div>
								
								
								<div class="area_proceso" id="AREA_PROCESO">
										<div id ="AREA_PROCESO_LINEAS" class="area_proceso_lineas">
										</div>
										 <xsl:call-template name="PLANTILLA_INSTANCIA_GRAFICA"/>			
								</div>
							</div>
							
							<div id="contenido_accion_redireccion" ></div>
															
						</div>
						
						<xsl:call-template name="PLANTILLAS_INSTANCIA"/>
						<xsl:call-template name="PLANTILLAS_ACCION"/>
						<xsl:call-template name="PLANTILLAS_ROL"/>
						
						
						<div id="temp_div_horario" style="display:none">
							<div id="blk_horario">
								<input type="hidden" id="id_horario"/>
								<xsl:call-template name="HORARIO"/>
							</div>
						</div>
					
				</contenido>
			</principal>
			
			<ventana id="VN_EDICION_ACCIONES" icono="confirmacion">
				<titulo>Edicion de acción</titulo>
				<contenido>
					
					<div id="div_contenido_ventana_accion">
						
					</div>
					
					<area_botones>
						<boton estilo="primary guardar" id="btn_aprobarcarga"
							accion="cerrarVentanaEdicion('VN_EDICION_ACCIONES')" >
							Cerrar Edicion
						</boton>
					</area_botones>
				</contenido>
			</ventana>
			
			<ventana id="VN_EDICION_ACCIONES_REDIRECCION" icono="confirmacion">
				<titulo>Edicion de acción de redirección</titulo>
				<contenido>
					
					<div id="div_contenido_ventana_accion_redireccion">
						
					</div>
					
					<area_botones>
						<boton estilo="primary guardar" id="btn_aprobarcarga"
							accion="cerrarVentanaEdicion('VN_EDICION_ACCIONES_REDIRECCION')" >
							Cerrar Edicion
						</boton>
					</area_botones>
				</contenido>
			</ventana>
			
			<xsl:call-template name="VENTANA_ACTUALIZA_PROCESO_ADMIN"></xsl:call-template>
			
			<xsl:call-template name="HORARIO_VENTANA" />

		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>
