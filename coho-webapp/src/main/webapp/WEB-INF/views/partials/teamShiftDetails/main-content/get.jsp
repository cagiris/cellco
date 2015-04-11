<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">Shift Details Configuration</h1>
<core:url value = '/teamShiftDetails/create' var="updateURL" />
<form:form class="form-horizontal form-coho" commandName="teamShiftDetailsBean" action="${updateURL}" method="POST">
	<tiles:insertAttribute name="feedback-msg" />
	

	<div class="form-group">
		<form:label path="shiftStartTime" for="shiftStartTime" class="control-label col-xs-2">Start
			Date</form:label>
		<div class="col-xs-6">
			<div class="input-group date datetimepicker-time">
				<form:input path="shiftStartTime" type="text" class="form-control" id="startDate"
					placeholder="Shift Start Time" required="true" /> <span
					class="input-group-addon add-on"><i
					class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		<div class="col-xs-4">
			<form:errors path="shiftStartTime" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="shiftEndTime" for="shiftEndTime" class="control-label col-xs-2">End Date</form:label>
		<div class="col-xs-6">
			<div class="input-group date datetimepicker-time">
				<form:input path="shiftEndTime" type="text" class="form-control" id="endDate"
					placeholder="Shift End Date" required="true" /> <span
					class="input-group-addon add-on"><i
					class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		<div class="col-xs-4">
			<form:errors path="shiftEndTime" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="shiftDuration" for="shiftDuration" class="control-label col-xs-2">Shift Duration</form:label>
		<div class="col-xs-6">
			<form:input readonly="true" path="shiftDuration" type="text" class="form-control" id="shiftDuration" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="shiftDuration" cssClass="form-field-error"/>
		</div>
	</div>
		<div class="form-group">
		<form:label path="shiftBuffer" for="shiftBuffer" class="control-label col-xs-2">Shift Buffer</form:label>
		<div class="col-xs-6">
			<form:input path="shiftBuffer" type="text" class="form-control" id="shiftBuffer" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="shiftBuffer" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<form:button type="submit" class="btn btn-primary">Update</form:button>
		</div>
	</div>
</form:form>

