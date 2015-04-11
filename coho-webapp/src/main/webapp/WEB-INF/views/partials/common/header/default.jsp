<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% 
	String userName = SecurityContextHolder.getContext().getAuthentication().getName();
%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<core:url value = '/' />"> - | COHO | -</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<core:url value = '/dashboard' />"> <span class="glyphicon glyphicon-home"
						aria-hidden="false"></span> Dashboard
				</a></li>
			<sec:authorize access="hasRole('ADMIN')">
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"> Holiday Manager <span
						class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
						<sec:authorize access="hasRole('ADMIN')">
							<li><a href="<core:url value = '/holiday/list' />"> <span 
							        class="glyphicon glyphicon-th-list" aria-hidden="false"></span>
									Holiday List
							</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<li><a href="<core:url value = '/holiday/create-annual' />"> <span 
							        class="glyphicon glyphicon-th-list" aria-hidden="false"></span>
									Add Holiday
							</a></li>
						</sec:authorize>
					</ul></li>


			</sec:authorize>
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"> Leave Manager <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<sec:authorize access="hasRole('AGENT')">
							<li><a href="<core:url value = '/leave/create' />"> <span class="glyphicon glyphicon-pencil"
									aria-hidden="false"></span> Apply Leave
							</a></li>
							<li><a href="<core:url value = '/leave/list' />"> <span
									class="glyphicon glyphicon-th-list" aria-hidden="false"></span>
									My Leave List
							</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<li><a href="<core:url value = '/leave/list' />"> <span
									class="glyphicon glyphicon-th-list" aria-hidden="false"></span>
									Leaves List
							</a></li>
							<li><a href="<core:url value = '/leave/pending' />"> <span 
							        class="badge" id="leave-pending-approvals-badge">5</span>
									Pending Approvals
							</a></li>
						</sec:authorize>
					</ul></li>
				<sec:authorize access="hasRole('ADMIN')">
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"
						role="button" aria-expanded="false"> User Manager <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<core:url value = '/user/create' />"> <span class="glyphicon glyphicon-plus"
									aria-hidden="false"></span> Add User
							</a></li>
							<li><a href="<core:url value = '/user-profile/list' />"> <span
									class="glyphicon glyphicon-th-list" aria-hidden="false"></span>
									User List
							</a></li>
						</ul></li>
					<li role="presentation" class="dropdown"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"
						role="button" aria-expanded="false"> Shift Details <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<core:url value = '/teamShiftDetails/get/1' />"> <span class="glyphicon glyphicon-cog"
									aria-hidden="false"></span> Shift Configuration
							</a></li>
							<li><a href="<core:url value = '/user-statistics' />"> <span
									class="glyphicon glyphicon-road" aria-hidden="false"></span>
									Shift History
							</a></li>
							<li><a href="<core:url value = '/user-runtime-statistics' />"> <span class="glyphicon glyphicon-hourglass"
									aria-hidden="false"></span> Active Shift Details
							</a></li>
						</ul></li>
				</sec:authorize>
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-expanded="false"> <%=userName %> <span
						class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<core:url value = '/user-profile' />"> <span class="glyphicon glyphicon-user"
								aria-hidden="false"></span> Profile
						</a></li>
						<li><a href="<core:url value = '/j_spring_security_logout' />"> <span class="glyphicon glyphicon-off"
								aria-hidden="false"></span> Log Out
						</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>