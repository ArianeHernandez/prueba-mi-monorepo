<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/EditarPersona.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<xsl:variable name="UsuarioNegocio"
			select="//INFOUSUARIO/obtenerUsuarioPorIdentificacionNegocio/Usuario" />
		<xsl:variable name="Persona"
			select="//INFOPERSONA/obtenerPersonaPorIdentificacion/Persona" />
		<xsl:variable name="Variable"
			select="//VARIABLES/obtenerVariablesPorModeloId/listado/Variable" />
		<xsl:variable name="TipoPersona" select="//DATOS/TIPOPERSONA" />
		<xsl:variable name="TipoDocumento"
			select="//DATOS//TIPODOCUMENTO/obtenerTipoDocumento/TipoDocumento" />
		<xsl:variable name="Identificacion" select="//DATOS/IDENTIFICACION" />
		<xsl:variable name="editar" select="//editar" />
		<xsl:variable name="Usuario" select="//obtenerUsuario/Usuario" />
		<xsl:variable name="FtpUsuario"
			select="//obtenerFtpUsuarioPorUsuario/FtpUsuario" />


		<pagina titulo="Crear Cliente">

			<principal>
				<titulo>Crear Cliente</titulo>
				<contenido>
					<div class="box-container form-horizontal">
					<xsl:choose>
						<xsl:when
							test="count($UsuarioNegocio/id_negocio)>0 and string-length(//editar) = 0">
							<bloque-contenido>
								<titulo icono="edicion">Error</titulo>
								<contenido>
									<div class="alert alert-danger"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>
										El cliente ya existe para este negocio!
									</div>
								</contenido>
							</bloque-contenido>
							
							<area_botones>
								<boton estilo="volver" destino="cliente/6.1.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160; Volver</boton>
							</area_botones>
						</xsl:when>
						<xsl:otherwise>
							<formulario id="form_usuario" class="form-horizontal" destino="cliente/6.3.do"
								dato="multipart/form-data">
								<stylesheet>6.2.css</stylesheet>
								<javascript>cliente/6.2.js</javascript>
								<xsl:call-template name="EDITARPERSONA">
									<xsl:with-param name="Persona" select="$Persona" />
									<xsl:with-param name="TipoPersona" select="$TipoPersona" />
									<xsl:with-param name="TipoDocumento" select="$TipoDocumento" />
									<xsl:with-param name="Identificacion" select="$Identificacion" />
									<xsl:with-param name="editar" select="$editar" />
									<xsl:with-param name="Credencial"
										select="//CREDENCIAL/obtenerCredencialPersonaUsuario/Credencial" />
									<xsl:with-param name="LoginPorIdentificacion"
										select="//LOGIN/esLoginPorIdentificacion/Boolean" />
									<xsl:with-param name="rol">C</xsl:with-param>
								</xsl:call-template>
								<bloque-contenido>
									<titulo icono="edicion">Información del Cliente</titulo>
									<contenido>
										<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
										<registro>
											<item>
												Observación
											</item>
											<valor>
												<cajatexto id="Usuario.observacion" valor="{$Usuario/observacion}" />
											</valor>
										</registro>

										<registro>
											<item>
												Permite uso de firma
											</item>
											<valor>
												<cajachequeo id="Usuario.uso_firma" accion="cambiarUsoFirmaCliente();">
													<xsl:if test="$Usuario/uso_firma='S'">
														<xsl:attribute name="seleccionado">true</xsl:attribute>
														<xsl:attribute name="valor">S</xsl:attribute>
													</xsl:if>
												</cajachequeo>
											</valor>
										</registro>

										<div id="paramtrizacionUsoFirmaProcesoCliente" style="display:none">

											<registro>
												<item>
													Permite uso de firma para el preparador
												</item>
												<valor>
													<cajachequeo id="Usuario.uso_firma_preparador"
														accion="cambiarUsoFirma('Usuario.uso_firma_preparador');">
														<xsl:if test="$Usuario/uso_firma_preparador='S'">
															<xsl:attribute name="seleccionado">true</xsl:attribute>
															<xsl:attribute name="valor">S</xsl:attribute>
														</xsl:if>
													</cajachequeo>
												</valor>
											</registro>

											<registro>
												<item>
													Permite uso de firma para el revisor
												</item>
												<valor>
													<cajachequeo id="Usuario.uso_firma_revisor"
														accion="cambiarUsoFirma('Usuario.uso_firma_revisor');">
														<xsl:if test="$Usuario/uso_firma_revisor='S'">
															<xsl:attribute name="seleccionado">true</xsl:attribute>
															<xsl:attribute name="valor">S</xsl:attribute>
														</xsl:if>
													</cajachequeo>
												</valor>
											</registro>

											<registro>
												<item>
													Permite uso de firma para el liberador
												</item>
												<valor>
													<cajachequeo id="Usuario.uso_firma_liberador"
														accion="cambiarUsoFirma('Usuario.uso_firma_liberador');">
														<xsl:if test="$Usuario/uso_firma_liberador='S'">
															<xsl:attribute name="seleccionado">true</xsl:attribute>
															<xsl:attribute name="valor">S</xsl:attribute>
														</xsl:if>
													</cajachequeo>
												</valor>
											</registro>

										</div>

										<xsl:if test="count($Usuario)>0 and $TipoPersona = 'J'">
											<registro>
												<item>
													Permite cargue automático
												</item>
												<valor>
													<cajachequeo id="Usuario.uso_ftp_usuario"
														accion="cambiarCargueFTP();">
														<xsl:if test="$Usuario/uso_ftp_usuario='S'">
															<xsl:attribute name="seleccionado">true</xsl:attribute>
															<xsl:attribute name="valor">S</xsl:attribute>
														</xsl:if>
													</cajachequeo>
												</valor>
											</registro>
										</xsl:if>

										<xsl:if test="count($Usuario)=0 and $TipoPersona = 'J'">
											<div class="alert alert-info"><i class="fa fa-info-circle" aria-hidden="true"></i>
												Solamente se permite realizar la configuración de cargue automático despues de guardar la información del cliente.
											</div>
										</xsl:if>


										<registro id="boton_ftp" visible="false" unico="true">
											<valor>
												<area_botones>
													<boton estilo="primary" accion="verVentanaEdicionFTP();"><i class="fa fa-edit" aria-hidden="true"></i>&#160; Editar Conexión</boton>
												</area_botones>
											</valor>
										</registro>

										<variable id="Usuario.id_usuario" valor="{$Usuario/id_usuario}" />
										</div>
									</contenido>
								</bloque-contenido>
								<xsl:if test="count($Variable) > 0 ">
									<bloque-contenido>

										<titulo icono="edicion">Variables del Cliente</titulo>
										<contenido>
											<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
											<xsl:for-each select="$Variable">
												<registro>
													<item>
														<xsl:value-of select="nombre_variable" />
													</item>
													<valor>
														<cajatexto id="Variables:{position()}.valor_variable"
															valor="{valor_variable}" />
													</valor>
												</registro>
												<variable id="Variables:{position()}.id_variable"
													valor="{id_variable}" />
											</xsl:for-each>
											</div>
										</contenido>
									</bloque-contenido>
								</xsl:if>

								<!-- NEGOCIOS POR CLIENTE -->
								<bloque-contenido>
									<titulo icono="edicion">Negocios</titulo>
									<contenido>

										<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
											<div class="form-group form-group-sm">
												<label class="control-label col-sm-4">Negocios Asociados</label>
												<div class="col-sm-8">
													<cajatextoselector id="roles" class="form-control" accion="agregarNegocio(this);">
														<opcion valor="" texto="-- Seleccione el negocio a agregar--" />
														<xsl:for-each
															select="//obtenerListadoNegociosActivos/listado/Negocio">
															<xsl:sort select="cod_negocio" />
															<xsl:variable name="texto">
																(
																<xsl:value-of select="cod_negocio" />
																)
																<xsl:value-of select="nombre" />
																<xsl:if test="string-length(normalize-space(descripcion)) > 0">
																	-
																	<xsl:value-of select="descripcion"></xsl:value-of>
																</xsl:if>
															</xsl:variable>
															<opcion valor="{id_negocio}" texto="{$texto}" />
														</xsl:for-each>
													</cajatextoselector>
													<variable id="n_negocios" valor="{count(//obtenerListadoNegociosActivos/listado/Negocio)}" />	
													
													<div id="div_negocios"  class="row-filter">
														<xsl:for-each select="//obtenerNegociosPorUsuario/listado/Negocio">
															<xsl:sort select="cod_negocio" />
															<span id="fila_{id_negocio}" name="fila_negocio">													
																<a class="btn btn-sm btn-primary">
																	<span>
																	
																		(
																		<xsl:value-of select="cod_negocio" />
																		)
																		<xsl:value-of select="nombre" />
																	
																	</span>
																	<i class="fa fa-times" onclick="eliminarNegocio({id_negocio})"></i>
																</a>
																
																<input type="hidden" name="negocios:[{position()}]"
																	id="negocios:[{position()}]" value="{id_negocio}" />
															</span>
														</xsl:for-each>
													</div>
												</div>
											</div>
										</div>	
																				

										
										
											
										

										<!-- PLANTILLA PARA MOSTRAR NEGOCIOS -->
										<div class="row-filter" id="negocio_plantilla" style="display:none">
											<span id="fila_[ 1 ]" name="fila_negocio">
												<a class="btn btn-sm btn-primary">
													<span>[ 2 ]	</span> 
													<i class="fa fa-times" onclick="eliminarNegocio([ 1 ])"></i>
												</a>
												
												<input type="hidden" name="negocios:[[ 3 ]]" id="negocios:[[ 3 ]]" value="[ 1 ]" />
											</span>
										</div>

									</contenido>
								</bloque-contenido>

								<area_botones>
									<boton estilo="volver" destino="cliente/6.1.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160; Volver</boton>
									<boton estilo="guardar" validacion="validarCliente()"
										formulario="form_usuario"><i class="fa fa-save" aria-hidden="true"></i>&#160; Guardar</boton>
								</area_botones>
								<xsl:if test="$editar = 'SI'">
									<variable id="editar" />
								</xsl:if>
							</formulario>

						</xsl:otherwise>
					</xsl:choose>

					</div>
				</contenido>
			</principal>

		</pagina>

		<ventana id="vn_editar_conexion">

			<titulo>Configurar Parametros de Cargue</titulo>

			<contenido>

				<bloque estilo="grupo" id="area_configuracion">
					<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
					<formulario id="form_FtpUsuario" class="form-horizontal" destino="ordenes_pago/99.1.do">

						<h3 class="pop-title">Configuración del Servidor</h3>

						<registro>
							<item>Host</item>
							<valor>
								<cajatexto id="FtpUsuario.host" valor="{$FtpUsuario/host}" />
							</valor>
						</registro>

						<registro>
							<item>Dominio</item>
							<valor>
								<cajatexto id="FtpUsuario.dominio" valor="{$FtpUsuario/dominio}" />
							</valor>
						</registro>

						<registro>
							<item>Usuario</item>
							<valor>
								<cajatexto id="FtpUsuario.nombre_usuario" valor="{$FtpUsuario/nombre_usuario}" />
							</valor>
						</registro>

						<registro>
							<item>Contraseña</item>
							<valor>
								<input type="password" id="FtpUsuario.clave" name="FtpUsuario.clave"
									value="{$FtpUsuario/clave}" class="form-control" />
							</valor>
						</registro>

						<registro>
							<item>Ruta Carpeta de Originales</item>
							<valor>
								<cajatexto id="FtpUsuario.carpeta_transito" valor="{$FtpUsuario/carpeta_transito}" />
							</valor>
						</registro>

						<registro>
							<item>Ruta Carpeta de Procesados</item>
							<valor>
								<cajatexto id="FtpUsuario.carpeta_procesados" valor="{$FtpUsuario/carpeta_procesados}" />
							</valor>
						</registro>

						<registro>
							<item>Ruta Carpeta de Fallidos</item>
							<valor>
								<cajatexto id="FtpUsuario.carpeta_fallidos" valor="{$FtpUsuario/carpeta_fallidos}" />
							</valor>
						</registro>

						<h3 class="pop-title">Configuración de Rutas de Backup</h3>

						<registro>
							<item>Ruta Carpeta de Originales</item>
							<valor>
								<cajatexto id="FtpUsuario.carpeta_bk_transito" valor="{$FtpUsuario/carpeta_bk_transito}" />
							</valor>
						</registro>

						<registro>
							<item>Ruta Carpeta de Procesados</item>
							<valor>
								<cajatexto id="FtpUsuario.carpeta_bk_procesados"
									valor="{$FtpUsuario/carpeta_bk_procesados}" />
							</valor>
						</registro>

						<registro>
							<item>Ruta Carpeta de Fallidos</item>
							<valor>
								<cajatexto id="FtpUsuario.carpeta_bk_fallidos" valor="{$FtpUsuario/carpeta_bk_fallidos}" />
							</valor>
						</registro>

						<h3 class="pop-title">Archivos Encriptados</h3>

						<registro>
							<item>
								<input type="radio" name="encripcion" value="S">
									<xsl:if test="$FtpUsuario/encripcion='S'">
										<xsl:attribute name="checked">checked</xsl:attribute>
									</xsl:if>
								</input>
							</item>
							<valor>
								<div id="label_encripcion">Se manejan archivos encriptados</div>
							</valor>
						</registro>

						<registro>
							<item>
								<input type="radio" name="encripcion" value="N">
									<xsl:if test="$FtpUsuario/encripcion='N'">
										<xsl:attribute name="checked">checked</xsl:attribute>
									</xsl:if>
								</input>
							</item>
							<valor>
								<div id="label_no_encripcion">No se manejan archivos encriptados</div>
							</valor>
						</registro>

						<registro id="area_encripcion" visible="false" unico="true">
							<valor>
								<div class="teble-rsponsive">
									<table class="table table-">
										<tr>
											<td class="w070 rgh">
												Contraseña
											</td>
											<td>
												<cajatexto id="FtpUsuario.clave_encripcion" valor="{$FtpUsuario/clave_encripcion}" />
											</td>
										</tr>
										<tr>
											<td class="w070 rgh">
												Llave privada
											</td>
											<td>
												<areatexto id="FtpUsuario.llave_privada" valor="{$FtpUsuario/llave_privada}" />
											</td>
										</tr>
									</table>
								</div>
							</valor>
						</registro>

						<h3 class="pop-title">Detalle Configuración</h3>


						<xsl:call-template name="FTP_USUARIO_CORREOS" />

						<registro>
							<item>Tiempo de validación de archivos como repetidos (en días)</item>
							<valor>
								<cajatexto id="FtpUsuario.dias_validacion" valor="{$FtpUsuario/dias_validacion}" />
							</valor>
						</registro>

						<variable id="FtpUsuario.id_usuario" valor="{$Usuario/id_usuario}" />
						<variable id="FtpUsuario.id_ftp_usuario" valor="{$FtpUsuario/id_ftp_usuario}" />
						<variable id="FtpUsuario.encripcion" valor="{$FtpUsuario/encripcion}" />
						<variable id="id_respuesta" valor="" />

					</formulario>
					</div>
				</bloque>

				<div class="modal-footer">
					<boton estilo="validar" validacion="validarCampos()" accion="validarConexion()"><i class="fa fa-check-square-o" aria-hidden="true"></i>&#160; Validar
						Conexión</boton>
					<boton id="btnGuardar" validacion="validarCampos()" visible="false" estilo="crear" accion="obtenerIdRespuesta()"><i class="fa fa-save" aria-hidden="true"></i>&#160; Guardar</boton>
					<boton estilo="cancelar" accion="ocultarVentanaEdicionFTP();"><i class="fa fa-times" aria-hidden="true"></i>&#160; Cancelar</boton>
				</div>

			</contenido>
		</ventana>

	</xsl:template>

	<xsl:template name="FTP_USUARIO_CORREOS">

		<xsl:variable name="FtpUsuario"
			select="//obtenerFtpUsuarioPorUsuario/FtpUsuario" />
		<xsl:variable name="correos" select="$FtpUsuario/correos_correval" />

		<div id="div_correos">
			<xsl:choose>
				<xsl:when test="count($correos/FtpUsuarioCorreo)>0">
					<variable id="n_correos" valor="{count($correos/FtpUsuarioCorreo)}" />
					<xsl:for-each select="$correos/FtpUsuarioCorreo">
						<xsl:sort select="correo" />
						<registro id="fila_correo_{position()}">
							<item>
								<xsl:choose>
									<xsl:when test="position() = 1">
										Correos notificaciones
									</xsl:when>
									<xsl:otherwise>
										<div class="eliminar" style="float: right;"
											onclick="eliminarCorreo({position()});"></div>
									</xsl:otherwise>
								</xsl:choose>
							</item>
							<valor>
								<div style="overflow: hidden">
									<variable
										id="FtpUsuario.correos_correval:{position()}.id_ftp_usuario"
										valor="{$FtpUsuario/id_ftp_usuario}" />
									<variable
										id="FtpUsuario.correos_correval:{position()}.id_ftp_usuario_correo"
										valor="{id_ftp_usuario_correo}" />
									<variable id="FtpUsuario.correos_correval:{position()}.tipo"
										valor="C" />
									<div class="flft w90p">
										<cajatexto class="flft"
											id="FtpUsuario.correos_correval:{position()}.correo" valor="{correo}" />
									</div>
									<xsl:if test="position() = 1">
										<escapar>
											<div title="Añadir Correo" class="flft anadir" onclick="agregarCorreo();"></div>
										</escapar>
									</xsl:if>
								</div>
							</valor>
						</registro>
					</xsl:for-each>
				</xsl:when>
				<xsl:otherwise>
					<variable id="n_correos" valor="1" />
					<registro id="fila_correo_1">
						<item>Correos notificaciones</item>
						<valor>
							<div style="overflow: hidden">
								<variable id="FtpUsuario.correos_correval:1.tipo"
									valor="C" />
								<variable id="FtpUsuario.correos_correval:1.id_ftp_usuario"
									valor="{$FtpUsuario/id_ftp_usuario}" />
								<div class="flft w90p">
									<cajatexto id="FtpUsuario.correos_correval:1.correo"
										valor="" />
								</div>
								<escapar>
									<div class="flft anadir" onclick="agregarCorreo();"></div>
								</escapar>
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
						<variable id="FtpUsuario.correos_correval:[ 1 ].id_ftp_usuario"
							valor="{$FtpUsuario/id_ftp_usuario}" />
						<variable id="FtpUsuario.correos_correval:[ 1 ].tipo"
							valor="C" />
						<variable
							id="FtpUsuario.correos_correval:[ 1 ].id_ftp_usuario_correo"
							valor="" />
						<div class="flft w90p">
							<cajatexto class="flft"
								id="FtpUsuario.correos_correval:[ 1 ].correo" valor="" />
						</div>
					</div>
				</valor>
			</registro>
		</div>

	</xsl:template>

</xsl:stylesheet>
