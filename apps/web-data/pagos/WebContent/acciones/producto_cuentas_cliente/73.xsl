<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
 
	<xsl:template match="OSM-ACCION">

		<pagina titulo="Consulta de productos y cuentas">
			<javascript>producto_cuentas_cliente/73.js</javascript>
			<principal>
				<titulo>Consulta de Productos y Cuentas</titulo>
				<contenido>
					<div ng-app="app" ng-controller="appController" class="box-container">
						<div class="panel panel-default">
							<div class="panel-heading">
								Informacion del Cliente	
							 </div>
							<div class="panel-body">
							
							<div ng-if="visible" class="alert alert-info" role="alert"> <i class="fa fa-info-circle" aria-hidden="true"></i> Sin resultados </div>
							
							<div class="panel panel-default" ng-if="existePersona">
							
							<div class="panel-body">
							<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								<div class="form-horizontal">
										<div class="form-group form-group-sm">
											<label class="col-sm-4 control-label">Nombre: </label>
											<div class="col-sm-8">
												<input class="form-control" type="text" disabled="disabled" ng-model="persona.nombreCompleto"></input>
											</div>
										</div>
										<div class="form-group form-group-sm" ng-if="persona.telefono != null">
											<label class="col-sm-4 control-label">Telefono: </label>
											<div class="col-sm-8">
												<input class="form-control" type="text" disabled="disabled" ng-model="persona.telefono" ></input>
											</div>
										</div>
										<div class="form-group form-group-sm" ng-if="persona.direccion != null">
											<label class="col-sm-4 control-label">Direccion: </label>
											<div class="col-sm-8">
												<input class="form-control" type="text" disabled="disabled" ng-model="persona.direccion" ></input>
											</div>
										</div>
										<div class="form-group form-group-sm">
											<label class="col-sm-4 control-label">Correo: </label>
											<div class="col-sm-8">
												<input class="form-control" type="text" disabled="disabled" ng-model="persona.correo"></input>
											</div>
										</div>
									</div>
									</div>
							</div>
							<div class="panel-body">
								<table class="table table-striped" ng-if="existeCuentas">
								  	<thead>
									  	<tr>
									  		<th>Tipo de Negocio</th>
									  		<th>Producto</th>
									  		<th>Cuenta</th>
									  		<th>Saldo</th>
									  	</tr>
								  	</thead>
								  	<tbody>
									  	<tr ng-repeat="l in listaProductoCuenta" align="center">
									  		<td> {{ l.codigoLinea }} - {{l.nombreLinea}}</td>
									  		<td> {{ l.codigoProducto }} - {{l.nombreProducto}}</td>
									  		<td> {{ l.cuenta }} </td>
									  		<td> {{ l.saldo | currency}}</td>
									  	</tr>
								  	</tbody>
								</table>
							</div>
								<div ng-if="!existeCuentas" class="alert alert-info" role="alert"> <i class="fa fa-info-circle" aria-hidden="true"></i> Sin cuentas </div>
							</div>
						</div>
						
					</div>
					</div>
					
				</contenido>

			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
