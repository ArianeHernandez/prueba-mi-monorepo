<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			<javascript>publico/activar_cuenta.js</javascript>
			<stylesheet>estilos/index.css</stylesheet>
			
			<enlaces_encabezado>
				<enlace>Ayuda</enlace>
				<enlace>Salida Segura</enlace>
			</enlaces_encabezado>
		
			<menu_lateral>
				<titulo>Bienvenido</titulo>
				<enlace icono="inicio" destino="">Home</enlace>
			</menu_lateral>
		
			<menu_lateral>
				<titulo>Hora del Sistema</titulo>
				<informacion_sistema />
			</menu_lateral>
		
			<principal>
				<titulo icono="inicio">Bienvenido al Módulo de Insolvencia</titulo>
				<contenido>
					
					<bloque-contenido>
						<titulo>Confirmación crear contraseña</titulo>
						<contenido>
				
							<xsl:if test="//definirClave/Respuesta='0'">
								<parrafo estilo="resaltado">
									Su contraseña se creó con éxito.
								</parrafo>
								<xsl:choose>
									<xsl:when test="//confirmarCreacionUsuarios/Boolean='true'">
										<parrafo>
											Su contraseña deberá ser activada por el administrador
										</parrafo>
									</xsl:when>
									<xsl:otherwise>
										<xsl:choose>
											<xsl:when test="//esLoginPorIdentificacion/Boolean='true' and string-length(id_cliente) > 0">
												<div class='div-center'>
													<a onclick="osm_go('inicio/0.pub')" class="btn btn-primary" >Ingresar</a>
												</div>
											</xsl:when>
											<xsl:otherwise>
												<div class='div-center'>
													<a onclick="osm_go('inicio/0.pub')" class="btn btn-primary" >Ingresar</a>
												</div>
											</xsl:otherwise>
										</xsl:choose>
										
									</xsl:otherwise>
								</xsl:choose>
							</xsl:if>
							
							<xsl:if test="//definirClave/Respuesta='1'">
								<parrafo estilo="resaltado">
									Error al crear la contraseña
								</parrafo>
							</xsl:if>
							
							<xsl:if test="//definirClave/Respuesta='2'">
								<parrafo estilo="resaltado">
									El estado del usuario no es válido, comuniquese con el administrador.
								</parrafo>
							</xsl:if>
							
							<xsl:if test="//definirClave/Respuesta='3'">
								<parrafo estilo="resaltado">
									No es posible cambiar la clave.
								</parrafo>
							</xsl:if>
							
							<xsl:if test="//definirClave/Respuesta='4'">
								<parrafo estilo="resaltado">
									No esta autorizado.
								</parrafo>
							</xsl:if>
							
							<xsl:if test="//definirClave/Respuesta='4'">
								<parrafo estilo="resaltado">
									No se puede actualizar la información del usuario.
								</parrafo>
							</xsl:if>
									
						</contenido>
					</bloque-contenido>
			
				</contenido>
			</principal>
			
			<enlaces_pie>
				<enlace>inicio</enlace>
				<enlace>mapa del sitio</enlace>
				<enlace>condiciones de uso</enlace>
				<enlace>preguntas frecuentes</enlace>
				<enlace>aviso legal</enlace>
			</enlaces_pie>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
