<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="archivos_adjuntos">

		<xsl:param name="permiteAdjuntar" />
		<xsl:param name="permiteVer" />
		<xsl:param name="permiteGestionar" />
		<xsl:param name="id_instancia" />
		<xsl:param name="permiteEliminar" />
		<xsl:param name="infoAdjuntos" />
		<xsl:param name="extensionAdjuntos" />
		<xsl:param name="limiteAdjuntos" />
		<xsl:param name="tituloAdjuntos" />
		<xsl:param name="ayudaAdjuntos" />
		<xsl:param name="solicitud_inicial" />

		<javascript>archivos_adjuntos/archivos_adjuntos.js</javascript>
		<javascript>archivos_adjuntos/s3_archivos_adjuntos.js</javascript>
		<stylesheet>archivos_adjuntos.css</stylesheet>

		<input type="hidden" id="id_carga_adj" value="{//id_carga_seleccionada}" />
		<input type="hidden" id="limite_adjuntos" value="{$limiteAdjuntos}" />
		<input type="hidden" id="extensionesValidas" value="{$extensionAdjuntos}" />
	
		<xsl:choose>
			<xsl:when test="$tituloAdjuntos">
				<div class="tb-text">
				   <div class="bloque_titulo_adjuntos">
						<h3><xsl:value-of select="$tituloAdjuntos"></xsl:value-of></h3>
						<div style="position:relative">
							<a class="ico_ayuda ico_ayuda_seccion" id="seccion_adjuntos.ico_ayuda" onmouseover="ver_ayuda_campo('seccion_adjuntos')" onmouseout="ocultar_ayuda_campo()" onfocus="ver_ayuda_campo('seccion_adjuntos')"></a>
							<div class="area_ayuda" id="seccion_adjuntos.area_ayuda" style="position: absolute; transform: translateX(28vw); border-radius: 8px; color: rgb(35, 40, 44); text-align: justify; width: 26vw; background-color: rgb(250, 251, 253); border-style: solid; border-color: rgb(200, 206, 211); border-width: 1px; padding: 30px; z-index: 1; right: 0px; top: 0px;">
								<div class="inner_area_ayuda">
									<div class="Normal-1">
										<xsl:value-of select="$ayudaAdjuntos" disable-output-escaping="yes"  />
									</div>
								</div>
							</div>
						</div>
					</div>
					<xsl:if test="$infoAdjuntos">
						<div class="Normal-1">
							<xsl:value-of select="$infoAdjuntos" disable-output-escaping="yes"/>
						</div>
					</xsl:if>
				</div>
				<div id="adjuntos-titulo-div" class="divider"></div>
			</xsl:when>
			<xsl:otherwise>
				<h3 class="bloque_contenido_titulo">Archivos Adjuntos</h3>
			</xsl:otherwise>
		</xsl:choose>
		<div id="archivos_adjuntos"	class="bloque_contenido_base  form-horizontal adj_gestion_{$permiteGestionar}">

			<input type="hidden" id="existen_archivos_adj" name="existen_archivos_adj"
				value="false" />

			<div id="contenido_archivos_adjuntos">

				<xsl:if test="$permiteVer = 'false'">
					<xsl:attribute name="style">display:none</xsl:attribute>
				</xsl:if>

				<div class="alert alert-info" role="alert">
					<i class="fa fa-exclamation-circle" aria-hidden="true"></i>
					No existen archivos adjuntos
				</div>

			</div>


			<xsl:if test="$permiteAdjuntar='true'">
				<xsl:choose>
					<xsl:when test="$solicitud_inicial">
						<div id="contenido_archivos_subidos" style="display:none" data-solicitud-inicial="true">
							<p class="text-center">Total documentos adicionales cargados: <span id="cantidad_adjuntos">0</span></p>
						</div>
					</xsl:when>
					<xsl:otherwise>
						<div id="contenido_archivos_subidos" style="display:none" data-solicitud-inicial="false">
							<p class="text-center">Total documentos cargados: <span id="cantidad_adjuntos">0</span></p>
						</div>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="$solicitud_inicial">
						<h3 style = "text-transform: capitalize;" class="bloque_contenido_titulo">Adjuntar Archivo Adicional</h3>
					</xsl:when>
					<xsl:otherwise>
						<h3 class="bloque_contenido_titulo">Adjuntar Archivo</h3>
					</xsl:otherwise>
				</xsl:choose>
				
<!-- 				<div class="row"> -->
<!-- 					<div class="col-sm-4 text-right">Consideraciones:</div> -->
<!-- 					<div class="col-sm-8"> -->
<!-- 						<p>Aquí podrá adjuntar los archivos requeridos para la revisión de -->
<!-- 							su -->
<!-- 							solicitud. Descargue y diligencie las plantillas de los -->
<!-- 							documentos -->
<!-- 							requeridos para continuar con el proceso de solicitud.</p> -->

<!-- 						<p> -->
<!-- 							Descargue la Plantillas de Documentos -->
<!-- 							<a hreft="https://google.com" target="_blank">AQUI</a> -->
<!-- 						</p> -->

<!-- 						<p>Nota : Los documentos marcados con (*) son obligatorios.</p> -->
<!-- 						<ul> -->
<!-- 							<li>Plan de Negocios de Reorganización</li> -->
<!-- 							<li> -->
<!-- 								Inventario de activos* -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								Estados Financieros Básicos - Mes anterior* -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								Estados Financieros Básicos - anuales* -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								Flujo de caja proyectado* -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								Avalúo de un bien en Garantía -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								Relación de pasivo (Artículo 32 Ley 1429 de 2010)* -->
<!-- 							</li> -->
<!-- 							<li> -->
<!-- 								Proyecto de calificación, graduación y derechos de voto de -->
<!-- 								crédito* -->
<!-- 							</li> -->
<!-- 							<li>Cálculo actuarial de pasivos pensionales</li> -->
<!-- 						</ul> -->

<!-- 					</div> -->
<!-- 				</div> -->
				<div class="adj_adjuntar_true" id="bloque_form_adjuntar_archivos" style="margin-top: 20px">
					<form action="{//CONTEXT_PATH}/adjunto.subirarchivo" method="post"
						enctype="multipart/form-data" onsubmit="return false;" id="form_adjuntar_archivos"
						name="form_adjuntar_archivos">

						<input type="hidden" id="id_carga" name="id_carga"
							value="{//id_carga_seleccionada}" />
						<input type="hidden" id="id_archivo_adjunto" name="id_archivo_adjunto" />
						<input type="hidden" id="id_instancia" name="id_instancia"
							value="{$id_instancia}" />

						<xsl:if test="count(//OSM-INIT-SESSION/Info/Usuario) = 0">
							<div class="row mtop" style="display:none">
								<div class="col-sm-4 text-right">Visibilidad:</div>
								<div class="col-sm-8">
									<select class="form-control" id="interno_nota_guardar"
										name="interno_nota_guardar">
										<option value="S">Unicamente usuarios autorizadores del
											proceso.</option>
										<option value="N">Todos los usuarios.</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4 text-right">Etiqueta:</div>
								<div class="col-sm-8">
									<select class="form-control" id="id_tipoarchivo" name="id_tipoarchivo">									
									</select>
								</div>
							</div>
						</xsl:if>
						<xsl:choose>
							<xsl:when test="$ayudaAdjuntos">
								<div class="row">
										<div class="col-sm-4 text-right">Archivo:</div>
										<div class="col-sm-8 file-uploader">
											<input type="file" value="" id="caja_archivo_adjunto"
												name="caja_archivo_adjunto" style="display: none;">
											</input>
											<span id="file-name" class="file-name input-file-text sc-start">Arrastre o seleccione un archivo</span>
											 <label for="caja_archivo_adjunto" class="file-label l-button input-file-gral input-file-button p-buttonenable sc-end" style="text-align: center !important; padding: 0 !important;">Adjuntar</label>
										</div>
									</div>
								</xsl:when>
								<xsl:otherwise>
									<div class="row">
										<div class="col-sm-4 text-right">Archivo:</div>
										<div class="col-sm-8">
											<input type="file" value="" id="caja_archivo_adjunto"
												name="caja_archivo_adjunto">
											</input>
										</div>
									</div>
								</xsl:otherwise>
							</xsl:choose>
						<div class="row" id="bloque_tipo_archivo" style="margin-bottom: 15px">
							<xsl:if test="$solicitud_inicial">
								<xsl:attribute name="style">display:none</xsl:attribute>
							</xsl:if>
							<input type="hidden" id="id_tipo_archivo" name="id_tipo_archivo" />
							<div class="col-sm-4 text-right">Tipo de archivo:</div>
							<div class="col-sm-8">
								<select class="select-default form-input" id="select_tipo_archivo" onchange="setTipoArchivo(this)">
									<option value=""> Seleccione </option>
								</select>
							</div>
							<div style="position:relative">
								<a class="ico_ayuda ico_ayuda_seccion" id="tipo_archivo.ico_ayuda" onmouseover="ver_ayuda_campo('tipo_archivo')" onmouseout="ocultar_ayuda_campo()" onfocus="ver_ayuda_campo('tipo_archivo')"></a>
								
								<div class="area_ayuda" id="tipo_archivo.area_ayuda">
									<div class="inner_area_ayuda">
										<h1>Tipo de archivo</h1>
										<pre>Tenga en cuenta que en la opción "Tipo de archivo" usted encontrará el listado de todos los documentos necesarios para tramitar la solicitud. Si usted requiere aportar algún documento adicional seleccione la opción "Otro documento"</pre>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-4 text-right">Descripción:</div>
							<div class="col-sm-8">
								<input style="width:80%" class="form-control"
									type="text" autocomplete="off" id="descripcion_archivo_adj"
									name="descripcion_archivo_adj"></input>
							</div>
						</div>

					</form>
				</div>

				<div id="loading_form_adjuntar_archivos" style="display:none">
					<nota>
						Enviando archivo al servidor...
					</nota>
				</div>

				<div class="row-btn">
					<a class="btn btn-sm btn-primary" id="btn_adjuntar_archivo"
						onclick="validarAdjuntarArchivo()">Adjuntar archivo</a>
				</div>

			</xsl:if>

		</div>

		<div id="PLANTILLA_ARCHIVOS_ADJUNTOS" style="display:none">

			<div class="seccion_archivos">
				<h4 class="bloque_contenido_titulo titulo_seccion">[ 2 ]</h4>
				<div id="lista_archivos_adjuntos_[ 1 ]">&#160;</div>
			</div>

		</div>

		<div id="PLANTILLA_ITEM_ARCHIVO_ADJ" style="display:none">
			<div id="item_archivo_adj_[ 1 ]" class="bloqueestilo adj_cliente_[ 11 ]">

<!-- 				<h3 class="bloque_contenido_titulo nuevo_adjunto">Nuevos Adjuntos</h3> -->

				<div class="bloque_archivo bloque_archivo_[ 10 ]">

					<div class="row">

						<div class="col-xs-8 bloqueestilo_imagen" id="icono_adj[ 1 ]">

							<h4 class="item_adjunto">
								[ 2 ] 
								<span class="label label-primary interno_[ 7 ]">
									interno
								</span>
							</h4>
								<pre>[ 4 ]</pre>
							<xsl:if test="string-length($limiteAdjuntos) = 0">
								<pre>[ 12 ]</pre>
								<pre>[ 13 ]</pre>
							</xsl:if>
						</div>

						<div class="col-xs-4 text-right">
							
							<xsl:if test="$permiteEliminar='true'">
								<i id="trash_item_archivo_adj_[ 1 ]"
									onclick="borrarAdjunto([ 1 ]);"		
									aria-hidden="true"
									class="fa fa-trash ic-trash"></i>
							</xsl:if>
							
							<xsl:if test="string-length($limiteAdjuntos) = 0">
								<p class="adj_instancia">[ 9 ]</p>
							
							<p>
								<b>[ 8 ]</b>
							</p>
							<p>[ 3 ]</p>
							
							
							<div style="display: none">
								<a onclick="$('#visualizador_file_[ 1 ]').show(); $('#view_adj_[ 1 ]').hide(); $('#hide_adj_[ 1 ]').show()" class="btn btn-sm btn-default" id="view_adj_[ 1 ]" style="border-radius: 3px">
									<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
									Ver Archivo
								</a>
								<a onclick="$('#visualizador_file_[ 1 ]').hide(); $('#hide_adj_[ 1 ]').hide(); $('#view_adj_[ 1 ]').show()" class="btn btn-sm btn-default" id="hide_adj_[ 1 ]" style="display: none; border-radius: 3px">
									<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
									Ocultar Archivo
								</a>
							</div>
							
							<a
								onclick="osm_go('{//CONTEXT_PATH}/adjunto.bajararchivo?id_archivo_adjunto=[ 1 ]',false);return false;"
								class="btn btn-sm btn-info" id="link_adj_[ 1 ]">
								<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
								Descargar
							</a>
							</xsl:if>

							
						</div>

					</div>
					
					<div id="visualizador_file_[ 1 ]" style="display: none">
						
					</div>

					<div id="div_alerta_[ 1 ]" class="alert alert-danger" style="display:none">
						<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
						<span class="sr-only">Error:</span>
						<p>El archivo ha sido modificado sin autorización, no se permite
							su descarga.</p>
					</div>

					<div class="adj_gestion adj_gestion_[ 10 ]">
						<area_botones>
							<button type="button" class="btn btn-sm btn-primary"
								onclick="aprobarAdjunto([ 1 ]);">
								<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
								Aprobar
							</button>

							<button type="button" class="btn btn-sm btn-primary"
								onclick="descartarAdjunto([ 1 ]);">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								Descartar
							</button>
						</area_botones>
					</div>

				</div>

			</div>
		</div>

		<script>

			listarArchivosAdjuntos(
			<xsl:value-of select="//id_carga_seleccionada" />
			);

		</script>




	</xsl:template>


</xsl:stylesheet>
