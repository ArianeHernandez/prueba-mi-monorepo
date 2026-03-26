SEMAFORO_AMARILLO = 50;
SEMAFORO_ROJO = 100;
CARGA_REQUIERE_ADJUNTO = 'W';
CARGAS_PENDIENTES=null;

function cargarSemaforoAdminHijo(){
	
	$("#titulo_rcargas_pendientes").hide();
	$("#sin_admin_hijo").hide();
	$("[name='admin_hijo']").remove();
	jsonrpc._("procesoAdminJsonServicio.obtenerSemaforoParaAdminsHijoPorInstancia")(contarCargasPendientesPorAdminHijo);
	
}

function contarCargasPendientesPorAdminHijo(lista){
	var totalSemaforos= 0;
	
	$("#sin_admin_hijo").hide();
	$("#sin_cargas_admin_hijo").hide();
	
	if (lista != null) {
		lista = lista.list;
		totalSemaforos = lista.length;
		
		for (var admin_hijo = 0; admin_hijo < lista.length ; admin_hijo++){
			var semaforo = lista[admin_hijo];
			var cargasPorIntervalos = semaforo.cargasPorIntervalo.list;
			var total_intervalos = semaforo.total_intervalos;
			
			//Construimos la informacion de la pagina
			osm_construirHTML("lista_semaforo_admin_hijo", "template_semaforo_admin_hijo", 
			[semaforo.administrativo.id_administrativo,
			semaforo.administrativo.nombre+" "+semaforo.administrativo.apellido,
			"admin_hijo",
			"admin_hijo"+admin_hijo
			]);
				
			//recorremos las franjas de cada uno de los semaforos
			for (intervalo=0; intervalo<=total_intervalos; intervalo++){
				var obj = $("#template_intervalo_admin_hijo").clone();
				obj.appendTo($("#admin_hijo"+admin_hijo));
				obj.html(cargasPorIntervalos[intervalo]);
				obj.attr("id", "");
				
				obj.toggleClass("intervaloCero", intervalo == 0);
				obj.toggleClass("intervaloUno", intervalo == 1);
				obj.toggleClass("intervaloDos", intervalo == 2);
				obj.toggleClass("intervaloFinal", intervalo == 3);
				
			}//For de intervalos
		}//for de procesos
		
		if(totalSemaforos==0){
			$("#sin_admin_hijo").show();
		}else{
			$("#titulo_rcargas_pendientes").show();
		}
	}else{
		$("#titulo_rcargas_pendientes").hide();
		$("#sin_admin_hijo").hide();
	}
	
	if (totalSemaforos > 0) {
		$("#sin_admin_hijo").hide();
	}
	
		
}


function mostrarCargasPendientes(id_administrativo_hijo){

		INSTANCIAS_ADMIN_HIJO = null;
				
		//Se borran todas areas
		$(".area_cargas").slideUp();
		$(".area_cargas").each(
			function(){
				$(this).find("*").remove();
			}
		);
		
		//Se pinta el area especifica		
		var areaCargas =  $("#lista_cargas_admin_hijo_"+id_administrativo_hijo);
		
		if (areaCargas.is(":hidden ")) {
			
			var objs = jsonrpc._("procesoAdminJsonServicio.obtenerCargasPorInstanciaParaAdminHijo")(id_administrativo_hijo);
			
		
			
			if (objs != null && objs.list.length > 0) {
				var cargasPendientes = objs.list[0];
				var totalCargas = 0;
				
				INSTANCIAS_ADMIN_HIJO = objs.list[1]
				
				if (cargasPendientes != null) {
					cargasPendientes = cargasPendientes.list;
					totalCargas = cargasPendientes.length;
					
					for (var carga = 0; carga < cargasPendientes.length; carga++) {
						var carga_admin_hijo = cargasPendientes[carga];
						
						var id_contedor_cargas = "lista_cargas_admin_hijo_" + id_administrativo_hijo;
						
						/* Archivo JS: visualizadorCargaProcesoAdmin
						 * funcion que e encarga de pintar todo lo relacionado con la carga
						 * recibe el objeto carga y el id del contenedor de cargas
						 */
						
						try {
							pintarCarga(carga_admin_hijo, id_contedor_cargas, INSTANCIAS_ADMIN_HIJO );
						} catch (e) {
							alert('Problema para mostrar la informacion. Intentelo nuevamente');
						}
						
						
					}//for de cargas
				}
				
				if (totalCargas > 0) {
					$("#sin_cargas_admin_hijo").hide();
				}
				else {
					$("#sin_cargas_admin_hijo").show();
				}
				areaCargas.prepend('<div>&#160;</div>');
				areaCargas.append('<div class="dotted">&#160;</div>');
				areaCargas.append('<div>&#160;</div>');
				areaCargas.slideDown();
				
			}else{
				alert("Ha ocurrido un error inesperado cargando la informaci\u00f3n. Por favor cargue nuevamente la p\u00e1gina");
			}
		
		}else{
			areaCargas.slideUp();
			areaCargas.empty();
			
		}
	
}

function verDetalleCargaAdminHijo(id_carga, id_instancia){
	
	//No se utiliza el id_instancia
	osm_setValor("id_carga_admin_hijo", id_carga);
	osm_enviarFormulario("form_carga_admin_hijo");
	
}

var padre = $("#xxx");
obj = $("#template_intervalo_admin_hijo").clone();
obj.attr("value", "algun test");
obj.attr("name","nuevo_campo");
obj.attr("id","");
obj.insertAfter(padre);
