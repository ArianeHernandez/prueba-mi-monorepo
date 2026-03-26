<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Parametrización de contenidos">
			<javascript>contenido/36.1.js</javascript>
			<stylesheet>36.1.css</stylesheet>
		
			
			<principal>
				<titulo>Parametrización de contenidos</titulo>
				<contenido>
					
					<formulario id="form_editar_contenido" destino="contenido/36.2.do">
						<variable id="id_contenido" valor=""/> 
					</formulario>
					
					
					<xsl:for-each select="//obtenerAllServiciosContenido/listado/ServicioContenido">
					
						<table class="table table-hover table-striped tb">
							<thead>
								<tr class="tabla_encabezado servicio" id="tabla_{nombre_servicio}" onclick="toggleContenido('{nombre_servicio}');" >
									<td >Servicio > <xsl:value-of select="nombre_servicio"></xsl:value-of> </td>
									
								</tr>
							</thead>
							<tbody id="">
								
								<tr class="tabla_fila" id="body_{nombre_servicio}" style="display:none">
									<td >
										<div id="div_servicio_{nombre_servicio}" style="display:none">
											<table class="table table-hover table-striped tb"  style="width:100%">
												<thead>
													<tr class="tabla_encabezado">
														<td >URL </td>
														<td >Etiqueta</td>
														<td >Texto</td>
														<td >Editar</td>
															
													</tr>
												</thead>
												<tbody id="contenido_dinamico">
													<xsl:for-each select="contenidos/Contenido">
														<tr class="tabla_fila" id="contenido_{id_contenido}">
															<td ><xsl:value-of select="url"/></td>
															<td ><xsl:value-of select="etiqueta"/></td>
															<td ><xsl:value-of select="substring(texto,1, 100)"/>  ...</td>
															<td ><boton estilo="primary" accion="editarContenido('{id_contenido}');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></boton></td>
														</tr>
													</xsl:for-each>		
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<br/>
					</xsl:for-each>	
					
					
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>
	


</xsl:stylesheet>
