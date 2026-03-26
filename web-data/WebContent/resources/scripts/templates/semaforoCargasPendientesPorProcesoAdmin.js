$(document).ready(cargarSemaforo);

INTERVALO_SEMAFORO = 60000;

function cargarSemaforo(){
	jsonrpc._("procesoAdminJsonServicio.obtenerSemaforoProcesoAdminPorAdministrativo")(contarCargasPendientes);
}

function contarCargasPendientes(lista){
	
	var totalSemaforosDeProcesos = 0;
	
	$("[name='proceso']").remove();
	if (lista != null) {
		lista = lista.list;
		totalSemaforosDeProcesos = lista.length;
		
		for (var proceso = 0; proceso < lista.length ; proceso++){
			var semaforo = lista[proceso];
			var cargasPorIntervalos = semaforo.cargasPorIntervalo.list;
			var total_intervalos = semaforo.total_intervalos;
			
			//Construimos la informacion de la pagina
			osm_construirHTML("lista_proceso", "template_semaforo", 
			[semaforo.id_proceso_admin,
			semaforo.nombre_proceso,
			"proceso",
			"proceso"+proceso
			
			]);
			
			//recorremos las franjas de cada uno de los semaforos
			for (intervalo=0; intervalo<=total_intervalos; intervalo++){
				var obj = $("#template_intervalo").clone();
				obj.appendTo($("#proceso"+proceso));
				obj.html(cargasPorIntervalos[intervalo]);
				obj.attr("id", "");
				
				obj.toggleClass("intervaloCero", intervalo == 0);
				obj.toggleClass("intervaloUno", intervalo == 1);
				obj.toggleClass("intervaloDos", intervalo == 2);
				obj.toggleClass("intervaloFinal", intervalo == 3);
			
				
			}//For de intervalos
		}//for de procesos
	}
	
	if (totalSemaforosDeProcesos > 0) {
		$("#sin_procesos").hide();
	}else{
		$("#sin_procesos").show();
	}
	
	//Se actualiza el semaforo segun el intervalo
	setTimeout("cargarSemaforo()", INTERVALO_SEMAFORO);

	
}

function verCargas(id_proceso_admin){
	
	osm_setValor("id_proceso_admin", id_proceso_admin);
	osm_enviarFormulario("form_ver_cargas");
	
}


var padre = $("#xxx");
obj = $("#template_intervalo").clone();
obj.attr("value", "algun test");
obj.attr("name","nuevo_campo");
obj.attr("id","");
obj.insertAfter(padre);
