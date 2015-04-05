<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1 class="page-header">Update details of the user</h1>

<form:form commandName="userBean" cssClass="form-horizontal form-coho" id="updateUserForm">
	
	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="form-group">
		<form:label path="userId" cssClass="control-label col-xs-3">User Id</form:label>
		<div class="col-xs-5">
			<form:input cssClass="form-control" path="userId" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="userId" cssClass="form-field-error"/>
		</div>
	</div>
	
	<div class="form-group">
		<form:label path="userName" cssClass="control-label col-xs-3">Full name of user</form:label>
		<div class="col-xs-5">
			<form:input cssClass="form-control" path="userName" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="userName" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="password" cssClass="control-label col-xs-3">Password</form:label>
		<div class="col-xs-5">
			<form:password cssClass="form-control" path="password" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="password" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="userName" cssClass="control-label col-xs-3">Re-enter Password</form:label>
		<div class="col-xs-5">
			<form:input cssClass="form-control" path="reEnterdPassword" required="true" />
		</div>
		<div class="col-xs-4">
			<form:errors path="reEnterdPassword" cssClass="form-field-error"/>
		</div>
	</div>
	<div class="form-group">
        <form:label path="userRole" cssClass="control-label col-xs-3">User Role</form:label>
        <div class="col-xs-5">
        	<form:select path="userRole" cssClass="form-control">
        		<core:forEach var="role" items="${userRoleList}">
        			<core:choose>
	        			<core:when test="${ userBean.userRole == role}">
	        				<option selected="selected">${role}</option>
	        			</core:when>
	        			<core:otherwise>
	        				<option>${role}</option>
	        			</core:otherwise>
        			</core:choose>
        		</core:forEach>
        	</form:select>
        </div>
        <div class="col-xs-4">
			<form:errors path="userRole" cssClass="form-field-error"/>
		</div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-8">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form:form>
