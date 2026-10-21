<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/bill" />

<c:url var="addSearch" value="/home/login/bill/search" />

<c:url var="editUrl" value="/home/login/bill?id=" />


<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i>Bill List</li>
  </ol>
</nav>
</div>
<hr>
	<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/bill/search"
		modelAttribute="form">
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">Bill List</h5>
			<div class="card-body">
				<div class="row g-3">
					
					
					
				</div>
				<b><%@ include file="businessMessage.jsp"%></b>
				<br>
				<sf:input type="hidden" path="pageNo" />
				<sf:input type="hidden" path="pageSize" />

				<sf:input type="hidden" path="listsize" />
				<sf:input type="hidden" path="total" />
				<sf:input type="hidden" path="pagenosize" />

				<table class="table table-bordered border-primary">
					<thead>
						<tr>
						<c:if test="${sessionScope.user.roleId == 4}">
						<th scope="col"><input type="checkbox" id="selectall">Select
								All</th>
								</c:if>
							<th scope="col">#</th>
							<th scope="col">Patient Name</th>
							<th scope="col">Doctor Name</th>
							<th scope="col">Amount</th>
							<th scope="col">Date</th>
							<th scope="col">Description</th>
							<c:if test="${sessionScope.user.roleId == 4}">
							<th scope="col">Action</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="bi" varStatus="bill">
							<tr>
							<c:if test="${sessionScope.user.roleId == 4}">
							<td><input type="checkbox" class="case"
									name="ids" value="${bi.id}"></td>
									</c:if>
								<td scope="row">${bill.index+1}</td>
								<td scope="row">${bi.patient.firstName} ${bi.patient.lastName}</td>
								<td scope="row">${bi.doctorName} </td>
								<td scope="row">${bi.amount}</td>
								<td scope="row">${bi.date}</td>
								<td scope="row">${bi.description}</td>
								<c:if test="${sessionScope.user.roleId == 4}">
								<td><a href="${editUrl} ${rm.id}"
									><i class="fas fa-edit"></i></a></td>
									</c:if>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="clearfix">
				<c:if test="${sessionScope.user.roleId == 4}">
				
				</c:if>
				
				
			<nav aria-label="Page navigation example float-end">
				<ul class="pagination justify-content-end" style="font-size: 13px">
					<li class="page-item"><input type="submit" name="operation"
								class="page-link" 	<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
								value="Previous"></li>
								 <c:forEach var = "i" begin = "1" end = "${(listsize/10)+1}">
								 <c:if test="${i== pageNo}">
								<li class="page-item active"><a class="page-link activate" href="${addSearch}?pageNo=${i}">${i}</a></li>
								</c:if>
								<c:if test="${i != pageNo}">
								<li class="page-item"><a class="page-link" href="${addSearch}?pageNo=${i}">${i}</a></li>
								</c:if>
								</c:forEach>
					<li class="page-item"><input type="submit" name="operation"
								class="page-link" <c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
								value="Next"></li>
				</ul>
			</nav>
			</div>
				
				
			</div>

		</div>
	</sf:form>
