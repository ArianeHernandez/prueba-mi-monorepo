<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Carga de Información">
			
			<javascript>consulta_informacion/14.1.js</javascript>
			
			<principal>
				<titulo>Consulta de Información</titulo>
				<contenido>
				<div class="box-container">	
					<xsl:choose>
						<xsl:when test="count(//listarProcesosPreparadorContar/Listado/Proceso)>0">
							
							<texto key="MENSAJE ALERTA HISTORICO" ocultar_vacio="true"/>
							
						 	<tabla icono="service">
								<encabezado>
									<titulo>Proceso</titulo>
									<titulo>Solicitudes Asociadas</titulo>
								</encabezado>
								<xsl:for-each select="//listarProcesosPreparadorContar/Listado/Proceso">
									<fila accion="enviar('{id_proceso}', '{id_formato_salida}');">
										<valor><b><xsl:value-of select="nombre"/></b></valor>
										<valor><xsl:value-of select="total_cargas"/></valor><!--agregar consulta de datos -->
									</fila>
								</xsl:for-each>
							</tabla>
							<formulario id="form_proceso" destino="consulta_informacion/14.2.do">
								<variable id="id_proceso" />
								<variable id="id_formato_salida" />
							</formulario>
							<xsl:variable name="total" select="//contarProcesosPreparador/Total" />
							<xsl:if test="$total != ''">
								<formulario id="form_pag" destino="consulta_informacion/14.1.do">
									<paginacion id="_numeropagina" formulario="form_pag" numero="{//numeroPagina}" 
										paginacion="{//TAMANO_PAGINA}" total="{$total}"/>
								</formulario>
							</xsl:if>
								
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen procesos asociados a su usuario.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
