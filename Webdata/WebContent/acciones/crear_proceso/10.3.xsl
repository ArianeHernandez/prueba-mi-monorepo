<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Administración de Procesos">

		
			<principal>
				<titulo>Respuesta Transacción</titulo>
				<contenido>
				<div class="box-container">
				
					<xsl:choose>
						<xsl:when test="//eliminarProceso/exitoso='true'">
							<nota>
								El proceso se ha elimnado con exito.
							</nota>
						</xsl:when>
						<xsl:otherwise>
							<alerta>
								No se ha podido eliminar el proceso. Por favor intente nuevamente.
							</alerta>
						</xsl:otherwise>
					
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary volver" destino="crear_proceso/10.1.do">Volver</boton>
					</area_botones>
				
				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

	<xsl:template name="EDICION_REVISION">

		<xsl:variable name="id_revision" select="id_revision" />

		<xsl:for-each
			select="//revisiones/Revision[id_revision_siguiente=$id_revision]">
			<xsl:call-template name="EDICION_REVISION" />
		</xsl:for-each>

		<xsl:call-template name="REVISION" />

	</xsl:template>

	<xsl:template name="EDICION_PROCESO">

		<stylesheet>10.css</stylesheet>

		<escapar>

			<xsl:call-template name="PREPARADORES" />

			<xsl:for-each select="//revisiones/Revision[count(id_revision_siguiente)=0]">
				<xsl:call-template name="EDICION_REVISION" />
			</xsl:for-each>

			<xsl:call-template name="LIBERADORES" />

			<!-- PLANTILLAS -->

			<div style="display:none">

				<div id="PLANTILLA_PREPARADOR">
					<div class="cuadro_fila" id="filap_[ID]">

						<div class="cuadro_fila_item w20">
							<div class="eliminar" onclick="desasociarPreparador([ID])">&#160;</div>
						</div>

						<div class="cuadro_fila_item w220">
							<p>[NOMBRE]&#160;</p>
						</div>

						<div class="cuadro_fila_item w90">
							<b style="text-transform: lowercase;">[LOGIN]&#160;</b>
						</div>
					</div>
				</div>

				<div id="PLANTILLA_LIBERADOR">
					<div class="cuadro_fila" id="filal_[ID]">
						<div class="cuadro_fila_item w20">
							<div class="eliminar" onclick="desasociarLiberador([ID])">&#160;</div>
						</div>
						<div class="cuadro_fila_item w220">
							<p>[NOMBRE]&#160;</p>
						</div>

						<div class="cuadro_fila_item w90">
							<b style="text-transform: lowercase;">[LOGIN]&#160;</b>
						</div>
					</div>
				</div>

				<div id="PLANTILLA_REVISOR">
					<div class="cuadro_fila" id="filar_[ID_REV]_[ID]">
						<div class="cuadro_fila_item w20">
							<div class="eliminar" onclick="desasociarRevisor([ID_REV],[ID])">&#160;</div>
						</div>
						<div class="cuadro_fila_item w220">
							<p>[NOMBRE]&#160;</p>
						</div>

						<div class="cuadro_fila_item w90">
							<b style="text-transform: lowercase;">[LOGIN]&#160;</b>
						</div>
					</div>
				</div>

				<div id="PLANTILLA_REVISION">

					<div class="cuadro_proceso box_revision" id="box_revision_[ID_REV]"
						style="display:none">

						<div class="cuadro_imagen">
							&#160;
							<div class="eliminar" onclick="eliminarRevision([ID_REV])">&#160;</div>
						</div>
						<div class="cuadro_boton">
							<input type="button" value="+"
								onclick="agregarRevision([ID_REV], 'box_revision_[ID_REV]')" />
						</div>

						<div class="cuadro_info">
							<div class="cuadro_info_titulo">
								<h1>Revisión</h1>
								<input type="text" value="[NOMBRE]"
									onchange="actualizarNombreRevision([ID_REV], this.value)" />
							</div>

							<div class="cuadro_info_cuerpo" id="lista_revisores_[ID_REV]">
								&#160; </div>

							<div class="cuadro_info_footer">
								<select
									onchange="asociarRevisor([ID_REV],this.value, this.options[this.selectedIndex].text); this.value=''; ">
									<option value=''>-- seleccione --</option>
									<xsl:for-each select="//revisores_usuario/Revisor">
										<option value="{id_revisor}">
											<xsl:value-of select="concat(nombre, ' - ', login)" />
										</option>
									</xsl:for-each>
								</select>
							</div>

						</div>

					</div>



				</div>

			</div>


		</escapar>

	</xsl:template>

	<xsl:template name="PREPARADORES">

		<div class="cuadro_proceso" id="box_preparadores">

			<div class="cuadro_imagen">&#160;</div>
			<div class="cuadro_boton">
				<input type="button" value="+"
					onclick="agregarRevision(null, 'box_preparadores')" />
			</div>

			<div class="cuadro_info">
				<div class="cuadro_info_titulo">
					<h1>Preparadores</h1>
				</div>

				<div class="cuadro_info_cuerpo" id="lista_preparadores">
					&#160;
					<xsl:for-each select="preparadores/Preparador">
						<div class="cuadro_fila" id="filap_{id_preparador}">
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="desasociarPreparador({id_preparador})">&#160;</div>
							</div>
							<div class="cuadro_fila_item w220">
								<p>
									<xsl:value-of select="nombre" />
									&#160;
								</p>
							</div>

							<div class="cuadro_fila_item w90">
								<b style="text-transform: lowercase;">
									<xsl:value-of select="login" />
									&#160;
								</b>
							</div>
						</div>
					</xsl:for-each>
				</div>

				<div class="cuadro_info_footer">
					<select
						onchange="asociarPreparador(this.value, this.options[this.selectedIndex].text); this.value=''; ">
						<option value=''>-- seleccione --</option>
						<xsl:for-each select="preparadores_usuario/Preparador">
							<option value="{id_preparador}">
								<xsl:value-of select="concat(nombre, ' - ', login)" />
							</option>
						</xsl:for-each>
					</select>
				</div>

			</div>

		</div>


	</xsl:template>

	<xsl:template name="LIBERADORES">

		<div class="cuadro_proceso" id="box_liberadores">

			<div class="cuadro_imagen">&#160;</div>

			<div class="cuadro_info">
				<div class="cuadro_info_titulo">
					<h1>Liberadores</h1>
				</div>

				<div class="cuadro_info_cuerpo" id="lista_liberadores">
					&#160;
					<xsl:for-each select="liberadores/Liberador">
						<div class="cuadro_fila" id="filal_{id_liberador}">
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="desasociarLiberador({id_liberador})">&#160;</div>
							</div>
							<div class="cuadro_fila_item w220">
								<p>
									<xsl:value-of select="nombre" />
									&#160;
								</p>
							</div>

							<div class="cuadro_fila_item w90">
								<b style="text-transform: lowercase;">
									<xsl:value-of select="login" />
									&#160;
								</b>
							</div>
						</div>
					</xsl:for-each>
				</div>

				<div class="cuadro_info_footer">
					<select
						onchange="asociarLiberador(this.value, this.options[this.selectedIndex].text); this.value=''; ">
						<option value=''>-- seleccione --</option>
						<xsl:for-each select="liberadores_usuario/Liberador">
							<option value="{id_liberador}">
								<xsl:value-of select="concat(nombre, ' - ', login)" />
							</option>
						</xsl:for-each>
					</select>
				</div>

			</div>

		</div>


	</xsl:template>

	<xsl:template name="REVISION">

		<xsl:variable name="id_revision" select="id_revision" />

		<div class="cuadro_proceso box_revision" id="box_revision_{$id_revision}">

			<div class="cuadro_imagen">
				&#160;
				<div class="eliminar" onclick="eliminarRevision({$id_revision})">&#160;</div>
			</div>
			<div class="cuadro_boton">
				<input type="button" value="+"
					onclick="agregarRevision({$id_revision}, 'box_revision_{$id_revision}')" />
			</div>

			<div class="cuadro_info">
				<div class="cuadro_info_titulo">
					<h1>Revisión</h1>
					<input type="text" value="{nombre}"
						onchange="actualizarNombreRevision({$id_revision}, this.value)" />
				</div>

				<div class="cuadro_info_cuerpo" id="lista_revisores_{$id_revision}">
					&#160;

					<xsl:for-each
						select="//listaRevisionRevisores/RevisionRevisores[id_revision=$id_revision]/revisores/Revisor">
						<div class="cuadro_fila" id="filar_{$id_revision}_{id_revisor}">
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="desasociarRevisor({$id_revision},{id_revisor})">&#160;</div>
							</div>
							<div class="cuadro_fila_item w220">
								<p>
									<xsl:value-of select="nombre" />
									&#160;
								</p>
							</div>

							<div class="cuadro_fila_item w90">
								<b style="text-transform: lowercase;">
									<xsl:value-of select="login" />
									&#160;
								</b>
							</div>
						</div>
					</xsl:for-each>
				</div>

				<div class="cuadro_info_footer">
					<select
						onchange="asociarRevisor({$id_revision},this.value, this.options[this.selectedIndex].text); this.value=''; ">
						<option value=''>-- seleccione --</option>
						<xsl:for-each select="//revisores_usuario/Revisor">
							<option value="{id_revisor}">
								<xsl:value-of select="concat(nombre, ' - ', login)" />
							</option>
						</xsl:for-each>
					</select>
				</div>

			</div>

		</div>


	</xsl:template>

</xsl:stylesheet>
