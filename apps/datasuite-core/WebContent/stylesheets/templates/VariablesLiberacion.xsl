<?xml version="1.0" encoding="ISO-8859-1"?> 
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="VARIABLES_LIBERACION">
		<pestana titulo="Variables Liberación">
			<javascript>templates/variables_liberacion.js</javascript>

			<bloque>
				<registro>
					<item>Variables para el liberador</item>
					<valor>
						<input type="checkbox" name="Formato.para_liberador" value="S">
							<xsl:if test="//obtenerFormato/Formato/para_liberador = 'S'">
								<xsl:attribute name="checked">checked</xsl:attribute>
							</xsl:if>
						</input>
					</valor>
				</registro>
				<registro>
					<item>Variables para director de negocio</item>
					<valor>
						<input type="checkbox" name="Formato.para_director" value="S">
							<xsl:if test="//obtenerFormato/Formato/para_director = 'S'">
								<xsl:attribute name="checked">checked</xsl:attribute>
							</xsl:if>
						</input>
					</valor>
				</registro>

				<registro>
					<item>Campo</item>
					<valor>
						<cajatextoselector id="sel_var_campo">
							<opcion>--Seleccione--</opcion>
							<xsl:variable name="id_estructura"
								select="//obtenerFormato/Formato/id_estructura" />
							<xsl:for-each select="obtenerFormatoCampoCompleto/Formato/Campo[id_estructura=$id_estructura]">
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
						<cajatextoselector id="sel_var_lista_din">
							<opcion>--Seleccione--</opcion>
							<xsl:for-each select="obtenerListasDinamicas/listado/ListaDinamica">
								<opcion valor="{id_lista_dinamica}">
									<xsl:value-of select="nombre"></xsl:value-of>
								</opcion>
							</xsl:for-each>
						</cajatextoselector>
					</valor>
				</registro>

				<area_botones>
					<boton estilo="agregar" accion="agregarVariableCampo()">Agregar</boton>
				</area_botones>
			</bloque>
			<bloque-contenido>
				<titulo>Variables de Liberación para el Formato</titulo>
				<contenido>
					<bloque id="VARIABLES_LIBERACION">
						<xsl:for-each
							select="obtenerListasDCPorFormato/listado/ListaDinamicaCampo">
							<bloque estilo="grupo" id="variable_campo_{id_campo}">
								<input type="hidden" name="listaDC:{id_campo}.id_campo"
									value="{id_campo}" />
								<input type="hidden" name="listaDC:{id_campo}.id_lista_dinamica"
									value="{id_lista_dinamica}" />
								<input type="hidden" id="nombre_campo_{id_campo}" value="{nombre_campo}" />
								<input type="hidden" name="id_campos" value="{id_campo}" />
								<subtitulo></subtitulo>
								<registro>
									<item>Campo</item>
									<valor>
										<xsl:value-of select="nombre_campo" />
									</valor>
								</registro>
								<registro>
									<item>Lista Dinámica</item>
									<valor>
										<xsl:value-of select="nombre_lista_dinamica" />
									</valor>
								</registro>
								<registro>
									<item>Obligatorio</item>
									<valor>
										<input type="checkbox" name="listaDC:{id_campo}.obligatorio"
											value="S">
											<xsl:if test="obligatorio = 'S'">
												<xsl:attribute name="checked">checked</xsl:attribute>
											</xsl:if>
										</input>
									</valor>
								</registro>
								<registro>
									<item>Padre</item>

									<valor>
										<variable id="listaDC:{id_campo}.id_campo_padre"
											valor="{id_campo_padre}" />
										<select id="id_campo_padre_{id_campo}" name="id_campo_padre"
											class="form-control"
											onchange="osm_setValor('listaDC:{id_campo}.id_campo_padre', this.value)">
										</select>
									</valor>
								</registro>
								<area_botones>
									<boton accion="eliminarVariableLiberacion('{id_campo}')">Eliminar</boton>
								</area_botones>
							</bloque>
						</xsl:for-each>
					</bloque>
				</contenido>
			</bloque-contenido>

			<bloque id="PLANTILLA_VARIABLE_CAMPO" display="none">
				<bloque estilo="grupo" id="variable_campo_[ 1 ]">
					<input type="hidden" name="listaDC:[ 1 ].id_campo" value="[ 1 ]" />
					<input type="hidden" name="listaDC:[ 1 ].id_lista_dinamica"
						value="[ 2 ]" />
					<input type="hidden" id="nombre_campo_[ 1 ]" value="[ 3 ]" />
					<input type="hidden" name="id_campos" value="[ 1 ]" />
					<registro>
						<item>Campo</item>
						<valor>[ 3 ]</valor>
					</registro>
					<registro>
						<item>Lista Dinámica</item>
						<valor>[ 4 ]</valor>
					</registro>
					<registro>

						<item>Padre</item>

						<valor>
							<variable id="listaDC:[ 1 ].id_campo_padre" />

							<select id="id_campo_padre_[ 1 ]" name="id_campo_padre"
								class="form-control" onchange="osm_setValor('listaDC:[ 1 ].id_campo_padre', this.value)">
							</select>
						</valor>

					</registro>
					<area_botones>
						<boton accion="eliminarVariableLiberacion('[ 1 ]')">Eliminar</boton>
					</area_botones>
				</bloque>
			</bloque>
		</pestana>
	</xsl:template>

</xsl:stylesheet>