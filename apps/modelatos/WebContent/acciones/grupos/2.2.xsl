<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Grupos">
			<javascript>admin/2.2.js</javascript>
			
			<principal>
				<titulo icono="grupos">Edicion de Grupo</titulo>
				<contenido>
					
					<variable id="id_modelo_activo" valor="{//OSM-INIT-SESSION/Info/Negocio/id_modelo}"/>
					
					<formulario id="form_edicion" destino="grupos/2.3.do">
						<variable id="Accion" valor="GuardarEstructura"/>
						
						<variable id="Grupo.id_grupo" valor="{//obtenerGrupo/Grupo/id_grupo}"/>
						<variable id="Grupo.id_modelo" valor="{//obtenerGrupo/Grupo/id_modelo}"/>
						
						<bloque-contenido>
							<titulo icono="edicion">Información Basica</titulo>
							<contenido>
								
								<registro>
									<item>Nombre del Grupo</item>
									<valor>
										<cajatexto id="Grupo.nombre" valor="{//obtenerGrupo/Grupo/nombre}" />
									</valor>
								</registro>
								
							</contenido>
						</bloque-contenido>
					</formulario>	
						
						<formulario id="form_disociar" destino="grupos/2.2.do">
							<variable id="id_estructura" valor=""/>
							<variable id="id_grupo" valor="{//obtenerGrupo/Grupo/id_grupo}"/>
							<variable id="gruEstAccion" valor="eliminar"/>
						</formulario>
					
					<xsl:if test="count(//obtenerGrupo/Grupo/id_grupo)>0">
						<bloque-contenido>
							<titulo icono="edicion">Estructuras Asociadas</titulo>
							<contenido>
								<xsl:choose>
									<xsl:when test="count(//obtenerEstructurasPorGrupo/listado/Estructura)>0">
										<tabla>
											<encabezado>
												<titulo>ID</titulo>
												<titulo>Nombre de Estructura</titulo>
												<titulo></titulo>
											</encabezado>
											<xsl:for-each select="//obtenerEstructurasPorGrupo/listado/Estructura">
												<fila>
													<valor>
														<variable id="id_estructura_{position()}" valor="{id_estructura}"/>T<xsl:value-of select="id_estructura"/>
													</valor>
													<valor><xsl:value-of select="nombre"/></valor>
													<valor>
															<boton id="eliminar.GrpEst_{position()}" estilo="eliminar">Eliminar</boton>
													</valor>
												</fila>
											</xsl:for-each>
										</tabla>
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											No existen estructuras asociadas a este grupo.
										</parrafo>
									</xsl:otherwise>
								</xsl:choose>
								
								<formulario id="form_asociar" destino="grupos/2.2.do">			
									<registro>
										<item>Estructuras</item>
										<valor>
											<variable id="gruEstAccion" valor="guardar"/>
											<variable id="id_grupo" valor="{//obtenerGrupo/Grupo/id_grupo}"/>
											<xsl:choose>
												
												<xsl:when test="count(//obtenerEstructurasPorPersona/listado/Estructura)>0">
													<cajatextoselector id="id_estructura" valor="">
														<opcion valor="" texto="-- Seleccione --"/>
														
														<xsl:for-each select="//obtenerEstructurasPorPersona/listado/Estructura[number(id_estructura)>0]">
															<xsl:sort select="nombre"/>
															<xsl:variable name="nombre" select="nombre"/>
															<xsl:if test="count(//obtenerEstructurasPorGrupo/listado/Estructura[nombre = $nombre])=0">
																<opcion valor="{id_estructura}" texto="{nombre}"/>
															</xsl:if>
														</xsl:for-each>
													</cajatextoselector>
													
													<area_botones>
														<boton id="btn_agregar_estructura" estilo="crear" formulario="form_asociar">Agregar Estructura</boton>
													</area_botones>
												</xsl:when>
												
												<xsl:otherwise>
													<parrafo estilo="resaltado">
														No existen estructuras asociadas a su usuario.
													</parrafo>
												</xsl:otherwise>
												
											</xsl:choose>
											
										</valor>
									</registro>
								</formulario>		
								
							</contenido>
						</bloque-contenido>
					</xsl:if>

					
					
					<formulario id="form_eliminar" destino="grupos/2.4.do">
						<variable id="id_grupo" valor="{//obtenerGrupo/Grupo/id_grupo}"/>
					</formulario>
					
						<area_botones>
							<xsl:if test="count(//obtenerGrupo/Grupo)>0">
								<boton estilo="eliminar" formulario="form_eliminar">Eliminar</boton>
								<boton estilo="exportar" accion="osm_go('grupos/grupo.exportgrp?id_grupo={//obtenerGrupo/Grupo/id_grupo}', false);" >Exportar</boton>
							</xsl:if>
							<boton estilo="volver" destino="grupos/2.1.do">Volver</boton>
							<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar</boton>
						</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
