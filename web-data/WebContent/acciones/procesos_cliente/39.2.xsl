<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Administración de Procesos">

			<javascript>procesos_cliente/39.2.js</javascript>
			<stylesheet>10.css</stylesheet>
					
			<principal>
				<titulo>Administración de Procesos</titulo>
				<contenido>

					<bloque-pestanas>

						<pestana titulo="Información Basica">

							<formulario id="form_edicion" destino="crear_proceso/10.2.do">

								<variable id="Proceso.id_proceso" valor="{proceso/id_proceso}" />

								<registro>
									<item>Nombre del Proceso</item>
									<valor>
										<cajatexto id="Proceso.nombre" valor="{proceso/nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item><texto key="GRUPO FORMATO" /></item>
									<valor>
										<cajatextoselector id="Proceso.id_grupoformato" valor="{proceso/id_grupoformato}">
											<opcion valor="" texto="-- Seleccione --"/>
											<xsl:for-each select="//obtenerTodosLosGruposformato/Listado/GrupoFormato">
												<opcion valor="{id_grupoformato}" texto="{nombre}" />
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>

								<registro>
									<item>Formato de Salida</item>
									<valor>
										<cajatextoselector id="Proceso.id_formato_salida"
											valor="{proceso/id_formato_salida}" accion="guardarProceso()">
											<opcion valor="">-- Seleccione --</opcion>
											<xsl:for-each select="formatos/Formato[tipoformato='S']">
												<xsl:sort select="nombre" />
												<opcion valor="{id_formato}">
													<xsl:value-of select="nombre"></xsl:value-of>
												</opcion>
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>
								
								

								<registro>
									<item>Activo usuario</item>
									<valor>
										<cajachequeo id="Proceso.estado" valor="A"
											seleccionado="{boolean(proceso/estado='A')}" />
									</valor>
								</registro>
							</formulario>

							<bloque-contenido>
								<titulo icono="edicion">Formatos de Entrada</titulo>
								<contenido>
									<div id="div_formatosE">
										<xsl:for-each select="//obtenerFormatosProceso/listado/Formato">
											<div class="cuadro_fila" id="fila_{id_formato}" name="fila_formato">
												<div class="cuadro_fila_item w20">
													<div class="eliminar eliminarFormatosEntrada" onclick="eliminarFormato({id_formato})"></div>
												</div>
												<div class="cuadro_fila_item w250">
													<p>
														<xsl:value-of select="nombre" />
													</p>
												</div>
											</div>
										</xsl:for-each>
									</div>
									<div class="cuadro_info_footer">
										<cajatextoselector accion="agregarFormato(this.id)"
											id="select_formatoE">
											<opcion valor="">-- seleccione --</opcion>
											<xsl:for-each select="formatos/Formato[tipoformato='E']">
												<xsl:sort select="nombre" />
												<opcion valor="{id_formato}">
													<xsl:value-of select="nombre"></xsl:value-of>
												</opcion>
											</xsl:for-each>
										</cajatextoselector>
									</div>
								</contenido>
							</bloque-contenido>
						</pestana>

					</bloque-pestanas>

					<area_botones>
						<boton estilo="primary volver" destino="procesos_cliente/39.1.do">Volver</boton>
					</area_botones>


					<div style="display:none" id="TEMPLATE_FILA_FORMATO">
						<div class="cuadro_fila" id="fila_[ 1 ]" name="fila_formato">
							<div class="cuadro_fila_item w20">
								<div class="eliminar eliminarFormatosEntrada" onclick="eliminarFormato('[ 1 ]')"></div>
							</div>
							<div class="cuadro_fila_item w250">
								<p>
									[ 2 ]
								</p>
							</div>
						</div>
					</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
