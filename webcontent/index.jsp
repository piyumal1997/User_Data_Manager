<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.lang.Class"%> 
<%@page import="java.lang.Object"%>
    


<!DOCTYPE html>
<html>
  <head>
    <%-- <%@ include file="add.jsp"%> --%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />

    <title>NIC Validation</title>
  </head>
  <body>
    <h1></h1>
    <div style="padding: 4% 10%">
      <nav class="navbar navbar-light bg-light">
		  <form class="container-fluid justify-content-start">
		    <a href="<%=request.getContextPath()%>/list"> <button class="btn btn-outline-info me-2 btn-lg" type="button">View List</button></a>
		    <a href="<%=request.getContextPath()%>/new" ><button class="btn btn-outline-success me-2 btn-lg" type="button">Add Person</button></a>
		    <a href="<%=request.getContextPath()%>/view" ><button class="btn btn-outline-primary me-2 btn-lg" type="button">NIC Check</button></a>
		  </form>
	  </nav>
    </div>
    
    <div style="padding-right: 10%; padding-left: 10%">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Nationality</th>
            <th>NIC</th>
            <th>Adjust</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${listUser}">
          <tr>
          		<td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
				<td><c:out value="${user.address}" /></td>
				<td><c:out value="${user.nationality}" /></td>
				<td><c:out value="${user.nic}" /></td>
                <td>
                	<a href="edit?id=<c:out value='${user.id}' />"><button type="button" class="btn btn-warning">Edit</button></a>
                    <a href="delete?id=<c:out value='${user.id}' />"><button type="button" class="btn btn-danger">Delete</button></a>
                </td>
          </tr>
          </c:forEach>
          
        </tbody>
      </table>
    </div>
  </body>
</html>
