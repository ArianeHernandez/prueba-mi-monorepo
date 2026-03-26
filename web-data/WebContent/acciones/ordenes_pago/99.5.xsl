<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<pagina titulo="Consultar Órdenes de Pago Automáticas">
			
			<javascript>ordenes_pago/99.5.js</javascript>
			
			<stylesheet>99.css</stylesheet>
			
			<principal>
				<titulo icono="inicio">Consultar Órdenes de Pago Automáticas</titulo>
				<contenido>
					<div class="box-container">
					<bloque estilo="col panel panel-default">
												
						<titulo>Filtros de Búsqueda</titulo>
						<div class="panel-body">
							<div class=" form-horizontal">
								<registro>
									<item>Identificador</item>
									<valor>
										<cajatexto id="id_ftp_usuario_archivo" />
									</valor>
								</registro>
								
								<registro>
									<item>Formato</item>
									<valor>
										<cajatextoselector id="id_formato">
											<opcion valor="" texto="-- Seleccione --"/>
											<xsl:for-each select="listadoFormatos/Formato">
												<xsl:sort select="nombre" />
												<opcion valor="{id_formato}" texto="{nombre}" />
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Numero de Lote</item>
									<valor>
										<cajatexto id="id_carga" />
									</valor>
								</registro>
								
								<registro>
									<item>Fecha Desde</item>
									<valor>
										<cajafecha id="fecha_inicial" />
									</valor>
								</registro>
								
								<registro>
									<item>Nombre del Archivo</item>
									<valor>
										<cajatexto id="nombre" />
									</valor>
								</registro>
								
								<registro>
									<item>Fecha Hasta</item>
									<valor>
										<cajafecha id="fecha_final" />
									</valor>
								</registro>
								
								<registro>
									<item>Estado</item>
									<valor>
										<cajatextoselector id="estado">
											<opcion valor="" texto="-- Seleccione --"/>
											<opcion valor="T" texto="En Transito" />
											<opcion valor="P" texto="Procesado" />
											<opcion valor="F" texto="Fallido" />
										</cajatextoselector>
									</valor>
								</registro>
							</div>
						</div>
						<div class="panel-footer">
								<boton estilo="primary" accion="PAGINA_ACTUAL = 1; contarArchivos();">Buscar</boton>
								<boton estilo="danger" accion="limpiarFiltros();">Limpiar</boton>
						</div>
					</bloque>
					
					<variable id="totalArchivos" valor="{totalArchivos}" />
					<variable id="id_usuario" valor="{id_usuario}" />
					
					<div id="area_resultado" style="display:none">
						<div class="cuadro_reporte">
							Elementos por página:&#160;
							<select name="num_elems" id="num_elems" onchange="numeroElementos(this.options[this.selectedIndex].value)">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
							</select>
							&#160;&#160;&#160;
							<boton estilo="primary" accion="descargarReporte();">Descargar Reporte</boton>
						</div>
						<div class="tabla_borde table-responsive">
								<table class="table table-hover table-striped">
								<thead>
									<tr class="tabla_encabezado">
										<td>ID</td>
										<td class="w030">No. Lote</td>
										<td>Nombre del Archivo</td>
										<td>Fecha</td>
										<td>Formato</td>
										<td class="w030"># Reg.</td>
										<td class="w100">Valor Total</td>
										<td class="w070">Estado</td>
										<td class="w100">Característica</td>
										<td class="w030"></td>
									</tr>
								</thead>
								
								<tbody id="archivos_obtenidos">
									<xsl:for-each select="listadoArchivos/FtpUsuarioArchivo">
									<tr>
										<xsl:attribute name="class">
											<xsl:choose>
												<xsl:when test="duplicado = 'S'">tabla_fila_duplicado</xsl:when>
												<xsl:otherwise>tabla_fila</xsl:otherwise>
											</xsl:choose>
										</xsl:attribute>
										<td><xsl:value-of select="id_ftp_usuario_archivo" /></td>
										<td><xsl:value-of select="id_carga" /><xsl:if test="count(id_carga)=0">--</xsl:if></td>
										<td><xsl:value-of select="nombre" /></td>
										<td><xsl:value-of select="fecha_cargue" /></td>
										<td><xsl:value-of select="nombre_formato" /></td>
										<td><xsl:value-of select="total_registros" /><xsl:if test="count(total_registros)=0">--</xsl:if></td>
										<td class="rgh">$ <xsl:value-of select="format-number(valor_total, '###.##0,00', 'pesos')"/></td>
										<td><xsl:value-of select="estado_formato" /></td>
										<td><xsl:value-of select="caracteristica" /></td>
										<td>
											<boton accion="verDuplicados({id_ftp_usuario_archivo})">
												<xsl:attribute name="visible">
													<xsl:choose>
														<xsl:when test="duplicado = 'S'">true</xsl:when>
														<xsl:otherwise>false</xsl:otherwise>
													</xsl:choose>	
												</xsl:attribute>
												Duplicados
											</boton>
											&#160;
										</td>
									</tr>
									</xsl:for-each>
								</tbody>
							</table>
						</div>
						<div class="paginacion">
							<table class="tabla_paginacion" border="0" cellspacing="0"
								cellpadding="0" width="350px">
								<tr>
									<td width="22px">
										<a onclick="paginacion_inicio()">
											<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero" />
										</a>
									</td>
	
									<td width="22px">
										<a onclick="paginacion_anterior()">
											<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior" />
										</a>
									</td>
	
									<td>
										<p id="texto pagina">--</p>
									</td>
	
									<td width="22px">
										<a onclick="paginacion_siguiente()">
											<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente" />
										</a>
									</td>
	
									<td width="22px">
										<a onclick="paginacion_final()">
											<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo" />
										</a>
									</td>
									
									<td>
										<p id="texto registros">--</p>
									</td>
									
								</tr>
							</table>
						</div>
					</div>
					
					<div class="alert alert-danger" id="div_no_resultados">
						<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> No se encontraron resultados.
					</div>
					
					<div style="display:none">
						<table icono="service">
							<tbody id="PLANTILLA_ARCHIVOS">
								<tr class="tabla_fila[ 10 ]">
									<td>[ 1 ]</td>
									<td>[ 2 ]</td>
									<td>[ 3 ]</td>
									<td>[ 4 ]</td>
									<td>[ 5 ]</td>
									<td>[ 6 ]</td>
									<td class="rgh">[ 7 ]</td>
									<td>[ 8 ]</td>
									<td>[ 9 ]</td>
									<td><boton visible="false" id="boton_duplicados_[ 1 ]" accion="verDuplicados([ 1 ])">Duplicados</boton></td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<area_botones>
						<boton estilo="primary volver" destino="inicio/0.do">Volver</boton>
					</area_botones>
					</div>
				</contenido>
			</principal>
			
		</pagina>
		
		<ventana id="vn_reporte" icono="confirmacion">
			<titulo>Generando Reporte</titulo>
			<contenido>

				<bloque estilo="grupo">

					<div class="cr_notificacion">Espere mientras se genera el reporte</div>
					
				</bloque>
			</contenido>
		</ventana>
		
		<ventana id="vn_duplicados" icono="confirmacion">
			<titulo>Detalle de los Archivos Duplicados</titulo>
			<contenido>
				<bloque estilo="grupo" id="area_configuracion">
					
					<div id="area_duplicados">--</div>
					
					<div id="PLANTILLA_REG_DUPLICADO" style="display:none">
						<registro>
							<item>[ 1 ]</item>
							<valor>[ 2 ]</valor>
						</registro>
					</div>
				</bloque>
				
				<area_botones>
					<boton estilo="danger" accion="cerrarVentana();">Cerrar</boton>
				</area_botones>
			</contenido>
		</ventana>
		
	</xsl:template>
	
</xsl:stylesheet>