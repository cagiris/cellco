<h1 class="page-header">Apply for leave</h1>
<form class="form-horizontal form-coho">
	<div class="form-group">
		<label for="leaveType" class="control-label col-xs-2">Leave Type</label>
		<div class="col-xs-6">
		  <select class="form-control" id="leaveType" autofocus>
		      <option> Casual </option>
              <option> Emergency </option>
              <option> Maternity </option>
		  </select>
		</div>
		<div class="col-xs-4">
		
		</div>
	</div>
	<div class="form-group date form-date">
        <label for="startDate" class="control-label col-xs-2">Start Date</label>
        <div class="col-xs-6">
            <input type="text" class="form-control" id="startDate"
                placeholder="Start Date" required>
            <span class="add-on"><i class="icon-th"></i></span>
        </div>
        <div class="col-xs-4">
        
        </div>
    </div>
    <div class="form-group date form-date">
        <label for="endDate" class="control-label col-xs-2">End Date</label>
        <div class="col-xs-6 has-error">
            <input type="text" class="form-control" id="endDate"
                placeholder="End Date" required>
            <span class="add-on"><i class="icon-th"></i></span>
        </div>
        <div class="col-xs-4">
            <p class="form-control-static text-danger">*Invalid date</p>
        </div>
    </div>
	<div class="form-group">
        <label for="reason" class="control-label col-xs-2">Reason</label>
        <div class="col-xs-6">
            <textarea rows="5" id="reason" class="form-control"></textarea>
        </div>
        <div class="col-xs-4">
        
        </div>
    </div>
	<div class="form-group">
		<div class="col-xs-offset-2 col-xs-10">
			<button type="submit" class="btn btn-primary">Apply</button>
		</div>
	</div>
</form>

