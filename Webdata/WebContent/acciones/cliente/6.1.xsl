<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoNegocios.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Clientes">

			<principal>

				<javascript>cliente/6.1.js</javascript>

				<titulo>Administración de Clientes</titulo>

				<contenido>
				<div class="box-container">
					<xsl:if test="//eliminarUsuarioNegocio/Salida = 'false'">
						<div class="alert alert-danger"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
							No fue posible eliminar la persona
						</div>
					</xsl:if>


					<bloque-pestanas>

						<pestana titulo="Clientes por Negocio">
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								<xsl:call-template name="LISTADONEGOCIOS">
									<xsl:with-param name="Destino">cliente/6.1.do</xsl:with-param>
									<xsl:with-param name="Negocio" select="//obtenerListadoNegociosActivos/listado/Negocio" />
									<xsl:with-param name="NegocioActual" select="//id_negocio_actual" />
								</xsl:call-template>
									</div>
								</div>
								<xsl:variable name="UsuarioNegocio"
									select="//obtenerUsuariosPorNegocio/Listado/UsuarioNegocio" />
								<xsl:if test="count($UsuarioNegocio) > 0">
								
							
								<tabla>
									<encabezado>
										<titulo>Cliente</titulo>
										<titulo  class="w150">Activo</titulo>
										<titulo class="w150">Eliminar</titulo>
									</encabezado>
									<xsl:for-each select="$UsuarioNegocio">
										<xsl:variable name="accion">
											editarUsuario(
											'<xsl:value-of select="id_persona" />'
											,
											'<xsl:value-of select="id_usuario" />'
											,
											'<xsl:value-of select="identificacion" />'
											, 
											'<xsl:value-of select="tipo_persona" />'
											,
											'<xsl:value-of select="tipo_documento" />'
											)
										</xsl:variable>
										<fila>
											<valor accion="{$accion}">
												<b>
													(<xsl:value-of select="id_usuario"/>) <xsl:value-of select="concat(nombre, ' ', apellido )"></xsl:value-of>
												</b>
												<p>
													<xsl:choose>
														<xsl:when test="tipo_persona='J'">
															Jurídica
														</xsl:when>
														<xsl:otherwise>
															Natural
														</xsl:otherwise>
													</xsl:choose>

													|
													<xsl:value-of select="identificacion" />
												</p>
											</valor>
											<valor class="text-center">
												<cajachequeo2 id="cajachuqueo_{id_persona}"
													valor="S" valor2="N" seleccionado="{boolean(activo='S')}"
													accion="activarUsuario({id_usuario}, 'cajachuqueo_{id_persona}')" />
											</valor>
											<valor class="text-center">
												<boton estilo="eliminar" accion="eliminarUsuarioNegocio('{id_usuario}','{id_negocio}','{id_persona}');"><i class="fa fa-times" aria-hidden="true"></i><span class="hide-xs">&#160;Eliminar</span></boton>
											</valor>
										</fila>
									</xsl:for-each>
								</tabla>
								<xsl:variable name="total"
									select="//contarUsuariosPorNegocio/Total" />
								<xsl:if test="$total != ''">
									<formulario id="form_pag" destino="cliente/6.1.do">
										<paginacion id="_numeropagina" formulario="form_pag"
											numero="{//numeroPagina}" paginacion="{//TAMANO_PAGINA}"
											total="{$total}" />
									</formulario>
								</xsl:if>

								<formulario id="form_usuarionegocio" destino="cliente/6.1.do">
									<variable id="accion.enviar" />
									<variable id="id_usuario" />
									<variable id="id_negocio" valor="{//id_negocio_actual}" />
									<variable id="id_persona" />
								</formulario>
							</xsl:if>
						</pestana>

						<pestana titulo="Crear Nuevo Cliente">
							<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								<formulario id="form_crear" destino="cliente/6.2.do">
	
									<div class="alert alert-info"><i class="fa fa-info-circle" aria-hidden="true"></i>
										Por favor, ingrese los siguientes datos del cliente. Si
										ya existe el cliente registrado, el sistema le presentara la
										información correspondiente.
									</div>
									<div class="row-box">
										<registro>
											<item>Tipo de Persona:</item>
											<valor>
												<cajatextoselector id="tipo_persona">
													<opcion>--Seleccione un tipo--</opcion>
													<opcion valor="J">Jurídica</opcion>
													<opcion valor="N">Natural</opcion>
												</cajatextoselector>
											</valor>
										</registro>
										<registro>
											<item>Tipo de Documento:</item>
											<valor>
												<cajatextoselector id="tipo_documento">
													<opcion>--Seleccione un tipo--</opcion>
												</cajatextoselector>
											</valor>
										</registro>
										<registro>
											<item>Número de identificación:</item>
											<valor>
												<cajatexto id="identificacion" />
											</valor>
										</registro>
									</div>
									<variable id="id_negocio" valor="{//id_negocio_actual}" />
							
								</formulario>
							</div>
							<area_botones>
								<boton estilo="primary" validacion="validarIdentificacion()"
									formulario="form_crear">Continuar</boton>
							</area_botones>

						</pestana>
						<pestana titulo="Buscar Cliente">
							<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
							<xsl:call-template name="LISTADOCLIENTES">
								<xsl:with-param name="tipo_cliente" select="'T'"/>
							</xsl:call-template>
							</div>
						</pestana>

					</bloque-pestanas>

					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>

					<formulario id="form_editarusuario" destino="cliente/6.2.do">
						<variable id="edit.id_usuario" />
						<variable id="edit.id_persona" />
						<variable id="edit.identificacion" />
						<variable id="edit.tipo_persona" />
						<variable id="edit.tipo_documento" />
						<variable id="editar" />
					</formulario>
					</div>
				</contenido>
			</principal>

			<ventana id="vn_eliminar" icono="confirmacion">
				<titulo>Confirmación para Eliminar</titulo>
				<contenido>


						<div class="alert alert-danger" id="mensaje_disponible_empresa">
							<i class="fa fa-exclamation-circle" aria-hidden="true"></i> ¿Está seguro que va a eliminar el cliente para este negocio?
						</div>

					<area_botones>
						<boton estilo="guardar" id="btn_de_no" accion="confirmaUsuarioNegocio();">Aceptar</boton>
						<boton estilo="cancelar" accion="cancelar_E();">Cancelar</boton>
					</area_botones>
				</contenido>
			</ventana>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
