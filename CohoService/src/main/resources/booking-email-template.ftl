<h1>
	Booking Details
</h1>
<p>
<p><strong>Booking Id:</strong> ${contextObject.bookingId}</p>
<p><strong>PNR:</strong> ${contextObject.pnr}</p>
<p><strong>User Id:</strong> ${contextObject.userId}</p>
<p><strong>Booking GDS:</strong> ${contextObject.bookingGDSType}</p>
<p><strong>Base Fare:</strong> ${contextObject.baseFare}</p>
<p><strong>Taxes and Service Fee:</strong> ${contextObject.taxesAndServiceFee}</p>
<p><strong>Misc Charges:</strong> ${contextObject.miscellaneousCharges}</p>
</p>

<h2>Customer Details</h2>
<p>
<p><strong>First Name:</strong> ${contextObject.customer.firstName}</p>
<#if contextObject.customer.middleName??>
<p><strong>Middle Name:</strong> ${contextObject.customer.middleName}</p>
</#if>
<#if contextObject.customer.lastName??>
<p><strong>Last Name:</strong> ${contextObject.customer.lastName}</p>
</#if>
<p><strong>Email:</strong> ${contextObject.customer.emailId}</p>
<p><strong>Contact Number:</strong> ${contextObject.customer.contactNumber}</p>
<p><strong>Address Line1:</strong> ${contextObject.customer.addressLine1}</p>
<p><strong>Address Line2:</strong> ${contextObject.customer.addressLine2}</p>
<p><strong>City:</strong> ${contextObject.customer.city}</p>
<p><strong>Pincode:</strong> ${contextObject.customer.pincode}</p>
<p><strong>State:</strong> ${contextObject.customer.state}</p>  
<p><strong>Country:</strong> ${contextObject.customer.country}</p>
</p>

<h2>Passenger Details</h2>
<p>
<#list contextObject.passengers as passenger>
    <p><strong>First Name:</strong>${passenger.firstName}</p>
    <#if passenger.middleName??>
    <p><strong>Middle Name:</strong>${passenger.middleName}</p>
    </#if>
    <#if passenger.lastName??>
    <p><strong>Last Name:</strong>${passenger.lastName}</p>
    </#if>
    <p><strong>DOB:</strong>${passenger.dateOfBirth}</p>
    <p><strong>Passenger Type:</strong>${passenger.type}</p>
</#list>
</p>