<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Formatos">
			<javascript>admin/4.1.js</javascript>

			<principal>
				<titulo icono="formatos">Administración de Formatos</titulo>
				<contenido>

					<xsl:choose>
						<xsl:when test="count(//obtenerFormatosPorModelo/listado/Formato)>0">

							<formulario destino="formatos/4.2.do" id="form_formatos">
								<variable id="id_formato" valor="" />
							</formulario>

							<formulario destino="formatos/4.1.do" id="form_negocio">
								<registro>
									<item>
										Negocio:
									</item>
									<valor>
										<cajatextoselector name="_id_negocio" id="_id_negocio"
											accion="cambiarNegocio(this)" valor="{//id_negocio_actual}">
											<opcion>--Seleccione un negocio--</opcion>
											<xsl:for-each
												select="//obtenerListadoNegociosActivos/listado/Negocio">
												<xsl:sort select="cod_negocio" />
												<opcion valor="{id_negocio}">
													(<xsl:value-of select="cod_negocio"/>) <xsl:value-of select="nombre"/>
												</opcion>
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>
							</formulario>
							<formulario destino="formatos/4.1.do" id="form_filtro">
								<registro>
									<item>Nombre:</item>
									<valor><cajatexto id="filtro" valor="{FILTRO}"/></valor>
								</registro>
								<input type="hidden" name="_numeropagina_formatos" value="1"/>
							</formulario>

							<tabla>
								<encabezado>
									<titulo>&#160;</titulo>
									<titulo>Formato</titulo>
									<titulo>Disponible en Negocio</titulo>
									<titulo>Activo Usuario</titulo>
								</encabezado>
								<xsl:for-each select="//obtenerFormatosPorModelo/listado/Formato">

									<xsl:variable name="id_form" select="id_formato" />

									<fila>
										<valor accion="seleccionarFormato({id_formato},'{tipoformato}', '{bloqueado}')">
											<xsl:choose>
												<xsl:when test="tipoformato='E'">
													<bloque estilo="formIn" />
												</xsl:when>
												<xsl:when test="tipoformato='S'">
													<bloque estilo="formOut" />
												</xsl:when>
												<xsl:otherwise>
													??
												</xsl:otherwise>
											</xsl:choose>
										</valor>
										<valor accion="seleccionarFormato({id_formato},'{tipoformato}', '{bloqueado}')">
											
											<p><xsl:value-of select="tipoformato" /><xsl:value-of select="id_formato" /> | <b><xsl:value-of select="nombre" /></b></p>
											<xsl:value-of select="descripcion" />
										</valor>

										<valor>
											<cajachequeo2 id="cajachequeo_de_{id_formato}"
												valor="S" valor2="N"
												seleccionado="{boolean(count(//obtenerFormatoNegocioPorNegocio/listado/FormatoNegocio[id_formato=$id_form])>0)}"
												accion="accionDisponibleEmpresa({id_formato})" />
										</valor>

										<valor>
											<cajachequeo2 id="cajachequeo_au_{id_formato}"
												valor="S" valor2="N" seleccionado="{boolean(activo='S')}"
												accion="activarUsuario('{id_formato}')" />
										</valor>
									</fila>
								</xsl:for-each>
							</tabla>


							<formulario destino="formatos/4.1.do" id="form_pag">
								<paginacion id="_numeropagina_formatos" formulario="form_pag"
									numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}"
									total="{totalFormatosPorModelo/total}" />
								<input type="hidden" name="filtro" value="{FILTRO}"/>
							</formulario>

						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen formatos asociadas a su
								usuario.
							</parrafo>
						</xsl:otherwise>

					</xsl:choose>

					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="formatos/4.2.do">Crear Formato Entrada</boton>
						<boton estilo="crear" destino="formatos/4.8.do">Crear Formato Salida</boton>
					</area_botones>

				</contenido>

			</principal>


			<ventana id="vn_cambio_estado_activo" icono="confirmacion">
				<titulo>Confirmación Cambio de Estado</titulo>
				<contenido>

					<variable id="id_formato_DE" valor="" />
					<variable id="accion_DE" valor="" />

					<bloque estilo="grupo">
						<parrafo estilo="resaltado" id="mensaje_disponible_empresa">
							...
						</parrafo>
					</bloque>

					<area_botones>
						<boton estilo="guardar" id="btn_de_si"
							accion="cerrarVentana('vn_cambio_estado_activo'); disponibleFormatoNegocio('{//id_negocio_actual}', 'S');">Si, aplicar a todos los negocios.</boton>
						<boton estilo="guardar" id="btn_de_no"
							accion="cerrarVentana('vn_cambio_estado_activo'); disponibleFormatoNegocio('{//id_negocio_actual}', 'N');">No, solo aplicar al negocio actual.</boton>
						<boton estilo="cancelar" accion="cancelar_DE()">Cancelar</boton>
					</area_botones>
				</contenido>
			</ventana>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
