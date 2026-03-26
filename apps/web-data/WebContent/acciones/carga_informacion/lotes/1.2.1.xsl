<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
	<xsl:include href="context://stylesheets/templates/CamposAdicionales.xsl"/>
	<xsl:include href="context://stylesheets/templates/firma/firmarFormulario.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Información">
			<javascript>webdata/1.2.1.js</javascript>
			<stylesheet>1.1.css</stylesheet>
			<principal>
				<titulo><texto key="CARGA" /> <texto key="MASIVA"/> - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/></titulo>
				<xsl:variable name="hay_listaDC" select="count(//obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo) > 0"/>
								
				<contenido>
					
					<formulario destino="carga_informacion/lotes/1.2.2.do" id="form_enviar">
						
						<!-- INICIO INFORMACION DE LA TRANSACCION -->
						<input type="hidden" name="transaccion" value="{//ID_CARGA}"></input>
						<input type="hidden" name="realizada_por" value="{concat(//obtenerCarga/Carga/nombre_persona, ' ', //obtenerCarga/Carga/apellido_persona)}"></input>
						<input type="hidden" name="fecha_subida_transaccion" value="{//obtenerCarga/Carga/fecha_subida_string}"></input>
						<input type="hidden" name="cliente" value="{//obtenerCarga/Carga/nombre_usuario}"></input>
						<input type="hidden" name="archivo" value="{concat(//obtenerCarga/Carga/nombre,'_',//obtenerCarga/Carga/extension)}"/>
						<input type="hidden" name="enviar" value="S"/>
						<!-- FINAL  INFORMACION DE LA TRANSACCION -->
						
						
						
						<xsl:for-each select="//generarTablaControl/listado/TablaOperacion">
								<variable id="envionombrecampocontrol_{position()}" valor="{titulo}" />
								<variable id="enviocampocontrol_{position()}" valor= "{resultado}"/>
						</xsl:for-each>								
									
						
						<xsl:if test="count(//obtenerParametrosCarga/listado/ParametroCarga)>0">
							
							<bloque-contenido>
								<titulo>Parametros de <texto key="CARGA" /> - Archivo '<xsl:value-of select="obtenerCarga/Carga/nombre"/>.<xsl:value-of select="obtenerCarga/Carga/extension"/>'</titulo>
								<contenido>
							
					
									<parrafo>A continuación se presentan los parametros solicitados a la <texto key="CARGA" /> .</parrafo>
									<tabla>
										<encabezado>
											<titulo>Parametro</titulo>
											<titulo>Valor</titulo>
										</encabezado>
										<xsl:for-each select="//obtenerParametrosCarga/listado/ParametroCarga">
											<fila>
												<valor>
													<xsl:value-of select="nombre"/>
												</valor>
													
												<valor>
													<variable id="ValorParametroCarga:{position()}.id_parametro" valor= "{id_parametro_carga}"/>
													<cajatexto id="ValorParametroCarga:{position()}.valor" valor=""/>
												</valor>
												
											</fila>
										</xsl:for-each>
									</tabla>
								</contenido>
							</bloque-contenido>
							
							
						
						</xsl:if>
						
						<xsl:if test="$hay_listaDC">
								
								<xsl:for-each select="obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo">
								<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_campo" value="{id_campo}" />
								<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_carga" value="{//ID_CARGA}" />
								<input type="hidden" name="listaValoresDinamicos:{id_campo}.valor" id="listaValoresDinamicos_{id_campo}_valor" value="" />
								</xsl:for-each>
						</xsl:if>
					</formulario>
					
					
					<bloque-pestanas>
					
						<pestana titulo="Tabla de Control">
					
							<bloque-contenido>
								<titulo>Archivo '<xsl:value-of select="obtenerCarga/Carga/nombre"/>.<xsl:value-of select="obtenerCarga/Carga/extension"/>'</titulo>
								<contenido>
													
									<xsl:choose>
										<xsl:when test="count(//generarTablaControl/listado/TablaOperacion)>0">
									
											<parrafo>A continuación se presenta la informacion de control del archivo.</parrafo>
											<variable id="validacionActiva" valor="{//obtenerFormato/Formato/control_usuario}"/>
											<tabla>
												<encabezado>
													<titulo>Campo</titulo>
													<titulo>Operación</titulo>
													<titulo>Valor</titulo>
												</encabezado>
												<xsl:for-each select="//generarTablaControl/listado/TablaOperacion">
													<fila>
														<valor>
															<xsl:value-of select="titulo"/>
															<variable id="nombrecampocontrol_{position()}" valor="{titulo}" />
															<variable id="campocontrol_{position()}" valor= "{resultado}"/>
														</valor>
														<valor><xsl:value-of select="operacion/descripcion"/></valor>
														<xsl:choose>
															<xsl:when test="//obtenerFormato/Formato/control_usuario = 'S' and operacion/id_operacion= '2'">
																<valor>
																	<div id="div_sinformato_double_{position()}">
																		<cajatexto id="campouser_{position()}" valor="" onblur="mostrarDoubleConFormato('{position()}')"/>
																	</div>
																	
																	<div class="con_formato" id="div_formato_double_{position()}" onclick = "mostrarDoubleSinFormato('{position()}')" style="display:none"></div>
																
																</valor>
															</xsl:when>
															<xsl:when test="//obtenerFormato/Formato/control_usuario = 'S' and operacion/id_operacion != '2'">
																<valor><cajatexto id="campouser_{position()}" valor=""/></valor>
															</xsl:when>
															<xsl:otherwise>
																<valor><parrafo estilo="resaltado"><xsl:value-of select="resultado"/></parrafo></valor>
															</xsl:otherwise>	
														</xsl:choose>
													</fila>
												</xsl:for-each>
											</tabla>
													
										</xsl:when>
										<xsl:otherwise>
											<parrafo estilo="resaltado">El formato no tiene valores de control.</parrafo>
										</xsl:otherwise>
										
									</xsl:choose>
									
									
									
								</contenido>
							</bloque-contenido>
							
							<!-- CAMPOS ADICIONALES -->
							<xsl:if test="$hay_listaDC">
								
								<div style="display:none">
									<select autocomplete="off" id="select_negocios_libera" class="form-control">
											<option value="{//OSM-SESSION/CONFIGURACION/ID_NEGOCIO}">
													 <xsl:value-of select="//OSM-SESSION/CONFIGURACION/ID_NEGOCIO"/>
											</option>
									</select>
								</div>
								
							
							
								<bloque-contenido>
									<titulo>Variables de liberación</titulo>
									<contenido>
										<xsl:call-template name="CAMPOS_ADICIONALES"/>
									</contenido>
								</bloque-contenido>
								
								<div id="area_mensaje_liberacion"></div>
								
							</xsl:if>
							
						</pestana>
						
						<pestana titulo="Archivos Adjuntos">
						
							<!-- ARCHIVOS ADJUNTOS -->
						
							<xsl:call-template name="archivos_adjuntos">
								<xsl:with-param name="permiteAdjuntar">true</xsl:with-param>
								<xsl:with-param name="permiteVer">true</xsl:with-param>
								<xsl:with-param name="permiteEliminar">true</xsl:with-param>
							</xsl:call-template>
					
						</pestana>
					
					</bloque-pestanas>
					
					
					<xsl:choose>
						<xsl:when test="(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') or (//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
							<xsl:call-template name="firmarFormulario">
							<xsl:with-param name="id_componente_firma">firmaEnviar1</xsl:with-param>
							<xsl:with-param name="formularioParaFirmar">form_enviar</xsl:with-param>
							<xsl:with-param name="id_carga"><xsl:value-of select="//ID_CARGA"></xsl:value-of></xsl:with-param>
							<xsl:with-param name="nombreFaseProceso">PREPARACION MASIVA</xsl:with-param>
							<xsl:with-param name="funcionJavaScript_validacion">validarEnvioCarga()</xsl:with-param>
							<xsl:with-param name="nombre_boton">Firmar y Enviar</xsl:with-param>
							<xsl:with-param name="rutaBotonVolver">carga_informacion/lotes/1.2.do</xsl:with-param>
						</xsl:call-template>
						
						</xsl:when>
						
						<xsl:otherwise>
							<area_botones>
								<boton estilo="primary volver" destino="carga_informacion/lotes/1.2.do">Volver</boton>
								<boton estilo="avanzar" accion="enviar_carga()">Enviar</boton>
							</area_botones>
						</xsl:otherwise>
					
					
					</xsl:choose>
				
					
					
					<input type="hidden" name="tipo_persona_cliente" id="tipo_persona_cliente" value="{//esclientenatural}"/>
				</contenido>
			</principal>
			
		</pagina>
		
		
		<ventana id="vn_aprobarcarga" icono="confirmacion">
				<titulo>Confirmación de Envio</titulo>
				<contenido>

					<div class="row-box form-horizontal">
						
							<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i> ¿Está seguro que desea enviar la transacción? </div>
							
						<xsl:if test="string-length(//FORMATO_ENTRADA/obtenerFormato/Formato/mensaje_liberacion) > 1">
							<parrafo estilo="resaltado">
								<b><xsl:value-of select="//FORMATO_ENTRADA/obtenerFormato/Formato/mensaje_liberacion"/></b>
							</parrafo>
						</xsl:if>
						<div class="row-btn">
					 		<boton estilo="guardar" id="btn_aprobarcarga"
								accion="enviarDefinitivamente()">
								Aprobar
							</boton>
												
							<boton estilo="cancelar" id="btn_cancelar"
								accion="cerrarVentanaAprobarCarga()">
								Cerrar Ventana
							</boton>
						</div>
					</div>
				</contenido>
			</ventana>
			
		
	</xsl:template>

</xsl:stylesheet>

