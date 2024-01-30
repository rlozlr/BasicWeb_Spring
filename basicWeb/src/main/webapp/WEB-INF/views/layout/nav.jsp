<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">홈</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
    data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" 
    aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="/board/list">드림</a>
        </li>
        
        <sec:authorize access="isAuthenticated()">
        	<sec:authentication property="principal.mvo.email" var="authEmail"/>
        	<sec:authentication property="principal.mvo.nickName" var="authNick"/>
        	<sec:authentication property="principal.mvo.authList" var="auths"/>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/board/register">글쓰기</a>
	        </li>
	        <c:choose>
	        	<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
	        		<li class="nav-item">
	        			<a class="nav-link" href="/member/list">회원리스트 ${authNick }(${authEmail }/ADMIN)</a>
	        		</li>
	        	</c:when>
	        	<c:otherwise>
	        		<li class="nav-item">
	        			<a class="nav-link" href="/member/modify">회원정보수정 ${authNick } (${authEmail })</a>
	        		</li>
	        	</c:otherwise>
	 		</c:choose>
	 		<li class="nav-item">
	 			<a class="nav-link" href="" id="logoutLink">LogOut</a>
	 		</li>
	 		<form action="/member/logout" method="post" id="logoutForm">
	 			<input type="hidden" name="email" value="${authEmail }">
	 		</form>
        </sec:authorize>
        
        <sec:authorize access="isAnonymous()">
        <li class="nav-item">
          <a class="nav-link" href="/member/login">로그인</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/member/register">회원가입</a>
        </li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>

<script type="text/javascript">
document.getElementById('logoutLink').addEventListener('click', (e)=>{
	e.preventDefault();
	document.getElementById('logoutForm').submit();
});
</script>