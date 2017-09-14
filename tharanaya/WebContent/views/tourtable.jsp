<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/views/semantic.min.css"/>">
<title>Insert title here</title>

<style type="text/css">
	#align-ndays{
      	padding-left: 10px;
	}
	#thead-color{
		background-color:#ababab;
	}
	#padtable{
		padding-top: 30px;
	}
	#padform{
		padding-left: 10px;
		padding-right:0px;
		padding-top: 10px;
		padding-bottom:0px;
		/*border-style: solid;
    	border-width: 1px;
    	border-color:blue;*/
	}
</style>
</head>
<body>
		<div class="ui grid">
		  <div class="four wide column"> 
				<div class="ui attached olive message">
					  <div class="header">
					    Edit Tours Table
					  </div>
					  <p>To search items use search table option</p>
				</div>
			   <form class="ui form" id="padform" action="tours" method="post">
					<div class="field">
						<label>ID</label>
						<input type="text" name="tourID">
					</div>
					<div class="field">
						<label>Name</label>
						<input type="text" name="tName">
					</div>
					<div class="field">
						<label>Location</label>
						<input type="text" name="location">
					</div>
					<div class="field">
						<label>No of Days</label>
						<input type="text" name="nDays">
					</div>
					<div class="field">
						<div class="small three ui buttons">
						<input type="submit" name="tourInsert" value="Save" class="ui green button">
						<input type="submit" name="tourUpdate" value="Update" class="ui olive button">
						<input type="submit" name="tourDelete" value="Delete" class="ui black button">
						</div>
					</div>
				</form>
		  </div>
		  
		<div class="twelve wide column" id="padtable">
		<table class="ui selectable table">
		<thead id="thead-color">
			<tr>
				<td>ID</td>
				<td>Name</td>
				<td>Location</td>
				<td>No of Days</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="Tour" items="${resultset}">
				<tr>
				 <td><c:out value="${Tour.tourID}" /></td> <! here the Tour.getTourID() is called automatically>
				  <td><c:out value="${Tour.getTourName()}" /></td><! here the Tour.getTourName() is called since we have different names for variable and getter>
				  <td><c:out value="${Tour.location}" /></td>
				   <td id="align-ndays"><c:out value="${Tour.nDays}" /></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	</div>
</body>
</html>