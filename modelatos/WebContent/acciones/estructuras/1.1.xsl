<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Estructuras">
			<javascript>admin/1.1.js</javascript>
			
			<principal>
				<titulo icono="estructura">Administración de Estructuras</titulo>
				<contenido>
						
					<formulario destino="estructuras/1.1.do" id="form_filtro">
						<registro>
							<item>Nombre:</item>
							<valor><cajatexto id="filtro" valor="{FILTRO}"/></valor>
						</registro>
						<input type="hidden" name="_numeropagina_estructuras" value="1"/>
					</formulario>
					
					<xsl:choose>
						<xsl:when test="count(//obtenerEstructurasPorPersona/listado/Estructura)>0">
						
							
								
							 	<tabla icono="data">
									<encabezado>
										<titulo>Estructura</titulo>
										<titulo>Descripcion</titulo>
										<titulo></titulo>
										<titulo></titulo>
									</encabezado>
										<xsl:for-each select="//obtenerEstructurasPorPersona/listado/Estructura">
												<fila>
													<valor accion="verEstructura('{id_estructura}')" class="w30p">
														<p>T<xsl:value-of select="id_estructura"/> | <b><xsl:value-of select="nombre"/></b></p>
													</valor>
													
													<valor accion="verEstructura('{id_estructura}')" >
														<p><xsl:value-of select="descripcion"/></p>
													</valor>
													
													<valor class="w100">
														<boton accion="ordenarCampos('{id_estructura}')">Ordenar Campos</boton>
													</valor>
													
													<valor class="w100">
														<xsl:if test="not(bloqueo_datos='S')">
															<boton accion="editarDatos('{id_estructura}')">Editar Datos</boton>
														</xsl:if>
													</valor>
												</fila>
										</xsl:for-each>
								</tabla>
							
							<formulario id="form_estructura">
								<variable id="id_estructura"/>
							</formulario>
							
							<form id="descargaERM" name="descargaERM" action="{//CONTEXT_PATH}/DescargarERM">
								<input type="hidden" name="id_modelo" value="{idModelo}" />
							</form>
							
							<formulario destino="estructuras/1.1.do" id="form_pag">
								<paginacion id="_numeropagina_estructuras" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{totalEstructurasPorPersona/total}"/>
								<input type="hidden" name="filtro" value="{FILTRO}"/>
							</formulario>
							
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen estructuras asociadas a su usuario.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<!--boton estilo="importar" accion="verVentana()">Importar</boton-->
						<boton estilo="exportar" accion="osm_enviarFormulario('descargaERM'); osm_unblock_window();">Descargar ERM</boton>
						<boton estilo="crear" destino="estructuras/1.2.do">Crear Nueva</boton>
						<boton estilo="diagrama" destino="estructuras/1.8.do">Ver Diagrama</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			
			<ventana id="ventanaImport" icono="confirmacion">
				<titulo>Importar Estructuras</titulo>
				<contenido>
					<formulario id="form_importar" destino="estructuras/estruc.importEst" dato="multipart/form-data">
					
						<bloque-contenido>
							<titulo icono="importar">Ruta de la Estructura</titulo>
								<contenido>
									
									<registro>
										<item>Estructura:</item>
										<valor>
											<cajaarchivo id="filename"/>
										</valor>
									</registro>
									
								</contenido>
						</bloque-contenido>
						
					</formulario>
		
					<area_botones>
						<boton estilo="importar" formulario="form_importar">Importar</boton>
						<boton estilo="cancelar" id="btn_Electronico" accion="cerrarVentana()">Cerrar</boton>
					</area_botones>
				</contenido>
			</ventana>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
