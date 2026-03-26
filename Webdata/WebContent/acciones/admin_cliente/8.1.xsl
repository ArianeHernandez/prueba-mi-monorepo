<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoPersonas.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaBuscarPersona.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Crear Administrador de Cliente">
			
			<javascript>webdata/8.1.js</javascript>
			
			<principal>
				<titulo>Crear Administrador de Cliente</titulo>
				<contenido>
					<div class="box-container">
						<div class="panel panel-default">
	 	 						<div class="panel-heading">Edición</div>
						  
						  		<div class="panel-body">
						  		
						  			<div class=" form-horizontal">						
									<xsl:call-template name="LISTADOCLIENTES">
										<xsl:with-param name="Destino">admin_cliente/8.1.do</xsl:with-param>
										<xsl:with-param name="Usuario" select="//obtenerUsuario/Usuario"  />
										<xsl:with-param name="UsuarioActual" select="//id_usuario_actual"/>
										<xsl:with-param name="tipo_cliente" select="'J'"/>									
									</xsl:call-template>
									
									<div class="row-box">
									<xsl:call-template name="LISTADOPERSONAS">
										<xsl:with-param name="Persona" select="//obtenerPersonasUsuario/Listado/Persona"  />
										<xsl:with-param name="id_usuario" select="//id_usuario_actual"/>
										<xsl:with-param name="rol">AC</xsl:with-param>
										<xsl:with-param name="Destino">admin_cliente/8.2.do</xsl:with-param>
										<xsl:with-param name="total" select="//contarPersonasUsuario/Total"/>
										<xsl:with-param name="DestinoPaginacion">admin_cliente/8.1.do</xsl:with-param>
										<xsl:with-param name="MostrarSobreflex">
											<xsl:choose>
											<xsl:when test="//usosobreflex='true'">S</xsl:when>
											<xsl:otherwise>N</xsl:otherwise>
											</xsl:choose>
										</xsl:with-param>
										
									</xsl:call-template>
									</div>
									
									
								
							</div>
						</div>
						<div class="panel-footer">
							
							<boton estilo="default inicio" destino="inicio/0.do">Ir al Inicio</boton>
							<boton estilo="primary" accion="crearAdministrativoCliente();" >Crear Administrador Cliente</boton>
						</div>
					</div>
				</div>							
				</contenido>
			
			</principal>
			
			<xsl:call-template name="VENTANA_BUSCARPERSONA">
					<xsl:with-param name="Titulo">Crear Administrador Cliente</xsl:with-param>
					<xsl:with-param name="Formulario">CrearAdminCliente</xsl:with-param>
					<xsl:with-param name="Destino">admin_cliente/8.2.do</xsl:with-param>
					<xsl:with-param name="Variables">
						<variable id="id_negocio" valor="{//id_negocio_actual}" />
					</xsl:with-param>
					<xsl:with-param name="PersonaNatural" >SI</xsl:with-param>
				</xsl:call-template>
			<xsl:call-template name="LISTADOPERSONAS_VENTANAS" />
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
