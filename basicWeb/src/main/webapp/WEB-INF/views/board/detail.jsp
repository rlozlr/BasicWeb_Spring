<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
	<div class="mb-3">
		<label for="bno" class="form-label">No.</label> 
		<input type="text" name="bno" class="form-control" id="bno" readonly value="${bvo.bno }">
	</div>
	<div class="mb-3">
		<label for="title" class="form-label">제목</label>
		<input type="text" name="title" class="form-control" id="title" readonly value="${bvo.title }">
	</div>
	<div class="mb-3">
		<label for="writer" class="form-label">작성자</label>
		<input type="text" name="writer" class="form-control" id="writer" readonly value="${bvo.writer }">
	</div>
	<div class="mb-3">
		<label for="reg_date" class="form-label">작성일</label> 
		<span class="badge text-bg-primary">${bvo.readCount }</span> 
		<input type="text" name="reg_date" class="form-control" id="reg_date" readonly value="${bvo.regAt }">
	</div>
	<div class="mb-3">
		<label for="mod_date" class="form-label">수정일</label> 
		<input type="text" name="mod_date" class="form-control" id="mod_date" readonly value="${bvo.modAt }">
	</div>
	<div class="mb-3">
		<label for="content" class="form-label">내용</label>
		<input type="text" name="content" class="form-control" id="content" readonly value="${bvo.content }">
	</div>
	<a href="/board/modify?bno=${bvo.bno }"><button type="submit" class="btn btn-success">수정</button></a> 
	<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">삭제</button></a> 
	<a href="/board/list"><button type="submit" class="btn btn-primary">목록</button></a><br>
	<br><hr>
	<!-- 댓글 등록 라인 -->
	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter">Writer</span> 
		<input type="text" class="form-control" id="cmtText" aria-label="Amount (to the nearest dollar)">
		<button type="button" class="btn btn-success" id="cmtPostBtn">등록</button>
	</div>

	<!-- 댓글 표시 라인 -->
	<ul class="list-group list-group-flush" id="cmtListArea">
		<li class="list-group-item">
			<div class="mb-3">
				<div class="fw-bold">Writer <span class="badge rounded-pill text-bg-warning">modAt</span></div>
				content
			</div>
		</li>
	</ul>
</div>

<script type="text/javascript">
	 let bnoVal = `<c:out value="${bvo.bno}"/>`;
	 console.log(bnoVal);
</script>
<script src="/resources/js/boardComment.js"></script>
<script type="text/javascript">
	spreadCommentList(bnoVal);
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>