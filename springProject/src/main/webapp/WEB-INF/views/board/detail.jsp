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
		
			<tr>
				<th>글번호</th>
				<td>${bvo.bno }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${bvo.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${bvo.writer }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${bvo.content }</td>
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
	
	<a href="/board/modify?bno=${bvo.bno }"><button> 수정하기 </button></a>
	<a href="/board/remove?bno=${bvo.bno }"><button> 삭제하기 </button></a>
	<a href="/board/list"><button> 메인으로 </button></a>


	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`
		const isOk = `<c:out value="${isOk}" />`;
		console.log(isOk);
		if( isOk == 1){
			alert('수정완료!');
		}
		
	</script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>