<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/firma/firmarFormulario.xsl"/>
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
	<xsl:include href="context://stylesheets/templates/CamposAdicionales.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Cargue Bancolombia SAP">

			<principal>
				<titulo>Cargue Bancolombia SAP</titulo>
				<contenido>
					
					<javascript>occired/bancolombia_1.js</javascript>
					
					<xsl:call-template name="plantillas"/>
					
					<input type="hidden" id="TAMANO_PAGINA" value="{//OSM-SESSION/CONFIGURACION/TAMANO_PAGINA}"/>
					<input type="hidden" id="TOTAL_REGISTROS" value="{//cargarRetirosInfo/registro/num_retiros}"/>
					
					<input type="hidden" id="IDENTIFICACION_CLIENTE" value="{//OSM-INIT-SESSION/Info/Usuario/identificacion}"/>
					
					<!-- Codigos de banco -->
					<xsl:for-each select="//obtenerBancos/Listado/HashMap">
						<input type="hidden" id="banco_{ID}" value="{C1221}"/>
					</xsl:for-each> 
					
					<!-- -->
					
					<xsl:choose>
						<xsl:when test="//cargarRetirosInfo/registro/num_retiros > 0">
						
							<bloque-pestanas>
								<pestana titulo="Cargue Bancolombia SAP">
									<xsl:call-template name="info_carga"/>
								</pestana>
								
								<pestana titulo="Detalle Información">
								
									<tabla id="tabla_detalle">
										<encabezado>
											<titulo>Nombre Beneficiario</titulo>
											<titulo>Doc. Beneficiario</titulo>
											<titulo>Banco Beneficiario</titulo>
											<titulo>Cuenta</titulo>
											<titulo>Tipo Cuenta</titulo>
											<titulo>Valor</titulo>
											<titulo>Observación</titulo>
										</encabezado>
										
									</tabla>
									
									<div class="paginacion">
										<table class="tabla_paginacion" border="0" cellspacing="0" cellpadding="0" width="200px">
											<tr>
												<td width="22px">
													<a onclick="paginacion_inicio()">
														<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero"/>
													</a>
												</td>
											
												<td width="22px">
													<a onclick="paginacion_anterior()">
														<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior"/>
													</a>
												</td>
											
												<td>
													<p id="texto pagina"> Pagina 1 de muchos</p>
												</td>
						
												<td width="22px">
													<a onclick="paginacion_siguiente()">
														<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente"/>
													</a>
												</td>
											
												<td width="22px">
													<a onclick="paginacion_final()">
														<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo"/>
													</a>
												</td>
											</tr>
										</table>
									</div>
									
									
								</pestana>
							
								<pestana titulo="Archivos Adjuntos">
					
									<!-- ARCHIVOS ADJUNTOS -->
									
									<xsl:call-template name="archivos_adjuntos">
										<xsl:with-param name="permiteAdjuntar">true</xsl:with-param>
									</xsl:call-template>
								
								</pestana>	
							</bloque-pestanas>
							
							<xsl:variable name="hay_listaDC" select="count(//obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo) &gt; 0"/>
	
							<form id="form_enviarcarga" action="bancolombia_2.do">
									<!-- INICIO INFORMACION DE LA TRANSACCION -->
									<input type="hidden" name="transaccion_bcol" value="{ID_CARGA}"/>
									<input type="hidden" name="numero_registros" value="{//cargarRetirosInfo/registro/num_retiros}"/>
									<input type="hidden" name="valor_total" value="{format-number(//cargarRetirosInfo/registro/valortotal, '###.##0,00', 'pesos')}"/>
									<input type="hidden" name="enviarBCOL" value="S"/>
									<input type="hidden" name="enviada_por" value="{//OSM-INIT-SESSION/Info/Persona/nombreCompleto}"/>
									<!-- FINAL  INFORMACION DE LA TRANSACCION -->
								
								
									<xsl:if test="$hay_listaDC">
										
										<bloque-contenido>
											<titulo>
												Información de liberación
											</titulo>
											<contenido>
												<xsl:call-template name="CAMPOS_ADICIONALES"/>
											</contenido>
										</bloque-contenido>
										
										<xsl:for-each select="obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo">
										<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_campo" value="{id_campo}" />
										<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_carga" value="{//ID_CARGA}" />
										<input type="hidden" name="listaValoresDinamicos:{id_campo}.valor" id="listaValoresDinamicos_{id_campo}_valor" value="" />
										</xsl:for-each>
									</xsl:if>
								
							</form>
							
							<xsl:if test="$hay_listaDC">
								<div style="display:none">
									<select autocomplete="off" id="select_negocios_libera" class="form-control">
											<option value="{//OSM-SESSION/CONFIGURACION/ID_NEGOCIO}">
													 <xsl:value-of select="//OSM-SESSION/CONFIGURACION/ID_NEGOCIO"/>
											</option>
									</select>
								</div>
							</xsl:if>
							
						</xsl:when>
						<xsl:otherwise>
							<alerta>
								No fue posible cargar el archivo, por favor validar la información.
							</alerta>
						</xsl:otherwise>
					</xsl:choose>
					<area_botones>
						
						<xsl:choose>
							<xsl:when test="//cargarRetirosInfo/registro/exitoso = 'true'">
									<xsl:choose>
										<xsl:when test="(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') or (//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
											<xsl:call-template name="firmarFormulario">
											<xsl:with-param name="id_componente_firma">firmaEnviarOccired1</xsl:with-param>
											<xsl:with-param name="formularioParaFirmar">form_enviarcarga</xsl:with-param>
											<xsl:with-param name="id_carga"><xsl:value-of select="//ID_CARGA"></xsl:value-of></xsl:with-param>
											<xsl:with-param name="nombreFaseProceso">PREPARACION MASIVA</xsl:with-param>
											<xsl:with-param name="nombre_boton">Firmar y Enviar BCOL</xsl:with-param>
											<xsl:with-param name="rutaBotonVolver">carga_informacion/bancos/bancolombia.do</xsl:with-param>
											</xsl:call-template>
										
										</xsl:when>
										
										<xsl:otherwise>
												<boton estilo="enviar" formulario="form_enviarcarga">Enviar</boton>
										</xsl:otherwise>
									
									</xsl:choose>
								
								<xsl:if test="not(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') and not(//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
									<boton estilo="danger" destino="carga_informacion/bancos/bancolombia.do">Cancelar</boton>
								</xsl:if>
							</xsl:when>
							<xsl:otherwise>
								<boton estilo="primary volver" destino="carga_informacion/bancos/bancolombia.do">Volver</boton>
							</xsl:otherwise>
							
						</xsl:choose>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>

	</xsl:template>

	<xsl:template name="info_carga">
		
		<nota>
			<b>Numero de registros: <h1><xsl:value-of select="//cargarRetirosInfo/registro/num_retiros"/></h1></b>
			<b>valor total: <h1><xsl:value-of select="format-number(//cargarRetirosInfo/registro/valortotal, '###.##0,00', 'pesos')"/></h1></b>
		</nota>
		
		<div ng-app="app" ng-controller="appController" >
		
			<div ng-init="evaluarMostrar('{//cargarRetirosInfo/registro/valortotal}' , '{//cargarRetirosInfo/registro/num_retiros}')">
				<div ng-if="duplicados > 0">
					<nota>Existen registros <b>Duplicados</b>.
						<button id="btn_enviar" class="btn btn-xs btn-primary" data-toggle="modal" data-target="#modal_solicitud">
							Ver Registros 
						</button>
					</nota>
				</div>
				
				<xsl:choose>
					<xsl:when test="//cargarRetirosInfo/registro/exitoso = 'true'">
						<nota>Para Finalizar el envio de la Información haga click en el boton de <b>Enviar</b>.</nota>
					</xsl:when>
					<xsl:otherwise>
						<alerta>
							Existen Inconsistencias con la informacion del archivo. Por favor verificar los detalles.
						</alerta>
						
						<xsl:if test="//cargarRetirosInfo/registro/codError = 'MCLIENTE'">
							<alerta>
								En el lote de pagos a enviar se encuentra(n) traslado(s) a cuentas propias. Para realizar esto, deberá hacerlo mediante una carta de traslado.
							</alerta>
						</xsl:if>
						
						<xsl:if test="//cargarRetirosInfo/registro/codError = 'VALORINCORRECTO'">
							<alerta>
								En el lote de pagos a enviar se encuentra(n) retiros sin valor. Es obligatorio que los retiros tengan un valor mayor a cero.
							</alerta>
						</xsl:if>
						
					</xsl:otherwise>
				</xsl:choose>
				
					<div class="modal fade" id="modal_solicitud" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="myModalLabel">Solicitud <span></span> </h4>
								</div>
								<div class="modal-body">
									<div class="tabla_borde table-responsive">							
									<table class="table table-hover table-striped">
								  	<thead>
									  	<tr>
									  		<th>Identificador <texto key="CARGA" /></th>
									  		<th>Nombre</th>
									  		<th>Fecha de Subida</th>
									  		<th>Estado</th>
									  		<th>Valor</th>
									  		<th>Numero de Registros</th>
									  	</tr>
								  	</thead>
								  	<tbody>
								  	<tr align="center" ng-repeat="l in listaDuplicados">
									  		<td nowrap="nowrap"> {{ l.id_carga }} </td>
									  		<td nowrap="nowrap"> {{ l.nombre }} </td>
									  		<td nowrap="nowrap"> {{ l.fecha_subida_string }} </td>
							  		        <td nowrap="nowrap" ng-if='l.estado === "V"'>En Revisión</td>
							  		        <td nowrap="nowrap" ng-if='l.estado === "L"'>En Liberación</td> 
							  		        <td nowrap="nowrap" ng-if='l.estado === "S"'>En Proceso</td> 
							  		        <td nowrap="nowrap" ng-if='l.estado === "C"'>Aprobado</td>  
									  		<td nowrap="nowrap"> {{ l.valor_total_bigdecimal | currency : ""}} </td>
									  		<td nowrap="nowrap"> {{ l.numero_registros_bigdecimal }} </td>
									  	</tr>
								  	</tbody>
								</table>
								</div>
								</div>
								<div class="modal-footer">									
									<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&#160; Cerrar</button>
								</div>
							</div>
							</div>
						</div>
				</div>
			</div>
		
	</xsl:template>
	
	
	<xsl:template name="plantillas">
		<div style="display:none">
			<table>
				<tbody id="PLANTILLA_REGISTRO">
					<tr class="tabla_fila">
						<td class="w020">[ 1 ]</td>
						<td class="w090">[ 2 ] </td>
						<td class="w100">[ 3 ]</td>
						<td class="w090">[ 4 ]</td>
						<td class="w090">[ 5 ]</td>
						<td class="w090">[ 6 ]</td>
						<td class="w090">[ 7 ]</td>
					</tr>
				</tbody>
			</table>
		</div>

	</xsl:template>

</xsl:stylesheet>
