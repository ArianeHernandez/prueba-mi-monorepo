<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="INFORMACION_FIRMA_DIGITAL_CARGA">
		
		<stylesheet>firma/firmaDigital.css</stylesheet>
	
	
				<!-- INFO FIRMA -->
		
				<xsl:choose>
					<xsl:when test="//tieneFormularioFirmadoPorFasePreparacion/respuesta='true' or 
							//tieneFormularioFirmadoPorFaseLiberacion/respuesta='true' or
							//tieneDocumentoFirmadoPorFasePreparacion/respuesta='true'">
							
						<bloque-contenido>
						<titulo>Firma Digital</titulo>
						<contenido>	
					
							<xsl:if test="//tieneFormularioFirmadoPorFasePreparacion/respuesta='true' or 
									//tieneFormularioFirmadoPorFaseLiberacion/respuesta='true'">
								
								
								<div class="formulario_firmado">
									<div>
							
										<xsl:if test="//tieneFormularioFirmadoPorFasePreparacion/respuesta='true' ">
											<br/> - la aprobación de la fase de <b> PREPARACIÓN </b> presenta firma digital 
										</xsl:if>
									
										<xsl:if test="//tieneFormularioFirmadoPorFaseLiberacion/respuesta='true' ">
											<br/> - la aprobación de la fase de <b> LIBERACIÓN</b> presenta firma digital 
										</xsl:if>
										
									</div>
								</div>
								
							</xsl:if>
							<xsl:if test="//tieneDocumentoFirmadoPorFasePreparacion/respuesta='true'">
								<div class="documento_firmado">
									<div >
										<xsl:if test="//tieneDocumentoFirmadoPorFasePreparacion/respuesta='true' ">
											<br/> - El documento cargado en la fase de <b> PREPARACIÓN </b> presenta firma digital 
										</xsl:if>
									
									</div>
									
								</div>
							</xsl:if>
						
						</contenido>	
						</bloque-contenido>
						<div class="separador">&#160;</div>	
					</xsl:when>
		
				</xsl:choose>
			
		
		
		
	</xsl:template>


</xsl:stylesheet>