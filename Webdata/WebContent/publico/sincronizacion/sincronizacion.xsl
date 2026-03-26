<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Sincronizacion SIFI">
			
			<principal>
				<titulo icono="estructura">Sincronizacion SIFI</titulo>
				<contenido>
		
					<bloque-contenido>
						<titulo>Sincronizacion Estado Orden de Operación</titulo>
						<contenido>
							<xsl:choose>
								<xsl:when test="//sincEstOrdenOperacion/exito='true'">
									<parrafo>El envío ha sido todo un éxito.</parrafo>
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">No se puede realizar la operación.</parrafo>
								</xsl:otherwise>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					<bloque-contenido>
						<titulo>Sincronizacion Orden de Pago</titulo>
						<contenido>
							<xsl:choose>
								<xsl:when test="//sincEstOrdenOperacion/exito='true'">
									<parrafo>El envío ha sido todo un éxito.</parrafo>
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">No se puede realizar la operación.</parrafo>
								</xsl:otherwise>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					<bloque-contenido>
						<titulo>Sincronizacion Estado Orden de Pago</titulo>
						<contenido>
							<xsl:choose>
								<xsl:when test="//sincEstOrdenPago/exito='true'">
									<parrafo>El envío ha sido todo un éxito.</parrafo>
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">No se puede realizar la operación.</parrafo>
								</xsl:otherwise>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					<bloque-contenido>
						<titulo>Sincronizacion Instrucción de Pago</titulo>
						<contenido>
							<xsl:choose>
								<xsl:when test="//sincInstPago/exito='true'">
									<parrafo>El envío ha sido todo un éxito.</parrafo>
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">No se puede realizar la operación.</parrafo>
								</xsl:otherwise>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					<area_botones>
						<boton estilo="primary volver" destino="administracion_carga/3.3.do">Volver</boton>
					</area_botones>
					
				</contenido>
				
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
