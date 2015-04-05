<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">List of Leave Requests</h1>

<div class="row">
    <div class="table-responsive">
		<table class="table table-bordered table-results" id="list-table">
            <thead>
                <tr>
                    <th>Subject</th>
                    <th>Description</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Status</th>
                    <th>Leave Count</th>
                </tr>
            </thead>
			<tbody>
				<c:forEach items="${leaveRequestBeanList}" var="leaveRequestBean">
					<c:set var="userId" value="${userBean.userId}" scope="request"/>
					
				    <tr id="row-${leaveRequestBean.leaveApplicationId}">
				    	<td>${leaveRequestBean.requestSubject}</td>      
				        <td>${leaveRequestBean.requestDescription}</td>
				        <td>${leaveRequestBean.leaveStartDate}</td>
				        <td>${leaveRequestBean.leaveEndDate}</td>
				        <td>${leaveRequestBean.leaveRequestStatus}</td>
				        <td>${leaveRequestBean.leaveCount}</td>
				    </tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
</div>