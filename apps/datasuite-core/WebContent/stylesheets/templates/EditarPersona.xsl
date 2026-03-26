<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="EDITARPERSONA">
	
		<xsl:param name="Persona" />
		<xsl:param name="TipoPersona" />
		<xsl:param name="TipoDocumento" />
		<xsl:param name="Identificacion" />
		<xsl:param name="Credencial" />
		<xsl:param name="id_usuario" />
		<xsl:param name="editar" />
		<xsl:param name="rol" />
		<xsl:param name="LoginPorIdentificacion" />
		<xsl:param name="login_fijo" />
		
		
		<xsl:if test="$Identificacion != '' and $TipoPersona != '' or $editar='SI'">
		<javascript>templates/EditarPersona.js</javascript>
		<javascript>publico/custom_common_formns/commonResoursesCustomForms.js</javascript>
			
			<bloque-contenido>
				<titulo icono="edicion">Información Básica</titulo>
				<contenido>
					<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
					<div class="alert  alert-info">
						<i class="fa fa-info-circle" aria-hidden="true"></i>
						<span class="sr-only">Info:</span>
						Edita la información del usuario.
					</div>
					
					<xsl:if test="$LoginPorIdentificacion='false' and count($Credencial)=0">
						<div class="alert alert-info"><i class="fa fa-info-circle" aria-hidden="true"></i> Para no crear el usuario de acceso al sistema deje  el campo login en blanco</div>	
					</xsl:if>
					<xsl:if test="$LoginPorIdentificacion='true' and count($Credencial)=0">
						<div class="alert alert-info"><i class="fa fa-info-circle" aria-hidden="true"></i> Para crear el usuario de acceso al sistema deje seleccionada la opción Crear Usuario</div>	
					</xsl:if>
					<registro>
						<item>Nombre*:</item>
						<valor>
							<cajatexto id="Persona.nombre" valor="{$Persona/nombre}"/>
						</valor>
					</registro>
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonanombre"></div>
	
					<xsl:if test="$TipoPersona = 'N'">
						<registro>
							<item>Apellidos*:</item>
							<valor>
								<cajatexto id="Persona.apellido" valor="{$Persona/apellido}"/>
							</valor>
						</registro>
						<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonaapellido"></div>
					</xsl:if>
	
					<xsl:if test="$LoginPorIdentificacion='true' and count($Credencial)=0 and $rol='C'">
						<registro>
							<item>Crear Usuario:</item>
							<valor>
								<cajachequeo seleccionado="true" id="crear_login" accion="$('#reg_login').toggle()">
									<xsl:if test="$editar='SI'">
										<xsl:attribute name="seleccionado">false</xsl:attribute>
									</xsl:if>
								</cajachequeo>
							</valor>
						</registro>
					</xsl:if>
					
					<registro id="reg_login">
						<xsl:if test="count($Credencial)=0 and $rol='C' and $editar='SI' and $LoginPorIdentificacion='true'">
							<xsl:attribute name="visible">false</xsl:attribute>
						</xsl:if>
						<item>Login:</item>
						<valor>
							<xsl:choose>
								<xsl:when test="(count($Credencial)>0)">
									<parrafo>
										<xsl:value-of select="$Credencial/login" />
									</parrafo>
									<variable id="Credencial.login" valor="{$Credencial/login}" />
								</xsl:when>
								<xsl:when test="$LoginPorIdentificacion='true'">
									<parrafo>
										<xsl:value-of select="concat($TipoDocumento/prefijo, $Identificacion)" />
									</parrafo>
									<variable id="Credencial.login" valor="{concat($TipoDocumento/prefijo, $Identificacion)}" />
								</xsl:when>
								<xsl:otherwise>
									<cajatexto id="Credencial.login" valor="{$login_fijo}">
										<xsl:if test="string-length($login_fijo)>0">
											<xsl:attribute name="desactivado">true</xsl:attribute>	
										</xsl:if>
									</cajatexto>
								</xsl:otherwise>
							</xsl:choose>
							<variable id="Credencial.id_persona" valor="{$Credencial/id_persona}" />
						</valor>
					</registro>
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajelogin"></div>
					<variable nombre="id_usuario" valor="{$id_usuario}"/>
					
					<registro>
						<item>Tipo de Persona:</item>
						<valor>
							<parrafo>
								<xsl:choose>
									<xsl:when test="$TipoPersona = 'N' ">Natural</xsl:when>
									<xsl:otherwise>Jurídica</xsl:otherwise>
								</xsl:choose>
							</parrafo>
							<variable id="Persona.tipo_persona" valor="{$TipoPersona}" />
						</valor>
					</registro>
					<registro>
						<item>Tipo de Documento:</item>
						<valor>
							<parrafo>
								<xsl:value-of select="$TipoDocumento/nombre"></xsl:value-of>
							</parrafo>
							<variable id="Persona.tipo_documento" valor="{$TipoDocumento/id}"/>
						</valor>
					</registro>
					<registro>
						<item>Identificación:</item>
						<valor>
							<parrafo>
								<xsl:value-of select="$Identificacion" />
							</parrafo>
							<variable id="Persona.identificacion" valor="{$Identificacion}" />
						</valor>
					</registro>
					<registro>
						<item>Teléfono Celular*:</item>
						<valor>
							<cajatexto id="Persona.telefono" valor="{$Persona/telefono}"/>
						</valor>
					</registro>
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonatelefono"></div>
					
					<registro>
						<item>Correo Electrónico*:</item>
						<valor>
							<cajatexto id="Persona.correo" valor="{$Persona/correo}"/>
						</valor>
					</registro>
					<registro>
						<item>Confrirmar Correo Electrónico*:</item>
						<valor>
							<cajatexto id="Persona.confirmar_correo" valor="{$Persona/correo}"/>
						</valor>
					</registro>
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonacorreo"></div>
					
					<registro>
						<item>Dirección*:</item>
						<valor>
							<cajatexto id="Persona.direccion" valor="{$Persona/direccion}"/>
						</valor>
					</registro>
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonadireccion"></div>
					
					<registro>
					    <item>País:</item>
					    <valor>
					        <parrafo>
					            <xsl:choose>
					                <xsl:when test="$TipoDocumento/id = '1' or $TipoDocumento/id = '10'">Colombia</xsl:when>
					                <xsl:otherwise>Sin País</xsl:otherwise>
					            </xsl:choose>
					        </parrafo>
					        <variable id="Persona.pais_id">
					            <xsl:attribute name="valor">
					                <xsl:choose>
					                    <xsl:when test="$TipoDocumento/id = '1' or $TipoDocumento/id = '10'">15385397</xsl:when>
					                    <xsl:otherwise>15385397</xsl:otherwise>
					                </xsl:choose>
					            </xsl:attribute>
					        </variable>
					    </valor>
					</registro>
					<registro>
					    <item>Departamento*:</item>
					    <valor>
					        <xsl:choose>
					            <xsl:when test="$TipoDocumento/id = '1' or $TipoDocumento/id = '10'">
					                <select class="form-control"
					                        id="deudor_departamento"
					                        ng-model="solicitud.departamento_dane"> <option value="">Seleccione</option>
					                </select>
					                <input type="hidden" id="Persona.departamento_id" name="Persona.departamento_id" value="{$Persona/departamento_id}"/> 
					            </xsl:when>
					            <xsl:otherwise>
					                <parrafo>Sin Departamento</parrafo>
					                <input type="hidden" id="Persona.departamento_id" name="Persona.departamento_id" value="15224090"/>
					            </xsl:otherwise>
					        </xsl:choose>
					    </valor>
					</registro>				
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonadepartamento"></div>
					
					<registro>
					    <item>Municipio*:</item>
					    <valor>
					        <xsl:choose>
					            <xsl:when test="$TipoDocumento/id = '1' or $TipoDocumento/id = '10'">
					                <select class="form-control"
					                        id="deudor_ciudad"
					                        ng-model="solicitud.municipio_dane"
					                        disabled="true">
					                    <option value="">Seleccione</option>
					                </select>
					                <input type="hidden" id="Persona.municipio_id" name="Persona.municipio_id" value="{$Persona/municipio_id}"/>
					            </xsl:when>
					            <xsl:otherwise>
					                <parrafo>Sin Municipio</parrafo>
					                <input type="hidden" id="Persona.municipio_id" name="Persona.municipio_id" value="15224091"/>
					            </xsl:otherwise>
					        </xsl:choose>
					    </valor>
					</registro>					
					<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajepersonamunicipio"></div>
										
<!-- 					<xsl:if test="$TipoPersona = 'N'"> -->
<!-- 						<registro> -->
<!-- 							<item>Género*:</item> -->
<!-- 							<valor> -->
<!-- 								<cajatextoselector id="Persona.genero" valor="{$Persona/genero}"> -->
<!-- 									<opcion valor="M" texto="Masculino" /> -->
<!-- 									<opcion valor="F" texto="Femenino" /> -->
<!-- 								</cajatextoselector> -->
<!-- 							</valor> -->
<!-- 						</registro> -->
<!-- 					</xsl:if> -->
					
					<variable id="Persona.id_persona" valor="{$Persona/id_persona}" />
					</div>
				</contenido>
			</bloque-contenido>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>