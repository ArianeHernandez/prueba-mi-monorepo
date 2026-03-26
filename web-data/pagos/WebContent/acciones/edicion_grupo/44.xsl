<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina>
		
			<javascript>edicion_grupo/44.js</javascript>

			<principal>
			
				<titulo>Edicion de Grupos de Giro</titulo>

				<contenido>
				
					<formulario id="generarArchivoForm" destino="ArchivoBanco">
						<variable id="id_archivo" valor=""/>
						<variable id="nombre_archivo_d" valor=""/>
					</formulario>
					
					<div ng-app="app" ng-controller="appController">
					
					
						<div ng-if="archivo == null">
							<table class="table table-striped">
							  
							  	<thead>
								  	<tr>
								  		<th></th>
								  		<th>Fecha Creacion</th>
								  		<th>Nombre</th>
								  		<th>Cantidad</th>
								  		<th></th>
								  	</tr>
							  	</thead>
							  	
							  	<tbody>
								  	<tr ng-repeat="ar in listado">
								  		<th><i class="fa fa-files-o" aria-hidden="true"></i></th>
								  		<td> {{ formatoFecha(ar.fecha_creacion.time) }} </td>
								  		<td> {{ ar.nombre }}</td>
								  		<td> {{ ar.cantidad }} registros</td>
								  		<td> 
								  			<button type="button" class="btn btn-default" ng-click="seleccionarArchivo(ar)">
											  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&#160; Editar
											</button>
								  		 </td>
								  		
								  	</tr>
							  	</tbody>
							  
							</table>
							
						</div>
						
						<div ng-if="archivo != null">
							
							<div class="panel panel-default">
							  <div class="panel-heading">
							  		
							  		<div class="row">
							  			<div class="col-sm-6">{{ archivo.nombre }} <br/> {{ archivo.cuenta_origen }}</div>
							  			<div class="col-sm-6 text-right">{{ formatoFecha(archivo.fecha_creacion.time) }}<br/> {{ archivo.cantidad }} registros</div>
							  		</div>
							  	
							  	</div>
							  <div class="panel-body">
							    
							    
							    <table class="table table-striped">
							  
								  	<thead>
									  	<tr>
									  		<th></th>
									  		<th>Cliente</th>
									  		<th>Identificacion</th>
									  		<th>Valor</th>
									  		<th>Banco Destino</th>
									  		<th>Cuenta Destino</th>
									  		<th></th>
									  	</tr>
								  	</thead>
								  	
								  	<tbody>
									  	<tr ng-repeat="gg in grupos_giro">
									  	
									  		<th><i class="fa fa-file-o" aria-hidden="true"></i></th>
									  		<td> {{ gg.nombre_cliente }} </td>
									  		<td> {{ gg.identificacion_cliente }}</td>
									  		<td> {{ formatoMoneda(gg.valor) }}</td>
									  		<td> {{ gg.nombre_banco_destino }}</td>
									  		<td> {{ gg.cuenta_destino }}</td>
									  		<td> 
									  			<button type="button" class="btn btn-default" ng-click="alertaEliminar(gg)">
												  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&#160; Rechazar Pago
												</button>
									  		 </td>
									  		
									  	</tr>
								  	</tbody>
								  
								</table>
							    
							    
							  </div>
							  
							  <div class="panel-footer">
							  		<a class="btn btn-sm btn-default" ng-click="cancelarSeleccion()"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Cancelar</a>
									<a ng-if="archivo.cantidad > 0" class="btn btn-sm btn-primary" ng-click="generarArchivo(archivo)"><i class="fa fa-download" aria-hidden="true"></i>&#160;Descargar</a>
								</div>
							</div>
							
						</div>
					
					</div>
				
				</contenido>
				
			</principal>
			
		</pagina>

	</xsl:template>

</xsl:stylesheet>
