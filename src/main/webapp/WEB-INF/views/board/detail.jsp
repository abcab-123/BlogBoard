<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>글 작성 사이트</title>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: auto;
	background-color: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
}

input[type="text"], textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ced4da;
	border-radius: 4px;
}

button#btn-save {
	background-color: #007bff;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button#btn-save:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<%@ include file="../layout/header.jsp"%>

	<div class="container">
		<form>
			<div>
				작성자: <span id="username"><i>${board.user.username} </i></span> 글 번호: <span id="id"><i>${board.id} </i></span>
			</div>
			<br />
			<div class="form-group">
				<h3>${board.title}</h3>
			</div>
			<hr />
			<div class="form-group">
				<div>${board.content}</div>
			</div>
			<hr />
			<br />
			<button class="btn btn-secondary" onclick="history.back(); return false;">돌아가기</button>
			<c:if test="${board.user.id == principal.user.id}">
				<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
				<button id="btn-delete" class="btn btn-danger">삭제</button>
			</c:if>

		</form>

		<hr />
		<div class="card">
			<div class="card-header">댓글 리스트</div>
			<ul id="reply-box" class="list-group">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex">
							<div class="font-italic">작성자: ${reply.user.username}&nbsp;</div>
							<c:if test="${reply.user.id eq principal.user.id}">
								<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="card">
			<form>
				<input type="hidden" id="userId" value="${principal.user.id}" /> <input type="hidden" id="boardId" value="${board.id}" />
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
				</div>
			</form>
		</div>
	</div>
	<script src="/js/board.js"></script>
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>
