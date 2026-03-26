<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Registro">
			<javascript>webdata/1.1.1.js</javascript>
			<javascript>solicitud/redireccionLiberacion.js</javascript>
			
			<principal>
				<titulo><b>PASO 1 de 3: </b>Cargue de <xsl:value-of select="obtenerFormato/Formato/nombre"/> - <xsl:value-of select="obtenerCarga/Carga/id_carga"/></titulo>
				<xsl:variable name="idcarga" select="obtenerCarga/Carga/id_carga"/>
				<contenido>
				<div class="box-container">
					<div class="panel panel-default">
						<div class="panel-heading">Carga</div>
						<div class="panel-body">
							<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
					
								<xsl:for-each select="//eliminarElementoCarga">
									<xsl:choose>
										<xsl:when test="exitoso = 'true'">
											<parrafo estilo="danger"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i><texto key="LA CARGA SE ELIMINO" /></parrafo>
										</xsl:when>
										<xsl:otherwise>
											<alerta>No se pudo eliminar la carga.</alerta>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
								
								<xsl:for-each select="//crearYEnviarElementoCarga">
									<xsl:choose>
										<xsl:when test="exitoso = 'true'">
											<div class="alert alert-info"><texto key="LA CARGA SE ENVIO CORRECTAMENTE" valor="{$idcarga}"/></div>
										</xsl:when>
										<xsl:otherwise>
											<alerta>No se pudo realizar la carga.</alerta>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
							</div>
						</div>
								
						<div class="panel-footer">
							<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
						</div>
					</div>
				
				</div>	
				
				<xsl:if test="string-length(//OSM-INIT-SESSION/obtenerOsmoAutenticadorRol/OsmoAutenticaRol/TipoElementoSalidalistarRolesporLogin[id_rol=6])>0">
					<formulario id="form_carga" destino="liberacion/15.3.do">
						<variable id="id_carga" valor="{//obtenerCarga/Carga/id_carga}"/>
						<variable id="id_persona" valor="{//obtenerCarga/Carga/id_persona}" />
						<variable id="id_formato_entrada" valor="{//obtenerCarga/Carga/id_formato}" />
					</formulario>						
				</xsl:if>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
