<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl" />
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl" />
	<xsl:include href="context://stylesheets/templates/CamposAdicionales.xsl"/>
	<xsl:include href="context://componentes/notas_carga/notas_carga.xsl" />
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina
			titulo="{//obtenerFormato/Formato/nombre} - {//obtenerPersona/Persona/nombre}">

			<javascript>administracion_carga/3.3.js</javascript>

			<stylesheet>aprobacion.css</stylesheet>

			<principal>
				<titulo>
					<xsl:value-of select="//obtenerFormato/Formato/nombre" />
					-
					<xsl:value-of select="//obtenerPersona/Persona/nombre" />
					&#160;
					<xsl:value-of select="//obtenerPersona/Persona/apellido" />
					- <texto key="CARGA" />&#160;
					<xsl:value-of select="//obtenerCarga/Carga/id_carga" />
				</titulo>
				<contenido>
				<div class="box-container">
				
					<bloque-pestanas>
						<!-- INFO CARGA -->
						<pestana titulo="Detalle">
							<tabla>
								<encabezado>
									<titulo>Tipo</titulo>
									<titulo>Identificador <texto key="CARGA" /></titulo>
									<titulo>Nombre</titulo>
									<titulo>Fecha de <texto key="CARGA" /></titulo>
									<titulo>Estado</titulo>
									
									<!-- VALOR OPCIONAL -->
									<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
										<titulo>Valor</titulo>
									</xsl:if>
									<titulo>N° registros</titulo>	
								</encabezado>
								<xsl:for-each select="//obtenerCarga/Carga">
									<fila>
										<valor>
											<xsl:choose>
												<xsl:when test="extension='xls'">
													<bloque estilo="extension_xls" />
												</xsl:when>
												<xsl:otherwise>
													<bloque estilo="extension_none" />
												</xsl:otherwise>
											</xsl:choose>
										</valor>
										<valor>
											<xsl:value-of select="id_carga" />
										</valor>
										<valor>
											<xsl:value-of select="nombre" />
										</valor>
										<valor>
											<xsl:value-of select="fecha_subida" />
										</valor>
										<valor>
											<parrafo estilo="resaltado">
												<estado key="{estado}"/>
											</parrafo>
										</valor>
										
										<!-- VALOR OPCIONAL -->
										<xsl:if test="valor_total_bigdecimal!=''">
											<valor>
												<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
											</valor>
										</xsl:if>
										<valor>
												<xsl:value-of select="numero_registros_bigdecimal" />
										</valor>
									</fila>
								</xsl:for-each>
							</tabla>

							<!-- DETALLE DE LA CARGA -->


								<!--<formulario id="frm_aprobar" destino="administracion_carga/3.4.do"> 
									<variable id="accionaprobar" valor="S" /> <xsl:for-each select="//obtenerDatosCargaPorFormato/nodo"> 
									<xsl:call-template name="visualizacion-nodo"/> </xsl:for-each> </formulario> -->
								<xsl:call-template name="DETALLE_CARGA_AJAX" >
									<xsl:with-param name="validacionesActivas">false</xsl:with-param>
								</xsl:call-template>
								
							<xsl:if test="count(//obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo) > 0 and //obtenerCarga/Carga/estado='S'">
								<bloque-contenido>
									<titulo>
										Información de liberación
									</titulo>
									<contenido>
										<xsl:call-template name="CAMPOS_ADICIONALES"/>
									</contenido>
								</bloque-contenido>
								
							</xsl:if>
								<formulario id="frm_aprobar" destino="administracion_carga/3.4.do">
									<variable id="accionaprobar" valor="S" />
									
									<xsl:for-each select="obtenerListasDCPorFormatoValores/listado/ListaDinamicaCampo">
										<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_campo" value="{id_campo}" />
										<input type="hidden" name="listaValoresDinamicos:{id_campo}.id_carga" value="{//obtenerCarga/Carga/id_carga}" />
										<input type="hidden" name="listaValoresDinamicos:{id_campo}.valor" id="listaValoresDinamicos_{id_campo}_valor" value="" />
									</xsl:for-each>
									
								</formulario>
						</pestana>

						<!-- BOTONES DE DESTINO -->

						<pestana titulo="Ejecuciones">
							<xsl:if test="pestana = '2'">
								<xsl:attribute name="visible">true</xsl:attribute>
							</xsl:if>

							<!-- EJECUCIONES -->
							<bloque-contenido>
								<titulo>
									Ejecuciones
								</titulo>
								<contenido>
									<div id="div_ejecuciones">
			
									<parrafo><texto key="EJECUCIONES REALIZADAS POR CARGA" /></parrafo>

									<xsl:for-each select="//obtenerDestinosFormato/listado/Destino">
										<input type="hidden" id="destino_{id_destino}" value="{titulo}" />
									</xsl:for-each>
									<input type="hidden" id="id_carga" value="{//id_carga_seleccionada}"/>
									<div class="tabla_borde table-responsive">
										<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
											<tbody id="cc_contenido_ejecuciones">
												<tr class="tabla_encabezado">
													<td>DESTINO</td>
													<td>ESTADO</td>
													<td>FECHA INICIAL</td>
													<td>FECHA FINAL</td>
												</tr>
											</tbody>
										</table>
									</div>
									</div>
									<bloque display="none">
										<table>
											<tbody id="PLANTILLA_FILA_EJEC">
												<tr class="tabla_fila" onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'" name="[ 6 ]" 
													onclick="verVentanaDetalleLog('[ 5 ]')" id="fila_ejec_[ 5 ]">
													<td>
														[ 1 ]
													</td>
													<td  id="estado_ejec_[ 5 ]">
														[ 2 ]
													</td>
													<td>
														[ 3 ]
													</td>
													<td id="final_ejec_[ 5 ]">
														[ 4 ]
													</td>
												</tr>
											</tbody>
										</table>
									</bloque>
		
									<parrafo id="msj_sinejecucion">No se han realizado ejecuciones a ésta Carga.</parrafo>

									<xsl:if test="//obtenerCarga/Carga/estado='C' or //obtenerCarga/Carga/estado='J'">
										<xsl:for-each select="//obtenerDestinosFormato/listado/Destino">
											<xsl:variable name="idform" select="generate-id(.)" />
											<formulario id="frm_enviar_{$idform}" destino="administracion_carga/3.6.do">
												<variable id="id_carga" valor="{//obtenerCarga/Carga/id_carga}" />
												<variable id="id_destino" valor="{id_destino}" />
											</formulario>
										</xsl:for-each>

										<area_botones>
											<xsl:for-each select="//obtenerDestinosFormato/listado/Destino">
												<xsl:variable name="idform" select="generate-id(.)" />
												<boton estilo="primary guardar" formulario="frm_enviar_{$idform}" accion="" id="destino_{$idform}">
													<xsl:value-of select="titulo" />
												</boton>
											</xsl:for-each>
										</area_botones>
									</xsl:if>
								</contenido>
							</bloque-contenido>
						</pestana>
						
						<!-- NOTAS CARGA -->

						<pestana titulo="Notas">
							<xsl:call-template name="notas_carga">
								<xsl:with-param name="permiteAgregar">true</xsl:with-param>
							</xsl:call-template>
						</pestana>
					
						<pestana titulo="Adjuntos">
							<xsl:call-template name="archivos_adjuntos">
								<xsl:with-param name="permiteAdjuntar">
									false
								</xsl:with-param>
								<xsl:with-param name="permiteEliminar">false</xsl:with-param>
							</xsl:call-template>
						</pestana>
						<!-- APROBAR / DESAPROBAR -->
					</bloque-pestanas>
					<xsl:if test="//obtenerCarga/Carga/estado='S'">
						<area_botones>
							<boton estilo="primary guardar" accion="verVentanaAprobarCarga()">Aprobar <texto key="CARGA" /></boton>
							<boton estilo="danger eliminar" accion="verVentanaRechazarCarga()">Rechazar <texto key="CARGA" /></boton>
						</area_botones>
					</xsl:if>
					
					<area_botones>
						<xsl:choose>
						  <xsl:when test="autorizado='true'">
						  	<boton estilo="primary volver" destino="administracion_carga/3b.do">Volver</boton>
						  </xsl:when>
						  <xsl:otherwise>
						  	<boton estilo="primary volver" destino="administracion_carga/3.2.do">Volver</boton>
						  </xsl:otherwise>
						</xsl:choose>	
					</area_botones>
				</div>
				</contenido>
			</principal>

			<ventana id="vn_aprobarcarga" icono="confirmacion">
				<titulo>Confirmación</titulo>
				<contenido>
					<subtitulo>
						<texto key="SEGURO DESEA APROBAR LA CARGA" />
					</subtitulo>

					<area_botones>
						<boton estilo="primary guardar" id="btn_aprobarcarga" accion="cerrarVentanaAprobarCarga()"
							formulario="frm_aprobar">
							Aprobar
						</boton>
						<boton estilo="danger" id="btn_cancelar" accion="cerrarVentanaAprobarCarga()">
							Cerrar
							Ventana
						</boton>
					</area_botones>
					<xsl:apply-templates/>
				</contenido>
			</ventana>

			<ventana id="vn_detalleLog" icono="confirmacion">
				<titulo>Detalle</titulo>
				<contenido>

					<bloque>
						<div class="tabla_borde tabla_ventana">
							<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
								<tbody id="cc_contenido_ventana_detalle">
									<tr class="tabla_encabezado">
										<td>FECHA</td>
										<td>COD ERROR</td>
										<td>DESCRIPCION</td>
									</tr>
								</tbody>
							</table>
							<table style="display:none">
								<tbody id="">
									<tr class="tabla_encabezado" id="file_encabezado">
										<td>FECHA</td>
										<td>COD ERROR</td>
										<td>DESCRIPCION</td>
									</tr>
								</tbody>
							</table>
						</div>
					</bloque>

					<bloque display="none">
						<table>
							<tbody id="PLANTILLA_FILA">
								<tr class="tabla_fila" onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'" 
									name="[ 4 ]" id="fila_log_[ 5 ]">
									<td>
										[ 1 ]
									</td>
									<td>
										[ 2 ]
									</td>
									<td>
										[ 3 ]
									</td>
								</tr>
							</tbody>
						</table>
					</bloque>

					<area_botones>
						<boton estilo="danger" id="btn_cancelar" accion="cerrarVentanaDetalleLog()">
							Cerrar
							Ventana
						</boton>
					</area_botones>
				</contenido>
			</ventana>

			<ventana id="vn_rechazarcarga" icono="advertencia">
				<titulo>Confirmación</titulo>
				<contenido>

					<formulario id="frm_rechazar" destino="administracion_carga/3.4.do">
						<variable id="accionrechazar" valor="S" />
					</formulario>

					<area_botones>
						<subtitulo>
							<texto key="SEGURO DE RECHAZAR LA CARGA" />
						</subtitulo>
						<boton estilo="primary guardar" id="btn_aprobarcarga" accion="cerrarVentanaRechazarCarga()"
							formulario="frm_rechazar">
							Rechazar
						</boton>
						<boton estilo="danger" id="btn_cancelar" accion="cerrarVentanaRechazarCarga()">
							Cerrar
							Ventana
						</boton>
					</area_botones>
				</contenido>
			</ventana>

		</pagina>

	</xsl:template>


	<xsl:template name="visualizacion-nodo_old">

		<xsl:choose>

			<xsl:when test="@type='list'">

				<ocultable visible="false" textovisible="ocultar"
					textooculto="ver">
					<xsl:if test="count(element)>1">
						<xsl:attribute name="textooculto">ver (<xsl:value-of
							select="count(element)" />)</xsl:attribute>
					</xsl:if>

					<tabla>

						<encabezado>
							<titulo>Aprobar</titulo>
							<xsl:for-each select="element[1]/nodo">
								<titulo>
									<xsl:value-of select="@name" />
								</titulo>
							</xsl:for-each>
						</encabezado>

						<xsl:for-each select="element">
							<fila>
								<xsl:call-template name="caja_aprob" />
								<xsl:for-each select="nodo">
									<valor>
										<xsl:call-template name="visualizacion-nodo" />
									</valor>
								</xsl:for-each>
							</fila>
						</xsl:for-each>
					</tabla>

				</ocultable>

			</xsl:when>

			<xsl:when test="@type='object'">

				<ocultable visible="false" textovisible="ocultar"
					textooculto="ver">

					<tabla>
						<encabezado>
							<titulo>Aprobar</titulo>
							<xsl:for-each select="nodo">
								<titulo>
									<xsl:value-of select="@name" />
								</titulo>
							</xsl:for-each>
						</encabezado>

						<fila>
							<xsl:call-template name="caja_aprob" />
							<xsl:for-each select="nodo">
								<valor>
									<xsl:call-template name="visualizacion-nodo" />
								</valor>
							</xsl:for-each>
						</fila>
					</tabla>

				</ocultable>

			</xsl:when>

			<xsl:otherwise>
				<bloque
					style="color:{@colorletra}; background-color: {@colorfondo}; padding: 5px">
					<xsl:value-of select="@value" />
				</bloque>
			</xsl:otherwise>

		</xsl:choose>

	</xsl:template>
	<xsl:template name="caja_aprob">

		<xsl:if test="@ESTADO='R'">
			<xsl:attribute name="estilo">fila_rechazado</xsl:attribute>
		</xsl:if>
		<valor>
			<div>
				<xsl:if test="//obtenerFormato/Formato/rechaza_registro = 'S'">
					<xsl:attribute name="onclick">
						activar(this, '{@id_estructura}_{@ID}');
					</xsl:attribute>
				</xsl:if>
				<xsl:attribute name="class">
				<xsl:choose>
					<xsl:when test="@ESTADO='R'">
						caja_aprobar caja_aprobar_cancel
					</xsl:when>
					<xsl:otherwise>
						caja_aprobar caja_aprobar_ok
					</xsl:otherwise>
				</xsl:choose>
				</xsl:attribute>
				<input type="hidden" name="registros" id="{@id_estructura}_{@ID}"
					value="" />
			</div>
		</valor>
	</xsl:template>
</xsl:stylesheet>
