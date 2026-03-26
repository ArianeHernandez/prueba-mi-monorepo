<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/carga/mensajeAprobacionCarga.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Titulo">

			<principal>
				<titulo>
					<xsl:value-of select="//obtenerFormato/Formato/nombre" />
					-
					<xsl:value-of select="//obtenerPersona/Persona/nombre" />
					&#160;
					<xsl:value-of select="//obtenerPersona/Persona/apellido" />
					- <texto key="CARGA" /> -<xsl:value-of select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>
					<xsl:if test="count(ESAPROBADO)>0">
						<xsl:call-template name="aprobacion">
							<xsl:with-param name="volver_url">revisiones/11.1.do</xsl:with-param>
						</xsl:call-template>
					</xsl:if>

					<xsl:if test="count(ESRECHAZADO)>0">
						<xsl:call-template name="rechazo">
							<xsl:with-param name="volver_url">revisiones/11.1.do</xsl:with-param>
						</xsl:call-template>
					</xsl:if>

					<xsl:if test="count(ESAPROBADO)=0 and count(ESRECHAZADO)=0">
						<xsl:call-template name="fallo">
							<xsl:with-param name="volver_url">revisiones/11.1.do</xsl:with-param>
						</xsl:call-template>
					</xsl:if>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
