<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Desactivar Beneficiarios">

			<javascript>editar_respuesta_banco/41.js</javascript>

			<principal>
				<titulo>Cargue Respuesta Banco (Manual)</titulo>
				<contenido>
					
					<div class="box-container">
					<formulario>
						<div class="panel panel-default">
							<div class="panel-heading">Datos del Cliente</div>
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
									<registro>
										<item>Identificación Cliente:</item>
										<valor>
											<cajatexto id="identifica_cliente"></cajatexto>
										</valor>
									</registro>
									
									<registro >
										<item>Id Carga:</item>
										<valor>
											<cajatexto id="id_carga"></cajatexto>
										</valor>
									</registro>
									
									<registro >
										<item>Banco Girador:</item>
										<valor>
											<select id="id_bancoGirador" class="form-control">
												<option value="">-- Todos --</option>
												<xsl:for-each select="//obtenerBancos/Listado/HashMap">
													<xsl:sort select="C1221" />
													<option value="{ID}"><xsl:value-of select="C1221" /></option>
												</xsl:for-each>
											</select>
										</valor>
									</registro>
								</div>
							</div>
							<div class="panel-footer">
							<a class="btn btn-primary btn-sm" onclick="PAGINA_ACTUAL = 1; buscarRetiros()" id="btn_enviar">
							<i class="fa fa-search" aria-hidden="true"></i>  Buscar  		
						</a>
							</div>
						</div>
					</formulario>

				
					
					<bloque id="bloque_retiros_encontrados" visible="false">
						
						<div class="tabla_borde table-responsive">
							<table class="table table-hover table-striped">						
								<thead>
									<tr class="tabla_encabezado">
										<td>Id Carga</td>
										<td>Cliente</td>
										<td>Tipo Retiro</td>
										<td>Beneficiario</td>
										<td>Banco Destino</td>
										<td>Cuenta Destino</td>
										<td>Fecha Giro</td>
										<td>Banco Girador</td>
										<td>Valor</td>
										<td>Respuesta Banco</td>
										<td>Accion</td>
									</tr>
								</thead>
								
								<tbody id="cont_retiros"></tbody>
												
							</table>
						</div>
						
						<nav aria-label="Page navigation" class="text-center">
							  <ul class="pagination">
							    <li>
							      <a onclick="paginacion_inicio()" aria-label="ir al inicio">
							       <i class="fa fa-fast-backward" aria-hidden="true"></i>
							      </a>
							    </li>
							    <li>
							     <a onclick="paginacion_anterior()" aria-label="anterior">
							        <i class="fa fa-backward" aria-hidden="true"></i>
							      </a>
							    </li>
							    <li><a id="texto pagina"></a></li>	
							    					   
							    <li>
							      <a onclick="paginacion_siguiente()" aria-label="siguiente">
							       <i class="fa fa-forward" aria-hidden="true"></i>
							      </a>
							    </li>
							     <li>
							      <a onclick="paginacion_final()" aria-label="ir al final">
							        <i class="fa fa-fast-forward" aria-hidden="true"></i>
							      </a>
							    </li>
							  </ul>
							</nav>
				
						<area_botones>
							<boton estilo="cancelar" destino="inicio/0.do"><i class="fa fa-times" aria-hidden="true"></i>  Cancelar</boton>
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
								<td>[ 9 ]</td>
								<td>[ 11 ]</td>
								<td>[ 12 ]</td>
								<td><boton accion="validarEdicion('[ 1 ]','[ 2 ]','[ 3 ]','[ 4 ]','[ 5 ]','[ 6 ]','[ 7 ]','[ 8 ]','[ 9 ]','[ 10 ]','[ 11 ]','[ 12 ]')"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></boton></td>
							</tr>
						</tbody>
					</table>
					</div>
				</contenido>
			
				
			</principal>
			
			<ventana id="vn_confirmacion" icono="confirmacion">
				<titulo>Edicion Respuesta de Banco</titulo>
				<contenido>
				<div class="form-horizontal">
					<bloque-contenido>
							<titulo>Edición</titulo>
							<contenido>
	
						<div class="tabla_borde table-responsive">
							<table class="table table-hover table-striped">						
								<thead>
									<tr class="tabla_encabezado">
										<td>Id Carga</td>
										<td>Cliente</td>
										<td>Tipo Retiro</td>
										<td>Beneficiario</td>
										<td>Banco Destino</td>
										<td>Cuenta Destino</td>
										<td>Fecha Giro</td>
										<td>Banco Girador</td>
										<td>Valor</td>
										<td>Respuesta Banco</td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td id="m_id_carga"></td>
										<td id="m_cliente"></td>
										<td id="m_tipo_retiro"></td>
										<td id="m_identificacion_beneficiario"></td>
										<td id="m_banco_destino"></td>
										<td id="m_cuenta_destino"></td>
										<td id="m_fecha_giro"></td>
										<td id="m_banco_girador"></td>
										<td id="m_valor"></td>
										<td id="m_respuesta_banco"></td>
									</tr>
								</tbody>
							</table>
						</div>
					
						<input id="m_id_retiro" type="hidden" value="" />
						<div class="row-box">
							<div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								<registro>
									<item>Nueva Respuesta de Banco:</item>
									<valor>
										<select id="selector_respuestas_banco" class="form-control"></select>
									</valor>
								</registro>
							</div>
						</div>	
					</contenido>
					</bloque-contenido>
	
					<area_botones>
						<boton estilo="aceptar" accion="actualizarRespuestaBancoRetiro()"><i class="fa fa-save" aria-hidden="true"></i>  Guardar</boton>
						<boton estilo="cancelar" accion="cerrarVentanaValidacion();"><i class="fa fa-times" aria-hidden="true"></i>  Cancelar</boton>
					</area_botones>
					</div>
				</contenido>
			</ventana>
			
		</pagina>


	</xsl:template>
	
</xsl:stylesheet>
