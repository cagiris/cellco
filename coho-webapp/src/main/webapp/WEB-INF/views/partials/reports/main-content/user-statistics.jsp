<%@page import="com.cagiris.coho.controller.ShiftInfoController"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h1 class="page-header">User Shift Statistics</h1>

<div class="row">
	<div class="table-responsive">
		<table class="table table-bordered table-results" id="list-table">
			<thead>
				<tr>
					<th>User Id</th>
					<th>Shift Start Time</th>
					<th>Shift End Time</th>
					<th>Shift Duration</th>
					<th>Shift Start Reason</th>
					<th>Shift End Reason</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${shifInfoBeans}" var="shifInfoBean">
					<c:set var="userShiftId" value="${shifInfoBean.shiftId}"
						scope="request" />

					<tr id="row-${shifInfoBean.shiftId}">
						<td>${shifInfoBean.userId}</td>

						<td class="shiftStartTime">
							<div class="form-group">
								<div class='input-group date datetimepicker'>
									<input type='text' value="${shifInfoBean.shiftStartTime}" class="form-control" /> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div> <!--
						<input type="text" value="${shifInfoBean.shiftStartTime}" />
						-->
						</td>

						<td class="shiftEndTime">
							<div class="form-group">
								<div class='input-group date datetimepicker'>
									<input type='text' value="${shifInfoBean.shiftEndTime}" class="form-control"/> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						<!--  
						<input type="text" value="${shifInfoBean.shiftEndTime}" />
						-->
						</td>

						<td>${shifInfoBean.shiftDuration}</td>
						<td>${shifInfoBean.shiftStartReason}</td>
						<td>${shifInfoBean.shiftEndReason}</td>
						<td>
							<div class="pull-right">
								<a id="modifyUserShiftButton" href="#"
									class="btn btn-success btn-xs" title="Edit"> <span
									class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
									<input value="${shifInfoBean.shiftId}" type="hidden" />
								</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	<!-- To show calender dropdown -->
	<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />

	</div>
	
</div>

