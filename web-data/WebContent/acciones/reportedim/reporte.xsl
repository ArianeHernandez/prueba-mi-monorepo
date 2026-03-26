<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:include href="context://common/xsl/osm_page.xsl"/>
    <xsl:include href="context://componentes/menu/acciones.xsl"/>
    <xsl:include href="context://stylesheets/templates/ReporteDim.xsl"/>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
        <pagina>
            <stylesheet>reportedim.css</stylesheet>
            <principal>
            	
                <contenido>
                    <xsl:choose>
                        <xsl:when test="//crearReporte/ReporteDinamico != ''">
                            <xsl:call-template name="REPORTEDINAMICO">
                                <xsl:with-param name="Reporte" select="//crearReporte/ReporteDinamico"/>
                            </xsl:call-template>
                        </xsl:when>
                        <xsl:otherwise>
							<div class="alert alert-danger">
								<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> Lo sentimos, el reporte no está disponible en el momento.<br/> Intente <b>reiniciar sesión</b> o consulte al Administrador del Sistema.
							</div>
						</xsl:otherwise>
                    </xsl:choose>
                </contenido>
            	
            </principal>
        </pagina>
    </xsl:template>
</xsl:stylesheet>
