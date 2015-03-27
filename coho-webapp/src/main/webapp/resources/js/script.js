/**
 * Custom Javascript.
 */
(function () {
	$('.form-datepicker').datepicker();
} ());

function enableob(o) { 
	eval(o+".disabled = false"); 
}

function disableob(o) { 
	eval(o+".disabled = true"); 
}

function toggleform(formstr,chkobstr,obstr) {
	var checked = eval(formstr+"."+chkobstr+".checked");
	var obs = obstr.split(",");
	for (i = 0; i < obs.length; i++) {
		obs[i] = formstr+"."+obs[i];
	}
	
	if (checked == false) {
		for (i = 0; i < obs.length; i++) {
			disableob(obs[i]);
		}
	}
	else {
		for (i = 0; i < obs.length; i++) {
			enableob(obs[i]);
		}
	}
}