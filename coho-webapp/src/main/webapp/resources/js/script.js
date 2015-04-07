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

$('#myModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) 		// Button that triggered the modal
	  var recipient = button.data('whatever') 	// Extract info from data-* attributes
	  var modal = $(this)
	  modal.find('.modal-body input').val(recipient)
});
	
$("#myModal").on('click','.ajax-delete',function(event){
	var entityId = $('#recipient-name').val();
    console.log(entityId);
	$.ajax({
		url: "delete/"+entityId,
	    cache: false,
	    type: "GET",
	    success: function(response) {
	    	$(".modal-body-content").hide();
	    	$(".modal-body-content-ret").html("<div class='success-msg'>" + response + "</div>");
	    	// append hidden field
	    	$(".modal-body-content-ret").append("<input type=\"hidden\" class=\"form-control\" id=\"recipient-name\" value=\"" + entityId + "\"></div>");
	    	$(".modal-body-content-ret").show();
	    	// Hide Delete Button on pop-up window
	    	$("#myModal .ajax-delete").hide();
	    	// Hide deleted row visible behind pop-up window
	    	$("#row-"+entityId).hide();
	    },
	    error: function(xhr) {
	    	$(modal-body).html("<div class='error-msg'>" + response + "</div>");
	    }
	});
});

$("#myModal").on('click','.popup-close',function(event){
   	$(".modal-body-content").show();
   	$(".modal-body-content-ret").hide();
});

$('#myModal').on('hide.bs.modal', function (event) {
   	$(".modal-body-content").show();
   	$(".modal-body-content-ret").hide();
});

$('#shiftButton').on('click', function () {
	if($('#shiftId').val()){
		stopShift($('#shiftId').val());
	}else{
		startShift();
	}
})

function startShift(){
	$.ajax({
		url: "start-shift",
	    cache: false,
	    type: "POST",
	    success: function(response){
	    	$('#shiftId').val(response);
	    	$('#shiftButton').html('Stop Shift');
	    },
	    error: function(){
	    	$('#shiftButton').html('Start Shift');
	    }
	    	
	});
}

function stopShift(shiftId){
	$.ajax({
		url: "end-shift",
	    cache: false,
	    type: "POST",
	    success: function(response){
	    	$('#shiftId').val(null);
	    	$('#shiftButton').html('Start Shift');
	    },
	    data:{
	    	'shiftId':shiftId
	    },
	    error: function(){
	    	$('#shiftButton').html('Stop Shift');
	    }
	    	
	});
}

// to be called from page load ..
function checkForActiveShift(){
	$.ajax({
		url: "get-shift-status",
	    cache: false,
	    type: "GET",
	    success: function(response){
	    	$('#shiftId').val(response);
	    	$('#shiftButton').html('Stop Shift');
	    },
	    error: function(){
	    	$('#shiftButton').html('Start Shift');
	    }
	    	
	});
}


