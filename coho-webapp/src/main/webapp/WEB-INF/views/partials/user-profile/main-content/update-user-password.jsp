<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:useBean id="errorMap" class="java.util.HashMap" />
<spring:hasBindErrors name="updatePasswordBean">
	<core:forEach var="error" items="${errors.fieldErrors}">
		<core:set target="${errorMap}" property="${ error.field }" value="has-error"/>
	</core:forEach>
</spring:hasBindErrors>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title" id="change-password-modal-title">Change password</h4>
		</div>
		<div class="modal-body">
			<form:form commandName="updatePasswordBean" cssClass="form-horizontal" role="form">
				<div class="form-group <core:out value='${errorMap["newPassword"]}'/>">
					<form:label cssClass="col-md-4 control-label" path="newPassword">New Password</form:label>
					<div class="col-md-5 controls">
						<form:password path="newPassword" placeholder="New Password"
							cssClass="form-control" />
						<p class="help-block form-field-error">
							<form:errors path="newPassword" />
						</p>
					</div>
				</div>
				<div
					class="form-group <core:out value='${errorMap["reEnteredPassword"]}'/>">
					<form:label cssClass="col-md-4 control-label"
						path="reEnteredPassword">Re-enter Password</form:label>
					<div class="col-md-5 controls">
						<form:password path="reEnteredPassword" placeholder="Password"
							cssClass="form-control" />
						<p class="help-block form-field-error">
							<form:errors path="reEnteredPassword" />
						</p>
					</div>
				</div>
			</form:form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary"
				id="update-password-button" onclick="updatePassword();">Update Password</button>
		</div>
	</div>
</div>

