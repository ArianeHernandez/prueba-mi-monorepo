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
					Radicación de <xsl:value-of select="//obtenerFormato/Formato/nombre" /> 
					<xsl:choose>
						<xsl:when test="count(ESAPROBADO)>0 and //obtenerCarga/Carga/estado='L'">
							Parcialmente Liberada
						</xsl:when>
						<xsl:when test="count(ESAPROBADO)>0">
							Exitosa
						</xsl:when>
						<xsl:when test="count(ESRECHAZADO)>0">
							Descartada
						</xsl:when>
						<xsl:when test="count(ESAPROBADO)=0 and count(ESRECHAZADO)=0">
							Fallida
						</xsl:when>
					</xsl:choose>
					 - <xsl:value-of select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>

					<xsl:choose>
						
						<xsl:when test="count(ESAPROBADO)>0 and //obtenerCarga/Carga/estado='L'">
							<xsl:call-template name="liberacion_parcial">
								<xsl:with-param name="volver_url">liberacion/15.1.do</xsl:with-param>
							</xsl:call-template>
						</xsl:when>
						
						<xsl:when test="count(ESAPROBADO)>0">
							<xsl:call-template name="aprobacion">
								<xsl:with-param name="volver_url">liberacion/15.1.do</xsl:with-param>
							</xsl:call-template>
						</xsl:when>
						
						<xsl:when test="count(ESRECHAZADO)>0">
							<xsl:call-template name="rechazo">
								<xsl:with-param name="volver_url">liberacion/15.1.do</xsl:with-param>
							</xsl:call-template>
						</xsl:when>
						
						<xsl:when test="count(ESAPROBADO)=0 and count(ESRECHAZADO)=0">
							<xsl:call-template name="fallo">
								<xsl:with-param name="volver_url">liberacion/15.1.do</xsl:with-param>
							</xsl:call-template>
						</xsl:when>
						
					</xsl:choose>

				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
