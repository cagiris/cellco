<h1 class="page-header">My leaves</h1>
<div class="row form-filters">
	<form class="form-inline warning">
		<div class="form-group">
			<label class="sr-only" for="leaveType">Leave Type</label> 
			<select class="form-control" id="leaveType">
				<option>Casual</option>
				<option>Emergency</option>
				<option>Maternity</option>
			</select>
		</div>
		<div class="form-group date form-date">
			<label class="sr-only" for="startDate">Start Date</label> <input
				type="text" class="form-control" id="startDate"
				placeholder="Start Date">
		    <span class="add-on"><i class="icon-th"></i></span>
		</div>
		<div class="form-group date form-date">
			<label class="sr-only" for="endDate">End Date</label> <input
				type="text" class="form-control" id="endDate" placeholder="End Date">
		    <span class="add-on"><i class="icon-th"></i></span>
		</div>
		<div class="form-group">
			<label class="sr-only" for="status">Status</label> <input type="text"
				class="form-control" id="status" placeholder="Status">
		</div>
		<button type="submit" class="btn btn-primary">Filter</button>
	</form>
</div>
<div class="row">
	<div class="table-responsive">
		<table class="table table-bordered table-results">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>Leave Type</th>
					<th>From</th>
					<th>To</th>
					<th>Reason</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Emergency</td>
					<td>12-12-2012</td>
					<td>12-1-2013</td>
					<td>Broke hand</td>
					<td>Cancelled</td>
				</tr>
				<tr>
					<td>2</td>
					<td>Casual</td>
					<td>1-8-2012</td>
					<td>2-8-2012</td>
					<td>Fun @Goa</td>
					<td>Approved</td>
				</tr>
				<tr>
					<td>3</td>
					<td>Maternity</td>
					<td>12-12-2012</td>
					<td>14-12-2012</td>
					<td>-------</td>
					<td>Pending</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
