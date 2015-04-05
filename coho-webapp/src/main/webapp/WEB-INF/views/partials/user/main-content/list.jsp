<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">List of users</h1>

<div class="row">
    <div class="table-responsive">
		<table class="table table-bordered table-results" id="list-table">
            <thead>
                <tr>
                    <th>User Id</th>
                    <th>User Name</th>
                    <th>User Role</th>
                    <th>Operation</th>
                </tr>
            </thead>
			<tbody>
				<c:forEach items="${userBeanList}" var="userBean">
					<c:set var="userId" value="${userBean.userId}" scope="request"/>
					
				    <tr id="row-${userBean.userId}">      
				        <td>${userBean.userId}</td>
				        <td>${userBean.userName}</td>
				        <td>${userBean.userRole}</td>
				        <td><tiles:insertAttribute name="user-management-panel" /></td>
				    </tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
</div>