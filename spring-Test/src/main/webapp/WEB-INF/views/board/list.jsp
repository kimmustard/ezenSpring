<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<!-- 검색라인 -->
	<div>
		<form action="/board/list" method="get">
			<div>
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				
				
				<select name="type">
					<option ${typed == null? 'selected' : '' }> 검색목록 </option>
					<option value="t" ${typed eq 't'? 'selected':''}> 제목</option>
					<option value="w" ${typed eq 'w'? 'selected':''}> 작성자</option>
					<option value="c" ${typed eq 'c'? 'selected':''}> 내용</option>
					<option value="tw" ${typed eq 'tw'? 'selected':''}> 제목+작성자</option>
					<option value="tc" ${typed eq 'tc'? 'selected':''}> 제목+내용</option>
					<option value="wc" ${typed eq 'wc'? 'selected':''}> 작성자+내용</option>
					<option value="twc" ${typed eq 'twc'? 'selected':''}> 전체 </option>
				</select>
				<input type="text" name="keyword" value="${ph.pgvo.keyword }" placeholder="검색어 입력...">
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${ph.pgvo.qty }">
				
				<button type="submit"> 검색 </button>
			</div>
		</form>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>파일수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bvo">
			
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.registerDate }</td>
					<td>${bvo.read_count }</td>
					<td>${bvo.commentCount }</td>
					<td>${bvo.fileCount }</td>
				</tr>
				
			</c:forEach>
			

		</tbody>
	</table>
	
	<!-- 페이징 라인 -->
	<!-- prev -->
	<c:if test="${ph.prev }">
		<a href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> ◁ | </a>
	</c:if>
	
	<!-- 페이지숫자 -->
	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		<a href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a>
	</c:forEach>
	
	<!-- next -->
	<c:if test="${ph.next }">
		<a href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> | ▷ </a>
	</c:if>
	
	
	<script type="text/javascript">
		const isOk = `<c:out value="${isOk}" />`;
		console.log(isOk);
		if( isOk == 1){
			alert('삭제완료!');
		}
		
	</script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>