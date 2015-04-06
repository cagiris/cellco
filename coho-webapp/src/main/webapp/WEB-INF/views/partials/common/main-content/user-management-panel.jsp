<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pull-left">
	<a href="get/${ userId }" class="btn btn-success btn-xs" title="Show details"> <span
		class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
	</a> 
	<!--
	<a href="update.html?entityId=${ userId }" class="btn btn-warning btn-xs" title="Update"> <span
		class="glyphicon glyphicon glyphicon-cog" aria-hidden="true"></span>
	-->
	<a href="update/${ userId }" class="btn btn-warning btn-xs" title="Update"> <span
		class="glyphicon glyphicon glyphicon-cog" aria-hidden="true"></span>
	</a> 
	<!--
	<a href="#" class="btn btn-danger btn-xs ajax-delete" title="Remove User"> <span
		class="glyphicon glyphicon-remove" aria-hidden="true"></span>
	</a>
	-->
	<a href="#" class="btn btn-danger btn-xs btn-primary btn-lg" data-toggle="modal" 
		data-target="#myModal" title="Remove Users" data-whatever="${ userId }"> <span
		class="glyphicon glyphicon-remove" aria-hidden="true"  ></span>
	</a>
</div>
