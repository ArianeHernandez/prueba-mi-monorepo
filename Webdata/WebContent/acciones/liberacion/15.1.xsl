<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Procesos">
			
			<javascript>liberacion/15.1.js</javascript>
			
			<principal>
				<titulo>Solicitudes pendientes por enviar</titulo>
				<contenido>
					
					<xsl:choose>
						<xsl:when test="count(//listarProcesosLiberador/Listado/Proceso)>0">
							
							
							<xsl:for-each select="//obtenerTodosLosGruposformato/Listado/GrupoFormato">
								<xsl:variable name="grupoformato" select="id_grupoformato"/>
								
								<xsl:if test="count(//listarProcesosLiberador/Listado/Proceso[id_grupoformato = $grupoformato])>0">
							
									<div id="div_grupo_{id_grupoformato}" class="bloqueestilo_resultado"
										>
										<xsl:if test="count(//obtenerTodosLosGruposformato/Listado/GrupoFormato) > 1">
											<xsl:attribute name="onclick">toggleProcesosPorGrupo('<xsl:value-of select="id_grupoformato"/>')</xsl:attribute>
										</xsl:if>
										<h1 class="">
											<xsl:value-of select="nombre"/> 
										</h1>
										<div style="margin-left:10px; margin-right:10px">
											<xsl:value-of select="descripcion"/> 
										</div>
										
										<div style="margin:10px;" id="div_procesos_grupo_{id_grupoformato}">
											<bloque >
												<div class="tabla_borde" >
													<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
														<thead>
															<tr class="tabla_encabezado">
																<td style="width:70%">Proceso</td>
																<td><texto key="CARGAS" /> asociadas</td>
															</tr>
														</thead>
														<tbody id="cont_formatos">
															<xsl:for-each select="//listarProcesosLiberador/Listado/Proceso[id_grupoformato = $grupoformato]">
														
																<tr onclick="enviar('{id_proceso}','{id_formato_salida}');" class="tabla_fila"
																		onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
																		<td style="width:70%"><xsl:value-of select="nombre"/></td>
																		<td><xsl:value-of select="total_cargas"/></td>
																</tr>
															</xsl:for-each>
																		
														</tbody>
													</table>
												</div>
											</bloque>
										</div>
					
									</div>
								
								</xsl:if>
								
							</xsl:for-each>
														
							
							
							<formulario id="form_proceso" destino="liberacion/15.2.do">
								<variable id="id_proceso" />
								<variable id="id_formato_salida" />
							</formulario>
							<xsl:variable name="total" select="//contarProcesosLiberador/Total" />
							<xsl:if test="$total != ''">
								<formulario id="form_pag" destino="liberacion/15.1.do">
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
					
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
