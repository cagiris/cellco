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

(function () {
	initLeaveStatistics();
	initAdminStatistics();
} ());

function initLeaveStatistics() {
	if ($("#leave-statistics-table").length == 0) {
		return;
	}
	
	var rowCasualLeave = $("#leave-statistics-table")[0].insertRow(-1);
	rowCasualLeave.insertCell(0).innerHTML = "Casual Leaves";
	var cellCasualLeave = rowCasualLeave.insertCell(1);
	cellCasualLeave.innerHTML = "Loading...";
	getDataAjax("get-leave-count/CASUAL_LEAVE", null, function (response) {
		cellCasualLeave.innerHTML = response;
	}, function () {
		cellCasualLeave.innerHTML = "error";
	});

	var rowPaidLeave = $("#leave-statistics-table")[0].insertRow(-1);
	rowPaidLeave.insertCell(0).innerHTML = "Paid Leaves";
	var cellPaidLeave = rowPaidLeave.insertCell(1);
	cellPaidLeave.innerHTML = "Loading...";

	getDataAjax("get-leave-count/PAID_LEAVE", null, function (response) {
		cellPaidLeave.innerHTML = response;
	}, function () {
		cellPaidLeave.innerHTML = "error";
	});
}

function initAdminStatistics() {
	if ($("#admin-statistics-table").length == 0) {
		return;
	}
	
	var rowPendingApprovals = $("#admin-statistics-table")[0].insertRow(-1);
	rowPendingApprovals.insertCell(0).innerHTML = "Pending Approvals";
	var cellPendingApprovals = rowPendingApprovals.insertCell(1);
	cellPendingApprovals.innerHTML = "Loading...";
	getDataAjax("get-leave-approvals-count/NEW", null, function (response) {
		cellPendingApprovals.innerHTML = response;
	}, function () {
		cellPendingApprovals.innerHTML = "error";
	});

	var rowActiveUsers = $("#admin-statistics-table")[0].insertRow(-1);
	rowActiveUsers.insertCell(0).innerHTML = "Active Users";
	var cellActiveUsers = rowActiveUsers.insertCell(1);
	cellActiveUsers.innerHTML = "Loading...";
	getDataAjax("get-users-active-in-shift-count", null, function (response) {
		cellActiveUsers.innerHTML = response;
	}, function () {
		cellActiveUsers.innerHTML = "error";
	});
	
	var rowTotalUsers = $("#admin-statistics-table")[0].insertRow(-1);
	rowTotalUsers.insertCell(0).innerHTML = "Total Users";
	var cellTotalUsers = rowTotalUsers.insertCell(1);
	cellTotalUsers.innerHTML = "Loading...";
	getDataAjax("get-users-count", null, function (response) {
		cellTotalUsers.innerHTML = response;
	}, function () {
		cellTotalUsers.innerHTML = "error";
	});
}

/**
 * Utility functions.
 */

function getDataAjax (urlForData, requestData, callbackSuccess, callbackFail) {
	$.ajax({
		url: getRequestURL(urlForData),
	    cache: false,
	    data: requestData,
	    type: "GET",
	    success: callbackSuccess,
	    error: callbackFail
	});
}

function getRequestURL(requestURL) {
	return (getBaseURL() + requestURL);
}

function getBaseURL() {
    var url = location.href;  // entire url including querystring - also: window.location.href;
    var baseURL = url.substring(0, url.indexOf('/', 14));

    if (baseURL.indexOf('http://localhost') != -1) {
        // Base Url for localhost
        var url = location.href;  // window.location.href;
        var pathname = location.pathname;  // window.location.pathname;
        var index1 = url.indexOf(pathname);
        var index2 = url.indexOf("/", index1 + 1);
        var baseLocalUrl = url.substr(0, index2);

        return baseLocalUrl + "/";
    }
    else {
        // Root Url for domain name
        return baseURL + "/";
    }

}



