<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:include href="context://common/xsl/osm_page.xsl"/>
    <xsl:include href="context://componentes/menu/acciones.xsl"/>
    <xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
    <xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>
    <xsl:include href="context://stylesheets/templates/firma/InformacionFirmaDigitalCarga.xsl"/>
    <xsl:include href="context://componentes/notas_carga/notas_carga.xsl" />
    <xsl:decimal-format NaN="--" decimal-separator="," grouping-separator="." name="pesos"/>
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
        <pagina titulo="Detalle - {//obtenerPersona/Persona/nombre}">
            <javascript>administracion_carga/proceso/22.2.js</javascript>
            <stylesheet>aprobacion.css</stylesheet>
            <principal>
                <titulo>Proceso <xsl:value-of select="//obtenerProcesoAdmin/Proceso/nombre"/> / <texto key="CARGA"/>  No. <xsl:value-of select="//obtenerCarga/Carga/id_carga"/>
                </titulo>
                <contenido>
                	<div class="box-container">
                	<variable id="id_carga" valor="{//obtenerCarga/Carga/id_carga}"/>
                    <bloque-pestanas>
                        <pestana titulo="Información general">
                            <contenido>
                                <div>
                                    <!-- INFO CARGA -->
                                    <tabla>
                                        <encabezado>
                                            <titulo>Tipo</titulo>
                                            <titulo>Identificador <texto key="CARGA"/>
                                            </titulo>
<!--                                             <titulo>Nombre</titulo> -->
											<titulo>Deudor Solicitante</titulo>
                                            <titulo>Fecha de <texto key="Liberación"/></titulo>
                                            <titulo>Estado</titulo>
                                            <!-- VALOR OPCIONAL -->
                                            <xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
                                                <titulo>Valor</titulo>
                                            </xsl:if>
<!--                                             <titulo>N° Registros</titulo> -->
                                        </encabezado>
                                        <xsl:for-each select="//obtenerCarga/Carga">
                                            <fila>
                                                <valor>
                                                    <xsl:choose>
                                                        <xsl:when test="extension='xls'">
                                                            <bloque estilo="extension_xls"/>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <bloque estilo="extension_none"/>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </valor>
                                                <valor>
                                                    <xsl:value-of select="id_carga"/>
                                                </valor>
                                                <valor>
                                                    <xsl:value-of select="concat(nombre_usuario, ' ', apellido_usuario)"/>
                                                </valor>
                                                <valor>
                                                    <xsl:value-of select="fecha_liberacion"/>
                                                </valor>
                                                <valor>
                                                    <b>
                                                        <estado cliente="S" key="{estado}"/>
                                                    </b>
                                                </valor>
                                                <!-- VALOR OPCIONAL -->
                                                <xsl:if test="valor_total_bigdecimal!=''">
                                                    <valor>
                                                        <xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
                                                    </valor>
                                                </xsl:if>
<!--                                                 <valor> -->
<!--                                                     <xsl:value-of select="numero_registros_bigdecimal"/> -->
<!--                                                 </valor> -->
                                            </fila>
                                        </xsl:for-each>
                                    </tabla>
                                </div>
                                
                                <xsl:call-template name="INFORMACION_FIRMA_DIGITAL_CARGA"/>
                                
                            	<formulario destino="administracion_carga/proceso/22.3.do" id="frm_aprobar">
                                    <variable id="id_instancia" valor="{//id_instancia}"/>
                                    <variable id="id_formato_salida" valor="{//id_formato_salida}"/>
                                    <variable id="accionaprobar" valor="S"/>
                                </formulario>
                            	
                                <formulario destino="administracion_carga/proceso/22.4.do" id="frm_accion">
                                    <variable id="id_instancia" valor="{//id_instancia}"/>
                                    <variable id="id_formato_salida" valor="{//id_formato_salida}"/>
                                    <variable id="id_accion" valor=""/>
                                    <!-- OBJETO INSTANCIA_ACCION-->
                                    <variable id="InstanciaAccion.id_instancia" valor="{//id_instancia}"/>
                                    <variable id="InstanciaAccion.id_accion" valor=""/>
                                    <variable id="InstanciaAccion.id_proceso_admin" valor="{//id_proceso_admin}"/>
                                    <variable id="InstanciaAccion.id_administrativo" valor="{//id_administrativo}"/>
                                    <variable id="InstanciaAccion.id_carga" valor="{//id_carga}"/>
                                </formulario>
                                
                                <xsl:if test="count(//obtenerAccionesRedireccionPorInstancia/listado/AccionRedireccion)>0">
                                    <xsl:for-each select="//obtenerAccionesRedireccionPorInstancia/listado/AccionRedireccion">
                                        <formulario destino="{endpoint}" id="frm_{id_accion_redireccion}">
                                        	<variable id="id_carga" valor="{//id_carga}"/>
                                        	<variable id="id_instancia" valor="{//id_instancia}" />
                                        	<variable id="id_administrativo" valor="{//id_administrativo}" />
                                        	<variable id="id_formato_salida" valor="{//id_formato_salida}"/>
                                        	<variable id="id_proceso_admin" valor="{//id_proceso_admin}"/>
										</formulario>
                                    </xsl:for-each>
                                </xsl:if>
                                
                                <xsl:if test="count(//obtenerCargasSimilaresLiberadas/listado/Carga)>0">
                                    <div id="area_advertencia">
                                        <h2 class="bloque_contenido_titulo">Advertencia</h2>
                                        <xsl:if test="count(//obtenerCargasSimilaresLiberadas/listado/Carga)=1">
                                            <p>
                                                <b class="txt_alerta">
                                                    <texto key="LA CARGA TIENE EL MISMO VALOR Y NUMERO DE PAGOS QUE LA CARGA"/>:</b>
                                            </p>
                                        </xsl:if>
                                        <xsl:if test="count(//obtenerCargasSimilaresLiberadas/listado/Carga)>1">
                                            <p>
                                                <b>:</b>
                                            </p>
                                        </xsl:if>
                                        <tabla>
                                            <encabezado>
                                                <titulo>Tipo</titulo>
                                                <titulo>Identificador <texto key="CARGA"/>
                                                </titulo>
<!--                                                 <titulo>Nombre</titulo> -->
                                                <titulo>Deudor Solicitante</titulo>
                                                <titulo>Fecha de Liberación</titulo>
                                                <titulo>Estado</titulo>
                                                <!-- VALOR OPCIONAL -->
                                                <xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
                                                    <titulo>Valor</titulo>
                                                </xsl:if>
<!--                                                 <titulo>N° registros</titulo> -->
                                            </encabezado>
                                            <xsl:for-each select="//obtenerCargasSimilaresLiberadas/listado/Carga">
                                                <fila>
                                                    <valor>
                                                        <xsl:choose>
                                                            <xsl:when test="extension='xls'">
                                                                <bloque estilo="extension_xls"/>
                                                            </xsl:when>
                                                            <xsl:otherwise>
                                                                <bloque estilo="extension_none"/>
                                                            </xsl:otherwise>
                                                        </xsl:choose>
                                                    </valor>
                                                    <valor>
                                                        <xsl:value-of select="id_carga"/>
                                                    </valor>
                                                    <valor>
                                                        <xsl:value-of select="concat(nombre_usuario, ' ', apellido_usuario)"/>
                                                    </valor>
                                                    <valor>
                                                        <xsl:value-of select="fecha_liberacion"/>
                                                    </valor>
                                                    <valor>
                                                        <b>
                                                            <estado cliente="S" key="{estado}"/>
                                                        </b>
                                                    </valor>
                                                    <!-- VALOR OPCIONAL -->
                                                    <xsl:if test="valor_total_bigdecimal!=''">
                                                        <valor>
                                                            <xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
                                                        </valor>
                                                    </xsl:if>
<!--                                                     <valor> -->
<!--                                                         <xsl:value-of select="numero_registros_bigdecimal"/> -->
<!--                                                     </valor> -->
                                                </fila>
                                            </xsl:for-each>
                                        </tabla>
                                    </div>
                                </xsl:if>
                                <area_botones>
                                    <!-- ACCIONES INTERMEDIAS DE LA INSTANCIA-->
                                    <xsl:choose>
                                        <!-- SI LA MISMA CARGA ESTA PENDIENTE EN INSTANCIAS PREVIAS NO SE MUESTRAN NINGUNO DE LOS BOTONES-->
                                        <xsl:when test="count(//obtenerInstanciasPreviasConCargaActualPendiente/lista/Instancia)>0">
                                            <nota>
												No se puede realizar ninguna acción sobre la transacción <b>
                                                    <xsl:value-of select="//id_carga"/>
                                                </b>  mientras las siguientes instancias del proceso
												no hayan dado un concepto sobre ésta.
												<br/>
                                                <br/>
												Instancias:
												<xsl:for-each select="//obtenerInstanciasPreviasConCargaActualPendiente/lista/Instancia">
													-<b>
                                                        <xsl:value-of select="nombre"/>
                                                    </b>
                                                </xsl:for-each>
                                            </nota>
                                        </xsl:when>
                                        <!-- SI LA MISMA CARGA NO ESTA PENDIENTE EN INSTANCIAS PREVIAS SE MUESTRAN LOS BOTONES-->
                                        <xsl:when test="count(//obtenerInstanciasPreviasConCargaActualPendiente/lista/Instancia)=0 and //obtenerCarga/Carga/estado='S'">
                                            <xsl:if test="count(//obtenerAccionesRedireccionPorInstancia/listado/AccionRedireccion)>0">
                                                <xsl:for-each select="//obtenerAccionesRedireccionPorInstancia/listado/AccionRedireccion">
                                                    <boton accion="ejecutarAccionRedireccion('{id_accion_redireccion}')" estilo="guardar">
                                                        <xsl:value-of select="nombre"/>
                                                    </boton>
                                                </xsl:for-each>
                                            </xsl:if>
                                            <xsl:if test="count(//obtenerAccionesConInstanciaDestinoPorInstanciaOrigen/listado/Accion)>0">
                                                <xsl:for-each select="//obtenerAccionesConInstanciaDestinoPorInstanciaOrigen/listado/Accion">
                                                    <xsl:if test="oculto!='S' or count(oculto)=0">
                                                        <boton accion="verVentanaEjecutarAccion('{id_accion}', '{nombre}', '{mensaje_ejecucion}')" estilo="guardar">
                                                            <xsl:value-of select="nombre"/>
                                                        </boton>
                                                    </xsl:if>
                                                </xsl:for-each>
                                            </xsl:if>
                                            <!-- APROBAR / DESAPROBAR -->
                                            <xsl:if test="//obtenerInstanciaDelProcesoPorCarga/instancia/aprobar ='S' or //obtenerInstanciaDelProcesoPorCarga/instancia/rechazar ='S'">
                                                <xsl:if test="//obtenerInstanciaDelProcesoPorCarga/instancia/aprobar ='S'">
                                                    <boton accion="verVentanaAprobarCarga()" estilo="guardar">Aprobar Solicitud</boton>
                                                </xsl:if>
                                                <xsl:if test="//obtenerInstanciaDelProcesoPorCarga/instancia/rechazar ='S'">
                                                    <boton accion="verVentanaRechazarCarga()" estilo="eliminar">Rechazar Solicitud</boton>
                                                </xsl:if>
                                            </xsl:if>
                                        </xsl:when>
                                    </xsl:choose>
                                    <boton destino="administracion_carga/proceso/22.1.do" estilo="volver">Volver</boton>
                                </area_botones>
                            </contenido>
                        </pestana>
                        <pestana titulo="Detalle">
                            <!-- DETALLE DE LA CARGA -->
                            <xsl:call-template name="DETALLE_CARGA_AJAX">
                                <xsl:with-param name="validacionesActivas">false</xsl:with-param>
                            </xsl:call-template>
                        </pestana>
                        
                        <!-- NOTAS CARGA -->
<!-- se desactiva opcion para supersociedades -->
<!-- 						<pestana titulo="Notas"> -->
<!-- 							<xsl:call-template name="notas_carga"> -->
<!-- 								<xsl:with-param name="permiteAgregar">true</xsl:with-param> -->
<!-- 								<xsl:with-param name="id_instancia"><xsl:value-of select="id_instancia"/></xsl:with-param> -->
<!-- 							</xsl:call-template> -->
<!-- 						</pestana> -->
						
                        <!-- ARCHIVOS ADJUNTOS -->
                        <pestana pdfview="false" titulo="Archivos adjuntos">
                            <xsl:call-template name="archivos_adjuntos">
<!--                             se desactiva opcion para supersociedades -->
<!--                                 <xsl:with-param name="permiteAdjuntar"><xsl:choose><xsl:when test="//obtenerInstanciaDelProcesoPorCarga/instancia/adicionar_documentos='S'">true</xsl:when><xsl:otherwise>false</xsl:otherwise></xsl:choose></xsl:with-param> -->
<!--                                 <xsl:with-param name="permiteGestionar"><xsl:choose><xsl:when test="//obtenerInstanciaDelProcesoPorCarga/instancia/gestionar_documentos='S'">true</xsl:when><xsl:otherwise>false</xsl:otherwise></xsl:choose></xsl:with-param> -->
                                <xsl:with-param name="id_instancia"><xsl:value-of select="id_instancia"/></xsl:with-param>
                                <xsl:with-param name="permiteEliminar">false</xsl:with-param>
                            </xsl:call-template>
                        </pestana>
                        <!--HISTORIAL DE LA CARGA-->
                        <pestana titulo="Historial">
                            <contenido>
                                <parrafo>
                                    <br/>A continuación se presenta las instancias por las cuales ha trasncurrido la <texto key="CARGA"/>.<br/>
                                </parrafo>
                                <tabla>
                                    <encabezado>
                                        <titulo>Instancia del proceso</titulo>
                                        <titulo>Fecha de llegada a la instancia</titulo>
                                        <titulo>Fecha de salida a la instancia</titulo>
                                        <titulo>Persona encargada</titulo>
                                    </encabezado>
                                    <xsl:for-each select="//obtenerHistorialCargaInstancia/listado/CargaInstancia">
                                        <fila>
                                            <valor>
                                                <xsl:value-of select="nombre_instancia"/>
                                            </valor>
                                            <valor>
                                                <xsl:value-of select="fecha_llegada"/>
                                            </valor>
                                            <valor>
                                                <xsl:value-of select="fecha_salida"/>
                                            </valor>
                                            <valor>
                                                <xsl:value-of select="nombre_persona"/>
													 
													<xsl:value-of select="apellido_persona"/>
                                            </valor>
                                        </fila>
                                    </xsl:for-each>
                                </tabla>
                            </contenido>
                        </pestana>
                    </bloque-pestanas>
                   </div>
                </contenido>
            </principal>
            <!-- VENTANAS DE CONFIRMACION -->
            <xsl:variable name="carga" select="//obtenerCarga/Carga"/>
            <ventana icono="confirmacion" id="vn_accion_instancia">
                <titulo>Ejecutar Acción</titulo>
                <contenido>
                	<div id="contenido_ventana_ejecutar_accion">
	                     <div class="row-box form-horizontal">
							
								<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i>¿Está Seguro que desea ejecutar la acción?</div>
								
								 <div class="col-sm-10 col-sm-offset-1">
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4"> Cliente</label>
											<div class="col-sm-8">
												<span class="">
													<xsl:value-of select="$carga/nombre_usuario"/> <xsl:value-of select="$carga/apellido_usuario"/>
												</span>
											</div>
										</div>
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4"> Acción</label>
											<div class="col-sm-8">
												<span class="">
													<span id="nombre_accion"/>
												</span>
											</div>
										</div>
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4"> Transacción</label>
											<div class="col-sm-8">
												<span class="">
													<xsl:value-of select="$carga/id_carga"/>
												</span>
											</div>
										</div>
										<div class="form-group form-group-sm">							
											<label class="control-label col-sm-4">Fecha de liberación</label>
											<div class="col-sm-8">
												<span class="">
													<xsl:value-of select="$carga/fecha_subida"/>
												</span>
											</div>
										</div>
	                                <xsl:if test="$carga/valor_total_bigdecimal!=''">
	                                    <div class="form-group form-group-sm">							
											<label class="control-label col-sm-4">Valor total</label>
											<div class="col-sm-8">
												<span class="">
													<xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
												</span>
											</div>
										</div>
	                                </xsl:if>
	                                	<div class="form-group form-group-sm" id="bloque_mensaje_ejecucion" style="display:none">
			                                <div class="alert alert-info">
			                                	<div id="mensaje_ejecucion" class="text-justify"/>
			                                </div>
	                                	</div>
	                            </div>
	                        </div>
	                        
	                        
	                        
	                        <div id="div_campos_adicionales" class="row-box form-horizontal">
	                        	<bloque-contenido>
	                        		<titulo>Campos adicionales</titulo>
	                        		<contenido>
	                        			 <div id="contenido_campos_adicionales">
	                        			 </div>
	                        		</contenido>
	                        	</bloque-contenido>
		                        
	                        </div>
	                        
            	      		<div id="div_responsable" class="row-box form-horizontal">
	                        	<bloque-contenido>
	                        		<titulo>Asignación de Responsable</titulo>
	                        		<contenido>
	                        			 <registro>
											<item>Responsable</item>
											<valor>
												<cajatextoselector id="select_responsable">
													<opcion>-- Seleccione -- </opcion>
												</cajatextoselector>
											</valor>
										</registro>
	                        		</contenido>
	                        	</bloque-contenido>
		                        
	                        </div>
	                        
	                        <div class="row-btn">
		                        <boton validacion="validarCamposAdicionales()" accion="guardarCamposAdicionales()" estilo="guardar" formulario="frm_accion" id="btn_aprobarcarga">
									Ejecutar acción
								</boton>
		                        <boton accion="cerrarVentanaEjecutarAccion()" estilo="cancelar" id="btn_cancelar">
									Cerrar Ventana
								</boton>
		                    </div>
                	</div>
                        
	                    
	                    <div id="PLANTILLA_CAMPO_TEXTO" style="display:none">
							<div id="campo_info_adicional_[ 2 ]">
								<registro>
									<item>[ 1 ]</item>
									<valor>
										<cajatexto id="info_adicional_campo_[ 2 ]"/>
									</valor>
								</registro>
							</div>
						</div>
						
						<div id="PLANTILLA_CAMPO_CHECK" style="display:none">
							<div id="campo_info_adicional_[ 2 ]">
								<registro>
									<item>[ 1 ]</item>
									<valor>
										<cajachequeo id="info_adicional_campo_[ 2 ]"/>
									</valor>
								</registro>
							</div>
						</div>
						
						<div id="PLANTILLA_CAMPO_FECHA" style="display:none">
							<div id="campo_info_adicional_[ 2 ]">
								<registro>
									<item>[ 1 ]</item>
									<valor>
										<cajafecha id="info_adicional_campo_[ 2 ]"/>
									</valor>
								</registro>
							</div>
						</div>
						
						<div id="PLANTILLA_CAMPO_ENTERO" style="display:none">
							<div id="campo_info_adicional_[ 2 ]">
								<registro>
									<item>[ 1 ]</item>
									<valor>
										<cajatexto id="info_adicional_campo_[ 2 ]"/>
									</valor>
								</registro>
							</div>
						</div>
						
						<div id="PLANTILLA_CAMPO_TEXTO_LARGO" style="display:none">
							<div id="campo_info_adicional_[ 2 ]">
								<registro>
									<item>[ 1 ]</item>
									<valor>
										<areatexto id="info_adicional_campo_[ 2 ]"/>
									</valor>
								</registro>
							</div>
						</div>
						
						<div id="PLANTILLA_CAMPO_LISTA_VALORES" style="display:none">
							<div id="campo_info_adicional_[ 2 ]">
								<registro>
									<item>[ 1 ]</item>
									<valor>
										<cajatextoselector id="info_adicional_campo_[ 2 ]">
											<opcion value="">-- Seleccione -- </opcion>
										</cajatextoselector>
									</valor>
								</registro>
							</div>
						</div>
                </contenido>
            </ventana>
            <ventana icono="confirmacion" id="vn_aprobarcarga">
                <titulo>Confirmación de Aprobación</titulo>
                <contenido>
                    <div class="row-box form-horizontal">
						
							<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i> ¿Está Seguro que desea <b> "APROBAR" </b>la transacción? </div>
							
                            <div class="col-sm-10 col-sm-offset-1">
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4"> Cliente</label>
										<div class="col-sm-8">
											<span class="">
												<xsl:value-of select="$carga/nombre_usuario"/> <xsl:value-of select="$carga/apellido_usuario"/>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Transacción</label>
										<div class="col-sm-8">
											<span class="">
												<xsl:value-of select="$carga/id_carga"/>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Fecha de liberación</label>
										<div class="col-sm-8">
											<span class="">
												<xsl:value-of select="$carga/fecha_subida"/>
											</span>
										</div>
									</div>
                                <xsl:if test="$carga/valor_total_bigdecimal!=''">
                                    <div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Valor total</label>
										<div class="col-sm-8">
											<span class="">
												<xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos')"/>											
											</span>
										</div>
									</div>
                                </xsl:if>
                            </div>
                    	</div>
                    	<div class="row-btn">
	                        <boton accion="cerrarVentanaAprobarCarga()" estilo="guardar" formulario="frm_aprobar" id="btn_aprobarcarga">
								Aprobar
							</boton>
	                        <boton accion="cerrarVentanaAprobarCarga()" estilo="cancelar" id="btn_cancelar">
								Cerrar Ventana
							</boton>						
                    </div>
                </contenido>
            </ventana>
            
            <ventana icono="confirmacion" id="vn_detalleLog">
                <titulo>Detalle</titulo>
                <contenido>
                    <bloque id="cc_contenido_ventana_detalle">
						.
					</bloque>
                    <area_botones>
                        <boton accion="cerrarVentanaDetalleLog()" estilo="cancelar" id="btn_cancelar">
							Cerrar Ventana
						</boton>
                    </area_botones>
                </contenido>
            </ventana>
            
            <ventana icono="advertencia" id="vn_rechazarcarga">
                <titulo>Confirmación de Rechazo</titulo>
                <contenido>
                    <formulario destino="administracion_carga/proceso/22.3.do" id="frm_rechazar">
                        <variable id="accionrechazar" valor="S"/>
                        <variable id="id_instancia" valor="{//id_instancia}"/>
                        <variable id="id_formato_salida" valor="{//id_formato_salida}"/>
                        <variable id="motivorechazo" valor=""/>
                    </formulario>
                     <div class="row-box form-horizontal">
						
							<div class="alert alert-info"><i class="fa fa-question-circle" aria-hidden="true"></i> ¿Está Seguro que desea <b> "RECHAZAR" </b>la transacción? </div>
							
                            <div class="col-sm-10 col-sm-offset-1" style="margin-bottom: 20px">
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4"> Cliente</label>
										<div class="col-sm-8">
											<span class="">
												 <xsl:value-of select="$carga/nombre_usuario"/> <xsl:value-of select="$carga/apellido_usuario"/>
											</span>
										</div>
									</div>
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Transacción</label>
										<div class="col-sm-8">
											<span class="">
												 <xsl:value-of select="$carga/id_carga"/>
											</span>
										</div>
									</div>
									
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Fecha de liberación</label>
										<div class="col-sm-8">
											<span class="">
												 <xsl:value-of select="$carga/fecha_subida"/>
											</span>
										</div>
									</div>
									
									<xsl:if test="$carga/valor_total_bigdecimal!=''">
	                                    <div class="form-group form-group-sm">							
											<label class="control-label col-sm-4">Valor total</label>
											<div class="col-sm-8">
												<span class="">
													 <xsl:value-of select="format-number($carga/valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
												</span>
											</div>
										</div>
	                                </xsl:if>
									
									<div class="form-group form-group-sm">							
										<label class="control-label col-sm-4">Ingrese el Motivo del rechazo</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="tx_motivorechazo" maxlength="80"/>
										</div>
									</div>
									
                                
                            </div>
                        </div>
                        <div class="row-btn">
	                        <boton accion="aceptarRechazarCarga()" estilo="guardar" id="btn_aprobarcarga">
								Rechazar
							</boton>
	                        <boton accion="cerrarVentanaRechazarCarga()" estilo="cancelar" id="btn_cancelar">
								Cerrar Ventana
							</boton>
                    	</div>
                </contenido>
            </ventana>
        </pagina>
    </xsl:template>
</xsl:stylesheet>
