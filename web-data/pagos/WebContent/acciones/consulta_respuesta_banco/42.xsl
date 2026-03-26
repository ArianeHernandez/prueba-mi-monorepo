<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		
		<ventana id="detallecarga_ventana" icono="confirmacion">
			<titulo id="titulo_detallecarga_ventana">Soporte de Pago</titulo>
			<contenido>

				<bloque estilo="grupo">
					<iframe name="iframe_block" id="iframe_block" src="" scrollable="yes" frameborder="0" style="width: 100%">No se puede visualizar el contenido</iframe>
				</bloque>
				
				<area_botones>
					<div id="iframe_botones"></div>
					<boton estilo="danger" accion="osm_setValor('iframe_botones',''); osm_getObjeto('iframe_block').src=''; $('#detallecarga_ventana').hide(); ">Cerrar</boton>
				</area_botones>
				
			</contenido>
		</ventana>
		
		<pagina titulo="Carga Masiva">

			<javascript>consulta_respuesta_banco/42.js</javascript>
			
			<javascript>componente/CPAGO.js</javascript>
			
			<principal>
				<titulo>Consultar Estado Respuesta Banco</titulo>
				<contenido>
					<div class="box-container">
					<bloque-contenido>
					<titulo>Filtros de Busqueda</titulo>
					<contenido>
						<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
						<formulario id="form_filtro">
							<registro>
								<item>Id Carga:</item>
								<valor>
									<cajatexto id="id_carga" valor=""/>
								</valor>
							</registro>
							
							<registro>
								<item>Banco Destino:</item>
								<valor>
									<cajatextoselector id="id_banco_destino" valor="">
										<opcion>--Todos--</opcion>
										<xsl:for-each select="//obtenerBancos/Listado/HashMap">
											<xsl:sort select="C1221"/>
											<opcion valor="{ID}"><xsl:value-of select="C1221" /></opcion>
										</xsl:for-each>
									</cajatextoselector>
								</valor>
							</registro>
		
							<registro>
								<item>Id Beneficiario:</item>
								<valor>
									<cajatexto id="id_beneficiario" valor=""/>
								</valor>
							</registro>
							
							<registro>
								<item>Banco Girador:</item>
								<valor>
									<cajatextoselector id="id_banco_girador" valor="">
										<opcion>--Todos--</opcion>
										<xsl:for-each select="//obtenerBancos/Listado/HashMap">
											<xsl:sort select="C1221"/>
											<opcion valor="{ID}"><xsl:value-of select="C1221" /></opcion>
										</xsl:for-each>
									</cajatextoselector>
								</valor>
							</registro>
							
							<registro>
								<item>Respuesta Banco:</item>
								<valor>
									<cajatextoselector id="respuesta_banco" valor="_SR">
										<opcion>--Todos--</opcion>
										<opcion valor="_SR">--Sin Respuesta--</opcion>
										<xsl:for-each select="//obtenerRespuestasBancos/Listado/HashMap">
											<xsl:sort select="ESTADO"/>
											<opcion valor="{ESTADO}"><xsl:value-of select="ESTADO" /></opcion>
										</xsl:for-each>
									</cajatextoselector>
								</valor>
							</registro>
		
							<registro>
								<item>Tipo Actualización:</item>
								<valor>
									<cajatextoselector id="tipo_actualizacion" valor="">
										<opcion>--Todos--</opcion>
										<opcion valor="Manual">Manual</opcion>
										<opcion valor="Automatico">Automatico</opcion>
										<opcion valor="Archivo">Archivo</opcion>
									</cajatextoselector>
								</valor>
							</registro>
							
							<registro>
								<item>Valor:</item>
								<valor>
									<cajatexto id="valor" valor=""/>
								</valor>
							</registro>
							
							<registro>
								<item>Fecha de Giro:</item>
								<valor>
									<cajafecha id="fecha_giro" valor=""/>
								</valor>
							</registro>
							
							<registro>
								<item>Ordenar por:</item>
								<valor>
									<cajatextoselector id="ordenado_por" valor="ID_CARGA">
										<opcion valor="id_carga desc">Id Carga</opcion>
										<opcion valor="id_beneficiario">Id Beneficiario</opcion>
										<opcion valor="fecha_giro desc">Fecha giro</opcion>
										<opcion valor="valor">Valor</opcion>
										<opcion valor="tipo_actualizacion">Tipo de Actualización</opcion>
									</cajatextoselector>
								</valor>
							</registro>
							
							<registro>
								<item>Ver:</item>
								<valor>
									<cajatextoselector id="paginacion" valor="15">
										<opcion valor="15">15</opcion>
										<opcion valor="30">30</opcion>
										<opcion valor="50">50</opcion>
									</cajatextoselector>
								</valor>
							</registro>
						</formulario>
						<div class="clearfix"></div>
						</div>
						<div class="row-btn">
							<boton accion="buscar()"><i class="fa fa-search" aria-hidden="true"/>&#160;Buscar</boton>
						</div>
						
					</contenido>
					
					</bloque-contenido>
					</div>
					
					<!-- ******************************************************************* -->
					
					<div id="sin_resultados" style="display:none">
						<nota>Sin Resultados</nota>
					</div>
					
					<bloque id="bloque_retiros_encontrados" visible="false">
						
						<bloque estilo="grupo">
							<area_botones estilo='simple'>
								<boton estilo="primary xls" accion="osm_go('42.retiroconsulta', false); ">
									<i class="fa fa-file-excel-o" aria-hidden="true"></i>&#160;ver xls
								</boton>
							</area_botones>
						</bloque>
						
						<div class="table-responsive">
							<table class="table table-hover table-striped">						
								<thead>
									<tr class="tabla_encabezado">
										<td>ID</td>
										<td>Nombre Cliente</td>
										<td>Id Beneficiario</td>
										<td>Nombre Beneficiario</td>
										<td>Banco Destino</td>
										<td>Cuenta Destino</td>
										<td>Fecha Giro</td>
										<td>Banco Girador</td>
										<td>Valor</td>
										<td>Respuesta Banco</td>
										<td>Tipo Actualización</td>
										<td>.</td>
									</tr>
								</thead>
								
								<tbody id="cont_retiros"></tbody>
												
							</table>
						</div>
						
						<div class="paginacion">
							<table class="tabla_paginacion" border="0" cellspacing="0" cellpadding="0" width="200px">
								<tr>
									<td width="22px">
										<a onclick="paginacion_inicio()">
											<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero"/>
										</a>
									</td>
								
									<td width="22px">
										<a onclick="paginacion_anterior()">
											<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior"/>
										</a>
									</td>
								
									<td>
										<p id="texto pagina">--</p>
									</td>
			
									<td width="22px">
										<a onclick="paginacion_siguiente()">
											<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente"/>
										</a>
									</td>
								
									<td width="22px">
										<a onclick="paginacion_final()">
											<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo"/>
										</a>
									</td>
								</tr>
							</table>
						</div>

						<area_botones>
							<boton estilo="primary volver" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Volver</boton>
						</area_botones>
					
					</bloque>
				
					<table class="tabla_paginacion" style="display:none">
						<tbody id="PLANTILLA_FILA">
							<tr class="tabla_fila" id="fila_[ 1 ]">
								<td>[ 2 ]</td>
								<td>[ 3 ]</td>
								<td>[ 4 ]</td>
								<td>[ 5 ]</td>
								<td>[ 6 ]</td>
								<td>[ 7 ]</td>
								<td>[ 8 ]</td>
								<td>[ 12 ]</td>
								<td>[ 9 ]</td>
								<td>[ 10 ]</td>
								<td>[ 11 ]</td>
								<td>
									<boton accion="verDetalle('[ 1 ]')"><i class="fa fa-search-plus" aria-hidden="true"></i>&#160;Historial</boton>
									
									<div id="cmp_[ 1 ]" class="reg_detalle" style="display:none">
										<boton estilo="primary" accion="CMP_ACCION([ 1 ])">Soporte de Pago</boton>
									</div>
									
								</td>
							</tr>
						</tbody>
					</table>

					<table class="tabla_paginacion" style="display:none">
					<tbody id="PLANTILLA_FILA_HISTORIAL">
						<tr class="tabla_fila">
							<td>[ 1 ]</td>
							<td>[ 2 ]</td>
							<td>[ 3 ]</td>
							<td>[ 4 ]</td>
						</tr>
					</tbody>
				</table>								
					
				</contenido>
			</principal>
			
			<ventana id="vn_historialRegistro" icono="confirmacion">
				<titulo>Historial de Registro</titulo>
				<contenido>
					<bloque-contenido>
						<contenido>
							<div class="tabla_borde table-responsive">
								<table class="table table-hover table-striped tb">						
									<thead>
										<tr class="tabla_encabezado">
											<td>Estado</td>
											<td>Fecha/Hora</td>
											<td>Tipo Actualización</td>
											<td>Usuario</td>
										</tr>
									</thead>
									
									<tbody id="cont_historial"></tbody>
									
								</table>
							</div>
			
						</contenido>
					</bloque-contenido>
					
					<area_botones>
						<boton estilo="primary volver" accion="cerrarVentanaHistorial();"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Volver</boton>
					</area_botones>
					
				</contenido>
				
				
		
			
			</ventana>
			
			
			
		</pagina>
		
		

	</xsl:template>



</xsl:stylesheet>
