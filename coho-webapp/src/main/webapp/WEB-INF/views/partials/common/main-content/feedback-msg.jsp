<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:if test="${successMsg != null}">
	<p class="col-xs-offset-2 col-xs-8 success-msg">* ${ successMsg }</p>
	<div class="clearfix"></div>
</core:if>

<core:if test="${errorMsg != null}">
	<p class="col-xs-offset-2 col-xs-8 error-msg">* ${ errorMsg }</p>
	<div class="clearfix"></div>
</core:if>
