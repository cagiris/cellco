<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h1 class="page-header">User Runtime Statistics</h1>

<div class="row">
	<div class="table-responsive">
		<table class="table table-bordered table-results" id="list-table">
			<thead>
				<tr>
					<th>User Id</th>
					<th>Shift Start Time</th>
					<th>Shift Active Duration</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${activeShifInfoBeans}" var="activeShifInfoBean">
					<c:set var="userShiftId" value="${activeShifInfoBean.shiftId}" scope="request" />

					<tr id="row-${activeShifInfoBean.shiftId}">
						<td>${activeShifInfoBean.userId}</td>
						<td>${activeShifInfoBean.shiftStartTime}</td>
						<td>${activeShifInfoBean.shiftDuration}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>