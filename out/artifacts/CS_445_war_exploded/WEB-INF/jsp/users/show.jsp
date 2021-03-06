<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp"/>

<body>

<div class="container" role="main">

	<div class="col-sm-4">
		<jsp:include page="../fragments/leftbar.jsp"/>
	</div>
	<div class="col-sm-8">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>User detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${user.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">First name</label>
		<div class="col-sm-10">${user.firstName}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Last name</label>
		<div class="col-sm-10">${user.lastName}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${user.email}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Password</label>
		<div class="col-sm-10">${user.password}</div>
	</div>

</div>
</div>
<jsp:include page="../fragments/footer.jsp" />

</body>
</html>