<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
.brdContainer, .cmtContainer{
	width: 1000px;
	margin-left: 450px;
}
.btnContainer{
	display: flex;
	justify-content: center;
}
.btnContainer>a>button{
	width: 150px;
	margin-left: 20px;
	margin-right: 20px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<div class="brdContainer">
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
		<div class="btnContainer">
			<a href="/board/modify?bno=${bvo.bno }"><button class="btn btn-primary"> 수정 </button></a>
			<a href="/board/remove?bno=${bvo.bno }"><button class="btn btn-primary"> 삭제 </button></a>
			<a href="/board/list"><button class="btn btn-warning"> 리스트 </button></a>
		</div>
	</div>

	<!-- 댓글 라인 -->
	<div class="cmtContainer">
		<!-- 댓글 등록 라인 -->
		<div class="input-group mb-3">
	  		<span class="input-group-text" id="cmtWriter">Writer</span>
	 		 <input type="text" class="form-control" placeholder="Test Comment" id="cmtText">
			<button type="button" class="btn btn-primary" id="cmtPostBtn">등록</button>
	
		</div>
		
	
		<!-- 댓글 표시 라인 -->
		<ul class="list-group" id="cmtListArea">
		  
			  
		</ul>
		<!-- 댓글 페이징 라인 -->
		<div>
			<div>
				<button type="button" id="moreBtn" data-page="1" 
				class="btn btn-secondary" style="visibility:hidden">MOER+</button>
			</div>
		</div>
		
		
		<!-- 모달창 라인  -->
		<div class="modal" id="myModal" tabindex="-1">
		  <div class="modal-dialog">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title">Writer</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		        <div class="input-group mb-3">
			         <input type="text" class="form-control" placeholder="Test Comment" id="cmtTextMod">
					 <button type="button" class="btn btn-primary" id="cmtModBtn">등록</button>
				 </div>
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
	</div>
	
	
	
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`
		const isMod = `<c:out value="${isMod}" />`;
		if( isMod == 1){
			alert('수정완료!');
		}
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>