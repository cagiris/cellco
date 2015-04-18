<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String currentDate = simpleDateFormat.format(new Date());
%>

<h1 class="page-header">New booking <div class="loading pull-right"></div></h1>

<jsp:useBean id="errorMap" class="java.util.HashMap" />
<spring:hasBindErrors name="bookingDetailsBean">
	<core:forEach var="error" items="${errors.fieldErrors}" >
		<core:set target="${errorMap}" property="${ error.field }" value="has-error"/>
	</core:forEach>
</spring:hasBindErrors>

<form:form commandName="bookingDetailsBean" cssClass="form-horizontal" role="form">

	<tiles:insertAttribute name="feedback-msg" />

	<input type="hidden" value="${ userBean.userId }" />

	<div class="col-md-8">
		<fieldset>
			<legend>
				Passenger Details 
				<a href="#" class="btn btn-default btn-xs pull-right" title="Add Passenger" onclick="addPasssenger();"> 
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</a>
			</legend>

			<div class="panel-group" id="passenger-accordion" role="tablist"
				aria-multiselectable="true">

				<core:forEach var="passenger" items="${bookingDetailsBean.passengers}" varStatus="count">

					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="heading${count.index}">
							<h4 class="panel-title">
								<a class="collapsed" data-toggle="collapse"
									data-parent="#passenger-accordion"
									href="#passenger${count.index}" aria-expanded="true"
									aria-controls="passenger${count.index}"> Passenger </a> 
									
								<a href="#" class="btn btn-default btn-xs pull-right remove-passenger"
									title="Remove Passenger" > <span
									class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</a>
							</h4>
						</div>
						<div id="passenger${count.index}" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="heading${count.index}">
							<div class="panel-body">
								<core:set var="passengerFirstName"
									value="passengers[${count.index}].firstName" scope="page" />
								<div
									class="form-group <core:out value='${errorMap[passengerFirstName]}'/>">
									<form:label cssClass="col-md-2 control-label"
										path="${passengerFirstName}">First Name</form:label>
									<div class="col-md-5 controls">
										<form:input path="${passengerFirstName}"
											placeholder="First Name" cssClass="form-control passenger-firstName" />
										<p class="help-block form-field-error">
											<form:errors path="${passengerFirstName}" />
										</p>
									</div>
								</div>

								<core:set var="passengerMiddleName"
									value="passengers[${count.index}].middleName" scope="page" />
								<div
									class="form-group <core:out value='${errorMap[passengerMiddleName]}'/>">
									<form:label cssClass="col-md-2 control-label"
										path="${passengerMiddleName}">Middle Name</form:label>
									<div class="col-md-5">
										<form:input placeholder="Middle Name" cssClass="form-control passenger-middleName"
											path="${passengerMiddleName}" />
										<p class="help-block form-field-error">
											<form:errors path="${passengerMiddleName}" />
										</p>
									</div>
								</div>

								<core:set var="passengerLastName"
									value="passengers[${count.index}].lastName" scope="page" />
								<div
									class="form-group <core:out value='${errorMap[passengerLastName]}'/>">
									<form:label cssClass="col-md-2 control-label"
										path="${passengerLastName}">Last Name</form:label>
									<div class="col-md-5">
										<form:input placeholder="Last Name" cssClass="form-control passenger-lastName"
											path="${passengerLastName}" />
										<p class="help-block form-field-error">
											<form:errors path="${passengerLastName}" />
										</p>
									</div>
								</div>

								<core:set var="passengerType"
									value="passengers[${count.index}].type" scope="page" />
								<div
									class="form-group <core:out value='${errorMap[passengerType]}'/>">
									<form:label cssClass="col-md-2 control-label"
										path="${passengerType}">Type</form:label>
									<div class="col-md-5 controls">
										<form:select path="${passengerType}" cssClass="form-control passenger-type">
											<core:forEach var="type" items="${passengerTypeList}">
												<option>${type}</option>
											</core:forEach>
										</form:select>
										<p class="help-block form-field-error">
											<form:errors path="${passengerType}" />
										</p>
									</div>
								</div>

								<core:set var="passengerdateOfBirth"
									value="passengers[${count.index}].dateOfBirth" scope="page" />
								<div
									class="form-group <core:out value='${errorMap[passengerdateOfBirth]}'/>">
									<form:label cssClass="col-md-2 control-label"
										path="${passengerdateOfBirth}">Date of Birth</form:label>
									<div class="col-md-5">
										<div class="input-group date form-datepicker"
											data-date="<%=currentDate%>" data-date-format="yyyy-mm-dd">
											<form:input path="${passengerdateOfBirth}"
												cssClass="form-control passenger-dateOfBirth" placeholder="yyyy-mm-dd" />
											<span class="input-group-addon add-on"><i
												class="glyphicon glyphicon-calendar"></i></span>
										</div>
										<p class="help-block form-field-error">
											<form:errors path="${passengerdateOfBirth}" />
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</core:forEach>

			</div>
		</fieldset>
		<fieldset>
			<legend>Booking Details</legend>
			<div
				class="form-group <core:out value='${errorMap["pnr"]}'/>">
				<form:label cssClass="col-md-2 control-label"
					path="pnr">PNR</form:label>
				<div class="col-md-5 controls">
					<form:input path="pnr" placeholder="PNR"
						cssClass="form-control" />
					<p class="help-block form-field-error">
						<form:errors path="pnr" />
					</p>
				</div>
			</div>
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
	</div>
</form:form>

<div class="col-md-4 col-md-offset-2">
	<a href="#" class="btn btn-primary" title="Save Booking"
		onclick="saveBooking();"> <span class="glyphicon glyphicon-ok"
		aria-hidden="true"></span>
		Save Booking
	</a>
	<div class="loading col-md-4"></div>
</div>