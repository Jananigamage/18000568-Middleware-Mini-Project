<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Pet Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Pet Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Pets</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${pet != null}">
					<form action="update" method="post"> 
				</c:if>
				<c:if test="${pet == null}">
					<form action="insert" method="post">
				</c:if>
				

				<caption>
					<h2>
						<c:if test="${pet != null}">
            			Edit Pet
            		</c:if>
						<c:if test="${pet == null}">
            			Add New Pet
            		</c:if>
					</h2>
				</caption>

				<c:if test="${pet != null}">
					<input type="hidden" name="id" value="<c:out value='${pet.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Pet Name</label> <input type="text"
						value="<c:out value='${pet.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Type</label> <input type="text"
						value="<c:out value='${pet.type}' />" class="form-control"
						name="type">
				</fieldset>

				<fieldset class="form-group">
					<label>Age</label> <input type="text"
						value="<c:out value='${pet.age}' />" class="form-control"
						name="age">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Breed</label> <input type="text"
						value="<c:out value='${pet.breed}' />" class="form-control"
						name="breed">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>