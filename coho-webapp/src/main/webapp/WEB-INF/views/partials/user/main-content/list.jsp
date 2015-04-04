<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="page-header">List of users</h1>

<div class="row">
    <div class="table-responsive">
		<table class="table table-bordered table-results">
            <thead>
                <tr>
                    <th>User Id</th>
                    <th>User Name</th>
                    <th>User Role</th>
                </tr>
            </thead>
			<tbody>
				<c:forEach items="${userBeanList}" var="userBean">
				    <tr>      
				        <td>${userBean.userId}</td>
				        <td>${userBean.userName}</td>
				        <td>${userBean.userRole}</td>
				    </tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
</div>

