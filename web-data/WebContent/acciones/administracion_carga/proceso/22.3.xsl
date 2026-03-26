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
					<texto key="CARGA" />-	<xsl:value-of select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>
					<div class="box-container">
					<xsl:if test="count(ESAPROBADO)>0 and //finalizarRelacionesCargaInstancia/exitosa = 'true'" >
						<bloque-contenido>
							<titulo>Confirmación de Aprobación</titulo>
							<contenido>
								<xsl:choose>
									<xsl:when
										test="cambiarEstado/exitoso='true'">
										<parrafo estilo="resaltado">
											La <texto key="CARGA" />&#160;
											<xsl:value-of
												select="obtenerCarga/Carga/id_carga" />
											ha sido aprobado
											exitosamente.
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/proceso/22.1.do">
												Volver
											</boton>
										</area_botones>
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											No se ha podido aprobar la
											<texto key="CARGA" />. Por favor intente
											nuevamente.
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/proceso/22.2.do">
												Volver
											</boton>
										</area_botones>
									</xsl:otherwise>
								</xsl:choose>
								<parrafo></parrafo>
							</contenido>
						</bloque-contenido>
					</xsl:if>

					<xsl:if test="count(ESRECHAZADO)>0 and //finalizarRelacionesCargaInstancia/exitosa = 'true'">
						<bloque-contenido>
							<titulo>Confirmación de Rechazo</titulo>
							<contenido>
								<xsl:choose>
									<xsl:when
										test="cambiarEstado/exitoso='true'">
										<parrafo estilo="resaltado">
											La <texto key="CARGA" />&#160;
											<xsl:value-of
												select="obtenerCarga/Carga/id_carga" />
											ha sido rechazada
											exitosamente.
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/proceso/22.1.do">
												Volver
											</boton>
										</area_botones>
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											No se ha podido rechazar la
											<texto key="CARGA" />. Por favor intente
											nuevamente.
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/proceso/22.1.do">
												Volver
											</boton>
										</area_botones>
									</xsl:otherwise>
								</xsl:choose>
								<parrafo></parrafo>
							</contenido>
						</bloque-contenido>
					</xsl:if>

					<xsl:if
						test="count(ESAPROBADO)=0 and count(ESRECHAZADO)=0">
						<parrafo estilo="resaltado">
							No se puede realizar la operación.
						</parrafo>
						<area_botones>
							<boton estilo="primary volver"
								destino="administracion_carga/proceso/22.2.do">
								Volver
							</boton>

						</area_botones>
					</xsl:if>

				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
