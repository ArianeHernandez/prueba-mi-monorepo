<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<javascript>superadmin/9.1.js</javascript>

		<xsl:choose>
			<xsl:when test="HAB_SIPPAGOS='false'">
				<xsl:call-template name="EditarNegocios" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="VerNegocios" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="EditarNegocios">
		<pagina titulo="Negocios">

			<principal>
				<titulo icono="usuarios">Listado de Negocios</titulo>
				<contenido>
					
					<tabla id="tabla_negocios">
						<encabezado>
							<titulo>Negocio</titulo>
							<titulo># Administradores Activos</titulo>
							<titulo>Activo</titulo>
							<titulo>Editar</titulo>
							<titulo>Eliminar</titulo>
						</encabezado>
						<xsl:for-each select="//verNegociosVigentes/listado/Negocio">
							<xsl:sort data-type="number" select="cod_negocio" />
							<fila id="negocio_{id_negocio}">
								<valor accion="enviarFormularioNeg({id_negocio})" >
									<p>[<span id="cod_negocio_{id_negocio}"><xsl:value-of select="cod_negocio" /></span>] | <b id="nombre_{id_negocio}"><xsl:value-of select="nombre" /></b></p>
								</valor>
								<valor accion="enviarFormularioNeg({id_negocio})" >
									<xsl:value-of select="numero_administradores" />
								</valor>
								<valor>
									<cajachequeo id="activo_{id_negocio}" seleccionado="{boolean(activo='S')}" accion="actualizar_estado({id_negocio})"/>
								</valor>
								<valor accion="mostrar_editarNegocio({id_negocio});" id="">
									<boton estilo="editar">editar</boton>
								</valor>
								<valor>
									<boton estilo="eliminar" accion="eliminar_negocio({id_negocio})">eliminar</boton>
									<variable id="id_modelo_{id_negocio}" valor="{id_modelo}"/>
									<variable id="descripcion_{id_negocio}" valor="{descripcion}"/>
								</valor>
							</fila>
						</xsl:for-each>
					</tabla>
					
					
					<bloque visible="false">	
						<tabla>	
							 <fila id="negocio_template">
								<valor accion="enviarFormularioNeg([1])" id="cod_negocio_[1]">
								</valor>
								<valor accion="enviarFormularioNeg([1])" id="nombre_[1]">
								</valor>
								<valor accion="enviarFormularioNeg([1])" >
									0
								</valor>
								<valor>
									<cajachequeo id="activo_[1]" seleccionado="{boolean(activo='S')}" accion="actualizar_estado([1])"/>
								</valor>
								<valor accion="mostrar_editarNegocio([1]);" id="">
									<boton estilo="editar"></boton>
								</valor>
								<valor>
									<boton estilo="eliminar" accion="eliminar_negocio([1])">eliminar</boton>
									<variable id="id_modelo_[1]" />
									<variable id="descripcion_[1]"/>
								</valor>
							</fila>
						</tabla>
					</bloque>
					<formulario destino="negociosvigentes/personas/9.1.1.do" id="form_negocios">
						<variable id="negocio" />
					</formulario>
					<area_botones>
						<boton estilo="adicionar" accion="agregarNegocio();">Agregar Negocio</boton>
					</area_botones>
				</contenido>
			</principal>
			
			<ventana id="vn_editarNegocio" icono="confirmacion">
				<titulo>
					Editar Negocio
				</titulo>
				<contenido>
					<subtitulo>Edite la información del Negocio</subtitulo>
					<registro>
						<item>Código:</item>
						<valor>
							<cajatexto id="cod_negocio" onchange="validarNegocio();"/>
						</valor>
					</registro>
					<registro>
						<item>Nombre:</item>
						<valor>
							<cajatexto id="nombre" />
						</valor>
					</registro>
					<registro>
						<item>Descripción:</item>
						<valor>
							<cajatexto id="descripcion" />
						</valor>
					</registro>
					<registro>
						<item>Activo:</item>
						<valor>
							<cajachequeo id="activo" />
						</valor>
					</registro>
					<variable id="id_negocio" />
					<variable id="id_modelo" />
					<area_botones>
						<boton estilo="aceptar" accion="guardarNegocio();">Aceptar</boton>
						<boton estilo="cancelar" accion="cerrar_Ventana();">Cancelar</boton>
					</area_botones>
				</contenido>
			</ventana>
		</pagina>
	</xsl:template>
	<xsl:template name="VerNegocios">
		<pagina titulo="Negocios Vigentes">

			<principal>
				<titulo icono="usuarios">Negocios Vigentes</titulo>
				<contenido>

					<bloque-contenido>
						<titulo icono="edicion">Negocios Activos</titulo>
						<contenido>

							<xsl:choose>
								<xsl:when
									test="count(//verNegociosVigentes/listado/Negocio[activo='S'])>0">

									<formulario destino="negociosvigentes/9.2.do" id="form_negocios">
										<variable id="id_negocio" valor="" />
										<tabla>
											<encabezado>
												<titulo>Negocio</titulo>
												<titulo>Numero de Administradores Activos</titulo>
											</encabezado>
											<xsl:for-each
												select="//verNegociosVigentes/listado/Negocio[activo='S']">
												<xsl:sort data-type="number" select="cod_negocio" />
												<fila>
													<valor accion="enviarFormulario({id_negocio})">
														<p>[<xsl:value-of select="cod_negocio" />] | <b><xsl:value-of select="nombre" /></b></p>
													</valor>
													<valor accion="enviarFormulario({id_negocio})">
														<xsl:value-of select="numero_administradores" />
													</valor>
												</fila>
											</xsl:for-each>
										</tabla>
									</formulario>

								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">
										No existen negocios activos en el
										sistema.
									</parrafo>
								</xsl:otherwise>

							</xsl:choose>

						</contenido>
					</bloque-contenido>


					<bloque-contenido>
						<titulo icono="edicion">Negocios Inactivos</titulo>
						<contenido>

							<xsl:choose>
								<xsl:when
									test="count(//verNegociosVigentes/listado/Negocio[activo='N'])>0">

									<formulario destino="negociosvigentes/9.2.do" id="form_negocios">
										<variable id="id_negocio" valor="" />
										<tabla>
											<encabezado>
												<titulo>Negocio</titulo>
												<titulo>Numero de Administradores Activos</titulo>
											</encabezado>
											<xsl:for-each
												select="//verNegociosVigentes/listado/Negocio[activo='N']">
												<fila>
													<valor accion="enviarFormulario({id_negocio})">
														<p>[<xsl:value-of select="cod_negocio" />] | <b><xsl:value-of select="nombre" /></b></p>
													</valor>
													<valor accion="enviarFormulario({id_negocio})">
														<xsl:value-of select="numero_administradores" />
													</valor>
												</fila>
											</xsl:for-each>
										</tabla>
									</formulario>

								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">
										No existen negocios inactivos en el
										sistema.
									</parrafo>
								</xsl:otherwise>

							</xsl:choose>

						</contenido>
					</bloque-contenido>

					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
				</contenido>
			</principal>
		</pagina>
	</xsl:template>

</xsl:stylesheet>
