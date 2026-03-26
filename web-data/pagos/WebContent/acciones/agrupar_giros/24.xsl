<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include
		href="context://stylesheets/templates/firma/firmarFormulario.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Agrupar Giros">

			<javascript>webdata/24.js</javascript>
			<stylesheet>24.css</stylesheet>

			<principal>
				<titulo>Agrupar Giros</titulo>
				<contenido>
				<div class="box-container">
					<div class="panel panel-default">
 	 					<div class="panel-heading">Giros</div>
					  
				  		<div class="panel-body">

							<div class=" form-horizontal">
								
								<div class="alert alert-danger" id="giros_no_datos" style="display:none">
									<i class="glyphicon glyphicon-remove-sign" aria-hidden="true"></i>
									<span class="sr-only">Error:</span>
									<p>
										No hay
										<texto key="CARGAS" />
										pendientes.
									</p>
								</div>
								
								
								<div id="area_cargando" style="display:none">
									
									<p><b>Por favor, espere un momento mientras se realiza la operación.</b></p>
										
									<div class="barra-progreso">
										<div class="barra-progreso-avance" id="progreso">
										</div>
									</div>
									
									<p id="tiempo_transcurrido"></p>
									
								</div>
								
			
								<div id="giros_contenido">
								
									<div class="alert  alert-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										<span class="sr-only">Info:</span>
										<p>Debe seleccionar registros de la misma fecha y cuenta.</p>
									</div>
									
									<div id="cont_bancos">
									</div>
										
									<!-- FORMULARIO -->
									<form id="form_giros" name="form_giros" action="24.1.do" onsubmit="return false;">
										<input type="hidden" name="nombreArchivo" id="nombreArchivo" />
										<div id="div_comandos">
										</div>
									</form>
			
									<!-- SEGUN LA PARAMETRIZACION DEL SERVICIO SE UTILIZA FIRMA DIGITAL 
										O NO -->
									<xsl:choose>
										<xsl:when
											test="//obtenerTodosRegistrosConfiguracion/listado/RegistroConfiguracion[nombre_servicio='SALIDA BANCO' and etiqueta='USO_FIRMA_DIGITAL']/valor='S' ">
			
											<!-- COMPONENTE FIRMA DIGITAL -->
											<xsl:call-template name="firmarFormulario">
												<xsl:with-param name="id_componente_firma">
													firmaGenerarArchivoBanco
												</xsl:with-param>
												<xsl:with-param name="formularioParaFirmar">
													form_giros
												</xsl:with-param>
												<xsl:with-param name="nombreFaseProceso">
													GENERAR ARCHIVO BANCO
												</xsl:with-param>
												<xsl:with-param name="nombre_boton">
													Firmar y Crear Archivo
												</xsl:with-param>
												<xsl:with-param name="rutaBotonVolver">
													inicio/0.do
												</xsl:with-param>
												<xsl:with-param name="funcionJavaScript_validacion">
													validarFormulario()
												</xsl:with-param>
											</xsl:call-template>
			
			
										</xsl:when>
										<xsl:otherwise>
											<area_botones>
												<boton estilo="primary guardar" accion="enviar()" id="btn_guardar">Crear Archivo</boton>
												<boton estilo="danger" destino="inicio/0.do">Cancelar</boton>
											</area_botones>
			
										</xsl:otherwise>
			
									</xsl:choose>
			
			
								</div>
			
			
			
								<xsl:call-template name="plantillas" />
							</div>
						</div>
					</div>
					</div>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>

	<xsl:template name="plantillas">
		<div style="display:none">
			<div id="PLANTILLA_FILA">
				<div>
					<div class="encabezado" id="enc_[ 1 ]">
						<a name="linkBanco" id="linkbanco_[ 1 ]" onclick="mostrarOcultarCont('[ 1 ]')">[ 2 ]</a>
					</div>
					<div name="contBanco" id="contBanco_[ 1 ]" style="display:none"
						class="tabla_borde listado">
						<table class="table table-hover table-striped tb">
							<thead>
								<tr class="tabla_encabezado">
									<td class="w020">
										<input type="checkbox" onclick="seleccionTodas([ 1 ], this.checked)" id="check_[ 1 ]"/>
									</td>
									<td class="w090">Identificación</td>
									<td class="w090">Valor</td>
									<td class="w100">Banco Destino</td>
									<td class="w090">Cuenta Destino</td>
									<td class="w090">Fecha Trans.</td>
									<td class="w090">Estado</td>
									<td class="w090">Cuenta</td>
								</tr>
							</thead>
							<tbody id="registrosBanco_[ 1 ]">

							</tbody>
						</table>
						<div class="paginacion">
							<table class="tabla_paginacion" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="22px">
										<a onclick="paginacion_inicio('[ 1 ]')">
											<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero" />
										</a>
									</td>

									<td width="22px">
										<a onclick="paginacion_anterior('[ 1 ]')">
											<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior" />
										</a>
									</td>

									<td>
										<p id="texto_paginacion_[ 1 ]">Cargando ... </p>
									</td>

									<td width="22px">
										<a onclick="paginacion_siguiente('[ 1 ]')">
											<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente" />
										</a>
									</td>

									<td width="22px">
										<a onclick="paginacion_final('[ 1 ]')" >
											<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo" />
										</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>

			<table>
				<tbody id="PLANTILLA_REGISTRO">
					<tr class="tabla_fila" id="fila_[ 1 ]">
						<td>
							<input type="checkbox" name="gruposGiro:[[ 1 ]]" id="grupogiro_[ 1 ]"
								value="[ 1 ]" onclick="seleccionRegistro([ 1 ], this.checked)" class="check_registro"/>
							<input type="hidden" name="cuentas_[ 12 ]" id="cuentas_[ 1 ]"
								value="[ 10 ]" />
							<input type="hidden" name="fechas_[ 12 ]" id="fechas_[ 1 ]"
								value="[ 11 ]" />
							<input type="hidden" id="grupogiro_[ 1 ]_banco"
								value="[ 12 ]" />
						</td>
						<td onclick="clickFila('[ 1 ]')">[ 3 ] </td>
						<td onclick="clickFila('[ 1 ]')">[ 4 ]</td>
						<td onclick="clickFila('[ 1 ]')">[ 5 ]</td>
						<td onclick="clickFila('[ 1 ]')">[ 6 ]</td>
						<td onclick="clickFila('[ 1 ]')">[ 7 ]</td>
						<td onclick="clickFila('[ 1 ]')">[ 8 ]</td>
						<td onclick="clickFila('[ 1 ]')">[ 9 ]</td>
					</tr>
				</tbody>
			</table>
			<div id="PLANTILLA_COMANDO">
				<div id="comando_[ 1 ]_[ 2 ]">
					<input type="hidden" id="comando_id_banco_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].id_banco" value="[ 1 ]"/>
					<input type="hidden" id="comando_id_grupo_giro_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].id_grupo_giro"  value="[ 2 ]"/>
					<input type="hidden" id="comando_orden_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].orden"  value="[ 3 ]"/>
					<input type="hidden" id="comando_agregar_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].agregar"  value="[ 4 ]"/>
				</div>
			</div>
		</div>
	</xsl:template>

</xsl:stylesheet>
