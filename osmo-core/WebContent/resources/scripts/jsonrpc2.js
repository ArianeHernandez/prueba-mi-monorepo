
var JSONRPC_HTS = function(serverURL){
	
	this._ = function(metodo){
		
		var f = function(){
			var params = [];
			for (var i = 0; i < arguments.length; i++) {
				params.push(arguments[i]);
			}
			
			// cuando es asincrono
			
			var esASincrono = params.length > 0 && (typeof params[0] == "function");
			
			if(esASincrono){
				
				var self = this;

				var aparams = params.slice(1);
				
				$.ajax({
					method : "POST",
					url : serverURL,
					contentType : "application/json;charset=iso-8859-1",
					data : JSON.stringify({method: metodo, params: aparams}),
					dataType : "json",
					cache : false
				}).done(function(msg) {
					params[0].apply(self, [msg.result]);
				});
				
				return;
			}
			
			// cuando es sincrono
			
			var resp = null;
			$.ajax({
				method : "POST",
				url : serverURL,
				contentType : "application/json;charset=iso-8859-1",
				data : JSON.stringify({method: metodo, params: params}),
				dataType : "json",
				async : false,
				cache : false
			}).done(function(msg) {
				resp = msg;
			});
			
			return resp.result;
			
		};
		
		
		return f;
	};
	
	// ---
	
	this.pcore = {};
	this.core = {};
	
	this.core.setSessionErrorLog = this._("core.setSessionErrorLog");
	this.core.setSessionInfoLog = this._("core.setSessionInfoLog");
	
	this.pcore.getCurrentTime = this._("pcore.getCurrentTime");
	this.pcore.validarCorreo = this._("pcore.validarCorreo");
	this.pcore.calcularDigitoVerificacion = this._("pcore.calcularDigitoVerificacion");
	this.pcore.setErrorLog = this._("pcore.setErrorLog");
	this.pcore.getAplicationName = this._("pcore.getAplicationName");
	
}


try {
	jsonrpc = new JSONRPC_HTS( CONTEXTPATH + "/JSON-RPC" );
} catch (e) {
	alert("Error inesperado consultando datos JSON: "+ e);
	osm_unblock_window();
}

