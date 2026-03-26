<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Confirmación de Aprobación">

			<principal>
				<titulo>
					<xsl:value-of
						select="//obtenerFormato/Formato/nombre" />
					-
					<texto key="CARGA" />
					<xsl:value-of
						select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>
				<div class="box-container">
					
					<xsl:if test="count(//ejecutarAccionDeInstanciaActual/exitosa)>0">
						<bloque-contenido>
							<titulo>Confirmación de Aprobación</titulo>
							<contenido>
								<xsl:choose>
									<xsl:when
										test="//ejecutarAccionDeInstanciaActual/exitosa='true'">
										<parrafo estilo="resaltado">
											Transaccion exitosa. La <texto key="CARGA" />&#160;
											<xsl:value-of
												select="obtenerCarga/Carga/id_carga" />
											ha pasado a la siguiente instancia del proceso.
											<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/proceso/22.1.1.do">
												Volver
											</boton>
										</area_botones>
											
										</parrafo>
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											No se ha podido realizar la transacción.
											La <texto key="CARGA" /> no ha pasado a la siguiente instancia del proceso. Por favor intente
											nuevamente.
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/proceso/22.2.1.do">
												Volver
											</boton>
										</area_botones>
									</xsl:otherwise>
								</xsl:choose>
								<parrafo></parrafo>
							</contenido>
						</bloque-contenido>
					</xsl:if>
				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
