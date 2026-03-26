<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Confirmacion">
			<javascript>publico/confirmacion.js</javascript>

			<principal>
				<contenido>
					<div id="pagina" ng-app="app" ng-controller="enrolamiento">
						<div id="principal">
							<div id="nota_info">
								<nota>
								</nota>
							</div>
							<div id="nota_error">
								<alerta> {{mensaje_error}} </alerta>
							</div>
							<bloque estilo="columna">
								<p>Certifico bajo gravedad de juramento que:</p>

								<formulario>

									<registro>
										<item style="text-align: left">
											Cuento con la autorización para tramitar esta
											solicitud (Si aplica):
										</item>
										<valor>
											<select class="put" ng-model="autorizacion" id="autorizacion">
												<option value="">--Seleccione--</option>
												<option>Si</option>
												<option>No</option>
											</select>
										</valor>
									</registro>

									<div id="observ_1"
										style="text-align: center; justify-content: center; display: flex;">
										<textarea ng-model="autorizacion_observaciones" id="autorizacion_observaciones"
											rows="5" placeholder="Ingrese las observaciones"
											style="resize: none; width:90%"></textarea>
									</div>

									<registro>
										<item style="text-align: left">
											La sociedad a la que represento lleva
											contabilidad regular de sus negocios:
										</item>
										<valor>
											<select ng-model="contabilidad" id="contabilidad"
												class="put">
												<option value="">--Seleccione--</option>
												<option>Si</option>
												<option>No</option>
											</select>
										</valor>
									</registro>

									<div id="observ_2"
										style="text-align: center; justify-content: center; display: flex;">
										<textarea ng-model="contabilidad_observaciones" id="contabilidad_observaciones"
											rows="5" placeholder="Ingrese las observaciones"
											style="resize: none; width:90%"></textarea>
									</div>

									<registro>
										<item style="text-align: left">
											¿El deudor tiene pasivos pensionales a
											cargo?:
										</item>
										<valor>
											<select ng-model="pasivo_pensional" id="pasivo_pensional"
												class="put">
												<option value="">--Seleccione--</option>
												<option>Si</option>
												<option>No</option>
											</select>
										</valor>
									</registro>

									<div id="observ_3"
										style="text-align: center; justify-content: center; display: flex;">
										<textarea ng-model="pasivo_pensional_observaciones"
											id="pasivo_pensional_observaciones" rows="5"
											placeholder="Ingrese las observaciones" style="resize: none; width:90%"></textarea>
									</div>

									<registro>
										<item style="text-align: left">
											El marco bajo el cual lleva la contabilidad
											de
											la organización:
										</item>
										<valor>
											<select ng-model="marco_legal" id="marco_legal" class="put">
												<option value="">--Seleccione--</option>
												<option>Grupo 1</option>
												<option>Grupo 2</option>
												<option>Grupo 3</option>
											</select>
										</valor>
									</registro>

									<div id="observ_4"
										style="text-align: center; justify-content: center; display: flex;">
										<textarea ng-model="marco_legal_observaciones" id="marco_legal_observaciones"
											rows="5" placeholder="Ingrese las observaciones"
											style="resize: none; width:90%"></textarea>
									</div>

									<registro>
										<item style="text-align: left">
											El deudor pertenece a un grupo empresarial en
											los términos del artículo 28 de la Ley 222 de 1995:
										</item>
										<valor>
											<select ng-model="grupo_empresarial_95" id="grupo_empresarial_95"
												class="put">
												<option value="">--Seleccione--</option>
												<option>Si</option>
												<option>No</option>
											</select>
										</valor>
									</registro>

									<div id="observ_5"
										style="text-align: center; justify-content: center; display: flex;">
										<textarea ng-model="grupo_empresarial_95_observaciones"
											id="grupo_empresarial_95_observaciones" rows="5"
											placeholder="Ingrese las observaciones" style="resize: none; width:90%"></textarea>
									</div>

									<registro>
										<item style="text-align: left">
											El deudor pertenece a un grupo empresarial en
											los términos del artículo 2.2.2.14.1.1 Decreto 1074 de 2015:
										</item>
										<valor>
											<select ng-model="grupo_empresarial_2015" id="grupo_empresarial_2015"
												class="put">
												<option value="">--Seleccione--</option>
												<option>Si</option>
												<option>No</option>
											</select>
										</valor>
									</registro>

									<div id="observ_6"
										style="text-align: center; justify-content: center; display: flex;">
										<textarea ng-model="grupo_empresarial_2015_observaciones"
											id="grupo_empresarial_2015_observaciones" rows="5"
											placeholder="Ingrese las observaciones" style="resize: none; width:90%"></textarea>
									</div>
								</formulario>
							</bloque>
							<div id="div_exito">
								<div class="alert alert-success">
									<i class="fa fa-check-circle" aria-hidden="true"></i>
									<span class="sr-only">Info:</span>
									<p>IMPORTANTE ! La solicitud se envió correctamente. Hemos
										enviado un correo electrónico a su dirección registrada
										para
										que
										valide la información. Una vez validada la información le
										llegará nuevamente un correo para que pueda
										iniciar sesión en
										el
										sistema.</p>
								</div>
							</div>
							<div id="div_error">
								<alerta> No fue posible enviar la solicitud, intentelo mas
									tarde.
								</alerta>
							</div>
							<div id="area_botones">
								<area_botones>
									<button id="boton_enviar" ng-click="enviar()" class="btn btn-primary">Aceptar</button>
								</area_botones>
							</div>
							<ventana id="vn_advertencia" icono="confirmacion">
								<titulo>Advertencia</titulo>
								<contenido>
									<div class="panel-body">
										<bloque estilo="grupo">

											<div>Al continuar y registrar las certificaciones
												diligenciadas
												en el
												presente formulario, como Representante
												Legal de la
												Sociedad
												Prosalon Distribuciones S.A.S, certifico
												que toda la
												información
												anterioremente suministrada es
												verdadera y
												corresponde con la
												realidad de acuerdo con la
												siguiente
												advertencia:</div>
											<div
												style="height: 300px; width: auto; border: 1px solid #ddd; background: #f1f1f1; overflow-y: scroll;">
												<p>"Las manifestaciones en el diligenciamiento de los
													formularios electrónicos de los
													que dispone la
													Superintendencia de Sociedades a través de sus
													plataformas,
													así
													como las manifestaciones realizadas a través de mensajes
													de
													datos
													en el marco de
													los procesos de negociación de
													emergencia de acuerdos de
													reorganización se
													entenderán
													realizados bajo la gravedad de juramento. Las conductas de
													falsedad en
													la información o el uso de documentos falsos
													están
													tipificadas
													como delitos bajo el
													Código Penal, Ley 599
													de 2000, y eventualmente pueden ser
													consideradas como
													falsedad material en documento público, falsedad en
													documento
													privado, uso de
													documento falso, falsedad personal,
													entre otros, que se encuentran
													tipificados así:
													ARTÍCULO 287.
													Falsedad material en documento público. El que
													falsifique
													documento
													público que pueda servir de prueba, incurrirá en
													prisión de tres
													(3)
													a seis (6) años.
													Si la conducta fuere
													realizada por un servidor público en
													ejercicio de sus
													funciones,
													la pena será de cuatro (4) a ocho (8) años e
													inhabilitación
													para el ejercicio de
													derechos y funciones
													públicas de cinco (5) a diez (10) años.
													ARTÍCULO 289.
													Falsedad en documento privado. El que
													falsifique
													documento
													privado
													que pueda servir de prueba, incurrirá, si lo usa, en
													prisión
													de
													uno (1) a seis (6) años.
													ARTÍCULO 291. Uso de
													documento falso. El que sin haber concurrido a
													la
													falsificación
													hiciere uso de documento público falso que
													pueda servir de prueba,
													incurrirá en
													prisión de cuatro (4) a
													doce (12) años.
													Si la conducta recae sobre documentos
													relacionados con medios
													motorizados, el
													mínimo de la pena se
													incrementará en la mitad.
													ARTÍCULO 294. Documento. Para los
													efectos de la ley penal es
													documento toda
													expresión de
													persona conocida o conocible recogida por escrito o por
													cualquier
													medio mecánico o técnicamente impreso, soporte
													material que
													exprese o incorpore
													datos o hechos, que tengan
													capacidad probatoria.
													ARTÍCULO 296. Falsedad personal. El
													que con el fin de obtener un
													provecho para sí
													o para otro, o
													causar daño, sustituya o suplante a una
													persona
													o se atribuya
													nombre,
													edad, estado civil, o calidad que pueda tener
													efectos jurídicos,
													incurrirá en multa,
													siempre que la
													conducta no constituya otro delito.</p>
											</div>
										</bloque>

										<div class="modal-footer">
											<area_botones>
												<button estilo="danger" onclick="cerrarVentana()">Aceptar</button>
												<button estilo="danger" onclick="cerrarVentana()">Cancelar</button>
											</area_botones>
										</div>
									</div>
								</contenido>
							</ventana>

						</div>
					</div>


				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
