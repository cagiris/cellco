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

<div class="panel panel-default">
	<div class="panel-heading" role="tab" id="heading${passengerIndex}">
		<h4 class="panel-title">
			<a class="collapsed" data-toggle="collapse" data-parent="#accordion"
				href="#passenger${passengerIndex}" aria-expanded="false"
				aria-controls="passenger${passengerIndex}"> 
				Passenger 
			</a> <a href="#"
				class="btn btn-default btn-xs pull-right remove-passenger"
				title="Remove Passenger"> <span
				class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</a>
		</h4>
	</div>
	<div id="passenger${passengerIndex}" class="panel-collapse collapse" role="tabpanel"
		aria-labelledby="headingTwo">
		<div class="panel-body">
			<div class="panel-body">
				<div class="form-group">
					<label class="col-md-2 control-label" >First Name</label>
					<div class="col-md-5 controls">
						<input type="text" id="firstName${passengerIndex}"
							placeholder="First Name" class="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" >Middle Name</label>
					<div class="col-md-5">
						<input type="text" placeholder="Middle Name" class="form-control"
							id="middleName${passengerIndex}" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-2 control-label" >Last Name</label>
					<div class="col-md-5">
						<input type="text" placeholder="Last Name" class="form-control"
							id="lastName${passengerIndex}" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" >Type</label>
					<div class="col-md-5 controls">
						<select id="type${passengerIndex}" class="form-control">
							<core:forEach var="type" items="${passengerTypeList}">
								<option>${type}</option>
							</core:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label" >Date of Birth</label>
					<div class="col-md-5">
						<div class="input-group date form-datepicker"
							data-date="<%=currentDate%>" data-date-format="dd-mm-yyyy">
							<input type="text" id="dateOfBirth${passengerIndex}"
								class="form-control" placeholder="dd-MM-YYYY" />
							<span class="input-group-addon add-on"><i
								class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
