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
			<input type="hidden" id="id" value="${board.id}" />
			<div class="form-group">
				<label for="title">제목</label> <input value="${board.title}" type="text" class="form-control" placeholder="제목을 입력하세요." id="title">
			</div>

			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control summernote" rows="10" cols="" id="content" placeholder="내용을 입력하세요.">${board.content}</textarea>
			</div>

		</form>
		<button id="btn-update" class="btn btn-primary">수정</button>
	</div>
	<script>
		$('.summernote').summernote({
			placeholder : 'Hello Bootstrap 4',
			tabsize : 2,
			height : 300
		});
	</script>

	<script src="/js/board.js"></script>
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>
