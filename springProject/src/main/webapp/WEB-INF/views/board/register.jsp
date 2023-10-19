<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<style type="text/css">
.errorFont{
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<form:form action="/board/register" method="post" modelAttribute="bvo">
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">title</label>
			<form:input type="text" path="title" class="form-control" name="title" value="" id="exampleFormControlInput1" />
			<form:errors class="errorFont" path="title"></form:errors>
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">writer</label>
			<input type="text" class="form-control" name="writer" id="exampleFormControlInput1">
		</div>
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label">content</label>
			<textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"></textarea>
			<form:errors class="errorFont" path="content"></form:errors>
		</div>
		<button type="submit" class="btn btn-primary"> 작성완료 </button>
	</form:form>
<jsp:include page="../common/footer.jsp" />
</body>
</html>