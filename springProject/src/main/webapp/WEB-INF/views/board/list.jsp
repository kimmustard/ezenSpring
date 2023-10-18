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
	
	<table class="table table-hover">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>댓글수</td>
				<td>파일수</td>
			
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list }" var="bvo" >
			<tr>
				<td> <a href="/board/cntdetail?bno=${bvo.bno }">${bvo.bno }</a></td>
				<td> <a href="/board/cntdetail?bno=${bvo.bno }">${bvo.title }</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.regAt }</td>
				<td>${bvo.readCount }</td>
				<td>${bvo.cmtQty }</td>
				<td>${bvo.hasFile }</td>
			</tr>
			
		</c:forEach>
		</tbody>

	</table>
	
	<script type="text/javascript">
		const isOk = `<c:out value="${isOk}" />`;
		console.log(isOk);
		if( isOk == 1){
			alert('삭제완료!');
		}
		
	</script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>