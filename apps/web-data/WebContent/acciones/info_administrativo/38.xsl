<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">


		<pagina>

			<principal>

				<titulo>Configurar Valores en Atributo de delegados</titulo>

				<contenido>
				
					<javascript>info_administrativo/38.js</javascript>
					<script> listaAtributos = <xsl:value-of select="listaAtributos" /> ;</script>
					<script> listaAdminis = <xsl:value-of select="listaAdminis" /> ;</script>
					
					<div ng-app="app" ng-controller="appController"><!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
						<div class="box-container">
							<div class="row-btn">								
								<button estilo="crear" class="btn btn-primary" ng-click="crearAtributo()">
								<i class="fa fa-plus" aria-hidden="true"></i> Agregar Atributo</button>								
							</div>
							<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
							<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								<div class="form-group form-group-sm">									
									<div class="input-group input-group-sm">
										<span class="input-group-addon"> Atributo a Editar </span>
										<select ng-model="atributoActual" name="Listado Atributos" ng-change="traerListaValores()" ng-options="atributo.nombre for atributo in listaAtributo"  class="form-control"/>									
										<div class="input-group-btn" ng-if="listadoActual[0].nombre != null">
											<button estilo="primary" ng-click="eliminarAtributo()"> 
												<i class="fa fa-times" aria-hidden="true"></i><span class="hide-xs">&#160;Eliminar Atributo</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
							<div class="panel" ng-if="atributoActual.nombre != null">
								<div class="panel panel-default">
									<table class="input-group table table-hover table-striped col-sm-12">
										<tbody ng-repeat="administrativo in listadoActual">
											<tr class="col-sm-12"><th> {{ administrativo.nombre }} </th></tr>
											<tr class="col-sm-12" ng-repeat="valor in administrativo.listValores" ng-if="administrativo.listValores[0].valor != null">
	<!-- 											<td class="col-sm-6 text-right"></td> -->
												<td padding-buttom="50pt">
													<a class="btn btn-sm btn-primary">
														<span>  ->  {{ valor.valor }} </span>
														<i class="fa fa-times" ng-click="eliminarValorAtributo(valor)"/>
													</a>
												</td>
											</tr>
											<tr class="table table-hover table-striped col-sm-12" ng-if="atributoActual != null">
												<td class="col-sm-3">
													<th> </th>
												</td>
												<td class="col-sm-3">
													<input type="text" class="form-control" ng-model="nuevoValor.valor[$index]">
													</input>
												</td>
												<td class="col-sm-3">
													<button class="btn btn-primary" ng-click="crearValorAtributoAdminst(administrativo.id_administrativo, $index)"> 
													<i class="fa fa-floppy-o" aria-hidden="true"></i> Agregar Valor</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
						</div>
						
					</div><!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
				</contenido>
			</principal>

		</pagina>
	</xsl:template>

</xsl:stylesheet>
