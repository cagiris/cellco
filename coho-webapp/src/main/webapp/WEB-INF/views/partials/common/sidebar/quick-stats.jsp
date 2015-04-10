<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasRole('AGENT')">
	<div class="panel panel-primary">
		<div class="panel-heading">Leave Statistics</div>
		<table class="table table-bordered" id="leave-statistics-table">
			<thead>
				<tr>
					<th class="active">Leave Type</th>
					<th class="active">Balance</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">Shift Status</div>
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td class="text-center">
					<input id="shiftStartTime" type="hidden"/>
					<label>Shift Duration - </label>
					<label id="shiftDuration">00:00:00</label>
					</td>
				</tr>
				<tr>
					<input id="shiftId" type="hidden"/>
					<td class="text-center">
						<button type="button" id="shiftButton"
							data-loading-text="Loading..." class="btn btn-primary"
							autocomplete="off">Start shift</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
	<div class="panel panel-primary">
		<div class="panel-heading">Quick Statistics</div>
		<table class="table table-bordered" id="admin-statistics-table">
			<tbody>
			</tbody>
		</table>
	</div>
</sec:authorize>
