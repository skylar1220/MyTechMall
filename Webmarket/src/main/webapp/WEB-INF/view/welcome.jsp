<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<head>
	<title>Welcome</title>
</head>
<body>

	<jsp:include page="../../menu.jsp" />
	
	
	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">Welcome to MyTechMall</h1>
	      </div>
	    </div>
	 </section>
	
	<div class="container">
		<div class="text-center">
		</div>
	</div>	
		<hr>
	
	<jsp:include page="footer.jsp" />
</body>
</html>