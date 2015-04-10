<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h1 class="page-header">Pending Approvals</h1>

<c:forEach items="${leaveRequestBeanList}" var="leaveRequestBean">
	<div class="panel panel-danger" id="pending-${leaveRequestBean.leaveApplicationId}">
		<div class="panel-heading clearfix">
			<div id="pending-user">${leaveRequestBean.userId}</div>
			<!--
		<input type="hidden" name=${leaveRequestBean.leaveApplicationId}>
			-->
		</div>
		<div class="pull-right">
			<a href="#" class="pending-approve "
				class="btn btn-success btn-xs ajax-approve" title="Approve"" > <span
				class="glyphicon glyphicon-thumbs-up" aria-hidden="true"> <input
					type="hidden" value="${leaveRequestBean.leaveApplicationId}">
			</span>
			</a> <a href="#" class="pending-hold"
				class="btn btn-warning btn-xs ajax-hold" title="On Hold"> <span
				class="glyphicon glyphicon-info-sign" aria-hidden="true"> <input
					type="hidden" value="${leaveRequestBean.leaveApplicationId}">
			</span>
			</a> <a href="#" class="pending-cancel"
				class="btn btn-danger btn-xs ajax-cancel" title="Cancel"> <span
				class="glyphicon glyphicon-thumbs-down" aria-hidden="true"> <input
					type="hidden" value="${leaveRequestBean.leaveApplicationId}">
			</span>
			</a>
		</div>

		<div class="panel-body">
			<dl class="dl-horizontal">
				<dt>Employee Description :</dt>
				<dd>${leaveRequestBean.userId}</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>Leave duration :</dt>
				<dd>
					<mark>${leaveRequestBean.leaveCount}</mark>
					Leave starting from
					<mark>${leaveRequestBean.leaveStartDate} to
						${leaveRequestBean.leaveEndDate}</mark>
				</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>Subject :</dt>
				<dd>${leaveRequestBean.requestSubject}</dd>
			</dl>
			<dl class="dl-horizontal">
				<dt>Detailed Description :</dt>
				<dd>${leaveRequestBean.requestDescription}</dd>
			</dl>
			<dl class="dl-horizontal" id="pending-status-${leaveRequestBean.leaveApplicationId}">
				<dt>Status :</dt>
				<dd>${leaveRequestBean.leaveRequestStatus}</dd>
			</dl>

		</div>


	</div>
</c:forEach>

<!-- Modal -->
<div class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove User</h4>
			</div>
			<div class="modal-body">
				<div class="modal-body-content">Do you want to remove user?</div>
				<div class="modal-body-content-ret"></div>
				<input type="hidden" class="form-control" id="recipient-name">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary ajax-delete">Delete</button>
				<button type="button" class="btn btn-default popup-close"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="pending-approval-cont" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove User</h4>
			</div>
			<div class="modal-body">
				<div class="modal-body-content">Do you want to remove user?</div>
				<div class="modal-body-content-ret"></div>
				<input type="hidden" class="form-control" id="recipient-name">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary ajax-delete">Delete</button>
				<button type="button" class="btn btn-default popup-close"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="pending-approval-cont" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Remove User</h4>
			</div>
			<div class="modal-body">
				<div class="modal-body-content">Do you want to remove user?</div>
				<div class="modal-body-content-ret"></div>
				<input type="hidden" class="form-control" id="recipient-name">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary ajax-delete">Delete</button>
				<button type="button" class="btn btn-default popup-close"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>







