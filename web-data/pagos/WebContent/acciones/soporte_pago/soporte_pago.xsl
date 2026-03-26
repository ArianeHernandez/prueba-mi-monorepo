<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

    <xsl:template match="OSM-ACCION">
    	
        <pagina_simple>
        
        <stylesheet>tbreport.css</stylesheet>
        	
        	<xsl:variable name="sp" select="obtenerSoportePago/SoportePago"/>

			<div id="CONTENIDO_HTML" class='boxreport' style="overflow:auto">
				<div class="topreport"><img class="imgreport" src="images/general/logo.png" /></div>
				
				<h1>REPORTE DE ESTADO DEL PAGO</h1>

				<table class="tbreport">
					<tr>
						<td class="h2" id='estado_pago'><b>ESTADO DEL PAGO</b></td>
						<td class="ctr" id='estado_pago_val'><xsl:value-of select="$sp/estadoPago"/></td>
					</tr>
				</table>
			
				<h2 id='titulo_originador'>DATOS DEL ORIGINADOR</h2>
				
				<table class="tbreport">
					<tr>
						<td><b>NOMBRE</b></td>
						<td id='nombre'><xsl:value-of select="$sp/nombreOriginador"/></td>
					</tr>
					<tr class="par">
						<td><b>FECHA</b></td>
						<td id='fecha'><xsl:value-of select="$sp/fecha"/></td>
					</tr>
					<tr>
						<td><b>CONCEPTO DE PAGO</b></td>
						<td id='concepto'><xsl:value-of select="$sp/concepto"/></td>
					</tr>
					<tr class="par">
						<td><b>MONTO</b></td>
						<td id='monto'><xsl:value-of select="$sp/monto"/></td>
					</tr>
				</table>
				
				<h2 id='titulo_destinatario'>DATOS DEL DESTINATARIO</h2>
				
				<table class="tbreport">
					<tr>
						<td><b>NOMBRE</b></td>
						<td id='nombre_destinatario'><xsl:value-of select="$sp/nombreDestinatario"/></td>
					</tr>
					<tr class="par"> 
						<td><b>NUMERO DE IDENTIFICACION</b></td>
						<td id='numero_id'><xsl:value-of select="$sp/identificacionDestinatario"/></td>
					</tr>
					<tr>
						<td><b>BANCO DE DESTINO</b></td>
						<td id='banco_destino'><xsl:value-of select="$sp/bancoDestino"/></td>
					</tr>
					<tr class="par">
						<td><b>TIPO DE CUENTA</b></td>
						<td id='tipo_cuenta'><xsl:value-of select="$sp/tipoCuenta"/></td>
					</tr>
					<tr>
						<td><b>CUENTA DE DESTINO</b></td>
						<td id='cuenta_destino'><xsl:value-of select="$sp/cuentaDestino"/></td>
					</tr>
				</table>
				
				<p class="footer">
					<texto key="TEXTO SOPORTE DE PAGO" />
				</p>
				
			</div>	
		
		</pagina_simple>
		
		
	
    </xsl:template>

</xsl:stylesheet>
