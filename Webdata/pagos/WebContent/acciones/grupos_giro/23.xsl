<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Asignar Bancos">

			<javascript>webdata/23.js</javascript>
			<stylesheet>23.css</stylesheet>

			<principal>
				<titulo>Asignar Bancos</titulo>
				<contenido>
					<div class="box-container">
						<bloque-pestanas>
	
	
							<pestana titulo="Solicitudes de Retiro">
															
								<div id="mensaje_cargando" class="alert  alert-info">
									<i class="fa fa-info-circle" aria-hidden="true"></i>
  									<span class="sr-only">Info:</span>
								
									<p>Cargando Información...</p>
								</div>
	
								<div style="display:none" id="cont_grupo_giro">
									
									<div class="alert  alert-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
	  									<span class="sr-only">Info:</span>
										<p>Solo se guardan los registros que tengan fecha y cuenta.</p>
									</div>
									
									<div>
										<table class="table table-hover table-striped">
											<thead>
		
												<tr class="">
													<th class="w020">#</th>
													<th class="w090">Identificación</th>
													<th class="w090">Valor</th>
													<th class="w100">Banco Destino</th>
													<th class="w090">Cuenta Destino</th>
													<th class="w090">Fecha Trans.</th>
													<th class="w200">Banco y Cuenta Saliente</th>
													<th class="w090">Estado</th>
												</tr>
											</thead>
											<tbody>
												<xsl:for-each select="//obtenerCargas/Lista/GrupoGiroCarga">
													<xsl:sort select="id_carga" />
													<xsl:variable name="mod" select="(position() mod 2) + 1" />
													<xsl:variable name="registro" select="datosCarga/element" />
													<xsl:choose>
														<xsl:when test="tipo_carga = '1'">
															<tr class="tr-0{$mod}">
																<td>
																	<xsl:value-of select="position()" />
																	<input type="hidden" name="carga_inter" value="{id_carga}" />
																	<input type="hidden" name="carga" id="carga_{id_carga}"
																		value="{id_carga}" />
																	<input type="hidden" name="registrosGrupoGiro:{position()}.id_carga" value="{id_carga}" />
																	<input type="hidden" name="registrosGrupoGiro:{position()}.id_estructura" value="{id_estructura}" />
																</td>
																<td id="identificacion_{id_carga}">
																	--
																</td>
																<td id="valor_{id_carga}" class="money">
																	<xsl:value-of select="format-number(valorTotal, '##,###.00')"/>
																</td>
																<td id="banco_{id_carga}">
																	--
																</td>
																<td id="cuenta_{id_carga}">
																	--
																</td>
																<td>
																	<div class="pbox">
																		<span class="clean_date" onclick="osm_setValor('fecha_pago_{id_carga}','')">X</span>
																		<input class="dp-applied dp-choose-date" name="registrosGrupoGiro:{position()}.fecha_pago"
																			id="fecha_pago_{id_carga}" onchange="seleccionCampoCarga({id_carga}, 'fecha', this.value, this.value, false, true)" readonly="readonly"/>
																	</div>
																</td>
																<td>
																	<div class="thumbnail" value="{id_carga}" id="sel_banco_{id_carga}">
																		<span id="cuenta_pago_{id_carga}">--</span>
																		<input type="hidden" id="cuenta_pago2_{id_carga}" name="registrosGrupoGiro:{position()}.cuenta"/>
																		<input type="hidden" id="producto_{id_carga}"/>
																		<xsl:call-template name="sel-cuenta">
																			<xsl:with-param name="id_carga" select="id_carga" />
																		</xsl:call-template>
																	</div>
																</td>
																<td id="estado_{id_carga}">--</td>
																
															</tr>
														</xsl:when>
														<xsl:otherwise>
															<tr class="tr-0{$mod}">
																<td>
																	<xsl:value-of select="position()"></xsl:value-of>
																	<input type="hidden" name="carga_lotes" value="{id_carga}" />
																	<input type="hidden" name="carga" id="carga_{id_carga}" value="{id_carga}" />
																	<input type="hidden" name="registrosGrupoGiro:{position()}.id_carga" value="{id_carga}" />
																	<input type="hidden" name="registrosGrupoGiro:{position()}.id_estructura" value="{id_estructura}" />
																</td>
																<td>
																	<a name="link_ver" id="link_ver_{id_carga}">
																		--Ver Registros<span id="total_reg_{id_carga}"></span>--
																	</a>
																</td>
																<td>
																	<xsl:value-of select="format-number(valorTotal, '##,###.00')"/>
																</td>
																<td>
																	--
																</td>
																<td>
																	--
																</td>
																<td>
																	<div class="pbox">
																		<span class="clean_date" onclick="osm_setValor('fecha_pago_{id_carga}',''); seleccionCampoCarga({id_carga}, 'fecha', '', '', true, true)">X</span>
																		<input class="date-pick-now dp-applied dp-choose-date" name="registrosGrupoGiro:{position()}.fecha_pago"
																			id="fecha_pago_{id_carga}" onchange="seleccionCampoCarga({id_carga}, 'fecha', this.value, this.value, true, true)" readonly="readonly"/>
																	</div>
																</td>
																<td>
																	<div class="thumbnail" name="sel_banco" value="{id_carga}">
																		<span id="cuenta_pago_{id_carga}">--</span>
																		<input type="hidden" id="cuenta_pago2_{id_carga}" name="registrosGrupoGiro:{position()}.cuenta"/>
																		<input type="hidden" id="producto_{id_carga}"/>
																		<xsl:call-template name="sel-cuenta">
																			<xsl:with-param name="id_carga" select="id_carga" />
																		</xsl:call-template>
																	</div>
																</td>
																<td>--</td>
															</tr>
															<tr style="display:none;" id="contenido_carga_{id_carga}"
																class="tr-01">
																<td class="flover" colspan="9">
																	<xsl:call-template name="contenido-carga">
																		<xsl:with-param name="id_carga" select="id_carga" />
																	</xsl:call-template>
																</td>
															</tr>
														</xsl:otherwise>
													</xsl:choose>
		
		
		
												</xsl:for-each>
		
											</tbody>
										</table>
									</div>
								</div>
								<area_botones>
									<boton estilo="inicio" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Cancelar</boton>
									<boton estilo="guardar" formulario="form_grupos_giro"><i class="fa fa-save" aria-hidden="true"></i>&#160;Guardar</boton> 
									
								</area_botones>
							</pestana>
						</bloque-pestanas>
					
					<form id="form_grupos_giro" name="form_grupos_giro" action="23.1.do">
						<div id="div_comandos"></div>
						<input type="hidden" name="idReporteGruposGiro" id="idReporteGruposGiro" />
					</form>
					
					
					
					<xsl:call-template name="plantillas" />
					</div>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>

	<xsl:template name="sel-cuenta">
		<xsl:param name="id_carga" />
		<xsl:param name="id_registro" />

		<xsl:variable name="id">
			<xsl:choose>
				<xsl:when test="string-length($id_registro) > 0">
					<xsl:value-of select="concat($id_carga,'_', $id_registro)" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$id_carga" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<span name="cont_sel_banco" id="cont_sel_banco_{$id}" class="thumbnail_sel">
			<label>Definir Banco&#160;-&#160;<xsl:value-of select="$id_carga"/> </label>
			<select class="put w200" name="select_bancos" id="select_bancos_{$id}"
				onchange="mostrarSelectCuentas('{$id}',this.id)">
				<option>--Seleccione--</option>
			</select>
			<label>No. de Cuenta</label>
			<select class="put w200" name="select_cuenta" id="select_cuenta_{$id}">
				<xsl:attribute name="onchange">
					<xsl:choose>
						<xsl:when test="$id_registro != ''">
							seleccionCampoRegistro('<xsl:value-of select="$id_carga" />',
							'<xsl:value-of select="$id_registro" />', 
							'cuenta',osm_getValor(this.id), osm_getValorText('select_bancos_' + '<xsl:value-of select="$id"/>') +
						 ' - ' +osm_getValorText(this.id), false , true);
							seleccionCampoCarga('<xsl:value-of select="$id_carga" />', 'cuenta', '', '--')
						</xsl:when>
						<xsl:otherwise>seleccionCampoCarga('<xsl:value-of select="$id_carga" />', 
						'cuenta',osm_getValor(this.id), osm_getValorText('select_bancos_' + '<xsl:value-of select="$id"/>') +
						 ' - ' + osm_getValorText(this.id), false, true)</xsl:otherwise>
					</xsl:choose>
				</xsl:attribute>
				<option>--Ninguna--</option>
			</select>
			<area_botones>
				<a class="btn btn-default" name="cerrar_sel_banco" id="cerrar_sel_banco_{$id}"
					value="{$id}">
					Cerrar
				</a>
			</area_botones>
		</span>
	</xsl:template>


	<xsl:template name="contenido-carga">
		<xsl:param name="id_carga" />
		<div id="cont_scroll_{$id_carga}">
			<table class="tb-02" id="registros_carga_{$id_carga}">
				<tbody>
				</tbody>
			</table>
			<table id="registros_carga_buffer_{$id_carga}" style="display:none">
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="loading_reg" id="loading_reg_{$id_carga}" style="display:none">
		</div>
		<div class="paginacion">
			<table class="tabla_paginacion" border="0" cellspacing="0"
				cellpadding="0" >
				<tr>
					<td width="22px">
						<a onclick="paginacion_inicio('{$id_carga}')">
							<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero" />
						</a>
					</td>
		
					<td width="22px">
						<a onclick="paginacion_anterior('{$id_carga}')">
							<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior" />
						</a>
					</td>
		
					<td>
						<p id="texto_paginacion_{$id_carga}">Cargando ... </p>
					</td>
		
					<td width="22px">
						<a onclick="paginacion_siguiente('{$id_carga}')">
							<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente" />
						</a>
					</td>
		
					<td width="22px">
						<a onclick="paginacion_final('{$id_carga}')">
							<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo" />
						</a>
					</td>
				</tr>
			</table>
		</div>
		
	</xsl:template>

	<!--
		1: idregistro, 2:posicion , 3: identificacion, 4: valor 5: banco
		destino, 6: cuenta destino, 7: idcarga, 8: par(1 ó 2, para estilo)
		9: cuenta_pago, 10: fecha_pago, 11: estado
	-->
	<xsl:template name="plantillas">
		<div style="display:none" id="DIV_PLANTILLAS">
			<table>
				<tbody id="PLANTILLA_REGISTRO">
					<tr class="tr-0[ 8 ]" name="registro_[ 7 ]">
						<td class="w020">[ 2 ]</td>
						<td class="w090">[ 3 ] </td>
						<td class="w100 money">[ 4 ]</td>
						<td class="w090">[ 5 ]</td>
						<td class="w090">[ 6 ]</td>
						<td class="w090">
							<div class="pbox">
								<span class="clean_date" onclick="seleccionCampoRegistro('[ 7 ]', '[ 1 ]', 'fecha', '', '', false ,true); seleccionCampoCarga('[ 7 ]', 'fecha', '', '')">X</span>
								<input type="hidden" name="registrosGrupoGiro:[ 7 ]_[ 1 ].codigo_registro" value="[ 1 ]" />
								<input class="date-pick_[ 7 ]_[ 1 ] dp-applied dp-choose-date" id="fecha_pago_[ 7 ]_[ 1 ]" name="registrosGrupoGiro:[ 7 ]_[ 1 ].fecha_pago"
									onchange="seleccionCampoRegistro('[ 7 ]', '[ 1 ]', 'fecha', this.value, this.value, false ,true); seleccionCampoCarga('[ 7 ]', 'fecha', '', '')" readonly="readonly"
									value="[ 10 ]"/>
							</div>
						</td>
						<td class="w200">
							<div class="thumbnail" id="sel_banco_[ 7 ]_[ 1 ]" name="sel_banco"
								value="[ 7 ]_[ 1 ]">
								<span class="clean_date" id="cuenta_pago_[ 7 ]_[ 1 ]">[ 9 ]</span>
								<input type="hidden" id="cuenta_pago2_[ 7 ]_[ 1 ]" name="registrosGrupoGiro:[ 7 ]_[ 1 ].cuenta"  value="[ 12 ]"/>
								<input type="hidden" id="producto_[ 7 ]_[ 1 ]"/>
								<xsl:call-template name="sel-cuenta">
									<xsl:with-param name="id_carga" select="'[ 7 ]'" />
									<xsl:with-param name="id_registro" select="'[ 1 ]'" />
								</xsl:call-template>
							</div>
						</td>
						<td class="w090">
							<span name="grupogiro_estado_[ 7 ]_[ 1 ]">[ 11 ]</span>
						</td>
					</tr>
				</tbody>
			</table>
			<div id="PLANTILLA_COMANDO">
				<div id="comando_[ 1 ]_[ 2 ]" style="comando">
					<input type="hidden" id="comando_id_carga_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].id_carga" value="[ 1 ]" />
					<input type="hidden" id="comando_codigo_registro_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].codigo_registro"  value="[ 2 ]" />
					<input type="hidden" id="comando_orden_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].orden"  value="[ 3 ]"/>
					<input type="hidden" id="comando_cuenta_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].cuenta"  value="[ 4 ]" />
					<input type="hidden" id="comando_fecha_[ 1 ]_[ 2 ]" name="comandos:[ 1 ]_[ 2 ].fecha_pago"  value="[ 5 ]" />
				</div>
			</div>
			
		</div>

	</xsl:template>

</xsl:stylesheet>
