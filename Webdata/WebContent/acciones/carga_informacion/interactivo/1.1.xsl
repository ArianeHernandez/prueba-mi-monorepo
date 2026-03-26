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
	
	<xsl:variable name="campos" select="//obtenerFormatoCampoCompleto/Formato/Campo"/>
	
	<xsl:variable name="acciones"  select="//OSM-INIT-SESSION/acciones/obtenerMenusPorAplicacion/Listado"/>
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Creacion Registro">
			<javascript>webdata/1.1.js</javascript>
			
			<stylesheet>horario.css</stylesheet>
			<stylesheet>1.1.css</stylesheet>
			<stylesheet>reportePersonalizado/reporte.css</stylesheet>
			<stylesheet>jspdf/generar_pdf_html.css</stylesheet>
						
			<javascript>templates/mostrar_horario.js</javascript>
			<javascript>templates/generar_pdf_html.js</javascript>
			<javascript>jspdf/html2canvas.min.js</javascript>
			<javascript>jspdf/jspdf.min.js</javascript>
			<xsl:if test="string-length(//obtenerFormato/Formato/id_componente)>0">
				<javascript>componente/<xsl:value-of select="//obtenerFormato/Formato/id_componente"/>.js</javascript>
			</xsl:if>
			<xsl:if test="boolean(not(//obtenerFormato/Formato/usa_adjuntos_formulario='S'))">
				<javascript>archivos_adjuntos/archivos_adjuntos.js</javascript>
				<javascript>archivos_adjuntos/s3_archivos_adjuntos.js</javascript>
			</xsl:if>
			
			<principal>
			
				<xsl:variable name="tipocampo_file" select="//obtenerTipoCampos/listado/TipoCampo[tipo_dato='File']"/>
			
				<xsl:variable name="hay_campoFile" select="count(//obtenerFormatoCampoCompleto/Formato/Campo[id_tipocampo=$tipocampo_file/id_tipocampo]) &gt; 0"/>
			
				<titulo><texto key="CARGA" /> Individual - <xsl:value-of select="obtenerFormato/Formato/nombre"/></titulo>
				<contenido>
					<div class="box-container" style="display:none" id="area_pagina">
					<xsl:choose>
					<xsl:when test="count(obtenerFormato/Formato/valortramite)=0">
					
				
					<variable id="Variable.id_formato" valor="{//obtenerFormato/Formato/id_formato}" />
					<variable id="Variable.validacion_rest" valor="{boolean(count(//obtenerFormato/Formato/endpoint_validacion) > 0)}" />
					
					<bloque-pestanas>
					
						<pestana titulo="{obtenerFormatoCampoCompleto/Formato/FormatoCampo/titulo}">
					
							<input type="hidden" id="pestana_titulo" value="{obtenerFormatoCampoCompleto/Formato/FormatoCampo/titulo}"></input>
							
							<input type="hidden" id="formato_encabezado_html" value="{//obtenerFormato/Formato/encabezado_html}"></input>
							<input type="hidden" id="formato_pie_html" value="{//obtenerFormato/Formato/pie_html}"></input>
							<input type="hidden" id="formato_alerta_html" value="{//obtenerFormato/Formato/alerta_html}"></input>
							<input id="msg_modal_pdf" type="hidden" 
								value="&lt;div id=&quot;mensajeAlerta&quot; class=&quot;alert alert-info&quot;&gt;El sistema está cargando la información 
																											y creando una copia de su formulario en PDF.
																											Por favor espere.&lt;/div&gt;" />

							<ventana id="vn_msg_envio">
								<titulo icono="inicio">
									<xsl:value-of select="//obtenerFormatoCampoCompleto/Formato/FormatoCampo/titulo"/> 
								</titulo>
								<contenido>
									<div id="msg_modal_pdf" class="alert alert-info" style="display:none">
										El sistema está creando una copia de su formulario en PDF.
											Por favor espere.
									</div>
									<div id="msg_modal_carga_archivos" style="display:none">	
										<div  class="alert alert-info">
											El sistema está cargando la información.
												Por favor espere.
										</div>
									</div>
									<div id="seccion_carga">
				                        <div id="barra_carga" class="barra_carga">
				                            <div id="barra_contenido" class="barra_carga_contenido"
												style="height:24px;width:0%"></div>
				                        </div>
				                    </div>
			                        <p id="textBar">
										Cargando
										
			                            <span id="archivos_adjuntados">0</span>
										de
										
			                            <span id="total_adjuntos"></span>
										archivos
									
			                        </p>
			                        <div id="msg_modal_error_adjuntos" style="display:none">
			                        	<div class="tb-text">
			                                <div class="Normal-1">
			                                    <i aria-hidden="true" class="fa fa-info-circle"></i>
												Señor usuario, los siguientes archivos no pudieron ser cargados ya que tomaron más tiempo del esperado: <br/>
												<div id="div_error_adjuntos">
													<ul id="lista_error_adjuntos"></ul>
												</div> 
												Para poder realizar el envío de su solicitud reintente el cargue de los documentos listados que no 
												pudieron ser cargados utilizando el botón "Reintentar Cargue Documentos", en caso de querer reemplazar los
												documentos adjuntos seleccione el botón "Descartar Solicitud", al ejecutar está acción la aplicación 
												cargará el formulario en blanco para su diligenciamiento. <br/>
												Recuerde que los campos del formulario permiten archivos en formato PDF, Excel etc con un peso máximo de 100MB.								
			                                </div>
			                            </div>
			                            <div class="divider"></div>
			                            <div style="text-align: center;">
			                                <button class="l-button s-buttonenable-primary" onclick="reintentarArchivosFallidos()"
												id="boton_enviar">Reintentar Cargue Documentos</button>
											<button class="l-button s-buttonenable-primary" onclick="cancelar()"
												id="boton_enviar">Descartar Solicitud</button>
			                            </div>
			                        </div>
								</contenido>
							</ventana>

							<div id="area_encabezado_html"></div>

							<p><xsl:value-of select="obtenerFormatoCampoCompleto/Formato/descripcion"></xsl:value-of></p>
							
							<bloque estilo="base_ayuda" id="bloque_form_cargainteractiva">
							
							<bloque estilo="grupo panel-col col-sm-12 col-md-8 col-md-offset-2 form-horizontal">
								<xsl:variable name="hay_listaDC" select="count(//obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo) &gt; 0"/>
								<div id="formulario_completo">
									<formulario destino="carga_informacion/interactivo/1.1.1.do" id="form_cargainteractiva">
										<xsl:if test="$hay_campoFile">
							                <xsl:attribute name="dato">multipart/form-data</xsl:attribute>
							            </xsl:if>
										
										<variable id="FormatoDato.id_formato_campo" valor="{obtenerFormatoCampoCompleto/Formato/FormatoCampo/id_formato_campo}"/>
										<variable id="FormatoDato.id_estructura" valor="{obtenerFormato/Formato/id_estructura}"/>
										
										<xsl:for-each select="obtenerFormatoCampoCompleto/Formato/FormatoCampo/formato_campo_list/FormatoCampo">
												<xsl:call-template name="FormatoCampoFormulario">
													<xsl:with-param name="FormatoDatoName" select="concat('FormatoDato.formatoDatoList:',campo_orden)"/>
													<xsl:with-param name="EsPlantilla">false</xsl:with-param>
												</xsl:call-template>
										</xsl:for-each>
									
										<variable id="accion_guardar_formatodato" valor="S"/>
										
										<xsl:if test="$hay_listaDC">
											<xsl:call-template name="CAMPOS_ADICIONALES"/>
											
											<xsl:for-each select="obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo">
											<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_campo" value="{id_campo}" />
											<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_carga" value="{//ID_CARGA}" />
											<input type="hidden" name="listaValoresDinamicos:{id_campo}.valor" id="listaValoresDinamicos_{id_campo}_valor" value="" />
											</xsl:for-each>
										</xsl:if>
										
									</formulario>
									
									
									<xsl:if test="boolean(//obtenerFormato/Formato/usa_adjuntos_formulario='S')">
										<!-- ARCHIVOS ADJUNTOS -->
										<xsl:call-template name="archivos_adjuntos">
											<xsl:with-param name="permiteAdjuntar">true</xsl:with-param>
											<xsl:with-param name="permiteEliminar">true</xsl:with-param>
											<xsl:with-param name="infoAdjuntos"><xsl:value-of select="//obtenerFormato/Formato/info_adjuntos_reporte"/></xsl:with-param>
											<xsl:with-param name="extensionAdjuntos"><xsl:value-of select="//obtenerFormato/Formato/extensiones_adjuntos_reporte"/></xsl:with-param>
											<xsl:with-param name="limiteAdjuntos"><xsl:value-of select="//obtenerFormato/Formato/limite_adjuntos_reporte"/></xsl:with-param>
											<xsl:with-param name="tituloAdjuntos"><xsl:value-of select="//obtenerFormato/Formato/titulo_seccion_adjuntos_reporte"/></xsl:with-param>
											<xsl:with-param name="ayudaAdjuntos"><xsl:value-of select="//obtenerFormato/Formato/ayuda_adjuntos_reporte"/></xsl:with-param>
											<xsl:with-param name="solicitud_inicial"><xsl:value-of select="//obtenerFormato/Formato/solicitud_inicial"/></xsl:with-param>
										</xsl:call-template>
										
									</xsl:if>
								</div>
								<xsl:if test="$hay_listaDC">
									<div style="display:none">
										<select autocomplete="off" id="select_negocios_libera" class="form-control">
												<option value="{//OSM-SESSION/CONFIGURACION/ID_NEGOCIO}">
														 <xsl:value-of select="//OSM-SESSION/CONFIGURACION/ID_NEGOCIO"/>
												</option>
										</select>
									</div>
								</xsl:if>
								
							</bloque>
							
							</bloque>
<!-- 							<xsl:if test="(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') or (//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')"> -->
<!-- 								<xsl:call-template name="firmarFormulario"> -->
<!-- 									<xsl:with-param name="id_componente_firma">firma1</xsl:with-param> -->
<!-- 									<xsl:with-param name="formularioParaFirmar">form_cargainteractiva</xsl:with-param> -->
<!-- 									<xsl:with-param name="id_carga"><xsl:value-of select="//ID_CARGA"/></xsl:with-param> -->
<!-- 									<xsl:with-param name="nombreFaseProceso">PREPARACION INDIVIDUAL</xsl:with-param> -->
<!-- 									<xsl:with-param name="funcionJavaScript_validacion">validacion_carga_interactiva()</xsl:with-param> -->
<!-- 									<xsl:with-param name="nombre_boton">Firmar y Enviar</xsl:with-param> -->
<!-- 								</xsl:call-template> -->
<!-- 							</xsl:if> -->
							
							<formulario id="form_cancelar" >
								<xsl:attribute name="destino">
									<xsl:choose>
										<xsl:when test="$acciones/Menu[direccion='carga_informacion/13.do']/autorizado='true'">carga_informacion/13.do</xsl:when>
										<xsl:otherwise>carga_informacion/1a.do</xsl:otherwise>
									</xsl:choose>
								</xsl:attribute>
								<variable id="id_carga" valor="{//ID_CARGA}"/>
								<input type="hidden" name="id_formato" value="{//obtenerFormato/Formato/id_formato}"/>
							</formulario>
							
							
<!-- 							<xsl:choose> -->
<!-- 								<xsl:when test="//obtenerFormato/Formato/horario_estricto='S'"> -->
<!-- 									<variable id="id_horario_formato" valor="{//obtenerFormato/Formato/id_horario}"/> -->
<!-- 								</xsl:when> -->
<!-- 								<xsl:otherwise> -->
<!-- 									<variable id="id_horario_formato" valor=""/> -->
<!-- 								</xsl:otherwise> -->
<!-- 							</xsl:choose> -->
							
							<!-- HORARIOS DE LIBERACION-->
<!-- 							<xsl:if test="boolean(//esHorarioActivo/Boolean) and //horario_atencion/totalFranjasHorario/TotalFranjas>0"> -->
<!-- 								<bloque-contenido> -->
<!-- 									<titulo> -->
<!-- 										Horario de atencion! -->
<!-- 									</titulo> -->
<!-- 									<contenido> -->
<!-- 										<parrafo> -->
<!-- 											A continuación se encuentra el listado de franjas en las cuales las liberaciones son atendidas. -->
<!-- 										</parrafo> -->
<!-- 										<escapar> -->
<!-- 											<div id="criterios_aplicados_horario" class="item bk_horario2"> -->
<!-- 												<div class="contenedor_ventana" id="contenedor_ventana_edicion_horario" -->
<!-- 													style="left:200px; top:-220px; display:none"> -->
<!-- 													&#160; </div> -->
		
<!-- 												<table class="tabla_horario_vista" style="cursor: pointer;"> -->
<!-- 													<tr> -->
<!-- 														<th>L</th> -->
<!-- 														<th>M</th> -->
<!-- 														<th>C</th> -->
<!-- 														<th>J</th> -->
<!-- 														<th>V</th> -->
<!-- 														<th>S</th> -->
<!-- 														<th>D</th> -->
<!-- 													</tr> -->
<!-- 													<tr> -->
<!-- 														<td id="valor_vista_criterio_hora_L{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 										 				<td id="valor_vista_criterio_hora_M{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_C{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_J{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_V{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_S{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_D{//obtenerFormato/Formato/id_horario}">&#160;</td> -->
<!-- 													</tr> -->
<!-- 												</table> -->
		
<!-- 												<input type="hidden" id="valor_vista_criterio_hora" /> -->
												
<!-- 											</div> -->
<!-- 										</escapar> -->
<!-- 										<variable id="Formato.id_formato" valor="{//obtenerFormato/Formato/id_formato}" /> -->
<!-- 										<variable id="Formato.endpoint_validacion" valor="{//obtenerFormato/Formato/endpoint_validacion}" /> -->
<!-- 										<variable id="id_horario_atencion" valor="{//obtenerFormato/Formato/id_horario}" /> -->
<!-- 									</contenido> -->
<!-- 								</bloque-contenido> -->
<!-- 							</xsl:if> -->
		
							
<!-- 							<xsl:if test="count(//obtenerHorarioLiberacion/Horario/id_horario)>0 and //horario_liberacion/totalFranjasHorarioLiberacion/TotalFranjas>0"> -->
<!-- 								<bloque-contenido> -->
<!-- 									<titulo> -->
<!-- 										Horario para liberación! -->
								
<!-- 									</titulo> -->
<!-- 									<contenido> -->
<!-- 										<parrafo> -->
<!-- 											A continuación se encuentra el listado de franjas en las cuales  es posible realizar la liberacion. -->
<!-- 										</parrafo> -->
<!-- 										<escapar> -->
<!-- 											<div id="criterios_aplicados_horario" class="item bk_horario2"> -->
<!-- 												<div class="contenedor_ventana" id="contenedor_ventana_edicion_horario" -->
<!-- 													style="left:200px; top:-220px; display:none"> -->
<!-- 													&#160; </div> -->
		
<!-- 												<table class="tabla_horario_vista" style="cursor: pointer;"> -->
<!-- 													<tr> -->
<!-- 														<th>L</th> -->
<!-- 														<th>M</th> -->
<!-- 														<th>C</th> -->
<!-- 														<th>J</th> -->
<!-- 														<th>V</th> -->
<!-- 														<th>S</th> -->
<!-- 														<th>D</th> -->
<!-- 													</tr> -->
<!-- 													<tr> -->
<!-- 														<td id="valor_vista_criterio_hora_L{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 										 				<td id="valor_vista_criterio_hora_M{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_C{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_J{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_V{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_S{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 														<td id="valor_vista_criterio_hora_D{//obtenerHorarioLiberacion/Horario/id_horario}">&#160;</td> -->
<!-- 													</tr> -->
<!-- 												</table> -->
		
<!-- 												<input type="hidden" id="valor_vista_criterio_hora" /> -->
<!-- 												<input type="hidden" name="franjas" id="franjas" /> -->
<!-- 											</div> -->
<!-- 										</escapar> -->
<!-- 										<variable id="id_horario_liberacion" valor="{//obtenerHorarioLiberacion/Horario/id_horario}"/> -->
						
<!-- 									</contenido> -->
<!-- 								</bloque-contenido> -->
<!-- 							</xsl:if> -->
							
							<div id="area_pie_html"></div>
							
							<div id="area_mensaje_liberacion"></div>
							
						</pestana>
					
						<xsl:if test="boolean(//obtenerFormato/Formato/usa_archivos_adjuntos='S')">
							<pestana titulo="Archivos Adjuntos">
						
								<!-- ARCHIVOS ADJUNTOS -->
								<xsl:call-template name="archivos_adjuntos">
									<xsl:with-param name="permiteAdjuntar">true</xsl:with-param>
									<xsl:with-param name="permiteEliminar">true</xsl:with-param>
								</xsl:call-template>
							
							</pestana>
						</xsl:if>

					</bloque-pestanas>
					
<!-- 					<xsl:if test="$hay_campoFile"> -->
<!-- 						<div id="seccion_carga" style="display:none;"> -->
<!-- 							<div id="barra_carga" class="barra_carga"> -->
<!-- 								<div id="barra_contenido" class="barra_carga_contenido" -->
<!-- 									style="height:24px;width:0%"> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<p id="textBar"> -->
<!-- 								Cargando -->
<!-- 								<span id="archivos_adjuntados">0</span> -->
<!-- 								de -->
<!-- 								<span id="total_adjuntos"> -->
<!-- 								</span> -->
<!-- 								archivos -->
<!-- 							</p> -->
<!-- 						</div> -->
<!-- 						<div id="div_exito" style="display: none;"> -->
<!-- 							<div class="alert alert-success"> -->
<!-- 								<i class="fa fa-check-circle" aria-hidden="true"></i> -->
<!-- 								<span class="sr-only">Info:</span> -->
<!-- 								<p id="info_exito"></p> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</xsl:if> -->
					
					<!-- VARIABLES PARA LA VALIDACION DE HORARIOS DE LIBERACION DE LA CARGA-->
				
<!-- 					<xsl:choose> -->
<!-- 						<xsl:when test="//esclientenatural='true' and count(//obtenerHorarioLiberacion/Horario/id_horario)>0 " > -->
<!-- 							<variable id="id_horario_liberacion" valor="{//obtenerHorarioLiberacion/Horario/id_horario}"/> -->
<!-- 						</xsl:when> -->
<!-- 						<xsl:otherwise> -->
<!-- 							<variable id="id_horario_liberacion" valor=""/> -->
<!-- 						</xsl:otherwise> -->
<!-- 					</xsl:choose> -->
					
<!-- 					<xsl:choose> -->
<!-- 						<xsl:when test="//obtenerFormato/Formato/horario_estricto='S'"> -->
<!-- 							<variable id="id_horario_formato" valor="{//obtenerFormato/Formato/id_horario}"/> -->
<!-- 						</xsl:when> -->
<!-- 						<xsl:otherwise> -->
<!-- 							<variable id="id_horario_formato" valor=""/> -->
<!-- 						</xsl:otherwise> -->
<!-- 					</xsl:choose> -->
					
					<!-- BOTONES -->
					<area_botones>
						<boton estilo="danger" accion="cancelar()">Cancelar</boton>
							<boton estilo="primary guardar" validacion="validacion_carga_interactiva()" accion="enviarYOAprobar()">Guardar</boton>
					</area_botones>
					
					</xsl:when>
					
					<xsl:otherwise>
						
						
						<bloque-contenido>
							<titulo>
								Este trámite requiere pago
							</titulo>
							<contenido>
								<parrafo>
									Realice sus pagos desde su cuenta de ahorros o corriente usando el sistema PSE.
								</parrafo>
								
								
								<bloque estilo="grupo">
						
									<registro>
										<item>Valor de la Transación</item>
										<valor>
											<b><xsl:value-of select="obtenerFormato/Formato/valortramite"/></b>
										</valor>
									</registro>
								</bloque>
								
								
							</contenido>
						</bloque-contenido>
						
						
						<bloque-contenido>
							<titulo>
								Datos del Solicitante
							</titulo>
							<contenido>
								<parrafo estilo="primary">
									La información aqui registrada permitirá atenderle en caso de cualquier inconveniente.
								</parrafo>
								
								<bloque estilo="grupo">
						
									<registro>
										<item>Tipo de persona</item>
										<valor>
											<cajatextoselector id="tipo_persona">
												<opcion valor="N" texto="Natural"/>
												<opcion valor="N" texto="Jurídica"/>
											</cajatextoselector>
										</valor>
									</registro>
									
									<registro>
										<item>Tipo de Documento</item>
										<valor>
											<cajatextoselector id="tipo_documento">
												<opcion valor="C" texto="Cedula de Ciudadania"/>
												<opcion valor="N" texto="NIT"/>
											</cajatextoselector>
										</valor>
									</registro>
									
									
									<registro>
										<item>Número de Identificación</item>
										<valor>
											<cajatexto valor="" id="numero_identificacion"/>
										</valor>
									</registro>
									
									<registro>
										<item>Nombre o Razón Social</item>
										<valor>
											<cajatexto valor="" id="nombre"/>
										</valor>
									</registro>
									
									<registro>
										<item>Email</item>
										<valor>
											<cajatexto valor="" id="email"/>
										</valor>
									</registro>
									
									<registro>
										<item>Confirmar Email</item>
										<valor>
											<cajatexto valor="" id="email_confirm"/>
										</valor>
									</registro>
									
								</bloque>
								
								<parrafo estilo="resaltado">
									Si los datos de la transacción y la informacion del solicitante son correctos, haga click en el botón Continuar para ingresar a su entidad Financiera, de lo contrario regrese y haga los cambios necesarios.
								</parrafo>
								
								
								
								<area_botones>
									<boton estilo="danger" id="btn_cancelar" destino="carga_informacion/13.1.do">Cancelar</boton>
									<img src="../images/pse.png"/>
									<boton id="btn_busquedaVacia" accion="alert('Lo sentimos, No se puede conectar con PSE.')">Continuar</boton>
								</area_botones>
								
							</contenido>
						</bloque-contenido>
						
						
					</xsl:otherwise>
					
					</xsl:choose>
					</div>
				</contenido>
			</principal>
			
			<ventana id="vn_busqueda" icono="confirmacion">
				<titulo>Busqueda</titulo>
				<contenido>
					<div class="col-sm-10 col-sm-offset-1 col-lg-8 row col-lg-offset-2">
					<variable id="id_estructura_busqueda" valor=""/>
					<variable id="formatoDatoName" valor=""/>
					<variable id="id_persona" valor="{//OSM-INIT-SESSION/Info/Persona/id_persona}"/>
					
					<bloque estilo="grupo">
						
						<registro>
							<item>Criterio de Busqueda</item>
							<valor>
								<cajatextoselector id="vnb_criterioBusqueda">
									<opcion valor="" texto="-- Seleccione --"/>
								</cajatextoselector>
							</valor>
						</registro>
						
						<registro>
							<item>Criterio</item>
							<valor>
								<cajatexto id="vnb_valorBusqueda"/>
							</valor>
						</registro>
						
						<bloque>
							<boton estilo="buscar" accion="realizarBusqueda()">Buscar</boton>
						</bloque>
						
						<parrafo id="busqueda_mensaje" estilo="resaltado" texto=""/>
						
						<bloque estilo="area_resultado" id="area_resultado">
							
						</bloque>
				
						<bloque visible="false" id="plantilla_resultado">
							<bloque estilo="resultado" id="IDENTIFICADOR" accion="seleccionarElementoBusqueda('IDSELECCION','TITULO')">
								<titulo texto="TITULO"/>
								<subtitulo texto="MENSAJE"/>
							</bloque>
						</bloque>
						
					</bloque>
		
					<area_botones>
						<boton estilo="danger" id="btn_cancelar" accion="cerrarVentanaBusqueda()">Cerrar</boton>
						<boton id="btn_busquedaVacia" accion="seleccionBusquedaVacia()">Seleccion Vacia</boton>
					</area_botones>
					<input type="hidden" name="tipo_persona_cliente" id="tipo_persona_cliente" value=""/>
					</div>
				</contenido>
			</ventana>
			
		</pagina>
		
		
		<ventana id="vn_aprobarcarga" icono="confirmacion">
				<titulo>Confirmación de Envio</titulo>
				<contenido>

					<div class="row-box form-horizontal">
						
							<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i> ¿Está seguro que desea enviar la transacción? </div>
					
						<xsl:if test="string-length(//obtenerFormato/Formato/mensaje_liberacion) > 1">
							<parrafo estilo="resaltado">
								<b><xsl:value-of select="//obtenerFormato/Formato/mensaje_liberacion"/></b>
							</parrafo>
						</xsl:if>
						
				 		<boton estilo="guardar" id="btn_aprobarcarga"
							accion="enviarDefinitivamente()">
							Aprobar
						</boton>
						
						<area_botones>
							<boton estilo="cancelar" id="btn_cancelar"
								accion="cerrarVentanaAprobarCarga()">
								Cerrar Ventana
							</boton>
						</area_botones>
						
					</div>
				</contenido>
			</ventana>
	</xsl:template>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template name="FormatoCampoFormulario">
	
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		
		<div class="bloque_campo_{id_campo} ">
		
			<variable id="{$FormatoDatoName}.id_formato_campo" valor="{id_formato_campo}"/>
			<variable id="ID {titulo}" valor="{$FormatoDatoName}.valor" />
			
			<xsl:if test="obligatorio_valor_campo = 'S' and string-length(campo_obligatoriedad) > 0 and string-length(lista_dinamica_obligatoriedad) > 0">
				<input type="hidden" id="{$FormatoDatoName}.obligatorio_campo" class="obligatorio_campo" value="{id_campo}"/>
				<input type="hidden" id="lista_dinamica_obligatoriedad_{id_campo}" value="{lista_dinamica_obligatoriedad}"/>
				<input type="hidden" id="campo_obligatoriedad_{id_campo}" value="{campo_obligatoriedad}"/>
			</xsl:if>
			
			<xsl:if test="ocultable_valor_campo = 'S' and string-length(ocultable_campo) > 0 and string-length(lista_dinamica_ocultable) > 0">
				<input type="hidden" id="{$FormatoDatoName}.ocultable_campo" class="ocultable_campo" value="{id_campo}"/>
				<input type="hidden" id="lista_dinamica_ocultable_{id_campo}" value="{lista_dinamica_ocultable}"/>
				<input type="hidden" id="ocultable_campo_{id_campo}" value="{ocultable_campo}"/>
			</xsl:if>
			
			<xsl:if test="validacion_campo_lista = 'S' and string-length(lista_dinamica_validacion) > 0">
				<input type="hidden" id="{$FormatoDatoName}.validacion_campo" class="validacion_campo" value="{id_campo}"/>
				<input type="hidden" id="lista_dinamica_validacion_{id_campo}" value="{lista_dinamica_validacion}"/>
				<input type="hidden" id="campo_validacion_{id_campo}" value="{campo_validacion}"/>
				<input type="hidden" id="mensaje_validacion_{id_campo}" value="{mensaje_validacion}"/>
			</xsl:if>
			
			<xsl:if test="string-length(lista_dinamica_valor_inicial) > 0">
				<input type="hidden" id="{$FormatoDatoName}.valor_inicial" class="lista_valor_inicial" value="{lista_dinamica_valor_inicial}"/>
			</xsl:if>
			
			<input type="hidden" id="formatodatoname_{id_campo}" value="{$FormatoDatoName}"/>
			
			<xsl:if test=" string-length(ayuda)>1 and ( tipo_ingreso=0 or tipo_ingreso=1 or tipo_ingreso=2 or tipo_ingreso=3 or tipo_ingreso=7) ">
				<div style="position:relative">
					<xsl:variable name="counter" select="count(formato_campo_list/FormatoCampo) > 0"/>
					<input type="hidden" id="tipo_dato_{id_campo}" value="{$counter}"/>
					<xsl:choose>
						<xsl:when test="count(formato_campo_list/FormatoCampo) > 0">
							<a class="ico_ayuda ico_ayuda_seccion" id="{$FormatoDatoName}.ico_ayuda" onmouseover="ver_ayuda_campo('{$FormatoDatoName}')" onmouseout="ocultar_ayuda_campo()"  onfocus="ver_ayuda_campo('{$FormatoDatoName}')"></a>						
						</xsl:when>
						<xsl:otherwise>
							<a class="ico_ayuda" id="{$FormatoDatoName}.ico_ayuda" onmouseover="ver_ayuda_campo('{$FormatoDatoName}')" onmouseout="ocultar_ayuda_campo()"  onfocus="ver_ayuda_campo('{$FormatoDatoName}')"></a>						
						</xsl:otherwise>					
					</xsl:choose>					
					
					<div class="area_ayuda" id="{$FormatoDatoName}.area_ayuda">
						<div class="inner_area_ayuda">
							<h1><xsl:value-of select="titulo"/></h1>
							<pre><xsl:value-of select="ayuda" disable-output-escaping="yes"/></pre>
						</div>
					</div>
				</div>
			</xsl:if>
			
			<xsl:choose>
				
				<xsl:when test="tipo_ingreso=0">
					<xsl:call-template name="TipoIngreso_Padre">
						<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
						<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="tipo_ingreso=1">
					<xsl:call-template name="TipoIngreso_SeleccionarExistente">
						<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
						<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="tipo_ingreso=2">
					<xsl:call-template name="TipoIngreso_IdExistente">
						<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
						<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="tipo_ingreso=3">
					<xsl:call-template name="TipoIngreso_IngresadoPorUsuario">
						<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
						<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="tipo_ingreso=4">
					<xsl:if test="boolean(visualizar_valor_constante='S')">
						<xsl:call-template name="TipoIngreso_ValorConstante">
							<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
							<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
						</xsl:call-template>
					</xsl:if>
				</xsl:when>
				
				<xsl:when test="tipo_ingreso=7">
					<xsl:call-template name="TipoIngreso_SeleccionarListadinamica">
						<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
						<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="tipo_ingreso=12">
					<xsl:if test="boolean(visualizar_valor_sesion='S')">
						<xsl:call-template name="TipoIngreso_ValorSesion">
							<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
							<xsl:with-param name="EsPlantilla" select="$EsPlantilla"/>
						</xsl:call-template>
					</xsl:if>
				</xsl:when>
				
			</xsl:choose>
		</div>

	</xsl:template>
	
	<xsl:template name="FormatoCampoMultiplicidadN">
		
		<xsl:param name="FormatoDatoName"/>
		
		<bloque>
					
			<variable id="CANTIDAD_{id_formato_campo}N_{$FormatoDatoName}F" valor="0"/>
			
			<bloque id="CONTENIDO_{id_formato_campo}N_{$FormatoDatoName}F">
			
			</bloque>
			
			<bloque id="PLANTILLA_{id_formato_campo}N_{$FormatoDatoName}F" visible="false" >
			
				
				<bloque id="PLANTILLA_{id_formato_campo}C_{$FormatoDatoName}F">
					
					<xsl:call-template name="FormatoCampoFormulario">
						<xsl:with-param name="FormatoDatoName" select="concat('PLANTILLA_',id_formato_campo,'V_',$FormatoDatoName,'F')"/>
						<xsl:with-param name="EsPlantilla" >true</xsl:with-param>
					</xsl:call-template>
					
					<input type="hidden" class="bloque_{$FormatoDatoName}"/>
					
					<area_botones estilo="simple">
						<boton estilo="danger eliminar" accion="eliminarElemento('DDPLANTILLA_{id_formato_campo}V_{$FormatoDatoName}FDD')">Eliminar</boton>
					</area_botones>
					
				</bloque>
				
			</bloque>
			
			<area_botones>
				<boton estilo="adicionar" accion="adicionarElemento('{id_formato_campo}','{$FormatoDatoName}')">Adicionar <xsl:value-of select="titulo"/></boton>
			</area_botones>
		</bloque>
		
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_Padre">
				
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		<xsl:if test="count(formato_campo_list/FormatoCampo)>0">
			<bloque estilo="grupo">
				<subtitulo texto="{titulo}"/>
				
				<xsl:for-each select="formato_campo_list/FormatoCampo">
					<xsl:call-template name="FormatoCampoFormulario">
						<xsl:with-param name="FormatoDatoName" select="concat($FormatoDatoName,'.formatoDatoList:',campo_orden)"/>
						<xsl:with-param name="EsPlantilla">false</xsl:with-param>
					</xsl:call-template>
				</xsl:for-each>
				
			</bloque>
		</xsl:if>
		
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_IngresadoPorUsuario">
				
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		
		<xsl:variable name="id_campo" select="id_campo"/>
		<xsl:variable name="campo" select="$campos[id_campo=$id_campo]"/>
		
		<xsl:variable name="id_lista_valores" select="$campo/id_lista_valores" />
		<xsl:variable name="es_obligatorio">
			<xsl:choose>
				<xsl:when test="obligatorio='S' or $campo/obligatorio='S'">S</xsl:when>
				<xsl:otherwise>N</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:variable name="id_tipocampo" select="$campo/id_tipocampo"/>
		<xsl:variable name="tipoCampo" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]"/>
		
		<xsl:variable name="tipo_dato" select="$tipoCampo/tipo_dato"/>
		<xsl:variable name="tipo_dato_nombre" select="$tipoCampo/nombre"/>
		<xsl:variable name="patron_validacion" select="$tipoCampo/patron_validacion"/>
		<xsl:variable name="longitud_dato" select="$tipoCampo/longitud"/>
		<xsl:variable name="multiplicidad" select="$campo/multiplicidad"/>
		<xsl:variable name="id_estructura" select="$campo/id_estructura"/>
		<xsl:variable name="llaveprimaria" select="$campo/llaveprimaria"/>
		<xsl:variable name="id_formato_campo_padre" select="id_formato_campo_padre"/>
		<xsl:variable name="formato_campo" select="//obtenerFormatoCampoCompleto//FormatoCampo[id_formato_campo = $id_formato_campo_padre ]"/>
		<xsl:variable name="precargaPadre" select="$formato_campo/precarga"/>
		<xsl:variable name="patron_validacion_campo" select="$campo/patronvalidacion"/>
		<xsl:variable name="usa_titulo_html">
		
			<xsl:choose>
				<xsl:when test="usa_titulo_html='S' or $campo/usa_titulo_html='S'">S</xsl:when>
				<xsl:otherwise>N</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:if test="$llaveprimaria = 'S'">
			<variable id="{$FormatoDatoName}.llaveprimaria" valor="{$llaveprimaria}"/>
		</xsl:if>
		<variable id="{$FormatoDatoName}.id_estructura" valor="{$id_estructura}"/>
		<variable id="{$FormatoDatoName}.id_campo" valor="{$id_campo}"/>
		<variable id="{$FormatoDatoName}.patron_validacion" valor="{$patron_validacion}"/>
		<variable id="{$FormatoDatoName}.es_obligatorio" valor="{$es_obligatorio}"/>
		<variable id="{$FormatoDatoName}.tipo_dato_nombre" valor="{$tipo_dato_nombre}"/>
		<variable id="{$FormatoDatoName}.titulo" valor="{titulo}"/>
		<xsl:if test="string-length($patron_validacion_campo) > 0">
			<variable id="{$FormatoDatoName}.campo_validacion" valor="{$patron_validacion_campo}"/>
		</xsl:if>
		<variable id="{$FormatoDatoName}.multiplicidad" valor="{$multiplicidad}"/>
		<variable id="{$FormatoDatoName}.id_formato_campo" valor="{id_formato_campo}"/>
		<variable id="{$FormatoDatoName}.validacion_servicio" valor="{boolean(validacion_servicio='S')}" />
		<variable id="{$FormatoDatoName}.campo_orden" valor="{campo_orden}" />

		<xsl:choose>
			<xsl:when test="$multiplicidad='1...1' or $EsPlantilla='true'">
				<xsl:choose>
					
					<xsl:when test="string-length($id_lista_valores)>0">
						<registro>
						
							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							
							<valor>
								<cajatexto id="{$FormatoDatoName}.valor" alias="{titulo}" placeholder="--Seleccione--" 
									bloqueado="{boolean(bloqueado='S')}">
									<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
										<xsl:attribute name="accion">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
									</xsl:if>
								</cajatexto>
								<variable id="{$FormatoDatoName}.id_lista_valores" valor="{$id_lista_valores}"/>
								<input type="hidden" class="campo_lista_valores" value="{$FormatoDatoName}"/>
							</valor>
						</registro>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='Object'">
						<bloque estilo="grupo">
							<div class="tb-text">
<!-- 							<subtitulo> -->
			                    <div class="Subtitle-2">
									
									<xsl:choose>
										<xsl:when test="$usa_titulo_html='S'">
											<xsl:value-of select="titulo_html" disable-output-escaping="yes" />
										</xsl:when>
										<xsl:otherwise>
	<!-- 									Se elimina (*) de obligatorio para proyecto de SSOC	
											<xsl:if test="$es_obligatorio='S'"> (*) </xsl:if> <xsl:value-of select="titulo"/> -->
											<xsl:value-of select="titulo"/>
										</xsl:otherwise>
									</xsl:choose>
								
	<!-- 							</subtitulo> -->
								</div>
			                   	<div class="Normal-1"><xsl:value-of select="mensaje_campo_padre" disable-output-escaping="yes" /></div>
			                </div>
			                <div class="divider"></div>
							
							<xsl:for-each select="formato_campo_list/FormatoCampo">
								<xsl:call-template name="FormatoCampoFormulario">
									<xsl:with-param name="FormatoDatoName" select="concat($FormatoDatoName,'.formatoDatoList:',campo_orden)"/>
									<xsl:with-param name="EsPlantilla" >false</xsl:with-param>
								</xsl:call-template>
							</xsl:for-each>
							
						</bloque>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='String' and number($longitud_dato)>200">
						<registro>

							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							
							<valor>
								<areatexto valor="" id="{$FormatoDatoName}.valor" alias="{titulo}" />
								<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
									<xsl:attribute name="accion">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
								</xsl:if>
							</valor>
						</registro>
					</xsl:when>	
					
					<xsl:when test="$tipo_dato='String'">
						<registro>
							
							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							
							<valor>
								<cajatexto valor="" id="{$FormatoDatoName}.valor" alias="{titulo}"  
								bloqueado="{boolean(bloqueado='S')}"/>
								<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
									<xsl:attribute name="onchange">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
								</xsl:if>
							</valor>
						</registro>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='Integer'">
						<registro>
							
							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							
							<valor>
								<cajatexto valor="" id="{$FormatoDatoName}.valor" alias="{titulo}" 
								bloqueado="{boolean(bloqueado='S')}">
									<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
										<xsl:attribute name="onchange">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
									</xsl:if>
								</cajatexto>
							</valor>
						</registro>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='Double'">
							<registro>
								
								<xsl:call-template name="Titulo_Registro">
									<xsl:with-param name="titulo" select="titulo"/>
									<xsl:with-param name="titulo_html" select="titulo_html"/>
									<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
									<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
									<xsl:with-param name="id_campo" select="$id_campo"/>
								</xsl:call-template>
								
								<valor>
									<div id="div_sinformato_double_{$FormatoDatoName}">
										<cajatexto valor="" id="{$FormatoDatoName}.valor" onblur="mostrarDoubleConFormato('{$FormatoDatoName}','{$tipo_dato_nombre}')" alias="{titulo}"   
										  bloqueado="{boolean(bloqueado='S')}"/>
										<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
											<xsl:attribute name="onchange">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
										</xsl:if>
									</div>
									<xsl:choose>
										<xsl:when test="$tipo_dato_nombre='Porcentaje'">
											<input class="form-control valorlista" id="div_formato_double_{$FormatoDatoName}" onfocus = "mostrarDoubleSinFormato('{$FormatoDatoName}')" style="display:none" readonly="readonly" placeholder="Ingresar valor"></input>
										</xsl:when>
										<xsl:otherwise>
											<input class="form-control valorlista" id="div_formato_double_{$FormatoDatoName}" onfocus = "mostrarDoubleSinFormato('{$FormatoDatoName}')" style="display:none" readonly="readonly" placeholder="Ingresar valor en Pesos Colombianos"></input>
										</xsl:otherwise>
									</xsl:choose>
								</valor>
							</registro>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='Boolean'">
						<registro>
						
							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							
							<valor>
								<cajachequeo valor="true" id="{$FormatoDatoName}.valor" alias="{titulo}" />
								<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
									<xsl:attribute name="onchange">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
								</xsl:if>
							</valor>
						</registro>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='Date'">
						<registro>
						
							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							
							<valor>
								<cajafecha valor="" id="{$FormatoDatoName}.valor"  alias="{titulo}" placeholder="dd/mm/yyyy"/>
								<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
									<xsl:attribute name="onchange">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
								</xsl:if> 
							</valor>
						</registro>
					</xsl:when>
					
					<xsl:when test="$tipo_dato='File'">
						<div class="row col-md-12 margin-row div-input-file" style="transform: translateX(10px)">
							<div class="col-md-5 Normal-1 label-input-file" style="top: 6px">
							
								<xsl:choose>
									<xsl:when test="$usa_titulo_html='S'">
										<xsl:value-of select="titulo_html" disable-output-escaping="yes" />
									</xsl:when>
									<xsl:otherwise>
<!-- 									Se elimina (*) de obligatorio para el proyecto de SSOC	
										<xsl:if test="$es_obligatorio='S'"> (*) </xsl:if> <xsl:value-of select="titulo"/> -->
										<xsl:value-of select="titulo"/>
									</xsl:otherwise>
								</xsl:choose>
							
							</div>
						    <div class="row form-input col-md-7 col-sm-7 value-input-file">
								<input id="label_{id_campo}" class="input-file-text sc-start"
									type="text" placeholder="Arrastre o seleccione un archivo"
									disabled="" />
								<div
								    id="{$FormatoDatoName}.valor_file"
									class="l-button input-file-gral input-file-button p-buttonenable sc-end"
									style="min-width: 70px">
									Adjuntar
								</div>
								<i id="trash_{id_campo}" aria-hidden="true"
									onclick="removeFile('{id_campo}')" class="fa fa-trash ic-trash ic-hide"></i>
							</div>
							<div class="input-custom" style="width: 60%; margin-left: 40%;">
								<div class="row col-md-12 margin-row">
									<input id="caja_{id_campo}"
										class="input-caja caja_archivo_adjunto"
										type="file"
										style="width: 100%"
										/>	
								</div>
							</div>

							<div style="display: none">
								<input class="form-control" type="hidden" autocomplete="off"
									id="descripcion_{id_campo}" name="descripcion_{id_campo}"
									value="{titulo}" />
							</div>
						</div>
						<variable valor="" id="{$FormatoDatoName}.valor" class="variable_{id_campo}" />
						<input type="hidden" id="id_tipo_archivo_{id_campo}" value="{$campo/id_tipo_archivo}" />
					</xsl:when>	
					
					<xsl:otherwise>
						<registro>
							<xsl:call-template name="Titulo_Registro">
								<xsl:with-param name="titulo" select="titulo"/>
								<xsl:with-param name="titulo_html" select="titulo_html"/>
								<xsl:with-param name="usa_titulo_html" select="$usa_titulo_html"/>
								<xsl:with-param name="es_obligatorio" select="$es_obligatorio"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
							</xsl:call-template>
							<valor>
								<parrafo estilo="resaltado" texto="Tipo de dato no soportado '{$tipo_dato}'"/>
							</valor>
						</registro>
					</xsl:otherwise>
					
				</xsl:choose>
				
			</xsl:when>
			
			<xsl:when test="$multiplicidad='1...N'">
				<xsl:call-template name="FormatoCampoMultiplicidadN">
					<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
				</xsl:call-template>
				
			</xsl:when>
			
			<xsl:otherwise><parrafo estilo="resaltado" texto="multiplicidad no soportada '{$multiplicidad}'"/></xsl:otherwise>
			
		</xsl:choose>
				
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_ValorSesion">
				
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		<xsl:variable name="valorSesionCampo" select="concat('valor_sesion_',id_formato_campo)" />
		<xsl:variable name="valorSesion" select="//obtenerValoresSesionFormatoCampo/*[@name=$valorSesionCampo]"/>
		
		<registro>
			<item><xsl:value-of select="titulo"/></item>
			<valor>
				<cajatexto valor="{$valorSesion}" id="{$FormatoDatoName}.valor" desactivado="true">
					<xsl:value-of select="$valorSesion"/>
				</cajatexto>
			</valor>
		</registro>
				
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_ValorConstante">
				
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		<registro>
			<item><xsl:value-of select="titulo"/></item>
			<valor>
				<cajatexto valor="{valor_constante}" id="{$FormatoDatoName}.valor" desactivado="true">
					<xsl:value-of select="valor_constante"/>
				</cajatexto>
			</valor>
		</registro>
				
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_IdExistente">
				
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		<xsl:variable name="id_campo" select="id_campo"/>
		<xsl:variable name="id_tipocampo" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_tipocampo"/>
		<xsl:variable name="id_lista_valores" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_lista_valores" />
		<xsl:variable name="tipo_dato" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]/tipo_dato"/>
		<xsl:variable name="multiplicidad" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/multiplicidad"/>
		<xsl:variable name="id_estructura" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructura"/>
		<xsl:variable name="llaveprimaria" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/llaveprimaria"/>
		<xsl:variable name="id_formato_campo_padre" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/llaveprimaria"/>
		<xsl:variable name="id_formato_campo_padre" select="id_formato_campo_padre"/>
		<xsl:variable name="precargaPadre" select="//obtenerFormatoCampoCompleto//FormatoCampo[id_formato_campo = $id_formato_campo_padre ]/precarga"/>
		
		<xsl:if test="$llaveprimaria = 'S'">
			<variable id="{$FormatoDatoName}.llaveprimaria" valor="{$llaveprimaria}"/>
		</xsl:if>
		<variable id="{$FormatoDatoName}.id_estructura" valor="{$id_estructura}"/>
		<variable id="{$FormatoDatoName}.id_campo" valor="{$id_campo}"/>
		
		<xsl:choose>
			<xsl:when test="$multiplicidad='1...1' or $EsPlantilla='true'">
					
				<registro>
					<item><xsl:value-of select="titulo"/></item>
					<valor>
						<cajatexto valor="" id="{$FormatoDatoName}.valor">
							<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
									<xsl:attribute name="onchange">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
							</xsl:if>
						
						</cajatexto>
					</valor>
				</registro>
				
			</xsl:when>
			
			<xsl:when test="$multiplicidad='1...N'">
				<xsl:call-template name="FormatoCampoMultiplicidadN">
					<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
				</xsl:call-template>
			</xsl:when>
			
			<xsl:otherwise><parrafo estilo="resaltado" texto="multiplicidad no soportada '{$multiplicidad}'"/></xsl:otherwise>
			
		</xsl:choose>
				
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_SeleccionarExistente">
		
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		<xsl:variable name="id_campo" select="id_campo"/>
		<xsl:variable name="id_tipocampo" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_tipocampo"/>
		<xsl:variable name="id_lista_valores" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_lista_valores" />
		<xsl:variable name="tipo_dato" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]/tipo_dato"/>
		<xsl:variable name="multiplicidad" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/multiplicidad"/>
		<xsl:variable name="id_estructura" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructura"/>
		<xsl:variable name="id_estructurarelacionada" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada"/>
		<xsl:variable name="llaveprimaria" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/llaveprimaria"/>
		<xsl:variable name="id_formato_campo_padre" select="id_formato_campo_padre"/>
		<xsl:variable name="precargaPadre" select="//obtenerFormatoCampoCompleto//FormatoCampo[id_formato_campo = $id_formato_campo_padre ]/precarga"/>
		
		<xsl:if test="$llaveprimaria = 'S'">
			<variable id="{$FormatoDatoName}.llaveprimaria" valor="{$llaveprimaria}"/>
		</xsl:if>
		<variable id="{$FormatoDatoName}.id_estructura" valor="{$id_estructura}"/>
		<variable id="{$FormatoDatoName}.id_campo" valor="{$id_campo}"/>
		
		<xsl:choose>
			<xsl:when test="$multiplicidad='1...1' or $EsPlantilla='true'">
				<xsl:choose>
					
					<xsl:when test="string-length($id_lista_valores)>0">
					</xsl:when>
					<xsl:otherwise>
					
						<bloque>
							
							<registro>
								<item><xsl:value-of select="titulo"/></item>
								<valor>
									<variable id="{$FormatoDatoName}.valor" valor=""/>
									<constante id="{$FormatoDatoName}.valorVisible" texto="--"/>
								</valor>
							</registro>
					
							<area_botones estilo="simple">
								<boton estilo="buscar" accion="verVentanaBusqueda('{$id_estructurarelacionada}','{$FormatoDatoName}')">Buscar</boton>
							</area_botones>
						</bloque>
						
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			
			<xsl:when test="$multiplicidad='1...N'">
				<xsl:call-template name="FormatoCampoMultiplicidadN">
					<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
				</xsl:call-template>
			</xsl:when>
			
		</xsl:choose>
			
	</xsl:template>
	
	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
	<xsl:template name="TipoIngreso_SeleccionarListadinamica">
				
		<xsl:param name="FormatoDatoName"/>
		<xsl:param name="EsPlantilla"/>
		
		<xsl:variable name="id_campo" select="id_campo"/>
		<xsl:variable name="id_tipocampo" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_tipocampo"/>
		<xsl:variable name="id_lista_valores" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_lista_valores" />
		<xsl:variable name="es_obligatorio">
			<xsl:choose>
				<xsl:when test="obligatorio='S' or //obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/obligatorio='S'">S</xsl:when>
				<xsl:otherwise>N</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="llaveprimaria" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/llaveprimaria"/>
		<xsl:variable name="tipo_dato" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]/tipo_dato"/>
		<xsl:variable name="tipo_dato_nombre" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]/nombre"/>
		<xsl:variable name="patron_validacion" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]/patron_validacion"/>
		<xsl:variable name="longitud_dato" select="//obtenerTipoCampos/listado/TipoCampo[id_tipocampo=$id_tipocampo]/longitud"/>
		<xsl:variable name="multiplicidad" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/multiplicidad"/>
		<xsl:variable name="id_estructura" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructura"/>
		<xsl:variable name="id_lista_dinamica" select="id_lista_dinamica"/>
		<xsl:variable name="id_formato_campo_padre" select="id_formato_campo_padre"/>
		<xsl:variable name="precargaPadre" select="//obtenerFormatoCampoCompleto//FormatoCampo[id_formato_campo = $id_formato_campo_padre ]/precarga"/>
		
		<variable id="{$FormatoDatoName}.id_estructura" valor="{$id_estructura}"/>
		<variable id="{$FormatoDatoName}.id_campo" valor="{$id_campo}"/>
		<variable id="{$FormatoDatoName}.patron_validacion" valor="{$patron_validacion}"/>
		<variable id="{$FormatoDatoName}.es_obligatorio" valor="{$es_obligatorio}"/>
		<variable id="{$FormatoDatoName}.tipo_dato_nombre" valor="{$tipo_dato_nombre}"/>
		<xsl:if test="$llaveprimaria = 'S'">
			<variable id="{$FormatoDatoName}.llaveprimaria" valor="{$llaveprimaria}"/>
		</xsl:if>
		<variable id="{$FormatoDatoName}.titulo" valor="{titulo}"/>
		<variable id="{$FormatoDatoName}.validacion_servicio" valor="{boolean(validacion_servicio='S')}" />
	
		<xsl:choose>
			<xsl:when test="$multiplicidad='1...1' or $EsPlantilla='true'">
					
					<registro>
<!-- 					Se elimina (*) de obligatorio para proyecto de SSOC	
						<item  id="label_campo_{id_campo}"><xsl:if test="$es_obligatorio='S'"> (*) </xsl:if> <xsl:value-of select="titulo"/></item> -->
						<item  id="label_campo_{id_campo}">
							<xsl:choose>
								<xsl:when test="usa_titulo_html='S'">
									<xsl:value-of select="titulo_html" disable-output-escaping="yes" ></xsl:value-of>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="titulo"/>
								</xsl:otherwise>
							</xsl:choose>
						</item>
						<valor>
								<cajatexto id="{$FormatoDatoName}.valor" alias="{titulo}" placeholder="--Seleccione--"  
									onfocus="goScroll(this);" reference="{concat(patron_dependiente,'|',$id_lista_dinamica)}" 
									bloqueado="{boolean(bloqueado='S')}">
									<xsl:if test="$precargaPadre='S' and $llaveprimaria='S'">
									<xsl:attribute name="accion">obtenerDatosPrecarga('<xsl:value-of select="$FormatoDatoName"></xsl:value-of>','<xsl:value-of select="$id_formato_campo_padre"></xsl:value-of>', this)</xsl:attribute>
								</xsl:if>
								</cajatexto> 
								<xsl:if test="string-length($id_lista_dinamica) > 0">
									<variable id="{$FormatoDatoName}.id_lista_dinamica" valor="{$id_lista_dinamica}"/>
									<input type="hidden" class="campo_lista_dinamica" value="{$FormatoDatoName}"/>
								</xsl:if>
						</valor>
					</registro>
					
			</xsl:when>
			
			<xsl:when test="$multiplicidad='1...N'">
				<xsl:call-template name="FormatoCampoMultiplicidadN">
					<xsl:with-param name="FormatoDatoName" select="$FormatoDatoName"/>
				</xsl:call-template>
			</xsl:when>
			
			<xsl:otherwise><parrafo estilo="resaltado" texto="multiplicidad no soportada '{$multiplicidad}'"/></xsl:otherwise>
			
		</xsl:choose>
				
	</xsl:template>
	
	<xsl:template name="Titulo_Registro">
		<xsl:param name="titulo"/>
		<xsl:param name="titulo_html"/>
		<xsl:param name="usa_titulo_html"/>
		<xsl:param name="es_obligatorio"/>
		<xsl:param name="id_campo"/>
		
		<item  id="label_campo_{$id_campo}">
			<xsl:choose>
				<xsl:when test="$usa_titulo_html='S'">
					<xsl:value-of select="$titulo_html" disable-output-escaping="yes" ></xsl:value-of>
				</xsl:when>
				<xsl:otherwise>
<!-- 				Se elimina (*) de obligatorio para proyecto de SSOC	
					<xsl:if test="$es_obligatorio='S'"> (*) </xsl:if> <xsl:value-of select="$titulo"/> -->
					<xsl:value-of select="$titulo"/>
				</xsl:otherwise>
			</xsl:choose>
		</item>
		
	</xsl:template>

	<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
	
</xsl:stylesheet>
