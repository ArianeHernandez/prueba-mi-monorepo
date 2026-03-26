<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Generar Firma">
			<principal>
				<titulo>Generar Firma</titulo>
				<contenido>
				<div class="box-container">
				
				
					<javascript>firma/31.1.js</javascript>

					<bloque>

						<formulario id="form_gen_firma" destino="firma/generarClave.pkcs12">

							<bloque-contenido>

								<contenido>

									<div>Digite su clave para obtener la Firma</div>

									<registro>
										<item>Contraseña</item>
										<valor>
											<cajaclave id="passwordFirma"/>
										</valor>
									</registro>

									<registro>
										<item>Confirmar Contraseña</item>
										<valor>
											<cajaclave id="conf_passwordFirma"/>
										</valor>
									</registro>

								</contenido>

							</bloque-contenido>

							<area_botones>
								<boton accion="validar_datos()">Generar</boton>
							</area_botones>

						</formulario>

					</bloque>
				
				</div>
				</contenido>
			</principal>
		</pagina>

	</xsl:template>


</xsl:stylesheet>
