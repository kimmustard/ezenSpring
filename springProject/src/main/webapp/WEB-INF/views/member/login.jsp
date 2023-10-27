<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<jsp:include page="../common/nav.jsp"/>
	
<div class="container">
	<form action="/member/login" method="post">
		<h4> 로그인 </h4>
		<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">이메일</label>
				<input type="text" class="form-control" name="email" id="exampleFormControlInput1" />
			</div>
			
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">비밀번호</label>
				<input type="text" class="form-control" name="pwd" id="exampleFormControlInput1" />
			</div>
			
			<button type="submit" class="btn btn-primary"> 로그인 </button>
	</form>
</div>
	
	<jsp:include page="../common/footer.jsp"/>

</body>
</html>