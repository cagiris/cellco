<h1 class="page-header">Leaves</h1>
<div class="row form-filters">
    <form class="form-inline">
        <div class="form-group">
            <label class="sr-only" for="employeeDescription">Employee Decription</label> 
            <select class="form-control" id="employeeDescription">
                <option>All Employees</option>
                <option>Employee Name (Employee ID)</option>
                <option>Ashish (2069)</option>
                <option>Sanjeev (xyz)</option>
                <option>Akhil (1234)</option>
            </select>
        </div>
        <div class="form-group">
            <label class="sr-only" for="leaveType">Leave Type</label> 
            <select class="form-control" id="leaveType">
                <option>All</option>
                <option>Casual</option>
                <option>Emergency</option>
                <option>Maternity</option>
            </select>
        </div>
        <div class="form-group">
            <label class="sr-only" for="startDate">Start Date</label>
            <div class="input-group date form-datepicker" data-date="12-02-2012"
                data-date-format="dd-mm-yyyy">
                <input type="text" class="form-control" id="startDate"
                    placeholder="Start Date" required> <span
                    class="input-group-addon add-on"><i
                    class="glyphicon glyphicon-calendar"></i></span>
            </div>
        </div>
        <div class="form-group">
            <label class="sr-only" for="endDate">End Date</label>
            <div class="input-group date form-datepicker" data-date="12-02-2012"
                data-date-format="dd-mm-yyyy">
                <input type="text" class="form-control" id="endDate"
                    placeholder="End Date" required> <span
                    class="input-group-addon add-on"><i
                    class="glyphicon glyphicon-calendar"></i></span>
            </div>
        </div>
        <div class="form-group">
            <label class="sr-only" for="status">Status</label> 
            <select class="form-control" id="status">
                <option>Any</option>
                <option>Approved</option>
                <option>Pending</option>
                <option>Cancelled</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Filter</button>
        </div>
    </form>
</div>
<div class="row">
    <div class="table-responsive">
        <table class="table table-bordered table-results">
            <thead>
                <tr>
                    <th>S.No.</th>
                    <th>Employee Description</th>
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
                    <td>Ashish (2069)</td>
                    <td>Emergency</td>
                    <td>12-12-2012</td>
                    <td>12-1-2013</td>
                    <td>Broke hand</td>
                    <td>Cancelled</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Akhil (1234)</td>
                    <td>Casual</td>
                    <td>1-8-2012</td>
                    <td>2-8-2012</td>
                    <td>Fun @Goa</td>
                    <td>Approved</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Employee name (Employee ID)</td>
                    <td>Maternity</td>
                    <td>12-12-2012</td>
                    <td>14-12-2012</td>
                    <td>-------</td>
                    <td>
                        Pending
						<div class="pull-right">
							<a href="#" class="btn btn-success btn-xs" title="Approve"> <span
								class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
							</a> <a href="#" class="btn btn-warning btn-xs" title="On Hold">
								<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
							</a> <a href="#" class="btn btn-danger btn-xs" title="Cancel"> <span
								class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
							</a>
						</div>
					</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<nav>
  <ul class="pagination">
    <li class="disabled">
        <a href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a>
    </li>
    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>