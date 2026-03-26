<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
			
		
		<pagina titulo="Edición de Reporte">
			<javascript>admin_reportes/admin_rep.2.js</javascript>
			
			<principal>
				<titulo icono="formatos">Edición de Reportes</titulo>
				<contenido>
					<div class=" box-container">
					<formulario id="form_edicion" class="form-horizontal" destino="admin_reportes/admin_rep.3.do">					
						<variable id="nuevo" valor="false" />
						<variable id="Reporte.id" />
						
						<bloque-pestanas>
							
							<pestana titulo="Información Básica">								
								
								<div class=" form-horizontal">
									
									<div class="form-group form-group-sm">
										 <label class="contol-label col-sm-4">Nombre del Reporte</label>
										 <div class="col-sm-8">
											<valor>
												<cajatexto id="Reporte.nombre" />
											</valor>
										</div>
									</div>
								
								
									<div class="form-group form-group-sm">
										 <label class="contol-label col-sm-4">Consulta</label>
										 <div class="col-sm-8">
											<valor>
												<areatexto id="Reporte.consulta" />
											</valor>
										</div>
									</div>
								
								
									<div class="form-group form-group-sm">
										 <label class="contol-label col-sm-4">Conexión</label>
										 <div class="col-sm-8">
											<valor>
												<cajatextoselector id="Reporte.nombre_conexion">
													<xsl:for-each select="//obtenerConexiones/listado/String">
														<opcion valor="{.}" texto="{.}"/>	
													</xsl:for-each>	
												</cajatextoselector>
											</valor>
										</div>
									</div>
									
								</div>
							</pestana>
							
							<pestana titulo="Parámetros">
								
								<div id="contenido_lista_parametro"></div>
					
								<div id="PLANTILLA_PARAMETRO" style="display:none">
									
									<div id="parametro_[ 1 ]">

									<input type="hidden" id="Reporte.parametros:[ 1 ].id_reporte" value=""	/>
																									
									<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
									
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Título</label>
											 <div class="col-sm-8">
												<valor>
													<cajatexto id="Reporte.parametros:[ 1 ].titulo" valor="[ 6 ]" />
												</valor>
											</div>
										</div>
								

										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Nombre</label>
											 <div class="col-sm-8">
												<valor>
													<cajatexto id="Reporte.parametros:[ 1 ].nombre" valor="[ 2 ]" />
												</valor>
											</div>
										</div>									
								
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Tipo</label>
											 <div class="col-sm-8">
												<valor>
													<cajatextoselector id="Reporte.parametros:[ 1 ].tipo" valor="[ 3 ]">
														<opcion valor="string" texto="Texto"/>
														<opcion valor="integer" texto="Entero"/>	
														<opcion valor="double" texto="Flotante"/>	
														<opcion valor="date" texto="Fecha"/>	
														<opcion valor="boolean" texto="Booleano"/>	
														<opcion valor="money" texto="Moneda"/>		
													</cajatextoselector>
												</valor>
											</div>
										</div>	
									
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Encriptado</label>
											 <div class="col-sm-8">
												<valor>
													<cajatextoselector id="Reporte.parametros:[ 1 ].encriptado" valor="[ 4 ]">
														<opcion valor="S" texto="Si"/>
														<opcion valor="N" texto="No"/>		
													</cajatextoselector>
												</valor>
											</div>
										</div>
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Filtro</label>
											 <div class="col-sm-8">
												<valor>
													<cajatextoselector id="Reporte.parametros:[ 1 ].filtro" valor="[ 5 ]">
														<opcion valor="true" texto="Si"/>
														<opcion valor="false" texto="No"/>		
													</cajatextoselector>
												</valor>
											</div>
										</div>
										
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Orden</label>
											 <div class="col-sm-8">
												<valor>
													<cajatexto id="Reporte.parametros:[ 1 ].orden" valor="[ 7 ]" />
												</valor>
											</div>
										</div>
									<area_botones>
										<boton id="btn_quitar_parametro_[ 1 ]" estilo="btn btn-danger" accion="page_quitar('parametro', PARAMETROS,'[ 1 ]')"><i class="fa fa-times" aria-hidden="true"></i>&#160; Quitar Parámetro</boton>
									</area_botones>
									
									<br /><br />
									
									</div>
									
								</div>
												
								<area_botones>
									<boton id="btn_agregar_parametro" estilo="crear" accion="page_agregar('parametro', PARAMETROS)"><i class="fa fa-plus" aria-hidden="true"></i>&#160; Agregar Parámetro</boton>
								</area_botones>
								
								</div>
								
							</pestana>							
							
							<pestana titulo="Resultados">
								
								<div id="contenido_lista_resultado"></div>
								
									<div id="PLANTILLA_RESULTADO" style="display:none">
									
										<div id="resultado_[ 1 ]">
										<div class="col-sm-10 col-sm-offset-1 col-lg-8 row col-lg-offset-2 form-horizontal">
									
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Título</label>
											 <div class="col-sm-8">
												<valor>
													<cajatexto id="Reporte.resultados:[ 1 ].titulo" valor="[ 2 ]" />
												</valor>
											</div>
										</div>										
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Nombre</label>
											 <div class="col-sm-8">
												<valor>
													<cajatexto id="Reporte.resultados:[ 1 ].nombre" valor="[ 3 ]" />
												</valor>
											</div>
										</div>
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Tipo</label>
											 <div class="col-sm-8">
												<valor>
													<cajatextoselector id="Reporte.resultados:[ 1 ].tipo" valor="[ 4 ]">
														<opcion valor="string" texto="Texto"/>
														<opcion valor="integer" texto="Entero"/>	
														<opcion valor="double" texto="Flotante"/>	
														<opcion valor="date" texto="Fecha"/>	
														<opcion valor="boolean" texto="Booleano"/>	
														<opcion valor="money" texto="Moneda"/>	
													</cajatextoselector>
												</valor>
											</div>
										</div>
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Encriptado</label>
											 <div class="col-sm-8">
												<valor>
													<cajatextoselector id="Reporte.resultados:[ 1 ].encriptado" valor="[ 5 ]">
														<opcion valor="S" texto="Si"/>
														<opcion valor="N" texto="No"/>		
													</cajatextoselector>
												</valor>
											</div>
										</div>
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Oculto</label>
											 <div class="col-sm-8">
												<valor>
													<cajatextoselector id="Reporte.resultados:[ 1 ].oculto" valor="[ 6 ]">
														<opcion valor="S" texto="Si"/>
														<opcion valor="N" texto="No"/>		
													</cajatextoselector>
												</valor>
											</div>
										</div>
										
										<div class="form-group form-group-sm">
											 <label class="contol-label col-sm-4">Orden</label>
											 <div class="col-sm-8">
												<valor>
													<cajatexto id="Reporte.resultados:[ 1 ].orden" valor="[ 7 ]" />
												</valor>
											</div>
										</div>
									
										<area_botones>
											<boton id="btn_quitar_resultado_[ 1 ]" estilo="btn btn-danger" accion="page_quitar('resultado',RESULTADOS,'[ 1 ]')">Quitar Resultado</boton>
										</area_botones>
											
									
									</div>		
								</div>
								
								</div>
								
								<area_botones>
									<boton id="btn_agregar_resultado" estilo="crear" accion="page_agregar('resultado',RESULTADOS)"><i class="fa fa-plus" aria-hidden="true"></i>&#160; Agregar Resultado</boton>
								</area_botones>
								
							</pestana>
							
							<pestana titulo="Navegaciones">
								
								<div id="contenido_lista_navegacion"></div>
								
									<div id="PLANTILLA_NAVEGACION" style="display:none">
									
										<div id="navegacion_[ 1 ]">
										
										<bloque-contenido>
										<titulo>Navegación</titulo>
										<contenido>
										
										<div class="col-sm-10 col-sm-offset-1 col-lg-8 row col-lg-offset-2 form-horizontal">
									
											<div class="form-group form-group-sm">
												 <label class="contol-label col-sm-4">NOMBRE</label>
												 <div class="col-sm-8">
													<valor>
														<cajatexto id="Reporte.navegacion:[ 1 ].nombre" valor="[ 2 ]" />
													</valor>
												</div>
											</div>
											
											<div class="form-group form-group-sm">
												 <label class="contol-label col-sm-4">DESTINO</label>
												 <div class="col-sm-8">
													<valor>
														<cajatexto id="Reporte.navegacion:[ 1 ].destino" valor="[ 3 ]" />
													</valor>
												</div>
											</div>
										
											<bloque-contenido>	
												<titulo>Parámetros</titulo>
												<contenido>		
													<div id="contenido_parametros_navegacion_[ 1 ]"></div>
												</contenido>
											</bloque-contenido>
											
											<area_botones>
												<boton id="btn_quitar_navegacion_[ 1 ]" estilo="btn btn-danger" accion="page_quitar('navegacion',NAVEGACIONES,'[ 1 ]')"><i class="fa fa-times" aria-hidden="true"></i>&#160; Quitar Navegación</boton>
												<boton id="btn_agregar_parametro_navegacion_[ 1 ]" estilo="crear" accion="agregar_navegacion_param('[ 1 ]')"><i class="fa fa-plus" aria-hidden="true"></i>&#160;  Agregar Parámetro Navegación</boton>
												
											</area_botones>
										</div>
										</contenido>	
										</bloque-contenido>
									
										</div>
									
									</div>
									
									<div id="PLANTILLA_PARAMETROS_NAVEGACION" style="display:none">
									
										<div id="parametro_navegacion_[ 2 ]">
												
										<div class="col-sm-10 col-sm-offset-1 col-lg-8 row col-lg-offset-2 form-horizontal">
									
											<div class="form-group form-group-sm">
												 <label class="contol-label col-sm-4">Nombre</label>
												 <div class="col-sm-8">
													<valor>
														<cajatexto id="Reporte.navegacion:[ 1 ].parametros:[ 2 ].nombre" valor="[ 3 ]" />
													</valor>
												</div>
											</div>
											
											<div class="form-group form-group-sm">
												 <label class="contol-label col-sm-4">Tipo</label>
												 <div class="col-sm-8">
													<valor>
														<cajatextoselector id="Reporte.navegacion:[ 1 ].parametros:[ 2 ].tipo" valor="[ 4 ]">
															<opcion valor="string" texto="Texto"/>
															<opcion valor="integer" texto="Entero"/>	
															<opcion valor="double" texto="Flotante"/>	
															<opcion valor="date" texto="Fecha"/>	
															<opcion valor="boolean" texto="Booleano"/>	
															<opcion valor="money" texto="Moneda"/>			
														</cajatextoselector>
													</valor>
												</div>
											</div>
											
											<div class="form-group form-group-sm">
												 <label class="contol-label col-sm-4">Encriptado</label>
												 <div class="col-sm-8">
													<valor>
														<cajatextoselector id="Reporte.navegacion:[ 1 ].parametros:[ 2 ].encriptado" valor="[ 5 ]" >
															<opcion valor="S" texto="Si"/>
															<opcion valor="N" texto="No"/>		
														</cajatextoselector>
													</valor>
												</div>
											</div>
											
											<div class="form-group form-group-sm">
												 <label class="contol-label col-sm-4">Valor</label>
												 <div class="col-sm-8">
													<valor>
														<cajatexto id="Reporte.navegacion:[ 1 ].parametros:[ 2 ].valor" valor="[ 6 ]" />
													</valor>
												</div>
											</div>
										
										<area_botones>
											<boton id="btn_quitar_parametro_navegacion_[ 2 ]" estilo="btn btn-danger" accion="quitar_navegacion_param('[ 2 ]')">Quitar Parametro Navegación</boton>
										</area_botones>
										</div>
									</div>	
										
								</div>
											
								<area_botones>
									<boton id="btn_agregar_navegacion" estilo="crear" accion="page_agregar('navegacion',NAVEGACIONES)"><i class="fa fa-plus" aria-hidden="true"></i>&#160; Agregar Navegación</boton>
								</area_botones>
								
							</pestana>
							
							<pestana titulo="Acciones">
								
								<div id="contenido_lista_accion"></div>
								
									<div id="PLANTILLA_ACCION" style="display:none">
									
										<div id="accion_[ 4 ]">
										
										<bloque-contenido>
										<titulo>Acción</titulo>
										<contenido>
										
										<p style="text-align:center">		
											Sub Reporte:
											<input type="radio" id="radio_subreporte" name="tipo_reporte" value="subreporte" checked="true" onchange="radioTipo_ReporteClicked('radio_subreporte')"/>											
											<br />
											Reporte:
											<input type="radio" id="radio_reporte" name="tipo_reporte" value="reporte" onchange="radioTipo_ReporteClicked('radio_reporte')"/>
										</p>	
											
										<registro id='input_reporte'>
											<item>SUB REPORTE</item>
											<valor>
												<cajatextoselector id="Reporte.accionfila.subreporte">
														<opcion valor="" texto="Seleccione un sub reporte..."/>	
													<xsl:for-each select="//reportesDB/reportesDB/ServicioReporteDim">
														<opcion valor="{id}" texto="{nombre}"/>	
													</xsl:for-each>	
												</cajatextoselector>
											</valor>
										</registro>
										
										<registro id='input_destino' visible='false'>
											<item>DESTINO</item>
											<valor>
												<cajatextoselector id="Reporte.accionfila.destino">
														<opcion valor="" texto="Seleccione un reporte..."/>
													<xsl:for-each select="//reportesDB/reportesDB/ServicioReporteDim">
														<opcion valor="reportedim/reporte.do?id={id}" texto="{nombre}"/>	
													</xsl:for-each>	
												</cajatextoselector>
											</valor>
										</registro>	
									
										<bloque-contenido>	
											<titulo>Parámetros</titulo>
											<contenido>		
												<div id="contenido_parametros_accion_[ 4 ]"></div>
											</contenido>
										</bloque-contenido>
											
										<area_botones>
											<boton id="btn_quitar_accion_[ 4 ]" estilo="btn btn-danger" accion="page_quitar('accion',ACCIONES,'[ 4 ]')"><i class="fa fa-times" aria-hidden="true"></i>&#160; Quitar Acción</boton>
											<boton id="btn_agregar_parametro_accion_[ 4 ]" estilo="crear" accion="agregar_accion_param('[ 4 ]')"><i class="fa fa-plus" aria-hidden="true"></i>&#160; Agregar Parámetro Acción</boton>											
										</area_botones>
										
										</contenido>	
										</bloque-contenido>
									
										</div>
									
									</div>
									
									<div id="PLANTILLA_PARAMETROS_ACCION" style="display:none">
									
										<div id="parametro_accion_[ 2 ]">
										
										<registro>
											<item>Nombre</item>
											<valor>
												<cajatexto id="Reporte.accionfila.parametros:[ 2 ].nombre" valor="[ 3 ]" />
											</valor>
										</registro>
										
										<registro>
											<item>Tipo</item>
											<valor>
												<cajatextoselector id="Reporte.accionfila.parametros:[ 2 ].tipo" valor="[ 4 ]">
													<opcion valor="string" texto="Texto"/>
													<opcion valor="integer" texto="Entero"/>	
													<opcion valor="double" texto="Flotante"/>	
													<opcion valor="date" texto="Fecha"/>	
													<opcion valor="boolean" texto="Booleano"/>	
													<opcion valor="money" texto="Moneda"/>			
												</cajatextoselector>
											</valor>
										</registro>
											
										<registro>
											<item>Encriptado</item>
											<valor>
												<cajatextoselector id="Reporte.accionfila.parametros:[ 2 ].encriptado" valor="[ 5 ]" >
													<opcion valor="S" texto="Si"/>
													<opcion valor="N" texto="No"/>		
												</cajatextoselector>
											</valor>
										</registro>
											
										<registro>
											<item>Valor</item>
											<valor>
												<cajatexto id="Reporte.accionfila.parametros:[ 2 ].valor" valor="[ 6 ]" />
											</valor>
										</registro>
										
										<area_botones>
											<boton id="btn_quitar_parametro_accion_[ 2 ]" estilo="btn btn-danger" accion="quitar_accion_param('[ 2 ]')">Quitar Parametro Acción</boton>
										</area_botones>
									
									</div>	
										
								</div>
											
								<area_botones>
									<boton id="btn_agregar_accion" estilo="crear" accion="page_agregar('accion',ACCIONES)"><i class="fa fa-plus" aria-hidden="true"></i>&#160; Agregar Acción</boton>
								</area_botones>
								
							</pestana>
							
						</bloque-pestanas>
						
						<variable id="id_reporte" valor="{//REPORTE_ID}"/>
						
						<script>recuperarReporte();</script>
						
					</formulario>
					</div>					
					<area_botones>
						<boton estilo="danger" destino="admin_reportes/admin_rep.1.do"><i class="fa fa-times" aria-hidden="true"></i>&#160; Cancelar</boton>
						<boton estilo="primary guardar" formulario="form_edicion" validacion="page_validarGuardar()"><i class="fa fa-save" aria-hidden="true"></i>&#160; Guardar Reporte</boton>
					</area_botones>
					
				</contenido>
					
			</principal>

		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>