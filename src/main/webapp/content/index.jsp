<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD page</title>
</head>
<body>
	<c:forEach begin="1" end="10" var="i">
		<a href="allEmp?page=${i}">${i}</a>
	</c:forEach>
	<table class="table table-bordered">
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
					<td>${emp.active}</td>
					<td>${emp.department.name}</td>
					<td><a href="#" class="row" data-toggle="modal" data-target="#myModal">${emp.id} Edit</a></td>
					<td><a href="delete?id=${emp.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<select style="visibility:hidden;" id="select">
		<c:forEach items="${departments}" var="dep">
			<option value="${dep.id}">${dep.name}</option>
		</c:forEach>
	</select>
</body>
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <form action="edit" method="post" id="form">
        	
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</html>
<script>
	$('.row').on('click', function(){
		var form = "";"
		$('#form').html(form);
	})
</script>