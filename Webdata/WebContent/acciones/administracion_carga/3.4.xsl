<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Titulo">

			<principal>
				<titulo>
					<xsl:value-of
						select="//obtenerFormato/Formato/nombre" />
					-
					<xsl:value-of
						select="//obtenerPersona/Persona/nombre" />
					 
					<xsl:value-of
						select="//obtenerPersona/Persona/apellido" />
					-&#160;<texto key="CARGA" />&#160;
					<xsl:value-of
						select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>
				<div class="box-container">
				
					<xsl:if test="count(ESAPROBADO)>0">
						<bloque-contenido>
							<titulo>Confirmación de Aprobación</titulo>
							<contenido>
								<xsl:choose>
									<xsl:when
										test="cambiarEstado/exitoso='true'">
										<parrafo estilo="resaltado">
											<texto key="LA CARGA HA SIDO APROBADA" />
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/3.3.do">
												Volver
											</boton>
										</area_botones>
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											<texto key="NO SE HA APROBADO LA CARGA" />
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/3.3.do">
												Volver
											</boton>
										</area_botones>
									</xsl:otherwise>
								</xsl:choose>
								<parrafo></parrafo>
							</contenido>
						</bloque-contenido>
					</xsl:if>

					<xsl:if test="count(ESRECHAZADO)>0">
						<bloque-contenido>
							<titulo>Confirmación de Rechazo</titulo>
							<contenido>
								<xsl:choose>
									<xsl:when
										test="cambiarEstado/exitoso='true'">
										<parrafo estilo="resaltado">
											<texto key="LA CARGA HA SIDO RECHAZADA" />
										</parrafo>
										<area_botones>
											<xsl:choose>
												  <xsl:when test="autorizado='true'">
												  	<boton estilo="primary volver" destino="administracion_carga/3b.do">Volver</boton>
												  </xsl:when>
												  <xsl:otherwise>
												  	<boton estilo="primary volver" destino="administracion_carga/3.2.do">Volver</boton>
												  </xsl:otherwise>
											</xsl:choose>
										</area_botones>
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											<texto key="LA CARGA NO HA SIDO RECHAZADA" />
										</parrafo>
										<area_botones>
											<boton estilo="primary volver"
												destino="administracion_carga/3.3.do">
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
							<xsl:choose>
							  <xsl:when test="autorizado='true'">
							  	<boton estilo="primary volver" destino="administracion_carga/3b.do">Volver</boton>
							  </xsl:when>
							  <xsl:otherwise>
							  	<boton estilo="primary volver" destino="administracion_carga/3.2.do">Volver</boton>
							  </xsl:otherwise>
							</xsl:choose>
						</area_botones>
					</xsl:if>

				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
