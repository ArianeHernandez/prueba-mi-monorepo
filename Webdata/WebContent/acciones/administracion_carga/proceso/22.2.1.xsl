<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>
	<xsl:include href="context://stylesheets/templates/firma/InformacionFirmaDigitalCarga.xsl"/>
	
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina
			titulo="{Detalle - {//obtenerPersona/Persona/nombre} para administrativo hijo">

			<javascript>administracion_carga/proceso/22.2.1.js</javascript>
			
			<stylesheet>aprobacion.css</stylesheet>
			

			<principal>
				<titulo>Proceso <xsl:value-of select="//obtenerProcesoAdmin/Proceso/nombre"/> / <texto key="CARGA" /> No. <xsl:value-of select="//obtenerCarga/Carga/id_carga" /></titulo>
				<contenido>
				<div class="box-container">
					<bloque-pestanas>
						<pestana titulo="Información de general">
							<contenido>
								<parrafo><br/><texto key="INFORMACION DETALLADA DE LA CARGA" /><br/></parrafo>
								
								<!-- INFO CARGA -->
								<tabla>
									<encabezado>
										<titulo>Tipo</titulo>
										<titulo>Identificador <texto key="CARGA" /></titulo>
										<titulo>Nombre</titulo>
										<titulo>Fecha de Liberación</titulo>
										<titulo>Estado</titulo>
										<titulo>N° registros</titulo>
										<!-- VALOR OPCIONAL -->
										<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
											<titulo>Valor</titulo>
										</xsl:if>	
									</encabezado>
									<xsl:for-each select="//obtenerCarga/Carga">
										<fila>
											<valor>
												<xsl:choose>
													<xsl:when test="extension='xls'">
														<bloque	estilo="extension_xls" />
													</xsl:when>
													<xsl:otherwise>
														<bloque estilo="extension_none" />
													</xsl:otherwise>
												</xsl:choose>
											</valor>
											<valor>
												<xsl:value-of select="id_carga" />
											</valor>
											<valor>
												<xsl:value-of select="nombre" />
											</valor>
											<valor>
												<xsl:value-of select="fecha_liberacion" />
											</valor>
											<valor>
												<b>
													<estado key="{estado}" cliente="S"/>
												</b>
											</valor>
											
											<!-- VALOR OPCIONAL -->
											<xsl:if test="valor_total_bigdecimal!=''">
												<valor>
													<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
												</valor>
											</xsl:if>
											<valor>
												<xsl:value-of select="numero_registros_bigdecimal" />
											</valor>
										</fila>
									</xsl:for-each>
								</tabla>
								
								<xsl:call-template name="INFORMACION_FIRMA_DIGITAL_CARGA"></xsl:call-template>
								
			
			
								<!-- DETALLE DE LA CARGA -->
								<xsl:call-template name="DETALLE_CARGA_AJAX">
									<xsl:with-param name="validacionesActivas">false</xsl:with-param>
								</xsl:call-template>
								
								<formulario id="frm_aprobar" destino="administracion_carga/proceso/22.3.1.do">
											<variable id="id_instancia" valor="{//id_instancia}" />
											<variable id="id_formato_salida_admin_hijo" valor="{//id_formato_salida}" />
											<variable id="accionaprobar" valor="S" />
								</formulario>
									
								
								<formulario id="frm_accion" destino="administracion_carga/proceso/22.4.1.do">
									<variable id="id_instancia" valor="{//id_instancia}" />
									<variable id="id_formato_salida_admin_hijo" valor="{//id_formato_salida}" />
									<variable id="id_accion_admin_hijo" valor="" />
									
									<!-- OBJETO INSTANCIA_ACCION-->
									<variable id="InstanciaAccion.id_instancia" valor="{//id_instancia}" />
									<variable id="InstanciaAccion.id_accion" valor="" />
									<variable id="InstanciaAccion.id_proceso_admin" valor="{//id_proceso_admin}" />
									<variable id="InstanciaAccion.id_administrativo" valor="{//id_administrativo}" />
									<variable id="InstanciaAccion.id_carga" valor="{//id_carga}" />
									
								</formulario>
							</contenido>
						</pestana>
						
						
						<!--HISTORIAL DE LA CARGA-->
						<pestana titulo="Historial">
							<contenido>
								
								<parrafo>
									<br/>
									<texto key="INSTANCIAS POR LAS CUALES HA TRANSCURRIDO LA CARGA" />.
									<br/>
								</parrafo>
											
								<tabla>
									<encabezado>
										<titulo>Instancia del proceso</titulo>
										<titulo>Fecha de llega a la instancia</titulo>
										<titulo>Fecha de salida a la instancia</titulo>
										<titulo>Persona encargada</titulo>
									</encabezado>
										<xsl:for-each select="//obtenerHistorialCargaInstancia/listado/CargaInstancia">
											<fila>
												<valor>
													<xsl:value-of select="nombre_instancia"/>
												</valor>
												<valor>
													<xsl:value-of select="fecha_llegada"/>
												</valor>
												<valor>
													<xsl:value-of select="fecha_salida"/>
												</valor>
												<valor>
													<xsl:value-of select="nombre_persona"/>
													&#160;
													<xsl:value-of select="apellido_persona"/>
												</valor>
												
											</fila>
										</xsl:for-each>
								</tabla>
							</contenido>
						</pestana>
						
						<!-- ARCHIVOS ADJUNTOS -->
						<pestana titulo="Archivos adjuntos" pdfview="false">
							<xsl:call-template name="archivos_adjuntos">
								<xsl:with-param name="permiteAdjuntar">false</xsl:with-param>
								<xsl:with-param name="permiteEliminar">false</xsl:with-param>
							</xsl:call-template>
						</pestana>		
						
					</bloque-pestanas>	
					
					
					<area_botones>
						<!-- ACCIONES INTERMEDIAS DE LA INSTANCIA-->
						<xsl:choose>
							
							<!-- SI LA MISMA CARGA ESTA PENDIENTE EN INSTANCIAS PREVIAS NO SE MUESTRAN NINGUNO DE LOS BOTONES-->
							<xsl:when test="count(//obtenerInstanciasPreviasConCargaActualPendiente/lista/Instancia)>0">
								<nota>
									No se puede realizar ninguna acción sobre la transacción <b><xsl:value-of select="//id_carga"/></b>  mientras las siguientes instancias del proceso
									no hayan dado un concepto sobre ésta.
									<br/><br/>
									Instancias:
									<xsl:for-each select="//obtenerInstanciasPreviasConCargaActualPendiente/lista/Instancia">
										-<b><xsl:value-of select="nombre"/></b>
									</xsl:for-each>  
								</nota>
							</xsl:when>
							
							<!-- SI LA MISMA CARGA NO ESTA PENDIENTE EN INSTANCIAS PREVIAS SE MUESTRAN LOS BOTONES-->
							<xsl:when test="count(//obtenerInstanciasPreviasConCargaActualPendiente/lista/Instancia)=0 and //obtenerCarga/Carga/estado='S'">
								<xsl:if test="count(//obtenerAccionesConInstanciaDestinoPorInstanciaOrigen/listado/Accion)>0">
										
									<xsl:for-each select="//obtenerAccionesConInstanciaDestinoPorInstanciaOrigen/listado/Accion">
										<xsl:if test="oculto!='S' or count(oculto)=0">
											<boton estilo="primary guardar"	accion="verVentanaEjecutarAccion('{id_accion}', '{nombre}')"><xsl:value-of select="nombre" /></boton>	
										</xsl:if>	
											
									</xsl:for-each>
									
								</xsl:if>		
								
								<!-- APROBAR / DESAPROBAR -->
								<xsl:if test="//obtenerInstanciaDelProcesoPorCarga/instancia/aprobar ='S' or //obtenerInstanciaDelProcesoPorCarga/instancia/rechazar ='S'">
											
										<xsl:if	test="//obtenerInstanciaDelProcesoPorCarga/instancia/aprobar ='S'">
											<boton estilo="primary guardar"	accion="verVentanaAprobarCarga()">Aprobar Carga</boton>
										</xsl:if>
										
										<xsl:if	test="//obtenerInstanciaDelProcesoPorCarga/instancia/rechazar ='S'">
											<boton estilo="danger eliminar" accion="verVentanaRechazarCarga()">Rechazar Carga</boton>
										</xsl:if>
											
								</xsl:if>
								
							</xsl:when>
							
							
							
						</xsl:choose>
						
								
						<boton estilo="primary volver" destino="administracion_carga/proceso/22.1.1.do">Volver</boton>
					
					</area_botones>
					</div>
				</contenido>	
			</principal>


			<!-- VENTANAS DE CONFIRMACION -->
			<xsl:variable name="carga" select="//obtenerCarga/Carga"></xsl:variable>
						
			
			<ventana id="vn_accion_instancia" icono="confirmacion">
				
				
			
				<titulo>Ejecutar Acción</titulo>
				<contenido>

					<area_botones>
						<subtitulo>
							¿Está Seguro que desea ejecutar la acción?
							
							<parrafo estilo="resaltado">
									
									<b>- Cliente:</b>&#160;&#160;<xsl:value-of select="$carga/nombre_usuario"/>&#160;<xsl:value-of select="$carga/apellido_usuario"/>
									<br/>
									<br/>
									<b>- Acción:</b>&#160;&#160;<span id="nombre_accion"></span>
									<br/>
									<br/>
									<b>- Transacción:</b>&#160;&#160;<xsl:value-of select="$carga/id_carga"></xsl:value-of>
									<br/>
									<br/>
									<b>- Fecha de liberación:</b>&#160;&#160;<xsl:value-of select="$carga/fecha_subida"/>
									<br/>
									<br/>
									<xsl:if test="$carga/valor_total_bigdecimal!=''">
										<b>- Valor total :</b>&#160;&#160;<xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
									</xsl:if>
									
								
							</parrafo>
							

							
						</subtitulo>
						<boton estilo="primary guardar" id="btn_aprobarcarga" validacion="validarArchivosPendientes()"
							accion="cerrarVentanaEjecutarAccion()" formulario="frm_accion">
							Ejecutar acción
						</boton>
						<boton estilo="danger" id="btn_cancelar"
							accion="cerrarVentanaEjecutarAccion()">
							Cerrar Ventana
						</boton>
					</area_botones>
				</contenido>
			</ventana>
					
			<ventana id="vn_aprobarcarga" icono="confirmacion">
				<titulo>Confirmación de Aprobación</titulo>
				<contenido>

					
						<div class="row-box form-horizontal">
						
								<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i> ¿Está Seguro que desea <b> "APROBAR" </b> la transacción? </div>
							
							
								<div class="col-sm-10 col-sm-offset-1">
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Cliente</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/nombre_usuario"/>&#160;<xsl:value-of select="$carga/apellido_usuario"/>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Transacción</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/id_carga"></xsl:value-of>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Fecha de liberación</label>
										<div class="col-sm-8">
											<span class="form-control disable"><xsl:value-of select="$carga/fecha_subida"/></span>
										</div>
									</div>
									<div class="form-group form-group-sm">
										<xsl:if test="$carga/valor_total_bigdecimal!=''">
											<label class="control-label col-sm-4">Valor total</label>
											<div class="col-sm-8">
												<span class="form-control disable"><xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos')"/></span>
											</div>									
										</xsl:if>
									</div>
								</div>
							</div>
							
						
						<div class="row-btn">
						<boton estilo="guardar" id="btn_aprobarcarga"
							accion="cerrarVentanaAprobarCarga()" formulario="frm_aprobar">
							Aprobar
						</boton>
						<boton estilo="cancelar" id="btn_cancelar"
							accion="cerrarVentanaAprobarCarga()">
							Cerrar Ventana
						</boton>
						</div>
				</contenido>
			</ventana>
			
			<ventana id="vn_detalleLog" icono="confirmacion">
				<titulo>Detalle</titulo>
				<contenido>
					
					<bloque id="cc_contenido_ventana_detalle">
						.
					</bloque>
					
					<div class="row-btn">
						<boton estilo="cancelar" id="btn_cancelar" accion="cerrarVentanaDetalleLog()">
							Cerrar Ventana
						</boton>
					</div>
				</contenido>
			</ventana>

			<ventana id="vn_rechazarcarga" icono="advertencia">
				<titulo>Confirmación de Rechazo</titulo>
				<contenido>

					<formulario id="frm_rechazar" destino="administracion_carga/proceso/22.3.1.do">
						<variable id="accionrechazar" valor="S" />
						<variable id="id_instancia" valor="{//id_instancia}" />
						<variable id="id_formato_salida_admin_hijo" valor="{//id_formato_salida}" />
					</formulario>

					<div class="row-box form-horizontal">
						
							<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i>¿Está Seguro que desea <b> "RECHAZAR" </b>la transacción? </div>
							
			
								<div class="col-sm-10 col-sm-offset-1">
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4"> Cliente</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/nombre_usuario"/>&#160;<xsl:value-of select="$carga/apellido_usuario"/>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4"> Transacción</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/id_carga"></xsl:value-of>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4"> Fecha de liberación</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/fecha_subida"/>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">	
										<xsl:if test="$carga/valor_total_bigdecimal!=''">
											<label class="control-label col-sm-4"> Valor total</label>
											<div class="col-sm-8">
												<span class="form-control disable">
													<xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
												</span>
											</div>
										</xsl:if>
								    </div> 
								</div>	
							</div>
						
						<boton estilo="primary guardar" id="btn_aprobarcarga"
							accion="cerrarVentanaRechazarCarga()"
							formulario="frm_rechazar">
							Rechazar
						</boton>
						<boton estilo="danger" id="btn_cancelar"
							accion="cerrarVentanaRechazarCarga()">
							Cerrar Ventana
						</boton>
					
				</contenido>
			</ventana>

		</pagina>

	</xsl:template>

	
</xsl:stylesheet>
