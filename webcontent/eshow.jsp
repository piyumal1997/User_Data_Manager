<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <meta charset="ISO-8859-1" />
    <title>Error</title>
  </head>
  <body>
    <div style="padding-left: 15%; padding-right: 15%; padding-top: 8%">
      <div class="alert alert-danger" role="alert">
        <h1 class="alert-heading">Error!</h1>
        <hr>
        <h3><c:out value="${name}"/> </h3>
        <hr>
        <h4><c:out value="${name0}"/> </h4>
      </div>
    </div>
    <button
      type="button"
      class="btn btn-secondary btn-lg"
      onclick="history.back()"
      style="margin-top: 5%; margin-left: 67%; font-size: 38px"
    >
      Back to Form
    </button>
  </body>
</html>
