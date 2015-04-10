<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="java.util.Date"%>

<h1 class="page-header">
	User Profile - ${ userProfileBean.userId}
	<sec:authorize access="hasRole('ADMIN')">
		<a href="<core:url value = '/user-profile/update/${ userProfileBean.userId}' />" class="btn btn-primary btn-md pull-right" role="button">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			Update Profile
		</a>
	</sec:authorize>
</h1>

<form:form commandName="userProfileBean" cssClass="form-horizontal" role="form">

	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="col-md-7">
		<fieldset>
          	<legend>Personal Details</legend>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="firstName">First Name:</form:label>
				<div class="col-md-5 controls">
					<p class="form-control-static">${ userProfileBean.firstName}</p>
				</div>
			</div>

			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="lastName">Last Name:</form:label>
				<div class="col-md-5">
					<p class="form-control-static">${ userProfileBean.lastName}</p>
				</div>
			</div>

			<div class="form-group">
				<form:label path="dateOfBirth" cssClass="col-md-2 control-label">Date of birth:</form:label>
				<div class="col-md-5">
					<p class="form-control-static">${ userProfileBean.dateOfBirth}</p>
				</div>
			</div>

			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="gender">Gender:</form:label>
				<div class="col-md-5">
					<p class="form-control-static">${ userProfileBean.gender}</p>
				</div>
			</div>
		</fieldset>
		<fieldset>
          	<legend>User authorization details</legend>
			
			<input id="userId" type="hidden" value="${ userProfileBean.userId}" />
			
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="state">Password:</form:label>
				<div class="col-sm-3">
					<p class="form-control-static">*******</p>
				</div>

				<div class="col-sm-3">
					<button type="button" class="btn btn-default" data-toggle="modal" 
							data-target="#change-password-modal" data-userId="${ userProfileBean.userId }">
							
							<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
							Change
					</button>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="pincode">Privileges:</form:label>
				<div class="col-sm-3">
					<p class="form-control-static">${ userBean.userRole }</p>
				</div>

				<div class="col-sm-3">
					<button type="button" class="btn btn-default" data-toggle="modal" 
							data-target="#change-privileges-modal" data-userId="${ userBean.userRole }">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
							Change
					</button>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>Contact Details</legend>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="mobileNumber">Mobile:</form:label>
				<div class="col-md-6">
					<p class="form-control-static">${ userProfileBean.mobileNumber}</p>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="emailId">E-mail ID:</form:label>
				<div class="col-md-6">
					<p class="form-control-static">${ userProfileBean.emailId}</p>
				</div>
			</div>
		</fieldset>
		<fieldset>
          <legend>Address Details</legend>
          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="addressLine1">Line 1:</form:label>
            <div class="col-md-8">
              <p class="form-control-static">${ userProfileBean.addressLine1}</p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="addressLine2">Line 2:</form:label>
            <div class="col-md-8">
              <p class="form-control-static">${ userProfileBean.addressLine2}</p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="city">City:</form:label>
            <div class="col-md-8">
              <p class="form-control-static">${ userProfileBean.city}</p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="state">State:</form:label>
            <div class="col-sm-3">
              <p class="form-control-static">${ userProfileBean.state}</p>
            </div>

            <form:label cssClass="col-md-2 control-label" path="pincode">Pin Code:</form:label>
            <div class="col-sm-3">
              <p class="form-control-static">${ userProfileBean.pincode}</p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="country">Country:</form:label>
            <div class="col-md-8">
              <p class="form-control-static">${ userProfileBean.country}</p>
            </div>
          </div>
		</fieldset>
		<fieldset>
			<legend>Employment Details</legend>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="joinedOn">Joined on:</form:label>
				<div class="col-md-5">
					<p class="form-control-static">${ userProfileBean.joinedOn}</p>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="leftOn">Left on:</form:label>
				<div class="col-md-5">
					<p class="form-control-static">${ userProfileBean.leftOn}</p>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="designation">Designation:</form:label>
				<div class="col-md-8">
					<p class="form-control-static">${ userProfileBean.designation}</p>
				</div>
			</div>
		</fieldset>
	</div>
	<div class="col-md-3 col-md-offset-1">
		<div class="well" id="employee-photo-container">Employee Photograph</div>
	</div>
</form:form>

<div class="modal fade" id="change-password-modal" tabindex="-1"
	role="dialog" aria-labelledby="change-password-modal"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="change-password-modal-title">Change password</h4>
			</div>
			<div class="modal-body">
				<form>
					<p class="help-block error-msg" id="update-password-error"></p>
					<p class="help-block success-msg" id="update-password-sucess"></p>
					<div class="form-group">
						<label for="new-password">New Password</label>
						<div>
							<input type="password" placeholder="New Password"
								id="new-password" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="re-entered-password">Re-Enter Password</label>
						<div>
							<input type="password" placeholder="New Password"
								id="re-entered-password" class="form-control" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="update-password-button">Update Password</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="change-privileges-modal" tabindex="-1"
	role="dialog" aria-labelledby="change-privileges-moda"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="change-privileges-modal-title">Change privileges</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="userRoleList">Privileges</label>
						<div>
							
							<select id="userRoleList">
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
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="update-user-role-button">Save</button>
			</div>
		</div>
	</div>
</div>
