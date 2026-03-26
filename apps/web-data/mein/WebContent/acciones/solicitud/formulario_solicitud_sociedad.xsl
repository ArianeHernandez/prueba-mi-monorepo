<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:include href="context://common/xsl/osm_page.xsl" />
    <xsl:include href="context://componentes/menu/acciones.xsl" />
    <xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
        <pagina titulo="Confirmacion">
            <javascript>solicitud/funcionesValidacion.js</javascript>
            <javascript>solicitud/validacionSolicitud.js</javascript>
            <javascript>solicitud/setTipoSolicitud.js</javascript>
            <javascript>solicitud/formulario_solicitud_sociedad.js</javascript>
            <javascript>solicitud/generarSolicitud.js</javascript>
            <javascript>publico/custom_common_formns/commonResoursesCustomForms.js</javascript>
            <javascript>archivos_adjuntos/archivos_adjuntos.js</javascript>
			<javascript>archivos_adjuntos/s3_archivos_adjuntos.js</javascript>

            <javascript>templates/generar_pdf_html.js</javascript>
			<javascript>jspdf/html2canvas.min.js</javascript>
			<javascript>jspdf/jspdf.min.js</javascript>
			<javascript>solicitud/redireccionLiberacion.js</javascript>
            <stylesheet>reportePersonalizado/reporte.css</stylesheet>
            <stylesheet>jspdf/generar_pdf_html.css</stylesheet>
            <principal>
                <contenido>
                    <div id="msg_modal" class="modal-msg">
                        <!-- Modal content -->
                        <div class="modal-msg-content card">
                            <div class="tb-text">
                                <div class="Subtitle-2">Importante</div>
                            </div>
                            <div class="divider"></div>
                            <div class="tb-text">
                                <div class="Normal-1">
                                    <i aria-hidden="true" class="fa fa-info-circle"></i>
									Para
									salvaguardar la seguridad de su información personal y la
									de la sociedad,
									la
									aplicación
									permite un tiempo máximo de
									inactividad de 12 horas. Transcurrido este
									tiempo, el
									formulario se
									cerrará automáticamente y usted deberá iniciar el proceso
									nuevamente.
								
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div style="text-align: center;">
                                <button class="l-button s-buttonenable-primary" onclick="cerrarModal()"
									id="boton_enviar">Continuar</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" id="formulario_completo">
                        <div id="formulario_carga" class="card" style="">
                            <div class="alert alert-info" id="mensajeAlerta">
							
								IMPORTANTE: A continuación, se presentan plantillas que sirven como base para la presentación de algunos 
								documentos por parte del deudor. Sin embargo, las mismas deberán ajustarse a los requerimientos, necesidades, 
								grupo de preparadores de información y objeto social que desarrolla el deudor. Tenga en cuenta que las mismas deben estar suscritas 
								por el representante legal, contador (si aplica) y revisor fiscal (si aplica). <strong>Se recomienda descargar 
								las plantillas y abrirlas directamente desde su equipo local para su diligenciamiento y firma.</strong>
        
							</div>
                            <div id="nota_advertencia_info" class="alert alert-danger" >
							
								ADVERTENCIA: Tenga en cuenta que, al diligenciar el formulario, debe cumplir con la obligación de cargar todos los documentos solicitados. 
								Adicionalmente, verifique que los archivos que se carguen correspondan a un formato válido.
							</div>

                        <input class="form-control" type="hidden" autocomplete="off"
										id="id_carga" name="id_carga" value="{//ID_CARGA}"/>
                        <div class="Title-1">Solicitud de Negociación de Acuerdos de
							Reorganización (Ley 2437 de 2024)</div>
                        <input type="hidden" id="tipos_solicitud" value="{//solicitud/tipo_formulario}" />
                        <div class="card" style="max-width: 70%;">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Tipo de solicitud de insolvencia
								</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label">Tipo de Solicitante</div>
                                <div class="col-md-6">
                                    <input id="tipo_solicitante_near" class="input-default form-input"
										type="text" placeholder="Tipo Solicitante" disabled="" />
                                </div>
                                <div class="col-md-6 Normal-1 form-label">Tipo de Solicitud</div>
                                <div class="col-md-6">
                                    <select class="select-default form-input" id="tipo_solicitud_near"
										disabled="true">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label">Norma Aplicable</div>
                                <div class="col-md-6">
                                    <select class="select-default form-input" id="norma_aplicable"
										disabled="true">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;">
                            <div class="tb-text">
                                <div class="Subtitle-2" id="label_identificador_deudor">Identificación del deudor</div>
                                <div class="Normal-1">Artículo 2, Ley 1116 de 2006</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12">
                                <div class="col-md-6 Normal-1 form-label" id="tipo_de_identificacion_label">Tipo de identificación</div>
                                <div class="col-md-6">
                                    <input id="tipo_identificacion" class="input-default form-input"
										type="text" placeholder="Tipo de identificacion" disabled="" />
                                </div>
                            </div>
                            <div class="row col-md-12">
                                <div class="col-md-6 Normal-1 form-label" id="identificacion_solicitante_label">NIT del deudor solicitante</div>
                                <div class="col-md-6">
                                    <input id="deudor_nit" class="input-default form-input"
										type="text" placeholder="NIT" disabled="" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label" id="nombre_solicitante_label">Razón Social del Deudor Solicitante</div>
                                <div class="col-md-6">
                                    <input id="deudor_razon_social" class="input-default form-input"
										type="text" placeholder="Razón social" disabled="" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="row_ciiu">
                                <div class="col-md-6 Normal-1 form-label">Código CIIU</div>
                                <div class="col-md-6">
                                    <select onmouseover="" onclick="" onchange=""
										autocomplete="off" class="select-default form-input" id="deudor_ciiu"
										name="deudor_ciiu" style="visibility: visible;">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="row_deudor_macrosector">
                                <div class="col-md-6 Normal-1 form-label">Macrosector</div>
                                <div class="col-md-6">
                                    <select onmouseover="" onclick="" onchange=""
										autocomplete="off" class="select-default form-input" id="deudor_macrosector"
										name="deudor_macrosector" style="visibility: visible;">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12">
                               <div class="col-md-6 Normal-1 form-label" id="deudor_pais_label">País de domicilio</div>
                                   <div class="col-md-6">
                                       <select onmouseover="" onclick="" onchange=""
										autocomplete="off" class="select-default form-input" id="deudor_pais"
										name="deudor_pais" style="visibility: visible;">
                                           <option value="">-- Seleccione --</option>
                                       </select>
                                   </div>
                               </div>
                                
                            <div id="inputContainer">
                               <div class="row col-md-12 margin-row">
                                    <div class="col-md-6 Normal-1 form-label">Departamento de domicilio</div>
                                    <div class="col-md-6">
                                        <select class="select-default form-input" id="deudor_departamento">
                                            <option value=""> Seleccione </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row">
                                    <div class="col-md-6 Normal-1 form-label">Municipio de domicilio</div>
                                    <div class="col-md-6">
                                        <select class="select-default form-input" id="deudor_ciudad" ng-model="solicitud.ciudad"
											disabled="true">
                                            <option value=""> Seleccione </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-md-12">
                                <div class="col-md-6 Normal-1 form-label" id="deudor_direccion_label">Dirección de notificación</div>
                                <div class="col-md-6">
                                    <input id="deudor_direccion" class="input-default form-input"
										type="text" placeholder="Dirección" />
                                </div>
                            </div>
                            <div class="row col-md-12 tooltip_custom" >
                                <div class="col-md-6 Normal-1 form-label" id="deudor_correo_label">Correo electrónico de notificación</div>
                                <div class="col-md-6">
                                    <input id="deudor_correo" class="input-default form-input"
										type="text" placeholder="Email" />
                                </div>
                                <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                <div class="tooltiptext">
                                    <p>En este correo electrónico recibirá las comunicaciones del proceso.</p>
                                </div>
                            </div>
                            <div class="row col-md-12">
                                <div class="col-md-6 Normal-1 form-label" id="deudor_telefono_label">Teléfono</div>
                                <div class="col-md-6">
                                    <input id="deudor_telefono" class="input-default form-input"
										type="text" placeholder="Teléfono" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="row_naturaleza">
                                <div class="col-md-6 Normal-1 form-label">Naturaleza</div>
                                <div id="deudor_naturaleza_div" class="col-md-6 row div_botones">
                                    <div class="Normal-1 text-danger col-md-12" style="display: none;">Seleccione
										una
										opción</div>
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="deudor_naturaleza" name="deudor_naturaleza" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="div_porcentaje_p"
								style="display: none">
                                <div class="col-md-6 Normal-1 form-label">Porcentaje participación estatal</div>
                                <div class="col-md-6">
                                    <input onfocus="" onmouseover="" onclick="" reference=""
										alias="" autocomplete="off" value="" type="text"
										class="input-default form-input"
										onblur="mostrarPorcentajeConFormato('deudor_porcentaje_estatal_formato', 'deudor_porcentaje_estatal')"
										id="deudor_porcentaje_estatal" name="deudor_porcentaje_estatal"
										placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarProcentajeSinFormato('deudor_porcentaje_estatal_formato', 'deudor_porcentaje_estatal')"
										id="deudor_porcentaje_estatal_formato" class="input-default form-input"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="empleadas_row">
                                <div class="col-md-6 Normal-1 form-label">Número de empleadas mujeres</div>
                                <div class="col-md-6">
                                    <input id="deudor_empleados_mujeres" class="input-default form-input numeric_values"
										onblur="validarValorNumerico('deudor_empleados_mujeres')"
										type="text" placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="empleados_row">
                                <div class="col-md-6 Normal-1 form-label">Número de empleados hombres</div>
                                <div class="col-md-6">
                                    <input id="deudor_empleados_hombres" class="input-default form-input numeric_values"
										onblur="validarValorNumerico('deudor_empleados_hombres')" 
										type="text" placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="row_representante_legal">
                                <div class="col-md-6 Normal-1 form-label">Representante legal</div>
                                <div class="col-md-6">
                                    <select id="representante_legal" name="representante_legal"
										class="select-default form-input">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_certificado_existencia_row">
                                <div class="col-md-6 Normal-1" id="title_archivo_certificado_existencia">
									Certificado de Existencia y Representacion Legal
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_certificado_existencia" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_certificado_existencia" aria-hidden="true"
										onclick="removeFile('archivo_certificado_existencia')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificado_existencia" name="archivo_certificado_existencia"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificado de existencia y representacion legal" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="11" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>El certificado de existencia y representacion legal expedido por 
										la camara de comercio del lugar del domicilio del deudor, 
										debe tener una vigencia no mayor a tres meses contados a partir de 
										la fecha de radicación de esta solicitud.</p>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="row_archivo_certificado_matricula_mercantil">
                                <div class="col-md-6 Normal-1">
									Certificado de Matrícula Mercantil 
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_certificado_matricula_mercantil"
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_certificado_matricula_mercantil"
										aria-hidden="true"
										onclick="removeFile('archivo_certificado_matricula_mercantil')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificado_matricula_mercantil" name="archivo_certificado_matricula_mercantil"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificado de Matrícula Mercantil" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="69" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row" id="row_replegal_tiene_limitacion">
                                <div class="col-md-6 Normal-1 form-label" id="replegal_tiene_limitacion_label">
									El Representante Legal tiene alguna limitación para presentar el proceso	
								</div>
                                <div id="replegal_tiene_limitacion_div"
									class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="replegal_tiene_limitacion" name="replegal_tiene_limitacion" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="row_archivo_maximo_organo_autoriza_replegal">
                                <div class="col-md-6 Normal-1">
									Documento mediante el cuál el máximo órgano competente autoriza al representante legal 
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_maximo_organo_autoriza_replegal"
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_maximo_organo_autoriza_replegal"
										aria-hidden="true"
										onclick="removeFile('archivo_maximo_organo_autoriza_replegal')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_maximo_organo_autoriza_replegal" name="archivo_maximo_organo_autoriza_replegal"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Documento mediante el cuál el máximo órgano competente autoriza al representante legal" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="91" />
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
								Información de profesionales asociados al deudor
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>
										Se requiere haber registrado previamente la información del contador, revisor fiscal (si aplica) y apoderado (si aplica)
										</p>
                                    </div>
                                </div>
                                <div class="Normal-1">
                                	<div class="alert-warning">
                                		ADVERTENCIA: Se requiere haber registrado previamente la información del contador, 
                                		revisor fiscal (si aplica) y apoderado (si aplica) para continuar con el diligenciamiento del formulario.
                                		Para registrar esta información por favor diríjase a la opción de menú: Usuario -> Contador/Rev. Fiscal.<br/>
                                		Nota: Lo anterior no significa que estos roles deban hacer la aprobacion de la solicitud para poder radicarla ante la Superintendencia de Sociedades, pero podrán realizar consultas de esta solicitud.
                                	</div>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label" id="contador_label">Contador</div>
                                <div class="col-md-6">
                                    <select id="contador" name="contador" class="select-default form-input">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row" id="row_revisor_fiscal">
                                <div class="col-md-6 Normal-1 form-label" id="label_revisor_fiscal">Revisor fiscal (Si aplica)</div>
                                <div class="col-md-6">
                                    <select id="revisor_fiscal" name="revisor_fiscal"
										class="select-default form-input">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1 form-label">Apoderado (Opcional)</div>
                                <div class="col-md-6">
                                    <select id="apoderado" name="apoderado" class="select-default form-input" onchange="mostrarPoderAbogado()">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                                <div class="tooltiptext">
                                    <p>Parágrafo del Artículo 11, Ley 1116 de 2006. La solicitud de
										inicio del proceso
										podrá hacerse
										directamente o a través de
										abogado.</p>
                                </div>
                            </div>
                            <div id="contenedor_apoderado">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="poder_abogado_row">
                                    <div class="col-md-6 Normal-1">Poder al abogado para realizar la solicitud
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_poder_abogado" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_poder_abogado" aria-hidden="true"
										onclick="removeFile('archivo_poder_abogado')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_poder_abogado" name="archivo_poder_abogado"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Poder al abogado para realizar la solicitud" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="12" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>Parágrafo del Artículo 11, Ley 1116 de 2006. La solicitud de
										inicio del proceso podrá hacerse directamente o a través de
										abogado.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
                                    <span id="tittle_card_memoria_explicativa">Memoria Explicativa de las Causas de Insolvencia</span>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext" id="text_tooltip_memoria_explicativa">
                                        <p>El régimen de insolvencia regulado en la Ley 2437 de 2024,
											tiene
											por
											objeto mitigar
											la extensión de
											los efectos sobre las empresas afectadas por
											las causas que
											motivaron la
											declaratoria del estado de emergencia económica,
											social y ecológica de
											que
											trata
											el
											decreto 417 del
											17 de marzo de
											2020, y la recuperación y conservación de la
											empresa como
											unidad
											de
											explotación
											económica y fuente generadora de empleo, a
											través de los mecanismos de
											salvamento y
											recuperación
											aquí
											previstos.</p>
                                        <p>Las herramientas allí previstas, entre ellas el trámite de
											Negociación de
											Emergencia
											de Acuerdos de
											Reorganización, serán
											aplicables a las empresas que se han afectado como
											consecuencia de la
											emergencia antes mencionada, y estarán
											disponibles desde la entrada en
											vigencia
											del
											mencionado
											decreto
											legislativo, hasta dos (2) años contados a partir de entrada
											en
											vigencia
											del
											mismo.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div
								class="row margin-row col-md-12 Normal-1 form-label tooltip_custom">
                                <div class="col-md-10" id="contenido_parrafo_cesacion_pagos">"Manifiesto que, de
									conformidad con
									la Ley 2437 de 2024, las causas que dan
									inicio
									a este proceso de
									Negociación de un Acuerdo
									de Reorganización son consecuencia de los hechos
									que dieron
									lugar a la
									Emergencia Económica, Social y Ecológica declarada
									mediante el Decreto
									417 de 17 de
									marzo de 2020."</div>
                                <div class="col-md-2" id="memoria_explicativa_manifiesto_div">
                                    <div class="l-button s-buttonenable-primary 1"
										onclick="setButtonValue('memoria_explicativa_manifiesto',1, '1')">Si</div>
                                    <input onchange="" class="input-default" type="hidden"
										autocomplete="off" id="memoria_explicativa_manifiesto" name="memoria_explicativa_manifiesto"
										onclick="" onmouseover="" />
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row div-input-file">
                                <div class="col-md-6 Normal-1">
									Memoria explicativa de las causas de insolvencia (Descargar
									
                                    <a onclick="descargaPlantilla(13,39,53,13)">plantilla aquí</a>
									)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_memoria_explicativa" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_memoria_explicativa" aria-hidden="true"
										onclick="removeFile('archivo_memoria_explicativa')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_memoria_explicativa" name="archivo_memoria_explicativa"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Memoria explicativa de las causas de insolvencia" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="13" />
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="contenedor_supuestos_admisibilidadd">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Supuestos de admisibilidad
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>
                                            <strong>Cesación de pagos de conformidad con el numeral 1 del 
											artículo 9 de la Ley 1116 de 2006</strong>
                                        </p>
                                        <p>
											El deudor estará en cesación de pagos cuando incumpla el pago 
											por más de noventa (90) días de dos (2) o más obligaciones a favor 
											de dos (2) o más acreedores, contraídas en desarrollo de su actividad, 
											o tenga por lo menos dos (2) demandas de ejecución presentadas por 
											dos (2) o más acreedores para el pago de obligaciones. En cualquier 
											caso, el valor acumulado de las obligaciones en cuestión deberá 
											representar no menos del diez por ciento (10%) del pasivo total a 
											cargo del deudor a la fecha de los estados financieros de la solicitud, 
											de conformidad con lo establecido para el efecto en la presente ley.
										</p>
                                        <p>
                                            <strong>Incapacidad de pago inminente de conformidad con el numeral 
											2 del artículo 9 de la Ley 1116 de 2006</strong>
                                        </p>
                                        <p>
											El deudor estará en situación de incapacidad de pago inminente, cuando 
											acredite la existencia de circunstancias en el respectivo mercado o al 
											interior de su organización o estructura, que afecten o razonablemente 
											puedan afectar en forma grave, el cumplimiento normal de sus obligaciones, 
											con un vencimiento igual o inferior a un año.
										</p>
                                    </div>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1" id="situacion_presentada_label">Certifico que la actividad que desarrollo 
								se encuentra en el supuesto de
								</div>
                                <div class="col-md-6">
                                    <select onmouseover="" onclick="" onchange="mostrarOpcionesadmisibilidad();"
										autocomplete="off" class="select-default form-input" id="situacion_presentada"
										name="situacion_presentada" style="visibility: visible;">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <!-- Se añaden archivos para supuesto de admisibilidad -->
                            <div id="contenedor_situaciones">
                                <div id="contenedor_Cesacion">
                                    <div class="row col-md-12 margin-row div-input-file">
                                        <div class="col-md-6 Normal-1">
									Certificación que acredite la cesación de pagos (Descargar
									
                                            <a onclick="descargaPlantilla(19,41,41,19)">plantilla aquí</a>
									)
								
                                        </div>
                                        <div class="row form-input col-md-6">
                                            <input id="label_archivo_certificado_cesacion_pagos" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                            <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                            <i id="trash_archivo_certificado_cesacion_pagos" aria-hidden="true"
										onclick="removeFile('archivo_certificado_cesacion_pagos')" class="fa fa-trash ic-trash ic-hide"></i>
                                        </div>
                                        <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificado_cesacion_pagos" name="archivo_certificado_cesacion_pagos"
									class="formulario_archivo input-custom">
                                            <div class="row col-md-12 margin-row">
                                                <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                            </div>
                                            <div style="display: none">
                                                <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación que acredite la cesación de pagos" />
												<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="14" />
                                            </div>
                                        </form>
                                    </div>
                                    <div class="row col-md-12 margin-row div-input-file">
                                        <div class="col-md-6 Normal-1">
									Anexo de la certificación de cesación de pagos (Descargar
									
                                            <a onclick="descargaPlantilla(20,40,40,20)">plantilla aquí</a>
									)
								
                                        </div>
                                        <div class="row form-input col-md-6">
                                            <input id="label_archivo_anexo_cesacion_pagos" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                            <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                            <i id="trash_archivo_anexo_cesacion_pagos" aria-hidden="true"
										onclick="removeFile('archivo_anexo_cesacion_pagos')" class="fa fa-trash ic-trash ic-hide"></i>
                                        </div>
                                        <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_anexo_cesacion_pagos" name="archivo_anexo_cesacion_pagos"
									class="formulario_archivo input-custom">
                                            <div class="row col-md-12 margin-row">
                                                <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                            </div>
                                            <div style="display: none">
                                                <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Anexo de la certificación de cesación de pagos" />
												<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="15" />
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file" id="contenedor_Incapacidad">
                                    <div class="col-md-6 Normal-1">
									Certificación que acredite la incapacidad de pago (Descargar
									
                                        <a onclick="descargaPlantilla(21,0,0,21)">plantilla aquí</a>
									)
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_certificado_incapacidad_pago" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_certificado_incapacidad_pago" aria-hidden="true"
										onclick="removeFile('archivo_certificado_incapacidad_pago')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificado_incapacidad_pago" name="archivo_certificado_incapacidad_pago"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación que acredite la incapacidad de pago" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="16" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1" id="negociacion_emergencia_adelanta_label">Certifico que la negociación 
								de emergencia del acuerdo de reorganización se adelanta con
								</div>
                                <div class="col-md-6">
                                    <select onmouseover="" onclick="" onchange=";"
										autocomplete="off" class="select-default form-input" id="negociacion_se_adelanta_por"
										name="negociacion_se_adelanta_por" style="visibility: visible;">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!-- Se añaden archivos para condicion de controlante -->
                        <div class="card" style="max-width: 70%; display: none;" id="rowCondicionControlante">
                            <div class="tb-text">
                                <div class="Subtitle-2" style="position: relative;">
									Condición de controlante
                                </div>
                            </div>
                            <div class="divider"></div>
                            
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label" id="controlante_sociedad_en_reorganizacion_label">
									¿El solicitante es controlante de una sociedad que se encuentra en reorganización?
								</div>
                                <div id="controlante_sociedad_en_reorganizacion_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="controlante_sociedad_en_reorganizacion" name="controlante_sociedad_en_reorganizacion" />
                                </div>
                            </div>                      
                            
                            <div id="contenedor_controlante_sociedad_en_reorganizacion" name="contenedor_controlante_sociedad_en_reorganizacion">
	                            <div id="nit_sociedad_controlada_row" class="row col-md-12 margin-row">
	                                <div class="col-md-6 Normal-1 form-label" id="nit_sociedad_controlada_label">NIT</div>
	                                <div class="col-md-6">
	                                    <input name="nit_sociedad_controlada" id="nit_sociedad_controlada" class="input-default form-input"
											type="text" placeholder="NIT" />
	                                </div>
	                            </div>
	                            <div id="nombre_sociedad_controlada_row" class="row col-md-12 margin-row">
	                                <div class="col-md-6 Normal-1 form-label" id="nombre_sociedad_controlada_label">Nombre de la sociedad</div>
	                                <div class="col-md-6">
	                                    <input name="nombre_sociedad_controlada" id="nombre_sociedad_controlada" class="input-default form-input"
											type="text" placeholder="Nombre Sociedad" />
	                                </div>
	                            </div>
	                            <div id="radicado_sociedad_controlada_row" class="row col-md-12 margin-row">
	                                <div class="col-md-6 Normal-1 form-label" id="radicado_sociedad_controlada_label">Radicado solicitud o auto</div>
	                                <div class="col-md-6">
	                                    <input name="radicado_sociedad_controlada" id="radicado_sociedad_controlada" class="input-default form-input"
											type="text" placeholder="Radicado solicitud o auto" />
	                                </div>
	                            </div>
                            </div>
                            
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Certificación en la que se indique que el deudor es controlante de la sociedad
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_certificado_controlante_sociedad" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_certificado_controlante_sociedad" aria-hidden="true"
										onclick="removeFile('archivo_certificado_controlante_sociedad')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificado_controlante_sociedad" name="archivo_certificado_controlante_sociedad"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación en la que se indique que el deudor es controlante de la sociedad" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="50" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>Acreditación que la persona natural no comerciante es controlante de una sociedad.</p>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file">
                                <div class="col-md-6 Normal-1">
									Composición del capital de la sociedad controlada
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_composicion_sociedad_controlada" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_composicion_sociedad_controlada" aria-hidden="true"
										onclick="removeFile('archivo_composicion_sociedad_controlada')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_composicion_sociedad_controlada" name="archivo_composicion_sociedad_controlada"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Composición del capital de la sociedad controlada" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="51" />
                                    </div>
                                </form>
                            </div>
                            <!---  -->
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_certificado_representacion_legal_pnnoc">
                                <div class="col-md-6 Normal-1">
                                                            Certificado de Existencia y Representación Legal de la Sociedad
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_certificado_representacion_legal_pnnoc" class="input-file-text sc-start"
                                                                    type="text" placeholder="Arrastre o seleccione un archivo"
                                                                    disabled="" />
                                    <div
                                                                    class="l-button input-file-gral input-file-button p-buttonenable sc-end">
                                                                    Adjuntar
                                                                </div>
                                    <i id="trash_certificado_representacion_legal_pnnoc" aria-hidden="true"
                                                                    onclick="removeFile('certificado_representacion_legal_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
                                                                method="post" enctype="multipart/form-data" onsubmit="return false;"
                                                                id="certificado_representacion_legal_pnnoc" name="certificado_representacion_legal_pnnoc" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
                                                                        class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
                                                                        id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
                                                                        value="Certificado de Existencia y Representación Legal de la Sociedad" />
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="11" />
                                    </div>
                                </form>
                            </div>
                            <!-- -->
                            <div class="row col-md-12 margin-row div-input-file">
                                <div class="col-md-6 Normal-1">
									Prueba de ser controlante en sociedad admitida
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_prueba_controlante_soc_admitida" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_prueba_controlante_soc_admitida" aria-hidden="true"
										onclick="removeFile('archivo_prueba_controlante_soc_admitida')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_prueba_controlante_soc_admitida" name="archivo_prueba_controlante_soc_admitida"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Prueba de ser controlante en sociedad admitida" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="52" />
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Relación de	Pasivos
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Usted podrá descargar la plantilla para relacionar los
											pasivos por concepto
											de
											retenciones,
											descuentos efectuados a trabajadores y aportes al sistema de seguridad
											social.
											Recuerde que de
											conformidad con el artículo 32 de la Ley 1429
											de 2010, sin perjuicio de
											la
											responsabilidad penal o
											de
											cualquiera otra índole a que hubiere lugar, la existencia de
											pasivos por
											retenciones de carácter
											obligatorio a favor de
											autoridades fiscales, descuentos efectuados a
											trabajadores o
											aportes al
											sistema de seguridad social no impedirá al deudor
											acceder al proceso
											de
											reorganización.</p>
                                        <p>En todo caso, al momento de presentar la solicitud el
											deudor informará al
											juez
											acerca de su
											existencia y presentará un
											plan para la atención de dichos pasivos, los
											cuales
											deberán
											satisfacerse
											a más tardar al momento de la confirmación del
											acuerdo de
											reorganización. Si
											a
											esa
											fecha no se
											cumpliere dicha
											condición, el juez no podrá confirmar el acuerdo que
											le
											fuere
											presentado.</p>
                                        <p>Las obligaciones que por estos conceptos se causen con
											posterioridad al
											inicio
											del
											proceso serán
											pagadas como gastos de
											administración.</p>
                                    </div>
                                </div>
                                <div class="Normal-1">Artículo 32, Ley 1429 de 2010</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label" id="relacion_pasivos_retenciones_autoridades_fiscales_label">
									¿Tiene pasivos por retenciones de
									carácter
									obligatorio
									a favor de autoridades fiscales?</div>
                                <div id="relacion_pasivos_retenciones_autoridades_fiscales_div"
									class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="relacion_pasivos_retenciones_autoridades_fiscales" name="relacion_pasivos_retenciones_autoridades_fiscales" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Certificación de tener o no pasivos por retenciones obligatorias con el fisco 
									(Descargar 
                                        
                                    <a onclick="descargaPlantilla(24,45,45,24)">plantilla aquí</a>)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_retenciones_fisco"
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_retenciones_fisco"
										aria-hidden="true"
										onclick="removeFile('archivo_retenciones_fisco')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_retenciones_fisco" name="archivo_retenciones_fisco"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de tener o no pasivos por retenciones obligatorias con el fisco" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="17" />
                                    </div>
                                </form>
                            </div>
                            <div id="contenedor_relacion_pasivos_retenciones_autoridades_fiscales">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">
									Plan para la atención de pasivos por retenciones obligatorias con el fisco
                                    (Descargar 
                                        
                                    <a onclick="descargaPlantilla(25,44,44,25)">plantilla aquí</a>)
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_plan_fisco" 
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_plan_fisco"
										aria-hidden="true"
										onclick="removeFile('archivo_plan_fisco')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_plan_fisco" name="archivo_plan_fisco"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Plan para la atención de pasivos por retenciones obligatorias con el fisco" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="18" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>
										Plan para la atención de dichos pasivos, los cuales deberán satisfacerse a más tardar al momento de la confirmación del acuerdo de reorganización.
									</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label" id="relacion_pasivos_descuentos_trabajadores_label">
									¿La sociedad tiene pasivos por descuentos
									efectuados a
									trabajadores?</div>
                                <div id="relacion_pasivos_descuentos_trabajadores_div"
									class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="relacion_pasivos_descuentos_trabajadores" name="relacion_pasivos_descuentos_trabajadores" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Certificación de tener o no tener pasivos por descuentos efectuados a trabajadores (Descargar
                                    <a onclick="descargaPlantilla(22,43,43,22)">plantilla aquí</a>)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_retenciones_trabajadores"
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_retenciones_trabajadores"
										aria-hidden="true"
										onclick="removeFile('archivo_retenciones_trabajadores')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_retenciones_trabajadores" name="archivo_retenciones_trabajadores"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de tener o no tener pasivos por descuentos efectuados a trabajadores" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="19" />
                                    </div>
                                </form>
                            </div>
                            <div id="contenedor_relacion_pasivos_descuentos_trabajadores">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">
									Plan para la atención de pasivos por descuentos efectuados a trabajadores
                                    (Descargar 
                                    <a onclick="descargaPlantilla(23,42,42,23)">plantilla aquí</a>)
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_plan_trabajadores" 
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_plan_trabajadores"
										aria-hidden="true"
										onclick="removeFile('archivo_plan_trabajadores')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_plan_trabajadores" name="archivo_plan_trabajadores"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Plan para la atención de pasivos por descuentos efectuados a trabajadores" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="20" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>
										Plan para la atención de dichos pasivos, los cuales deberán satisfacerse 
										a más tardar al momento de la confirmación del acuerdo de reorganización.
									</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1 form-label" id="relacion_pasivos_aportes_seguridad_social_label">
									¿La sociedad tiene pasivos por aportes al
									sistema
									de
									seguridad
									social?</div>
                                <div id="relacion_pasivos_aportes_seguridad_social_div"
									class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="relacion_pasivos_aportes_seguridad_social" name="relacion_pasivos_aportes_seguridad_social" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Certificación de tener o no pasivos por aportes al sistema de seguridad social 
									(Descargar 
                                    
                                    <a onclick="descargaPlantilla(26,47,47,26)">plantilla aquí</a>)
                                
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_retenciones_seguridad_social"
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_retenciones_seguridad_social"
										aria-hidden="true"
										onclick="removeFile('archivo_retenciones_seguridad_social')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_retenciones_seguridad_social" name="archivo_retenciones_seguridad_social"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de tener o no pasivos por aportes al sistema de seguridad social" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="21" />
                                    </div>
                                </form>
                            </div>
                            <div id="contenedor_relacion_pasivos_aportes_seguridad_social">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">
									Plan para la atención de pasivos por aportes al sistema de seguridad social
                                    (Descargar 
                                    
                                    <a onclick="descargaPlantilla(27,46,46,27)">plantilla aquí</a>)
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_plan_seguridad_social" 
										class="input-file-text sc-start" type="text"
										placeholder="Arrastre o seleccione un archivo" disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_plan_seguridad_social"
										aria-hidden="true"
										onclick="removeFile('archivo_plan_seguridad_social')"
										class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_plan_seguridad_social" name="archivo_plan_seguridad_social"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Plan para la atención de pasivos por aportes al sistema de seguridad social" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="22" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>
										Plan para la atención de dichos pasivos, los cuales deberán satisfacerse 
										a más tardar al momento de la confirmación del acuerdo de reorganización.
									</p>
                                    </div>
                                </div>
                            </div>
                            <!-- CAMPOS SOBRANTES. SE MANTIENEN TEMPORALMENTE -->
                            <div class="divider"></div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="seccion_informacion_financiera">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Información financiera con corte al último día calendario del mes anterior
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Decreto 2420 de 2015</p>
                                        <p>Por medio del cual se expide el decreto único reglamentario de las normas de contabilidad, 
										de información financiera y de aseguramiento de la Información financiera con corte al último 
										día calendario del mes anterior información y se dictan otras disposiciones.</p>
                                    </div>
                                </div>
                                <div class="Normal-1">
									<div class="alert-warning">
									   Tenga en cuenta que el valor debe estar expresado en Pesos Colombianos. Ingrese un punto como separador de decimales. Adicionalmente recuerde que los documentos deben presentarse de forma individual.
									</div>
								</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1">Fecha de los estados financieros mes anterior</div>
                                <!-- <div class="col-md-6">
                                    <cajafecha id="fecha_giro" valor=""/>
                                </div>
                                -->
                                <div class="col-md-6">
                                    <input name="fecha_giro" id="fecha_giro"
                                        class="input-default form-input" type="date"
                                        value="" autocomplete="off" alias="" reference="" onclick=""
                                        onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
                                        onfocus="mostrarDoubleSinFormato('fecha_estados_financieros_formato', 'fecha_giro')"
                                        id="fecha_estados_financieros_formato" class="input-default form-input valorlista numeric_values"
                                        placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div id="antepenultimo_radicado_dictamen_row" style="display:none;"
								class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Fecha de los estados financieros</div>
                                <div class="col-md-6">
                                    <input name="fecha_estados_financieros" id="fecha_estados_financieros"
										onblur="mostrarDoubleConFormato('tantepenultimo_radicado_dictamen_formato', 'antepenultimo_radicado_dictamen')"
										class="input-default form-input" type="date"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('fecha_estados_financieros_formato', 'fecha_estados_financieros')"
										id="fecha_estados_financieros_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1">Valor total activos mes anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_activos" id="informacion_financiera_activos"
										onblur="mostrarDoubleConFormato('informacion_financiera_activos_formato', 'informacion_financiera_activos')"
										class="input-default form-input numeric_values"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_activos_formato', 'informacion_financiera_activos')"
										id="informacion_financiera_activos_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <div class="tooltiptext">
                                        <p>Ingrese aquí la información del total de activos con corte al último día calendario del mes anterior a la realización de la solicitud.</p>
                                        <ul>
                                            <li><b>El valor debe estar expresado en Pesos Colombianos.</b></li>
                                            <li>Ingrese un punto como separador de decimales.</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div id="advertencia_activos" style="display:none;" class="col-md-12 margin-row">El
								valor total de los activos debe ser igual a pasivos + patrimonio</div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1">Valor total pasivos mes anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_pasivos" id="informacion_financiera_pasivos"
										onblur="mostrarDoubleConFormato('informacion_financiera_pasivos_formato', 'informacion_financiera_pasivos')"
										class="input-default form-input numeric_values"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_pasivos_formato', 'informacion_financiera_pasivos')"
										id="informacion_financiera_pasivos_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <div class="tooltiptext">
                                        <p>Ingrese aquí la información del total de pasivos con corte al último día calendario del mes anterior a la realización de la solicitud.</p>
                                        <ul>
                                            <li><b>El valor debe estar expresado en Pesos Colombianos.</b></li>
                                            <li>Ingrese un punto como separador de decimales.</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1">Valor total patrimonio mes anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_patrimonio" id="informacion_financiera_patrimonio"
										onblur="mostrarDoubleConFormato('informacion_financiera_patrimonio_formato', 'informacion_financiera_patrimonio')"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_patrimonio_formato', 'informacion_financiera_patrimonio')"
										id="informacion_financiera_patrimonio_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos"/>
                                </div>
                                <div class="tooltiptext">
                                    <p>Ingrese aquí la información del total de patrimonio con corte al último día calendario del mes anterior a la realización de la solicitud.</p>
                                    <ul>
                                        <li><b>El valor debe estar expresado en Pesos Colombianos.</b></li>
                                        <li>Ingrese un punto como separador de decimales.</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Grupo de preparadores de información</div>
                                <div class="col-md-6">
                                    <select onmouseover="" onclick="" onchange=";"
										autocomplete="off" class="select-default form-input" id="tipo_solicitud_niif"
										name="tipo_solicitud_niif" style="visibility: visible;">
                                        <option value="">-- Seleccione --</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom" style="display: none">
                                <div class="col-md-6 Normal-1" id="inventario_label" >
									Inventario de activos del mes anterior (Descargar 
                                    
                                    <a onclick="descargaPlantilla(28,0,0,28)">plantilla aquí</a>)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_inventario" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_inventario" aria-hidden="true"
										onclick="removeFile('archivo_inventario')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_inventario" name="archivo_inventario" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Inventario de activos del mes anterior" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="34" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>- Se comprueba el detalle de cada una de las partidas de
										activos y pasivos a un
										nivel contable más
										detallado.</p>
                                    <p>- Se debe verificar que la información corresponda al último
										día del mes
										inmediatamente anterior
										a la solicitud.</p>
                                    <p>- Debe estar suscrito por el representante legal, contador y
										revisor fiscal (si
										aplica revisor
										fiscal)</p>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">Estados financieros mes anterior (ESF, ERI,
									EFE,
									ORI, ECP)
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_estados_mes" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_estados_mes" aria-hidden="true"
										onclick="removeFile('archivo_estados_mes')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_estados_mes" name="archivo_estados_mes" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Estados financieros mes anterior (ESF, ERI, EFE, ORI, ECP)" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="23" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>Estados Financieros:</p>
                                    <p>-Debe relacionar : Estado de Situación Financiera, Estado de Resultados, Estado de Flujo de Efectivo, Otro Resultado Integral y Estado de Cambios en el Patrimonio.</p>
                                    <p>-Debe estar firmado por el representante legal, contador y revisor fiscal (si aplica revisor fiscal).</p>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">Notas y certificación a los estados financieros del mes anterior</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_nota_estados_mes" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_nota_estados_mes" aria-hidden="true"
										onclick="removeFile('archivo_nota_estados_mes')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_nota_estados_mes" name="archivo_nota_estados_mes"
									class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Notas a estados financieros y certificación a los estados financieros del mes anterior" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="24" />
                                    </div>
                                </form>
                                <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                <div class="tooltiptext">
                                    <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Información financiera con corte al último año anterior
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Ingrese aquí la información de activos, pasivos e ingresos con corte al último año anterior a la realización de está solicitud.</p>
	                                    <ul>
	                                        <li><b>El valor debe estar expresado en Pesos Colombianos.</b></li>
	                                        <li>Ingrese un punto como separador de decimales.</li>
	                                    </ul>
                                    </div>
                                </div>
                                <div class="Normal-1">
									<div class="alert-warning">
									   Tenga en cuenta que el valor debe estar expresado en Pesos Colombianos. Ingrese un punto como separador de decimales.
									</div>
								</div>
                            </div>
                            <div class="divider"></div>
                            <div id="informacion_financiera_fecha_anio_anterior" class="row col-md-12 margin-row">
							  <div class="col-md-6 Normal-1">Fecha de los estados financieros año anterior</div>
							  <div class="col-md-6">
							      <input name="fecha_eeff_anio_anterior" id="fecha_eeff_anio_anterior"
							        class="input-default form-input" type="date"
							        value="" autocomplete="off" alias="" reference="" onclick=""
							        onmouseover="" onfocus="" placeholder="Ingresar valor" />
							      <input readonly="" style="display:none"
							        onfocus="mostrarDoubleSinFormato('fecha_eeff_anio_anterior_formato', 'fecha_eeff_anio_anterior')"
							        id="fecha_eeff_anio_anterior_formato" class="input-default form-input valorlista numeric_values"
							        placeholder="Ingresar valor" />
							  </div>
							</div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1">Valor total activos año anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_activos_anio" id="informacion_financiera_activos_anio"
										onblur="mostrarDoubleConFormato('informacion_financiera_activos_anio_formato', 'informacion_financiera_activos_anio')"
										class="input-default form-input numeric_values"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_activos_anio_formato', 'informacion_financiera_activos_anio')"
										id="informacion_financiera_activos_anio_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos"/>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row tooltip_custom">
                                <div class="col-md-6 Normal-1">Valor total pasivos año anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_pasivos_anio" id="informacion_financiera_pasivos_anio"
										onblur="mostrarDoubleConFormato('informacion_financiera_pasivos_anio_formato', 'informacion_financiera_pasivos_anio')"
										class="input-default form-input numeric_values"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos"/>
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_pasivos_formato_anio', 'informacion_financiera_pasivos_anio')"
										id="informacion_financiera_pasivos_anio_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos"/>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Valor total ingresos ordinarios año anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_ingresos_ordinarios"
										id="informacion_financiera_ingresos_ordinarios"
										onblur="mostrarDoubleConFormato('informacion_financiera_ingresos_ordinarios_formato', 'informacion_financiera_ingresos_ordinarios')"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_ingresos_ordinarios_formato', 'informacion_financiera_ingresos_ordinarios')"
										id="informacion_financiera_ingresos_ordinarios_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Valor total otros ingresos año anterior</div>
                                <div class="col-md-6">
                                    <input name="informacion_financiera_otros_ingresos" id="informacion_financiera_otros_ingresos"
										onblur="mostrarDoubleConFormato('informacion_financiera_otros_ingresos_formato', 'informacion_financiera_otros_ingresos')"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor en Pesos Colombianos" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('informacion_financiera_otros_ingresos_formato', 'informacion_financiera_otros_ingresos')"
										id="informacion_financiera_otros_ingresos_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor en Pesos Colombianos" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="sociedad_inversiones_label">¿La sociedad tiene inversiones en subsidiarias,
									negocios conjuntos, asociadas u otras inversiones?</div>
                                <div id="sociedad_inversiones_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="sociedad_inversiones" name="sociedad_inversiones" />
                                </div>
                            </div>
                            <div id="total_participacion_metodo_participacion_row" style="display:none;"
								class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Valor Total Participación en ganancias (pérdidas)
									por medición al método de participación</div>
                                <div class="col-md-6">
                                    <input name="total_participacion_metodo_participacion"
										id="total_participacion_metodo_participacion"
										onblur="mostrarDoubleConFormato('total_participacion_metodo_participacion_formato', 'total_participacion_metodo_participacion')"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('total_participacion_metodo_participacion_formato', 'total_participacion_metodo_participacion')"
										id="total_participacion_metodo_participacion_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div id="total_participacion_metodo_costo_row" style="display:none;"
								class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Valor Total Participación en ganancias (pérdidas)
									por medición al método al costo</div>
                                <div class="col-md-6">
                                    <input name="total_participacion_metodo_costo" id="total_participacion_metodo_costo"
										onblur="mostrarDoubleConFormato('total_participacion_metodo_costo_formato', 'total_participacion_metodo_costo')"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('total_participacion_metodo_costo_formato', 'total_participacion_metodo_costo')"
										id="total_participacion_metodo_costo_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div id="total_participacion_metodo_vrazonable_row" style="display:none;"
								class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Valor Total Participación en ganancias (pérdidas)
									por medición al método al valor razonable</div>
                                <div class="col-md-6">
                                    <input name="total_participacion_metodo_vrazonable" id="total_participacion_metodo_vrazonable"
										onblur="mostrarDoubleConFormato('total_participacion_metodo_vrazonable_formato', 'total_participacion_metodo_vrazonable')"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('total_participacion_metodo_vrazonable_formato', 'total_participacion_metodo_vrazonable')"
										id="total_participacion_metodo_vrazonable_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="conjunto_completo_estados_financieros">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Conjunto completo de estados financieros comparativos
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Decreto 2420 de 2015</p>
                                        <p>Por medio del cual se expide el decreto único reglamentario de las normas de contabilidad, 
										de información financiera y de aseguramiento de la información y se dictan otras disposiciones.</p>
                                    </div>
                                </div>
                                <div class="Normal-1">
									<div class="alert-warning">
									   Tenga en cuenta que los documentos deben presentarse de forma individual.
									</div>
								</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="ultimo_radicado_dictamen_label">¿Usted ha remitido previamente 
								información financiera a la Superintendencia de sociedades?</div>
                                <div id="remision_previa_informacion_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="remision_previa_informacion" name="remision_previa_informacion" />
                                </div>
                            </div>
                            <div id="contenedor_yes_remision_previa_informacion">
                                <div id="ultimo_radicado_dictamen_row"
								class="row col-md-12 margin-row">
                                    <div class="col-md-6 Normal-1">Radicado mediante el cual remitió los estados financieros, notas, 
                                    certificación y dictamen al 31 de diciembre de <script>docWrite(getUltimoAnho());</script></div>
                                    <div class="col-md-6">
                                        <input name="ultimo_radicado_dictamen"
										id="ultimo_radicado_dictamen"
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                        <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('ultimo_radicado_dictamen_formato', 'ultimo_radicado_dictamen')"
										id="ultimo_radicado_dictamen_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                    </div>
                                </div>
                                <div id="penultimo_radicado_dictamen_row"
								class="row col-md-12 margin-row">
                                    <div class="col-md-6 Normal-1">Radicado mediante el cual remitió los estados financieros, notas, 
                                    certificación y dictamen al 31 de diciembre de <script>docWrite(getPenultimoAnho());</script></div>
                                    <div class="col-md-6">
                                        <input name="penultimo_radicado_dictamen" id="penultimo_radicado_dictamen"
										
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                        <input readonly="" style="display:none"
										
										id="penultimo_radicado_dictamen_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                    </div>
                                </div>
                                <div id="antepenultimo_radicado_dictamen_row"
								class="row col-md-12 margin-row">
                                    <div class="col-md-6 Normal-1">Radicado mediante el cual remitió los estados financieros, notas, 
                                    certificación y dictamen al 31 de diciembre de <script>docWrite(getAntepenultimoAnho());</script></div>
                                    <div class="col-md-6">
                                        <input name="antepenultimo_radicado_dictamen" id="antepenultimo_radicado_dictamen"
										
										class="input-default form-input numeric_values" type="text"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                        <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('antepenultimo_radicado_dictamen_formato', 'antepenultimo_radicado_dictamen')"
										id="antepenultimo_radicado_dictamen_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                    </div>
                                </div>
                            </div>
                            <div id="contenedor_not_remision_previa_informacion">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Estados financieros del último año 
                                        (<script>docWrite(getUltimoAnho());</script>)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_estados_an" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_estados_an" aria-hidden="true"
											onclick="removeFile('archivo_estados_an')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_estados_an" name="archivo_estados_an" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Estados financieros del último año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="25" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf, .xlsx, o .xls.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Notas y certificación a los estados financieros del último año 
                                        (<script>docWrite(getUltimoAnho());</script>)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_nota_estados_an" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_nota_estados_an" aria-hidden="true"
											onclick="removeFile('archivo_nota_estados_an')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_nota_estados_an" name="archivo_nota_estados_an"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Notas a estados financieros y certificación a los estados financieros del último año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="26" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Dictamen del revisor fiscal del último año 
                                    	(<script>docWrite(getUltimoAnho());</script>) (si aplica)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_dictamen_revisorf_an" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_dictamen_revisorf_an" aria-hidden="true"
											onclick="removeFile('archivo_dictamen_revisorf_an')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_dictamen_revisorf_an" name="archivo_dictamen_revisorf_an"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Dictamen del revisor fiscal del último año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="27" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Estados financieros del penúltimo año 
                                    	(<script>docWrite(getPenultimoAnho());</script>)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_estados_pan" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_estados_pan" aria-hidden="true"
											onclick="removeFile('archivo_estados_pan')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_estados_pan" name="archivo_estados_pan" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Estados financieros del penúltimo año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="28" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf, .xlsx, o .xls.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Notas y certificación a los estados financieros del penúltimo año 
										(<script>docWrite(getPenultimoAnho());</script>)
									
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_nota_estados_pan" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_nota_estados_pan" aria-hidden="true"
											onclick="removeFile('archivo_nota_estados_pan')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_nota_estados_pan" name="archivo_nota_estados_pan"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Notas a estados financieros y certificación a los estados financieros del penúltimo año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="29" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Dictamen del revisor fiscal del penúltimo año 
                                    	(<script>docWrite(getPenultimoAnho());</script>) (si aplica)
									
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_dictamen_revisorf_pan" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_dictamen_revisorf_pan" aria-hidden="true"
											onclick="removeFile('archivo_dictamen_revisorf_pan')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_dictamen_revisorf_pan" name="archivo_dictamen_revisorf_pan"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Dictamen del revisor fiscal del penúltimo año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="30" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Estados financieros del antepenúltimo año 
                                    	(<script>docWrite(getAntepenultimoAnho());</script>)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_estados_apan" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_estados_apan" aria-hidden="true"
											onclick="removeFile('archivo_estados_apan')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_estados_apan" name="archivo_estados_apan"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Estados financieros del antepenúltimo año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="31" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf, .xlsx, o .xls.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Notas y certificación a los estados financieros del antepenúltimo año
										(<script>docWrite(getAntepenultimoAnho());</script>)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_nota_estados_apan" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_nota_estados_apan" aria-hidden="true"
											onclick="removeFile('archivo_nota_estados_apan')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_nota_estados_apan" name="archivo_nota_estados_apan"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Notas a estados financieros y certificación a los estados financieros del antepenúltimo año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="32" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Dictamen del revisor fiscal del antepenúltimo año (<script>docWrite(getAntepenultimoAnho());</script>) (si aplica)</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_dictamen_revisorf_apan" class="input-file-text sc-start"
											type="text" placeholder="Arrastre o seleccione un archivo"
											disabled="" />
                                        <div
											class="l-button input-file-gral input-file-button p-buttonenable sc-end">
											Adjuntar
										</div>
                                        <i id="trash_archivo_dictamen_revisorf_apan" aria-hidden="true"
											onclick="removeFile('archivo_dictamen_revisorf_apan')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
										method="post" enctype="multipart/form-data" onsubmit="return false;"
										id="archivo_dictamen_revisorf_apan" name="archivo_dictamen_revisorf_apan"
										class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
												class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
												id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
												value="Dictamen del revisor fiscal del antepenúltimo año" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="33" />
                                        </div>
                                    </form>
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Recuerde que el documento a cargar debe estar en formato .pdf.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="contenedor_inventarios">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Inventario de activos y pasivos con corte al último día del mes anterior
									
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1" id="inventario_label">
									Inventario de activos del mes anterior (Descargar 
                                    
                                    <a onclick="descargaPlantilla(28,48,0,28)">plantilla aquí</a>)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_inventario_activos" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_inventario_activos" aria-hidden="true"
										onclick="removeFile('archivo_inventario_activos')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_inventario_activos" name="archivo_inventario_activos" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Inventario de activos del mes anterior" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="34" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>- Se comprueba el detalle de cada una de las partidas de
										activos y pasivos a un nivel contable más detallado.</p>
                                    <p>- Se debe verificar que la información corresponda al último
										día del mes inmediatamente anterior a la solicitud.</p>
                                    <p>- Debe estar suscrito por el representante legal, contador y
										revisor fiscal (si aplica revisor fiscal)</p>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1" id="inventario_label">
									Inventario de pasivos del mes anterior (Descargar 
                                    
                                    <a onclick="descargaPlantilla(29,49,0,29)">plantilla aquí</a>)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_inventario_pasivos" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_inventario_pasivos" aria-hidden="true"
										onclick="removeFile('archivo_inventario_pasivos')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_inventario_pasivos" name="archivo_inventario_pasivos" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Inventario de pasivos del mes anterior" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="35" />
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="container_otros_documentos_requeridos">
                            <div class="tb-text">
                                <div class="Subtitle-2">Otros documentos requeridos</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_contabilidad_regular">
                                <div class="col-md-6 Normal-1">
									Certificación de llevar la contabilidad regular
									(Descargar
									
                                    <a onclick="descargaPlantilla(30,50,0,30)">plantilla aquí</a>
									)
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_cr" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_cr" aria-hidden="true"
										onclick="removeFile('archivo_cr')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_cr" name="archivo_cr" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de llevar la contabilidad regular" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="36" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="row_archivo_composicion_accionaria">
                                <div class="col-md-6 Normal-1">
									Documento de la composición accionaria
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_composicion_accionaria" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_composicion_accionaria" aria-hidden="true"
										onclick="removeFile('archivo_composicion_accionaria')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_composicion_accionaria" name="archivo_composicion_accionaria" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Documento de la composición accionaria" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="92" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row" id="rowSociedadCausalDisolucion">
                                <div class="col-md-6 Normal-1" id="sociedad_en_disolucion_label"> ¿Se encuentra en causal de disolución? </div>
                                <div id="sociedad_en_disolucion_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="sociedad_en_disolucion" name="sociedad_en_disolucion" />
                                </div>
                            </div>
                            <div id="contenedor_yes_sociedad_en_disolucion">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_causal_disolucion_row">
                                    <div class="col-md-6 Normal-1">
									Certificación causal de disolución (Descargar
									
                                        <a onclick="descargaPlantilla(31,0,0,31)">plantilla aquí</a>
									)
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_causal_disolucion" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_causal_disolucion" aria-hidden="true"
										onclick="removeFile('archivo_causal_disolucion')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_causal_disolucion" name="archivo_causal_disolucion"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación causal de disolución" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="37" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>El certificado debe indicar la fecha a partir de la cual estuvo inmersa en dicha causal y aportar 
									claramente las medidas tendientes a enervarla.</p>
                                    </div>
                                </div>
                            </div>
                            <div id="contenedor_not_sociedad_en_disolucion">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_no_causal_disolucion_row">
                                    <div class="col-md-6 Normal-1">
									Certificación de no encontrarse en causal de disolución (Descargar
									
                                        <a onclick="descargaPlantilla(32,0,0,32)">plantilla aquí</a>
									)
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_no_causal_disolucion" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_no_causal_disolucion" aria-hidden="true"
										onclick="removeFile('archivo_no_causal_disolucion')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_no_causal_disolucion" name="archivo_no_causal_disolucion"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de no encontrarse en causal de disolución" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="38" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="sociedad_causal_disolucion_label">¿Tiene pasivos pensionales a cargo?</div>
                                <div id="sociedad_pasivos_pensionales_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="sociedad_pasivos_pensionales" name="sociedad_pasivos_pensionales" />
                                </div>
                            </div>
                            <div id="contenedor_sociedad_pasivos_pensionales">
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Certificación del monto de las obligaciones causadas por cálculo actuarial
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_certificacion_calculo_actuarial" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_certificacion_calculo_actuarial" aria-hidden="true"
										onclick="removeFile('archivo_certificacion_calculo_actuarial')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificacion_calculo_actuarial" name="archivo_certificacion_calculo_actuarial"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación del monto de las obligaciones causadas por cálculo actuarial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="39" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>Aportar el monto de las obligaciones causadas por cálculo actuarial del año inmediatamente
									 anterior a la presentación de la solicitud.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_causal_disolucion_row">
                                    <div class="col-md-6 Normal-1">
									Documento que acredita el cálculo actuarial
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_documento_calculo_actuarial" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_documento_calculo_actuarial" aria-hidden="true"
										onclick="removeFile('documento_calculo_actuarial')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="documento_calculo_actuarial" name="documento_calculo_actuarial"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Documento que acredita el cálculo actuarial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="40" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_aprobacion_calculo_actuarial_row">
                                    <div class="col-md-6 Normal-1">
									Aprobación del cálculo actuarial
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_aprobacion_calculo_actuarial" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_aprobacion_calculo_actuarial" aria-hidden="true"
										onclick="removeFile('archivo_aprobacion_calculo_actuarial')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_aprobacion_calculo_actuarial" name="archivo_aprobacion_calculo_actuarial"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Aprobación del cálculo actuarial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="41" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="certificacion_mesadas_al_dia_row">
                                    <div class="col-md-6 Normal-1">
									Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales
									(Descargar 
                                    
                                        <a onclick="descargaPlantilla(33,51,0,33)">plantilla aquí</a> )
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_certificacion_mesadas_al_dia" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_certificacion_mesadas_al_dia" aria-hidden="true"
										onclick="removeFile('certificacion_mesadas_al_dia')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_mesadas_al_dia" name="certificacion_mesadas_al_dia"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="42" />
                                        </div>
                                    </form>
                                </div>    
                            </div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="sociedad_garante_aalista_label">¿Es garante, avalista o codeudor de terceros?</div>
                                <div id="sociedad_garante_codeudor_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="sociedad_garante_codeudor" name="sociedad_garante_codeudor" />
                                </div>
                            </div>
                            <div id="contenedor_sociedad_garante_codeudor">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="certificacion_garante_codeudor_row">
                                    <div class="col-md-6 Normal-1">
									Certificación en la que se relacione los terceros a los cuales el deudor es 
									(avalista, garante o codeudor de terceros)
									(Descargar 
                                    
                                        <a onclick="descargaPlantilla(14,34,0,14)">plantilla aquí</a>)
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_certificacion_garante_codeudor" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_certificacion_garante_codeudor" aria-hidden="true"
										onclick="removeFile('certificacion_garante_codeudor')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_garante_codeudor" name="certificacion_garante_codeudor"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación en la que se relacione los terceros a los cuales el deudor es (avalista, garante o codeudor de terceros)" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="43" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <span id="rowPlan_negocios">
                                <div class="tb-text">
                                    <div class="Subtitle-2">Plan de negocios</div>
                                </div>
                            </span>
                            <span id="rowFlujo_caja">
                                <div class="divider"></div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1" id="plan_negocios_reorganizacion_label">Plan de negocios de reorganización</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_plan_negocios" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_plan_negocios" aria-hidden="true"
										onclick="removeFile('archivo_plan_negocios')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_plan_negocios" name="archivo_plan_negocios"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Plan de negocios de reorganización" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="44" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>Debe contemplar no solo la reestructuración financiera, sino
										también
										organizacional, operativa o
										de competitividad,
										conducentes a solucionar las razones por las
										cuales es
										solicitado el proceso,
										cuando sea del caso.</p>
                                    </div>
                                </div>
                                <div class="tb-text">
                                    <div class="Subtitle-2">Flujo de caja</div>
                                </div>
                                <div class="divider"></div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">
									Flujo de caja proyectado (Descargar
									
                                        <a onclick="descargaPlantilla(15,35,0,15)">plantilla aquí</a>
									)
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_flujo" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_flujo" aria-hidden="true"
										onclick="removeFile('archivo_flujo')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_flujo" name="archivo_flujo" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Flujo de caja proyectado" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="45" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>Un flujo de caja para atender el pago de las obligaciones sujetas al proceso.</p>
                                    </div>
                                </div>
                            </span>
                            <div class="tb-text">
                                <div class="Subtitle-2">Proyectos de calificación y graduación de créditos y derechos de voto</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Proyecto de calificación y graduación de créditos y 
									proyecto de determinación de derechos de voto
									(Descargar 
                                    
                                    <a onclick="descargaPlantilla(16,36,0,16)">plantilla aquí</a> )
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_pry_pnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_pry_pnoc" aria-hidden="true"
										onclick="removeFile('archivo_pry_pnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_pry_pnoc" name="archivo_pry_pnoc" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Proyecto de calificación y graduación de créditos y proyecto de determinación de derechos de voto" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="46" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>Descargue la plantilla para relacionar los Acreedores y
										acreencias así como el
										proyecto de
										determinación de los derechos
										de voto correspondientes a cada acreedor.</p>
                                </div>
                            </div>
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Reporte de bienes sujetos a garantías mobiliarias

                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Si existen gravámenes sobre los bienes del deudor, 
										podrá adjuntar prueba del valor de los bienes gravados con garantía.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="divider"></div>

                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="garantias_mobiliarias_label">¿Tiene bienes sujetos a garantías mobiliarias? </div>
                                <div id="garantias_mobiliarias_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="garantias_mobiliarias" name="garantias_mobiliarias" />
                                </div>
                            </div>
                            <div id="contenedor_garantias_mobiliarias">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1" >
									Certificación de bienes sujetos a garantías mobiliarias e 
									indicar cuáles de los mismos son necesarios para la actividad del deudor
									(Descargar 
                                    
                                        <a onclick="descargaPlantilla(17,38,0,17)">plantilla aquí</a> )
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_certificacion_garantia_mobiliaria" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_certificacion_garantia_mobiliaria" aria-hidden="true"
										onclick="removeFile('certificacion_garantia_mobiliaria')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_garantia_mobiliaria" name="certificacion_garantia_mobiliaria" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de bienes sujetos a garantías mobiliarias e indicar cuáles de los mismos son necesarios para la actividad del deudor" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="47" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">
									Avalúo de los bienes
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_avaluo" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_avaluo" aria-hidden="true"
										onclick="removeFile('archivo_avaluo')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_avaluo" name="archivo_avaluo" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Valor de los bienes gravados con garantía" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="48" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Certificación que relacione las obligaciones 
								que surgen de la adquisición del bien sujeto a garantía
                                (Descargar 
                                        
                                        <a onclick="descargaPlantilla(18,37,0,18)">plantilla aquí</a>
									)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_rel_pas" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_rel_pas" aria-hidden="true"
										onclick="removeFile('archivo_rel_pas')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_rel_pas" name="archivo_rel_pas" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación que relacione las obligaciones que surgen de la adquisición del bien sujeto a garantía" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="49" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- Tarjeta para Relacion completa y detallada de bienes pn_noc -->
                        <div class="card" style="max-width: 70%; display: none" id="div_card_relacion_bienes_pnnoc">
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Relación completa y detallada de sus bienes y acreedores
                                    
                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>
                                        	Relación completa y actualizada de todos los acreedores y de todos los bienes con corte al 
                                        	ultimo día calendario del mes inmediatamente anterior a la solicitud.
                                        </p>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="divider"></div>
                            <div id="fechaestadosfinancieros_pnnoc" style="display:none;"
								class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Fecha de la relación de bienes y acreedores</div>
                                <div class="col-md-6">
                                    <input name="fecha_relacion_bienes_acreedores" id="fecha_relacion_bienes_acreedores"

										class="input-default form-input" type="date"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('fecha_relacion_bienes_acreedores_formato', 'fecha_relacion_bienes_acreedores')"
										id="fecha_relacion_bienes_acreedores_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_detalle_bienes">
                                <div class="col-md-6 Normal-1">
								Detalle de bienes
                            </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_detalle_bienes_a" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_detalle_bienes_a" aria-hidden="true"
										onclick="removeFile('detalle_bienes_a')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="detalle_bienes_a" name="detalle_bienes_a" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Detalle de bienes y acreedores" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="53" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_relacion_acreedores">
                                <div class="col-md-6 Normal-1">
								Relación de los acreedores
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_relacion_acreedores" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_relacion_acreedores" aria-hidden="true"
										onclick="removeFile('relacion_acreedores')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="relacion_acreedores" name="relacion_acreedores" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Relación de los acreedores" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="54" />
                                    </div>
                                </form>
                            </div>
                            <div id="fechaestadosfinancierospnc" style="display:none;"
								class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1">Fecha de los estados financieros</div>
                                <div class="col-md-6">
                                    <input name="fecha_estados_financieros" id="fecha_estados_financieros"

										class="input-default form-input" type="date"
										value="" autocomplete="off" alias="" reference="" onclick=""
										onmouseover="" onfocus="" placeholder="Ingresar valor" />
                                    <input readonly="" style="display:none"
										onfocus="mostrarDoubleSinFormato('fecha_estados_financieros_formato', 'fecha_estados_financieros')"
										id="fecha_estados_financieros_formato" class="input-default form-input valorlista numeric_values"
										placeholder="Ingresar valor" />
                                </div>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="card_negociacion_deudas">
                            <div class="tb-text">
                                <div class="Subtitle-2">Propuesta para la negociación de deudas</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Propuesta para la negociación de deudas
                                </div>
                                <div class="row form-input col-md-6">
                                    <input  id="label_propuesta_negociacion_deudas" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_propuesta_negociacion_deudas" aria-hidden="true"
										onclick="removeFile('propuesta_negociacion_deudas')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="propuesta_negociacion_deudas" name="propuesta_negociacion_deudas" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Propuesta para la negociación de deudas" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="55" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>La propuesta para la negociación de deudas, debe ser clara, expresa y objetiva</p>
                                </div>
                            </div>
                            <div class="tb-text">
                                <div class="Subtitle-2">Proyectos de calificación y graduación de créditos y derechos de voto</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                <div class="col-md-6 Normal-1">
									Proyecto de calificación y graduación de créditos y 
									proyecto de determinación de derechos de voto
									(Descargar 
                                    
                                    <a onclick="descargaPlantilla(17,36,52,17)">plantilla aquí</a> )
								
                                </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_archivo_pry" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_archivo_pry" aria-hidden="true"
										onclick="removeFile('archivo_pry')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_pry" name="archivo_pry" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Proyecto de calificación y graduación de créditos y proyecto de determinación de derechos de voto" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="46" />
                                    </div>
                                </form>
                                <div class="tooltiptext">
                                    <p>Descargue la plantilla para relacionar los Acreedores y
										acreencias así como el
										proyecto de
										determinación de los derechos
										de voto correspondientes a cada acreedor.</p>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="max-width: 70%;" id="container_otros_documentos_requeridos_pnnoc">
                            <div class="tb-text">
                                <div class="Subtitle-2">Otros documentos</div>
                            </div>
                            <div class="divider"></div>
                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="pnnoc_pasivos_pensionales_label">¿Tiene pasivos pensionales a cargo?</div>
                                <div id="pnnoc_pasivos_pensionales_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="pnnoc_pasivos_pensionales" name="pnnoc_pasivos_pensionales" />
                                </div>
                            </div>
                            <div id="contenedor_pnnoc_pasivos_pensionales">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Certificación del monto de las obligaciones causadas por cálculo actuarial
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_certificacion_calculo_actuarial_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_certificacion_calculo_actuarial_pnnoc" aria-hidden="true"
										onclick="removeFile('archivo_certificacion_calculo_actuarial_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_certificacion_calculo_actuarial_pnnoc" name="archivo_certificacion_calculo_actuarial_pnnoc"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación del monto de las obligaciones causadas por cálculo actuarial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="39" />
                                        </div>
                                    </form>
                                    <div class="tooltiptext">
                                        <p>Aportar el monto de las obligaciones causadas por cálculo actuarial del año inmediatamente
									 anterior a la presentación de la solicitud.</p>
                                    </div>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_causal_disolucion_row">
                                    <div class="col-md-6 Normal-1">
									Documento que acredita el cálculo actuarial
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_documento_calculo_actuarial_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_documento_calculo_actuarial_pnnoc" aria-hidden="true"
										onclick="removeFile('documento_calculo_actuarial_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="documento_calculo_actuarial_pnnoc" name="documento_calculo_actuarial_pnnoc"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Documento que acredita el cálculo actuarial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="40" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="archivo_aprobacion_calculo_actuarial_row">
                                    <div class="col-md-6 Normal-1">
									Aprobación del cálculo actuarial
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_aprobacion_calculo_actuarial_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_aprobacion_calculo_actuarial_pnnoc" aria-hidden="true"
										onclick="removeFile('archivo_aprobacion_calculo_actuarial_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_aprobacion_calculo_actuarial_pnnoc" name="archivo_aprobacion_calculo_actuarial_pnnoc"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Aprobación del cálculo actuarial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="41" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom" id="certificacion_mesadas_al_dia_row">
                                    <div class="col-md-6 Normal-1">
									Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales
									(Descargar 
                                    
                                        <a onclick="descargaPlantilla(0,0,51,0)">plantilla aquí</a> )
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_certificacion_mesadas_al_dia_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_certificacion_mesadas_al_dia_pnnoc" aria-hidden="true"
										onclick="removeFile('certificacion_mesadas_al_dia_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_mesadas_al_dia_pnnoc" name="certificacion_mesadas_al_dia_pnnoc"
									class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="42" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- raton-->
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_detalle_bienes">
                                <div class="col-md-6 Normal-1">
								Detalle de bienes
                                 </div>
                                <div class="row form-input col-md-6">
                                    <input id="label_detalle_bienes_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_detalle_bienes_pnnoc" aria-hidden="true"
										onclick="removeFile('detalle_bienes_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="detalle_bienes_pnnoc" name="detalle_bienes_pnnoc" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Relación completa y detallada de los bienes" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="53" />
                                    </div>
                                </form>
                            </div>
                            <!-- quesoo-->
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_relacion_acreedores">
                                <div class="col-md-6 Normal-1">
								Relación de los acreedores
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_relacion_acreedores_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_relacion_acreedores_pnnoc" aria-hidden="true"
										onclick="removeFile('relacion_acreedores_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="relacion_acreedores_pnnoc" name="relacion_acreedores_pnnoc" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Relación completa y detallada de las acreencias" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="54" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_certificacion_procesos_judiciales_administrativos">
                                <div class="col-md-6 Normal-1">
								Certificación de tener o no tener procesos judiciales y relación de los procesos judiciales y de cualquier procedimiento o actuación administrativa de carácter patrimonial
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_certificacion_procesos_judiciales_administrativos" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_certificacion_procesos_judiciales_administrativos" aria-hidden="true"
										onclick="removeFile('certificacion_procesos_judiciales_administrativos')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_procesos_judiciales_administrativos" name="certificacion_procesos_judiciales_administrativos" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de tener o no tener procesos judiciales y relación de los procesos judiciales y de cualquier procedimiento o actuación administrativa de carácter patrimonial" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="70" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_certificacion_ingresos_deudor_juramentada">
                                <div class="col-md-6 Normal-1">
								Certificación de los ingresos del deudor expedida por su empleador o declaración juramentada de los mismos en caso de ser trabajador independiente
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_certificacion_ingresos_deudor_juramentada" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_certificacion_ingresos_deudor_juramentada" aria-hidden="true"
										onclick="removeFile('certificacion_ingresos_deudor_juramentada')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_ingresos_deudor_juramentada" name="certificacion_ingresos_deudor_juramentada" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de los ingresos del deudor expedida por su empleador o declaración juramentada de los mismos en caso de ser trabajador independiente" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="56" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_recursos_disponibles_pago_obligaciones">
                                <div class="col-md-6 Normal-1">
								Certificación de recursos disponibles para el pago de las obligaciones
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_recursos_disponibles_pago_obligaciones" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_recursos_disponibles_pago_obligaciones" aria-hidden="true"
										onclick="removeFile('recursos_disponibles_pago_obligaciones')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="recursos_disponibles_pago_obligaciones" name="recursos_disponibles_pago_obligaciones" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de recursos disponibles para el pago de las obligaciones" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="57" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_certificacion_solteria">
                                <div class="col-md-6 Normal-1">
								Certificación de tener o no sociedad conyugal o patrimonial vigente
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_certificacion_solteria" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_certificacion_solteria" aria-hidden="true"
										onclick="removeFile('certificacion_solteria')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_solteria" name="certificacion_solteria" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de tener o no sociedad conyugal o patrimonial vigente" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="58" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_certificacion_demanda_alimentos">
                                <div class="col-md-6 Normal-1">
								Certificación de tener o no obligaciones alimentarias a su cargo con la discriminación de las obligaciones alimentarias a su cargo
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_certificacion_demanda_alimentos" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_certificacion_demanda_alimentos" aria-hidden="true"
										onclick="removeFile('certificacion_demanda_alimentos')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_demanda_alimentos" name="certificacion_demanda_alimentos" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de tener o no obligaciones alimentarias a su cargo con la discriminación de las obligaciones alimentarias a su cargo" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="59" />
                                    </div>
                                </form>
                            </div>
                            <div class="row col-md-12 margin-row div-input-file " style="display: none" id="row_renta_anio_anterior">
                                <div class="col-md-6 Normal-1">
								Declaración de renta año anterior
								</div>
                                <div class="row form-input col-md-6">
                                    <input id="label_renta_anio_anterior" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                    <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                    <i id="trash_renta_anio_anterior" aria-hidden="true"
										onclick="removeFile('renta_anio_anterior')" class="fa fa-trash ic-trash ic-hide"></i>
                                </div>
                                <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="renta_anio_anterior" name="renta_anio_anterior" class="formulario_archivo input-custom">
                                    <div class="row col-md-12 margin-row">
                                        <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                    </div>
                                    <div style="display: none">
                                        <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Declaración de renta año anterior" />
										<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="60" />
                                    </div>
                                </form>
                            </div>
                            <div class="tb-text">
                                <div class="Subtitle-2 tooltip_custom" style="position: relative;">
									Reporte de bienes sujetos a garantías mobiliarias

                                    <i aria-hidden="true" class="fa fa-question-circle ic-question"></i>
                                    <div class="tooltiptext">
                                        <p>Si existen gravámenes sobre los bienes del deudor, 
										podrá adjuntar prueba del valor de los bienes gravados con garantía.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="divider"></div>

                            <div class="row col-md-12 margin-row">
                                <div class="col-md-6 Normal-1" id="sociedad_bienes_sujetos_garantias_label">¿La sociedad 
								tiene bienes sujetos a garantías mobiliarias?
								</div>
                                <div class="col-md-6 Normal-1" id="garantias_mobiliariasa_label" style="display:none;">¿Tiene bienes sujetos a garantías mobiliarias? </div>
                                <div id="garantias_mobiliariasa_div" class="col-md-6 row div_botones">
                                    <input class="form-control" type="hidden" autocomplete="off"
										id="garantias_mobiliariasa" name="garantias_mobiliariasa" />
                                </div>
                            </div>
                            <div id="contenedor_garantias_mobiliariasa">
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1" >
									Certificación de bienes sujetos a garantías mobiliarias e 
									indicar cuáles de los mismos son necesarios para la actividad del deudor
									(Descargar 
                                    
                                        <a onclick="descargaPlantilla(8,0,38,8)">plantilla aquí</a> )
								
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_certificacion_garantia_mobiliaria_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_certificacion_garantia_mobiliaria_pnnoc" aria-hidden="true"
										onclick="removeFile('certificacion_garantia_mobiliaria_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="certificacion_garantia_mobiliaria_pnnoc" name="certificacion_garantia_mobiliaria_pnnoc" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de bienes sujetos a garantías mobiliarias e indicar cuáles de los mismos son necesarios para la actividad del deudor" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="47" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">
									Avalúo de los bienes
								</div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_avaluo_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_avaluo_pnnoc" aria-hidden="true"
										onclick="removeFile('archivo_avaluo_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_avaluo_pnnoc" name="archivo_avaluo_pnnoc" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Valor de los bienes gravados con garantía" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="48" />
                                        </div>
                                    </form>
                                </div>
                                <div class="row col-md-12 margin-row div-input-file tooltip_custom">
                                    <div class="col-md-6 Normal-1">Certificación que relacione las obligaciones 
								que surgen de la adquisición del bien sujeto a garantía (Descargar 
                                        
                                        <a onclick="descargaPlantilla(18,0,37,18)">plantilla aquí</a>
									)
                                    
                                    </div>
                                    <div class="row form-input col-md-6">
                                        <input id="label_archivo_rel_pas_pnnoc" class="input-file-text sc-start"
										type="text" placeholder="Arrastre o seleccione un archivo"
										disabled="" />
                                        <div
										class="l-button input-file-gral input-file-button p-buttonenable sc-end">
										Adjuntar
									</div>
                                        <i id="trash_archivo_rel_pas_pnnoc" aria-hidden="true"
										onclick="removeFile('archivo_rel_pas_pnnoc')" class="fa fa-trash ic-trash ic-hide"></i>
                                    </div>
                                    <form action="{//CONTEXT_PATH}/adjunto.subirarchivo"
									method="post" enctype="multipart/form-data" onsubmit="return false;"
									id="archivo_rel_pas_pnnoc" name="archivo_rel_pas_pnnoc" class="formulario_archivo input-custom">
                                        <div class="row col-md-12 margin-row">
                                            <input id="caja_archivo_adjunto_form" name="caja_archivo_adjunto_form"
											class="input-caja" type="file" style="width: 80%" />
                                        </div>
                                        <div style="display: none">
                                            <input class="form-control" type="hidden" autocomplete="off"
											id="descripcion_archivo_adj_form" name="descripcion_archivo_adj_form"
											value="Certificación de garantía mobiliaria" />
											<input class="form-control" type="hidden" autocomplete="off"
											id="id_tipo_archivo_form" name="id_tipo_archivo_form"
											value="49" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
				          <xsl:if test="boolean(//obtenerFormato/Formato/usa_adjuntos_formulario='S')">
				          	<div class="card" style="max-width: 70%;">
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
				          	</div>
						</xsl:if>
                        
                        </div>
                        <div class="card" style="max-width: 70%;">
                            <div class="tb-text" style="text-align: center;">
                                <div id="mensajeAlerta" class="alert alert-info">
                                	<p>Al dar clic en guardar estoy seguro de que se ha incluido la totalidad de la información y los documentos 
                                	para presentar la solicitud. <b>Tenga en cuenta que la solicitud sólo quedará radicada una vez se realicen los 
                                	siguientes 3 pasos.</b>
                                	</p>
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div style="text-align: center;">
								<button class="l-button s-buttonenable-primary" onclick="mostrarModalCancelacionSolicitud()"
									id="boton_cancelar">Cancelar</button>
                                <button class="l-button s-buttonenable-primary" onclick="enviar()"
									id="boton_enviar">Guardar</button>
                            </div>
                        </div>
                        <div id="div_error" class="card" style="max-width: 70%;display: none;">
                            <div>
                                <alerta> No fue posible enviar la solicitud, intentelo más
									tarde.
								</alerta>
                            </div>
                        </div>
                    </div>
                    
                    <div id="msg_modal_pdf" class="modal-msg" style="display: none; z-index: 100000;">
                        <!-- Modal content -->
                        <div class="modal-msg-content card">
                            <div class="tb-text">
                                <div class="Subtitle-2">Importante</div>
                            </div>
                            <div class="divider"></div>
                            <div class="tb-text">
                                <div class="Normal-1">
                                    <i aria-hidden="true" class="fa fa-info-circle"></i>
									El sistema está cargando la información y creando una copia de su formulario en PDF.
									Por favor espere.								
                                </div>
                            </div>
<!--                             <div class="divider"></div> -->
<!--                             <div style="text-align: center;"> -->
<!--                                 <button class="l-button s-buttonenable-primary" onclick="cerrarModalPdf()" -->
<!-- 									id="boton_enviar">Aceptar</button> -->
<!--                             </div> -->
                        </div>
                    </div>
                    <div id="msg_modal_adjuntos" class="modal-msg" style="display: none; z-index: 100000;">
                        <!-- Modal content -->
                        <div class="modal-msg-content card">
                            <div class="tb-text">
                                <div class="Subtitle-2">Importante</div>
                            </div>
                            <div class="divider"></div>
                            <div class="tb-text">
                                <div class="Normal-1">
                                    <i aria-hidden="true" class="fa fa-info-circle"></i>
									El sistema está cargando los documentos.
									Por favor espere.								
                                </div>
                                <br/>
                                <div id="seccion_carga">
			                        <div id="barra_carga" class="barra_carga">
			                            <div id="barra_contenido" class="barra_carga_contenido"
											style="height:24px;width:0%"></div>
			                        </div>
			                        <p id="textBar">
										Cargando
										
			                            <span id="archivos_adjuntados">0</span>
										de
										
			                            <span id="total_adjuntos"></span>
										archivos
									
			                        </p>
			                    </div>
                            </div>
                        </div>
                    </div>
                    <div id="msg_modal_error_adjuntos" class="modal-msg" style="display: none; z-index: 100000;">
                        <!-- Modal content -->
                        <div class="modal-msg-content card">
                            <div class="tb-text">
                                <div class="Subtitle-2">Importante</div>
                            </div>
                            <div class="divider"></div>
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
								<button class="l-button s-buttonenable-primary" onclick="mostrarModalDescartarSolicitud()"
									id="boton_enviar">Descartar Solicitud</button>
                            </div>
                        </div>
                    </div>
                    <div id="msg_modal_cancelar_solicitud" class="modal-msg" style="display: none; z-index: 100000;">
                    	<div class="modal-msg-content card">
                            <div class="tb-text">
                                <div class="Subtitle-2">Confirmación de Cancelación</div>
                            </div>
                            <div class="divider"></div>
                            <div class="tb-text">
                                <div class="Normal-1">
									¿Está seguro que desea cancelar el envío de la solicitud?. Al seleccionar aceptar se limpiará y se recargará el formulario de la solicitud.								
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div style="text-align: center;">
								<button class="l-button s-buttonenable-primary" onclick="cerrarModalCancelacionSolicitud()"
									id="boton_cancelar">Cancelar</button>
                                <button class="l-button s-buttonenable-primary" onclick="osm_enviarFormulario('form_cancelar');"
									id="boton_aceptar">Aceptar</button>
                            </div>
                        </div>
                    </div>
                    <div id="msg_modal_descartar_solicitud" class="modal-msg" style="display: none; z-index: 100000;">
                    	<div class="modal-msg-content card">
                            <div class="tb-text">
                                <div class="Subtitle-2">Confirmación de Descartar Solicitud</div>
                            </div>
                            <div class="divider"></div>
                            <div class="tb-text">
                                <div class="Normal-1">
									¿Está seguro que desea descartar el envío de la solicitud?. Al seleccionar aceptar se limpiará y se recargará el formulario de la solicitud.								
                                </div>
                            </div>
                            <div class="divider"></div>
                            <div style="text-align: center;">
								<button class="l-button s-buttonenable-primary" onclick="cerrarModalDescartarSolicitud()"
									id="boton_cancelar">Cancelar</button>
                                <button class="l-button s-buttonenable-primary" onclick="osm_enviarFormulario('form_cancelar');"
									id="boton_aceptar">Aceptar</button>
                            </div>
                        </div>
                    </div>
                    <div id="div_exito" style="display: none;margin:25px;">
                    	
                    	<div class="Title-1"><b>PASO 1 DE 3:</b> Cargue de 
							Solicitud de Negociación de Emergencia de Reorganización - <xsl:value-of select="//ID_CARGA"/></div>
                        <div class="alert alert-info">
                            <texto key="LA CARGA SE ENVIO CORRECTAMENTE" valor="{//ID_CARGA}"/>
                        </div>
                        <button class="l-button s-buttonenable-primary" onclick="osm_go('inicio/0.do')"
							id="boton_volver" style="display: none">Ir al Inicio</button>
							
						<xsl:if test="string-length(//OSM-INIT-SESSION/obtenerOsmoAutenticadorRol/OsmoAutenticaRol/TipoElementoSalidalistarRolesporLogin[id_rol=6])>0">
							<formulario id="form_carga" destino="liberacion/15.3.do">
								<variable id="id_carga" valor="{//ID_CARGA}"/>
								<variable id="id_persona" valor="{//id_persona}" />
								<variable id="id_formato_entrada" valor="{//id_formato_entrada}" />
							</formulario>						
						</xsl:if>	
                    </div>
                    <formulario id="form_cancelar" >
						<xsl:attribute name="destino">solicitud/formulario_solicitud_sociedad.do</xsl:attribute>
						<variable id="id_carga" valor="{//ID_CARGA}"/>
						<input type="hidden" name="id_formato" value="{//id_formato_entrada}"/>
					</formulario>
                </contenido>
            </principal>
        </pagina>
    </xsl:template>
</xsl:stylesheet>
