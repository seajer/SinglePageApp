<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD page</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-6">
			<c:if test="${pageCount > 1}">
				<ul class="pagination">
					<c:forEach begin="1" end="${pageCount}" var="i">
						<li><a href="allEmp?page=${i}">${i}</a></li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<div class="col-lg-3 col-lg-offset-3">
			Name:<input type="text" name="search" id='searched' value="${searchedStr}"/>
			<button id='search'><span class="glyphicon glyphicon-search"></span></button>
			<a href ="allEmp"><span class="glyphicon glyphicon-remove"></span></a>
		</div>
	</div>
	<div class="container">
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
						<td class="id">${emp.id}</td>
						<td class="name">${emp.name}</td>
						<td class="active">${emp.active}</td>
						<td class="department">${emp.department.name}</td>
						<td><a href="#" class="row" data-toggle="modal" data-target="#myModal">${emp.id} Edit</a></td>
						<td><a href="delete?id=${emp.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
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
        <h4 class="modal-title">Edit employee</h4>
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
<script>
	$('.row').on('click', function(){
		var form = "";
		var tr = $(this).parents().eq(1);
		var department = tr.find($('.department')).text();
		var select = createSelect();
		form += "<input type='hidden' name='id' value='" + tr.find('.id').text() + "'>";
		form += "Name: <input type='text' name='name' value='" + tr.find($('.name')).text() + "'><br>";
		var checked = (tr.find('.active').text() == 'true') ? "checked" : "";
		form += "isActive: <input type='checkbox' name='isActive' " + checked + "><br>";
		form += "<select name='dep_id'>";
		$.each(select, function(k,v){
			var selected = (v.name == department) ? "selected" : "";
			form += "<option value='" + v.id + "' " + selected + ">" + v.name + "</option>";
		})
		form += "</select><br>";
		form += "<button type='submit' class='pull-right'>Submit</button>"
		$('#form').html(form);
	})
	function createSelect(){
		var items = [];
		$('#select option').each(function(){
			items.push({id: $(this).val(), name: $(this).text()})
		})
		return items;
	}
	$('#search').on('click', function(){
		var searched = $('#searched').val();
		var url = window.location.href;
		var index = url.indexOf("&");
		if(index >= 0) url = url.substring(0, index);
		window.location.replace(url + "&search=" + searched);
	})
</script>
</html>