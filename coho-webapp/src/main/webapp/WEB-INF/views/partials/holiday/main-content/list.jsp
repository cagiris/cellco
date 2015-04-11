<%@page import="com.cagiris.coho.model.AnnualHolidayBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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

				<%
				    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    //AnnualHolidayBean annualHolidayBean= (AnnualHolidayBean)pageContext. getAttribute("bean");
				    //String holidayDateFormatted = simpleDateFormat.format(annualHolidayBean.getHolidayDate());
				    String holidayDateFormatted = "";
				%>

				<c:forEach items="${annualHolidayBeanList}" var="annualHolidayBean">

					<tr id="row-${annualHolidayBean.holidayId}">

						<td>

							<div class="input-group date form-datepicker"
								data-date="<%=holidayDateFormatted%>"
								data-date-format="yyyy-mm-dd">
								${annualHolidayBean.holidayDate}</div>

						</td>
						<td>${annualHolidayBean.description}</td>
						<td>${annualHolidayBean.userRole}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>