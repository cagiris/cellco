<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1 class="page-header">Add a new user</h1>

<jsp:useBean id="errorMap" class="java.util.HashMap" />
<spring:hasBindErrors name="userBean">
	<core:forEach var="error" items="${errors.fieldErrors}">
		<core:set target="${errorMap}" property="${ error.field }" value="has-error"/>
	</core:forEach>
</spring:hasBindErrors>

<form:form commandName="userBean" cssClass="form-horizontal" id="createUserForm">
	
	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="col-md-8">
		<fieldset>
          	<legend>User authorization details</legend>
          	
          	<div class="form-group <core:out value='${errorMap["userId"]}'/>">
				<form:label cssClass="col-md-3 control-label" path="userId">User ID</form:label>
				<div class="col-md-5 controls">
					<form:input path="userId" placeholder="User ID" cssClass="form-control" />
					<p class="help-block form-field-error"><form:errors path="userId" /></p>
				</div>
			</div>
			<div class="form-group <core:out value='${errorMap["password"]}'/>">
				<form:label cssClass="col-md-3 control-label" path="password">Password</form:label>
				<div class="col-md-5 controls">
					<form:password path="password" placeholder="Password" cssClass="form-control" />
					<p class="help-block form-field-error"><form:errors path="password" /></p>
				</div>
			</div>
			<div class="form-group <core:out value='${errorMap["reEnterdPassword"]}'/>">
				<form:label cssClass="col-md-3 control-label" path="reEnterdPassword">Re-enter Password</form:label>
				<div class="col-md-5 controls">
					<form:password path="reEnterdPassword" placeholder="Password" cssClass="form-control" />
					<p class="help-block form-field-error"><form:errors path="reEnterdPassword" /></p>
				</div>
			</div>
			<div class="form-group <core:out value='${errorMap["userRole"]}'/>">
				<form:label cssClass="col-md-3 control-label" path="userRole">Privileges</form:label>
				<div class="col-md-5 controls">
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
		        <div class="col-md-4 col-md-offset-3">
		            <button type="submit" class="btn btn-primary">Submit</button>
		        </div>
		    </div>
		</fieldset>
	</div>
</form:form>
