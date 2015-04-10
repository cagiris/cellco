<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="page-header">List of Holidays</h1>

<div class="row">

	<tiles:insertAttribute name="feedback-msg" />
	
    <div class="table-responsive">
		<table class="table table-bordered table-results" id="list-table">
            <thead>
                <tr>
                    <th>Day</th>
                    <th>Description</th>
                    <th>Role</th>
                </tr>
            </thead>
			<tbody>
				<c:forEach items="${weeklyHolidayBeanList}" var="weeklyHolidayBean">
					
				    <tr id="row-${weeklyHolidayBean.holidayId}">      
				        <td>${weeklyHolidayBean.weekDay}</td>
				        <td>${weeklyHolidayBean.description}</td>
				        <td>${weeklyHolidayBean.userRole}</td>
				    </tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
</div>