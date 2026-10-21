<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg bgcolor"
	style="height: 59px;">
	<div class="container-fluid">
		<a class="navbar-brand " href="#"
			style="font-size: 26px; font-family: serif; color: white;"> 
			<span>Hospital-Management</span>
		</a>

		<form class="container-fluid">
			
		</form>

		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<c:if test="${sessionScope.user != null}">
				<span class="navbar-text"
					style="color: white; font-size: 14px; font-variant-caps: petite-caps;">
					Hello,${sessionScope.user.firstName}(${sessionScope.user.roleName}) </span>
			</c:if>
			<c:if test="${sessionScope.user == null}">
				<span class="navbar-text"
					style="color: white; font-size: 14px; font-variant-caps: petite-caps;">
					Hello,SignIn </span>
			</c:if>
			</ul>
			
		</div>
	</div>
</nav>
<div class="shadow bg-body rounded">
	<nav class="navbar navbar-expand-lg "
		style="height: 39px; background-color: #452e2efc;">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item linkSize"><a
						class="nav-link active link-light" aria-current="page"
						href="<c:url value="/home"/>">Home</a></li>
					<c:if test="${sessionScope.user != null}">
						<c:if test="${sessionScope.user.roleId == 1}">
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/doctor"/>">Add Doctor</a></li>
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/doctor/search"/>">Doctor List</a></li>
								
									<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/receptionist"/>">Add Receptionist</a></li>
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/receptionist/search"/>">Receptionist List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/room"/>">Add Room</a></li>
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/room/search"/>">Room List</a></li>
								
								
								
							
						</c:if>

						<c:if test="${sessionScope.user.roleId == 2}">
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/doctor/search"/>">Doctor List</a></li>
								
							
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patient"/>">Patient Form</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patient/search"/>">Patient List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/appointment"/>">Appointment</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/appointment/search"/>">Appointment List</a></li>
								
							
								
									<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/bill/search"/>">Bill List</a></li>
							
							
						</c:if>
						
							<c:if test="${sessionScope.user.roleId == 3}">
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patient/search"/>">Patient List</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/appointment/search"/>">Appointment List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patientSummery"/>">Add Patient Summery</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patientSummery/search"/>">Patient Summery List</a></li>
							
							
						</c:if>
						
						
							<c:if test="${sessionScope.user.roleId == 4}">
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/doctor/search"/>">Doctor List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patient"/>">Patient Form</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/patient/search"/>">Patient List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/appointment"/>">Appointment</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/appointment/search"/>">Appointment List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/allotRoom"/>">Allot Room</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/allotRoom/search"/>">Allot Room List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/bill"/>">Bill</a></li>
								
									<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/bill/search"/>">Bill List</a></li>
							
							
						</c:if>

					</c:if>
					<c:if test="${sessionScope.user == null}">
					
						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/home/aboutUs"/>">AboutUs</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/home/contactUs"/>">ContactUs</a></li>
					</c:if>
				</ul>
			</div>
			<ul class="nav justify-content-end">
				<c:if test="${sessionScope.user != null}">

				

					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/home/login/profile"/>">My
							Profile</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/home/login/changepassword"/>">Change
							Password</a></li>

					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/home/login"/>">Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/home/login"/>">SignIn</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/home/signUp"/>">SignUp</a></li>
				</c:if>

			</ul>
		</div>
	</nav>
</div>
