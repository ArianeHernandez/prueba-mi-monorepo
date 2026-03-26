<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/horario.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:variable name="Usuario" select="//Usuario"/>
	
	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="FtpUsuario" select="//obtenerFtpUsuarioPorUsuario/FtpUsuario"/>

		<pagina titulo="Administración de Procesos">

			<javascript>crear_proceso/10.2.js</javascript>

			<principal>
				<titulo>Administración de Procesos (<xsl:value-of select="//tipoProceso/nombre"/>) </titulo>
				<contenido>
				<div class="box-container">
				
					<bloque-pestanas>

						<pestana titulo="Información Básica">

							<variable id="revisorMultiplesInstancias" valor="{//permiteRevisorMultiplesInstancias}" />
							
							<formulario id="form_edicion" destino="crear_proceso/10.2.do">

								<variable id="Proceso.id_proceso" valor="{proceso/id_proceso}" />
								<registro>
									<item>Nombre del Proceso</item>
									<valor>
										<cajatexto id="Proceso.nombre" valor="{proceso/nombre}" />
									</valor>
								</registro>
								
								<xsl:if test="//tipoProceso/usa_formato = 'S'">
									<registro>
										<item><texto key="GRUPO FORMATO" /></item>
										<valor>
											<cajatextoselector id="Proceso.id_grupoformato" valor="{proceso/id_grupoformato}">
												<opcion valor="" texto="-- Seleccione --"/>
												<xsl:for-each select="//obtenerTodosLosGruposformato/Listado/GrupoFormato">
													<opcion valor="{id_grupoformato}" texto="{nombre}" />
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
									
									<registro>
										<item>Formato de Salida</item>
										<valor>
											<cajatextoselector id="Proceso.id_formato_salida"
												valor="{proceso/id_formato_salida}">
												<xsl:for-each select="formatos/Formato[tipoformato='S']">
													<xsl:sort select="nombre" />
													<opcion valor="{id_formato}">
														<xsl:value-of select="nombre"></xsl:value-of>
													</opcion>
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
								</xsl:if>
								<registro>
									<item>Activo usuario</item>
									<valor>
										<cajachequeo id="Proceso.estado" valor="A"
											seleccionado="{boolean(proceso/estado='A')}" />
									</valor>
								</registro>
								
								<xsl:if test="normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true'">
									<registro>
										<item>Peso</item>
										<valor>
											<cajatextoselector id="Proceso.peso"
												valor="{proceso/peso}" accion="validarNuevoPeso({proceso/peso},{diferenciaPesoLiberadoresYProceso})">
												    <opcion valor="">-- Seleccione --</opcion>
													<opcion valor="1">1</opcion>
													<opcion valor="2">2</opcion>
													<opcion valor="3">3</opcion>
													<opcion valor="4">4</opcion>
													<opcion valor="5">5</opcion>
													<opcion valor="6">6</opcion>
													<opcion valor="7">7</opcion>
													<opcion valor="8">8</opcion>
													<opcion valor="9">9</opcion>
													<opcion valor="10">10</opcion>			
												
											</cajatextoselector>
										</valor>
									</registro>
								</xsl:if>
							
							</formulario>
							
							<xsl:if test="//tipoProceso/usa_formato = 'S'">
								<bloque-contenido>
									<titulo icono="edicion">Formatos de Entrada</titulo>
									<contenido>
										<div id="div_formatosE">
											<xsl:for-each select="//obtenerFormatosProceso/listado/Formato">
												<div class="cuadro_fila" id="fila_{id_formato}" name="fila_formato">
													<div class="cuadro_fila_item w20">
														<div class="eliminar eliminarFormatosEntrada" onclick="eliminarFormato('{id_formato}')"></div>
													</div>
													<div class="cuadro_fila_item w250">
														<p>
															<xsl:value-of select="nombre" />
														</p>
													</div>
												</div>
											</xsl:for-each>
										</div>
										<div class="cuadro_info_footer">
											<cajatextoselector accion="agregarFormato(this.id)"
												id="select_formatoE">
												<opcion valor="">-- seleccione --</opcion>
												<xsl:for-each select="formatos/Formato[tipoformato='E']">
													<xsl:sort select="nombre" />
													<opcion valor="{id_formato}">
														<xsl:value-of select="nombre"></xsl:value-of>
													</opcion>
												</xsl:for-each>
											</cajatextoselector>
										</div>
									</contenido>
								</bloque-contenido>
								
								
								<xsl:if test="count($Usuario/uso_ftp_usuario)>0 and $Usuario/uso_ftp_usuario='S'">
									<bloque-contenido>
										<titulo icono="edicion">Carga Automática</titulo>
										<contenido>
											
											<dialogo id="ayuda_ftp_usuario" titulo="Ayuda Carga Automática" textoboton="Mostrar Ayuda">
												<p>
													Tenga en cuenta lo siguiente:<br />
													<b>*</b> La carga automática sólo puede activarse para un proceso<br />
													<b>*</b> El proceso no puede tener asociado más de un formato de entrada<br />
													<b>*</b> Si no selecciona un horario de cargue los archivos se cargarán por defecto de 8 a.m. a 6 p.m.<br />
													<b>*</b> No olvide guardar la configuración antes de salir.<br />
												</p>
											</dialogo>
											
											<xsl:choose>
												<xsl:when test="$FtpUsuario/id_ftp_usuario>0">
													<formulario id="form_FtpUsuario" destino="ordenes_pago/99.2.do">
													
														<variable id="FtpUsuario.id_ftp_usuario" valor="{$FtpUsuario/id_ftp_usuario}" />
														<variable id="id_respuesta" valor="" />
														<variable id="franjas" valor="" />									
														
														<div id="area_error_formatos" class="alert alert-danger">
															<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
  															<span class="sr-only">Error:</span>
															<xsl:attribute name="style">
																<xsl:if test="(count(//obtenerFormatosProceso/listado/Formato) &lt; 2) or (count($FtpUsuario/id_proceso)>0)">display:none</xsl:if>
															</xsl:attribute>
															<p>La carga automática no puede activarse debido a que el número de formatos de entrada asociados al proceso es mayor a 1.</p>
														</div>
														
														<div id="area_error_proceso" class="alert alert-danger">
															<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
  															<span class="sr-only">Error:</span>
															<xsl:attribute name="style">
																<xsl:if test="count($FtpUsuario/id_proceso)=0 or $FtpUsuario/id_proceso=//proceso/id_proceso">display:none</xsl:if>
															</xsl:attribute>
															<p>La carga automática no puede activarse debido a que se encuentra activa para otro proceso.</p>
														</div>
														
														<registro>
															<item>
																Habilitar Carga Automática
															</item>
															<valor>
																<cajachequeo2 id="ftp_usuario_activo" valor="S" valor2="N" accion="activarCargaAutomatica()">
																	<xsl:choose>
																		<xsl:when test="count($FtpUsuario/id_proceso)>0 and $FtpUsuario/id_proceso=//proceso/id_proceso">
																			<xsl:attribute name="seleccionado">true</xsl:attribute>
																		</xsl:when>
																		<xsl:when test="count($FtpUsuario/id_proceso)>0">
																			<xsl:attribute name="desactivado">true</xsl:attribute>
																		</xsl:when>
																	</xsl:choose>
																</cajachequeo2>
															</valor>
														</registro>
														
														<div id="area_configuracion_carga_automatica" style="display:none">
															
															<h3>Configuración del servidor</h3>
															
															<registro>
																<item>
																	Host:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/host"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Dominio:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/dominio"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Usuario:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/nombre_usuario"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Contraseña:
																</item>
																<valor>
																	*****
																</valor>
															</registro>
															
															<registro>
																<item>
																	Carpeta de Originales:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/carpeta_transito"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Carpeta de Procesados:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/carpeta_procesados"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Carpeta de Fallidos:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/carpeta_fallidos"/>
																</valor>
															</registro>
															
																<h3>Configuración Back Up</h3>
															
															<registro>
																<item>
																	Carpeta de Originales:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/carpeta_bk_transito"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Carpeta de Procesados:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/carpeta_bk_procesados"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Carpeta de Fallidos:
																</item>
																<valor>
																	<xsl:value-of select="$FtpUsuario/carpeta_bk_fallidos"/>
																</valor>
															</registro>
															
															<registro>
																<item>
																	Frecuencia de Lectura (mins):
																</item>
																<valor>
																	<cajatexto id="FtpUsuario.frecuencia_lectura" valor="{$FtpUsuario/frecuencia_lectura}" />
																</valor>
															</registro>
															
															<xsl:call-template name="FTP_USUARIO_CORREOS" />
															
															<registro>
																<item>
																	Horario Cargue:
																</item>
																<valor>
																	<xsl:call-template name="HORARIO"></xsl:call-template>
																	<xsl:call-template name="HORARIO_VENTANA" />
																	
																	<input type="hidden" id="id_horario" name="FtpUsuario.id_horario" value="{$FtpUsuario/id_horario}" />
																</valor>
															</registro>
															
															<area_botones>
																<boton estilo="primary" validacion="validarConfiguracionAutomatica()" accion="obtenerIdRespuesta()">Guardar Configuración de Carga Automática</boton>
															</area_botones>
															
														</div>
													
													</formulario>
												</xsl:when>
												<xsl:otherwise>
													<alerta>No existe ninguna configuración de ftp para el cliente</alerta>
												</xsl:otherwise>
											</xsl:choose>
										</contenido>
									</bloque-contenido>
								</xsl:if>
							</xsl:if>
						</pestana>
						
						<pestana titulo="Edición Proceso (Usuarios)">
							<xsl:call-template name="EDICION_PROCESO" />
						</pestana>

					</bloque-pestanas>

					<area_botones>
						<boton estilo="primary volver" destino="crear_proceso/10.1.do">Volver</boton>
					</area_botones>


					<div style="display:none" id="TEMPLATE_FILA_FORMATO">
						<div class="cuadro_fila" id="fila_[ 1 ]" name="fila_formato">
							<div class="cuadro_fila_item w20">
								<div class="eliminar eliminarFormatosEntrada" id="btnEliminar_[ 1 ]" ></div>
							</div>
							<div class="cuadro_fila_item w250">
								<p>
									[ 2 ]
								</p>
							</div>
						</div>
					</div>
					
				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

	<xsl:template name="EDICION_REVISION">

		<xsl:variable name="id_revision" select="id_revision" />

		<xsl:for-each
			select="//revisiones/Revision[id_revision_siguiente=$id_revision]">
			<xsl:call-template name="EDICION_REVISION" />
		</xsl:for-each>

		<xsl:call-template name="REVISION" />

	</xsl:template>

	<xsl:template name="EDICION_PROCESO">

		<stylesheet>10.css</stylesheet>


	<xsl:call-template name="PREPARADORES" />

	<xsl:for-each select="//revisiones/Revision[count(id_revision_siguiente)=0]">
		<xsl:call-template name="EDICION_REVISION" />
	</xsl:for-each>

	<xsl:call-template name="LIBERADORES" />

	<!-- PLANTILLAS -->

	<div style="display:none">

		<div id="PLANTILLA_PREPARADOR">
			<div class="cuadro_fila" id="filap_[ID]">

				<div class="cuadro_fila_item w20">
					<div class="eliminar" onclick="desasociarPreparador([ID])">&#160;</div>
				</div>

				<div class="cuadro_fila_item w220">
					<p>[NOMBRE]&#160;</p>
				</div>

				<div class="cuadro_fila_item w90">
					<b style="text-transform: lowercase;">[LOGIN]&#160;</b>
				</div>
			</div>
		</div>

		<div id="PLANTILLA_LIBERADOR">
			<div class="cuadro_fila" id="filal_[ID]">
				<div class="cuadro_fila_item w20">
					<div class="eliminar" onclick="desasociarLiberador([ID])">&#160;</div>
				</div>
				<div class="cuadro_fila_item w220">
					<p>[NOMBRE]&#160;</p>
				</div>

				<div class="cuadro_fila_item w90">
					<b style="text-transform: lowercase;">[LOGIN]&#160;</b>
				</div>
			</div>
		</div>

		<div id="PLANTILLA_REVISOR">
			<div class="cuadro_fila" id="filar_[ID_REV]_[ID]">
				<div class="cuadro_fila_item w20">
					<div class="eliminar" onclick="desasociarRevisor([ID_REV],[ID])">&#160;</div>
				</div>
				<div class="cuadro_fila_item w220">
					<p>[NOMBRE]&#160;</p>
				</div>

				<div class="cuadro_fila_item w90">
					<b style="text-transform: lowercase;">[LOGIN]&#160;</b>
				</div>
			</div>
		</div>

		<div id="PLANTILLA_REVISION">

			<div class="cuadro_proceso box_revision" id="box_revision_[ID_REV]"
				style="display:none">

				<div class="cuadro_imagen">
					&#160;
					<div class="eliminar" onclick="eliminarRevision([ID_REV])">&#160;</div>
				</div>
				<div class="cuadro_boton">
					<input type="button" value="+"
						onclick="agregarRevision([ID_REV], 'box_revision_[ID_REV]')" />
				</div>

				<div class="cuadro_info">
					<div class="cuadro_info_titulo">
						<h1>Revisión</h1>
						<input type="text" value="[NOMBRE]"
							onchange="actualizarNombreRevision([ID_REV], this.value)" />
					</div>

					<div class="cuadro_info_cuerpo" id="lista_revisores_[ID_REV]">
						&#160; </div>

					<div class="cuadro_info_footer">
						<select
							onchange="asociarRevisor([ID_REV],this.value, this.options[this.selectedIndex].text); this.value=''; ">
							<option value=''>-- seleccione --</option>
							<xsl:for-each select="//revisores_usuario/Revisor">
								<option value="{id_revisor}">
									<xsl:value-of select="concat(nombre, ' - ', login)" />
								</option>
							</xsl:for-each>
						</select>
					</div>

				</div>

			</div>

		</div>

	</div>


	</xsl:template>

	<xsl:template name="PREPARADORES">

		<div class="cuadro_proceso" id="box_preparadores">

			<div class="cuadro_imagen">&#160;</div>
			<div class="cuadro_boton">
				<input type="button" value="+"
					onclick="agregarRevision(null, 'box_preparadores')" />
			</div>

			<div class="cuadro_info">
				<div class="cuadro_info_titulo">
					<h1>Preparadores</h1>
				</div>

				<div class="cuadro_info_cuerpo" id="lista_preparadores">
					&#160;
					<xsl:for-each select="preparadores/Preparador">
						<div class="cuadro_fila" id="filap_{id_preparador}">
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="desasociarPreparador({id_preparador})">&#160;</div>
							</div>
							<div class="cuadro_fila_item w220">
								<p>
									<xsl:value-of select="nombre" />
									&#160;
								</p>
							</div>

							<div class="cuadro_fila_item w90">
								<b style="text-transform: lowercase;">
									<xsl:value-of select="login" />
									&#160;
								</b>
							</div>
						</div>
					</xsl:for-each>
				</div>

				<div class="cuadro_info_footer">
					<select
						onchange="asociarPreparador(this.value, this.options[this.selectedIndex].text); this.value=''; ">
						<option value=''>-- seleccione --</option>
						<xsl:for-each select="preparadores_usuario/Preparador">
							<option value="{id_preparador}">
								<xsl:value-of select="concat(nombre, ' - ', login)" />
							</option>
						</xsl:for-each>
					</select>
				</div>

			</div>

		</div>


	</xsl:template>

	<xsl:template name="LIBERADORES">

		<div class="cuadro_proceso" id="box_liberadores">

			<div class="cuadro_imagen">&#160;</div>

			<div class="cuadro_info">
				<div class="cuadro_info_titulo">
					<h1>Liberadores</h1>
				</div>

				<div class="cuadro_info_cuerpo" id="lista_liberadores">
					&#160;
					<xsl:for-each select="liberadores/Liberador">
						<div class="cuadro_fila" id="filal_{id_liberador}">
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="comprobarDesasociarLiberadorPorProceso({id_liberador},{listadoLiberadorTipoProceso/LiberadorTipoProceso[id_tipo_proceso = ../../../../proceso/id_tipo_proceso]/peso},{//diferenciaPesoLiberadoresYProceso},{//proceso/peso},{//proceso/id_proceso})">&#160;</div>
							</div>
							<div class="cuadro_fila_item w220">
								<p>
									<xsl:value-of select="nombre" />
									&#160;
								</p>
							</div>

							<div class="cuadro_fila_item w90">
								<b style="text-transform: lowercase;">
									<xsl:value-of select="login" />
									&#160;
									<xsl:if test="normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true'">
										<xsl:value-of select="peso" />
										&#160;
									</xsl:if>
								</b>
							</div>
							
						</div>
					</xsl:for-each>
				</div>

				<div class="cuadro_info_footer">
					<select
						onchange="asociarLiberador(this.value, this.options[this.selectedIndex].text); this.value=''; ">
						<option value=''>-- seleccione --</option>
						<xsl:for-each select="liberadores_usuario/Liberador">
							<option value="{id_liberador}">
								<xsl:value-of select="concat(nombre, ' - ', login, ' - ', peso)" />
							</option>
						</xsl:for-each>
					</select>
				</div>

			</div>

		</div>


	</xsl:template>

	<xsl:template name="REVISION">

		<xsl:variable name="id_revision" select="id_revision" />

		<div class="cuadro_proceso box_revision" id="box_revision_{$id_revision}">

			<div class="cuadro_imagen">
				&#160;
				<div class="eliminar" onclick="eliminarRevision({$id_revision})">&#160;</div>
			</div>
			<div class="cuadro_boton">
				<input type="button" value="+"
					onclick="agregarRevision({$id_revision}, 'box_revision_{$id_revision}')" />
			</div>

			<div class="cuadro_info">
				<div class="cuadro_info_titulo">
					<h1>Revisión</h1>
					<input type="text" value="{nombre}"
						onchange="actualizarNombreRevision({$id_revision}, this.value)" />
				</div>

				<div class="cuadro_info_cuerpo" id="lista_revisores_{$id_revision}">
					&#160;

					<xsl:for-each
						select="//listaRevisionRevisores/RevisionRevisores[id_revision=$id_revision]/revisores/Revisor">
						<div class="cuadro_fila" id="filar_{$id_revision}_{id_revisor}">
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="desasociarRevisor({$id_revision},{id_revisor})">&#160;</div>
							</div>
							<div class="cuadro_fila_item w220">
								<p>
									<xsl:value-of select="nombre" />
									&#160;
								</p>
							</div>

							<div class="cuadro_fila_item w90">
								<b style="text-transform: lowercase;">
									<xsl:value-of select="login" />
									&#160;
								</b>
							</div>
						</div>
					</xsl:for-each>
				</div>

				<div class="cuadro_info_footer">
					<select
						onchange="asociarRevisor({$id_revision},this.value, this.options[this.selectedIndex].text); this.value=''; ">
						<option value=''>-- seleccione --</option>
						<xsl:for-each select="//revisores_usuario/Revisor">
							<option value="{id_revisor}">
								<xsl:value-of select="concat(nombre, ' - ', login)" />
							</option>
						</xsl:for-each>
					</select>
				</div>

			</div>

		</div>


	</xsl:template>
	
	<xsl:template name="FTP_USUARIO_CORREOS">
		
		<xsl:variable name="FtpUsuario" select="//obtenerFtpUsuarioPorUsuario/FtpUsuario"/>
		<xsl:variable name="correos" select="$FtpUsuario/correos_usuario" />
		
		<div id="div_correos">
			<xsl:choose>
				<xsl:when test="count($correos/FtpUsuarioCorreo)>0">
					<variable id="n_correos" valor="{count($correos/FtpUsuarioCorreo)}" />
					<xsl:for-each select="$correos/FtpUsuarioCorreo">
						<xsl:sort select="correo"/>
							<registro id="fila_correo_{position()}">
								<item>
									<xsl:choose>
										<xsl:when test="position() = 1">
											Correos notificaciones:
										</xsl:when>
										<xsl:otherwise>
											<div class="eliminar" style="float: right;" onclick="eliminarCorreo({position()});"></div>
										</xsl:otherwise>
									</xsl:choose>
								</item>
								<valor>
									<div style="overflow: hidden">
										<variable id="FtpUsuario.correos_usuario:{position()}.id_ftp_usuario" valor="{$FtpUsuario/id_ftp_usuario}" />
										<variable id="FtpUsuario.correos_usuario:{position()}.id_ftp_usuario_correo" valor="{id_ftp_usuario_correo}" />
										<variable id="FtpUsuario.correos_usuario:{position()}.tipo" valor="U" />
										<div class="flft w90p"><cajatexto class="flft" id="FtpUsuario.correos_usuario:{position()}.correo" valor="{correo}" /></div>
										<xsl:if test="position() = 1">
											<escapar><div title="Añadir Correo" class="flft anadir" onclick="agregarCorreo();"></div></escapar>
										</xsl:if>
									</div>
								</valor>
							</registro>
					</xsl:for-each>
				</xsl:when>
				<xsl:otherwise>
					<variable id="n_correos" valor="1" />
					<registro id="fila_correo_1">
						<item>Correos notificaciones:</item>
						<valor>
							<div style="overflow: hidden">
								<variable id="FtpUsuario.correos_usuario:1.tipo" valor="U" />
								<variable id="FtpUsuario.correos_usuario:1.id_ftp_usuario" valor="{$FtpUsuario/id_ftp_usuario}" />
								<div class="flft w90p"><cajatexto id="FtpUsuario.correos_usuario:1.correo" valor="" /></div>
								<escapar><div title="Añadir Correo" class="flft anadir" onclick="agregarCorreo();"></div></escapar>
							</div>
						</valor>
					</registro>
				</xsl:otherwise>
			</xsl:choose>
		</div>
		
		<!-- PLANTILLA PARA MOSTRAR CORREOS -->
		<div id="correo_plantilla" style="display:none">
			<registro id="fila_correo_[ 1 ]">
				<item>
					<div class="eliminar" style="float: right;" onclick="eliminarCorreo([ 1 ]);"></div>
				</item>
				<valor>
					<div style="overflow: hidden">
						<variable id="FtpUsuario.correos_usuario:[ 1 ].id_ftp_usuario" valor="{$FtpUsuario/id_ftp_usuario}"/>
						<variable id="FtpUsuario.correos_usuario:[ 1 ].tipo" valor="U" />
						<variable id="FtpUsuario.correos_usuario:[ 1 ].id_ftp_usuario_correo" valor=""/>
						<div class="flft w90p"><cajatexto class="flft" id="FtpUsuario.correos_usuario:[ 1 ].correo" valor="" /></div>
					</div>
				</valor>
			</registro>
		</div>
		
	</xsl:template>

</xsl:stylesheet>
