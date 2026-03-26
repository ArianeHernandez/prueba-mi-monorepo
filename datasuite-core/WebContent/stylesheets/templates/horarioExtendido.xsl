<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="HORARIO_EXTENDIDO">
		<xsl:param name="nombre_horario" select="'default'" />
		<xsl:param name="hora_inicio" select="'6'" />
		<xsl:param name="hora_fin" select="'20'" />
		
		<javascript>templates/horarioExtendido.js</javascript>
		<stylesheet>horario.css</stylesheet>
		
		<bloque accion="editarCriterioHorario('{$nombre_horario}')">
			<input type="hidden" class="listahorarios" name="horario[{$nombre_horario}]" value="{$nombre_horario}" />
			<variable id="hora_inicio_{$nombre_horario}" valor="{$hora_inicio}" />
			<variable id="hora_fin_{$nombre_horario}" valor="{$hora_fin}" />
			<div id="criterios_aplicados_horario_{$nombre_horario}" class="item bk_horario2">
				<div class="contenedor_ventana" id="contenedor_ventana_edicion_horario_{$nombre_horario}"
					style="left:200px; top:-220px; display:none">
					&#160; </div>

				<table class="tabla_horario_vista" style="cursor: pointer;"
					onclick="editarCriterioHorario('{$nombre_horario}')">
					<tr>
						<th>L</th>
						<th>M</th>
						<th>C</th>
						<th>J</th>
						<th>V</th>
						<th>S</th>
						<th>D</th>
					</tr>
					<tr>
						<td id="valor_vista_criterio_hora_L_{$nombre_horario}">&#160;</td>
						<td id="valor_vista_criterio_hora_M_{$nombre_horario}">&#160;</td>
						<td id="valor_vista_criterio_hora_C_{$nombre_horario}">&#160;</td>
						<td id="valor_vista_criterio_hora_J_{$nombre_horario}">&#160;</td>
						<td id="valor_vista_criterio_hora_V_{$nombre_horario}">&#160;</td>
						<td id="valor_vista_criterio_hora_S_{$nombre_horario}">&#160;</td>
						<td id="valor_vista_criterio_hora_D_{$nombre_horario}">&#160;</td>
					</tr>
				</table>

				<input type="hidden" id="valor_vista_criterio_hora_{$nombre_horario}" />
				<input type="hidden" name="franjas" id="franjas_{$nombre_horario}" />
			</div>
		</bloque>
	</xsl:template>
	
	<xsl:template name="HORARIO_VENTANA_EXTENDIDO">
		<xsl:param name="nombre_horario" select="'default'" />
		<xsl:param name="hora_inicio" select="'6'" />
		<xsl:param name="hora_fin" select="'20'" />
		<xsl:param name="titulo" />
		<ventana id="vn_cambio_estado_activo_{$nombre_horario}" icono="confirmacion">
			<titulo>Definir Horario</titulo>
			<contenido>

				<bloque estilo="grupo">
					<parrafo estilo="resaltado" id="mensaje_disponible_empresa_{$nombre_horario}">
						<xsl:value-of select="$titulo" />
					</parrafo>
					<escapar>
						<div id="criterios_aplicados_horario_{$nombre_horario}" class="div_horario">
			
							<table class="tabla_horario_edicion">
								<tr>
									<th id="horario_">&#160;</th>
									<th id="horario_L_{$nombre_horario}">L</th>
									<th id="horario_M_{$nombre_horario}">M</th>
									<th id="horario_C_{$nombre_horario}">C</th>
									<th id="horario_J_{$nombre_horario}">J</th>
									<th id="horario_V_{$nombre_horario}">V</th>
									<th id="horario_S_{$nombre_horario}">S</th>
									<th id="horario_D_{$nombre_horario}">D</th>
								</tr>
								<xsl:if test="$hora_inicio &lt;= 0 and $hora_fin &gt;= 0">
								<tr>
									<th id="horario_0_{$nombre_horario}">0</th>
									<td id="horario_0L_{$nombre_horario}">&#100;</td>
									<td id="horario_0M_{$nombre_horario}">&#100;</td>
									<td id="horario_0C_{$nombre_horario}">&#100;</td>
									<td id="horario_0J_{$nombre_horario}">&#100;</td>
									<td id="horario_0V_{$nombre_horario}">&#100;</td>
									<td id="horario_0S_{$nombre_horario}">&#100;</td>
									<td id="horario_0D_{$nombre_horario}">&#100;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 1 and $hora_fin &gt;= 1">
								<tr>
									<th id="horario_1_{$nombre_horario}">1</th>
									<td id="horario_1L_{$nombre_horario}">&#110;</td>
									<td id="horario_1M_{$nombre_horario}">&#110;</td>
									<td id="horario_1C_{$nombre_horario}">&#110;</td>
									<td id="horario_1J_{$nombre_horario}">&#110;</td>
									<td id="horario_1V_{$nombre_horario}">&#110;</td>
									<td id="horario_1S_{$nombre_horario}">&#110;</td>
									<td id="horario_1D_{$nombre_horario}">&#110;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 2 and $hora_fin &gt;= 2">
								<tr>
									<th id="horario_2_{$nombre_horario}">2</th>
									<td id="horario_2L_{$nombre_horario}">&#120;</td>
									<td id="horario_2M_{$nombre_horario}">&#120;</td>
									<td id="horario_2C_{$nombre_horario}">&#120;</td>
									<td id="horario_2J_{$nombre_horario}">&#120;</td>
									<td id="horario_2V_{$nombre_horario}">&#120;</td>
									<td id="horario_2S_{$nombre_horario}">&#120;</td>
									<td id="horario_2D_{$nombre_horario}">&#120;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 3 and $hora_fin &gt;= 3">
								<tr>
									<th id="horario_3_{$nombre_horario}">3</th>
									<td id="horario_3L_{$nombre_horario}">&#130;</td>
									<td id="horario_3M_{$nombre_horario}">&#130;</td>
									<td id="horario_3C_{$nombre_horario}">&#130;</td>
									<td id="horario_3J_{$nombre_horario}">&#130;</td>
									<td id="horario_3V_{$nombre_horario}">&#130;</td>
									<td id="horario_3S_{$nombre_horario}">&#130;</td>
									<td id="horario_3D_{$nombre_horario}">&#130;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 4 and $hora_fin &gt;= 4">
								<tr>
									<th id="horario_4_{$nombre_horario}">4</th>
									<td id="horario_4L_{$nombre_horario}">&#140;</td>
									<td id="horario_4M_{$nombre_horario}">&#140;</td>
									<td id="horario_4C_{$nombre_horario}">&#140;</td>
									<td id="horario_4J_{$nombre_horario}">&#140;</td>
									<td id="horario_4V_{$nombre_horario}">&#140;</td>
									<td id="horario_4S_{$nombre_horario}">&#140;</td>
									<td id="horario_4D_{$nombre_horario}">&#140;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 5 and $hora_fin &gt;= 5">
								<tr>
									<th id="horario_5_{$nombre_horario}">5</th>
									<td id="horario_5L_{$nombre_horario}">&#150;</td>
									<td id="horario_5M_{$nombre_horario}">&#150;</td>
									<td id="horario_5C_{$nombre_horario}">&#150;</td>
									<td id="horario_5J_{$nombre_horario}">&#150;</td>
									<td id="horario_5V_{$nombre_horario}">&#150;</td>
									<td id="horario_5S_{$nombre_horario}">&#150;</td>
									<td id="horario_5D_{$nombre_horario}">&#150;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 6 and $hora_fin &gt;= 6">
								<tr>
									<th id="horario_6_{$nombre_horario}">6</th>
									<td id="horario_6L_{$nombre_horario}">&#160;</td>
									<td id="horario_6M_{$nombre_horario}">&#160;</td>
									<td id="horario_6C_{$nombre_horario}">&#160;</td>
									<td id="horario_6J_{$nombre_horario}">&#160;</td>
									<td id="horario_6V_{$nombre_horario}">&#160;</td>
									<td id="horario_6S_{$nombre_horario}">&#160;</td>
									<td id="horario_6D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 7 and $hora_fin &gt;= 7">
								<tr>
									<th id="horario_7_{$nombre_horario}">7</th>
									<td id="horario_7L_{$nombre_horario}">&#160;</td>
									<td id="horario_7M_{$nombre_horario}">&#160;</td>
									<td id="horario_7C_{$nombre_horario}">&#160;</td>
									<td id="horario_7J_{$nombre_horario}">&#160;</td>
									<td id="horario_7V_{$nombre_horario}">&#160;</td>
									<td id="horario_7S_{$nombre_horario}">&#160;</td>
									<td id="horario_7D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 8 and $hora_fin &gt;= 8">
								<tr>
									<th id="horario_8_{$nombre_horario}">8</th>
									<td id="horario_8L_{$nombre_horario}">&#160;</td>
									<td id="horario_8M_{$nombre_horario}">&#160;</td>
									<td id="horario_8C_{$nombre_horario}">&#160;</td>
									<td id="horario_8J_{$nombre_horario}">&#160;</td>
									<td id="horario_8V_{$nombre_horario}">&#160;</td>
									<td id="horario_8S_{$nombre_horario}">&#160;</td>
									<td id="horario_8D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 9 and $hora_fin &gt;= 9">
								<tr>
									<th id="horario_9_{$nombre_horario}">9</th>
									<td id="horario_9L_{$nombre_horario}">&#160;</td>
									<td id="horario_9M_{$nombre_horario}">&#160;</td>
									<td id="horario_9C_{$nombre_horario}">&#160;</td>
									<td id="horario_9J_{$nombre_horario}">&#160;</td>
									<td id="horario_9V_{$nombre_horario}">&#160;</td>
									<td id="horario_9S_{$nombre_horario}">&#160;</td>
									<td id="horario_9D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 10 and $hora_fin &gt;= 10">
								<tr>
									<th id="horario_10_{$nombre_horario}">10</th>
									<td id="horario_10L_{$nombre_horario}">&#160;</td>
									<td id="horario_10M_{$nombre_horario}">&#160;</td>
									<td id="horario_10C_{$nombre_horario}">&#160;</td>
									<td id="horario_10J_{$nombre_horario}">&#160;</td>
									<td id="horario_10V_{$nombre_horario}">&#160;</td>
									<td id="horario_10S_{$nombre_horario}">&#160;</td>
									<td id="horario_10D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 11 and $hora_fin &gt;= 11">
								<tr>
									<th id="horario_11_{$nombre_horario}">11</th>
									<td id="horario_11L_{$nombre_horario}">&#160;</td>
									<td id="horario_11M_{$nombre_horario}">&#160;</td>
									<td id="horario_11C_{$nombre_horario}">&#160;</td>
									<td id="horario_11J_{$nombre_horario}">&#160;</td>
									<td id="horario_11V_{$nombre_horario}">&#160;</td>
									<td id="horario_11S_{$nombre_horario}">&#160;</td>
									<td id="horario_11D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 12 and $hora_fin &gt;= 12">
								<tr>
									<th id="horario_12_{$nombre_horario}">12</th>
									<td id="horario_12L_{$nombre_horario}">&#160;</td>
									<td id="horario_12M_{$nombre_horario}">&#160;</td>
									<td id="horario_12C_{$nombre_horario}">&#160;</td>
									<td id="horario_12J_{$nombre_horario}">&#160;</td>
									<td id="horario_12V_{$nombre_horario}">&#160;</td>
									<td id="horario_12S_{$nombre_horario}">&#160;</td>
									<td id="horario_12D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 13 and $hora_fin &gt;= 13">
								<tr>
									<th id="horario_13_{$nombre_horario}">13</th>
									<td id="horario_13L_{$nombre_horario}">&#160;</td>
									<td id="horario_13M_{$nombre_horario}">&#160;</td>
									<td id="horario_13C_{$nombre_horario}">&#160;</td>
									<td id="horario_13J_{$nombre_horario}">&#160;</td>
									<td id="horario_13V_{$nombre_horario}">&#160;</td>
									<td id="horario_13S_{$nombre_horario}">&#160;</td>
									<td id="horario_13D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 14 and $hora_fin &gt;= 14">
								<tr>
									<th id="horario_14_{$nombre_horario}">14</th>
									<td id="horario_14L_{$nombre_horario}">&#160;</td>
									<td id="horario_14M_{$nombre_horario}">&#160;</td>
									<td id="horario_14C_{$nombre_horario}">&#160;</td>
									<td id="horario_14J_{$nombre_horario}">&#160;</td>
									<td id="horario_14V_{$nombre_horario}">&#160;</td>
									<td id="horario_14S_{$nombre_horario}">&#160;</td>
									<td id="horario_14D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 15 and $hora_fin &gt;= 15">
								<tr>
									<th id="horario_15_{$nombre_horario}">15</th>
									<td id="horario_15L_{$nombre_horario}">&#160;</td>
									<td id="horario_15M_{$nombre_horario}">&#160;</td>
									<td id="horario_15C_{$nombre_horario}">&#160;</td>
									<td id="horario_15J_{$nombre_horario}">&#160;</td>
									<td id="horario_15V_{$nombre_horario}">&#160;</td>
									<td id="horario_15S_{$nombre_horario}">&#160;</td>
									<td id="horario_15D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 16 and $hora_fin &gt;= 16">
								<tr>
									<th id="horario_16_{$nombre_horario}">16</th>
									<td id="horario_16L_{$nombre_horario}">&#160;</td>
									<td id="horario_16M_{$nombre_horario}">&#160;</td>
									<td id="horario_16C_{$nombre_horario}">&#160;</td>
									<td id="horario_16J_{$nombre_horario}">&#160;</td>
									<td id="horario_16V_{$nombre_horario}">&#160;</td>
									<td id="horario_16S_{$nombre_horario}">&#160;</td>
									<td id="horario_16D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 17 and $hora_fin &gt;= 17">
								<tr>
									<th id="horario_17_{$nombre_horario}">17</th>
									<td id="horario_17L_{$nombre_horario}">&#160;</td>
									<td id="horario_17M_{$nombre_horario}">&#160;</td>
									<td id="horario_17C_{$nombre_horario}">&#160;</td>
									<td id="horario_17J_{$nombre_horario}">&#160;</td>
									<td id="horario_17V_{$nombre_horario}">&#160;</td>
									<td id="horario_17S_{$nombre_horario}">&#160;</td>
									<td id="horario_17D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 18 and $hora_fin &gt;= 18">
								<tr>
									<th id="horario_18_{$nombre_horario}">18</th>
									<td id="horario_18L_{$nombre_horario}">&#160;</td>
									<td id="horario_18M_{$nombre_horario}">&#160;</td>
									<td id="horario_18C_{$nombre_horario}">&#160;</td>
									<td id="horario_18J_{$nombre_horario}">&#160;</td>
									<td id="horario_18V_{$nombre_horario}">&#160;</td>
									<td id="horario_18S_{$nombre_horario}">&#160;</td>
									<td id="horario_18D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 19 and $hora_fin &gt;= 19">
								<tr>
									<th id="horario_19_{$nombre_horario}">19</th>
									<td id="horario_19L_{$nombre_horario}">&#160;</td>
									<td id="horario_19M_{$nombre_horario}">&#160;</td>
									<td id="horario_19C_{$nombre_horario}">&#160;</td>
									<td id="horario_19J_{$nombre_horario}">&#160;</td>
									<td id="horario_19V_{$nombre_horario}">&#160;</td>
									<td id="horario_19S_{$nombre_horario}">&#160;</td>
									<td id="horario_19D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 20 and $hora_fin &gt;= 20">
								<tr>
									<th id="horario_20_{$nombre_horario}">20</th>
									<td id="horario_20L_{$nombre_horario}">&#160;</td>
									<td id="horario_20M_{$nombre_horario}">&#160;</td>
									<td id="horario_20C_{$nombre_horario}">&#160;</td>
									<td id="horario_20J_{$nombre_horario}">&#160;</td>
									<td id="horario_20V_{$nombre_horario}">&#160;</td>
									<td id="horario_20S_{$nombre_horario}">&#160;</td>
									<td id="horario_20D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 21 and $hora_fin &gt;= 21">
								<tr>
									<th id="horario_21_{$nombre_horario}">21</th>
									<td id="horario_21L_{$nombre_horario}">&#160;</td>
									<td id="horario_21M_{$nombre_horario}">&#160;</td>
									<td id="horario_21C_{$nombre_horario}">&#160;</td>
									<td id="horario_21J_{$nombre_horario}">&#160;</td>
									<td id="horario_21V_{$nombre_horario}">&#160;</td>
									<td id="horario_21S_{$nombre_horario}">&#160;</td>
									<td id="horario_21D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 22 and $hora_fin &gt;= 22">
								<tr>
									<th id="horario_22_{$nombre_horario}">22</th>
									<td id="horario_22L_{$nombre_horario}">&#160;</td>
									<td id="horario_22M_{$nombre_horario}">&#160;</td>
									<td id="horario_22C_{$nombre_horario}">&#160;</td>
									<td id="horario_22J_{$nombre_horario}">&#160;</td>
									<td id="horario_22V_{$nombre_horario}">&#160;</td>
									<td id="horario_22S_{$nombre_horario}">&#160;</td>
									<td id="horario_22D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
								<xsl:if test="$hora_inicio &lt;= 23 and $hora_fin &gt;= 23">
								<tr>
									<th id="horario_23_{$nombre_horario}">23</th>
									<td id="horario_23L_{$nombre_horario}">&#160;</td>
									<td id="horario_23M_{$nombre_horario}">&#160;</td>
									<td id="horario_23C_{$nombre_horario}">&#160;</td>
									<td id="horario_23J_{$nombre_horario}">&#160;</td>
									<td id="horario_23V_{$nombre_horario}">&#160;</td>
									<td id="horario_23S_{$nombre_horario}">&#160;</td>
									<td id="horario_23D_{$nombre_horario}">&#160;</td>
								</tr>
								</xsl:if>
							</table>
							<input type="hidden" id="valor_criterio_horario_{$nombre_horario}" />
							<input type="hidden" id="valor_vista_criterio_horario_{$nombre_horario}" />
			
						</div>
					</escapar>
				</bloque>
				
				<area_botones>
					<boton estilo="primary aceptar" accion="guardarHorario('{$nombre_horario}');">Aceptar</boton>
					<boton estilo="danger" accion="cancelar('{$nombre_horario}')">Cancelar</boton>
				</area_botones>
			</contenido>
		</ventana>
	</xsl:template>
</xsl:stylesheet>