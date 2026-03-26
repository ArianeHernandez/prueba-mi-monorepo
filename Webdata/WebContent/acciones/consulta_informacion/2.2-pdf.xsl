<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:include href="context://common/xsl/osm_page.xsl"/>
    <xsl:include href="context://componentes/menu/acciones.xsl"/>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
        <pagina titulo="Consulta de Informacion">
            <principal>
                <titulo>Consultar Información - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/> - <texto key="CARGA" /> </titulo>
                <contenido>
                <div class="box-container">
                
                    <xsl:if test="count(//obtenerDatosCargaPorFormato)>0">
                        <bloque-contenido>
                            <titulo>Detalle</titulo>
                            <contenido>
                                <xsl:for-each select="//obtenerDatosCargaPorFormato/nodo">
                                    <xsl:call-template name="visualizacion-nodo"/>
                                </xsl:for-each>
                            </contenido>
                        </bloque-contenido>
                        <firma>
                            <encabezado>
                                <titulo>
                					Impreso Por:
                				</titulo>
                                <titulo>
                                    <xsl:value-of select="//OSM-INIT-SESSION/Info/Persona/nombreCompleto"/>
                                </titulo>
                                <titulo>
                					Responsable:
                				</titulo>
                                <titulo>
                					________________________
                				</titulo>
                            </encabezado>
                            <fila>
                                <valor/>
                                <valor>
                					________________________
                				</valor>
                                <valor/>
                                <valor>
                					________________________
                				</valor>
                            </fila>
                        </firma>
                    </xsl:if>
                
                </div>    
                </contenido>
            </principal>
        </pagina>
    </xsl:template>
    <xsl:template name="visualizacion-nodo">
        <xsl:choose>
            <xsl:when test="@type='list'">
                <ocultable textooculto="ver" textovisible="ocultar" visible="false">
                    <xsl:if test="count(element)>1">
                        <xsl:attribute name="textooculto">ver (<xsl:value-of select="count(element)"/>)</xsl:attribute>
                    </xsl:if>
                    <tabla>
                        <encabezado>
                            <xsl:for-each select="element[1]/nodo">
                                <xsl:if test="string-length(@value)>0">
                                    <titulo>
                                        <xsl:value-of select="@name"/>
                                    </titulo>
                                </xsl:if>
                                <xsl:if test="@name='tercero'">
                                    <titulo>
                                        <xsl:value-of select="@name"/>
                                    </titulo>
                                </xsl:if>
                            </xsl:for-each>
                        </encabezado>
                        <xsl:for-each select="element">
                            <fila>
                                <xsl:for-each select="nodo">
                                    <xsl:if test="string-length(@value)>0">
                                        <valor>
                                            <xsl:call-template name="visualizacion-nodo"/>
                                        </valor>
                                    </xsl:if>
                                    <xsl:if test="@name='tercero'">
                                        <valor>
                                            <xsl:call-template name="visualizacion-nodo"/>
                                        </valor>
                                    </xsl:if>
                                </xsl:for-each>
                            </fila>
                        </xsl:for-each>
                    </tabla>
                </ocultable>
            </xsl:when>
            <xsl:when test="@type='object'">
                <bloque style="color:{@colorletra}; background-color: {@colorfondo}; padding: 5px">
                    <xsl:value-of select="nodo[@name = 'numero de identificacion']/@value"/>
                </bloque>
            </xsl:when>
            <xsl:otherwise>
                <bloque style="color:{@colorletra}; background-color: {@colorfondo}; padding: 5px">
                    <xsl:value-of select="@value"/>
                </bloque>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
