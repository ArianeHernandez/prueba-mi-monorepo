<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Consulta de archivos a sistemas SIF-ORION">
			<javascript>salida_sistemas_externos/consulta/35.3.js</javascript>
			<stylesheet>35.1.css</stylesheet>
		
			
			<principal>
				<titulo>Consulta de archivos a sistemas SIF-ORION</titulo>
				<contenido>
					<div class="box-container form-horizontal">
					
						<div class="panel panel-default">
							<div class="panel-heading">Consulta</div>
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								
						<!-- SELECTOR DE TIPOS DE ARCHIVO DE SALIDA -->
						<xsl:variable name="archivos" select="//obtenerTiposArchivoSalidaCorreval/listado/String"/>
						
						<div class="form-group form-group-sm">
							<label class="control-label col-sm-4  col-xs-12">Tipo de archivo a consultar</label>
							<div class="col-sm-8">
							<cajatextoselector class="form-control select_formato" id="select_tipo_archivo" accion="listarRegistros()" valor="{id_formato}">
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
							No hay archivos históricos asociados al tipo de archivo seleccionado
							</parrafo>
						</div>
						
						<formulario id="form_generar_archivo" destino="salida_sistemas_externos/consulta/35.4.do">
							<input type="hidden" id="archivoSalidaCore.tipoArchivo" name="archivoSalidaCore.tipoArchivo" value=""/>
							<input type="hidden" id="archivoSalidaCore.id_archivo" name="archivoSalidaCore.id_archivo" value=""/>
							
							<div id="LISTA_REGISTROS"/>
							
						</formulario>
							</div>
						</div>
						<div class="panel-footer">
							<boton estilo="inicio" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160; Ir al Inicio</boton>
						</div>
					</div>
					
					
					<xsl:call-template name="paginacion" />
					
					<xsl:call-template name="plantillas" />
					
					
				
					</div>
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>
	
	<xsl:template name="paginacion">
		<div class="paginacion" id="div_paginacion" style="display:none">
			<table class="tabla_paginacion" border="0" cellspacing="0"
				cellpadding="0" width="300px">
				<tr>
					<td width="22px">
						<a id="pag_primero" onclick="ir_primero()">
							<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero" />
						</a>
					</td>
					<td width="22px">
						<a id="pag_atras" onclick="ir_atras()">
							<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior" />
						</a>
					</td>
					<td>
						<p id="pag_info_pags">
							<span id="pag_desde"></span>
							a
							<span id="pag_hasta"></span>
							de
							<span id="pag_total"></span>
						</p>
						<div id="pag_info_carga">
							
						</div>
					</td>
					<td width="22px">
						<a id="pag_siguiente" onclick="ir_siguiente()">
							<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente" />
						</a>
					</td>
					<td width="22px">
						<a id="pag_ultimo" onclick="ir_ultimo()">
							<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo" />
						</a>
					</td>
				</tr>
			</table>
		</div>
	</xsl:template>
	
	<xsl:template name="plantillas">
		<div style="display:none">
			<div id="PLANTILLA_ENCABEZADO_TABLA">
				<div>
					<table class="table table-hover table-striped tb">
						<thead>
							<tr class="tabla_encabezado">
								
								<td class="">N° de archivo</td>
								<td class="">Tipo de archivo</td>
								<td class="">Fecha de creación</td>
								<td class="">Nombre dede la persona generadora</td>
								<td class="">Descargar</td>
								
							</tr>
						</thead>
						<tbody id="contenido_dinamico"></tbody>
					</table>

					
				</div>
			</div>

			<table>
				<tbody id="PLANTILLA_FILA_TABLA">
					<tr class="tabla_fila" id="fila_[ 1 ]">
						
						<td class="">A[ 1 ]</td>
						<td class="">[ 2 ]</td>
						<td class="">[ 3 ]</td>
						<td class="">[ 4 ]</td>
						<td class="">
							<boton estilo="primary" accion="return generarArchivo('[ 1 ]');">Generar archivo</boton>
						</td>
						
					</tr>
				</tbody>
			</table>

		</div>
	</xsl:template>
	
	

</xsl:stylesheet>
