<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://stylesheets/templates/visualizadorCargaProcesoAdmin.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>


	<xsl:template name="LISTARCARGASPENDIENTES">
		<javascript>templates/ventanaBandejaEntrada.js</javascript>
		<stylesheet>templates/ventanaBandejaEntrada.css</stylesheet>
	
		<formulario id="form_orden_filtro" destino="administracion_carga/proceso/22.1.do">
			<variable id="orden" valor=""/>
			<variable id="tipo_orden" valor=""/>
			<variable id="filtro_tipo_carga" valor=""/>
			<variable id="filtro_instancia_carga" valor=""/>
			<variable id="filtro_semaforo" valor=""/>
			<variable id="filtro_cliente" valor="{//filtro_cliente}"/>
			<variable id="filtro_orden_activo" valor="S"/>
		</formulario>
		
		
		<ocultable visible="false" textovisible="ocultar filtros" textooculto="ver filtros" >
		<div id="div_ordenamiento_filtro">		
		
			<bloque-contenido>
			<titulo>Filtros </titulo>
													
			<contenido>
				<div class="panel-col  form-horizontal">
					<parrafo estilo="info">
						<i class="fa fa-info-circle" aria-hidden="true"></i>
						Puede filtrar las <texto key="CARGAS" /> por cada uno de los critérios que se listan a continuación
					</parrafo>
					
					<div class="col-sm-5">	
						<!-- INSTANCIA DEL PROCESO-->	
				 		<registro>
							<item>Estado de Solicitud: </item>
							<valor>
								<cajatextoselector class="" id="select_filtro_instancia_carga" valor="{//filtro_instancia_carga}">
									<opcion valor="" texto="--Todos--" />
									<xsl:for-each select="//instanciasPorAdministrativo/Instancia">
										<opcion valor="{id_instancia}" texto="{nombre}" />		
									</xsl:for-each>
								</cajatextoselector>
							</valor>
				 		</registro>
					</div>
					
					<div class="col-sm-5">
						<registro >
							<item>Fecha de <texto key="CARGA"/>: </item>
							<valor>
								<input type="text" class="form-control date-pick" id="fecha_carga" />
							</valor>
						</registro>
					</div>
					
					<div class="col-sm-5">
						<registro >
							<item>Fecha de <texto key="Liberación"/>: </item>
							<valor>
								<input type="text" class="form-control date-pick" id="fecha_liberacion" />
							</valor>
						</registro>
					</div>
					
					<xsl:for-each select="//camposFiltroBandejaEntrada/FormatoCampo">
						<xsl:variable name="id_campo" select="id_campo"/>
						<xsl:variable name="Campo" select="//formatoCamposCompletos/Campo[id_campo=$id_campo]"/>
						<xsl:variable name="id_lista_valores" select="$Campo/id_lista_valores"/>
						<div class="col-sm-5">
							<registro >
								<item><xsl:value-of select="titulo" />: </item>
								<valor>
									<xsl:choose>
										<xsl:when test="string-length($Campo/id_lista_valores)>0">
											<select id="filtro_{id_formato_campo}" class="form-control">
												<option value="">--Todos--</option>
												<xsl:for-each select="//formatoCamposCompletos/HashMap/*[@name=$id_lista_valores]/ValorLista">
													<option value="{id}">
														<xsl:value-of select="nombre" />
													</option>
												</xsl:for-each>
											</select>
										</xsl:when>
										<xsl:when test="$Campo/id_tipocampo = 2">
											<div class="col-sm-5">
												<registro>
													<item>
														<input type="radio" id="filtro_{id_formato_campo}" name="filtro_boolean_{id_formato_campo}" value="S"/>
													</item>
													<valor>
														<div id="label_booleano_si">Si</div>
													</valor>
												</registro>
											</div>
											<div class="col-sm-5">
												<registro>
													<item>
														<input type="radio" id="filtro_{id_formato_campo}" name="filtro_boolean_{id_formato_campo}" value="N"/>
													</item>
													<valor>
														<div id="label_booleano_no">No</div>
													</valor>
												</registro>
											</div>
										</xsl:when>
										<xsl:when test="$Campo/id_tipocampo = 4">
											<input type="text" class="form-control date-pick" id="filtro_{id_formato_campo}" />
										</xsl:when>
										<xsl:otherwise>
											<input type="text" class="form-control" id="filtro_{id_formato_campo}" />
										</xsl:otherwise>
									</xsl:choose>
									<variable id="Filtro:{id_formato_campo}.id_formato_campo" class="filtroBandeja" valor="{id_formato_campo}"/>
								</valor>
							</registro>
						</div>
					</xsl:for-each>
					<area_botones>
						<boton estilo="primary aceptar" accion="buscar_cargas()"><i class="fa fa-search" aria-hidden="true"></i> Buscar</boton>
					</area_botones>
				</div>
			</contenido>
			</bloque-contenido>
	
		</div>
		</ocultable>		
						
		<!-- LISTADO DE CARGAS-->	
		
		<bloque-contenido>
		<titulo><texto key="CARGAS"/> asociadas al proceso </titulo>
			<contenido>
				<div class="panel-col  form-horizontal">
					<formulario id="form_siguiente" destino="administracion_carga/proceso/22.2.do">
						<variable id="id_carga" valor=""/>
						<variable id="id_instancia" valor=""/>
					</formulario>
			 		
			 		<div id="mensaje_sin_cargas" class="alert alert-danger" style="display:none">
			 			<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> No hay cargas encontradas
			 		</div>

					<div id="NOTA_CARGA_DETALLE">
						<nota>
							Cargando Información... <br/> Espere un momento por favor.
						</nota>
					</div>

					<div id="div_resultados" style="display:none">
						<div class="tabla_borde table-responsive" style="overflow: auto;
                		            max-height: 488px;
                		            border-radius: 8px;
                		            border: 1px solid #c8ced3 !important;" id="table_resultados">
			
							<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
								<thead class="tabla_encabezado">
									<tr>
										<th>ID Solicitud</th>
										<th class="hidden-xs">Fecha <texto key="CARGA"/></th>
										<th class="hidden-xs">Fecha <texto key="Liberación"/></th>
										<xsl:for-each select="//camposBandejaEntrada/FormatoCampo">
												<th class="hidden-xs"><xsl:value-of select="titulo" /></th>
										</xsl:for-each>
										<th class="hidden-xs">Estado de Solicitud</th>
										<th class="hidden-xs">Revisión</th>
									</tr>
								</thead>
								<tbody id="lista_cargas">
								
								</tbody>
							</table>
			
						</div>
						
						<xsl:call-template name="paginacion" />
					</div>
		 		</div>
			 		
			</contenido>
		</bloque-contenido>
	
		<xsl:call-template name="plantillasBandeja"/>
		<xsl:call-template name="VENTANA_BUSCARCARGAS"/>
	
	</xsl:template>
	
	<xsl:template name="plantillasBandeja">
		<div style="display:none">
		
			<!-- Plantilla para mostrar cargas -->
			<table>
				<tbody id="PLANTILLA_FILA_CARGA">
					<tr name="[ 5 ]" class="tabla_fila"  onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
						<td class="hidden-xs">[ 1 ]</td>
						<td class="hidden-xs">[ 2 ]</td>
						<td class="hidden-xs">[ 3 ]</td>
						<xsl:for-each select="//camposBandejaEntrada/FormatoCampo">
							<xsl:variable name="index" select="position() + 6"/>
							<td class="hidden-xs">[ <xsl:value-of select="$index"/> ]</td>
						</xsl:for-each>
						<td class="hidden-xs">[ 4 ]</td>
						<td>
							<area_botones>
								<boton estilo="primary" accion="verDetalleCarga('[ 1 ]', '[ 6 ]')"><i class="fa fa-eye" aria-hidden="true"></i>&#160;Revisar</boton>
								<boton estilo="primary sm" accion="verDetalleHistoria('[ 1 ]')"><i class="fa fa-history" aria-hidden="true"></i>&#160;Historial</boton>
							</area_botones>			
						</td>
					</tr>
				</tbody>
			</table>
			<!-- Plantilla para mostrar historial de las instancias -->	
			<table>
				<tbody id="PLANTILLA_HIST_INSTANCIAS">
					<tr name="[ 5 ]"  class="tabla_fila"  onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
						<td>[ 1 ]</td>
						<td>[ 2 ]</td>
						<td>[ 3 ]</td>
						<xsl:if test="normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true'">
							<td>[ 4 ]</td>
						</xsl:if>
					</tr>
				</tbody>
			</table>
		</div>
	</xsl:template>

	<xsl:template name="paginacion">
		<div class="paginacion">
			<table class="tabla_paginacion" border="0" cellspacing="0"
				cellpadding="0" width="300px">
				<tr>
					<td width="22px">
						<a id="pag_primero" onclick="ir_primero()">
							<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero" />
						</a>
					</td>
					<td width="22px">
						<a id="pag_atras" onclick="ir_atras()">
							<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior" />
						</a>
					</td>
					<td>
						<p id="pag_info_pags">
							<span id="pag_desde"></span>
							a
							<span id="pag_hasta"></span>
							de
							<span id="pag_total"></span>
						</p>
						<p id="pag_info_carga">
							<span>Cargando datos ...</span>
						</p>
					</td>
					<td width="22px">
						<a id="pag_siguiente" onclick="ir_siguiente()">
							<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente" />
						</a>
					</td>
					<td width="22px">
						<a id="pag_ultimo" onclick="ir_ultimo()">
							<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo" />
						</a>
					</td>
				</tr>
			</table>
		</div>
	
		<input type="hidden" id="cantidad_pagina" value="{//OSM-SESSION/CONFIGURACION/TAMANO_PAGINA}"/>
		
	</xsl:template>

		<xsl:template name="VENTANA_BUSCARCARGAS">
		<ventana id="vn_historialcarga" icono="confirmacion">
			<titulo><texto key="HISTORIAL DE LA CARGA" /></titulo>
			<contenido>
			
				<div class="tabla_borde" id="div_hist_instancias" style="display:none">

					<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
						<thead class="tabla_encabezado">
							<tr>
								<td>Instancia</td>
								<td>Persona</td>
								<td>Fecha Llegada</td>
								<td>Fecha Salida</td>
							</tr>
						</thead>
						<tbody id="cont_hist_instancias">
						</tbody>
					</table>
				</div>
							
				<div class="tabla_borde" id="div_hist_cliente" style="display:none">

					<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
						<thead class="tabla_encabezado">
							<tr>
								<td>Persona</td>
								<td>Estado</td>
							</tr>
						</thead>
						<tbody id="cont_hist_cliente">
						</tbody>
					</table>
				</div>
				
				<area_botones>
					<boton estilo="primary aceptar" accion="cerrarVentanaHistorial()">
						Cerrar
					</boton>
				</area_botones>
			</contenido>
		</ventana>
	</xsl:template>

</xsl:stylesheet>
