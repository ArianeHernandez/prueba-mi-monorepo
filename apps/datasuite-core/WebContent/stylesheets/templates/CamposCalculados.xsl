<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="CAMPOS_CALCULADOS">
		<xsl:variable name="id_estructura"
			select="//obtenerFormato/Formato/id_estructura" />

		<javascript>templates/campos_calculados.js</javascript>

		<pestana titulo="Campos Calculados">

			<bloque>

				<registro>
					<item>Campo</item>
					<valor>
						<cajatextoselector id="sel_campo_calculado">
							<opcion>--Seleccione--</opcion>
							<xsl:for-each
								select="//obtenerFormatoCampoCompleto/Formato/Campo">
								<xsl:sort select="nombre"/>
								<opcion valor="{id_campo}">
									<xsl:value-of select="nombre"></xsl:value-of>
								</opcion>
							</xsl:for-each>
						</cajatextoselector>
					</valor>
				</registro>

				<registro>
					<item>Lista Dinámica</item>
					<valor>
						<cajatextoselector id="sel_lista_din_calculado">
							<opcion>--Seleccione--</opcion>
							<xsl:for-each select="obtenerListasDinamicas/listado/ListaDinamica">
								<opcion valor="{id_lista_dinamica}">
									<xsl:value-of select="nombre"></xsl:value-of>
								</opcion>
							</xsl:for-each>
						</cajatextoselector>
					</valor>
				</registro>

				<registro>
					<item>Operación</item>
					<valor>
						<cajatextoselector id="sel_var_operacion">
							<opcion>--Seleccione--</opcion>
							<xsl:for-each select="obtenerOperacionesCampoAct/listado/Operacion">
								<opcion valor="{id_operacion}" texto="{descripcion}" />
							</xsl:for-each>
						</cajatextoselector>
					</valor>
				</registro>

				<area_botones>
					<boton estilo="agregar" accion="agregarCampoCalculado()">Agregar</boton>
				</area_botones>
				
			</bloque>
			<bloque-contenido>
				<titulo>Campos calculados para el Formato</titulo>
				<contenido>
					<bloque id="CAMPOS_CALCULADOS">
						<contenido>
							<xsl:for-each
								select="obtenerOperacionesPorFormato/listado/FormatoOperacion">
								<bloque estilo="grupo" id="formato_operacion_{id_formato_operacion}">
									<input type="hidden"
										name="Formato.operaciones:{id_formato_operacion}.id_formato_operacion"
										value="{id_formato_operacion}" />
									<input type="hidden"
										name="Formato.operaciones:{id_formato_operacion}.id_formato"
										value="{id_formato}" />
									<input type="hidden"
										name="Formato.operaciones:{id_formato_operacion}.id_campo"
										value="{id_campo}" />
	
									<xsl:variable name="id_campo" select="id_campo"/>
									<subtitulo><xsl:value-of select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/nombre" /></subtitulo>
									
									<registro>
										<item>Lista Dinámica</item>
										<valor>
											<cajatextoselector id="Formato.operaciones:{id_formato_operacion}.id_listadinamica"
												valor="{id_listadinamica}">
												<opcion>--Seleccione--</opcion>
												<xsl:for-each
													select="//obtenerListasDinamicas/listado/ListaDinamica">
													<opcion valor="{id_lista_dinamica}">
														<xsl:value-of select="nombre"></xsl:value-of>
													</opcion>
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
									<registro>
										<item>Operación</item>
										<valor>
											<cajatextoselector id="Formato.operaciones:{id_formato_operacion}.id_operacion" valor="{id_operacion}">
												<opcion>--Seleccione--</opcion>
												<xsl:for-each select="//obtenerOperacionesCampoAct/listado/Operacion">
													<opcion valor="{id_operacion}" texto="{descripcion}" />
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
									<area_botones>
										<boton accion="eliminarVariableCampo('{id_formato_operacion}')">Eliminar</boton>
									</area_botones>
								</bloque>
							</xsl:for-each>
						</contenido>
					</bloque>
				</contenido>
			</bloque-contenido>

			<bloque id="PLANTILLA_CAMPO_CALCULADO" display="none">
			
			
				<!-- Parametros
					1 - id_formato_operacion
					2 - id_campo
					3 - campo
					4 - id lista dinamica
					5 - id operacion
				 -->
				<bloque estilo="grupo" id="formato_operacion_[ 1 ]">
				
					<input type="hidden" name="Formato.operaciones:[ 1 ].id_formato_operacion"
						value="" />
					<input type="hidden" name="Formato.operaciones:[ 1 ].id_formato"
						value="{//obtenerFormato/Formato/id_formato}" />
					<input type="hidden" name="Formato.operaciones:[ 1 ].id_campo"
						value="[ 2 ]" />
									
					<subtitulo>[ 3 ]</subtitulo>
					
					<registro>
						<item>Lista Dinámica</item>
						<valor>
							<cajatextoselector id="Formato.operaciones:[ 1 ].id_listadinamica" >
								<opcion>--Seleccione--</opcion>
								<xsl:for-each
									select="//obtenerListasDinamicas/listado/ListaDinamica">
									<opcion valor="{id_lista_dinamica}">
										<xsl:value-of select="nombre"></xsl:value-of>
									</opcion>
								</xsl:for-each>
							</cajatextoselector>
						</valor>
					</registro>
					
					<registro>
						<item>Operación</item>
						<valor>
							<cajatextoselector id="Formato.operaciones:[ 1 ].id_operacion">
								<opcion>--Seleccione--</opcion>
								<xsl:for-each select="//obtenerOperacionesCampoAct/listado/Operacion">
									<opcion valor="{id_operacion}" texto="{descripcion}" />
								</xsl:for-each>
							</cajatextoselector>
						</valor>
					</registro>
					<area_botones>
						<boton accion="eliminarVariableCampo('[ 1 ]')">Eliminar</boton>
					</area_botones>
				</bloque>
			</bloque>
		</pestana>
	</xsl:template>

</xsl:stylesheet>