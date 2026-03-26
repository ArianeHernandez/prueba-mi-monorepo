<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		<xsl:variable name="formato" select="//obtenerFormato/Formato" />
		
		<pagina titulo="Solicitud relacionada por formato">
			
			<javascript>carga_informacion/interactivo/1.0.js</javascript>
			
			<principal>
				<titulo><texto key="CARGA RELACIONADA POR FORMATO" /></titulo>
				<contenido>
				<div class="box-container">	
					<xsl:if test="obtenerFormato/Formato/cargas_relacionadas='S'">
						<bloque estilo="grupo">
						<subtitulo ><texto key="SELECCION DE LA CARGA RELACIONADA" /></subtitulo>
							<registro>
							<item><texto key="CARGA" /></item>
							<valor>
								<cajatextoselector id="Formato.idformato_carga_relacionada" valor="{//obtenerFormato/Formato/idformato_carga_relacionada}" accion="guardarCargaRelaciona(this.value)">
									<opcion valor="" texto="-- Seleccione --"/>
									<xsl:for-each select="//obtenerCargasRelacionadasPorCliente/listado/Carga">
									<opcion valor="{id_carga}" texto="{concat(id_carga, ' - ', nombre, ' - ', fecha_carga) }" />
									</xsl:for-each>
								</cajatextoselector>
								
							</valor>
						</registro>
							
						</bloque>
					</xsl:if>					
					<area_botones>
						<boton estilo="siguiente" destino="carga_informacion/interactivo/1.1.do">Siguiente</boton>
					</area_botones>
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

	
</xsl:stylesheet>
