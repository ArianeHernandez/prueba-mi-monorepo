<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		<xsl:include href="context://stylesheets/templates/horario.xsl"/> 
	
	<xsl:template name="VENTANA_CREA_PROCESO_ADMIN">
		
		<javascript>templates/VentanaProcesoAdmin.js</javascript>
		<ventana id="ventana_proceso_admin">
			
			<titulo>
				Creacion de Proceso
			</titulo>
			
			<contenido>
					<parrafo>Por favor, ingrese los siguientes datos:</parrafo>
					
					<registro>
						<item>Nombre:</item>
						<valor>
							<escapar>
								<div id="divcaja_nombre_proceso_admin">
									<input id="cajatex_nombre_proceso_admin" class="form-control" type="text" autocomplete="off"/>
								</div>
							</escapar>
						</valor>
					</registro>
					
					<registro>
						<item>Negocio:</item>
						<valor>
							<escapar>
								<div id="divsel_negocio_proceso_admin">
									&#160;
								</div>
							</escapar>
						</valor>
					</registro>
					
					<registro>
						<item><texto key="FORMATO DE ENTRADA" /></item>
						<valor>
							<escapar>
								<div id="divsel_formato_proceso_admin">
									&#160;
								</div>
							</escapar>
						</valor>
					</registro>
					
				<area_botones>
					<boton estilo="primary" id="btn_guardar_proceso_admin" accion="guardarProcesoAdmin()" >Guardar</boton>
					<boton estilo="danger" id="btn_cancelar_proceso_admin" accion="cancelarProcesoAdmin()">Cancelar</boton>
				</area_botones>
			</contenido>
		</ventana>

	</xsl:template>
	
	<xsl:template name="VENTANA_ACTUALIZA_PROCESO_ADMIN">
		
		<javascript>templates/VentanaProcesoAdmin.js</javascript>

		<ventana id="ventana_actualiza_proceso_admin">
			
			<titulo>
				Actualizacion de Proceso
			</titulo>
			
			<contenido>
					<parrafo id="parr_mensaje_actualiza">&#160;</parrafo>
					
				<area_botones>
					<boton estilo="primary aceptar" id="btn_cerrar_act_proceadmin" accion="cerrarVentanaActualiza()">Cerrar</boton>
				</area_botones>
			</contenido>
		</ventana>

	</xsl:template>
	
	<xsl:template name="PLANTILLA_INSTANCIA_GRAFICA">
		<div id="PLANTILLA_INSTANCIA_GRAFICA" style="display:none">
			<!-- INSTANCIA-->
 			<div class="instancia" id="div_instancia_[ 1 ]">
 			
 				<div class="instancia_inicial instancia_inicial_[ 3 ]" id="instancia_inicial_[ 1 ]"></div>
 				<div class="instancia_final instancia_final_[ 4 ][ 5 ][ 13 ][ 14 ]" id="instancia_final_[ 1 ]"></div>
 			
 				<div class="header_instancia" id="header_instancia_[ 1 ]">
 					
     				<div>
 						[ 2 ]
 					</div>
 				</div>
 				
 				
 						
 				<a href="#" id="body_instancia_[ 1 ]" class="body_instancia" onclick="">
 					
 					<!--TIEMPO-->
 					<div id="tiempo_instancia_[ 1 ]" class="tiempo_instancia">
 						[ 6 ] min
					</div>
 					
 					<!--APROBAR-->
 					
					<div class="aprobar_instancia aprobar_instancia_[ 4 ]" id="aprobar_instancia_[ 1 ]"></div>

					<!--RECHAZAR-->
 					
					<div class="rechazar_instancia rechazar_instancia_[ 5 ]" id="rechazar_instancia_[ 1 ]"></div>
 					
 					<!--ES INSTANCIA INICIAL-->
 				</a>
 			</div>
		</div>
	
	</xsl:template>
	
	<xsl:template name="PLANTILLA_INSTANCIA_GRAFICA_ACCESO">
		<div id="PLANTILLA_INSTANCIA_GRAFICA" style="display:none">
			<!-- INSTANCIA-->
 			<div class="instancia" id="div_instancia_[ 1 ]">
 				<div class="header_instancia" id="header_instancia_[ 1 ]">
 					[ 2 ]
 				</div>		
 				<a href="#" id="body_instancia_[ 1 ]" class="body_instancia" onclick="">
 					
 					<!--TIPO DE INSTANCIA-->
 					<xsl:choose>
 						<xsl:when test="1=1">
 							<div class="icono_instancia tipo_carga_interactivo"/>
							<div class="id_instancia">[ 3 ]</div>
 						</xsl:when>
 						
 					</xsl:choose>

 					<div class="tabla_instancia">
	 					<table style="border-collapse:collapse;">
	 						<tbody>
	 							<tr class="tabla_instancia_fila">
	 								<td>Control</td>
		 							<td id="ins_ge_co_[ 1 ]"/>
		 							<td id="ins_O_co_[ 1 ]" style="display:none">O</td>
		 							<td id="ins_F_co_[ 1 ]" style="display:none">F</td>
		 							<td id="ins_D_co_[ 1 ]" style="display:none">D</td>
		 							<td id="ins_I_co_[ 1 ]" style="display:none">I</td>
	 							</tr>
		 						<tr class="tabla_instancia_fila">
		 							<td>Aprobar</td>
		 							<td id="ins_ge_ap_[ 1 ]" style="display:none">G</td>
		 							<td id="ins_O_ap_[ 1 ]" style="display:none">O</td>
		 							<td id="ins_F_ap_[ 1 ]" style="display:none">F</td>
		 							<td id="ins_D_ap_[ 1 ]" style="display:none">D</td>
		 							<td id="ins_I_ap_[ 1 ]" style="display:none">I</td>
		 						</tr>
		 						<tr class="tabla_instancia_fila">
		 							<td>Rechazar</td>
		 							<td id="ins_ge_re_[ 1 ]" style="display:none">G</td>
		 							<td id="ins_O_re_[ 1 ]" style="display:none">O</td>
		 							<td id="ins_F_re_[ 1 ]" style="display:none">F</td>
		 							<td id="ins_D_re_[ 1 ]" style="display:none">D</td>
		 							<td id="ins_I_re_[ 1 ]" style="display:none">I</td>
		 						</tr>
	 						</tbody>
	 					</table>
 					</div>
 					
 					<!--ES INSTANCIA INICIAL-->
 					<div class="instancia_inicial" id="instancia_inicial_[ 1 ]">
 						Inicial: [ 3 ]
 					</div>
 				</a>
 			</div>
		</div>
	
	</xsl:template>
	
	<xsl:template name="PLANTILLAS_INSTANCIA">
		<xsl:param name="usa_formato_salida" />
		<xsl:param name="usa_control_acceso" />
	
		<div id="PLANTILLA_LISTA_INST_PROCEADMIN" style="display:none">

			<div id="mensaje_sin_instancias" class="alert alert-info" style="width:200px;display:none">
				<i class="fa fa-info-circle" aria-hidden="true"></i>
				No existen instancias.
			</div>

			<div id="mensaje_seleccionar_instancia" class="alert alert-info" style="width:200px;display:none">
				<i class="fa fa-info-circle" aria-hidden="true"></i>
				Seleccione una de las instancias para poder editar su contenido
			</div>
			
			<div id="contenido_lista_inst_proceadmin"/>
			
		</div>
						
		<div id="PLANTILLA_ITEM_INST_PROCEADMIN" style="display:none">
			
			<!-- 1 id instancia -->
			<div id="blq_instancia_[ 1 ]">
						
				<!-- INFORMACION GENERAL INSTANCIA -->
				<div id="blq_info_instancia_[ 1 ]" class="grupo" >
					<div id="titulo_info_instancia_[ 1 ]" class="titulo_info_instancia">
						[ 2 ]
					</div>
					
					<div id="subtitulo_inst_[ 1 ]" onclick="toggleSeccion('div_info_instancia_[ 1 ]')" class="toggle" >
						Informacion general
					</div>
					<div id="div_info_instancia_[ 1 ]" class="area_contenido_inst">
						<div>
							Nombre:
							<input id="nombre_inst_[ 1 ]" class="form-control" value="[ 2 ]" autocomplete="off"
								onchange="actualizarObjetoInstancia([ 1 ]);"/>
							
						</div>
						
						<div>
							Inicial:
							<cajachequeo id="inicial_inst_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);">
								<xsl:attribute name="validacion">
									<xsl:if test="$usa_control_acceso = 'true'">validarInicialAcceso([ 1 ])</xsl:if>
								</xsl:attribute>
							</cajachequeo>
							
						</div>
						
						<div>
							Aprobar:
							<cajachequeo id="perm_aprobar_inst_[ 1 ]" accion="aprobarRechazarToggle([ 1 ]);actualizarObjetoInstancia([ 1 ]);"/>
							
						</div>
						
						<div id="contenedor_aprobar_aut_[ 1 ]" style="display:none;">
							Aprobar Automáticamente:
							<cajachequeo id="perm_aprobar_auto_inst_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);"/>
						</div>
						
						<div>
							Rechazar:
							<cajachequeo id="perm_rechazar_inst_[ 1 ]" accion="aprobarRechazarToggle([ 1 ]);actualizarObjetoInstancia([ 1 ]);"/>
						</div>
						
						<div id="contenedor_rechazar_aut_[ 1 ]" style="display:none;">
							Rechazar Automáticamente:
							<cajachequeo id="perm_rechazar_auto_inst_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);"/>
						</div>
						
						<div>
							Adicionar Documentos:
							<cajachequeo id="perm_adicionar_docs_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);">
							</cajachequeo>
							
						</div>
						<div>
							Gestionar Documentos:
							<cajachequeo id="perm_gestionar_docs_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);">
							</cajachequeo>
							
						</div>
						<div>
							Ocultar Cargas sin Filtro:
							<cajachequeo id="perm_ocultar_cargas_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);">
							</cajachequeo>
							
						</div>
						<div>
							Tiempo:
							<input id="tiempo_inst_[ 1 ]" class="form-control" value="[ 6 ]" autocomplete="off"
								onchange="actualizarObjetoInstancia([ 1 ]);"/>
						</div>
						
						<xsl:if test="$usa_formato_salida != 'false'">
							<div>
								Formato de Salida:
								<cajatextoselector id="formato_salida_inst_[ 1 ]" accion="actualizarObjetoInstancia([ 1 ]);">
										&#160;
								</cajatextoselector>
							</div>
						</xsl:if>
						
						<div>
							Horario de Atencion:
	
							<div id="area_horario_instancia_[ 1 ]"></div>
						</div>
						
					</div>
				</div>
					
				<xsl:if test="$usa_control_acceso = 'true'">
					<!-- CONTROL ACCESO INSTANCIA -->
					<div id="blq_acceso_instancia_[ 1 ]" class="grupo">
						<div id="subtitulo_acceso_inst_[ 1 ]" onclick="toggleAcceso('[ 1 ]')" class="toggle">
							Control de Acceso y Aprobación
						</div>
							
						<div id="acceso_instancia_[ 1 ]" style="display:none" class="area_contenido_inst">
							<br/>
							<div id="contenido_acceso_inst_[ 1 ]">
								No existen elementos asociados.
							</div>
							
							<area_botones>
							
								<select id="select_acceso_inst_[ 1 ]" autocomplete="off" class="cajatextoselector w140">
									 	&#160;
								</select>
																			
								<boton estilo="primary" accion="asignarAcceso([ 1 ]);">Establecer Control</boton>
								
							</area_botones>
						
						</div>
						
					</div>
				</xsl:if>
										
				<!-- ACCIONES POR INSTANCIA -->
				<div id="blq_acciones_instancia_[ 1 ]" class="grupo" >
					<div id="subtitulo_acciones_inst_[ 1 ]" onclick="toggleSeccion('acciones_instancia_[ 1 ]')" class="toggle">
						Acciones
					</div>
					
					<div id="acciones_instancia_[ 1 ]" style="display:none" class="area_contenido_inst">
						<div id="menu_acciones_por_instancia_[ 1 ]"></div>
						
						<div style="display:none">
							<div id="contenido_acciones_inst_[ 1 ]" >
								No existen acciones.
							</div>
							
						</div>
						
						<area_botones>
							<boton estilo="primary" accion="crearAccion([ 1 ]);">
								Crear Accion
							</boton>
						</area_botones>
					</div>
						
				</div>
					
				<!-- ACCIONES REDIRECCION POR INSTANCIA -->
				<div id="blq_acciones_redireccion_instancia_[ 1 ]" class="grupo" >
					<div id="subtitulo_acciones_redireccion_inst_[ 1 ]" onclick="toggleSeccion('acciones_redireccion_instancia_[ 1 ]')" class="toggle">
						Acciones Redireccion
					</div>
					
					<div id="acciones_redireccion_instancia_[ 1 ]" style="display:none" class="area_contenido_inst">
						<div id="menu_acciones_redireccion_por_instancia_[ 1 ]"></div>
						<area_botones>
							<boton estilo="primary" accion="crearAccionRedireccion([ 1 ]);">
								Crear Accion Redireccion
							</boton>
						</area_botones>
					</div>
						
				</div>
														
				<!-- ROLES INSTANCIA -->
				<div id="blq_roles_instancia_[ 1 ]" class="grupo">
					<div id="subtitulo_roles_inst_[ 1 ]" onclick="toggleSeccion('roles_instancia_[ 1 ]')" class="toggle">
						Roles
					</div>
						
					<div id="roles_instancia_[ 1 ]" style="display:none" class="area_contenido_inst">
						<br/>
						<div id="contenido_roles_inst_[ 1 ]">
							<div id="mensaje_sin_roles" class="alert alert-info" style="width: 200px">
								No existen roles asociados
							</div>
						</div>
						
						<area_botones>
						
							<select id="select_rol_inst_[ 1 ]" autocomplete="off" class="cajatextoselector w180">
								 	&#160;
							</select>
																		
							<boton estilo="primary" accion="asignarRol([ 1 ]);">
								Asignar Rol
							</boton>
							
						</area_botones>
					
					</div>
					
				</div>
					
			</div>
			
		</div>
						
	</xsl:template>
	
	
	<xsl:template name="PLANTILLAS_ACCION">
		<xsl:param name="usa_control_acceso" />
		
		<div id="PLANTILLA_ITEM_MENU_ACCIONES_INSTANCIA" style="display:none">
			<div id="item_menu_accion_[ 1 ]" class="item_menu_accion">
			
				<div id="titulo_item_menu_acciones_[ 1 ]" onclick="mostrarVentanaEdicionAccion([ 1 ], [ 3 ])" class="titulo_item_menu_accion">
					[ 2 ]
				</div>
				<div class="div_eliminar_accion"  onclick="eliminarAccion([ 1 ], [ 3 ]);">
					x
				</div>
			</div>
		</div>
	
		<div id="PLANTILLA_ITEM_MENU_ACCIONES_REDIRECCION_INSTANCIA" style="display:none">
			<div id="item_menu_accion_redireccion_[ 1 ]" class="item_menu_accion">
			
				<div id="titulo_item_menu_acciones_redireccion_[ 1 ]" onclick="mostrarVentanaEdicionAccionRedireccion([ 1 ])" class="titulo_item_menu_accion">
					[ 2 ]
				</div>
				<div class="div_eliminar_accion"  onclick="eliminarAccionRedireccion([ 1 ], [ 3 ]);">
					x
				</div>
			</div>
		</div>
	
		<div id="PLANTILLA_ITEM_ACCION_INST" style="display:none">
			<br/>
			<div id="item_accion_[ 1 ]">
			
				<bloque id="blq_item_accion_[ 1 ]" estilo="grupo">
					<div id="div_accion_[ 1 ]">
						<bloque estilo="grupo">
							<subtitulo id="" texto="Información Basica"/>
							
							<registro>
								<item>Nombre</item>
								<valor>
									<input id="nombre_accion_[ 1 ]" class="form-control" value="[ 2 ]"  style="width: 200px;" autocomplete="off" onchange="actualizarAccion([ 1 ], [ 3 ], true);"/>
								</valor>
							</registro>
							
							<registro>
								<item>Oculto</item>
								<valor>
									<cajachequeo id="oculto_accion_[ 1 ]" accion="actualizarAccion([ 1 ], [ 3 ], true);"/>
								</valor>
							</registro>
							<registro>
								<item>Accion automática</item>
								<valor>
									<select id="select_aa_lista_dinamica_[ 1 ]" autocomplete="off" class="form-control"  style="width: 200px;" onchange="actualizarAccion([ 1 ], [ 3 ], false);" >
											&#160;
									</select>
								</valor>				

							</registro>
								
						</bloque>
						
						<xsl:if test="$usa_control_acceso = 'true'">
							<bloque estilo="grupo">
								<subtitulo id="" texto="Elementos asociados a la acción"/>
								
								<div id="contenido_accion_acceso_[ 1 ]">
									No existen elementos asignados a la acción.
								</div>
								
								<area_botones>
							
									<select id="select_accion_acceso_[ 1 ]" autocomplete="off" class="cajatextoselector w140">
										&#160;
									</select>
																									
									<boton estilo="primary" accion="asignarAccionAcceso([ 1 ]);">
										Asociar Elemento
									</boton>
														
								</area_botones>
								
							</bloque>
						</xsl:if>
						
						<bloque estilo="grupo">
							<subtitulo id="subtitulo_instancias_dest_[ 1 ]" texto="Instancias de Destino"/>
							
							<div id="contenido_instancias_dest_[ 1 ]">
								<div id="mensaje_accion_sin_instancias_destino" class="alert alert-info">No existen instancias asociadas</div>
							</div>
							
							<area_botones>
							
								<select id="select_inst_dest_[ 1 ]" autocomplete="off" class="cajatextoselector w140">
									&#160;
								</select>
																								
								<boton estilo="primary" accion="asignarInstanciaDestino([ 1 ], [ 3 ]);">
									Asignar Instancia
								</boton>
													
							</area_botones>
						</bloque>
						
						<bloque estilo="grupo">
							<subtitulo id="subtitulo_notificaciones_accion_[ 1 ]" texto="Notificaciones"/>
							<div id="contenido_notificaciones_accion_[ 1 ]">
								<div id="mensaje_accion_sin_notificaciones" class="alert alert-info">
									No existen notificaciones asociadas
								</div>
							</div>
							
							<area_botones>
							
								<boton estilo="primary" accion="crearNotificacionAccion([ 1 ]);">
									Crear Notificación
								</boton>
													
							</area_botones>
							
						</bloque>
						
						<!-- jmhurtado -->
						<bloque estilo="grupo">
							<subtitulo id="subtitulo_instancias_dest_[ 1 ]" texto="Operaciones Internas"/>
							
							<div id="contenido_operacion_interna_[ 1 ]">
								<div id="mensaje_accion_sin_opinternas" class="alert alert-info">
									No existen operaciones internas asociadas
								</div>
							</div>
							
							<area_botones>
							
								<select id="select_operacion_interna_[ 1 ]" autocomplete="off" class="cajatextoselector w140">
									&#160;
								</select>
																								
								<boton estilo="primary" accion="asignarOperacionInterna([ 1 ]);">
									Asignar Operacion
								</boton>
													
							</area_botones>
						</bloque>
						<!--  -->
						
						<bloque estilo="grupo">
							<subtitulo id="subtitulo_info_adicional_[ 1 ]" texto="Información Adicional"/>
							
							<div id="contenido_info_adicional_[ 1 ]">
								<div id="mensaje_accion_sin_info_adicional" class="alert alert-info">No existen campos asociados</div>
							</div>
							
							<area_botones>
							
								<select id="select_info_adicional_[ 1 ]" autocomplete="off" class="cajatextoselector w140">
									&#160;
								</select>
																								
								<boton estilo="primary" accion="asignarCamposInfoAdicional([ 1 ]);">
									Seleccionar Campo
								</boton>
													
							</area_botones>
						</bloque>
						
						<bloque estilo="grupo">
							<subtitulo id="subtitulo_responsable_[ 1 ]" texto="Asignación de Responsable"/>
							
							<div id="contenido_responsable_[ 1 ]">
								<div id="mensaje_accion_sin_responsable" class="alert alert-info">
									No existen responsables asociados
								</div>
							</div>
							
							<area_botones>
							
								<select id="select_responsable_[ 1 ]" autocomplete="off" class="cajatextoselector w140">
									&#160;
								</select>
																								
								<boton estilo="primary" accion="asignarRolResponsable([ 1 ]);">
									Seleccionar Rol
								</boton>
													
							</area_botones>
						</bloque>
						
						<bloque estilo="grupo">
							<subtitulo id="subtitulo_mensaje_[ 1 ]" texto="Mensaje de Ejecución"/>
						
							<textarea id="textarea_mensaje_ejecucion" style="height: 200px" class="form-control" 
								type="text" autocomplete="off" onchange="actualizarAccion([ 1 ], [ 3 ], false);" value="[ 4 ]">[ 4 ]</textarea>
						
						</bloque>
				
					</div>
				</bloque>
			
			</div>
			
		</div>
		
		<!--  -->
		
		<div id="PLANTILLA_ITEM_ACCION_REDIRECCION_INST" style="display:none">
			<br/>
			<div id="item_accion_redireccion_[ 1 ]">
			
				<bloque id="blq_item_accion_redireccion_[ 1 ]" estilo="grupo">
					<div id="div_accion_redireccion[ 1 ]">
						<bloque estilo="grupo">
							<subtitulo id="" texto="Información"/>
							
							<registro>
								<item>Nombre</item>
								<valor>
									<input id="nombre_accion_redireccion_[ 1 ]" class="form-control" value="[ 2 ]"  style="width: 200px;" autocomplete="off" onchange="actualizarAccionRedireccion([ 1 ], [ 3 ]);"/>
								</valor>
							</registro>
							
							<registro>
								<item>Endpoint</item>
								<valor>
									<input id="endpoint_accion_redireccion_[ 1 ]" class="form-control" value="[ 4 ]"  style="width: 200px;" autocomplete="off" onchange="actualizarAccionRedireccion([ 1 ], [ 3 ]);"/>
								</valor>
							</registro>
							
						</bloque>
					</div>
				</bloque>
			</div>
		</div>
		<!--  -->
		
		<div id="PLANTILLA_ITEM_INST_DEST" style="display:none">
			
			<!-- 1:accion 2:id_instancia_destino -->
			
			<div id="item_accion_[ 1 ]_inst_dest_[ 2 ]">
				<registro>
					<item>Nombre</item>
					<valor>
						<div>[ 3 ]</div>
					</valor>
				</registro>
				
				<registro>
					<item></item>
					<valor>
						<a class="boton btn-danger" onclick="eliminarInstanciaDestino([ 1 ], [ 2 ]);">Eliminar</a>
					</valor>
				</registro>
			
			</div>
			
		</div>
		
		<!--  -->
		
		<!--  -->
		
		<div id="PLANTILLA_ITEM_OPERACION_INTERNA" style="display:none">
			
			<!-- 1:accion 2:id_instancia_destino -->
			
			<div id="item_accion_[ 1 ]_operacion_interna_[ 2 ]">
				<registro>
					<item>Nombre</item>
					<valor>
						<div>[ 3 ]</div>
					</valor>
				</registro>
				
				<registro>
					<item></item>
					<valor>
						<a class="boton btn-danger" onclick="eliminarOperacionInterna([ 1 ], [ 2 ]);">Eliminar</a>
					</valor>
				</registro>
			
			</div>
			
		</div>
		
		<div id="PLANTILLA_ITEM_INFO_ADICIONAL" style="display:none">
			
			<!-- 1:accion 2:id_instancia_destino -->
			
			<div id="item_accion_[ 1 ]_info_adicional_[ 2 ]">
				<registro>
					<item>Nombre</item>
					<valor>
						<div>[ 3 ]</div>
					</valor>
				</registro>
				
				<registro>
					<item></item>
					<valor>
						<a class="boton btn-danger" onclick="eliminarCampoInfoAdicional([ 1 ], [ 2 ]);">Eliminar</a>
					</valor>
				</registro>
			
			</div>
			
		</div>
		
		<div id="PLANTILLA_ITEM_ROL_RESPONSABLE" style="display:none">
			
			<div id="item_accion_[ 1 ]_responsable_[ 2 ]">
				<registro>
					<item>Rol</item>
					<valor>
						<div>[ 3 ]</div>
					</valor>
				</registro>
				
				<registro>
					<item></item>
					<valor>
						<a class="boton btn-danger" onclick="eliminarRol([ 1 ], [ 2 ]);">Eliminar</a>
					</valor>
				</registro>
			
			</div>
			
		</div>
		
		
		<!--  -->
		
		<div id="PLANTILLA_ITEM_ACCION_ACCESO" style="display:none">
			
			<!-- 1:id_accion_acceso : 2 nombreElemento : 3 id_accion -->
			
			<div id="item_accion_acceso_[ 1 ]">
				<registro>
					<item>Elemento</item>
					<valor>
						<div>[ 2 ]</div>
					</valor>
				</registro>
				
				<registro>
					<item></item>
					<valor>
						<a class="boton btn-danger" onclick="eliminarAccionAcceso([ 1 ], [ 3 ]);">Eliminar</a>
					</valor>
				</registro>
			
			</div>
			
		</div>
		
		<!--  -->
		
		<div id="PLANTILLA_ITEM_NOTI_ACC" style="display:none">
			
			<!-- 1:id_notificacion_accion -->
			<!-- 6: id_accion -->
			
			<div id="item_noti_acci_[ 1 ]">
			
				<bloque estilo="grupo">
					
					<registro>
						<item>Tipo</item>
						<valor>
							<select id="select_tipo_noti_acc_[ 1 ]" autocomplete="off" class="cajatextoselector w140"
							onchange="actualizarTipoNotificacionAccion([ 1 ]);">
								<option value="">-- ninguno -- </option>
								<option value="D">Destino</option>
								<option value="C">Correo Especifico</option>
								<option value="A">Administrativo</option>
								<option value="L">Liberador</option>
								<option value="Z">Asesor Comercial</option>
							</select>
						</valor>
					</registro>
					
					<registro>
						<item id="titulo_tipo_noti_acc_[ 1 ]">&#160;</item>
						<valor>
							<div id="valor_tipo_noti_acc_[ 1 ]">&#160;</div>
						</valor>
					</registro>
					
					<registro>
						<item>Mensaje</item>
						<valor>
							<areatexto id="areatexto_mensaje_noti_acc_[ 1 ]" valor="[ 5 ]"
							accion="actualizarNotificacionAccion([ 1 ]);"/>
						</valor>
					</registro>
					
					<registro>
						<item></item>
						<valor>
							<a class="boton btn-danger" onclick="eliminarNotificacionAccion([ 1 ],[ 6 ]);">Eliminar</a>
						</valor>
					</registro>
				
				</bloque>
				
			</div>
			
		</div>
		
	
	</xsl:template>
	
	<xsl:template name="PLANTILLAS_ROL">
	
		<!-- 1:id_rol -->
		<!-- 3: id_instancia -->
	
		<div id="PLANTILLA_ITEM_ROL_INST" style="display:none">
			
			<div id="item_rol_[ 1 ]_inst_[ 3 ]">
			<registro>
				<item>Nombre</item>
				<valor>
					<div>[ 2 ]</div>
				</valor>
			</registro>
			
			<registro>
				<item></item>
				<valor>
					<a class="boton btn-danger" onclick="eliminarRolInstancia([ 1 ], [ 3 ]);">Eliminar</a>
				</valor>
			</registro>
			
			</div>
			
		</div>
	
	</xsl:template>
	
	<xsl:template name="PLANTILLAS_ACCESO">
	
		<!-- 1: id_instancia_acceso -->
		<!-- 2: nombre_elemento -->
		<!-- 3: id_instancia -->
		<!-- 4: aprobar -->
		<!-- 5: rechazar -->
		<!-- 6: tiempo -->
		<!-- 7: elemento -->
	
		<div id="PLANTILLA_ITEM_ACCESO_INST" style="display:none">
			
			<div id="item_acceso_[ 1 ]">

				<variable id="id_instancia_acceso_[ 1 ]" valor="[ 1 ]" />
				
				<registro>
					<item>Elemento</item>
					<valor>
						[ 2 ]
					</valor>
				</registro>
	
				<registro>
					<item>Aprobar</item>
					<valor>
						<cajachequeo id="perm_aprobar_acceso_[ 1 ]" accion="actualizarPermisosAcceso([ 1 ], '[ 7 ]');"/>
					</valor>
				</registro>
				
				<registro>
					<item>Rechazar</item>
					<valor>
						<cajachequeo id="perm_rechazar_acceso_[ 1 ]" accion="actualizarPermisosAcceso([ 1 ], '[ 7 ]');"/>
					</valor>
				</registro>
				
				<registro>
					<item>Tiempo</item>
					<valor>
						<cajatexto id="tiempo_acceso_[ 1 ]" onchange="actualizarTiempoAcceso([ 1 ])" valor="[ 6 ]"/>
					</valor>
				</registro>
								
				<registro>
					<item></item>
					<valor>
						<boton estilo="danger eliminar" accion="eliminarAcceso([ 1 ], [ 3 ]); limpiarPermisosAcceso([ 1 ], '[ 7 ]',[ 3 ] );">Eliminar</boton>
					</valor>
				</registro>
			
			</div>
			
		</div>
	
	</xsl:template>

</xsl:stylesheet>