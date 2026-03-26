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
			<javascript>salida_banco/descarga.js</javascript>
			
			<principal>
			
				<titulo icono="inicio">Archivos Salida a Banco</titulo>
					
				<contenido>
				<div class="box-container form-horizontal">
	
					<xsl:call-template name="REPORTEDINAMICO">
						<xsl:with-param name="Reporte" select="//crearReporte/ReporteDinamico" />
					</xsl:call-template>
					
					
					<formulario id="generarArchivoForm" destino="ArchivoBanco">
						<variable id="id_archivo" valor=""/>
						<variable id="nombre_archivo_d" valor=""/>
					</formulario>
				</div>
				</contenido>
			</principal>
	
		</pagina>
	</xsl:template>

</xsl:stylesheet>
