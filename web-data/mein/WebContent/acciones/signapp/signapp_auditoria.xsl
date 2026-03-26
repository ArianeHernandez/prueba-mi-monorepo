<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Signapp Auditoria">
			<javascript>signapp/signapp_auditoria.js</javascript>
			<stylesheet>reportePersonalizado/reporte.css</stylesheet>
			<principal>
				<contenido id="id_contenido">
					<div class="login-content" style="height:500px; width: 100%" id="div_frame">
						<noscript> You need to enable JavaScript to use this webpage. </noscript>
						<iframe name="frame-id" id="frame-id" src="{//signappAuditoriaEndpoint}" frameborder="0" style="overflow:hidden;height:100%;width:100%" height="100%" width="100%" onload=""></iframe>
					</div>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>

</xsl:stylesheet>
