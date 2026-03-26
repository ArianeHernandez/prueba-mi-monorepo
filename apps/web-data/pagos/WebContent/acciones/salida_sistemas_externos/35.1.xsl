<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Exportar información a sistemas SIF-ORION">
			<javascript>salida_sistemas_externos/35.1.js</javascript>
			<stylesheet>35.1.css</stylesheet>
		
			
			<principal>
				<titulo>Exportar información a sistemas SIF-ORION</titulo>
				<contenido>
					<div class="box-container form-horizontal">
					
						<div class="panel panel-default">
							<div class="panel-heading">Consulta</div>
							<div class="panel-body">
								<div class=" form-horizontal">
									<!-- SELECTOR DE TIPOS DE ARCHIVO DE SALIDA -->
									<xsl:variable name="archivos" select="//obtenerTiposArchivoSalidaCorreval/listado/String"/>
									
									<div class="form-group form-group-sm">
										<label class="control-label col-sm-4  col-xs-12">Tipo de archivo a generar</label>
										<div class="col-sm-8">
											<cajatextoselector class="form-ycontrol select_formato" id="select_tipo_archivo" accion="listarRegistros()" valor="{id_formato}">
												<opcion valor="" texto="-Seleccione-"/>
												<xsl:for-each select="$archivos">
												<xsl:sort select="."></xsl:sort>
													<opcion valor="{.}" texto="{.}"/>
												</xsl:for-each>
											</cajatextoselector>
										</div>
									</div>
									<br/>
									
									<div id="mensaje_error" style="display:none">
										<parrafo estilo="resaltado">
										No hay <texto key="RETIROS" /> asociados al tipo de archivo seleccionado
										</parrafo>
									</div>
									
									<formulario id="form_generar_archivo" destino="salida_sistemas_externos/35.2.do">
										<input type="hidden" id="tipo_archivo" name="tipo_archivo" value=""/>
										 
										<div id="div_comandos"></div>	
										
									</formulario>
								</div>
							</div>
						</div>
								
								<div id="LISTA_REGISTROS"/>
								
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
								
								<br/><br/>
								<div id="area_botones" style="display:none">
									<area_botones>
										<boton estilo="primary" accion="return generarArchivo();"><i class="fa fa-toggle-right" aria-hidden="true"></i>&#160; Generar archivo</boton>
									</area_botones>
								</div>
								
								<xsl:call-template name="plantillas" />
					</div>
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>
	
	<xsl:template name="plantillas">
		<div style="display:none">
			<div id="PLANTILLA_ENCABEZADO_TABLA">
				<div class="table-responsive">
					<table class="table table-hover table-striped">
						<thead>
							<tr class="tabla_encabezado">
								<td class=""><input type="checkbox" id="check_todos" valor="S" onclick="toogleSeleccionarTodos()"/></td>
								<td class="">Nombre Beneficiario</td>
								<td class="">Banco Origen</td>
								<td class="">Banco Destino</td>
								<td class="">Tipo Retiro</td>
								<td class="">Valor Retiro</td>
								
							</tr>
						</thead>
						<tbody id="contenido_dinamico"></tbody>
					</table>

					
				</div>
			</div>

			<table>
				<tbody id="PLANTILLA_FILA_TABLA">
					<tr class="table tabla_fila" id="fila_[ 1 ]">
						<td ><input type="checkbox" id="retiro_[ 1 ]" name="id_retiros:[[ 1 ]]" value="[ 1 ]" onclick="seleccionRegistro([ 1 ], this.checked)" class="cajachequeo"/> </td>
						<td >[ 2 ]</td>
						<td >[ 3 ]</td>
						<td >[ 4 ]</td>
						<td >[ 5 ]</td>
						<td >[ 6 ]</td>
						
					</tr>
				</tbody>
			</table>
			<div id="PLANTILLA_COMANDO">
				<div id="comando_[ 1 ]">
					<input type="hidden" id="comando_id_registro_[ 1 ]" name="comandos:[ 1 ].id_registro" value="[ 1 ]"/>
					<input type="hidden" id="comando_orden_[ 1 ]" name="comandos:[ 1 ].orden"  value="[ 2 ]"/>
					<input type="hidden" id="comando_agregar_[ 1 ]" name="comandos:[ 1 ].agregar"  value="[ 3 ]"/>
				</div>
			</div>

		</div>
	</xsl:template>

	

</xsl:stylesheet>
