<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<jsp:include page="../fixtures/head.jsp" />
<body>
	<jsp:include page="../fixtures/header.jsp" />
	<div class="container col-md-5 mt-5">
		<div class="card">
			<div class="card-body">
				<form action="<c:url value='/register'/>" method="post">
					<h2>Register</h2>
					<div class="form-group">
						<label for="name">Name</label> <input type="text"
							class="form-control" name="name" required />
					</div>
					<div class="form-group">
						<label for="email">Email</label> <input type="text"
							class="form-control" name="email" required />
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" name="password" required />
					</div>
					<button type="submit" class="btn btn-success mt-2">Register</button>
					<p class="mt-2">
						Already have an account? <a href="<c:url value='/login'/>">Login
							here.</a>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../fixtures/scripts.jsp" />
</html>
