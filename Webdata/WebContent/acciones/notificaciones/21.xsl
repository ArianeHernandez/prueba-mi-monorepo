<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />
|
		<pagina titulo="Notificaciones">
			
			<xsl:variable name="pagina">
				<xsl:choose>
					<xsl:when test="count(//obtenerNotificacionesAdministrativoPagNotificacion) > 0">
						<xsl:value-of select="//obtenerNotificacionesAdministrativoPagNotificacion/Mapa/pagina"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="//numeroPagina"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:variable>
	
			<javascript>notificaciones/21.js</javascript>
			<stylesheet>21.css</stylesheet>

			<principal>
				<titulo>Notificaciones</titulo>
				<contenido>
					<xsl:if test="//ID_NOTIFICACION != ''">
						<script>
							setTimeout("new function(){marcarNotificacionesLeida('<xsl:value-of select="//ID_NOTIFICACION"/>')}",500);
						</script>
					</xsl:if>
					<xsl:choose>
						<xsl:when test="//contarNotificacionesAdministrativo/Total>0">
							<div class="tabla_borde table-responsive">
								<table class="table table-hover table-striped tb">
									<thead>
										<tr class="tabla_encabezado">
											<td width="1"></td>
											<td>De</td>
											<td>Titulo</td>
											<td>Fecha</td>
										</tr>
									</thead>
									<tbody>
										<xsl:choose>
											<xsl:when test="count(//obtenerNotificacionesAdministrativoPagNotificacion) > 0">
												<xsl:call-template name="fila_notificacion">
													<xsl:with-param name="notificacion" select="//obtenerNotificacionesAdministrativoPagNotificacion/Mapa/lista/Notificacion"/>
													<xsl:with-param name="id_notificacion_sel" select="//ID_NOTIFICACION"/>
												</xsl:call-template>
											</xsl:when>
											<xsl:otherwise>
												<xsl:call-template name="fila_notificacion">
													<xsl:with-param name="notificacion" select="//obtenerNotificacionesAdministrativo/Listado/Notificacion"/>
													<xsl:with-param name="id_notificacion_sel">.</xsl:with-param>
												</xsl:call-template>
											</xsl:otherwise>
										</xsl:choose>
									</tbody>
								</table>
							</div>
							<xsl:variable name="total"
								select="//contarNotificacionesAdministrativo/Total" />
							<xsl:if test="$total != ''">
								<formulario id="form_pag" destino="notificaciones/21.do">
									<paginacion id="_numeropagina" formulario="form_pag"
										numero="{$pagina}" paginacion="{//TAMANO_PAGINA}"
										total="{$total}" />
								</formulario>
							</xsl:if>

						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No hay notificaciones
							</parrafo>
						</xsl:otherwise>

					</xsl:choose>

					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>

				</contenido>
			</principal>

		</pagina>

	</xsl:template>
	<xsl:template name="fila_notificacion">
		<xsl:param name="notificacion"/>
		<xsl:param name="id_notificacion_sel" />
		<xsl:for-each select="$notificacion">
			<tr onclick="leer_notificacion({id_notificacion});" id="fila_{id_notificacion}" 
				class="tabla_fila enc_msj"  name="fila_not">
				<xsl:if test="leida='N'">
					<xsl:attribute name="class">tabla_fila sinleer_notificacion enc_msj</xsl:attribute>
				</xsl:if>
				<td class="ico_msj">&#160;</td>
				<td>
					<xsl:value-of select="emisor" />
				</td>
				<td>
					<xsl:value-of select="substring(titulo, 0, 100)" />
				</td>
				<td>
					<xsl:value-of select="substring(fecha_envio, 0, 17)" />
				</td>
			</tr>
			<tr id="notif_{id_notificacion}" class="tabla_fila cont_msj">
				<xsl:if test="normalize-space($id_notificacion_sel) != normalize-space(id_notificacion)">
					<xsl:attribute name="class">tabla_fila transparencia_0 contenido_oculto</xsl:attribute>
				</xsl:if>
				<td></td>
				<td colspan="4">
					<div>
						<span class="">
							<xsl:value-of select="contenido"></xsl:value-of>
						</span>
					</div>
				</td>
			</tr>
			
		</xsl:for-each>
	</xsl:template>
	

</xsl:stylesheet>
