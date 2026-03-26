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
			
			<principal>
				<titulo icono="usuarios">Edicion de Usuario</titulo>
				<contenido>
				<div class="box-container">	
					<bloque-pestanas>
					
						<xsl:if test="not(//credencial_usuario/cambiar_clave = 'S')">
							<pestana titulo="Informacion Persona">
								<div class=" form-horizontal">
								<formulario id="form_informacion" destino="configuracion/5.2.do">
									<variable id="AccionI" valor="guardarInfoPersonal"/>
									
									<registro>
										<item>Usuario</item>
										<valor>
											<input type="hidden" id="login_user" value="{//LOGIN_USER}"/>
											<div class="form-control"><xsl:value-of select="//LOGIN_USER"/></div>
										</valor>
									</registro>
									
									<registro>
										<item>Nombre *</item>
										<valor>
											<cajatexto id="Persona.nombre" valor="{//obtenerPersona/Persona/nombre}" />
										</valor>
									</registro>
									
									<registro>
										<item>Apellido *</item>
										<valor>
											<cajatexto id="Persona.apellido" valor="{//obtenerPersona/Persona/apellido}" />
										</valor>
									</registro>
									
									<registro>
										<item>Identificación</item>
										<valor>
											<div class="form-control"><xsl:value-of select="//obtenerPersona/Persona/identificacion"/></div>
										</valor>
									</registro>
									
									<registro>
										<item>Correo *</item>
										<valor>
											<cajatexto id="Persona.correo" valor="{//obtenerPersona/Persona/correo}" />
										</valor>
									</registro>
									
									<registro>
										<item>Telefono</item>
										<valor>
											<cajatexto id="Persona.telefono" valor="{//obtenerPersona/Persona/telefono}" />
										</valor>
									</registro>
									
									<registro>
										<item>Dirección</item>
										<valor>
											<cajatexto id="Persona.direccion" valor="{//obtenerPersona/Persona/direccion}" />
										</valor>
									</registro>
									
									<registro>
										<item>Genero</item>
										<valor>
											<cajatextoselector id="Persona.genero" valor="{//obtenerPersona/Persona/genero}">
												<opcion valor="M" texto="Masculino"/>
												<opcion valor="F" texto="Femenino"/>
											</cajatextoselector>
										</valor>
									</registro>
									
									<xsl:if	test="//obtenerLiberadorPorIdentificacion/Liberador">
									<registro>
										<item>Peso</item>
										<valor>
											<parrafo><xsl:value-of select="//liberadorTipoProceso/peso"/></parrafo>
										</valor>
									</registro>
									
									<registro>
										<item>Valor Máximo Individuales</item>
										<valor>
											<parrafo id="valMaxIndv"><xsl:value-of select="//liberadorTipoProceso/valor_max_individual"/></parrafo>
										</valor>
									</registro>
									
									<registro>
										<item>Valor Máximo Carga</item>
										<valor>
											<parrafo id="valMaxCarga"><xsl:value-of select="//liberadorTipoProceso/valor_max_carga"/></parrafo>
										</valor>
									</registro>
									</xsl:if>
									
								</formulario>	
								</div>
		
								<area_botones>
									<boton estilo="primary guardar" formulario="form_informacion" validacion="page_validarInfoGuardar()"> <i class="fa fa-floppy-o" aria-hidden="true"></i> Guardar Información</boton>
								</area_botones>
								
							</pestana>
						
						</xsl:if>
						
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
									<boton estilo="primary guardar" formulario="form_edicion" validacion="page_validarGuardar()">Cambiar Clave</boton>
								</area_botones>
							</div>
						</pestana>
						
					</bloque-pestanas>
				</div>	
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
