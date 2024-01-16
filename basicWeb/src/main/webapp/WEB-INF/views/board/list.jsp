<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/nav.jsp"></jsp:include>
<div class="container-md">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">조회수</th>
				<th scope="col">댓글</th>
				<th scope="col">등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<th scope="row">${bvo.bno}</th>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.cmtQty}</td>
					<td>${bvo.regAt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>