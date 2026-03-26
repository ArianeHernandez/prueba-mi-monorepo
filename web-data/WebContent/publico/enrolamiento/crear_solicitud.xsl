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
            <javascript>publico/crear_solicitud.js</javascript>
            <javascript>publico/custom_common_formns/commonResoursesCustomForms.js</javascript>
            <stylesheet>reportePersonalizado/reporte.css</stylesheet>
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
                                        <!-- ul class="nav navbar-nav"></ul -->
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
                            <!-- Nav tabs -->
                            <!-- ul class="nav nav-tabs text-center" role="tablist"><li role="presentation" 
								class="active"><a href="#inicio" aria-controls="inicio" role="tab" data-toggle="tab">Inicio</a></li><li role="presentation"><a href="#manual" aria-controls="manual" role="tab" 
								data-toggle="tab">óComo hacer la Solicitud?</a></li></ul -->
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div id="pagina" ng-app="app" ng-controller="enrolamiento">
                                    <div id="principal">
                                        <div id="nota_error" style="display:none">
                                            <alerta> {{mensaje_error}} </alerta>
                                        </div>
                                        <div id="formulario_solicitud" class="" style="display:block">
                                            <div class="panel-body bloque_contenido_contenido">
                                                <div
													class="card col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                                                    <div id="nota_info_soc" style="display:none">
                                                        <nota>
															Recuerde: Quien realiza el trámite de inscripción en el Módulo de
															Insolvencia MI ante la Superintendencia de Sociedades
															debe corresponder únicamente  al Representante Legal de la
															sociedad inscrito en el Certificado de Existencia y
															Representación Legal
														</nota>
                                                    </div>
                                                    <div id="nota_info_pnc" style="display:none">
                                                        <nota>
															Recuerde: Quien realiza el trámite  de inscripción en el Módulo de
															Insolvencia MI ante la Superintendencia de Sociedades
															debe corresponder únicamente  al deudor inscrito en el
															Certificado de Cómara y Comercio
														</nota>
                                                    </div>
                                                    <div id="nota_info_pnnoc" style="display:none">
                                                        <nota>
															Recuerde: Quien realiza el trámite  de inscripción en el Módulo de
															Insolvencia MI ante la Superintendencia de Sociedades
															debe corresponder únicamente  a los deudores sujetos a su
															competencia.
														</nota>
                                                    </div>
                                                    <div class="row col-md-12">
                                                        <div class="col-md-3 Title-1 form-label">Tipo de Solicitante:
														</div>
                                                        <div class="col-md-9">
                                                            <select id="tipoFormulario" ng-init="solicitud.tipo_formulario = 1"
																class="select-default form-input"
																ng-options="option.valor as option.nombre for option 
                                    							in tiposSolicitud"
																ng-model="solicitud.tipo_formulario" ng-change="setTipoUsuario(solicitud.tipo_formulario)"></select>
                                                        </div>
                                                    </div>
                                                    <br />
                                                    <div id="contenidoTab">
                                                        <div class="divider"></div>
                                                        <br />
                                                        <div class="row col-md-12 tooltip_custom" id="formNIT">
                                                            <div class="col-md-3 Normal-1 form-label">NIT:</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="solicitud.datos_representante.nit_sociedad" class="input-default form-input"
																	type="text" placeholder="Ingresar valor" id="nit"  ng-blur="nitChange()" ng-change="nitChange()"/>
                                                            </div>
                                                            <div class="tooltiptext2">
                                                                <p>Ingresar NIT sin dígito de verificación.</p>
                                                            </div>
                                                            <div class="row col-md-12" ng-show="mensajenit!=''">
	                                                            <div class="col-md-3 Normal-1 form-label"></div>
	                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajenit}}
																</div>
	                                                        </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Teléfono:</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="solicitud.datos_representante.celular"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="numero_telefono"
																	ng-blur="telefonoChange()" ng-change="telefonoChange()"/>
                                                            </div>
                                                            <div class="row col-md-12" ng-show="mensajetelefono!=''">
	                                                            <div class="col-md-3 Normal-1 form-label"></div>
	                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajetelefono}}
																</div>
	                                                        </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label" id="deudor_pais">País de domicilio</div>
                                                            <div class="col-md-9">
                                                                <input id="pais" class="input-default form-input"
																		type="text" placeholder="Colombia" disabled="" value="Colombia"/>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12 ">
                                                            <div class="col-md-3 Normal-1 form-label">Departamento de domicilio</div>
                                                            <div class="col-md-9">
                                                                <select class="select-default form-input" id="deudor_departamento" ng-model="solicitud.departamento_dane"
																	onchange="consultarCiudades()" >
                                                                    <option value=""> Seleccione </option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Municipio de domicilio</div>
                                                            <div class="col-md-9">
                                                                <select class="select-default form-input" id="deudor_ciudad" ng-model="solicitud.municipio_dane"
																	disabled="true">
                                                                    <option value=""> Seleccione </option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Dirección de notificación:</div>
                                                            <div class="col-md-9">
                                                                <input
																	ng-model="solicitud.direccion"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor"
																	id="pj_usuario_direccion_notificacion" ng-blur="direccionChange()" ng-change="direccionChange()"/>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12" ng-show="mensajedireccion!=''">
                                                            <div class="col-md-3 Normal-1 form-label"></div>
                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajedireccion}}
															</div>
                                                        </div>
                                                        <div class="row col-md-12 tooltip_custom">
                                                            <div class="col-md-3 Normal-1 form-label">Correo
																Electrónico:
															</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="correo"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="correo"
																	ng-change="correoChange()" ng-blur="correoBlur()" />
                                                            </div>
                                                            <div class="tooltiptext2">
                                                                <p>En este correo electrónico recibirá las comunicaciones del proceso.</p>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Confirmar Correo
																Electrónico:
															</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="correo_confirmacion"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="correo_confirmacion"
																	ng-change="correoChange()" ng-blur="correoBlur()"/>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12" ng-show="mensajecorreo!=''">
                                                            <div class="col-md-3 Normal-1 form-label"></div>
                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajecorreo}}
															</div>
                                                        </div>
														<div class="tb-text">
                                                            <div class="Subtitle-2" id="label_identificador_deudor">Información del solicitante</div>
                                                        </div>
                                                        <div class="divider"></div>
                                                        <br />
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Tipo de Documento:
															</div>
                                                            <div class="col-md-9">
                                                                <select class="select-default form-input"
																	ng-options="option.id as option.nombre for option 
                                        in tipos_documento | orderBy:'id'"
																	ng-model="tipo_documento" id="tipo_documento" ng-change="documentoChange()"></select>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Número de
																Documento:
															</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="numero_documento"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="numero_documento" 
																	ng-blur="documentoChange()" ng-change="documentoChange()"/>
                                                            </div>
                                                            <div class="row col-md-12" ng-show="mensajedocumento!=''">
	                                                            <div class="col-md-3 Normal-1 form-label"></div>
	                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajedocumento}}</div>
                                                        </div>
                                                        </div>
                                                        <div class="row col-md-12">
														    <div class="col-md-3 Normal-1 form-label">Nombres:</div>
														    <div class="col-md-9">
														        <input ng-model="solicitud.datos_representante.nombres"
														               class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
														               type="text" placeholder="Ingresar valor" id="pj_usuario_nombre" 
														               ng-change="nombreChange()" ng-blur="nombreChange()"/>
														    </div>
														</div>
														<div class="row col-md-12" ng-show="mensajenombres!=''">
														    <div class="col-md-3 Normal-1 form-label"></div>
														    <div class="col-md-9 Normal-1 text-danger">{{mensajenombres}}</div>
														</div>
														
														<div class="row col-md-12">
														    <div class="col-md-3 Normal-1 form-label">Apellidos:</div>
														    <div class="col-md-9">
														        <input ng-model="solicitud.datos_representante.apellidos"
														               class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
														               type="text" placeholder="Ingresar valor" id="pj_usuario_apellidos" 
														               ng-change="apellidoChange()" ng-blur="apellidoChange()"/>
														    </div>
														</div>
														<div class="row col-md-12" ng-show="mensajeapellidos!=''">
														    <div class="col-md-3 Normal-1 form-label"></div>
														    <div class="col-md-9 Normal-1 text-danger">{{mensajeapellidos}}</div>
														</div>
                                                        <div class="tb-text">
                                                            <div class="Subtitle-2">Información de Seguridad</div>
                                                            <texto key="MENSAJE ALERTA INFO SEGURIDAD" ocultar_vacio="true"/>
                                                        </div>
                                                        <div class="divider"></div>
                                                        <br />
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Ingrese Token Sign
																App:</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="otp"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="otp" ng-change="tokenChange()"/>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12" ng-show="mensajetoken!=''">
                                                            <div class="col-md-3 Normal-1 form-label"></div>
                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajetoken}}
															</div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Usuario:</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="login"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="login"
																	ng-blur="loginChange()"  ng-change="loginChange()"/>
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12" ng-show="mensajelogin!=''">
                                                            <div class="col-md-3 Normal-1 form-label"></div>
                                                            <div class="col-md-9 Normal-1 text-danger">{{mensajelogin}}
															</div>
                                                        </div>
                                                        <div
															class="row col-md-12 margin-row div-input-file tooltip_custom">
                                                            <div class="col-md-3 Normal-1" id="archivo_label">
																Certificado de Existencia y Representación Legal:
															</div>
                                                            <div class="row form-input col-md-9">
                                                                <input id="label_formulario_archivo" class="input-file-text sc-start"
																	type="text" placeholder="Arrastre o seleccione un archivo"
																	disabled="" />
                                                                <div
																	class="l-button input-file-gral input-file-button p-buttonenable sc-end">
																	Adjuntar
																</div>
                                                                <i id="trash_formulario_archivo" aria-hidden="true"
																	ng-click="removeFile('formulario_archivo')" class="fa fa-trash ic-trash ic-hide"></i>
                                                            </div>
                                                            <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
																method="post" enctype="multipart/form-data" onsubmit="return false;"
																id="formulario_archivo" name="formulario_archivo"
																class="formulario_archivo input-custom">
                                                                <div class="row col-md-12 margin-row">
                                                                	<input type="hidden" name="csrfToken" value="f1b8105da0274dae86b17813b6fdc1532imhf38xjj8kuqo8ch7ee9jly4dyhku6"/>                                                               
                                                                    <input id="caja_archivo_adjunto" name="caja_archivo_adjunto"
																		class="input-caja" type="file" style="width: 80%" />
                                                                    <input type="hidden" id="id_carga" name="id_carga" />
                                                                    <input type="hidden" id="id_archivo_adjunto"
																		name="id_archivo_adjunto" />
																	<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>
                                                                </div>
                                                                <div style="display: none">
                                                                    <input class="form-control" type="hidden"
																		autocomplete="off" id="descripcion_archivo_adj" name="descripcion_archivo_adj"
																		value="Certificado de existencia y representación legal" />
																	<input class="form-control" type="hidden" autocomplete="off"
																		id="id_tipo_archivo" name="id_tipo_archivo"
																		value="11" />
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <div id="empresaSucursales"  style="display:none">
                                                        <div class="row col-md-12 margin-row">
                                                            <div class="col-md-6 Normal-1" id="solicitante_controlante_sociedad_reorganizacio_label">
                                                                    ¿El solicitante es controlante de una sociedad que se encuentra en reorganización?</div>
                                                            <div id="solicitante_controlante_sociedad_reorganizacio_div" class="col-md-6 row div_botones">
                                                                <input class="form-control" type="hidden" autocomplete="off"
										id="solicitante_controlante_sociedad_reorganizacio" name="solicitante_controlante_sociedad_reorganizacio" />
                                                            </div>
                                                        </div>
                                                        <div id="contenedor_solicitante_controlante_sociedad_reorganizacio">
                                                         <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">NIT:</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="solicitud.sociedad.nit"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="pj_usuario_apellidos" />
                                                            </div>
                                                        </div>
                                                        <div class="row col-md-12">
                                                            <div class="col-md-3 Normal-1 form-label">Nombre de la sociedad:</div>
                                                            <div class="col-md-9">
                                                                <input ng-model="solicitud.sociedad.nombre"
																	class="input-default form-input ng-pristine ng-untouched ng-valid ng-empty"
																	type="text" placeholder="Ingresar valor" id="pj_usuario_apellidos" />
                                                            </div>
                                                        </div>
                                                        </div>
                                                        </div>
								                        <div class="card" style="max-width: 100%; display: none;" id="mensaje_footer_pnnoc">
								                                <div class="alert alert-info">
								                                
								                                    Tenga en cuenta que la admisión al proceso de la Persona Natural No Comerciante depende de la existencia de la situación de control sobre la sociedad al momento de la solicitud.
								            
								                                </div>
								                            </div>
                                                       
                                                        <div id="botones_form">
                                                            <xsl:if test="string-length( //recaptchaSecret ) > 0">
                                                                <script src='https://www.google.com/recaptcha/api.js?hl=es' />
                                                                <center style="margin: 20px 0px">
                                                                    <div class="g-recaptcha" data-sitekey="{//recaptchaSecret}"></div>
                                                                </center>
                                                            </xsl:if>
                                                            <area_botones>
                                                            	<a href="#bs-example-navbar-collapse-1">
                                                            		<button ng-click="enviar()"
																		class="btn btn-primary">Continuar</button>
                                                            	</a>
                                                                <button onclick="osm_go('inicio/0.pub')" class="btn btn-primary">Volver</button>
                                                            </area_botones>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="div_exito">
                                        <div class="alert alert-success row">
                                            <i class="fa fa-check-circle" aria-hidden="true"></i>
                                            <span class="sr-only">Info:</span>
                                            <p>El registro de su usuario se envió correctamente. La información será revisada. En caso de ser aprobada le
												llegará un correo para que pueda iniciar sesión en el sistema.<br/>
												<b>¡IMPORTANTE! </b>En caso de no recibir el mensaje de activación de cuenta al correo registrado en los
												próximos 10 min por favor revise su bandeja de spam o contacte al área de soporte de la Entidad,
												comunicándose al teléfono 2201000 ext. 3020 o al correo soporte@supersociedades.gov.co. 
											</p>
                                        </div>
                                    </div>
                                    <div id="div_error">
                                        <alerta> No fue posible enviar la solicitud, intóntelo mós
											tarde. </alerta>
                                    </div>
                                    <div id="area_botones" style="display:none">
                                        <area_botones>
                                            <button onclick="osm_go('inicio/0.pub')" class="btn btn-primary">Volver</button>
                                            <button id="boton_enviar" ng-click="enviar()" class="btn btn-primary">Enviar</button>
                                        </area_botones>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Tab panes -->
                        </div>
                    </div>
                </contenido>
            </principal>
        </pagina>
        <ventana id="vn_advertencia_PNC_PNNC" icono="confirmacion">
				<titulo>Confirmación Tipo de Solicitante</titulo>
				<contenido>

					<div class="row-box form-horizontal">
						
						<parrafo> ¿Está seguro que desea continuar su registro como <b id="t_form_advertencia"></b>?</parrafo>
						<parrafo id="msg_PNC"> Recuerde que una <b>Persona Natural Comerciante</b> es aquella que profesionalmente se ocupa en alguna de las actividades que la ley considera mercantiles. <em>(Artículo 10 Código de Comercio)</em> y adicionalmente debe cumplir con la obligaciones establecidas en el <em>Artículo 19</em> de dicha norma, cómo es estar matriculado en el registro mercantil.
						</parrafo>
						<parrafo> Si desea continuar su registro como <b id="t_form_final"></b> seleccione el botón Aceptar, de lo contrario seleccione el botón Cancelar y cambie el tipo de solicitante.
						</parrafo>
					
						<area_botones>
							<button class="btn btn-sm btn-primary" id="btn_aceptar_vn_ad"
								>
								Aceptar
							</button>
							<button class="btn btn-sm btn-primary" id="btn_cancelar_vn_ad"
								>
								Cancelar
							</button>
						</area_botones>
						
					</div>
				</contenido>
			</ventana>
		<ventana id="vn_creacion_solicitud" icono="confirmacion">
			<titulo>Registro Solicitante</titulo>
			<contenido>
				<div class="row-box form-horizontal">
					<parrafo>El sistema está cargando la información. Por favor espere.</parrafo>
				</div>
			</contenido>
		</ventana>
		<ventana id="vn_error_cargue" icono="confirmacion">
			<titulo>Registro Solicitante</titulo>
			<contenido>
				<div class="row-box form-horizontal">
					<parrafo>Señor usuario, el documento soporte de la solicitud no se ha podido cargar.</parrafo>
					<parrafo>Para poder realizar el envío de su solicitud reintente el cargue del documento
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
