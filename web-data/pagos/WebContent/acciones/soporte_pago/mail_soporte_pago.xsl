<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

    <xsl:template match="OSM-ACCION">
    	
        <pagina_simple>

		<javascript>soporte_pago/mail_soporte_pago.js</javascript>        
        <stylesheet>index_simple.css</stylesheet>
        	
        	<xsl:variable name="sp" select="obtenerSoportePago/SoportePago"/>

			<div>
				
				<bloque-contenido>
					<titulo>Correo Electrónico</titulo>
					<contenido>
						
						<registro>
							<item>Para:</item>
							<valor>
								<cajatexto class="" id="para" valor="{//OSM-INIT-SESSION/Info/Persona/correo}"/>	
							</valor>
					 	</registro>
						
						<registro>
							<item>Asunto:</item>
							<valor>
								<cajatexto class="" id="asunto" valor="Soporte de Pago"/>	
							</valor>
					 	</registro>
						
						<registro>
							<item>Mensaje:</item>
							<valor>
								<areatexto class="" id="mensaje" valor="Archivo Adjunto."/>	
							</valor>
					 	</registro>
						
						<div id="boton_envio">
						<area_botones>
							<boton class='crear' accion="enviarCorreo({$sp/idRetiro})">Enviar</boton>
						</area_botones>
						</div>
						
						<div id="nota_envio" style="display:none">
						<nota>Enviando el correo...</nota>
						</div>
						
					</contenido>
				</bloque-contenido>
				

			</div>	
		
		</pagina_simple>
		
    </xsl:template>

</xsl:stylesheet>
