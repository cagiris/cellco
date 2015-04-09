<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">List of users</h1>

<div class="row">

	<tiles:insertAttribute name="feedback-msg" />
	
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


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove User</h4>
      </div>
      <div class="modal-body">
      	<div class="modal-body-content">
        	Do you want to remove user?
        </div>
      	<div class="modal-body-content-ret">
      	</div>
        <input type="hidden" class="form-control" id="recipient-name">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary ajax-delete">Delete</button>
        <button type="button" class="btn btn-default popup-close" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
