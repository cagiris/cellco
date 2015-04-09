<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<h1 class="page-header">User Profile - ${ userProfileBean.userId}</h1>

<%
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String currentDate = simpleDateFormat.format(new Date());
%>

<form:form commandName="userProfileBean" cssClass="form-horizontal" role="form">

	<tiles:insertAttribute name="feedback-msg" />
	
	<div class="col-md-7">
		<fieldset>
          	<legend>Personal Details</legend>
          	
          	<form:hidden path="userId"/>
          	
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="firstName">First Name</form:label>
				<div class="col-md-5 controls">
					<form:input path="firstName" placeholder="First Name" cssClass="form-control" />
					<p class="help-block form-field-error"><form:errors path="firstName" /></p>
				</div>
			</div>

			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="lastName">Last Name</form:label>
				<div class="col-md-5">
					<form:input placeholder="Last Name" cssClass="form-control" path="lastName"/>
					<p class="help-block form-field-error"><form:errors path="lastName" /></p>
				</div>
			</div>

			<div class="form-group">
				<form:label path="dateOfBirth" cssClass="col-md-2 control-label">Date of birth</form:label>
				<div class="col-md-5">
					<div class="input-group date form-datepicker"
						data-date="<%=currentDate%>" data-date-format="dd-mm-yyyy">
						<form:input type="text" cssClass="form-control" 
							placeholder="dd-MM-YYYY" path="dateOfBirth" /> <span
							class="input-group-addon add-on"><i
							class="glyphicon glyphicon-calendar"></i></span>
					</div>
					<p class="help-block form-field-error"><form:errors path="dateOfBirth" /></p>
				</div>
			</div>

			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="gender">Gender</form:label>
				<div class="col-md-5 control-group">
					<form:label cssClass="radio inline col-md-5" path="gender"> 
						<form:radiobutton path="gender" name="gender" value="Male" checked="checked" /> 
						Male
					</form:label> 
					
					<form:label cssClass="radio inline" path="gender"> 
						<form:radiobutton path="gender" name="gender" value="Female" /> 
						Female
					</form:label>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>Contact Details</legend>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="mobileNumber">Mobile</form:label>
				<div class="col-md-6">
					<form:input placeholder="Mobile Number" 
								cssClass="form-control" 
								path="mobileNumber" />
					<p class="help-block form-field-error"><form:errors path="mobileNumber" /></p>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="emailId">E-mail ID</form:label>
				<div class="col-md-6">
					<form:input placeholder="E-mail ID"
								cssClass="form-control"
								path="emailId" />
					<p class="help-block form-field-error"><form:errors path="emailId" /></p>
				</div>
			</div>
		</fieldset>
		<fieldset>
          <legend>Address Details</legend>
          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="addressLine1">Line 1</form:label>
            <div class="col-md-8">
              <form:input path="addressLine1" placeholder="Address Line 1" cssClass="form-control" />
              <p class="help-block form-field-error"><form:errors path="addressLine1" /></p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="addressLine2">Line 2</form:label>
            <div class="col-md-8">
              <form:input placeholder="Address Line 2" cssClass="form-control" path="addressLine2" />
              <p class="help-block form-field-error"><form:errors path="addressLine2" /></p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="city">City</form:label>
            <div class="col-md-8">
              <form:input placeholder="City" cssClass="form-control" path="city" />
              <p class="help-block form-field-error"><form:errors path="city" /></p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="state">State</form:label>
            <div class="col-sm-3">
              <form:input path="state" placeholder="State" cssClass="form-control" />
              <p class="help-block form-field-error"><form:errors path="state" /></p>
            </div>

            <form:label cssClass="col-md-2 control-label" path="pincode">Pin Code</form:label>
            <div class="col-sm-3">
              <form:input path="pincode" placeholder="Pin Code" cssClass="form-control" />
              <p class="help-block form-field-error"><form:errors path="pincode" /></p>
            </div>
          </div>

          <div class="form-group">
            <form:label cssClass="col-md-2 control-label" path="country">Country</form:label>
            <div class="col-md-8">
              <form:input path="country" placeholder="Country" cssClass="form-control" />
              <p class="help-block form-field-error"><form:errors path="country" /></p>
            </div>
          </div>
		</fieldset>
		<fieldset>
			<legend>Employment Details</legend>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="joinedOn">Joined on</form:label>
				<div class="col-md-5">
					<div class="input-group date form-datepicker"
						data-date="<%=currentDate%>" data-date-format="dd-mm-yyyy">
						<form:input path="joinedOn" cssClass="form-control" placeholder="dd-MM-YYYY" /> 
						<span class="input-group-addon add-on"><i
							class="glyphicon glyphicon-calendar"></i></span>
					</div>
					<p class="help-block form-field-error"><form:errors path="joinedOn" /></p>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="leftOn">Left on</form:label>
				<div class="col-md-5">
					<div class="input-group date form-datepicker"
						data-date="<%=currentDate%>" data-date-format="dd-mm-yyyy">
						<form:input path="leftOn" cssClass="form-control"
							placeholder="dd-MM-YYYY" /> <span
							class="input-group-addon add-on"><i
							class="glyphicon glyphicon-calendar"></i></span>
					</div>
					<p class="help-block form-field-error"><form:errors path="leftOn" /></p>
				</div>
			</div>
			<div class="form-group">
				<form:label cssClass="col-md-2 control-label" path="designation">Designation</form:label>
				<div class="col-md-8">
					<form:input path="designation" placeholder="Designation"
						cssClass="form-control" />
					<p class="help-block form-field-error"><form:errors path="designation" /></p>
				</div>
			</div>
		</fieldset>

		<div class="form-group">
			<div class="col-md-4 col-md-offset-2">
				<button type="submit" class="btn btn-primary">Save Profile</button>
			</div>
		</div>
	</div>
	<div class="col-md-3 col-md-offset-1">
		<div class="well" id="employee-photo-container">Employee Photograph</div>
	</div>
</form:form>
