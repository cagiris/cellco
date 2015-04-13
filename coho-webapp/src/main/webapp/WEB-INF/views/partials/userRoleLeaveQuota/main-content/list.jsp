<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">Shift Details Configuration</h1>
<tiles:insertAttribute name="feedback-msg" />
<form class="form-horizontal form-coho" id="createUserForm">

	<core:forEach items="${userRoleLeaveQuotaBeans}"
		var="userRoleLeaveQuotaBean">
		<div>
		<div class="form-group">
			<label class="control-label col-xs-3">User Role:</label>
			<div class="col-md-5">
				<p class="form-control-static">${userRoleLeaveQuotaBean.userRole}</p>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-xs-3">Leave Accumulation Policy:</label>
			<div class="col-md-5">
				<select class="form-control">
					<core:forEach var="policy" items="${leaveAccumulationPolicies}">
						<core:choose>
						<core:when test="${policy == userRoleLeaveQuotaBean.leaveAccumulationPolicy}">
							<option selected>${policy}</option>
						</core:when>
						<core:otherwise>
							<option>${policy}</option>
						</core:otherwise>
						</core:choose>
					</core:forEach>
				</select>
			</div>
		</div>
		
		<core:forEach items="${userRoleLeaveQuotaBean.leaveTypeVsLeaveCount}" var="entry">
			<div class="form-group">
				<label class="${entry.key} control-label col-xs-3">${entry.key.val}:</label>
				<div class="col-md-5">
					<input name="${entry.key}" type ="text" class="form-control-static" value="${entry.value}" />
				</div>
			</div>
		</core:forEach>
		
		<div>
			<button type="button" class="userRoleLeaveQuota btn btn-primary" >Update</button>
			<input type="hidden" value="${userRoleLeaveQuotaBean.userLeaveQuotaId}" />
		</div>
		</div>
	</core:forEach>

</form>