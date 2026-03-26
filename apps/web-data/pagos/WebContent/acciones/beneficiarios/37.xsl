<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Desactivar Beneficiarios">

			<javascript>beneficiarios/37.js</javascript>

			<principal>
				<titulo>Desactivar Beneficiarios</titulo>
				<contenido>
					
					<parrafo>
						Seleccione los beneficiarios que desea borrar.
					</parrafo>
					
					
						<tabla>
							<encabezado>
								<titulo>Tipo Documento</titulo>
								<titulo>Nro. Documento</titulo>
								<titulo>Nombres</titulo>
								<titulo>Apellidos</titulo>
								<titulo>Banco</titulo>
								<titulo>Tipo Cuenta</titulo>
								<titulo>Nro. Cuenta</titulo>
								<titulo></titulo>
							</encabezado>
							<xsl:for-each select="//obtenerBeneficiariosCliente/Listado/HashMap">
								<fila id="fila_{ID}">
									<valor><xsl:value-of select="TIPO_DOCUMENTO"/></valor>
									<valor><xsl:value-of select="NUMERO_DOCUMENTO"/></valor>
									<valor><xsl:value-of select="concat(PRIMER_NOMBRE, ' ', SEGUNDO_NOMBRE)"/></valor>
									<valor><xsl:value-of select="concat(PRIMER_APELLIDO, ' ', SEGUNDO_APELLIDO)"/></valor>
									<valor><xsl:value-of select="BANCO"/></valor>
									<valor><xsl:value-of select="TIPO_CUENTA"/></valor>
									<valor><xsl:value-of select="NUMERO_CUENTA"/></valor>
									<valor><boton accion="eliminarBeneficiario('{ID}')">Eliminar</boton></valor>
								</fila>
							</xsl:for-each>
						</tabla>
						
					<formulario destino="beneficiarios/37.do" id="form_beneficiarios">
						<variable id="numero_pagina" valor="{numeroPagina}" />
						<paginacion id="_numeropagina_beneficiarios" formulario="form_beneficiarios"
									numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}"
									total="{contarBeneficiariosCliente/total}" />
					</formulario>
				
					<area_botones>
						<boton estilo="danger" destino="inicio/0.do">Cancelar</boton>
					</area_botones>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>
	
</xsl:stylesheet>
