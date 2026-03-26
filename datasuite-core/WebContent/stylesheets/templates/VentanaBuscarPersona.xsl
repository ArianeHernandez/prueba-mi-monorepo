<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="VENTANA_BUSCARPERSONA">
		<xsl:param name="Titulo" />
		<xsl:param name="Formulario" />
		<xsl:param name="Destino" />
		<xsl:param name="Variables" />
		<xsl:param name="PersonaNatural" />
		<javascript>templates/ventana_buscarpersona.js</javascript>

		<ventana id="vn_buscarpersona" icono="busqueda">
			<titulo>
				<xsl:value-of select="$Titulo"></xsl:value-of>
			</titulo>
			<contenido>
				<formulario id="{$Formulario}" destino="{$Destino}">
					
					<div class="alert alert-info">
						<p>Por favor, ingrese los siguientes datos de la Persona. Si ya existe una persona con estos datos el sistema le presentará la información correspondiente.</p>
					</div>
					
					<div class="form-horizontal">
					
						<xsl:if test="$PersonaNatural = ''">
							<registro>
								<item>Tipo de Persona:</item>
								<valor>
									<cajatextoselector name="tipo_persona" id="tipo_persona">
										<opcion>--Seleccione un tipo--</opcion>
										<opcion valor="J">Jurídica</opcion>
										<opcion valor="N">Natural</opcion>
									</cajatextoselector>
	
								</valor>
							</registro>
						</xsl:if>
						
						<registro>
							<item>Tipo de Documento:</item>
							<valor>
								<cajatextoselector id="tipo_documento">
								<opcion>--Seleccione un tipo--</opcion>
								</cajatextoselector>
							</valor>
						</registro>
						
						<registro>
							<item>Numero de identificacion:</item>
							<valor>
								<cajatexto id="identificacion" />
							</valor>
						</registro>
						<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajeidentificacion"></div>
					
					</div>
					
					<xsl:value-of select="Variables" />
				</formulario>
				<area_botones>
					<boton estilo="primary" validacion="validar_buscarpersona()"
						formulario="{$Formulario}">Continuar</boton>
					<boton estilo="danger" id="btn_cancelar" accion="cancelar_buscarpersona()">Cerrar</boton>
				</area_botones>
			</contenido>
		</ventana>

	</xsl:template>

</xsl:stylesheet>