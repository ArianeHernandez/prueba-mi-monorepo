<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ReporteDim.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina>
			<stylesheet>reportedim.css</stylesheet>
			<stylesheet>reportedim/consulta_saldo.css</stylesheet>
			<javascript>reportedim/consulta_saldo.js</javascript>
			<principal>
			
				<titulo icono="inicio">Consulta de Saldo</titulo>
					
				<contenido>
				
					<parrafo>
						<texto key="consulta_saldo"/>
					</parrafo>
	
					<xsl:call-template name="REPORTEDINAMICO">
						<xsl:with-param name="Reporte" select="//crearReporte/ReporteDinamico" />
					</xsl:call-template>
	
				</contenido>
			</principal>
	
		</pagina>
	</xsl:template>

</xsl:stylesheet>
