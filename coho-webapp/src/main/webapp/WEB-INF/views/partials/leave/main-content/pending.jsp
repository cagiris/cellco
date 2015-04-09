<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h1 class="page-header">Pending Approvals</h1>

<!-- 
<div class="panel panel-danger">
	<div class="panel-heading clearfix">
		Ashissshsshhh : Need leaves, as not able work because of broken hand
		<div class="pull-right" id="pending-approval-cont">
			<a href="#" class="btn btn-success btn-xs ajax-approve" title="Approve"> <span
				class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
			</a> <a href="#" class="btn btn-warning btn-xs ajax-hold" title="On Hold"> <span
				class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
			</a> <a href="#" class="btn btn-danger btn-xs ajax-cancel" title="Cancel"> <span
				class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<dl class="dl-horizontal">
			<dt>Employee Description :</dt>
			<dd>Ashish Jindal (2069)</dd>
		</dl>
		<dl class="dl-horizontal">
			<dt>Leave duration :</dt>
			<dd>
				<mark>31 days</mark>
				Leave starting from
				<mark>12th December 2012 to 12th January 2013</mark>
			</dd>
		</dl>
		<dl class="dl-horizontal">
			<dt>Subject :</dt>
			<dd>Need leaves, as not able work because of broken hand</dd>
		</dl>
		<dl class="dl-horizontal">
			<dt>Detailed Description :</dt>
			<dd>-----------------------------------------------------</dd>
		</dl>
	</div>
</div>

<div class="panel panel-primary">
	<div class="panel-heading clearfix">
		Akhil : Needs to go out for fun@Goa
		<div class="pull-right">
			<a href="#" class="btn btn-success btn-xs" title="Approve"> <span
				class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
			</a> <a href="#" class="btn btn-warning btn-xs" title="On Hold"> <span
				class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
			</a> <a href="#" class="btn btn-danger btn-xs" title="Cancel"> <span
				class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<dl class="dl-horizontal">
			<dt>Employee Description :</dt>
			<dd>Akhil Sood (xyz)</dd>
		</dl>
		<dl class="dl-horizontal">
			<dt>Leave duration :</dt>
			<dd>
				<mark>7 days</mark>
				Leave starting from
				<mark>1st December 2012 to 7 December 2012</mark>
			</dd>
		</dl>
		<dl class="dl-horizontal">
			<dt>Subject :</dt>
			<dd>Needs to go out for fun@Goa</dd>
		</dl>
		<dl class="dl-horizontal">
			<dt>Detailed Description :</dt>
			<dd>Fun</dd>
		</dl>
	</div>
</div>
-->

hello
<c:forEach items="${leaveRequestBeanList}" var="leaveRequestBean">
	<div class="panel panel-danger">
		<div class="panel-heading clearfix">
			<div id="pending-user">
				${leaveRequestBean.userId}
			</div>
			<!--
			${leaveRequestBean.requestSubject}
			-->
		</div>
		<div class="pull-right" >
			<a href="#" id="pending-approve" class="btn btn-success btn-xs ajax-approve" title="Approve" > <span
				class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
			</a> <a href="#" id="pending-hold" class="btn btn-warning btn-xs ajax-hold" title="On Hold" > <span
				class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
			</a> <a href="#" id="pending-cancel" class="btn btn-danger btn-xs ajax-cancel" title="Cancel" > <span
				class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
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
				<mark>${leaveRequestBean.leaveStartDate} to ${leaveRequestBean.leaveEndDate}</mark>
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
		<dl class="dl-horizontal">
			<dt>Status :</dt>
			<dd>${leaveRequestBean.leaveRequestStatus}</dd>
		</dl>

	</div>


	</div>
</c:forEach>

ends

<!-- Modal -->
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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

<div class="modal fade" id="pending-approval-cont" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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

<div class="modal fade" id="pending-approval-cont" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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







