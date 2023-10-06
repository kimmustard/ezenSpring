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
<!-- mapping 상태는 get / post 가 별도의 mapping을 가짐 -->
    <jsp:include page="../layout/header.jsp"></jsp:include>
    
	    <form action="/board/register" method="post"> <br>
	    	title : <input type="text" name="title"> <br>
	    	writer : <input type="text" value="${ses.id }" name="writer" readonly="readonly"> <br>
			content : <textarea rows="5" cols="50" name="content"></textarea> <br>
			<button type="submit"> 작성 완료 </button> <br>
	    </form>
	    
	    <a href="/"> 
	    	<button type="button"> 메인 </button> <br>
	    </a>
	    <a href="/board/list"> 
	    	<button type="button"> 리스트 </button> <br>
	    </a>
	    
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>