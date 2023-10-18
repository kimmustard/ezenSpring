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
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
		<form action="/board/modify" method="post">
		<table class="table table-hover">
			<tr>
				<th>글번호</th>
				<td><input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${bvo.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${bvo.writer }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="50" name="content">${bvo.content }</textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${bvo.regAt }</td>
			</tr>
			<tr>	
				<th>조회수</th>
				<td>${bvo.readCount }</td>
			</tr>
		
		
		</table>
	
	<button type="submit"> 수정 완료 </button><br>

	</form>
	<a href="/"><button> 메인으로 </button></a>
	
	
	<jsp:include page="../common/footer.jsp" />

</body>
</html>