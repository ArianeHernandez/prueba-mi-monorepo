<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/ventana_videos/ventana_videos.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<xsl:variable name="acciones"
			select="//OSM-INIT-SESSION/acciones/obtenerMenusPorAplicacion/Listado" />
		<pagina>

			<javascript>inicio/0.js</javascript>
			<stylesheet>login.css</stylesheet>
			<principal>
				
				<contenido>
				<xsl:call-template name="ventana_videos"></xsl:call-template>	
					<div role="main" id="msg_bienvenida" class="col-md-12">						
						<xsl:choose>
							<xsl:when test="string-length(//OSM-SESSION/ID_ADMINISTRATIVO)>0">
								<h2 id="tab-bienvenido" class="text-center">
									<div class="pic pic-manual-02 h-160 pic-round"></div>
									<b>Bienvenido al módulo de insolvencia</b>
								</h2>
								<h4 id="tab2-020a" class="text-center">
									Accede al manual del proceso de gestión de solicitudes de reorganización y liquidación.
								</h4>
								<div class="row-btn">
									<a onclick="descargaPlantilla(55);" class="btn btn-primary">
										Descargar manual
									</a>
								</div>
							</xsl:when>
							<xsl:otherwise>
								<h2 id="tab-bienvenido" class="text-center">
									<b>Bienvenido al módulo de insolvencia</b>
									<div class="pic pic-manual-02 h-160 pic-round"></div>
								</h2>
								<div class="row-btn">
									<a onclick="descargaPlantilla(54);" class="btn btn-primary">
										Descargar manual
									</a>
									<a class="btn btn-primary btn-lg" onclick="verVentanaVideos()"><i class="fa fa-film"></i> Ver Videos</a>
								</div>
								<div class="row alert alert-info alert-home" >
									<div class="col-md-2">
										<div class="pic pic-10 h-120 pic-round pic-home"></div>
									</div>
									<div class="col-md-10">
										<p class="text-alert-home">
									    	Para revisar las comunicaciones emitidas por la Superintendencia de Sociedades correspondientes a 
									    	oficio de inadmisión tenga en cuenta que el mismo será enviado al correo electrónico de notificaciones 
									    	relacionado en la solicitud al proceso de insolvencia. <br/><br/>
									    	En cuanto a los Autos de admisión, inadmisión o rechazo los mismos podrán ser consultados a través de Baranda Virtual 
									    	en la página <a src="www.supersociedades.gov.co">www.supersociedades.gov.co</a> - baranda virtual.
										</p>
									</div>
								</div>
							</xsl:otherwise>
						</xsl:choose>
					</div>
					<div id="bienvenida_manual" class="tab-pane active" role="tabpanel"
						style="display:none;">
						<div role="main" class="col-md-9">
							<h2 id="tab2-020a" class="text-center">
								<b>¿Cómo realizar el proceso de solicitud de Negociación de
									Emergencia de Acuerdos de Reorganización en línea?</b>
							</h2>
							<a data-target=".picinfo-manual-00" data-toggle="modal"
								class="picinfo info-manual-00 h-200" type="button"></a>
							<div id="" class="panel-pic pic-list pic-manual-01">
								<h4 id="tab2-021">
									<b>1. Aliste los documentos requeridos</b>
								</h4>
								<div class="row-btn">
									<a onclick="descargaPlantilla(11)" class="btn btn-primary">
										<i class="fa fa-download"></i>
										Descargar plantillas
									</a>
								</div>
								<ul>
									<li>Memoria Explicativa de las causas de insolvencia.</li>
									<li>Estados Financieros y Notas.</li>
									<li>Estados Financieros (ESF, ERI, EFE, ORI, ECP) - Mes
										anterior.
									</li>
									<li>Notas a Estados Financieros (ESF, ERI, EFE, ORI, ECP) -
										Mes
										anterior.</li>
									<li> Estados Financieros (ESF, ERI, EFE, ORI, ECP) - últimos
										tres años (archivos separados por año).</li>
									<li>Notas a Estados Financieros (ESF, ERI, EFE, ORI, ECP) -
										últimos tres años (archivos separados por año).</li>
									<li>Inventario de activos. </li>
									<li>Relación de pasivos.</li>
									<li>Plan de Negocios de Reorganización.</li>
									<li>Flujo de caja proyectado.</li>
									<li>Proyectos de calificación y graduación de créditos y
										derechos de voto.</li>
									<li>Avalúo de los bienes en garantía (Opcional).</li>
									<li>Certificados de Garantía Mobiliaria (Si aplica).</li>
									<li>Cálculo actuarial de pasivos pensionales (Opcional).</li>
									<li>Prueba de capacidad del Representante Legal para tramitar
										la solicitud, en el evento en que requiera alguna
										autorización
										para el efecto.</li>
									<li>Certificación de no estar incurso en causal de disolución.</li>
									<li>Certificación de llevar la contabilidad regular.</li>
									<li>Certificación de existencia o inexistencia de Pasivos
										Pensionales a Cargo del deudor.</li>
									<li>Certificación de pasivos por retenciones obligatorias con
										el fisco, descuentos a trabajadores y aportes al Sistema de
										Seguridad Social.</li>
									<li>Poder al abogado para realizar la solicitud (Opcional).</li>
								</ul>
								<a data-target=".picinfo-manual-01" data-toggle="modal"
									class="picinfo info-manual-01 h-200" type="button"></a>
							</div>
							<div class="panel-pic pic-list pic-manual-02">
								<h4 id="tab2-022">
									<b>2. Diligenciamiento de la solicitud</b>
								</h4>
								<div role="alert" class="alert alert-warning">Para salvaguardar la seguridad de
									su
									información personal y la de la sociedad, el aplicativo
									permite
									un tiempo máximo de inactividad de 4 horas.
									Transcurrido este
									tiempo, el formulario se cerrará
									automáticamente y usted
									deberá
									iniciar el proceso nuevamente.</div>
								<ol>
									<li>Ingresar información general de la sociedad.</li>
									<li>Seleccionar Representante Legal, Contador, Revisor Fiscal
										(si aplica) y Apoderado (opcional) de las listas desplegables
										que generará el sistema.</li>
									<li>Seleccionar situación presentada: Cesación de Pagos o
										Incapacidad de Pago inminente.</li>
									<li>Indicar Sí/No la sociedad tiene pasivos por concepto de
										retenciones de carácter obligatorio a favor de autoridades
										fiscales, descuentos efectuados a trabajadores y aportes al
										sistema de seguridad social.</li>
									<li>
										Información Financiera con corte al último día del mes
										anterior. Ingresar información de:
										<ul>
											<li>Valor Total Activos.</li>
											<li>Valor Total Pasivos.</li>
											<li>Valor Total Patrimonio.</li>
											<li>Valor Total Ingresos Ordinarios.</li>
											<li>Valor Total Otros Ingresos.</li>
										</ul>
									</li>
									<li>Adjuntar los documentos que se indicaron en el paso 1. </li>
									<li>Ingresar Token de seguridad generado en SignApp para
										certificar la información registrada en el formulario.</li>
									<li>Dar clic en Aceptar. </li>
									<li>El módulo le informará al usuario que la solicitud fue
										creada exitosamente y el número consecutivo del formulario.</li>
									<li>Finalmente, el sistema enviará un email de notificación a
										los asociados al deudor para la validación de las
										certificaciones.</li>
								</ol>
								<a data-target=".picinfo-manual-02" data-toggle="modal"
									class="picinfo info-manual-02 h-200" type="button"></a>
							</div>
							<div class="panel-pic pic-list pic-manual-03">
								<h4 id="tab2-023">
									<b>3. Certificaciones del Contador y Revisor fiscal</b>
								</h4>
								<ol>
									<li>Iniciar sesión en MI.</li>
									<li>Ir al menú de Servicios y dar clic en Revisiones.</li>
									<li>Seleccionar la solicitud que desea revisar.</li>
									<li>El sistema mostrará la información general de la
										solicitud.
									</li>
									<li>Al dar clic en la solicitud el usuario podrá ver el
										detalle
										de la solicitud de la misma.</li>
									<li>En la opción Archivos Adjuntos podrá ver los documentos
										cargados en la solicitud.
									</li>
									<li>El usuario dará clic en aprobar la solicitud, si la misma
										corresponde a la realidad.</li>
									<li>El sistema generará una ventana emergente para confirmar
										la
										aprobación de la solicitud e Ingresar el token de seguridad
										generado en SignApp.</li>
									<li>Finalmente, podrá dar clic en Aceptar.</li>
								</ol>
								<a data-target=".picinfo-manual-03" data-toggle="modal"
									class="picinfo info-manual-03 h-200" type="button"></a>
							</div>
							<div class="panel-pic pic-list0 pic-manual-04">
								<h4 id="tab2-024">
									<b>4. Certificación Representante Legal y envío de la
										solicitud</b>
								</h4>
								<ol>
									<li>Iniciar sesión en MI</li>
									<li>Ir al menú de Servicios y dar clic en Revisiones. </li>
									<li>Seleccionar la solicitud que desea revisar. </li>
									<li>El sistema mostrará la información general de la
										solicitud.
									</li>
									<li>Al dar clic en la solicitud el usuario podrá ver el
										detalle
										de la solicitud de la misma.</li>
									<li>En la opción Archivos Adjuntos podrá ver los documentos
										cargados en la solicitud.
									</li>
									<li>El usuario dará clic en aprobar la solicitud, si la misma
										corresponde a la realidad.</li>
									<li>El sistema generará una ventana emergente para confirmar
										la
										aprobación de la solicitud e Ingresar el token de seguridad
										generado en SignApp.</li>
									<li>Finalmente, podrá dar clic en Aceptar.</li>
								</ol>
								<a data-target=".picinfo-manual-04" data-toggle="modal"
									class="picinfo info-manual-04 h-200" type="button"></a>
							</div>
							<div class="row-btn">
								<a onclick="ocultar('bienvenida_manual');mostrar('msg_bienvenida');" class="btn btn-primary">
									Ocultar
								</a>
							</div>
						</div>
						<div role="complementary" class="col-md-3">
							<nav class="docs-sidebar">
								<ul class="nav docs-sidenav">
									<li class="active">
										<a href="#tab2-020a">¿Cómo realizar el proceso de solicitud de
											Negociación de Emergencia de Acuerdos de Reorganización en
											línea?</a>
									</li>
									<li class="">
										<a href="#tab2-021">1. Aliste los documentos requeridos</a>
									</li>
									<li class="">
										<a href="#tab2-022">2. Diligenciamiento de la solicitud</a>
									</li>
									<li class="">
										<a href="#tab2-023">3. Certificaciones del Contador y Revisor fiscal </a>
									</li>
									<li class="">
										<a href="#tab2-024">4. Certificación Representante Legal y envío de la
											solicitud
										</a>
									</li>
								</ul>
							</nav>
						</div>
					</div>

					<!-- xsl:choose>
						<xsl:when
							test="$acciones/Menu[direccion='carga_informacion/13.do']/autorizado='true' or count(//menusPreparador/MenuGrupoFormato/grupoFormato)>0">
							<texto key="TEXTO BIENVENIDA PREPARADOR" />
						</xsl:when>

						<xsl:when
							test="$acciones/Menu[direccion='revisiones/11.1.do']/autorizado='true'">
							<texto key="TEXTO BIENVENIDA REVISOR" />
						</xsl:when>

						<xsl:when
							test="$acciones/Menu[direccion='liberacion/15.1.do']/autorizado='true'">
							<texto key="TEXTO BIENVENIDA LIBERADOR" />
						</xsl:when>

						<xsl:when
							test="$acciones/Menu[direccion='administracion_carga/proceso/22.do']/autorizado='true'">
							<texto key="TEXTO BIENVENIDA ADMINISTRATIVO" />
						</xsl:when>

						<xsl:otherwise>
							<texto key="TEXTO BIENVENIDA" />
						</xsl:otherwise>

					</xsl:choose>

					<xsl:if test="//ERROR = 1">
						<nota>
							<texto key="NO AUTORIZADO" />
						</nota>
					</xsl:if> -->
				</contenido>

			</principal>



		</pagina>

	</xsl:template>

</xsl:stylesheet>
