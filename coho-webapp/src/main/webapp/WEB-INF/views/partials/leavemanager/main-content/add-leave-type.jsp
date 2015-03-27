<h1 class="page-header">Add Leave Type</h1>

<form class="form-horizontal form-coho" id="addLeaveTypeForm">
	<div class="form-group">
		<label for="leaveType" class="control-label col-xs-4">Leave
			Type</label>
		<div class="col-xs-4">
			<input type="text" class="form-control" id="leaveType" required>
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-4">Define additional rules</label> 
		<div class="col-xs-4">
		  <input type="checkbox" id="defineAdditionalRules" checked="checked"
		          onclick="javascript:toggleform('addLeaveTypeForm', 
		        	  'defineAdditionalRules',
		        	  'numberOfLeavesCredited, creditingSchedule')">
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div class="form-group">
        <label for="numberOfLeavesCredited" class="control-label col-xs-4"> Leaves credited</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="numberOfLeavesCredited">
        </div>
        <div class="col-xs-4"></div>
    </div>
    <div class="form-group">
        <label for="creditingSchedule" class="control-label col-xs-4">Crediting Schedule</label>
        <div class="col-xs-4">
          <select class="form-control" id="creditingSchedule" autofocus>
              <option> Monthly </option>
              <option> Quarterly </option>
              <option> Biannually </option>
              <option> Annualy </option>
          </select>
        </div>
        <div class="col-xs-4">
        
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-4 col-xs-8">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form>