<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="formato" select="//obtenerFormato/Formato" />
		
		<pagina titulo="Carga de Información">
			
			<javascript>carga_informacion/1a.js</javascript>
			
			<principal>
				<titulo>Carga de Información</titulo>
				<contenido>
					<div class="box-container">
					<div class="panel panel-default">
						<div class="panel-heading">Carga</div>
						<div class="panel-body">
							<div class=" form-horizontal">
								<xsl:choose>
									<xsl:when test="count($formato/*)>0">
										<formulario id="form_formato">
											<variable id="id_formato" valor="{$formato/id_formato}" />
										</formulario>
									</xsl:when>
									<xsl:otherwise>
										<div class="alert alert-danger">
											<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
			 								<span class="sr-only">Error:</span>
											<p>No existen formatos asociados a su usuario..</p>
										</div>
									</xsl:otherwise>
									
								</xsl:choose>
								
								<xsl:if test="count($formato/*)>0" >
									<div class="alert  alert-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										<span class="sr-only">Info:</span>
										<p>Seleccione el tipo de <texto key="CARGA" /> que desea Realizar.</p>
									</div>
								</xsl:if>
							</div>
						</div>
						<div class="panel-footer">
							<boton estilo="inicio" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Ir al Inicio</boton>
							<xsl:if test="count($formato/*)>0" >
								<xsl:if test="$formato/medio_formulario = 'S'">
									<boton estilo="aceptar" accion="enviarFormato('I')"><i class="fa fa-file-o" aria-hidden="true"></i>&#160;Individual</boton>
								</xsl:if>
								<xsl:if test="$formato/medio_excel = 'S'">
									<boton estilo="aceptar"  accion="enviarFormato('L')"><i class="fa fa-files-o" aria-hidden="true"></i>&#160;Masiva</boton>
								</xsl:if>
							</xsl:if>
																		
						</div>	
					</div>	
					</div>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
