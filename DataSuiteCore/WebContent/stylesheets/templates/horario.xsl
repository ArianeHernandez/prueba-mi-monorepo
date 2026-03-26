<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="HORARIO">
		
		<javascript>templates/horario.js</javascript>
		<stylesheet>horario.css</stylesheet>
		
		<bloque accion="editarCriterioHorario()">
			<div id="criterios_aplicados_horario" class="item bk_horario2">
				<div class="contenedor_ventana" id="contenedor_ventana_edicion_horario"
					style="left:200px; top:-220px; display:none">
					&#160; </div>

				<table class="tabla_horario_vista" style="cursor: pointer;"
					onclick="editarCriterioHorario()">
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
						<td id="valor_vista_criterio_hora_L">&#160;</td>
						<td id="valor_vista_criterio_hora_M">&#160;</td>
						<td id="valor_vista_criterio_hora_C">&#160;</td>
						<td id="valor_vista_criterio_hora_J">&#160;</td>
						<td id="valor_vista_criterio_hora_V">&#160;</td>
						<td id="valor_vista_criterio_hora_S">&#160;</td>
						<td id="valor_vista_criterio_hora_D">&#160;</td>
					</tr>
				</table>

				<input type="hidden" id="valor_vista_criterio_hora" />
				<input type="hidden" name="franjas" id="franjas" />
			</div>
		</bloque>
	</xsl:template>
	
	<xsl:template name="HORARIO_VENTANA">
		<ventana id="vn_cambio_estado_activo" icono="confirmacion">
			<titulo>Definir Horario</titulo>
			<contenido>

				<bloque estilo="grupo">
					<parrafo estilo="resaltado" id="mensaje_disponible_empresa">
						Seleccione el horario en el cual el formato es liberado.
					</parrafo>
					<escapar>
						<div id="criterios_aplicados_horario" class="div_horario">
			
							<table class="tabla_horario_edicion">
								<tr>
									<th id="horario_">&#160;</th>
									<th id="horario_L">L</th>
									<th id="horario_M">M</th>
									<th id="horario_C">C</th>
									<th id="horario_J">J</th>
									<th id="horario_V">V</th>
									<th id="horario_S">S</th>
									<th id="horario_D">D</th>
								</tr>
								<tr>
									<th id="horario_0">0</th>
									<td id="horario_0L">&#160;</td>
									<td id="horario_0M">&#160;</td>
									<td id="horario_0C">&#160;</td>
									<td id="horario_0J">&#160;</td>
									<td id="horario_0V">&#160;</td>
									<td id="horario_0S">&#160;</td>
									<td id="horario_0D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_1">1</th>
									<td id="horario_1L">&#160;</td>
									<td id="horario_1M">&#160;</td>
									<td id="horario_1C">&#160;</td>
									<td id="horario_1J">&#160;</td>
									<td id="horario_1V">&#160;</td>
									<td id="horario_1S">&#160;</td>
									<td id="horario_1D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_2">2</th>
									<td id="horario_2L">&#160;</td>
									<td id="horario_2M">&#160;</td>
									<td id="horario_2C">&#160;</td>
									<td id="horario_2J">&#160;</td>
									<td id="horario_2V">&#160;</td>
									<td id="horario_2S">&#160;</td>
									<td id="horario_2D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_3">3</th>
									<td id="horario_3L">&#160;</td>
									<td id="horario_3M">&#160;</td>
									<td id="horario_3C">&#160;</td>
									<td id="horario_3J">&#160;</td>
									<td id="horario_3V">&#160;</td>
									<td id="horario_3S">&#160;</td>
									<td id="horario_3D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_4">4</th>
									<td id="horario_4L">&#160;</td>
									<td id="horario_4M">&#160;</td>
									<td id="horario_4C">&#160;</td>
									<td id="horario_4J">&#160;</td>
									<td id="horario_4V">&#160;</td>
									<td id="horario_4S">&#160;</td>
									<td id="horario_4D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_5">5</th>
									<td id="horario_5L">&#160;</td>
									<td id="horario_5M">&#160;</td>
									<td id="horario_5C">&#160;</td>
									<td id="horario_5J">&#160;</td>
									<td id="horario_5V">&#160;</td>
									<td id="horario_5S">&#160;</td>
									<td id="horario_5D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_6">6</th>
									<td id="horario_6L">&#160;</td>
									<td id="horario_6M">&#160;</td>
									<td id="horario_6C">&#160;</td>
									<td id="horario_6J">&#160;</td>
									<td id="horario_6V">&#160;</td>
									<td id="horario_6S">&#160;</td>
									<td id="horario_6D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_7">7</th>
									<td id="horario_7L">&#160;</td>
									<td id="horario_7M">&#160;</td>
									<td id="horario_7C">&#160;</td>
									<td id="horario_7J">&#160;</td>
									<td id="horario_7V">&#160;</td>
									<td id="horario_7S">&#160;</td>
									<td id="horario_7D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_8">8</th>
									<td id="horario_8L">&#160;</td>
									<td id="horario_8M">&#160;</td>
									<td id="horario_8C">&#160;</td>
									<td id="horario_8J">&#160;</td>
									<td id="horario_8V">&#160;</td>
									<td id="horario_8S">&#160;</td>
									<td id="horario_8D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_9">9</th>
									<td id="horario_9L">&#160;</td>
									<td id="horario_9M">&#160;</td>
									<td id="horario_9C">&#160;</td>
									<td id="horario_9J">&#160;</td>
									<td id="horario_9V">&#160;</td>
									<td id="horario_9S">&#160;</td>
									<td id="horario_9D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_10">10</th>
									<td id="horario_10L">&#160;</td>
									<td id="horario_10M">&#160;</td>
									<td id="horario_10C">&#160;</td>
									<td id="horario_10J">&#160;</td>
									<td id="horario_10V">&#160;</td>
									<td id="horario_10S">&#160;</td>
									<td id="horario_10D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_11">11</th>
									<td id="horario_11L">&#160;</td>
									<td id="horario_11M">&#160;</td>
									<td id="horario_11C">&#160;</td>
									<td id="horario_11J">&#160;</td>
									<td id="horario_11V">&#160;</td>
									<td id="horario_11S">&#160;</td>
									<td id="horario_11D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_12">12</th>
									<td id="horario_12L">&#160;</td>
									<td id="horario_12M">&#160;</td>
									<td id="horario_12C">&#160;</td>
									<td id="horario_12J">&#160;</td>
									<td id="horario_12V">&#160;</td>
									<td id="horario_12S">&#160;</td>
									<td id="horario_12D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_13">13</th>
									<td id="horario_13L">&#160;</td>
									<td id="horario_13M">&#160;</td>
									<td id="horario_13C">&#160;</td>
									<td id="horario_13J">&#160;</td>
									<td id="horario_13V">&#160;</td>
									<td id="horario_13S">&#160;</td>
									<td id="horario_13D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_14">14</th>
									<td id="horario_14L">&#160;</td>
									<td id="horario_14M">&#160;</td>
									<td id="horario_14C">&#160;</td>
									<td id="horario_14J">&#160;</td>
									<td id="horario_14V">&#160;</td>
									<td id="horario_14S">&#160;</td>
									<td id="horario_14D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_15">15</th>
									<td id="horario_15L">&#160;</td>
									<td id="horario_15M">&#160;</td>
									<td id="horario_15C">&#160;</td>
									<td id="horario_15J">&#160;</td>
									<td id="horario_15V">&#160;</td>
									<td id="horario_15S">&#160;</td>
									<td id="horario_15D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_16">16</th>
									<td id="horario_16L">&#160;</td>
									<td id="horario_16M">&#160;</td>
									<td id="horario_16C">&#160;</td>
									<td id="horario_16J">&#160;</td>
									<td id="horario_16V">&#160;</td>
									<td id="horario_16S">&#160;</td>
									<td id="horario_16D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_17">17</th>
									<td id="horario_17L">&#160;</td>
									<td id="horario_17M">&#160;</td>
									<td id="horario_17C">&#160;</td>
									<td id="horario_17J">&#160;</td>
									<td id="horario_17V">&#160;</td>
									<td id="horario_17S">&#160;</td>
									<td id="horario_17D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_18">18</th>
									<td id="horario_18L">&#160;</td>
									<td id="horario_18M">&#160;</td>
									<td id="horario_18C">&#160;</td>
									<td id="horario_18J">&#160;</td>
									<td id="horario_18V">&#160;</td>
									<td id="horario_18S">&#160;</td>
									<td id="horario_18D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_19">19</th>
									<td id="horario_19L">&#160;</td>
									<td id="horario_19M">&#160;</td>
									<td id="horario_19C">&#160;</td>
									<td id="horario_19J">&#160;</td>
									<td id="horario_19V">&#160;</td>
									<td id="horario_19S">&#160;</td>
									<td id="horario_19D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_20">20</th>
									<td id="horario_20L">&#160;</td>
									<td id="horario_20M">&#160;</td>
									<td id="horario_20C">&#160;</td>
									<td id="horario_20J">&#160;</td>
									<td id="horario_20V">&#160;</td>
									<td id="horario_20S">&#160;</td>
									<td id="horario_20D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_21">21</th>
									<td id="horario_21L">&#160;</td>
									<td id="horario_21M">&#160;</td>
									<td id="horario_21C">&#160;</td>
									<td id="horario_21J">&#160;</td>
									<td id="horario_21V">&#160;</td>
									<td id="horario_21S">&#160;</td>
									<td id="horario_21D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_22">22</th>
									<td id="horario_22L">&#160;</td>
									<td id="horario_22M">&#160;</td>
									<td id="horario_22C">&#160;</td>
									<td id="horario_22J">&#160;</td>
									<td id="horario_22V">&#160;</td>
									<td id="horario_22S">&#160;</td>
									<td id="horario_22D">&#160;</td>
								</tr>
								<tr>
									<th id="horario_23">23</th>
									<td id="horario_23L">&#160;</td>
									<td id="horario_23M">&#160;</td>
									<td id="horario_23C">&#160;</td>
									<td id="horario_23J">&#160;</td>
									<td id="horario_23V">&#160;</td>
									<td id="horario_23S">&#160;</td>
									<td id="horario_23D">&#160;</td>
								</tr>
			
							</table>
							<input type="hidden" id="valor_criterio_horario" />
							<input type="hidden" id="valor_vista_criterio_hora" />
			
						</div>
					</escapar>
				</bloque>
				
				<area_botones>
					<boton estilo="primary aceptar" accion="guardarHorario();">Aceptar</boton>
					<boton estilo="danger" accion="cancelar()">Cancelar</boton>
				</area_botones>
			</contenido>
		</ventana>
	</xsl:template>
</xsl:stylesheet>