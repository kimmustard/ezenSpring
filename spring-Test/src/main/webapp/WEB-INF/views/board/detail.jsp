<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<c:set value="${boardDTO.bvo }" var="bvo"></c:set>
	<table class="table table-hover">
			<tr>
				<th>번호</th>
				<td>${bvo.bno}</td>
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
				<td>${bvo.registerDate } </td>
			</tr>
			<tr>	
				<th>조회수</th>
				<td>${bvo.read_count }</td>
			</tr>

	</table>
	
	<!-- File -->
	<c:set value="${boardDTO.flist}" var="flist"></c:set>
	<div>
		<ul>
			<!-- 파일 개수만큼 li를 추가하여 파일을 표시 1일 경우만 표시 -->
			<!-- li
				div => img 그림표시
				div => div 파일이름 작성일자 span 크기 설정 -->
				<!-- 하나의 파일만 따와서 fvo로 이름 저장 -->
				<c:forEach items="${flist}" var="fvo">
					<li>
						<c:choose>
							<c:when test="${fvo.file_type > 0 }">
								<div id="fileListArea">
								<!-- /upload/month/day/uuid_file_name 이 필요하다 -->
									<img alt="그림 없음" src="/upload/${fn:replace(fvo.save_dir,'\\','/')}/${fvo.uuid}_th_${fvo.file_name}">
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<!-- file 아이콘 같은 모양 값으로 넣을 수 있음. -->
								</div>
							</c:otherwise>
						</c:choose>
					
						<div>${fvo.file_name }</div>
						
						

					</li>		
				</c:forEach>
		</ul>
	
	
	
	</div>
	
	
	
	<a href="/board/modify?bno=${bvo.bno} "><button type="button"> 수정 </button></a>
	<a href="/board/remove?bno=${bvo.bno} "><button type="button"> 삭제 </button></a>
	<!-- Comment Line -->
	<div>
	<br>
		<!-- 댓글 작성 라인 -->
		<div>
			<span id="cmtWriter">${ses.id}</span>
			<input type="text" id="cmtText" placeholder="댓글을 작성하세요...">
			<button type="button" id="cmtPostBtn"> 댓글 등록 </button>
		</div>
		<br>
		
		<!-- 댓글 표시 라인 -->
		<div id="cmtListArea">
			
			
			
		
		</div>
		
	</div>

<script type="text/javascript">
	const bnoVal = `<c:out value="${bvo.bno}" />`
	const sesId = `<c:out value="${ses.id}" />`
	console.log(bnoVal);
	console.log(sesId);
</script>
<script type="text/javascript" src="/resources/js/boardComment.js"></script>
<script type="text/javascript">
	getCommentList(bnoVal);
</script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>