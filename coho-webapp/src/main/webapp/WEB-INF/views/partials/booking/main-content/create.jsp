<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String currentDate = simpleDateFormat.format(new Date());
%>

<h1 class="page-header">New booking</h1>

<jsp:useBean id="errorMap" class="java.util.HashMap" />
<spring:hasBindErrors name="bookingDetailsBean">
	<core:forEach var="error" items="${errors.fieldErrors}">
		<core:set target="${errorMap}" property="${ error.field }" value="has-error"/>
	</core:forEach>
</spring:hasBindErrors>

<form:form commandName="bookingDetailsBean" cssClass="form-horizontal" role="form">

	<tiles:insertAttribute name="feedback-msg" />

	<input type="hidden" value="${ userBean.userId }" />

	<div class="col-md-8">
		<fieldset>
			<legend>Passenger Details</legend>
			<div
				class="form-group <core:out value='${errorMap["passengers[0].firstName"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="passengers[0].firstName">First Name</form:label>
				<div class="col-md-5 controls">
					<form:input path="passengers[0].firstName" placeholder="First Name"
						cssClass="form-control" />
					<p class="help-block form-field-error">
						<form:errors path="passengers[0].firstName" />
					</p>
				</div>
			</div>

			<div
				class="form-group <core:out value='${errorMap["passengers[0].middleName"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="passengers[0].middleName">Middle Name</form:label>
				<div class="col-md-5">
					<form:input placeholder="Middle Name" cssClass="form-control"
						path="passengers[0].middleName" />
					<p class="help-block form-field-error">
						<form:errors path="passengers[0].middleName" />
					</p>
				</div>
			</div>

			<div
				class="form-group <core:out value='${errorMap["passengers[0].lastName"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="passengers[0].lastName">Last Name</form:label>
				<div class="col-md-5">
					<form:input placeholder="Last Name" cssClass="form-control"
						path="passengers[0].lastName" />
					<p class="help-block form-field-error">
						<form:errors path="passengers[0].lastName" />
					</p>
				</div>
			</div>
			<div class="form-group <core:out value='${errorMap["passengers[0].type"]}'/>">
				<form:label cssClass="col-md-2 control-label" path="passengers[0].type">Type</form:label>
				<div class="col-md-5 controls">
					<form:select path="passengers[0].type" cssClass="form-control">
						<core:forEach var="type" items="${passengerTypeList}">
							<option>${type}</option>
						</core:forEach>
					</form:select>
					<p class="help-block form-field-error">
						<form:errors path="passengers[0].type" />
					</p>
				</div>
			</div>
			<div class="form-group <core:out value='${errorMap["passengers[0].dateOfBirth"]}'/>">
				<form:label cssClass="col-md-2 control-label" path="passengers[0].dateOfBirth">Date of Birth</form:label>
				<div class="col-md-5">
					<div class="input-group date form-datepicker"
						data-date="<%=currentDate%>" data-date-format="dd-mm-yyyy">
						<form:input path="passengers[0].dateOfBirth" cssClass="form-control" placeholder="dd-MM-YYYY" /> 
						<span class="input-group-addon add-on"><i
							class="glyphicon glyphicon-calendar"></i></span>
					</div>
					<p class="help-block form-field-error"><form:errors path="passengers[0].dateOfBirth" /></p>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>Booking Details</legend>
			<div class="form-group <core:out value='${errorMap["bookingGDSType"]}'/>">
				<form:label cssClass="col-md-2 control-label" path="bookingGDSType">Type</form:label>
				<div class="col-md-5 controls">
					<form:select path="bookingGDSType" cssClass="form-control">
						<core:forEach var="type" items="${bookingGDSTypeList}">
							<option>${type}</option>
						</core:forEach>
					</form:select>
					<p class="help-block form-field-error">
						<form:errors path="bookingGDSType" />
					</p>
				</div>
			</div>
			<div
				class="form-group <core:out value='${errorMap["baseFare"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="baseFare">Base Fare</form:label>
				<div class="col-md-5 controls">
					<form:input path="baseFare" placeholder="Base Fare"
						cssClass="form-control" />
					<p class="help-block form-field-error">
						<form:errors path="baseFare" />
					</p>
				</div>
			</div>
			<div
				class="form-group <core:out value='${errorMap["taxesAndServiceFee"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="taxesAndServiceFee">T & S</form:label>
				<div class="col-md-5 controls">
					<form:input path="taxesAndServiceFee" placeholder="Taxes And Services Fee"
						cssClass="form-control" />
					<p class="help-block form-field-error">
						<form:errors path="taxesAndServiceFee" />
					</p>
				</div>
			</div>
			<div
				class="form-group <core:out value='${errorMap["miscellaneousCharges"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="miscellaneousCharges">MCO</form:label>
				<div class="col-md-5 controls">
					<form:input path="miscellaneousCharges" placeholder="Miscellaneous Charges"
						cssClass="form-control" />
					<p class="help-block form-field-error">
						<form:errors path="miscellaneousCharges" />
					</p>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>Customer Details</legend>

			<div
				class="form-group <core:out value='${errorMap["customer.firstName"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="customer.firstName">First Name</form:label>
				<div class="col-md-5 controls">
					<form:input path="customer.firstName" placeholder="First Name"
						cssClass="form-control" />
					<p class="help-block form-field-error">
						<form:errors path="customer.firstName" />
					</p>
				</div>
			</div>

			<div
				class="form-group <core:out value='${errorMap["customer.middleName"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="customer.middleName">Middle Name</form:label>
				<div class="col-md-5">
					<form:input placeholder="Middle Name" cssClass="form-control"
						path="customer.middleName" />
					<p class="help-block form-field-error">
						<form:errors path="customer.middleName" />
					</p>
				</div>
			</div>

			<div
				class="form-group <core:out value='${errorMap["customer.lastName"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="customer.lastName">Last Name</form:label>
				<div class="col-md-5">
					<form:input placeholder="Last Name" cssClass="form-control"
						path="customer.lastName" />
					<p class="help-block form-field-error">
						<form:errors path="customer.lastName" />
					</p>
				</div>
			</div>
			<fieldset>
				<legend>Customer Contact</legend>
				<div
					class="form-group <core:out value='${errorMap["customer.contactNumber"]}'/>">
					<form:label cssClass="col-md-2 control-label"
						path="customer.contactNumber">Contact</form:label>
					<div class="col-md-6">
						<form:input placeholder="Contact Number" cssClass="form-control"
							path="customer.contactNumber" />
						<p class="help-block form-field-error">
							<form:errors path="customer.contactNumber" />
						</p>
					</div>
				</div>
				<div
					class="form-group <core:out value='${errorMap["customer.emailId"]}'/>">
					<form:label cssClass="col-md-2 control-label"
						path="customer.emailId">E-mail ID</form:label>
					<div class="col-md-6">
						<form:input placeholder="E-mail ID" cssClass="form-control"
							path="customer.emailId" />
						<p class="help-block form-field-error">
							<form:errors path="customer.emailId" />
						</p>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>Customer Address</legend>
				<div
					class="form-group <core:out value='${errorMap["customer.addressLine1"]}'/>">
					<form:label cssClass="col-md-2 control-label"
						path="customer.addressLine1">Line 1</form:label>
					<div class="col-md-8">
						<form:input path="customer.addressLine1"
							placeholder="Address Line 1" cssClass="form-control" />
						<p class="help-block form-field-error">
							<form:errors path="customer.addressLine1" />
						</p>
					</div>
				</div>
	
				<div
					class="form-group <core:out value='${errorMap["customer.addressLine2"]}'/>">
					<form:label cssClass="col-md-2 control-label"
						path="customer.addressLine2">Line 2</form:label>
					<div class="col-md-8">
						<form:input placeholder="Address Line 2" cssClass="form-control"
							path="customer.addressLine2" />
						<p class="help-block form-field-error">
							<form:errors path="customer.addressLine2" />
						</p>
					</div>
				</div>
	
				<div
					class="form-group <core:out value='${errorMap["customer.city"]}'/>">
					<form:label cssClass="col-md-2 control-label" path="customer.city">City</form:label>
					<div class="col-md-8">
						<form:input placeholder="City" cssClass="form-control"
							path="customer.city" />
						<p class="help-block form-field-error">
							<form:errors path="customer.city" />
						</p>
					</div>
				</div>
	
				<div class="form-group">
					<form:label cssClass="col-md-2 control-label" path="customer.state">State</form:label>
					<div
						class="col-sm-3 <core:out value='${errorMap["customer.state"]}'/>">
						<form:input path="customer.state" placeholder="State"
							cssClass="form-control" />
						<p class="help-block form-field-error">
							<form:errors path="customer.state" />
						</p>
					</div>
	
					<form:label cssClass="col-md-2 control-label" path="customer.pincode">Pin Code</form:label>
					<div
						class="col-sm-3 <core:out value='${errorMap["customer.pincode"]}'/>">
						<form:input path="customer.pincode" placeholder="Pin Code"
							cssClass="form-control" />
						<p class="help-block form-field-error">
							<form:errors path="customer.pincode" />
						</p>
					</div>
				</div>
	
				<div
					class="form-group <core:out value='${errorMap["customer.country"]}'/>">
					<form:label cssClass="col-md-2 control-label"
						path="customer.country">Country</form:label>
					<div class="col-md-8">
						<form:input path="customer.country" placeholder="Country"
							cssClass="form-control" />
						<p class="help-block form-field-error">
							<form:errors path="customer.country" />
						</p>
					</div>
				</div>
			</fieldset>
		</fieldset>
		<div class="form-group">
			<div class="col-md-4 col-md-offset-2">
				<button type="submit" class="btn btn-primary">Save Booking</button>
			</div>
		</div>
	</div>
</form:form>
