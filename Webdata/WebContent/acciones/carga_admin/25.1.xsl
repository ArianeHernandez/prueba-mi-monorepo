<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Transacción Manual">

			<javascript>webdata/25.1.js</javascript>

			<principal>
				<titulo>Transacción Manual</titulo>
				<contenido>
				<div class="box-container">		
					<bloque>
						<xsl:for-each select="obtenerNegociosPorUsuario/listado/Negocio">
							<div id="div_negocio_{id_negocio}" class="bloqueestilo_resultado" onclick="cargarFormatos('{//ID_PERSONA}', '{id_negocio}')">
								<h1  class="" style="display:inline">
									<xsl:value-of select="nombre" />
								</h1>
								<xsl:if test="normalize-space(descripcion) != ''">
									 - <xsl:value-of select="descripcion"/>
								</xsl:if>
							</div>
						</xsl:for-each>
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
						<variable id="id_negocio" />
					</formulario>
					
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>

				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>

