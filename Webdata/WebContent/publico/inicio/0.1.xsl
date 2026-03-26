<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			<javascript>publico/0.1.js</javascript>
			
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
				<titulo icono="inicio">Bienvenido. <texto key="PLATAFORMA"/></titulo>
				<contenido>
					
					<bloque-contenido>
						<titulo>Ingreso de Usuario</titulo>
						<contenido>
					
							<formulario id="frm_ingreso" destino="inicio/0.do">
								<parrafo>Bienvenido.. PTE le facilita la labor de cargar un gran volumen de pagos y realizar un cargue masivo.</parrafo>
								
								<componente_clave_ingreso identificacion="true" claveingreso="true" cliente_id="true"/>
								
								<xsl:if test="count(usuario_invalido)>0">
									<parrafo estilo="resaltado">
										Usuario o Contraseña Incorrecta
									</parrafo>
								</xsl:if>
								
								<xsl:if test="count(acceso_denegado)>0">
									<parrafo estilo="resaltado">
										Acceso denegado
									</parrafo>
								</xsl:if>
								
								<xsl:if test="count(error_inesperado)>0">
									<parrafo estilo="resaltado">
										Lo sentimos, ha ocurrido un error inesperado al intentar validar su acceso a la aplicación.
									</parrafo>
								</xsl:if>
						
								<area_botones>
									<boton estilo="usuario" formulario="frm_ingreso" validacion="validar_login()">Ingresar</boton>
								</area_botones>
							</formulario>
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
