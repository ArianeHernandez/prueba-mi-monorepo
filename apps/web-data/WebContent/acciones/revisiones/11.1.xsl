<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<javascript>revisiones/11.1.js</javascript>
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Revisiones">
			
			<principal>
				<titulo>Revisiones Asociadas</titulo>
				<contenido>
					
					<xsl:variable name="grupos" select="//obtenerTodosLosGruposformato/Listado/GrupoFormato[id_grupoformato = //lista_procesos/Proceso[id_proceso = //obtenerRevisionesRevisor/Listado/Revision/id_proceso]/id_grupoformato]" />
					<xsl:choose>
						<xsl:when test="count($grupos)>0">
						
							<xsl:for-each select="$grupos">
								
								<div id="div_grupo_{id_grupoformato}" class="bloqueestilo_resultado"
									onclick="toggleProcesosPorGrupo('{id_grupoformato}')">
									
									<h1 class="">
										<xsl:value-of select="nombre"/> 
									</h1>
									<div style="margin-left:10px; margin-right:10px">
										<xsl:value-of select="descripcion"/> 
									</div>
									
										<div style="margin:10px;" id="lista_procesos_grupo_{id_grupoformato}" >
										<bloque >
											<div class="tabla_borde" >
												<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
													<thead>
														<tr class="tabla_encabezado">
															<td style="width:70%">Proceso</td>
															<td>Pendientes</td>
														</tr>
													</thead>
													
													<tbody id="cont_grupo_{id_grupoformato}"></tbody>
													
												</table>
											</div>
										</bloque>
									</div>
									
								</div>	
								
								<xsl:variable name="id_grupoformato" select="id_grupoformato"/>
								<xsl:for-each select="//obtenerRevisionesRevisor/Listado/Revision[id_proceso = //lista_procesos/Proceso[id_grupoformato = $id_grupoformato]/id_proceso]">
									
									<xsl:variable name="id_proceso" select="id_proceso"/>
									<div style="display:none">
										<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0" >
											
											<tbody id="cont_grupo">
													<tr onclick="enviar('{id_revision}','{id_proceso}');" class="tabla_fila grupo_{$id_grupoformato}"
															onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'" name="div_procesos_grupo_{$id_grupoformato}" >
															<td style="width:70%"><xsl:value-of select="//lista_procesos/Proceso[id_proceso=$id_proceso]/nombre"/>&#160; - &#160;<xsl:value-of select="nombre"/></td>
															<td><xsl:value-of select="total_cargas"/></td>
													</tr>
											</tbody>
										</table>
									</div>
								
								</xsl:for-each>
								
							</xsl:for-each>
						
							<formulario id="form_revision" destino="revisiones/11.2.do">
								<variable id="id_revision" />
								<variable id="id_proceso"   />
							</formulario>
							
						</xsl:when>
						
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen revisiones asociadas a su usuario.
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
