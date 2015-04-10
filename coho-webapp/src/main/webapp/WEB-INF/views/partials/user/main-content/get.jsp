<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<h1 class="page-header">Details of the user</h1>

<form class="form-horizontal form-coho" id="createUserForm">
	
	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="form-group">
		<label class="control-label col-xs-3">User Id :</label>
		<div class="col-xs-5">
			<p class="form-control-static">${ userBean.userId }</p>
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div class="form-group">
        <label class="control-label col-xs-3">Role :</label>
        <div class="col-xs-5">
        	<p class="form-control-static">${ userBean.userRole }</p>
        </div>
        <div class="col-xs-4"></div>
    </div>
</form>
