<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<xsl:param name="id_administrador"/>
		<xsl:variable name="filtro_cliente" select="'S'" />
		<xsl:variable name="filtro_negocio" select="'S'" />
		<xsl:variable name="formatos" select="//obtenerFormatosPorTipo/Listado/Formato"/>
		<xsl:variable name="url_volver" select="'historico/solicitudNEAR.do'"/>
		
        <pagina titulo="Histórico">
        				
			<xsl:variable name="cliente">
				<xsl:choose>
					<xsl:when test="string-length($filtro_cliente) > 0">N</xsl:when>
					<xsl:otherwise>S</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
			
			<javascript>historico/solicitudNEAR.js</javascript>
			<stylesheet>historico/solicitudNEAR.css</stylesheet>
			<stylesheet>reportePersonalizado/reporte.css</stylesheet>
				
			
            
            <principal>
            
				
                <titulo>Histórico</titulo>
                <contenido>
                	<estados_javascript cliente="{$cliente}"/>
					
					<formulario destino="historico/28.1.do" id="form_detallecarga">
						<variable id="id_carga_detalle" valor="" />
						<variable id="id_formato_salida_detalle" valor="" />
						<variable id="url_volver" valor="{$url_volver}"/>
					</formulario>
					
                	<div id="contenedorContenido">
                	
	                	<div id="contenedorFiltro">
	                	
		                	<div id="botonFiltro" onclick="toggleFilter()" >
		                		Ver Filtros <i id="chevronFiltro" class="fa fa-chevron-down" style="margin-left: 4px;"></i>
		                	</div>
		                	
		                	<div id="areaFiltro" class="hide">
		                		<div class="flex-item">
		                			<div class="flex-registro">ID Solicitud</div>
		                			<div class="flex-valor">
		                				<input placeholder="ID Solicitud" type="text" 
		                				  class="input-default form-input"
		                				  style="font-family: FontAwesome, Poppins !important;"
		                				  id="id_solicitud" />
		                			</div>
		                		</div>
		                		<div class="flex-item">
		                			<div class="flex-registro">Proceso</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="proceso">
		             						<option value="" selected="true">-- Seleccione --</option>
		             						
		           						</select>
		                			</div>
		                		</div>
		                		<div class="flex-item">
		                			<div class="flex-registro"># Radicación</div>
		                			<div class="flex-valor">
		                				<input placeholder="&#xF002; Número de radicación" type="text" 
		                				  class="input-default form-input"
		                				  style="font-family: FontAwesome, Poppins !important;"
		                				  id="numero_radicado" />
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Estado de la solicitud</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="id_estado">
		             						<option value="0" selected="true">-- Seleccione --</option>
		             						<xsl:for-each select="//listaEstados/ValorLista">
		             							<option value="{nombre}"><xsl:value-of select="nombre"/></option>
		             						</xsl:for-each>
		           						</select>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Fecha cargue documentos</div>
		                			<div class="flex-valor">
		                				<cajafecha id="fecha_solicitud" valor="" class="flex-fecha"/>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Solicitante</div>
		                			<div class="flex-valor">
		                					<select onmouseover="" onclick="" onchange=""
												autocomplete="off" class="select-default form-input" id="solicitante"
												name="solicitante" style="visibility: visible;">
		                                        <option value="">-- Seleccione --</option>
		                                    </select>
<!-- 		                				<input placeholder="&#xF002; Buscar solicitante" type="text"  -->
<!-- 		                				  class="input-default form-input" -->
<!-- 		                				  style="font-family: FontAwesome, Poppins !important;" -->
<!-- 		                				  id="solicitante" /> -->
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Tipo de solicitante</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="tipo_solicitante">
		             						<option value="0" selected="true">-- Seleccione --</option>
		             						<xsl:for-each select="//listaTipoSolicitante/ValorLista">
		             							<xsl:sort select="id" />
		             							<option value="{nombre}"><xsl:value-of select="id"/> - <xsl:value-of select="nombre"/></option>
		             						</xsl:for-each>
		           						</select>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Fecha de Inicio Radicación</div>
		                			<div class="flex-valor">
		                				<cajafecha id="fecha_radicacion" valor="" class="flex-fecha"/>
		                			</div>
		                		</div>
		                		<div class="flex-item">
		                			<div class="flex-registro">Fecha de Fin Radicación</div>
		                			<div class="flex-valor">
		                				<cajafecha id="fecha_radicacion_fin" valor="" class="flex-fecha"/>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">NIT / Identificación</div>
		                			<div class="flex-valor">
		                				<input placeholder="&#xF2C2; Buscar por identificación" type="text" 
		                				  class="input-default form-input"
		                				  style="font-family: FontAwesome, Poppins !important;" 
		                				  id="identificacion"/>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Categoria</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="categoria">
		             						<option value="0" selected="true">-- Seleccione --</option>
		             						<xsl:for-each select="//listaCategoria/ValorLista">
		             							<xsl:sort select="nombre" />
		             							<option value="{nombre}"><xsl:value-of select="nombre"/></option>
		             						</xsl:for-each>
		           						</select>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Dependencia</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="intendencia">
		             						<option value="0" selected="true">-- Seleccione --</option>
<!-- 		             						<xsl:for-each select="//listaIntendencias/ValorLista"> -->
<!-- 		             							<xsl:sort select="nombre" /> -->
<!-- 		             							<option value="{nombre}"><xsl:value-of select="nombre"/></option> -->
<!-- 		             						</xsl:for-each> -->
		           						</select>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Trámite</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="tramite">
		             						<option value="" selected="true">-- Seleccione --</option>
		             						
		           						</select>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">Responsable Instancia</div>
		                			<div class="flex-valor">
		                				<select class="select-default form-input placeholder" 
		                						style="visibility: visible;"
		                						id="responsables">
		             						<option value="" selected="true">-- Seleccione --</option>
		             						
		           						</select>
		                			</div>
		                		</div>
		                		
		                		<div class="flex-item">
		                			<div class="flex-registro">&#160;</div>
		                			<div class="flex-valor">
		                				<div class="flex-boton" onclick="PAGINA_ACTUAL = 1; contarSolicitudes();"><i class="fa fa-search" aria-hidden="true" style="margin-right: 5px; color: #fff !important;"></i> Buscar</div>
		                			</div>
		                		</div>
		                		
		                	</div>
		                	
	                	</div>
	                
	                	<xsl:for-each select="//obtenerRolesAdministrativo/Listado/Rol"><xsl:value-of select="nombre_rol" /></xsl:for-each>
	                	<div id="area_resultado">
	                		<div style="overflow: auto;
	                		            max-height: 488px;
	                		            border-radius: 8px;
	                		            border: 1px solid #c8ced3 !important;">
	                			<table class="table table-hover table-striped">
	                			
	                			<tbody id="idTbody">
	                				<tr class="tabla_encabezado" id="historialHeader">
	                				</tr>
	                			</tbody>
	                			
	                		</table>
	                		</div>
	                		<div id="area_paginacion">
	                			<div class="botonl botones" onclick="primero();">
	                				<i class="fa fa-step-backward" style="line-height: inherit;"></i>
	                			</div>
	                			<div class="botonc botones botoncl" onclick="anterior();">
	                				<i class="fa fa-play fa-rotate-180" style="line-height: inherit;"></i>
	                			</div>
	                			<div class="num_pag r_pag" id="curr_pag">
	                				1 a 20
	                			</div>
	                			<div id="div_pag">de</div>
	                			<div class="num_pag l_pag" id="last_pag">
	                				20
	                			</div>
	                			<div class="botonc botones botoncr" onclick="siguiente();">
	                				<i class="fa fa-play" style="line-height: inherit;"></i>
	                			</div>
	                			<div class="botonr botones" onclick="ultimo();">
	                				<i class="fa fa-step-forward" style="line-height: inherit;"></i>
	                			</div>
	                		</div>
	                		<div id="area_botones">
	                			<div class="boton_foot" onclick="descargar_reporte()">
	                				Descargar Reporte
	                			</div>
	                		</div>
	                		<xsl:call-template name="plantillas"/>
	                	</div>
	
		                <parrafo id="div_no_resultados" estilo="info">
							<i class="fa fa-info-circle" aria-hidden="true"></i> No se encontraron resultados.
						</parrafo>
	
	                	<div style="display:none">
		                	<table>
								<tbody id="PLANTILLA_REGISTRO">
									<tr class="registro">
										<td style="width: 120px !important;">[ 21 ]</td>
										<td style="width: 120px !important;">[ 1 ]</td>
										<td style="width: 120px !important;">[ 26 ]</td>
										<td style="width: 120px !important;">[ 25 ]</td>
										<td style="width: 120px !important;">[ 24 ]</td>
										<td style="width: 120px !important;">[ 2 ]</td>
										<td style="width: 120px !important;">[ 3 ]</td>
										<td style="width: 120px !important;">[ 4 ]</td>
										<td style="width: 120px !important;">[ 5 ]</td>
										<td style="width: 120px !important;">[ 7 ]</td>
										<td style="width: 120px !important;">[ 8 ]</td>
										<td style="width: 120px !important;">[ 9 ]</td>
										<td style="width: 120px !important;">[ 10 ]</td>
										<td style="width: 120px !important;">[ 11 ]</td>
										<td style="width: 120px !important;">[ 12 ]</td>
										<td style="width: 120px !important;">[ 13 ]</td>
										<td style="width: 120px !important;">[ 14 ]</td>
										<td style="width: 120px !important;">[ 15 ]</td>
										<td style="width: 120px !important;">[ 16 ]</td>
										<td style="width: 120px !important;">[ 17 ]</td>
										<td style="width: 120px !important;">[ 18 ]</td>
										<td style="width: 120px !important;">[ 19 ]</td>
										<td style="width: 120px !important;">[ 20 ]</td>
										<td style="width: 120px !important;">[ 23 ]</td>
										<td style="width: 120px !important;">[ 22 ]</td>
										<td>
											<area_botones>
												<boton estilo="primary sm" accion="verDetalleHistoria('[ 21 ]')"><i class="fa fa-history" aria-hidden="true"></i>&#160;Historial</boton>
												<boton estilo="primary" accion="verDetalleCarga('[ 21 ]')"><i class="fa fa-eye" aria-hidden="true"></i>&#160;Detalle</boton>
											</area_botones>
										</td>
									</tr>
								</tbody>
							</table>
	                	</div>
                
                	</div>
                
                </contenido>
            </principal>
			<xsl:call-template name="VENTANA_BUSCARCARGAS"/>
			
        </pagina>
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
		
			<ventana id="vn_responsableCarga" icono="advertencia">
				<titulo>Re-asignación de Responsable de instancia</titulo>
				<contenido>
							<div id="mensaje_no_responsable" style="display: none" class="alert  alert-danger">
								<i class="fa fa-info-circle" aria-hidden="true"></i>
								<span class="sr-only">Info:</span>
								La instancia actual no tiene responsable asignado
							</div>
							<input type="hidden" id="vn_id_carga" />
							<input type="hidden" id="vn_id_instancia" />
							<div id="area_asignarResponsableCarga">								
								<div class="flex-item form-group form-group-sm" style="display: flex;">
									<div class="flex-registro control-label col-md-5 col-sm-5">Responsable Actual: </div>
		                			<div class="flex-valor control-label-value col-md-7 col-sm-6">
		                				<input placeholder="Responsable Actual" type="text" 
		                				  class="input-default form-input disabled"
		                				  style="font-family: FontAwesome, Poppins !important; border: 0 !important;"
		                				  id="vn_responsable_actual" />
		                			</div>
								</div>
								<div class="flex-item form-group form-group-sm" style="display: flex;">
									<div class="flex-registro control-label col-md-5 col-sm-5">Nuevo Responsable: </div>
		                			<div class="flex-valor control-label-value col-md-7 col-sm-6">
		                				<select class="select-default form-input placeholder" style="visibility: visible;" id="lista_responsables">
			           						<option value="" selected="true"> Seleccione </option>
			        					</select>
				        			</div>
								</div>
							</div>
					<div class="row-btn center" id="area_botones" style="display: flex; justify-content: center;">
						<boton id="vn_btn_guardar" estilo="btn-primary" accion="guardarResponsableCarga()">Guardar</boton>
						<boton estilo="volver" accion="cerrarVentanaResponsableCarga()">Cerrar</boton>
					</div>
				</contenido>
			</ventana>
	</xsl:template>

</xsl:stylesheet>
