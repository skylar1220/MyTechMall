<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>상품 수정</title>
</head>
<body>
	<fmt:setLocale value='<%=request.getParameter("language")%>' />
	<fmt:bundle basename="bundle.message">
	<jsp:include page="../../../../menu.jsp" />
	
     	<section class="py-3 text-center "  style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
		background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">상품수정</h1>
	      </div>
	    </div>
	 </section>
	
	<div class="container">
		<div class="row my-4">
			<div class="col-md-5  my-5 text-center" >
			<div class="container my-5" >
				<img src="/img/${p.filename}" alt="image" style="width: 80% ; " />
			</div>
			</div>
			<div class="col-md-7">
			 <div class="container border my-3 rounded-5 text-center">
		       <div class="container mx-auto" style="width: 80%;">
		       <div class="row my-4" >
				<form name="editProduct" action="edit" class="form-horizontal" method="post" enctype="multipart/form-data">
				<input type="hidden" id="ogProductId" name="ogProductId" value='${p.productId}'>
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		<b><fmt:message key="productId" /></b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								<input disabled type="text" id="productId" name="productId" required class="form-control"  value="${p.productId}">
							</div>	
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		 <b><fmt:message key="pname" /></b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								<input type="text" id="pname" name="pname" required  class="form-control" value="${p.pname }">
							</div>	
						</div>
						
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		 <b><fmt:message key="unitPrice" /></b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								<input type="text" id="unitPrice" name="unitPrice"  value="${p.unitPrice}" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" class="form-control" required >
							</div>	
						</div>
						
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		 <b><fmt:message key="description" /></b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								 <textarea name="description" cols="50" rows="2" class="form-control">${p.description}</textarea>
							</div>	
						</div>
						
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		<b><fmt:message key="manufacturer" /></b>
							</div>
							<div class="col-12 col-sm-8 col-md-8 align-self-center">
								<input type="text" name="manufacturer"  required class="form-control" value="${p.manufacturer}">
							</div>	
						</div>
						
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		 <b><fmt:message key="category" /></b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								<input type="radio" name="category" value="Phone" ${(p.category == "Phone")? "checked" : "" } required> 휴대폰 
									<input type="radio" name="category" value="Tab"  ${(p.category == "Tab")? "checked" : "" }> 태블릿PC 
									<input type="radio" name="category" value="Notebook" ${(p.category == "Notebook")? "checked" : "" }> 노트북 
									<input type="radio" name="category" value="Audio" ${(p.category == "Audio")? "checked" : "" }> 음향 가전 
									<input type="radio" name="category" value="other" ${(p.category == "other")? "checked" : "" }> 기타
							</div>	
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-3 col-md-3 align-self-center " >
		                   		 <b><fmt:message key="unitsInStock" /></b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								<input type="text" id="unitsInStock" name="unitsInStock"  value="${p.unitsInStock}"  onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"  required class="form-control">
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
									<input class="form-check-input" type="checkbox" id="open" 
										name="open" value="${(p.pub == true)? true : false }" ${(p.pub == true)? 'checked' : '' } >
								</div>
							</div>	
						</div>
						
						<div class="form-group  row pt-3">
						<div class="col-12 mt-3">
		                    <input type="submit" class="btn btn-primary" value="수정">
		               	</div> 
		               	</div>		
				</form>
				</div>
				</div>
		</div>
			
			</div>
		</div>
		<div class="row"></div>
	</div>
	
	</fmt:bundle> 
</body>
</html>
