<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1 class="page-header">Leave Request Details</h1>

<form class="form-horizontal form-coho" id="createUserForm">

	<tiles:insertAttribute name="feedback-msg" />

	<div class="form-group">
		<label class="control-label col-xs-3">Leave Subject :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ leaveRequestBean.requestSubject }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>

	<div class="form-group">
		<label class="control-label col-xs-3">Leave Description :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ leaveRequestBean.requestDescription }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-3">Leave Start Date :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ leaveRequestBean.leaveStartDate }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-3">Leave End Date :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ leaveRequestBean.leaveEndDate }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-3">Leave Request Status :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ leaveRequestBean.leaveRequestStatus }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-3">Leave Count :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ leaveRequestBean.leaveCount }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	
</form>
