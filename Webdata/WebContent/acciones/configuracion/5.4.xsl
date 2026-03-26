<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Edición de Clave">
		
			<javascript>configuracion/5.1.js</javascript>
			<javascript>jsbn/jsbn.js</javascript>
			<javascript>jsbn/rng.js</javascript>
			<javascript>jsbn/rsa.js</javascript>
			<javascript>jsbn/base64.js</javascript>
			<javascript>jsbn/secure.js</javascript>
			
			<principal>
				<titulo icono="usuarios">Edicion de Usuario</titulo>
				<contenido>
					<div class="box-container">
					<bloque-pestanas>
					
						<pestana titulo="Cambio de Contraseña">
						
							<div class="col-sm-6 form-horizontal">
								<xsl:if test="//credencial_usuario/cambiar_clave = 'S'">
									<nota>Por seguridad, es necesario que realice el cambio de clave antes de iniciar cualquier operación.</nota>
								</xsl:if>
								
								<formulario id="form_edicion" destino="configuracion/5.2.do">
									<variable id="Accion" valor="cambioContraseña"/>
									
									<componente_clave_ingreso claveingreso="true" nuevaclaveingreso="true" confirmacionclave="true"/>
								</formulario>	
								
								
								<area_botones>
									<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">
										<i class="fa fa-floppy-o" aria-hidden="true"/>&#160;Cambiar Clave
									</boton>
								</area_botones>
								<div class="clearfix"></div>
							</div>
						</pestana>
						
					</bloque-pestanas>
					<div class="clearfix"></div>
					</div>
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
