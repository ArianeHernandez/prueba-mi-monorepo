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
                <div class="box-container form-horizontal">
							<xsl:call-template name="BUSCARCARGAS">
								<xsl:with-param name="filtro_cliente">S</xsl:with-param>
								<xsl:with-param name="filtro_negocio">S</xsl:with-param>
								<xsl:with-param name="formatos" select="//obtenerFormatosPorTipo/Listado/Formato"/>
								<xsl:with-param name="url_volver">historico/27.do</xsl:with-param>
							</xsl:call-template>
					
				</div>
                </contenido>
            </principal>
            <xsl:call-template name="VENTANA_BUSCARCARGAS"/>
        </pagina>
    </xsl:template>
</xsl:stylesheet>
