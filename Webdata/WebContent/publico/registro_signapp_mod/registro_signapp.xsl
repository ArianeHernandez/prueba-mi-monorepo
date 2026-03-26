<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://stylesheets/login/form.xsl" />
	<xsl:decimal-format name="pesos" NaN="--"
		decimal-separator="," grouping-separator="." />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<pagina tipo_login="true">
			<javascript>publico/registro_signapp_mod/registro_signapp.js</javascript>
			<stylesheet>reportePersonalizado/reporte.css</stylesheet>
			<stylesheet>registro_signapp_mod/registro_signapp.css</stylesheet>
			<javascript>archivos_adjuntos/archivos_adjuntos.js</javascript>
			<javascript>archivos_adjuntos/s3_archivos_adjuntos.js</javascript>

			<principal>
				<contenido>

					<div class="login-content">

						<div class="header" style="transform: translateY(-10px)">

							<nav class="navbar navbar-default">
								<div class="container-fluid">

									<div class="navbar-header">
										<button type="button" class="navbar-toggle collapsed"
											data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
											aria-expanded="false">
											<span class="sr-only">Toggle navigation</span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
											<span class="icon-bar"></span>
										</button>

										<a class="navbar-brand logo-mix" href="#"></a>
									</div>


									<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

										<ul class="nav navbar-nav navbar-right">
											<li>
												<a href="#" onclick="osm_go('inicio/0.pub')"
													aria-controls="inicio" role="tab" data-toggle="tab">Inicio</a>
											</li>
										</ul>
									</div>
								</div>
							</nav>

						</div>

						<div class="login-body">


							<div class="tab-content">
								<div id="pagina">
									<div id="principal">
										<div id="nota_info">
											<nota>
											</nota>
										</div>
										<div id="nota_error">
											<alerta> <span id="msg_alerta"></span> </alerta>
										</div>
										<div id="formulario_solicitud" class="" style="display:block">

											<div class="panel-body bloque_contenido_contenido">

												<div
													class="card col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-1">

													<div class="tb-text">
														<div class="Title-1">Registro Alterno</div>
													</div>
													<div class="divider"></div>
													<div class="tb-text">
                                                        <div class="Subtitle-2">Deudor Solicitante</div>
                                                    </div>
                                                    <div class="divider"></div>
                                                    <div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Tipo de Documento:</div>
														<div class="col-md-9">
															<select class="select-default form-input" id="tipoDocumento">
                                                            </select>
														</div>
													</div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Número de Documento:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="numeroDocumento" />
														</div>
													</div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Fecha de Expedición:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="date" placeholder="Ingresar valor" id="fechaExpedicion" />
														</div>
													</div>
													
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Nombres:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="nombres" />
														</div>
													</div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Apellidos:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="apellidos" />
														</div>
													</div>
													<div class="tb-text">
                                                        <div class="Subtitle-2">Información de Seguridad</div>
                                                    </div>
                                                    <div class="divider"></div>
													
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Fecha de Nacimiento:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="date" placeholder="Ingresar valor" id="fechaNacimiento" />
														</div>
													</div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Correo:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="correo" onchange="validarCorreo()"/>
														</div>
													</div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Confirmar correo:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="correo_confirmar" onchange="validarCorreo()"/>
														</div>
													</div>
													<div class="row col-md-12" id="div_confirmar_correo" style="display:none;">
                                                        <div class="col-md-3 Normal-1 form-label"></div>
                                                        <div class="col-md-9 Normal-1 text-danger">Los valores de correo y confirmar correo deben ser iguales
                                                        </div>
                                                    </div><div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Usuario:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="login" onchange="loginOnChange()"/>
														</div>
													</div>
													<div class="row col-md-12" id="login_error" style="display:none;">
                                                        <div class="col-md-3 Normal-1 form-label"></div>
                                                        <div class="col-md-9 Normal-1 text-danger">Usuario no disponible
                                                        </div>
                                                    </div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Celular:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="celular" onchange="validarCelular()"/>
														</div>
													</div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Confirmar Celular:</div>
														<div class="col-md-9">
															<input class="input-default form-input"
																type="text" placeholder="Ingresar valor" id="celular_confirmar" onchange="validarCelular()"/>
														</div>
													</div>
													<div class="row col-md-12" id="div_confirmar_celular" style="display:none;">
                                                        <div class="col-md-3 Normal-1 form-label"></div>
                                                        <div class="col-md-9 Normal-1 text-danger">Los valores de celular y confirmar celular deben ser iguales
                                                        </div>
                                                    </div>
													<div class="row col-md-12">
														<div class="col-md-3 Normal-1 form-label">Sexo:</div>
														<div class="col-md-9">
															<select class="select-default form-input" id="sexo">
																<option value="">-- Seleccione --</option>
																<opcion valor="F" texto="Femenino"/>
																<opcion valor="M" texto="Masculino"/>
                                                            </select>
														</div>
													</div>

													<div id="nota_info_soc">
														<div style="margin-top: 20px;" class="alert alert-info ">
															<i aria-hidden="true" class="fa fa-info-circle"></i>
															<span id="">
																Recuerde que los adjuntos deben estar en formato .jpg, .jpeg, .png
															</span>
														</div>
													</div>

													<div
														class="row col-md-12 margin-row div-input-file tooltip_custom">
														<div class="col-md-3 Normal-1">
															Foto de documento frontal
														</div>
														<div class="row form-input col-md-9">
															<input
																id="label_archivo_documento_frontal"
																class="input-file-text sc-start" type="text"
																placeholder="Arrastre o seleccione un archivo" disabled="" />

															<div
																class="l-button input-file-gral input-file-button p-buttonenable sc-end">
																Adjuntar
															</div>
															<i
																id="trash_archivo_documento_frontal"
																aria-hidden="true"
																onclick="removeFile('archivo_documento_frontal')"
																class="fa fa-trash ic-trash ic-hide"></i>
														</div>
														<form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
															method="post" enctype="multipart/form-data" onsubmit="return false;"
															id="archivo_documento_frontal"
															name="archivo_documento_frontal"
															class="formulario_archivo input-custom">

															<div class="row col-md-12 margin-row">
																<input id="caja_archivo_adjunto" name="caja_archivo_adjunto"
																	class="input-caja" type="file" style="width: 80%" accept="image/jpeg,image/png"/>
																<input type="hidden" id="id_carga" name="id_carga" />
                                                                <input type="hidden" id="id_archivo_adjunto"
                                                                    name="id_archivo_adjunto"/>

															</div>

															<div style="display: none">
																<input class="form-control" type="hidden"
																	autocomplete="off" id="descripcion_archivo_adj" name="descripcion_archivo_adj"
																	value="Foto frontal del documento" />
															</div>
														</form>

													</div>
													<div
														class="row col-md-12 margin-row div-input-file tooltip_custom">
														<div class="col-md-3 Normal-1">
															Foto de documento posterior
														</div>
														<div class="row form-input col-md-9">
															<input
																id="label_archivo_documento_posterior"
																class="input-file-text sc-start" type="text"
																placeholder="Arrastre o seleccione un archivo" disabled="" />

															<div
																class="l-button input-file-gral input-file-button p-buttonenable sc-end">
																Adjuntar
															</div>
															<i
																id="trash_archivo_documento_posterior"
																aria-hidden="true"
																onclick="removeFile('archivo_documento_posterior')"
																class="fa fa-trash ic-trash ic-hide"></i>
														</div>
														<form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
															method="post" enctype="multipart/form-data" onsubmit="return false;"
															id="archivo_documento_posterior"
															name="archivo_documento_posterior"
															class="formulario_archivo input-custom">

															<div class="row col-md-12 margin-row">
																<input id="caja_archivo_adjunto" name="caja_archivo_adjunto"
																	class="input-caja" type="file" style="width: 80%" accept="image/jpeg,image/png"/>
																<input type="hidden" id="id_carga" name="id_carga" />
                                                                <input type="hidden" id="id_archivo_adjunto"
                                                                    name="id_archivo_adjunto"/>

															</div>

															<div style="display: none">
																<input class="form-control" type="hidden"
																	autocomplete="off" id="descripcion_archivo_adj" name="descripcion_archivo_adj"
																	value="Foto posterior del documento" />
															</div>
														</form>

													</div>
													<div
														class="row col-md-12 margin-row div-input-file tooltip_custom">
														<div class="col-md-3 Normal-1">
															Foto selfie (De Frente)
														</div>
														<div class="row form-input col-md-9">
															<input
																id="label_archivo_foto_selfie"
																class="input-file-text sc-start" type="text"
																placeholder="Arrastre o seleccione un archivo" disabled="" />

															<div
																class="l-button input-file-gral input-file-button p-buttonenable sc-end">
																Adjuntar
															</div>
															<i
																id="trash_archivo_foto_selfie"
																aria-hidden="true"
																onclick="removeFile('archivo_foto_selfie')"
																class="fa fa-trash ic-trash ic-hide"></i>
														</div>
														<form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
															method="post" enctype="multipart/form-data" onsubmit="return false;"
															id="archivo_foto_selfie"
															name="archivo_foto_selfie"
															class="formulario_archivo input-custom">

															<div class="row col-md-12 margin-row">
																<input id="caja_archivo_adjunto" name="caja_archivo_adjunto"
																	class="input-caja" type="file" style="width: 80%" accept="image/jpeg,image/png"/>
																<input type="hidden" id="id_carga" name="id_carga" />
                                                                <input type="hidden" id="id_archivo_adjunto"
                                                                    name="id_archivo_adjunto"/>

															</div>

															<div style="display: none">
																<input class="form-control" type="hidden"
																	autocomplete="off" id="descripcion_archivo_adj" name="descripcion_archivo_adj"
																	value="Foto selfie" />
															</div>
														</form>

													</div>
													<div id="botones_form">
														<xsl:if test="count( //recaptchaSecret ) > 0">
															<script src='https://www.google.com/recaptcha/api.js?hl=es' />
															<center style="margin: 20px 0px">
																<div class="g-recaptcha" data-sitekey="{//recaptchaSecret}"></div>
															</center>
														</xsl:if>
														<area_botones>
															<button onclick="enviarSolicitud()"
																class="btn btn-primary">Continuar</button>
															<button onclick="osm_go('inicio/0.pub')" class="btn btn-primary">Volver</button>
														</area_botones>
													</div>
												</div>
											</div>
										</div>
										<div id="seccion_carga" style="display:none;">
											<div id="barra_carga" class="barra_carga">
												<div id="barra_contenido" class="barra_carga_contenido"
													style="height:24px;width:0%">
												</div>
											</div>
											<p id="textBar">
												Cargando
												<span id="archivos_adjuntados">0</span>
												de
												<span id="total_adjuntos">
												</span>
												archivos
											</p>
										</div>
									</div>

								</div>
								<div id="div_exito">
									<div class="alert alert-success row">
										<i class="fa fa-check-circle" aria-hidden="true"></i>
										<span class="sr-only">Info:</span>
										<p>IMPORTANTE ! La solicitud se envió correctamente. La
											información será revisada. En caso de ser aprobada le
											llegará
											un correo para que pueda
											iniciar sesión en el
											sistema.</p>
									</div>
								</div>
								<div id="div_error">
									<alerta> No fue posible enviar la solicitud, inténtelo más
										tarde.
									</alerta>
								</div>
								<div id="area_botones">
									<area_botones>
										<button onclick="osm_go('inicio/0.pub')" class="btn btn-primary">Volver</button>
									</area_botones>
								</div>
							</div>

						</div>

					</div>
					<input type="hidden" id="id_carga_base" />
				</contenido>
			</principal>

		</pagina>
		<ventana id="vn_creacion_solicitud" icono="confirmacion">
			<titulo>Registro Alterno Signapp</titulo>
			<contenido>
				<div class="row-box form-horizontal">
					<parrafo>El sistema está cargando la información. Por favor espere.</parrafo>
				</div>
			</contenido>
		</ventana>
		<ventana id="vn_error_cargue" icono="confirmacion">
			<titulo>Registro Alterno Signapp</titulo>
			<contenido>
				<div class="row-box form-horizontal">
					<parrafo>Señor usuario, los documentos soporte de la solicitud no se han podido cargar.</parrafo>
					<parrafo>Para poder realizar el envío de su solicitud reintente el cargue de los documentos
							utilizando el botón "Reintentar", en caso de querer reemplazar la información del registro 
							seleccione el botón "Volver".</parrafo>
					<area_botones>
						<button class="btn btn-sm btn-primary" id="btn_reintentar_vn"
							>
							Reintentar
						</button>
						<button class="btn btn-sm btn-primary" id="btn_cancelar_reintento_vn"
							>
							Volver
						</button>
					</area_botones>
				</div>
			</contenido>
		</ventana>


	</xsl:template>

</xsl:stylesheet>