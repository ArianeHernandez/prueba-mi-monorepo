<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Carga de Información">

			<javascript>carga_informacion/1.js</javascript>

			<principal>
				<titulo>Carga de Información</titulo>
				<contenido>
				<div class="box-container">
					<xsl:choose>
						<xsl:when test="count(//listarProcesosPreparador/Listado/Proceso)>0">
							<bloque>
								<xsl:for-each select="//listarProcesosPreparador/Listado/Proceso">
									<div id="div_proceso_{id_proceso}" class="bloqueestilo_resultado">
										<h1 onclick="cargarFormatos('{id_proceso}')" class=""><xsl:value-of select="nombre" /></h1>
									</div>
								</xsl:for-each>
								<xsl:variable name="total"
									select="//contarProcesosPreparador/Total" />
								<xsl:if test="$total != ''">
									<formulario id="form_pag" destino="carga_informacion/1.do">
										<paginacion id="_numeropagina" formulario="form_pag"
											numero="{//numeroPagina}" paginacion="{//TAMANO_PAGINA}"
											total="{$total}" />
									</formulario>
								</xsl:if>
							</bloque>
							<div style="display:none" id="div_formatos">
								<bloque>
									<div class="tabla_borde table-responsive">
										<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
											<thead>
												<tr class="tabla_encabezado">
													<td>Nombre Formato</td>
													<td>Descripción</td>
												</tr>
											</thead>
											<tbody id="cont_formatos">
											</tbody>
										</table>
									</div>
								</bloque>
							</div>
							<div style="display:none">
								<table icono="service">
									<tbody id="PLANTILLA_FILA">
										<tr onclick="enviar('[ 1 ]', '[ 4 ]')" class="tabla_fila" onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
											<td>[ 2 ]</td>
											<td>[ 3 ]</td>
										</tr>
									</tbody>
								</table>
							</div>

							<formulario id="form_formato" destino="carga_informacion/1a.do">
								<variable id="id_formato" />
								<variable id="id_proceso" />
							</formulario>

						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen procesos asociados a su
								usuario.
							</parrafo>
						</xsl:otherwise>

					</xsl:choose>

					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>

				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
