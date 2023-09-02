<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
	<fmt:setLocale value='<%=request.getParameter("language")%>' />
		<fmt:bundle basename="bundle.message">
			<jsp:include page="../../../../menu.jsp" />
	

<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">상품등록</h1>
	      </div>
	    </div>
</section>
	 
	 <div class="container my-5">
     <div class="col-lg-12 col-md-12">
     <div class="container mx-auto" style="width: 80%;">
      <div class="container border my-3 rounded-5 text-center">
       <div class="container mx-auto" style="width: 80%;">
       <div class="row my-5" >
		<form name="newProduct" action="add" enctype="multipart/form-data"	 class="form-horizontal" method="post"> 
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		<b><fmt:message key="productId" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						<input type="text" id="productId" name="productId" required class="form-control">
					</div>	
				</div>
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="pname" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						<input type="text" id="pname" name="pname" required  class="form-control">
					</div>	
				</div>
				
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="unitPrice" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						<input type="text" id="unitPrice" name="unitPrice" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" class="form-control" required >
					</div>	
				</div>
				
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="description" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						 <textarea name="description" cols="50" rows="2" class="form-control"></textarea>
					</div>	
				</div>
				
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="manufacturer" /></b>
					</div>
					<div class="col-12 col-sm-8 col-md-8 align-self-center">
						<input type="text" name="manufacturer"  required class="form-control">
					</div>	
				</div>
				
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="category" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						<input type="radio" name="category" value="Phone" required> Phone
						<input type="radio" name="category" value="Tab" style="margin-left: 10px"> Tab 
						<input type="radio" name="category" value="Notebook" style="margin-left: 10px"> Notebook 
						<input type="radio" name="category" value="PC" style="margin-left: 10px"> PC  
						<input type="radio" name="category" value="Audio" style="margin-left: 10px"> Audio 
						<input type="radio" name="category" value="Other" style="margin-left: 10px"> Other 
					</div>	
				</div>
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="unitsInStock" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						<input type="text" id="unitsInStock" name="unitsInStock"  onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"  required class="form-control">
					</div>	
				</div>
				
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b><fmt:message key="productImage" /></b>
					</div>
					<div class="col-12 col-sm-8  col-md-8  align-self-center">
						<input type="file" name="productImage" class="form-control"  required >
					</div>	
				</div>
				
				
				<div class="form-group row py-2 ">
					<div class="col-12 col-sm-3 col-md-3 align-self-center " >
                   		 <b>바로공개</b>
					</div>
					<div class="col-12 col-sm-2 align-self-center">
						<div class="form-check form-check-inline" >
							<input class="form-check-input" type="checkbox" id="open"  name="open" value="true">
						</div>
					</div>	
				</div>
				
				<div class="form-group  row pt-3">
				<div class="col-12  mt-3">
                    <input type="submit" class="btn btn-primary " value="등록 ">
               	</div> 
               	</div>		
		</form>
		</div>
		</div>
		</div>
		</div>
		</div>
	</div>
	 
	
		 </fmt:bundle> 
</body>
</html>