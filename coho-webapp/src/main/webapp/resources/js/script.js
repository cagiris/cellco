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

$("#list-table").on('click','.ajax-delete',function(){
	var td = $(this).parent().parent();
	var rowId = td.parent().attr('id');
	var entityId = rowId.substring(4); // "appended 'row-' in front of entity id to create the <tr> id"
	
	$.ajax({
		url: "delete/"+entityId,
	    cache: false,
	    type: "GET",
	    success: function(response) {
	    	$(td).html("<div class='success-msg'>" + response + "</div>");
	    },
	    error: function(xhr) {
	    	$(td).html("<div class='error-msg'>" + response + "</div>");
	    }
	});
});

$('#shiftButton').on('click', function () {
    var $btn = $(this).button('loading')
    // business logic...
    $btn.button('Stop Shift')
  })