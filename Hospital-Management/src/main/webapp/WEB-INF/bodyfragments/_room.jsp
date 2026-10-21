<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/welcome"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Add Room</li>
  </ol>
</nav>
</div>
<hr>
<div class="container"> 
	<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/room"
		modelAttribute="form">
		<sf:hidden path="id"/>
		<div class="card">
			<h5 class="card-header bgcolor"
				style="color: white;">Room</h5>
			<div class="card-body">
				<b><%@ include file="businessMessage.jsp"%></b>

				<div class="col-md-6">
					<s:bind path="roomNo">
						<label for="inputEmail4" class="form-label">RoomNo</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Room No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				<div class="col-md-6">
					<s:bind path="type">
						<label for="inputEmail4" class="form-label">Room Type</label>
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="Select" />
									<sf:options   items="${roomType}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				<div class="col-md-6">
					<s:bind path="price">
						<label for="inputEmail4" class="form-label">Price</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Price" class="form-control" />
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