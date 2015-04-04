<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="page-header">Add a new user</h1>
<form:form commandName="userBean" cssClass="form-horizontal form-coho" id="createUserForm">
	<div class="form-group">
		<form:label path="userId" cssClass="control-label col-xs-2">User Id</form:label>
		<div class="col-xs-6">
			<form:input cssClass="form-control" path="userId" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="userId" cssClass="text-danger"/>
		</div>
	</div>
	
	<div class="form-group">
		<form:label path="userName" cssClass="control-label col-xs-2">User name</form:label>
		<div class="col-xs-6">
			<form:input cssClass="form-control" path="userName" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="userName" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="password" cssClass="control-label col-xs-2">Password</form:label>
		<div class="col-xs-6">
			<form:password cssClass="form-control" path="password" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="password" cssClass="text-danger"/>
		</div>
	</div>
	<div class="form-group">
        <form:label path="userRole" cssClass="control-label col-xs-2">User Role</form:label>
        <div class="col-xs-6">
        	<form:select path="userRole" cssClass="form-control">
        		<core:forEach var="role" items="${userRoleList}">
        			<option>${role}</option>
        		</core:forEach>
        	</form:select>
        </div>
        <div class="col-xs-4">
			<form:errors path="userRole" cssClass="text-danger"/>
		</div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-8">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form:form>
