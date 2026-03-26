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

			<javascript>webdata/23.1.js</javascript>
			<stylesheet>23.css</stylesheet>

			<principal>
				<titulo>Asignar Bancos</titulo>
				<contenido>
					
					<xsl:choose>
						<xsl:when test="//guardarGruposGiroPorComandos/Boolean = 'true'">
							
							<p id="mensaje"><b>Por favor, espere un momento mientras se realiza la operación.</b></p>
							
							<div class="barra-progreso" id="barra-progreso">
								<div class="barra-progreso-avance" id="progreso">
								</div>
							</div>
							
						</xsl:when>
						<xsl:otherwise>
							Error al crear al Asignar Bancos!
						</xsl:otherwise>
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary volver" id="btn_volver" destino="grupos_giro/23.do">Volver</boton>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
					<xsl:call-template name="plantillas" />
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
			<label>Definir Banco -<xsl:value-of select="$id_carga"/> </label>
			<select class="put w200" name="select_bancos" id="select_bancos_{$id}"
				onchange="mostrarSelectCuentas('{$id}',this.id)">
				<option>--Seleccione--</option>
			</select>
			<label>No. de Cuenta</label>
			<select class="put w200" name="select_cuenta" id="select_cuenta_{$id}">
				<xsl:attribute name="onchange">
					<xsl:choose>
						<xsl:when test="$id_registro != ''">seleccionCampoRegistro('<xsl:value-of
					select="$id_carga" />','<xsl:value-of select="$id_registro" />', 
						'cuenta',osm_getValor(this.id), osm_getValorText(this.id))</xsl:when>
						<xsl:otherwise>seleccionCampoCarga('<xsl:value-of
					select="$id_carga" />', 
						'cuenta',osm_getValor(this.id), osm_getValorText(this.id))</xsl:otherwise>
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
			<table class="tb-02">
				<tbody id="registros_carga_{$id_carga}">
				</tbody>
			</table>
		</div>
		<div class="loading_reg" id="loading_reg_{$id_carga}">&#160;</div>
	</xsl:template>

	<!--
		1: idregistro, 2:posicion , 3: identificacion, 4: valor 5: banco
		destino, 6: cuenta destino, 7: idcarga, 8: par(1 ó 2, para estilo)
	-->
	<xsl:template name="plantillas">
		<div style="display:none">
			<table>
				<tbody id="plantilla_registro">
					<tr class="tr-0[ 8 ]">
						<td class="w020">[ 2 ]</td>
						<td class="w090">[ 3 ] </td>
						<td class="w100">[ 4 ]</td>
						<td class="w090">[ 5 ]</td>
						<td class="w090">[ 6 ]</td>
						<td class="w090">
							<div class="pbox">
								<input type="hidden" name="GrupoGiroRegistro:[ 7 ]_[ 1 ].id_registro" value="[ 1 ]" />
								<input class="date-pick dp-applied dp-choose-date" id="gg_fecha_[ 7 ]_[ 1 ]" name="GrupoGiroRegistro:[ 7 ]_[ 1 ].fecha_pago"
									onchange="seleccionCampoRegistro('[ 7 ]', '[ 1 ]', 'fecha', this.value, this.value)"/>
							</div>
						</td>
						<td class="w200">
							<div class="thumbnail" id="sel_banco_[ 7 ]_[ 1 ]" name="sel_banco"
								value="[ 7 ]_[ 1 ]">
								<span id="gg_cuenta_[ 7 ]_[ 1 ]">--</span>
								<input type="hidden" id="gg_id_cuenta_[ 7 ]_[ 1 ]" name="GrupoGiroRegistro:[ 7 ]_[ 1 ].id_cuenta"/>
								<xsl:call-template name="sel-cuenta">
									<xsl:with-param name="id_carga" select="'[ 7 ]'" />
									<xsl:with-param name="id_registro" select="'[ 1 ]'" />
								</xsl:call-template>
							</div>
						</td>
						<td class="w090">
							<span name="grupogrio_estado_[ 7 ]_[ 1 ]">--</span>
						</td>
						<td>--</td>
					</tr>
				</tbody>
			</table>
		</div>

	</xsl:template>

</xsl:stylesheet>
