<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />


	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina>
			<javascript>tipo_archivo/tipo_archivo.js</javascript>
			<stylesheet>reportePersonalizado/reporte.css</stylesheet>
			<principal>

				<contenido>
					<div id="msg_modal_result" class="modal-msg" style="display:none">

						<!-- Modal content -->
						<div class="modal-msg-content card">
							<div class="tb-text">
								<div class="Subtitle-2">Tipo de archivo</div>

							</div>
							<div class="divider"></div>
							<p id="message_result"></p>
							<div style="text-align: center;">
								<button class="l-button s-buttonenable-primary" onclick="cerrarMsg()"
									id="boton_aceptar">Aceptar</button>
							</div>
						</div>
					</div>
					<div id="msg_modal" class="modal-msg">

						<!-- Modal content -->
						<div class="modal-msg-content card">
							<div class="tb-text">
								<div class="Subtitle-2">Tipo de archivo</div>

							</div>
							<div class="divider"></div>

							<div class="row col-md-12 margin-row">
								<div class="col-md-6 Normal-1 form-label">Id</div>
								<div class="col-md-6">
									<input id="tipo_archivo_id" class="input-default form-input"
										type="text" placeholder="Ingresar valor" />
								</div>
							</div>
							<div class="row col-md-12 margin-row">
								<div class="col-md-6 Normal-1 form-label">Nombre</div>
								<div class="col-md-6">
									<input id="tipo_archivo_nombre" class="input-default form-input"
										type="text" placeholder="Ingresar valor" />
								</div>
							</div>
							<div class="row col-md-12 margin-row">
								<div class="col-md-6 Normal-1 form-label">Activo</div>
								<div class="col-md-6">
									<select id="tipo_archivo_estado"
                                        class="select-default form-input">
                                        <option value="S">Si</option>
                                        <option value="N">No</option>
                                    </select>
								</div>
							</div>

							<div class="divider"></div>
							<div style="text-align: center;">
								<button class="l-button s-buttonenable-primary" onclick="enviarSolicitud()"
									id="boton_enviar">Guardar</button>
								<button class="l-button s-buttonenable-primary" onclick="enviarActualizarSolicitud()"
									id="boton_actualizar">Actualizar</button>
								<button class="l-button s-buttonenable-primary" onclick="cerrarModal()"
									id="boton_cerrar">Cancelar</button>
							</div>

						</div>
					</div>
					<div class="row-btn">
						<a estilo="btn btn-sm btn-primary" onclick="abrirModal()"
							class="btn btn-sm btn-primary">
							<i aria-hidden="true" class="fa fa-pencil-square-o"></i>
							Crear Tipo de Archivo
						</a>
					</div>
					<div class="tabla_borde table-responsive">
						<table cellpadding="0" cellspacing="0"
							class="table table-hover table-striped">
							<tbody id="tabla_tipo_archivo">
								<tr class="tabla_encabezado">
									<td>Id</td>
									<td>Nombre</td>
									<td>Activo</td>
								</tr>

							</tbody>
						</table>
					</div>

				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>