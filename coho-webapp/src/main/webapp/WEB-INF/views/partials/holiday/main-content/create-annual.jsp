<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1 class="page-header">Add Holiday</h1>

<form:form class="form-horizontal form-coho"
	commandName="annualHolidayBean" action="${updateURL}" method="POST">

	<tiles:insertAttribute name="feedback-msg" />

	<div class="form-group">
		<form:label path="holidayDate" for="hoildayDate"
			class="control-label col-xs-2">Holiday Date</form:label>
		<div class="col-xs-6">
			<div class="input-group date datetimepicker-date" data-date-format="dd-mm-yyyy">
				<form:input path="holidayDate" type="text" class="form-control"
					id="holidayDate" placeholder="Holiday Date" required="true" />
				<span class="input-group-addon add-on"><i
					class="glyphicon glyphicon-calendar"></i></span>
			</div>
		</div>
		<div class="col-xs-4">
			<form:errors path="holidayDate" cssClass="form-field-error" />
		</div>
	</div>

	<div class="form-group">
		<form:label path="description" for="holidayDescription"
			class="control-label col-xs-2">Description</form:label>
		<div class="col-xs-6">
			<form:input path="description" type="text"
				class="form-control" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="description" cssClass="form-field-error" />
		</div>
	</div>

	<div class="form-group <core:out value='${errorMap["userRole"]}'/>">
		<form:label cssClass="col-md-2 control-label" path="userRole">User Role</form:label>
		<div class="col-md-6 controls">
			<form:select path="userRole" cssClass="form-control">
				<core:forEach var="role" items="${userRoleList}">
					<option>${role}</option>
				</core:forEach>
			</form:select>
			<p class="help-block form-field-error">
				<form:errors path="userRole" />
			</p>
		</div>
	</div>

	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<form:button type="submit" class="btn btn-primary">Submit</form:button>
		</div>
	</div>
</form:form>
