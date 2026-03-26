<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Carga Masiva">

			<principal>
				<titulo><texto key="CARGA"/>&#160;<texto key="MASIVA"/></titulo>
				<contenido>
					<div class="box-container">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="alert alert-info">
								<i class="fa fa-info-circle" aria-hidden="true"></i> Seleccione una opción de carga
							</div>
									
							
						</div>
						<div class="panel-footer">
								<boton estilo="primary aceptar" destino="carga_masiva/31.1.do"><i class="fa fa-users" aria-hidden="true"></i>&#160;Clientes</boton>
								<boton estilo="primary aceptar" destino="carga_masiva/31.2.do"><i class="fa fa-bank" aria-hidden="true"></i>&#160;Cuentas</boton>
								<boton estilo="primary aceptar" destino="carga_masiva/31.3.do"><i class="fa fa-credit-card" aria-hidden="true"></i>&#160;Productos</boton>								
						</div>
					</div>
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Ir al Inicio</boton>
					</area_botones>
					</div>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>


</xsl:stylesheet>
