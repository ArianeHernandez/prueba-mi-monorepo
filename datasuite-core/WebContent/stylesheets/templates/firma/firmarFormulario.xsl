<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="firmarFormulario">
		<xsl:param name="formularioParaFirmar"/>			<!-- obligatorio: nombre del formulario que va a firmar -->
		<xsl:param name="id_componente_firma"/>				<!-- obligatorio: id unico que tendra el componente -->
		<xsl:param name="id_carga"/>						<!-- opcional: id de la carga asociada a la transaccion -->
		<xsl:param name="nombreFaseProceso"/> 				<!-- obligatorio: nombre de la fase del proceso en la que se realiza la firma-->
		<xsl:param name="funcionJavaScript_previa"/> 		<!-- opcional: es una funcion javascript que se ejecuta antes de enviar el formulario firmado -->
		<xsl:param name="funcionJavaScript_validacion"/> 	<!-- opcional: es una funcion javascript de validacion debe devolver true or false-->
		<xsl:param name="nombre_boton"/> 					<!-- obligatorio: nombre del boton de la accion a realizar -->
		<xsl:param name="rutaBotonVolver"/> 				<!-- opcional: especifica la ruta del boton volver. Si hay ruta coloca el boton volver-->
		<xsl:param name="jsonrpc"/>		 					<!-- opcional: especifica el json a utilizar -->
		<xsl:param name="funcionJavaScript_retorno"/>		 			<!-- opcional: si exista indica que se debe llamar esa función luego de firmar en vez de hacer el submit -->
		
		<xsl:variable name="rpc">
			<xsl:choose>
				<xsl:when test="string-length($jsonrpc)>0"><xsl:value-of select="$jsonrpc"/></xsl:when>
				<xsl:otherwise>null</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:variable name="func">
			<xsl:choose>
				<xsl:when test="string-length($funcionJavaScript_retorno)>0"><xsl:value-of select="$funcionJavaScript_retorno"/></xsl:when>
				<xsl:otherwise>null</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		
		
		<javascript>firma/firmaFormulario.js</javascript>
		<javascript>firma/Data_CryptoAPI.js</javascript>
		<javascript>firma/Logic_CryptoAPI.js</javascript>

		<stylesheet>archivos_adjuntos.css</stylesheet>
		
	
		<escapar>
			<!-- Insertamos el código html-->
		    <!--Esta instrucción detecta si capicom se encuentra instalada en la máquina. Si no lo está la instala de manera automática-->
		    <OBJECT id="oCAPICOM" codeBase="http://download.microsoft.com/download/E/1/8/E18ED994-8005-4377-A7D7-0A8E13025B94/capicom.cab#version=2,0,0,3" classid="clsid:A996E48C-D3DC-4244-89F7-AFA33EC60679" 	VIEWASTEXT="VIEWASTEXT">
		    &#160;
		    </OBJECT>
		      <!-- Fin de inserción de código html-->
	    </escapar>
	  

		<bloque estilo="grupo">	
											
			<div id = "div_{$id_componente_firma}" style="display:none">
				<textarea name="inputIn_{$id_componente_firma}" cols="100" rows="10" class="combo" id="inputIn_{$id_componente_firma}"></textarea>
				<textarea name="inputOut_{$id_componente_firma}" cols="100" rows="10" class="combo" id="inputOut_{$id_componente_firma}"></textarea>
			</div>
			
			<area_botones>
				<boton estilo="primary guardar" validacion="{$funcionJavaScript_validacion}" accion="{$funcionJavaScript_previa}; crearInfoFormulario('{$formularioParaFirmar}', 'inputIn_{$id_componente_firma}', 'inputOut_{$id_componente_firma}', '{$id_carga}', '{$nombreFaseProceso}', {$rpc} , {$func})"><xsl:value-of select="$nombre_boton"></xsl:value-of></boton>
				
				<xsl:if test="$rutaBotonVolver!=''">
					<boton estilo="primary volver" destino="{$rutaBotonVolver}">Volver</boton>
				</xsl:if>
				
			</area_botones>
			
		</bloque>

			

	</xsl:template>
	
	<xsl:template name="firmarFormularioBoton">
		<xsl:param name="formularioParaFirmar"/>			<!-- obligatorio: nombre del formulario que va a firmar -->
		<xsl:param name="id_componente_firma"/>				<!-- obligatorio: id unico que tendra el componente -->
		<xsl:param name="id_carga"/>						<!-- opcional: id de la carga asociada a la transaccion -->
		<xsl:param name="nombreFaseProceso"/> 				<!-- obligatorio: nombre de la fase del proceso en la que se realiza la firma-->
		<xsl:param name="funcionJavaScript_previa"/> 		<!-- opcional: es una funcion javascript que se ejecuta antes de enviar el formulario firmado -->
		<xsl:param name="funcionJavaScript_validacion"/> 	<!-- opcional: es una funcion javascript de validacion debe devolver true or false-->
		<xsl:param name="nombre_boton"/> 					<!-- obligatorio: nombre del boton de la accion a realizar -->
		<xsl:param name="jsonrpc"/>		 					<!-- opcional: especifica el json a utilizar-->
		<xsl:param name="funcionJavaScript_retorno"/>		<!-- opcional: si exista indica que se debe llamar esa función luego de firmar en vez de hacer el submit -->
		
		<xsl:variable name="rpc">
			<xsl:choose>
				<xsl:when test="string-length($jsonrpc)>0"><xsl:value-of select="$jsonrpc"/></xsl:when>
				<xsl:otherwise>null</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:variable name="func">
			<xsl:choose>
				<xsl:when test="string-length($funcionJavaScript_retorno)>0"><xsl:value-of select="$funcionJavaScript_retorno"/></xsl:when>
				<xsl:otherwise>null</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<javascript>firma/firmaFormulario.js</javascript>
		<javascript>firma/Data_CryptoAPI.js</javascript>
		<javascript>firma/Logic_CryptoAPI.js</javascript>

		<stylesheet>archivos_adjuntos.css</stylesheet>
		
	
		<escapar>
			<!-- Insertamos el código html-->
		    <!--Esta instrucción detecta si capicom se encuentra instalada en la máquina. Si no lo está la instala de manera automática-->
		    <OBJECT id="oCAPICOM" codeBase="http://download.microsoft.com/download/E/1/8/E18ED994-8005-4377-A7D7-0A8E13025B94/capicom.cab#version=2,0,0,3" classid="clsid:A996E48C-D3DC-4244-89F7-AFA33EC60679" 	VIEWASTEXT="VIEWASTEXT">
		    &#160;
		    </OBJECT>
		      <!-- Fin de inserción de código html-->
	    </escapar>
											
			<div id = "div_{$id_componente_firma}" style="display:none">
				<textarea name="inputIn_{$id_componente_firma}" cols="100" rows="10" class="combo" id="inputIn_{$id_componente_firma}"></textarea>
				<textarea name="inputOut_{$id_componente_firma}" cols="100" rows="10" class="combo" id="inputOut_{$id_componente_firma}"></textarea>
			</div>
			
			<boton estilo="primary guardar" validacion="{$funcionJavaScript_validacion}" accion="{$funcionJavaScript_previa}; crearInfoFormulario('{$formularioParaFirmar}', 'inputIn_{$id_componente_firma}', 'inputOut_{$id_componente_firma}', '{$id_carga}', '{$nombreFaseProceso}', {$rpc} , {$func})"><xsl:value-of select="$nombre_boton"></xsl:value-of></boton>

	</xsl:template>


</xsl:stylesheet>