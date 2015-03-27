<h1 class="page-header">Apply for leave</h1>
<form class="form-horizontal form-coho">
    <div class="form-group">
        <label for="leaveSubject" class="control-label col-xs-2">Subject</label>
        <div class="col-xs-6">
            <input type="text" class="form-control" id="leaveSubject" required>
        </div>
        <div class="col-xs-4"></div>
    </div>
	<div class="form-group">
        <label for="startDate" class="control-label col-xs-2">Start Date</label>
        <div class="col-xs-6">
            <div class="input-group date form-datepicker" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
                <input type="text" class="form-control" id="startDate" placeholder="Start Date" required>
                <span class="input-group-addon add-on"><i class="glyphicon glyphicon-calendar"></i></span>
            </div>
        </div>
        <div class="col-xs-4">
        
        </div>
    </div>
    <div class="form-group">
        <label for="endDate" class="control-label col-xs-2">End Date</label>
        <div class="col-xs-6">
            <div class="input-group date form-datepicker" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
                <input type="text" class="form-control" id="endDate" placeholder="End Date" required>
                <span class="input-group-addon add-on"><i class="glyphicon glyphicon-calendar"></i></span>
            </div>
        </div>
        <div class="col-xs-4">
        
        </div>
    </div>
	<div class="form-group">
        <label for="description" class="control-label col-xs-2">Description</label>
        <div class="col-xs-6">
            <textarea rows="5" id="description" class="form-control"></textarea>
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

