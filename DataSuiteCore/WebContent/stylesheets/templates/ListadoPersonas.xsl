<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="LISTADOPERSONAS">
		<xsl:param name="Persona" />
		<xsl:param name="id_negocio" />
		<xsl:param name="id_usuario" />
		<xsl:param name="rol" />
		<xsl:param name="Destino" />
		<xsl:param name="total" />
		<xsl:param name="DestinoPaginacion" />
		<xsl:param name="MostrarSobreflex" />
		<xsl:param name="Titulo" />
		<xsl:param name="TiposProcesosPorIdUsuario" select="/usuarioTipoProcesosDefault" /> 
		<javascript>templates/ListadoPersonas.js</javascript>

		<xsl:choose>
		<xsl:when test="count($Persona) > 0">

			<tabla>
				<encabezado>
					<titulo>Nombre</titulo>
					<xsl:if test="count($TiposProcesosPorIdUsuario)>1">
						<titulo>Tipo <xsl:value-of select="$Titulo"/></titulo>
					</xsl:if>
					<titulo>Activo</titulo>
					<titulo>Acciones</titulo>
				</encabezado>

				<xsl:for-each select="$Persona">

					<xsl:variable name="accion">
						<xsl:choose>
							<xsl:when test="string-length($Destino)>0">
								editarPersona('<xsl:value-of select="id_persona" />',
									'<xsl:value-of select="identificacion" />', '<xsl:value-of select="tipo_persona" />'
									 , '<xsl:value-of select="tipo_documento" />', '<xsl:value-of select="id"></xsl:value-of>')
							 </xsl:when>
						 </xsl:choose>
					 </xsl:variable>
								 
					<fila>
						<valor accion="{$accion}">
							<p>
								<b><xsl:value-of select="concat(nombre, ' ', apellido )"></xsl:value-of></b>
							</p>
							<p>
								<xsl:choose>
									<xsl:when test="tipo_persona='J'">
										Jurídica
									</xsl:when>
									<xsl:otherwise>
										Natural
									</xsl:otherwise>											
								</xsl:choose>
								
								| <xsl:value-of select="identificacion"/></p>
						</valor>
						<xsl:if test="count($TiposProcesosPorIdUsuario)>1">
							<valor>
								<xsl:for-each select="tiposProcesoRol/TipoProcesoRol">
									<xsl:variable name="id_tipo_proceso" select="id_tipo_proceso"/>
									<xsl:for-each select="$TiposProcesosPorIdUsuario">
										<xsl:if test="id_tipo_proceso = $id_tipo_proceso">
											<p><xsl:value-of select="nombre"/></p>
										</xsl:if>
									</xsl:for-each>
								</xsl:for-each>
							</valor>
						</xsl:if>
						<valor>
							<xsl:choose>
								<xsl:when test="string-length($Destino)>0 and normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true' and $rol='LIB'">
									<cajachequeo2 id="cajachuqueo_{id_persona}" valor="S"
										valor2="N" seleccionado="{boolean(activo='S')}"
										accion="inicioActivarLiberador({id_persona}, '{$id_negocio}', '{$id_usuario}', '{$rol}')" />
								</xsl:when>
								<xsl:when test="string-length($Destino)>0">
									<cajachequeo2 id="cajachuqueo_{id_persona}" valor="S"
										valor2="N" seleccionado="{boolean(activo='S')}"
										accion="inicioActivarPersona({id_persona}, '{$id_negocio}', '{$id_usuario}', '{$rol}')" />
								</xsl:when>
								<xsl:otherwise>
									<xsl:choose>
										<xsl:when test="activo='S'">SI</xsl:when>
										<xsl:otherwise>NO</xsl:otherwise>
									</xsl:choose>
								</xsl:otherwise>
							</xsl:choose>
						</valor>

						<valor>
						
							<area_botones>
								<xsl:choose>
									<xsl:when test="string-length($Destino)>0 and normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true' and $rol='LIB'">
										<boton estilo="danger btn-xs eliminar" accion="validarEliminacionLiberador('{id_persona}', '{$id_negocio}', '{$id_usuario}', '{$rol}');">Eliminar</boton>
									</xsl:when>
									<xsl:when test="string-length($Destino)>0">
										<boton estilo="danger  btn-xs eliminar" accion="inicioEliminarPersona('{id_persona}', '{$id_negocio}', '{$id_usuario}', '{$rol}');"><i class="fa fa-ban" aria-hidden="true"/> &#160; Eliminar</boton>
									</xsl:when>
								</xsl:choose>
							
								<xsl:choose>
									<xsl:when test="$MostrarSobreflex='S'">
										<boton estilo="primary  btn-xs editar" accion="generarSobreflex('{id_persona}', '{$id_usuario}');">Generar Sobreflex</boton>
									</xsl:when>
									<xsl:otherwise>
										<boton estilo="primary btn-xs editar" accion="regenerarClave('{id_persona}', '{$rol}');"><i class="fa fa-refresh" aria-hidden="true"/> &#160; Regenerar Clave</boton>
									</xsl:otherwise>
								</xsl:choose>
							</area_botones>
						</valor>

					</fila>
				</xsl:for-each>
			</tabla>
			<xsl:if test="$total != ''">
				<formulario id="form_pag" destino="{$DestinoPaginacion}">
					<paginacion id="_numeropagina" formulario="form_pag" numero="{//numeroPagina}" 
						paginacion="{//TAMANO_PAGINA}" total="{$total}"/>
				</formulario>
			</xsl:if>
			<formulario id="form_editarpersona" destino="{$Destino}">
				<variable id="edit.id_persona" />
				<variable id="edit.identificacion" />
				<variable id="edit.tipo_persona" />	
				<variable id="edit.tipo_documento" />
				<variable id="edit.id" />
				<variable id="editar" />
			</formulario>
			
			<formulario destino="sobreflex/sobreflex_1.do" id="form_sobreflex">
				<variable id="id_usuario_sel" valor=""></variable>
				<variable id="id_persona_sel" valor=""></variable>
			</formulario>
			
			
		</xsl:when>
		<xsl:otherwise>
			<div class="alert alert-danger">
				<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
				<span class="sr-only">Error:</span>
				<p>No se encontraron personas</p>
			</div>
		</xsl:otherwise>
	</xsl:choose>
	</xsl:template>
	
	<xsl:template name="LISTADOPERSONAS_VENTANAS">
		<xsl:param name="todos" />
		<ventana id="vn_cambio_estado_activo" icono="confirmacion">
			<titulo>Confirmación Cambio de Estado</titulo>
			<contenido>

				<bloque estilo="grupo">
					<parrafo estilo="danger" id="mensaje_cambio_activo">
						<i class="fa fa-warning" aria-hidden="true"></i>
					</parrafo>
				</bloque>
				
				<bloque estilo="grupo2">
					<parrafo estilo="danger" id="Procesos_Afectados_Al_Desactivar">
						<i class="fa fa-warning" aria-hidden="true"></i>
					</parrafo>
				</bloque>

				<div class="modal-footer">
					<xsl:choose>
						<xsl:when test="$todos != 'NO'">
							<boton estilo="guardar" accion="activarPersona('N');">No, solo aplicar a este.</boton>
							<boton estilo="guardar" accion="activarPersona('S');">Si, aplicar a todos.</boton>							
							<variable nombre="aplicar_todos" valor="SI" />
						</xsl:when>
						<xsl:otherwise>
							<boton estilo="primary aceptar" accion="activarPersona('N');"><i class="fa fa-floppy-o" aria-hidden="true"/> &#160; Aceptar</boton>
						</xsl:otherwise>
					</xsl:choose>
					<boton estilo="danger" accion="cerrarVentana('vn_cambio_estado_activo',true);"><i class="fa fa-times" aria-hidden="true"/> &#160; Cancelar</boton>
				</div>
			</contenido>
		</ventana>

		<ventana id="vn_eliminar" icono="confirmacion">
			<titulo>Confirmación para Eliminar</titulo>
			<contenido>

				<div class="alert  alert-info">
					<i class="fa fa-info-circle" aria-hidden="true"></i>
					<span class="sr-only">Info:</span>
						<p id="mensaje_cambio_activo">
							¿Desea eliminar la Persona?
						</p>
				</div>
				
				<parrafo estilo="info" id="Procesos_Afectados_Al_Eliminar">
				</parrafo>

				<div class="modal-footer">
					<boton estilo="danger" accion="cerrarVentana('vn_eliminar');"><i class="fa fa-caret-square-o-left" aria-hidden="true"/> &#160; Cancelar</boton>
					<boton estilo="aceptar" accion="eliminarPersona();"><i class="fa fa-floppy-o" aria-hidden="true"/> &#160; Aceptar</boton>
				</div>
			</contenido>
		</ventana>
	</xsl:template>

</xsl:stylesheet>