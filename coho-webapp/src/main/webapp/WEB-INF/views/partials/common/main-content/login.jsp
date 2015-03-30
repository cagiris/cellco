<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-signin" action="<core:url value = '/login' />" method="POST">
	<h3 class="form-signin-heading">Please sign in</h3>
	
	<label for="userName" class="sr-only">User Name</label>
	<input name="userName" type="text" id="userName" class="form-control" placeholder="User Name" required autofocus> 
	
    <label for="password" class="sr-only">Password</label>
    <input name="password" type="password" id="password" class="form-control" placeholder="Password" required>
	
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
