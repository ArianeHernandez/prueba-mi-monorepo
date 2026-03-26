<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="LISTAR_ACCIONES">
		<javascript>templates/ejecutarAccion.js</javascript>
		<div style="display:none">
			<formulario destino="administracion_carga/proceso/22.4.do" id="frm_accion">
                <variable id="id_instancia" valor="{//id_instancia}"/>
                <variable id="id_formato_salida" valor="{//id_formato_salida}"/>
                <variable id="id_accion" valor=""/>
                <!-- OBJETO INSTANCIA_ACCION-->
                <variable id="InstanciaAccion.id_instancia" valor="{//id_instancia}"/>
                <variable id="InstanciaAccion.id_accion" valor=""/>
                <variable id="InstanciaAccion.id_proceso_admin" valor="{//id_proceso_admin}"/>
                <variable id="InstanciaAccion.id_administrativo" valor="{//id_administrativo}"/>
                <variable id="InstanciaAccion.id_carga" valor="{//id_carga}"/>
            </formulario>
             
            <xsl:if test="count(//obtenerAccionesConInstanciaDestinoPorInstanciaOrigen/listado/Accion)>0">
                <xsl:for-each select="//obtenerAccionesConInstanciaDestinoPorInstanciaOrigen/listado/Accion">
		        	<xsl:if test="count(oculto)>0 and oculto='S'">        
		                <variable id="{nombre}"  valor="{id_accion}"/>
	               </xsl:if>
                </xsl:for-each>
            </xsl:if>
             
		</div>
	</xsl:template>
</xsl:stylesheet>