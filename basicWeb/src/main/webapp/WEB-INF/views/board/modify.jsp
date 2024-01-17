<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>

<div class="container-md">
	<h3>게시글 수정</h3>
	<hr>
	<form action="/board/modify" method="post">
		<div class="mb-3">
			<label for="bno" class="form-label">No.</label> 
			<input type="text" name="bno" class="form-control" id="bno" readonly value="${bvo.bno }">
		</div>
		<div class="mb-3">
			<label for="title" class="form-label">제목</label> 
			<input type="text" name="title" class="form-control" id="title" value="${bvo.title }">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">작성자</label> 
			<input type="text" name="writer" class="form-control" id="writer" readonly value="${bvo.writer }">
		</div>
		<div class="mb-3">
			<label for="reg_date" class="form-label">작성일</label> 
			<input type="text" name="reg_date" class="form-control" id="reg_date" readonly value="${bvo.regAt }">
		</div>
		<div class="mb-3">
			<label for="mod_date" class="form-label">수정일</label> 
			<input type="text" name="mod_date" class="form-control" id="mod_date" readonly value="${bvo.modAt }">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">내용</label>
			<input type="text" name="content" class="form-control" id="content" value="${bvo.content }">
		</div>
		<a href="/board/modify?bno=${bvo.bno }"><button type="submit" class="btn btn-success">수정</button></a> 
		<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">삭제</button></a> 
		<a href="/board/list"><button type="submit" class="btn btn-primary">목록</button></a><br>
	</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>