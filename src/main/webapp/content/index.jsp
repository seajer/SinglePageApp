<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD page</title>
</head>
<body>
	<c:forEach begin="1" end="10" var="i">
		<a href="allEmp?page=${i}">${i}</a>
	</c:forEach>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>EmpName</th>
				<th>isActive</th>
				<th>Department</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="emp">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.name}</td>
					<td>${emp.isActive}</td>
					<td>${emp.department.name}</td>
					<td><a href="#">${emp.id} Edit</a></td>
					<td><a href="#">${emp.id} Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>