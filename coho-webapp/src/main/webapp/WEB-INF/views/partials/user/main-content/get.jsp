<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1 class="page-header">Details of the user</h1>

<form class="form-horizontal form-coho" id="createUserForm">
	
	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="form-group">
		<label class="control-label col-xs-2">User Id</label>
		<div class="col-xs-6">
			<p class="form-control-static">${ userBean.userId }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-2">User name</label>
		<div class="col-xs-6">
			<p class="form-control-static">${ userBean.userName }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-2">Password</label>
		<div class="col-xs-6">
			<p class="form-control-static">${ userBean.password }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div class="form-group">
        <label class="control-label col-xs-2">Role</label>
        <div class="col-xs-6">
        	<p class="form-control-static">${ userBean.userRole }</p>
        </div>
        <div class="col-xs-4"></div>
    </div>
</form>
