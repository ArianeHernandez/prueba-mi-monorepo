<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/carga/aprobacionCarga.xsl" />
	<xsl:include href="context://componentes/notas_carga/notas_carga.xsl" />
	<xsl:include href="context://stylesheets/templates/horario.xsl" />
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>
	<xsl:include href="context://stylesheets/templates/CamposAdicionales.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="seleccion_negocio" select="count(//OSM-INIT-SESSION/Info/Negocio[id_negocio=0])>0 and //FORMATO_ENTRADA/obtenerFormato/Formato/autorizacion_automatica = 'N'"/>
		
		<pagina
			titulo="{//obtenerFormato/Formato/nombre} - {//obtenerPersona/Persona/nombre}">
			

			<javascript>liberacion/15.3.js</javascript>
			<stylesheet>horario.css</stylesheet>
			<javascript>templates/mostrar_horario.js</javascript>
			
			<principal>
				
				<titulo>
					<b>PASO 2 de 3:</b> Confirmación para la radicación de la 
					<xsl:value-of select="//obtenerFormato/Formato/nombre" /> - <xsl:value-of select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>

					<texto key="MENSAJE APROBACION LIBERACION" ocultar_vacio="true"/>

					<!-- INFO CARGA -->
					
					<bloque-pestanas>
					
						<pestana titulo="Aprobación / Rechazo">
						
							<tabla>
								<encabezado>
									<titulo>Tipo</titulo>
									<titulo>Identificador <texto key="CARGA" /></titulo>
									<titulo>Deudor Solicitante</titulo>
									<titulo>Fecha de Carga de Información</titulo>
									<titulo>Estado</titulo>
									
									<!-- VALOR OPCIONAL -->
									<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
										<titulo>Valor</titulo>
									</xsl:if>
<!-- 									<titulo>N°registros</titulo> -->
								</encabezado>
								<xsl:for-each select="//obtenerCarga/Carga">
									<xsl:variable name="nusuario" select="nombre_usuario" />
									<xsl:variable name="npersona" select="nombre_persona" />
									<fila>
										<valor>
											<xsl:choose>
												<xsl:when test="extension='xls'">
													<bloque estilo="extension_xls" />
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
											<xsl:if test="$nusuario=$npersona">
											<xsl:value-of select="nombre_usuario" />&#160;<xsl:value-of select="apellido_persona" />
												
											</xsl:if>
											<xsl:if test="$nusuario!=$npersona">
												<xsl:value-of select="nombre_usuario" />
											</xsl:if>
										</valor>
										<valor>
											<xsl:value-of select="fecha_subida" />
										</valor>
										<valor>
											<b>
												<estado key="{estado}" cliente="S"/>
											</b>
										</valor>
										
										<!-- VALOR OPCIONAL -->
										<xsl:if test="valor_total_bigdecimal!=''">
											<valor>
												<span id="valor_carga_str"><xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/></span>
												<input type="hidden" id="valor_total_carga_str" class="form-control" value="{//obtenerCarga/Carga/valor_total_bigdecimal}"/>
											</valor>
										</xsl:if>
<!-- 										<valor> -->
<!-- 											<xsl:value-of select="numero_registros_bigdecimal" /> -->
<!-- 										</valor> -->
									
									</fila>
									
									
								</xsl:for-each>
							</tabla>
						
							<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)>0">
							
								<div id="area_advertencia">
					
									<h2 class="bloque_contenido_titulo">Advertencia</h2>
									
									<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)=1">
										<p><b style="color:red"><texto key="LA CARGA TIENE EL MISMO VALOR Y NUMERO DE REGISTROS QUE LA CARGA" /></b></p>
									</xsl:if>
									
									<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)>1">
										<p><b style="color:red"><texto key="LA CARGA TIENE EL MISMO VALOR Y NUMERO DE REGISTROS QUE LAS CARGAS" /></b></p>
									</xsl:if>
							
									<tabla>
										<encabezado>
											<titulo>Tipo</titulo>
											<titulo>Identificador <texto key="CARGA" /></titulo>
											<titulo>Nombre</titulo>
											<titulo>Fecha de <texto key="CARGA" /></titulo>
											<titulo>Estado</titulo>

											<!-- VALOR OPCIONAL -->
											<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
												<titulo>Valor</titulo>
											</xsl:if>
<!-- 											<titulo>N° registros</titulo> -->
											
										</encabezado>
										<xsl:for-each select="//obtenerCargasSimilaresCliente/listado/Carga">
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
													<xsl:value-of select="fecha_subida" />
												</valor>
												<valor>
													<b><estado key="{estado}" cliente="S"/></b>
												</valor>
												
												<!-- VALOR OPCIONAL -->
												<xsl:if test="valor_total_bigdecimal!=''">
													<valor>
														<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
													</valor>
												</xsl:if>
<!-- 												<valor> -->
<!-- 													<xsl:value-of select="numero_registros_bigdecimal" /> -->
<!-- 												</valor> -->
												
											</fila>
											
											
										</xsl:for-each>
									</tabla>
									
									<area_botones>
										<boton estilo="continuar" accion="osm_setVisible('area_advertencia', false); osm_setVisible('area_liberacion', true); ">Continuar</boton>
									</area_botones>
									
								</div>
									
							</xsl:if>
						
						<div id="area_liberacion" >
							<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)>0">
								<xsl:attribute name="style">display:none</xsl:attribute>
							</xsl:if>
							
						<!-- LISTA DE NEGOCIOS -->
						<xsl:variable name="hay_listaDC" select="count(//obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo) > 0"/>
						
						<xsl:if test="//obtenerVecesLiberadoHistorialCarga/NumeroLiberaciones != '0'">
							<p><b style="color:red"><texto key="ÉSTE USUARIO YA LIBERÓ PARCIALMENTE LA SOLICITUD." /></b></p>
						</xsl:if>
							
						<xsl:if test="count(obtenerFormato/Formato/id_formato) = 0">
							<p><b style="color:red"><texto key="No se encuentra configurado el formato con el cual se va a realizar la liberación." /></b></p>
						</xsl:if>
							
						<xsl:if test="count(obtenerFormato/Formato/id_formato) > 0 and //obtenerVecesLiberadoHistorialCarga/NumeroLiberaciones = '0'">
							
							<xsl:if test="$seleccion_negocio or $hay_listaDC">
							
								<xsl:if test="$seleccion_negocio or (//OSM_PAGE/OSM-SESSION/credencial_usuario/id_usuario = '' and //OSM-ACCION/obtenerFormato/Formato/para_director = 'S') or (//OSM_PAGE/OSM-SESSION/credencial_usuario/id_usuario!='' and //OSM-ACCION/obtenerFormato/Formato/para_liberador = 'S')">
									<bloque-contenido>
										<titulo>
											Información de liberación
										</titulo>
										<contenido>
										<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
											<xsl:if test="count(//negocios_liberador/Negocio)=0 and count(//OSM-SESSION/CONFIGURACION/COD_NEGOCIO)=0">
												<alerta>No existen Negocios disponibles. <xsl:value-of select="count(//OSM-SESSION/CONFIGURACION/COD_NEGOCIO)"/></alerta>
											</xsl:if>
											
											<xsl:if test="$seleccion_negocio">
												<registro>
													<item>Negocio</item>
													<valor>
														
														<xsl:choose>
															<xsl:when test="count(//obtenerCarga/Carga/id_negocio)>0 and //obtenerCarga/Carga/id_negocio != '0'">
																<p><xsl:value-of select="//negocios_liberador/Negocio[id_negocio = //obtenerCarga/Carga/id_negocio]/nombre"/></p>
																<input type="hidden" id="select_negocios_libera" class="form-control" value="{//obtenerCarga/Carga/id_negocio}"/>
															</xsl:when>
															
															<xsl:otherwise>
																
																<select autocomplete="off" id="select_negocios_libera" class="form-control">
																	<xsl:for-each select="//negocios_liberador/Negocio">
																		<xsl:sort select="cod_negocio"/>	
																		<option value="{id_negocio}">
																			(<xsl:value-of select="cod_negocio"/>) <xsl:value-of select="nombre"/>
																		</option>
																	</xsl:for-each>
																</select>
																
															</xsl:otherwise>
														</xsl:choose>
														
													</valor>
												</registro>
											</xsl:if>
											
											<xsl:if test="$hay_listaDC and ( count(//obtenerCarga/Carga/id_negocio)=0 or //obtenerCarga/Carga/id_negocio='0' )">
												<xsl:call-template name="CAMPOS_ADICIONALES"/>
											</xsl:if>
											
											<xsl:for-each select="//obtenerVariablesLiberacionPorCarga/listado/VariableLiberacion">
												<registro>
													<item><xsl:value-of select="nombreVariableLiberacion"/></item>
													<valor><xsl:value-of select="valor"/></valor>
												</registro>
											</xsl:for-each>
											
											<div id="area_mensaje_liberacion"></div>
											
											</div>
										</contenido>
									</bloque-contenido>
								</xsl:if>					
							</xsl:if>
					
							<!-- APROBAR / DESAPROBAR -->
		
							<xsl:if test="//obtenerFormato/Formato/prog_liberacion='S'">
								<bloque-contenido>
									<titulo>Fecha Liberación</titulo>
									<contenido>
										<parrafo>Puede programar una fecha en la cual será enviada la liberación.</parrafo>
										
										<registro>
												<item>Fecha de Liberación</item>
												<valor>
													
													<div>
														<input type="radio" name="radio_selector_fecha_liberacion" checked="checked" onclick="osm_setVisible('bloque_selector_fecha_liberacion', false); osm_setValor('caja_selector_fecha_liberacion',''); osm_setValor('fecha_liberacion','')"/> Enviar ahora
													</div>
													
													<div> 
														<input type="radio" name="radio_selector_fecha_liberacion" onclick="osm_setVisible('bloque_selector_fecha_liberacion', true);  osm_setValor('caja_selector_fecha_liberacion','{//STRING_DATE_FORMATTED}'); osm_setValor('fecha_liberacion','{//STRING_DATE_FORMATTED}')"/> Seleccionar Fecha
													</div>
			
													<div id="bloque_selector_fecha_liberacion" style="display:none">
														<input id="caja_selector_fecha_liberacion" class="form-control date-pick" type="text" readonly="readonly" onchange="osm_setValor('fecha_liberacion',this.value)"/>
													</div>
													
												</valor>
										</registro>
										
						

									</contenido>
								</bloque-contenido>
							</xsl:if>
						
							<xsl:if test="//validarTotalesLiberacion/ValorCargaValido = 'false'">
								<p><b style="color:red"><texto key="El valor de la solicitud supera el valor máximo asignado." /></b></p>
							</xsl:if>
							
							<xsl:call-template name="aprobacion-botones" >
								<xsl:with-param name="id_horario_liberacion">
									<xsl:if test="count(//obtenerHorarioLiberacion/Horario/id_horario)>0">
										<xsl:value-of select="//obtenerHorarioLiberacion/Horario/id_horario"/>
									</xsl:if>
								</xsl:with-param>
								<xsl:with-param name="id_horario_formato">
									<xsl:if test="//FORMATO_ENTRADA/obtenerFormato/Formato/horario_estricto='S'">
										<xsl:value-of select="//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario"/>
									</xsl:if>
								</xsl:with-param>
								<xsl:with-param name="puede_guardar">
									<xsl:value-of select="//validarTotalesLiberacion/ValorCargaValido"/>
								</xsl:with-param>
							</xsl:call-template>
						</xsl:if>
							
							</div>
						
						</pestana>
					
						
						<xsl:if test="count(obtenerFormato/Formato/id_formato) > 0">
							
							<pestana titulo="Detalle de la Información" visible="{count(//ver_detalle)>0}">
								<xsl:call-template name="DETALLE_CARGA_AJAX" >
									<xsl:with-param name="validacionesActivas">false</xsl:with-param>
								</xsl:call-template>
							</pestana>
							
						</xsl:if>
						
						

					
					<xsl:if test="//esHorarioActivo/Boolean='true' and //horario_atencion/totalFranjasHorario/TotalFranjas>0">
					
						<pestana titulo="Horario de Atencion">
						
								<parrafo>
									A continuación se encuentra el listado de franjas en las cuales las liberaciones son atendidas.
								</parrafo>
								<escapar>
									<div id="criterios_aplicados_horario" class="item bk_horario2">
										<div class="contenedor_ventana" id="contenedor_ventana_edicion_horario"
											style="left:200px; top:-220px; display:none">
											&#160; </div>

										<table class="tabla_horario_vista" style="cursor: pointer;">
											<tr>
												<th>L</th>
												<th>M</th>
												<th>C</th>
												<th>J</th>
												<th>V</th>
												<th>S</th>
												<th>D</th>
											</tr>
											<tr>
												<td id="valor_vista_criterio_hora_L{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
								 				<td id="valor_vista_criterio_hora_M{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_C{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_J{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_V{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_S{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_D{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}">&#160;</td>
											</tr>
										</table>

										<input type="hidden" id="valor_vista_criterio_hora" />
										
									</div>
								</escapar>
								<variable id="Formato.id_formato" valor="{//obtenerFormato/Formato/id_formato}" />
								<variable id="id_horario_atencion" valor="{//FORMATO_ENTRADA/obtenerFormato/Formato/id_horario}" />
						</pestana>
					</xsl:if>

					
					<xsl:if test="count(//obtenerHorarioLiberacion/Horario/id_horario)>0 and //horario_liberacion/totalFranjasHorarioLiberacion/TotalFranjas>=0">
					
						<pestana titulo="Horario de Liberacion">
					
								<parrafo>
									A continuación se encuentra el listado de franjas en las cuales  es posible realizar la liberacion.
								</parrafo>
								<escapar>
									<div id="criterios_aplicados_horario" class="item bk_horario2">
										<div class="contenedor_ventana" id="contenedor_ventana_edicion_horario"
											style="left:200px; top:-220px; display:none">
											&#160; </div>

										<table class="tabla_horario_vista" style="cursor: pointer;">
											<tr>
												<th>L</th>
												<th>M</th>
												<th>C</th>
												<th>J</th>
												<th>V</th>
												<th>S</th>
												<th>D</th>
											</tr>
											<tr>
												<td id="valor_vista_criterio_hora_L{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
								 				<td id="valor_vista_criterio_hora_M{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_C{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_J{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_V{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_S{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
												<td id="valor_vista_criterio_hora_D{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td>
											</tr>
										</table>

										<input type="hidden" id="valor_vista_criterio_hora" />
										<input type="hidden" name="franjas" id="franjas" />
									</div>
								</escapar>
								<variable id="id_horario_liberacion" valor="{//obtenerHorarioLiberacion/Horario/id_horario}" />
						</pestana>
					</xsl:if>
					
					<!-- NOTAS CARGA -->

					<pestana titulo="Notas">
						<xsl:call-template name="notas_carga">
							<xsl:with-param name="permiteAgregar">true</xsl:with-param>
						</xsl:call-template>
					</pestana>

					<!-- ARCHIVOS ADJUNTOS -->
					
					<pestana titulo="Adjuntos">
						<xsl:call-template name="archivos_adjuntos">
							<xsl:with-param name="permiteAdjuntar">false</xsl:with-param>
							<xsl:with-param name="permiteEliminar">true</xsl:with-param>
						</xsl:call-template>
					</pestana>
					
					</bloque-pestanas>
					
					<variable id = "horarioAtencionEstricto" valor="{//obtenerFormato/Formato/horario_estricto}"></variable>
					
					<area_botones>
						<boton estilo="primary volver" destino="liberacion/15.2.do">Volver</boton>
					</area_botones>

				</contenido>
			</principal>

			<xsl:call-template name="aprobacion-carga">
				<xsl:with-param name="siguiente_url">liberacion/15.4.do</xsl:with-param>
				<xsl:with-param name="actualizar_negocio">
					<xsl:choose>
						<xsl:when test="$seleccion_negocio">S</xsl:when>
						<xsl:otherwise>N</xsl:otherwise>
					</xsl:choose>					
				</xsl:with-param>	
				
				<xsl:with-param name="campos_adicionales">
					
					<!-- LISTA DE NEGOCIOS -->
					<xsl:variable name="hay_listaDC" select="count(//obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo) > 0"/>
							
					<xsl:if test="$hay_listaDC and ( count(//obtenerCarga/Carga/id_negocio)=0 or //obtenerCarga/Carga/id_negocio='0' )">
						
						<xsl:if test="(//OSM_PAGE/OSM-SESSION/credencial_usuario/id_usuario = '' and //OSM-ACCION/obtenerFormato/Formato/para_director = 'S') or (//OSM_PAGE/OSM-SESSION/credencial_usuario/id_usuario!='' and //OSM-ACCION/obtenerFormato/Formato/para_liberador = 'S')">
						
							<xsl:for-each select="obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo">
								<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_campo" value="{id_campo}" />
								<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_carga" value="{//obtenerCarga/Carga/id_carga}" />
								<input type="hidden" name="listaValoresDinamicos:{id_campo}.valor" id="listaValoresDinamicos_{id_campo}_valor" value="" />
							</xsl:for-each>
							
						</xsl:if>
					</xsl:if>
					
				</xsl:with-param>
				
				<xsl:with-param name="nombreFaseProceso">LIBERACION</xsl:with-param>
				<xsl:with-param name="usar_firma_digital">
					<xsl:choose>
						<xsl:when test="//OSM-INIT-SESSION/Info/Usuario/uso_firma_liberador='S'">S</xsl:when>
						<xsl:otherwise>N</xsl:otherwise>					
					</xsl:choose>
				</xsl:with-param>			
			</xsl:call-template>
			
			<xsl:call-template name="HORARIO_VENTANA" />
		</pagina>

	</xsl:template>

</xsl:stylesheet>
