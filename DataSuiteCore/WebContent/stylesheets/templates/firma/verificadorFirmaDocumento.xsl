<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="verificadorFirmaDocumento">
		<xsl:param name="formularioOrigen"/>		<!-- obligatorio: nombre del formulario origen de la informacion-->
		<xsl:param name="nombreFaseProceso"/> 		<!-- obligatorio: nombre de la fase del proceso en la que se realiza la verificacion del documento-->
		<xsl:param name="rutaBotonVolver"/> 		<!-- opcional: especifica la ruta del boton volver. Si hya ruta coloca el boton volver-->
		
		<javascript>firma/firmaDocumento.js</javascript>

		<stylesheet>archivos_adjuntos.css</stylesheet>

			<div id="verificadorFirmaDocumento" class="" style="display: block; width: 100%;">
					<form action="{//CONTEXT_PATH}/archivo.uploadDocumentSigned" method="post"
						enctype="multipart/form-data" onsubmit="return false;" id="form_verificar_firma_documento"
						name="form_verificar_firma_documento">
						<input type="hidden" id="id_carga" name="id_carga"
							value="{//id_carga_seleccionada}" />
						<input type="hidden" id="id_archivo_firmado" name="id_archivo_firmado" />
						
						<input type="hidden" id="formularioOrigen" name="formularioOrigen" value ="{$formularioOrigen}"/>
						
						<input type="hidden" id="nombreFaseProceso" name="nombreFaseProceso" value ="{$nombreFaseProceso}"/>
						
						<div class="bloque_contenido_contenido">
							Archivo firmado: <input class="form-control" type="file" value="" id="caja_archivo_firmado" name="caja_archivo_firmado"></input>
						</div>
						
					</form>
					
					<div class="area_botones" id="area_botones_verificar_firma">
						<a class="boton icono24_excel" id="btn_verificar_archivo_firmado" onclick="verificarArchivoFirmado()">Verificar y subir archivo firmado</a>
						
						<xsl:if test="$rutaBotonVolver!=''">
							<boton estilo="primary volver" destino="{$rutaBotonVolver}">Volver</boton>
						</xsl:if>
					</div>
					
					
					<form action="" method="post" id="formularioDestino">
						<input type="hidden" id="IDPXFRM" name="IDPXFRM"/>
						<input type="hidden" id="IDF" name="IDF"/>
						
					</form>
					
			</div>

			

	</xsl:template>


</xsl:stylesheet>