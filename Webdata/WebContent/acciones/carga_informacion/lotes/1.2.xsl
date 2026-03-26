<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/firma/verificadorFirmaDocumento.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Carga Masiva">
			<javascript>webdata/1.2.js</javascript>
			<xsl:if test="//OSM-INIT-SESSION/Info/Usuario/uso_firma='S'">
				<javascript>firma/1.2_firma.js</javascript>
			</xsl:if>
			<stylesheet>1.2.css</stylesheet>
			
			
			<principal>
			
				<titulo><texto key="CARGA"/>&#160;<texto key="MASIVA"/> - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/></titulo>
				<contenido>
					
					<bloque-pestanas>
						
						<pestana titulo="Paso 1: Descarga de archivo">
							
							<!--SELECTOR DE CARGAS RELACIONADAS-->
							<xsl:if test="obtenerFormato/Formato/cargas_relacionadas='S'">
								<bloque >
								<subtitulo ><texto key="SELECCIÓN DE CARGA RELACIONADA" /></subtitulo>
									<registro>
									<item><texto key="CARGA" /></item>
									<valor>
										<cajatextoselector id="Formato.idformato_carga_relacionada" valor="{//obtenerFormato/Formato/idformato_carga_relacionada}" accion="guardarCargaRelaciona(this.value)">
											<opcion valor="" texto="-- Seleccione --"/>
											<xsl:for-each select="//obtenerCargasRelacionadasPorCliente/listado/Carga">
											<opcion valor="{id_carga}" texto="{concat(id_carga, ' - ', nombre, ' - ', fecha_carga) }" />
											</xsl:for-each>
										</cajatextoselector>
										
									</valor>
								</registro>
									
								</bloque>
							</xsl:if>	
							<xsl:if test="//descargaError='S'">
								<div class="alert alert-danger">
									<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> El archivo con el formato específico no pudo ser descargado, por favor intente más tarde.
								</div>
							</xsl:if>
								
							<!--DESCARGA DE ARCHIVOS-->
							<div class="alert alert-info"> <i class="fa fa-info-circle" aria-hidden="true"></i> Descargue el archivo con el formato específico para realizar la carga de información.</div>
							
							<area_botones>
								<boton estilo="excel" id="btn_descargar_excel" accion="descargar_excel();osm_go('/xls.download?id_formato={//ID_FORMATO}&amp;id_persona={//Persona/id_persona}', false)">Descargar</boton>
							</area_botones>
							
						</pestana>
						
						
						
						<pestana titulo="Paso 2: Subir Archivo">
							<variable id="respuesta" valor="{respuesta}"/>
							
							<div class="alert alert-info"> <i class="fa fa-info-circle" aria-hidden="true"></i> Envíe el archivo de sus datos con el formato específico.</div>
							
							
							<formulario id="form_sendfile" destino="file.upload" dato="multipart/form-data">
								<variable id="id_formato" valor="{//ID_FORMATO}"/>
								<xsl:if test="not(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') and not(//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
									<registro>
										<item>Seleccionar Archivo</item>
										<valor>
											<cajaarchivo id="filename"/>
										</valor>
									</registro>
								</xsl:if>
								
							</formulario>
							
							<xsl:if test="not(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') and not(//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
								<area_botones>
									<boton estilo="excel" formulario="form_sendfile" validacion="validar_archivo()">Subir Archivo</boton>
								</area_botones>
							</xsl:if>
										
							
							<xsl:if test="(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') or (//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
								
								<xsl:call-template name="verificadorFirmaDocumento">
								<xsl:with-param name="formularioOrigen">form_sendfile</xsl:with-param>
								<xsl:with-param name="nombreFaseProceso">PREPARACION MASIVA</xsl:with-param>
								</xsl:call-template>
								
							</xsl:if>
							
						</pestana>
						
						
						<pestana titulo="Paso 3: Realizar Envío">
							<xsl:if test="respuesta='1'">
								<xsl:attribute name="visible">true</xsl:attribute>
							</xsl:if>
							
							<div class="alert alert-info"> <i class="fa fa-info-circle" aria-hidden="true"></i> A continuación se encuentra el listado de los archivos subidos.</div>
							<div ng-app="app" ng-controller="appController">
							<xsl:choose>
								<xsl:when test="count(//obtenerListaCarga/listado/Carga)>0">
									
									<formulario id="frm_enviar" destino="carga_informacion/lotes/1.2.1.do">
										<variable id="id_carga" valor=""/>
									</formulario>
									
									<formulario id="frm_eliminar" destino="carga_informacion/lotes/1.2.3.do">
										<input type="hidden" name="id_carga" id="id_carga_eliminar"/>
									</formulario>
									
									<tabla icono="excel">
										<encabezado>
											<titulo>Identificador <texto key="CARGA" /></titulo>
											<titulo>Nombre de Archivo</titulo>
											<titulo>Fecha de <texto key="CARGA" /></titulo>
											<titulo>Valor Total</titulo>
											<titulo>Numero de Registros</titulo>
											<titulo>Duplicados</titulo>
											<titulo></titulo>
										</encabezado>
										<xsl:for-each select="//obtenerListaCarga/listado/Carga[estado='B']">
											<fila>
												<valor><xsl:value-of select="id_carga"/></valor>
												<valor><xsl:value-of select="nombre"/></valor>
												<valor><xsl:value-of select="fecha_subida"/></valor>
												<valor><xsl:value-of select="format-number(valor_total_bigdecimal, '###,###.00')"/></valor>
												<valor><xsl:value-of select="numero_registros_bigdecimal"/></valor>
												<valor><xsl:if test="duplicados>0">
															<area_botones>
																<button id="btn_enviar" class="btn btn-xs btn-primary" data-toggle="modal" data-target="#modal_solicitud" ng-click="evaluarMostrar('{id_carga}', '{valor_total}', '{numero_registros}')">
																	<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>Duplicados 
																</button>
															</area_botones>
														</xsl:if>
												</valor>
												<valor>
													
													<area_botones>
														<boton estilo="danger eliminar btn-xs" accion="eliminar_carga('{id_carga}')">Eliminar</boton>
														<boton estilo="avanzar" accion="enviar_carga('{id_carga}', '{nombre}.{extension}')">Enviar</boton>
													</area_botones>
												</valor>
											</fila>
										</xsl:for-each>
										<xsl:for-each select="//obtenerListaCarga/listado/Carga[estado!='B']">
											<fila>
												<valor><xsl:value-of select="id_carga"/></valor>
												<valor><xsl:value-of select="nombre"/></valor>
												<valor><xsl:value-of select="fecha_subida"/></valor>
												<valor>
													<parrafo estilo="resaltado">
														<xsl:choose>
															<xsl:when test="estado='B'">Sin enviar</xsl:when>
															<xsl:when test="estado='E'">Eliminado</xsl:when>
															<xsl:when test="estado='V'">En Revisión</xsl:when>
															<xsl:when test="estado='L'">En Liberación</xsl:when>
															<xsl:when test="estado='S'">En Proceso</xsl:when>
															<xsl:when test="estado='C'">Aprobado</xsl:when>
															<xsl:when test="estado='R'">Rechazado</xsl:when>
															<xsl:otherwise>Indefinido</xsl:otherwise>
														</xsl:choose>
													</parrafo>
												</valor>
											</fila>
										</xsl:for-each>
									</tabla>
								</xsl:when>
								<xsl:otherwise>
									<div class="alert alert-danger">
									<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> No se han subido archivos de carga para este servicio.</div>
								</xsl:otherwise>
							</xsl:choose>
							
								<div class="modal fade" id="modal_solicitud" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title" id="myModalLabel">Solicitud <span></span> </h4>
											</div>
											<div class="modal-body">
												<div class="tabla_borde table-responsive">							
												<table class="table table-hover table-striped">
											  	<thead>
												  	<tr>
												  		<th>Identificador <texto key="CARGA" /></th>
												  		<th>Nombre</th>
												  		<th>Fecha de Subida</th>
												  		<th>Estado</th>
												  		<th>Valor</th>
												  		<th>Numero de Registros</th>
												  	</tr>
											  	</thead>
											  	<tbody>
											  	<tr align="center" ng-repeat="l in listaDuplicados">
												  		<td nowrap="nowrap"> {{ l.id_carga }} </td>
												  		<td nowrap="nowrap"> {{ l.nombre }} </td>
												  		<td nowrap="nowrap"> {{ l.fecha_subida_string }} </td>
										  		        <td nowrap="nowrap" ng-if='l.estado === "V"'>En Revisión</td>
										  		        <td nowrap="nowrap" ng-if='l.estado === "L"'>En Liberación</td> 
										  		        <td nowrap="nowrap" ng-if='l.estado === "S"'>En Proceso</td> 
										  		        <td nowrap="nowrap" ng-if='l.estado === "C"'>Aprobado</td>  
												  		<td nowrap="nowrap"> {{ l.valor_total_bigdecimal | currency : ""}} </td>
												  		<td nowrap="nowrap"> {{ l.numero_registros_bigdecimal }} </td>
												  	</tr>
											  	</tbody>
											</table>
											</div>
											</div>
											<div class="modal-footer">									
												<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&#160; Cerrar</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						
							
						</pestana>
						
						
					</bloque-pestanas>
					
					<area_botones>
						<boton estilo="primary volver" destino="inicio/0.do">Cancelar</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
			
			<ventana id="vn_confirmacion" icono="confirmacion">
				<titulo>Confirmación Envío de Archivo</titulo>
				<contenido>
					
					<bloque estilo="grupo">
						<parrafo estilo="resaltado">
							El archivo ha sido subido al servidor exitosamente.
						</parrafo>
					</bloque>
		
					<area_botones>
						<boton estilo="danger" id="btn_cancelar" accion="cerrarVentana('vn_confirmacion')">Cerrar Ventana</boton>
					</area_botones>
				</contenido>
			</ventana>
			
			<ventana id="vn_error" icono="error">
				<titulo>Confirmación Envío de Archivo</titulo>
				<contenido>
					
					<bloque estilo="grupo">
						<parrafo id="mensaje">
							<div class="errores">
								<xsl:for-each select="//ERRORES_VALIDACION/mapaErrores/*">
									
									<div estilo="grupo">
										<titulo> Hoja <xsl:value-of select="@name"></xsl:value-of></titulo>
										<contenido>
										
											<xsl:for-each select="String">
												<div class="errorDato">
													<xsl:value-of select="."></xsl:value-of>
												</div>
											
											</xsl:for-each>
										</contenido>
									</div>
									
									<br/>
									
								</xsl:for-each>
							</div>
						</parrafo>
					</bloque>
		
					<area_botones>
						<boton estilo="danger" id="btn_cancelar" accion="cerrarVentana('vn_error')">Cerrar Ventana</boton>
					</area_botones>
				</contenido>
			</ventana>
			
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
