<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title" id="change-password-modal-title">
				<core:if test="${successMsg != null}">
					Success!
				</core:if>

				<core:if test="${errorMsg != null}">
					Error!
				</core:if>
			</h4>
		</div>
		<div class="modal-body">
			<core:if test="${successMsg != null}">
				<p class="col-xs-offset-2 col-xs-8 success-msg">* ${ successMsg }</p>
				<div class="clearfix"></div>
			</core:if>

			<core:if test="${errorMsg != null}">
				<p class="col-xs-offset-2 col-xs-8 error-msg">* ${ errorMsg }</p>
				<div class="clearfix"></div>
			</core:if>
		</div>
	</div>
</div>
