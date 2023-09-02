<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>

<title>상품 상세 정보</title>

<script type="text/javascript">
	function addToCart() {
			if($("#message").val()!=""){
				alert($("#message").val());
				document.location.href="/member/login";
			}
			else{
				if (confirm("상품을 장바구니에 추가하시겠습니까?")) {
					document.addForm.submit();
				} else {		
					document.addForm.reset();
				}
			}
		} 
	
	function editOn(){ // 수정버튼 활성화 
		$('#editContent').attr('readonly', false);
		$('#editButton').remove();
		$("#editSubmit").css('display', 'block');
	}
	
	function editComment(){
		$('#commentForm').prepend('<input hidden  name="type"  value="edit">');
		$("#commentForm").submit();
	}
	
	function insertComment(){
		$('#commentForm').prepend('<input hidden  name="type"  value="insert">');
		let formData = $("form").serialize(); 
		$.ajax({
			url: '/product/detail' ,
			type: 'POST',
			data : formData,
			success: function(response){
				$('#commentForm').prepend('<input hidden  name="insertChecked"  value="true">');
				$("#commentForm").submit();
			},
			error:function(){
				alert("후기는 상품 당 하나만 등록할 수 있습니다.");
			}
		}); 
	};
	
	function deleteComment(){
		$('#commentForm').prepend('<input hidden  name="type"  value="delete">');
		$("#commentForm").submit();
	}
</script>
</head>
<body>
	<jsp:include page="../../../menu.jsp" />
	
	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
				<h1 class="display-4 fw-bolder my-5 text-center">상품정보</h1>
	      </div>
	    </div>
	 </section>
        	
      <input id="message" hidden value="${message}"> 
    
	<div class="container" style="width: 85%;">
	
		<div class="row my-4">
			<div class="col-md-5  my-4 text-center" >
			<div class="container" >
				<img src="/img/${p.filename}" alt="image" style="width: 80% ; " />
			</div>
			</div>
			<div class="col-md-7">
			 <div class="container border my-3 rounded-5">
		       <div class="container mx-auto" style="width: 60%;">
		       <div class="row my-4" >
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-12 col-md-12 align-self-center " >
		                   		<h3>${p.pname}</h3>
							</div>
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-12 col-md-12 align-self-center " >
		                   		<p>${p.description}
							</div>
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-4 col-md-4 align-self-center " >
		                   		<b>상품 코드</b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								 <span class="badge text-bg-danger">${p.productId}</span>
							</div>	
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12  col-sm-4 col-md-4 align-self-center " >
		                   		 <b>제조사</b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								 ${p.manufacturer}
							</div>	
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12  col-sm-4 col-md-4  align-self-center " >
		                   		<b>분류</b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								  ${p.category}
							</div>	
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12  col-sm-4 col-md-4 align-self-center " >
		                   		  <b>재고 수</b>
							</div>
							<div class="col-12 col-sm-8  col-md-8  align-self-center">
								  ${p.unitsInStock}
							</div>	
						</div>
						
						<div class="form-group row py-4 ">
							<div class="col-12 col-sm-12 col-md-12 align-self-center " >
		                   		<h4><fmt:formatNumber value="${p.unitPrice}"/>원</h4>
							</div>
						</div>
						
						<div class="form-group row py-2 ">
							<div class="col-12 col-sm-12 col-md-12 align-self-center " >
		                   		<form name="addForm" action="/cart/add?id=${p.productId}" method="post">
									<input type="submit" class="btn btn-info" value="장바구니 &raquo;" onclick="addToCart()" >
									<a href="./products" class="btn btn-secondary"> 상품 목록 &raquo;</a>
								</form>
							</div>
						</div>
						
				</div>
				</div>
			</div>
			</div>
		</div>
	
		
		<div class="row mt-3">
		<div class="container" style="width: 90% ; " >
			<div class="col-md-12">
			<form id="commentForm" action="detail" method="post">
				<div class="row">
					<label for="ex" class="form-label">상품후기 ${commentCnt}건 </label>
				</div>
				
				<div class="row">
					<c:if test="${not empty memberId}"> <!-- ?? -->
						
						<div class="input-group mb-3">
							<input hidden  name="writerId" id="writerId" value="${memberId}">
							<input hidden  name="productId" id="productId" value="${p.productId}">
							<input class="form-control" name="c_content" id="c_content" height="50px" ></input>
							<button class="btn btn-secondary" onclick="insertComment()" type="button" >등록</button>
						</div>
					</c:if>
				</div>
				
				<div class="row">
					<div class="container border my-3 rounded-2">
					<c:forEach var="comment" items="${commentList}"  >
					  <div class="container mx-2 my-3">
					  		<input hidden name="c_number" id="c_number" value="${comment.c_number}">
							<p>	작성자: ${comment.writerId} 작성시간: <fmt:formatDate value="${comment.c_regdate}" pattern="yyyy-MM-dd HH:mm" /> 
							<c:if test = '${ memberId == comment.writerId}'>
								<a type="button" id="editButton" onclick="editOn()" class="badge text-bg-success"  >수정</a>
								<a type="button" onclick="deleteComment()" class="badge text-bg-success"  >삭제</a>
							</c:if>
							</p>
							
							<div id="modifyComment" class="input-group">
								<input id="editContent" name="editContent" class="form-control" type="text" value="${comment.c_content}" readonly /> 
								<button id="editSubmit" onclick="editComment()" style="display: none;" class="btn btn-secondary" type="button" >등록</button>
							</div>	
					</div>
					</c:forEach>
					</div>
				</div>
			</form>
			</div>
			</div>
		</div>

	</div>

		
		
		<hr>
	<jsp:include page="../footer.jsp" />
</body>
</html>
