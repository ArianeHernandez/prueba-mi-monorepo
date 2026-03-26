<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl"/>

	<!-- TEMPLATE PRINCIPAL -->
	<xsl:template name="BUSCARCARGAS">
		<xsl:param name="filtro_cliente"/>		
		<xsl:param name="filtro_negocio"/>
		<xsl:param name="formatos"/>
		<xsl:param name="url_volver"/>
		<xsl:param name="id_administrador"/>

		<xsl:variable name="cliente">
			<xsl:choose>
				<xsl:when test="string-length($filtro_cliente) > 0">N</xsl:when>
				<xsl:otherwise>S</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>		
		
		<javascript>templates/buscar_cargas.js</javascript>
		
		<estados_javascript cliente="{$cliente}"/>
		
		<formulario destino="historico/28.1.do" id="form_detallecarga">
			<variable id="id_carga_detalle" valor="" />
			<variable id="id_formato_salida_detalle" valor="" />
			<variable id="url_volver" valor="{$url_volver}"/>
		</formulario>
						
		
		<ocultable visible="false" textooculto="ver filtros" textovisible="ocultar filtros">
		
		
			<div class="row">
			
			
				<div class="col-md-6">
				
					<xsl:if test="string-length($filtro_cliente) > 0">
						<xsl:call-template name="LISTADOCLIENTES">
							<xsl:with-param name="tipo_cliente">T</xsl:with-param>
						</xsl:call-template> 
					</xsl:if>
				
		
					<!-- Formato (Si es DN o PN)-->
					<xsl:choose>
						<xsl:when test="/OSM_PAGE/OSM-INIT-SESSION/Info/Usuario/tipo_persona = 'N'">
						
								<registro>
									<item>Formato:</item>
									<valor>
										<select id="buscadorCargas.id_formato" class="form-control">
											<option value="">--Todos--</option>
											<xsl:for-each select="$formatos">
												<option value="{id_formato}">
													<xsl:value-of select="nombre" />
												</option>
											</xsl:for-each>
										</select>
									</valor>
								</registro>
								
						</xsl:when>
					</xsl:choose>
					
					<!-- id_carga -->
					<registro>
						<item>ID Carga:</item>
						<valor>
							<input type="text" class="form-control" maxlenght="25" name="id_carga" id="id_carga" value="{id_carga}">
								<xsl:value-of select="id_carga" />
							</input>
						</valor>
					</registro>
					
					<!-- Negocio -->
					<xsl:if test="string-length($filtro_negocio) > 0">
						<registro>
							<item>Negocio:</item>
							<valor>
								<select id="select_negocio" class="form-control">
									<option value="">--Todos--</option>
									<xsl:for-each select="//obtenerListadoNegociosActivos/listado/Negocio">
										<option value="{id_negocio}">
											<xsl:value-of select="nombre" />
										</option>
									</xsl:for-each>
								</select>
							</valor>
						</registro>
					</xsl:if>
					
					<!-- Fechas carga-->
					<registro >
						<item>Fecha <texto key="CARGA"/> Entre:</item>
						<valor>
							<input type="text" class="form-control date-pick" id="fecha_inicial" style="width:48%" />
							<input type="text" class="form-control date-pick" id="fecha_final" style="width:48%"/>
						</valor>
					</registro>
					<!-- Fechas liberacion -->
					<registro >
						<item>Fecha <texto key="Liberación"/> Entre:</item>
						<valor>
							<input type="text" class="form-control date-pick" id="fecha_inicial_lib" style="width:48%" />
							<input type="text" class="form-control date-pick" id="fecha_final_lib" style="width:48%"/>
						</valor>
					</registro>
			
					<!-- Tipo Carga -->
					<registro>
						<item>Tipo <texto key="CARGA" />:</item>
						<valor>
							<select id="tipo_carga" class="form-control">
								<option value="">--Todos--</option>
								<option value="1">Individual</option>
								<option value="2">Masiva</option>
							</select>
						</valor>
					</registro>
					
					<!-- Proceso -->
					<registro>
						<item>Proceso <texto key="PROCESO" />:</item>
						<valor>
							<select id="proceso" class="form-control">
								<option value="">Seleccione una opción</option>
								<option value="1">Near</option>
								<option value="2">R. Abreviada</option>
							</select>
						</valor>
					</registro>
					
				</div>
				
				<div class="col-md-6">
					
					<!-- Orden -->
					<registro>
						<item>Ordenar Por:</item>
						<valor>
							<select id="ordenar" class="form-control">
								<option value="1">Fecha <texto key="CARGA"/></option>
								<option value="2">Estado</option>
								<option value="3">Tipo de Carga</option>
							</select>
						</valor>
					</registro>
					
				
					<!-- Estado -->
					<xsl:if test="count(//ESTADOS/estado) > 0">
						<registro>
							<item>Estados:</item>
							<valor>
								<div>
									<input class="cajachequeo " type="checkbox" onclick="selTodosEstados(this)" id="check_todos">Todos</input>
								</div>
								<xsl:for-each select="//ESTADOS/estado">
									<div>
										<input class="cajachequeo" type="checkbox" name="estados"
											value="{@id}"><estado key="{@id}" cliente="{$cliente}" /></input>
									</div>
								</xsl:for-each>
							</valor>
						</registro>
					</xsl:if>
					
				</div>
			
			</div>








			
			<area_botones>
				<boton estilo="primary aceptar" accion="buscarCargas()"><i class="fa fa-search" aria-hidden="true"></i> Buscar</boton>
			</area_botones>
			
		</ocultable>
		
		
		
		<!-- Resultados -->
		<div id="div_resultados" style="display:none">
			<div class="tabla_borde table-responsive">

				<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
					<thead class="tabla_encabezado">
						<tr>
							<td>ID</td>
<!-- 							<td class="hidden-xs">Nombre</td> -->
							<td class="hidden-xs">Deudor Solicitante</td>
							<td class="hidden-xs">N Proceso</td>
							<td class="hidden-xs">N Radicado</td>
							<td class="hidden-xs">Fecha de Carga de Información</td>
							<td class="hidden-xs">Fecha <texto key="Liberación"/></td>
							<td class="hidden-xs">Estado</td>
<!-- 							<td class="hidden-xs">Tipo <texto key="CARGA" /></td> -->
							<xsl:choose>
								<xsl:when test="string-length($filtro_cliente) > 0">
									<td class="hidden-xs">Cliente</td>
								</xsl:when>
								<xsl:otherwise>
									<td class="hidden-xs">Usuario Solicitante</td>
								</xsl:otherwise>
							</xsl:choose>
							<td class="hidden-xs">Proceso</td>
<!-- 							<td class="hidden-xs">Regs.</td> -->
<!-- 							<td class="hidden-xs">Valor</td> -->
							<td></td>
						</tr>
					</thead>
					<tbody id="lista_cargas">
					
					</tbody>
				</table>

			</div>
			
			<xsl:call-template name="paginacion" />
		</div>
		
		<parrafo id="div_no_resultados" estilo="info">
			<i class="fa fa-info-circle" aria-hidden="true"></i> No se encontraron resultados.
		</parrafo>
		
		<xsl:call-template name="plantillas"/>
		
		<input type="hidden" id="cantidad_pagina" value="{//OSM-SESSION/CONFIGURACION/TAMANO_PAGINA}"/>
		<input type="hidden" id="filtro_cliente" value="{$filtro_cliente}"/>
		<input type="hidden" id="id_administrador" value="{$id_administrador}"/>
		
		
		
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
	
	<xsl:template name="plantillas">
		<div style="display:none">
		
			<!-- Plantilla para mostrar cargas -->
			<table>
				<tbody id="PLANTILLA_FILA_CARGA">
					<tr name="[ 7 ]" class="tabla_fila"  onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
						<td>
							<h4>[ 1 ]</h4>
							<div class="visible-xs-block">
								<div>[ 3 ]</div>
								<br/>
								<div><b style="color:#005596">[ 4 ]</b></div>
							</div>
						</td>
						<td class="hidden-xs">[ 2 ]</td>
						<td class="hidden-xs">[ 14 ]</td>
						<td class="hidden-xs">[ 15 ]</td>
						<td class="hidden-xs">[ 3 ]</td>
						<td class="hidden-xs">[ 13 ]</td>
						<td class="hidden-xs estado_[ 12 ]">[ 4 ]<p>[ 11 ]</p></td>
<!-- 						<td class="hidden-xs">[ 5 ]</td> -->
						<td class="hidden-xs">[ 6 ]</td>
						<td class="hidden-xs">[ 8 ]</td>
<!-- 						<td class="hidden-xs">[ 9 ]</td> -->
<!-- 						<td class="rgh hidden-xs" style="color:red">[ 10 ]</td> -->
						<td>
							<div class="visible-xs-block">
								<div>[ 6 ]</div>
								<div><b>formato: </b>[ 8 ]</div>
								<div><b>registros: </b>[ 9 ]</div>
								<div class="rgh" style="color:red">[ 10 ]</div>
							</div>
							
							<area_botones>
								<boton estilo="primary sm" accion="verDetalleHistoria('[ 1 ]')"><i class="fa fa-history" aria-hidden="true"></i>&#160;Historial</boton>
								<boton estilo="primary" accion="verDetalleCarga('[ 1 ]')"><i class="fa fa-eye" aria-hidden="true"></i>&#160;Detalle</boton>
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
			<!-- Plantilla para mostrar historial de la carga en el Cliente -->
			<table>
				<tbody id="PLANTILLA_HIST_CLIENTES">
					<tr name="[ 4 ]"  class="tabla_fila"  onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
						<td>
							<div>[ 2 ]</div>
							<xsl:if test="normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true'">
								<div>[ 5 ]</div>
							</xsl:if>
						</td>
						<td>
							<div><b>[ 1 ]</b></div>
							<div>[ 3 ]</div>
						</td>
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
							<span>cargando datos ...</span>
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
		
	</xsl:template>

</xsl:stylesheet>