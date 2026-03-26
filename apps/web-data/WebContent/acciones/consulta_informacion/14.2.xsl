<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<javascript>consulta_informacion/14.2.js</javascript>

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Cargas">

			<principal>
				<titulo> Listado de <texto key="CARGAS" /></titulo>
				<contenido>
				<div class="box-container">
					
					<xsl:variable name="total" select="//contarCargasRevision/numCargas" />
					<xsl:choose>
						<xsl:when test="$total>0">
							<tabla icono="service">
								<encabezado>
									<titulo>Estado</titulo>
									<titulo>Identificador <texto key="CARGA" /></titulo>
									<titulo>Nombre</titulo>
									<titulo>Fecha de <texto key="CARGA" /></titulo>
									<titulo>Tipo de <texto key="CARGA" /></titulo>	
									<titulo>Usuario</titulo>
									<titulo>Valor</titulo>
									<titulo>N° registros</titulo>
									
								</encabezado>
								<xsl:for-each select="//obtenerCargasRevision/Listado/Carga">
									<fila accion="mostrarDetalleCarga('{id_carga}');">
										<valor>
											<xsl:attribute name="class">
												<xsl:choose>
													<xsl:when test="estado='C' or estado='A'">bg_green</xsl:when>
													<xsl:when test="estado='T' or estado='E' or estado='R'">bg_red</xsl:when>
												</xsl:choose>
											</xsl:attribute>	
											<b style="color:inherit;"><estado key="{estado}" cliente="S"/></b>
										</valor>
										<valor>
											<xsl:value-of select="id_carga" />
										</valor>
										<valor>
											<xsl:value-of select="nombre" />
										</valor>
										<valor>
											<xsl:value-of select="fecha_subida" />
										</valor>
										<valor>
											<tipo_carga id="{id_tipocarga}"/>
										</valor>
										<valor>
											<xsl:value-of select="concat(nombre_persona,' ',apellido_persona)" />
										</valor>
										<valor>
											<xsl:choose>
												<xsl:when test="valor_total_bigdecimal!=''">
													$<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
												</xsl:when>
												<xsl:otherwise>
													NA
												</xsl:otherwise>
											</xsl:choose>
										</valor>
										<valor>
											<xsl:value-of select="numero_registros_bigdecimal" />
										</valor>
									</fila>
								</xsl:for-each>
							</tabla>
							<formulario id="form_carga" destino="consulta_informacion/14.3.do">
								<variable id="id_carga" />
							</formulario>
							<xsl:if test="$total != ''">
								<formulario id="form_pag" destino="consulta_informacion/14.2.do">
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
						<boton estilo="primary volver" destino="consulta_informacion/14.1.do">Ver Procesos</boton>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
				</div>	
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
