
var _SKTIME = Math.random();
var _SKOUT = false;

try {
	localStorage.setItem("_SKTIME", _SKTIME);
} catch (e) {
}

var LOCATION_HREF = window.location.href;

if (LOCATION_HREF.indexOf('.pub') < 0) {

	osm_listen("storage", window, function() {

		var LOCATION_HREF = window.location.href;

		if (!_SKOUT && _SKTIME != localStorage.getItem("_SKTIME") && LOCATION_HREF.indexOf('.pub') < 0) {
			_SKOUT = true;
			localStorage.setItem("_SKTIME", _SKTIME);
			$("html").hide();
			osm_go("inicio/0.pub?failed");
		}

	});

};
