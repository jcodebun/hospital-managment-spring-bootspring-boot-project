<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/welcome"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Appointment Form</li>
  </ol>
</nav>
</div>
<hr>
<div class="container"> 
	<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/appointment"
		modelAttribute="form">
		<sf:hidden path="id"/>
		<div class="card">
			<h5 class="card-header bgcolor"
				style="color: white;">Patient</h5>
			<div class="card-body">
				<b><%@ include file="businessMessage.jsp"%></b>
				
				<div class="col-md-6">
					<s:bind path="doctorId">
						<label for="inputEmail4" class="form-label">Doctor Name</label>
						<sf:select style="margin-bottom: 10px" class="form-control"
								path="${status.expression}">
								<sf:option value="-1" label="Select" />
								<sf:options itemLabel="firstName" itemValue="id" items="${doctorList}" />
							</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				<div class="col-md-6">
					<s:bind path="firstName">
						<label for="inputEmail4" class="form-label">First Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter First Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				<div class="col-md-6">
					<s:bind path="lastName">
						<label for="inputEmail4" class="form-label">Last Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Last Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
				<div class="col-md-6">
					<s:bind path="appDate">
						<label for="inputEmail4" class="form-label">Appointment Date</label>
						<sf:input path="${status.expression}" placeholder="Enter Appointment Date"
							class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
				<div class="col-md-6">
					<s:bind path="time">
						<label for="inputEmail4" class="form-label">Appointment Time</label>
						<sf:input path="${status.expression}" placeholder="Enter Appointment Time"
							class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>

				
				
				<div class="col-md-6">
					<s:bind path="mobileNo">
						<label for="inputEmail4" class="form-label">Mobile No</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Mobile No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
				
				
				<div class="col-md-6">
					<s:bind path="disease">
						<label for="inputEmail4" class="form-label">Disease</label>
						<sf:textarea rows="4" cols="4" path="${status.expression}"
							placeholder="Enter Disease" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
				
			
				<div class="col-md-6">
					<s:bind path="address">
						<label for="inputEmail4" class="form-label">Address</label>
						<sf:textarea rows="4" cols="4" path="${status.expression}"
							placeholder="Enter Address" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>

				
				

				<br>
				<div class="col-12">
					<input type="submit" name="operation"
						class="btn btn-primary pull-right" value="Save"> or <input
						type="submit" name="operation" class="btn btn-primary pull-right"
						value="Reset">
				</div>
			</div>
		</div>
	</sf:form>
</div>