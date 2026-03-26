
function descargar_reporte() {

	var filtro = {};
	
	filtro.fecha_desde = osm_trim(osm_getValor("fecha_desde"));
	filtro.fecha_hasta = osm_trim(osm_getValor("fecha_hasta"));
	filtro.id_negocio = osm_getValor("select_negocio");
	filtro.id_cliente = osm_getValor("id_usuario");
	console.log(filtro);
	var id = jsonrpc._("reporteTipoNegocio.generarReporte")(filtro);
	$("#id_archivo").val(id);
	$("#form_download_report").get(0).submit();
	
	osm_unblock_window();

}

function buscar() {

	var filtro = {};
	
	filtro.fecha_desde = osm_trim(osm_getValor("fecha_desde"));
	filtro.fecha_hasta = osm_trim(osm_getValor("fecha_hasta"));
	filtro.id_negocio = osm_getValor("select_negocio");
	filtro.id_cliente = osm_getValor("id_usuario");
	console.log(filtro);
	
	var lista = jsonrpc._("reporteTipoNegocio.consultar")(filtro).list;
	$("#lista").val(lista);
	
	console.log(lista);
	
	makeTable(lista);
}

function makeTable(array) {
	var new_tbody = document.createElement('tbody');
	var old_tbody = document.getElementById("tabla_tipo_negocio");
	new_tbody.setAttribute("id", "tabla_tipo_negocio");
	console.log(new_tbody);
    for (var i = 0; i < array.length; i++){
        var row = new_tbody.insertRow(i);
        row.className = "tabla_fila";
        a = array[i];
        console.log(a);
        var cell = row.insertCell(0);
        cell.innerHTML = a.id_encargo;
        var cell = row.insertCell(1);
        cell.innerHTML = a.nombre_cliente; 
        var cell = row.insertCell(2);
        cell.innerHTML = a.tipo_negocio; 
        var cell = row.insertCell(3);
        cell.innerHTML = a.producto; 
        var cell = row.insertCell(4);
        cell.innerHTML = a.encargo; 
        var cell = row.insertCell(5);
        cell.innerHTML = osm_formatoMoneda(a.saldo);
        var cell = row.insertCell(6);
        cell.innerHTML = osm_getFecha(a.fecha.time);
    }
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
    old_tbody.remove();
}
