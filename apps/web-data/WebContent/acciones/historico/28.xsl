<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:include href="context://common/xsl/osm_page.xsl"/>
    <xsl:include href="context://componentes/menu/acciones.xsl"/>
    <xsl:include href="context://stylesheets/templates/BuscarCargas.xsl"/>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
        <pagina titulo="Históricos">
            <principal>
                <titulo>Históricos</titulo>
                <contenido>
                	<texto key="MENSAJE ALERTA HISTORICO" ocultar_vacio="true"/>
					<xsl:call-template name="BUSCARCARGAS">
						<xsl:with-param name="formatos" select="//obtenerFormatosPorCliente/listado/Formato[tipoformato='E']"/>
						<xsl:with-param name="url_volver">historico/28.do</xsl:with-param>
					</xsl:call-template>
				
                </contenido>
            </principal>
            <xsl:call-template name="VENTANA_BUSCARCARGAS"/>
        </pagina>
    </xsl:template>
</xsl:stylesheet>
