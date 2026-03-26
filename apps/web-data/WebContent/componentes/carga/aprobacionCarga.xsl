<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://stylesheets/templates/firma/firmarFormulario.xsl"/>
	
	<xsl:decimal-format name="pesos2" NaN="--" decimal-separator="," grouping-separator="."/>
	
	 <xsl:template name="aprobacion-botones">
        <xsl:param name="id_horario_liberacion"/>
        <xsl:param name="id_horario_formato"/>
        <xsl:param name="puede_guardar"/>
        <javascript>cargas/carga.js</javascript>
        <xsl:choose>
            <xsl:when test="$puede_guardar='false'">
                <area_botones>
                    <boton accion="verVentanaRechazarCarga()" estilo="eliminar">Descartar <texto key="CARGA"/>
                    </boton>
                </area_botones>
            </xsl:when>
            <xsl:otherwise>
                <area_botones>
                    <boton accion="verVentanaAprobarCarga('{$id_horario_liberacion}', '{$id_horario_formato}')" estilo="guardar" validacion="validarCarga()">Confirmar <texto key="CARGA"/>
                    </boton>
                    <boton accion="verVentanaRechazarCarga()" estilo="eliminar">Descartar <texto key="CARGA"/>
                    </boton>
                </area_botones>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
	
	<xsl:template name="aprobacion-carga">
		<xsl:param name="siguiente_url"/>
		<xsl:param name="actualizar_negocio"/>
		<xsl:param name="campos_adicionales"/>
		<xsl:param name="nombreFaseProceso"/>
		<xsl:param name="usar_firma_digital"/> <!-- opcional : S or N -->
		
		
		<xsl:variable name="carga" select="//obtenerCarga/Carga"></xsl:variable>
						
		<ventana id="vn_aprobarcarga" icono="confirmacion">
				<titulo>Confirmación de <texto key="Aprobación" /> 
						<a onclick="cerrarVentanaAprobarCarga();
			        		return false;
						" id="btn_cancelar">
							<i class="fa fa-close" style="color: white; float:right"></i>
						</a>
				</titulo>
				<contenido>

					<formulario id="frm_aprobar"
						destino="{$siguiente_url}">
						
						<!-- INICIO INFORMACION DE LA TRANSACCION -->
						<input type="hidden" name="transaccion" value="{$carga/id_carga}"></input>
						<input type="hidden" name="valor_total_transaccion" value="{format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos2')}"></input>
						<input type="hidden" name="realizada_por" value="{concat($carga/nombre_persona, ' ', $carga/apellido_persona)}"></input>
						<input type="hidden" name="fecha_subida_carga" value="{$carga/fecha_subida}"></input>
						<!-- FINAL  INFORMACION DE LA TRANSACCION -->
						
						<variable id="accionaprobar" valor="S" />
						<variable id="fecha_liberacion" />
						
						<xsl:if test="$actualizar_negocio='S'">
							<variable id="accion_actualizar_negocio" valor="{$actualizar_negocio}"/>
							<variable id="negocio_seleccionado" />
						</xsl:if>
						<xsl:copy-of select="$campos_adicionales"></xsl:copy-of>
					</formulario>

					
						
							<div class="row-box form-horizontal">
						
								<div class="Subtitle-2"><h4><b>PASO 3 de 3:</b> Finalizar proceso de radicación</h4></div>
						
								<texto key="MENSAJE VENTANA APROBACION LIBERACION" ocultar_vacio="true"/>
							
								<div class="col-sm-10 col-sm-offset-1">
									<div class="form-group form-group-sm">							
<!-- 										<label class="control-label col-sm-4">Transacción:</label> -->
										<label class="control-label col-sm-4">ID Solicitud:</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/id_carga"></xsl:value-of>
											</span>
										</div>
									</div>
															
										
									<xsl:if test="$carga/valor_total_bigdecimal!=''">
									<div class="form-group form-group-sm">	
										<label class="control-label col-sm-4">Valor Total</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos2')"/>
											</span>
										</div>
									</div>
									
									</xsl:if>
									
									<xsl:if test="//OSM-INIT-SESSION/Info/Persona/nombreCompleto">
										<div class="form-group form-group-sm">	
											<label class="control-label col-sm-4">Realizada por</label>
											<div class="col-sm-8">
												<span class="form-control disable">
													<xsl:value-of select="//OSM-INIT-SESSION/Info/Persona/nombreCompleto"/>
												</span>
											</div>
										</div>
									</xsl:if>
									<xsl:if test="$carga/fecha_subida!='' ">
										<div class="form-group form-group-sm">	
											<label class="control-label col-sm-4">Fecha</label>
											<div class="col-sm-8">
												<span class="form-control disable">
													<xsl:value-of select="$carga/fecha_subida"/>
												</span>
											</div>
										</div>
									</xsl:if>
									<xsl:if test="normalize-space(//OSM-INIT-SESSION/PESO_LIBERADOR) = 'true'">
										<xsl:if test="//obtenerPesoHistorialCarga/Peso!='' or //obtenerProceso/Proceso/peso>=//obtenerLiberadorPorIdentificacion/Liberador/peso ">
											<div class="form-group form-group-sm">	
												<label class="control-label col-sm-4">Peso Liberado del Proceso</label>
												<div class="col-sm-8">
													<span class="form-control disable">
													   <xsl:choose>										   	
													   	 <xsl:when test="//obtenerPesoHistorialCarga/Peso!=''">
													   	 	<xsl:value-of select="//obtenerPesoHistorialCarga/Peso"/>
													   	 </xsl:when>
													   	 <xsl:otherwise>
													   	 	0
													   	 </xsl:otherwise>
													   </xsl:choose>										   			 
												    /<xsl:value-of select="//obtenerProceso/Proceso/peso"/>
												</span>
												</div>
											</div>
											<div class="form-group form-group-sm">	
												<label class="control-label col-sm-4">Peso Liberador :</label>
												<div class="col-sm-8">
													<span class="form-control disable">
														<xsl:value-of select="//obtenerLiberadorPorIdentificacion/Liberador/listadoLiberadorTipoProceso/LiberadorTipoProceso/peso"/>
													</span>
												</div>
											</div>	
										</xsl:if>
									</xsl:if>
									<xsl:if test="$nombreFaseProceso='REVISION'">
							
			<!-- 							<div class="form-group form-group-sm">	 -->
			<!-- 								<label class="control-label col-sm-4">Ingreso token <a href="https://www.google.com/search?q=SignApp">SignApp</a> :</label> -->
			<!-- 								<div class="col-sm-8"> -->
			<!-- 									<input class="form-control" id="token_signapp"></input> -->
			<!-- 								</div> -->
			<!-- 							</div> -->
										
										
									</xsl:if>
									
									<xsl:if test="$nombreFaseProceso='LIBERACION'">
										
										<div class="form-group form-group-sm">	
											<label class="control-label col-sm-4">Ingreso token <a href="https://www.google.com/search?q=SignApp">SignApp</a> :</label>
											<div class="col-sm-8">
												<input class="form-control" id="token_signapp"></input>
											</div>
										</div>
										
									</xsl:if>
								
							</div>
							
						
						
						<xsl:if test="$nombreFaseProceso='LIBERACION'">
							
							<xsl:if test="string-length(//FORMATO_ENTRADA/obtenerFormato/Formato/mensaje_liberacion) > 1">
								<parrafo estilo="resaltado">
									<b><xsl:value-of select="//FORMATO_ENTRADA/obtenerFormato/Formato/mensaje_liberacion" disable-output-escaping="yes"/></b>
								</parrafo>
							</xsl:if>
						</xsl:if>
						
						<input id="nombreFaseProceso" type="hidden" value="{$nombreFaseProceso}"/>
						<input id="id_carga_adj" type="hidden" value="{$carga/id_carga}"/>
						
						<!-- INIT USO DE FIRMA DIGITAL APROBAR -->
						<div class="row-btn">
						 <xsl:choose>
						 	<xsl:when test="$usar_firma_digital='S'">
						 		<xsl:call-template name="firmarFormulario">
									<xsl:with-param name="id_componente_firma">firmaAprobar1</xsl:with-param>
									<xsl:with-param name="formularioParaFirmar">frm_aprobar</xsl:with-param>
									<xsl:with-param name="id_carga"><xsl:value-of select="$carga/id_carga"></xsl:value-of></xsl:with-param>
									<xsl:with-param name="nombreFaseProceso"><xsl:value-of select="$nombreFaseProceso"/></xsl:with-param>
									<xsl:with-param name="funcionJavaScript_previa">cerrarVentanaAprobarCarga()</xsl:with-param>
									<xsl:with-param name="nombre_boton">Firmar y Aprobar</xsl:with-param>
								</xsl:call-template>
						 	</xsl:when>
						 	
						 	<xsl:otherwise>
						 	
						 		<boton estilo="guardar" id="btn_aprobarcarga"
						 			accion="enviarFormularioAprobacion()">
									<texto key="Aprobar" />
								</boton>
						 	
						 	</xsl:otherwise>
						 
						 </xsl:choose>
						<!-- FINAL USO DE FIRMA DIGITAL APROBAR -->
						
<!-- 							<boton estilo="cancelar" id="btn_cancelar" -->
<!-- 								accion="cerrarVentanaAprobarCarga()"> -->
<!-- 								Cerrar Ventana -->
<!-- 							</boton> -->
						</div>
						
					</div>
				</contenido>
			</ventana>
			
			<ventana id="vn_rechazarcarga" icono="advertencia">
				<titulo>Descartar Solicitud 
					<a onclick="cerrarVentanaRechazarCarga();
		        		return false;
					" id="btn_cancelar">
						<i class="fa fa-close" style="color: white; float:right"></i>
					</a>
				</titulo>
				<contenido>

					<formulario id="frm_rechazar" destino="{$siguiente_url}">
						<!-- INICIO INFORMACION DE LA TRANSACCION -->
						<input type="hidden" name="transaccion" value="{$carga/id_carga}"></input>
						<input type="hidden" name="valor_total_transaccion" value="{format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos2')}"></input>
						<input type="hidden" name="realizada_por" value="//OSM-INIT-SESSION/Info/Persona/nombreCompleto"></input>
						<input type="hidden" name="fecha_subida_carga" value="{$carga/fecha_subida}"></input>
						<!-- FINAL  INFORMACION DE LA TRANSACCION -->
					
						<variable id="accionrechazar" valor="S" />
					</formulario>

					<div class="row-box form-horizontal">
						
							<texto key="MENSAJE VENTANA RECHAZO LIBERACION" ocultar_vacio="true"/>
						
							<div class="col-sm-10 col-sm-offset-1">
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Solicitud</label>
										<div class="col-sm-8">
											<span class="form-control disable">
												<xsl:value-of select="$carga/id_carga"></xsl:value-of>
											</span>
										</div>
									</div>
									
									<xsl:if test="$carga/valor_total_bigdecimal!=''">
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4">Valor total</label>
											<div class="col-sm-8">
												<span class="form-control disable">
													<xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos2')"/>
												</span>
											</div>
										</div>
									</xsl:if>
						
									<xsl:if test="//OSM-INIT-SESSION/Info/Persona/nombreCompleto!=''">
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4">Realizada por</label>
											<div class="col-sm-8">
												<span class="form-control disable">
													<xsl:value-of select="//OSM-INIT-SESSION/Info/Persona/nombreCompleto"/>
												</span>
											</div>
										</div> 
									</xsl:if>
									
									<xsl:if test="$carga/fecha_subida!='' ">
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4">Fecha</label>
											<div class="col-sm-8">
												<span class="form-control disable">
													<xsl:value-of select="$carga/fecha_subida"/>
												</span>
											</div>
										</div> 
									</xsl:if>
								
							</div>
							
						
						<!-- INIT USO DE FIRMA DIGITAL APROBAR -->
						<div class="row-btn">
						<xsl:choose>
						 	<xsl:when test="$usar_firma_digital='S'">
						 		<xsl:call-template name="firmarFormulario">
									<xsl:with-param name="id_componente_firma">firmaRechazar1</xsl:with-param>
									<xsl:with-param name="formularioParaFirmar">frm_rechazar</xsl:with-param>
									<xsl:with-param name="id_carga"><xsl:value-of select="$carga/id_carga"></xsl:value-of></xsl:with-param>
									<xsl:with-param name="nombreFaseProceso"><xsl:value-of select="$nombreFaseProceso"/></xsl:with-param>
									<xsl:with-param name="funcionJavaScript_previa">cerrarVentanaRechazarCarga()</xsl:with-param>
									<xsl:with-param name="nombre_boton">Firmar y Rechazar</xsl:with-param>
								</xsl:call-template>
						 	</xsl:when>
						 	
						 	<xsl:otherwise>
						 		<boton estilo="guardar" id="btn_aprobarcarga"
									accion="cerrarVentanaRechazarCarga()"
									formulario="frm_rechazar">
									Descartar
								</boton>
						 	
						 	</xsl:otherwise>
						 
						 </xsl:choose>
						
							
							<!-- FINAL USO DE FIRMA DIGITAL APROBAR -->
<!-- 							<boton estilo="cerrar" id="btn_cancelar" -->
<!-- 								accion="cerrarVentanaRechazarCarga()"> -->
<!-- 								Cerrar Ventana -->
<!-- 							</boton> -->
						</div>
					</div>
				</contenido>
			</ventana>
	</xsl:template>
</xsl:stylesheet>
