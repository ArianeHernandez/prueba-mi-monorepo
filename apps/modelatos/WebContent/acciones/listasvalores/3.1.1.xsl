<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Lista de Valores - {//obtenerListaValores/ListaValores/nombre}">
			<javascript>admin/3.1.1.js</javascript>
			
			<principal>
				<titulo icono="listavalores">Lista de Valores - <xsl:value-of select="//obtenerListaValores/ListaValores/nombre"/></titulo>
				<contenido>
					
					
						
						<bloque-pestanas>
					
							<pestana titulo="Información Basica">
								<formulario id="form_edicion" destino="listasvalores/3.2.do">
									<variable id="id_lista_valores" valor="{//obtenerListaValores/ListaValores/id_lista_valores}"/>
									<registro>
										<item>Id</item>
										<valor>
											<b>L<xsl:value-of select="//obtenerListaValores/ListaValores/id_lista_valores"/></b>
										</valor>
									</registro>
									
									<registro>
										<item>Nombre</item>
										<valor>
											<b><xsl:value-of select="//obtenerListaValores/ListaValores/nombre"/></b>
										</valor>
									</registro>
									
									<registro>
										<item>Descripción</item>
										<valor>
											<parrafo texto="{//obtenerListaValores/ListaValores/descripcion}" />
										</valor>
									</registro>
									
									<registro>
										<item>Tipo Identificación</item>
										<valor>
											<parrafo texto="{//TipoCampo[id_tipocampo=//obtenerListaValores/ListaValores/id_tipocampo]/nombre}"/>
										</valor>
									</registro>
									<registro>
										<item>Campo Ordenamiento</item>
										<valor>
											<xsl:choose>
												<xsl:when test="//obtenerListaValores/ListaValores/criterio_orden = 'V'">Valor</xsl:when>
												<xsl:otherwise>ID</xsl:otherwise>
											</xsl:choose>
										</valor>
									</registro>
									
									<registro>
										<item>Tipo de orden</item>
										<valor>
											<xsl:choose>
												<xsl:when test="//obtenerListaValores/ListaValores/tipo_orden = 'A'">Ascendente</xsl:when>
												<xsl:otherwise>Descendente</xsl:otherwise>
											</xsl:choose>
										</valor>
									</registro>
									
									<registro>
										<item>Identificación</item>
										<valor>
											<xsl:value-of select="//obtenerListaValores/ListaValores/c_id"/>
										</valor>
									</registro>
									<registro>
										<item>Etiqueta</item>
										<valor>
											<xsl:value-of select="//obtenerListaValores/ListaValores/c_nombre"/>
										</valor>
									</registro>
									<registro>
										<item>Fuente</item>
										<valor>
											<xsl:value-of select="//obtenerListaValores/ListaValores/consulta"/>
										</valor>
									</registro>
									<registro>
										<item>Intervalo Actualización</item>
										<valor>
											<xsl:value-of select="//obtenerListaValores/ListaValores/intervalo_actualizacion"/>
										</valor>
									</registro>
									<registro>
										<item>Nombre Conexión</item>
										<valor>
											<xsl:value-of select="//obtenerListaValores/ListaValores/base_datos"/>
										</valor>
									</registro>
								</formulario>
							</pestana>
							
							
							<pestana titulo="Valores">
								
								
								
								<xsl:choose>
									<xsl:when test="count(//obtenerValoresLV/listado/ValorLista)>0">
										<tabla>
											<encabezado>
												<titulo>Identificación</titulo>
												<titulo>Nombre</titulo>
											</encabezado>
											<xsl:for-each select="//obtenerValoresLV/listado/ValorLista">
												<fila>
													<valor><xsl:value-of select="id"/></valor>
													<valor>
														<bloque style="color:{colorletra}; background-color: {colorfondo}; padding: 5px"><xsl:value-of select="nombre"/></bloque>
													</valor>
												</fila>
											</xsl:for-each>
										</tabla>
										
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="resaltado">
											No existen valores en la lista de valores.
										</parrafo>
									</xsl:otherwise>
									
								</xsl:choose>
								
									<bloque-contenido>
									<titulo>Edicion por archivo Excel</titulo>
									<contenido>
										<formulario id="form_sendfile" destino="file.cargaListaValores" dato="multipart/form-data">
											
											<bloque estilo="grupo">
											<xsl:if test="count(//respuesta)>0">
												<script>activarPestana();</script>
											</xsl:if>
											
											<xsl:choose>
											
												<xsl:when test="//respuesta='1'">
													
													<nota>
														La carga de valores ha sido exitosa.
													</nota>
												</xsl:when>
												<xsl:when test="//respuesta='2'">
													<alerta>
														Error! No se pudo actualizar ningun registro por inconsistencias en el archivo, por favor intentelo nuevamente.
													</alerta>
													
												</xsl:when>
												
												<xsl:when test="//respuesta='3'">
													<alerta>
														Error! El archivo no presenta la estructura de la plantilla. Por favor cargue nuevamente el archivo
													</alerta>
													
												</xsl:when>
												
												<xsl:otherwise>
													
												</xsl:otherwise>
											</xsl:choose>
											
											
												<parrafo>Envíe el archivo con el formato especifico.</parrafo>
												
												<cajaarchivo id="filename"/>
											</bloque>
											
											<area_botones>
												
												<boton id="boton_descarga" accion="osm_go('descarga.salidaListaValores',false)">Descargar Archivo</boton>
												
												<boton estilo="excel" formulario="form_sendfile" validacion="validar_archivo()">Enviar Archivo</boton>
												
											</area_botones>
										</formulario>
									</contenido>
								</bloque-contenido>
								
							</pestana>
							
						</bloque-pestanas>
						
						<area_botones>
							<boton estilo="volver" destino="listasvalores/3.1.do">Volver</boton>
							<boton estilo="editar" formulario="form_edicion">Edición Interactiva</boton>
						</area_botones>
					
					
					
				</contenido>
				
					
					
					

					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
