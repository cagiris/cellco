<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">Apply for leave</h1>
<form:form class="form-horizontal form-coho" commandName="leaveRequestBean">
	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="form-group">
		<form:label path="requestSubject" for="requestSubject" class="control-label col-xs-2">Subject</form:label>
		<div class="col-xs-6">
			<form:input path="requestSubject" type="text" class="form-control" id="leaveSubject" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="requestSubject" cssClass="form-field-error"/>
		</div>
	</div>
	<%
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String currentDate=simpleDateFormat.format(new Date());
	%>
	<div class="form-group">
		<form:label path="leaveStartDate" for="leaveStartDate" class="control-label col-xs-2">Start
			Date</form:label>
		<div class="col-xs-6">
			<div class="input-group date form-datepicker" data-date="<%=currentDate %>"
				data-date-format="dd-mm-yyyy">
				<form:input path="leaveStartDate" type="text" class="form-control" id="startDate"
					placeholder="Start Date" required="true" /> <span
					class="input-group-addon add-on"><i
					class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		<div class="col-xs-4">
			<form:errors path="leaveStartDate" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="leaveEndDate" for="leaveEndDate" class="control-label col-xs-2">End Date</form:label>
		<div class="col-xs-6">
			<div class="input-group date form-datepicker" data-date="<%=currentDate %>"
				data-date-format="dd-mm-yyyy">
				<form:input path="leaveEndDate" type="text" class="form-control" id="endDate"
					placeholder="End Date" required="true" /> <span
					class="input-group-addon add-on"><i
					class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		<div class="col-xs-4">
			<form:errors path="leaveEndDate" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="requestDescription" for="description" class="control-label col-xs-2">Description</form:label>
		<div class="col-xs-6">
			<form:textarea path="requestDescription" rows="5" id="description" class="form-control"></form:textarea>
		</div>
		<div class="col-xs-4">
			<form:errors path="requestDescription" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<button type="submit" class="btn btn-primary">Apply</button>
		</div>
	</div>
</form:form>

