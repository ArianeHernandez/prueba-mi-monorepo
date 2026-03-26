<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		

		<pagina>

			<javascript>validacion_estructura/28.2.A.js</javascript>
			
			<stylesheet>28.2.css</stylesheet>
				
			<principal>
			
				<titulo>Validacion por Estructura</titulo>

				<contenido>
					<bloque-pestanas>
					<pestana titulo="Información Basica">
					
						<formulario destino="validaciones_estructura/28.3.do" id="form_siguiente">
							<variable id="ValidacionEstructura.id_estructura" valor="{//id_estructura}" />
							<variable id="ValidacionEstructura.id_validacion_estructura" valor="{//id_validacion_estructura}" />
							
							<!-- SI HAY ID_VALIDACION EL FORMULARIO ES DE EDICION -->
							<xsl:if test="//editar='true'">
									<variable id="editar" />
							</xsl:if>
										
							<!--NOMBRE DE LA VALIDACION -->
						 	<registro>
								<item>Nombre validacion:</item>
								<valor>
									<cajatexto id="ValidacionEstructura.descripcion" valor="{//obtenerValidacionPorEstructura/Validacion/descripcion}"/>
								</valor>
						 	</registro>
							
							<!--SELECTOR DE TIPO DE VALIDACION -->
							<registro>
								<item>Tipo validacion:</item>
								<valor>
								<cajatextoselector class="" id="ValidacionEstructura.id_tipovalidacion" accion="activarValoresPorTipoValidacion(this.value)" valor="{//obtenerValidacionPorEstructura/Validacion/id_tipovalidacion}">
									<opcion valor="" texto="-- Seleccione --"/>
									<xsl:for-each select="//obtenerTiposDeValidacion/listado/TipoValidacion">
										<opcion valor="{id_tipovalidacion}" texto="{descripcion}" />
									</xsl:for-each>
								</cajatextoselector>
								</valor>
						 	</registro>
	
							<registro>
								<item>Validación en Línea</item>
								<valor>
									<cajachequeo id="ValidacionEstructura.enlinea" valor="S" seleccionado="{//obtenerValidacionPorEstructura/Validacion/enlinea='S'}">
									</cajachequeo>
								</valor>
						 	</registro>
						 	
						 		
						 	<!--CAMPOS ASOCIADOS A LA VALIDACION-->
						 	
						 	<div id="campos_por_estructura" >
						 		<bloque-contenido>
							 		<titulo icono="edicion">Campos de la estructura asociados a la validacion</titulo>
									<contenido>
							 	
								 		<xsl:for-each select="//obtenerCamposPorEstructura/listado/Campo">
										 	<div id="campo_{id_campo}" class="campo">
										 	<xsl:variable name="ID_CAMPO" select="id_campo"></xsl:variable>
												<bloque estilo="grupo">
												
													<!-- VALIDACION CAMPO -->
													<input type="hidden" name="campos:[{id_campo}].id_campo" id="campos:[{id_campo}].id_campo" value="{id_campo}" />
												
													<registro>
													<item>Campo:</item>
													<valor>
														<cajatexto id="nombre_campo_{id_campo}" valor="{nombre}" desactivado="true"/>
													</valor>
												 	</registro>
												
													<registro>
													<item>Asociar a la validacion</item>
													<valor>
														<cajachequeo id="campos:[{id_campo}].seleccionado" valor="S">
															<xsl:if test="count(//obtenerValidacionPorEstructura/Validacion/listaValidacionCampo/ValidacionCampo[id_campo=$ID_CAMPO]/id_campo)>0">
																<xsl:attribute name="seleccionado">true</xsl:attribute>
															</xsl:if>
														</cajachequeo>
	
													</valor>
												 	</registro>
												 	
												 	
												 	<!-- PRIMER Y SEGUNDO VALOR PARA CADA CAMPO -->
												 	<div id="valores_campo_{id_campo}">
												 		<xsl:variable name="ID_CAMPO" select="id_campo"></xsl:variable>
																
												 	
													 	<div id="primer_valor_campo_{id_campo}" class="primer_valor">
													 		
														 	<registro>
															<item>Valor primer campo</item>
															<valor>
																<xsl:variable name="texto_primer_valor" select="//obtenerValidacionPorEstructura/Validacion/listaValidacionCampo/ValidacionCampo[id_campo=$ID_CAMPO]/primer_valor"/>
																
																<cajatexto id="campos:[{id_campo}].primer_valor" valor="{$texto_primer_valor}"/>
															</valor>
														 	</registro>
														 	
													 	</div>
													 	
													 	<div id="segundo_valor_campo_{id_campo}" class="segundo_valor">
													 		
														 	<registro>
															<item>Valor segundo campo</item>
															<valor>
																<xsl:variable name="texto_segundo_valor" select="//obtenerValidacionPorEstructura/Validacion/listaValidacionCampo/ValidacionCampo[id_campo=$ID_CAMPO]/segundo_valor"/>
																
																<cajatexto id="campos:[{id_campo}].segundo_valor" valor="{$texto_segundo_valor}"/>
															</valor>
														 	</registro>
														 	
													 	</div>
												 	</div>
												</bloque>
											
											</div>
										</xsl:for-each>
									</contenido>
								</bloque-contenido>
							</div>
						
						</formulario>	
					</pestana>

					
				</bloque-pestanas>
					
				<area_botones>
					<br/>
					<br/>
					<boton estilo="volver" id="btn_volver_list_proc_admin" 
							accion="osm_go('validaciones_estructura/28.1.do');return false;">
					Volver al Listado</boton>
					
					<boton estilo="guardar" id="btn_guardar_validacion" 
							accion="guardarValidacionPorEstructura();">
					Guardar Validacion</boton>
				</area_botones>

				</contenido>
			</principal>
			
			
		</pagina>
		
	</xsl:template>
	
	
	

</xsl:stylesheet>
