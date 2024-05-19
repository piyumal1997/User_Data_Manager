<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="style.css" />

    <title>Data Update</title>
  </head>
  <body>
    <h1></h1>
    
    <header>
	 <nav class="navbar navbar-light" style="background-color: #e3f2fd">
        <a href="<%=request.getContextPath()%>/list">
          <button class="btn btn-outline-success btn-lg" type="button">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 28 28"
              width="24"
              height="24"
            >
              <path
                d="M12.97 2.59a1.5 1.5 0 00-1.94 0l-7.5 6.363A1.5 1.5 0 003 10.097V19.5A1.5 1.5 0 004.5 21h4.75a.75.75 0 00.75-.75V14h4v6.25c0 .414.336.75.75.75h4.75a1.5 1.5 0 001.5-1.5v-9.403a1.5 1.5 0 00-.53-1.144l-7.5-6.363z"
              ></path>
            </svg>
            Back to Home
          </button>
        </a>
      </nav>
	</header>

	<br/>

    <div style="padding: 2% 30%">
	  <form action="update" method="post">
		<caption>
			<h2>Update User</h2>
		</caption>
		  <div  class="mb-3">
			  <div class="form-floating">
	            <input
	              name="id"
	              type="text"
	              class="form-control"
	              id="id"
	              placeholder="#"
	              value="<c:out value='${user.id}' />"
	              required
	              readonly
	            />
	            <label for="name">Id</label>
	          </div>
          </div>
		  <div class="mb-3">
          <div class="form-floating">
            <input
              name="name"
              type="text"
              class="form-control"
              id="name"
              placeholder="#"
              value="<c:out value='${user.name}' />"
              autocomplete="off"
              required
            />
            <label for="name">Name</label>
          </div>
        </div>
        <div class="mb-3">
          <div class="form-floating">
            <input
              name="address"
              type="text"
              class="form-control"
              id="address"
              placeholder="#"
              value="<c:out value='${user.address}' />"
              autocomplete="off"
              required
            />
            <label for="address">Address</label>
          </div>
        </div>
        <div class="mb-3">
          <div class="form-floating">
            <input
              name="nationality"
              type="text"
              class="form-control"
              id="nationality"
              placeholder="#"
              value="<c:out value='${user.nationality}' />"
              autocomplete="off"
              required
            />
            <label for="nationality">Nationality</label>
          </div>
        </div>
        <div class="mb-3">
          <div class="form-floating">
            <input
              name="nic"
              type="text"
              class="form-control"
              id="nic"
              placeholder="#"
              value="<c:out value='${user.nic}' />"
              autocomplete="off"
              required
            />
            <label for="nic">NIC Number</label>
          </div>
        </div>
        
        <div class="mb-3">
          <button type="submit" class="form-control btn btn-success btn-lg">
            Update
          </button>
        </div>
      </form>
    </div>
  </body>
</html>