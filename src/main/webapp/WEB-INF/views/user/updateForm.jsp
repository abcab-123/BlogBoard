<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" value="${principal.user.username}" class="form-control" placeholder="아이디를 입력하세요." id="username" readonly>
		</div>

		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." id="password">
			</div>
		</c:if>

		<div class="form-group">
			<label for="email">이메일</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="이메일을 입력하세요." id="email" readonly>
		</div>

	</form>

	<button id="btn-update" class="btn btn-primary">회원수정</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>