<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Grupos">
			<javascript>admin/2.1.js</javascript>
			
			<principal>
				<titulo icono="grupos">Administración de Grupos de Estructuras</titulo>
				<contenido>
					
					<xsl:choose>
						<xsl:when test="count(//obtenerGruposPorPersona/listado/Grupo)>0">
					
						 	<tabla icono="group">
								<encabezado>
									<titulo>ID</titulo>
									<titulo>Nombre de Grupo</titulo>
									<titulo></titulo>
									<titulo></titulo>
								</encabezado>
								<xsl:for-each select="//obtenerGruposPorPersona/listado/Grupo">
									<fila id="fila_grupo_{position()}">
										<valor accion="verGrupo('{id_grupo}')" class="w100">
											<variable id="id_grupo_{position()}" valor="{id_grupo}"/>
											G<xsl:value-of select="id_grupo"/>
										</valor>
										<valor accion="verGrupo('{id_grupo}')">
											<xsl:value-of select="nombre"/>
										</valor>
										<valor class="w100">
											<boton accion="verDiagrama('{id_grupo}')">Ver Diagrama</boton>
                                      	</valor>
										<valor class="w100">
											<boton accion="descargarERM('{id_grupo}'); osm_unblock_window();">Descargar ERM</boton>
                                      	</valor>
									</fila>
								</xsl:for-each>
							</tabla>
							
							<formulario destino="grupos/2.1.do" id="form_pag">
								<paginacion id="_numeropagina_grupogiros" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{totalGruposPorPersona/total}"/>
							</formulario>
							
							<formulario id="form_grupos">
								<variable id="id_grupo" valor=""/>
								<variable id="id_modelo" valor="{idModelo}" />
							</formulario>
							
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen grupos de estructuras asociadas a su usuario.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="importar" accion="verVentana()">Importar</boton>
						<boton estilo="crear" destino="grupos/2.2.do">Crear Nuevo</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			
			
			<ventana id="ventanaImport" icono="importar">
				<titulo>Importar Grupo</titulo>
				<contenido>
					<formulario id="form_importar" destino="grupos/grupo.importgrp" dato="multipart/form-data">
					
						<bloque-contenido>
							<titulo icono="importar">Ruta del Grupo</titulo>
								<contenido>
									
									<registro>
										<item>Grupo:</item>
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
