<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
<h2>글쓰기</h2>
<hr>
<div class="container-md">
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<form action="/board/register" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="title" class="form-label">제목</label> 
			<input
				type="text" name="title" class="form-control" id="title">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">작성자</label> 
			<input
				type="text" name="writer" class="form-control" id="writer" value="${authEmail }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">내용</label>
			<textarea name="content" class="form-control" id="content" rows="3"></textarea>
		</div>
		
		<!-- file 입력 라인 추가 -->
 		<div class="mb-3">
			<input type="file" name="files" class="form-control" id="files" multiple="multiple" style="display: none"><br>
			<!-- 파일 버튼 트리거 사용하기 위해서 주는 버튼 -->
			<button type="button" class="btn btn-primary" id="trigger">파일 업로드</button>
		</div>

		<!-- 파일 목록 표시라인 -->
 		<div class="mb-3" id="fileZone">
 			
		</div>
		<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
		<a href="/"><button type="button" class="btn btn-danger">취소</button></a>
		
	</form>
	</sec:authorize>
</div>
<script src="/resources/js/boardFile.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>