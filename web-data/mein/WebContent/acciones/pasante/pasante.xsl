<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/EjecutarAccion.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Confirmacion">
			<javascript>pasante/pasante.js</javascript>
			<javascript>archivos_adjuntos/archivos_adjuntos.js</javascript>
			<javascript>archivos_adjuntos/s3_archivos_adjuntos.js</javascript>
			<stylesheet>reportePersonalizado/reporte.css</stylesheet>
			<principal>

				<contenido id="id_contenido">
					
					<xsl:call-template name="LISTAR_ACCIONES" />
					
					<div class="login-content" style="height:600px; width: 100%">
						<input type="hidden" id="id_carga" value="{//id_carga}" />
						<input type="hidden" id="rol" value="{//rol_pasante}"/>
						<noscript> You need to enable JavaScript to use this webpage. </noscript>
					
							<iframe name="frame-id" src="{//pasanteFront}" frameborder="0" style="overflow:hidden;height:100%;width:100%" height="100%" width="100%"></iframe>
						
					</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
