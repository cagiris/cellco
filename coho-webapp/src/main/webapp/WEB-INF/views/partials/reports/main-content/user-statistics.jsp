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
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${shifInfoBeans}" var="shifInfoBean">
					<c:set var="userShiftId" value="${shifInfoBean.shiftId}" scope="request" />

					<tr id="row-${shifInfoBean.shiftId}">
						<td>${shifInfoBean.userId}</td>
						<td>${shifInfoBean.shiftStartTime}</td>
						<td>${shifInfoBean.shiftEndTime}</td>
						<td>${shifInfoBean.shiftDuration}</td>
						<td>${shifInfoBean.shiftStartReason}</td>
						<td>${shifInfoBean.shiftEndReason}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>