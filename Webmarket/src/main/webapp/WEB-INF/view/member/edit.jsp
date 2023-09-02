<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script type="text/javascript">
	function checkForm() {
		if (!document.editMember.memberId.value) {
			alert("아이디를 입력하세요.");
			return false;
		}

		if (!document.editMember.password.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		if (document.editMember.password.value != document.editMember.password_confirm.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
	}
</script>
<title>회원 수정</title>
</head>
<body onload="init()">
	<jsp:include page="/menu.jsp" />

	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">회원 정보 수정</h1>
	      </div>
	    </div>
	 </section>
	 

<div class="container my-5">
     <div class="col-lg-12 col-md-12">
     <div class="container mx-auto" style="width: 80%;">
      <div class="container border my-3 rounded-5 text-center">
       <div class="row my-5" >
		<form name="editMember" class="form-horizontal"
			action="edit" method="post"
			onsubmit="return checkForm()">
				<div class="form-group row pt-5 pb-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		아이디
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input disabled id="m_id"  name="m_id"  type="text" class="form-control" value="${m.memberId}" required autofocus  maxlength="20" >
					</div>	
				</div>
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		비밀번호
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input name="m_pwd"  type="password"  class="form-control" value="${m.password}"  required minlength="4" maxlength="20" >
					</div>		
				</div>
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		이름
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input   name="m_name"  type="text"  class="form-control"  value="${m.name}" required maxlength="20">
					</div>		
				</div>
				
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
					  이메일            		
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input type="text" name="mail" id="mail"  maxlength="50"  class="form-control"  value="${m.email}"  required>
					</div>		
				</div>
				
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
					  주소          		
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input type="text"  name="address"  id=address  class="form-control"  value="${m.address}"   required  maxlength="30">
					</div>		
				</div>
				
				<div class="form-group  row pt-3">
				<div class="col-12  mt-3">
                    <input type="submit" class="btn btn-primary" value="수정 ">
					<a type="reset" class="btn btn-danger"  href="/member/delete">회원탈퇴</a>
               	</div> 
               	</div>		
		</form>
		</div>
		</div>
		</div>
		</div>
	</div>
				
</body>
</html>