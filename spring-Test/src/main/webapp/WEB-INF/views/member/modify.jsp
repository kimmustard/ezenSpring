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
<jsp:include page="../layout/header.jsp"></jsp:include>
	<form action="/member/modify" method="post">
		<table class="table table-hover">
			<thead>
			
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" value="${ses.id }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="text" name="pw" placeholder="변경할 비밀번호"></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><input type="text" name="name" value="${ses.name }"></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><input type="text" name="email" value="${ses.email }"></td>
				</tr>
				<tr>
					<th>Home</th>
					<td><input type="text" name="home" value="${ses.home }"></td>
				</tr>
				<tr>
					<th>Age</th>
					<td><input type="text" name="age" value="${ses.age }"></td>
				</tr>
			</thead>

		
		</table>
		<button type="submit"> 수정완료 </button>
	</form>
	
	<a href="/"><button type="button">메인으로</button></a>
	
<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>