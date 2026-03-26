<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	<xsl:include href="context://common/xsl/osm_page.xsl" />



	<xsl:template match="OSM-ACCION">

		<pagina titulo="Detalles de error">

			<principal>
				
				<titulo>Detalle Errores Carga</titulo>
			
				<contenido>
					
					<bloque>
						
						<subtitulo>Fecha y Hora de Consulta: <xsl:value-of select="../STRING_TIME_FORMATTED"/></subtitulo>
						<subtitulo>Nombre de Usuario: <xsl:value-of select="../OSM-INIT-SESSION/Info/Persona/nombreCompleto"/></subtitulo>
						<subtitulo>Nombre de Cliente: <xsl:value-of select="concat(obtenerCarga/Carga/nombre_usuario, ' ', obtenerCarga/Carga/apellido_usuario)"/></subtitulo>
						
						<subtitulo>Numero de la Carga: <xsl:value-of select="id_carga_seleccionada" /></subtitulo>
						
					</bloque>
					
					<tabla>
						<encabezado>
							<titulo>FECHA</titulo>
							<titulo>COD ERROR</titulo>
							<titulo colspan="3">MENSAJE</titulo>
							<titulo colspan="3">INFORMACIÓN DE REGISTRO</titulo>
						</encabezado>
						<xsl:for-each select="obtenerLogErrorEjecucionesCarga/listado/Log">
							<xsl:sort data-type="number" select="id_log" />
							<fila>
								<valor>
									<xsl:value-of select="fecha"></xsl:value-of>
								</valor>
								<valor>
									<xsl:value-of select="cod_error"></xsl:value-of>
								</valor>
								<valor colspan="3">
									<xsl:value-of select="descripcion"></xsl:value-of>
								</valor>
								<valor>
									<xsl:value-of select="ref1"></xsl:value-of>
								</valor>
								<valor>
									<xsl:value-of select="ref2"></xsl:value-of>
								</valor>
								<valor>
									<xsl:value-of select="ref3"></xsl:value-of>
								</valor>
							</fila>
						</xsl:for-each>
					</tabla>

				</contenido>
			</principal>

		</pagina>

	</xsl:template>




</xsl:stylesheet>