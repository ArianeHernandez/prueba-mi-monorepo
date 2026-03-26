<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
 
	<xsl:template match="OSM-ACCION">

		<pagina titulo="Conf Cuenta ACH">
			<javascript>producto_cuentas/72.js</javascript>
			<principal>
				<titulo>Configuracion Cuenta por Defecto para Pago ACH</titulo>
				<contenido>
					<div ng-app="app" ng-controller="appController" class="box-container">
						
						<div class="list-group-item" ng-repeat="producto in listaProductos">	
							<div class="form-group form-group-sm row">
								<label class="col-sm-4 control-label text-left">{{ producto.producto.nombre }}</label>
								<div class="col-sm-8">
									<select ng-model="producto.producto.cuentaDefecto" name="Tipo" ng-options="cuenta.cuentaBancaria for cuenta in producto.cuentas.list"  class="form-control" ng-change="actualizarCuentasPorDefault(producto.producto)" />
								</div>
							</div>
								
		      			</div>
		      			
		      			<div class="panel-footer">																														
								<button class="btn btn-sm btn-default" ng-click="cancelar()"> 
								<i class="fa fa-caret-left" aria-hidden="true"></i> Cancelar </button>
								<button class="btn btn-sm btn-primary" ng-click="guardarCuentasPorDefault()"> 
								<i class="fa fa-floppy-o" aria-hidden="true"></i> Guardar </button>																
						</div>
					</div>
					
					
				</contenido>

			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
