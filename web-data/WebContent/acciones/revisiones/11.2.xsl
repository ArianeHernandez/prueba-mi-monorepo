<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<javascript>revisiones/11.2.js</javascript>

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Cargas">

			<principal>
				<titulo>
					<texto key="CARGAS" /> de la revisión
					<xsl:value-of select="//obtenerRevisionPorId/Revision/nombre" />
				</titulo>
				<contenido>

					<xsl:variable name="total" select="//contarCargasRevision/numCargas" />
					
					<xsl:choose>
						<xsl:when test="$total>0">
							<tabla icono="service">
								<encabezado>
									<titulo>Identificador de <texto key="CARGA" /></titulo>
									<titulo>Deudor Solicitante</titulo>
									<titulo>Estado</titulo>
									<titulo>Fecha de carga de información</titulo>
<!-- 									<titulo>Tipo de <texto key="CARGA" /></titulo> -->
									<titulo>Usuario Solicitante</titulo>
<!-- 									<titulo>Valor</titulo> -->
<!-- 									<titulo>N° registros</titulo> -->
								</encabezado>
								<xsl:for-each select="//obtenerCargasRevision/Listado/Carga">
									<xsl:variable name="nusuario" select="nombre_usuario" />
									<xsl:variable name="npersona" select="nombre_persona" />
									<fila accion="mostrarDetalleCarga('{id_carga}','{id_formato}');">
										<valor>
											<xsl:value-of select="id_carga" />
										</valor>
										<valor>
											<xsl:if test="$nusuario=$npersona">
												<xsl:value-of select="nombre_usuario" />&#160;<xsl:value-of select="apellido_persona" />
												
											</xsl:if>
											<xsl:if test="$nusuario!=$npersona">
												<xsl:value-of select="nombre_usuario" />
											</xsl:if>
										</valor>
										<valor>
											<b><estado key="{estado}" cliente="S"/></b>
										</valor>
										<valor>
											<xsl:value-of select="fecha_subida" />
										</valor>
<!-- 										<valor> -->
<!-- 											<tipo_carga id="{id_tipocarga}"/> -->
<!-- 										</valor> -->
										<valor>
											<xsl:value-of select="concat(nombre_persona,' ',apellido_persona)" />
										</valor>
<!-- 										<valor> -->
<!-- 											<xsl:choose> -->
<!-- 												<xsl:when test="valor_total_bigdecimal!=''"> -->
<!-- 													<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/> -->
<!-- 												</xsl:when> -->
<!-- 												<xsl:otherwise> -->
<!-- 													NA -->
<!-- 												</xsl:otherwise> -->
<!-- 											</xsl:choose> -->
<!-- 										</valor> -->
<!-- 										<valor> -->
<!-- 											<xsl:value-of select="numero_registros_bigdecimal" /> -->
<!-- 										</valor> -->
									</fila>
								</xsl:for-each>
							</tabla>
							<formulario id="form_carga" destino="revisiones/11.3.do">
								<variable id="id_carga" />
								<variable id="id_formato" />
								<variable id="id_formato_salida" valor="{//obtenerProceso/Proceso/id_formato_salida}" />
							</formulario>
							<xsl:if test="$total != ''">
								<formulario id="form_pag" destino="revisiones/11.2.do">
									<paginacion id="_numeropagina" formulario="form_pag"
										numero="{//numeroPagina}" paginacion="{//TAMANO_PAGINA}"
										total="{$total}" />
								</formulario>
							</xsl:if>
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen <texto key="CARGAS" /> para esta revision
							</parrafo>
						</xsl:otherwise>
					</xsl:choose>


					<area_botones>
						<boton estilo="primary volver" destino="revisiones/11.1.do">Ir al listado de revisiones</boton>
					</area_botones>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
