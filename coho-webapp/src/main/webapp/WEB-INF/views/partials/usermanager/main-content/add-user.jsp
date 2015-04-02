<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page import="com.cagiris.coho.service.api.UserRole"%>
<h1 class="page-header">Add User</h1>
<core:url value="/add-user" var="addUserUrl" />
<f:form class="form-horizontal form-coho" action="${addUserUrl}" modelAttribute="userBean" method="POST">
    <div class="form-group">
        <label for="userId" class="control-label col-xs-2">User Id</label>
        <div class="col-xs-6">
            <f:input type="text" class="form-control" id="userId" path="userId" />
        </div>
        <div class="col-xs-4"></div>
    </div>
    <div class="form-group">
        <label for="userName" class="control-label col-xs-2">Name</label>
        <div class="col-xs-6">
            <f:input type="text" class="form-control" id="userId" path="userName" />
        </div>
        <div class="col-xs-4"></div>
    </div>
    
    <div class="form-group">
        <label for="userType" class="control-label col-xs-2">User Type</label>
        <div class="col-xs-6"></div>
          <f:select id="creditingSchedule" path="userType">
              <%
              UserRole[] userRoles= (UserRole[])request.getAttribute("userTypes");
                System.out.println("yo");
              for(int i=0;i<userRoles.length;i++){
                  UserRole userRole = userRoles[i];
                  %>
                  <option> <%= userRole.toString() %></option>
                  <%
              }
              %>
          </f:select>
        </div>
    <div class="col-xs-4">
    </div>
    
    <div class="form-group">
        <label for="password" class="control-label col-xs-2">Password</label>
        <div class="col-xs-6">
            <f:input type="password" class="form-control" id="password" path="password" />
        </div>
        <div class="col-xs-4"></div>
    </div>
    
	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<button type="submit" class="btn btn-primary">Apply</button>
		</div>
	</div>
</f:form>


